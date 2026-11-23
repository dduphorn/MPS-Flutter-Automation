package AutomationCode;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Pure-Java planner for SentryLink rate blocks that may cross midnight.
 *
 * WHY: the old code built rate block times with CommonWeb.AddTimeToCurrentTime(min,"HH:mm"),
 * which drops the date and re-reads the clock on every call. Around midnight the free window
 * "wrapped" (start 23:50, end 01:50) and the tests either raised
 * "Code Needs to be added to handle rateblocks around midnight" or built bad blocks.
 *
 * HOW (same pattern as SentryLink rate block group 277):
 *  - 'now' is read ONCE and every window is an absolute LocalDateTime.
 *  - Every window is split at midnight into per-day blocks. Each block has exactly ONE day checked.
 *  - Seams inside a day step by one minute (09:40 then 09:41, group 277 style). With EXCLUSIVE_END_TIMES = true
 *    block N would end at the minute block N+1 starts (09:41 then 09:41); not used, see EXCLUSIVE_END_TIMES.
 *    The midnight seam is always 23:59 then 00:00 of the next day. 24:00 is never used.
 *  - Today AND tomorrow are always built (even at noon) so every run takes the same path.
 *  - The remaining days of the week get one "All Day" rate block (see ADD_ALL_DAY_BLOCK_FOR_OTHER_DAYS).
 *
 * END TIMES (see EXCLUSIVE_END_TIMES, default false = inclusive): a window [start, end) is written as Start Time = start,
 * End Time = end - 1 minute (23:59 when end is midnight), like group 277 (18:00 -> 18:01).
 * Meter behaviour seen on 2026-11-19/20: the scheduler treats the typed End Time as exclusive (so "00:00-00:07" leaves a
 * 1-minute default-rate gap 00:07-00:08), but the max-time / minutes-to-free logic treats the typed end minute as PAYABLE.
 * With inclusive ends the parker's session therefore ends exactly at the free start (I3078F); with exclusive ends it ran
 * 1 minute past it (I3079F 2026-11-20). A 23:59 end is compressed to 24:00 by the meter, so the midnight seam has no gap.
 *
 * No Selenium / browser code in here, so it can be unit tested (see src/test/java/AutomationCode/RateBlockPlannerTest.java).
 */
public final class RateBlockPlanner
{
	/**
	 * ASSUMPTION (easy to change): days of the week that are NOT today/tomorrow (or any other day a window touches)
	 * get ONE "All Day" rate block with all of those days checked, so the meter never sees a day with no block
	 * (undefined behaviour). Set to false to leave those days empty.
	 */
	public static boolean ADD_ALL_DAY_BLOCK_FOR_OTHER_DAYS = true;

	/**
	 * false (default): End Time = next start - 1 minute (group 277 style, 18:00 then 18:01). The meter's scheduler leaves a
	 * 1-minute default-rate gap at each seam, but its max-time logic treats the typed end minute as payable, so a parker's
	 * session ends exactly at the free start (I3078F 2026-11-20 09:17: free 09:41, session end 09:41).
	 * true: End Time = the start of the next block (no scheduler gap). Evidence I3079F 2026-11-20 09:46: rate block typed
	 * 00:00-10:11, free 10:11, but the meter's countdown / max time ran to 10:12, so sessions ended 1 minute after the free
	 * start and expected end times were off. Do not use true unless the meter changes.
	 */
	public static boolean EXCLUSIVE_END_TIMES = false;

	/** Name suffix used for the "All Day" block that covers the other days of the week. */
	public static final String OTHER_DAYS_SUFFIX = "Other";

	public static final DateTimeFormatter HHMM = DateTimeFormatter.ofPattern("HH:mm");
	public static final DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern("HH:mm:ss");
	public static final DateTimeFormatter H12 = DateTimeFormatter.ofPattern("h:mm a", Locale.US);
	public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private RateBlockPlanner() {}

	public enum BlockType
	{
		RATE("RateBlock"), FREE("FreeRateBlock"), NO_PARKING("NoParkingRateBlock");
		private final String strNameSuffix;
		BlockType(String strNameSuffix) {this.strNameSuffix = strNameSuffix;}
	}

	/** An absolute window [start, end) of one type. */
	public static final class Window
	{
		public final LocalDateTime start;
		public final LocalDateTime end;
		public final BlockType type;
		public final String rateSetName;

		public Window(LocalDateTime start, LocalDateTime end, BlockType type, String rateSetName)
		{
			if (start == null || end == null) {throw new IllegalArgumentException("Window start/end can not be null");}
			if (!end.isAfter(start)) {throw new IllegalArgumentException("Window end (" + end + ") must be after start (" + start + ")");}
			this.start = start.truncatedTo(ChronoUnit.MINUTES);
			this.end = end.truncatedTo(ChronoUnit.MINUTES);
			this.type = type;
			this.rateSetName = rateSetName;
		}

		@Override public String toString() {return type + "[" + start + " -> " + end + ") " + rateSetName;}
	}

	/** One SentryLink rate block, as it is typed into the Rate Block Setting form. */
	public static final class PlannedBlock
	{
		public final String name;
		public final Set<DayOfWeek> days;
		public final boolean allDay;
		public final LocalTime startTime;
		/** End Time as typed into SentryLink (exclusive or inclusive, see EXCLUSIVE_END_TIMES). Never 24:00. */
		public final LocalTime endTime;
		/** Absolute moment this block really ends (exclusive), e.g. 2026-11-20 00:00 for a 23:59 end. Null for the All Day block. */
		public final LocalDateTime endExclusive;
		public final BlockType type;
		public final String rateSetName;
		/** Calendar date the block is for (null for the All Day "other days" block). */
		public final LocalDate date;

		PlannedBlock(String name, Set<DayOfWeek> days, boolean allDay, LocalTime startTime, LocalTime endTime, BlockType type, String rateSetName, LocalDate date)
		{this(name, days, allDay, startTime, endTime, type, rateSetName, date, null);}

		PlannedBlock(String name, Set<DayOfWeek> days, boolean allDay, LocalTime startTime, LocalTime endTime, BlockType type, String rateSetName, LocalDate date, LocalDateTime endExclusive)
		{
			this.endExclusive = endExclusive;
			this.name = name;
			this.days = Collections.unmodifiableSet(EnumSet.copyOf(days));
			this.allDay = allDay;
			this.startTime = startTime;
			this.endTime = endTime;
			this.type = type;
			this.rateSetName = rateSetName;
			this.date = date;
		}

		public String getStartHHmm() {return startTime.format(HHMM);}
		public String getEndHHmm() {return endTime.format(HHMM);}
		public DayOfWeek getSingleDay() {return days.size() == 1 ? days.iterator().next() : null;}
		/** Hours the app shows for this block, e.g. "12:00 am-9:41 am" (lower case, h:mm a). */
		public String getHours12() {return hours12(startTime, endTime);}

		@Override public String toString()
		{
			StringBuilder sb = new StringBuilder();
			for (DayOfWeek d : days) {if (sb.length() > 0) {sb.append(",");} sb.append(dayAbbr(d));}
			return name + " {" + sb + "} " + (allDay ? "All Day" : getStartHHmm() + "-" + getEndHHmm()) + " [" + rateSetName + "]";
		}
	}

	/** The full plan plus the absolute times the validation steps need. */
	public static final class Plan
	{
		public final LocalDateTime now;
		public final List<Window> windows;
		public final List<PlannedBlock> blocks;

		Plan(LocalDateTime now, List<Window> windows, List<PlannedBlock> blocks)
		{
			this.now = now;
			this.windows = Collections.unmodifiableList(windows);
			this.blocks = Collections.unmodifiableList(blocks);
		}

		/** First non-RATE window of the given type, or null. */
		public Window getWindow(BlockType type)
		{
			for (Window w : windows) {if (w.type == type) {return w;}}
			return null;
		}
		public LocalDateTime getFreeStart() {Window w = getWindow(BlockType.FREE); return w == null ? null : w.start;}
		/** Moment the free window ends (rate resumes). Exclusive end, e.g. 14:15 when the free block's End Time is 14:14. */
		public LocalDateTime getFreeEnd() {Window w = getWindow(BlockType.FREE); return w == null ? null : w.end;}

		public String getFreeStartHHmm() {return fmt(getFreeStart(), HHMM);}
		public String getFreeEndHHmm() {return fmt(getFreeEnd(), HHMM);}
		public String getFreeStartHHmmss() {return fmt(getFreeStart(), HHMMSS);}
		public String getFreeStart12() {return fmt(getFreeStart(), H12);}
		public String getFreeEnd12() {return fmt(getFreeEnd(), H12);}
		public String getFreeStartDateTime() {return fmt(getFreeStart(), DATE_TIME);}
		public String getFreeEndDateTime() {return fmt(getFreeEnd(), DATE_TIME);}

		/** The block the parker is paying in at 'at' (dated per-day block first, else the All Day block for that weekday), or null. */
		public PlannedBlock getCurrentBlock(LocalDateTime at)
		{
			for (PlannedBlock b : blocks)
			{
				if (b.allDay || b.date == null || b.endExclusive == null) {continue;}
				LocalDateTime dtStart = b.date.atTime(b.startTime);
				if (!at.isBefore(dtStart) && at.isBefore(b.endExclusive)) {return b;}
			}
			for (PlannedBlock b : blocks) {if (b.allDay && b.days.contains(at.getDayOfWeek())) {return b;}}
			return null;
		}
		/** Hours of the current block as the app shows them ("12:00 am-11:59 pm"), or null. */
		public String getCurrentBlockHours(LocalDateTime at) {PlannedBlock b = getCurrentBlock(at); return b == null ? null : b.getHours12();}

		/**
		 * Compact schedule kept in objDictionary (strRateBlockPlanSchedule) so the hours can be recomputed at validation time
		 * (the current block may change between planning and validating, e.g. across midnight).
		 * Dated block: "yyyy-MM-dd|HH:mm|HH:mm|yyyy-MM-dd HH:mm:ss(exclusive end)"; All Day block: "ALL|MONDAY,TUESDAY|00:00|23:59". Joined with ";".
		 */
		public String encodeSchedule()
		{
			StringBuilder sb = new StringBuilder();
			for (PlannedBlock b : blocks)
			{
				if (sb.length() > 0) {sb.append(";");}
				if (b.allDay || b.date == null)
				{
					StringBuilder sbDays = new StringBuilder();
					for (DayOfWeek d : b.days) {if (sbDays.length() > 0) {sbDays.append(",");} sbDays.append(d.name());}
					sb.append("ALL|").append(sbDays).append("|").append(b.getStartHHmm()).append("|").append(b.getEndHHmm());
				}
				else
				{
					sb.append(b.date).append("|").append(b.getStartHHmm()).append("|").append(b.getEndHHmm()).append("|").append(b.endExclusive.format(DATE_TIME));
				}
			}
			return sb.toString();
		}

		/** True when at least one window crosses midnight. */
		public boolean crossesMidnight()
		{
			for (Window w : windows) {if (!w.start.toLocalDate().equals(w.end.minusMinutes(1).toLocalDate())) {return true;}}
			return false;
		}

		public String describe()
		{
			StringBuilder sb = new StringBuilder("RateBlockPlan now=" + now.format(DATE_TIME) + " (" + dayAbbr(now.getDayOfWeek()) + ")");
			for (PlannedBlock b : blocks) {sb.append(" | ").append(b);}
			return sb.toString();
		}
		@Override public String toString() {return describe();}
	}

	// ---------------------------------------------------------------------------------------------
	// Builders for the scenarios used by SENTRYLINK_UpdateRateSetValues
	// ---------------------------------------------------------------------------------------------

	/**
	 * RATE TO FREE: rate until now+minutesBeforeFree, free for freeMinutes, then rate.
	 * Free window = [now+MBF, now+MBF+FREE).
	 */
	public static Plan rateToFree(LocalDateTime now, int intMinutesBeforeFree, int intFreeMinutes, String strRateType, String strRateSetName, String strFreeRateSetName)
	{
		if (intFreeMinutes <= 0) {throw new IllegalArgumentException("Free minutes must be > 0");}
		LocalDateTime dtNow = now.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime dtFreeStart = dtNow.plusMinutes(intMinutesBeforeFree);
		LocalDateTime dtFreeEnd = dtFreeStart.plusMinutes(intFreeMinutes);
		List<Window> lst = new ArrayList<>();
		lst.add(new Window(dtFreeStart, dtFreeEnd, BlockType.FREE, strFreeRateSetName));
		return plan(dtNow, strRateType, strRateSetName, lst);
	}

	/**
	 * FREE TO RATE: free from now+minutesOfFreeTimeBeforeCurrentTime (usually 0, may be negative) until now+freeMinutes, then rate.
	 * Same arithmetic as the old code (free end = now + FREE).
	 */
	public static Plan freeToRate(LocalDateTime now, int intMinutesOfFreeTimeBeforeCurrentTime, int intFreeMinutes, String strRateType, String strRateSetName, String strFreeRateSetName)
	{
		LocalDateTime dtNow = now.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime dtFreeStart = dtNow.plusMinutes(intMinutesOfFreeTimeBeforeCurrentTime);
		LocalDateTime dtFreeEnd = dtNow.plusMinutes(intFreeMinutes);
		List<Window> lst = new ArrayList<>();
		lst.add(new Window(dtFreeStart, dtFreeEnd, BlockType.FREE, strFreeRateSetName));
		return plan(dtNow, strRateType, strRateSetName, lst);
	}

	/**
	 * RATE TO NO PARKING (not wired into CommonWeb yet): rate until now+minutesBeforeNoParking, no parking for N minutes, then rate.
	 */
	public static Plan rateToNoParking(LocalDateTime now, int intMinutesBeforeNoParking, int intNoParkingMinutes, String strRateType, String strRateSetName, String strNoParkingRateSetName)
	{
		LocalDateTime dtNow = now.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime dtStart = dtNow.plusMinutes(intMinutesBeforeNoParking);
		List<Window> lst = new ArrayList<>();
		lst.add(new Window(dtStart, dtStart.plusMinutes(intNoParkingMinutes), BlockType.NO_PARKING, strNoParkingRateSetName));
		return plan(dtNow, strRateType, strRateSetName, lst);
	}

	// ---------------------------------------------------------------------------------------------
	// Core planner
	// ---------------------------------------------------------------------------------------------

	/**
	 * Build per-day blocks. Gaps between the special windows are filled with the RATE set.
	 * Days covered = from min(today, first window day) to max(tomorrow, last window day). At most 7 days.
	 */
	public static Plan plan(LocalDateTime now, String strRateType, String strRateSetName, List<Window> specialWindows)
	{
		LocalDateTime dtNow = now.truncatedTo(ChronoUnit.MINUTES);
		List<Window> lstWindows = new ArrayList<>(specialWindows);
		lstWindows.sort((a, b) -> a.start.compareTo(b.start));
		for (int i = 1; i < lstWindows.size(); i++)
		{
			if (lstWindows.get(i).start.isBefore(lstWindows.get(i - 1).end))
			{throw new IllegalArgumentException("Rate block windows overlap: " + lstWindows.get(i - 1) + " and " + lstWindows.get(i));}
		}
		LocalDate dFirst = dtNow.toLocalDate();
		LocalDate dLast = dtNow.toLocalDate().plusDays(1);
		for (Window w : lstWindows)
		{
			if (w.start.toLocalDate().isBefore(dFirst)) {dFirst = w.start.toLocalDate();}
			LocalDate dEnd = w.end.minusMinutes(1).toLocalDate();
			if (dEnd.isAfter(dLast)) {dLast = dEnd;}
		}
		long lDays = ChronoUnit.DAYS.between(dFirst, dLast) + 1;
		if (lDays > 7) {throw new IllegalArgumentException("Rate block windows span " + lDays + " days; a week only has 7 day checkboxes");}

		String strRateName = (strRateType == null ? "Fixed" : strRateType) + BlockType.RATE.strNameSuffix;
		List<PlannedBlock> lstBlocks = new ArrayList<>();
		EnumSet<DayOfWeek> setCovered = EnumSet.noneOf(DayOfWeek.class);
		for (LocalDate d = dFirst; !d.isAfter(dLast); d = d.plusDays(1))
		{
			DayOfWeek dow = d.getDayOfWeek();
			setCovered.add(dow);
			LocalDateTime dtDayStart = d.atStartOfDay();
			LocalDateTime dtDayEnd = d.plusDays(1).atStartOfDay();
			LocalDateTime dtCursor = dtDayStart;
			Map<String, Integer> mapNameCount = new HashMap<>();
			for (Window w : lstWindows)
			{
				LocalDateTime s = max(w.start, dtDayStart);
				LocalDateTime e = min(w.end, dtDayEnd);
				if (!e.isAfter(s)) {continue;}
				if (s.isAfter(dtCursor)) {addBlock(lstBlocks, mapNameCount, strRateName, BlockType.RATE, strRateSetName, d, dtCursor, s);}
				String strName = (w.type == BlockType.RATE) ? strRateName : w.type.strNameSuffix;
				addBlock(lstBlocks, mapNameCount, strName, w.type, w.rateSetName, d, s, e);
				dtCursor = e;
			}
			if (dtDayEnd.isAfter(dtCursor)) {addBlock(lstBlocks, mapNameCount, strRateName, BlockType.RATE, strRateSetName, d, dtCursor, dtDayEnd);}
		}
		if (ADD_ALL_DAY_BLOCK_FOR_OTHER_DAYS)
		{
			EnumSet<DayOfWeek> setOther = EnumSet.complementOf(setCovered);
			if (!setOther.isEmpty())
			{lstBlocks.add(new PlannedBlock(strRateName + "_" + OTHER_DAYS_SUFFIX, setOther, true, LocalTime.MIDNIGHT, LocalTime.of(23, 59), BlockType.RATE, strRateSetName, null));}
		}
		return new Plan(dtNow, lstWindows, lstBlocks);
	}

	private static void addBlock(List<PlannedBlock> lstBlocks, Map<String, Integer> mapNameCount, String strBaseName, BlockType type, String strRateSetName, LocalDate d, LocalDateTime s, LocalDateTime e)
	{
		String strName = strBaseName + "_" + dayAbbr(d.getDayOfWeek());
		int intCount = mapNameCount.merge(strName, 1, Integer::sum);
		if (intCount > 1) {strName = strName + "_" + intCount;}
		LocalTime tStart = s.toLocalTime();
		LocalTime tEnd = (type == BlockType.FREE) ? freeEndTimeFor(e, d) : endTimeFor(s, e, d);
		lstBlocks.add(new PlannedBlock(strName, EnumSet.of(d.getDayOfWeek()), false, tStart, tEnd, type, strRateSetName, d, e));
	}

	/**
	 * End Time typed into SentryLink for the piece [s, e) of day d. Never 24:00.
	 * Exclusive: e (23:59 when e is midnight). Edge case: a seam exactly at 23:59 (next piece is [23:59, 24:00)) would give
	 * two blocks ending at 23:59, so the earlier block keeps the old inclusive end (23:58) and the 23:59 piece is 23:59-23:59.
	 * Inclusive (EXCLUSIVE_END_TIMES = false): e - 1 minute.
	 */
	static LocalTime endTimeFor(LocalDateTime s, LocalDateTime e, LocalDate d)
	{
		LocalDateTime dtDayEnd = d.plusDays(1).atStartOfDay();
		if (!EXCLUSIVE_END_TIMES || !e.isBefore(dtDayEnd)) {return e.minusMinutes(1).toLocalTime();}
		if (e.toLocalTime().equals(LAST_MINUTE)) {return LAST_MINUTE.minusMinutes(1);}
		return e.toLocalTime();
	}

	private static final LocalTime LAST_MINUTE = LocalTime.of(23, 59);

	/**
	 * End Time typed for a FREE block: the same minute the next block starts (free 20:21-20:51, rate 20:51-23:59), matching the
	 * legacy SentryLink setup. Ending free one minute early (20:50) cost the parker the last free minute and made the meter's
	 * MaxRemaining 224.5 instead of 225 in A2067F (2026-11-22). A free piece that runs to midnight still ends at 23:59 (never 24:00).
	 * Rate blocks keep endTimeFor (rate 00:00-20:20 before free 20:21), which the I3079F evidence needs.
	 */
	static LocalTime freeEndTimeFor(LocalDateTime e, LocalDate d)
	{
		LocalDateTime dtDayEnd = d.plusDays(1).atStartOfDay();
		if (!e.isBefore(dtDayEnd)) {return LAST_MINUTE;}
		return e.toLocalTime();
	}


	/** "12:00 am-9:41 am" (the app's Hours of Enforcement format). */
	public static String hours12(LocalTime tStart, LocalTime tEnd) {return (tStart.format(H12) + "-" + tEnd.format(H12)).toLowerCase(Locale.US);}

	/** Hours of the block that contains 'at', from an encoded schedule (Plan.encodeSchedule); null if none / unparsable. */
	public static String currentBlockHoursFromSchedule(String strSchedule, LocalDateTime at)
	{
		if (strSchedule == null || strSchedule.trim().isEmpty() || at == null) {return null;}
		try
		{
			String strAllDayHours = null;
			for (String strBlock : strSchedule.split(";"))
			{
				String[] arr = strBlock.split("\\|");
				if (arr.length < 4) {continue;}
				if (arr[0].equals("ALL"))
				{
					LocalTime tStart = LocalTime.parse(arr[2], HHMM);
					LocalTime tEnd = LocalTime.parse(arr[3], HHMM);
					for (String strDay : arr[1].split(",")) {if (!strDay.isEmpty() && DayOfWeek.valueOf(strDay) == at.getDayOfWeek() && strAllDayHours == null) {strAllDayHours = hours12(tStart, tEnd);}}
					continue;
				}
				LocalDate dDate = LocalDate.parse(arr[0]);
				LocalTime tStart = LocalTime.parse(arr[1], HHMM);
				LocalTime tEnd = LocalTime.parse(arr[2], HHMM);
				LocalDateTime dtEndExclusive = LocalDateTime.parse(arr[3], DATE_TIME);
				LocalDateTime dtStart = dDate.atTime(tStart);
				if (!at.isBefore(dtStart) && at.isBefore(dtEndExclusive)) {return hours12(tStart, tEnd);}
			}
			return strAllDayHours;
		}
		catch (Exception e) {return null;}
	}

	/**
	 * Hours of Enforcement the app should show right now for the current RateBlockPlanner plan, or null when the plan keys
	 * are missing or stale (strFreeParkingStartDateTime must match strMinutesBeforeFreeStart, so a legacy path that only set
	 * strMinutesBeforeFreeStart is never mixed with an older plan).
	 */
	public static String currentRateBlockHours(Map<String, String> objDictionary, LocalDateTime at)
	{
		if (objDictionary == null) {return null;}
		String strSchedule = objDictionary.get("strRateBlockPlanSchedule");
		if (strSchedule == null) {return null;}
		String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
		LocalDateTime dtFreeStart = parseDateTime(objDictionary.get("strFreeParkingStartDateTime"));
		if (strMinutesBeforeFreeStart != null)
		{
			try {if (dtFreeStart == null || !dtFreeStart.toLocalTime().equals(parseTime(strMinutesBeforeFreeStart))) {return null;}}
			catch (Exception e) {return null;}
		}
		String strHours = currentBlockHoursFromSchedule(strSchedule, at);
		return strHours != null ? strHours : objDictionary.get("strCurrentRateBlockHours");
	}

	/**
	 * Wrap-safe minutes (ceil) from 'now' until a time of day, preferring the dated strFreeParkingStartDateTime when its time
	 * of day matches. Returns null when the dated value is missing / does not match / is not in the future (caller keeps its old math).
	 */
	public static Long secondsUntilDated(String strTarget, String strTargetDateTime, LocalDateTime now)
	{
		try
		{
			LocalDateTime dt = parseDateTime(strTargetDateTime);
			if (dt == null || !dt.toLocalTime().withNano(0).equals(parseTime(strTarget).withNano(0))) {return null;}
			long l = secondsBetween(now, dt);
			return l > 0 ? Long.valueOf(l) : null;
		}
		catch (Exception e) {return null;}
	}

	/**
	 * Dated free start of the current plan when the keys are valid (strFreeParkingStartDateTime matches strMinutesBeforeFreeStart)
	 * and it is still ahead of 'now'; null otherwise (old paths keep their old expectations).
	 */
	public static LocalDateTime datedFreeStart(Map<String, String> objDictionary, LocalDateTime now)
	{
		if (objDictionary == null) {return null;}
		try
		{
			String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
			LocalDateTime dt = parseDateTime(objDictionary.get("strFreeParkingStartDateTime"));
			if (dt == null || strMinutesBeforeFreeStart == null) {return null;}
			if (!dt.toLocalTime().equals(parseTime(strMinutesBeforeFreeStart))) {return null;}
			return dt.isAfter(now) ? dt : null;
		}
		catch (Exception e) {return null;}
	}

	/** Minutes from now until target, rounded half-up like the meter's valid-time-remaining (Math.round(seconds / 60)). */
	public static int roundedMinutesUntil(LocalDateTime target, LocalDateTime now) {return (int) Math.round(secondsBetween(now, target) / 60.0);}

	/** "MMM dd, hh:mm a" exactly like AddTimeToExistingTime (SimpleDateFormat, default locale), e.g. "Nov 20, 10:45 AM". */
	public static String formatSessionTime(LocalDateTime dt)
	{
		return new java.text.SimpleDateFormat("MMM dd, hh:mm a").format(java.util.Date.from(dt.atZone(java.time.ZoneId.systemDefault()).toInstant()));
	}

	// ---------------------------------------------------------------------------------------------
	// Wrap-safe time helpers used by Meter.java expected value calculations
	// ---------------------------------------------------------------------------------------------

	/**
	 * Seconds from 'from' to 'to' when only the time of day is known. The result is normalized into
	 * (-12h, +12h], so 23:50 -> 00:10 is +20 minutes (not -23h40m) and 00:10 -> 23:50 is -20 minutes.
	 * Same-day results inside +/-12h are unchanged from the old (to - from) math.
	 */
	public static long wrapSafeSecondsBetween(LocalTime from, LocalTime to)
	{
		long lDiff = to.toSecondOfDay() - from.toSecondOfDay();
		if (lDiff > 43200) {lDiff -= 86400;}
		else if (lDiff <= -43200) {lDiff += 86400;}
		return lDiff;
	}

	/** Absolute seconds between two date-times. */
	public static long secondsBetween(LocalDateTime from, LocalDateTime to) {return Duration.between(from, to).getSeconds();}

	private static final DateTimeFormatter LENIENT_TIME = DateTimeFormatter.ofPattern("H:mm[:ss]");

	/** Parses "HH:mm", "HH:mm:ss" (also a single digit hour like "9:05:00", which SimpleDateFormat used to accept). */
	public static LocalTime parseTime(String strTime)
	{
		String strTrimmed = strTime.trim();
		try {return LocalTime.parse(strTrimmed);}
		catch (Exception e) {return LocalTime.parse(strTrimmed, LENIENT_TIME);}
	}

	/** Parses the DATE_TIME format used for the strFreeParking*DateTime dictionary keys; null if blank/invalid. */
	public static LocalDateTime parseDateTime(String strDateTime)
	{
		if (strDateTime == null || strDateTime.trim().isEmpty()) {return null;}
		try {return LocalDateTime.parse(strDateTime.trim(), DATE_TIME);} catch (Exception e) {return null;}
	}

	/**
	 * Seconds elapsed since strStart (HH:mm[:ss]) at 'now'. If strStartDateTime (absolute) is given and its time of day
	 * matches strStart, the absolute value is used; otherwise time-of-day math that only unwraps a start from late yesterday.
	 */
	public static long elapsedSeconds(String strStart, String strStartDateTime, LocalDateTime now)
	{
		LocalTime tStart = parseTime(strStart);
		LocalDateTime dtStart = parseDateTime(strStartDateTime);
		if (dtStart != null && dtStart.toLocalTime().withNano(0).equals(tStart.withNano(0))) {return secondsBetween(dtStart, now);}
		long lDiff = now.toLocalTime().toSecondOfDay() - tStart.toSecondOfDay();
		// Only the "started late yesterday" case is unwrapped (23:50 -> 00:10 = +20 min instead of -23h40m).
		// Large positive values are left alone so an all-day free block that started at 00:00 still reads e.g. 14h at 14:00.
		// A start that is still in the future but after midnight is covered by the absolute strStartDateTime above.
		if (lDiff <= -43200) {lDiff += 86400;}
		return lDiff;
	}

	public static String dayAbbr(DayOfWeek d) {return d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);}

	private static String fmt(LocalDateTime dt, DateTimeFormatter f) {return dt == null ? null : dt.format(f);}
	private static LocalDateTime max(LocalDateTime a, LocalDateTime b) {return a.isAfter(b) ? a : b;}
	private static LocalDateTime min(LocalDateTime a, LocalDateTime b) {return a.isBefore(b) ? a : b;}
}

package AutomationCode;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import AutomationCode.RateBlockPlanner.BlockType;
import AutomationCode.RateBlockPlanner.Plan;
import AutomationCode.RateBlockPlanner.PlannedBlock;

/**
 * Unit tests for RateBlockPlanner (no browser, no device).
 * Run: mvn -q -Dtest=RateBlockPlannerTest test
 * 2026-11-18 is a Wednesday.
 */
public class RateBlockPlannerTest
{
	private static final String RATE = "AutomationRateSet1Fixed";
	private static final String FREE = "I Free Parking";
	private static final String NOP = "I No Parking";

	@AfterMethod(alwaysRun = true)
	public void resetFlag() {RateBlockPlanner.ADD_ALL_DAY_BLOCK_FOR_OTHER_DAYS = true; RateBlockPlanner.EXCLUSIVE_END_TIMES = false;}

	private static LocalDateTime wed(int h, int m) {return LocalDateTime.of(2026, 11, 18, h, m, 0);}

	/** "Wed 00:00-12:14 Fixed" style compact string for each per-day block (excludes the All Day block). */
	private static List<String> compact(Plan p)
	{
		List<String> lst = new ArrayList<>();
		for (PlannedBlock b : p.blocks)
		{
			if (b.allDay) {continue;}
			lst.add(RateBlockPlanner.dayAbbr(b.getSingleDay()) + " " + b.getStartHHmm() + "-" + b.getEndHHmm() + " " + b.type);
		}
		return lst;
	}

	/**
	 * Every covered day must be tiled 00:00..23:59, single day per block, never 24:00.
	 * Exclusive ends: the next block starts at the previous End Time (only exception: the 23:58 -> 23:59 seam edge case).
	 * Inclusive ends: the next block starts one minute after the previous End Time.
	 */
	private static void assertDaysFullyTiled(Plan p)
	{
		LocalTime tPrevEnd = null;
		DayOfWeek dCurrent = null;
		for (PlannedBlock b : p.blocks)
		{
			if (b.allDay) {continue;}
			Assert.assertEquals(b.days.size(), 1, "Per-day block must have exactly one day: " + b);
			Assert.assertNotEquals(b.getEndHHmm(), "24:00");
			Assert.assertFalse(b.endTime.isBefore(b.startTime), "End before start: " + b);
			if (!b.getSingleDay().equals(dCurrent))
			{
				if (dCurrent != null) {Assert.assertEquals(tPrevEnd, LocalTime.of(23, 59), "Previous day did not end at 23:59");}
				dCurrent = b.getSingleDay();
				Assert.assertEquals(b.startTime, LocalTime.MIDNIGHT, "Day must start at 00:00: " + b);
			}
			else if (RateBlockPlanner.EXCLUSIVE_END_TIMES)
			{
				boolean blnEdge = tPrevEnd.equals(LocalTime.of(23, 58)) && b.startTime.equals(LocalTime.of(23, 59));
				if (!blnEdge) {Assert.assertEquals(b.startTime, tPrevEnd, "Exclusive seam: block must start at the previous End Time: " + b);}
			}
			else
			{
				Assert.assertEquals(b.startTime, tPrevEnd.plusMinutes(1), "Inclusive seam must step by exactly one minute: " + b);
			}
			tPrevEnd = b.endTime;
		}
		Assert.assertEquals(tPrevEnd, LocalTime.of(23, 59), "Last day did not end at 23:59");
	}

	private static PlannedBlock otherDaysBlock(Plan p)
	{
		for (PlannedBlock b : p.blocks) {if (b.allDay) {return b;}}
		return null;
	}

	@Test(groups = {"Unit"})
	public void rateToFree_noon_MBF15_FREE120()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(12, 0), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-12:14 RATE", "Wed 12:15-14:14 FREE", "Wed 14:15-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		Assert.assertEquals(p.getFreeStartHHmm(), "12:15");
		Assert.assertEquals(p.getFreeEndHHmm(), "14:15");
		Assert.assertEquals(p.getFreeStartHHmmss(), "12:15:00");
		Assert.assertEquals(p.getFreeStart12(), "12:15 PM");
		Assert.assertFalse(p.crossesMidnight());
		assertDaysFullyTiled(p);
		PlannedBlock other = otherDaysBlock(p);
		Assert.assertNotNull(other);
		Assert.assertEquals(other.days, EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
		Assert.assertEquals(other.rateSetName, RATE);
		Assert.assertEquals(other.name, "FixedRateBlock_Other");
	}

	@Test(groups = {"Unit"})
	public void rateToFree_2130_MBF15_FREE120_wrapsIntoTomorrow()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(21, 30), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-21:44 RATE", "Wed 21:45-23:44 FREE", "Wed 23:45-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		Assert.assertEquals(p.getFreeEndHHmm(), "23:45");
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_2350_MBF15_FREE120_freeCrossesMidnight()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(23, 50), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:59 RATE",
			"Thu 00:00-00:04 RATE", "Thu 00:05-02:04 FREE", "Thu 02:05-23:59 RATE"));
		Assert.assertEquals(p.getFreeStartDateTime(), "2026-11-19 00:05:00");
		Assert.assertEquals(p.getFreeEndDateTime(), "2026-11-19 02:05:00");
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_2340_MBF15_FREE120_freeBlockSplitAtMidnight()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(23, 40), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:54 RATE", "Wed 23:55-23:59 FREE",
			"Thu 00:00-01:54 FREE", "Thu 01:55-23:59 RATE"));
		Assert.assertTrue(p.crossesMidnight());
		Assert.assertEquals(p.blocks.get(1).name, "FreeRateBlock_Wed");
		Assert.assertEquals(p.blocks.get(2).name, "FreeRateBlock_Thu");
		Assert.assertEquals(p.blocks.get(1).rateSetName, FREE);
		Assert.assertEquals(p.getFreeStartHHmm(), "23:55");
		Assert.assertEquals(p.getFreeEndHHmm(), "01:55");
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_2359_MBF15_FREE120()
	{
		Plan p = RateBlockPlanner.rateToFree(LocalDateTime.of(2026, 11, 18, 23, 59, 42), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(p.now, wed(23, 59), "Seconds must be dropped once");
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:59 RATE",
			"Thu 00:00-00:13 RATE", "Thu 00:14-02:13 FREE", "Thu 02:14-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_freeStartsExactlyAtMidnight()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(23, 45), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:59 RATE",
			"Thu 00:00-01:59 FREE", "Thu 02:00-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_freeEndsExactlyAtMidnight()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(21, 45), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-21:59 RATE", "Wed 22:00-23:59 FREE",
			"Thu 00:00-23:59 RATE"));
		Assert.assertEquals(p.getFreeEndDateTime(), "2026-11-19 00:00:00");
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_midnightStart_0000()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(0, 0), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-00:14 RATE", "Wed 00:15-02:14 FREE", "Wed 02:15-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToFree_otherCombos()
	{
		Plan p = RateBlockPlanner.rateToFree(wed(23, 30), 30, 120, "Progressive", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:59 RATE",
			"Thu 00:00-01:59 FREE", "Thu 02:00-23:59 RATE"));
		Assert.assertEquals(p.blocks.get(0).name, "ProgressiveRateBlock_Wed");
		p = RateBlockPlanner.rateToFree(wed(23, 55), 10, 30, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:59 RATE",
			"Thu 00:00-00:04 RATE", "Thu 00:05-00:34 FREE", "Thu 00:35-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void freeToRate_noon_and_2350()
	{
		Plan p = RateBlockPlanner.freeToRate(wed(12, 0), 0, 30, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-11:59 RATE", "Wed 12:00-12:29 FREE", "Wed 12:30-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		Assert.assertEquals(p.getFreeEndHHmm(), "12:30");
		p = RateBlockPlanner.freeToRate(wed(23, 50), 0, 30, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:49 RATE", "Wed 23:50-23:59 FREE",
			"Thu 00:00-00:19 FREE", "Thu 00:20-23:59 RATE"));
		Assert.assertEquals(p.getFreeStartHHmmss(), "23:50:00");
		Assert.assertEquals(p.getFreeEnd12(), "12:20 AM");
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void freeToRate_atMidnight_noEmptyRateBlock()
	{
		Plan p = RateBlockPlanner.freeToRate(wed(0, 0), 0, 30, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-00:29 FREE", "Wed 00:30-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void freeToRate_freeStartedYesterday_coversYesterdayToo()
	{
		Plan p = RateBlockPlanner.freeToRate(wed(0, 5), -10, 30, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Tue 00:00-23:54 RATE", "Tue 23:55-23:59 FREE",
			"Wed 00:00-00:34 FREE", "Wed 00:35-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		Assert.assertEquals(otherDaysBlock(p).days, EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void rateToNoParking_wraps()
	{
		Plan p = RateBlockPlanner.rateToNoParking(wed(23, 50), 15, 20, "Fixed", RATE, NOP);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:59 RATE",
			"Thu 00:00-00:04 RATE", "Thu 00:05-00:24 NO_PARKING", "Thu 00:25-23:59 RATE"));
		Assert.assertEquals(p.blocks.get(2).name, "NoParkingRateBlock_Thu");
	}

	@Test(groups = {"Unit"})
	public void otherDaysBlock_canBeTurnedOff()
	{
		RateBlockPlanner.ADD_ALL_DAY_BLOCK_FOR_OTHER_DAYS = false;
		Plan p = RateBlockPlanner.rateToFree(wed(12, 0), 15, 120, "Fixed", RATE, FREE);
		Assert.assertNull(otherDaysBlock(p));
	}

	@Test(groups = {"Unit"})
	public void sameNameInOneDay_getsSuffix()
	{
		List<RateBlockPlanner.Window> lst = new ArrayList<>();
		lst.add(new RateBlockPlanner.Window(wed(10, 0), wed(11, 0), BlockType.FREE, FREE));
		lst.add(new RateBlockPlanner.Window(wed(13, 0), wed(14, 0), BlockType.FREE, FREE));
		Plan p = RateBlockPlanner.plan(wed(9, 0), "Fixed", RATE, lst);
		List<String> names = new ArrayList<>();
		for (PlannedBlock b : p.blocks) {names.add(b.name);}
		Assert.assertEquals(names.subList(0, 5), List.of("FixedRateBlock_Wed", "FreeRateBlock_Wed", "FixedRateBlock_Wed_2", "FreeRateBlock_Wed_2", "FixedRateBlock_Wed_3"));
	}

	@Test(groups = {"Unit"}, expectedExceptions = IllegalArgumentException.class)
	public void overlappingWindows_rejected()
	{
		List<RateBlockPlanner.Window> lst = new ArrayList<>();
		lst.add(new RateBlockPlanner.Window(wed(10, 0), wed(11, 0), BlockType.FREE, FREE));
		lst.add(new RateBlockPlanner.Window(wed(10, 30), wed(12, 0), BlockType.NO_PARKING, NOP));
		RateBlockPlanner.plan(wed(9, 0), "Fixed", RATE, lst);
	}

	@Test(groups = {"Unit"})
	public void wrapSafeSeconds()
	{
		Assert.assertEquals(RateBlockPlanner.wrapSafeSecondsBetween(LocalTime.of(23, 50), LocalTime.of(0, 10)), 20 * 60);
		Assert.assertEquals(RateBlockPlanner.wrapSafeSecondsBetween(LocalTime.of(0, 10), LocalTime.of(23, 50)), -20 * 60);
		Assert.assertEquals(RateBlockPlanner.wrapSafeSecondsBetween(LocalTime.of(12, 0), LocalTime.of(12, 30)), 30 * 60);
		Assert.assertEquals(RateBlockPlanner.wrapSafeSecondsBetween(LocalTime.of(12, 30), LocalTime.of(12, 0)), -30 * 60);
	}

	@Test(groups = {"Unit"})
	public void elapsedSeconds_prefersAbsoluteStart()
	{
		LocalDateTime now = LocalDateTime.of(2026, 11, 19, 0, 20, 30);
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("23:50:00", "2026-11-18 23:50:00", now), 30 * 60 + 30);
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("23:50:00", null, now), 30 * 60 + 30);
		// Absolute value ignored when its time of day does not match the start that was passed in
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("00:10", "2026-11-18 23:50:00", now), 10 * 60 + 30);
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("9:05:00", null, LocalDateTime.of(2026, 11, 18, 9, 15, 0)), 600);
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("12:15", null, LocalDateTime.of(2026, 11, 18, 12, 0, 0)), -900);
		// All-day free that started at 00:00 still reads 14h at 14:00 (no symmetric wrap)
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("00:00:00", null, LocalDateTime.of(2026, 11, 18, 14, 0, 0)), 14 * 3600);
		// Free start still in the future: negative elapsed (same as the old same-day behaviour)
		Assert.assertEquals(RateBlockPlanner.elapsedSeconds("00:05:00", "2026-11-19 00:05:00", LocalDateTime.of(2026, 11, 18, 23, 55, 0)), -10 * 60);
	}

	@Test(groups = {"Unit"})
	public void exclusiveEndTimes_whenToggledOn()
	{
		RateBlockPlanner.EXCLUSIVE_END_TIMES = true;
		Plan p = RateBlockPlanner.rateToFree(wed(12, 0), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-12:15 RATE", "Wed 12:15-14:15 FREE", "Wed 14:15-23:59 RATE",
			"Thu 00:00-23:59 RATE"));
		Assert.assertEquals(p.getCurrentBlockHours(wed(12, 5)), "12:00 am-12:15 pm");
		assertDaysFullyTiled(p);
		p = RateBlockPlanner.rateToFree(LocalDateTime.of(2026, 11, 19, 23, 38, 10), 30, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Thu 00:00-23:59 RATE",
			"Fri 00:00-00:08 RATE", "Fri 00:08-02:08 FREE", "Fri 02:08-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	@Test(groups = {"Unit"})
	public void exclusiveEnd_seamAt2359_edgeCase()
	{
		RateBlockPlanner.EXCLUSIVE_END_TIMES = true;
		Plan p = RateBlockPlanner.rateToFree(wed(23, 44), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Wed 00:00-23:58 RATE", "Wed 23:59-23:59 FREE",
			"Thu 00:00-01:59 FREE", "Thu 01:59-23:59 RATE"));
		assertDaysFullyTiled(p);
	}

	/** The 2026-11-19 I3078F failure: planned Thu 23:38, MBF 30, FREE 120. Free starts Fri 00:08, so the parker pays in Thursday's 00:00-23:59 block. */
	@Test(groups = {"Unit"})
	public void currentBlockHours_freeStartsAfterMidnight()
	{
		LocalDateTime dtPlan = LocalDateTime.of(2026, 11, 19, 23, 38, 10);
		Plan p = RateBlockPlanner.rateToFree(dtPlan, 30, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(compact(p), List.of(
			"Thu 00:00-23:59 RATE",
			"Fri 00:00-00:07 RATE", "Fri 00:08-02:07 FREE", "Fri 02:08-23:59 RATE"));
		Assert.assertEquals(p.getCurrentBlockHours(dtPlan), "12:00 am-11:59 pm");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 19, 23, 43, 15)), "12:00 am-11:59 pm");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 19, 23, 59, 30)), "12:00 am-11:59 pm");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 20, 0, 3, 0)), "12:00 am-12:07 am");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 20, 0, 7, 30)), "12:00 am-12:07 am");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 20, 0, 8, 0)), "12:08 am-2:07 am");
		// Same answers from the encoded schedule kept in objDictionary
		String strSchedule = p.encodeSchedule();
		Assert.assertEquals(RateBlockPlanner.currentBlockHoursFromSchedule(strSchedule, LocalDateTime.of(2026, 11, 19, 23, 43, 15)), "12:00 am-11:59 pm");
		Assert.assertEquals(RateBlockPlanner.currentBlockHoursFromSchedule(strSchedule, LocalDateTime.of(2026, 11, 20, 0, 3, 0)), "12:00 am-12:07 am");
		// A weekday only covered by the All Day block
		Assert.assertEquals(RateBlockPlanner.currentBlockHoursFromSchedule(strSchedule, LocalDateTime.of(2026, 11, 22, 10, 0, 0)), "12:00 am-11:59 pm");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 22, 10, 0, 0)), "12:00 am-11:59 pm");
	}

	@Test(groups = {"Unit"})
	public void currentBlockHours_sameDay()
	{
		Plan p = RateBlockPlanner.rateToFree(LocalDateTime.of(2026, 11, 20, 9, 11, 0), 30, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 20, 9, 15, 54)), "12:00 am-9:40 am");
		Assert.assertEquals(RateBlockPlanner.currentBlockHoursFromSchedule(p.encodeSchedule(), LocalDateTime.of(2026, 11, 20, 9, 15, 54)), "12:00 am-9:40 am");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 20, 9, 41, 0)), "9:41 am-11:40 am");
	}

	@Test(groups = {"Unit"})
	public void currentBlockHours_exactlyAtMidnight()
	{
		// Planned at 00:00: today's first block is 00:00-00:15
		Plan p = RateBlockPlanner.rateToFree(wed(0, 0), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(p.getCurrentBlockHours(wed(0, 0)), "12:00 am-12:14 am");
		// Free starts exactly at Thu 00:00: Wed 23:50 pays in Wed 00:00-23:59, Thu 00:00 is already free
		p = RateBlockPlanner.rateToFree(wed(23, 45), 15, 120, "Fixed", RATE, FREE);
		Assert.assertEquals(p.getCurrentBlockHours(wed(23, 50)), "12:00 am-11:59 pm");
		Assert.assertEquals(p.getCurrentBlockHours(LocalDateTime.of(2026, 11, 19, 0, 0, 0)), "12:00 am-1:59 am");
	}

	@Test(groups = {"Unit"})
	public void currentRateBlockHours_fromDictionary_guardsStaleKeys()
	{
		Plan p = RateBlockPlanner.rateToFree(LocalDateTime.of(2026, 11, 19, 23, 38, 0), 30, 120, "Fixed", RATE, FREE);
		java.util.Map<String, String> d = new java.util.HashMap<>();
		d.put("strRateBlockPlanSchedule", p.encodeSchedule());
		d.put("strFreeParkingStartDateTime", p.getFreeStartDateTime());
		d.put("strMinutesBeforeFreeStart", p.getFreeStartHHmmss());
		d.put("strCurrentRateBlockHours", p.getCurrentBlockHours(p.now));
		Assert.assertEquals(RateBlockPlanner.currentRateBlockHours(d, LocalDateTime.of(2026, 11, 19, 23, 43, 0)), "12:00 am-11:59 pm");
		// A later legacy path set a different free start: plan keys are stale, caller keeps its legacy expectation
		d.put("strMinutesBeforeFreeStart", "10:15:00");
		Assert.assertNull(RateBlockPlanner.currentRateBlockHours(d, LocalDateTime.of(2026, 11, 19, 23, 43, 0)));
		Assert.assertNull(RateBlockPlanner.currentRateBlockHours(new java.util.HashMap<>(), LocalDateTime.of(2026, 11, 19, 23, 43, 0)));
	}

	@Test(groups = {"Unit"})
	public void secondsUntilDated()
	{
		Assert.assertEquals(RateBlockPlanner.secondsUntilDated("00:08:00", "2026-11-20 00:08:00", LocalDateTime.of(2026, 11, 19, 23, 43, 15)), Long.valueOf(24 * 60 + 45));
		Assert.assertNull(RateBlockPlanner.secondsUntilDated("09:41:00", "2026-11-20 00:08:00", LocalDateTime.of(2026, 11, 19, 23, 43, 15)));
		Assert.assertNull(RateBlockPlanner.secondsUntilDated("00:08:00", "2026-11-20 00:08:00", LocalDateTime.of(2026, 11, 20, 0, 9, 0)));
		Assert.assertNull(RateBlockPlanner.secondsUntilDated("00:08:00", null, LocalDateTime.of(2026, 11, 19, 23, 43, 15)));
	}

	@Test(groups = {"Unit"})
	public void freeStartCap_helpers()
	{
		Plan p = RateBlockPlanner.rateToFree(LocalDateTime.of(2026, 11, 20, 10, 15, 0), 30, 120, "Fixed", RATE, FREE);
		java.util.Map<String, String> d = new java.util.HashMap<>();
		d.put("strFreeParkingStartDateTime", p.getFreeStartDateTime());
		d.put("strMinutesBeforeFreeStart", p.getFreeStartHHmmss());
		LocalDateTime now = LocalDateTime.of(2026, 11, 20, 10, 20, 31);
		Assert.assertEquals(RateBlockPlanner.datedFreeStart(d, now), LocalDateTime.of(2026, 11, 20, 10, 45, 0));
		Assert.assertEquals(RateBlockPlanner.roundedMinutesUntil(LocalDateTime.of(2026, 11, 20, 10, 45, 0), now), 24);   // 24.48
		Assert.assertEquals(RateBlockPlanner.roundedMinutesUntil(LocalDateTime.of(2026, 11, 20, 10, 45, 0), LocalDateTime.of(2026, 11, 20, 10, 20, 30)), 25); // 24.5 half-up
		Assert.assertEquals(RateBlockPlanner.formatSessionTime(LocalDateTime.of(2026, 11, 20, 10, 45, 0)), new java.text.SimpleDateFormat("MMM dd, hh:mm a").format(java.util.Date.from(LocalDateTime.of(2026, 11, 20, 10, 45, 0).atZone(java.time.ZoneId.systemDefault()).toInstant())));
		// Across midnight
		p = RateBlockPlanner.rateToFree(LocalDateTime.of(2026, 11, 19, 23, 38, 0), 30, 120, "Fixed", RATE, FREE);
		d.put("strFreeParkingStartDateTime", p.getFreeStartDateTime());
		d.put("strMinutesBeforeFreeStart", p.getFreeStartHHmmss());
		Assert.assertEquals(RateBlockPlanner.datedFreeStart(d, LocalDateTime.of(2026, 11, 19, 23, 43, 0)), LocalDateTime.of(2026, 11, 20, 0, 8, 0));
		// Stale / old path / already passed -> null (old expectation kept)
		d.put("strMinutesBeforeFreeStart", "10:15:00");
		Assert.assertNull(RateBlockPlanner.datedFreeStart(d, LocalDateTime.of(2026, 11, 19, 23, 43, 0)));
		Assert.assertNull(RateBlockPlanner.datedFreeStart(new java.util.HashMap<>(), now));
		d.put("strMinutesBeforeFreeStart", p.getFreeStartHHmmss());
		Assert.assertNull(RateBlockPlanner.datedFreeStart(d, LocalDateTime.of(2026, 11, 20, 0, 9, 0)));
	}
}

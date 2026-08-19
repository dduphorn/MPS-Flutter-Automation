package AutomationCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeConverter {
	
	// Method to convert 24-hour time format (hh:mm) to 12-hour time format (hh:mm AM/PM)
	public static String convertHourTo12HourFormat(String time24hr) {
	    if (time24hr == null || time24hr.trim().isEmpty()) {
	        throw new IllegalArgumentException("Time input is required.");
	    }

	    time24hr = time24hr.trim();

	    // Normalize input: if only hour is provided, add ":00"
	    if (time24hr.matches("^([01]?\\d|2[0-3])$")) {
	        time24hr += ":00";
	    }

	    // Validate the final format: hh:mm
	    if (!time24hr.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
	        throw new IllegalArgumentException("Invalid 24-hour time format. Expected hh[:mm]");
	    }

	    // Parse hour and minute
	    String[] timeParts = time24hr.split(":");
	    int hour = Integer.parseInt(timeParts[0]);
	    int minute = Integer.parseInt(timeParts[1]);

	    String period = "AM";
	    if (hour >= 12) {
	        period = "PM";
	        if (hour > 12) {
	            hour -= 12;
	        }
	    }
	    if (hour == 0) {
	        hour = 12;
	    }

	    // Return time in 12-hour format with leading zero
	    return String.format("%02d:%02d %s", hour, minute, period);
	}
	public static String convert12HourToSingleDigitHour(String time12hr) {
	    if (time12hr == null || time12hr.trim().isEmpty()) {
	        throw new IllegalArgumentException("Time input is required.");
	    }

	    time12hr = time12hr.trim().toUpperCase();

	    // Match valid 12-hour time format with leading zeros allowed
	    if (!time12hr.matches("^(0?[1-9]|1[0-2]):[0-5][0-9]\\s?(AM|PM)$")) {
	        throw new IllegalArgumentException("Invalid 12-hour time format. Expected format: hh:mm AM/PM");
	    }

	    // Split into time and period
	    String[] parts = time12hr.split(" ");
	    String[] timeParts = parts[0].split(":");

	    int hour = Integer.parseInt(timeParts[0]); // Removes leading zero automatically
	    int minute = Integer.parseInt(timeParts[1]);
	    String period = parts[1];

	    // Return in h:mm AM/PM format
	    return String.format("%d:%02d %s", hour, minute, period);
	}

    public static String convertTo24HourFormat(String time12hr) {
        // Validate the input to ensure it's in 12-hour format (hh:mm AM/PM)
        if (time12hr == null || !time12hr.matches("^(0?[1-9]|1[0-2]):([0-5][0-9]) [AP]M$")) {
            throw new IllegalArgumentException("Invalid 12-hour time format. Expected format: hh:mm AM/PM");
        }

        // Split time into time part (hh:mm) and period (AM/PM)
        String[] parts = time12hr.split(" ");
        String time = parts[0]; // Time part (hh:mm)
        String period = parts[1]; // AM/PM part

        // Split time into hour and minute components
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        // Convert to 24-hour format based on the AM/PM period
        if (period.equals("PM") && hour != 12) {
            hour += 12; // Convert PM to 24-hour format
        } else if (period.equals("AM") && hour == 12) {
            hour = 0; // Convert 12 AM to 00:00 in 24-hour format
        }

        // Return the time in 24-hour format
        return String.format("%02d:%02d", hour, minute);
    }

    public String addOrSubtractMinutesFromCurrentTime(String currentTime, String minutesToAddOrSubtract, String dateFormat) {
        String newTime = "";
        try {
            DateFormat sdf = new SimpleDateFormat(dateFormat);
            Date parsedDate = sdf.parse(currentTime);
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            
            int minutes = Integer.parseInt(minutesToAddOrSubtract);
            calendar.add(Calendar.MINUTE, minutes);
            
            newTime = sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Better error reporting than just "MIH"
        }
        return newTime;
    }
}

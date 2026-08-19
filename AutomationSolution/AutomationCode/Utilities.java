package AutomationCode;

public class Utilities {

	public static String TimeConversion(String inputString1) {
	    int value;
	    String unit;

	    if (inputString1.contains("Mins")) {
	        value = Integer.parseInt(inputString1.replaceAll("\\D+", ""));
	        unit = "Mins";

	        int hours = value / 60;
	        return "Max time : " + hours + " Hrs";
	    } else if (inputString1.contains("Hrs")|| inputString1.contains("Hr")) {
	        value = Integer.parseInt(inputString1.replaceAll("\\D+", ""));
	        unit = "hrs";

	        int minutes = value * 60;
	        return "Max time : " + minutes + " Mins";
	    } else {
	        return "Invalid input format";
	    }
}
}









package AutomationCode;

public class FreshInstallCommandTest
{
	public static void main(String[] args)
	{
		String adbPath = "/Users/test/Library/Android/sdk/platform-tools/adb";
		String phone1 = CommonANDROID_Flutter.uninstallCommand(adbPath, "R5CT71MQ5YT", "com.mpspark.mobileOfficer");
		String phone2 = CommonANDROID_Flutter.uninstallCommand(adbPath, "R5CW42VHVZY", "com.mpspark.mobileOfficer");
		if (!phone1.equals(adbPath + " -s R5CT71MQ5YT uninstall com.mpspark.mobileOfficer"))
		{
			throw new AssertionError("Phone 1 uninstall command was " + phone1);
		}
		if (!phone2.equals(adbPath + " -s R5CW42VHVZY uninstall com.mpspark.mobileOfficer"))
		{
			throw new AssertionError("Phone 2 uninstall command was " + phone2);
		}
		if (phone1.contains("R5CW42VHVZY") || phone2.contains("R5CT71MQ5YT"))
		{
			throw new AssertionError("An uninstall command targeted the other phone");
		}
		System.out.println("FreshInstallCommandTest passed");
	}
}

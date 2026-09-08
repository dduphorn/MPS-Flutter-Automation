package AutomationCode;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;

public class ADB_Commands 
{
	public void SENTRYMOBILE_SET_AIRPLANE_MODE(Map<String, String> objDictionary)
	{
	    String strAutomationUser = objDictionary.get("strAutomationUser");
	    String adbPath;

	    String strAirplaneMode = objDictionary.get("strAirplaneMode");
	    if (strAirplaneMode == null) strAirplaneMode = "Disabled";

	    boolean shouldEnable = strAirplaneMode.equalsIgnoreCase("Enabled");
	    String expectedValue = shouldEnable ? "1" : "0";
	    String stateFlag = shouldEnable ? "true" : "false";
	    String modernAction = shouldEnable ? "enable" : "disable";

	    // ADB path
	    String sdkRoot = System.getenv("ANDROID_HOME");
	    if (sdkRoot == null || sdkRoot.trim().isEmpty()) sdkRoot = System.getenv("ANDROID_SDK_ROOT");
	    adbPath = (sdkRoot != null && !sdkRoot.trim().isEmpty())
	            ? sdkRoot + "/platform-tools/adb"
	            : "/Users/" + strAutomationUser + "/Library/Android/sdk/platform-tools/adb";

	    String deviceSerial = objDictionary.get("strDeviceName");
	    boolean hasDevice = deviceSerial != null && !deviceSerial.trim().isEmpty();

	    try {
	        // Helper that returns full output (trimmed)
	        java.util.function.Function<String[], String> runAdb = cmd -> {
	            try {
	                Process p = Runtime.getRuntime().exec(cmd);
	                java.io.BufferedReader r = new java.io.BufferedReader(
	                        new java.io.InputStreamReader(p.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line;
	                while ((line = r.readLine()) != null) {
	                    sb.append(line).append("\n");
	                }
	                p.waitFor();
	                return sb.toString().trim();
	            } catch (Exception e) {
	                return null;
	            }
	        };

	        // Helper to build command array
	        java.util.function.Function<String[], String[]> withDevice = args -> {
	            if (!hasDevice) return args;
	            String[] full = new String[args.length + 2];
	            full[0] = adbPath;
	            full[1] = "-s";
	            full[2] = deviceSerial;
	            System.arraycopy(args, 1, full, 3, args.length - 1); // skip original adbPath
	            return full;
	        };

	        // 1. Check current status
	        String[] statusCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "settings", "get", "global", "airplane_mode_on"}
	                : new String[]{adbPath, "shell", "settings", "get", "global", "airplane_mode_on"};

	        String current = runAdb.apply(statusCmd);
	        if (expectedValue.equals(current)) {
	            Reporter.log("Airplane Mode is already " + (shouldEnable ? "enabled" : "disabled") +
	                    (hasDevice ? " on " + deviceSerial : ""));
	            return;
	        }

	        // 2. Preferred modern method (Android 10+)
	        boolean modernSucceeded = false;
	        try {
	            String[] modernCmd = hasDevice
	                    ? new String[]{adbPath, "-s", deviceSerial, "shell", "cmd", "connectivity", "airplane-mode", modernAction}
	                    : new String[]{adbPath, "shell", "cmd", "connectivity", "airplane-mode", modernAction};
	            Runtime.getRuntime().exec(modernCmd).waitFor();
	            Thread.sleep(1500);

	            String afterModern = runAdb.apply(statusCmd);
	            if (expectedValue.equals(afterModern)) {
	                modernSucceeded = true;
	                Reporter.log("Airplane Mode set via modern 'cmd connectivity' command");
	            }
	        } catch (Exception ignored) {
	            // fall through to classic method
	        }

	        // 3. Classic method (more compatible + forces the system like UI toggle)
	        if (!modernSucceeded) {
	            // Set the global flag
	            String[] putCmd = hasDevice
	                    ? new String[]{adbPath, "-s", deviceSerial, "shell", "settings", "put", "global", "airplane_mode_on", expectedValue}
	                    : new String[]{adbPath, "shell", "settings", "put", "global", "airplane_mode_on", expectedValue};
	            Runtime.getRuntime().exec(putCmd).waitFor();

	            // Broadcast WITH state (this is the critical part missing in many implementations)
	            String[] broadcastCmd = hasDevice
	                    ? new String[]{adbPath, "-s", deviceSerial, "shell", "am", "broadcast",
	                                   "-a", "android.intent.action.AIRPLANE_MODE", "--ez", "state", stateFlag}
	                    : new String[]{adbPath, "shell", "am", "broadcast",
	                                   "-a", "android.intent.action.AIRPLANE_MODE", "--ez", "state", stateFlag};
	            Runtime.getRuntime().exec(broadcastCmd).waitFor();
	        }

	        // 4. Explicitly force data + Wi-Fi to match airplane mode state
	        // (helps eliminate residual connectivity that pure airplane mode sometimes leaves)
	        String dataAction = shouldEnable ? "disable" : "enable";
	        String wifiAction = shouldEnable ? "disable" : "enable";

	        String[] dataCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "svc", "data", dataAction}
	                : new String[]{adbPath, "shell", "svc", "data", dataAction};
	        Runtime.getRuntime().exec(dataCmd).waitFor();

	        String[] wifiCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "svc", "wifi", wifiAction}
	                : new String[]{adbPath, "shell", "svc", "wifi", wifiAction};
	        Runtime.getRuntime().exec(wifiCmd).waitFor();

	        Thread.sleep(3000); // give radios time to settle

	        // 5. Final validation
	        String actual = runAdb.apply(statusCmd);
	        if (!expectedValue.equals(actual)) {
	            UpdateErrorMessageWithPivotalData(objDictionary,
	                    "Airplane Mode status did not change" + (hasDevice ? " on " + deviceSerial : "") +
	                    ". Expected: " + expectedValue + ", got: " + actual);
	            return;
	        }

	        Reporter.log("Airplane Mode successfully " + (shouldEnable ? "enabled" : "disabled") +
	                " and verified" + (hasDevice ? " on " + deviceSerial : "") +
	                " (UI-like toggle)");

	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Airplane Mode toggle failed: " + e.getMessage());
	    }
	}
	public void SENTRYMOBILE_SET_WIFI(Map<String, String> objDictionary)
	{
	    String strAutomationUser = objDictionary.get("strAutomationUser");
	    String adbPath;

	    String strWIFI = objDictionary.get("strWIFI");
	    if (strWIFI == null) strWIFI = "Enabled";

	    boolean shouldEnable = strWIFI.equalsIgnoreCase("Enabled");
	    String action = shouldEnable ? "enable" : "disable";

	    // ADB path
	    String sdkRoot = System.getenv("ANDROID_HOME");
	    if (sdkRoot == null || sdkRoot.trim().isEmpty()) sdkRoot = System.getenv("ANDROID_SDK_ROOT");
	    adbPath = (sdkRoot != null && !sdkRoot.trim().isEmpty())
	            ? sdkRoot + "/platform-tools/adb"
	            : "/Users/" + strAutomationUser + "/Library/Android/sdk/platform-tools/adb";

	    String deviceSerial = objDictionary.get("strDeviceName");
	    boolean hasDevice = deviceSerial != null && !deviceSerial.trim().isEmpty();

	    try {
	        // Helper
	        java.util.function.Function<String[], String> runAdb = cmd -> {
	            try {
	                Process p = Runtime.getRuntime().exec(cmd);
	                java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
	                String line = r.readLine();
	                p.waitFor();
	                return line == null ? null : line.trim();
	            } catch (Exception e) { return null; }
	        };

	        // 1. Check Airplane Mode first
	        String[] airplaneCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "settings", "get", "global", "airplane_mode_on"}
	                : new String[]{adbPath, "shell", "settings", "get", "global", "airplane_mode_on"};

	        String airplaneStatus = runAdb.apply(airplaneCmd);
	        boolean isAirplaneOn = "1".equals(airplaneStatus);

	        if (shouldEnable && isAirplaneOn) {
	            UpdateErrorMessageWithPivotalData(objDictionary,
	                    "Cannot enable Wi-Fi while Airplane Mode is enabled" +
	                    (hasDevice ? " on " + deviceSerial : "") +
	                    ". Please disable Airplane Mode first.");
	            return;
	        }

	        // 2. Check current Wi-Fi status
	        String[] statusCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "settings", "get", "global", "wifi_on"}
	                : new String[]{adbPath, "shell", "settings", "get", "global", "wifi_on"};

	        String currentStatus = runAdb.apply(statusCmd);

	        // ----- IMPROVED CHECK -----
	        // "0" = disabled
	        // anything else ("1", "2", null, etc.) = considered enabled
	        boolean currentlyEnabled = !"0".equals(currentStatus);

	        if (shouldEnable == currentlyEnabled) {
	            Reporter.log("Wi-Fi is already " + action + "d" +
	                    (hasDevice ? " on " + deviceSerial : "") +
	                    " (status=" + currentStatus + "). No action needed.");
	            return;
	        }

	        // 3. Toggle
	        String[] toggleCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "svc", "wifi", action}
	                : new String[]{adbPath, "shell", "svc", "wifi", action};

	        Process p = Runtime.getRuntime().exec(toggleCmd);
	        if (p.waitFor() != 0) {
	            throw new RuntimeException("Failed to " + action + " Wi-Fi");
	        }

	        Thread.sleep(3000);

	        // 4. Validate with the same tolerant logic
	        String actualStatus = runAdb.apply(statusCmd);
	        boolean nowEnabled = !"0".equals(actualStatus);

	        if (shouldEnable != nowEnabled) {
	            UpdateErrorMessageWithPivotalData(objDictionary,
	                    "Wi-Fi status did not change" +
	                    (hasDevice ? " on " + deviceSerial : "") +
	                    ". Expected enabled=" + shouldEnable + ", got status=" + actualStatus);
	            return;
	        }

	        Reporter.log("Wi-Fi successfully " + action + "d and verified" +
	                (hasDevice ? " on " + deviceSerial : "") +
	                " (status=" + actualStatus + ")");

	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Wi-Fi toggle failed: " + e.getMessage());
	    }
	}
	public void SENTRYMOBILE_SET_WIFIScanning(Map<String, String> objDictionary)
	{
	    String strAutomationUser = objDictionary.get("strAutomationUser");
	    String adbPath;

	    String strWIFIScanning = objDictionary.get("strWIFIScanning");
	    if (strWIFIScanning == null) strWIFIScanning = "Disabled";

	    boolean shouldEnable = strWIFIScanning.equalsIgnoreCase("Enabled");
	    String action = shouldEnable ? "enable" : "disable";
	    String expectedValue = shouldEnable ? "1" : "0";

	    String sdkRoot = System.getenv("ANDROID_HOME");
	    if (sdkRoot == null || sdkRoot.trim().isEmpty()) sdkRoot = System.getenv("ANDROID_SDK_ROOT");
	    adbPath = (sdkRoot != null && !sdkRoot.trim().isEmpty())
	            ? sdkRoot + "/platform-tools/adb"
	            : "/Users/" + strAutomationUser + "/Library/Android/sdk/platform-tools/adb";

	    String deviceSerial = objDictionary.get("strDeviceName");
	    boolean hasDevice = deviceSerial != null && !deviceSerial.trim().isEmpty();

	    try {
	        java.util.function.Function<String[], String> runAdb = cmd -> {
	            try {
	                Process p = Runtime.getRuntime().exec(cmd);
	                java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
	                String line = r.readLine();
	                p.waitFor();
	                return line == null ? null : line.trim();
	            } catch (Exception e) { return null; }
	        };

	        String[] statusCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "settings", "get", "global", "wifi_scan_always_enabled"}
	                : new String[]{adbPath, "shell", "settings", "get", "global", "wifi_scan_always_enabled"};

	        String currentStatus = runAdb.apply(statusCmd);
	        boolean currentlyEnabled = "1".equals(currentStatus);

	        if (shouldEnable == currentlyEnabled) {
	            Reporter.log("Wi-Fi scanning is already " + action + "d" +
	                    (hasDevice ? " on " + deviceSerial : "") +
	                    " (wifi_scan_always_enabled=" + currentStatus + "). No action needed.");
	            return;
	        }

	        String[] toggleCmd = hasDevice
	                ? new String[]{adbPath, "-s", deviceSerial, "shell", "settings", "put", "global", "wifi_scan_always_enabled", expectedValue}
	                : new String[]{adbPath, "shell", "settings", "put", "global", "wifi_scan_always_enabled", expectedValue};

	        Process p = Runtime.getRuntime().exec(toggleCmd);
	        if (p.waitFor() != 0) {
	            throw new RuntimeException("Failed to " + action + " Wi-Fi scanning");
	        }

	        Thread.sleep(1000);

	        String actualStatus = runAdb.apply(statusCmd);
	        boolean nowEnabled = "1".equals(actualStatus);

	        if (shouldEnable != nowEnabled) {
	            UpdateErrorMessageWithPivotalData(objDictionary,
	                    "Wi-Fi scanning status did not change" +
	                    (hasDevice ? " on " + deviceSerial : "") +
	                    ". Expected wifi_scan_always_enabled=" + expectedValue + ", got " + actualStatus);
	            return;
	        }

	        Reporter.log("Wi-Fi scanning successfully " + action + "d and verified" +
	                (hasDevice ? " on " + deviceSerial : "") +
	                " (wifi_scan_always_enabled=" + actualStatus + ")");

	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Wi-Fi scanning toggle failed: " + e.getMessage());
	    }
	}
	
	
	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,String strErrorMsg)
	{
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		String strPivotalId = "";
		switch (strErrorMsg)
	    {
	    	
		}
		if(!strPivotalId.equals(""))
		{
			if((strAssociatedBug.contains(strPivotalId) && !strAssociatedBug.equals("")) || strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
			{Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");}
			else
			{Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");}
			Assert.fail(strPivotalId+"-"+strErrorMsg);
		}
		else
  		{
  			Assert.fail(strErrorMsg);
  		}
	}

}

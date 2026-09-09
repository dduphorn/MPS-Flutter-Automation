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
	        boolean failOnMismatch = !"False".equalsIgnoreCase(objDictionary.get("strAdbFailOnMismatch"));

	        java.util.function.Function<String[], String> runAdb = cmd -> readAdbOutput(cmd);

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

	        String current = readAirplaneModeStatus(runAdb, statusCmd);
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

	            String afterModern = readAirplaneModeStatus(runAdb, statusCmd);
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

	        // 5. Final validation — retry because settings get can return blank right after a radio toggle
	        String actual = readAirplaneModeStatus(runAdb, statusCmd);
	        if (!expectedValue.equals(actual)) {
	            String message = "Airplane Mode status did not change" + (hasDevice ? " on " + deviceSerial : "") +
	                    ". Expected: " + expectedValue + ", got: " + actual;
	            if (failOnMismatch) {
	                UpdateErrorMessageWithPivotalData(objDictionary, message);
	            } else {
	                Reporter.log("WARNING: " + message + " (ignored during cleanup)");
	            }
	            return;
	        }

	        Reporter.log("Airplane Mode successfully " + (shouldEnable ? "enabled" : "disabled") +
	                " and verified" + (hasDevice ? " on " + deviceSerial : "") +
	                " (UI-like toggle)");

	    } catch (Exception e) {
	        if ("False".equalsIgnoreCase(objDictionary.get("strAdbFailOnMismatch"))) {
	            Reporter.log("WARNING: Airplane Mode toggle failed: " + e.getMessage() + " (ignored during cleanup)");
	        } else {
	            UpdateErrorMessageWithPivotalData(objDictionary, "Airplane Mode toggle failed: " + e.getMessage());
	        }
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
	            String message = "Cannot enable Wi-Fi while Airplane Mode is enabled" +
	                    (hasDevice ? " on " + deviceSerial : "") +
	                    ". Please disable Airplane Mode first.";
	            if ("False".equalsIgnoreCase(objDictionary.get("strAdbFailOnMismatch"))) {
	                Reporter.log("WARNING: " + message + " (ignored during cleanup)");
	            } else {
	                UpdateErrorMessageWithPivotalData(objDictionary, message);
	            }
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

	public void SENTRYMOBILE_SET_BATTERY_SAVER(Map<String, String> objDictionary)
	{
	    String strBatterySaver = objDictionary.get("strBatterySaver");
	    if (strBatterySaver == null) strBatterySaver = "Disabled";
	    boolean shouldEnable = strBatterySaver.equalsIgnoreCase("Enabled");
	    String expectedValue = shouldEnable ? "1" : "0";
	    boolean failOnMismatch = !"False".equalsIgnoreCase(objDictionary.get("strAdbFailOnMismatch"));

	    try {
	        String current = runAdbShell(objDictionary, "settings", "get", "global", "low_power");
	        if (expectedValue.equals(normalizeZeroOne(current))) {
	            Reporter.log("Battery Saver is already " + (shouldEnable ? "enabled" : "disabled"));
	            return;
	        }

	        runAdbShell(objDictionary, "settings", "put", "global", "low_power", expectedValue);
	        runAdbShell(objDictionary, "am", "broadcast", "-a", "android.os.action.POWER_SAVE_MODE_CHANGED");
	        Thread.sleep(1500);

	        String actual = normalizeZeroOne(runAdbShell(objDictionary, "settings", "get", "global", "low_power"));
	        if (!expectedValue.equals(actual)) {
	            String message = "Battery Saver status did not change. Expected: " + expectedValue + ", got: " + actual;
	            if (failOnMismatch) {
	                UpdateErrorMessageWithPivotalData(objDictionary, message);
	            } else {
	                Reporter.log("WARNING: " + message + " (ignored during cleanup)");
	            }
	            return;
	        }
	        Reporter.log("Battery Saver successfully " + (shouldEnable ? "enabled" : "disabled") + " (low_power=" + actual + ")");
	    } catch (Exception e) {
	        if (failOnMismatch) {
	            UpdateErrorMessageWithPivotalData(objDictionary, "Battery Saver toggle failed: " + e.getMessage());
	        } else {
	            Reporter.log("WARNING: Battery Saver toggle failed: " + e.getMessage());
	        }
	    }
	}

	public void SENTRYMOBILE_SET_BATTERY_LEVEL(Map<String, String> objDictionary)
	{
	    String strBatteryLevel = objDictionary.get("strBatteryLevel");
	    if (strBatteryLevel == null || strBatteryLevel.trim().isEmpty()) {
	        SENTRYMOBILE_RESET_BATTERY(objDictionary);
	        return;
	    }

	    try {
	        runAdbShell(objDictionary, "dumpsys", "battery", "unplug");
	        runAdbShell(objDictionary, "dumpsys", "battery", "set", "level", strBatteryLevel);
	        Thread.sleep(1000);
	        Reporter.log("Battery level set to " + strBatteryLevel + "% (simulated via dumpsys battery)");
	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Setting battery level failed: " + e.getMessage());
	    }
	}

	public void SENTRYMOBILE_RESET_BATTERY(Map<String, String> objDictionary)
	{
	    try {
	        runAdbShell(objDictionary, "dumpsys", "battery", "reset");
	        runAdbShell(objDictionary, "settings", "put", "global", "low_power", "0");
	        Reporter.log("Battery simulation reset and Battery Saver disabled");
	    } catch (Exception e) {
	        Reporter.log("Battery reset failed: " + e.getMessage());
	    }
	}

	public void SENTRYMOBILE_FORCE_STOP_APP(Map<String, String> objDictionary)
	{
	    String packageName = getCaPackageName(objDictionary);
	    try {
	        runAdbShell(objDictionary, "am", "force-stop", packageName);
	        Thread.sleep(2000);
	        Reporter.log("Force-stopped " + packageName);
	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Force-stop failed for " + packageName + ": " + e.getMessage());
	    }
	}

	public void SENTRYMOBILE_LAUNCH_APP(Map<String, String> objDictionary)
	{
	    String packageName = getCaPackageName(objDictionary);
	    try {
	        runAdbShell(objDictionary, "monkey", "-p", packageName, "-c", "android.intent.category.LAUNCHER", "1");
	        Thread.sleep(5000);
	        Reporter.log("Launched " + packageName + " from launcher");
	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Launch failed for " + packageName + ": " + e.getMessage());
	    }
	}

	public void SENTRYMOBILE_SEND_HOME(Map<String, String> objDictionary)
	{
	    try {
	        runAdbShell(objDictionary, "input", "keyevent", "KEYCODE_HOME");
	        Thread.sleep(1000);
	        Reporter.log("Sent device Home key");
	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Sending Home key failed: " + e.getMessage());
	    }
	}

	public boolean SENTRYMOBILE_IS_APP_RUNNING(Map<String, String> objDictionary)
	{
	    String packageName = getCaPackageName(objDictionary);
	    try {
	        String pid = runAdbShell(objDictionary, "pidof", packageName);
	        boolean running = pid != null && !pid.trim().isEmpty() && !pid.toLowerCase().contains("not found");
	        Reporter.log(packageName + (running ? " is running (pid=" + pid.trim() + ")" : " is not running"));
	        return running;
	    } catch (Exception e) {
	        Reporter.log("Could not check whether " + packageName + " is running: " + e.getMessage());
	        return false;
	    }
	}

	public String SENTRYMOBILE_CORRUPT_APP_CACHE(Map<String, String> objDictionary)
	{
	    String packageName = getCaPackageName(objDictionary);
	    StringBuilder evidence = new StringBuilder();
	    boolean wroteCorruptFile = false;

	    try {
	        String[] cacheDirs = {"cache", "code_cache"};
	        for (String dir : cacheDirs) {
	            String listing = runAsPackage(objDictionary, packageName, "ls", dir);
	            evidence.append(dir).append(" listing: ").append(listing == null ? "(unavailable)" : listing).append("\n");
	            if (listing != null && !listing.toLowerCase().contains("permission denied") && !listing.toLowerCase().contains("no such file")) {
	                String[] files = listing.split("\\s+");
	                for (String file : files) {
	                    if (file == null || file.trim().isEmpty() || file.contains("/") || file.equals("..") || file.equals(".")) continue;
	                    runAsPackage(objDictionary, packageName, "sh", "-c", "echo 'corrupt data' > " + dir + "/" + file);
	                    evidence.append("Corrupted ").append(dir).append("/").append(file).append("\n");
	                    wroteCorruptFile = true;
	                    break;
	                }
	            }
	            String sentinel = runAsPackage(objDictionary, packageName, "sh", "-c", "echo 'corrupt data' > " + dir + "/adb_corrupt_cache.txt");
	            if (sentinel == null || (!sentinel.toLowerCase().contains("permission denied") && !sentinel.toLowerCase().contains("can't"))) {
	                wroteCorruptFile = true;
	                evidence.append("Wrote sentinel ").append(dir).append("/adb_corrupt_cache.txt\n");
	            }
	        }

	        if (!wroteCorruptFile) {
	            String externalCache = "/sdcard/Android/data/" + packageName + "/cache";
	            runAdbShell(objDictionary, "mkdir", "-p", externalCache);
	            String external = runAdbShell(objDictionary, "sh", "-c", "echo 'corrupt data' > " + externalCache + "/adb_corrupt_cache.txt");
	            evidence.append("External cache write: ").append(external == null ? "ok" : external).append("\n");
	        }

	        Reporter.log("Cache corruption evidence for " + packageName + ":\n" + evidence);
	        objDictionary.put("strCacheCorruptionEvidence", evidence.toString());
	        return evidence.toString();
	    } catch (Exception e) {
	        UpdateErrorMessageWithPivotalData(objDictionary, "Cache corruption failed: " + e.getMessage());
	        return evidence.toString();
	    }
	}

	private String getCaPackageName(Map<String, String> objDictionary)
	{
	    String packageName = objDictionary.get("strAppPackage");
	    if (packageName == null || packageName.trim().isEmpty()) {
	        packageName = "com.mpspark.consumer.mpsconsumer";
	    }
	    return packageName;
	}

	private String getAdbPath(Map<String, String> objDictionary)
	{
	    String strAutomationUser = objDictionary.get("strAutomationUser");
	    String sdkRoot = System.getenv("ANDROID_HOME");
	    if (sdkRoot == null || sdkRoot.trim().isEmpty()) sdkRoot = System.getenv("ANDROID_SDK_ROOT");
	    if (sdkRoot != null && !sdkRoot.trim().isEmpty()) {
	        return sdkRoot + "/platform-tools/adb";
	    }
	    return "/Users/" + strAutomationUser + "/Library/Android/sdk/platform-tools/adb";
	}

	private String getDeviceSerial(Map<String, String> objDictionary)
	{
	    String deviceSerial = objDictionary.get("strDeviceName");
	    if (deviceSerial == null || deviceSerial.trim().isEmpty()) {
	        deviceSerial = objDictionary.get("strAndroidUdid");
	    }
	    return deviceSerial;
	}

	private String readAdbOutput(String[] cmd)
	{
	    try {
	        ProcessBuilder pb = new ProcessBuilder(cmd);
	        pb.redirectErrorStream(true);
	        Process p = pb.start();
	        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = r.readLine()) != null) {
	            sb.append(line).append("\n");
	        }
	        p.waitFor();
	        return sb.toString().trim();
	    } catch (Exception e) {
	        return "";
	    }
	}

	private String readAirplaneModeStatus(java.util.function.Function<String[], String> runAdb, String[] statusCmd)
	{
	    String actual = "";
	    for (int i = 0; i < 5; i++) {
	        actual = normalizeZeroOne(runAdb.apply(statusCmd));
	        if ("0".equals(actual) || "1".equals(actual)) {
	            return actual;
	        }
	        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
	    }
	    return actual == null ? "" : actual;
	}

	private String normalizeZeroOne(String raw)
	{
	    if (raw == null) return "";
	    String trimmed = raw.trim();
	    if ("0".equals(trimmed) || "1".equals(trimmed)) return trimmed;
	    java.util.regex.Matcher m = java.util.regex.Pattern.compile("(?m)^([01])$").matcher(trimmed);
	    if (m.find()) return m.group(1);
	    return trimmed;
	}

	private String runAdbShell(Map<String, String> objDictionary, String... shellArgs) throws Exception
	{
	    String adbPath = getAdbPath(objDictionary);
	    String deviceSerial = getDeviceSerial(objDictionary);
	    boolean hasDevice = deviceSerial != null && !deviceSerial.trim().isEmpty();

	    String[] cmd;
	    if (hasDevice) {
	        cmd = new String[4 + shellArgs.length];
	        cmd[0] = adbPath;
	        cmd[1] = "-s";
	        cmd[2] = deviceSerial;
	        cmd[3] = "shell";
	        System.arraycopy(shellArgs, 0, cmd, 4, shellArgs.length);
	    } else {
	        cmd = new String[2 + shellArgs.length];
	        cmd[0] = adbPath;
	        cmd[1] = "shell";
	        System.arraycopy(shellArgs, 0, cmd, 2, shellArgs.length);
	    }
	    return readAdbOutput(cmd);
	}

	private String runAsPackage(Map<String, String> objDictionary, String packageName, String... innerArgs)
	{
	    try {
	        String[] shellArgs = new String[2 + innerArgs.length];
	        shellArgs[0] = "run-as";
	        shellArgs[1] = packageName;
	        System.arraycopy(innerArgs, 0, shellArgs, 2, innerArgs.length);
	        return runAdbShell(objDictionary, shellArgs);
	    } catch (Exception e) {
	        return e.getMessage();
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

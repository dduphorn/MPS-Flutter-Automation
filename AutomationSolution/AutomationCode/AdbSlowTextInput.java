package AutomationCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Types through {@code adb shell input text} one character at a time.
 * Android 12 drops key events when {@code input text} sends a whole string at once.
 */
public final class AdbSlowTextInput
{
	public static final int DEFAULT_CHAR_DELAY_MS = 120;

	private AdbSlowTextInput() {}

	public static int charDelayMs()
	{
		String raw = System.getProperty("adb.input.char.delay.ms");
		if (raw == null || raw.trim().isEmpty()) return DEFAULT_CHAR_DELAY_MS;
		try
		{
			return Math.max(0, Integer.parseInt(raw.trim()));
		}
		catch (NumberFormatException e)
		{
			return DEFAULT_CHAR_DELAY_MS;
		}
	}

	public static String shellCommandForChar(char c)
	{
		if (c == '\n' || c == '\r') return "input keyevent 66";
		return "input text " + quotedInputToken(c);
	}

	public static void typeText(String adbPath, String deviceSerial, String text) throws Exception
	{
		if (text == null || text.isEmpty()) return;
		int delayMs = charDelayMs();
		for (int i = 0; i < text.length(); i++)
		{
			runShell(adbPath, deviceSerial, shellCommandForChar(text.charAt(i)));
			if (i < text.length() - 1 && delayMs > 0)
			{
				Thread.sleep(delayMs);
			}
		}
	}

	static String quotedInputToken(char c)
	{
		if (c == ' ') return "'%s'";
		if (c == '\'') return "''\\'''";
		return "'" + c + "'";
	}

	private static void runShell(String adbPath, String deviceSerial, String remoteCommand) throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder(adbPath, "-s", deviceSerial, "shell", remoteCommand);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
		{
			while (reader.readLine() != null) {}
		}
		if (!process.waitFor(20, TimeUnit.SECONDS))
		{
			process.destroyForcibly();
			throw new IllegalStateException("adb timed out running: " + remoteCommand);
		}
		if (process.exitValue() != 0)
		{
			throw new IllegalStateException("adb failed (" + process.exitValue() + ") running: " + remoteCommand);
		}
	}
}

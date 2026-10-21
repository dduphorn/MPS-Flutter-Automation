package AutomationCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Types into an Android EditText from the Mac host when Appium sendKeys is not the active path.
 *
 * Android 12 only delivers injected text to the focused field. With unicodeKeyboard enabled,
 * the session IME is Appium's UnicodeIME, which ignores {@code adb shell input text} and only
 * commits text from an explicit {@code ADB_INPUT_B64} broadcast. The field must already be focused
 * before {@link #typeText} is called, otherwise the broadcast has no input connection and the
 * screen stays empty.
 */
public final class AdbSlowTextInput
{
	static final String SETTINGS_IME = "io.appium.settings/.UnicodeIME";
	static final String LEGACY_IME = "io.appium.android.ime/.UnicodeIME";
	static final String BROADCAST_ACTION = "ADB_INPUT_B64";
	static final long COMMAND_TIMEOUT_SECONDS = 15L;
	static final long SLOW_CHAR_DELAY_MILLIS = 80L;

	private static final ThreadLocal<String> ACTIVE_IME_PACKAGE = new ThreadLocal<String>();

	private AdbSlowTextInput() {}

	public static boolean usesUnicodeIme(int majorAndroidVersion)
	{
		return majorAndroidVersion == 12;
	}

	public static int majorVersion(String release)
	{
		if (release == null)
		{
			return -1;
		}
		String trimmed = release.trim();
		if (trimmed.isEmpty())
		{
			return -1;
		}
		int end = 0;
		while (end < trimmed.length() && Character.isDigit(trimmed.charAt(end)))
		{
			end++;
		}
		if (end == 0)
		{
			return -1;
		}
		try
		{
			return Integer.parseInt(trimmed.substring(0, end));
		}
		catch (NumberFormatException e)
		{
			return -1;
		}
	}

	/**
	 * Prefer the dictionary UDID. Session deviceName is often the literal placeholder
	 * "DeviceName" or a marketing model, and {@code adb -s} then targets nothing.
	 */
	public static String resolveSerial(String androidUdid, String capabilityUdid, String deviceName)
	{
		if (isUsableSerial(androidUdid))
		{
			return androidUdid.trim();
		}
		if (isUsableSerial(capabilityUdid))
		{
			return capabilityUdid.trim();
		}
		if (isUsableSerial(deviceName))
		{
			return deviceName.trim();
		}
		return "";
	}

	public static String adbPathForUser(String automationUser)
	{
		return "/Users/" + automationUser + "/Library/Android/sdk/platform-tools/adb";
	}

	public static String escapeInputText(String text)
	{
		if (text == null || text.isEmpty())
		{
			return "";
		}
		StringBuilder escaped = new StringBuilder(text.length());
		for (int i = 0; i < text.length(); i++)
		{
			escaped.append(escapeInputTextChar(text.charAt(i)));
		}
		return escaped.toString();
	}

	/**
	 * True when a non-password field still shows empty or hint text, so a second entry attempt
	 * will not append a duplicate value. Password fields often report empty text after a successful
	 * entry, so they are never retried.
	 */
	public static boolean shouldRetryWithKeyEvents(String actualText, String hint, String expected, boolean password)
	{
		if (password || expected == null || expected.isEmpty())
		{
			return false;
		}
		if (actualText == null || actualText.isEmpty())
		{
			return true;
		}
		if (hint != null && !hint.isEmpty() && actualText.equals(hint))
		{
			return true;
		}
		return !actualText.equals(expected);
	}

	/**
	 * Password fields often stay blank in the hierarchy after a successful setText, so they are
	 * retried only when replaceElementValue itself failed. Other fields are retried while they
	 * still show empty or hint text.
	 */
	public static boolean needsAnotherEntryAttempt(String actualText, String hint, String expected, boolean password, boolean replaceFailed)
	{
		if (password)
		{
			return replaceFailed;
		}
		return shouldRetryWithKeyEvents(actualText, hint, expected, false);
	}

	public static void prepareUnicodeIme(String adbPath, String serial) throws IOException, InterruptedException
	{
		ACTIVE_IME_PACKAGE.set(selectUnicodeIme(adbPath, serial, AdbSlowTextInput::run));
	}

	/**
	 * Commit {@code text} into the focused EditText. On failure of the UnicodeIME broadcast,
	 * fall back to one escaped character at a time.
	 */
	public static void typeText(String adbPath, String serial, String text) throws IOException, InterruptedException
	{
		if (text == null || text.isEmpty())
		{
			return;
		}
		requireSerial(serial);
		try
		{
			run(unicodeBroadcastCommand(adbPath, serial, activePackage(), text));
		}
		catch (IOException broadcastFailed)
		{
			typeSlowly(adbPath, serial, text);
		}
	}

	/** Pre-Android 12 path: one {@code input text} of the whole escaped value. */
	public static void typeWithInputText(String adbPath, String serial, String text) throws IOException, InterruptedException
	{
		if (text == null || text.isEmpty())
		{
			return;
		}
		requireSerial(serial);
		run(legacyInputCommand(adbPath, serial, text));
	}

	public static void typeSlowly(String adbPath, String serial, String text) throws IOException, InterruptedException
	{
		requireSerial(serial);
		for (List<String> command : slowInputCommands(adbPath, serial, text))
		{
			run(command);
			Thread.sleep(SLOW_CHAR_DELAY_MILLIS);
		}
	}

	static String selectUnicodeIme(String adbPath, String serial, CommandRunner runner) throws IOException, InterruptedException
	{
		IOException lastFailure = null;
		String[] imes = new String[] { SETTINGS_IME, LEGACY_IME };
		for (int i = 0; i < imes.length; i++)
		{
			String ime = imes[i];
			try
			{
				runner.run(imeCommand(adbPath, serial, "enable", ime));
				runner.run(imeCommand(adbPath, serial, "set", ime));
				return packageOf(ime);
			}
			catch (IOException e)
			{
				lastFailure = e;
			}
		}
		if (lastFailure != null)
		{
			throw lastFailure;
		}
		throw new IOException("No Unicode IME candidates");
	}

	static List<String> imeCommand(String adbPath, String serial, String action, String ime)
	{
		return adbShell(adbPath, serial, "ime", action, ime);
	}

	static List<String> unicodeBroadcastCommand(String adbPath, String serial, String imePackage, String text)
	{
		String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
		return adbShell(adbPath, serial, "am", "broadcast", "-a", BROADCAST_ACTION, "-p", imePackage, "--es", "msg", encoded);
	}

	static List<String> legacyInputCommand(String adbPath, String serial, String text)
	{
		return adbShell(adbPath, serial, "input", "text", escapeInputText(text));
	}

	static List<List<String>> slowInputCommands(String adbPath, String serial, String text)
	{
		List<List<String>> commands = new ArrayList<List<String>>();
		if (text == null)
		{
			return commands;
		}
		for (int i = 0; i < text.length(); i++)
		{
			commands.add(adbShell(adbPath, serial, "input", "text", escapeInputTextChar(text.charAt(i))));
		}
		return commands;
	}

	static List<String> adbShell(String adbPath, String serial, String... shellArgs)
	{
		List<String> command = new ArrayList<String>();
		command.add(adbPath);
		command.add("-s");
		command.add(serial);
		command.add("shell");
		for (int i = 0; i < shellArgs.length; i++)
		{
			command.add(shellArgs[i]);
		}
		return command;
	}

	static String escapeInputTextChar(char c)
	{
		if (c == ' ')
		{
			return "%s";
		}
		if (c == '%')
		{
			return "%%";
		}
		if ("&|<>;()$`\\\"'*?[]{}~#!".indexOf(c) >= 0)
		{
			return "\\" + c;
		}
		return String.valueOf(c);
	}

	static String packageOf(String imeComponent)
	{
		int slash = imeComponent.indexOf('/');
		if (slash < 0)
		{
			return imeComponent;
		}
		return imeComponent.substring(0, slash);
	}

	private static String activePackage()
	{
		String selected = ACTIVE_IME_PACKAGE.get();
		if (selected == null || selected.isEmpty())
		{
			return packageOf(SETTINGS_IME);
		}
		return selected;
	}

	private static void requireSerial(String serial) throws IOException
	{
		if (!isUsableSerial(serial))
		{
			throw new IOException("No adb serial. deviceName is not the device UDID.");
		}
	}

	private static boolean isUsableSerial(String value)
	{
		if (value == null)
		{
			return false;
		}
		String trimmed = value.trim();
		if (trimmed.isEmpty() || "null".equalsIgnoreCase(trimmed) || "DeviceName".equals(trimmed))
		{
			return false;
		}
		return true;
	}

	private static void run(List<String> command) throws IOException, InterruptedException
	{
		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
		ByteArrayOutputStream captured = new ByteArrayOutputStream();
		Thread drain = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					process.getInputStream().transferTo(captured);
				}
				catch (IOException ignored) {}
			}
		});
		drain.setDaemon(true);
		drain.start();
		boolean finished = process.waitFor(COMMAND_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		if (!finished)
		{
			process.destroyForcibly();
			throw new IOException("timed out: " + String.join(" ", command));
		}
		drain.join(5000L);
		String output = captured.toString(StandardCharsets.UTF_8);
		int code = process.exitValue();
		String lower = output.toLowerCase();
		if (code != 0 || lower.contains("error:") || lower.contains("unknown id") || lower.contains("device not found") || lower.contains("device offline"))
		{
			throw new IOException(String.join(" ", command) + " -> " + output.trim() + " (exit " + code + ")");
		}
	}

	interface CommandRunner
	{
		void run(List<String> command) throws IOException, InterruptedException;
	}
}

package AutomationCode;

public class AdbSlowTextInputTest
{
	public static void main(String[] args)
	{
		expect("input text 'A'", AdbSlowTextInput.shellCommandForChar('A'));
		expect("input text '7'", AdbSlowTextInput.shellCommandForChar('7'));
		expect("input text '%s'", AdbSlowTextInput.shellCommandForChar(' '));
		expect("input text ''\\'''", AdbSlowTextInput.shellCommandForChar('\''));
		expect("input text '&'", AdbSlowTextInput.shellCommandForChar('&'));
		expect("input text '%'", AdbSlowTextInput.shellCommandForChar('%'));
		expect("input keyevent 66", AdbSlowTextInput.shellCommandForChar('\n'));

		String previous = System.getProperty("adb.input.char.delay.ms");
		try
		{
			System.clearProperty("adb.input.char.delay.ms");
			if (AdbSlowTextInput.charDelayMs() != AdbSlowTextInput.DEFAULT_CHAR_DELAY_MS)
			{
				throw new AssertionError("Expected the default per-character delay");
			}
			System.setProperty("adb.input.char.delay.ms", "80");
			if (AdbSlowTextInput.charDelayMs() != 80)
			{
				throw new AssertionError("Expected adb.input.char.delay.ms to override the delay");
			}
			System.setProperty("adb.input.char.delay.ms", "nope");
			if (AdbSlowTextInput.charDelayMs() != AdbSlowTextInput.DEFAULT_CHAR_DELAY_MS)
			{
				throw new AssertionError("An invalid delay should fall back to the default");
			}
		}
		finally
		{
			if (previous == null) System.clearProperty("adb.input.char.delay.ms");
			else System.setProperty("adb.input.char.delay.ms", previous);
		}
		System.out.println("AdbSlowTextInputTest passed");
	}

	private static void expect(String expected, String actual)
	{
		if (!expected.equals(actual))
		{
			throw new AssertionError("Expected [" + expected + "] but was [" + actual + "]");
		}
	}
}

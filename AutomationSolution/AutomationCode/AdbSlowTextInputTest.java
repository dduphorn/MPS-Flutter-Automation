package AutomationCode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdbSlowTextInputTest
{
	private static final String ADB = "/Users/mpsadmin/Library/Android/sdk/platform-tools/adb";
	private static final String SERIAL = "R5CW42VHVZY";

	@Test(groups = {"Unit"})
	public void usesUnicodeIme_onlyForAndroid12()
	{
		Assert.assertFalse(AdbSlowTextInput.usesUnicodeIme(11));
		Assert.assertTrue(AdbSlowTextInput.usesUnicodeIme(12));
		Assert.assertFalse(AdbSlowTextInput.usesUnicodeIme(13));
		Assert.assertFalse(AdbSlowTextInput.usesUnicodeIme(14));
		Assert.assertFalse(AdbSlowTextInput.usesUnicodeIme(0));
	}

	@Test(groups = {"Unit"})
	public void majorVersion_readsLeadingIntegerFromReleaseProperty()
	{
		Assert.assertEquals(AdbSlowTextInput.majorVersion("12"), 12);
		Assert.assertEquals(AdbSlowTextInput.majorVersion("12.1"), 12);
		Assert.assertEquals(AdbSlowTextInput.majorVersion(" 12.0.0\n"), 12);
		Assert.assertEquals(AdbSlowTextInput.majorVersion("9"), 9);
		Assert.assertEquals(AdbSlowTextInput.majorVersion(""), -1);
		Assert.assertEquals(AdbSlowTextInput.majorVersion(null), -1);
		Assert.assertEquals(AdbSlowTextInput.majorVersion("unknown"), -1);
	}

	@Test(groups = {"Unit"})
	public void resolveSerial_prefersUdidOverDeviceNamePlaceholder()
	{
		Assert.assertEquals(AdbSlowTextInput.resolveSerial(SERIAL, SERIAL, "DeviceName"), SERIAL);
		Assert.assertEquals(AdbSlowTextInput.resolveSerial(null, "emulator-5554", "Pixel 4a"), "emulator-5554");
		Assert.assertEquals(AdbSlowTextInput.resolveSerial("", " DeviceName ", "0123456789ABCDEF"), "0123456789ABCDEF");
		Assert.assertEquals(AdbSlowTextInput.resolveSerial("null", null, "  "), "");
	}

	@Test(groups = {"Unit"})
	public void escapeInputText_keepsRegisterValuesAndEncodesShellSpecials()
	{
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("Delete"), "Delete");
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("User"), "User");
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("XDeleteUser@gmail.com"), "XDeleteUser@gmail.com");
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("XDeleteMeMN01"), "XDeleteMeMN01");
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("a b"), "a%sb");
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("100%"), "100%%");
		Assert.assertEquals(AdbSlowTextInput.escapeInputText("a&b"), "a\\&b");
	}

	@Test(groups = {"Unit"})
	public void unicodeBroadcast_targetsUdidAndBase64OfTheWholeValue()
	{
		String email = "XDeleteUser@gmail.com";
		List<String> command = AdbSlowTextInput.unicodeBroadcastCommand(ADB, SERIAL, "io.appium.settings", email);
		Assert.assertEquals(command.get(0), ADB);
		Assert.assertEquals(command.get(1), "-s");
		Assert.assertEquals(command.get(2), SERIAL);
		Assert.assertTrue(command.contains("am"));
		Assert.assertTrue(command.contains("broadcast"));
		Assert.assertTrue(command.contains("ADB_INPUT_B64"));
		Assert.assertTrue(command.contains("-p"));
		Assert.assertTrue(command.contains("io.appium.settings"));
		Assert.assertFalse(command.contains("input"));
		String encoded = command.get(command.size() - 1);
		String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
		Assert.assertEquals(decoded, email);
	}

	@Test(groups = {"Unit"})
	public void slowInputCommands_sendOneEscapedCharacterAtATime()
	{
		List<List<String>> commands = AdbSlowTextInput.slowInputCommands(ADB, SERIAL, "A B");
		Assert.assertEquals(commands.size(), 3);
		Assert.assertEquals(commands.get(0).get(commands.get(0).size() - 1), "A");
		Assert.assertEquals(commands.get(1).get(commands.get(1).size() - 1), "%s");
		Assert.assertEquals(commands.get(2).get(commands.get(2).size() - 1), "B");
		Assert.assertEquals(commands.get(0).get(2), SERIAL);
	}

	@Test(groups = {"Unit"})
	public void legacyInputCommand_escapesTheWholeValueAsOneArgument()
	{
		List<String> command = AdbSlowTextInput.legacyInputCommand(ADB, SERIAL, "XDeleteUser@gmail.com");
		Assert.assertEquals(command.get(command.size() - 3), "input");
		Assert.assertEquals(command.get(command.size() - 2), "text");
		Assert.assertEquals(command.get(command.size() - 1), "XDeleteUser@gmail.com");
	}

	@Test(groups = {"Unit"})
	public void selectUnicodeIme_fallsBackToLegacyPackage() throws Exception
	{
		List<String> attempted = new ArrayList<String>();
		AdbSlowTextInput.CommandRunner runner = new AdbSlowTextInput.CommandRunner()
		{
			public void run(List<String> command) throws IOException
			{
				attempted.add(command.get(command.size() - 1));
				if (command.get(command.size() - 1).startsWith("io.appium.settings"))
				{
					throw new IOException("Unknown id: io.appium.settings/.UnicodeIME");
				}
			}
		};
		String selected = AdbSlowTextInput.selectUnicodeIme(ADB, SERIAL, runner);
		Assert.assertEquals(selected, "io.appium.android.ime");
		Assert.assertEquals(attempted.get(0), AdbSlowTextInput.SETTINGS_IME);
		Assert.assertTrue(attempted.contains(AdbSlowTextInput.LEGACY_IME));
	}

	@Test(groups = {"Unit"})
	public void needsAnotherEntryAttempt_retriesFailedPasswordReplaceAndEmptyVisibleFields()
	{
		Assert.assertTrue(AdbSlowTextInput.needsAnotherEntryAttempt("", null, "XDeleteMeMN01", true, true));
		Assert.assertFalse(AdbSlowTextInput.needsAnotherEntryAttempt("", null, "XDeleteMeMN01", true, false));
		Assert.assertTrue(AdbSlowTextInput.needsAnotherEntryAttempt("", null, "Delete", false, false));
		Assert.assertTrue(AdbSlowTextInput.needsAnotherEntryAttempt("This will be your user id", "This will be your user id", "XDeleteUser@gmail.com", false, false));
		Assert.assertFalse(AdbSlowTextInput.needsAnotherEntryAttempt("Delete", null, "Delete", false, false));
	}

	@Test(groups = {"Unit"})
	public void shouldRetryWithKeyEvents_retriesEmptyOrHintAndSkipsPasswords()
	{
		Assert.assertTrue(AdbSlowTextInput.shouldRetryWithKeyEvents("", null, "Delete", false));
		Assert.assertTrue(AdbSlowTextInput.shouldRetryWithKeyEvents("This will be your user id", "This will be your user id", "XDeleteUser@gmail.com", false));
		Assert.assertFalse(AdbSlowTextInput.shouldRetryWithKeyEvents("Delete", null, "Delete", false));
		Assert.assertFalse(AdbSlowTextInput.shouldRetryWithKeyEvents("XDeleteUser@gmail.com", "This will be your user id", "XDeleteUser@gmail.com", false));
		Assert.assertFalse(AdbSlowTextInput.shouldRetryWithKeyEvents("", null, "XDeleteMeMN01", true));
		Assert.assertFalse(AdbSlowTextInput.shouldRetryWithKeyEvents(null, null, "", false));
	}
}

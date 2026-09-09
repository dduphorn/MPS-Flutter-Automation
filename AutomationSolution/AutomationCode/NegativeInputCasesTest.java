package AutomationCode;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeInputCasesTest
{
	@Test(groups = {"Unit"})
	public void excessiveInput_isExactlyTenThousandFillCharacters()
	{
		String payload = NegativeInputCases.excessiveInput('a');
		Assert.assertEquals(payload.length(), NegativeInputCases.EXCESSIVE_INPUT_LENGTH);
		Assert.assertTrue(payload.matches("a{" + NegativeInputCases.EXCESSIVE_INPUT_LENGTH + "}"));
	}

	@Test(groups = {"Unit"})
	public void injectionPayloads_includeSqlAndXssFromThePlan()
	{
		String[] payloads = NegativeInputCases.injectionPayloads();
		Assert.assertEquals(payloads.length, 2);
		Assert.assertEquals(payloads[0], NegativeInputCases.SQL_INJECTION);
		Assert.assertEquals(payloads[1], NegativeInputCases.XSS_INJECTION);
		Assert.assertEquals(payloads[0], "' OR 1=1 --");
		Assert.assertEquals(payloads[1], "<script>alert(1)</script>");
	}

	@Test(groups = {"Unit"})
	public void looksLikeCrash_detectsFatalOverlaysAndIgnoresShortOrNullSource()
	{
		Assert.assertFalse(NegativeInputCases.looksLikeCrash(null));
		Assert.assertFalse(NegativeInputCases.looksLikeCrash("tiny"));
		Assert.assertFalse(NegativeInputCases.looksLikeCrash(repeat("Login form Email or Phone number Password Log in ", 4)));
		Assert.assertTrue(NegativeInputCases.looksLikeCrash(repeat("x", 50) + " SentryMobile keeps stopping"));
		Assert.assertTrue(NegativeInputCases.looksLikeCrash(repeat("x", 50) + " isn't responding"));
		Assert.assertTrue(NegativeInputCases.looksLikeCrash(repeat("x", 50) + " isnt responding"));
		Assert.assertTrue(NegativeInputCases.looksLikeCrash(repeat("x", 50) + " unfortunately the app has stopped"));
		Assert.assertTrue(NegativeInputCases.looksLikeCrash(repeat("x", 50) + " Exception caught by Flutter"));
	}

	@Test(groups = {"Unit"})
	public void looksLikeLoginForm_andLoggedIn_areMutuallyExclusive()
	{
		String login = "<android.view.View content-desc=\"Email or Phone number\"/><android.widget.EditText/><android.view.View content-desc=\"Password\"/><android.view.View content-desc=\"Log in\"/>";
		String account = "<android.widget.ImageView content-desc=\"Account\"/><android.widget.ImageView content-desc=\"Park\"/>";
		Assert.assertTrue(NegativeInputCases.looksLikeLoginForm(login));
		Assert.assertFalse(NegativeInputCases.looksLikeLoggedIn(login));
		Assert.assertTrue(NegativeInputCases.looksLikeLoggedIn(account));
		Assert.assertFalse(NegativeInputCases.looksLikeLoggedIn(null));
		Assert.assertFalse(NegativeInputCases.looksLikeLoginForm(null));
		Assert.assertTrue(NegativeInputCases.looksLikeLoggedIn("<node content-desc='Session history'/>"));
	}

	@Test(groups = {"Unit"})
	public void looksLikeValidationOrInvalidLogin_coversCredentialAndLengthMessages()
	{
		Assert.assertFalse(NegativeInputCases.looksLikeValidationOrInvalidLogin(null));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("Invalid credentials, please contact customer support."));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("Invalid email"));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("Please enter a valid phone"));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("Value is too long"));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("character limit"));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("Email is not valid"));
		Assert.assertTrue(NegativeInputCases.looksLikeValidationOrInvalidLogin("Field cannot be blank"));
	}

	@Test(groups = {"Unit"})
	public void looksLikeLocationOrPermissionPrompt_matchesPlanDegradeCopy()
	{
		Assert.assertFalse(NegativeInputCases.looksLikeLocationOrPermissionPrompt(null));
		Assert.assertTrue(NegativeInputCases.looksLikeLocationOrPermissionPrompt("For a better experience, your device will need to use Location Accuracy"));
		Assert.assertTrue(NegativeInputCases.looksLikeLocationOrPermissionPrompt("Turn on location services"));
		Assert.assertTrue(NegativeInputCases.looksLikeLocationOrPermissionPrompt("No thanks"));
		Assert.assertTrue(NegativeInputCases.looksLikeLocationOrPermissionPrompt("Allow camera permission"));
		Assert.assertTrue(NegativeInputCases.looksLikeLocationOrPermissionPrompt("While using the app"));
		Assert.assertTrue(NegativeInputCases.looksLikeLocationOrPermissionPrompt("GPS is off"));
	}

	@Test(groups = {"Unit"})
	public void xssPayload_isPlainTextWhenStillVisible_andExecutedWhenAlertLacksTheTags()
	{
		String payload = NegativeInputCases.XSS_INJECTION;
		String asText = "<android.widget.EditText text=\"<script>alert(1)</script>\"/>";
		Assert.assertTrue(NegativeInputCases.injectionAppearsAsPlainText(asText, payload));
		Assert.assertFalse(NegativeInputCases.looksLikeScriptExecuted(asText, payload));
		Assert.assertTrue(NegativeInputCases.injectionAppearsAsPlainText("&lt;script&gt;alert(1)&lt;/script&gt;", payload));
		Assert.assertTrue(NegativeInputCases.looksLikeScriptExecuted("javascript alert dialog from the page at app", payload));
		Assert.assertFalse(NegativeInputCases.looksLikeScriptExecuted("login form", "' OR 1=1 --"));
		Assert.assertFalse(NegativeInputCases.looksLikeScriptExecuted(null, payload));
		Assert.assertFalse(NegativeInputCases.injectionAppearsAsPlainText(null, payload));
		Assert.assertFalse(NegativeInputCases.injectionAppearsAsPlainText("abc", null));
		Assert.assertFalse(NegativeInputCases.injectionAppearsAsPlainText("abc", ""));
	}

	@Test(groups = {"Unit"})
	public void loginAttemptDidNotBypassAuth_requiresStayOnLoginOrValidation_notCrashOrHome()
	{
		String login = "<android.view.View content-desc=\"Email or Phone number\"/> Password Log in";
		String invalid = "Invalid credentials, please contact customer support.";
		String home = "<android.widget.ImageView content-desc=\"Account\"/>";
		String crash = repeat("x", 50) + " keeps stopping";
		Assert.assertTrue(NegativeInputCases.loginAttemptDidNotBypassAuth(login));
		Assert.assertTrue(NegativeInputCases.loginAttemptDidNotBypassAuth(invalid));
		Assert.assertFalse(NegativeInputCases.loginAttemptDidNotBypassAuth(home));
		Assert.assertFalse(NegativeInputCases.loginAttemptDidNotBypassAuth(crash));
	}

	@Test(groups = {"Unit"})
	public void xmlEscape_encodesMarkupAndQuotes()
	{
		Assert.assertEquals(NegativeInputCases.xmlEscape(null), "");
		Assert.assertEquals(NegativeInputCases.xmlEscape("<script>\"x\" & 'y'</script>"),
				"&lt;script&gt;&quot;x&quot; &amp; &apos;y&apos;&lt;/script&gt;");
	}

	private static String repeat(String value, int times)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) sb.append(value);
		return sb.toString();
	}
}

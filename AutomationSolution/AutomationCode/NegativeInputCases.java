package AutomationCode;

/**
 * Device-free helpers for Mobile QA Test Plan input cases (TC-NEG-18, TC-NEG-19)
 * and shared crash / degrade / login-screen detection used by A2209F–A2214F.
 */
public class NegativeInputCases
{
	public static final int EXCESSIVE_INPUT_LENGTH = 10000;
	public static final String SQL_INJECTION = "' OR 1=1 --";
	public static final String XSS_INJECTION = "<script>alert(1)</script>";

	public static String excessiveInput(char fill)
	{
		StringBuilder sb = new StringBuilder(EXCESSIVE_INPUT_LENGTH);
		for (int i = 0; i < EXCESSIVE_INPUT_LENGTH; i++)
		{
			sb.append(fill);
		}
		return sb.toString();
	}

	public static String[] injectionPayloads()
	{
		return new String[] { SQL_INJECTION, XSS_INJECTION };
	}

	public static boolean looksLikeCrash(String pageSource)
	{
		if (pageSource == null || pageSource.trim().length() < 50)
		{
			return false;
		}
		String lower = pageSource.toLowerCase();
		return lower.contains("keeps stopping")
				|| lower.contains("isn't responding")
				|| lower.contains("isnt responding")
				|| lower.contains("unfortunately")
				|| lower.contains("has stopped")
				|| lower.contains("exception caught by flutter");
	}

	public static boolean looksLikeLoginForm(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("email or phone")
				|| lower.contains("email or phone number")
				|| (lower.contains("password") && lower.contains("log in"));
	}

	public static boolean looksLikeLoggedIn(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		if (looksLikeLoginForm(pageSource)) return false;
		return lower.contains("content-desc=\"account\"")
				|| lower.contains("content-desc='account'")
				|| lower.contains("content-desc=\"park\"")
				|| lower.contains("content-desc='park'")
				|| lower.contains("content-desc=\"session")
				|| lower.contains("content-desc='session");
	}

	public static boolean looksLikeValidationOrInvalidLogin(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("invalid credential")
				|| lower.contains("invalid email")
				|| lower.contains("invalid phone")
				|| lower.contains("please enter")
				|| lower.contains("too long")
				|| lower.contains("character")
				|| lower.contains("not valid")
				|| lower.contains("cannot be")
				|| lower.contains("customer support");
	}

	public static boolean looksLikeLocationOrPermissionPrompt(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("location accuracy")
				|| lower.contains("location service")
				|| lower.contains("turn on")
				|| lower.contains("no thanks")
				|| lower.contains("permission")
				|| lower.contains("while using the app")
				|| lower.contains("allow camera")
				|| lower.contains("access this device")
				|| lower.contains("gps");
	}

	public static boolean looksLikeScriptExecuted(String pageSource, String payload)
	{
		if (pageSource == null || payload == null) return false;
		String lower = pageSource.toLowerCase();
		if (!payload.toLowerCase().contains("<script>")) return false;
		boolean payloadStillVisibleAsText = lower.contains("<script>alert(1)</script>")
				|| lower.contains("&lt;script&gt;alert(1)&lt;/script&gt;");
		if (payloadStillVisibleAsText) return false;
		return lower.contains("javascript alert")
				|| lower.contains("the page at")
				|| lower.contains("alert(1)");
	}

	public static boolean injectionAppearsAsPlainText(String pageSource, String payload)
	{
		if (pageSource == null || payload == null || payload.isEmpty()) return false;
		return pageSource.contains(payload)
				|| pageSource.contains(xmlEscape(payload));
	}

	public static boolean loginAttemptDidNotBypassAuth(String pageSource)
	{
		if (looksLikeCrash(pageSource)) return false;
		if (looksLikeLoggedIn(pageSource)) return false;
		return looksLikeLoginForm(pageSource) || looksLikeValidationOrInvalidLogin(pageSource);
	}

	static String xmlEscape(String value)
	{
		if (value == null) return "";
		return value
				.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot;")
				.replace("'", "&apos;");
	}
}

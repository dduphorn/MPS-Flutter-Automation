package AutomationCode;

/**
 * Device-free helpers for Mobile QA Test Plan input cases (TC-NEG-04, 05, 12, 13, 18, 19),
 * shared crash / degrade / login-screen detection used by A2209F–A2219F, and PEO
 * Espresso-style null/empty fallback checks used by P4061F–P4062F.
 */
public class NegativeInputCases
{
	public static final int EXCESSIVE_INPUT_LENGTH = 10000;
	public static final String SQL_INJECTION = "' OR 1=1 --";
	public static final String XSS_INJECTION = "<script>alert(1)</script>";
	public static final String STORAGE_FILL_PATH = "/sdcard/Download/mps_neg04_fill.bin";
	public static final long STORAGE_FILL_CAP_KB = 512L * 1024L;
	public static final long STORAGE_LEAVE_FREE_KB = 200L * 1024L;
	public static final String HTTP_PROXY_BLACKHOLE = "192.0.2.1:8080";
	public static final String PRIVATE_DNS_INVALID = "dns.invalid";
	public static final String PEO_PACKAGE = "com.mpspark.mobileOfficer";

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

	public static String[] nullIntentExtraKeys()
	{
		return new String[] { "USER", "TOKEN", "extra", "userId", "authToken" };
	}

	public static String amStartLauncherWithNullExtras(String packageName)
	{
		if (packageName == null || packageName.trim().isEmpty())
		{
			packageName = PEO_PACKAGE;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -p ");
		sb.append(packageName);
		for (String key : nullIntentExtraKeys())
		{
			sb.append(" --esn ").append(key);
		}
		return sb.toString();
	}

	public static boolean looksLikePeoLoginForm(String pageSource)
	{
		if (pageSource == null) return false;
		if (looksLikePeoLoggedIn(pageSource)) return false;
		String lower = pageSource.toLowerCase();
		boolean signIn = lower.contains("sign in");
		boolean credentials = lower.contains("email") || lower.contains("password");
		return signIn && credentials;
	}

	public static boolean looksLikePeoLoggedIn(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("new violations")
				|| lower.contains("create violation")
				|| lower.contains("snoozed violations");
	}

	public static boolean looksLikeEmptyOrNullFallbackUi(String pageSource, String snackbar)
	{
		String combined = ((snackbar == null ? "" : snackbar) + " " + (pageSource == null ? "" : pageSource)).toLowerCase();
		if (combined.trim().isEmpty()) return false;
		return combined.contains("please enter")
				|| combined.contains("cannot be blank")
				|| combined.contains("cannot be empty")
				|| combined.contains("is required")
				|| combined.contains("invalid credential")
				|| combined.contains("invalid email")
				|| combined.contains("no violation")
				|| combined.contains("no results")
				|| combined.contains("nothing to display")
				|| combined.contains("no data")
				|| combined.contains("try again")
				|| combined.contains("something went wrong")
				|| combined.contains("unable to")
				|| combined.contains("failed to");
	}

	public static boolean peoNullOrEmptyDidNotBypassAuth(String pageSource, String snackbar)
	{
		if (looksLikeCrash(pageSource)) return false;
		if (looksLikePeoLoggedIn(pageSource)) return false;
		return looksLikePeoLoginForm(pageSource) || looksLikeEmptyOrNullFallbackUi(pageSource, snackbar);
	}

	public static boolean looksLikeLowStorage(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("not enough space")
				|| lower.contains("free up")
				|| lower.contains("storage space")
				|| lower.contains("low storage")
				|| lower.contains("insufficient storage")
				|| lower.contains("disk full")
				|| lower.contains("storage is running out");
	}

	public static boolean looksLikeBiometricPrompt(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("biometric")
				|| lower.contains("fingerprint")
				|| lower.contains("face unlock")
				|| lower.contains("face id")
				|| lower.contains("touch id")
				|| lower.contains("not right now")
				|| lower.contains("use fingerprint")
				|| lower.contains("confirm fingerprint")
				|| lower.contains("verify your identity");
	}

	public static boolean looksLikeApiOrNetworkError(String pageSource)
	{
		if (pageSource == null) return false;
		String lower = pageSource.toLowerCase();
		return lower.contains("timed out")
				|| lower.contains("timeout")
				|| lower.contains("try again")
				|| lower.contains("retry")
				|| lower.contains("unable to")
				|| lower.contains("failed to")
				|| lower.contains("certificate")
				|| lower.contains("ssl")
				|| lower.contains("proxy")
				|| lower.contains("unable to connect")
				|| lower.contains("connection")
				|| lower.contains("unreachable")
				|| lower.contains("something went wrong")
				|| lower.contains("network request")
				|| lower.contains("dns");
	}

	public static long parseAvailableKbFromDf(String dfOutput)
	{
		if (dfOutput == null || dfOutput.trim().isEmpty()) return -1;
		String[] lines = dfOutput.trim().split("\\r?\\n");
		for (int i = lines.length - 1; i >= 0; i--)
		{
			String line = lines[i].trim();
			if (line.isEmpty() || line.toLowerCase().startsWith("filesystem")) continue;
			String[] parts = line.split("\\s+");
			if (parts.length >= 4)
			{
				try
				{
					return Long.parseLong(parts[3]);
				}
				catch (NumberFormatException e)
				{
					try
					{
						return Long.parseLong(parts[parts.length - 3]);
					}
					catch (NumberFormatException e2)
					{
						return -1;
					}
				}
			}
		}
		return -1;
	}

	public static long computeFillKb(long availableKb, long capKb, long leaveFreeKb)
	{
		if (availableKb < 0) return 0;
		long room = availableKb - leaveFreeKb;
		if (room <= 0) return 0;
		return Math.min(capKb, room);
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

package AutomationCode;

import java.util.List;

import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * Server flags Appium Inspector needs in order to attach to a session that is already
 * running on a device. Without CORS the Inspector UI cannot call the server, and without
 * session discovery the Attach to Session tab cannot see the live device session.
 */
public final class AppiumInspectorServer {
	public static final String ALLOW_CORS = "--allow-cors";
	public static final String RELAXED_SECURITY = "--relaxed-security";
	public static final String ALLOW_INSECURE = "--allow-insecure";
	/** Wildcard scope is required for the server-level session_discovery feature. */
	public static final String SESSION_DISCOVERY = "*:session_discovery";

	private AppiumInspectorServer() {
	}

	public static List<String> arguments() {
		return List.of(ALLOW_CORS, RELAXED_SECURITY, ALLOW_INSECURE, SESSION_DISCOVERY);
	}

	public static AppiumServiceBuilder apply(AppiumServiceBuilder builder) {
		builder.withArgument(flag(ALLOW_CORS));
		builder.withArgument(flag(RELAXED_SECURITY));
		builder.withArgument(flag(ALLOW_INSECURE), SESSION_DISCOVERY);
		return builder;
	}

	public static String attachMessage(String sessionId, String port, String deviceId) {
		return "Appium Inspector can attach to session " + sessionId
				+ " for device " + deviceId
				+ " at http://127.0.0.1:" + port + "/";
	}

	private static ServerArgument flag(String name) {
		return () -> name;
	}
}

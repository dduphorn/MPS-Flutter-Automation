package AutomationCode;

import java.util.List;

public class AppiumInspectorServerTest {
	public static void main(String[] args) {
		List<String> serverArguments = AppiumInspectorServer.arguments();
		if (!serverArguments.contains(AppiumInspectorServer.ALLOW_CORS)) {
			throw new AssertionError("Inspector attach requires --allow-cors, got " + serverArguments);
		}
		if (!serverArguments.contains(AppiumInspectorServer.RELAXED_SECURITY)) {
			throw new AssertionError("Inspector attach requires --relaxed-security, got " + serverArguments);
		}
		int allowInsecure = serverArguments.indexOf(AppiumInspectorServer.ALLOW_INSECURE);
		if (allowInsecure < 0 || allowInsecure + 1 >= serverArguments.size()
				|| !AppiumInspectorServer.SESSION_DISCOVERY.equals(serverArguments.get(allowInsecure + 1))) {
			throw new AssertionError("session_discovery must follow --allow-insecure, got " + serverArguments);
		}
		if (!AppiumInspectorServer.SESSION_DISCOVERY.startsWith("*:")) {
			throw new AssertionError("session_discovery must use the server-wide wildcard scope");
		}

		String message = AppiumInspectorServer.attachMessage("abc-session", "4723", "R5CT71MQ2MM");
		if (!message.contains("abc-session") || !message.contains("4723") || !message.contains("R5CT71MQ2MM")) {
			throw new AssertionError("Attach message did not name the session, port, and device: " + message);
		}
		if (!message.contains("http://127.0.0.1:4723/")) {
			throw new AssertionError("Attach message must use the Appium 2/3 root path, got " + message);
		}
		System.out.println("AppiumInspectorServerTest passed");
	}
}

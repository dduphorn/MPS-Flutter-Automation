package AutomationCode;

import java.net.NoRouteToHostException;

import com.jcraft.jsch.JSchException;

public class MeterConnectRetryTest
{
	public static void main(String[] args)
	{
		JSchException noRoute = new JSchException("session is down", new NoRouteToHostException("No route to host"));
		if (!Meter.shouldRetryMeterConnect(noRoute))
		{
			throw new AssertionError("No route to host should be retried");
		}
		JSchException authFail = new JSchException("Auth fail");
		if (Meter.shouldRetryMeterConnect(authFail))
		{
			throw new AssertionError("Auth fail should not be retried");
		}
		System.out.println("MeterConnectRetryTest passed");
	}
}

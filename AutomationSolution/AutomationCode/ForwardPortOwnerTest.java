package AutomationCode;

import java.util.List;

public class ForwardPortOwnerTest
{
	public static void main(String[] args)
	{
		String forwards = "R5CT71MQ5YT tcp:8200 tcp:6790\nR5CW42VHVZY tcp:8200 tcp:6790\nR5CW42VHVZY tcp:7811 tcp:7811\n";
		List<String> port8200 = CommonANDROID_Flutter.serialsHoldingHostPort(forwards, "8200");
		if (port8200.size() != 2 || !port8200.get(0).equals("R5CT71MQ5YT") || !port8200.get(1).equals("R5CW42VHVZY"))
		{
			throw new AssertionError("Both phones holding tcp:8200 should be returned, got " + port8200);
		}
		List<String> port8201 = CommonANDROID_Flutter.serialsHoldingHostPort(forwards, "8201");
		if (!port8201.isEmpty())
		{
			throw new AssertionError("tcp:8201 is not forwarded and should not be cleared, got " + port8201);
		}
		List<String> blank = CommonANDROID_Flutter.serialsHoldingHostPort(null, "8200");
		if (!blank.isEmpty())
		{
			throw new AssertionError("A missing forward list should not match a port");
		}
		System.out.println("ForwardPortOwnerTest passed");
	}
}

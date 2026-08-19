package AutomationCode;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class CompareMeterSettings
{

	public void METER_CompareMeterSettingsDual()
	{
		JSch jsch = new JSch();
		String strPassword = "firesale";
		String strHost1 = "10.10.103.98";
		String strHostType1 = "root";
		String strHost2 = "10.10.102.182";
		String strHostType2 = "root";
		try
		{
			// Retrieve JSON from the first host
	    	Session session = jsch.getSession(strHostType1, strHost1, 22);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_settings.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1024];
	        ByteArrayOutputStream output = new ByteArrayOutputStream(); // Use a ByteArrayOutputStream to store the complete output
	        while (true) {
	            int bytesRead = in.read(tmp, 0, 1024);
	            if (bytesRead < 0) {
	                break;
	            }
	            output.write(tmp, 0, bytesRead);
	        }
	        byte[] result = output.toByteArray();
	        // Convert the byte array to JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode1 = objectMapper.readTree(result);
	        // Print the JSON to the console
	        //System.out.println(jsonNode1.toPrettyString());


	        // Retrieve JSON from the second host
	        Session session2 = jsch.getSession(strHostType2, strHost2, 22);
	    	session2.setPassword(strPassword);
	        session2.setConfig("StrictHostKeyChecking", "no");
	        session2.connect();
	        Channel channel2=session2.openChannel("exec");
	        ((ChannelExec)channel2).setCommand("/usr/local/bin/settings_get_settings.py");
	        channel2.setInputStream(null);
	        ((ChannelExec)channel2).setErrStream(System.err);
	        InputStream in2=channel2.getInputStream();
	        channel2.connect();
	        byte[] tmp2 = new byte[1024];
	        ByteArrayOutputStream output2 = new ByteArrayOutputStream(); // Use a ByteArrayOutputStream to store the complete output
	        while (true) {
	            int bytesRead2 = in2.read(tmp2, 0, 1024);
	            if (bytesRead2 < 0) {
	                break;
	            }
	            output2.write(tmp2, 0, bytesRead2);

	        }
	        byte[] result2 = output2.toByteArray();
	        // Convert the byte array to JSON
	        ObjectMapper objectMapper2 = new ObjectMapper();
	        JsonNode jsonNode2 = objectMapper2.readTree(result2);
	        // Print the JSON to the console
	        //System.out.println(jsonNode2.toPrettyString());

	        String jsonString1 = jsonNode1.toString();
	        String jsonString2 = jsonNode2.toString();


	        JSONObject json1 = new JSONObject(jsonString1);
	        JSONObject json2 = new JSONObject(jsonString2);

	        // Array of keys to be ignored
	        String[] ignoredKeys =
	        	{
	        		"EPAY_READER_SERIAL_NUMBER", "PARKING_VMD_SETTINGS", "SYS_LATITUDE", "SYS_NO_PARKING_OVERLAY","EPAY_READER_SERIAL_NUMBER",
	        		"COIN_VOLUME_MAP", "SYS_MAC_ADDRESS", "SYS_LONGITUDE", "CAMERA_USER_DETECT_SETTINGS", "SYS_DEVICE_ID", "SYS_IP_ADDRESS",
	        		"OGG_DOWN_USER","SYS_LOAD_CHECK_LAST_THRESHOLD_EXCEEDED","SYS_FRIENDLY_NAME","CREDIT_CALL_TRANSACTION_KEY","SYS_COS_ADDRESS_BINDING",
	        		"CREDIT_CALL_TERMINAL_ID","RABBITMQ_HOST","SYS_HELP_VOLUME","PARKING_IMAGE_SILVER_BULLET_IP_ADDR"
	        	};

	        JSONArray keys1 = json1.names();
	        int numKeys1 = keys1.length();
	        System.out.println(strHost1+" - Number of keys: " + numKeys1);
	        JSONArray keys2 = json2.names();
	        int numKeys2 = keys2.length();
	        System.out.println(strHost2+" - Number of keys: " + numKeys2);

	        //Make the host ip string the same length
	        if (strHost1.length() > strHost2.length()) {
	            System.out.println("MIH");
	            int difference = strHost1.length() - strHost2.length();
	            StringBuilder sb = new StringBuilder(strHost2);
	            for (int i = 0; i < difference; i++)
	            {sb.append(" ");} // Update the strHost2 string with the appended spaces
	        } else if (strHost2.length() > strHost1.length()) {
	            System.out.println("MIH");
	            int difference = strHost2.length() - strHost1.length();
	            StringBuilder sb = new StringBuilder(strHost1);
	            for (int i = 0; i < difference; i++) {sb.append(" ");}
	            strHost1 = sb.toString();
	        }

	        // Compare the keys
	        int intDifferenenceCounter = 0;
	        int intTotalCounter = 0;
	        if (!keys1.equals(keys2)) {
	            System.out.println("Keys are different between the JSON structures.");
	        } else {
	            // Iterate through the keys
	            for (int i = 0; i < keys1.length(); i++) {
	                String key = keys1.getString(i);

	                // Check if the key should be ignored
	                if (shouldIgnoreKey(key, ignoredKeys)) {
	                    continue; // Skip the key and proceed to the next iteration
	                }

	                Object value1 = json1.get(key).toString().trim();
	                Object value2 = json2.get(key).toString().trim();

	                // Compare the values
	                if (!value1.equals(value2)) {
	                    System.out.println("Difference found for key: " + key);
	                    System.out.println("Value in "+strHost1+": " + value1);
	                    System.out.println("Value in "+strHost2+": " + value2);
	                    System.out.println();
	                    intDifferenenceCounter++;
	                }
	                intTotalCounter++;
	            }
	            System.out.println("Number of differenences: "+intDifferenenceCounter);
	            System.out.println("Total: "+intTotalCounter);
	        }
	        System.out.println("MIH");
		}
		catch(Exception e){System.out.println(e);}
	}
	private static boolean shouldIgnoreKey(String key, String[] ignoredKeys) {
	    for (String ignoredKey : ignoredKeys) {
	        if (key.equals(ignoredKey)) {
	            return true;
	        }
	    }
	    return false;
	}
}

package AutomationCode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.Reporter;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class Gmail
{
	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,String strErrorMsg)
  	{
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");
  		String strHost = objDictionary.get("strHost");
  		if(strAssociatedBug == null){strAssociatedBug = "";}
  		String strPivotalId = "";
  		String strPattern = "";
  		Pattern CompilePattern = null;
  		Matcher MatchPattern = null;
  		//Create a request to the Pivotal API to get bug status and assigned to
  		switch (strErrorMsg)
  	    {
  			case "The Button (Set to normal mode)  did not exist-ClickButton":
  				Reporter.log(strErrorMsg);
				strErrorMsg = "The maintenance mode \"MAINT_PARKING_SPOT\" is putting the meter in Free Parking instead of Maintenance mode.";
				strPivotalId = "154970036";
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
				clsMeter.METER_MeterWaitWithMessage(objDictionary,120, "Waiting for Meter To Reboot");
				break;
			default:
  		    		if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 500 Internal Server Error"))
  		    		{strPivotalId = "137885163";break;}
  		    		strPattern = "Email Alert did not exist: Vehicle Departed at spot .* \\(Violation: Initial Grace Period Exceeded\\)";
  		    		CompilePattern = Pattern.compile(strPattern);
  		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
  		    		if(MatchPattern.find( ))
  		    		{
  		    			strPivotalId = "178800354";Reporter.log(strErrorMsg);
  		    			strErrorMsg = "PEO's aren't getting email alerts when the vehicle exits after a violation.";
  		    			break;
  		    		}
  		    		strPattern = "The cell value in row (.*) column (.*) of the table \\(Parking Session History\\) did not equal \\(CSR Approved .*\\) - actual value \\(Claimed .*\\)";
  		    		CompilePattern = Pattern.compile(strPattern);
  		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
  		    		if(MatchPattern.find( ))
  		    		{
  		    			strPivotalId = "155234278";Reporter.log(strErrorMsg);
  		    			strErrorMsg = "When Generate Ticket is clicked in PEO app it overrides the CSR Approval  time and email address in Sentry Link History page.";
  		    			break;
  		    		}
  		    		strPattern = "The cell value in row (.*) column (.*) of the table \\(Parking Session History\\) did not equal \\(CSR Approved .*\\) - actual value \\(To Verify\\)";
  		    		CompilePattern = Pattern.compile(strPattern);
  		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
  		    		if(MatchPattern.find( ))
  		    		{
  		    			strPivotalId = "155234278";Reporter.log(strErrorMsg);
  		  			strErrorMsg = "When Generate Ticket is clicked in PEO app it overrides the CSR Approval  time and email address in Sentry Link History page.";
  		    			break;
  		    		}
  		    		strPattern = "The violation alert message did not contain (.*)-actual \\(\\)";
  		    		CompilePattern = Pattern.compile(strPattern);
  		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
  		    		if(MatchPattern.find( ))
  		    		{
  		    			Reporter.log(strErrorMsg);
					strErrorMsg = "PEO not receiving violation emails when RIch runs script in staging";
					strPivotalId = "156175760";
					//Disable Subscription violation email
			      	clsCommonWeb.SENTRYLINK_PopulateOtherSubscriptions(objDictionary,"violation", "UnChecked", "email");
  		    			break;
  		    		}
  	    }
  		if(!strPivotalId.equals(""))
  		{
  			if(strAssociatedBug.contains(strPivotalId)||strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
  			{
  				//Yellow Means Know Issue
  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				//RPSS: Remain Parked Short Session
  		      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked on error (15) seconds-Spot 1");
  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  			}
  			else
  			{
  				//Red Means New Issue
  				Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				//RPSS: Remain Parked Short Session
  		      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked or error (15) seconds-Spot 1");
  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  			}
  		}
  		else
  		{
  	        	Reporter.log("<font color='red'>"+strErrorMsg+"</font>");
  	        	//if(driver != null){TakeWebScreenShoot(objDictionary,driver);}
  	        	//RPSS: Remain Parked Short Session
  	      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked (15) seconds-Spot 1");
  	      	Assert.fail(strErrorMsg);
  		}
  	}
	public String Gmail_GetParkerGmailPassword(Map<String, String> objDictionary)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strRole = "parker";
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
		switch (strUserName)
		{
			case "aaMinnetonkaparker@gmail.com":
				return "vhjenncuqvniufhm ";
			case "ffAutomationMunicipalityparker@gmail.com":
				return "mmvpwktzyhfmarvs";
	  			//Web Password "FireS@le";
			case "ffTicketServiceTestingparker@gmail.com":
				return "jmfhvuiqqzkfdamz";
	  			//Web Password "FireS@le";
	  		default:
	  			UpdateErrorMessageWithPivotalData(objDictionary,"The UserName ("+strUserName+") has not been added-"+strMethondName);
	  			break;
		}
		return "";
	}
	public String Gmail_GetPEOGmailPassword(Map<String, String> objDictionary)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strRole = "peo";
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
		switch (strUserName)
		{
			case "paMinnetonkapeo@gmail.com":
				return "synczlzcurfnixqf ";
			case "aaMinnetonkapeo@gmail.com":
				return "ubrcqzlcrbtiifai ";
			case "aaMPSpeo@gmail.com":
				return "aaMPSMNpeo1";
			case "aaExcelsiorMNpeo@gmail.com":
	  			return "aaExcelsiorMNpeo1";
	  		case "bbExcelsiorMNpeo@gmail.com":
	  			return "bbExcelsiorMNpeo1";
	  		case "eeExcelsiorMNpeo@gmail.com": //First Name: eeExcelsiorMN Last Name: peo
	  			return "eeExcelsiorMNpeo1";
	  		case "ffExcelsiorMNpeo@gmail.com":
	  			return "ffExcelsiorMNpeo1";
	  		case "ffAutomationMunicipalitypeo@gmail.com":
	  			return "svsplidghkmbbysr";
	  			//Web Password "FireS@le";
	  		case "ffTicketServiceTestingpeo@gmail.com":
	  		case "ffticketservicetestingpeo@gmail.com":
	  			return "jvqyisxthxgnqbnk";
	  			//Web Password "FireS@le";
	  		case "fgAutomationMunicipalitypeo@gmail.com":
	  			return "G6AGA78d22jTCYJ";
	  		case "rbExcelsiorMNpeo@gmail.com":
	  			return "ovxzkytwswrtwdcn";
	  		//	return "rbExcelsiorMNpeo2!";
	  			//Need to setup the App Password in the google account
	  		default:
	  			UpdateErrorMessageWithPivotalData(objDictionary,"The UserName ("+strUserName+") has not been added-"+strMethondName);
	  			break;
		}
		return "";
	}
	public String Gmail_GetResetToken(String strEmail,String strPassword) throws IOException
    {
		// IMAP server properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        try {
            // Create a JavaMail session
            Session session = Session.getInstance(properties);
            // Connect to the IMAP server
            Store store = session.getStore();
            store.connect("imap.gmail.com", strEmail, strPassword);
            // Open the desired folder (e.g., INBOX)
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            // Get the number of messages in the folder
            int messageCount = folder.getMessageCount();
            // Print the message count
            System.out.println("Number of messages in the folder: " + messageCount);
            String targetSubject = "SentryMobile Reset";
            // Search for emails with a specific subject
            Message[] messages = folder.getMessages();
            // Process the matching emails
            for (Message message : messages) {
                // Get the subject of the email
                String subject = message.getSubject();
                // Print the subject of the email
                System.out.println("Email Subject: " + subject);
                if(subject.contains(targetSubject))
                {
                	  // Get the content of the email
                    Object content = message.getContent();
                    // Check if the content is text/plain
                    if (content instanceof String) {
                        String body = (String) content;
                        System.out.println("Email Body:\n" + body);
                        String strToken = body.substring(body.indexOf("<p>This is your Sentry Mobile Password Reset Token:</p>") +55,body.indexOf("<p>If you didn't request this, please ignore this email.</p>"));
    		            return strToken.replace("<p>","").replace("</p>","");
    		        }
                }
            }
            // Close the folder and the connection
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	    return "";
    }
	public void Gmail_DeleteAllEmails(String strEmail,String strPassword)
    {
		 // IMAP server properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        try {
            // Create a JavaMail session
            Session session = Session.getInstance(properties);
            // Connect to the IMAP server
            Store store = session.getStore();
            store.connect("imap.gmail.com", strEmail, strPassword);
            // Open the desired folder (e.g., INBOX)
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            // Get all messages in the folder
            Message[] messages = folder.getMessages();
            // Delete each message
            for (Message message : messages) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
            // Expunge deleted messages to permanently remove them
            folder.expunge();
            // Close the folder and the connection
            folder.close(false);
            store.close();
            System.out.println("All emails deleted successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	public void Gmail_ValidateViolationAlertEmailContains(Map<String, String> objDictionary, String strLicensePlateNumber, String strEmail, String strPassword, String strSubject, String strExpectedMessage) throws IOException
    {
		String strEmailExist = "False";
		// IMAP server properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        try {
            // Create a JavaMail session
            Session session = Session.getInstance(properties);
            // Connect to the IMAP server
            Store store = session.getStore();
            store.connect("imap.gmail.com", strEmail, strPassword);
            // Open the desired folder (e.g., INBOX)
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            // Get the number of messages in the folder
            int messageCount = folder.getMessageCount();
            // Print the message count
            System.out.println("Number of messages in the folder: " + messageCount);
            // Search for emails with a specific subject
            Message[] messages = folder.getMessages();
            // Process the matching emails
            for (Message message : messages) {
                // Get the subject of the email
                String subject = message.getSubject();
                // Print the subject of the email
                System.out.println("Email Subject: " + subject);
                if(subject.contains(strSubject))
                {
                	strEmailExist = "True";
                	// Get the content of the email
                    Object content = message.getContent();
                    // Check if the content is text/plain
                    if (content instanceof String) {
                        String body = (String) content;
                        body = content.toString().replace(" -", "-").replace("0600", "06:00").replace("0500", "05:00").trim();
    		            System.out.println(body.trim());
    		           	System.out.println(strExpectedMessage.replace(" -", "-").replace("0600", "06:00").replace("0500", "05:00").trim());
                		//This seems to change with Daylight savings
    		           	if(body.trim().equals(strExpectedMessage.replace(" -", "-").replace("0600", "06:00").replace("0500", "05:00").trim()))
                		{
                			Reporter.log("The violation alert message contained: ("+strExpectedMessage+")");
                			return;
                		}
    		           	else
    		           	{
    		           		UpdateErrorMessageWithPivotalData(objDictionary,"The violation alert message did not contain ("+strExpectedMessage.replace(" -", "-").replace("0600", "06:00").replace("0500", "05:00").trim()+")-actual ("+body.trim()+")");
    		           		return;
    		           	}
                    }
                }
            }
            // Close the folder and the connection
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(strEmailExist.equals("False"))
    	{UpdateErrorMessageWithPivotalData(objDictionary,"Email Alert did not exist: "+strSubject);}
    }
	public void Gmail_ValidateViolationPaymentReceiptEmail(Map<String, String> objDictionary,String strEmail, String strPassword, String strViolationNumber)throws Exception
 	{
  		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strGmailUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+"peo@gmail.com";
		HttpConnections clsHttpConnections = new HttpConnections();
     	clsHttpConnections.HTTPCONNECTIONS_StorePaymentReceiptValues(objDictionary, "Local", "1");
		String strEmailExist = "False";
		String strSubject = "Parking Violation Receipt";
		// IMAP server properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        try {
            // Create a JavaMail session
            Session session = Session.getInstance(properties);
            // Connect to the IMAP server
            Store store = session.getStore();
            store.connect("imap.gmail.com", strEmail, strPassword);
            // Open the desired folder (e.g., INBOX)
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            // Get the number of messages in the folder
            int messageCount = folder.getMessageCount();
            // Print the message count
            System.out.println("Number of messages in the folder: " + messageCount);
            // Search for emails with a specific subject
            Message[] messages = folder.getMessages();
            // Process the matching emails
            for (Message message : messages) {
                // Get the subject of the email
                String subject = message.getSubject();
                // Print the subject of the email
                System.out.println("Email Subject: " + subject);
                if(subject.contains(strSubject))
                {
                	strEmailExist = "True";
                	// Get the content of the email
                    Object content = message.getContent();
                    String strInitialViolationTimestamp = objDictionary.get("strInitialViolationTimestamp");
                 	String strPaymentTimestamp = objDictionary.get("strPaymentTimestamp");
                 	String strTransactionNumber = objDictionary.get("strTransactionNumber");
                 	String strViolationAmount = objDictionary.get("strViolationAmount");
                 	//Convert Violation Amount
                 	double dblViolationAmount = Double.parseDouble(strViolationAmount) * .01;
                 	DecimalFormat format = new DecimalFormat("0.00");
                 	String strFormattedViolationAmount = format.format(dblViolationAmount);
                 	CommonWeb clsCommonWeb = new CommonWeb();
                 	//Extra Space
                 	String strFormatedInitialViolationTimestamp = clsCommonWeb.ConvertUTCTimeToLocalTimeWithTimeZoneAbbr(strInitialViolationTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","MM/dd/yyyy h:mm:ss a","America/Chicago");
                 	String strFormatedstrPaymentTimestamp = clsCommonWeb.ConvertUTCTimeToLocalTimeWithTimeZoneAbbr(strPaymentTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","MM/dd/yyyy h:mm:ss a","America/Chicago");//
                 	String strFormatedFeeTimestamp = strFormatedstrPaymentTimestamp.split(" ")[0];
                 	//Increment strFormatedFeeTimestamp to next day if current time > 7 pm
                 	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                 	LocalDate date = LocalDate.parse(strFormatedFeeTimestamp, formatter);
                 	// Check if current time is after 7:00 PM (19:00)
                 	if (LocalTime.now().isAfter(LocalTime.of(19, 0))) {
                 	    date = date.plusDays(1);
                 	}
                 	strFormatedFeeTimestamp = date.format(formatter);
                 	String strDeviceId = objDictionary.get("strDeviceId");
                    String strExpectedMessage = strGmailUserName+",\n" +
		            		"\n" +
		            		"Your violation has been paid successfully.\n" +
		            		"\n" +
		            		"Violation details:\n" +
		            		"\n" +
		            		"Violation Number: "+strViolationNumber+"\n" +
		            		"\n" +
		            		"Time issued: "+strFormatedInitialViolationTimestamp+"\n" +
		            		"\n" +
		            		"Location: "+strDeviceId+"\n" +
		            		"\n" +
		            		"\n" +
		            		"Violation payment history:\n" +
		            		"\n" +
		            		"\n" +
		            		"\n" +
		            		"Time: "+strFormatedstrPaymentTimestamp+"\n" +
		            		"\n" +
		            		"Method: Credit Card\n" +
		            		"\n" +
		            		"Source: Online\n" +
		            		"\n" +
		            		"Reference Number: "+strTransactionNumber+"\n" +
		            		" Violation Fee: USD 30.00"+
		            		" Violation Processing Fee: "+strFormatedFeeTimestamp+": USD 7.00" +
		            		"Sales Tax: USD 0.00" +
							"\n" +
		            		"Amount: "+strFormattedViolationAmount+"\n" +
		            		"";

                    // Check if the content is text/plain
                    if (content instanceof String) {
                        String body = (String) content;
                        System.out.println(body.replace("\n","").replace("\r","").replaceAll("\\s+", " ").trim());
                        System.out.println(strExpectedMessage.replace("\n","").replace("\r","").replaceAll("\\s+", " ").trim());
                        if(body.replace("\n","").replace("\r","").replaceAll("\\s+", " ").trim().contains(strExpectedMessage.replace("\n","").replace("\r","").replaceAll("\\s+", " ").trim()))
                    	{Reporter.log("The Violation Payment Receipt Email was correct: ("+strExpectedMessage.replace("\n","").replaceAll("\\s+", " ").replace("\r","").trim()+")");}
        		        else
        		        {UpdateErrorMessageWithPivotalData(objDictionary,"The violation Payment Receipt did not contain  ("+strExpectedMessage.replace("\n","").replace("\r","").replaceAll("\\s+", " ").trim()+")-actual ("+body.replace("\n","").replace("\r","").replaceAll("\\s+", " ").trim()+")");}
                    }
                }
            }
            // Close the folder and the connection
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(strEmailExist.equals("False"))
    	{UpdateErrorMessageWithPivotalData(objDictionary,"Email Alert did not exist: "+strSubject);}
    }
	public static String formatDateTime(String inputDateTime, String outputFormat)
	{
        SimpleDateFormat inputSdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a", Locale.US);
        SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormat, Locale.US);

        try {
            Date date = inputSdf.parse(inputDateTime);
            return outputSdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
	public void Gmail_ValidateDisputedViolationEmailParker(Map<String, String> objDictionary,String strEmail, String strPassword, String strViolationNumber)throws Exception
 	{
		HttpConnections clsHttpConnections = new HttpConnections();
     	clsHttpConnections.HTTPCONNECTIONS_StorePaymentReceiptValues(objDictionary, "Local", "1");
		String strEmailExist = "False";
		String strMunicipality = objDictionary.get("strMunicipality");
		String strSubject = strMunicipality+" Violation #"+strViolationNumber+" Disputed";
		// IMAP server properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        try {
            // Create a JavaMail session
            Session session = Session.getInstance(properties);
            // Connect to the IMAP server
            Store store = session.getStore();
            store.connect("imap.gmail.com", strEmail, strPassword);
            // Open the desired folder (e.g., INBOX)
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            // Get the number of messages in the folder
            int messageCount = folder.getMessageCount();
            // Print the message count
            System.out.println("Number of messages in the folder: " + messageCount);
            // Search for emails with a specific subject
            Message[] messages = folder.getMessages();
            // Process the matching emails
            for (Message message : messages) {
                // Get the subject of the email
                String subject = message.getSubject();
                // Print the subject of the email
                System.out.println("Email Subject: " + subject);
                if(subject.contains(strSubject))
                {
                	strEmailExist = "True";
                	// Get the content of the email
                    Object content = message.getContent();
                 	//Convert Violation Amount
                    String strExpectedMessage = "Your dispute request for violation #"+strViolationNumber+" has been received.\n" +
 		            		"\n" +
 		            		"The violation is currently being reviewed.\n" +
 		            		"\n" +
 		            		"You may review the current status or provide additional information by looking up your violation at SentryPay.";
                    String body = (String) content;
                    body = content.toString().replaceAll("\\<.*?\\>","");
                    System.out.println(body.replace("\n","").replace("\r","").trim());
                    System.out.println(strExpectedMessage.replace("\n","").replace("\r","").trim());
                    if(body.replace("\n","").replace("\r","").trim().contains(strExpectedMessage.replace("\n","").replace("\r","").trim()))
                	{Reporter.log("The Violation Payment Receipt Email was correct: ("+strExpectedMessage+")");}
                    else
    		        {UpdateErrorMessageWithPivotalData(objDictionary,"The violation Payment Receipt did not equal  ("+strExpectedMessage.replace("\n","").replace("\r","").trim()+")-actual ("+body.replace("\n","").replace("\r","").trim()+")");}
                }
            }
            // Close the folder and the connection
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(strEmailExist.equals("False"))
    	{UpdateErrorMessageWithPivotalData(objDictionary,"Email Alert did not exist: "+strSubject);}
    }
	public void Gmail_ValidateDisputedViolationEmail(Map<String, String> objDictionary,String strEmail, String strPassword, String strViolationNumber)throws Exception
 	{
		HttpConnections clsHttpConnections = new HttpConnections();
     	clsHttpConnections.HTTPCONNECTIONS_StorePaymentReceiptValues(objDictionary, "Local", "1");
		String strEmailExist = "False";
		String strMunicipality = objDictionary.get("strMunicipality");
		String strSubject = strMunicipality+" Ticket #"+strViolationNumber+" Disputed";
		// IMAP server properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        try {
            // Create a JavaMail session
            Session session = Session.getInstance(properties);
            // Connect to the IMAP server
            Store store = session.getStore();
            store.connect("imap.gmail.com", strEmail, strPassword);
            // Open the desired folder (e.g., INBOX)
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            // Get the number of messages in the folder
            int messageCount = folder.getMessageCount();
            // Print the message count
            System.out.println("Number of messages in the folder: " + messageCount);
            // Search for emails with a specific subject
            Message[] messages = folder.getMessages();
            // Process the matching emails
            for (Message message : messages) {
                // Get the subject of the email
                String subject = message.getSubject();
                // Print the subject of the email
                System.out.println("Email Subject: " + subject);
                if(subject.contains(strSubject))
                {
                	strEmailExist = "True";
                	// Get the content of the email
                    Object content = message.getContent();
                 	//Convert Violation Amount
                    String strExpectedMessage = "Your dispute request for ticket #"+strViolationNumber+" has been received.\n" +
 		            		"\n" +
 		            		"The ticket is currently being reviewed.\n" +
 		            		"\n" +
 		            		"You may review the current status or provide additional information by looking up your violation at SentryPay.";
                    String body = (String) content;
                    body = content.toString().replaceAll("\\<.*?\\>","");
                    System.out.println(body.replace("\n","").replace("\r","").trim());
                    System.out.println(strExpectedMessage.replace("\n","").replace("\r","").trim());
                    if(body.replace("\n","").replace("\r","").trim().contains(strExpectedMessage.replace("\n","").replace("\r","").trim()))
                	{Reporter.log("The Violation Payment Receipt Email was correct: ("+strExpectedMessage+")");}
                    else
    		        {UpdateErrorMessageWithPivotalData(objDictionary,"The violation Payment Receipt did not equal  ("+strExpectedMessage.replace("\n","").replace("\r","").trim()+")-actual ("+body.replace("\n","").replace("\r","").trim()+")");}
                }
            }
            // Close the folder and the connection
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if(strEmailExist.equals("False"))
    	{UpdateErrorMessageWithPivotalData(objDictionary,"Email Alert did not exist: "+strSubject);}
    }
}
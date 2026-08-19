package AutomationCode;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class SentryHealth
{
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver(){return threadDriver.get();}

	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,WebDriver driver, String strErrorMsg)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strHost = objDictionary.get("strHost");
		String strExecutedGlobalPayAbbr = objDictionary.get("strExecutedGlobalPayAbbr");
		//Copy Screen Shot Locally
		if(!strErrorMsg.contains("lost connection"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
			try {Thread.sleep(3000);}catch (Exception e) {}
			clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestCase);
		}
		String strMeterLogExist = objDictionary.get("strMeterLogExist");
		if(strMeterLogExist == null){strErrorMsg = clsMeter.METER_CheckForErrorsInMeterLogs(objDictionary, strErrorMsg);}
		//Check For StackTrace Error
		//clsMeter.METER_CheckIfStackTraceExistsInMeterLogs(objDictionary, strErrorMsg);
		//Check For Traceback
		//clsMeter.METER_CheckIfTracebackExistsInMeterLogs(objDictionary, strErrorMsg);
		//Create a request to the Pivotal API to get bug status and assigned to
		String strPivotalId = "";
		String strRemainParkedShortSession = "True";
		//****************TEST CDOE********************************************
		//Create a request to the Pivotal API to get bug status and assigned to
   		switch (strErrorMsg)//SCREEN_MULTI_SELECT_SPACE
		{
   			//Place Holder
   			case "The Initial Grace Period Violated unexpectedly created multiple violations":
   				Reporter.log(strErrorMsg);
				strErrorMsg = "Duplicate Additional Time Expired Violation";
				strPivotalId = "172745182";
				break;
   			default:
   				//Place Holder
    			String strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)";
	    		Pattern CompilePattern = Pattern.compile(strPattern);
	    		Matcher MatchPattern = CompilePattern.matcher(strErrorMsg);
	    		if(MatchPattern.find( ))
	    		{
	    			Reporter.log(strErrorMsg);
    				strErrorMsg = "The meter Remaining Time is calculated incorrectly when making a credit card payment in Free To Rate.";
    				strPivotalId = "172317409";
    				break;
	    		}
	    }
		if(!strPivotalId.equals(""))
  		{
  			if(strAssociatedBug.contains(strPivotalId)||strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
  			{
  				//Yellow Means Know Issue
  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
  				//RPSS: Remain Parked Short Session
  				//if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  			}
  			else
  			{
  				//Red Means New Issue
  				Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
  				//RPSS: Remain Parked Short Session
  			 	//if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  			}
  		}
  		else
  		{
  			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");
  			if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
        	//RPSS: Remain Parked Short Session
  	      	//clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked (15) seconds-Spot 1");
  	      	Assert.fail(strErrorMsg);
  		}
	}

	public void SetHealthKioskVariables(Map<String, String> objDictionary)
	{
		String strHost = objDictionary.get("strHost");
		SentryHealth  clsSentryHealth  = new SentryHealth ();
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_CARD_READ_TIMEOUT 3");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_WIRE_DOOR_OPEN");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_PROXIMITY_NOT_DEPARTED_TO 300");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_QUESTION_TITLE");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CALL_ON_MASK 300");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_STOP_ON_QUESTIONS");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_PROXIMITY_DEPART 20");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_EVENT_ON_START");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_SHOW_QR_CODE");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_PROCEED_DELAY 5");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_STOP_ON_LOW_TEMP");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_DISPENSE_FORWARD");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_WIRE_DOOR_ACCESS");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_MANUAL_ID");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CALL_ON_CARD");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_FEVER_QUESTION");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_NO_STATUS_LED");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_SANITIZE_WAIT 10");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_PICTURES_ON_OOB_TEMP_ONLY");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_SWIPE_EMPLOYEE_BADGE");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_NO_SANITIZER");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_USE_GESTURES");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_MPSPUMPME");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_EMPLOYEE_TIMEOUT 10");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_ASK_SMELL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_ADA_ENABLED");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_DISPENSE_MAX 3000");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_STOP_ON_TEMP");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_PRINTER_IP_ADDR");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_float.py HEALTH_TEMP_THRESHOLD 99.5");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_CUSTOMER_LABEL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_READ_CARD");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_FACEME_EXTRACTION_MODEL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_STOP_ON_CARD");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_SMARTLINK");
		//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_QRCODE_URL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_float.py HEALTH_TEMP_OFFSET 0.5");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_FORCE_DEPART");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CARD_IGNORE_DEPART");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_QUESTION_TIMEOUT 10");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_float.py HEALTH_TEMP_THRESHOLD_LOW 95.0");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_ASK_FEVER");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_USE_MONITOR");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_STOP_ON_SMELL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_RETRY_LIMIT 1");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_WRISTBAND");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_PROXIMITY_THRESHOLD 20");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_CARD_TIMEOUT 45");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_ENABLE_TURNSTILE");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_BANNER_PROX");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_BOCA_PRINTER");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CALL_ON_TEMP");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_float.py HEALTH_DISPENSE_AMOUNT 0.5");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_FACEME_DETECTION_MODEL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_ASK_QUESTIONS");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_FACEME_REGISTER_QUALITY");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_STOP_ON_MASK");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_TEMP_FOREARM");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_FACIAL_LEFT_RIGHT_ATTEMPTS_COUNT 2");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HHEALTH_STOP_ON_SANITIZE");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_QUESTIONS");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_PROXIMITY_THREAD");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_TEMP_TIMEOUT 7");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_DISPENSE_THRESHOLD 500");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_FACEME_DATABASE_PASSWORD");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CALL_ON_SANITIZE");
		//clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_FACEME_FALSE_ACCEPTANCE_RATE");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_FACEME_LICENSE_KEY");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_ASK_VISITOR");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_SHOW_NO_TEMP");
		//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_text.py HEALTH_CUSTOMER_LOGO");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CALL_ON_SMELL");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_float.py HEALTH_SANITIZE_TO_CARD_DELAY 0.2");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_int.py HEALTH_SKIP_DEPART_COUNT 3");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_EVENT_ON_END");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_CALL_ON_QUESTIONS");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py HEALTH_OOS_WHEN_NETWORK_OFFLINE");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_REQUIRE_MASK");
		clsSentryHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py HEALTH_MAKE_SOFT_CALL");
	}

	public void SentryHealth_AddReportVariables(Map<String, String> objDictionary, WebDriver driver, String strTestCaseName)
	{
		//Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		try{clsHttpConnections.JsonStoreSentryVersion(objDictionary);}catch (Exception e) {}
  		//Add Test Case Name to IReporter Listner
  		String strTestSuiteName = objDictionary.get("strTestSuiteName");
  		strTestCaseName = objDictionary.get("strTestCaseName");
		ITestResult ITResult = Reporter.getCurrentTestResult();
		ITResult.setAttribute("strTestCaseName", strTestCaseName);
		ITResult.setAttribute("strTestSuiteName", strTestSuiteName);
		objDictionary.put("strTestCaseName", strTestCaseName);
		String strSubdomainError = objDictionary.get("strSubdomainError");
		if(strSubdomainError != null) {UpdateErrorMessageWithPivotalData(objDictionary, driver, strSubdomainError);}
		//Delete Screen Shot
		//try{clsCommonWeb.DeletePreviousScreenShot(ITResult);}catch (Exception e) {}
		//clsMeter.METER_DeleteMeterScreenShotLocally(objDictionary, strTestCaseName);
  		String strEnvironment = objDictionary.get("strEnvironment");
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strPEOAPK = objDictionary.get("strPEOAPK");
		String strRemotePath = objDictionary.get("strRemotePath");


		Meter clsMeter = new Meter();
		String strLocalMeterVersion = clsMeter.GetMeterVersion(objDictionary,driver,"Local");
		String strMeterStartTime = objDictionary.get("strMeterStartTime");
		String strMD5SUMVersion  = clsMeter.GetMeterMD5SUMVersion(objDictionary);

		String strSilverBulletVersion = "";
		//String strSilverBulletVersion = clsMeter.SENTRYMETER_GetSilverBulletVersion(objDictionary);


		String strSentryLinkVersion = objDictionary.get("strSentryLinkVersion");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strRemoteDeviceId = objDictionary.get("strRemoteDeviceId");
		String strRemoteUser = objDictionary.get("strRemoteUser");
		objDictionary.remove("strMeterVersion");objDictionary.put("strMeterVersion", strLocalMeterVersion);
		String strSOMLocalImageId  = clsMeter.SENTRYMETER_GetSOMImageId(objDictionary,driver,"Local");
		String strSOMLocalDistroVersion = clsMeter.SENTRYMETER_GetSOMDistroVersion(objDictionary,driver,"Local");
		String strSOMLocalDistroName = clsMeter.SENTRYMETER_GetSOMDistroName(objDictionary,driver,"Local");
		Reporter.log("************************************************************");
		Reporter.log("Test:  "+strTestSuiteName+"_"+strTestCaseName+"             ");
		Reporter.log("Environment:  "+strEnvironment+"                            ");
		Reporter.log("Municipality: "+strMunicipality+"                           ");
		Reporter.log("Meter Name:   "+strMeterName+"                              ");
		Reporter.log("Meter Type;	"+strMeterUser+"                              ");
		Reporter.log("Meter IP:     "+strHost+"                                   ");
		Reporter.log("Local Meter Version:"+strLocalMeterVersion+"                ");
		if(strRemoteUser != null)
		{
			String strRemoteMeterVersion = clsMeter.GetMeterVersion(objDictionary,driver,"Remote");
			Reporter.log("Remotel Meter Version:"+strRemoteMeterVersion+"             ");
		}
		//Reporter.log("Meter MD5SUM Version:"+strMD5SUMVersion+"                   ");
		Reporter.log("Remote Device:"+strRemoteDeviceId+"                         ");
		Reporter.log("Remote Type:"+strRemoteUser+"                               ");
		Reporter.log("Sentry Link Version:"+strSentryLinkVersion+"                ");
		Reporter.log("SilverBullet Version:"+strSilverBulletVersion+"             ");
		Reporter.log("RemotePath:   "+strRemotePath+"                             ");
		Reporter.log("SOM Local Image Id:   "+strSOMLocalImageId+"                ");
		Reporter.log("SOM Local Distro Version:   "+strSOMLocalDistroVersion+"    ");
		Reporter.log("SOM Distro Name:   "+strSOMLocalDistroName+"                ");
		if(strRemoteUser != null)
		{
			String strSOMRemoteImageId  = clsMeter.SENTRYMETER_GetSOMImageId(objDictionary,driver,"Remote");
			String strSOMRemoteDistroVersion = clsMeter.SENTRYMETER_GetSOMDistroVersion(objDictionary,driver,"Remote");
			String strSOMRemoteDistroName = clsMeter.SENTRYMETER_GetSOMDistroName(objDictionary,driver,"Remote");
			Reporter.log("SOM Remote Image Id:   "+strSOMRemoteImageId+"                ");
			Reporter.log("SOM Remote Distro Version:   "+strSOMRemoteDistroVersion+"    ");
			Reporter.log("SOM Remote Distro Name:   "+strSOMRemoteDistroName+"          ");
		}
		Reporter.log("************************************************************");
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
        Reporter.log("<font color='green'>Meter Start Time "+strMeterStartTime+"</font>");
		Reporter.log("<font color='green'>Test Start Time "+sdf.format(cal.getTime())+"</font>");
		clsMeter.METER_DeleteMeterScreenShotLocally(objDictionary, strTestCaseName);
	}


	public void SENTRYHEALTH_ExecutePythonScriptAgainstMeter(Map<String, String> objDictionary,String strHost,String strPythonCommand)
	{
		//String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strPassword = "firesale";
		String strMeterUser = "seco";
		int port=22;
		try
		{
			Session session = jsch.getSession(strMeterUser, strHost, port);
			session.setPassword(strPassword);
		    session.setConfig("StrictHostKeyChecking", "no");
		    session.connect();
		    Channel channel=session.openChannel("exec");
		    SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		    ((ChannelExec)channel).setCommand("/usr/local/bin/"+strPythonCommand);
		    Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
    		channel.setInputStream(null);
		    channel.connect();
		    channel.disconnect();session.disconnect();
		}
		catch(Exception e)
		{
			System.out.println("MIH");
		}
	}
	public String SENTRYHEALTH_GetScreenName(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strMeterUser = "seco";
		String strPassword = objDictionary.get("strUniquePassword");
		String strHost = objDictionary.get("strHost");
		String strPythonScriptExisted = "False";
		String strScreenName = "";
		int port=22;
		try
		{
			Session session = jsch.getSession(strMeterUser, strHost, port);
			session.setPassword(strPassword);
			session.setConfig("StrictHostKeyChecking", "no");
		    session.connect();
		    Channel channel=session.openChannel("exec");
		    ((ChannelExec)channel).setCommand("testauto_dump_health_sessions.py");
		    channel.setInputStream(null);
		    ((ChannelExec)channel).setErrStream(System.err);
		    InputStream in=channel.getInputStream();
		    channel.connect();
		    //Get Begun Value
		    String s = "";int c;
		    while((c = in.read()) != -1) {s += (char)c;}
		    String[] lines = s.split("\n");
		    System.out.println(Arrays.toString(lines));
		    strPythonScriptExisted = "True";
		    for (String line : lines)
		    {
		    	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		    	dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		    	strScreenName = line.substring(line.indexOf("Scrn,")+5, line.length());
		    	objDictionary.put("strScreenName", strScreenName);
		    	Reporter.log("The strScreenName value equaled ("+strScreenName+")-UTC Time:"+dateFormatGmt.format(new Date()));
		    }
		    in.close();
		    channel.disconnect();
		    session.disconnect();
		}
		catch(Exception e)
		{System.out.println(e);}
		return strScreenName;
	  }


	public void SENTRYHEALTH_ValidateHealthSession_RU_VT_SA_NS_MOFF_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "Natalie Duphorn");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "96.2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-half fa-2x text-success");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-bullhorn fa-2x text-danger");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}
	public void SENTRYHEALTH_ValidateHealthSession_RU_VT_SA_NS_MON_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "Natalie Duphorn");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "96.2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-half fa-2x text-success");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}
	public void SENTRYHEALTH_ValidateHealthSession_RU_IT_SS_SP_MOFF_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "Jack Duphorn");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "100.1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-full fa-2x text-danger");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-thumbs-down fa-2x text-danger");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-frown-o fa-2x text-danger");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-bullhorn fa-2x text-danger");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}
	public void SENTRYHEALTH_ValidateHealthSession_RU_VT_SS_SP_MOFF_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "Jack Duphorn");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "97.1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-half fa-2x text-success");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-thumbs-down fa-2x text-danger");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-frown-o fa-2x text-danger");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-bullhorn fa-2x text-danger");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}
	public void SENTRYHEALTH_ValidateHealthSession_RU_VT_SS_SP_MON_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "Darin Duphorn");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "97.1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-half fa-2x text-success");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-thumbs-down fa-2x text-danger");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-frown-o fa-2x text-danger");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}

	public void SENTRYHEALTH_ValidateHealthSession_UU_VT_SA_NS_MON_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "96.2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-half fa-2x text-success");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}
	public void SENTRYHEALTH_ValidateHealthSession_UU_VT_SA_NS_MOFF_NC_VHS(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Navigate to Configure Meter
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Health Sessions","Local");
		String strRowNumber = clsCommonWeb.StoreTableRowNumberBaseOnColumnValue(objDictionary, driver, "Health Sessions", "Health Sessions", 1, 11, "SH79", "strRowNumber");
  		//Validate Health Sessions
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"2", "CellValue", "");
  		//Temperature
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellValue", "96.2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"3", "CellImageClassName", "fa fa-thermometer-half fa-2x text-success");
  		//Sanitizer
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"4", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Symptoms
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"5", "CellImageClassName", "fa fa-check-circle fa-2x text-success");
  		//Mask
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"6", "CellImageClassName", "fa fa-bullhorn fa-2x text-danger");
	  	//Call
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Health Sessions", "Health Sessions", 1, strRowNumber,"7", "CellValue", "No Call");
  		driver.quit();
  	}
}

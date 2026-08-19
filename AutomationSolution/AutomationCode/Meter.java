package AutomationCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Optional;

import com.google.common.base.Stopwatch;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.DecimalFormat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.Instant;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Meter
{
	public String strActualWaitType = "";
	public String strActualWaitValue = "";
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver(){return threadDriver.get();}
	//**************************************************************************************************************************************************************************************
	//Update Error Message With Pivotal Data
	//**************************************************************************************************************************************************************************************
	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strErrorMsg,String strExceptionError)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Dictionary Variables
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
		String strHost = objDictionary.get("strHost");
		String strExecutedGlobalPayAbbr = objDictionary.get("strExecutedGlobalPayAbbr");
		Reporter.log("<font color='Pink'>     "+strErrorMsg+"</font>");
		//Copy Screen Shot Locally
		if(strExceptionError.equals("False"))
		{
			if(sessionMeter!= null)
			{
				Reporter.log("String Initial Error Message: "+ strErrorMsg);
				System.out.println("String initial Error Message: "+ strErrorMsg);
				objDictionary.put("strOnScreenShot", "True");
				clsMeter.METER_CopyMeterScreenShotLocally(objDictionary,sessionMeter,strTestCase);
				objDictionary.put("strOnScreenShot", "False");
			}
			//Copy Meter Logs Locally
			strErrorMsg = clsMeter.METER_CheckForErrorsInMeterLogs(objDictionary, strErrorMsg);
		}
		//Copy Meter Logs Locally
		//clsMeter.METER_CopyMeterLogsLocally(objDictionary,strErrorMsg);
		//Check For CallStack Error
		//clsMeter.METER_CheckIfCallStackExistsInMeterLogs(objDictionary, strErrorMsg);
		//Check For StackTrace Error
		//clsMeter.METER_CheckIfStackTraceExistsInMeterL ogs(objDictionary, strErrorMsg);
		//Check For Traceback
		//clsMeter.METER_CheckIfTracebackExistsInMeterLogs(objDictionary, strErrorMsg);
		//Create a request to the Pivotal API to get bug status and assigned to
		String strPivotalId = "";
		String strRemainParkedShortSession = "True";
		String strSentryLinkMaintenanceMode = "";
		//****************TEST CDOE********************************************
		//Create a request to the Pivotal API to get bug status and assigned to
 		switch (strErrorMsg)//SCREEN_MULTI_SELECT_SPACE
		{
 			case "The meter max time remaining (179.8) did not equal the expected time remaining (180.0)-METER_InsertCardPayNoSpot":
 			case "The (Local) Meter Max Remaining Time (1) did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
 				Reporter.log(strErrorMsg);
				strErrorMsg = "Max time remaining is calculated incorrectly when making a max time payment Free To Rate.  Expected maxtime 0 actual 1 minute.";
				strPivotalId = "184057945";
				break;
 			case "The (Remote1) Meter Max Remaining Time  (160) did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
 			case "The (Remote1) Meter Max Remaining Time (160) did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
 				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter validate time purchase is incorrect when make a True Up payment for max time on a remote space.";
				strPivotalId = "175171409";
				break;
			case "None of the expected objects (pause_coins) existed after (10) seconds":
			case "None of the expected objects (pause_coins) existed after (15) seconds":
			case "None of the expected objects ({WaitUntilMeterCoinAcceptorEqualsTrue} NA) existed after (15) seconds-RMQ Cert may have expired":
				//May need to use strGlobalPayActionsSpot1  & strGlobalPayActionsSpot1
				Reporter.log(strErrorMsg);
				strErrorMsg = "After a remote space goes from pause_coins to resume_coins on a remote space, it takes over 10 seconds for the Coin acceptor Value to equal True.";
				strPivotalId = "172299604";
				break;
			case "None of the expected objects ({WaitUntilMeterMaxRemainingEqualsTheAmount} 180) existed after (30) seconds-RMQ Cert may have expired":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The Max Time remaining is calculated incorrectly when make batch coin payments.";
				strPivotalId = "172297235";
				break;
  			case "The expected meter screen (SCREEN_MULTI_IDLE) did not appear current screen equaled (SCREEN_MULTI_HOME)-METER_WaitForMeterScreen":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When the button is press and payments are processing,  What should the behavior of the Start Button be?";
				strPivotalId = "169483717";
				break;
  			case "The (Local) Meter time did not increment correctly-METER_InsertCoin":
  				Reporter.log(strErrorMsg);
				strSentryLinkMaintenanceMode = clsHttpConnections.HTTPCONNECTIONS_IsMeterSpotMainteanceMode(objDictionary, "Local", "1");
  				if(strSentryLinkMaintenanceMode.equals("true"))
  				{
  					strErrorMsg = "If a active session exists on a global spot, and that spot goes into maintenance \"No Parking\" the user is no longer able to add payments to that space.";
  					strPivotalId = "169718110";
  					strAssociatedBug = "169718110";
  				}
  				else
  				{
  					strErrorMsg = "When user enters multiple coins on a remote spot, the meter screen remains on the home screen, and a Hard Reboot is required to clear the screen.";
  					strPivotalId = "169354167";
  				}
				break;
			case "The (Remote1) Meter time did not increment correctly-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strSentryLinkMaintenanceMode = clsHttpConnections.HTTPCONNECTIONS_IsMeterSpotMainteanceMode(objDictionary, "Remote1", "1");
  				if(strSentryLinkMaintenanceMode.equals("true"))
  				{
  					strErrorMsg = "If a active session exists on a global spot, and that spot goes into maintenance \"No Parking\" the user is no longer able to add payments to that space.";
  					strPivotalId = "169718110";
  					strAssociatedBug = "169718110";
  				}
  				else
  				{
  					strErrorMsg = "When user enters multiple coins on a remote spot, the meter screen remains on the home screen, and a Hard Reboot is required to clear the screen.";
  					strPivotalId = "169354167";
  				}
				break;
			case "The expected meter screen (SCREEN_MULTI_HOME) did not appear current screen equaled (SCREEN_MULTI_IDLE)-METER_WaitForMeterScreen":
				Reporter.log(strErrorMsg);
				if(strTestCase.contains("RTF_LM_POELS_WUIGPTELS_S5_VVSTLS_WUMFETLS_ESNLS_VCAE"))
				{
					strErrorMsg = "The user is unable to add true up payments after the meter rolls into Free parking.";
					strPivotalId = "185396048";
				}
				else
				{
					strErrorMsg = "If the user enters coins for a remote space, then clicks the start button, then enters the space number again, the \"Please pay at meter\" error unexpectedly appears, and the logs contain the Error:  No active spot";
					strPivotalId = "169320965";
				}
				break;
  			case "The meter max time remaining (165.0) did not equal the expected time remaining (163.0)-METER_InsertCardPayNoSpot":
	  			Reporter.log(strErrorMsg);
				strErrorMsg = "The meter MaxTime remaining is calculated incorrectly when make a coin payment followed by credit card payment.";
				strPivotalId = "167490267";
				break;
  			case "The (Free Parking) Maintenance Mode for Meter (Spot 1) was not set to (True)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter free maintenance mode isn’t being updated correctly on vehicle exit.";
				strPivotalId = "166883596";
				break;
 			case "The expected meter screen (SCREEN_MULTI_HOME) did not appear current screen equaled (SCREEN_MULTI_IDLE)-METER_InsertCardPurchaseRemainingTimeNoSpot":
 				String strLocalStackTraceError = objDictionary.get("strLocalStackTraceError");if(strLocalStackTraceError == null) {strLocalStackTraceError = "False";}
 				if(strLocalStackTraceError.equals("True"))
 				{
	 				Reporter.log(strErrorMsg);
					strErrorMsg = "A STACKTRACE (cpu_load)  followed by a successful credit card payment, Brings user to IDLE screen instead of the Home Screen.";
					strPivotalId = "166766731";
 				}
				break;
 			case "The expected meter screen (SCREEN_DUAL_NON_EMPTY_HOME) did not appear current screen equaled (SCREEN_DUAL_IDLE)-METER_WaitForMeterScreen":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Coin payment went to Applied Payment Screen, then directly to the IDLE screen instead of Home.";
				strPivotalId = "166278247";
				break;
			case "The Meter Time did not increment correctly-METER_InsertCoinNoSpot":
				if(strTestCase.contains("M2012_RSVNTR_TU_PSVWL_VMT_LMV_VV_ULCP_ES1_VPSH"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "The meter time is calculated incorrectly when making a true up coin payment after reservation to rate time expires.  Expecting 15 mins actual 11 mins";
					strPivotalId = "184068429";
					break;
				}
				else
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "Coin payment doesn't get added to 2nd spot when first spot is in Maintenance Mode No Parking.";
					strPivotalId = "166291148";
					break;
				}
			case "The meter max time remaining (200) did not equal the expected time remaining (0.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - Max Time remaining is calculating incorrectly when purchasing time during rate to free";
				strPivotalId = "163460118";
				break;
			case "The meter max time remaining (225.0) did not equal the expected time remaining (0.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - Max Time remaining is calculating incorrectly when purchasing time during rate to free";
				strPivotalId = "164560994";
				break;
			case "The (Remote) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
				if(strTestCase.contains("FTR"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "Meter Max remaining time doesn't calculate correctly when making a remote payment for the remaining purchase time when in Free To Rate.";
					strPivotalId = "164502915";
				}
				break;
			case "The meter max time remaining (196.8) did not equal the expected time remaining (225.0)-METER_InsertCoin":
			case "The meter max time remaining (224.8) did not equal the expected time remaining (225.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - The Max time remaining after initial coin payment when Free Time First Payment exists isn't calculating correctly.";
				strPivotalId = "163529646";
				break;
			case "The meter max time remaining (210.0) did not equal the expected time remaining (0.0)-METER_InsertCoin"://FTFP 10
			case "The meter max time remaining (210.0) did not equal the expected time remaining (15.0)-METER_InsertCoin"://FTFP 0
			case "The meter max time remaining (210.0) did not equal the expected time remaining (16.0)-METER_InsertCoin"://FTFP 0
			case "The meter max time remaining (215.0) did not equal the expected time remaining (36.0)-METER_InsertCoin"://FTFP 10
			case "The meter max time remaining (215.0) did not equal the expected time remaining (35.0)-METER_InsertCoin"://FTFP 10
			case "The meter max time remaining (225.0) did not equal the expected time remaining (16.0)-METER_InsertCoin"://FTFP 0
			case "The meter max time remaining (225.0) did not equal the expected time remaining (15.0)-METER_InsertCoin"://FTFP 0
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - Rate to Free: MaxRemaining time is calculated incorrectly after coin payment when Minutes Before Free (30) > Meter increment time (15)";
				strPivotalId = "163307540";
				break;
			case "The meter max time remaining (235.0) did not equal the expected time remaining (225.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				if(strTestCase.contains("FTFP10"))
				{
					strErrorMsg = "Global Pay-The Free Time First payment doesn't get applied to the remote spot.";
					strPivotalId = "163330269";
				}
				else
				{
					strErrorMsg = "Global Pay - MaxTime is calculated incorrectly when removing free time first payment from the rate block.";
					strPivotalId = "163537013";
				}
				break;
			case "The Actual Meter Time (20) was not equal to expected meter time (150)"://M63_FTFP0_FDOFF_RTF_MTIV_gt_MBF_PS1_CP1_VMT_ES1_VPSH_VICAE_VIAC
				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter is displaying incorrect time in Rate to free when free disconnect is off/false.";
				strPivotalId = "159470690";
				break;
			case "The Actual Meter Time (19) was not equal to expected meter time (24)":
			case "The Actual Meter Time (19) was not equal to expected meter time (23)":
			case "The Actual Meter Time (22) was not equal to expected meter time (24)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter displays wrong time when purchasing max time after a violation while in Rate to Free, where free disconnect is true.";
				strPivotalId = "158540352";
				break;
			case "The Actual Meter Time (150) was not equal to expected meter time (330)":

				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter displays the wrong purchase time when purchasing max time in rate to free when free disconnect is set to false";
				strPivotalId = "158537123";
			case "The Actual Meter Time (48) was not equal to expected meter time (40)":
			case "The Actual Meter Time (49) was not equal to expected meter time (40)":
			case "The Actual Meter Time (99) was not equal to expected meter time (89)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When No Parking starts in 40 minutes, and 45 minutes are purchased the meter incorrectly displays 49 minutes.";
				strPivotalId = "157810810";
				break;
			case "The Meter Time did not increment correctly-METER_InsertCoinMaxTime":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Max time is not being calculated correctly when purchasing time while in Free To Rate.";
				strPivotalId = "156595298";
				break;
			case "The meter Maintenacne Mode value did not change to True":
			case "The (Unenforced Parking) Maintenance Mode for Meter (Spot 1) was not set to (True)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "If active parking session exists on spot 1 and Both Spots get put in Maint Unenforced, Spot one doesn’t go into maintenance mode like it did in prior SL builds.";
				strPivotalId = "161008756";
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
				clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Waiting for Meter To Reboot");
				break;
			case "The Actual Meter Time (0) was not equal to expected meter time (2)":
			case "The Actual Meter Time (0) was not equal to expected meter time (3)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Making a unlock payment on the meter when No Parking maintenance mode exists on the meter is not working correctly.";
				strPivotalId = "155837153";
				//Reboot
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
				clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Waiting for Meter To Reboot");
				break;
			case "The (No Parking) Maintenance Mode for Meter (Spot 1) was not set to (False)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Unable to clear maintenance mode after violation occurs on meter.";
				strPivotalId = "155402903";
				break;
			case "The Actual Meter Time (0) was not equal to expected meter time (15)":
				Reporter.log(strErrorMsg);
				if(strTestCase.contains("MP2"))
				{
					strErrorMsg = "Unable to add a mobile payment when meter spot is in Unenforced Maintenance mode.";
					strPivotalId = "155749440";
				}
				else
				{
					strErrorMsg = "When inserting a coin when meter is in Maint. Unenforced puts the time on the 2nd spot.  Expected the payment arrows to appear.";
					strPivotalId = "155205594";
				}
				break;
	    		case "The (Maintenance) Maintenance Mode for Meter (Spot 2) was not set to (False)":
			case "The (Maintenance) Maintenance Mode for Meter (Spot 1) was not set to (False)":
			case "None of the expected objects ({WaitUntilMeterVariableBegunEqualTrue} NA) existed after (40) seconds":
				if(strTestCase.contains("VMM"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "The meter isn't returning the correct Maintenance Mode value when a user puts the meter in Maint. Parking from SL.";
					strPivotalId = "156172643";
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
					clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Waiting for Meter To Reboot");
				}
				break;
			case "The Actual Meter Time (187) was not equal to expected meter time (15)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter displays the wrong purchase time after making a payment on a approved violation.";
				strPivotalId = "154451000";
				break;
	    	case "The Value ('amount': 375) did not exist in the Meter Logs":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The amount due is calculated incorrectly when making a credit card payment for the remaining about after a coin payment when in Free.";
				strPivotalId = "152564999";
				break;
			case "The Actual Meter Time (13) was not equal to expected meter time (5)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "";
  				strPivotalId ="";
				break;
			case "The Actual Meter Time (307) was not equal to expected meter time (309)":
			case "The Actual Meter Time (341) was not equal to expected meter time (343)":
			case "The cell value in row (4) column (4) of the table (Parking Session History) did not equal ($ 3.75) - actual value ($ 3.73)":
			case "The Actual Meter Time (342) was not equal to expected meter time (345)":
			case "The Actual Meter Time (353) was not equal to expected meter time (355)":
			case "The Actual Meter Time (297) was not equal to expected meter time (299)":
			case "The Actual Meter Time (297) was not equal to expected meter time (300)":
			case "The Actual Meter Time (83) was not equal to expected meter time (85)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The amount due is calculated incorrectly when making a credit card payment for the remaining about after a coin payment when in Free.";
  				strPivotalId ="152564999";
				break;
			case "The Actual Meter Time (70) was not equal to expected meter time (185)":
				strPivotalId = "146375673";
				break;
			case "None of the expected objects ({WaitUntilMeterFreeEqualsTrue} NA) existed after (120) seconds":
				clsMeter.SENTRYMETER_AddRateBlocksToAutomationResults(objDictionary,"Local");
				break;
			case "The meter max remaining time did not decrement correctly on Spot 1":
				strPivotalId = "SteveWorkingWithMark";
				break;
			case "None of the expected objects ({WaitUntilMeterViolationEqualsFalse} NA) existed after (30) seconds":
				strPivotalId = "133370117";
				break;
			case "None of the expected objects ({WaitUntilMeterVariableBegunEqualFalse} NA) existed after (30) seconds":
				strPivotalId = "141702819";
				break;
			case "Unexpected Dialog Appeared: Invalid credentials. Please check your user email id and password and try again.":
				strPivotalId = "127084171";
				break;
		    	case "The Checkbox (TRUCK) with index (1) was not checked":
		    		strPivotalId = "118048903";
		    		break;
		    	case "The ActualRemainingTimeMinutes (3) was less than FreeTime (120)":
			    	strPivotalId = "133462963";
		    		break;
		    	default:
		    		if(strErrorMsg.contains("Bad file descriptor"))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "Global Pay - Rate to Free: MaxRemaining time is calculated incorrectly after coin payment when Minutes Before Free (30) > Meter increment time (15)";
	    				strPivotalId = "163307540";
	    				break;
		    		}
		    		String strPattern = "The Meter (.*) spot (.*) was not in Violation Status";
		    		Pattern CompilePattern = Pattern.compile(strPattern);
		    		Matcher MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "When the initial grace period is set to zero, it still takes avg 45 seconds to go into violation.  If it's set to 1 minute it takes avg 62 seconds.";
	    				strPivotalId = "178233749";
	    				break;
		    		}
		    		strPattern = "The meter max time remaining presumed occupied (.*) - was not greater than (.*) and less than (.*)METER_.*";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "The Max Time Remaining calculation has changed (Incorrect?) when making an initial credit card payment payment in presumed occupied.";
	    				strPivotalId = "183923438";
	    				break;
		    		}
		    		strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\):\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			strErrorMsg = "ERROR   | ui.multi  | main_reactor | UISCT::_cardpay_timeout() Ignoring current_state UI_STATE_IDLE expected UI_STATE_CARD_TRANSACTION ";
						strPivotalId = "179135398";
	    				break;
		    		}
		    		strPattern = "None of the expected objects \\(resume_coins\\) existed after (.*) seconds";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			if(strTestCase.contains("FTR"))
		    			{
		    				strErrorMsg = "The coin pause is not occurring when making global pay coin payment while in Free to Rate.";
		    				strPivotalId = "183429767";
			    		}
		    			else
		    			{
			    			strErrorMsg = "The meter unexpectedly goes into \"pause_coin\" when adding a coin payment to a remote space that has existing time.";
		    				strPivotalId = "172469350";
		    			}
	    				break;
		    		}
		    		strPattern = "None of the expected objects \\(\\{WaitUntilMeterMaxRemainingEqualsZero\\} NA\\) existed after (.*) seconds-RMQ Cert may have expired";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "The meter unexpectedly goes into \"pause_coin\" when adding a coin payment to a remote space that has existing time.";
	    				strPivotalId = "172469350";
	    				break;
		    		}
		    		strPattern = "Failed: Value \\(\"result\":\"success\"\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "Unable to make coin payment on a remote space after clearing a Coin Full Alert on a local space.";
	    				strPivotalId = "172438908";
	    				break;
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("gt_FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Available time remaining is calculated incorrectly (3hr 40 mins) when minutes before No Parking is 20 minutes.";
		    				strPivotalId = "166033546";
		    				break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(on_message_pass\\(\\)\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			if(strTestCase.contains("CCP"))
		    			{
		    				strAssociatedBug = "170953001";
		    				strErrorMsg = "The python script that execute a credit card payment on dual meters is not longer working.";
			    			strPivotalId = "170953001";
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
			    			strErrorMsg = "Error making a credit card payment while in free to rate: void(): ignoring - no transaction currently instantiated";
			    			strPivotalId = "167874733";
		    			}
		    			else
		    			{
		    				strErrorMsg = "Unable to make a credit card payment after a coin payment on single meter when Free Time First Payment is enabled.";
			    			strPivotalId = "168174213";
		    			}
		    			break;
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTR"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter Remaining Time is calculated incorrectly when making a credit card payment or coin in Free To Rate.";
		    				strPivotalId = "172317409";
		    				break;
		    			}
		    			else if(strTestCase.contains("G100_Global_LM_POELS_WUIGPTELS_VVSTLS_W9_VMTUTLS9_CPLS_CPLS_183365985"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "When a user make a true up payment when free time first payment is enabled, sentry is incrementing the unlock counter on both the payment and the virtual payment.  When it should only increment it on the actual unlock payment.";
		    				strPivotalId = "183365985";
		    				break;
		    			}
		    			else
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter max time remaining isn't calculating correctly when make a coin payment (Presumed Occupied)  Expected 225 actual 465";
		    				strPivotalId = "170873229";
		    				break;
		    			}
		    		}
		    		strPattern = "None of the expected objects \\(\\{WaitUntilMeterInMaintEqualsFalse\\} .*\\) existed after (.*) seconds-RMQ Cert may have expired";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			strAssociatedBug = "169832015";
	    				strErrorMsg = "The maintenance mode isn't clearing from the  meter if a user parks in a space that is in maintenance,  then the maintenance mode is cleared from Sentry Link.";
	    				strPivotalId = "169832015";
	    				break;
		    		}
		    		strPattern = "The (.*) Meter Time did not increment correctly-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strExecutedGlobalPayAbbr.contains("MMUPRS") || strExecutedGlobalPayAbbr.contains("MMUPLS"))
		 				{
		 					strAssociatedBug = "169629728";
			 				Reporter.log(strErrorMsg);
							strErrorMsg = " When a global pay spot is in Maintenance mode (Unenforced) and a credit card is swiped, the payment is successful, and the meter time displays correctly on the screen, but the meter time purchased isn't incremented, and if you re-enter the spot the purchased time is gone";
							strPivotalId = "169629728";
		 				}
		    			else if(strExecutedGlobalPayAbbr.contains("MMNPRS") || strExecutedGlobalPayAbbr.contains("MMNPLS"))
		 				{
		 					strAssociatedBug = "169718110";
			 				Reporter.log(strErrorMsg);
							strErrorMsg = "If a active session exists on a global spot, and that spot goes into maintenance \"No Parking\" the user is no longer able to add payments to that space.";
							strPivotalId = "169718110";
		 				}
		    			else
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Unable to make a credit card payment on a remote space when the meter is in Rate to Free where the minutes before free were less that the purchase time and a payment exists on the local meter.";
		    				strPivotalId = "172343443";
		    				break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("MBNB_gt"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Available time remaining is calculated incorrectly (3hr 40 mins) when minutes before No Parking is 20 minutes.";
		    				strPivotalId = "166033546";
		    				break;
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
		    				String strRateBlockType = objDictionary.get("strRateBlockType");
		    				if(strRateBlockType == null)
		    				{
		    					String strGlobalPayActionsSpot1 = objDictionary.get("strGlobalPayActionsSpot1");
		    					if(strGlobalPayActionsSpot1 == null) {strGlobalPayActionsSpot1= "";}
		    					if(strGlobalPayActionsSpot1.equals("")) {strGlobalPayActionsSpot1 ="NA,NA";}
			    		 		String strGlobalPayActionsSpot2 = objDictionary.get("strGlobalPayActionsSpot2");
			    		 		if(strGlobalPayActionsSpot2 == null) {strGlobalPayActionsSpot2 ="";}
			    		 		if(strGlobalPayActionsSpot2.equals("")) {strGlobalPayActionsSpot2 ="NA,NA";}
			    		 		String[] arrGlobalPayActions1 = strGlobalPayActionsSpot1.split(",");
			    				String[] arrGlobalPayActions2 = strGlobalPayActionsSpot2.split(",");
			    				System.out.println(arrGlobalPayActions1[0]);
			    				System.out.println(arrGlobalPayActions2[0]);
			    				if(arrGlobalPayActions1[0].contains("CCPRS")||arrGlobalPayActions2[0].contains("CCPRS"))
			    				{
			    					//strAssociatedBug ="167520771";
				    				Reporter.log(strErrorMsg);
				    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
				    				strPivotalId = "167520771";
				    				break;
			    				}
		    				}
		    				else
		    				{
		    					if(strRateBlockType.equals("RateBlockToFreeToRate"))
		    					{
		    						strAssociatedBug ="167520771";
				    				Reporter.log(strErrorMsg);
				    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
				    				strPivotalId = "167520771";
				    				break;
		    					}
		    				}
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTR_FTFP0_CPRS_CCPRS"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "When purchasing in Free, the Maxtime available is decremented by the number of free minutes that elapse.";
							strPivotalId = "164964841";
							break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			String strTrueUp = objDictionary.get("strTrueUp");if(strTrueUp == null){strTrueUp = "False";}
		    			if(strErrorMsg.equals("The meter max time remaining (119.0) did not equal the expected time remaining (120.0)-METER_InsertCardPayNoSpot"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "When the meter increment time is 30 minutes and you park and swipe a credit card the meter time displasy as 2 hours 1 minute. If you complete the purchase you get that extra minute. Not sure of the down stream ramifications.";
							strPivotalId = "184616517";
		    			}
		    			else if(strTestCase.contains("RTF"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "Unable to purchase Max Time when making multiple payment Rate to Free when there is a time duration between the payments.";
							strPivotalId = "185364676";
		    			}
		    			else if(strTrueUp.equals("True"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "When making an unlock payment with a credit card where the unlock time > purchase time, the meter displays the payment correctly, but the space goes into Expired.";
							strPivotalId = "172981791";
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "When making the initial free to rate payment on a dual meter the free time isn’t being included on the display. It’s only showing the meter increment time.";
							strPivotalId = "183839825";
		    			}
		    			else
		    			{
			    			Reporter.log(strErrorMsg);
							strErrorMsg = "The Meter max remaining time is calculated incorrectly when making a credit card payment while Presumed Occupied on a remote space.";
							strPivotalId = "171840106";
		    			}
		    			break;
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCoin";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
						strErrorMsg = "The Meter max remaining time is calculated incorrectly when making a credit card payment while Presumed Occupied on a remote space.";
						strPivotalId = "171840106";
						break;
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			String strRateBlockType = objDictionary.get("strRateBlockType");
	    				if(strRateBlockType == null)
	    				{
	    					String strGlobalPayActionsSpot1 = objDictionary.get("strGlobalPayActionsSpot1");if(strGlobalPayActionsSpot1  == null) {strGlobalPayActionsSpot1 ="NA,NA";}if(strGlobalPayActionsSpot1.equals("")) {strGlobalPayActionsSpot1 ="NA,NA";}
		    		 		String strGlobalPayActionsSpot2 = objDictionary.get("strGlobalPayActionsSpot2");if(strGlobalPayActionsSpot2  == null) {strGlobalPayActionsSpot2 ="NA,NA";}if(strGlobalPayActionsSpot2.equals("")) {strGlobalPayActionsSpot2 ="NA,NA";}
		    		 		String[] arrGlobalPayActions1 = strGlobalPayActionsSpot1.split(",");
		    				String[] arrGlobalPayActions2 = strGlobalPayActionsSpot2.split(",");
		    				System.out.println(arrGlobalPayActions1[0]);
		    				System.out.println(arrGlobalPayActions2[0]);
		    				if(arrGlobalPayActions1[0].contains("CCPRS")||arrGlobalPayActions2[0].contains("CCPRS"))
		    				{
		    					strAssociatedBug ="167520771";
			    				Reporter.log(strErrorMsg);
			    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
			    				strPivotalId = "167520771";
			    				break;
		    				}
	    				}
	    				else
	    				{
	    					if(strRateBlockType.equals("RateBlockToFreeToRate"))
	    					{
	    						String strGlobalPayActionsSpot1 = objDictionary.get("strGlobalPayActionsSpot1");if(strGlobalPayActionsSpot1  == null) {strGlobalPayActionsSpot1 ="NA,NA";}if(strGlobalPayActionsSpot1.equals("")) {strGlobalPayActionsSpot1 ="NA,NA";}
			    		 		String strGlobalPayActionsSpot2 = objDictionary.get("strGlobalPayActionsSpot2");if(strGlobalPayActionsSpot2  == null) {strGlobalPayActionsSpot2 ="NA,NA";}if(strGlobalPayActionsSpot2.equals("")) {strGlobalPayActionsSpot2 ="NA,NA";}

			    		 		if(strGlobalPayActionsSpot1.equals("CCPLS,CPRTLS") && strGlobalPayActionsSpot2.equals("CCPRS,CPRTRS"))
			    		 		{
		    						strAssociatedBug ="171524219";
				    				Reporter.log(strErrorMsg);
				    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
				    				break;
			    		 		}
			    		 		else
			    		 		{
			    		 			strAssociatedBug ="167520771";
				    				Reporter.log(strErrorMsg);
				    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
				    				strPivotalId = "167520771";
				    				break;
			    		 		}
	    					}
	    				}
		    		}
		    		strPattern = "The Actual Meter Time (.*) was not equal to expected meter time (.*)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{

		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    			else if(strTestCase.contains("CS_FTR_PSWL_WFTE_ALPTPS"))
		    			{
		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is incorrect when a Concierge user parks in Free to Rate and the LPR fails and the license plate is entered after the violation.";
							strPivotalId = "174661813";
							break;
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "Meter-Remaining time is calculated incorrectly when purchasing max time while in Free up to No parking.";
							strPivotalId = "163886244";
							break;
		    			}
		    		}
		    		strPattern = "The (.*) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{

		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\):\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("169347804"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter unexpectedly go to the IDLE screen before the IDLE Screen Timeout is reached when making a Remote Credit Card Payment after a local Credit Card Payment";
		    				strPivotalId = "169347804";
		    				break;
		    			}
		    			else if(strTestCase.contains("169490710"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter Screen unexpectedly goes to the Idle screen after making a remote credit card payment after a local credit card payment.";
		    				strPivotalId = "169490710";
		    				break;
		    			}
		    			else if(strTestCase.contains("MMNP_VMM_WUMV_VMM_CP1_VMM_ES1_VMM_RMM_VMM"))
		    			{
		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "If existing parking session get put in maintenance mode no parking, and the meter violates the user cannot unlock the space.";
							strPivotalId = "166276846";
							break;
		    			}
		    		}
		    		strPattern = "The Actual Meter Time (.*) was not equal to expected meter time (.*)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTR_FTFP0_CPRS_CCPRS")||strTestCase.contains("FTR_FTFP0_CPRS_CCPRTRS")||strTestCase.contains("FTR_FTFP0_L_CPLS_CCPLS_CCPRTLS_R_CPRS_CCPRS_CCPRTRS"))
		    			{
			    			Reporter.log(strErrorMsg);
							strErrorMsg = "When purchasing in Free, the Maxtime available is decremented by the number of free minutes that elapse.  ";
							strPivotalId = "164964841";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(SCREEN_MULTI_HOME\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV")||strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
			    			strErrorMsg = "Master - QT errors on yocto";
							strPivotalId = "164587935";
							break;
		    			}
		    			else if(strTestCase.contains("S1_CPRS_CPLS_CCPRTLS_ESNRS_SP2_CCPRTLS_ESNLS_CCPRTRS"))
		    			{
		    				strErrorMsg = "After entering a space number, the loading screen appears, and doesn't go away.  Looks like a loop exists in the logs.";
		    				strPivotalId = "164993695";
							break;
		    			}
		    			else
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The SCREEN_MULTI_HOME doesn't always appear after entering the space on the key pad.";
							strPivotalId = "164993695";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(SCREEN_MULTI_SELECT_SPACE\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			strErrorMsg = "The SCREEN_MULTI_SELECT_SPACE doesn't always appear when click the Home Button";
						strPivotalId = "164997425";
						break;
		    		}
		    		strPattern = "The (.*) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "Global Pay - Max Time remaining is calculating incorrectly when purchasing time during rate to free";
							strPivotalId = "163460118";
			  				break;
		    			}
		    		}
					strPattern = "The Actual Meter Time (.*) was not equal to expected meter time (.*)";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV")||strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
			    			Reporter.log(strErrorMsg);
							strErrorMsg = "The Meter time is calculated incorrectly when Minutes Before No Parking > Free time Minutes > Meter Increment Time";
							strPivotalId = "163460118";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\):\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV")||strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
			    			Reporter.log(strErrorMsg);
							strErrorMsg = "The Meter time is calculated incorrectly when Minutes Before No Parking > Free time Minutes > Meter Increment Time";
							strPivotalId = "163460118";
							break;
		    			}
		    			if(strTestCase.contains("169347804"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "Meter unexpectedly go to the IDLE screen before the IDLE Screen Timeout is reached when making a Remote Credit Card Payment after a local Credit Card Payment";
							strPivotalId = "169347804";
							break;
		    			}

		    		}
		    		strPattern = "The Actual Meter Time (.*) was not equal to expected meter time (.*)";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("M29_FTFP0_FTR_RFT_gt_MTIV_PS1_CP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC")
		    			||strTestCase.contains("M30_FTFP0_FTR_RFT_gt_MTIV_PS1_CP1_PMT_LPRM_gt_MTIV_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Free To Rate - Last payment sets the validate time remaining to 0, and the meter displays free instead of purchased minutes.";
		    				strPivotalId ="159145802";
		    			}
		    			else if(strTestCase.contains("FTFP0_TUON_MUON_PCUV_RSMDNEMT_CGPV_CCP1_PMT_VMU_VPSH_VPSH_VICAE_VIAC"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Sentry rate blocks - change implementation to allow new rate_block RPC control of all rate settings";
		    				strPivotalId ="161183243";
		    			}
		    			else if(strTestCase.contains("FTFP0_TUOFF_MUON_PCUV_CGPV_CCP1_PMT_VMU_VPSH_VPSH_VICAE_VIAC"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter time isn't calculation correctly when making a unlock payment for maxtime when TrueUp = False.";
		      			strPivotalId ="161327749";
		    			}
		    			else if(strTestCase.contains("PMT") && strTestCase.contains("FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The amount due is calculated incorrectly when making a credit card payment for the remaining about after a coin payment when in Free.";
		    				strPivotalId ="152564999";
		    			}
		    			else if(strTestCase.contains("FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "Max time is not being calculated correctly when purchasing time while in Free To Rate.";
							strPivotalId = "156595298";
		    			}
		    			break;
		    		}
		    		strPattern = "Failed: Value \\(\"result\":\"success\"\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
						strErrorMsg = "When making a coin payment on remaining 5 mins payment fails coin pause.";
						strPivotalId = "185471165";
		    		}
	    }
		if(strErrorMsg.contains("No active spot!!"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "Occasionally when I add a coin payment, I get this ERROR in the logs \"No active spot!! spot None spot_name gp_spot_id SPOT_3\" and the payment does get added.";
			strPivotalId = "165235396";
		}
		else if(strErrorMsg.contains("The meter log contains a Traceback error"))
		{
			if(strTestCase.contains("170116397"))
			{
				Reporter.log(strErrorMsg);
				strErrorMsg = "Traceback  -  When making multiple remote coin payments on remote space.";
				strPivotalId = "170116397";
			}
		}
		else if(strErrorMsg.contains("previous global_pay transaction still in progress"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "Occasionally when I add a coin in to a remote space the Error:  \"ERROR    | ui.multi   | main_reactor | UISI: previous global_pay transaction still in progress, closing\"  appears in the meter log and the payment never gets added.";
			strPivotalId = "165270060";
		}
//		else if(strErrorMsg.contains("Ignoring current_state UI_STATE_IDLE expected UI_STATE_CARD_TRANSACTION"))
//		{
//			Reporter.log(strErrorMsg);
//			strErrorMsg = "Log Error: UISCT::_cardpay_timeout() Ignoring current_state UI_STATE_IDLE expected UI_STATE_CARD_TRANSACTION-METER";
//			strPivotalId = "165361870";
//		}
		else if(strErrorMsg.contains("add_payment(): reference_id None payment"))
		{
			Reporter.log(strErrorMsg);
			String lastAction = "";
			if(strExecutedGlobalPayAbbr != null)
			{
				String[] actions = strExecutedGlobalPayAbbr.split(",");
				lastAction = actions[actions.length-1];
			}
			if(strExecutedGlobalPayAbbr.equals("CPLS,CPRS,CCPLS,CCPRS"))
			{
				strErrorMsg = "RESPONSE doesn't include purchase_parking or id";
				strPivotalId = "166685908";
			}
			else if(lastAction.contains("CPRTRS"))
			{
				strErrorMsg = "After adding a 3rd coin, the meter screen unexpectedly  displayed the IDLE screen.   There was a Rabbit MQ disconnect before display_screen SCREEN_MULTI_IDLE.";
				strPivotalId = "166875952";
			}
			else
			{
				strErrorMsg = "After entering a coin payment on a remote spot, I'm not seeing a (\"result\":\"success\") row in the meter logs after 15 second of waiting.  The ERROR:  \"add_payment(): reference_id None payment\" Appears in the logs during the 15 second wait.";
				strPivotalId = "165365194";
			}
		}
		else if(strErrorMsg.contains("coin acceptor configured to reject and is accepting now"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "After entering a coin payment on a remote spot, I'm not seeing a (\"result\":\"success\") row in the meter logs after 15 second of waiting.  The ERROR:  \"confirm_coin_rejecting_status(): coin acceptor configured to reject and is accepting now\" Appears in the logs during the 15 second wait.";
			strPivotalId = "165366267";
		}
		else if(strErrorMsg.contains("unable to get id from payment_service"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "ERROR:  unable to get id from payment_service.  When making a Card remaining time purchase after an initial coin payment.";
			strPivotalId = "165515742";
		}
		else if(strErrorMsg.contains("not in self._valid_numbers"))
		{
			strErrorMsg = "Occasionally,  entering a remote spot doesn't display \"SCREEN_MULTI_SELECT_SPACE\" in the logs and remote spot doesn't appear on the screen, instead the logs displays \" is_valid_space_number: space 5592 not in self._valid_numbers";
			strPivotalId = "165604169";
		}
		String strExecutedGlobalPayActions = objDictionary.get("strExecutedGlobalPayActions");
		if(strExecutedGlobalPayActions != null)
		{
			Reporter.log("******Global Pay Actions******");
			Reporter.log(strExecutedGlobalPayAbbr.replaceFirst("<br>", ""));
			Reporter.log(strExecutedGlobalPayActions);
			Reporter.log("******Global Pay Actions******");
		}
		if(!strPivotalId.equals(""))
  		{
  			if(strAssociatedBug.contains(strPivotalId)||strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
  			{
  				//Yellow Means Know Issue
  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				objDictionary.put("strAssociatedBug",strPivotalId);
  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
  				//RPSS: Remain Parked Short Session
  				if(strExceptionError.equals("False"))
  				{
  					if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
  				}
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
  			if(strExceptionError.equals("False"))
			{
  				//RPSS: Remain Parked Short Session
  				clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked (15) seconds-Spot 1");
			}
  	      	Assert.fail(strErrorMsg);
  		}
	}

	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,WebDriver driver, String strErrorMsg)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		System.out.println("strAssociatedBug: " + strAssociatedBug);
		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strHost = objDictionary.get("strHost");
		String strExecutedGlobalPayAbbr = objDictionary.get("strExecutedGlobalPayAbbr");
		String strMeterId = objDictionary.get("strMeterId");
		Reporter.log(strErrorMsg);
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,driver, "Local");
		//Copy Screen Shot Locally
		if(!strErrorMsg.contains("lost connection"))
		{
			clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestCase);
		}
		if(strHost != null)
		{
			if(!strErrorMsg.contains(".py"))//Don't valid Meter logs if error includes python script.
			{
				String strMeterLogExist = objDictionary.get("strMeterLogExist");
				if(strMeterLogExist == null){strErrorMsg = clsMeter.METER_CheckForErrorsInMeterLogs(objDictionary, strErrorMsg);}
			}
		}
		//Create a request to the Pivotal API to get bug status and assigned to
		String strPivotalPath = "";
		String strPivotalId = "";
		String strRemainParkedShortSession = "True";
		//****************TEST CDOE********************************************
		//Create a request to the Pivotal API to get bug status and assigned to
		switch (strErrorMsg)//SCREEN_MULTI_SELECT_SPACE
		{
 			case "The (Local) Meter time did not increment correctly-METER_InsertCoin":
 				Reporter.log(strErrorMsg);
				strErrorMsg = "Unable to add a coin payment when purchasing time free to rate in latest meter build.";
				strPivotalId = "187729557";
				strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315564/stories/187729557";
				break;
 			case "Meter Time did not decremented correctly after coin payment-expected (25.0)-actual (25.2)-METER_InsertCoin":
 				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter time is calculated incorrectly when make a payment Rate to No Parking.";
				strPivotalId = "185932173";
				break;
			case "Meter Time did not decremented correctly after coin payment-expected (15.0)-actual (1)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter time is calculated incorrectly when make a payment Rate to No Parking.";
				strPivotalId = "184560523";
				break;
		   case "Meter Time did not decremented correctly after coin payment-expected (3.0)-actual (14)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When making a meter payment free to Rate on a remote multi space the meter time isn't displaying incorrectly.";
				strPivotalId = "184297358";
				break;
			case "The (Local) Meter Max Remaining Time (1) did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Max time remaining is calculated incorrectly when making a max time payment Free To Rate.  Expected maxtime 0 actual 1 minute.";
				strPivotalId = "184057945";
				break;
			case "The expected meter screen (SCREEN_DUAL_NON_EMPTY_HOME) did not appear current screen equaled (SCREEN_DUAL_IDLE)-METER_InsertCardPayNoSpot":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter screen name is incorrect when making a remaining time purchase in rate to free.  The expected meter screen (SCREEN_DUAL_NON_EMPTY_HOME) did not appear actual screen equaled (SCREEN_DUAL_IDLE)";
				strPivotalId = "184043994";
				break;
			case "The expected meter screen (SCREEN_MULTI_HOME) did not appear current screen equaled (SCREEN_MULTI_IDLE)-METER_WaitForMeterScreen":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When a global pay spot is in Maintenance mode (Unenforced) and a credit card is swiped, the payment is successful, and the meter time displays correctly on the screen, but the meter time purchased isn't incremented, and if you re-enter the spot the purchased time is gone";
				strPivotalId = "169629728";
				break;
   			case "The meter max time remaining (210.0) did not equal the expected time remaining (180.0)-METER_InsertCoin":
   			case "The meter max time remaining (210.0) did not equal the expected time remaining (181.0)-METER_InsertCoin":
   			case "The meter max time remaining (180.0) did not equal the expected time remaining (121.0)-METER_InsertCardPayNoSpot":
   			case "The meter max time remaining (225.0) did not equal the expected time remaining (199.0)-METER_InsertCoin":
   			case "The meter max time remaining (180.0) did not equal the expected time remaining (150.0)-METER_InsertCardPayNoSpot":
   			case "The meter max time remaining (180.0) did not equal the expected time remaining (151.0)-METER_InsertCardPayNoSpot":
   			case "The meter max time remaining (225.0) did not equal the expected time remaining (166.0)-METER_InsertCoin":
   			case "The meter max time remaining (225.0) did not equal the expected time remaining (165.0)-METER_InsertCoin":
   			case "The meter max time remaining (180.0) did not equal the expected time remaining (120.0)-METER_InsertCardPayNoSpot":
   				Reporter.log(strErrorMsg);
   				strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315564/stories/187847191";
				strErrorMsg = "Max time remaining is not being calculated correctly";
				strPivotalId = "187847191";
			break;
			case "The Coin Accepter was not Enabled":
				if(strTestCase.contains("RTF_LM_POELS_CPLS_CPLS_WULSRTEZ_W2_VMTUTLS2_CPLS"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "Rate going into free";
					strPivotalId = "183643400";
				}
				else
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "True up time shown in free";
					strPivotalId = "172594408";
				}
				break;
			case "Spot 1 Begin value did not equal True":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When a active session exists and the meter reboots the parking session no longer exists on the meter, but the session is still active is SL.";
				strPivotalId = "182742334";
				if(strMeterId != null)
				{
					//NTPS: Navigate To Parking Session
					threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
			  		driver = getDriver();
			      	clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver); 
			  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver); 
			  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
			  		try {Thread.sleep(5000);}catch (Exception e) {}
			  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Open Parking Sessions","Local");
			  		try {Thread.sleep(1500);}catch (Exception e) {}
			  		objDictionary.put("strWaitForPageLoad","False");
			  		objDictionary.put("SeleniumSwitchBrowserFlag","False");
			  		String strConditionalValue = clsCommonWeb.ConditionalStepButton(objDictionary, driver, "Open Parking Sessions", "End", 1, "Exists", "");
					if(strConditionalValue.equals("True"))
					{
						clsCommonWeb.ClickButton(objDictionary, driver,  "Open Parking Sessions", "End", 1, "Local");
					}
					try {Thread.sleep(1000);}catch (Exception e) {}
			  		driver.quit();
				}
				else
				{
					System.out.println("MIH");
				}
				break;
			
			case "Meter Time did not decremented correctly after coin payment-expected (65.0)-actual (64.6666666667)-METER_InsertCoin":
			case "Meter Time did not decremented correctly after coin payment-expected (65.0)-actual (64.5)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "RO - The meter time purchased is calculated incorrectly when adding coins between the rate blocks .";
				strPivotalId = "182036409";
				break;
			case "Spot 2 Begin value did not equal False":
				strErrorMsg = "When a meter is rebooted when a active session exists on spot 1, spot 2 gets a active session with a parked at time.";
				strPivotalId = "181703899";
			case "The meter sub screen did not equal PSV_STATE_AVAILABLE-Actual Value PSV_STATE_VALID":
				Reporter.log(strErrorMsg);
				strPivotalId = "NoPivotal";
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
				clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Waiting for Meter To Reboot");
				break;		
			case "The Actual Meter Time (0) was not equal to expected meter time (56)":
				strErrorMsg = "The reservation time isn't showing up on the meter when initial grace period expires followed by a CSR entering reservation plate.";
				strPivotalId = "181526103";
				strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315567/stories/181526103";
				break;
			case "Meter Time did not decremented correctly after coin payment-expected (5.0)-actual (8)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter time is calculating incorrectly when user parks in free then makes a payment in initial grace time.";
				strPivotalId = "183866222";
				break;
			case "None of the expected objects ({WaitUntilMeterViolationEqualsFalse} NA) existed after (30) seconds-RMQ Cert may have expired":
				strErrorMsg = "Whether the setting PARKING_STOP_CSR_RESET is set true or false the results are the same.  Rejected violation doesn't clear the violation on the meter.";
				strPivotalId = "179985202";
				break;
			case "The Actual Meter Time (240) was not equal to expected meter time (2)":
				strErrorMsg = "The Meter time is displaying incorrectly when a valid reservation user parked at the reservation space.";
				strPivotalId = "179221482";
				break;
			case "The Actual Meter Time (120) was not equal to expected meter time (15)":
				if(strTestCase.contains("CS_PS1_GPV1_WFFACTNVT_ALPTPS_VMT_VPSH"))
				{
					strErrorMsg = "When a Concierge user parks and the LP is recognized seconds before the initial grace period violation the meter screen display You must removed your vehicle instead of the time purchases.";
					strPivotalId = "178678280";
				}
				else if(strTestCase.contains("CS_PS1_GPV1_ALPTPS_VMT_VPSH"))
				{
					strErrorMsg = "When a Concierge user parks and violates before the LP is recognized the dual meter display Purchase Time Exceeded.  When the LP gets recognized and SL sends a payment. The meter screen does not update with time purchased.  ";
					strPivotalId = "178674942";
				}
				else if (strTestCase.contains("CS_FTR_PSWL_WFTE_ALPTPS_VMT_VPSH"))
				{
					strErrorMsg = "The Meter time is calculated incorrectly when parking free to rate with a Concierge User where LPR read fails and the LP get added after the violation.";
					strPivotalId = "181330406";
				}	
				break;
			case "The Actual Meter Time (31) was not equal to expected meter time (25)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter time is calculated incorrectly when making a 2nd presumed occupied payment 5 minutes after the original payment";
				strPivotalId = "175938308";
				break;
			case "Meter Time did not decremented correctly after credit card payment-expected (240.0)-actual (29)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after credit card payment-expected (240.0)-actual (30)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after coin payment-expected (30)-actual (60)-METER_InsertCardPayNoSpot":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When minutes before No parking equals 30 and a credit card payment is entered the meter unexpectedly displays 60 mins.";
				strPivotalId = "174935714";
				break;
			case "The Actual Meter Time (0) was not equal to expected meter time (58)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When a reservation is created for a user and the “Valid From” Date is current date, the reservation time does not appear on the meter when the user parks";
				strPivotalId = "174803984";
				break;
   			case "The Initial Grace Period Violated unexpectedly created multiple violations":
   				Reporter.log(strErrorMsg);
				strErrorMsg = "Duplicate Additional Time Expired Violation";
				strPivotalId = "172745182";
				break;
   			case "When making the initial free to rate payment on a dual meter the free time isn't being included on the display. It's only showing the meter increment time":	
   			case "The expected meter screen (SCREEN_DUAL_APPLY_PAYMENT) did not appear current screen equaled (SCREEN_DUAL_NON_EMPTY_HOME)-METER_WaitForMeterScreen":
   				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter time calculated incorrectly when making a Credit Card Payment followed by coin payment up to maxtime.";
				strPivotalId = "171217543";
				break;
   			case "The (No Parking) Maintenance Mode for Meter (Spot 1) was not set to (True)":
  				Reporter.log(strErrorMsg);
				strErrorMsg = "When a prior payment expires on a meter in maintenance mode \"no parking\" the Maintenance mode icon isn't displayed on the screen.";
				strPivotalId = "168550387";
				break;
  			case "The (Local) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
  			case "Meter Time did not decremented correctly after credit card payment-expected (29.0)-actual (27)-METER_InsertCardPurchaseRemainingTimeNoSpot":
  				if(strTestCase.contains("FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_LPRM_e_MTIV"))
  				{
  					Reporter.log(strErrorMsg);strPivotalId = "184057945";
	  				strErrorMsg = "Max time remaining is calculated incorrectly when making a max time payment Free To Rate. Expected maxtime 0 actual 1 minute.";
  				}
  				else if(strTestCase.contains("FTFP0_TUOFF_MUON_PCUV_CGPV_CCP1_PMT")||strTestCase.contains("FTFP0_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_LPRM_e_MTIV")||strTestCase.contains("FTFP0_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_PRT")||strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV"))
  				{
  					Reporter.log(strErrorMsg);strPivotalId = "184057945";
	  				strErrorMsg = "Max time remaining is calculated incorrectly when making a max time payment Free To Rate.  Expected maxtime 0 actual 1 minute.";
  				}
  				else if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV"))
  				{
  					Reporter.log(strErrorMsg);strPivotalId = "171340352";
	  				strErrorMsg = "Max time on iOS is calculated incorrectly when making a coin payment followed by a mobile payment.";
				}
  				else if (strTestCase.contains("FTFP0_FTR_PS1_CP1_VMT_PRT_CCP1_VMT_ES1_VPSH_VICAE_VIAC")||strTestCase.contains("FTFP10_FTR_PS1_CP1_VMT_PRT_CCP1_VMT_ES1_VPSH_VICAE_VIAC")|| strTestCase.contains("M1057B_FTFP0_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_PRT_ES1_VPSH_VICAE_VIAC")&& strForcedMulti != "true")
  				{
  					strPivotalId = "185220812";
  					strErrorMsg = "Meter in the dual setting is not calculating Max time correctly-R";
  				}
  				else if (strTestCase.contains("M1111_FTFP0_TUON_FDON_MUON_MUT5_RTF_CGPV_W4_PMT_VMT_ES1_VPSH_VICAE_VIAC"))
  				{
  					Reporter.log(strErrorMsg);strPivotalId = "187726515";
	  				strErrorMsg = "Max time remaining is calculated incorrectly when making a max time true up payment Rate To Free.  Expected maxtime 0 actual 1 minute.";
	  				strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315564/stories/187726515";
  				}
  				else
  				{
  					Reporter.log(strErrorMsg);strPivotalId = "182809528";
	  				strErrorMsg = "Customer is getting overcharged  when the remaining free time is 60 minutes and the time before no parking is 3 hours. (2 hour time slot between free and no parking)  Also the payment appears on the meter, then it goes away.";
				}
				break;
  			case "None of the expected objects ({WaitUntilMeterInMaintEqualsTrue} 1) existed after (30) seconds-RMQ Cert may have expired":
  			case "None of the expected objects ({WaitUntilMeterInMaintEqualsTrue} 2) existed after (45) seconds-RMQ Cert may have expired":
  			case "None of the expected objects ({WaitUntilMeterInMaintEqualsTrue} 1) existed after (45) seconds-RMQ Cert may have expired":
  				Reporter.log(strErrorMsg);
				strErrorMsg = "When user set the Maintenance Mode to No Parking from Sentry Link, the Meter doesn't get updated to display No Parking.";
				//strPivotalId = "168277029";
				strPivotalId = "177048633";
				clsCommonWeb.PopulateAction(objDictionary, driver, "Meter", "Populate Maintenance Values", "{CB} Maintenance All", "Checked");
		  		clsCommonWeb.ClickButton(objDictionary, driver, "Meter", "Clear All", 1, "Local");
		  		driver.quit();
				break;
  			case "The meter max time remaining (165.0) did not equal the expected time remaining (163.0)-METER_InsertCardPayNoSpot":
	  			Reporter.log(strErrorMsg);
				strErrorMsg = "The meter MaxTime remaining is calculated incorrectly when make a coin payment followed by credit card payment.";
				strPivotalId = "167490267";
				break;
  			case "The (Free Parking) Maintenance Mode for Meter (Spot 1) was not set to (True)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter free maintenance mode isn’t being updated correctly on vehicle exit.";
				strPivotalId = "166883596";
				break;
 			case "The expected meter screen (SCREEN_MULTI_HOME) did not appear current screen equaled (SCREEN_MULTI_IDLE)-METER_InsertCardPurchaseRemainingTimeNoSpot":
 				String strLocalStackTraceError = objDictionary.get("strLocalStackTraceError");
 				if(strLocalStackTraceError.equals("True"))
 				{
	 				Reporter.log(strErrorMsg);
					strErrorMsg = "A STACKTRACE (cpu_load)  followed by a successful credit card payment, Brings user to IDLE screen instead of the Home Screen.";
					strPivotalId = "166766731";
 				}
				break;
 			case "The expected meter screen (SCREEN_DUAL_NON_EMPTY_HOME) did not appear current screen equaled (SCREEN_DUAL_IDLE)-METER_WaitForMeterScreen":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Coin payment went to Applied Payment Screen, then directly to the IDLE screen instead of Home.";
				strPivotalId = "166278247";
				break;
			case "The Meter Time did not increment correctly-METER_InsertCoinNoSpot":
				if(strTestCase.contains("M2012_RSVNTR_TU_PSVWL_VMT_LMV_VV_ULCP_ES1_VPSH"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "The meter time is calculated incorrectly when making a true up coin payment after reservation to rate time expires.  Expecting 15 mins actual 11 mins";
					strPivotalId = "184068429";
					break;
				}
				else
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "Coin payment doesn't get added to 2nd spot when first spot is in Maintenance Mode No Parking.";
					strPivotalId = "166291148";
					strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315564/stories/166291148";
					break;
				}
			case "The meter max time remaining (200) did not equal the expected time remaining (0.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - Max Time remaining is calculating incorrectly when purchasing time during rate to free";
				strPivotalId = "163460118";
				break;
			case "The meter max time remaining (225.0) did not equal the expected time remaining (0.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - Max Time remaining is calculating incorrectly when purchasing time during rate to free";
				strPivotalId = "164560994";
				break;
			case "The (Remote) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot":
				if(strTestCase.contains("FTR"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "Meter Max remaining time doesn't calculate correctly when making a remote payment for the remaining purchase time when in Free To Rate.";
					strPivotalId = "164502915";
				}
				break;
			case "The meter max time remaining (196.8) did not equal the expected time remaining (225.0)-METER_InsertCoin":
			case "The meter max time remaining (224.8) did not equal the expected time remaining (225.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - The Max time remaining after initial coin payment when Free Time First Payment exists isn't calculating correctly.";
				strPivotalId = "163529646";
				break;
			case "The meter max time remaining (210.0) did not equal the expected time remaining (0.0)-METER_InsertCoin"://FTFP 10
			case "The meter max time remaining (210.0) did not equal the expected time remaining (15.0)-METER_InsertCoin"://FTFP 0
			case "The meter max time remaining (210.0) did not equal the expected time remaining (16.0)-METER_InsertCoin"://FTFP 0
			case "The meter max time remaining (215.0) did not equal the expected time remaining (36.0)-METER_InsertCoin"://FTFP 10
			case "The meter max time remaining (215.0) did not equal the expected time remaining (35.0)-METER_InsertCoin"://FTFP 10
			case "The meter max time remaining (225.0) did not equal the expected time remaining (16.0)-METER_InsertCoin"://FTFP 0
			case "The meter max time remaining (225.0) did not equal the expected time remaining (15.0)-METER_InsertCoin"://FTFP 0
				Reporter.log(strErrorMsg);
				strErrorMsg = "Global Pay - Rate to Free: MaxRemaining time is calculated incorrectly after coin payment when Minutes Before Free (30) > Meter increment time (15)";
				strPivotalId = "163307540";
				break;
			case "The meter max time remaining (235.0) did not equal the expected time remaining (225.0)-METER_InsertCoin":
				Reporter.log(strErrorMsg);
				if(strTestCase.contains("FTFP10"))
				{
					strErrorMsg = "Global Pay-The Free Time First payment doesn't get applied to the remote spot.";
					strPivotalId = "163330269";
				}
				else
				{
					strErrorMsg = "Global Pay - MaxTime is calculated incorrectly when removing free time first payment from the rate block.";
					strPivotalId = "163537013";
				}
				break;
			case "The Actual Meter Time (20) was not equal to expected meter time (150)"://M63_FTFP0_FDOFF_RTF_MTIV_gt_MBF_PS1_CP1_VMT_ES1_VPSH_VICAE_VIAC
				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter is displaying incorrect time in Rate to free when free disconnect is off/false.";
				strPivotalId = "159470690";
				break;
			case "The Actual Meter Time (19) was not equal to expected meter time (24)":
			case "The Actual Meter Time (19) was not equal to expected meter time (23)":
			case "The Actual Meter Time (22) was not equal to expected meter time (24)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter displays wrong time when purchasing max time after a violation while in Rate to Free, where free disconnect is true.";
				strPivotalId = "158540352";
				break;
			case "The Actual Meter Time (150) was not equal to expected meter time (330)":
				
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter displays the wrong purchase time when purchasing max time in rate to free when free disconnect is set to false";
				strPivotalId = "158537123";
			case "The Actual Meter Time (48) was not equal to expected meter time (40)":
			case "The Actual Meter Time (49) was not equal to expected meter time (40)":
			case "The Actual Meter Time (99) was not equal to expected meter time (89)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "When No Parking starts in 40 minutes, and 45 minutes are purchased the meter incorrectly displays 49 minutes.";
				strPivotalId = "157810810";
				break;
			case "The Meter Time did not increment correctly-METER_InsertCoinMaxTime":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Max time is not being calculated correctly when purchasing time while in Free To Rate.";
				strPivotalId = "156595298";
				break;
			case "The meter Maintenacne Mode value did not change to True":
			case "The (Unenforced Parking) Maintenance Mode for Meter (Spot 1) was not set to (True)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "If active parking session exists on spot 1 and Both Spots get put in Maint Unenforced, Spot one doesn’t go into maintenance mode like it did in prior SL builds.";
				strPivotalId = "161008756";
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
				clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Waiting for Meter To Reboot");
				break;
			case "The (No Parking) Maintenance Mode for Meter (Spot 1) was not set to (False)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Unable to clear maintenance mode after violation occurs on meter.";
				strPivotalId = "155402903";
				break;
			case "The Actual Meter Time (0) was not equal to expected meter time (15)":
				Reporter.log(strErrorMsg);
				if(strTestCase.contains("MP2"))
				{
					strErrorMsg = "Unable to add a mobile payment when meter spot is in Unenforced Maintenance mode.";
					strPivotalId = "155749440";
				}
				else if(strTestCase.contains("RSVNTR_CS_PSVWL_VMT"))
				{
					strErrorMsg = "Concierge user parking in reservation to rate is violating after reservation time expires.";
					strPivotalId = "174731286";
				}
				else if(strTestCase.contains("CS_PS1_GPV1_WFFACTNVT_ALPTPS_VMT_VPSH"))
				{
					strErrorMsg = "When a Concierge user parks and the license plate is recognized seconds before the initial grace period violation, the meter screen unexpectedly displays “Max Time Already Purchased";
					strPivotalId = "SL-7995";
				}
				else if(strTestCase.contains("CS_PS1_GPV1_ALPTPS_VMT_VPSH"))
				{
					strErrorMsg = "When a Concierge user parks and violates before the LP is recognized the dual meter display Purchase Time Exceeded.  When the LP gets recognized and SL sends a payment. The meter screen does not update with time purchased.  ";
					strPivotalId = "178674942";
				}
				else
				{
					strErrorMsg = "When inserting a coin when meter is in Maint. Unenforced puts the time on the 2nd spot.  Expected the payment arrows to appear.";
					strPivotalId = "155205594";
				}
				break;
	    		case "The (Maintenance) Maintenance Mode for Meter (Spot 2) was not set to (False)":
			case "The (Maintenance) Maintenance Mode for Meter (Spot 1) was not set to (False)":
			case "None of the expected objects ({WaitUntilMeterVariableBegunEqualTrue} NA) existed after (40) seconds":
				if(strTestCase.contains("VMM"))
				{
					Reporter.log(strErrorMsg);
					strErrorMsg = "The meter isn't returning the correct Maintenance Mode value when a user puts the meter in Maint. Parking from SL.";
					strPivotalId = "156172643";
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
					clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Waiting for Meter To Reboot");
				}
				break;
			case "The Actual Meter Time (187) was not equal to expected meter time (15)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "Meter displays the wrong purchase time after making a payment on a approved violation.";
				strPivotalId = "154451000";
				break;
			case "Meter Time did not decremented correctly after coin payment-expected (41.0)-actual (36)-METER_InsertCardPayNoSpot":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The meter time was not calculated correctly";
				strPivotalId = "186172379";
				break;

	    	case "The Value ('amount': 375) did not exist in the Meter Logs":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The amount due is calculated incorrectly when making a credit card payment for the remaining about after a coin payment when in Free.";
				strPivotalId = "152564999";
				break;
			case "The Actual Meter Time (13) was not equal to expected meter time (5)":
				Reporter.log(strErrorMsg);
				break;
			case "The Actual Meter Time (307) was not equal to expected meter time (309)":
			case "The Actual Meter Time (341) was not equal to expected meter time (343)":
			case "The cell value in row (4) column (4) of the table (Parking Session History) did not equal ($ 3.75) - actual value ($ 3.73)":
			case "The Actual Meter Time (342) was not equal to expected meter time (345)":
			case "The Actual Meter Time (353) was not equal to expected meter time (355)":
			case "The Actual Meter Time (297) was not equal to expected meter time (299)":
			case "The Actual Meter Time (297) was not equal to expected meter time (300)":
			case "The Actual Meter Time (83) was not equal to expected meter time (85)":
				Reporter.log(strErrorMsg);
				strErrorMsg = "The amount due is calculated incorrectly when making a credit card payment for the remaining about after a coin payment when in Free.";
  				strPivotalId ="152564999";
				break;
			case "The Actual Meter Time (70) was not equal to expected meter time (185)":
				strPivotalId = "146375673";
				break;
			case "None of the expected objects ({WaitUntilMeterFreeEqualsTrue} NA) existed after (120) seconds":
				clsMeter.SENTRYMETER_AddRateBlocksToAutomationResults(objDictionary,"Local");
				break;
			case "The meter max remaining time did not decrement correctly on Spot 1":
				strPivotalId = "SteveWorkingWithMark";
				break;
			case "None of the expected objects ({WaitUntilMeterViolationEqualsFalse} NA) existed after (30) seconds":
				strPivotalId = "133370117";
				break;
			case "None of the expected objects ({WaitUntilMeterVariableBegunEqualFalse} NA) existed after (30) seconds":
				strPivotalId = "141702819";
				break;
			case "The meter max time remaining (6.0) did not equal the expected time remaining (1.0)-METER_InsertCoin":
			case "The meter max time remaining (8) did not equal the expected time remaining (0.0)-METER_InsertCoin":
			case "The meter max time remaining (6.0) did not equal the expected time remaining (0.0)-METER_InsertCoin":
			case "The meter max time remaining (7) did not equal the expected time remaining (0.0)-METER_InsertCoin":
				strPivotalId = "187504011";
				break;
			case "None of the expected objects ({WaitUntilMeterVariableBegunEqualFalse} NA) existed after (30) seconds-RMQ Cert may have expired":
				  Reporter.log(strErrorMsg);
				  strPivotalId = "188358875";
				  strErrorMsg = "When making a coin payment, it's  taking a long time to process the payment, which makes the Vehicle left log to not show up.";
				break;
			case "expected (225.0)-actual (75)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "expected (236.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "expected (240.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "expected (237.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "expected (120.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "expected (236.0)-actual (121)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after credit card payment-expected (225.0)-actual (85)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after credit card payment-expected (240.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after credit card payment-expected (250.0)-actual (70)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after credit card payment-expected (236.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
			case "Meter Time did not decremented correctly after credit card payment-expected (239.0)-actual (60)-METER_InsertCardPurchaseRemainingTimeNoSpot":
				strPivotalId = "185220812";
				strErrorMsg = "Meter in the dual setting is not calculating Max time correctly-R";
			     break;
			case "Meter Time did not decremented correctly after coin payment-expected (17.0)-actual (15.8333333333)-METER_InsertCoin":
				strPivotalId="183468408";
				strErrorMsg="The Meter Time is calculated incorrectly when make a coin payment on Royal Oak settings where the coin is added between rate blocks.";
				break;
			case "The Meter time was not between (15) and (62.0) - actual actual (60.0)-METER_InsertCoin":
				strPivotalId ="185247091";
				strErrorMsg = "In Dual Meter, presumed Occupied-Valid Until is not synching up with the time remaining-R";
				break;
			case "Unexpected Dialog Appeared: Invalid credentials. Please check your user email id and password and try again.":
				strPivotalId = "127084171";
				break;
		    	case "The Checkbox (TRUCK) with index (1) was not checked":
		    		strPivotalId = "118048903";
		    		break;
		    	case "The ActualRemainingTimeMinutes (3) was less than FreeTime (120)":
			    	strPivotalId = "133462963";
		    		break;
		    	default:
		    		if(strErrorMsg.contains("Bad file descriptor"))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "Global Pay - Rate to Free: MaxRemaining time is calculated incorrectly after coin payment when Minutes Before Free (30) > Meter increment time (15)";
	    				strPivotalId = "163307540";
	    				break;
		    		}
		    		else if (strErrorMsg.contains("clear_violation: can't clear - concierge False merchant_validated False reservation False permitted False"))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "I'm not seeing the meter logs display \"result\":\"success\" after making a mobile payment on a space where the violation has been snoozed.";
	    				strPivotalId = "184791360";
	    				break;
	
		    		}
		    		String strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCoin";
		    		Pattern CompilePattern = Pattern.compile(strPattern);
		    		Matcher MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("_RO_"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The Meter Time is calculated incorrectly when make a coin payment on Royal Oak settings where the coin is added between rate blocks.";
							strPivotalId = "183468408";
							break;
		    			}
		    			else if(strTestCase.contains("FTFP0_TU_RNP_MBNB_gt_MBNP_PS1_CGPV1_CP1_UL_BMT_ES1_VPSH_VICAE_VIAC"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter Time is incorrect when make a True Up payment when Minutes Before No Parking (25) > Meter Increment Time (15) and user makes 2 coin payments.";
		    				strPivotalId = "180636720";
		    				break;
		    			}
		    			else if(strTestCase.contains("RSVN"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Reservations are no longer working in SL.  Started in Version 8.12.2.6";
		    				strPivotalId = "187215421";
		    				break;
		    			}
		    			else if(strTestCase.contains("M1053_G_FTFP0_RNP_MTIV_gt_MBNB_PS1_CGPV1_W2_CP1_UL_ES1_VPSH_VICAE_VIAC"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter time is calculated incorrectly when making an unlock payment with a mobile app when True up is Disabled.";
		    				strPivotalId = "183842654";
		    				break;
		    			}
		    		}
		    		strPattern = "The Actual Meter Time (.*) was not equal to expected meter time (.*)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1"))
			  			{
				  			Reporter.log(strErrorMsg);
			  				strErrorMsg = "Meter time is not being displayed on the meter when making a mobile payment where the minutes before free < Meter Increment Time w/Free Time First payment.";
			  				strPivotalId = "183964856";
			  			}
		    			else if(strTestCase.contains("FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT"))
			  			{
			  				Reporter.log(strErrorMsg);
			  				strPivotalId = "183984038";
			  				strErrorMsg = "The meter time isn't calculated correctly when making a back to back CA payment in free to rate when free time first payment 10 is enabled.";
			  			}	
		    			else if(strTestCase.contains("FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1"))
			  			{
			  				Reporter.log(strErrorMsg);
			  				strPivotalId = "184027852";
			  				strErrorMsg = "he meter time isn't being displayed on the meter when a user makes a mobile payment Rate To Free when minutes before free < Meter time w/ Free Time First payment";
			  			}	
		    			else if(strTestCase.contains("FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1"))
			  			{
			  				Reporter.log(strErrorMsg);
			  				strPivotalId = "184059120";
			  				strErrorMsg = "The meter time is incorrect when rejecting a violation with a payment when free time first is enabled. Expecting 70 minutes actual 71.";
			  			}	
		    			else if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV")||strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
			    			Reporter.log(strErrorMsg);
							strErrorMsg = "The Meter time is calculated incorrectly when Minutes Before No Parking > Free time Minutes > Meter Increment Time";
							strPivotalId = "163460118";
							break;
		    			}
		    			else if(strTestCase.contains("FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT"))
			  			{
			  				Reporter.log(strErrorMsg);
			  				strPivotalId = "184186698";
			  				strErrorMsg = "The meter time is calculated incorrectly when making a mobile payment Free to rate when meter increment time > remaining free time. (iOS Only) (Dual)";
			  			}	
		    			else if(strTestCase.contains("FDOFF"))
		    			{
		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    			else if(strTestCase.contains("FTR_RFT_gt_MTIV_PS1_MP1")|| strTestCase.contains("FTR_MTIV_gt_RFT_PS1_MP1"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "After the Sept 27 SL push I'm seeing an issue where the Free Time isn't being included when doing a mobile payment.";
							strPivotalId = "183408640";
							break;
		    			}
		    			else if(strTestCase.contains("CGPV1_RV_RES_IP"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is calculated incorrectly when adding a coin payment after rejecting a violation with Reset Session and Include Payment are checked.";
			    			strPivotalId = "171441463";
			    			break;
		    			}
		    			else if(strTestCase.contains("FTFP0_TUON_FDON_MUON_MUT5_RTF_CGPV_W4_PMT"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "The meter time displayed is greater than the available time when unlocking a meter with true up enabled.";
							strPivotalId = "183917076";
							break;
		    			}
		    			else if (strTestCase.contains("FTFP0_TUOFF_MUON_PCUV_CGPV_CCP1_PMT"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "The meter time is calculated incorrectly when making a unlock CC payment for max time. ";
							strPivotalId = "184064981";
							break;
		    			}
		    			else if(strTestCase.contains("FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_PMT"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The Meter time is calculated incorrectly when purchasing Max Time while in Free to Rate with free time first payment enabled.";
			    			strPivotalId = "171445499";
			    			break;
		    			}
		    			else if(strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "The Meter Time is calculated incorrectly when making a coin payment when meter is in Free -> Rate -> No Parking.  The meter max_time_allowed equaled 37, but the time purchased equaled 39.";
							strPivotalId = "182819241";
							break;
		    			}
		    			else if(strTestCase.contains("FTR") && strTestCase.contains("PRT")||strTestCase.contains("FTR") && strTestCase.contains("PMT"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "If the user make multiple mobile payment while in Free to Rate, the time between those 2 payments gets lost.";
							strPivotalId = "169782400";
							break;
		    			}
		    			else if(strTestCase.contains("FTR_RFT_gt_MTIV"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter time isn't being added to the meter after a successful credit card swipe when meter is Free to Rate.";
							strPivotalId = "168407497";
		    			}
		    			else if (strTestCase.contains("RV_RES_IP_wo_LP"))
		    			{
		    				Reporter.log(strErrorMsg);
			    			strPivotalId = "181543065";Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is calculated incorrectly when adding a coin payment after rejecting a violation with Reset Session and Include Payment are checked.";
		    			}
		    			else if(strTestCase.contains("FTR_PS1_WFTE"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter time is calculating incorrectly when user parks in free then makes a payment in initial grace time. ";
							strPivotalId = "180904841";
		    			}
//		    			else if (strTestCase.contains("FTFP10"))
//		    			{
//		    				Reporter.log(strErrorMsg);
//		    				strErrorMsg = "Meter time is calculated incorrectly when Free Time First Payment is 10 and a coin payment is made.";
//							strPivotalId = "170429307";
//		    			}
		    			else if (strTestCase.contains("CS_FTR_PSWL_WFTE_ALPTPS"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "If a concierge user violates when parks free to rate with a failed LPR & the CSR enters the license plate, no time gets added to the time gets added to the meter.";
							strPivotalId = "175081984";
		    			}
		    			else if(strTestCase.contains("CS_FTR_PSWL_WFTE_VMT_VPSH"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter time is incorrect when parking Free to rate with a concierge users.";
							strPivotalId = "181320619";
							break;
						}
		    			else if(strTestCase.contains("FTFP0_TU_RNP_MBNB_gt_MBNP_PS1_CGPV1_CP1_UL_BMT_ES1_VPSH_VICAE_VIAC"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter Time is incorrect when make a True Up payment when Minutes Before No Parking (25) > Meter Increment Time (15) and user makes 2 coin payments.";
		    				strPivotalId = "180636720";
		    				break;
		    			}
		    			else if(strTestCase.contains("RSVN"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Text (Payment rejected, spot is reserved) with index (1) did not contain (Payment rejected, spot is reserved) - actual value (Parking purchases beyond the maximum time limit is prohibited. Please refer to the parking rules//limits in your area.)";
		    				strPivotalId = "187626408";
		    				break;
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter-Remaining time is calculated incorrectly when purchasing max time while in Free up to No parking.";
							strPivotalId = "163886244";
							break;
		    			}
		    			else if(strTestCase.contains("A2161_SENTRYMOBILE_PS1_EC1_NSAU_NSTC"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "In Quality When a User Parks then enables concierge the parking session doesn't get updated to Concierges and time doesn't get added to the meter.";
							strPivotalId = "7028";
							break;
		    			}
		    			else if(strTestCase.contains("A2090_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2091_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2092_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2093_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2094_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2095_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2096_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2097_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2102_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2103_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2104_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2105_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2106_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2107_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2108_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else if(strTestCase.contains("A2109_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strPivotalId = "FLUTTERCA-155";Reporter.log(strErrorMsg);
		    				strPivotalPath = "https://mpspark.atlassian.net/browse/FLUTTERCA-155";
			    			strErrorMsg = "Performing a Mobile Unlock Payment on the Flutter app unexpectedly generates two parking sessions.";
			    			break;
		    			}
		    			else
			  			{
				  			Reporter.log(strErrorMsg);
			  				strErrorMsg = "The meter time is calculated incorrectly when making an unlock payment with a mobile app when True up is Disabled.";
			  				strPivotalPath = "https://mpspark.atlassian.net/issues?jql=textfields%20~%20%22183842654*%22&selectedIssue=SEN-1171";
			  				strPivotalId = "183842654";
			  			}
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCardPayNoSpot";
		    		strPattern = "Meter Time did not decremented correctly after credit card payment-expected (.*)-actual (.*)-METER_InsertCardPurchaseRemainingTimeNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTFP0_FDON_MUON_MUT5_RTF_CGPV_W4_PMT"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The Valid Time Purchase is incorrect when on the Meter when making a unlock payment Rate to Free (Dual)";
							strPivotalId = "184077625";
							break;
		    			}
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTFP0_TU_RNP_MBNB_gt_MBNP_PS1_CGPV1_CP1_UL_BMT_ES1_VPSH_VICAE_VIAC"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter Time is incorrect when make a True Up payment when Minutes Before No Parking (25) > Meter Increment Time (15) and user makes 2 coin payments.";
		    				strPivotalId = "180636720";
		    				break;
		    			}
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("_FTFP0_FTR_PS1_WFTE_CCP1"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strPivotalId = "183866222";
		    				strErrorMsg = "The meter time is calculating incorrectly when user parks in free then makes a payment in initial grace time.";
		    				break;
		    			}
		    			else
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Incorrect card amount rate to no parking";
		    				strPivotalId = "180551161";
		    				break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining presumed occupied (.*) - was not greater than \\(225.0\\) and less than \\(226.0\\)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "When Free Time First Payment equal 10 and a coin payment is made in presumed occupied, the parking session history displays 2 Virt Payment results in the parking session hisroty 1 for 1 minute, and another for 10 minutes.";
	    				strPivotalId = "1732524895";
	    				break;
		    		}
		    		strPattern = "The meter max time remaining presumed occupied (.*) - was not greater than (.*) and less than (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "Rejecting a grace period violation by Reset existing session with Payment is displaying a Virt Payment for 1 minute in the parking session history.";
	    				strPivotalId = "173279622";
	    				strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315564/stories/173279622";
	    				break;
		    		}
		    		strPattern = "The Meter True Up Time did not equal (.*) - Actual Time (.*)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("M1117_TU_CGPV1_W10_VTU10_CP1_VTU6_CP1_VTU2_CP1_VMT_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The True Up time is not being calculated correctly.  When there is 10 minutes of True Up time and the user make a coin payment for 4 minutes the time true up time remaining is 6 minutes.  When he makes a 2nd coin payment the True Up time goes to 0 when it's expected to be 2.";
		    				strPivotalId = "187727653";
		    				strPivotalPath = "https://www.pivotaltracker.com/n/projects/2315564/stories/187727653";
		    			}
		    			else
		    			{
			    			Reporter.log("<font color='Pink'>     "+strErrorMsg+"</font>");
			    			strErrorMsg = "The True up time is calculated incorrectly on Dual meter when parking in free and initial grace expires time expires.";
		    				strPivotalId = "172960925";
		    			}
	    				break;
		    		}
		    		strPattern = "The Actual Meter Time \\(0\\) was not equal to expected meter time \\(12\\)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
	    				strErrorMsg = "The meter displays Awaiting Payment  0:05 minute after mobile payment is made to unlock a approved violation.  Expected result was the meter would displays 12 minutes of time purchased.";
	    				strPivotalId = "174306633";
	    				break;
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTFP0_TU_RNP_MTIV_gt_MBNB_PS1_CGPV1_CP1_UL_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter Time is incorrect when make a True Up payment when Meter Increment Time > Minutes Before No Parking.";
		    				strPivotalId = "180626254";
		    			}
		    			else if(strTestCase.contains("FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Meter Max Time Remaining is being calculated incorrectly when rejecting a violation with a payment.";
		    				strPivotalId = "183974251";
		    			}
		    			else if(strTestCase.contains("TFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter remaining time is calculated incorrectly when rejecting a violation with payment";
		    				strPivotalId = "184059365";
		    			}
		    			else if(strTestCase.contains("FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_CCP1_VMT"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter remaining time is calculated incorrectly when rejecting a violation with payment";
		    				strPivotalId = "184057945";
		    			}
		    			else if(strTestCase.contains("FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_CCP1_VMT"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter max time remaining did not equal zero when making a coin payment for remaining time Rate to Free while Free time First payment 10 is enabled.";
		    				strPivotalId = "184543510";
		    			}
		    			else
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "When making the initial free to rate payment on a dual meter the free time isn't being included on the display.  It's only showing the meter increment time.";
		    				strPivotalId = "183839825";
		    			}
	    				break;
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTR_PS1_WFTE"))
		    			{
		    				Reporter.log(strErrorMsg);
//		    				strErrorMsg = "The meter time is calculating incorrectly when user parks in free then makes a payment in initial grace time.";
//							strPivotalId = "183866222";
		    				strErrorMsg = "The meter time is calculated incorrectly when user parks in free then make the initial payment in rate.  It looks like the initial payment is including the time between the park and free time to the total time allocated.";
							strPivotalId = "184559293";
		    				break;
		    			}
		    			else if(strTestCase.contains("FTFP10"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter time is calculated incorrectly when Free Time First Payment is 10 and a coin payment is made.";
		    				strPivotalId = "170429307";
		    				break;
		    			}
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("CGPV1_RV_RES_IP"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is calculated incorrectly when adding a coin payment after rejecting a violation with Reset Session and Include Payment are checked.";
			    			strPivotalId = "171441463";
			    			break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCoin";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if (strTestCase.contains("TFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP"))
		    			{
		    				Reporter.log(strErrorMsg);
			    			strPivotalId = "181302331";Reporter.log(strErrorMsg);
			    			strErrorMsg = "Rejecting a violation with included payment caused meter time to calculate incorrectly when adding additional payments.";
		  				}
		    			else if (strTestCase.contains("RV_RES_IP_wo_LP"))
			    		{
		    				Reporter.log(strErrorMsg);
			    			strPivotalId = "181543065";Reporter.log(strErrorMsg);
		  				}	
		    			else if(strTestCase.contains("FTFP10"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter time is calculated incorrectly when Free Time First Payment is 10 and a coin payment is made.";
		    				strPivotalId = "170429307";
		    				break;
		    			}
		    			else if(strTestCase.contains("FTR_MTIV_gt_RFT_PS1_CP1")|| strTestCase.contains("FTR_PS1_CP1"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter Remaining Time is calculated incorrectly when making a credit card payment in Free To Rate.";
		    				strPivotalId = "172317409";
		    				break;
		    			}
		    			else
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter max time remaining isn't calculating correctly when make a coin payment (Presumed Occupied)  Expected 225 actual 465";
			    			strPivotalId = "170873229";
			    			break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(PARKING_STATE_VEHICLE_LEFT\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("RV_RES"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "If a violation is rejected with \"Reset Existing Session\" checked the spot unexpectedly gets exited.";
		    				strPivotalId = "170463267";
		    				break;
		    			}
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTFP10"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter time is calculated incorrectly when Free Time First Payment is 10 and a coin payment is made.";
		    				strPivotalId = "170429307";
		    				break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("MM"))
		    			{
			    			Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Meter Time is incorrect when making a credit card payment to a space that is in Maintenance Mode Unenforced.";
		    				strPivotalId = "170187042";
		    				break;
		    			}
		    			if(strTestCase.contains("FTR_MTIV_gt_RFT_PS1_CCP1")||strTestCase.contains("FTR_PS1_CCP1"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter Remaining Time is calculated incorrectly when making a credit card payment in Free To Rate.";
		    				strPivotalId = "172317409";
		    				break;
		    			}
		    			else
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter time is displaying incorrectly when making a coin payment followed by a credit card payment";
		    				strPivotalId = "170237836";
		    				break;
		    			}
		    		}
		    		strPattern = "Meter Time did not decremented correctly after coin payment-expected (.*)-actual (.*)";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find())
		    		{
		    			if(strTestCase.contains("FTR_PS1_WFTE"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The meter time is calculating incorrectly when user parks in free then makes a payment in initial grace time. ";
							strPivotalId = "180904841";
		    			}
		    			else if(strTestCase.contains("FTFP10"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "Meter time is calculated incorrectly when Free Time First Payment is 10 and a coin payment is made.";
		    				strPivotalId = "170429307";
		    				break;
		    			}
		    		}
		    		
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("gt_FTR"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Available time remaining is calculated incorrectly (3hr 40 mins) when minutes before No Parking is 20 minutes.";
		    				strPivotalId = "166033546";
		    				break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(on_message_pass\\(\\)\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			if(strTestCase.contains("M1058B_FTFP10_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_PRT_ES1_VPSH_VICAE_VIAC"))
		    			{
		    				strErrorMsg = "After a credit card swipe the default time is 62 minute greater than expected when purchasing in free.";
			    			strPivotalId = "184654267";
		    			}
		    			else if(strTestCase.contains("CCP"))
		    			{
		    				strErrorMsg = "The python script that execute a credit card payment on dual meters is not longer working.";
			    			strPivotalId = "170953001";
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
			    			strErrorMsg = "Error making a credit card payment while in free to rate: void(): ignoring - no transaction currently instantiated";
			    			strPivotalId = "167874733";
		    			}
		    			else
		    			{
		    				strErrorMsg = "Unable to make a credit card payment after a coin payment on single meter when Free Time First Payment is enabled.";
			    			strPivotalId = "168174213";
		    			}
		    			break;
		    		}
		    		strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\):\\) did not appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("CCP"))
		    			{
		    				strAssociatedBug = "170953001";
		    				strErrorMsg = "The python script that execute a credit card payment on dual meters is not longer working.";
			    			strPivotalId = "170953001";
		    			}
		    		}
		    		strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\):\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			strErrorMsg = "Error making a credit card payment while in free to rate: void(): ignoring - no transaction currently instantiated";
		    			strPivotalId = "167874733";
		    			break;
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("MBNB_gt"))
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The Available time remaining is calculated incorrectly (3hr 40 mins) when minutes before No Parking is 20 minutes.";
		    				strPivotalId = "166033546";
		    				break;
		    			}
		    			else if(strTestCase.contains("FTR"))
		    			{
		    				String strRateBlockType = objDictionary.get("strRateBlockType");
		    				if(strRateBlockType == null)
		    				{
		    					String strGlobalPayActionsSpot1 = objDictionary.get("strGlobalPayActionsSpot1");if(strGlobalPayActionsSpot1.equals("")) {strGlobalPayActionsSpot1 ="NA,NA";}
			    		 		String strGlobalPayActionsSpot2 = objDictionary.get("strGlobalPayActionsSpot2");if(strGlobalPayActionsSpot2.equals("")) {strGlobalPayActionsSpot2 ="NA,NA";}
			    		 		String[] arrGlobalPayActions1 = strGlobalPayActionsSpot1.split(",");
			    				String[] arrGlobalPayActions2 = strGlobalPayActionsSpot2.split(",");
			    				System.out.println(arrGlobalPayActions1[0]);
			    				System.out.println(arrGlobalPayActions2[0]);
			    				if(arrGlobalPayActions1[0].contains("CCPRS")||arrGlobalPayActions2[0].contains("CCPRS"))
			    				{
			    					strAssociatedBug ="167520771";
				    				Reporter.log(strErrorMsg);
				    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
				    				strPivotalId = "167520771";
				    				break;
			    				}
		    				}
		    				else
		    				{
		    					if(strRateBlockType.equals("RateBlockToFreeToRate"))
		    					{
		    						strAssociatedBug ="167520771";
				    				Reporter.log(strErrorMsg);
				    				strErrorMsg = "The meters maxtime remaining is calculated incorrectly when making a credit card payment while in free to rate.";
				    				strPivotalId = "167520771";
				    				break;
		    					}
		    				}
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTR_FTFP0_CPRS_CCPRS"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "When purchasing in Free, the Maxtime available is decremented by the number of free minutes that elapse.";
							strPivotalId = "164964841";
							break;
		    			}
		    		}
		    		strPattern = "The (.*) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{
		    				
		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(on_message_pass\\(\\)\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
			    		strErrorMsg = "The meter time isn't being added to the meter after a successful credit card swipe when meter is Free to Rate.";
						strPivotalId = "168407497";
						break;
		    		}
		    		strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\)\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("MMNP_VMM_WUMV_VMM_CP1_VMM_ES1_VMM_RMM_VMM"))
		    			{
		    				Reporter.log(strErrorMsg);
			    			strErrorMsg = "If existing parking session get put in maintenance mode no parking, and the meter violates the user cannot unlock the space.";
							strPivotalId = "166276846";
							break;
		    			}
		    		}
		    		strPattern = "The meter max time remaining (.*) did not equal the expected time remaining (.*)-METER_InsertCardPayNoSpot";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FDOFF"))
		    			{
			    			Reporter.log(strErrorMsg);
			    			strErrorMsg = "The meter time is displaying incorrectly when free disconnect is false and the Meter Increment value is greater than the minutes before free.";
							strPivotalId = "159508312";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(SCREEN_MULTI_HOME\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV")||strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
			    			strErrorMsg = "Master - QT errors on yocto";
							strPivotalId = "164587935";
							break;
		    			}
		    			else if(strTestCase.contains("S1_CPRS_CPLS_CCPRTLS_ESNRS_SP2_CCPRTLS_ESNLS_CCPRTRS"))
		    			{
		    				strErrorMsg = "After entering a space number, the loading screen appears, and doesn't go away.  Looks like a loop exists in the logs.";
		    				strPivotalId = "164993695";
							break;
		    			}
		    			else
		    			{
		    				Reporter.log(strErrorMsg);
		    				strErrorMsg = "The SCREEN_MULTI_HOME doesn't always appear after entering the space on the key pad.";
							strPivotalId = "164993695";
							break;
		    			}
		    		}
		    		strPattern = "Failed: Value \\(SCREEN_MULTI_SELECT_SPACE\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			Reporter.log(strErrorMsg);
		    			strErrorMsg = "The SCREEN_MULTI_SELECT_SPACE doesn't always appear when click the Home Button";
						strPivotalId = "164997425";
						break;
		    		}
		    		strPattern = "The (.*) Meter Max Remaining Time did not equal zero-METER_InsertCardPurchaseRemainingTimeNoSpot";
			    	CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_"))
		    			{
		    				Reporter.log(strErrorMsg);
							strErrorMsg = "Global Pay - Max Time remaining is calculating incorrectly when purchasing time during rate to free";
							strPivotalId = "163460118";
			  				break;
		    			}
		    		}
					strPattern = "Failed: Value \\(do_rabbitmq_send_event\\(\\): success\\(\\):\\) didn't appear in the logs after (.*) Seconds on host (.*)-METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached";
		    		CompilePattern = Pattern.compile(strPattern);
		    		MatchPattern = CompilePattern.matcher(strErrorMsg);
		    		if(MatchPattern.find( ))
		    		{
		    			if(strTestCase.contains("FTNP_MBNB_gt_FTR_gt_MTIV")||strTestCase.contains("FTNP_MBNB_gt_MTIV_gt_FTR"))
		    			{
			    			Reporter.log(strErrorMsg);
							strErrorMsg = "The Meter time is calculated incorrectly when Minutes Before No Parking > Free time Minutes > Meter Increment Time";
							strPivotalId = "163460118";
							break;
		    			}
		    		}
	    }
		if(strErrorMsg.contains("No active spot!!"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "Occasionally when I add a coin payment, I get this ERROR in the logs \"No active spot!! spot None spot_name gp_spot_id SPOT_3\" and the payment does get added.";
			strPivotalId = "165235396";
		}
		else if(strErrorMsg.contains("previous global_pay transaction still in progress"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "Occasionally when I add a coin in to a remote space the Error:  \"ERROR    | ui.multi   | main_reactor | UISI: previous global_pay transaction still in progress, closing\"  appears in the meter log and the payment never gets added.";
			strPivotalId = "165270060";
		}
		else if(strErrorMsg.contains("Ignoring current_state UI_STATE_IDLE expected UI_STATE_CARD_TRANSACTION"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "Log Error: UISCT::_cardpay_timeout() Ignoring current_state UI_STATE_IDLE expected UI_STATE_CARD_TRANSACTION-METER";
			strPivotalId = "165361870";
		}
		else if(strErrorMsg.contains("add_payment(): reference_id None payment"))
		{
			Reporter.log(strErrorMsg);
			String lastAction = "";
			if(strExecutedGlobalPayAbbr != null)
			{
				String[] actions = strExecutedGlobalPayAbbr.split(",");
				lastAction = actions[actions.length-1];
			}
			if(strExecutedGlobalPayAbbr.equals("CPLS,CPRS,CCPLS,CCPRS"))
			{
				strErrorMsg = "RESPONSE doesn't include purchase_parking or id";
				strPivotalId = "166685908";
			}
			else if(lastAction.contains("CPRTRS"))
			{
				strErrorMsg = "After adding a 3rd coin, the meter screen unexpectedly  displayed the IDLE screen.   There was a Rabbit MQ disconnect before display_screen SCREEN_MULTI_IDLE.";
				strPivotalId = "166875952";
			}
			else
			{
				strErrorMsg = "After entering a coin payment on a remote spot, I'm not seeing a (\"result\":\"success\") row in the meter logs after 15 second of waiting.  The ERROR:  \"add_payment(): reference_id None payment\" Appears in the logs during the 15 second wait.";
				strPivotalId = "165365194";

			}
		}
		else if(strErrorMsg.contains("coin acceptor configured to reject and is accepting now"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "After entering a coin payment on a remote spot, I'm not seeing a (\"result\":\"success\") row in the meter logs after 15 second of waiting.  The ERROR:  \"confirm_coin_rejecting_status(): coin acceptor configured to reject and is accepting now\" Appears in the logs during the 15 second wait.";
			strPivotalId = "165366267";
		}
		else if(strErrorMsg.contains("unable to get id from payment_service"))
		{
			Reporter.log(strErrorMsg);
			strErrorMsg = "ERROR:  unable to get id from payment_service.  When making a Card remaining time purchase after an initial coin payment.";
			strPivotalId = "165515742";
		}
		else if(strErrorMsg.contains("not in self._valid_numbers"))
		{
			strErrorMsg = "Occasionally,  entering a remote spot doesn't display \"SCREEN_MULTI_SELECT_SPACE\" in the logs and remote spot doesn't appear on the screen, instead the logs displays \" is_valid_space_number: space 5592 not in self._valid_numbers";
			strPivotalId = "165604169";
		}
		else if(strErrorMsg.contains("expected (225.0)-actual (75)-METER_InsertCardPurchaseRemainingTimeNoSpot"))
		{
			strErrorMsg = "Meter in the dual setting is not calculating Max time correctly-R";
			strPivotalId = "185220812";
		}
		String strExecutedGlobalPayActions = objDictionary.get("strExecutedGlobalPayActions");
		if(strExecutedGlobalPayActions != null)
		{
			Reporter.log("******Global Pay Actions******");
			Reporter.log(strExecutedGlobalPayAbbr.replaceFirst("<br>", ""));
			Reporter.log(strExecutedGlobalPayActions);
			Reporter.log("******Global Pay Actions******");
		}
		if(!strPivotalId.equals(""))
  		{
			String strFirstLetter = String.valueOf(strTestCase.charAt(0));
			if(strFirstLetter.equals("G"))
			{
				//Global Pay test case are randomizes so if there is a error with a pivotal show it in Yellow.
				if(strAssociatedBug.contains(strPivotalId) && !strAssociatedBug.equals("")||strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
				{
					//Yellow Means Know Issue
	  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
	  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
	  				//RPSS: Remain Parked Short Session
	  				if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
	  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
	  			}
	  			else
	  			{
	  				//Red Means New Issue
	  				Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
	  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
	  				//RPSS: Remain Parked Short Session
	  			 	if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
	  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
	  			}
			}
			else
			{
				if(strAssociatedBug.contains(strPivotalId) && !strAssociatedBug.equals(""))
			  	{
	  				//Yellow Means Know Issue
	  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
	  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
	  				//RPSS: Remain Parked Short Session
	  				if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
	  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
	  			}
	  			else
	  			{
	  				//Red Means New Issue
	  				Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
	  				if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
	  				//RPSS: Remain Parked Short Session
	  			 	if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
	  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
	  			}
			}
  		}
  		else
  		{
  			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");
  			if(driver != null){clsCommonWeb.TakeWebScreenShot(objDictionary,driver);driver.quit();}
        	//RPSS: Remain Parked Short Session
  	      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked (15) seconds-Spot 1");
  	      	Assert.fail(strErrorMsg);
  		}
	}
	//**************************************************************************************************************************************************************************************

	public String METER_GetMeterSpotName(Map<String, String> objDictionary, String strSpotNumber)
	{
		String strDeviceId = objDictionary.get("strDeviceId");
		String[] arrMeterSpots = strDeviceId.split("-");
		String strMeterSpotName = "";
		if(strSpotNumber.equals("1")){strMeterSpotName = arrMeterSpots[0];}else{strMeterSpotName = arrMeterSpots[1];}
		return strMeterSpotName;
	}


	public void Pivotal155402903(Map<String, String> objDictionary, WebDriver driver, String strMaintenanceReason)
  	{

		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strBrowser = objDictionary.get("strBrowser");
  		String strRemotePath = objDictionary.get("strRemotePath");
  		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, getDriver());
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, getDriver());
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, getDriver());
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary,getDriver(), strPageName, "AutomationMeterGroup","Local");
  		//START MaintenanceMode Log Trace
  		clsMeter.METER_StartLogTrace2(objDictionary,driver,"MaintenanceMode");
  		clsCommonWeb.ClickLink(objDictionary, getDriver(), "Meter", "Spots", 1);
		//Populate Maintenance Mode
  		clsCommonWeb.PopulateAction(objDictionary, getDriver(), "Meter", "Populate Maintenance Values", "{L} Maintenance Reason|{CB} Maintenance All", strMaintenanceReason+"|Checked");
		//Click Set to maintenance mode
  		clsCommonWeb.ClickButton(objDictionary, getDriver(), "Meter", "Apply", 1,"Local");
  		try {Thread.sleep(5000);}catch (Exception e) {}
  		clsCommonWeb.PopulateAction(objDictionary, getDriver(), "Meter", "Populate Maintenance Values", "{CB} Maintenance All", "Checked");
  		clsCommonWeb.ClickButton(objDictionary, getDriver(), "Meter", "Clear All", 1,"Local");
  		getDriver().quit();
  	}

//	public ThreadLocal<RemoteWebDriver> SetDriverBrowser(@Optional String strBrowser, @Optional String strRemotePath)
//	{
//		ThreadLocal<RemoteWebDriver> threadDriver = null;
//		DesiredCapabilities capabilities1  = null;
//		if(strBrowser == null){strBrowser = "Chrome";}
//		if(strRemotePath == null){strRemotePath = "http://localhost:5555";}//http://192.168.163.132:5558/wd/hub
//		//if(strRemotePath == null){strRemotePath = "http://192.168.163.136:5558/";}//http://192.168.163.132:5558/wd/hub
//		switch (strBrowser)
//	    	{
//	    		case "Chrome":
//	    		    //Old
//	    			ChromeOptions chromeoptions = new ChromeOptions();
//	    			//chromeoptions.addArguments("--dns-prefetch-disable");
//	    			//chromeoptions.addArguments("--start-maximized");
//	    			capabilities1 = DesiredCapabilities.chrome();
//	    			System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe");
//	    			capabilities1.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
//	    			try {threadDriver.set(new RemoteWebDriver(new URL(strRemotePath+"/wd/hub"), capabilities1));}
//	    			catch (Exception e)
//	    			{Reporter.log("<font color='red'>The Selenium Hub and Node were not started</font>");Assert.fail("The Selenium Hub and Node were not started");}
//	    			break;
////	    		case "Safari":
////	    			threadDriver = new ThreadLocal<RemoteWebDriver>();
////	    			SafariOptions safarioptions = new SafariOptions();
////	    			//safarioptions.setUseCleanSession(true);
////	    			capabilities1 = DesiredCapabilities.safari();
////	    			capabilities1.setCapability(SafariOptions.CAPABILITY, safarioptions);
////	    			try {threadDriver.set(new RemoteWebDriver(new URL(strRemotePath+"/wd/hub"), capabilities1));}
////	    			catch (Exception e){e.printStackTrace();System.exit(1);}
////	    			break;
////	    		case "FireFox":
////	    		case "Firefox":
////	    			threadDriver = new ThreadLocal<RemoteWebDriver>();
////	    			capabilities1 = DesiredCapabilities.firefox();
////	    			try {threadDriver.set(new RemoteWebDriver(new URL(strRemotePath+"/wd/hub"), capabilities1));}
////	    			catch (Exception e){e.printStackTrace();System.exit(1);}
//				break;
//			default:
//				Reporter.log("<font color='red'>The Browser (" + strBrowser + ") had not been added</font>");Assert.fail("The Browser (" + strBrowser + ") had not been added");
//		}
//		return threadDriver;
//	}


	public ThreadLocal<RemoteWebDriver> setDriverBrowser(@Optional String strBrowser, @Optional String strRemotePath)
	{
	    ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<>();
	    if (strBrowser == null) {
	        strBrowser = "Chrome";
	    }
	    if (strRemotePath == null) {
	        strRemotePath = "http://localhost:5555";
	    }
	    switch (strBrowser) {
	        case "Chrome":
	            ChromeOptions chromeOptions = new ChromeOptions();
	            // chromeOptions.addArguments("--dns-prefetch-disable");
	            // chromeOptions.addArguments("--start-maximized");
	            try {
	                threadDriver.set(new RemoteWebDriver(new URL(strRemotePath + "/wd/hub"), chromeOptions));
	            } catch (Exception e) {
	                Reporter.log("<font color='red'>The Selenium Hub and Node were not started</font>");
	                Assert.fail("The Selenium Hub and Node were not started");
	            }
	            break;
	        default:
	            Reporter.log("<font color='red'>The Browser (" + strBrowser + ") had not been added</font>");
	            Assert.fail("The Browser (" + strBrowser + ") had not been added");
	    }
	    return threadDriver;
	}


	// ***************************************
	// Reset Number Of Space Back To Two Spots
	// ***************************************
	public void SENTRYMETER_GlobalPay_ResetNumberOfSpaceBackToTwoSpots(Map<String, String> objDictionary, Session sessionLocalMeter1, Session sessionRemoteMeter1,Session sessionRemoteMeter2)
	{
		Meter clsMeter = new Meter();
		//Resets Number of spaces back to 2 Spot
		String strNumberOfStopsLocal = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionLocalMeter1);
		String strNumberOfStopsRemote1 = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionRemoteMeter1);
		String strNumberOfStopsRemote2 = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionRemoteMeter2);
		String strReboot = "False";
		if(strNumberOfStopsLocal.equals("1"))
		{
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter1, "LocaL", "settings_set_setting_int.py SYS_NUMBER_OF_SPOTS 2");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter1, "LocaL", "sys_reboot.py");
	  		strReboot = "True";
		}
		if(strNumberOfStopsRemote1.equals("1"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionRemoteMeter1, "Remote1", "settings_set_setting_int.py SYS_NUMBER_OF_SPOTS 2");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionRemoteMeter1, "Remote1", "sys_reboot.py");
	  		strReboot = "True";
		}
		if(strNumberOfStopsRemote2.equals("1"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionRemoteMeter1, "Remote2", "settings_set_setting_int.py SYS_NUMBER_OF_SPOTS 2");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionRemoteMeter1, "Remote2", "sys_reboot.py");
	  		strReboot = "True";
		}
		if(strReboot.equals("True")) {clsMeter.METER_MeterWaitWithMessage(objDictionary,460, "Waiting for Meter To Reboot");}
	}

	//*****************************
	//Meter Connection
	//*****************************
	public Session METER_ReturnMeterSocket(Map<String, String> objDictionary,String strHostType)
    {
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		String strErrorMsg = objDictionary.get("strErrorMsg");
		if(strErrorMsg != null){UpdateErrorMessageWithPivotalData(objDictionary,null,null,strErrorMsg,"True");}
		String strHost = "";String strMeterUser = "";
		if(strHostType.equals("Local1")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		if(strHost != null)
		{
			if(strVirtualMeter == null){strVirtualMeter = "False";}
			if (strVirtualMeter.equals("False"))
			{
				JSch jsch = new JSch();
				int port=22;
				try
		    	{
					String strPassword = objDictionary.get("strUniquePassword");
		    		Session session = jsch.getSession(strMeterUser, strHost, port);
		    		session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Reporter.log(strHostType+" SessionId: "+session.toString().replace("com.jcraft.jsch.Session@", ""));
		    		return session;
			    }catch(Exception e)
				{
//			    	UpdateErrorMessageWithPivotalData(objDictionary,null,null,strMethodName+" :"+e,"True");
			    }
			}
		}
		else
		{
			Reporter.log(strMethodName+": The strHost name was null");
		}
		return null;
    }

	//*****************************
	//CONCIERGE
	//*****************************
	public void SENTRYMETER_ActiveConcierge(Map<String, String> objDictionary, String strTestCaseName, String strRole, String strLicensePlateNumber, String strState)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_StartLogTrace2(objDictionary, null, "ConciergeSession");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//Park With License Plate
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		//Wait For initial grace to expire
  		String strInitialGracePeriod = objDictionary.get("strInitialGracePeriod");
  		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60+7;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for initial grace time to expire-Spot1");
		String strMeterName = objDictionary.get("strMeterName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True"))
  		{
  			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);
  		}
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  	}
	public String METER_GetConciergeJson(Map<String, String> objDictionary, String strTestCaseName, String strValues)
	{
		Meter clsMeter = new Meter();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		File directory = new File(".");
		String strPath = "";
		try {strPath = directory.getCanonicalPath() +"/MeterLogs";}catch (IOException e){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null, e+"-"+strMethodName);}
		String strFullMeterLogsPath = strPath+"/"+strTestCaseName+".txt";
		File file = new File(strFullMeterLogsPath);
		try
		{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine())
		    {
		    	 	String line = scanner.nextLine();
		    	 	if(line.contains("random_code"))
		    	 	{
		    	 		return line.substring(line.indexOf("{"), line.length());
		    	 	}
		    	 	else if (line.contains("on_concierge_id"))
		    	 	{try{return line.substring(line.indexOf("session ") + 8, line.indexOf("concierge ID")-1);}catch(Exception e) {}}
		    }
		}
		catch(FileNotFoundException e)
		{ clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null, "The File ("+file+") did not exist in the MeterLogs");}
		//Failed When Current Status was De-enrolled
		clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null, "The Concierge Session id didn't exist in the Meter Logs-"+strMethodName);
		return "";
	}
	public static String InsertCharacterPeriodicallyWithinString(String text, String insert, int period)
	{
		StringBuilder builder = new StringBuilder(
	    text.length() + insert.length() * (text.length()/period)+1);

	    int index = 0;
	    String prefix = "";
	    while (index < text.length())
	    {
	        // Don't put the insert in the very first iteration.
	        // This is easier than appending it *after* each substring
	        builder.append(prefix);
	        prefix = insert;
	        builder.append(text.substring(index,
	            Math.min(index + period, text.length())));
	        index += period;
	    }
	    return builder.toString();
	}
//<<<<<<< HEAD
//	public void METER_ParkSpotWithLicensePlate(Map<String, String> objDictionary, String strLicensePlateNumber)
//	{
//		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
//		Meter clsMeter = new Meter();
//		String strHost = objDictionary.get("strHost");String strSpotNumber = objDictionary.get("strSpotNumber");
//    	String strVehicleParkType = objDictionary.get("strVehicleParkType");
//    	String strReservationTestCase = objDictionary.get("strReservationTestCase"); if(strReservationTestCase == null) {strReservationTestCase = "False";}
//    	String strConciergeTestCase = objDictionary.get("strConciergeTestCase"); if(strConciergeTestCase == null) {strConciergeTestCase = "False";}
//    	if(strReservationTestCase.contains("True")||strConciergeTestCase.contains("True"))
//    	{
//    		//Permit Tests Require Park and Leave
//    		strVehicleParkType = "park";
//    	}
//    	//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
//    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py "+strLicensePlateNumber);
//    	clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterVariableBegunEqualTrue} NA", 40,strSpotNumber,"Local");
//	}
//=======
//
//>>>>>>> branch 'master' of https://github.com/municipalparkingservices/TestAutomation
	public void METER_ParkSpotWithActiveConciergeLicensePlate(Map<String, String> objDictionary, String strLicensePlateNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		Meter clsMeter = new Meter();
		Database clsDatabase = new Database();
		String strHost = objDictionary.get("strHost");String strSpotNumber = objDictionary.get("strSpotNumber");
    	String strVehicleParkType = objDictionary.get("strVehicleParkType");
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
    	//GET CURRENT METER TIME
    	String strOriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,null,"1","Local","");
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py "+strLicensePlateNumber);
    	clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterVariableBegunEqualTrue} NA", 40,strSpotNumber,"Local");
    	long startTime = System.nanoTime();
    	clsMeter.METER_WaitForMeterPayment(objDictionary, strOriginalValidTimePurchase);
    	long stopTime = System.nanoTime();
		System.out.println("It took ("+TimeUnit.SECONDS.convert(stopTime - startTime, TimeUnit.NANOSECONDS)+") seconds for initial Concierge payment to display on the meter");
		Reporter.log("<font color='firebrick'>It took ("+TimeUnit.SECONDS.convert(stopTime - startTime, TimeUnit.NANOSECONDS)+") seconds for initial Concierge payment to display on the meter</font>");
		clsDatabase.WriteToActionTimeDatabase(objDictionary,"ConciergeInitialPayment", (int)TimeUnit.MILLISECONDS.convert(stopTime - startTime, TimeUnit.NANOSECONDS),"");
	}
	public void METER_WaitForMeterPayment(Map<String, String> objDictionary, String strOriginalValidTimePurchase)
	{
		//String strOriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,null,"1");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		//GET CURRENT METER TIME
		String CurrentValidTimePurchase = "0";
		int intCounter = 0;
		do
	  	{
	  		CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,null,"1","Local","");
	  		intCounter++;
	  		if(intCounter>30){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Meter Time did not increment correctly-"+strMethodName);}
	  	} while (strOriginalValidTimePurchase.equals(CurrentValidTimePurchase));
	}

	//Coin Payment - Remove it possible Use -> clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
	public void SENTRYMETER_CoinInitialPayment(Map<String, String> objDictionary, WebDriver driver,String strFreeTimeMinutes, String strFreeTimeFirstPayment, String strMeterTimeIncrementValue)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		//Dictionary Variables
  		String strHost = objDictionary.get("strHost");
  		String strSpotNumber = objDictionary.get("strSpotNumber");
  		//Copy Screen Shot Locally
  		clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, "BeforeCoinPayment");
  		//CP1: Coin Payment Spot 1
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_insert_coins_spot_"+strSpotNumber+".py");
  	 	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_apply_payment.py SPOT_1");
  		clsMeter.METER_WaitForMeterArrowsToDisappear(objDictionary,driver);
  		//Copy Screen Shot Locally
  		clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, "BeforeCoinPayment");
  		//strFirstPaymentTime
  		Calendar calendar = Calendar.getInstance();
  		String strFirstPaymentTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
  		Reporter.log("strFirstPaymentTime: "+strFirstPaymentTime);
		objDictionary.remove("strFirstPaymentTime");objDictionary.put("strFirstPaymentTime",strFirstPaymentTime);
		//VMT: Validate Meter Time
  		int intExpectedRemainingTimeMinutes = Integer.parseInt(strMeterTimeIncrementValue) + Integer.parseInt(strFreeTimeMinutes) + Integer.parseInt(strFreeTimeFirstPayment);
	  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, driver, intExpectedRemainingTimeMinutes,"1");
  	}
	public void SENTRYMETER_CoinPaymentBuyMoreTime(Map<String, String> objDictionary, WebDriver driver, String strInitialTimeMinutes, String strFreeTimeMinutes, String strFreeTimeFirstPayment)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		Meter clsMeter = new Meter();
		//Dictionary Variables
  		String strHost = objDictionary.get("strHost");
  		String strSpotNumber = objDictionary.get("strSpotNumber");
  		String strFirstPaymentTime = objDictionary.get("strFirstPaymentTime");
  		//Function Variables
  		String strMeterTimeIncrementValue = clsMeter.GetMeterTimeIncrementValue(objDictionary,driver);
	  	//CP1: Coin Payment Spot 1
  		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","Before");
		//CP1: Coin Payment Spot 1
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_insert_coins_spot_"+strSpotNumber+".py");
	    clsMeter.METER_WaitForMeterArrowsToDisappear(objDictionary,driver);
	    String CurrentValidTimePurchase = "0";
	    clsMeter.StoreDumpStackValuesInDictionary(objDictionary,driver,strSpotNumber,"Local","Before");
		int intCounter = 0;
		do
		{
			CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,"Local","After");
      		intCounter++;
      		if(intCounter>20)
      		{
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The (Local) Meter time did not increment correctly-"+strMethodName);
      		}
     	} while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase));
		//Calculate Expect Remaining Time
	  	int intUsedTime = 0;
	  	if(strFirstPaymentTime != null){intUsedTime = clsMeter.METER_CalculateUsedTime(objDictionary, driver, strFirstPaymentTime);}
	  	int intExpectedRemainingTimeMinutes = Integer.parseInt(strMeterTimeIncrementValue) + Integer.parseInt(strInitialTimeMinutes) + Integer.parseInt(strFreeTimeMinutes) + Integer.parseInt(strFreeTimeFirstPayment) - intUsedTime;
		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, driver, intExpectedRemainingTimeMinutes,"1");
	}
	//Credit Card Payment
	public int SENTRYMETER_CreditCardInitialPayment(Map<String, String> objDictionary, WebDriver driver, String strFreeTimeMinutes, String strCreditCardIncrementTime, String strFreeTimeFirstPayment, String strSpotNumber)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		//Classes
		Meter clsMeter = new Meter();
		//Insert Card
		clsMeter.METER_InsertCardPayNoSpot(objDictionary,null, "1", "Local");
		//VMT: Validate Meter Time
	  	int intCreditCardMinimumPurchaseMinutes = Integer.parseInt(strCreditCardIncrementTime) * 4;
	  	int intExpectedRemainingTimeMinutes = intCreditCardMinimumPurchaseMinutes + Integer.parseInt(strFreeTimeFirstPayment);
	  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, driver ,intExpectedRemainingTimeMinutes,"1");
	  	//strFirstPaymentTime
  		Calendar calendar = Calendar.getInstance();
  	  	String strFirstPaymentTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
  	  	Reporter.log("strFirstPaymentTime: "+strFirstPaymentTime);
		objDictionary.remove("strFirstPaymentTime");objDictionary.put("strFirstPaymentTime",strFirstPaymentTime);
	  	return intCreditCardMinimumPurchaseMinutes;
	}

	//**************************************************************************************************************
	//Credit Card Payment Buy More Time
	//**************************************************************************************************************
	public int SENTRYMETER_CreditCardPaymentBuyMoreTime(Map<String, String> objDictionary, WebDriver driver, Session sesssionMeter, String strCreditCardIncrementTime, String strFreeTimeMinutes, String strInitialTimeMinutes)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strFirstPaymentTime = objDictionary.get("strFirstPaymentTime");
		//Credit Card Payment
		clsMeter.METER_InsertCardPayNoSpot(objDictionary,null,sesssionMeter,null,"1", "Local");
		//VMT: Validate Meter Time
		int intUsedTime = 0;
		if(strFirstPaymentTime != null){intUsedTime = clsMeter.METER_CalculateUsedTime(objDictionary, driver, strFirstPaymentTime);}
		int intCreditCardMinimumPurchaseMinutes = Integer.parseInt(strCreditCardIncrementTime) * 4;
	  	int intExpectedRemainingTimeMinutes = intCreditCardMinimumPurchaseMinutes + Integer.parseInt(strInitialTimeMinutes) - intUsedTime;
	  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, driver, intExpectedRemainingTimeMinutes,"1");
		return intCreditCardMinimumPurchaseMinutes;
	}
	public int SENTRYMETER_CreditCardPaymentBuyMoreTime(Map<String, String> objDictionary, WebDriver driver, String strCreditCardIncrementTime, String strFreeTimeMinutes, String strInitialTimeMinutes)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strFirstPaymentTime = objDictionary.get("strFirstPaymentTime");
		//Credit Card Payment
		clsMeter.METER_InsertCardPayNoSpot(objDictionary,null, "1", "Local");
		//VMT: Validate Meter Time
		int intUsedTime = 0;
		if(strFirstPaymentTime != null){intUsedTime = clsMeter.METER_CalculateUsedTime(objDictionary, driver, strFirstPaymentTime);}
		int intCreditCardMinimumPurchaseMinutes = Integer.parseInt(strCreditCardIncrementTime) * 4;
	  	int intExpectedRemainingTimeMinutes = intCreditCardMinimumPurchaseMinutes + Integer.parseInt(strInitialTimeMinutes) - intUsedTime;
	  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, driver, intExpectedRemainingTimeMinutes,"1");
		return intCreditCardMinimumPurchaseMinutes;
	}



	//*******************************************************************************************************************************
  	//Reset Parking Session If Free Time First Payment Changes
  	//*******************************************************************************************************************************
  	public void SENTRYMETER_ResetParkingSessionIfFreeTimeFirstPaymentChanges(Map<String, String> objDictionary,Session sessionMeter,String strPriorFreeTimeFirstPayment,String strHostType)
  	{
  		Meter clsMeter = new Meter();
  		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
		if(strFreeTimeFirstPayment.equals("0") && strPriorFreeTimeFirstPayment.equals("10")||strFreeTimeFirstPayment.equals("10") && strPriorFreeTimeFirstPayment.equals("0"))
		{
			clsMeter.METER_ParkSpot(objDictionary,sessionMeter,"1",strHostType);
	      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, sessionMeter);
	      	if(strNumberOfSpots.equals("1")){clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary,null,sessionMeter,"1",strHostType);}
	      	else
	      	{
		      	clsMeter.METER_ParkSpot(objDictionary,sessionMeter,"2",strHostType);
		      	clsMeter.SENTRYMETER_ShortSessionWaitExitBothSpots(objDictionary,null,sessionMeter,strHostType);
	      	}

	    }
  	}
	//**************************************************************************************************************************************************************************************************************/
	//Update Meter Settings
	//**************************************************************************************************************************************************************************************************************/
	public void SENTRYMETER_UpdateMetertSetting(Map<String, String> objDictionary,Session sessionMeter,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		//Classes
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strInitialGracePeriod = objDictionary.get("strInitialGracePeriod");
  		String strMaximumDuration = objDictionary.get("strMaximumDuration");
  		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String strViolationGracePeriod = objDictionary.get("strViolationGracePeriod");
  		String strNoParkingGrace = objDictionary.get("strNoParkingGrace");
  		String strSetImageSendBeforeViolation = objDictionary.get("strSetImageSendBeforeViolation");
  		String strParkingShortSessionSec = objDictionary.get("strParkingShortSessionSec");
  		String strUnlockValue = objDictionary.get("strUnlockValue");
  		String strUnlockTime = objDictionary.get("strUnlockTime");
  		String strUnlockMax = objDictionary.get("strUnlockMax");
		String strFreeDisconnectValue = objDictionary.get("strFreeDisconnectValue");if(strFreeDisconnectValue == null){strFreeDisconnectValue = "On";}
		String strTrueUp = objDictionary.get("strTrueUp");if(strTrueUp == null){strTrueUp = "False";}
		String strDebugMask = "0"; objDictionary.put("strDebugMask", strDebugMask);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py SYS_DEBUG_MASK "+strDebugMask);
		//UMRSV: Update Meter Rate Set Values
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"set_initgrace.py "+intInitialGracePeriod);
		try {Thread.sleep(500);}catch (Exception e) {}
		int intNoParkingGrace = Integer.parseInt(strNoParkingGrace) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"set_no_parking_grace.py "+intNoParkingGrace);
		try {Thread.sleep(1000);}catch (Exception e) {}
		int intViolationGracePeriod = Integer.parseInt(strViolationGracePeriod) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_violation_grace.py "+intViolationGracePeriod);
		try {Thread.sleep(500);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_free_time_first_payment.py "+strFreeTimeFirstPayment);
		try {Thread.sleep(500);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_apply_payment_screen_timeout.py 5");
		try {Thread.sleep(500);}catch (Exception e) {}
		//Free Disconnect Value is True is Production
		if (strFreeDisconnectValue.equals("On")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_free_disconnect_on.py");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_free_disconnect_off.py");}
		if (strUnlockValue.equals("On")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_unlock_on.py");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_unlock_off.py");}
		try {Thread.sleep(500);}catch (Exception e) {}
		if (strTrueUp.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "settings_set_setting_true.py PARKING_TRUEUP_ENABLED");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "settings_set_setting_false.py PARKING_TRUEUP_ENABLED");}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_unlock_time.py "+strUnlockTime);
		try {Thread.sleep(500);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_unlock_max.py "+strUnlockMax);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_image_send_before_violation.py "+strSetImageSendBeforeViolation);
		try {Thread.sleep(500);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_maximum_duration.py "+strMaximumDuration);
		try {Thread.sleep(500);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_parking_short_session.py "+strParkingShortSessionSec);
		try {Thread.sleep(500);}catch (Exception e) {}
		if(strMeterIncrementTime.equals("5"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_parking_rate_minimum_time.py 20");}
		else if(strMeterIncrementTime.equals("15"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "settings_set_setting.py PARKING_RATE_DEFAULT_MINIMUM_TIME 60");}
		else if(strMeterIncrementTime.equals("30"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_parking_rate_minimum_time.py 120");}
		else if(strMeterIncrementTime.equals("45"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "set_parking_rate_minimum_time.py 180");}
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}
	public void SENTRYMETER_UpdateMetertSetting(Map<String, String> objDictionary,String strMaximumDuration, String strCoinTimePuchaseLimit, String strFreeTimeFirstPayment, String strCreditCardIncrementTime, String strInitialGracePeriod, String strViolationGracePeriod, String strHandicapInitialGracePeriod, String strHandicapViolationGrace, String strNoParkingGrace, String strSetImageSendBeforeViolation, String strParkingShortSessionSec, String strUnlockValue, String strUnlockTime, String strUnlockMax)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		//Classes
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strFreeDisconnectValue = objDictionary.get("strFreeDisconnectValue");if(strFreeDisconnectValue == null){strFreeDisconnectValue = "On";}
		String strTrueUp = objDictionary.get("strTrueUp");if(strTrueUp == null){strTrueUp = "False";}
  		//UMRSV: Update Meter Rate Set Values
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"set_initgrace.py "+intInitialGracePeriod);
		int intNoParkingGrace = Integer.parseInt(strNoParkingGrace) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_no_parking_grace.py "+intNoParkingGrace);
		int intViolationGracePeriod = Integer.parseInt(strViolationGracePeriod) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_violation_grace.py "+intViolationGracePeriod);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_free_time_first_payment.py "+strFreeTimeFirstPayment);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_apply_payment_screen_timeout.py 5");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_false.py PARKING_PAY_BY_PLATE");
		//Free Disconnect Value is True is Production
		if (strFreeDisconnectValue.equals("On")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_free_disconnect_on.py");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_free_disconnect_off.py");}
		if (strUnlockValue.equals("On"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_true.py PARKING_SHOW_UNLOCK_TIME");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_on.py");
		}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_off.py");}
		if (strTrueUp.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_true.py PARKING_TRUEUP_ENABLED");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_false.py PARKING_TRUEUP_ENABLED");}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_time.py "+strUnlockTime);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_max.py "+strUnlockMax);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_image_send_before_violation.py "+strSetImageSendBeforeViolation);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_maximum_duration.py "+strMaximumDuration);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_short_session.py "+strParkingShortSessionSec);
		if(strCreditCardIncrementTime.equals("5"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_rate_minimum_time.py 20");}
		else if(strCreditCardIncrementTime.equals("15"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting.py PARKING_RATE_DEFAULT_MINIMUM_TIME 60");}
		else if(strCreditCardIncrementTime.equals("30"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_rate_minimum_time.py 120");}
		else if(strCreditCardIncrementTime.equals("45"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_rate_minimum_time.py 180");}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_false.py SYS_OUT_OF_SERVICE");
		//REMOTE GLOBAL METER
		String strRemoteHost = objDictionary.get("strRemoteHost");
		if(strRemoteHost != null)
		{
			//UMRSV: Update Meter Rate Set Values
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost,"set_initgrace.py "+intInitialGracePeriod);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_no_parking_grace.py "+intNoParkingGrace);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_violation_grace.py "+intViolationGracePeriod);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_free_time_first_payment.py "+strFreeTimeFirstPayment);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_apply_payment_screen_timeout.py 5");
			//Free Disconnect Value is True is Production
			if (strFreeDisconnectValue.equals("On")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_free_disconnect_on.py");}
			else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_free_disconnect_off.py");}
			if (strUnlockValue.equals("On")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_unlock_on.py");}
			else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_unlock_off.py");}
			if (strTrueUp.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting.py PARKING_TRUEUP_ENABLED true");}
			else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting.py PARKING_TRUEUP_ENABLED false");}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_unlock_time.py "+strUnlockTime);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_unlock_max.py "+strUnlockMax);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_image_send_before_violation.py "+strSetImageSendBeforeViolation);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_maximum_duration.py "+strMaximumDuration);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_parking_short_session.py "+strParkingShortSessionSec);
			if(strCreditCardIncrementTime.equals("5"))
			{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_parking_rate_minimum_time.py 20");}
			else if(strCreditCardIncrementTime.equals("15"))
			{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_parking_rate_minimum_time.py 60");}
			else if(strCreditCardIncrementTime.equals("30"))
			{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_parking_rate_minimum_time.py 120");}
			else if(strCreditCardIncrementTime.equals("45"))
			{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strRemoteHost, "set_parking_rate_minimum_time.py 180");}
		}
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}
	public void SENTRYMETER_UpdateKioskSetting(Map<String, String> objDictionary,String strMaximumDuration, String strCoinTimePuchaseLimit, String strFreeTimeFirstPayment, String strCreditCardIncrementTime, String strInitialGracePeriod, String strViolationGracePeriod, String strHandicapInitialGracePeriod, String strHandicapViolationGrace, String strNoParkingGrace, String strSetImageSendBeforeViolation, String strParkingShortSessionSec, String strUnlockValue, String strUnlockTime, String strUnlockMax)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		//Classes
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strFreeDisconnectValue = objDictionary.get("strFreeDisconnectValue");if(strFreeDisconnectValue == null){strFreeDisconnectValue = "On";}
		String strTrueUp = objDictionary.get("strTrueUp");if(strTrueUp == null){strTrueUp = "False";}
  		//UMRSV: Update Meter Rate Set Values
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"set_initgrace.py "+intInitialGracePeriod);
		int intNoParkingGrace = Integer.parseInt(strNoParkingGrace) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_no_parking_grace.py "+intNoParkingGrace);
		int intViolationGracePeriod = Integer.parseInt(strViolationGracePeriod) * 60;
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_violation_grace.py "+intViolationGracePeriod);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_free_time_first_payment.py "+strFreeTimeFirstPayment);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_apply_payment_screen_timeout.py 5");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_false.py PARKING_PAY_BY_PLATE");
		//Free Disconnect Value is True is Production
		if (strFreeDisconnectValue.equals("On")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_free_disconnect_on.py");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_free_disconnect_off.py");}
		if (strUnlockValue.equals("On"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_true.py PARKING_SHOW_UNLOCK_TIME");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_on.py");
		}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_off.py");}
		if (strTrueUp.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_true.py PARKING_TRUEUP_ENABLED");}
		else{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_false.py PARKING_TRUEUP_ENABLED");}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_time.py "+strUnlockTime);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_unlock_max.py "+strUnlockMax);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_image_send_before_violation.py "+strSetImageSendBeforeViolation);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_maximum_duration.py "+strMaximumDuration);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_short_session.py "+strParkingShortSessionSec);
		if(strCreditCardIncrementTime.equals("5"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_rate_minimum_time.py 20");}
		else if(strCreditCardIncrementTime.equals("15"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting.py PARKING_RATE_DEFAULT_MINIMUM_TIME 60");}
		else if(strCreditCardIncrementTime.equals("30"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_rate_minimum_time.py 120");}
		else if(strCreditCardIncrementTime.equals("45"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_parking_rate_minimum_time.py 180");}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "settings_set_setting_false.py SYS_OUT_OF_SERVICE");
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}
	//**************************************************************************************************************************************************************************************************************/
	//Add Rate Blocks To Automation Results
	//**************************************************************************************************************************************************************************************************************/
	public void SENTRYMETER_AddRateBlocksToAutomationResults(Map<String, String> objDictionary,Session sessionMeter,String strHostType)
	{
		String strHost = "";if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}else{strHost = objDictionary.get("strRemoteHost");}
		try
	    {
			Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        Reporter.log("***********Current Meter Rate Blocks*************"+strHost);
	        for (String line : lines) {
	        	if (line.charAt(0) != '{') continue;
	        	Reporter.log(line.toString());
	        	line.toString();
	        }
	        Reporter.log("*************************************************");
	        channel.disconnect();
    	}catch(Exception e){}
    }
	public void SENTRYMETER_AddRateBlocksToAutomationResults(Map<String, String> objDictionary, String strHostType)
	{
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else{strHost = objDictionary.get("strRemoteHost");}
		JSch jsch = new JSch();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        Reporter.log("***********Current Meter Rate Blocks*************"+strHost);
	        for (String line : lines) {
	        	if (line.charAt(0) != '{') continue;
	        	Reporter.log(line.toString());
	        	line.toString();
	        }
	        Reporter.log("*************************************************");
	        channel.disconnect();
	        session.disconnect();
    		}catch(Exception e){}
    }
	//**************************************************************************************************************************************************************************************************************/

	//**************************************************************************************************************************************************************************************************************/
	//Get Curren tRate Block Id
	//**************************************************************************************************************************************************************************************************************/
	public String SENTRYMETER_GetCurrentRateBlockId(Map<String, String> objDictionary,Session sessionMeter)
	{
		String data = "";
		String blockId = "";
	    try
	    {
	    	 Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines) {
	        	if (line.charAt(0) != '{') continue;
        		data = data + line.toString();
        		if(data.contains("q")) {break;}
    		}
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(data.replace("q", ""));
	        blockId = jsonObject.get("block_id").toString();
	        channel.disconnect();
    	}catch(Exception e)
	    {}
	    return blockId;
    }
	public String SENTRYMETER_GetCurrentRateBlockId(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
		String data = "";
		String blockId = "";
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines) {
	        	if (line.charAt(0) != '{') continue;
        		data = data + line.toString();
        		if(data.contains("q")) {break;}
    		}
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(data.replace("q", ""));
	        blockId = jsonObject.get("block_id").toString();
	        channel.disconnect();
	        session.disconnect();
    	}catch(Exception e)
	    {}
	    return blockId;
    }

	//**************************************************************************************************************************************************************************************************************/

	//**************************************************************************************************************************************************************************************************************/
	//Store Dump Stack Values In Dictionary
	//**************************************************************************************************************************************************************************************************************/
	//Store Dump Stack as Variables
	public void StoreDumpStackValuesInDictionary(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber,String strHostType, String strBeforeOrAfterPayment)
	{
		Stopwatch timer = Stopwatch.createStarted();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
				if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//strValidTimePurchased
	        		String strValidTimePurchased = line.substring(line.indexOf("ValidTimePurchased,")+19, line.indexOf("|ValidTimeRemaining"));
	        		objDictionary.put("strValidTimePurchased", strValidTimePurchased);
	        		Reporter.log("The meter purchase amount equaled ("+strValidTimePurchased+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strMaxRemaining
	        		String strMaxRemaining = line.substring(line.indexOf("MaxRemaining,")+13, line.indexOf("|ValidTimePurchased"));
	        		if(strMaxRemaining.equals("0.0")) {strMaxRemaining = "0";}
	        		objDictionary.put("strMaxRemaining", strMaxRemaining);
	        		Reporter.log("The meter max remaining amount equaled ("+strMaxRemaining+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strMeterFreeValue
	        		String strMeterFreeValue = line.substring(line.indexOf("Free,")+5, line.indexOf("|No"));
	        		objDictionary.put("strMeterFreeValue", strMeterFreeValue);
	        		Reporter.log("The meter free value equaled ("+strMeterFreeValue+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strBegunVariable
	        		String strBegunVariable = line.substring(line.indexOf("Begun,")+6, line.indexOf("|ParkTime"));
	        		objDictionary.put("strBegunVariable", strBegunVariable);
	        		Reporter.log("The meter begin value equaled ("+strBegunVariable+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		String strParkTime = line.substring(line.indexOf("ParkTime,")+9, line.indexOf("|ExitTime"));
	        		if(!strParkTime.equals("None"))
	        		{
		        		strParkTime = strParkTime.replace("-06:00","-0600").replace("-05:00","-0500");
		    	  		DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
		    	  		utcFormat.setTimeZone(TimeZone.getTimeZone("CST"));
		    	  		Date date = utcFormat.parse(strParkTime);
		    	  		DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
		    	  		pstFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		    	  		System.out.println(pstFormat.format(date));
		    	  		String strCovertedParkTime = pstFormat.format(date);
		    	  		System.out.println(strCovertedParkTime);
		        		objDictionary.put("strParkTime", strCovertedParkTime);
		        		Reporter.log("The meter Park Time equaled ("+strParkTime+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		}
	        		//strViolation
	        		String strViolation = line.substring(line.indexOf("Violation,")+10, line.indexOf("|Unlocked"));
	        		objDictionary.put("strViolation", strViolation);
	        		Reporter.log("The meter violation value equaled ("+strViolation+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strCurrentMaintenanceMode
	        		String strMaintenanceModeEnabled = line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
	        		objDictionary.put("strMaintenanceModeEnabled", strMaintenanceModeEnabled);
	        		Reporter.log("The meter maintenance mode value equaled ("+strMaintenanceModeEnabled+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strValidTimeRemainingSec
	        		String strValidTimeRemainingSec = line.substring(line.indexOf("ValidTimeRemaining,")+19, line.indexOf("|Violation"));
	        		objDictionary.put("strValidTimeRemainingSec", strValidTimeRemainingSec);
	        		Reporter.log("The meter ValidTimeRemainingSec value equaled ("+strValidTimeRemainingSec+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		String strTrueUpTime = line.substring(line.indexOf("Trup,")+5, line.indexOf("|TrupT"));
	        		objDictionary.put("strTrueUpTime", strTrueUpTime);
	        		Reporter.log("The meter strTrueUpTime value equaled ("+strTrueUpTime+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		if(strMaintenanceModeEnabled.equals("True"))
	        		{
	        			String strMtFreeParking = line.substring(line.indexOf("MtFree,")+7, line.indexOf("|MtNo"));
	        			if(strMtFreeParking.equals("False"))
	        			{
	        				String strMtNoParking = line.substring(line.indexOf("MtNo,")+5, line.indexOf("|MtUn"));
	        				if(strMtNoParking.equals("False"))
		        			{
	        					String strMtUnenforcedParking = line.substring(line.indexOf("MtUn,")+5, line.indexOf("|MtPark"));
	        					if(strMtUnenforcedParking.equals("False"))
			        			{
	        						String strMtParking = line.substring(line.indexOf("MtPark,")+7, line.indexOf("|SrateS"));
	        						if(strMtParking.equals("False")) {objDictionary.put("strMaintenanceMode", "");}
	        						else{objDictionary.put("strMaintenanceMode", "Parking");}
	        					}else{objDictionary.put("strMaintenanceMode", "UnenforcedParking");}
	        				}else{objDictionary.put("strMaintenanceMode", "NoParking");}
	        			}else{objDictionary.put("strMaintenanceMode", "FreeParking");}
	        		}
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
		Reporter.log("<font color='#5533ff'>Method ("+strMethodName+") took: " + timer.stop()+"</font>");
	}
	public void StoreDumpStackValuesInDictionary(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber,String strHostType, String strBeforeOrAfterPayment)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost"+strSpotNumber);strMeterUser = objDictionary.get("strRemoteUser"+strSpotNumber);}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
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
				if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//strValidTimePurchased
	        		String strValidTimePurchased = line.substring(line.indexOf("ValidTimePurchased,")+19, line.indexOf("|ValidTimeRemaining"));
	        		objDictionary.put("strValidTimePurchased", strValidTimePurchased);
	        		Reporter.log("The meter purchase amount equaled ("+strValidTimePurchased+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strValidTimeRemaining
	        		String strValidTimeRemaining = line.substring(line.indexOf("ValidTimeRemaining,")+19, line.indexOf("|Violation"));
	        		objDictionary.put("strValidTimeRemaining", strValidTimeRemaining);
	        		Reporter.log("The meter Valid Time Remining equaled ("+strValidTimeRemaining+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strMaxRemaining
	        		String strMaxRemaining = line.substring(line.indexOf("MaxRemaining,")+13, line.indexOf("|ValidTimePurchased"));
	        		objDictionary.put("strMaxRemaining", strMaxRemaining);
	        		Reporter.log("The meter max remaining amount equaled ("+strMaxRemaining+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strMeterFreeValue
	        		String strMeterFreeValue = line.substring(line.indexOf("Free,")+5, line.indexOf("|No"));
	        		objDictionary.put("strMeterFreeValue", strMeterFreeValue);
	        		Reporter.log("The meter free value equaled ("+strMeterFreeValue+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strMeterNoParkingValue
	        		String strMeterNoParkingValue = line.substring(line.indexOf("No,")+3, line.indexOf("|Begun"));
	        		objDictionary.put("strMeterNoParkingValue", strMeterNoParkingValue);
	        		Reporter.log("The meter no parking value equaled ("+strMeterNoParkingValue+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strBegunVariable
	        		String strBegunVariable = line.substring(line.indexOf("Begun,")+6, line.indexOf("|ParkTime"));
	        		objDictionary.put("strBegunVariable", strBegunVariable);
	        		Reporter.log("The meter begin value equaled ("+strBegunVariable+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strViolation
	        		String strViolation = line.substring(line.indexOf("Violation,")+10, line.indexOf("|Unlocked"));
	        		objDictionary.put("strViolation", strViolation);
	        		Reporter.log("The meter violation value equaled ("+strViolation+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strTrueUp
	        		String strTrueUpTime = line.substring(line.indexOf("Trup,")+5, line.indexOf("|TrupT"));
	        		objDictionary.put("strTrueUpTime", strTrueUpTime);
	        		Reporter.log("The meter TrueUpTime value equaled ("+strTrueUpTime+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		//strCurrentMaintenanceMode
	        		String strMaintenanceModeEnabled = line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
	        		objDictionary.put("strMaintenanceModeEnabled", strMaintenanceModeEnabled);
	        		Reporter.log("The meter maintenance mode value equaled ("+strMeterFreeValue+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
	        		if(strMaintenanceModeEnabled.equals("True"))
	        		{
	        			String strMtFreeParking = line.substring(line.indexOf("MtFree,")+7, line.indexOf("|MtNo"));
	        			if(strMtFreeParking.equals("False"))
	        			{
	        				String strMtNoParking = line.substring(line.indexOf("MtNo,")+5, line.indexOf("|MtUn"));
	        				if(strMtNoParking.equals("False"))
		        			{
	        					String strMtUnenforcedParking = line.substring(line.indexOf("MtUn,")+5, line.indexOf("|MtPark"));
	        					if(strMtUnenforcedParking.equals("False"))
			        			{
	        						String strMtParking = line.substring(line.indexOf("MtPark,")+7, line.indexOf("|SrateS"));
	        						if(strMtParking.equals("False")) {objDictionary.put("strMaintenanceMode", "");}
	        						else{objDictionary.put("strMaintenanceMode", "Parking");}
	        					}else{objDictionary.put("strMaintenanceMode", "UnenforcedParking");}
	        				}else{objDictionary.put("strMaintenanceMode", "NoParking");}
	        			}else{objDictionary.put("strMaintenanceMode", "FreeParking");}
	        		}
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
    }
	public void DumpStackMeterParkTimeValue(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType) {
	    JSch jsch = new JSch();
	    String strHost;
	    String strMeterUser;
	    String strMeterParkTimeValue = null;
	    String scriptCommand = "testauto_dump_sessions.py";

	    if (strHostType.equals("Local")) {
	        strHost = objDictionary.get("strHost");
	        strMeterUser = objDictionary.get("strMeterUser");
	    } else {
	        strHost = objDictionary.get("strRemoteHost" + strSpotNumber);
	        strMeterUser = objDictionary.get("strRemoteUser" + strSpotNumber);
	    }

	    String strPassword = objDictionary.get("strUniquePassword");
	    String host = strHost;
	    boolean strPythonScriptExisted = false;
	    int port = 22;

	    Session session = null;
	    ChannelExec channel = null;
	    InputStream in = null;

	    try {
	        session = jsch.getSession(strMeterUser, host, port);
	        session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();

	        channel = (ChannelExec) session.openChannel("exec");
	        // Command to check if the Python script exists and then run it
	        channel.setCommand("if [ -f /usr/local/bin/" + scriptCommand + " ]; then /usr/local/bin/" + scriptCommand + "; else echo 'ScriptNotFound'; fi");
	        channel.setInputStream(null);
	        channel.setErrStream(System.err);

	        in = channel.getInputStream();
	        channel.connect();

	        // Get ParkTime Value
	        StringBuilder s = new StringBuilder();
	        int c;
	        while ((c = in.read()) != -1) {
	            s.append((char) c);
	        }

	        String[] lines = s.toString().split("\n");
	        System.out.println(Arrays.toString(lines));

	        for (String line : lines) {
	            if (line.contains("ScriptNotFound")) {
	                strPythonScriptExisted = false;
	                break;
	            }
	            if (line.contains("SPOT_" + strSpotNumber + "|")) {
	                // Extract the ParkTime value
	                String[] fields = line.split("\\|");
	                for (String field : fields) {
	                    if (field.startsWith("ParkTime,")) {
	                        strMeterParkTimeValue = field.split(",")[1];
	                        // Remove offset from ParkTime value
	                        if (strMeterParkTimeValue.contains("+")) {
	                            String[] parts = strMeterParkTimeValue.split("\\+");
	                            // Further split the date-time part to extract the correct value
	                            strMeterParkTimeValue = parts[0];
	                            
	                        } else if (strMeterParkTimeValue.contains("-")) {
	                            String[] parts = strMeterParkTimeValue.split("-");
	                            // Join the first three parts to get the date-time string
	                            strMeterParkTimeValue = parts[0] + "-" + parts[1] + "-" + parts[2].split(" ")[0] + " " + parts[2].split(" ")[1];

	                        }
	                        break;
	                    }
	                }
	                strPythonScriptExisted = true;
	                break;
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        try {
	            if (in != null) {
	                in.close();
	            }
	            if (channel != null) {
	                channel.disconnect();
	            }
	            if (session != null) {
	                session.disconnect();
	            }
	        } catch (IOException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }

	    if (!strPythonScriptExisted) {
	        UpdateErrorMessageWithPivotalData(objDictionary, driver, "The python script (" + scriptCommand + ") didn't exist on the meter-ssh into the meter");
	    }
	    objDictionary.put("strMeterParkTimeValue", strMeterParkTimeValue);
	}
	public void DumpStackMeterExitTimeValue(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType) {
	    JSch jsch = new JSch();
	    String strHost;
	    String strMeterUser;
	    String strMeterExitTimeValue = null;
	    String scriptCommand = "testauto_dump_sessions.py";

	    if (strHostType.equals("Local")) {
	        strHost = objDictionary.get("strHost");
	        strMeterUser = objDictionary.get("strMeterUser");
	    } else {
	        strHost = objDictionary.get("strRemoteHost" + strSpotNumber);
	        strMeterUser = objDictionary.get("strRemoteUser" + strSpotNumber);
	    }

	    String strPassword = objDictionary.get("strUniquePassword");
	    String host = strHost;
	    boolean strPythonScriptExisted = false;
	    int port = 22;

	    Session session = null;
	    ChannelExec channel = null;
	    InputStream in = null;

	    try {
	        session = jsch.getSession(strMeterUser, host, port);
	        session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();

	        channel = (ChannelExec) session.openChannel("exec");
	        // Command to check if the Python script exists and then run it
	        channel.setCommand("if [ -f /usr/local/bin/"+scriptCommand+" ]; then /usr/local/bin/"+scriptCommand+"; else echo 'ScriptNotFound'; fi");
	        channel.setInputStream(null);
	        channel.setErrStream(System.err);

	        in = channel.getInputStream();
	        channel.connect();

	        // Get ExitTime Value
	        StringBuilder s = new StringBuilder();
	        int c;
	        while ((c = in.read()) != -1) {
	            s.append((char) c);
	        }

	        String[] lines = s.toString().split("\n");
	        System.out.println(Arrays.toString(lines));

	        for (String line : lines) {
	            if (line.contains("ScriptNotFound")) {
	                strPythonScriptExisted = false;
	                break;
	            }
	            if (line.contains("SPOT_" + strSpotNumber + "|")) {
	                // Extract the ExitTime value
	                String[] fields = line.split("\\|");
	                for (String field : fields) {
	                    if (field.startsWith("ExitTime,")) {
	                        strMeterExitTimeValue = field.split(",")[1];
	    	        		Reporter.log("The vehicle park time equaled ("+strMeterExitTimeValue+") "+" on spot ("+strSpotNumber+")-UDT Time");
	                        break;
	                    }
	                }
	                strPythonScriptExisted = true;
	                break;
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        try {
	            if (in != null) {
	                in.close();
	            }
	            if (channel != null) {
	                channel.disconnect();
	            }
	            if (session != null) {
	                session.disconnect();
	            }
	        } catch (IOException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }

	    if (!strPythonScriptExisted) {
	        UpdateErrorMessageWithPivotalData(objDictionary, driver, "The python script ("+scriptCommand+") didn't exist on the meter-ssh into the meter");
	    }

	    objDictionary.put("strMeterExitTimeValue", strMeterExitTimeValue);
	}
		
	//**************************************************************************************************************************************************************************************************************/

	public void SENTRYMETER_StoreParkingSessionTime(Map<String, String> objDictionary)
	{
		String strMobileDeviceType = objDictionary.get("strMobileDeviceType");
		if(strMobileDeviceType == null){strMobileDeviceType = "";}
		//Store Parked At Time HHmmss
		Calendar calendar = Calendar.getInstance();
		String strParkedAtTime_HHmmss = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		Reporter.log("strParkedAtTime_HHmmss: "+strParkedAtTime_HHmmss);
		//Used for Android
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
  		String strParkedAtTime_MMMddhhmma = sdf.format(Date.from(Instant.now().truncatedTo(ChronoUnit.MINUTES)));
  		objDictionary.put("strParkedAtTime_MMMddhhmma", strParkedAtTime_MMMddhhmma);
		//Not Sure Where This is Used
		objDictionary.remove("strParkedAtTime_HHmmss");objDictionary.put("strParkedAtTime_HHmmss", strParkedAtTime_HHmmss);
		if(strMobileDeviceType.equals("IOS"))
		{
			//Minus One
			Calendar calendar0 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			calendar0.add(Calendar.SECOND, -60);
			String strParkingSessionsParkedAtTimeMinusOne = "Today at " +new SimpleDateFormat("h:mm a").format(calendar0.getTime());
			objDictionary.remove("strParkingSessionsParkedVAtTimeMinusOne");objDictionary.put("strParkingSessionsParkedAtTimeMinusOne", strParkingSessionsParkedAtTimeMinusOne);
			Reporter.log("strParkingSessionsParkedAtTimeMinusOne: "+strParkingSessionsParkedAtTimeMinusOne+"");
			//Actual
			Calendar calendar1 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			calendar1.add(Calendar.SECOND, 0);
			String strParkingSessionsParkedAtTime = "Today at " +new SimpleDateFormat("h:mm a").format(calendar1.getTime());
			objDictionary.remove("strParkingSessionsParkedAtTime");objDictionary.put("strParkingSessionsParkedAtTime", strParkingSessionsParkedAtTime);
			Reporter.log("strParkingSessionsParkedAtTime: "+strParkingSessionsParkedAtTime);
			//Plus One
			Calendar calendar2 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			calendar2.add(Calendar.SECOND, 60);
			String strParkingSessionsParkedAtTimePlusOne = "Today at " +new SimpleDateFormat("h:mm a").format(calendar2.getTime());
			objDictionary.remove("strParkingSessionsParkedAtTimePlusOne");objDictionary.put("strParkingSessionsParkedAtTimePlusOne", strParkingSessionsParkedAtTimePlusOne);
			Reporter.log("strParkingSessionsParkedAtTimePlusOne: "+strParkingSessionsParkedAtTimePlusOne);
		}
		else if(strMobileDeviceType.equals("ANDROID"))
		{
			//Minus Two
			Calendar calendar0 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			calendar0.add(Calendar.SECOND, -120);
			//String strParkingSessionsParkedAtTimeMinusTwo = new SimpleDateFormat("yyyy-MM-dd").format(calendar1.getTime()) + " at " +new SimpleDateFormat("hh:mm a").format(calendar1.getTime());
			String strParkingSessionsParkedAtTimeMinusTwo = "Today at " +new SimpleDateFormat("h:mm a").format(calendar0.getTime());
//			String strParkingSessionsParkedAtTimeMinusTwo = "Parked at : " +new SimpleDateFormat("yyyy-MM-dd 'at' h:mm a").format(calendar0.getTime());
			objDictionary.remove("strParkingSessionsParkedAtTimeMinusTwo");objDictionary.put("strParkingSessionsParkedAtTimeMinusTwo", strParkingSessionsParkedAtTimeMinusTwo);
			Reporter.log("strParkingSessionsParkedAtTimeMinusTwo: "+strParkingSessionsParkedAtTimeMinusTwo);
			//Minus One
			Calendar calendar1 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			calendar1.add(Calendar.SECOND, -60);
			//String strParkingSessionsParkedAtTimeMinusOne = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()) + " at " +new SimpleDateFormat("hh:mm a").format(calendar.getTime());
			String strParkingSessionsParkedAtTimeMinusOne = "Today at " +new SimpleDateFormat("h:mm a").format(calendar1.getTime());
//			String strParkingSessionsParkedAtTimeMinusOne = "Parked at : " +new SimpleDateFormat("yyyy-MM-dd 'at' h:mm a").format(calendar1.getTime());
			objDictionary.remove("strParkingSessionsParkedAtTimeMinusOne");objDictionary.put("strParkingSessionsParkedAtTimeMinusOne", strParkingSessionsParkedAtTimeMinusOne);
			Reporter.log("strParkingSessionsParkedAtTimeMinusOne: "+strParkingSessionsParkedAtTimeMinusOne);
			//Actual
			//String strParkingSessionsParkedAtTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " at " +new SimpleDateFormat("hh:mm a").format(new Date());
			Calendar calendar2 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			String strParkingSessionsParkedAtTime = "Today at " +new SimpleDateFormat("h:mm a").format(calendar2.getTime());
//			String strParkingSessionsParkedAtTime = "Parked at : " +new SimpleDateFormat("yyyy-MM-dd 'at' h:mm a").format(calendar2.getTime());
			objDictionary.remove("strParkingSessionsParkedAtTime");objDictionary.put("strParkingSessionsParkedAtTime", strParkingSessionsParkedAtTime);
			Reporter.log("strParkingSessionsParkedAtTime: "+strParkingSessionsParkedAtTime);
			//Plus One
			Calendar calendar3 = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			calendar3.add(Calendar.SECOND, 60);
			//String strParkingSessionsParkedAtTimePlusOne = new SimpleDateFormat("yyyy-MM-dd").format(calendar3.getTime()) + " at " +new SimpleDateFormat("hh:mm a").format(calendar2.getTime());
			String strParkingSessionsParkedAtTimePlusOne = "Today at " +new SimpleDateFormat("h:mm a").format(calendar3.getTime());
//			String strParkingSessionsParkedAtTimePlusOne = "Parked at : " +new SimpleDateFormat("yyyy-MM-dd 'at' h:mm a").format(calendar3.getTime());
			objDictionary.remove("strParkingSessionsParkedAtTimePlusOne");objDictionary.put("strParkingSessionsParkedAtTimePlusOne", strParkingSessionsParkedAtTimePlusOne);
			Reporter.log("strParkingSessionsParkedAtTimePlusOne: "+strParkingSessionsParkedAtTimePlusOne);
		}
	}

	//************************************************************************************************************************************************************************
  	//Click Home Button
  	//************************************************************************************************************************************************************************
  	public void SENTRYMETER_GlobalPay_ClickHomeButton(Map<String, String> objDictionary,Session sessionMeter,String strHostType)
  	{
  		Meter clsMeter = new Meter();
  		objDictionary.put("strWaitForMeterScreen", "False");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"testautof_home_button_push.py");
  		objDictionary.put("strWaitForMeterScreen", "True");
  	}

	//**************************************************************************************************************************************************************************************************************/
	//Short Session Wait Exit Spot
	//**************************************************************************************************************************************************************************************************************/
	public void SENTRYMETER_ShortSessionWaitExitSpot(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber, String strHostType)
  	{
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strParkingShortSessionSec = objDictionary.get("strParkingShortSessionSec");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strVirtualMeter.equals("True")){try {clsHttpConnections.JsonEndParkingSession(objDictionary);}catch (Exception e){}}
		else
		{
			//RPSS: Remain Parked Short Session
			clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
			Reporter.log("Waited ("+Integer.parseInt(strParkingShortSessionSec)+") seconds before exiting "+strHostType+" spot "+strSpotNumber);
			clsMeter.METER_ExitSpotAfterRejectedViolation(objDictionary,driver,sessionMeter,strSpotNumber,strHostType);
		}
	}
	public void SENTRYMETER_ShortSessionWaitExitSpot(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber, String strHostType)
  	{
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strParkingShortSessionSec = objDictionary.get("strParkingShortSessionSec");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strVirtualMeter.equals("True"))
		{
			try {Thread.sleep(20000);}catch (Exception e) {}
			try{clsHttpConnections.JsonEndParkingSession(objDictionary);}catch (Exception e){}
			//try{clsHttpConnections.CURL_EndParkingSession(objDictionary);}catch (Exception e) {}
		}
		else
		{
			//RPSS: Remain Parked Short Session
			clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
			try {Thread.sleep(1000);}catch (Exception e) {}
			clsMeter.METER_ExitSpotAfterRejectedViolation(objDictionary, driver, strSpotNumber, strHostType);
		}
	}

	//**************************************************************************************************************************************************************************************************************/
	//Short Session Wait Exit Both Spots
	//**************************************************************************************************************************************************************************************************************/
	public void SENTRYMETER_ShortSessionWaitExitBothSpots(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strHostType)
  	{
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strParkingShortSessionSec = objDictionary.get("strParkingShortSessionSec");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
		if(strVirtualMeter.equals("True")){try {clsHttpConnections.JsonEndParkingSession(objDictionary);}catch (Exception e){}}
		else
		{
			//RPSS: Remain Parked Short Session
			clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
			if (strNumberOfSpots.equals("2"))
	  		{clsMeter.METER_ExitBothSpots(objDictionary, driver, sessionMeter,strHostType);}
			else
			{clsMeter.METER_ExitSpot(objDictionary,driver,sessionMeter,"1",strHostType);}
		}
  	}
	public void SENTRYMETER_ShortSessionWaitExitBothSpots(Map<String, String> objDictionary,WebDriver driver, String strHostType)
  	{
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strParkingShortSessionSec = objDictionary.get("strParkingShortSessionSec");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,driver,strHostType);
		if(strVirtualMeter.equals("True")){try {clsHttpConnections.JsonEndParkingSession(objDictionary);}catch (Exception e){}}
		else
		{
			//RPSS: Remain Parked Short Session
			clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
			if (strNumberOfSpots.equals("2"))
	  		{clsMeter.METER_ExitBothSpots(objDictionary, driver, strHostType);}
	  		else
			{clsMeter.METER_ExitSpot(objDictionary,driver, "1", strHostType);}
		}
  	}

	//*******************************************************************************************************************************************************************************************
	//Add Report Variables
	//*******************************************************************************************************************************************************************************************
	public void Meter_AddReportVariables(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strTestCaseName)
	{
		Meter clsMeter = new Meter();
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
		if(strSubdomainError != null) {UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,strSubdomainError,"False");}
		//Delete Screen Shot
		//try{clsCommonWeb.DeletePreviousScreenShot(ITResult);}catch (Exception e) {}
		//clsMeter.METER_DeleteMeterScreenShotLocally(objDictionary, strTestCaseName);
  		String strEnvironment = objDictionary.get("strEnvironment");
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strPEOAPK = objDictionary.get("strPEOAPK");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strLocalMeterVersion = "";
		String strMD5SUMVersion = "";
		String strSilverBulletVersion = "";
		String strMeterStartTime = objDictionary.get("strMeterStartTime");
		String strSOMLocalImageId = "";String strSOMLocalDistroVersion = "";String strSOMLocalDistroName = "";
		if(sessionLocalMeter != null)
		{
			strLocalMeterVersion = clsMeter.GetMeterVersion(objDictionary,sessionLocalMeter);
			strMD5SUMVersion  = clsMeter.GetMeterMD5SUMVersion(objDictionary,sessionLocalMeter);
			strSilverBulletVersion = clsMeter.SENTRYMETER_GetSilverBulletVersion(objDictionary,sessionLocalMeter);
			objDictionary.remove("strMeterVersion");objDictionary.put("strMeterVersion", strLocalMeterVersion);
			strSOMLocalImageId  = clsMeter.SENTRYMETER_GetSOMImageId(objDictionary,sessionLocalMeter);
			strSOMLocalDistroVersion = clsMeter.SENTRYMETER_GetSOMDistroVersion(objDictionary,sessionLocalMeter);
			strSOMLocalDistroName = clsMeter.SENTRYMETER_GetSOMDistroName(objDictionary,sessionLocalMeter);
		}
		else
		{
			String strRemoteMeterVersion = clsMeter.GetMeterVersion(objDictionary,sessionRemoteMeter);
			strMD5SUMVersion  = clsMeter.GetMeterMD5SUMVersion(objDictionary,sessionRemoteMeter);
			strSilverBulletVersion = clsMeter.SENTRYMETER_GetSilverBulletVersion(objDictionary,sessionRemoteMeter);
			objDictionary.remove("strMeterVersion");objDictionary.put("strMeterVersion", strRemoteMeterVersion);
		}
		String strSentryLinkVersion = objDictionary.get("strSentryLinkVersion");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strRemoteDeviceId = objDictionary.get("strRemoteDeviceId");
		String strRemoteUser = objDictionary.get("strRemoteUser");
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
			String strRemoteMeterVersion = clsMeter.GetMeterVersion(objDictionary,sessionRemoteMeter);
			Reporter.log("Remotel Meter Version:"+strRemoteMeterVersion+"             ");
		}
		Reporter.log("Meter MD5SUM Version:"+strMD5SUMVersion+"                   ");
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
			String strSOMRemoteImageId  = clsMeter.SENTRYMETER_GetSOMImageId(objDictionary,sessionRemoteMeter);
			String strSOMRemoteDistroVersion = clsMeter.SENTRYMETER_GetSOMDistroVersion(objDictionary,sessionRemoteMeter);
			String strSOMRemoteDistroName = clsMeter.SENTRYMETER_GetSOMDistroName(objDictionary,sessionRemoteMeter);
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
	public void Meter_AddReportVariables(Map<String, String> objDictionary, WebDriver driver, String strTestCaseName)
	{
		Meter clsMeter = new Meter();
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
		//Used For Sentry View Test Case
		if(driver == null)
		{
			String strBrowser = objDictionary.get("strBrowser");
	      	CommonWeb clsCommonWeb = new CommonWeb();
		  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		  	driver = getDriver();
		}

		String strLocalMeterVersion = clsMeter.GetMeterVersion(objDictionary,driver,"Local");
		String strMeterStartTime = objDictionary.get("strMeterStartTime");
		String strMD5SUMVersion  = clsMeter.GetMeterMD5SUMVersion(objDictionary);
		String strSilverBulletVersion = clsMeter.SENTRYMETER_GetSilverBulletVersion(objDictionary);
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
		driver.close();
	}

	//********************************************************************************************************
	//Execute Python Script Against Meter
	//********************************************************************************************************
	public void METER_ExecutePythonScriptAgainstMeter(Map<String, String> objDictionary,Session sessionMeter, String strHostType, String strPythonCommand)
    {
		Stopwatch timer = Stopwatch.createStarted();
		Meter clsMeter = new Meter();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		String strCurrentAction = objDictionary.get("strCurrentAction");if(strCurrentAction == null){strCurrentAction = "";}
		String strScanValue = objDictionary.get("strScanValue");
		String strWaitForMeterScreen = objDictionary.get("strWaitForMeterScreen");
		if(strWaitForMeterScreen == null) {strWaitForMeterScreen = "True";}if(strVirtualMeter == null){strVirtualMeter = "False";}
		String strDebugMask = objDictionary.get("strDebugMask");if(strDebugMask == null) {strDebugMask = "0";}
		int intLogWait = 45;if(Integer.parseInt(strDebugMask) >= 5){intLogWait = 360;}
		//String strCoinWaitForLogMessage = objDictionary.get("strCoinWaitForLogMessage"); if(strCoinWaitForLogMessage == null) {strCoinWaitForLogMessage = "True";}
		String strHost = "";
		System.out.println("strRemoteHost"+strHostType.substring(strHostType.length() - 1));
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else if(strHostType.contains("Remote"))
		{
			strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));
		}
		if(strHost == null) {UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,strMethodName+" :The strHost equals null","False");}
		if (strVirtualMeter.equals("False"))
		{
			JSch jsch = new JSch();
			String strPassword = objDictionary.get("strUniquePassword");
			String strLocalHost = objDictionary.get("strHost");
			String strMeterUser = "";
			if(strHost.equals(strLocalHost)){strMeterUser = objDictionary.get("strMeterUser");strHostType = "Local";}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
			int port=22;
			//METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand);
			try
	    	{
				Channel channel= sessionMeter.openChannel("exec");
		        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
				System.out.println("PYTHON SCRIPT: "+strPythonCommand);
				//LOG BEGINNING TIME EXECUTED
				if(strPythonCommand.contains("testautof_home_button_push.py"))
				{
					if(strCurrentAction.equals("ESNRSRM")||strCurrentAction.equals("ESNLSRM"))
			    	{
						 Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
			    	}
					else
					{
						Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strLocalHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
					}
				}
				else if(strPythonCommand.contains("testautof_enter_space.py"))
				{
					if(strCurrentAction.equals("ESNRSRM")||strCurrentAction.equals("ESNLSRM"))
			    	{
						Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
			    	}
					else
					{
						Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strLocalHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
					}
				}
				else if(strPythonCommand.contains("testautof_insert_coins_no_spot.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("testauto_card_pay_no_spot.sh")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("ytestauto_card_pay_no_spot.sh")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("testautof_apply_payment.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				((ChannelExec)channel).setCommand("/usr/local/bin/"+strPythonCommand);
		        channel.setInputStream(null);
		        channel.connect();
		        if(strPythonCommand.contains("testautof_home_button_push.py") && strWaitForMeterScreen.equals("True"))
		        {
		        	clsMeter.METER_WaitForMeterScreen(objDictionary,sessionMeter,"Local","SCREEN_MULTI_SELECT_SPACE", "PinPad To "+strHostType+" Space");
		        }
			    else if(strPythonCommand.contains("testautof_enter_space.py"))
			    {
			    	String strExistsInLogs = "";
			    	if(strCurrentAction.equals("ESNRSRM")||strCurrentAction.equals("ESNLSRM"))
			    	{
			    		//REMOTE METER
			    		strExistsInLogs = clsMeter.METER_ConditionalScanMeterLogs(objDictionary,sessionMeter,strHost, "REQUEST_MASTER_INHIBIT_STATUS indicates acceptor released", 15,"Check if Coin Acceptor Released", "1", "Remote1");
			    	}
			    	else
			    	{
			    		//LOCAL METER
			    		strExistsInLogs = clsMeter.METER_ConditionalScanMeterLogs(objDictionary,sessionMeter,strLocalHost, "REQUEST_MASTER_INHIBIT_STATUS indicates acceptor released", 15,"Check if Coin Acceptor Released", "1", "Local");
			    	}
			    	if(strExistsInLogs.equals("True"))
					{
						Reporter.log("The Coin Accepter was Enabled: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date()));
						objDictionary.put("strCoinAccepter","Enabled");
					}
					else
					{
						Reporter.log("The Coin Accepter was Disabled: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date()));
						objDictionary.put("strCoinAccepter","Disabled");
					}
			    	clsMeter.METER_WaitForMeterScreen(objDictionary,sessionMeter,"Local","SCREEN_MULTI_HOME", "PinPad To "+strHostType+" Space");
			    }
		        else if(strPythonCommand.contains("screenshot.png"))
		        {
		        	//channel.setInputStream(null);
		        	InputStream in=channel.getInputStream();
		        	byte[] tmp=new byte[1024];while(in.available()>0){int i=in.read(tmp, 0, 1024);if(i<0) {
						break;
					}}
		        	try{TimeUnit.SECONDS.sleep(1);}catch (Exception e) {}
		        }
		        if(strPythonCommand.contains("vehicle_empty_spot")||strPythonCommand.contains("vehicle_leave_spot")||strPythonCommand.contains("testauto_vehicle_detection_control.py -e")||strPythonCommand.contains("testauto_vehicle_detection_control.py -l"))
			    {
		        	Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
		        	if(strHostType.equals("Local"))
		        	{
		        		//Add a better sync when strDebugMask > 5
		        		clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionMeter,strHost,strScanValue,intLogWait,"ParkingSessionEnd","",strHostType);
		        	}
		        	else
		        	{
		        		clsMeter.METER_ScanRemoteMeterLogsUntilValueAppearsOrTimeOutReachedNew(objDictionary,sessionMeter,strHost,strScanValue,90,"ParkingSessionEnd","",strHostType);
		        	}
			    }
		        else if(strPythonCommand.contains("vehicle_occupy_spot")||strPythonCommand.contains("vehicle_park_spot"))
		        {
		        	SENTRYMETER_StoreParkingSessionTime(objDictionary);
			    }
		        switch (strPythonCommand)
		        {
		        	case "sys_get_screen_snapshot.sh /home/seco/screenshot.png":
		        	case "sys_get_screen_snapshot.sh":
		        	case "testautof_home_button_push.py":
		        	case "testauto_card_pay_no_spot.sh":
		        	case "ytestauto_card_pay_no_spot.sh":
		        	case "testautof_insert_coins_no_spot.py":
		        	case "testautof_apply_payment.py SPOT_1":
		        	case "testautof_apply_payment.py SPOT_2":
		        	case "testauto_vehicle_detection_control.py -e SPOT_1":
		        	case "testauto_vehicle_detection_control.py -e SPOT_2":
		        		break;
		        	default:
		        		if(!strPythonCommand.contains("testautof_enter_space.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
			    }
		    }
		    catch(Exception e)
		    {
		    	if(e.toString().equals("com.jcraft.jsch.JSchException: Auth fail")){UpdateErrorMessageWithPivotalData(objDictionary,null,strMethodName+" :"+e);}
		    	else{UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,strMethodName+" :"+e,"True");}
		    }
		}
		Reporter.log("<font color='#5533ff'>Method ("+strMethodName+") took: " + timer.stop()+"</font>");
    }
	public void METER_ExecutePythonScriptAgainstMeter(Map<String, String> objDictionary,String strHost, String strPythonCommand)
    {
		Meter clsMeter = new Meter();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			JSch jsch = new JSch();
			String strPassword = objDictionary.get("strUniquePassword");
			String strLocalHost = objDictionary.get("strHost");
			String strScanValue = objDictionary.get("strScanValue");
			String strEnv = objDictionary.get("strEnvironment");
			String strMeterName = objDictionary.get("strMeterName");
			String strMeterUser = "";
			String strHostType = "";
			if(strHost.equals(strLocalHost)){strMeterUser = objDictionary.get("strMeterUser");strHostType = "Local";}
			else{strMeterUser = objDictionary.get("strRemoteUser");strHostType = "Remote";}
			int port=22;
			try
	    	{
	    		Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		    	session.setConfig("StrictHostKeyChecking", "no");
		    	session.connect();
		        Channel channel=session.openChannel("exec");
		        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
				//LOG BEGINNING TIME EXECUTED
				if(strPythonCommand.contains("testautof_home_button_push.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("testautof_enter_space.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("testautof_insert_coins_no_spot.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("testauto_card_pay_no_spot.sh")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("ytestauto_card_pay_no_spot.sh")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("testautof_apply_payment.py")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("vehicle_empty_spot")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				else if(strPythonCommand.contains("vehicle_leave_spot")){Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");}
				((ChannelExec)channel).setCommand("/usr/local/bin/"+strPythonCommand);
		        channel.setInputStream(null);
		        //((ChannelExec)channel).setErrStream(System.err);
		        //InputStream in=channel.getInputStream();
		        channel.connect();
		        if(strPythonCommand.contains("vehicle_empty_spot")||strPythonCommand.contains("vehicle_leave_spot"))
			    {
		        	String strEnvironment = objDictionary.get("strEnvironment");
		        	if(strEnvironment.equals("QA"))
		        	{	
		        		// Get the current time in UTC
		                Instant now = Instant.now();
		                // Define the pattern: YYYY-MM-DD HH:MM:SS
		                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
		                objDictionary.put("strMeterExitTimeValue", formatter.format(now));
		        	}
		        	else
		        	{
		        		//Might not need this one, use the Above would also work.
		        		DumpStackMeterExitTimeValue(objDictionary,null,"1","Local");
		        	}
		        	if(strEnvironment.equals("SG"))
		    		{
//		    			if(strHostType.equals("Local")){clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strHost,strScanValue,45,"ParkingSessionEnd","`",strHostType);}
//				    	else{clsMeter.METER_ScanRemoteMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary, strHost,strScanValue,60,"ParkingSessionEnd", "2", strHostType);}
		    			System.out.println("MIH");
		    		}
			    }
		        else if(strPythonCommand.contains("testautof_home_button_push.py") && !strMeterName.equals("Lot Auto One Pay"))
		        {
		        	clsMeter.METER_WaitForMeterScreen(objDictionary,"Local","SCREEN_MULTI_SELECT_SPACE", "PinPad To "+strHostType+" Space");
		        }
		        else if(strPythonCommand.contains("testautof_enter_space.py"))
			    {
			    	clsMeter.METER_WaitForMeterScreen(objDictionary,"Local","SCREEN_MULTI_HOME", "PinPad To "+strHostType+" Space");
			    }
			    else if(strPythonCommand.contains("screenshot.png"))
		        {
		        	//channel.setInputStream(null);
		        	InputStream in=channel.getInputStream();
		        	byte[] tmp=new byte[1024];while(in.available()>0){int i=in.read(tmp, 0, 1024);if(i<0)break;}
		        	try{TimeUnit.SECONDS.sleep(1);}catch (Exception e) {}
		        }
		        //If parking store Parking Session Time
		        else if(strPythonCommand.contains("vehicle_occupy_spot")||strPythonCommand.contains("vehicle_park_spot"))
		        {
		        	SENTRYMETER_StoreParkingSessionTime(objDictionary);
			    }
		        switch (strPythonCommand)
		        {
		        	case "sys_get_screen_snapshot.sh /home/seco/screenshot.png":
		        	case "sys_get_screen_snapshot.sh":
		        	case "testautof_home_button_push.py":
		        	case "testauto_card_pay_no_spot.sh":
		        	case "ytestauto_card_pay_no_spot.sh":
		        	case "testautof_insert_coins_no_spot.py":
		        	case "testautof_apply_payment.py SPOT_1":
		        	case "testautof_apply_payment.py SPOT_2":
		        	case "vehicle_empty_spot_1":
		        	case "vehicle_empty_spot_2":
		        		break;
		        	default:
		        		if(!strPythonCommand.contains("testautof_enter_space.py"))
		        		{
			        		Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
						}
		        }
				channel.disconnect();session.disconnect();
		    }
		    catch(Exception e)
		    {
		    	UpdateErrorMessageWithPivotalData(objDictionary,null,null,strMethodName+" :"+e,"True");
		    }
		}
    }
	//********************************************************************************************************

	public void METER_UpdateMeterAdditionalMeterSettings(Map<String, String> objDictionary,Session sessionMeter)
    {
		if(sessionMeter != null)
		{
			Meter clsMeter = new Meter();
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py IDLE_TIMEOUT_SECONDS_MULTI 15");

//			IDLE_TIMEOUT_SECONDS
//			IDLE_TIMEOUT_SECONDS_MULTI



			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py SYS_LOAD_CHECK_THRESHOLD 6.5");
			try {Thread.sleep(1000);}catch (Exception e) {}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_true.py SYS_FORCE_MULTI");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting.py COIN_ERROR_RATE_MODE_OVERLAY \"\"");
			String strDebugMask = objDictionary.get("strDebugMask");if(strDebugMask == null) { strDebugMask = "0";}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py SYS_DEBUG_MASK "+strDebugMask);
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py EPAY_AUTO_AUTHORIZE_AMOUNT 1000");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting.py PUCK_ERROR_RATE_MODE_OVERLAY \"\"");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_true.py SYS_GLOBAL_PAY_MUNI_SINGLE_RATE_SCHEDULE");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_false.py SYS_SOFT_VIOLATION");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py COIN_SCREEN_IDLE_TIMEOUT 15");

			//New
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_int.py PARKING_CARD_PAY_TIMEOUT 30");
			try {Thread.sleep(5000);}catch (Exception e) {}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","settings_set_setting_true.py PARKING_SHOW_UNLOCK_TIME");

			//settings_set_setting_int.py IDLE_TIMEOUT_SECONDS 10

//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter, "Local", "coin_cashout.py");
//			try {Thread.sleep(3000);}catch (Exception e) {}
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local", "settings_set_setting_false.py COIN_JAR_FULL_MAINT_ENABLE");
//			try {Thread.sleep(3000);}catch (Exception e) {}
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","set_card_error_rate_mode_overlay.py \"\"");
//			try {Thread.sleep(3000);}catch (Exception e) {}
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","set_coin_error_rate_mode_overlay.py \"\"");
//
//			try {Thread.sleep(3000);}catch (Exception e) {}
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","parking_clear_maintenance_mode.py");
//
			//Clear Coin Jar Alert
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter, "Local","set_coin_full_disable_percentage_100.py");
			try {Thread.sleep(3000);}catch (Exception e) {}



		}
    }

	public void METER_ExecutePythonScriptAgainstMeterWithLogScan(Map<String, String> objDictionary,String strHost, String strPythonCommand,String strHostType)
    {
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			JSch jsch = new JSch();
			String strPassword = objDictionary.get("strUniquePassword");
			String strLocalHost = objDictionary.get("strHost");
			String strMeterUser = "";
			if(strHost.equals(strLocalHost)){strMeterUser = objDictionary.get("strMeterUser");}
			else{strMeterUser = objDictionary.get("strRemoteUser");}
			int port=22;
	    	METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
	    	try
	    	{
	    		Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		    	session.setConfig("StrictHostKeyChecking", "no");
		    	session.connect();
		        Channel channel=session.openChannel("exec");
		        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
				((ChannelExec)channel).setCommand("/usr/local/bin/"+strPythonCommand);
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp=new byte[1024];
		        while(in.available()>0){int i=in.read(tmp, 0, 1024);if(i<0) {
					break;
				}}
				//If parking store Parking Session Time
		        if(strPythonCommand.contains("vehicle_occupy_spot")||strPythonCommand.contains("vehicle_park_spot"))
		        {
		        	SENTRYMETER_StoreParkingSessionTime(objDictionary);
			    }
				System.out.println("The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date()));
				Reporter.log("<font color='green'>The Python Script ("+strPythonCommand+") was executed successfully on host ("+strHost+")-System Time: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date())+"</font>");
				channel.disconnect();session.disconnect();
		    }
		    catch(Exception e)
		    {
		    	if(e.toString().equals("com.jcraft.jsch.JSchException: Auth fail"))
		    	{
		    		UpdateErrorMessageWithPivotalData(objDictionary,null,strMethodName+" :"+e);
		    	}
		    	else
		    	{
		    		UpdateErrorMessageWithPivotalData(objDictionary,null,strMethodName+" :"+e);
		    	}
		    }
		}
    }



//	public void METER_CompareMeterSettingsDual()
//    {
//		JSch jsch = new JSch();
//		String strPassword = "firesale";
//		String strHost1 = "10.10.101.104";
//		String strHostType1 = "seco";
//		String strHost2 = "10.10.102.182";
//		String strHostType2 = "root";
//    	try
//    	{
//    		// Retrieve JSON from the first host
//	    	Session session = jsch.getSession(strHostType1, strHost1, 22);
//	    	session.setPassword(strPassword);
//	        session.setConfig("StrictHostKeyChecking", "no");
//	        session.connect();
//	        Channel channel=session.openChannel("exec");
//	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_settings.py");
//	        channel.setInputStream(null);
//	        ((ChannelExec)channel).setErrStream(System.err);
//	        InputStream in=channel.getInputStream();
//	        channel.connect();
//	        byte[] tmp = new byte[1024];
//	        ByteArrayOutputStream output = new ByteArrayOutputStream(); // Use a ByteArrayOutputStream to store the complete output
//	        while (true) {
//	            int bytesRead = in.read(tmp, 0, 1024);
//	            if (bytesRead < 0) {
//	                break;
//	            }
//	            output.write(tmp, 0, bytesRead);
//	        }
//	        byte[] result = output.toByteArray();
//	        // Convert the byte array to JSON
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        JsonNode jsonNode1 = objectMapper.readTree(result);
//	        // Print the JSON to the console
//	        //System.out.println(jsonNode1.toPrettyString());
//
//
//	        // Retrieve JSON from the second host
//	        Session session2 = jsch.getSession(strHostType2, strHost2, 22);
//	    	session2.setPassword(strPassword);
//	        session2.setConfig("StrictHostKeyChecking", "no");
//	        session2.connect();
//	        Channel channel2=session2.openChannel("exec");
//	        ((ChannelExec)channel2).setCommand("/usr/local/bin/settings_get_settings.py");
//	        channel2.setInputStream(null);
//	        ((ChannelExec)channel2).setErrStream(System.err);
//	        InputStream in2=channel2.getInputStream();
//	        channel2.connect();
//	        byte[] tmp2 = new byte[1024];
//	        ByteArrayOutputStream output2 = new ByteArrayOutputStream(); // Use a ByteArrayOutputStream to store the complete output
//	        while (true) {
//	            int bytesRead2 = in2.read(tmp2, 0, 1024);
//	            if (bytesRead2 < 0) {
//	                break;
//	            }
//	            output2.write(tmp2, 0, bytesRead2);
//
//	        }
//	        byte[] result2 = output2.toByteArray();
//	        // Convert the byte array to JSON
//	        ObjectMapper objectMapper2 = new ObjectMapper();
//	        JsonNode jsonNode2 = objectMapper2.readTree(result2);
//	        // Print the JSON to the console
//	        //System.out.println(jsonNode2.toPrettyString());
//
//	        String jsonString1 = jsonNode1.toString();
//	        String jsonString2 = jsonNode2.toString();
//
//
//	        JSONObject json1 = new JSONObject(jsonString1);
//	        JSONObject json2 = new JSONObject(jsonString2);
//
//	        // Array of keys to be ignored
//	        String[] ignoredKeys =
//	        	{
//	        		"EPAY_READER_SERIAL_NUMBER", "PARKING_VMD_SETTINGS", "SYS_LATITUDE", "SYS_NO_PARKING_OVERLAY","EPAY_READER_SERIAL_NUMBER",
//	        		"COIN_VOLUME_MAP", "SYS_MAC_ADDRESS", "SYS_LONGITUDE", "CAMERA_USER_DETECT_SETTINGS", "SYS_DEVICE_ID", "SYS_IP_ADDRESS",
//	        		"OGG_DOWN_USER","SYS_LOAD_CHECK_LAST_THRESHOLD_EXCEEDED","SYS_FRIENDLY_NAME","CREDIT_CALL_TRANSACTION_KEY","SYS_COS_ADDRESS_BINDING",
//	        		"CREDIT_CALL_TERMINAL_ID","RABBITMQ_HOST","SYS_HELP_VOLUME","PARKING_IMAGE_SILVER_BULLET_IP_ADDR"
//	        	};
//
//	        JSONArray keys1 = json1.names();
//	        int numKeys1 = keys1.length();
//	        System.out.println(strHost1+" - Number of keys: " + numKeys1);
//	        JSONArray keys2 = json2.names();
//	        int numKeys2 = keys2.length();
//	        System.out.println(strHost2+" - Number of keys: " + numKeys2);
//
//	        //Make the host ip string the same length
//	        if (strHost1.length() > strHost2.length()) {
//	            System.out.println("MIH");
//	            int difference = strHost1.length() - strHost2.length();
//	            StringBuilder sb = new StringBuilder(strHost2);
//	            for (int i = 0; i < difference; i++) {sb.append(" ");} // Update the strHost2 string with the appended spaces
//	        } else if (strHost2.length() > strHost1.length()) {
//	            System.out.println("MIH");
//	            int difference = strHost2.length() - strHost1.length();
//	            StringBuilder sb = new StringBuilder(strHost1);
//	            for (int i = 0; i < difference; i++) {sb.append(" ");}
//	            strHost1 = sb.toString();
//	        }
//
//	        // Compare the keys
//	        int intDifferenenceCounter = 0;
//	        int intTotalCounter = 0;
//	        if (!keys1.equals(keys2)) {
//	            System.out.println("Keys are different between the JSON structures.");
//	        } else {
//	            // Iterate through the keys
//	            for (int i = 0; i < keys1.length(); i++) {
//	                String key = keys1.getString(i);
//
//	                // Check if the key should be ignored
//	                if (shouldIgnoreKey(key, ignoredKeys)) {
//	                    continue; // Skip the key and proceed to the next iteration
//	                }
//
//	                Object value1 = json1.get(key).toString().trim();
//	                Object value2 = json2.get(key).toString().trim();
//
//	                // Compare the values
//	                if (!value1.equals(value2)) {
//	                    System.out.println("Difference found for key: " + key);
//	                    System.out.println("Value in "+strHost1+": " + value1);
//	                    System.out.println("Value in "+strHost2+": " + value2);
//	                    intDifferenenceCounter++;
//	                }
//	                intTotalCounter++;
//	            }
//	            System.out.println("Number of differenences: "+intDifferenenceCounter);
//	            System.out.println("Total: "+intTotalCounter);
//	        }
//	        System.out.println("MIH");
//    	}
//    	catch(Exception e){System.out.println(e);}
//    }
	// Function to check if a key should be ignored
//	private static boolean shouldIgnoreKey(String key, String[] ignoredKeys) {
//	    for (String ignoredKey : ignoredKeys) {
//	        if (key.equals(ignoredKey)) {
//	            return true;
//	        }
//	    }
//	    return false;
//	}
	public String METER_CheckIfPythonScriptExistsOnMeter(Map<String, String> objDictionary,String strHost, String strPythonCommand,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPassword = objDictionary.get("strUniquePassword");
		strPythonCommand = strPythonCommand.substring(0,strPythonCommand.indexOf(".py")+3);
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		if(strMeterUser.equals("root"))
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" ssh root@"+strHost+" file /usr/local/bin/"+strPythonCommand};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strStandardOutPut = "";
				while ((s = stdInput.readLine()) != null){strStandardOutPut =s;}
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(strStandardOutPut.contains("Python script, ASCII text executable")||strErrorMessage.contains("sh: sshpass: command not found"))
				{return "True";}else
				{return "False";}
			}
			catch (Exception e){System.out.println(e+"-"+strMethodName);}
		}
		else
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" ssh seco@"+strHost+" file /usr/local/bin/"+strPythonCommand};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strStandardOutPut = "";
				while ((s = stdInput.readLine()) != null){strStandardOutPut =s;}
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(strStandardOutPut.contains("Python script, ASCII text executable")||strErrorMessage.contains("sh: sshpass: command not found"))
				{return "True";}else
				{return "False";}
			}
			catch (Exception e){System.out.println(e+"-"+strMethodName);}
		}
		return "False";
	}
	public String METER_CheckIfShellScriptExistsOnMeter(Map<String, String> objDictionary,String strHost, String strPythonCommand)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPassword = objDictionary.get("strUniquePassword");
		strPythonCommand = strPythonCommand.substring(0,strPythonCommand.indexOf(".sh")+3);
		String strMeterUser = objDictionary.get("strMeterUser");
		if(strMeterUser.equals("root"))
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" ssh root@"+strHost+" file /usr/local/bin/"+strPythonCommand};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strStandardOutPut = "";
				while ((s = stdInput.readLine()) != null){strStandardOutPut =s;}
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(strStandardOutPut.contains("Python script, ASCII text executable")||strStandardOutPut.contains("Bourne-Again shell script, ASCII text executable")||strErrorMessage.contains("sh: sshpass: command not found"))
				{return "True";}
				else
				{return "False";}
			}
			catch (Exception e)
			{
				System.out.println(e+"-"+strMethodName);
			}
		}
		else
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" ssh seco@"+strHost+" file /usr/local/bin/"+strPythonCommand};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strStandardOutPut = "";
				while ((s = stdInput.readLine()) != null){strStandardOutPut =s;}
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(strStandardOutPut.contains("Python script, ASCII text executable")||strStandardOutPut.contains("Bourne-Again shell script, ASCII text executable")||strErrorMessage.contains("sh: sshpass: command not found"))
				{return "True";}
				else
				{return "False";}
			}
			catch (Exception e)
			{
				System.out.println(e+"-"+strMethodName);
			}
		}


		return "False";
	}
	public void METER_CreateGracePeriodViolation(Map<String, String> objDictionary, WebDriver driver, String strHost, String strSpotNumber, String strInitialGracePeriod)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strMeterName = objDictionary.get("strMeterName");
		String strRemainingFreeTimeStart =  objDictionary.get("strRemainingFreeTimeStart");
		//PS: Park Spot 1
		clsMeter.METER_ParkSpot(objDictionary,strSpotNumber,"Local");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a").withZone(ZoneId.systemDefault()); // or a specific zone
		String strNoParkingStartTime = objDictionary.get("strNoParkingStartTime");
		if(strNoParkingStartTime != null)
		{
			int intRemainingTimeBeforeNoParkingAtPark = clsMeter.METER_CalculateRemainingMinutesBeforeNoParking(objDictionary, null,  strNoParkingStartTime);
			objDictionary.put("strRemainingTimeBeforeNoParkingAtPark",Integer.toString(intRemainingTimeBeforeNoParkingAtPark));
		}
		if(strRemainingFreeTimeStart != null)
		{
			String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");
			int intRemainingFreeTimeSec = clsMeter.METER_CalculateRemainingFreeTimeSeconds(objDictionary,driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
			//Wait for Meter to Violate
			clsMeter.METER_MeterWaitWithMessageWithoutTouchScreen(objDictionary,intRemainingFreeTimeSec, "Waiting for free time to expire on spot-"+strSpotNumber);
			//intUsedTimeStart
	  		Calendar calendar = Calendar.getInstance();
	      	String strFreeTimeEndedTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
	      	objDictionary.put("strFreeTimeEndedTime", strFreeTimeEndedTime);
		}
		//Function Variables
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		try {Thread.sleep(10000);}catch (Exception e) {}
		//Wait for Meter to Violate
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for grace period violation-Spot"+strSpotNumber);
		if(strNoParkingStartTime != null)
		{
			//Used Meter Increment Time > Minutes Before Free & Unlock Enabled and True Up disabled
			int intRemainingTimeBeforeNoParkingAtViolation = clsMeter.METER_CalculateRemainingMinutesBeforeNoParking(objDictionary, null,  strNoParkingStartTime);
			objDictionary.put("strRemainingTimeBeforeNoParkingAtViolation",Integer.toString(intRemainingTimeBeforeNoParkingAtViolation));
		}
		Reporter.log("Waited ("+intInitialGracePeriod+") seconds for grace period violation");
		GlobalWait(objDictionary, driver, "{WaitUntilMeterViolationEqualsTrue} NA", 65,strSpotNumber,"Local");
		//Grace Period Violation Time
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm a");
//		String strGracePeriodViolationTime = dateFormat.format(new Date());
		Calendar calendar = Calendar.getInstance();
		String strGracePeriodViolationTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		Reporter.log("<font color='Green'>Grace Peroid Violation Time "+strGracePeriodViolationTime+"</font>");
		objDictionary.put("strGracePeriodViolationTime", strGracePeriodViolationTime);
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local",strSpotNumber);
		Reporter.log("strMeterViolationValue: "+strMeterViolationValue);
		if(strMeterViolationValue.equals("True"))
		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") has violated do grace period violation");}
		else
		{UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter ("+strMeterName+") spot ("+strSpotNumber+") did not violate do to grace period violation");}
		//Store Violation Id
		GlobalWait(objDictionary, driver, "{WaitUntilSentryLinkViolationIdExists} NA", 60,strSpotNumber,"Local");
		HttpConnections clsHttpConnections = new HttpConnections();
  		String strSLViolationId = clsHttpConnections.HTTPCONNECTIONS_GetSLViolationNumber(objDictionary, "Local", strSpotNumber,1);
  		objDictionary.put("strSLViolationId",strSLViolationId);
  		String strNumberOfViolations = objDictionary.get("strNbrOfViolations");
	    if(!strNumberOfViolations.equals("1"))
	    {
	    	if(strNumberOfViolations.equals("0"))
	    	{
	    		UpdateErrorMessageWithPivotalData(objDictionary,driver, "No Violation existed");
	    	}
	    	else
	    	{
	    		UpdateErrorMessageWithPivotalData(objDictionary,driver, "The Initial Grace Period Violated unexpectedly created multiple violations");
	    	}
	    }
	    String strEnvironment = objDictionary.get("strEnvironment");if(strEnvironment == null) {strEnvironment = "";}
		//DEVICE SETTINGS
		switch (strEnvironment)
		{
			case "SG":
				objDictionary.remove("strViolationReason");objDictionary.put("strViolationReason", "Initial Grace Period Exceeded");
				break;
			case "PROD":
				objDictionary.remove("strViolationReason");objDictionary.put("strViolationReason", "Initial grace period exceeded");
				break;
		}
	}
	public void METER_WaitForMeterToViolate(Map<String, String> objDictionary, WebDriver driver, String strHost, String strSpotNumber, String strInitialGracePeriod)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		//Dictionary Variables
		String strMeterName = objDictionary.get("strMeterName");
		String strRemainingFreeTimeStart =  objDictionary.get("strRemainingFreeTimeStart");
		if(strRemainingFreeTimeStart != null)
		{
			String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");
			int intRemainingFreeTimeSec = clsMeter.METER_CalculateRemainingFreeTimeSeconds(objDictionary,driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
			//Wait for Meter to Violate
			clsMeter.METER_MeterWaitWithMessageWithoutTouchScreen(objDictionary,intRemainingFreeTimeSec, "Waiting for free time to expire on spot-"+strSpotNumber);
			//intUsedTimeStart
	  		Calendar calendar = Calendar.getInstance();
	      	String strFreeTimeEndedTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
	      	objDictionary.put("strFreeTimeEndedTime", strFreeTimeEndedTime);
		}
		//Function Variables
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Wait for Meter to Violate
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for grace period violation-Spot"+strSpotNumber);
		Reporter.log("Waited ("+intInitialGracePeriod+") seconds for grace period violation");
		GlobalWait(objDictionary, driver, "{WaitUntilMeterViolationEqualsTrue} NA", 60,strSpotNumber,"Local");
		//Grace Period Violation Time
		Calendar calendar = Calendar.getInstance();
		String strGracePeriodViolationTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		Reporter.log("<font color='Green'>Grace Peroid Violation Time "+strGracePeriodViolationTime+"</font>");
		objDictionary.remove("strGracePeriodViolationTime");objDictionary.put("strGracePeriodViolationTime", strGracePeriodViolationTime);
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local",strSpotNumber);
		Reporter.log("strMeterViolationValue: "+strMeterViolationValue);
		if(strMeterViolationValue.equals("True"))
		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") has violated do grace period violation");}
		else
		{UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter ("+strMeterName+") spot ("+strSpotNumber+") did not violate do to grace period violation");}
		//Store Violation Id
		GlobalWait(objDictionary, driver, "{WaitUntilSentryLinkViolationIdExists} NA", 40,strSpotNumber,"Local");
		HttpConnections clsHttpConnections = new HttpConnections();
  		String strSLViolationId = clsHttpConnections.HTTPCONNECTIONS_GetSLViolationNumber(objDictionary, "Local", strSpotNumber,1);
  		objDictionary.put("strSLViolationId",strSLViolationId);
  		String strNumberOfViolations = objDictionary.get("strNbrOfViolations");
	    if(!strNumberOfViolations.equals("1"))
	    {
	    	if(strNumberOfViolations.equals("0"))
	    	{
	    		UpdateErrorMessageWithPivotalData(objDictionary,driver, "No Violation existed");
	    	}
	    	else
	    	{
	    		UpdateErrorMessageWithPivotalData(objDictionary,driver, "The Initial Grace Period Violated unexpectedly created multiple violations");
	    	}
	    }objDictionary.remove("strViolationReason");objDictionary.put("strViolationReason", "Initial Grace Period Exceeded");
	}
	public String METER_CreateGracePeriodViolationReturnParkingSessionId(Map<String, String> objDictionary,WebDriver driver,String strHost, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strBrowser = objDictionary.get("strBrowser");
   		String strRemotePath = objDictionary.get("strRemotePath");
   		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
   		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		String strMeterName = objDictionary.get("strMeterName");
		String strVehicleParkType = objDictionary.get("strVehicleParkType");
    	String strInitialGracePeriod = METER_GetParkingInitialGracePeriodTime(objDictionary,driver);
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
		GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualTrue} NA", 30,strSpotNumber,"Local");
		//StartTime
		long startTime = System.currentTimeMillis();
		//Create a thread to Check That Parking Session Exists
		String strParkingSessionId = clsCommonWeb.SENTRYLINK_StoreParkingSessionId(objDictionary,getDriver());
		//EndTime
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		int seconds = (int) (totalTime / 1000) % 60;
		int intInitialGracePeriodSec = Integer.parseInt(strInitialGracePeriod) - seconds;
		//Wait for Meter to Violate
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSec, "Waiting for grace period violation-Spot"+strSpotNumber);
		Reporter.log("Waited ("+Integer.parseInt(strInitialGracePeriod)+") seconds for grace period violation");
		//Grace Period Violation Time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm a");
		String strGracePeriodViolationTime = dateFormat.format(new Date());
		Reporter.log("<font color='Green'>Grace Peroid Violation Time "+strGracePeriodViolationTime+"</font>");
		objDictionary.remove("strGracePeriodViolationTime");objDictionary.put("strGracePeriodViolationTime", strGracePeriodViolationTime);
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local", strSpotNumber);
		if(strMeterViolationValue.equals("True"))
		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") has violated do grace period violation");}
		else
		{
			String strErrorMsg = "The Meter ("+strMeterName+") spot ("+strSpotNumber+") did not violate do to grace period violation";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		}
		objDictionary.remove("strViolationReason");objDictionary.put("strViolationReason", "Initial Grace Period Exceeded");
		getDriver().quit();
		return strParkingSessionId;
	}
	public void METER_MeterWaitWithMessage(Map<String, String> objDictionary,int intSeconds, String strMessage)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intSeconds);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
        int intTouchScreenCount = 0;
//        clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");

        if(intSeconds > 700)
        {
        	UpdateErrorMessageWithPivotalData(objDictionary,null,"The Number of Seconds ("+intSeconds+") is to high for an automated test");
        }
        do
        {
        		System.out.println(strMessage);
	        	if(intTouchScreenCount == 30)
	        	{
//	        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
	        		intTouchScreenCount = 0;
	        	}
	        	try{TimeUnit.SECONDS.sleep(1);}catch (Exception e) {}
	        	currentTime = new Timestamp(System.currentTimeMillis());
	        	intTouchScreenCount++;
	    }while (endTime.getTime() > currentTime.getTime());
        Reporter.log(strMessage.replace("Waiting", "Waited"));
//        clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
	}
	public void METER_MeterWaitWithMessage2(Map<String, String> objDictionary,int intSeconds, String strMessage)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intSeconds);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
        int intTouchScreenCount = 0;
//        clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");

        if(intSeconds > 1000)
        {
        	UpdateErrorMessageWithPivotalData(objDictionary,null,"The Number of Seconds ("+intSeconds+") is to high for an automated test");
        }
        do
        {
        		System.out.println(strMessage);
	        	if(intTouchScreenCount == 30)
	        	{
//	        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
	        		intTouchScreenCount = 0;
	        	}
	        	try{TimeUnit.SECONDS.sleep(1);}catch (Exception e) {}
	        	currentTime = new Timestamp(System.currentTimeMillis());
	        	intTouchScreenCount++;
	    }while (endTime.getTime() > currentTime.getTime());
        Reporter.log(strMessage.replace("Waiting", "Waited"));
//        clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
	}
	
	public void METER_MeterWaitWithMessageWithoutTouchScreen(Map<String, String> objDictionary,int intSeconds, String strMessage)
	{
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intSeconds);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
        int intTouchScreenCount = 0;
        do
        {
        	if(intTouchScreenCount == 30){ intTouchScreenCount = 0;}
        	try {Thread.sleep(1000);}catch (Exception e) {}
        	currentTime = new Timestamp(System.currentTimeMillis());
	        intTouchScreenCount++;
	    }while (endTime.getTime() > currentTime.getTime());
    }
	public void METER_DeleteFreeRateBlock(Map<String, String> objDictionary, WebDriver driver)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		GetRateBlockFromMeter(objDictionary);
		String strFreeTimeRateBlockId = objDictionary.get("strFreeTimeRateBlockId");
		String strDeviceId = objDictionary.get("strDeviceId");
		if(strFreeTimeRateBlockId != null)
		{
			//Remove Free Time Rate Block
			clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
			clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, "admin");
			String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
			clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Rate Block Group","Local");
			String strCondition = clsCommonWeb.ConditionalStepLink(objDictionary, driver, "Rate Block Group", "FreeRateBlock", 1, "Exists", "strCondition");
			if(strCondition.equals("True"))
			{
				clsCommonWeb.ClickLink(objDictionary, driver, "Rate Block Group", "FreeRateBlock", 1);
				clsCommonWeb.ClickButton(objDictionary, driver, "Rate Block Setting", "Delete Rate Block", 1,"Local");
			}
			METER_RemoveAllRateBlocks(objDictionary);
			clsCommonWeb.ClickButton(objDictionary, driver, "Rate Block Group", "Send to all", 1,"Local");
			GlobalWait(objDictionary, driver, "{WaitUntilARateBlockIdIsNotNeg1} NA", 40, "1","Local");
			try {Thread.sleep(30000);}catch (Exception e) {}
		}
	}
	public void GetRateBlockFromMeter(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
    	try
    	{
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[2048];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 2048);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		int sBlockType = 0;
		        		int sBlockId = 0;
		        		int sSchedule = 0;
	        			int intBlockType = strOutput.indexOf("block_type", sBlockType);
		        		int intBlockId = strOutput.indexOf("block_id", sBlockId);
		        		int intSchedule = strOutput.indexOf("schedule", sSchedule);
		        		if (intBlockId != -1)
		        		{
		        			String strBlockType = strOutput.substring(intBlockType+14, intBlockId-4);
		        			String strBlockId = strOutput.substring(intBlockId+11, intSchedule-3);
		        			if(strBlockType.equals("free"))
		        			{
		        				objDictionary.remove("strFreeTimeRateBlockId");objDictionary.put("strFreeTimeRateBlockId", strBlockId);
		        				Reporter.log("The value (" + strBlockId + ") was stored as variable name (strFreeTimeRateBlockId)"+"");
		        			}
		        			else if(strBlockType.equals("fixed"))
		        			{
		        				objDictionary.remove("strFixedTimeRateBlockId");objDictionary.put("strFixedTimeRateBlockId", strBlockId);
		        				Reporter.log("The value (" + strBlockId + ") was stored as variable name (strFixedTimeRateBlockId)"+"");
		        			}
		        		}
		        		if (intBlockId == -1) {
							break;
						}
		        		sBlockType = intBlockType + 2;
		        		sBlockId = intBlockId + 2;
		        		sSchedule = intSchedule + 2;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    	}catch(Exception e){}
    }
	public void METER_RemoveAllRateBlocks(Map<String, String> objDictionary)
	{
		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines) {
		        	if (line.charAt(0) != '{') continue;
		        	JSONParser jsonParser = new JSONParser();
		        	JSONObject jsonObject = (JSONObject) jsonParser.parse(line.toString().replace("}q", "}"));
		        	String blockId = jsonObject.get("block_id").toString();
		        	if (!blockId.equals("-1"))
		        	{
		        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "parking_delete_rate_blk.py " + blockId);
		        	}
		        	else
		        	{System.out.println("Ignoring Block #: " + blockId);}
	        }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
    		} catch(Exception e) {System.err.println(e.getMessage());}
    }



	//************************************************************************************************************************************************************************************
	//Park Spot
	//************************************************************************************************************************************************************************************
	public void METER_ParkSpot(Map<String, String> objDictionary,Session sessionMeter,String strSpotNumber,String strHostType)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strVirtualMeter.equals("False"))
		{
			String strMeterUser = "";
			if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}
			else if(strHostType.contains("Remote")){strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
			String strVehicleParkType = objDictionary.get("strVehicleParkType");
			if(strVehicleParkType.equals("Park")) {strVehicleParkType = "p";}else {strVehicleParkType = "o";}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"testauto_vehicle_detection_control.py -"+strVehicleParkType+" SPOT_"+strSpotNumber);
			clsMeter.GlobalWait(objDictionary, null, sessionMeter,"{WaitUntilMeterVariableBegunEqualTrue} NA", 65, strSpotNumber,strHostType);
		}
		else
		{
			clsMeter.SENTRYMETER_StoreParkingSessionTime(objDictionary);
		}
	}
	public void METER_ParkSpot(Map<String, String> objDictionary,String strSpotNumber, String strHostType)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>"+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strConciergeTestCase = objDictionary.get("strConciergeTestCase");if(strConciergeTestCase == null){strConciergeTestCase = "False";}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strVirtualMeter.equals("False"))
		{
			String strHost = "";
			String strMeterUser = "";
			if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
			else{strHost = objDictionary.get("strRemoteHost"+strSpotNumber);strMeterUser = objDictionary.get("strRemoteUser"+strSpotNumber);}
			String strVehicleParkType = objDictionary.get("strVehicleParkType");
			if(strMeterUser.equals("seco")){strVehicleParkType = "occupy";}
			//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py"); 
			if(strConciergeTestCase.equals("True"))			{
				strVehicleParkType = "park";
			}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_reset_spot_"+strSpotNumber+".py");
			try {Thread.sleep(2000);}catch (Exception e) {}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
			clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterVariableBegunEqualTrue} NA", 120, strSpotNumber,strHostType);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a").withZone(ZoneId.systemDefault()); // or a specific zone
		String strParkedTime = formatter.format(Instant.now().truncatedTo(ChronoUnit.MINUTES));
		objDictionary.put("strParkedTime", strParkedTime);
	}

	//************************************************************************************************************************************************************************************
	//Park Spot
	//************************************************************************************************************************************************************************************
	public void METER_ParkSpotBySpot(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber, String strHostType)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strVirtualMeter.equals("False"))
		{
			String strVehicleParkType = objDictionary.get("strVehicleParkType");
	    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "testautof_touch_screen.py");
	    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
	    	clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualTrue} "+strSpotNumber, 40, strSpotNumber,"Local");
		}
		else
		{clsMeter.SENTRYMETER_StoreParkingSessionTime(objDictionary);}
	}
	public void METER_ParkSpotBySpot(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strVirtualMeter.equals("False"))
		{
			String strHost = objDictionary.get("strHost");
		    	String strVehicleParkType = objDictionary.get("strVehicleParkType");
		    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
		    	clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualTrue} "+strSpotNumber, 40, strSpotNumber,"Local");
		}
		else
		{clsMeter.SENTRYMETER_StoreParkingSessionTime(objDictionary);}
	}
	//************************************************************************************************************************************************************************************

	//************************************************************************************************************************************************************************************
	//Park Spot With License Plate
	//************************************************************************************************************************************************************************************
	public void METER_ParkSpotWithLicensePlate(Map<String, String> objDictionary, String strLicensePlateNumber)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");String strSpotNumber = objDictionary.get("strSpotNumber");
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
    	//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_reset_spot_"+strSpotNumber+".py "+strLicensePlateNumber);
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_park_spot_"+strSpotNumber+".py "+strLicensePlateNumber);
    	clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterVariableBegunEqualTrue} NA", 40,strSpotNumber,"Local");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a").withZone(ZoneId.systemDefault()); // or a specific zone
		String strParkedTime = formatter.format(Instant.now().truncatedTo(ChronoUnit.MINUTES));
		objDictionary.put("strParkedTime", strParkedTime);
    }
	public void METER_ParkSpotValidateNoParkingSessionCreated(Map<String, String> objDictionary,WebDriver driver)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
    	String strVehicleParkType = objDictionary.get("strVehicleParkType");
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
    	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
    	clsMeter.METER_MeterWaitWithMessage(objDictionary, 30, "Waiting to see if parking session is created");
    	String strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,"1","Local");
    	if(strBegunVariable.equals("False"))
    	{Reporter.log("As expected no parking session was created");}
    	else
    	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unexpect parking session was created");}
	}



	//************************************************************************************************************************************************************************************
	//Exit Spot
	//************************************************************************************************************************************************************************************
	public void METER_ExitSpot(Map<String, String> objDictionary, WebDriver driver, Session sessionMeter, String strSpotNumber, String strHostType)
	{
		Meter clsMeter = new Meter();
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		String strVehicleDepartType = "";
		String strVirtualMeter = objDictionary.get("strVirtualMeter"); if(strVirtualMeter == null) {strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			if(strMeterUser.equals("seco")){strVehicleDepartType = "e";}else{strVehicleDepartType = "l";}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_"+Integer.parseInt(strSpotNumber));
			clsMeter.GlobalWait(objDictionary, driver,sessionMeter,"{WaitUntilMeterVariableBegunEqualFalse} NA",30,strSpotNumber,strHostType);
		}
	}
	public void METER_ExitSpot(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		Meter clsMeter = new Meter();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strVehicleDepartType = objDictionary.get("strVehicleDepartType");
		if(strMeterUser.equals("seco")){strVehicleDepartType = "empty";}else{strVehicleDepartType = "leave";}
		String strVirtualMeter = objDictionary.get("strVirtualMeter"); if(strVirtualMeter == null) {strVirtualMeter = "False";}
		String strFreeValue = METER_GetMeterFreeValue(objDictionary, driver, strHostType);
		String strScanValue = "PARKING_STATE_VEHICLE_LEFT";
		if(strFreeValue.equals("True")){strScanValue = "PARKING_STATE_VEHICLE_LEFT -> PARKING_STATE_FREE_EMPTY";}
		if (strVirtualMeter.equals("False"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleDepartType+"_spot_"+strSpotNumber+".py");
			clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, strSpotNumber,strHostType);
		}
	}
	//************************************************************************************************************************************************************************************

	//************************************************************************************************************************************************************************************
	//ExitSpotAfterRejectedViolation
	//************************************************************************************************************************************************************************************
	public void METER_ExitSpotAfterRejectedViolation(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber,String strHostType)
	{
		Meter clsMeter = new Meter();
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		String strVehicleDepartType = objDictionary.get("strVehicleDepartType");
		String strVehicleParkType = objDictionary.get("strVehicleParkType");
		String strVirtualMeter = objDictionary.get("strVirtualMeter"); if(strVirtualMeter == null) {strVirtualMeter = "False";}
		String strScanValue = "PARKING_STATE_VEHICLE_LEFT";
		String strFreeValue = METER_GetMeterFreeValue(objDictionary,sessionMeter);
		//PARKING_STATE_MAINT_FREE_EMPTY
		if(strFreeValue.equals("True"))
		{
			String strMaintenanceModeEnabled = objDictionary.get("strMaintenanceModeEnabled");if(strMaintenanceModeEnabled == null) {strMaintenanceModeEnabled = "False";}
			if(strMaintenanceModeEnabled.equals("True"))
			{
				String strMaintenanceMode = objDictionary.get("strMaintenanceMode");
				if(strMaintenanceMode.equals("FreeParking"))
				{
					strScanValue = "PARKING_STATE_MAINT_FREE_EMPTY";
				}
				else
				{
					strScanValue = "PARKING_STATE_FREE_EMPTY";
				}
			}
			else
			{
				strScanValue = "PARKING_STATE_FREE_EMPTY";
			}
		}
		objDictionary.put("strScanValue", strScanValue);
		if (strVirtualMeter.equals("False"))
		{
			HttpConnections clsHttpConnections = new HttpConnections();
      		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
      		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -o SPOT_"+Integer.parseInt(strSpotNumber));
      			try {Thread.sleep(3000);}catch (Exception e) {}
      		}
      		else if (strParkingSpotState.contains("PARKING_STATE_MAINT_FREE_OCCUPIED"))
      		{
      			objDictionary.put("strScanValue", "PARKING_STATE_MAINT_FREE_EMPTY");
      		}
			if(strMeterUser.equals("seco")){strVehicleDepartType = "e";}else{strVehicleDepartType = "l";}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_"+Integer.parseInt(strSpotNumber));
			clsMeter.GlobalWait(objDictionary,driver,sessionMeter,"{WaitUntilMeterVariableBegunEqualFalse} NA",30,strSpotNumber,strHostType);
		}
	}
	public void METER_ExitSpotAfterRejectedViolation(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		Meter clsMeter = new Meter();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strVehicleParkType = objDictionary.get("strVehicleParkType");if(strMeterUser.equals("seco")){strVehicleParkType = "occupy";}else{strVehicleParkType = "park";}
    	String strVehicleDepartType = objDictionary.get("strVehicleDepartType");
		if(strMeterUser.equals("seco")){strVehicleDepartType = "empty";}else{strVehicleDepartType = "leave";}
		String strReservationTestCase = objDictionary.get("strReservationTestCase"); if(strReservationTestCase == null) {strReservationTestCase = "False";}
		String strConciergeTestCase = objDictionary.get("strConciergeTestCase"); if(strConciergeTestCase == null) {strConciergeTestCase = "False";}
		String strPayByPlate = objDictionary.get("strPayByPlate"); if(strPayByPlate == null) {strPayByPlate = "False";}
		if(strReservationTestCase.equals("True")||strConciergeTestCase.equals("True")||strPayByPlate.equals("True"))
		{
			strVehicleDepartType = "leave";
		}
		String strVirtualMeter = objDictionary.get("strVirtualMeter"); if(strVirtualMeter == null) {strVirtualMeter = "False";}
		String strScanValue = "PARKING_STATE_VEHICLE_LEFT";
		objDictionary.put("strScanValue",strScanValue);
		if (strVirtualMeter.equals("False"))
		{
			//This value is used so active Sentry Link session doesn't get lost
			String strCheckIfSpotPresumedOccupied = objDictionary.get("strCheckIfSpotPresumedOccupied"); if(strCheckIfSpotPresumedOccupied == null) {strCheckIfSpotPresumedOccupied = "True";}
			if(strCheckIfSpotPresumedOccupied.equals("True"))
			{
				HttpConnections clsHttpConnections = new HttpConnections();
				String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"1");
				if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
	      		{
	      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
	    			try {Thread.sleep(15000);}catch (Exception e) {}
	      		}
			}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleDepartType+"_spot_"+strSpotNumber+".py");
			clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, strSpotNumber,strHostType);
		}
	}
	//**************************************************************************************************************************************************************************************************************/
	//Exit Spot(s)
	//**************************************************************************************************************************************************************************************************************/
	public void METER_ExitBothSpots(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		String strHost = "";String strMeterUser = "";String strBeginValue = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		if(strMeterUser == null) {UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The variable MeterUser was not set in the GLobalPay class GetMeterProperties method","False");}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			String strFreeValue = METER_GetMeterFreeValue(objDictionary,sessionMeter);
			String strScanValue = "PARKING_STATE_VEHICLE_LEFT";if(strFreeValue.equals("True")){strScanValue = "PARKING_STATE_VEHICLE_LEFT";}
			//Used when scanning the meter logs right after executing a python script.-METER_ExecutePythonScriptAgainstMeter
			objDictionary.put("strScanValue", strScanValue);
			String strVehicleDepartType = objDictionary.get("strVehicleDepartType");if(strMeterUser.equals("seco")){strVehicleDepartType = "e";}else{strVehicleDepartType = "l";}
			String strVehicleParkType = objDictionary.get("strVehicleParkType");if(strMeterUser.equals("seco")){strVehicleParkType = "o";}else{strVehicleParkType = "p";}
	    	//Spot #1
	    	strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,"1",strHostType);
	    	if(strBeginValue.equals("True"))
	    	{
	    		HttpConnections clsHttpConnections = new HttpConnections();
	      		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"1");
	      		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
	      		{
	      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleParkType+" SPOT_1");
	      			try {Thread.sleep(15000);}catch (Exception e) {}
	      		}
	    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_1");
	    		clsMeter.GlobalWait(objDictionary, driver, sessionMeter, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, "1",strHostType);
		  	}else{Reporter.log("The Begin value was set already False for spot 1-host-"+strHost);}
	    	//Spot #2
	    	String strNumberOfStops = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
	    	if(Integer.parseInt(strNumberOfStops) >= 2)
	    	{
		    	strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,"2",strHostType);
		    	if(strBeginValue.equals("True"))
		    	{
		    		HttpConnections clsHttpConnections = new HttpConnections();
		    		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"2");
		    		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
		      		{
		      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleParkType+" SPOT_2");
		      			try {Thread.sleep(5000);}catch (Exception e) {}
		      		}
		    		//Test is failing when strParkingSpotState = PARKING_STATE_VIOLATION
 		    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_2");
		    		clsMeter.GlobalWait(objDictionary,driver,sessionMeter,"{WaitUntilMeterVariableBegunEqualFalse} NA",30,"2",strHostType);
		    	}else{Reporter.log("The Begin value was set already False for spot 2-host-"+strHost);}
	    	}
		}
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}
	public void METER_ExitBothSpotsValidateMaintenanceModes(Map<String, String> objDictionary,WebDriver driver, Session sessionMeter,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		String strHost = "";String strMeterUser = "";String strBeginValue = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		if(strMeterUser == null) {UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The variable MeterUser was not set in the GLobalPay class GetMeterProperties method","False");}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			String strFreeValue = METER_GetMeterFreeValue(objDictionary,sessionMeter);
			String strScanValue = "PARKING_STATE_VEHICLE_LEFT";if(strFreeValue.equals("True")){strScanValue = "PARKING_STATE_VEHICLE_LEFT";}
			//Used when scanning the meter logs right after executing a python script.-METER_ExecutePythonScriptAgainstMeter
			objDictionary.put("strScanValue", strScanValue);
			String strVehicleDepartType = objDictionary.get("strVehicleDepartType");if(strMeterUser.equals("seco")){strVehicleDepartType = "e";}else{strVehicleDepartType = "l";}
	    	String strVehicleParkType = objDictionary.get("strVehicleParkType");if(strMeterUser.equals("seco")){strVehicleParkType = "o";}else{strVehicleParkType = "p";}
			//Spot #1
	    	strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,"1",strHostType);
	    	if(strBeginValue.equals("True"))
	    	{
	    		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"1");
	      		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
	      		{
	      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleParkType+" SPOT_1");
	      			try {Thread.sleep(3000);}catch (Exception e) {}
	      		}
	    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_1");
		    	clsMeter.GlobalWait(objDictionary, driver, sessionMeter, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, "1",strHostType);
		  	}else{Reporter.log("The Begin value was set already False for spot 1-host-"+strHost);}

	    	//Validate Maintenance Mode Match
	    	String strCurrentMeterMaintenanceMode = METER_GetMeterInMaintValue(objDictionary, sessionMeter,"1");
	    	String strCurrentSentryLinkMaintenanceMode = clsHttpConnections.HTTPCONNECTIONS_IsMeterSpotMainteanceMode(objDictionary, strHostType, "1");
	    	if(!strCurrentMeterMaintenanceMode.toLowerCase().equals(strCurrentSentryLinkMaintenanceMode.toLowerCase()))
	    	{
	    		System.out.println("MIH");
	    	}
	    	//Spot #2
	    	String strNumberOfStops = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
	    	if(Integer.parseInt(strNumberOfStops) >= 2)
	    	{
		    	strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,"2",strHostType);
		    	if(strBeginValue.equals("True"))
		    	{
		    		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"2");
		      		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
		      		{
		      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleParkType+" SPOT_1");
		      			try {Thread.sleep(3000);}catch (Exception e) {}
		      		}
		    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_2");
			    	clsMeter.GlobalWait(objDictionary,driver,sessionMeter,"{WaitUntilMeterVariableBegunEqualFalse} NA",30,"2",strHostType);
		    	}else{Reporter.log("The Begin value was set already False for spot 2-host-"+strHost);}
	    	}
	    	//Validate Maintenance Mode Match
	    	strCurrentMeterMaintenanceMode = METER_GetMeterInMaintValue(objDictionary, sessionMeter,"1");
	    	strCurrentSentryLinkMaintenanceMode = clsHttpConnections.HTTPCONNECTIONS_IsMeterSpotMainteanceMode(objDictionary, strHostType, "1");
	    	if(!strCurrentMeterMaintenanceMode.toLowerCase().equals(strCurrentSentryLinkMaintenanceMode.toLowerCase()))
	    	{
	    		System.out.println("MIH");
	    	}
		}
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}
	public void METER_ExitBothSpots(Map<String, String> objDictionary, WebDriver driver, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		Meter clsMeter = new Meter();
		//Method variables
		String strHost = "";String strMeterUser = "";String strBeginValue = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost1");strMeterUser = objDictionary.get("strRemoteUser1");}
		//Validate MeterUser Was Set
		if(strMeterUser == null) {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The variable MeterUser was not set in the GLobalPay class GetMeterProperties method");}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		String strFreeValue = METER_GetMeterFreeValue(objDictionary, driver, strHostType);
		//String strScanValue = "PARKING_STATE_VEHICLE_LEFT";if(strFreeValue.equals("True")){strScanValue = "PARKING_STATE_VEHICLE_LEFT";}
		String strScanValue = "VEHICLE_LEFT";if(strFreeValue.equals("True")){strScanValue = "VEHICLE_LEFT";}
		//Used when scanning the meter logs right after executing a python script.-METER_ExecutePythonScriptAgainstMeter
		objDictionary.put("strScanValue", strScanValue);
		if (strVirtualMeter.equals("False"))
		{
			String strBeginningSpotNumber = objDictionary.get("strSpotNumber");
			String strVehicleParkType = objDictionary.get("strVehicleParkType");;if(strVehicleParkType == null) {strVehicleParkType = "park";}
			String strVehicleDepartType = objDictionary.get("strVehicleDepartType");if(strVehicleDepartType == null) {strVehicleDepartType = "leave";}
	    	if(strMeterUser.equals("seco")){strVehicleDepartType = "empty";}else{strVehicleDepartType = "leave";}
	    	String strReservationTestCase  = objDictionary.get("strReservationTestCase");if(strReservationTestCase == null) {strReservationTestCase = "False";}
	    	HttpConnections clsHttpConnections = new HttpConnections();
	    	String strEnrolledState = clsHttpConnections.GET_ConciergeEnrolledState(objDictionary, "parker");
	    	if(strReservationTestCase.equals("True")||strEnrolledState.equals("true"))
	    	{
	    		strVehicleDepartType = "leave";
	    	}
	    	objDictionary.remove("strSpotNumber"); objDictionary.put("strSpotNumber", "1");
	    	strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,"1",strHostType);
	    	if(strBeginValue.equals("True"))
	    	{
	    		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"1");
	      		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
	      		{
	      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_1.py");
	      			try {Thread.sleep(15000);}catch (Exception e) {}
	      		}
	    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_reset_spot_1.py");
	    		try {Thread.sleep(3000);}catch (Exception e) {}
	    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleDepartType+"_spot_1.py");
	    		{clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, "1",strHostType);}
	    	}
	    	else
	    	{Reporter.log("The Begin value was set already False for spot 1-host-"+strHost);}
	    	String strNumberOfStops = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, null,strHostType);
	    	int intNumberOfStops = Integer.parseInt(strNumberOfStops);
	    	if(intNumberOfStops >= 2)
	    	{
		    	//objDictionary.remove("strSpotNumber"); objDictionary.put("strSpotNumber", "2");//Remove
	    		strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,"2", strHostType);
		    	if(strBeginValue.equals("True"))
		    	{
		    		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,"2");
		    		if(strParkingSpotState.contains("PRESUMED_OCCUPIED"))
		      		{
		    			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_leave_spot_2.py");
		      			try {Thread.sleep(15000);}catch (Exception e) {}
		      		}
		    		//There is a Issue here, I shouldn't have to park and exit when the begun state is True
		    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleDepartType+"_spot_2.py");
		    		clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, "2",strHostType);
			    	objDictionary.remove("strSpotNumber"); objDictionary.put("strSpotNumber", strBeginningSpotNumber);
		    	}
		    	else
		    	{Reporter.log("The Begin value was set already False for spot 2-host-"+strHost);}
	    	}
		}
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}
	//************************************************************************************************************************************************************************************
	public void METER_ExitAllSpots(Map<String, String> objDictionary,WebDriver driver, Session sessionMeter,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println("Start: "+strMethodName);
		Stopwatch timer = Stopwatch.createStarted();
		String strHost = "";String strMeterUser = "";String strBeginValue = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		if(strMeterUser == null) {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The variable MeterUser was not set in the GLobalPay class GetMeterProperties method");}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			String strFreeValue = METER_GetMeterFreeValue(objDictionary,sessionMeter);
			String strScanValue = "PARKING_STATE_VEHICLE_LEFT";if(strFreeValue.equals("True")){strScanValue = "PARKING_STATE_VEHICLE_LEFT";}objDictionary.put("strScanValue", strScanValue);
			String strVehicleDepartType = objDictionary.get("strVehicleDepartType");if(strMeterUser.equals("seco")){strVehicleDepartType = "e";}else{strVehicleDepartType = "l";}
	    	String strNumberOfStops = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
			int intSpotCounter = 1;
			do
			{
				strBeginValue = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,Integer.toString(intSpotCounter),strHostType);
		    	if(strBeginValue.equals("True"))
		    	{
		    		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "testauto_vehicle_detection_control.py -"+strVehicleDepartType+" SPOT_"+intSpotCounter);
		    		clsMeter.GlobalWait(objDictionary, driver, sessionMeter, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, Integer.toString(intSpotCounter),strHostType);
			  	}else{Reporter.log("The Begin value was set already False for spot "+intSpotCounter+"-host-"+strHost);}
		    	intSpotCounter++;
			}while (Integer.parseInt(strNumberOfStops) > intSpotCounter);//Change the + 2 to +1
		}
		System.out.println("Method ("+strMethodName+") took: " + timer.stop());
	}

	public void METER_SetMeterEndTime(Map<String, String> objDictionary)
	{
		//Meter End Time
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String strMeterEndTime = dateFormatGmt.format(new Date());
		Reporter.log("<font color='Green'>Meter End Time "+strMeterEndTime+"</font>");
		objDictionary.remove("strMeterEndTime");objDictionary.put("strMeterEndTime", strMeterEndTime);

		//TEST CODE
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals(""))
//		{
//			Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed, remove the AssociatedBug</font>");
//		}
	}
	public void METER_SetMeterStartTime(Map<String, String> objDictionary)
	{
		//Meter Start Time
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String strMeterStartTime = dateFormatGmt.format(new Date());
		Reporter.log("<font color='Green'>Meter End Time "+strMeterStartTime+"</font>");
		objDictionary.remove("strStartEndTime");objDictionary.put("strMeterStartTime", strMeterStartTime);
	}


	public String GetMeterMaxTime(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber)
	{
		//***
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strNumberOfMeterSpots = objDictionary.get("strNumberOfMeterSpots");
		String strMeterUser = objDictionary.get("strMeterUser");
    	String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        int intRowNumber = lines.length - 1;
	        String strSpotVariables = lines[intRowNumber];
	        Reporter.log("GetMeterMax: "+strSpotVariables);
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        String strMaxTime = strSpotVariables.substring(strSpotVariables.indexOf("MaxTime,")+8, strSpotVariables.indexOf("|MaxRemaining"));
	        Reporter.log("Dump Session MaxTime: "+strMaxTime);
	        return strMaxTime;
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }
	public void METER_SetBegunEqualFalse(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber, String strHostType)
	{
		//Classes
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
		//String strHostType
		String strHost = "";
		if(strHostType.equals("Local"))
		{strHost = objDictionary.get("strHost");}
		else
		{strHost = objDictionary.get("strRemoteHost");}
		String strVehicleParkType = objDictionary.get("strVehicleParkType");
		String strVehicleDepartType = objDictionary.get("strVehicleDepartType");
		//Get Current Begun Value
		String strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,strSpotNumber,strHostType);
		if(strBegunVariable.equals("False")){Reporter.log("The meter Begun value was set already set to False");return;}
		//Park Spot One
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
		try {Thread.sleep(15000);}catch (Exception e) {}
		//Leave Spot One
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleDepartType+"_spot_"+strSpotNumber+".py");
		strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,strSpotNumber,strHostType);
		if(strBegunVariable.equals("False")){Reporter.log("The meter Begun value was set already set to False");}
		//Park Spot One
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
		try {Thread.sleep(15000);}catch (Exception e) {}
		//Leave Spot One
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleDepartType+"_spot_"+strSpotNumber+".py");
		WaitUntilMeterVariableBegunEqualFalse(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualFalse} NA", 30, strSpotNumber, strHostType);
		strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,strSpotNumber, strHostType);
		if(strBegunVariable.equals("False")){Reporter.log("The meter Begun value was set already set to False");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"Unable to switch the Begun value to False");}
	}




	//**************************************************************************************************************************************************************************************************************/
	//Get Coin Percent Full
	//**************************************************************************************************************************************************************************************************************/
	public String METER_GetCoinPercentFull(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter)
	{
		String strPythonScriptExisted = "False";
    	try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/coin_percent_full.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        int intRowNumber = lines.length - 1;
	        String strSpotVariables = lines[intRowNumber];
	        in.close();
	        channel.disconnect();
	        Reporter.log("Current Coin Percen Full: "+strSpotVariables);
	        return strSpotVariables;
	    	}catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (coin_percent_full.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }
	public String METER_GetCoinPercentFull(Map<String, String> objDictionary,WebDriver driver)
	{
		//***
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/coin_percent_full.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        int intRowNumber = lines.length - 1;
	        String strSpotVariables = lines[intRowNumber];
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        Reporter.log("Current Coin Percen Full: "+strSpotVariables);
	        return strSpotVariables;
	    	}catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }

	//***********************/
	//Meter Screen Shots
	//***********************/
	public void METER_DeleteMeterScreenShotLocally(Map<String, String> objDictionary, String strTestCaseName)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		File directory = new File(".");
		String strPath = "";
		try {strPath = directory.getCanonicalPath() +"/MeterScreenShots/";}catch (IOException e){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null, e+"-"+strMethodName);}
		File file = new File(strPath+"/"+strTestCaseName+".png");
		if (file.exists())
		{
			if(file.delete()){Reporter.log("<font color='green'>The Meter Screen Shot ("+strTestCaseName+".png) was deleted</font>");}
	        else
	        {Reporter.log("<font color='orange'>Unable to Delete Meter Screen Shot ("+strTestCaseName+".png) was deleted</font>");}
		}
		else
		{Reporter.log("<font color='green'>The Meter Screen Shot ("+strTestCaseName+".png) did not exist</font>");}
	}

	//**************************************************************************************************************************************************************************************************************/
	//Copy Meter Screen Shot Locally
	//**************************************************************************************************************************************************************************************************************/
	public void METER_CopyMeterScreenShotLocallyNoScreenTouch(Map<String, String> objDictionary,Session sessionMeter,String strTestCaseName)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strHost = objDictionary.get("strHost");


		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,sessionMeter);
  		if(strForcedMulti.equals("true"))
  		{
  			if(!strTestCaseName.contains("NA"))
  			{
  				String strLastGlobalSpace = objDictionary.get("");
  				if(strLastGlobalSpace != null)
  				{
  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strLastGlobalSpace);
  				}
  			}
  		}
		if(strMeterUser.equals("root"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHost,"sys_get_screen_snapshot.sh");}
		else
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","sys_get_screen_snapshot.sh /home/seco/screenshot.png");}
		File directory = new File(".");
		String strPath = "";
		String strDirectory = "";
		try {strDirectory = directory.getCanonicalPath();}
		catch (IOException e)
		{
			System.out.println("Directory Path: "+strDirectory);
			clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Unable to get directory path-"+e+"-"+strMethodName,"True");
		}
		strPath = strDirectory +"/MeterScreenShots";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir()) {System.out.println("Directory is created!");}else {System.out.println("Failed to create directory!");}
		}
		String strFullMeterScreenShotPath = strPath+"/"+strTestCaseName+".png";
		String strCommand = "";
		if(strMeterUser.equals("root")){strCommand = "sshpass -p "+strPassword+" scp -r root@"+strHost+":/var/lib/sentry/screenshot.png "+strFullMeterScreenShotPath;}
		else{strCommand = "sshpass -p "+strPassword+" scp -r seco@"+strHost+":/home/seco/screenshot.png "+strFullMeterScreenShotPath;}
		try
		{
			String[] command1 = {"sh","-c",strCommand};
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = null;
			String strErrorMessage = "";
			while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
			if(!strErrorMessage.equals(""))
			{
				Reporter.log("<font color='goldenrod'>"+strErrorMessage+"-"+strMethodName+"</font>");
			}
		}
		catch (IOException e)
		{
			Reporter.log("<font color='red'>"+e+" "+strMethodName+"</font>");
		}
		try
		{
			//Upload Meter Screenshot Imgur
			file = new File(strFullMeterScreenShotPath);
			String strImageURL = ImgurClient.getInstance().UploadImage(file, strTestCaseName+"_" + Instant.now());
			if(strImageURL != null)
			{
				if (strImageURL.startsWith("http"))
				{
					Reporter.log("<a href=" + strImageURL + "/>Meter Screen Shot</a>");
					Reporter.log("<img src=\"" + strImageURL + "\" alt=\"\"/><br />");
				}
			}
		}
		catch (Exception e) {}
	}
	public void METER_CopyMeterScreenShotLocally(Map<String, String> objDictionary,Session sessionMeter,String strTestCaseName)
	{
		strTestCaseName.replace("", "_");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strHost = objDictionary.get("strHost");
		//Doesn't work with Remote 2
		String strCurrentAction = objDictionary.get("strCurrentAction");if(strCurrentAction == null){strCurrentAction = "";}
		if(strCurrentAction.equals("ESNRSRM")||strCurrentAction.equals("ESNLSRM"))
    	{
			strHost = objDictionary.get("strRemoteHost1");
			System.out.println("MIH");
    	}
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,sessionMeter);
  		String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,sessionMeter,"1");
  		if(strForcedMulti.equals("true") && strCurrentUIState.equals("SCREEN_MULTI_SELECT_SPACE"))
  		{
  			if(!strTestCaseName.contains("NA"))
  			{
  				String strLastGlobalSpace = objDictionary.get("strLastGlobalSpace");
  				if(strLastGlobalSpace != null)
  				{
  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  					try {Thread.sleep(2000);}catch (Exception e) {}
  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strLastGlobalSpace);
  				}
  			}
  		}
		if(strMeterUser.equals("root"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHost,"sys_get_screen_snapshot.sh");}
		else
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,"Local","sys_get_screen_snapshot.sh /home/seco/screenshot.png");}
		File directory = new File(".");
		String strPath = "";
		String strDirectory = "";
		try {strDirectory = directory.getCanonicalPath();}
		catch (IOException e)
		{
			System.out.println("Directory Path: "+strDirectory);
			clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Unable to get directory path-"+e+"-"+strMethodName,"True");
		}
		strPath = strDirectory +"/MeterScreenShots";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir()) {System.out.println("Directory is created!");}else {System.out.println("Failed to create directory!");}
		}
		String strFullMeterScreenShotPath = strPath+"/"+strTestCaseName+".png";
		String strCommand = "";
		if(strMeterUser.equals("root"))
		{strCommand = "sshpass -p "+strPassword+" scp -r root@"+strHost+":/var/lib/sentry/screenshot.png "+strFullMeterScreenShotPath;}
		else
		{strCommand = "sshpass -p "+strPassword+" scp -r seco@"+strHost+":/home/seco/screenshot.png "+strFullMeterScreenShotPath;}
		try
		{
			String[] command1 = {"sh","-c",strCommand};
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = null;
			String strErrorMessage = "";
			while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
			if(!strErrorMessage.equals(""))
			{
				Reporter.log("<font color='goldenrod'>"+strErrorMessage+"-"+strMethodName+"</font>");
			}
		}
		catch (IOException e)
		{
			Reporter.log("<font color='red'>"+e+" "+strMethodName+"</font>");
		}
		try
		{
			//Upload Meter Screenshot Imgur
			file = new File(strFullMeterScreenShotPath);
//			String strImageURL = ImgurClient_Old.getInstance().UploadImage1(file, strTestCaseName+"_" + Instant.now());
			String strImageURL = ImgurClient.getInstance().UploadImage(file, strTestCaseName+"_" + Instant.now());
			if(strImageURL != null)
			{
				if (strImageURL.startsWith("http"))
				{
					Reporter.log("<a href=" + strImageURL + "/>Meter Screen Shot</a>");
					Reporter.log("<img src=\"" + strImageURL + "\" alt=\"\"/><br />");
				}
			}
		}
		catch (Exception e) {}
	}
	public void METER_CopyMeterScreenShotLocally(Map<String, String> objDictionary, String strTestCaseName)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strHost = objDictionary.get("strHost");
		strTestCaseName = strTestCaseName.replace(" ", "_");
		if(strHost != null)
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
			try {Thread.sleep(2000);}catch (Exception e) {}
			String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null,"Local");
			String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
			if(strForcedMulti.equals("true") && strCurrentUIState.equals("SCREEN_MULTI_SELECT_SPACE"))
	  		{
	  			if(!strTestCaseName.contains("NA"))
	  			{
	  				String strLastGlobalSpace = objDictionary.get("strLastGlobalSpace");
	  				if(strLastGlobalSpace != null)
	  				{
	  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
	  					try {Thread.sleep(2000);}catch (Exception e) {}
	  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strLastGlobalSpace);
	  					try {Thread.sleep(5000);}catch (Exception e) {}
	  					if(strMeterUser.equals("root"))
	  					{
	  						clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "sys_get_screen_snapshot.sh");
	  					}
	  					else
	  					{
	  						clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "sys_get_screen_snapshot.sh /home/seco/screenshot.png");
	  					}
	  				}
	  				else
	  				{
	  					String strMeterSpotName = objDictionary.get("strMeterSpotName");
	  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
	  					try {Thread.sleep(2000);}catch (Exception e) {}
	  					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterSpotName);
	  					try {Thread.sleep(5000);}catch (Exception e) {}
	  					if(strMeterUser.equals("root"))
	  					{
	  						clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "sys_get_screen_snapshot.sh");
	  					}
	  					else
	  					{
	  						clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "sys_get_screen_snapshot.sh /home/seco/screenshot.png");
	  					}
	  				}
	  			}
	  		}
	  		else
	  		{
				if(strMeterUser.equals("root"))
				{
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
					try {Thread.sleep(2000);}catch (Exception e) {}
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "sys_get_screen_snapshot.sh");
				}
				else
				{
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "sys_get_screen_snapshot.sh /home/seco/screenshot.png");
				}
	  		}
			File directory = new File(".");
			String strPath = "";
			String strDirectory = "";
			try {strDirectory = directory.getCanonicalPath();}
			catch (IOException e)
			{
				System.out.println("Directory Path: "+strDirectory);
				clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unable to get directory path-"+e+"-"+strMethodName);
			}
			strPath = strDirectory +"/MeterScreenShots";
			File file = new File(strPath);
			if (!file.exists())
			{
				if (file.mkdir()) {System.out.println("Directory is created!");}else {System.out.println("Failed to create directory!");}
			}
			String strFullMeterScreenShotPath = strPath+"/"+strTestCaseName+".png";
			String strCommand = "";
			if(strMeterUser.equals("root")){strCommand = "sshpass -p "+strPassword+" scp -O -r root@"+strHost+":/var/lib/sentry/screenshot.png "+strFullMeterScreenShotPath;}
			else{strCommand = "sshpass -p "+strPassword+" scp -O -r seco@"+strHost+":/home/seco/screenshot.png "+strFullMeterScreenShotPath;}
			try
			{
				String[] command1 = {"sh","-c",strCommand};
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{
					Reporter.log("<font color='goldenrod'>"+strErrorMessage+"-"+strMethodName+"</font>");
				}
			}
			catch (IOException e)
			{
				Reporter.log("<font color='red'>"+e+" "+strMethodName+"</font>");
			}
			try
			{
				//Upload Meter Screenshot Imgur
				file = new File(strFullMeterScreenShotPath);
				String strImageURL = ImgurClient.getInstance().UploadImage(file, strTestCaseName+"_" + Instant.now());
				if(strImageURL != null)
				{
					if (strImageURL.startsWith("http"))
					{
						Reporter.log("<a href=" + strImageURL + "/>Meter Screen Shot</a>");
						Reporter.log("<img src=\"" + strImageURL + "\" alt=\"\"/><br />");
					}
				}
			}
			catch (Exception e) {}
		}
	}

	//***************************************************************************************************************************************************************************************
	//Get Silver Bullet Version
	//***************************************************************************************************************************************************************************************
	public String SENTRYMETER_GetSilverBulletVersion(Map<String, String> objDictionary,Session sessionMeter)
	{
		Meter clsMeter = new Meter();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			String strSilverBullet = objDictionary.get("strSilverBullet");
			String strMeterName = objDictionary.get("strMeterName");
			if(strSilverBullet == null){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Silver Bullet IP has not been added Global-GetMeterProperties for Meter-"+strMeterName);}
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("dpkg -l silverbullet | tail -1");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        in.close();
		        channel.disconnect();
		        System.out.println("Silver Bullet Version: "+lines[0].substring(lines[0].indexOf("silverbullet")+12, lines[0].indexOf("all")).trim());
				String strSilverBulletVersion = lines[0].substring(lines[0].indexOf("silverbullet")+12, lines[0].indexOf("all")).trim();
		        objDictionary.remove("strSilverBulletVersion");objDictionary.put("strSilverBulletVersion", strSilverBulletVersion);
		        return strSilverBulletVersion;
		    }
		    catch(Exception e)
		    {
//		    	clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,"The script (dpkg -l silverbullet | tail -1) did not execute correctly on the meter");
		    }
		}
        return "";
	}
	public String SENTRYMETER_GetSilverBulletVersion(Map<String, String> objDictionary)
	{
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			JSch jsch = new JSch();
			String strSilverBullet = objDictionary.get("strSilverBullet");
			if(strSilverBullet != null)
			{
				String strMeterName = objDictionary.get("strMeterName");
				if(strSilverBullet == null){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Silver Bullet IP has not been added Global-GetMeterProperties for Meter-"+strMeterName);}
				String strMeterUser = objDictionary.get("strMeterUser");
				String user = "";
				if(strMeterUser.equals("root")) {user = "ubuntu";} else {user = "seco";}
				String strPassword = objDictionary.get("strUniquePassword");
				String host = strSilverBullet;
		    	int port=22;
		    	try
		    	{
			    	Session session = jsch.getSession(user, host, port);
			    	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Channel channel=session.openChannel("exec");
			        ((ChannelExec)channel).setCommand("dpkg -l silverbullet | tail -1");
			        channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        InputStream in=channel.getInputStream();
			        channel.connect();
			        //Get Begun Value
			        String s = "";int c;
			        while((c = in.read()) != -1) {s += (char)c;}
			        String[] lines = s.split("\n");
			        in.close();
			        channel.disconnect();
			        session.disconnect();
			        System.out.println("Silver Bullet Version: "+lines[0].substring(lines[0].indexOf("silverbullet")+12, lines[0].indexOf("all")).trim());
					String strSilverBulletVersion = lines[0].substring(lines[0].indexOf("silverbullet")+12, lines[0].indexOf("all")).trim();
			        objDictionary.remove("strSilverBulletVersion");objDictionary.put("strSilverBulletVersion", strSilverBulletVersion);
			        return strSilverBulletVersion;
			    }
			    catch(Exception e)
			    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script (dpkg -l silverbullet | tail -1) did not execute correctly on the meter");}
			}
		}
        return "";
	}

	//***************************************************************************************************************************************************************************************
	//Get SOM Distro Name
	//***************************************************************************************************************************************************************************************
	public String SENTRYMETER_GetSOMDistroName(Map<String, String> objDictionary,Session sessionMeter)
	{
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
	    if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
		    try
		    {
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("cat /etc/os_release");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp = new byte[1200];
		        while(true)
		        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		String GetSOMDistroName = strOutput.substring(strOutput.indexOf("STRO_NAME=") +11, strOutput.indexOf("DISTRO_VERSION")-2);
		        		objDictionary.remove("GetSOMDistroName");objDictionary.put("GetSOMDistroName", GetSOMDistroName);
	        			if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
	        			channel.disconnect();
	        			return GetSOMDistroName;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();
		    }
		    catch(Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script cat /etc/os_release");}
		}
	    return "";
    }
	public String SENTRYMETER_GetSOMDistroName(Map<String, String> objDictionary,WebDriver driver,String strHostType)
	{
		JSch jsch = new JSch();
		String strHost ="";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}

		if(strHost != null)
		{
			String strPassword = objDictionary.get("strUniquePassword");
			String strPythonScriptExisted = "False";
			String strVirtualMeter = objDictionary.get("strVirtualMeter");
		    if(strVirtualMeter == null){strVirtualMeter = "False";}
			if (strVirtualMeter.equals("False"))
			{
				int port=22;
			    try
			    {
			    	Session session = jsch.getSession(strMeterUser, strHost, port);
			    	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Channel channel=session.openChannel("exec");
			        ((ChannelExec)channel).setCommand("cat /etc/os_release");
			        channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        InputStream in=channel.getInputStream();
			        channel.connect();
			        byte[] tmp = new byte[1200];
			        while(true)
			        {
			        	while(in.available()>0)
			        	{
			        		strPythonScriptExisted = "True";
			        		int i=in.read(tmp, 0, 1200);
			        		if(i<0) {
								break;
							}
			        		String strOutput = new String(tmp, 0, i);
			        		String GetSOMDistroName = strOutput.substring(strOutput.indexOf("STRO_NAME=") +11, strOutput.indexOf("DISTRO_VERSION")-2);
			        		objDictionary.remove("GetSOMDistroName");objDictionary.put("GetSOMDistroName", GetSOMDistroName);
		        			if(channel.isClosed()){if(in.available()>0) {
								continue;
							}break;}
		        			channel.disconnect();session.disconnect();
		        			return GetSOMDistroName;
			        	}
			        	if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
			        }
				    channel.disconnect();session.disconnect();
			    }
			    catch(Exception e)
			    {System.out.println(e);}
			    if (strPythonScriptExisted == "False")
			    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script cat /etc/os_release");}
			}
		}
	    return "";
    }

	//***************************************************************************************************************************************************************************************
	//Get SOM DISTRO Version
	//***************************************************************************************************************************************************************************************
	public String SENTRYMETER_GetSOMDistroVersion(Map<String, String> objDictionary,Session sessionMeter)
	{
		String strPythonScriptExisted = "False";
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			 try
		    {
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("cat /etc/os_release");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp = new byte[1200];
		        while(true)
		        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		String SOMDistroVersion = strOutput.substring(strOutput.indexOf("DISTRO_VERSION=") +16, strOutput.indexOf("IMAGE_VERSION=")-2);
		        		objDictionary.remove("SOMDistroVersion");objDictionary.put("strSOMImageValue", SOMDistroVersion);
	        			if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
	        			channel.disconnect();
	        			return SOMDistroVersion;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();
		    }
		    catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The script cat /etc/os_release");}
		}
	    return "";
    }
	public String SENTRYMETER_GetSOMDistroVersion(Map<String, String> objDictionary,WebDriver driver,String strHostType)
	{
		JSch jsch = new JSch();
		String strHost ="";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		if(strHost != null)
		{
			String strPassword = objDictionary.get("strUniquePassword");
			String strPythonScriptExisted = "False";
			String strVirtualMeter = objDictionary.get("strVirtualMeter");
		    if(strVirtualMeter == null){strVirtualMeter = "False";}
			if (strVirtualMeter.equals("False"))
			{
				int port=22;
			    try
			    {
			    	Session session = jsch.getSession(strMeterUser, strHost, port);
			    	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Channel channel=session.openChannel("exec");
			        ((ChannelExec)channel).setCommand("cat /etc/os_release");
			        channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        InputStream in=channel.getInputStream();
			        channel.connect();
			        byte[] tmp = new byte[1200];
			        while(true)
			        {
			        	while(in.available()>0)
			        	{
			        		strPythonScriptExisted = "True";
			        		int i=in.read(tmp, 0, 1200);
			        		if(i<0) {
								break;
							}
			        		String strOutput = new String(tmp, 0, i);
			        		String SOMDistroVersion = strOutput.substring(strOutput.indexOf("DISTRO_VERSION=") +16, strOutput.indexOf("IMAGE_VERSION=")-2);
			        		objDictionary.remove("SOMDistroVersion");objDictionary.put("strSOMImageValue", SOMDistroVersion);
		        			if(channel.isClosed()){if(in.available()>0) {
								continue;
							}break;}
		        			channel.disconnect();session.disconnect();
		        			return SOMDistroVersion;
			        	}
			        	if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
			        }
				    channel.disconnect();session.disconnect();
			    }
			    catch(Exception e)
			    {System.out.println(e);}
			    if (strPythonScriptExisted == "False")
			    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script cat /etc/os_release");}
			}
		}
	    return "False";
    }



	//***************************************************************************************************************************************************************************************
	//Get SOM Image Id
	//***************************************************************************************************************************************************************************************
	public String SENTRYMETER_GetSOMImageId(Map<String, String> objDictionary,Session sessionMeter)
	{
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
	    if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
		    {
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("cat /etc/os_release");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp = new byte[1200];
		        while(true)
		        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		String strSOMImageValue = strOutput.substring(strOutput.indexOf("IMAGE_VERSION=") +15, strOutput.length()-2);
		        		objDictionary.remove("strSOMImageValue");objDictionary.put("strSOMImageValue", strSOMImageValue);
	        			if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
	        			channel.disconnect();
	        			return strSOMImageValue;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();
		    }
		    catch(Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script cat /etc/os_release");}
		}
	    return "";
    }
	public String SENTRYMETER_GetSOMImageId(Map<String, String> objDictionary,WebDriver driver,String strHostType)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		if(strHost != null)
		{
			String strPassword = objDictionary.get("strUniquePassword");
			String strPythonScriptExisted = "False";
			String strVirtualMeter = objDictionary.get("strVirtualMeter");
		    if(strVirtualMeter == null){strVirtualMeter = "False";}
			if (strVirtualMeter.equals("False"))
			{
				int port=22;
			    try
			    {
			    	Session session = jsch.getSession(strMeterUser, strHost, port);
			    	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Channel channel=session.openChannel("exec");
			        ((ChannelExec)channel).setCommand("cat /etc/os_release");
			        channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        InputStream in=channel.getInputStream();
			        channel.connect();
			        byte[] tmp = new byte[1200];
			        while(true)
			        {
			        	while(in.available()>0)
			        	{
			        		strPythonScriptExisted = "True";
			        		int i=in.read(tmp, 0, 1200);
			        		if(i<0) {
								break;
							}
			        		String strOutput = new String(tmp, 0, i);
			        		String strSOMImageValue = strOutput.substring(strOutput.indexOf("IMAGE_VERSION=") +15, strOutput.length()-2);
			        		objDictionary.remove("strSOMImageValue");objDictionary.put("strSOMImageValue", strSOMImageValue);
		        			if(channel.isClosed()){if(in.available()>0) {
								continue;
							}break;}
		        			channel.disconnect();session.disconnect();
		        			return strSOMImageValue;
			        	}
			        	if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
			        }
				    channel.disconnect();session.disconnect();
			    }
			    catch(Exception e)
			    {System.out.println(e);}
			    if (strPythonScriptExisted == "False")
			    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script cat /etc/os_release");}
			}
		}
	    return "False";
    }
	//***************************************************************************************************************************************************************************************


	//Copy Python Scripts To Meter
	public void METER_AddPythonScriptsToMeter(Map<String, String> objDictionary,String strHost, String strPythonCommand,String strHostType)
	{
		String strPythonScriptExists = "";
		if(strPythonCommand.equals("testauto_card_pay_max_time_spot_1.py"))
		{
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testauto_card_pay_max_time_spot_1.py",strHostType);
			if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost, "testauto_card_pay_max_time_spot_1.py");}
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_select_active_spot.py",strHostType);
			if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_select_active_spot.py");}
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_state_paying_card.py",strHostType);
			if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_state_paying_card.py");}
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_card_pay_maxtime.py",strHostType);
			if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_card_pay_maxtime.py");}
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_accept_payment.py",strHostType);
			if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_accept_payment.py");}
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_complete_card_payment.py",strHostType);
			if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_complete_card_payment.py");}
		}
		else if (strPythonCommand.contains(".sh"))
		{
			String strShellScriptExists = METER_CheckIfShellScriptExistsOnMeter(objDictionary, strHost, strPythonCommand);
			if(strShellScriptExists.equals("False"))
		    {
				if(strPythonCommand.contains("testauto_card_pay_max_time_spot"))
				{
					METER_CopyShellScriptsToMeter( objDictionary,strPythonCommand);
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_select_active_spot.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_select_active_spot.py");}
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_state_paying_card.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_state_paying_card.py");}
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_card_pay_maxtime.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_card_pay_maxtime.py");}
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_accept_payment.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_accept_payment.py");}
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_complete_card_payment.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_complete_card_payment.py");}
				}
				else if(strPythonCommand.contains("testauto_card_pay_no_spot")||strPythonCommand.contains("ytestauto_card_pay_no_spot"))
				{
					METER_CopyShellScriptsToMeter( objDictionary,strPythonCommand);
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_state_paying_card.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_state_paying_card.py");}
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_accept_payment.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_accept_payment.py");}
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_complete_card_payment.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_complete_card_payment.py");}
				}
				else if(strPythonCommand.contains("testauto_card_pay_max_time_no_spot"))
				{
					METER_CopyShellScriptsToMeter( objDictionary,strPythonCommand);
					strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, "testautof_card_pay_maxtime.py",strHostType);
					if(strPythonScriptExists.equals("False")){METER_CopyPythonScriptsToMeter( objDictionary,strHost,"testautof_card_pay_maxtime.py");}
				}
				else
				{
					METER_CopyShellScriptsToMeter(objDictionary,strPythonCommand);
				}
		    }
		}
		else
		{
			strPythonCommand = strPythonCommand.substring(0,strPythonCommand.indexOf(".py")+3);
			strPythonScriptExists = METER_CheckIfPythonScriptExistsOnMeter(objDictionary, strHost, strPythonCommand,strHostType);
			if(strPythonScriptExists.equals("False") && !strHost.equals("10.10.101.109"))
		    {
				METER_CopyPythonScriptsToMeter(objDictionary,strHost,strPythonCommand);
		    }
		}
	}

	public void METER_CopyPythonScriptsToMeter(Map<String, String> objDictionary,String strHost, String strPythonCommand)
	{
		String strLocalHost = objDictionary.get("strHost");
		String strHostType = "";
		if(strHost.equals(strLocalHost)){strHostType = "local";}else{strHostType = "Remote";}
		try
    	{
		    File directory = new File(".");
			String strPythonScript = strPythonCommand.substring(0,strPythonCommand.indexOf(".py")+3);
			String strPath = directory.getCanonicalPath() +"/PythonScripts/"+strPythonScript;
			File file = new File(strPath);
			if (file.exists())
			{METER_CopyPythonScriptFromLocalPythonFolderToMeter(objDictionary,strHost,strPythonScript);}
			else
			{
				METER_CopyPythonScriptFromMeterToLocalPythonFolder(objDictionary,strPythonScript,strHostType);
				METER_CopyPythonScriptFromLocalPythonFolderToMeter(objDictionary,strHost,strPythonScript);
			}
    	}
		catch(Exception e)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,null,e.getMessage()+"-ssh into the ("+strHost+") from this machine");
		}
	}
	public void METER_CopyShellScriptsToMeter(Map<String, String> objDictionary,String strPythonCommand)
	{
		String strHost = objDictionary.get("strHost");
		try
	 	{
	 		strPythonCommand = strPythonCommand.replace("sudo ", "");
	 		File directory = new File(".");
	 		System.out.println(strPythonCommand);
	 		System.out.println(strPythonCommand.substring(0,strPythonCommand.indexOf(".sh")+3));
			String strPythonScript = strPythonCommand.substring(0,strPythonCommand.indexOf(".sh")+3);
			String strPath = directory.getCanonicalPath() +"/PythonScripts/"+strPythonScript;
			File file = new File(strPath);
			if (file.exists())
			{METER_CopyPythonScriptFromLocalPythonFolderToMeter(objDictionary,strHost,strPythonScript);}
			else
			{
				METER_CopyPythonScriptFromMeterToLocalPythonFolder(objDictionary, strPythonScript,"Local");
				METER_CopyPythonScriptFromLocalPythonFolderToMeter(objDictionary,strHost,strPythonScript);
			}
		}
		catch(Exception e)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,null,e.getMessage()+"-ssh into the ("+strHost+") from this machine");
		}
	}
	public String METER_GetMeterStateValue(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strMeterUser = objDictionary.get("strMeterUser");
    	String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
	        					break;

	        			}
		        		if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
	        			channel.disconnect();session.disconnect();
	        			System.out.println("Meter State: "+strSpotVariables.substring(strSpotVariables.indexOf("State,")+6, strSpotVariables.indexOf("|Begun")));
	        			return strSpotVariables.substring(strSpotVariables.indexOf("State,")+6, strSpotVariables.indexOf("|Begun"));
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }


	//***************************************************************************************************************************************************************************************
	//Get Meter Max Remaining
	//***************************************************************************************************************************************************************************************
	public String METER_GetMeterMaxRemaining(Map<String, String> objDictionary,Session sessionMeter,String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else if(strHostType.contains("Remote"))
		{strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));}
		String strMaxRemaining = "False";
		try
    	{
    		Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines)
            {
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		strMaxRemaining = line.substring(line.indexOf("MaxRemaining,")+13, line.indexOf("|ValidTimePurchased"));
	        		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
	     			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	     			String strUTC = dateFormatGmt.format(new Date());
	        		Reporter.log("Meter (Max Remaining) on space ("+strSpotNumber+") equaled: "+strMaxRemaining+" UTC-"+strUTC);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
    	}
    	catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) failed on meter ("+strHost+")-"+strMethodName);}
	    if(strMaxRemaining.equals("0.0")) {strMaxRemaining = "0";}
		return strMaxRemaining;
	}
	public String METER_GetMeterMaxRemaining(Map<String, String> objDictionary,String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		//String strHostType
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else{strHost = objDictionary.get("strRemoteHost");}
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strMaxRemaining = "False";
		int port=22;
//    	METER_AddPythonScriptsToMeter(objDictionary,strHost,"testauto_dump_sessions.py",strHostType);
    	try
    	{
    		Session session = jsch.getSession(strMeterUser, strHost, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines)
            {
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		strMaxRemaining = line.substring(line.indexOf("MaxRemaining,")+13, line.indexOf("|ValidTimePurchased"));
	        		if(strMaxRemaining.equals("0.0")) {strMaxRemaining = "0";}
	        		Reporter.log("Meter (Max Remaining) on space ("+strSpotNumber+") equaled: "+strMaxRemaining);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
    	}
    	catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) failed on meter ("+strHost+")-"+strMethodName);}
    	if(strMaxRemaining.equals("0.0")){strMaxRemaining = "0";}
    	return strMaxRemaining;
	}
	//***************************************************************************************************************************************************************************************


	//***************************************************************************************************************************************************************************************
	//Get Meter Coin Acceptor Value
	//***************************************************************************************************************************************************************************************
	public String GetMeterCoinAcceptorValue(Map<String, String> objDictionary,Session sessionMeter,String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strCoinAcceptorValue = "";
		try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testautof_coin_acceptor_status.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines)
            {
	        	strCoinAcceptorValue = line; break;
	        }
	        in.close();
	        channel.disconnect();
	    }
		catch(Exception e)
		{
			String strHost = "";if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
			else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));}
			UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) failed on meter ("+strHost+")-"+strMethodName);
	    }
	    return strCoinAcceptorValue;
    }
	public String GetMeterCoinAcceptorValue(Map<String, String> objDictionary,String strSpotNumber, String strHostType)
		{
			String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
			JSch jsch = new JSch();
			String strHost = "";
			String strBegunValue = "";
			String strMeterUser = "";
			if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
			else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
			String strPassword = objDictionary.get("strUniquePassword");
	    	int port=22;
		    try
		    {
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
			    session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        //System.out.println(Arrays.toString(lines));
		        for (String line : lines)
	            {
		        	if(line.contains("SPOT_"+strSpotNumber+"|"))
		        	{
		        		strBegunValue = line.substring(line.indexOf("Begun,")+6, line.indexOf("|ParkTime"));
		        		System.out.println("strBegunValue: "+strBegunValue);
		        		break;
		        	}
	            }
		        in.close();
		        channel.disconnect();
		        session.disconnect();
		    }
		    catch(Exception e) {UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) failed on meter ("+strHost+")-"+strMethodName);}
		    return strBegunValue;
	    }



	//***************************************************************************************************************************************************************************************
	//Get Meter Variables
	//***************************************************************************************************************************************************************************************
	public String GetMeterBegunValue(Map<String, String> objDictionary,Session sessionMeter,String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strBegunValue = "";
		try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines)
            {
	        	if(line.contains("SPOT_"+strSpotNumber+"|")){return line.substring(line.indexOf("Begun,")+6, line.indexOf("|ParkTime"));}
            }
	        in.close();
	        channel.disconnect();
	    }
		catch(Exception e)
		{
			String strHost = "";if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
			else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));}
			UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) failed on meter ("+strHost+")-"+strMethodName);
	    }
	    return strBegunValue;
    }
	public String GetMeterBegunValue(Map<String, String> objDictionary,String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = "";
		String strBegunValue = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
            {
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		strBegunValue = line.substring(line.indexOf("Begun,")+6, line.indexOf("|ParkTime"));
	        		System.out.println("strBegunValue: "+strBegunValue);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	    }
	    catch(Exception e) {UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) failed on meter ("+strHost+")-"+strMethodName);}
	    return strBegunValue;
    }




	//***************************************************************************************************************************************************************************************
	//GET_COIN_ACCEPTOR_FULL_DISABLE_PERCENTAGE
	//***************************************************************************************************************************************************************************************
	public String METER_GET_COIN_ACCEPTOR_FULL_DISABLE_PERCENTAGE(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py COIN_ACCEPTOR_FULL_DISABLE_PERCENTAGE");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strCoinAcceptorFullDisablePercent = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        return strCoinAcceptorFullDisablePercent;
	    	}catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }
	//***************************************************************************************************************************************************************************************
	//COIN_CANISTER_FULL_CRITICAL_LEVEL
	//***************************************************************************************************************************************************************************************
	public String METER_GET_COIN_CANISTER_FULL_CRITICAL_LEVEL(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py COIN_CANISTER_FULL_CRITICAL_LEVEL");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strCoinCanisterFullCriticalLevel = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        return strCoinCanisterFullCriticalLevel;
	    	}catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }
	//***************************************************************************************************************************************************************************************
	//Get Number Of Spots
	//***************************************************************************************************************************************************************************************
	public String METER_GetSYS_NUMBER_OF_SPOTS(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py SYS_NUMBER_OF_SPOTS");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        String strNumberOfMeterSpots = "";
		        int intRowNumber = lines.length - 1;
		        strNumberOfMeterSpots = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        objDictionary.remove("strNumberOfMeterSpots");objDictionary.put("strNumberOfMeterSpots", strNumberOfMeterSpots);
		        return strNumberOfMeterSpots;
	    	}catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }
	public String METER_GetSYS_NUMBER_OF_SPOTS(Map<String, String> objDictionary,WebDriver driver, String strHostType)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local"))
		{strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else
		{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String strPythonScriptExisted = "False";
    	String strPythonCommand = "testauto_dump_sessions.py";
    	int port=22;
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");
    	if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			//METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py SYS_NUMBER_OF_SPOTS");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        String strNumberOfMeterSpots = "";
		        int intRowNumber = lines.length - 1;
		        strNumberOfMeterSpots = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        session.disconnect();
		        objDictionary.remove("strNumberOfMeterSpots");objDictionary.put("strNumberOfMeterSpots", strNumberOfMeterSpots);
		        return strNumberOfMeterSpots;
	    	}
	    	catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False")
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }

	//***************************************************************************************************************************************************************************************
	//PARKING_TRUEUP_ENABLED
	//***************************************************************************************************************************************************************************************
	public String METER_Get_PARKING_TRUEUP_ENABLED(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_TRUEUP_ENABLED");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strParkingTrueUpEnabled = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        return strParkingTrueUpEnabled;
	    	}catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }
	public String METER_Get_PARKING_TRUEUP_ENABLED(Map<String, String> objDictionary,WebDriver driver, String strHostType)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local"))
		{strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else
		{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String strPythonScriptExisted = "False";
    	String strPythonCommand = "testauto_dump_sessions.py";
    	int port=22;
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");
    	if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			//METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_TRUEUP_ENABLED");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strParkingTrueUpEnabled = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        session.disconnect();
		        return strParkingTrueUpEnabled;
	    	}
	    	catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False")
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }


	//***************************************************************************************************************************************************************************************
	//PARKING_RATE_DEFAULT_MINIMUM_COST
	//***************************************************************************************************************************************************************************************
	public String METER_Get_PARKING_RATE_DEFAULT_MINIMUM_COST(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_RATE_DEFAULT_MINIMUM_COST");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strParkingTrueUpEnabled = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        return strParkingTrueUpEnabled;
	    	}catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py PARKING_RATE_DEFAULT_MINIMUM_COST Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }
	public String METER_Get_PARKING_RATE_DEFAULT_MINIMUM_COST(Map<String, String> objDictionary,WebDriver driver, String strHostType)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local"))
		{strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else
		{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String strPythonScriptExisted = "False";
    	String strPythonCommand = "testauto_dump_sessions.py";
    	int port=22;
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");
    	if(strVirtualMeter == null){strVirtualMeter = "False";}
//		if (strVirtualMeter.equals("False"))
//		{
//			METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_RATE_DEFAULT_MINIMUM_COST");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strParkingTrueUpEnabled = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        session.disconnect();
		        return strParkingTrueUpEnabled;
	    	}
	    	catch(Exception e){System.out.println(e);}
		    if (strPythonScriptExisted == "False")
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Command (settings_get_setting.py PARKING_RATE_DEFAULT_MINIMUM_COST Failed)-"+strMethondName);}
		    return "";
//		}
//		return "";
    }



	//***************************************************************************************************************************************************************************************
	//Get Parking Free Time On First Payment
	//***************************************************************************************************************************************************************************************
	public String METER_GetSYS_PARKING_FREE_TIME_ON_FIRST_PAYMENT(Map<String, String> objDictionary,Session sessionMeter,String strHostType)
	{
  		Meter clsMeter = new Meter();
  		String strDeviceId = "";
  		if(strHostType.equals("Local")) {strDeviceId = objDictionary.get("strDeviceId");}
  		else if(strHostType.equals("Remote")) {strDeviceId = objDictionary.get("strRemoteDeviceId");}
  		else if(strHostType.equals("Remote2")) {strDeviceId = objDictionary.get("strRemoteDeviceId2");}
  		String strPythonScriptExisted = "False";
    	String strOutput = "";
    	try
	    {
	    	 Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_FREE_TIME_ON_FIRST_PAYMENT");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_FREE_TIME_ON_FIRST_PAYMENT for Meter ("+strDeviceId+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();
	    }
	    catch(Exception e)
    	{
	    	System.out.println(e);
	    }
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,sessionMeter,"The python script (settings_get_setting.py) didn't exist on the meter","True");}
        return strOutput.trim();
	}
	public String METER_GetSYS_PARKING_FREE_TIME_ON_FIRST_PAYMENT(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_FREE_TIME_ON_FIRST_PAYMENT");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_FREE_TIME_ON_FIRST_PAYMENT for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (settings_get_setting.py) didn't exist on the meter");}
        return strOutput.trim();
	}
	//***************************************************************************************************************************************************************************************

	//***************************************************************************************************************************************************************************************
	//Get GS4 METER Value
	//***************************************************************************************************************************************************************************************
	public String METER_GET_GS4MeterValue(Map<String, String> objDictionary,WebDriver driver,String strMeterSetting)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py "+strMeterSetting);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The "+strMeterSetting+" for Meter ("+strMeterName+") is ("+strOutput.trim()+")");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (settings_get_setting.py) didn't exist on the meter");}
        return strOutput.trim();
	}




	//***************************************************************************************************************************************************************************************
	//Get Meter Maint Value
	//***************************************************************************************************************************************************************************************
	public String METER_GetMeterInMaintValue(Map<String, String> objDictionary, Session sessionMeter,String strSpotNumber)
	{
		String strPythonScriptExisted = "False";
    	try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		System.out.println("Meter InMaint Value: "+line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree")));
	     			return line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
	        	}
            }
	        in.close();
	        channel.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return null;
	}
	public String METER_GetMeterInMaintValue(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			JSch jsch = new JSch();
			String strHost = "";
			if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
			else if(strHostType.contains("Remote")){strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));}
			String strPassword = objDictionary.get("strUniquePassword");
			String strPythonScriptExisted = "False";
			String strPythonCommand = "testauto_dump_sessions.py";
			String strMeterUser = objDictionary.get("strMeterUser");
	    	METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, 22);
		    	session.setPassword(strPassword);
		    	session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1)
		        {s += (char)c;}
		        String[] lines = s.split("\n");
		        String strSpotVariables = "";
		        int intRowNumber = 0;
		        switch (strSpotNumber)
		        {
		        		case "1":
		        			intRowNumber = lines.length - 1;
		        			break;
		        		case "2":
		        			intRowNumber = lines.length - 2;
		        			break;
		        		default:
		        			UpdateErrorMessageWithPivotalData(objDictionary,driver,"Spot Number ("+strSpotNumber+") has not been added to - "+strMethodName);
		        	}
		        strSpotVariables = lines[intRowNumber];
		        System.out.println(strSpotVariables);
		        in.close();
		        channel.disconnect();
		        session.disconnect();
		        System.out.println("Meter InMaint Value: "+strSpotVariables.substring(strSpotVariables.indexOf("InMaint,")+8, strSpotVariables.indexOf("|MtFree")));
	    			return strSpotVariables.substring(strSpotVariables.indexOf("InMaint,")+8, strSpotVariables.indexOf("|MtFree"));
		    	}
		    catch(Exception e)
		    {System.out.println(e);}
		    if (strPythonScriptExisted == "False")
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script ("+strPythonCommand+") didn't exist on the meter-ssh into the meter");}
		    return "False";
		}
        return "";
    }

	//***************************************************************************************************************************************************************************************
	//Get Meter ParkedAt Value
	//***************************************************************************************************************************************************************************************
	public String METER_GetMeterParkTimeValue(Map<String, String> objDictionary, Session sessionMeter,String strSpotNumber)
	{
		String strPythonScriptExisted = "False";
    	try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		String strParkTime = line.substring(line.indexOf("ParkTime,")+9, line.indexOf("|ExitTime"));
	        		System.out.println("Meter ParkedTime Value: "+line.substring(line.indexOf("ParkTime,")+9, line.indexOf("|ExitTime")));
	        		strParkTime = strParkTime.replace("-06:00","").replace("-05:00","");
	        		return strParkTime;
	        	}
            }
	        in.close();
	        channel.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return null;
	}

	//*************************************************************************************************************************************************************************************************************
	//Get Meter Free Value
	//*************************************************************************************************************************************************************************************************************
	public String METER_GetMeterFreeValue(Map<String, String> objDictionary,Session sessionMeter)
	{
		//If Checked don't rerun
    	try
    	{
    		Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Free Value
	        String s = "";int c;
	        while((c = in.read()) != -1){s += (char)c;}
	        String[] lines = s.split("\n");
	        int intRowNumber = lines.length - 1;
	        //System.out.println("Row Number: "+intRowNumber);
	        if(intRowNumber == 0){return "";}
	        String strSpotVariables = lines[intRowNumber];
	        in.close();
	        channel.disconnect();
	        System.out.println("MeterFreeValue: "+strSpotVariables.substring(strSpotVariables.indexOf("Free,")+5, strSpotVariables.indexOf("|No")));
			return strSpotVariables.substring(strSpotVariables.indexOf("Free,")+5, strSpotVariables.indexOf("|No"));
	    }
    	catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return "";
    }
	public String METER_GetMeterFreeValue(Map<String, String> objDictionary,WebDriver driver, String strHostType)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strPythonScriptExisted = "False";
    	String strPythonCommand = "testauto_dump_sessions.py";
    	String strMeterUser = "";
    	if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
    	else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
    	//If Checked don't rerun
//    	String TestautoDumpSessionsExists = objDictionary.get("TestautoDumpSessionsExists");
//    	if(TestautoDumpSessionsExists == null)
//    	{
//	    	METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
//	    	objDictionary.put("TestautoDumpSessionsExists", "True");
//    	}
    	try
    	{
	    	Session session = jsch.getSession(strMeterUser, strHost, 22);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Free Value
	        String s = "";int c;
	        while((c = in.read()) != -1){s += (char)c;}
	        String[] lines = s.split("\n");
	        int intRowNumber = lines.length - 1;
	        //System.out.println("Row Number: "+intRowNumber);
	        if(intRowNumber == 0){return "";}
	        String strSpotVariables = lines[intRowNumber];
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        System.out.println("MeterFreeValue: "+strSpotVariables.substring(strSpotVariables.indexOf("Free,")+5, strSpotVariables.indexOf("|No")));
			return strSpotVariables.substring(strSpotVariables.indexOf("Free,")+5, strSpotVariables.indexOf("|No"));
	    }
    	catch(Exception e)
	    {
	    		System.out.println(e);
	    		if(e.toString().contains("Operation timed out (Connection timed out)"))
	    		{return "";}
	    	}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script ("+strPythonCommand+") didn't exist on the meter-ssh into the meter");}
        return "True";
    }
	//*************************************************************************************************************************************************************************************************************

	public String METER_GetMeterNoParkingValue(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strMeterUser = objDictionary.get("strMeterUser");
    	String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;int port=22;
    	String strPythonScriptExisted = "False";
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					System.out.println(strSpotVariables);
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.indexOf("SPOT_1"));
	        					System.out.println(strSpotVariables);
	        					break;

	        			}
		        		if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
		        		channel.disconnect();session.disconnect();
		        		System.out.println("MeterNoParkingValue: "+strSpotVariables.substring(strSpotVariables.indexOf("No,")+3, strSpotVariables.indexOf("|Begun")));
	        			return strSpotVariables.substring(strSpotVariables.indexOf("No,")+3, strSpotVariables.indexOf("|Begun"));
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();session.disconnect();
	    	}
	    catch(Exception e)
	    {
        		String strErrorMsg = e.toString();
         	Reporter.log("<font color='red'>"+strErrorMsg+"</font>"); Assert.fail(strErrorMsg);
	    }
	    if (strPythonScriptExisted == "False"){UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }
	public String METER_GetPARKING_IMAGE_DURATION_ENTRANCE(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
		    	Session session = jsch.getSession(strMeterUser, host, port);
		    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_IMAGE_DURATION_ENTRANCE");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_IMAGE_DURATION_ENTRANCE for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter");}
        return strOutput.trim();
	}
  	public String METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
		String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_IMAGE_LOOK_AHEAD_ENTRANCE");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_IMAGE_LOOK_AHEAD_ENTRANCE for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_IMAGE_LOOK_AHEAD_ENTRANCE) didn't exist on the meter");}
        return strOutput.trim();
	}
  	public String METER_GetPARKING_IMAGE_DURATION_EXIT(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_IMAGE_DURATION_EXIT");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_IMAGE_DURATION_EXIT for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_IMAGE_DURATION_EXIT) didn't exist on the meter");}
        return strOutput.trim();
	}
  	public String METER_GetPARKING_IMAGE_COUNT_VIOLATION(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_IMAGE_COUNT_VIOLATION");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_INIT_GRACE_PERIOD for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_IMAGE_COUNT_VIOLATION) didn't exist on the meter");}
        return strOutput.trim();
	}

  	//*************************************************************************************************************************************************************************************************************

  	public String METER_GetSYS_DEBUG_MASK(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py SYS_DEBUG_MASK");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_IMAGE_DURATION_ENTRANCE for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter");}
        return strOutput.trim();
	}
  	public String METER_GetSYS_MAC_ADDRESS(Map<String, String> objDictionary,WebDriver driver)
	{
  		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py SYS_MAC_ADDRESS");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		strPythonScriptExisted = "True";
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		strOutput = new String(tmp, 0, i);
	        		Reporter.log("The SYS_MAC_ADDRESS for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter");}
        return strOutput.trim();
	}



  	//********************************************************************************************************
  	//Get SYS_FORCED_MULTI
  	//********************************************************************************************************
  	public String METER_GetSYS_FORCE_MULTI(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
    	if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py SYS_FORCE_MULTI");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strSysForceMulti = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        objDictionary.remove("strSysForceMulti");objDictionary.put("strSysForceMulti", strSysForceMulti);
		        return strSysForceMulti;
	    	}
	    	catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py SYS_FORCE_MULT Failed)-"+strMethondName);}
		}
		return "";
    }
  	public String METER_GetSYS_FORCE_MULTI(Map<String, String> objDictionary,WebDriver driver, String strHostType)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local"))
		{strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else
		{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String strPythonScriptExisted = "False";
    	String strPythonCommand = "testauto_dump_sessions.py";
    	int port=22;
    	String strVirtualMeter = objDictionary.get("strVirtualMeter");
    	if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			//METER_AddPythonScriptsToMeter(objDictionary,strHost,strPythonCommand,strHostType);
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py SYS_FORCE_MULTI");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        String strNumberOfMeterSpots = "";
		        int intRowNumber = lines.length - 1;
		        strNumberOfMeterSpots = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        session.disconnect();
		        objDictionary.remove("strNumberOfMeterSpots");objDictionary.put("strNumberOfMeterSpots", strNumberOfMeterSpots);
		        return strNumberOfMeterSpots;
	    	}
	    	catch(Exception e)
	    	{System.out.println(e);}
		    if (strPythonScriptExisted == "False")
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		    return "False";
		}
		return "";
    }

  	//********************************************************************************************************
  	//Get PARKING_INIT_GRACE_PERIOD
  	//********************************************************************************************************
  	public String METER_GetPARKING_INIT_GRACE_PERIOD(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
    	if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
			try
	    	{
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_INIT_GRACE_PERIOD");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        //Get Begun Value
		        String s = "";int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        int intRowNumber = lines.length - 1;
		        String strSentryInitialGrace = lines[intRowNumber];
		        in.close();
		        channel.disconnect();
		        objDictionary.remove("strSentryInitialGrace");objDictionary.put("strSentryInitialGrace", strSentryInitialGrace);
		        return strSentryInitialGrace;
	    	}
	    	catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Command (settings_get_setting.py SYS_NUMBER_OF_SPOTS Failed)-"+strMethondName);}
		}
		return "";
    }

  	//*********************************************************************************************************************************************************************************
  	//Get Meter MD5SUM Version
  	//*********************************************************************************************************************************************************************************
  	public String GetMeterMD5SUMVersion(Map<String, String> objDictionary,Session sessionMeter)
	{
		String strPythonScriptExisted = "False";
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
	    if(strVirtualMeter == null){strVirtualMeter = "False";}
		if (strVirtualMeter.equals("False"))
		{
		    try
		    {
		    	Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("md5sum /opt/mps/sentry.egg");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp = new byte[1200];
		        while(true)
		        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
	        			objDictionary.remove("strMeterMD5SUMVersion");objDictionary.put("strMeterMD5SUMVersion", strOutput);
	        			if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
	        			channel.disconnect();
	        			return strOutput.replace(" /opt/mps/sentry.egg", "");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();
		    }
		    catch(Exception e)
		    {System.out.println(e);}
		    if (strPythonScriptExisted == "False")
		    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script md5sum /opt/mps/sentry.egg failed");}
		}
	    return "False";
    }
  	public String GetMeterMD5SUMVersion(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		if(strHost != null)
		{
			String strMeterUser = objDictionary.get("strMeterUser");
			String strPassword = objDictionary.get("strUniquePassword");
			String host = strHost;
			String strPythonScriptExisted = "False";
			String strVirtualMeter = objDictionary.get("strVirtualMeter");
		    if(strVirtualMeter == null){strVirtualMeter = "False";}
			if (strVirtualMeter.equals("False"))
			{
				int port=22;
			    try
			    {
			    	Session session = jsch.getSession(strMeterUser, host, port);
			    	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Channel channel=session.openChannel("exec");
			        ((ChannelExec)channel).setCommand("md5sum /opt/mps/sentry.egg");
			        channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        InputStream in=channel.getInputStream();
			        channel.connect();
			        byte[] tmp = new byte[1200];
			        while(true)
			        {
			        	while(in.available()>0)
			        	{
			        		strPythonScriptExisted = "True";
			        		int i=in.read(tmp, 0, 1200);
			        		if(i<0) {
								break;
							}
			        		String strOutput = new String(tmp, 0, i);
		        			objDictionary.remove("strMeterMD5SUMVersion");objDictionary.put("strMeterMD5SUMVersion", strOutput);
		        			if(channel.isClosed()){if(in.available()>0) {
								continue;
							}break;}
		        			channel.disconnect();session.disconnect();
		        			return strOutput.replace(" /opt/mps/sentry.egg", "");
			        	}
			        	if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
			        }
				    channel.disconnect();session.disconnect();
			    }
			    catch(Exception e)
			    {System.out.println(e);}
			    if (strPythonScriptExisted == "False")
			    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script md5sum /opt/mps/sentry.egg failed");}
			}
		}
	    return "";
    }

  	//*********************************************************************************************************************************************************************************
  	//Get Meter Version
  	//*********************************************************************************************************************************************************************************
  	public String GetMeterVersion(Map<String, String> objDictionary,Session sessionMeter)
	{
  		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strPythonScriptExisted = "False";
    	String strOutput = "";
	    String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
	    if (strVirtualMeter.equals("False"))
		{
	    	try
		    {
		       	 Channel channel=sessionMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/local/bin/sys_get_sw_version.py");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp = new byte[1200];
		        while(true)
		        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		break;
	            	}
	        		if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();
	    	}
		    catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		    if (strPythonScriptExisted.equals("False"))
		    {UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"The script md5sum /opt/mps/sentry.egg failed-RMQ Cert may have expired","False");}
		}
	    objDictionary.remove("strMeterVersion");objDictionary.put("strMeterVersion", strOutput);
		return strOutput;
    }
  	public String GetMeterVersion(Map<String, String> objDictionary,WebDriver driver, String strHostType)
	{
  		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strHost = "";
  		String strOutput = "";
  		String strMeterUser = "";
  		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
	    if(strHost != null)
  		{
	  		JSch jsch = new JSch();
			String strPassword = objDictionary.get("strUniquePassword");
			String strPythonScriptExisted = "False";
	    	String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter = "False";}
		    if (strVirtualMeter.equals("False"))
			{
		    	int port=22;
			    try
			    {
			       	Session session = jsch.getSession(strMeterUser, strHost, port);
			       	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        Channel channel=session.openChannel("exec");
			        ((ChannelExec)channel).setCommand("/usr/local/bin/sys_get_sw_version.py");
			        channel.setInputStream(null);
			        ((ChannelExec)channel).setErrStream(System.err);
			        InputStream in=channel.getInputStream();
			        channel.connect();
			        byte[] tmp = new byte[1200];
			        while(true)
			        {
			        	while(in.available()>0)
			        	{
			        		strPythonScriptExisted = "True";
			        		int i=in.read(tmp, 0, 1200);
			        		if(i<0) {
								break;
							}
			        		strOutput = new String(tmp, 0, i);
			        		break;
		            	}
		        		if(channel.isClosed()){if(in.available()>0) {
							continue;
						}break;}
			        }
				    channel.disconnect();session.disconnect();
		    	}
			    catch(Exception e)
			    {
			    	UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);
			    	System.out.println(e);
			    }
			    if (strPythonScriptExisted.equals("False"))
			    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script md5sum /opt/mps/sentry.egg host: ("+strHost+") failed-RMQ Cert may have expired"+"-Method: "+strMethodName);}
			}
		    objDictionary.remove("strMeterVersion");objDictionary.put("strMeterVersion", strOutput);
  		}
		return strOutput;
    }
  //*********************************************************************************************************************************************************************************


	public void GetMeterParkingRateBlocks(Map<String, String> objDictionary,WebDriver driver)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/parking_get_rate_blk_ids.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        Reporter.log("***********RateBlocks Start*************");
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		System.out.println(strOutput);
		        		Reporter.log(strOutput);
		        		strPythonScriptExisted = "True";
	        			break;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        	Reporter.log("***********RateBlock End*************");
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (parking_get_rate_blk_ids.py) exist but it failed to execute-"+strMethodName);}
    }




	//*********************************************************************************************************************************************************************************
	//Get Current Meter Screen
	//*********************************************************************************************************************************************************************************
	public String METER_GetMeterUIState(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber)
	{
		String strMeterUIState = "";
		String strPythonScriptExisted = "False";
        try
	    {
	        Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strMeterUIState = line.substring(line.indexOf("|Screen,")+8, line.indexOf("|SubScreen,"));
	        		System.out.println("Current Meter UI Screen: "+strMeterUIState);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        //Add UTC time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
			Reporter.log("The meter UI State equaled ("+strMeterUIState+") on spot ("+strSpotNumber+")-UTC: "+strUTC);
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return strMeterUIState;
    }
	public String METER_GetMeterUIState(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber,String strHostType)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUIState = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strMeterUIState = line.substring(line.indexOf("|Screen,")+8, line.indexOf("|SubScreen"));
	        		System.out.println("Current Meter UI Screen: "+strMeterUIState);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        //Add UTC time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
			Reporter.log("The meter UI State equaled ("+strMeterUIState+") on spot ("+strSpotNumber+")-UTC: "+strUTC);
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return strMeterUIState;
    }

	//*********************************************************************************************************************************************************************************
	//Get Current Meter Sub Screen
	//*********************************************************************************************************************************************************************************
	public String METER_GetMeterSubScreen(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber)
	{
		String strMeterUIState = "";
		String strPythonScriptExisted = "False";
        try
	    {
	        Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strMeterUIState = line.substring(line.indexOf("|SubScreen,")+11, line.indexOf("|Trup"));
	        		System.out.println("Current Meter UI Screen: "+strMeterUIState);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        //Add UTC time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
			Reporter.log("The meter UI State equaled ("+strMeterUIState+") on spot ("+strSpotNumber+")-UTC: "+strUTC);
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return strMeterUIState;
    }
	public String METER_GetMeterSubScreen(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber,String strHostType)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strMeterUIState = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strMeterUIState = line.substring(line.indexOf("|SubScreen,")+11, line.indexOf("|Trup"));
	        		System.out.println("Current Meter UI Screen: "+strMeterUIState);
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        //Add UTC time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
			Reporter.log("The meter UI State equaled ("+strMeterUIState+") on spot ("+strSpotNumber+")-UTC: "+strUTC);
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return strMeterUIState;
    }


	//*********************************************************************************************************************************************************************************
	//Get Current Maintenance Mode
	//*********************************************************************************************************************************************************************************
	public String GetMeterValidTimePurchased(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber,String strHostType, String strBeforeOrAfterPayment)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strValidTimePurchased = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length() - 1));}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
    	try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strValidTimePurchased = line.substring(line.indexOf("ValidTimePurchased,")+19, line.indexOf("|ValidTimeRemaining"));
        			break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        //Add UTC time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
			Reporter.log("The meter purchase amount equaled ("+strValidTimePurchased+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC: "+strUTC);
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return strValidTimePurchased.replace(".0", "");
    }
	public String GetMeterValidTimePurchased(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber,String strHostType, String strBeforeOrAfterPayment)
	{

		JSch jsch = new JSch();
		String strHost = "";
		String strValidTimePurchased = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
            {
	        	System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		System.out.println(line);
	        		strValidTimePurchased = line.substring(line.indexOf("ValidTimePurchased,")+19, line.indexOf("|ValidTimeRemaining"));
        			break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        //Add UTC time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
			Reporter.log("The meter purchase amount equaled ("+strValidTimePurchased+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC: "+strUTC);
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
	    return strValidTimePurchased.replace(".0", "");
    }
	//*********************************************************************************************************************************************************************************




	public String GetMeterValidTimePurchased_old(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber,String strHostType)
	{
		JSch jsch = new JSch();
		//String strHostType
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else{strHost = objDictionary.get("strRemoteHost");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String strPythonScriptExisted = "False";
    	int port=22;
    	String strMeterUser = "";
    	if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
    	else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
    	try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        //UTC Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.S");
	     	dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	     	Reporter.log("***********Get Meter Payment*************");
	     	Reporter.log("Host: "+strHost);
	        Reporter.log("UTC Time:"+dateFormatGmt.format(new Date()));
	        //Computer Test Time
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
	        Reporter.log("<font color='green'>System Time "+sdf.format(cal.getTime())+"</font>");
	        while(true)
	        {
				while(in.available()>0)
				{
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		System.out.println(strOutput);
	        		Reporter.log(strOutput);
	        		String strSpotVariables = "";
	        		switch (strSpotNumber)
        			{
        				case "1":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
        					break;
        				case "2":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
        					break;

        			}
        			String strValidTimePurchased = strSpotVariables.substring(strSpotVariables.indexOf("ValidTimePurchased,")+19, strSpotVariables.indexOf("|ValidTimeRemaining"));
        			if(strValidTimePurchased.indexOf(".") != -1)
        			{
        				strValidTimePurchased = strValidTimePurchased.substring(0,strValidTimePurchased.indexOf("."));
        			}
        			//UTC Time
        			Reporter.log("UTC Time:"+dateFormatGmt.format(new Date()));
        			//Computer Test Time
        	        Reporter.log("<font color='green'>System Time "+sdf.format(cal.getTime())+"</font>");
        			Reporter.log("***********Meter Payment End*************");
        			strPythonScriptExisted = "True";
        			return strValidTimePurchased;
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute-"+e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute");}
	    return "0";
    }


	public String METER_GetMaintenanceModeStatus(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber, String strMaintenanceMode)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        Reporter.log("***********Payments*************");
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		System.out.println(strOutput);
		        		Reporter.log(strOutput);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
	        					break;

	        			}
		        		String strMaintenanceModeStatus = "";
		        		switch (strMaintenanceMode)
		        		{
			        		case "Maintenance":
			        			strMaintenanceModeStatus = strSpotVariables.substring(strSpotVariables.indexOf("MtPark,")+7, strSpotVariables.indexOf("|SrateS"));
			        			break;
			        		case "No Parking":
			        			strMaintenanceModeStatus = strSpotVariables.substring(strSpotVariables.indexOf("MtNo,")+5, strSpotVariables.indexOf("|MtUn"));
			        			break;
			        		case "Free Parking":
			        			strMaintenanceModeStatus = strSpotVariables.substring(strSpotVariables.indexOf("MtFree,")+7, strSpotVariables.indexOf("|MtNo"));
			        			break;
			        		case "Unenforced Parking":
			        			strMaintenanceModeStatus = strSpotVariables.substring(strSpotVariables.indexOf("MtUn,")+5, strSpotVariables.indexOf("|MtPark"));
			        			break;
		        		}
//	        			if(strMaintenanceModeStatus.indexOf(".") != -1)
//	        			{
//	        				strMaintenanceModeStatus = strMaintenanceModeStatus.substring(0,strMaintenanceModeStatus.indexOf("."));
//	        			}
	        			strPythonScriptExisted = "True";
	        			return strMaintenanceModeStatus;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver, "The python script (testauto_dump_sessions.py) exist but it failed to execute-"+e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute");}
	    return "0";
    }

	public String METER_GetMaintenanceModeMaintenance(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        Reporter.log("***********Payments*************");
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		System.out.println(strOutput);
		        		Reporter.log(strOutput);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
	        					break;

	        			}
	        			String strMaintenanceModeNoParking = strSpotVariables.substring(strSpotVariables.indexOf("MtPark,")+7, strSpotVariables.indexOf("|SrateS"));
	        			if(strMaintenanceModeNoParking.indexOf(".") != -1)
	        			{
	        				strMaintenanceModeNoParking = strMaintenanceModeNoParking.substring(0,strMaintenanceModeNoParking.indexOf("."));
	        			}
	        			strPythonScriptExisted = "True";
	        			return strMaintenanceModeNoParking;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute-"+e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary, driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute");}
	    return "0";
    }
	public String METER_GetMaintenanceModeNoParking(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        Reporter.log("***********Payments*************");
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		System.out.println(strOutput);
		        		Reporter.log(strOutput);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
	        					break;

	        			}
	        			String strMaintenanceModeNoParking = strSpotVariables.substring(strSpotVariables.indexOf("MtNo,")+5, strSpotVariables.indexOf("|MtUn"));
	        			if(strMaintenanceModeNoParking.indexOf(".") != -1)
	        			{
	        				strMaintenanceModeNoParking = strMaintenanceModeNoParking.substring(0,strMaintenanceModeNoParking.indexOf("."));
	        			}
	        			strPythonScriptExisted = "True";
	        			return strMaintenanceModeNoParking;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute-"+e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute");}
	    return "0";
    }
	public String METER_GetMaintenanceModeFreeParking(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        Reporter.log("***********Payments*************");
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		System.out.println(strOutput);
		        		Reporter.log(strOutput);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
	        					break;

	        			}
	        			String strMaintenanceModeNoParking = strSpotVariables.substring(strSpotVariables.indexOf("MtFree,")+7, strSpotVariables.indexOf("|MtNo"));
	        			if(strMaintenanceModeNoParking.indexOf(".") != -1)
	        			{
	        				strMaintenanceModeNoParking = strMaintenanceModeNoParking.substring(0,strMaintenanceModeNoParking.indexOf("."));
	        			}
	        			strPythonScriptExisted = "True";
	        			return strMaintenanceModeNoParking;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute-"+e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute");}
	    return "0";
    }
	public String METER_GetMaintenanceModeUnenforcedParking(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        Reporter.log("***********Payments*************");
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		System.out.println(strOutput);
		        		Reporter.log(strOutput);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.length());
	        					break;

	        			}
	        			String strMaintenanceModeNoParking = strSpotVariables.substring(strSpotVariables.indexOf("MtUn,")+5, strSpotVariables.indexOf("|MtPark"));
	        			if(strMaintenanceModeNoParking.indexOf(".") != -1)
	        			{
	        				strMaintenanceModeNoParking = strMaintenanceModeNoParking.substring(0,strMaintenanceModeNoParking.indexOf("."));
	        			}
	        			strPythonScriptExisted = "True";
	        			return strMaintenanceModeNoParking;
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute-"+e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) exist but it failed to execute");}
	    return "0";
    }


	//*********************************************************************************************************************************************************************************
	//Get Current Maintenance Mode
	//*********************************************************************************************************************************************************************************
	public String METER_GetCurrentMaintenanceMode(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strSpotNumber,String strHostType)
	{
		String strMaintenanceModeNoParking = "";
		String strPythonScriptExisted = "False";
		try
	    {
	    	Channel channel = sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        for (String line : lines)
            {
	        	System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strMaintenanceModeNoParking = line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        Reporter.log("Dump Session InMaint: "+strMaintenanceModeNoParking);
	        return strMaintenanceModeNoParking;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False") {UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter -ssh into the meter","False");}
	    return strMaintenanceModeNoParking;
    }
	public String METER_GetCurrentMaintenanceMode(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber, String strHostType)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strMaintenanceModeNoParking = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
            {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strMaintenanceModeNoParking = line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
	        		break;
	        	}
            }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        Reporter.log("Maintenance Mode Spot ("+strSpotNumber+") equals: "+strMaintenanceModeNoParking);
	        return strMaintenanceModeNoParking;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter -ssh into the meter");}
	    return strMaintenanceModeNoParking;
    }
	//*********************************************************************************************************************************************************************************

	//*********************************************************************************************************************************************************************************
	//Check If An ySpots Are In Maintenance Mode
	//*********************************************************************************************************************************************************************************
	public String METER_CheckIfAnySpotsAreInMaintenanceMode(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strHostType)
	{
		String strMaintenanceMode = "";
		String strPythonScriptExisted = "False";
		try
	    {
	    	Channel channel = sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
            {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"))
	        	{
	        		//System.out.println(line);
	        		strMaintenanceMode = line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
	        		if(strMaintenanceMode.contains("True"))
	        		{
	        			return strMaintenanceMode;
	        		}
	        	}
            }
	        in.close();
	        return strMaintenanceMode;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {
	    	String strHostId = "";
	    	if(strHostType.equals("Local")){strHostId = objDictionary.get("strHost");}
	  		else if(strHostType.contains("Remote")){strHostId = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));}
	  		UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter ("+strHostId+")","False");
	    }
	    return strMaintenanceMode;
    }



	public void METER_ValidateMeterPresumedOccupied(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strVehicleParkType = objDictionary.get("strVehicleParkType");
		String strOriginalSpotNumber = objDictionary.get("strSpotNumber");
		objDictionary.remove("strSpotNumber"); objDictionary.put("strSpotNumber", strSpotNumber);
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "vehicle_"+strVehicleParkType+"_spot_"+strSpotNumber+".py");
  		clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterVariableBegunEqualTrue} NA", 30, strSpotNumber,"Local");
  		String strMeterBegunValue = clsMeter.GetMeterBegunValue(objDictionary,"1","Local");
  		if(strMeterBegunValue.equals("True"))
  		{Reporter.log("A new parking session was created on Meter ("+strMeterName+") spot ("+strSpotNumber+") ");}
  		else
  		{
  			String strErrorMsg = "A new parking session was not created on Meter ("+strMeterName+") spot ("+strSpotNumber+") ";
  			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
  		}
  		objDictionary.remove("strSpotNumber"); objDictionary.put("strSpotNumber", strOriginalSpotNumber);
	}
	public void METER_ValidateMeterNotViolated(Map<String, String> objDictionary,WebDriver driver)
	{
		Meter clsMeter = new Meter();
		String strMeterName = objDictionary.get("strMeterName");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local",strSpotNumber);
  		if(strMeterViolationValue.equals("False"))
  		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") was not in Violation Status");}
  		else
  		{
  			String strErrorMsg = "The Meter ("+strMeterName+") spot ("+strSpotNumber+") was still in Violation Status";
  			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
  		}
	}


	//*********************************************************************************************************************************************************************************
	//ValidateMeterViolated
	//*********************************************************************************************************************************************************************************
	public void METER_ValidateMeterViolated(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter,String strHostType,String strSpotNumber, String strRemoteMeterSpotName)
	{
		Meter clsMeter = new Meter();
		String strMeterViolationValue = METER_GetMeterViolationValue(objDictionary,sessionMeter,strHostType,strSpotNumber);
		if(strMeterViolationValue.equals("True"))
  		{Reporter.log("The Meter ("+strRemoteMeterSpotName+") spot ("+strSpotNumber+") was in Violation Status");}
  		else
  		{
  			UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The Meter ("+strRemoteMeterSpotName+") spot ("+strSpotNumber+") was not in Violation Status","False");
  		}
	}
	public void METER_ValidateMeterViolated(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber,String strHostType)
	{
		Meter clsMeter = new Meter();
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local", strSpotNumber);
  		clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterViolationEqualsTrue} NA", 60, strSpotNumber,strHostType);
  		if(strMeterViolationValue.equals("True"))
  		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") was in Violation Status");}
  		else
  		{
  			String strErrorMsg = "The Meter ("+strMeterName+") spot ("+strSpotNumber+") was not in Violation Status";
  			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
  		}
	}

	//*********************************************************************************************************************************************************************************
	//Validate Meter True Up Value
	//*********************************************************************************************************************************************************************************
	public void METER_ValidateMeterTrueUpValue(Map<String, String> objDictionary,Session sessionLocalMeter,Session sessionRemoteMeter,String strAction,String strSpotNumber,String strHostType,String strMeterSpotName)
	{
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strExpectedMinutes = "";
		Session sessionMeter = null;
		String strActionDescription = "";
		if(strHostType.equals("Local")){strActionDescription = "Validate Meter True Up Value Local Space: "+strMeterSpotName+"-Spot: "+strSpotNumber;}
		else{strActionDescription = "Validate Meter True Up Value Remote Space: "+strMeterSpotName+"-Spot: "+strSpotNumber;}
		String strExecutedGlobalPayActions = objDictionary.get("strExecutedGlobalPayActions");
		objDictionary.put("strExecutedGlobalPayActions", strExecutedGlobalPayActions+"<br>"+strActionDescription);
		Reporter.log("<font color='DEEPPINK'>"+strActionDescription+"</font>");
		if(strHostType.equals("Local")){strExpectedMinutes = strAction.replace("VMTUTLS", "");sessionMeter = sessionLocalMeter;}
		else{strExpectedMinutes = strAction.replace("VMTUTRS", "");sessionMeter = sessionRemoteMeter;}
		String strTrueUpMinutes = clsMeter.METER_GetMeterTrueUpTimeValue(objDictionary,sessionMeter,strHostType,strSpotNumber);
		if(strTrueUpMinutes.equals(strExpectedMinutes))
		{Reporter.log("The Meter True Up Time equaled ("+strTrueUpMinutes+")");}
  		else
  		{
  			String strInvocationCounter = objDictionary.get("strInvocationCounter");
  			String strTestSuiteName = objDictionary.get("strTestSuiteName");
  			String strTestCaseName = objDictionary.get("strTestCaseName");
  			clsCommonWeb.SENTRYMOBILE_GlobalPay_EnterSpaceNumber(objDictionary,sessionLocalMeter,strHostType,strMeterSpotName);
  			clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_AfterHome"+strInvocationCounter);
  			clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null, "The Meter True Up Time did not equal "+strExpectedMinutes+" - Actual Time ("+strTrueUpMinutes+")");
  		}
  	}

	//*********************************************************************************************************************************************************************************
	//Wait Until Meter Remaining Time Equals Zero
	//*********************************************************************************************************************************************************************************
	public void METER_WaitUntilMeterRemainingTimeEqualZero(Map<String, String> objDictionary,WebDriver driver,Session sessionMeter, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,null,sessionMeter,strSpotNumber);
		Reporter.log("strMeterRemainingTime: ("+strMeterRemainingTime+") Method-"+strMethodName);
		int intSecondsBeforeViolation = (int) Math.round(Double.parseDouble(strMeterRemainingTime) / 60.00) * 60;
		Reporter.log("intSecondsBeforeViolation: ("+intSecondsBeforeViolation+") Method-"+strMethodName);
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,intSecondsBeforeViolation + 5, "Waiting for meter Remaining Time to to equal zero-Spot"+strSpotNumber);
  		strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,null,sessionMeter,strSpotNumber);
	}
	public void METER_WaitUntilMeterRemainingTimeEqualZero(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver,"1");
		Reporter.log("strMeterRemainingTime: ("+strMeterRemainingTime+") Method-"+strMethodName);
		int intSecondsBeforeViolation = (int) Math.round(Double.parseDouble(strMeterRemainingTime) / 60.00) * 60;
		Reporter.log("intSecondsBeforeViolation: ("+intSecondsBeforeViolation+") Method-"+strMethodName);
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,intSecondsBeforeViolation + 5, "Waiting for meter Remaining Time to to equal zero-Spot"+strSpotNumber);
  		strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver,"1");
	}
	public void METER_WaitUntilMeterRemainingTimeEqualZero2(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver,"1");
		Reporter.log("strMeterRemainingTime: ("+strMeterRemainingTime+") Method-"+strMethodName);
		int intSecondsBeforeViolation = (int) Math.round(Double.parseDouble(strMeterRemainingTime) / 60.00) * 60;
		Reporter.log("intSecondsBeforeViolation: ("+intSecondsBeforeViolation+") Method-"+strMethodName);
  		clsMeter.METER_MeterWaitWithMessage2(objDictionary,intSecondsBeforeViolation + 5, "Waiting for meter Remaining Time to to equal zero-Spot"+strSpotNumber);
  		strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver,"1");
	}
	public void METER_WaitUntilMeterViolates(Map<String, String> objDictionary,WebDriver driver, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver,"1");
		Reporter.log("strMeterRemainingTime: ("+strMeterRemainingTime+") Method-"+strMethodName);
		int intSecondsBeforeViolation = Integer.parseInt(strMeterRemainingTime);
  		Reporter.log("intSecondsBeforeViolation: ("+intSecondsBeforeViolation+") Method-"+strMethodName);
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,intSecondsBeforeViolation + 5, "Waiting for meter to violate-Spot"+strSpotNumber);
  		Reporter.log("Waited ("+Integer.parseInt(strMeterRemainingTime)+") seconds for meter to Violate");
  		clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterViolationEqualsTrue} NA", 60,strSpotNumber,"Local");
	}
	public void METER_WaitForMeterArrowsToDisappear(Map<String, String> objDictionary,WebDriver driver)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		String strEnvironment = objDictionary.get("strEnvironment");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		String strScreenTimeoutTime = clsMeter.METER_GetScreenTimeoutWhenNoSpotSelected(objDictionary,driver);
		System.out.println("Waiting for meter arrows to disappear");
		if(strEnvironment.equals("QA"))
		{
			int intScreenTimeoutTime = Integer.parseInt(strScreenTimeoutTime) * 3000;
			try {Thread.sleep(intScreenTimeoutTime);}catch (Exception e) {}
			System.out.println("Meter arrows should be gone");
		}
		else
		{
			int intScreenTimeoutTime = Integer.parseInt(strScreenTimeoutTime) * 1200;
			try {Thread.sleep(intScreenTimeoutTime);}catch (Exception e) {}
			System.out.println("Meter arrows should be gone");
		}
	}
	public void METER_WaitUntilViolationGracePeriodExpires(Map<String, String>  objDictionary,WebDriver driver)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strParkingViolationGracePeriod = clsMeter.METER_GetParkingViolationGracePeriod(objDictionary,driver);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingViolationGracePeriod), "Waiting for grace period to violate-Spot"+strSpotNumber);
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");

	}
	public void METER_WaitUntilUnlockTimeExpires(Map<String, String>  objDictionary,WebDriver driver)
	{
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strParkingUnlockTimeMinutes = clsMeter.METER_GetParkingUnlockTime(objDictionary,driver);
  		int intParkingUnlockTimeSeconds = Integer.parseInt(strParkingUnlockTimeMinutes) * 60;
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,intParkingUnlockTimeSeconds, "Waiting for unlock time to expire-Spot"+strSpotNumber);
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
	}
	//Combine this into one function
	public String METER_GetParkingInitialGracePeriodTime(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_INIT_GRACE_PERIOD");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_INIT_GRACE_PERIOD for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return strOutput.trim();
	}
	public String METER_GetScreenTimeoutWhenNoSpotSelected(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_ACCEPT_COIN_NO_SPOT_SCREEN_TIMEOUT");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_ACCEPT_COIN_NO_SPOT_SCREEN_TIMEOUT for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    	}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_ACCEPT_COIN_NO_SPOT_SCREEN_TIMEOUT) didn't exist on the meter");}
        return strOutput.trim();
	}
	public String METER_GetParkingViolationGracePeriod(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
	 	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_VIOLATION_GRACE_PERIOD");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_VIOLATION_GRACE_PERIOD for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_VIOLATION_GRACE_PERIOD) didn't exist on the meter");}
        return strOutput.trim();
	}
	//When True Up Is enabled this is the max time before Meter Locks
	public String METER_GetParkingUnlockTime(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_UNLOCK_TIME");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_VIOLATION_GRACE_PERIOD for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_UNLOCK_TIME) didn't exist on the meter");}
        return strOutput.trim();
	}
	public String METER_GetParkingMinsAcceptFreeBeforeRatePayment(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_MINS_ACCEPT_FREE_BEFORE_RATE_PAYMENT");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_MINS_ACCEPT_FREE_BEFORE_RATE_PAYMENT for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_MINS_ACCEPT_FREE_BEFORE_RATE_PAYMENT) didn't exist on the meter");}
        return strOutput.trim();
	}
	public String METER_GetParkingNoFineSupport(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strOutput = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/settings_get_setting.py PARKING_NOFINE_SUPPORT");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		strOutput = new String(tmp, 0, i);
		        		Reporter.log("The PARKING_VIOLATION_GRACE_PERIOD for Meter ("+strMeterName+") is ("+strOutput.trim()+") seconds");
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (/usr/local/bin/settings_get_setting.py PARKING_NOFINE_SUPPORT) didn't exist on the meter");}
        return strOutput.trim();
	}


	//*********************************************************************************************************************************************************************************
	//Get Meter Valid Time Remaining
	//*********************************************************************************************************************************************************************************
	public String GetMeterValidTimeRemaining(Map<String, String> objDictionary, WebDriver driver, Session sessionMeter, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPythonScriptExisted = "False";
    	String strValidTimeRemaining = "";
    	try
	    {
	    	Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        String strSpotVariables = "";
	        int intRowNumber = 0;
	        switch (strSpotNumber)
	        {
        		case "1":
        			intRowNumber = lines.length - 1;
        			break;
        		case "2":
        			intRowNumber = lines.length - 2;
        			break;
        		default:
        			UpdateErrorMessageWithPivotalData(objDictionary,null,"Spot Number ("+strSpotNumber+") has not been added to - "+strMethodName);
	        }
	        strSpotVariables = lines[intRowNumber];
	        System.out.println(strSpotVariables);
	        in.close();
	        channel.disconnect();
	        System.out.println("Meter Begun Value: "+strSpotVariables.substring(strSpotVariables.indexOf("ValidTimeRemaining,")+19, strSpotVariables.indexOf("|Violation")));
			return strSpotVariables.substring(strSpotVariables.indexOf("ValidTimeRemaining,")+19, strSpotVariables.indexOf("|Violation"));
    	}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return strValidTimeRemaining;
    }
	public String GetMeterValidTimeRemaining(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
    	String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	String strValidTimeRemaining = "";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        String strSpotVariables = "";
	        int intRowNumber = 0;
	        switch (strSpotNumber)
	        {
        		case "1":
        			intRowNumber = lines.length - 1;
        			break;
        		case "2":
        			intRowNumber = lines.length - 2;
        			break;
        		default:
        			UpdateErrorMessageWithPivotalData(objDictionary,driver,"Spot Number ("+strSpotNumber+") has not been added to - "+strMethodName);
	        }
	        strSpotVariables = lines[intRowNumber];
	        System.out.println(strSpotVariables);
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strUTC = dateFormatGmt.format(new Date());
    		System.out.println("Meter Begun Value: "+strSpotVariables.substring(strSpotVariables.indexOf("ValidTimeRemaining,")+19, strSpotVariables.indexOf("|Violation")).replace(".0", "")+" UTC: "+strUTC);
    		System.out.println("Meter Begun Value: "+strSpotVariables.substring(strSpotVariables.indexOf("ValidTimeRemaining,")+19, strSpotVariables.indexOf("|Violation"))+" UTC: "+strUTC);
			return strSpotVariables.substring(strSpotVariables.indexOf("ValidTimeRemaining,")+19, strSpotVariables.indexOf("|Violation"));
    	}
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return strValidTimeRemaining;
    }


	//Used for Meter Logging
	public void METER_KillTraceLogProcess(Map<String, String> objDictionary,WebDriver driver)
  	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
    	//Local Logs
		if(strHost != null)
		{
			try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        if(strMeterUser.equals("seco"))
		        {
		        	Channel channel=session.openChannel("exec");
		        	((ChannelExec)channel).setCommand("ps -ef | grep journalctl| grep -v grep | awk '{print $2}'|xargs kill -9");
		        	channel.connect();
		        	Reporter.log("<font color='orange'>Tracelog Process Where Ended</font>");
		        }
		        else
		        {
		        	Channel channel=session.openChannel("exec");
		        	((ChannelExec)channel).setCommand("ps | grep \"tail -f /var/log/Xsession.log\" | grep -v grep | awk '{print $1}' | xargs kill -9");
		        	channel.connect();
		        	Reporter.log("<font color='orange'>Tracelog Process Where Ended</font>");
		        }
	    	}
			catch(Exception e)
			{
		    	UpdateErrorMessageWithPivotalData(objDictionary,driver,null,"The script (nohup tail -f Xsession.log > /tmp/Tracelog.txt &) failed to execute ("+strHost+")-Hint: Check if meter is in maintenance mode","True");
		    }
		}
		//Remote Logs
    	String strRemoteMeterExists = "True";
    	int intRemoteMeterCounter = 1;
		do
		{
			String strRemoteUser = objDictionary.get("strRemoteUser"+intRemoteMeterCounter);
			String strRemoteHost = objDictionary.get("strRemoteHost"+intRemoteMeterCounter);
			if(strRemoteUser != null)
			{
				try
		    	{
			    	Session session = jsch.getSession(strRemoteUser, strRemoteHost, port);
			    	session.setPassword(strPassword);
			        session.setConfig("StrictHostKeyChecking", "no");
			        session.connect();
			        if(strRemoteUser.equals("seco"))
			        {
				        Channel channel=session.openChannel("exec");
				        ((ChannelExec)channel).setCommand("ps -ef | grep journalctl| grep -v grep | awk '{print $2}'|xargs kill -9");
				        channel.connect();
			        }
			        else
			        {
			        	Channel channel=session.openChannel("exec");
				        ((ChannelExec)channel).setCommand("ps | grep \"tail -f /var/log/Xsession.log\" | grep -v grep | awk '{print $1}' | xargs kill -9");
				        channel.connect();
			        }
			        Reporter.log("<font color='orange'>Tracelog Process Where Ended</font>");
		    	}
			    catch(Exception e)
			    {
			    	UpdateErrorMessageWithPivotalData(objDictionary,driver,null,"The script (nohup tail -f Xsession.log > /tmp/Tracelog.txt &) failed to execute ("+strHost+")-HINT Check IP Address","True");
			    }
			}
	        else
			{strRemoteMeterExists = "False";}
	        intRemoteMeterCounter++;
		}while (strRemoteMeterExists.equals("True"));
  	}
	public void METER_StartLogTrace(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strLocalHost = objDictionary.get("strHost");
		if(strLocalHost != null)
		{
			String strPassword = objDictionary.get("strUniquePassword");
			//String strRemoteDeviceId = objDictionary.get("strRemoteDeviceId"); if(strRemoteDeviceId == null){strRemoteDeviceId = "";};
			String strMeterUser = objDictionary.get("strMeterUser");
			String strCommand;
			if(strMeterUser.equals("seco")){strCommand =  "journalctl -t sentry.service -f -o cat > /tmp/LocalTracelog.txt";}
			else{strCommand = "/usr/bin/nohup tail -f /var/log/Xsession.log > /tmp/LocalTracelog.txt &";}
			//Local Log Trace
			int port=22;
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strLocalHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand(strCommand);
		        channel.connect();
		        Reporter.log("<font color='orange'>Single or Dual MeterTracelog Started</font>");
	    	}
	    	catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,driver,null,"The script ("+strCommand+") failed to execute ("+strLocalHost+")","True");}
			//Remote Log Trace
	    	String strRemoteMeterExists = "True";
	    	int intRemoteMeterCounter = 1;
			do
			{
				String strRemoteHost = objDictionary.get("strRemoteHost"+intRemoteMeterCounter);
		        if(strRemoteHost != null)
			    {
			    	String strRemoteUser = objDictionary.get("strRemoteUser"+intRemoteMeterCounter);
			    	if(strRemoteUser.equals("seco")){strCommand =  "journalctl -t sentry.service -f -o cat > /tmp/RemoteTracelog"+intRemoteMeterCounter+".txt";}
					else{strCommand = "/usr/bin/nohup tail -f /var/log/Xsession.log > /tmp/RemoteTracelog"+intRemoteMeterCounter+".txt &";}
					try
					{
				    	Session session = jsch.getSession(strRemoteUser, strRemoteHost, port);
				    	session.setPassword(strPassword);
				        session.setConfig("StrictHostKeyChecking", "no");
				        session.connect();
				        Channel channel=session.openChannel("exec");
				        ((ChannelExec)channel).setCommand(strCommand);
				        channel.connect();
				        Reporter.log("<font color='orange'>Global Pay Meter Tracelog Remote"+intRemoteMeterCounter+" Started</font>");
			    	}catch(Exception e)
					{
//			    		UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script ("+strCommand+") failed to execute ("+strRemoteHost+")");
			    	}
				}
		        else
				{strRemoteMeterExists = "False";}
		        intRemoteMeterCounter++;
			}while (strRemoteMeterExists.equals("True"));
		}
	}
	public void METER_StartLogTrace2(Map<String, String> objDictionary,WebDriver driver,String strTraceLogName)
	{

		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
		if(strMeterUser.equals("root"))
		{
			try
			{
				Session session = jsch.getSession(strMeterUser, host, port);
				session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        System.out.println("/usr/bin/nohup tail -f /var/log/Xsession.log > /tmp/"+strTraceLogName+".txt &");
		        ((ChannelExec)channel).setCommand("/usr/bin/nohup tail -f /var/log/Xsession.log > /tmp/"+strTraceLogName+".txt &");
		        channel.connect();
		        Reporter.log("<font color='orange'>Tracelog Started</font>");
			}
		    catch
		    (Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script (nohup tail -f Xsession.log > /tmp/Tracelog.txt &) failed to execute ("+strHost+")");}
		}
		else
		{
			try
			{
				Session session = jsch.getSession(strMeterUser, host, port);
				session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        System.out.println("/usr/bin/journalctl -t sentry.service -f -o cat > /tmp/\"+strTraceLogName+\".txt &");
		        ((ChannelExec)channel).setCommand("/bin/journalctl -t sentry.service -f -o cat > /tmp/"+strTraceLogName+".txt &");
		        channel.connect();
		        Reporter.log("<font color='orange'>Tracelog Started</font>");
			}
		    catch
		    (Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script (nohup tail -f Xsession.log > /tmp/Tracelog.txt &) failed to execute ("+strHost+")");}
		}
	}
	public void METER_CopyLogTraceLocally(Map<String, String> objDictionary, WebDriver driver,String strTestCaseName) throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLocalHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		File directory = new File(".");
		String strCommand;
		String strPath = directory.getCanonicalPath() +"/MeterLogs";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir()) {System.out.println("Directory is created!");}
			else {System.out.println("Failed to create directory!");}
		}
		String strFullMeterLogsPath = strPath+"/Local_"+strTestCaseName+".txt";
		String strMeterUser = objDictionary.get("strMeterUser");
		//Need to get the version of sshpass.  if 1.09 you need to add -O
		if(strMeterUser.equals("root"))
		{
			//Need to update this based on OS
			String osName = System.getProperty("os.version");
			if(osName.equals("11.7.10")||osName.equals("10.16"))
			{
				strCommand = "sshpass -p "+strPassword+" scp -r root@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;
			}
			else
			{
				strCommand = "sshpass -p "+strPassword+" scp -O -r root@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;
			}
		}
		else
		{strCommand = "sshpass -p "+strPassword+" scp -O -r seco@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;}
		//{strCommand = "sshpass -p "+strPassword+" scp -O -r seco@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;}
		System.out.println("strCommand: "+strCommand);
		String[] command1 = {"sh","-c",strCommand};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = null;
			String strErrorMessage = "";
			while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
			if(!strErrorMessage.equals(""))
			{
				if(!strErrorMessage.contains("scp"))
				{
					System.out.println(strErrorMessage+"-"+strMethodName);
					UpdateErrorMessageWithPivotalData(objDictionary,driver,strErrorMessage+"-"+strMethodName);
				}
				return;
			}
		}
		catch (IOException e)
		{UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to Copy Log Trace Locally-"+strMethodName);}
		int intRemoteMeterCounter = 1;
		String strRemoteMeterExists = "True";
		do
		{
			String strRemoteUser = objDictionary.get("strRemoteUser"+intRemoteMeterCounter);
			if(strRemoteUser != null)
			{
				strFullMeterLogsPath = strPath+"/Remote"+intRemoteMeterCounter+"_"+strTestCaseName+".txt";
				String strRemoteHost = objDictionary.get("strRemoteHost"+intRemoteMeterCounter);
				if(strRemoteUser.equals("seco"))
				{strCommand = "sshpass -p "+strPassword+" scp -r seco@"+strRemoteHost+":/tmp/RemoteTracelog"+intRemoteMeterCounter+".txt "+strFullMeterLogsPath;}
				else
				{strCommand = "sshpass -p "+strPassword+" scp -r root@"+strRemoteHost+":/tmp/RemoteTracelog"+intRemoteMeterCounter+".txt "+strFullMeterLogsPath;}
				String[] command2 = {"sh","-c",strCommand};
				try
				{
					Process proc = Runtime.getRuntime().exec(command2);
					BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
					String s = null;
					String strErrorMessage = "";
					while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
					if(!strErrorMessage.equals("")){System.out.println(strErrorMessage+"-"+strMethodName);}
				}
				catch (IOException e)
				{UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to Copy Log Trace Locally-"+strMethodName);}
			}
			else
			{
				strRemoteMeterExists = "False";
			}
			intRemoteMeterCounter++;
		}while (strRemoteMeterExists.equals("True"));
		objDictionary.put("strMeterLogExist", "True");
	}
//	public void METER_CopyLogTraceLocally(Map<String, String> objDictionary, WebDriver driver,String strTestCaseName) throws Exception
//	{
//		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String strLocalHost = objDictionary.get("strHost");
//		String strPassword = objDictionary.get("strUniquePassword");
//		File directory = new File(".");
//		String strCommand;
//		String strPath = directory.getCanonicalPath() +"/MeterLogs";
//		File file = new File(strPath);
//		if (!file.exists())
//		{
//			if (file.mkdir()) {System.out.println("Directory is created!");}
//			else {System.out.println("Failed to create directory!");}
//		}
//		String strFullMeterLogsPath = strPath+"/Local_"+strTestCaseName+".txt";
//		String strMeterUser = objDictionary.get("strMeterUser");
//		//Need to get the version of sshpass.  if 1.09 you need to add -O
//		if(strMeterUser.equals("root"))
//		{
//			//Need to update this based on OS
//			String osName = System.getProperty("os.version");
//			if(osName.equals("11.7.10")||osName.equals("10.16"))
//			{
//				strCommand = "sshpass -p "+strPassword+" scp -r root@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;
//			}
//			else
//			{
//				strCommand = "sshpass -p "+strPassword+" scp -O -r root@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;
//			}
//		}
//		else
//		{strCommand = "sshpass -p "+strPassword+" scp -O -r seco@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;}
//		//{strCommand = "sshpass -p "+strPassword+" scp -O -r seco@"+strLocalHost+":/tmp/LocalTracelog.txt "+strFullMeterLogsPath;}
//		System.out.println("strCommand: "+strCommand);
//		String[] command1 = {"sh","-c",strCommand};
//		try
//		{
//			Process proc = Runtime.getRuntime().exec(command1);
//			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//			String s = null;
//			String strErrorMessage = "";
//			while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
//			if(!strErrorMessage.equals(""))
//			{
//				System.out.println(strErrorMessage+"-"+strMethodName);
//				UpdateErrorMessageWithPivotalData(objDictionary,driver,strErrorMessage+"-"+strMethodName);
//				return;
//			}
//		}
//		catch (IOException e)
//		{UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to Copy Log Trace Locally-"+strMethodName);}
//		int intRemoteMeterCounter = 1;
//		String strRemoteMeterExists = "True";
//		do
//		{
//			String strRemoteUser = objDictionary.get("strRemoteUser"+intRemoteMeterCounter);
//			if(strRemoteUser != null)
//			{
//				strFullMeterLogsPath = strPath+"/Remote"+intRemoteMeterCounter+"_"+strTestCaseName+".txt";
//				String strRemoteHost = objDictionary.get("strRemoteHost"+intRemoteMeterCounter);
//				if(strRemoteUser.equals("seco"))
//				{strCommand = "sshpass -p "+strPassword+" scp -r seco@"+strRemoteHost+":/tmp/RemoteTracelog"+intRemoteMeterCounter+".txt "+strFullMeterLogsPath;}
//				else
//				{strCommand = "sshpass -p "+strPassword+" scp -r root@"+strRemoteHost+":/tmp/RemoteTracelog"+intRemoteMeterCounter+".txt "+strFullMeterLogsPath;}
//				String[] command2 = {"sh","-c",strCommand};
//				try
//				{
//					Process proc = Runtime.getRuntime().exec(command2);
//					BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//					String s = null;
//					String strErrorMessage = "";
//					while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
//					if(!strErrorMessage.equals("")){System.out.println(strErrorMessage+"-"+strMethodName);}
//				}
//				catch (IOException e)
//				{UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to Copy Log Trace Locally-"+strMethodName);}
//			}
//			else
//			{
//				strRemoteMeterExists = "False";
//			}
//			intRemoteMeterCounter++;
//		}while (strRemoteMeterExists.equals("True"));
//		objDictionary.put("strMeterLogExist", "True");
//	}

	public void METER_CopyLogTraceLocally2(Map<String, String> objDictionary, WebDriver driver,String strTestCaseName, String strTraceLogName) throws InterruptedException
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		File directory = new File(".");
		String strPath = "";
		try
		{
			strPath = directory.getCanonicalPath() +"/MeterLogs";
		}catch (IOException e){UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);}
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir())
			{System.out.println("Directory is created!");}
			else
			{System.out.println("Failed to create directory!");}
		}
		String strFullMeterLogsPath = strPath+"/"+strTestCaseName+".txt";
		Reporter.log("METER_CopyLogTraceLocally2-Directory: "+strFullMeterLogsPath);
		String strMeterUser = objDictionary.get("strMeterUser");
		if(strMeterUser.equals("root"))
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r root@"+strHost+":/tmp/"+strTraceLogName+".txt "+strFullMeterLogsPath};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to Copy Log Trace Locally-"+strMethodName);
			}
		}
		else
		{
			System.out.println("sshpass -p "+strPassword+" scp -r seco@"+strHost+":/tmp/"+strTraceLogName+".txt "+strFullMeterLogsPath);
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r seco@"+strHost+":/tmp/"+strTraceLogName+".txt "+strFullMeterLogsPath};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to Copy Log Trace Locally-"+strMethodName);
			}
		}
		Reporter.log("<font color='orange'>Trace Copied Locally</font>");
	}
	public void METER_KillGlobalPayLogTrace2(Map<String, String> objDictionary) throws InterruptedException
	{
		JSch jsch = new JSch();
		String strLocalHost = objDictionary.get("strHost");
		String strRemoteHost = objDictionary.get("strRemoteHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		//Local
		try
		{
			Session session = jsch.getSession(strMeterUser, strLocalHost, port);
			session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("ps -ef | grep '/bin/journalctl -t sentry.service -f -o cat'| grep -v grep | awk '{print $2}' | xargs kill -9");
	        channel.connect();
	        Reporter.log("<font color='orange'>Global Pay Log Trace Ended</font>");
	    }
	    catch
	    (Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script (ps -ef | grep '/bin/journalctl -t sentry.service -f -o cat'| grep -v grep | awk '{print $2}' | xargs kill -9) failed to execute ("+strLocalHost+")");}
		//Remote
		try
		{
			Session session = jsch.getSession(strMeterUser, strRemoteHost, port);
			session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("ps -ef | grep '/bin/journalctl -t sentry.service -f -o cat'| grep -v grep | awk '{print $2}' | xargs kill -9");
	        channel.connect();
	        Reporter.log("<font color='orange'>Global Pay Log Trace Ended</font>");
	    }
	    catch
	    (Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,null,"The script (ps -ef | grep '/bin/journalctl -t sentry.service -f -o cat'| grep -v grep | awk '{print $2}' | xargs kill -9) failed to execute ("+strRemoteHost+")");}

	}
	public void METER_ScanMeterLogForValue(Map<String, String> objDictionary, WebDriver driver,String strTestCaseName, String strValues)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		File directory = new File(".");
		String strPath = "";
		try {strPath = directory.getCanonicalPath() +"/MeterLogs";}catch (IOException e){UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);}
		String strFullMeterLogsPath = strPath+"/"+strTestCaseName+".txt";
		File file = new File(strFullMeterLogsPath);
		String[] arrMessages = strValues.split("\\|", -1);
		int intNumberOfMessages = arrMessages.length;
		int intCounter = 0;
		try
		{
		    Scanner scanner = new Scanner(file);
		    int lineNum = 0;
		    while (scanner.hasNextLine())
		    {
		    	String line = scanner.nextLine();
		        if(line.contains(arrMessages[intCounter]))
		        {
		        	Reporter.log("The values ("+arrMessages[intCounter]+") existed in the Meter Logs");
		        	System.out.println("The Text ("+arrMessages[intCounter]+") existed in the meter logs");
		        	intCounter++;if(intNumberOfMessages == intCounter){break;}
		        }
		        lineNum++;
		    }
		}
		catch(FileNotFoundException e)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The File ("+file+") did not exist in the MeterLogs");
		}
		if(intNumberOfMessages != intCounter)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Value ("+arrMessages[intCounter]+") did not exist in the Meter Logs");
		}
	}

	public String METER_CheckIfValueExistsInMeterLogs(Map<String, String> objDictionary, WebDriver driver,String strTestCaseName, String strValues)
	{
		String strValueExists = "False";
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		File directory = new File(".");
		String strPath = "";
		try {strPath = directory.getCanonicalPath() +"/MeterLogs";}catch (IOException e){UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);}
		String strFullMeterLogsPath = strPath+"/"+strTestCaseName+".txt";
		File file = new File(strFullMeterLogsPath);
		String[] arrMessages = strValues.split("\\|", -1);
		int intNumberOfMessages = arrMessages.length;
		int intCounter = 0;
		try
		{
		    Scanner scanner = new Scanner(file);
		    int lineNum = 0;
		    while (scanner.hasNextLine())
		    {
		    	String line = scanner.nextLine();
		    	System.out.println(line);
		        if(line.contains(arrMessages[intCounter]))
		        {
		        	Reporter.log("The values ("+arrMessages[intCounter]+") existed in the Meter Logs");
		        	System.out.println("The Text ("+arrMessages[intCounter]+") existed in the meter logs");
		        	strValueExists = "True";
		        	objDictionary.put("strValueExistedLine",line);
		        	lineNum++;
		        	break;
		        }
		        lineNum++;
		    }
		}
		catch(FileNotFoundException e)
		{
			//UpdateErrorMessageWithPivotalData(objDictionary,driver,"The File ("+file+") did not exist in the MeterLogs");
		}
		return strValueExists;
	}



	//*********************************************************************************************************************************************************************************
	//Check For Errors in Meter Logs
	//*********************************************************************************************************************************************************************************
	public String METER_CheckForErrorsInMeterLogs(Map<String, String> objDictionary, String strError)
	{
		Meter clsMeter = new Meter();
		String strTestCaseName = objDictionary.get("strTestCaseName");
		String strTestSuiteName = objDictionary.get("strTestSuiteName");
		String strInvocationCounter = objDictionary.get("strInvocationCounter");
		//This line should stop a endless loop in the CopyLogTraceLocally Function
		if(strError.contains("sh: sshpass: command not found"))
		{return strError;}
		try{clsMeter.METER_CopyLogTraceLocally(objDictionary, null,  strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);}catch (Exception e) {}
		String strNewError = strError;
		strNewError = clsMeter.METER_CheckIfCallstackOrStacktraceOrTracebackExistInMeterLogs(objDictionary, null,"Local","Local_"+strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter, strError);
		int intRemoteMeterCounter = 1;
		String strRemoteMeterExists = "True";
		if(strError.equals(strNewError))
		{
			do
			{
				String strRemoteDeviceId = objDictionary.get("strRemoteDeviceId"+intRemoteMeterCounter);
				if(strRemoteDeviceId != null)
				{
	 				strNewError = clsMeter.METER_CheckIfCallstackOrStacktraceOrTracebackExistInMeterLogs(objDictionary, null,"Remote"+intRemoteMeterCounter,"Remote"+intRemoteMeterCounter+"_"+strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter, strError);
				}
				else{strRemoteMeterExists = "False";}
				if(!strError.equals(strNewError))
				{
					strError = strNewError;
					break;
				}
				intRemoteMeterCounter++;
			}while (strRemoteMeterExists.equals("True"));
		}
		else
		{
			strError = strNewError;
		}
		return strError;
	}
	public String METER_CheckIfCallstackOrStacktraceOrTracebackExistInMeterLogs(Map<String, String> objDictionary, WebDriver driver,String strHostType,String strFileName, String strError)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strTestSuiteName = objDictionary.get("strTestSuiteName");
		String strInvocationCounter = objDictionary.get("strInvocationCounter");
		File directory = new File(".");
		String strPath = "";try {strPath = directory.getCanonicalPath() +"/MeterLogs";}catch (IOException e){UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);}
		File file = new File( strPath+"/"+strFileName+".txt");
		String strCallstackExists = "False";String strStackTraceExists = "False";String strTracebackExists = "False";String strSpaceFailedToLoadExists = "False";
		String strCallstackLine = "";String strStackTraceLine = "";String strTracebackLine = "";String strSpaceFailedToLoadLine = "False";
		try
		{
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine())
		    {
		    	String line = scanner.nextLine();
		    	System.out.println(line);
		    	if(line.contains("Traceback") && strTracebackExists.equals("False"))
		    	{
		    		if(!scanner.nextLine().contains("file_io.py"))
		        	{
		        		strTracebackExists = "True";strTracebackLine = line;
		        	}
		    	}
		        if(line.contains("Callstack") && strCallstackExists.equals("False"))
		        {
		        	strCallstackExists = "True";strCallstackLine = line;break;
		        }
		        if(line.contains("STACKTRACE") && strStackTraceExists.equals("False"))
		        {
		        	strStackTraceExists = "True";strStackTraceLine = line;break;
		        }
		        if(line.contains("Unable to complete parking_session initialization via SL") && strTracebackExists.equals("False"))
		        {
		        	strSpaceFailedToLoadExists = "True";strSpaceFailedToLoadLine = line;
		        }
		    }
		}catch(FileNotFoundException e)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,driver,null,"The File ("+file+") did not exist in the MeterLogs","True");
		}
		if(strCallstackExists.equals("True"))
		{
			if(strError.equals("")){UpdateErrorMessageWithPivotalData(objDictionary,null,"The meter log contains a Callstack error-("+strCallstackLine+") "+strHostType+"_"+strTestSuiteName+"_"+strFileName);}
			else{Reporter.log("<font color='7d3c98'>"+strHostType+" meter log contains a Callstack error-"+strHostType+"_"+strTestSuiteName+"_"+strFileName+"</font>");}
		}
		else{Reporter.log(strHostType+ " meter log did not contain Callstack");}
		if(strStackTraceExists.equals("True") && !strError.equals(""))
		{
			if(strError.equals(""))
			{UpdateErrorMessageWithPivotalData(objDictionary,null,"The meter log contains a STACKTRACE error-("+strStackTraceLine+") "+strHostType+"_"+strTestSuiteName+"_"+strFileName);}
			else{Reporter.log("<font color='7d3c98'>"+strHostType+" meter log contains a STACKTRACE error-"+strHostType+"_"+strTestSuiteName+"_"+strFileName+"</font>");}
		}
		else{Reporter.log(strHostType+ " meter log did not contain STACKTRACE");}
		if(strTracebackExists.equals("True"))
		{
			if(strError.equals("")){UpdateErrorMessageWithPivotalData(objDictionary,null,"The meter log contains a Traceback error-("+strSpaceFailedToLoadLine+") "+strFileName);}
			else
			{strError  = "The meter log contains a Traceback error-("+strTracebackLine+") "+strHostType+"_"+strTestSuiteName+"_"+strFileName;}
		}
		else{Reporter.log(strHostType+ " meter log did not contain Traceback");}
		if(strSpaceFailedToLoadExists.equals("True"))
		{
			if(strError.equals("")){UpdateErrorMessageWithPivotalData(objDictionary,null,"The meter log contains a Space Failed To Load error-("+strSpaceFailedToLoadLine+") "+strFileName);}
			else{Reporter.log("<font color='7d3c98'>"+strHostType+" meter log contains a Space Failed To Load error-"+strFileName+"</font>");}
		}
		else{Reporter.log(strHostType+ " meter log did not contain Space Failed To Load error");}
		return strError;
	}



	public String METER_StoreMeterLogTime(Map<String, String> objDictionary, WebDriver driver,String strFileName, String strActionName, String strMeterLogContainsValue)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		File directory = new File(".");
		String strPath = "";
		try {strPath = directory.getCanonicalPath() +"/MeterLogs";}catch (IOException e){UpdateErrorMessageWithPivotalData(objDictionary,driver,e+"-"+strMethodName);}
		String strFullMeterLogsPath = strPath+"/"+strFileName+".txt";
		File file = new File(strFullMeterLogsPath);
		try
		{
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine())
		    {
		    	String line = scanner.nextLine();
		    	if(line.contains(strMeterLogContainsValue))
		        {
		        	return line.substring(line.indexOf(" "), line.indexOf("]"));
		        }
		    }
		    UpdateErrorMessageWithPivotalData(objDictionary,driver,"The value ("+strMeterLogContainsValue+") in Meter Log ("+strFileName+") did not exist-"+strMethodName);
		}
		catch(FileNotFoundException e)
		{ UpdateErrorMessageWithPivotalData(objDictionary,driver,"The File ("+file+") did not exist in the MeterLogs");}
		return "";
	}
	public void METER_CalculateLatencyBetweenTwoMeterTimes(Map<String, String> objDictionary, WebDriver driver, String strActionName, String strStartTime, String strEndTime)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Database clsDatabase = new Database();
		Meter clsMeter = new Meter();
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		try
		{
			long d1=formater.parse(strStartTime).getTime();
	    	long d2=formater.parse(strEndTime).getTime();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,strActionName,(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),"");
	    	Reporter.log(strActionName+": "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
	    }
		catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unable to convert time into MilliSecons-"+strMethodName);}
	}


	//*******************************************************************************************************************************************************************************************
	//Scan Meter Logs Until Value Appears or Timeout Reached
	//*******************************************************************************************************************************************************************************************
	public void METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(Map<String, String> objDictionary,Session sessionMeter, String strHost, String strContainsValue, int intWaitSeconds, String strActionName, String strSpotNumber, String strHostType)
	{
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strLocalHost = objDictionary.get("strHost");
		String strDebugMask = objDictionary.get("strDebugMask");if(strDebugMask == null) {strDebugMask = "0";}
		//Get StrMeterUser
		String strMeterUser = "";
		String strCommandString = "";
		String strFlag = "False";
		//Get strMeterUser
		if(strHost.equals(strLocalHost)){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length()-1));}
		//Start Meter Scan
		if(strMeterUser.equals("root")){strCommandString = "sshpass -p "+strPassword+" ssh root@"+strLocalHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
		else{strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strLocalHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";}
		//Set Command
		String[] command1 = {"sh","-c",strCommandString};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader strInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        String strErrorRow = "";
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("Scan Start At: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		String strStackDumpOccured = "False";
    		int intCounter = 0;
    		while (strFlag.equals("False"))
			{
	        	//Store Parking Id
				String strStackTraceTime = dateFormatGmt.format(new Date());
				long d1=formater.parse(strScanStartTime).getTime();
		    	long d2=formater.parse(strStackTraceTime).getTime();
		    	if(strContainsValue.contains("SCREEN_MULTI_SELECT_SPACE") && (int)TimeUnit.MILLISECONDS.toMillis(d2-d1) > 5000 && strStackDumpOccured.equals("False"))
				{
		    		System.out.println((int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
		    		System.out.print("dump_output.txt");
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType,"testauto_dump_stacks.py > /tmp/dump_output.txt");
					strStackDumpOccured = "True";
				}
	        	s = strInput.readLine();
				System.out.println(s);
				if(s != null)
				{
					//The 2nd part of the below statement is used when coin payment happens on remote space.
					if(s.contains(strContainsValue)||strContainsValue.contains("do_rabbitmq_send_event(): success()") && s.contains("poll_for_virtual_payment_completion(): success"))
					{
						System.out.println(s.indexOf("]"));
						if(s.indexOf("]") == 19)
						{
							String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]")).trim();
							String strScanEndTime2 = dateFormatGmt.format(new Date());
							if(intCounter == 0) {Reporter.log("<font color='orange'>First Row Scanned: "+strScanEndTime+"</font>");intCounter++;}
							try
							{
								if(strContainsValue.equals("on_message_pass()"))//Credit Call
						    	{
									String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
									if(strEnableAllImages.equals("True"))
									{
										METER_CopyMeterScreenShotLocally(objDictionary,sessionMeter,"NA");
									}
									String strMeterLogsTime = s.substring(s.indexOf(" "), s.indexOf("]"));
									//System.out.println(dateFormatGmt.format(new Date()));
									Reporter.log("<font color='orange'>The text (on_message_pass()) appeared as expected in the meter logs-"+strMeterLogsTime+"</font>");
						    		long e1=formater.parse(strScanStartTime).getTime();
						    		System.out.println("e1: "+e1);
							    	long e2=formater.parse(strScanEndTime2).getTime();
							    	System.out.println("e2: "+e2);
							    	Reporter.log("<font color='orange'>It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the value ("+strContainsValue+") to appear in the logs</font>");
							  		//The above is the Credit Call scan we have to scan for success message.
						    		if(strHostType.equals("Remote")){strContainsValue = "poll_for_virtual_payment_completion(): success";}
						    		else{strContainsValue = "do_rabbitmq_send_event(): success():";}
						    		//Reset Timers
						    		strScanStartTime = dateFormatGmt.format(new Date());
						    		System.out.println("strScanStartTime: "+strScanStartTime);
						    		Reporter.log("strScanStartTime: "+strScanStartTime);
						    		intWaitSeconds = 130;if(Integer.parseInt(strDebugMask) >= 5){intWaitSeconds = 120;}
						    	}
						    	else
						    	{
						    		//Python script time minus current time
						    		String strMeterLogsTime = s.substring(s.indexOf(" "), s.indexOf("]"));
						    		System.out.println(dateFormatGmt.format(new Date()));
						    		Reporter.log("<font color='orange'>The text ("+strContainsValue+") appeared as expected in the meter logs-"+strMeterLogsTime+"</font>");
						    		//Store Payment Time Of Successful Credit Card
						    		if(strContainsValue.contains("poll_for_virtual_payment_completion(): success")||strContainsValue.contains("do_rabbitmq_send_event(): success()")||strContainsValue.contains("\"result\":\"success\""))
						    		{
						    			//Used To Calculate The Time Between Payments.
						    			objDictionary.put("strLastSuccessfulPaymentTime", strMeterLogsTime);
						    			Reporter.log("<font color='a334db'>strLastSuccessfulPaymentTime: "+strMeterLogsTime+"</font>");
						    		}
						    		long e1=formater.parse(strScanStartTime).getTime();long e2=formater.parse(strScanEndTime2).getTime();
							    	Reporter.log("<font color='orange'>It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the value ("+strContainsValue+") to appear in the logs</font>");
							    	proc.destroy();
						    		return;
						    	}
							}
							catch (Exception e)
							{
								clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,s+"-Unable to convert time into MilliSeconds-"+strMethodName,"True");
							}
						}
					}
					if(s.contains("ERROR")){strErrorRow = strErrorRow +"\n"+ s;}
					if(s.contains("UI_STATE_IDLE: Unhandled Event: on_space_entered")){strErrorRow = s;}
					if(s.contains("Timers cannot be started from another thread ")){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"Mark needs log and pivotal when this error occurs: +"+s+"_"+strMethodName);}
					//if(s.contains("not in self._valid_numbers")){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"_"+strMethodName);}
					if(s.contains("Bad file descriptor")){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"_"+strMethodName);}
				}
				else
				{
					String strUTC = dateFormatGmt.format(new Date());
					UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Meter row was blank when scanning the logs-UTC: "+strUTC,"True");
				}
				//System.out.println(s.indexOf("]"));
				if(s.indexOf("]") == 19)
				{
					String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
					if(intCounter == 0) {Reporter.log("Time on First Row Scanned: "+strScanEndTime);intCounter++;}
					if(!strScanStartTime.equals(""))
					{
						try
						{
							long e1=formater.parse(strScanStartTime).getTime();
					    	long e2=formater.parse(strScanEndTime).getTime();
					    	int intWaitMilliSeconds = intWaitSeconds * 1000;
					    	if((int)TimeUnit.MILLISECONDS.toMillis(e2-e1) > intWaitMilliSeconds)
					    	{
					    		Reporter.log("strScanEndTime: "+strScanEndTime);
					    		if(strErrorRow.equals(""))
								{
					    			//Check If Payment Still Exists on Remote Meter
					    			if(strContainsValue.contains("\"result\":\"success\""))
					    			{
					    				//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null,sessionMeter, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>");
					    				clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
						    		}
					    			Reporter.log("Time Waited: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited: "+intWaitMilliSeconds);
									UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Failed: Value ("+strContainsValue+") did not appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName,"False");
					    		}
								else
								{
									if(strContainsValue.contains("\"result\":\"success\""))
					    			{
										//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null,sessionMeter,strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>");
								    	clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
									}
									else if(strErrorRow.contains("not in self._valid_numbers"))
									{
										strErrorRow = "not in self._valid_numbers";
									}
									Reporter.log("Time Waited: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited: "+intWaitMilliSeconds);
							    	Reporter.log("<font color=' Brown'>Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName+")</font>");
					    			UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName,"False");
							    	//UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,strErrorRow+"-"+strMethodName,"False");
								}
					    		proc.destroy();
					    	}
						}catch (Exception e)
						{
							clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,s+"-Unable to convert time into MilliSecons-"+strMethodName,"True");
						}
					}
				}
			}
		}catch (Exception e)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,e+"-"+strMethodName,"False");
		}
	}
	public String METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(Map<String, String> objDictionary,String strHost, String strContainsValue, int intWaitSeconds, String strActionName, String strSpotNumber, String strHostType) 
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Database clsDatabase = new Database();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strLocalHost = objDictionary.get("strHost");
		//Get StrMeterUser 
		String strMeterUser = "";
		String strCommandString = "";
		String strFlag = "False";
		if (strHost.equals(strLocalHost)) {
			strMeterUser = objDictionary.get("strMeterUser");
		} else {
			strMeterUser = objDictionary.get("strRemoteUser");
		}
		if (strMeterUser.equals("root")) {
			strCommandString = "sshpass -p " + strPassword + " ssh root@" + strLocalHost
					+ " '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";
		} else {
			strCommandString = "sshpass -p " + strPassword + " ssh seco@" + strLocalHost
					+ " '/bin/journalctl -t sentry.service -f -o cat'; echo $?";
		}
		String[] command1 = { "sh", "-c", strCommandString };
		try 
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        String strErrorRow = "";
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("Scan Start At: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		String strStackDumpOccured = "False";
    		int intCounter = 0;
    		int intDumpStackWaitCounter = 0;
	        while (strFlag.equals("False"))
			{
	        	//Store Parking Id
				String strStackTraceTime = dateFormatGmt.format(new Date());
				long d1=formater.parse(strScanStartTime).getTime();
		    	long d2=formater.parse(strStackTraceTime).getTime();
		    	if(strContainsValue.contains("SCREEN_MULTI_SELECT_SPACE") && (int)TimeUnit.MILLISECONDS.toMillis(d2-d1) > 5000 && strStackDumpOccured.equals("False"))
				{
		    		System.out.println((int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_dump_stacks.py > /tmp/dump_output.txt");
					strStackDumpOccured = "True";
				}
	        	s = stdInput.readLine();
				System.out.println("Expected Value: "+strContainsValue+" Row Values "+ s);
				if(s != null)
				{
					intDumpStackWaitCounter++;
					//The 2nd part of the below statement is used when coin payment happens on remote space.
					if(s.contains(strContainsValue)||strContainsValue.contains("do_rabbitmq_send_event(): success()") && s.contains("poll_for_virtual_payment_completion(): success"))
					{
						System.out.println(s.indexOf("]"));
						if(s.indexOf("]") == 19)
						{
							String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]")).trim();
							String strScanEndTime2 = dateFormatGmt.format(new Date());
							if(intCounter == 0) {Reporter.log("<font color='orange'>First Row Scanned: "+strScanEndTime+"</font>");intCounter++;}
							try
							{
								if(strContainsValue.equals("on_message_pass()"))//Credit Call
						    	{
									String strMeterLogsTime = s.substring(s.indexOf(" "), s.indexOf("]"));
									System.out.println("1-"+dateFormatGmt.format(new Date()));
									Reporter.log("<font color='orange'>The text (on_message_pass()) appeared as expected in the meter logs-"+strMeterLogsTime+"</font>");
						    		long e1=formater.parse(strScanStartTime).getTime();
							    	long e2=formater.parse(strScanEndTime2).getTime();
							    	Reporter.log("<font color='orange'>It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the value ("+strContainsValue+") to appear in the logs</font>");
							  		//The above is the Credit Call scan we have to scan for success message.
						    		if(strHostType.equals("Remote")){strContainsValue = "poll_for_virtual_payment_completion(): success";} 
						    		else{strContainsValue = "do_rabbitmq_send_event(): success():";}
						    		//Reset Timers
						    		strScanStartTime = dateFormatGmt.format(new Date());
						    		Reporter.log("strScanStartTime: "+strScanStartTime);
						    		intWaitSeconds = 90;
						    	}
						    	else
						    	{
						    		//Python script time minus current time
						    		String strMeterLogsTime = s.substring(s.indexOf(" "), s.indexOf("]"));
						    		System.out.println("2-"+dateFormatGmt.format(new Date()));
						    		System.out.println("Darin");
						    		Reporter.log("<font color='orange'>The text ("+strContainsValue+") appeared as expected in the meter logs-"+strMeterLogsTime+"</font>");
						    		//Store Payment Time Of Successful Credit Card
						    		if(strContainsValue.contains("poll_for_virtual_payment_completion(): success")||strContainsValue.contains("do_rabbitmq_send_event(): success()")||strContainsValue.contains("\"result\":\"success\""))
						    		{
						    			//Used To Calculate The Time Between Payments.
						    			objDictionary.put("strLastSuccessfulPaymentTime", strMeterLogsTime);
						    			Reporter.log("<font color='a334db'>strLastSuccessfulPaymentTime: "+strMeterLogsTime+"</font>");
						    		}
						    		long e1=formater.parse(strScanStartTime).getTime();long e2=formater.parse(strScanEndTime2).getTime();
							    	Reporter.log("<font color='orange'>It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the value ("+strContainsValue+") to appear in the logs</font>");
							    	proc.destroy();
							    	strFlag = "True";
							    	break;
						    	}
							}catch (Exception e)
							{
								clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSeconds-"+strMethodName);
							}
						}
					}
					if(s.contains("ERROR")){strErrorRow = strErrorRow +"\n"+ s;}
					if(s.contains("UI_STATE_IDLE: Unhandled Event: on_space_entered")){strErrorRow = s;}
					if(s.contains("Timers cannot be started from another thread ")){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"Mark needs log and pivotal when this error occurs: +"+s+"_"+strMethodName);}
					//if(s.contains("not in self._valid_numbers")){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"_"+strMethodName);}
					if(s.contains("Bad file descriptor")){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"_"+strMethodName);}
				}
				else
				{
					String strUTC = dateFormatGmt.format(new Date());
					UpdateErrorMessageWithPivotalData(objDictionary,null,"Meter row was blank when scanning the logs-UTC: "+strUTC);
				}
				//System.out.println(s.indexOf("]"));
				if(s.indexOf("]") == 19)
				{
					String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
					if(intCounter == 0) {Reporter.log("Time on First Row Scanned: "+strScanEndTime);intCounter++;}
					if(!strScanStartTime.equals(""))
					{
						try
						{
							long e1=formater.parse(strScanStartTime).getTime();
					    	long e2=formater.parse(strScanEndTime).getTime();
					    	int intWaitMilliSeconds = intWaitSeconds * 1000;
					    	if((int)TimeUnit.MILLISECONDS.toMillis(e2-e1) > intWaitMilliSeconds)
					    	{
					    		Reporter.log("strScanEndTime: "+strScanEndTime);
					    		HttpConnections clsHttpConnections = new HttpConnections();
					    		if(strErrorRow.equals(""))
								{
					    			//Check If Payment Still Exists on Remote Meter
					    			if(strContainsValue.contains("\"result\":\"success\""))
					    			{
					    				//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>"); 
					    				clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
						    		}
					    			Reporter.log("Time Waited: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited: "+intWaitMilliSeconds);
							    	//Reporter.log("Failed: Value ("+strContainsValue+") did not appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
									UpdateErrorMessageWithPivotalData(objDictionary,null,"Failed: Value ("+strContainsValue+") did not appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
					    		}
								else
								{
									if(strContainsValue.contains("\"result\":\"success\""))
					    			{
										//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>"); 
								    	//clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
									}
									else if(strContainsValue.contains("on_message_pass()"))
									{
										UpdateErrorMessageWithPivotalData(objDictionary,null,"Failed: Value ("+strContainsValue+") did not appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
									}
									else if(strErrorRow.contains("not in self._valid_numbers"))
									{
										strErrorRow = "not in self._valid_numbers";
									}
									Reporter.log("Time Waited"+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited"+intWaitMilliSeconds);
							    	Reporter.log("<font color=' Brown'>Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName+")</font>");
					    			if(strContainsValue.equals("PARKING_STATE_VEHICLE_LEFT"))
			    					{
					    				UpdateErrorMessageWithPivotalData(objDictionary,null,"Failed: Value ("+strContainsValue+") did not appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
								    }
					    			{UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorRow+"-"+strMethodName);}
								}
					    		proc.destroy();
					    	}
						}catch (Exception e)
						{
							clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);
						}
					}	
				}
			}
		}catch (Exception e)
		{UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		return "False";
	}
	//*******************************************************************************************************************************************************************************************
	//Conditional Meter Scan
	//*******************************************************************************************************************************************************************************************
	public String METER_ConditionalScanMeterLogs(Map<String, String> objDictionary,Session sessionMeter, String strHost, String strContainsValue, int intWaitSeconds, String strActionName, String strSpotNumber, String strHostType)
	{
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strLocalHost = objDictionary.get("strHost");
		String strDebugMask = objDictionary.get("strDebugMask");if(strDebugMask == null) {strDebugMask = "0";}
		String strMeterUser = "";
		String strCommandString = "";
		String strFlag = "False";
		if(strHost.equals(strLocalHost)){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.substring(strHostType.length()-1));}
		
//		if(strMeterUser.equals("root")){strCommandString = "sshpass -p "+strPassword+" ssh root@"+strLocalHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
//		else{strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strLocalHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";}
		
		if(strMeterUser.equals("root")){strCommandString = "sshpass -p "+strPassword+" ssh root@"+strHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
		else{strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";}
		
		
		
		String[] command1 = {"sh","-c",strCommandString};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader strInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("Scan Start At: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		int intCounter = 0;
    		while (strFlag.equals("False"))
			{
	        	String strStackTraceTime = dateFormatGmt.format(new Date());
				s = strInput.readLine();
				System.out.println(s);
//				if(s == null)
//				{
//					String strUTC = dateFormatGmt.format(new Date());
//					UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Meter row was blank when scanning the logs-UTC: "+strUTC,"True");
//				}
				if(s.contains(strContainsValue))
				{
					System.out.println(s.indexOf("]"));
					if(s.indexOf("]") == 19)
					{
						String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]")).trim();
						String strScanEndTime2 = dateFormatGmt.format(new Date());
						if(intCounter == 0) {Reporter.log("<font color='orange'>First Row Scanned: "+strScanEndTime+"</font>");intCounter++;}
						try
						{
				    		String strMeterLogsTime = s.substring(s.indexOf(" "), s.indexOf("]"));
				    		System.out.println(dateFormatGmt.format(new Date()));
				    		Reporter.log("<font color='orange'>The text ("+strContainsValue+") appeared as expected in the meter logs-"+strMeterLogsTime+"</font>");
				    		long e1=formater.parse(strScanStartTime).getTime();long e2=formater.parse(strScanEndTime2).getTime();
					    	Reporter.log("<font color='orange'>It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the value ("+strContainsValue+") to appear in the logs</font>");
					    	proc.destroy();
				    		return "True";
						}
						catch (Exception e)
						{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,s+"-Unable to convert time into MilliSeconds-"+strMethodName,"True");}
					}
				}
				else
				{
					if(s.indexOf("]") == 19)
					{
						String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
						if(intCounter == 0) {Reporter.log("Time on First Row Scanned: "+strScanEndTime);intCounter++;}
						if(!strScanStartTime.equals(""))
						{
							try
							{
								long e1=formater.parse(strScanStartTime).getTime();
						    	long e2=formater.parse(strScanEndTime).getTime();
						    	int intWaitMilliSeconds = intWaitSeconds * 1000;
						    	if((int)TimeUnit.MILLISECONDS.toMillis(e2-e1) > intWaitMilliSeconds)
						    	{return "False";}
							}
							catch (Exception e)
							{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			int lineNumber = 0;
			StackTraceElement[] stackTrace = e.getStackTrace();
		    if (stackTrace.length > 0) {
		        lineNumber = stackTrace[0].getLineNumber();
		        System.out.println("Exception occurred at line " + lineNumber);
		    }
			UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-Line Number"+lineNumber+"-"+strMethodName);
		}
		return "False";
	}

	//*******************************************************************************************************************************************************************************************
	//Scan Meter Remote Meter Logs
	//*******************************************************************************************************************************************************************************************
	public String METER_ScanRemoteMeterLogsUntilValueAppearsOrTimeOutReachedNew(Map<String, String> objDictionary,Session sessionMeter,String strHost, String strContainsValue, int intWaitSeconds, String strActionName, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Database clsDatabase = new Database();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		//Get StrMeterUser
		String strRemoteHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else
		{
			strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));
			strRemoteHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));
		}
		String strCommandString = "";
		int intCounter = 0;
		String strFlag = "False";
		if(strMeterUser.equals("root"))
		{strCommandString = "sshpass -p "+strPassword+" ssh root@"+strRemoteHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
		else
		{
			//System.out.println("/usr/local/bin/sshpass -p "+strPassword+" ssh seco@"+strLocalHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?");
			strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strRemoteHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";
		}
		String[] command1 = {"sh","-c",strCommandString};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("strScanStartTime: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		while (strFlag.equals("False"))
			{
				s = stdInput.readLine();
				System.out.println(s);
				if(s != null)
				{
					if(s.indexOf("]") == 19)
					{
						String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));

						if(s.contains(strContainsValue))
						{
							if(intCounter == 0) {Reporter.log("Time on First Row Scanned: "+strScanEndTime);intCounter++;}
							try
							{
								long d1=formater.parse(strScanStartTime).getTime();
						    	long d2=formater.parse(strScanEndTime).getTime();
						    	clsDatabase.WriteToActionTimeDatabase(objDictionary,strActionName,(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
						    	Reporter.log("<font color=' #723c98'>The meter log value ("+strContainsValue+") appeared at ("+strScanEndTime+"), taking ("+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1)+") Milliseconds on host ("+strHost+")</font>");
						    	proc.destroy();
						    	break;
							}catch (Exception e){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);}
						}
						else
						{
							try
							{
								long d1=formater.parse(strScanStartTime).getTime();
						    	long d2=formater.parse(strScanEndTime).getTime();
						    	int intWaitMilliSeconds = intWaitSeconds * 1000;
						    	if((int)TimeUnit.MILLISECONDS.toMillis(d2-d1) > intWaitMilliSeconds)
						    	{
						    		Reporter.log("strScanEndTime: "+strScanEndTime);
						    		Reporter.log("Time Waited: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
								    Reporter.log("Time To Waited: "+intWaitMilliSeconds);
									UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+") Spot ("+strSpotNumber+") Meter Session ("+sessionMeter.toString().replace("com.jcraft.jsch.Session@", "")+")-"+strMethodName,"False");
						    		proc.destroy();
						    	}
							}catch (Exception e){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);}
						}
					}
				}
				else
				{
					String strUTC = dateFormatGmt.format(new Date());
					UpdateErrorMessageWithPivotalData(objDictionary,null,"Meter row was blank when scanning the logs-UTC: "+strUTC);
				}
			}
		}catch (Exception e)
		{UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		return "False";
	}
	public String METER_ScanRemoteMeterLogsUntilValueAppearsOrTimeOutReached(Map<String, String> objDictionary,Session sessionMeter,String strHost, String strContainsValue, int intWaitSeconds, String strActionName, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Database clsDatabase = new Database();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		//String strRemoteHost = objDictionary.get("strRemoteHost");
		//Get StrMeterUser
		String strRemoteHost = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else
		{
			strHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));
			strRemoteHost = objDictionary.get("strRemoteHost"+strHostType.substring(strHostType.length() - 1));
		}
		String strCommandString = "";
		String strFlag = "False";
		if(strMeterUser.equals("root"))
		{strCommandString = "sshpass -p "+strPassword+" ssh root@"+strRemoteHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
		else
		{
			//System.out.println("/usr/local/bin/sshpass -p "+strPassword+" ssh seco@"+strLocalHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?");
			strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strRemoteHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";
		}
		String[] command1 = {"sh","-c",strCommandString};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        String strErrorRow = "";
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("strScanStartTime: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		int intCounter = 0;
	        while (strFlag.equals("False"))
			{
				s = stdInput.readLine();
				System.out.println(s);
				if(s != null)
				{
					//The 2nd part of the below statement is used when coin payment happens on remote space.
					if(s.contains(strContainsValue)||strContainsValue.contains("do_rabbitmq_send_event(): success()") && s.contains("poll_for_virtual_payment_completion(): success"))
					{
						//System.out.println(s.indexOf("]"));
						if(s.indexOf("]") == 19)
						{
							String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
							if(intCounter == 0) {Reporter.log("Time on First Row Scanned: "+strScanEndTime);intCounter++;}
							try
							{
								long d1=formater.parse(strScanStartTime).getTime();
						    	long d2=formater.parse(strScanEndTime).getTime();
						    	clsDatabase.WriteToActionTimeDatabase(objDictionary,strActionName,(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
						    	Reporter.log("<font color=' #723c98'>The meter log value ("+strContainsValue+") appeared at ("+strScanEndTime+"), taking ("+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1)+") Milliseconds on host ("+strHost+")</font>");
						    	proc.destroy();
						    	break;
							}catch (Exception e)
							{
								clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);
							}
						}
					}
					//if(s.contains("ERROR")){strErrorRow = s;}
					if(s.contains("UI_STATE_IDLE: Unhandled Event: on_space_entered")){strErrorRow = s;}
					if(s.contains("Timers cannot be started from another thread "))
					{
						clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"Mark needs log and pivotal when this error occurs: +"+s+"_"+strMethodName);
					}
				}
				else
				{
					String strUTC = dateFormatGmt.format(new Date());
					UpdateErrorMessageWithPivotalData(objDictionary,null,"Meter row was blank when scanning the logs-UTC: "+strUTC);
				}
				//System.out.println(s.indexOf("]"));
				if(s.indexOf("]") == 19)
				{
					String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
					if(!strScanStartTime.equals(""))
					{
						try
						{
							long d1=formater.parse(strScanStartTime).getTime();
					    	long d2=formater.parse(strScanEndTime).getTime();
					    	int intWaitMilliSeconds = intWaitSeconds * 1000;
					    	if((int)TimeUnit.MILLISECONDS.toMillis(d2-d1) > intWaitMilliSeconds)
					    	{
					    		Reporter.log("strScanEndTime: "+strScanEndTime);
					    		HttpConnections clsHttpConnections = new HttpConnections();
					    		if(strErrorRow.equals(""))
								{
					    			//Check If Payment Still Exists on Remote Meter
					    			if(strContainsValue.contains("\"result\":\"success\""))
					    			{
					    				//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>");
								    	clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
						    		}
					    			Reporter.log("Time Waited: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited: "+intWaitMilliSeconds);
									UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+") Spot ("+strSpotNumber+") Meter Session ("+sessionMeter.toString().replace("com.jcraft.jsch.Session@", "")+")-"+strMethodName,"False");
					    		}
								else
								{
									if(strContainsValue.contains("\"result\":\"success\""))
					    			{
										//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>");
								    	clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
									}
									Reporter.log("Time Waited"+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited"+intWaitMilliSeconds);
							    	Reporter.log("Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
					    			UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,strErrorRow+"-"+strMethodName,"False");
								}
					    		proc.destroy();
					    	}
						}catch (Exception e)
						{
							clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);
						}
					}
				}
			}
		}catch (Exception e)
		{UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		return "False";
	}
	public String METER_ScanRemoteMeterLogsUntilValueAppearsOrTimeOutReached(Map<String, String> objDictionary,String strHost, String strContainsValue, int intWaitSeconds, String strActionName, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Database clsDatabase = new Database();
		Meter clsMeter = new Meter();
		String strPassword = objDictionary.get("strUniquePassword");
		String strRemoteHost = objDictionary.get("strRemoteHost");
		//Get StrMeterUser
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strCommandString = "";
		String strFlag = "False";
		if(strMeterUser.equals("root"))
		{strCommandString = "sshpass -p "+strPassword+" ssh root@"+strRemoteHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
		else
		{
			//System.out.println("/usr/local/bin/sshpass -p "+strPassword+" ssh seco@"+strLocalHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?");
			strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strRemoteHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";
		}
		String[] command1 = {"sh","-c",strCommandString};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        String strErrorRow = "";
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("strScanStartTime: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		int intCounter = 0;
	        while (strFlag.equals("False"))
			{
				s = stdInput.readLine();
				System.out.println(s);
				if(s != null)
				{
					//The 2nd part of the below statement is used when coin payment happens on remote space.
					if(s.contains(strContainsValue)||strContainsValue.contains("do_rabbitmq_send_event(): success()") && s.contains("poll_for_virtual_payment_completion(): success"))
					{
						//System.out.println(s.indexOf("]"));
						if(s.indexOf("]") == 19)
						{
							String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
							if(intCounter == 0) {Reporter.log("Time on First Row Scanned: "+strScanEndTime);intCounter++;}
							try
							{
								long d1=formater.parse(strScanStartTime).getTime();
						    	long d2=formater.parse(strScanEndTime).getTime();
						    	clsDatabase.WriteToActionTimeDatabase(objDictionary,strActionName,(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
						    	Reporter.log("<font color=' #723c98'>The meter log value ("+strContainsValue+") appeared at ("+strScanEndTime+"), taking ("+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1)+") Milliseconds on host ("+strHost+")</font>");
						    	proc.destroy();
						    	break;
							}catch (Exception e)
							{
								clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);
							}
						}
					}
					if(s.contains("ERROR")){strErrorRow = s;}
					if(s.contains("UI_STATE_IDLE: Unhandled Event: on_space_entered")){strErrorRow = s;}
					if(s.contains("Timers cannot be started from another thread "))
					{
						clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"Mark needs log and pivotal when this error occurs: +"+s+"_"+strMethodName);
					}
				}
				else
				{
					String strUTC = dateFormatGmt.format(new Date());
					UpdateErrorMessageWithPivotalData(objDictionary,null,"Meter row was blank when scanning the logs-UTC: "+strUTC);
				}
				//System.out.println(s.indexOf("]"));
				if(s.indexOf("]") == 19)
				{
					String strScanEndTime = s.substring(s.indexOf(" "), s.indexOf("]"));
					if(!strScanStartTime.equals(""))
					{
						try
						{
							long d1=formater.parse(strScanStartTime).getTime();
					    	long d2=formater.parse(strScanEndTime).getTime();
					    	int intWaitMilliSeconds = intWaitSeconds * 1000;
					    	if((int)TimeUnit.MILLISECONDS.toMillis(d2-d1) > intWaitMilliSeconds)
					    	{
					    		Reporter.log("strScanEndTime: "+strScanEndTime);
					    		HttpConnections clsHttpConnections = new HttpConnections();
					    		if(strErrorRow.equals(""))
								{
					    			//Check If Payment Still Exists on Remote Meter
					    			if(strContainsValue.contains("\"result\":\"success\""))
					    			{
					    				//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>");
								    	clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
						    		}
					    			Reporter.log("Time Waited: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited: "+intWaitMilliSeconds);
									UpdateErrorMessageWithPivotalData(objDictionary,null,"Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
					    		}
								else
								{
									if(strContainsValue.contains("\"result\":\"success\""))
					    			{
										//Spot Number
					    				Reporter.log("strHostType: "+strHostType);
					    				String strValidTimePurchased = clsMeter.GetMeterValidTimePurchased(objDictionary, null, strSpotNumber, strHostType, "After");
					    				Reporter.log("<font color=' Brown'>The Valid Time Purchased at the time of the failure was ("+strValidTimePurchased+")</font>");
								    	clsHttpConnections.HTTPCONNECTIONS_ValidateParkingSessionId(objDictionary, strHostType, strSpotNumber);
									}
									Reporter.log("Time Waited"+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
							    	Reporter.log("Time To Waited"+intWaitMilliSeconds);
							    	Reporter.log("Failed: Value ("+strContainsValue+") didn't appear in the logs after ("+intWaitSeconds+") Seconds on host ("+strHost+")-"+strMethodName);
					    			UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorRow+"-"+strMethodName);
								}
					    		proc.destroy();
					    	}
						}catch (Exception e)
						{
							clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,s+"-Unable to convert time into MilliSecons-"+strMethodName);
						}
					}
				}
			}
		}catch (Exception e)
		{UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		return "False";
	}

	//*********************************************************************************************************************************************************************************
	//Get Current Maintenance Mode
	//*********************************************************************************************************************************************************************************
	public void METER_WaitForMeterScreen(Map<String, String> objDictionary,Session sessionMeter,String strHostType,String strExpectedScreen,String strActionName)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Database clsDatabase = new Database();
		String CurrentUIState = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		String strLocalHost = objDictionary.get("strHost");
		//Start Time
  		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String strScanStartTime = dateFormatGmt.format(new Date());Reporter.log("strScanStartTime: "+strScanStartTime);
		int intCounter = 0;
		do
		{
			CurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,sessionMeter,"1");
			intCounter++;
	  		if(intCounter>70)//IDLE_TIMEOUT_SECONDS_MULTI
	  		{
	  			//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "testautof_touch_screen.py");
	  			clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"The expected meter screen ("+strExpectedScreen+") did not appear current screen equaled ("+CurrentUIState+")-"+strMethodName,"False");
	  		}
		} while (!CurrentUIState.equals(strExpectedScreen));
		String strScanEndTime = dateFormatGmt.format(new Date());
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		try
		{
			long e1=formater.parse(strScanStartTime).getTime();
	    	long e2=formater.parse(strScanEndTime).getTime();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,strActionName,(int)TimeUnit.MILLISECONDS.toMillis(e2-e1),strMeterUser);
	    	Reporter.log("It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Millieconds for the ("+strExpectedScreen+") screen to appear: "+new SimpleDateFormat("hh:mm:ss").format(new Date())+" UTC Time:"+dateFormatGmt.format(new Date()));
	    	System.out.println("It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the ("+strExpectedScreen+") screen to appear");
		}
		catch (Exception e)
		{
			clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"-Unable to convert time into MilliSecons-"+strMethodName);
		}
    }
	public void METER_WaitForMeterScreen(Map<String, String> objDictionary,String strHostType, String strExpectedScreen,String strActionName)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Database clsDatabase = new Database();
		String CurrentUIState = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		String strLocalHost = objDictionary.get("strHost");
		//Start Time
  		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String strScanStartTime = dateFormatGmt.format(new Date());Reporter.log("strScanStartTime: "+strScanStartTime);
		int intCounter = 0;
		do
		{
			CurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
			intCounter++;
	  		if(intCounter>30)//IDLE_TIMEOUT_SECONDS_MULTI
	  		{
	  			String strOnScreenShot = objDictionary.get("strOnScreenShot");
	  			if(strOnScreenShot.equals("False"))
	  			{
	  				clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,null,"The expected meter screen ("+strExpectedScreen+") did not appear current screen equaled ("+CurrentUIState+")-"+strMethodName);
	  			}
	  			break;
	  		}
		} while (!CurrentUIState.equals(strExpectedScreen));
		String strScanEndTime = dateFormatGmt.format(new Date());
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		try
		{
			long e1=formater.parse(strScanStartTime).getTime();
	    	long e2=formater.parse(strScanEndTime).getTime();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,strActionName,(int)TimeUnit.MILLISECONDS.toMillis(e2-e1),strMeterUser);
	    	Reporter.log("It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Millieconds for the ("+strExpectedScreen+") screen to appear");
	    	System.out.println("It took ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Milliseconds for the ("+strExpectedScreen+") screen to appear");
		}
		catch (Exception e)
		{
			clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,"-Unable to convert time into MilliSecons-"+strMethodName);
		}
    }

	//*********************************************************************************************************************************************************************************************
	//Insert Coin
	//*********************************************************************************************************************************************************************************************
	//Coin Payment
	public void METER_InsertCoin(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber, String strHostType)
	{
		Stopwatch timer = Stopwatch.createStarted();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strMaximumDuration = objDictionary.get("strMaximumDuration");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
		//Associated Syncs
		String strLogMessageSyncDisabled = objDictionary.get("strLogMessageSyncDisabled");if(strLogMessageSyncDisabled == null) {strLogMessageSyncDisabled = "False";}
		String strUISyncDisabled = objDictionary.get("strUISyncDisabled");if(strUISyncDisabled == null) {strUISyncDisabled = "False";}
		String strPaymentSyncDisabled = objDictionary.get("strPaymentSyncDisabled");if(strPaymentSyncDisabled == null) {strPaymentSyncDisabled = "False";}
		//Set sessionMeter
		Session sessionMeter = null;if(strHostType.equals("Local")){sessionMeter = sessionLocalMeter;}else{sessionMeter = sessionRemoteMeter;}
		//strFreeTimeMinutes should only be set for Free To Rate
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		//Store Dump Stack Values
		clsMeter.StoreDumpStackValuesInDictionary(objDictionary,driver,sessionMeter,strSpotNumber,strHostType,"Before");
		String strOriginalValidTimePurchase = objDictionary.get("strValidTimePurchased").replace(".0", "");
		String strOriginalMaxRemaining = objDictionary.get("strMaxRemaining");
		String strMeterFreeValue = objDictionary.get("strMeterFreeValue");
		String strValidTimeRemainingSec = objDictionary.get("strValidTimeRemainingSec");
		int intValidTimeRemaining = (int) Math.round(Double.parseDouble(strValidTimeRemainingSec) / 60.00);
		//Only used to indicate which meter screen should appear
		String strRemoteUser = objDictionary.get("strRemoteUser1"); if(strRemoteUser == null){strRemoteUser = "";}
		Double dblExpectedMaxTimeRemaining = 0.0;
		//Touch Local Meter
		String strLocalHost = objDictionary.get("strHost");
		int intRemainingFreeTime = 0;
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,sessionMeter);
		//clsMeter.METER_GetUptime(objDictionary,sessionLocalMeter,sessionRemoteMeter,strHostType,"BeforeCoinPayment");
		String strMinutesBeforeFreeParking = objDictionary.get("strMinutesBeforeFreeParking");if(strMinutesBeforeFreeParking == null){strMinutesBeforeFreeParking = "";}
		//A change around Rate to free
		if(!strFreeTimeMinutes.equals("0") && strMinutesBeforeFreeParking.equals(""))
		{
			String strFreeParkingStartTime = objDictionary.get("strFreeParkingStartTime");
			intRemainingFreeTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strFreeParkingStartTime, strFreeTimeMinutes);
			//intRemainingFreeTime = clsMeter.METER_CalculateRemainingMinutesBeforeFreeStartTime(objDictionary, driver);
		}
		//Calculate Max Time Remaining
		if(Double.parseDouble(strOriginalMaxRemaining) == Double.parseDouble(strMaximumDuration) + intRemainingFreeTime)
		{dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment) - intRemainingFreeTime;}
		else
		{
			if(Double.parseDouble(strOriginalMaxRemaining) < Double.parseDouble(strMeterIncrementTime))
			{dblExpectedMaxTimeRemaining = 0.0;}
			else
			{
				//Rate To Free Check if In Free
				if(strOriginalValidTimePurchase.equals("0"))
				{
					if(strMinutesBeforeFreeParking.equals(""))
					{
						if(Double.parseDouble(strMeterIncrementTime) + Double.parseDouble(strFreeTimeMinutes) + Double.parseDouble(strFreeTimeFirstPayment) >= Double.parseDouble(strOriginalMaxRemaining))
						{dblExpectedMaxTimeRemaining = 0.0;}
						else
						{dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime -  Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment);}
					}
					else
					{
						if(Double.parseDouble(strMeterIncrementTime) + Double.parseDouble(strFreeTimeFirstPayment) >= Double.parseDouble(strOriginalMaxRemaining))
						{dblExpectedMaxTimeRemaining = 0.0;}
						else
						{dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime -  Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment);}
					}
				}
				else
				{
					if(Double.parseDouble(strMeterIncrementTime) >= Double.parseDouble(strOriginalMaxRemaining)){dblExpectedMaxTimeRemaining = 0.0;}
					else
					{
						HttpConnections clsHttpConnections = new HttpConnections();
						String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
						System.out.println("Parking State: "+strParkingSpotState);
						if(strParkingSpotState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))
						{
							dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intValidTimeRemaining - Double.parseDouble(strMeterIncrementTime);
						}
						else
						{
							dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - Double.parseDouble(strMeterIncrementTime);
						}
					}
				}
			}
		}
		//Get Current Screen
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
		String strExpectedScreen = "SCREEN_MULTI_HOME";//Global
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		if(strMeterUser.equals("root"))
		{
  			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
		}
		//Calculate Time Between Payments
  		String strLastSuccessfulPaymentTime = objDictionary.get("strLastSuccessfulPaymentTime");if(strLastSuccessfulPaymentTime == null) {strLastSuccessfulPaymentTime = "";}
  		if(!strLastSuccessfulPaymentTime.equals(""))
  		{
  			try
  			{
	  			SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
	  			formater.setTimeZone(TimeZone.getTimeZone("GMT"));
	  			String strCurrentTimeGMT = formater.format(new Date());
	  			long e1=formater.parse(strLastSuccessfulPaymentTime).getTime();
		    	long e2=formater.parse(strCurrentTimeGMT).getTime();
		    	Reporter.log("Current Time: "+strCurrentTimeGMT+"- Last Payment Time: "+strLastSuccessfulPaymentTime);
	  			Reporter.log("<font color='a334db'>Time Between Payments ("+(int)TimeUnit.MILLISECONDS.toMillis(e2-e1)+") Millis</font>");
  			}catch (Exception e){clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, null,e+"-Time Between Payment Unable to convert time into MilliSeconds-"+strMethodName);}
  		}
  		if(strUISyncDisabled.equals("False"))
		{
	  		String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,sessionLocalMeter,"1");
	  		if(!strCurrentUIState.equals(strExpectedScreen))
			{
	  			clsMeter.METER_CopyMeterScreenShotLocally(objDictionary,sessionLocalMeter,"Wrong Screen") ;
	  			UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"Unable to add a payment, The expected screen ("+strExpectedScreen+") did not appear - actual screen ("+strCurrentUIState+")-"+strMethodName,"False");
	  		}
		}
  		if(strForcedMulti.contains("false") && strRemoteUser.equals(""))
		{
 			//Dual and Single Meter
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strLocalHost,"testauto_insert_coins_spot_"+strSpotNumber+".py");
			if(!strNumberOfSpots.equals("1"))
			{
				//If Spot 1 is active, and spot 2 is in maintenance mode, the Apply Payment Screen will not appear
				String strMaintenanceModeSpot2 = clsMeter.METER_GetCurrentMaintenanceMode(objDictionary, driver, "2", strHostType);
				if(!strMaintenanceModeSpot2.equals("True"))
				{
		  			clsMeter.METER_WaitForMeterScreen(objDictionary,sessionLocalMeter,"Local","SCREEN_DUAL_APPLY_PAYMENT", "HomeToApplyPayment"+strHostType+" Space");
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strLocalHost, "testautof_apply_payment.py SPOT_"+strSpotNumber);
				}
			}
			//Wait For "success" Text to exists before additional payments
			if(strLogMessageSyncDisabled.equals("False"))
			{
				if(strHostType.equals("Remote")){clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "\"result\":\"success\"", 20,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
		  		else{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "do_rabbitmq_send_event(): success()", 40,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
			}
	  		//Set Expected Screen
	  		strExpectedScreen = "SCREEN_MULTI_HOME";
	  		if(strRemoteUser.equals("")){if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}}
	  		if(strUISyncDisabled.equals("False"))
	  		{
	  			clsMeter.METER_WaitForMeterScreen(objDictionary,sessionLocalMeter,"Local",strExpectedScreen, "TransactionDetailsToAvailableTime"+strHostType+" Space");
	  		}
	  	}
		else
		{
			//Global Pay Meter
			clsMeter.METER_GetMeterSubScreen(objDictionary,null,sessionLocalMeter,"1");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
			//clsMeter.METER_GetMeterUIState(objDictionary,null,sessionLocalMeter,"1");
	  		if(strLogMessageSyncDisabled.equals("False"))
			{
				String strDebugMask = objDictionary.get("strDebugMask");if(strDebugMask == null) {strDebugMask = "0";}
				String strDoRabbitmgSendEventWaitTimeCoin = objDictionary.get("strDoRabbitmgSendEventWaitTimeCoin");
				int intScanWait = Integer.parseInt(strDoRabbitmgSendEventWaitTimeCoin);
				if(Integer.parseInt(strDebugMask) > 4){intScanWait = 120;}
				//do_rabbitmq_send_event(): success() That’s where Sentry in the meter gets an acknowledgement for sending a message to SL
				if(strHostType.contains("Remote")){clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost,"\"result\":\"success\"",intScanWait,"Coin Payment "+strHostType+" Space",strSpotNumber, strHostType);}
				else{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost,"do_rabbitmq_send_event(): success()",intScanWait,"Coin Payment "+strHostType+" Space",strSpotNumber,strHostType);}
			}
	  		clsMeter.METER_GetMeterUIState(objDictionary,null,sessionLocalMeter,"1");
		}
		//Take Screen Shot
		String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
		if(strEnableAllImages.equals("True"))
		{
			String strInvocationCounter = objDictionary.get("strInvocationCounter");
			String strTestSuiteName = objDictionary.get("strTestSuiteName");
			String strTestCaseName = objDictionary.get("strTestCaseName");
			clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_AfterCoin"+strInvocationCounter);
		}
		if(strPaymentSyncDisabled.equals("False"))
		{
			int intCounter = 0;
			String CurrentValidTimePurchase = "";
			do
			{
				CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,sessionMeter,strSpotNumber,strHostType,"After");
	      		intCounter++;
	      		if(intCounter>20){UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The ("+strHostType+") Meter time did not increment correctly-"+strMethodName,"False");}
	     	} while (strOriginalValidTimePurchase.equals(CurrentValidTimePurchase));
			String strValidateMeterTime = objDictionary.get("strValidateMeterTime");if(strValidateMeterTime == null) {strValidateMeterTime = "True";}
			if(strValidateMeterTime.equals("True"))
			{
				if(strMeterFreeValue.equals("False"))
				{
					double dblExpectedMeterValue;
					if(Double.parseDouble(strOriginalValidTimePurchase) == 0)
					{dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + Double.parseDouble(strMeterIncrementTime) + Double.parseDouble(strFreeTimeFirstPayment) + intRemainingFreeTime;}
					else
					{dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + Double.parseDouble(strMeterIncrementTime);}
					if(dblExpectedMeterValue == Double.parseDouble(CurrentValidTimePurchase))
					{Reporter.log("Meter Time decremented correctly after coin payment-("+dblExpectedMeterValue+")");}
					else
					{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"Meter Time did not decremented correctly after coin payment-expected ("+dblExpectedMeterValue+")-actual ("+CurrentValidTimePurchase+")-"+strMethodName);}
				}
				//Validate MaxRemainingTime (Function)
				HttpConnections clsHttpConnections = new HttpConnections();
				String strParkingState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
				String strMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,sessionMeter,strSpotNumber,strHostType);
				if(strParkingState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))//Need a way to validate Presumed Occupied
	      		{
	      			double dbllessThanTime = dblExpectedMaxTimeRemaining+1;
					if(dblExpectedMaxTimeRemaining < Double.parseDouble(strMaxRemaining) &&  (dblExpectedMaxTimeRemaining+1) > Double.parseDouble(strMaxRemaining))
				  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
				  	else
				  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The meter max time remaining presumed occupied ("+strMaxRemaining+") - was not greater than ("+dblExpectedMaxTimeRemaining+") and less than ("+dbllessThanTime+")"+strMethodName,"False");}
				}
	      		else
	      		{
	      			if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining) || dblExpectedMaxTimeRemaining - 1 == Double.parseDouble(strMaxRemaining)|| dblExpectedMaxTimeRemaining + 1 == Double.parseDouble(strMaxRemaining))
	      			//if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining))
	    			{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
				  	else
				  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName,"False");}
	      		}
			}
		}
		//This takes be 2 - 3 seconds
		//Parking Sesson
		//HttpConnections clsHttpConnections = new HttpConnections();
		//clsHttpConnections.HTTPCONNECTIONS_GetParkingSessionId(objDictionary, strHostType, strSpotNumber);
		//clsHttpConnections.HTTPCONNECTIONS_GetSpotEstimatedTimeRemaining(objDictionary, strHostType, strSpotNumber);
		Reporter.log("<font color='#5533ff'>Method ("+strMethodName+") took: " + timer.stop()+"</font>");
	}
	public void METER_InsertCoin(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		Stopwatch timer = Stopwatch.createStarted();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strMaximumDuration = objDictionary.get("strMaximumDuration");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
		String strUnlockValue = objDictionary.get("strUnlockValue");
		if(strUnlockValue == null) {strUnlockValue = "Off";}
		String strTrueUp = objDictionary.get("strTrueUp");if(strTrueUp == null) {strTrueUp = "False";}
		//strFreeTimeMinutes should only be set for Free To Rate
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		//Store Dump Stack Values
		clsMeter.StoreDumpStackValuesInDictionary(objDictionary, driver, strSpotNumber, strHostType, "Before");
		//String strValidTimeRemaining = objDictionary.get("strValidTimeRemaining");
		String strOriginalValidTimePurchase = objDictionary.get("strValidTimePurchased").replace(".0", "");
		String strOriginalMaxRemaining = objDictionary.get("strMaxRemaining");
		String strMeterFreeValue = objDictionary.get("strMeterFreeValue");
		String strGracePeriodViolationTime = objDictionary.get("strGracePeriodViolationTime");
		String strMaintenanceModeSpot2 = clsMeter.METER_GetCurrentMaintenanceMode(objDictionary, driver, "2", strHostType);
		String strRemoteUser = objDictionary.get("strRemoteUser"); if(strRemoteUser == null){strRemoteUser = "";}
		String strNoParkingStartTime = objDictionary.get("strNoParkingStartTime");
		String strFamily = objDictionary.get("strFamily");if(strFamily == null){strFamily = "";}
		String strFamilyBuyMinutes = objDictionary.get("strFamilyBuyMinutes");
//		int intMinutesBeforeNoParking = 0;
//		if(strNoParkingTimeStart != null)
//		{
//			intMinutesBeforeNoParking = clsMeter.METER_CalculateRemainingMinutesBeforeNoParking(objDictionary, null,  strNoParkingTimeStart);
//		}
		Double dblExpectedMaxTimeRemaining = 0.0;
		//Touch Local Meter
		String strLocalHost = objDictionary.get("strHost");
		if(strRemoteUser.equals(""))
		{
			//Touch Screen on Dual and Single to Clear Idle Screen
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strLocalHost, "testautof_touch_screen.py");
		}
		int intRemainingFreeTime = 0;
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary, driver,"Local");
		clsMeter.METER_GetUptime(objDictionary, strHostType, "BeforeCoinPayment");
		if(strMeterFreeValue.equals("True"))
		{
			String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
			intRemainingFreeTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
		}
		//Calculate Max Time Remaining
		if(Double.parseDouble(strOriginalMaxRemaining) == Double.parseDouble(strMaximumDuration) + intRemainingFreeTime)
		{
			if(!strFamily.equals("")){strMeterIncrementTime = strFamilyBuyMinutes;}
			if(strNoParkingStartTime == null)
			{
				//This was test code for True Up disabled and Unlock Enabled
				if(strGracePeriodViolationTime != null && strUnlockValue.equals("On") && strTrueUp.equals("False"))//Unlock Enable and True UP disabled
				{
					int intUsedMinutes = METER_CalculateUsedTime(objDictionary, driver, strGracePeriodViolationTime);
					dblExpectedMaxTimeRemaining = Double.parseDouble(strMaximumDuration) - intRemainingFreeTime -  Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment) - intUsedMinutes;
				}
				else
				{
					dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime -  Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment);
				}
			}
			else
			{
				int intRemainingTimeBeforeNoParking = clsMeter.METER_CalculateRemainingMinutesBeforeNoParking(objDictionary, null,  strNoParkingStartTime);
				if(intRemainingTimeBeforeNoParking < Double.parseDouble(strMeterIncrementTime))
				{
					dblExpectedMaxTimeRemaining = 0.0;
				}
				else
				{
					dblExpectedMaxTimeRemaining = intRemainingTimeBeforeNoParking - Double.parseDouble(strMeterIncrementTime);
				}
				System.out.println("MIH");
			}
		}
		else
		{
			if(!strFamily.equals("")){strMeterIncrementTime = strFamilyBuyMinutes;}
			if(Double.parseDouble(strOriginalMaxRemaining) < Double.parseDouble(strMeterIncrementTime))
			{dblExpectedMaxTimeRemaining = 0.0;}
			else
			{
				//Rate To Free Check if In Free
				if(strOriginalValidTimePurchase.equals("0"))
				{
					if(Double.parseDouble(strMeterIncrementTime) + Double.parseDouble(strFreeTimeMinutes) + Double.parseDouble(strFreeTimeFirstPayment) >= Double.parseDouble(strOriginalMaxRemaining))
					{dblExpectedMaxTimeRemaining = 0.0;}
					else
					{
						//This was test code for True Up disabled and Unlock Enabled
						if(strGracePeriodViolationTime != null && strUnlockValue.equals("On") && strTrueUp.equals("False"))//Unlock Enable and True UP disabled
						{
							int intUsedMinutes = METER_CalculateUsedTime(objDictionary, driver, strGracePeriodViolationTime);
							dblExpectedMaxTimeRemaining = Double.parseDouble(strMaximumDuration) - intRemainingFreeTime -  Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment) - intUsedMinutes;
						}
						else
						{
							dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime -  Double.parseDouble(strMeterIncrementTime) - Double.parseDouble(strFreeTimeFirstPayment);
						}
					}
				}
				else
				{
					if(Double.parseDouble(strMeterIncrementTime) >= Double.parseDouble(strOriginalMaxRemaining))
					{dblExpectedMaxTimeRemaining = 0.0;}
					else
					{
						if(!strFamily.equals("")){strMeterIncrementTime = strFamilyBuyMinutes;}
						dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - Double.parseDouble(strMeterIncrementTime);
					}
				}
			}
		}
		//Coin Payment
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
		if(strForcedMulti.contains("false") && strRemoteUser.equals(""))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testauto_insert_coins_spot_"+strSpotNumber+".py");
			if(!strNumberOfSpots.equals("1"))
			{
				//If Spot 1 is active, and spot 2 is in maintenance mode, the Apply Payment Screen will not appear
				if(!strMaintenanceModeSpot2.equals("True"))
				{
		  			//clsMeter.METER_WaitForMeterScreen(objDictionary,"Local","SCREEN_DUAL_APPLY_PAYMENT", "HomeToApplyPayment"+strHostType+" Space");
					//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_apply_payment.py SPOT_"+strSpotNumber);
				}
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_apply_payment.py SPOT_"+strSpotNumber);
				//clsMeter.METER_WaitForMeterScreen(objDictionary,"Local","SCREEN_DUAL_APPLY_PAYMENT", "HomeToApplyPayment"+strHostType+" Space");
			}
			//Wait For "success" Text to exists before additional payments
			String strCoinWaitForLogMessage = objDictionary.get("strCoinWaitForLogMessage");if(strCoinWaitForLogMessage == null) {strCoinWaitForLogMessage = "True";}
			if(strCoinWaitForLogMessage.equals("True"))
			{
				if(strHostType.equals("Remote"))
		  		{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strLocalHost, "\"result\":\"success\"", 20,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
		  		else
		  		{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strLocalHost, "do_rabbitmq_send_event(): success()", 65,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
			}
	  		//Set Expected Screen
//	  		strExpectedScreen = "SCREEN_MULTI_HOME";
//	  		if(strRemoteUser.equals(""))
//	  		{
//	  			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
//	  		}
//	  		clsMeter.METER_WaitForMeterScreen(objDictionary,"Local",strExpectedScreen, "TransactionDetailsToAvailableTime"+strHostType+" Space");
	  	}
		else
		{
			String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
			String strSpotName = clsMeter.METER_GetMeterSpotName(objDictionary,strSpotNumber);
			if(strCurrentUIState.equals("SCREEN_MULTI_ALIGN"))
			{
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost,"testautof_button_push_align_next.py");
				try {Thread.sleep(3000);}catch (Exception e) {}
				strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
			}
			if(strCurrentUIState.equals("SCREEN_MULTI_SELECT_SPACE"))
  			{
				String strDeviceId = objDictionary.get("strDeviceId");
				String[] arrLocalMeterSpots = strDeviceId.split("-");
				if(strSpotNumber.equals("1")){strSpotName = arrLocalMeterSpots[0];}else{strSpotName = arrLocalMeterSpots[1];}
		  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_enter_space.py "+strSpotName);
		  		objDictionary.put("strLastGlobalSpace",strSpotName);
		  		strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
  			}
			String strExpectedScreen = "SCREEN_MULTI_HOME";//Global
	  		if(strForcedMulti.equals("False"))
	  		{
				//Get Current Screen
				if(strRemoteUser.equals(""))
		  		{
		  			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
		  		}
			}
	  		strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
	  		String strTrueUpTime = objDictionary.get("strTrueUpTime");
	  		if(!strCurrentUIState.equals(strExpectedScreen))
  			{
	  			//Test Code for force multi.  Sometime when True up is enable the screen is up, but it's called SCREEN_MULTI_IDLE
	  			if(!strTrueUpTime.equals("0")||!strCurrentUIState.equals("SCREEN_MULTI_IDLE"))
	  			{
	  				UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to add a payent, Meter screen was not at the (SCREEN_MULTI_IDLE) - Actual Screen ("+strCurrentUIState+") The Unable to add a payment"+strMethodName);
	  			}
	  			else
	  			{
	  				UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to add a payent, Meter screen was not at the (SCREEN_MULTI_HOME) - Actual Screen ("+strCurrentUIState+") The Unable to add a payment"+strMethodName);
	  			}
	  		}
			if (strFamily.equals("Handicap"))
			{
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost,"testauto_handicap_session_spot_1.sh");
		  		try {Thread.sleep(4000);}catch (Exception e) {}
			}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost,"testautof_insert_coins_no_spot.py");
			if(strForcedMulti.equals("false"))
	  		{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strLocalHost, "\"result\":\"success\"",40,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
			else{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strLocalHost, "do_rabbitmq_send_event(): success()", 40,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
		}
		if(Double.parseDouble(strOriginalMaxRemaining) == Double.parseDouble(strMaximumDuration) + intRemainingFreeTime)
		{
			Calendar calendar = Calendar.getInstance();
	  		String strFirstPaymentTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
	  		Reporter.log("strFirstPaymentTime: "+strFirstPaymentTime);
			objDictionary.remove("strFirstPaymentTime");objDictionary.put("strFirstPaymentTime",strFirstPaymentTime);
		}
		//Take Screen Shot
		String strInvocationCounter = objDictionary.get("strInvocationCounter");
		String strTestSuiteName = objDictionary.get("strTestSuiteName");
		String strTestCaseName = objDictionary.get("strTestCaseName");
		String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
		if(strEnableAllImages.equals("True")){clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_AfterCoin"+strInvocationCounter);}
		if(!strFreeTimeFirstPayment.equals("0"))
		{
			//Additional Wait for Free Time First to get added.
			try {Thread.sleep(3000);}catch (Exception e) {}
		}
		//Coin Sync
		int intCounter = 0;
		String CurrentValidTimePurchase = "";
		do
		{
			CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"After");
      		intCounter++;
      		if(intCounter>15)
      		{
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The ("+strHostType+") Meter time did not increment correctly-"+strMethodName);
      		}
		} while (strOriginalValidTimePurchase.equals(CurrentValidTimePurchase));
		String strValidateMeterTime = objDictionary.get("strValidateMeterTime");if(strValidateMeterTime == null) {strValidateMeterTime = "True";}
		HttpConnections clsHttpConnections = new HttpConnections();
		String strParkingState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
		if(strValidateMeterTime.equals("True"))
		{
			//New  //Not Sure How To Handle Free To Rate
			if(strMeterFreeValue.equals("False"))
			{
				double dblExpectedMeterValue;
				if(Double.parseDouble(strOriginalValidTimePurchase) == 0)
				{
					if(strNoParkingStartTime == null)
					{
						dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + Double.parseDouble(strMeterIncrementTime) + Double.parseDouble(strFreeTimeFirstPayment) + intRemainingFreeTime;
						if(Double.parseDouble(strOriginalMaxRemaining) < dblExpectedMeterValue)
						{
							dblExpectedMeterValue = Double.parseDouble(strOriginalMaxRemaining);
						}
					}
					else
					{
						//Changed to Ceil for Test Case M1053_B
						int intRemainingTimeBeforeNoParking = clsMeter.METER_CalculateRemainingMinutesBeforeNoParkingCeil(objDictionary, null,  strNoParkingStartTime);
						if(Double.parseDouble(strMeterIncrementTime) >= Double.valueOf(intRemainingTimeBeforeNoParking))
						{
							int intUsedMinutes = 0;
							//if(strGracePeriodViolationTime != null && strUnlockValue.equals("Off") && strTrueUp.equals("False"))
							if(strGracePeriodViolationTime != null && strUnlockValue.equals("Off"))
							{
								intUsedMinutes = METER_CalculateUsedTime(objDictionary, driver, strGracePeriodViolationTime);
							}
							dblExpectedMeterValue = Double.valueOf(intRemainingTimeBeforeNoParking) + intUsedMinutes;
						}
						else
						{
							dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + Double.parseDouble(strMeterIncrementTime) + Double.parseDouble(strFreeTimeFirstPayment) + intRemainingFreeTime;
						}
					}
					if(Double.parseDouble(strOriginalMaxRemaining) < dblExpectedMeterValue)
					{
						dblExpectedMeterValue = Double.parseDouble(strOriginalMaxRemaining);
					}
				}
				else
				{
					if(!strFamily.equals("")){strMeterIncrementTime = strFamilyBuyMinutes;}
					String strSecondRateStartTime = objDictionary.get("strSecondRateStartTime");
					dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + Double.parseDouble(strMeterIncrementTime);
					if(strSecondRateStartTime != null)
					{
						String strMeterNextRateCost = clsMeter.GetMeterNextRateCost(objDictionary);
						DecimalFormat dFormat = new DecimalFormat("#0.00");
						int intRemainingMinutesInFirstRateSet = clsMeter.METER_CalculateRemainingMinutesBeforeNextRate(objDictionary, null, strSecondRateStartTime)-Integer.parseInt(strFreeTimeFirstPayment);
						Reporter.log("Remaining Minutes First Rateblock: "+intRemainingMinutesInFirstRateSet);

//						if(dblExpectedMeterValue > intRemainingMinutesInFirstRateSet)
//						{
							int intNumberOfPaymentInFirsttRateSet = intRemainingMinutesInFirstRateSet/12;
							Reporter.log("intNumberOfPaymentInFirsttRateSet: "+intNumberOfPaymentInFirsttRateSet);

							int intMinutesFirstBlock = intRemainingMinutesInFirstRateSet - intNumberOfPaymentInFirsttRateSet * 12;
							Reporter.log("intMinutesFirstBlock: "+intMinutesFirstBlock);

							int intMinutesSecondBLock = Integer.parseInt(strMeterIncrementTime) - intMinutesFirstBlock;
							Reporter.log("intMinutesSecondBlock: "+intMinutesSecondBLock);


//							//Price Per Minute 1st Rate Block
//							2.0833333333333332
//							//Price Per Minute 2nd Rate Block
//							2.5
//
//
//
//							2.0833333333333332 * 5 = 10.4166666
//							2.5 * 7 = .125 = 17.5




							//The calculation has to be 11.666
							//Current Purchase time did equal Expected purchase time.
							//Meter Time did not decremented correctly after coin payment-expected (65.0)-actual (64.6666666667)-METER_InsertCoin
							System.out.println("MIH");


//						}



//						int intNumberOfPaymentInFirsttRateSet = intRemainingMinutesInFirstRateSet/12;
//						int intRemainingMinutes = intRemainingMinutesInFirstRateSet - intNumberOfPaymentInFirsttRateSet * 12;
//						Reporter.log("intRemainingMinutes: "+intRemainingMinutes);
//						String strTotalFeeFirstRateSet = "$" + dFormat.format((int)Math.ceil(((double)intRemainingMinutesInFirstRateSet)/ Integer.parseInt(strMeterIncrementTime)) * (Double.parseDouble(strMeterNextRateCost)/100));
//						Reporter.log("The Total Fee for the 1st Rate Block Equaled: "+strTotalFeeFirstRateSet);
//						int intMinutesInSecondRateSet = Integer.parseInt(strMaximumDuration) - intRemainingMinutesInFirstRateSet-Integer.parseInt(strFreeTimeFirstPayment);
//						Reporter.log("Remaining Minutes Second Rateblock: "+intMinutesInSecondRateSet);
//						String strTotalFeeSecondRateSet = "$" + dFormat.format((int)Math.ceil(((double)intMinutesInSecondRateSet)/ 10) * (Double.parseDouble(strMeterNextRateCost)/100));
//						Reporter.log("The Total Fee for the 2nd Rate Block Equaled: "+strTotalFeeSecondRateSet);

					}
					//Minutes Before  No Parking
					if(strNoParkingStartTime != null)
					{
						//Mintues before No parking
						int intRemainingTimeBeforeNoParking = clsMeter.METER_CalculateRemainingMinutesBeforeNoParking(objDictionary, null,  strNoParkingStartTime);
						if(Double.valueOf(intRemainingTimeBeforeNoParking) < dblExpectedMeterValue)
						{
							String strRemainingTimeBeforeNoParkingAtViolation =  objDictionary.get("strRemainingTimeBeforeNoParkingAtViolation");
							dblExpectedMeterValue = Double.valueOf(strRemainingTimeBeforeNoParkingAtViolation)+1;
						}
					}
					else
					{
						if(Double.parseDouble(strMaximumDuration) < dblExpectedMeterValue)
						{
							dblExpectedMeterValue = Double.parseDouble(strMaximumDuration);
						}
					}
				}
	      		if(strParkingState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))//Need a way to validate Presumed Occupied
	      		{
	      			double dblExpectedMeterValueMinusOne = dblExpectedMeterValue - 1;
					double dblExpectedMeterValuePlus2 =  dblExpectedMeterValue + 2;
					if(Double.parseDouble(CurrentValidTimePurchase) >= dblExpectedMeterValueMinusOne && Double.parseDouble(CurrentValidTimePurchase) < dblExpectedMeterValuePlus2)
					{Reporter.log("Meter Time decremented correctly after coin payment-("+CurrentValidTimePurchase+")");}
					else
					{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter time was not between ("+dblExpectedMeterValueMinusOne+") and ("+dblExpectedMeterValuePlus2+") - actual actual ("+CurrentValidTimePurchase+")-"+strMethodName);}
				}
				else
				{
					if(dblExpectedMeterValue == Double.parseDouble(CurrentValidTimePurchase)||dblExpectedMeterValue+ 1 == Double.parseDouble(CurrentValidTimePurchase)||dblExpectedMeterValue- 1 == Double.parseDouble(CurrentValidTimePurchase))
					{Reporter.log("Meter Time decremented correctly after coin payment-("+dblExpectedMeterValue+")");}
					else
					{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"Meter Time did not decremented correctly after coin payment-expected ("+dblExpectedMeterValue+")-actual ("+CurrentValidTimePurchase+")-"+strMethodName);}
				}
			}
			//End New
			if(strParkingState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))//Need a way to validate Presumed Occupied
      		{
				//Testing the code below because sometime the maxt time on first payment created max time less that expected and sometimes it more thatn expected
//				if(strFreeTimeFirstPayment.equals("10"))
//				{
//					//M1014A_FTFP10_PO_CP1_VMT_ES1_VPSH_VICAE_VIAC
//					double dbllessThanTime = dblExpectedMaxTimeRemaining+1;
//					if(dblExpectedMaxTimeRemaining > Double.parseDouble(strMaxRemaining) && Double.parseDouble(strMaxRemaining) > dblExpectedMaxTimeRemaining - 1)
//					{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
//				  	else
//				  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining presumed occupied ("+strMaxRemaining+") - was not greater than ("+dblExpectedMaxTimeRemaining+") and less than ("+dbllessThanTime+")-"+strMethodName);}
//				}
//				else
//				{
//					String strMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,strSpotNumber, strHostType);
//					double dbllessThanTime = dblExpectedMaxTimeRemaining+1;
//					if(Double.parseDouble(strMaxRemaining) > (dblExpectedMaxTimeRemaining) && Double.parseDouble(strMaxRemaining) < dblExpectedMaxTimeRemaining + 1)
//					{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
//				  	else
//				  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining presumed occupied ("+strMaxRemaining+") - was not greater than ("+dblExpectedMaxTimeRemaining+") and less than ("+dbllessThanTime+")-"+strMethodName);}
				//}
			}
      		else
      		{
      			String strMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,strSpotNumber, strHostType);
    			if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining)|| dblExpectedMaxTimeRemaining - 1 == Double.parseDouble(strMaxRemaining))//|| dblExpectedMaxTimeRemaining + 1 == Double.parseDouble(strMaxRemaining)|| dblExpectedMaxTimeRemaining + 2 == Double.parseDouble(strMaxRemaining))
    		  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
    			//else if(strGracePeriodViolationTime != null && strUnlockValue.equals("On"))
    			else if(strGracePeriodViolationTime != null)
    			{
    				//Handle Round When Unlock is enable dand True Up Isn't
    				if(dblExpectedMaxTimeRemaining - 1 == Double.parseDouble(strMaxRemaining) && strTrueUp.equals("False")|| dblExpectedMaxTimeRemaining + 1 == Double.parseDouble(strMaxRemaining))
        		  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
    				else
    				{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName);}
        		}
    			else
    		  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName);}
      		}
		}
		Reporter.log("Method ("+strMethodName+") took: " + timer.stop());
	}
	public void METER_InsertCoin2(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber, String strHostType)
	{
		Stopwatch timer = Stopwatch.createStarted();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		//Associated Syncs
		String strLogMessageSyncDisabled = objDictionary.get("strLogMessageSyncDisabled");if(strLogMessageSyncDisabled == null) {strLogMessageSyncDisabled = "False";}
		String strUISyncDisabled = objDictionary.get("strUISyncDisabled");if(strUISyncDisabled == null) {strUISyncDisabled = "False";}
		//Set sessionMeter
		Session sessionMeter = null;if(strHostType.equals("Local")){sessionMeter = sessionLocalMeter;}else{sessionMeter = sessionRemoteMeter;}
		//Only used to indicate which meter screen should appear
		String strRemoteUser = objDictionary.get("strRemoteUser1"); if(strRemoteUser == null){strRemoteUser = "";}
		//Touch Local Meter
		String strLocalHost = objDictionary.get("strHost");
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,sessionMeter);
		if(strForcedMulti.contains("false") && strRemoteUser.equals(""))
		{
 			//Dual and Single Meter
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strLocalHost,"testauto_insert_coins_spot_"+strSpotNumber+".py");
			String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
			if(!strNumberOfSpots.equals("1"))
			{
				//If Spot 1 is active, and spot 2 is in maintenance mode, the Apply Payment Screen will not appear
				String strMaintenanceModeSpot2 = clsMeter.METER_GetCurrentMaintenanceMode(objDictionary, driver, "2", strHostType);
				if(!strMaintenanceModeSpot2.equals("True"))
				{
		  			clsMeter.METER_WaitForMeterScreen(objDictionary,sessionLocalMeter,"Local","SCREEN_DUAL_APPLY_PAYMENT", "HomeToApplyPayment"+strHostType+" Space");
					clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strLocalHost, "testautof_apply_payment.py SPOT_"+strSpotNumber);
				}
			}
			//Wait For "success" Text to exists before additional payments
			if(strLogMessageSyncDisabled.equals("False"))
			{
				if(strHostType.equals("Remote")){clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "\"result\":\"success\"", 20,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
		  		else{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "do_rabbitmq_send_event(): success()", 40,"Coin Payment "+strHostType+" Space", strSpotNumber, strHostType);}
			}
	  		//Set Expected Screen
	  		String strExpectedScreen = "SCREEN_MULTI_HOME";
	  		if(strRemoteUser.equals("")){if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}}
	  		if(strUISyncDisabled.equals("False")){clsMeter.METER_WaitForMeterScreen(objDictionary,sessionLocalMeter,"Local",strExpectedScreen, "TransactionDetailsToAvailableTime"+strHostType+" Space");}
		  	}
		else
		{
			//Global Pay Meter
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
			if(strLogMessageSyncDisabled.equals("False"))
			{
				if(strHostType.contains("Remote")){clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost,"\"result\":\"success\"",40,"Coin Payment "+strHostType+" Space",strSpotNumber, strHostType);}
				else{clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost,"do_rabbitmq_send_event(): success()",40,"Coin Payment "+strHostType+" Space",strSpotNumber,strHostType);}
			}
		}
		//This takes be 2 - 3 seconds
		//Parking Sesson
		HttpConnections clsHttpConnections = new HttpConnections();
		//clsHttpConnections.HTTPCONNECTIONS_GetParkingSessionId(objDictionary, strHostType, strSpotNumber);
		clsHttpConnections.HTTPCONNECTIONS_GetSpotEstimatedTimeRemaining(objDictionary, strHostType, strSpotNumber);
		Reporter.log("Method ("+strMethodName+") took: " + timer.stop());
	}
	//*********************************************************************************************************************************************************************************************

	//*********************************************************************************************************************************************************************************************
	//Insert Coin Batch
	//*********************************************************************************************************************************************************************************************
	public void METER_InsertCoinsBatch(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber,String strHostType, String strAction)
	{
		Meter clsMeter = new Meter();
		Session sessionMeter = null;
		if(strHostType.equals("Local")) {sessionMeter = sessionLocalMeter;}else {sessionMeter = sessionRemoteMeter;}
		String strCurrentMaxRemaining = objDictionary.get("strMaxRemaining");//Where is this from?
		String strNbrOfCoins = "";
		if(strHostType.equals("Local")){strNbrOfCoins = strAction.replace("CPBLS", "");}else{strNbrOfCoins = strAction.replace("CPBRS", "");}
		System.out.println(strCurrentMaxRemaining);
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");if(strFreeTimeFirstPayment == null) {strFreeTimeFirstPayment = "0";}
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		double dblNbrCoinRequiredForMaxTime = ((Double.parseDouble(strCurrentMaxRemaining.replace(".0", "")) - Double.parseDouble(strFreeTimeFirstPayment))/ Double.parseDouble(strMeterIncrementTime.replace(".0", "")));
		double dblCreditCardPaymentAmount = Math.ceil(dblNbrCoinRequiredForMaxTime);
		int intNbrCoinRequiredForMaxTime = (int)dblCreditCardPaymentAmount;
		int intNbrOfCoins = Integer.parseInt(strNbrOfCoins);
		if(intNbrOfCoins > intNbrCoinRequiredForMaxTime){intNbrOfCoins = intNbrCoinRequiredForMaxTime;}
		Reporter.log("Number of coins in batch is ("+intNbrOfCoins+")");
		double dblNbrCoinsBeforeAcceptorDisabled = intNbrCoinRequiredForMaxTime * .75;
		Reporter.log("Number of coins before pause ("+dblNbrCoinsBeforeAcceptorDisabled+")");
		if(intNbrOfCoins > dblNbrCoinsBeforeAcceptorDisabled)
		{
			int intNbrCoinsBeforeAcceptorDisabled = (int) Math.ceil(dblNbrCoinsBeforeAcceptorDisabled) + 1;
			int intNbrRemainingCoins = intNbrOfCoins - intNbrCoinsBeforeAcceptorDisabled +1;
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r "+intNbrCoinsBeforeAcceptorDisabled+" -w 1");
			clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 30, strSpotNumber, strHostType);
			int intCounter = 0;
			do
			{
				try {Thread.sleep(2000);}catch (Exception e) {}
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r 1 -w 1");
				clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 30, strSpotNumber, strHostType);
				intCounter++;
			}while (intNbrRemainingCoins != intCounter);
			int intExpectedMaxRemaining = Integer.parseInt(strCurrentMaxRemaining) - Integer.parseInt(strMeterIncrementTime) * intNbrOfCoins;
			clsMeter.GlobalWait(objDictionary, null, sessionMeter,"{WaitUntilMeterMaxRemainingEqualsTheAmount} "+intExpectedMaxRemaining, 30, strSpotNumber,strHostType);
		}
		else
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r "+intNbrOfCoins+" -w 1");
			int intExpectedMaxRemaining = Integer.parseInt(strCurrentMaxRemaining.replace(".0","")) - Integer.parseInt(strMeterIncrementTime) * intNbrOfCoins;
			clsMeter.GlobalWait(objDictionary, null, sessionMeter,"{WaitUntilMeterMaxRemainingEqualsTheAmount} "+intExpectedMaxRemaining, 65, strSpotNumber,strHostType);
		}
	}
	public void METER_InsertCoinsNickelBatch(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber,String strHostType, String strAction)
	{
		//Add Batch Nickels of Nickels
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Session sessionMeter = null;
		if(strHostType.equals("Local")) {sessionMeter = sessionLocalMeter;}else {sessionMeter = sessionRemoteMeter;}
		String strCurrentMaxRemaining = objDictionary.get("strMaxRemaining");System.out.println(strCurrentMaxRemaining);
		String strNbrOfCoins = "";
		if(strHostType.equals("Local")){strNbrOfCoins = strAction.replace("CPBNLS", "");}else{strNbrOfCoins = strAction.replace("CPNBRS", "");}
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");if(strFreeTimeFirstPayment == null) {strFreeTimeFirstPayment = "0";}
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		double dblNbrCoinRequiredForMaxTime = ((Double.parseDouble(strCurrentMaxRemaining.replace(".0", "")) - Double.parseDouble(strFreeTimeFirstPayment))/ (Double.parseDouble(strMeterIncrementTime.replace(".0", "")) * .4));
		dblNbrCoinRequiredForMaxTime = Math.ceil(dblNbrCoinRequiredForMaxTime);
		int intNbrCoinRequiredForMaxTime = (int)dblNbrCoinRequiredForMaxTime;
		int intNbrOfCoins = Integer.parseInt(strNbrOfCoins);
		if(intNbrOfCoins > intNbrCoinRequiredForMaxTime){intNbrOfCoins = intNbrCoinRequiredForMaxTime;}
		Reporter.log("Number of coins in batch is ("+intNbrOfCoins+")");
		double dblNbrCoinsBeforeAcceptorDisabled = intNbrCoinRequiredForMaxTime * .75;
		if(intNbrOfCoins > dblNbrCoinsBeforeAcceptorDisabled)
		{
			int intNbrCoinsBeforeAcceptorDisabled = (int) Math.ceil(dblNbrCoinsBeforeAcceptorDisabled) + 1;
			int intNbrRemainingCoins = intNbrOfCoins - intNbrCoinsBeforeAcceptorDisabled +1;
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -c10 -r "+intNbrCoinsBeforeAcceptorDisabled+" -w 1");
			clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 45, strSpotNumber, strHostType);
			int intCounter = 0;
			do
			{
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -c10 -r 1 -w 3");
				clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 40, strSpotNumber, strHostType);
				intCounter++;
			}while (intNbrRemainingCoins != intCounter);
			int intExpectedMaxRemaining = Integer.parseInt(strCurrentMaxRemaining) - Integer.parseInt(strMeterIncrementTime) * intNbrOfCoins;
			//clsMeter.GlobalWait(objDictionary, null, sessionMeter,"{WaitUntilMeterMaxRemainingEqualsTheAmount} "+intExpectedMaxRemaining, 30, strSpotNumber,strHostType);
		}
		else
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -c10 -r "+intNbrOfCoins+" -w 2");
			double dblExpectedMaxTimeRemaining = Double.parseDouble(strCurrentMaxRemaining) - (intNbrOfCoins *  Double.parseDouble(strMeterIncrementTime.replace(".0", "")) * .4);
			try {Thread.sleep(5000);}catch (Exception e) {}
			String strMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,sessionMeter,strSpotNumber, strHostType);
			if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining))
			{Reporter.log("The meter max time remaining decremented correctly from ("+strCurrentMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
		  	else
		  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName);}
		}
	}

	public void METER_PS1_CP1_VMT_ES1_VPSH(Map<String, String> objDictionary, WebDriver driver, String strCreditCardIncrementTime, String strFreeTimeFirstPayment)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
  		String strHost = objDictionary.get("strHost");
  		String strSpotNumber = objDictionary.get("strSpotNumber");
  		//Function Variables
  		String strParkingShortSessionSec = "15";
  		//PS1: Park Spot 1
	  	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
	  	//CP1: Coin Payment Spot 1
  		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
  		//Store Parking Id
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	  	//VAMTEEMT: Validate Actual Meter Time Equals Expected Meter Time
	  	clsMeter.METER_WaitForMeterArrowsToDisappear(objDictionary,driver);
	  	try {Thread.sleep(2000);}catch (Exception e) {}
	  	int intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) + Integer.parseInt(strFreeTimeFirstPayment);
	  	String strMeterValidTimeRemaining = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver,"1");
	  	int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterValidTimeRemaining) / 60.00);
	  	int intActualRemainingTimeMinutesPlus1 = intActualRemainingTimeMinutes + 1;
	  	if(intActualRemainingTimeMinutes == intExpectedRemainingTimeMinutes||intActualRemainingTimeMinutesPlus1 == intExpectedRemainingTimeMinutes)
	  	{Reporter.log("The Actual Meter Time Equalled the Expected Meter Time ("+intActualRemainingTimeMinutes+")");}
	  	else
	  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The Actual Meter Time ("+intActualRemainingTimeMinutes+") did not equal expected meter payment ("+intExpectedRemainingTimeMinutes+")");}
	  	//RPSS: Remain Parked Short Session
      	clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
   		//ES1: Exit Spot 1
	  	clsMeter.METER_ExitSpot(objDictionary, driver, "1","Local");
	  	//NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser,strRemotePath,objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary,driver, "Sentry meter","1");
		//VPSH: Validate Parking Session History
		if (strFreeTimeFirstPayment.equals("0"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			String strPaymentRow2 = clsCommonWeb.StoreTableValue(objDictionary,  getDriver(), "Parking Session", "Parking Session History", 1, "strPaymentRow2", "2", "3");
	  		if(strPaymentRow2.equals("Virt Payment #1"))
	  		{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		}
	  		else
	  		{
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			}
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		}
		driver.quit();
	}

	public void METER_InsertCoinWithMeterLogValidation(Map<String, String> objDictionary, WebDriver driver, String strTestCaseName, String strValues)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
  		String strSpotNumber = objDictionary.get("strSpotNumber");
	 	//START InsertCoinApplyPayment Log Trace
		clsMeter.METER_StartLogTrace2(objDictionary,driver,"InsertCoinApplyPayment");
		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","Before");
		//CP1: Coin Payment Spot 1
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_insert_coins_spot_"+strSpotNumber+".py");
	    clsMeter.METER_WaitForMeterArrowsToDisappear(objDictionary,driver);
	    String CurrentValidTimePurchase = "0";
	    int intCounter = 0;
	  	do
	  	{
	  		CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","After");
	  		intCounter++;
			if(intCounter>10)
			{
				UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter Time did not increment correctly-"+strMethodName);
			}
		}
	  	while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase) && intCounter < 20);
	  	//VALIDATE METER LOGS
	  	try {clsMeter.METER_CopyLogTraceLocally2(objDictionary, driver, strTestCaseName+"_InsertCoin","InsertCoinApplyPayment");}catch (Exception e) {}
		//clsMeter.METER_ScanMeterLogForValue(objDictionary, driver ,strTestCaseName+"_InsertCoin", strValues);
	}
//	public void METER_InsertCoinApplyPayment(Map<String, String> objDictionary, WebDriver driver,String strTestCaseName, String strRateBlockType)
//	{
//		Meter clsMeter = new Meter();
//		String strFirstPaymentTime = objDictionary.get("strFirstPaymentTime");
//		String strNumberOfMeterSpots = objDictionary.get("strNumberOfMeterSpots");
//  		if (strNumberOfMeterSpots == null)
//  		{
//  			clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,driver,"Local");
//  			strNumberOfMeterSpots = objDictionary.get("strNumberOfMeterSpots");
//  		}
//  		String strMeterLogValues = "";
//  		switch (strRateBlockType)
//  		{
//  			case "Free":
//  				if(strNumberOfMeterSpots.equals("2"))
// 		    		{strMeterLogValues = "SCREEN_DUAL_NON_EMPTY_HOME|SCREEN_DUAL_APPLY_PAYMENT|Screen changed to SCREEN_DUAL_APPLY_PAYMENT";}
//  		    		else
//  		    		{strMeterLogValues = "UI_STATE_PAYING_COIN";}
//  				break;
//  	      	case "Rate":
//  	      		if(strNumberOfMeterSpots.equals("2"))
//	  		    	{strMeterLogValues = "SCREEN_DUAL_NON_EMPTY_HOME|SCREEN_DUAL_APPLY_PAYMENT|Screen changed to SCREEN_DUAL_APPLY_PAYMENT";}
//  				else
//	  		    	{strMeterLogValues = "UI_STATE_PAYING_COIN";}
//  				break;
//  	      	case "Maintenance Mode":
//  	      		strMeterLogValues = "UI_STATE_PAYING_COIN";
//  	      		break;
//  		}
//	 	//START InsertCoinApplyPayment Log Trace
//		clsMeter.METER_StartLogTrace2(objDictionary,driver,"InsertCoinApplyPayment");
//		//CP1: Coin Payment Spot 1
//		String strHost = objDictionary.get("strHost");
//  		String strSpotNumber = objDictionary.get("strSpotNumber");
//		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","Before");
//		//CP1: Coin Payment Spot 1
//		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_insert_coins_spot_"+strSpotNumber+".py");
//		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_apply_payment.py SPOT_1");
//	    clsMeter.METER_WaitForMeterArrowsToDisappear(objDictionary,driver);
//	    String CurrentValidTimePurchase = "0";
//	    int intCounter = 0;
//	  	do
//	  	{
//	  		CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","After");
//	  		intCounter++;
//	  	}
//	  	while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase) && intCounter < 30);
//	  	if(strFirstPaymentTime == null)
//	  	{
//	  		Calendar calendar = Calendar.getInstance();
//	  		strFirstPaymentTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
//	  		Reporter.log("strFirstPaymentTime: "+strFirstPaymentTime);
//			objDictionary.remove("strFirstPaymentTime");objDictionary.put("strFirstPaymentTime",strFirstPaymentTime);
//	  	}
//	  	//VALIDATE METER LOGS
//	  	try {clsMeter.METER_CopyLogTraceLocally2(objDictionary,driver, strTestCaseName+"_InsertCoinApplyPayment","InsertCoinApplyPayment");}catch (Exception e) {}
//  		//clsMeter.METER_ScanMeterLogForValue(objDictionary, strTestCaseName+"_InsertCoinApplyPayment", strMeterLogValues);
//	}
	public void METER_InsertCoinNoSpot(Map<String, String> objDictionary, String strTestCaseName, String strExpectedSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,null,strExpectedSpotNumber,"Local","Before");
		//CP1: Coin Payment Spot 1
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_insert_coins_no_spot.py");
		clsMeter.METER_WaitForMeterArrowsToDisappear(objDictionary,null);
	    String CurrentValidTimePurchase = "0";
	    objDictionary.put("strSpotNumber", strExpectedSpotNumber);
	    int intCounter = 0;
	  	do
	  	{
	  		CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,null,strExpectedSpotNumber,"Local","After");
	  		intCounter++;
			if(intCounter>10){UpdateErrorMessageWithPivotalData(objDictionary,null,"The Meter Time did not increment correctly-"+strMethodName);}
		}
	  	while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase) && intCounter < 20);
	}
	public int METER_InsertCoinMaxTime(Map<String, String> objDictionary, WebDriver driver, String strMaximumDuration, String strCreditCardIncrementTime, int intExistingPurchasedMins, String strSpotNumber)
	{
		Meter clsMeter = new Meter();
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		int intAddCounter = 0;
	  	Double dblCoinIncrementValue = (Double.parseDouble(strMaximumDuration)-intExistingPurchasedMins)/Double.parseDouble(strCreditCardIncrementTime);
	  	int intNbrCoinRequiredForMaxTime = (int)Math.ceil(dblCoinIncrementValue);
	  	//Get Extra Logging Value
	    //Need when Extra Logging is Enabled
	  	Calendar calendar = Calendar.getInstance();
      	String strUsedTimeStart = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		while (intAddCounter < intNbrCoinRequiredForMaxTime)
		{
			clsMeter.METER_InsertCoin(objDictionary, driver, strSpotNumber, "Local");
		  	if(intAddCounter == 0 && !strFreeTimeMinutes.equals("0"))
			{
				String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
			  	int strRemainingFreeTimeFirstPaymentMinutes = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
			  	Reporter.log("strRemainingFreeTimeFirstPaymentMinutes ("+strRemainingFreeTimeFirstPaymentMinutes+")");
			  	objDictionary.put("strRemainingFreeTimeFirstPaymentMinutes",Integer.toString(strRemainingFreeTimeFirstPaymentMinutes));
		    }
	      	intAddCounter++;
	    }
		int intTimeUsed = clsMeter.METER_CalculateUsedTime(objDictionary, null,  strUsedTimeStart);
      	return Integer.parseInt(strMaximumDuration) - intTimeUsed;
	}
	public void METER_InsertCoinMaxTime2(Map<String, String> objDictionary, WebDriver driver, int intNbrCoinRequiredForMaxTime, String strSpotNumber)
	{
		Meter clsMeter = new Meter();
  		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		int intAddCounter = 0;
	  	while (intAddCounter < intNbrCoinRequiredForMaxTime)
		{
			//CP1: Coin Payment Spot 1
	  		clsMeter.METER_InsertCoin(objDictionary, driver, strSpotNumber, "Local");
	  		if(intAddCounter == 0 && !strFreeTimeMinutes.equals("0"))
			{
				String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
			  	int strRemainingFreeTimeFirstPaymentMinutes = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
			  	Reporter.log("strRemainingFreeTimeFirstPaymentMinutes ("+strRemainingFreeTimeFirstPaymentMinutes+")");
			  	objDictionary.put("strRemainingFreeTimeFirstPaymentMinutes",Integer.toString(strRemainingFreeTimeFirstPaymentMinutes));
		    }
	      	String strLocalHost = objDictionary.get("strHost");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
	      	intAddCounter++;
	    }
	}


	//*******************************************************************************************************************************************************************************************
	//Insert Coins Purchase Remaining Time
	//*******************************************************************************************************************************************************************************************
	public void METER_InsertCoinsPurchaseRemainingTime(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber,String strHostType)
	{
		Meter clsMeter = new Meter();
		Session sessionMeter = null;
		if(strHostType.equals("Local")) {sessionMeter = sessionLocalMeter;}else {sessionMeter = sessionRemoteMeter;}
		String strCurrentMaxRemaining = objDictionary.get("strMaxRemaining").replace(".0", "");
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");if(strFreeTimeFirstPayment == null) {strFreeTimeFirstPayment = "0";}
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strMaximumDuration= objDictionary.get("strMaximumDuration");
		int intRemainingMinutes = 0;
		if(Integer.parseInt(strCurrentMaxRemaining) >  Integer.parseInt(strMaximumDuration))
		{
			int intRemainingFree = Integer.parseInt(strCurrentMaxRemaining) - Integer.parseInt(strMaximumDuration);
			intRemainingMinutes = Integer.parseInt(strCurrentMaxRemaining) - intRemainingFree;
		}
		else if(strMaximumDuration.equals(strCurrentMaxRemaining))
		{intRemainingMinutes = Integer.parseInt(strMaximumDuration);}
		else
		{intRemainingMinutes = Integer.parseInt(strCurrentMaxRemaining);}
		double dblNbrCoinRequiredForMaxTime = ((Double.parseDouble(strCurrentMaxRemaining.replace(".0", "")) - Double.parseDouble(strFreeTimeFirstPayment))/ Double.parseDouble(strMeterIncrementTime.replace(".0", "")));

		//double dblPercentRemainingTime = intRemainingMinutes/Integer.parseInt(strMaximumDuration);


//		double dblNbrCoinRequiredForMaxTime = ((Double.parseDouble(strCurrentMaxRemaining.replace(".0", "")) - Double.parseDouble(strFreeTimeFirstPayment))/ Double.parseDouble(strMeterIncrementTime.replace(".0", "")));
//		double dblCreditCardPaymentAmount = Math.ceil(dblNbrCoinRequiredForMaxTime);
//		int intNbrCoinRequiredForMaxTime = (int)dblCreditCardPaymentAmount;
//		Reporter.log("Number of coins before Max Time ("+intNbrCoinRequiredForMaxTime+")");
//		double dblNbrCoinsBeforeAcceptorDisabled = intNbrCoinRequiredForMaxTime * .85;
//		int intNbrCoinsBeforeAcceptorDisabled = 0;
//		if(strMaximumDuration.equals(strCurrentMaxRemaining))
//		{
//			intNbrCoinsBeforeAcceptorDisabled = (int) Math.ceil(dblNbrCoinsBeforeAcceptorDisabled) + 1;
//		}
//		else
//		{
//			intNbrCoinsBeforeAcceptorDisabled = (int) Math.ceil(dblNbrCoinsBeforeAcceptorDisabled);//Payment Exists
//		}
		//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r "+intNbrCoinsBeforeAcceptorDisabled+" -w 1");

		int intCounter = 0;
		do
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
			intRemainingMinutes = intRemainingMinutes - Integer.parseInt(strMeterIncrementTime);
			double dblPercentRemainingTime = intRemainingMinutes/Double.parseDouble(strMaximumDuration);
			Reporter.log("Percentage used to determin if Coin Pause should exist in the logs:"+dblPercentRemainingTime);
			if(dblPercentRemainingTime < 0.24 && intCounter != 0)//Changed from .25 to .24 based on FTR CPRTRS_LMSD
			{
				clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 15, strSpotNumber, strHostType);
			}
			try {Thread.sleep(3000);}catch (Exception e) {}
			intCounter++;
		}while (intCounter < dblNbrCoinRequiredForMaxTime);


//		int intCounter = 0;
//		do
//		{
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
//			if(intNbrRemainingCoins != intCounter)
//			{
//				clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 15, strSpotNumber, strHostType); //-DARIN
//			}
//			intCounter++;
//		}while (intNbrRemainingCoins != intCounter);

//		intCounter = 0;
//		do
//		{
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
//			try {Thread.sleep(1500);}catch (Exception e) {}
//			intCounter++;
//		}while (intCounter < 1);
//		clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 45, strSpotNumber, strHostType);
//
//		intCounter = 0;
//		do
//		{
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
//			try {Thread.sleep(1500);}catch (Exception e) {}
//			intCounter++;
//		}while (intCounter < 1);
//		clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 45, strSpotNumber, strHostType);
//
//		intCounter = 0;
//		do
//		{
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_insert_coins_no_spot.py");
//			try {Thread.sleep(1500);}catch (Exception e) {}
//			intCounter++;
//		}while (intCounter < 1);
//		clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 45, strSpotNumber, strHostType);



//		UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"Fail To check the logs for coin pause","False");
//
//
//		//test
//		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r 9 -w 3");
//		clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 45, strSpotNumber, strHostType);
//		clsMeter.StoreDumpStackValuesInDictionary(objDictionary, null,sessionMeter,strSpotNumber, strHostType, "AfterExit");
//
//
//		//Add Until Next Coin Pause
//		int intNbrRemainingCoins = intNbrCoinRequiredForMaxTime - intNbrCoinsBeforeAcceptorDisabled;
//		dblNbrCoinsBeforeAcceptorDisabled = intNbrRemainingCoins * .75;
//		intNbrCoinsBeforeAcceptorDisabled = (int) Math.ceil(dblNbrCoinsBeforeAcceptorDisabled);
//		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r "+intNbrCoinsBeforeAcceptorDisabled+" -w 1");
//		clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 15, strSpotNumber, strHostType);
//		intNbrRemainingCoins = intNbrRemainingCoins - intNbrCoinsBeforeAcceptorDisabled;
//		if(intNbrRemainingCoins != 0)
//		{
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r "+intNbrRemainingCoins+" -w 1");
//		}

//		int intCounter = 0;
//		do
//		{
//			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_insert_coins_all.py -r 1 -w 1");
//			if(intNbrRemainingCoins != intCounter)
//			{
//				clsMeter.METER_CoinPauseWait(objDictionary,sessionLocalMeter, 15, strSpotNumber, strHostType); //-DARIN
//			}
//			intCounter++;
//		}while (intNbrRemainingCoins != intCounter);
		HttpConnections clsHttpConnections = new HttpConnections();
		String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
		if(!strParkingSpotState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))
		{
			clsMeter.GlobalWait(objDictionary, null, sessionMeter,"{WaitUntilMeterMaxRemainingEqualsZero} NA", 30, strSpotNumber,strHostType);
		}
	}

	public void METER_CoinPauseWait(Map<String, String> objDictionary,Session sessionMeter, int intWaitSeconds, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		String strPassword = objDictionary.get("strUniquePassword");
		String strLocalHost = objDictionary.get("strHost");
		String strLastResumeCoin = objDictionary.get("strLastResumeCoin"); if (strLastResumeCoin == null) {strLastResumeCoin = "NA";}
		//Get StrMeterUser
		String strMeterUser = "";
		String strCommandString = "";
		String strFlag = "False";
		//Calculate End Time
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intWaitSeconds);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
		if(strMeterUser.equals("root"))
		{strCommandString = "sshpass -p "+strPassword+" ssh root@"+strLocalHost+" '/usr/bin/tail -f /var/log/Xsession.log'; echo $?";}
		else
		{strCommandString = "sshpass -p "+strPassword+" ssh seco@"+strLocalHost+" '/bin/journalctl -t sentry.service -f -o cat'; echo $?";}
		//Set Command
		String[] command1 = {"sh","-c",strCommandString};
		try
		{
			Process proc = Runtime.getRuntime().exec(command1);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
	        //Set Scan Start Time
	        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String strScanStartTime = dateFormatGmt.format(new Date());
			Reporter.log("Scan Start At: "+strScanStartTime);
    		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
    		while (strFlag.equals("False"))
			{
	        	//Store Parking Id
				String strStackTraceTime = dateFormatGmt.format(new Date());
				long d1=formater.parse(strScanStartTime).getTime();
		    	long d2=formater.parse(strStackTraceTime).getTime();
		    	s = stdInput.readLine();
				System.out.println(s);
				if(s != null)
				{
				 	while (strFlag.equals("False"))
					{
						s = stdInput.readLine();
						System.out.println(s);
						if(s.contains("resume_coins"))// && !s.contains(strLastResumeCoin))
						{
							objDictionary.put("strLastResumeCoin", s.substring(s.indexOf(" "), s.indexOf("]")).trim());
							if(s.indexOf("]") == 19){Reporter.log("<font color='orange'>The text (resume_coins) appeared as expected in the meter logs-"+s.substring(s.indexOf(" "), s.indexOf("]")).trim()+"</font>");}
							return;
						}
						currentTime = new Timestamp(System.currentTimeMillis());
						if (currentTime.getTime() > endTime.getTime())
						{
							UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"None of the expected objects (resume_coins) existed after (" + intWaitSeconds + ") seconds","False");
						}
					}
				}
				currentTime = new Timestamp(System.currentTimeMillis());
				if (currentTime.getTime() > endTime.getTime())
				{
					UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"None of the expected objects (pause_coins) existed after (" + intWaitSeconds + ") seconds","False");
				}
			}

		}catch (Exception e)
		{
			UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,e+"-"+strMethodName,"False");
		}
	}



	public void METER_InsertCoinsPurchaseRemainingTime(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		Meter clsMeter = new Meter();
		String strCurrentMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,strSpotNumber,strHostType);
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");if(strFreeTimeFirstPayment == null) {strFreeTimeFirstPayment = "0";}
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		double dblNbrCoinRequiredForMaxTime = ((Double.parseDouble(strCurrentMaxRemaining.replace(".0", "")) - Double.parseDouble(strFreeTimeMinutes) - Double.parseDouble(strFreeTimeFirstPayment))/ Double.parseDouble(strMeterIncrementTime.replace(".0", "")));
		double dblCreditCardPaymentAmount = Math.ceil(dblNbrCoinRequiredForMaxTime);
		int intNbrCoinRequiredForMaxTime = (int)dblCreditCardPaymentAmount;
		int intAddCounter = 0;
	  	while (intAddCounter < intNbrCoinRequiredForMaxTime)
		{
	  		if(intNbrCoinRequiredForMaxTime -1 == intAddCounter)
	  		{
	  			//Time Remaining Before Last Payment
	  			String strTimeRemainingBeforeLastPayment = clsMeter.METER_GetMeterMaxRemaining(objDictionary,strSpotNumber, strHostType);
	  			objDictionary.put("strTimeRemainingBeforeLastPayment",strTimeRemainingBeforeLastPayment.replace(".0", ""));
	  		}
			//CP1: Coin Payment Spot 1
	  		clsMeter.METER_InsertCoin(objDictionary, driver, strSpotNumber, strHostType);
	  		if(intAddCounter == 0)
			{
	  			//Store Parking Id
				HttpConnections clsHttpConnections = new HttpConnections();
				clsHttpConnections.HTTPCONNECTIONS_StoreParkingSessionId(objDictionary, strHostType, strSpotNumber);
				if(!strFreeTimeMinutes.equals("0"))
				{
					String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
				  	int strRemainingFreeTimeFirstPaymentMinutes = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
				  	Reporter.log("strRemainingFreeTimeFirstPaymentMinutes ("+strRemainingFreeTimeFirstPaymentMinutes+")");
				  	objDictionary.put("strRemainingFreeTimeFirstPaymentMinutes",Integer.toString(strRemainingFreeTimeFirstPaymentMinutes));
				}
			}
	  		intAddCounter++;
	    }
	}


	//*******************************************************************************************************************************************************************************************
	//Insert Card Pay No Spot
	//*******************************************************************************************************************************************************************************************
	public void METER_InsertCardPayNoSpot(Map<String,String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber,String strHostType)
	{
		Stopwatch timer = Stopwatch.createStarted();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Reporter.log("METER_InsertCardPayNoSpot: "+strHostType);
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");if(strFreeTimeFirstPayment == null) {strFreeTimeFirstPayment = "0";}
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		//String strHostType
		Session sessionMeter = null;if(strHostType.equals("Local")){sessionMeter = sessionLocalMeter;}else{sessionMeter = sessionRemoteMeter;}
		clsMeter.StoreDumpStackValuesInDictionary(objDictionary,driver,sessionMeter,strSpotNumber,strHostType,"Before");
		String OriginalValidTimePurchase = objDictionary.get("strValidTimePurchased").replace(".0", "");
		String strOriginalMaxRemaining = objDictionary.get("strMaxRemaining");
		String strMeterFreeValue = objDictionary.get("strMeterFreeValue");
		String strValidTimeRemainingSec = objDictionary.get("strValidTimeRemainingSec");
		int intValidTimeRemaining = (int) Math.round(Double.parseDouble(strValidTimeRemainingSec) / 60.00);
		int intRemainingFreeTime = 0;
		String strLocalHost = objDictionary.get("strHost");
		String strDebugMask = objDictionary.get("strDebugMask");if(strDebugMask == null) {strDebugMask = "0";}
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary, driver,"Local");
		int intLogWait = 30;if(Integer.parseInt(strDebugMask) >= 5){intLogWait = 420;}
		//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_touch_screen.py");
		if(strMeterFreeValue.equals("True"))
		{
			String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
			intRemainingFreeTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
		}
		//PRT: Purchase Remaining Time
  		Double dblExpectedMaxTimeRemaining = 0.0;
  		double dblCreditCardPaymentTime = 0.0;
  		String strFamily = objDictionary.get("strFamily");if(strFamily == null) {strFamily = "Normal";}
  		if(strFamily == "Normal"){dblCreditCardPaymentTime = Double.parseDouble(strMeterIncrementTime) * 4;}
  		else
  		{
  			String strFamilyBuyMinutes = objDictionary.get("strFamilyBuyMinutes");
  			dblCreditCardPaymentTime = Double.parseDouble(strFamilyBuyMinutes) * 4;
  		}
  		if(Double.parseDouble(strOriginalMaxRemaining) <= dblCreditCardPaymentTime)
  		{dblExpectedMaxTimeRemaining = 0.0;}
  		else
  		{
  			if(OriginalValidTimePurchase.equals("0"))
			{
  				if(intRemainingFreeTime + Double.parseDouble(strFreeTimeFirstPayment) + Double.parseDouble(strMeterIncrementTime) * 4  >= Double.parseDouble(strOriginalMaxRemaining))
				{dblExpectedMaxTimeRemaining = 0.0;}
				else
				{
					dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime - Double.parseDouble(strFreeTimeFirstPayment) - Double.parseDouble(strMeterIncrementTime) * 4;
				}
			}
  			else
  			{
  				if(Double.parseDouble(strMeterIncrementTime) * 4 >= Double.parseDouble(strOriginalMaxRemaining))
				{dblExpectedMaxTimeRemaining = 0.0;}
				else
				{
					HttpConnections clsHttpConnections = new HttpConnections();
					String strParkingSpotState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
					if(strParkingSpotState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))
					{dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining)  - intValidTimeRemaining - Double.parseDouble(strMeterIncrementTime) * 4;}
	  				else
	  				{dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - Double.parseDouble(strMeterIncrementTime) * 4;}
				}
  			}
  		}
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_touch_screen.py");
		//Touch Screen
  		String strExpectedScreen = "SCREEN_MULTI_HOME";//Global
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		if(strForcedMulti.equals("false"))
		{
  			String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, sessionLocalMeter);
  	  		if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
  		}
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,driver,sessionLocalMeter,"1");//Local Spot 1 use to get local UI State
  		if(strCurrentUIState.equals("SCREEN_MULTI_SELECT_SPACE"))
		{
			String strDeviceId = objDictionary.get("strDeviceId");
			String[] arrLocalMeterSpots = strDeviceId.split("-");
			String strSpotName = "";if(strSpotNumber.equals("1")){strSpotName = arrLocalMeterSpots[0];}else{strSpotName = arrLocalMeterSpots[1];}
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_enter_space.py "+strSpotName);
			strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
		}
  		if(!strCurrentUIState.equals(strExpectedScreen)){UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"Unable to add a payent, Meter screen was not at the (SCREEN_MULTI_HOME) - The Unable to add a payment"+strMethodName,"False");}
  		if(strForcedMulti.equals("false"))
 		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strHostType,"testauto_card_pay_spot_"+strSpotNumber+".py");}
		else
		{
			if (strFamily.equals("Handicap"))
			{
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost,"testauto_handicap_session_spot_1.sh");
		  		try {Thread.sleep(4000);}catch (Exception e) {}
			}
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testauto_card_pay_no_spot.sh");
		}
 		//Need a flag to control this - If this isn't enabled the remote meter Max Remaining is calculated incorrectly
 		//clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "on_message_pass", intLogWait,"Card Response "+strHostType+" Space", strSpotNumber, strHostType);
 		//Timer
  		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		String strScanStartTime = dateFormatGmt.format(new Date());
  		int intCounter = 0;
		//Set Expected Screen
  		strExpectedScreen = "SCREEN_MULTI_HOME";//Global
  		if(strForcedMulti.equals("false"))
  		{
  			String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
  	  		if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else {strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
  		}//Dual
  		//Flag to to enable and disable this sync
  		String CurrentUIState = "";
		do
		{
			CurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,driver,sessionLocalMeter,"1");//Local Spot 1
			intCounter++;
      		if(intCounter>40)//IDLE_TIMEOUT_SECONDS_MULTI
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local", "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"The expected meter screen ("+strExpectedScreen+") did not appear current screen equaled ("+CurrentUIState+")-"+strMethodName,"False");
      		}
		} while (!CurrentUIState.equals(strExpectedScreen));
		//Timer Enable Disable
		try
		{
			String strScanEndTime = dateFormatGmt.format(new Date());
			long d1=formater.parse(strScanStartTime).getTime();
	    	long d2=formater.parse(strScanEndTime).getTime();
	    	Database clsDatabase = new Database();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,"TransactionDetailsToAvailableTime",(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
	    	Reporter.log("TransactionDetailsToAvailableTime: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
		}catch (Exception e) {}
		//Validate Purchase Time Changed
		intCounter = 0;
		String CurrentValidTimePurchase = "";
		do
      	{
			CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,sessionMeter,strSpotNumber,strHostType,"After");
			Reporter.log("ValidTimePurchaseAfterCreditCardSwipe: "+CurrentValidTimePurchase);
			intCounter++;
      		if(intCounter>30)
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionMeter,strHostType, "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"The ("+strHostType+") Meter Time did not increment correctly-"+strMethodName,"False");
      		}
      	} while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase));

		//Take Screen Shot
		String strInvocationCounter = objDictionary.get("strInvocationCounter");
		String strTestSuiteName = objDictionary.get("strTestSuiteName");
		String strTestCaseName = objDictionary.get("strTestCaseName");
		String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
		if(strEnableAllImages.equals("True")){clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_AfterCard"+strInvocationCounter);}
		//Validate MaxRemainingTime (Function)
	  	String strValidateMeterTime = objDictionary.get("strValidateMeterTime");if(strValidateMeterTime == null) {strValidateMeterTime = "True";}
  		if(strValidateMeterTime.equals("True"))
  		{
  			//Validate Presumed Occupied
  			HttpConnections clsHttpConnections = new HttpConnections();
			String strParkingState = clsHttpConnections.HTTPCONNECTIONS_StoreParkingSpotState(objDictionary,strHostType,strSpotNumber);
  			String strMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,sessionMeter,strSpotNumber,strHostType);
			if(strParkingState.equals("PARKING_STATE_VALID_PRESUMED_OCCUPIED"))//Need a way to validate Presumed Occupied
      		{
      			double dbllessThanTime = dblExpectedMaxTimeRemaining+1;
				if(dblExpectedMaxTimeRemaining < Double.parseDouble(strMaxRemaining) && (dblExpectedMaxTimeRemaining+10) > Double.parseDouble(strMaxRemaining))
			  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
			  	else
			  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"The meter max time remaining presumed occupied ("+strMaxRemaining+") - was not greater than ("+dblExpectedMaxTimeRemaining+") and less than ("+dbllessThanTime+")"+strMethodName,"False");}
      		}
      		else
      		{
      			if(OriginalValidTimePurchase.equals("0"))
      			{
      			 	if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining))
				  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
				  	else
				  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName,"False");}
      			}
      			else
      			{
				  	if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining)||dblExpectedMaxTimeRemaining + 1 == Double.parseDouble(strMaxRemaining)||dblExpectedMaxTimeRemaining - 1 == Double.parseDouble(strMaxRemaining))
				  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
				  	else
				  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName,"False");}
      			}
      		}
		  	//clsMeter.METER_ValidateExpectedMeterTime(objDictionary,driver, intExpectedRemainingTimeMinutes, strSpotNumber);
		}
  		Reporter.log("<font color='#5533ff'>Method ("+strMethodName+") took: " + timer.stop()+"</font>");
	}
	public void METER_InsertCardPayNoSpot(Map<String, String> objDictionary,WebDriver driver,String strSpotNumber,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Reporter.log("METER_InsertCardPayNoSpot: "+strHostType);
		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strMaximumDuration = objDictionary.get("strMaximumDuration");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");if(strFreeTimeFirstPayment == null) {strFreeTimeFirstPayment = "0";}
		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		String strTrueUp = objDictionary.get("strTrueUp");if(strTrueUp == null) {strTrueUp = "False";}
		//String strHostType
		clsMeter.StoreDumpStackValuesInDictionary(objDictionary, driver, strSpotNumber, strHostType, "Before");
		String strOriginalValidTimePurchase = objDictionary.get("strValidTimePurchased");
		String strOriginalMaxRemaining = objDictionary.get("strMaxRemaining");
		String strMeterFreeValue = objDictionary.get("strMeterFreeValue");
		String strTrueUpTime = objDictionary.get("strTrueUpTime");if(strTrueUpTime == null) {strTrueUpTime = "0";}
		int intRemainingFreeTime = 0;
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary, driver,"Local");
		String strLocalHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
		//Screen Shot
		String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
		if(strEnableAllImages.equals("True")){clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, "NA");}
		if(strMeterFreeValue.equals("True"))
		{
			String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
			intRemainingFreeTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
		}
		String strNoParkingStartTime = objDictionary.get("strNoParkingStartTime");
		int intMinutesBeforeNoParking = 0;
		if(strNoParkingStartTime != null)
		{
			intMinutesBeforeNoParking = clsMeter.METER_CalculateRemainingMinutesBeforeNoParkingCeil(objDictionary, null,  strNoParkingStartTime);
		}
		//PRT: Purchase Remaining Time
  		Double dblExpectedMaxTimeRemaining = 0.0;
  		double dblCreditCardPaymentTime = 0.0;
  		String strFamily = objDictionary.get("strFamily");if(strFamily == null) {strFamily = "";}
  		String strParkingRateDefaultMinimumCost = clsMeter.METER_GET_GS4MeterValue(objDictionary,driver,"PARKING_RATE_DEFAULT_MINIMUM_COST");
		int intDefaultMinMultiplier = Integer.parseInt(strParkingRateDefaultMinimumCost)/25;
		if(strFamily.equals("")){dblCreditCardPaymentTime = Double.parseDouble(strMeterIncrementTime) * intDefaultMinMultiplier;}
  		else
  		{
  			String strFamilyBuyMinutes = objDictionary.get("strFamilyBuyMinutes");
  			dblCreditCardPaymentTime = Double.parseDouble(strFamilyBuyMinutes) * intDefaultMinMultiplier;
  		}
  		if(Double.parseDouble(strOriginalMaxRemaining) <= dblCreditCardPaymentTime)
  		{dblExpectedMaxTimeRemaining = 0.0;}
  		else
  		{
  			if(strOriginalValidTimePurchase.equals("0"))
			{
  				if(intRemainingFreeTime + Double.parseDouble(strFreeTimeFirstPayment) + dblCreditCardPaymentTime  >= Double.parseDouble(strOriginalMaxRemaining))
				{dblExpectedMaxTimeRemaining = 0.0;}
				else
				{
					//54 - 0 - 5 - 30 - 3 = 16
					//M3001_RO_RTF_FTFP5_PS1_CGPV1_CCP I commented out the below if for this test cases.
//					if(strTrueUp.equals("True"))
//					{
//						dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime - Double.parseDouble(strFreeTimeFirstPayment) - dblCreditCardPaymentTime - Double.parseDouble(strTrueUpTime);
//					}
//					else
//					{
						dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - intRemainingFreeTime - Double.parseDouble(strFreeTimeFirstPayment) - dblCreditCardPaymentTime;
//					}
				}
			}
  			else
  			{
  				if(dblCreditCardPaymentTime >= Double.parseDouble(strOriginalMaxRemaining))
				{dblExpectedMaxTimeRemaining = 0.0;}
				else
				{dblExpectedMaxTimeRemaining = Double.parseDouble(strOriginalMaxRemaining) - dblCreditCardPaymentTime;}
  			}
  		}
  		String strRemoteUser = objDictionary.get("strRemoteUser");if(strRemoteUser == null) {strRemoteUser = "";}
  		//Get Current Screen
  		String strExpectedScreen = "SCREEN_MULTI_HOME";//Global
  		if(strForcedMulti.equals("false"))
  		{
  			String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
  	  		if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
  		}
  		String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
		if(strCurrentUIState.equals("SCREEN_MULTI_SELECT_SPACE"))
		{
			String strDeviceId = objDictionary.get("strDeviceId");
			String[] arrLocalMeterSpots = strDeviceId.split("-");
			String strSpotName = "";if(strSpotNumber.equals("1")){strSpotName = arrLocalMeterSpots[0];}else{strSpotName = arrLocalMeterSpots[1]; }
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_enter_space.py "+strSpotName);
	  		strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
		}
  		strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
  		if(!strCurrentUIState.equals(strExpectedScreen)){UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to add a payent, Meter screen was not at the (SCREEN_MULTI_HOME) - The Unable to add a payment"+strMethodName);}
  		if (strFamily.equals("Handicap"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost,"testauto_handicap_session_spot_1.sh");
	  		try {Thread.sleep(4000);}catch (Exception e) {}
		}
  		if(strForcedMulti.equals("false"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testauto_card_pay_spot_"+strSpotNumber+".py");}
		else
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testauto_card_pay_no_spot.sh");}
  		//Calculate Time between Card Swipe and Credit Card Approval
 		clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strLocalHost, "on_message_pass()", 90,"Card Response "+strHostType+" Space", strSpotNumber, strHostType);
  		//Timer
  		String strMeterUser = "";
  		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		String strScanStartTime = dateFormatGmt.format(new Date());
  		int intCounter = 0;
		//Set Expected Screen
  		strExpectedScreen = "SCREEN_MULTI_HOME";//Global
  		if(strForcedMulti.equals("false"))
  		{
  			String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
  	  		if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else {strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
  		}//Dual
  		String CurrentUIState = "";
		do
		{
			CurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,driver,strSpotNumber,"Local");
      		intCounter++;
      		if(intCounter>25)//IDLE_TIMEOUT_SECONDS_MULTI
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The expected meter screen ("+strExpectedScreen+") did not appear current screen equaled ("+CurrentUIState+")-"+strMethodName);
      		}
		} 	while (!CurrentUIState.equals(strExpectedScreen));
		try
		{
			String strScanEndTime = dateFormatGmt.format(new Date());
			long d1=formater.parse(strScanStartTime).getTime();
	    	long d2=formater.parse(strScanEndTime).getTime();
	    	Database clsDatabase = new Database();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,"TransactionDetailsToAvailableTime",(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
	    	Reporter.log("TransactionDetailsToAvailableTime: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
		}catch (Exception e) {}
		String CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"After");
		intCounter = 0;
		do
      	{
			CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"After");
			Reporter.log("ValidTimePurchaseAfterCreditCardSwipe: "+CurrentValidTimePurchase);
			intCounter++;
      		if(intCounter>30)
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter Time did not increment correctly-"+strMethodName);
      		}
      	} while (strOriginalValidTimePurchase.equals(CurrentValidTimePurchase));
		if(Double.parseDouble(strOriginalMaxRemaining) == Double.parseDouble(strMaximumDuration) + intRemainingFreeTime)
		{
			Calendar calendar = Calendar.getInstance();
	  		String strFirstPaymentTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
	  		Reporter.log("strFirstPaymentTime: "+strFirstPaymentTime);
			objDictionary.remove("strFirstPaymentTime");objDictionary.put("strFirstPaymentTime",strFirstPaymentTime);
		}
  		//Take Screen Shot
		String strInvocationCounter = objDictionary.get("strInvocationCounter");
		String strTestSuiteName = objDictionary.get("strTestSuiteName");
		String strTestCaseName = objDictionary.get("strTestCaseName");
		if(strEnableAllImages.equals("True")){clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_AfterCard"+strInvocationCounter);}
		//Validate MaxRemainingTime (Function)
	  	String strValidateMeterTime = objDictionary.get("strValidateMeterTime");if(strValidateMeterTime == null) {strValidateMeterTime = "True";}
  		if(strValidateMeterTime.equals("True"))
  		{
  			if(intMinutesBeforeNoParking != 0)
			{
  				if(intMinutesBeforeNoParking == (int) Double.parseDouble(CurrentValidTimePurchase) || intMinutesBeforeNoParking+2 == (int) Double.parseDouble(CurrentValidTimePurchase) || intMinutesBeforeNoParking+1 == (int) Double.parseDouble(CurrentValidTimePurchase) || intMinutesBeforeNoParking-1 == (int) Double.parseDouble(CurrentValidTimePurchase))
				{Reporter.log("Meter Time decremented correctly after coin payment-("+intMinutesBeforeNoParking+")");}
				else
				{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"Meter Time did not decremented correctly after coin payment-expected ("+intMinutesBeforeNoParking+")-actual ("+CurrentValidTimePurchase+")-"+strMethodName);}
				dblExpectedMaxTimeRemaining = 0.0;
			}
			else
			{
				if(strMeterFreeValue.equals("False"))
				{
					double dblExpectedMeterValue;
					if(Double.parseDouble(strOriginalValidTimePurchase) == 0)
					{
						dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + dblCreditCardPaymentTime + Double.parseDouble(strFreeTimeFirstPayment) + intRemainingFreeTime;
						if(Double.parseDouble(strOriginalMaxRemaining) < dblExpectedMeterValue)
						{
							dblExpectedMeterValue = Double.parseDouble(strOriginalMaxRemaining);
						}
					}
					else
					{
						if(Double.parseDouble(strOriginalMaxRemaining) < dblCreditCardPaymentTime)
						{
							dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) + Double.parseDouble(strOriginalMaxRemaining);
						}
						else
						{
							dblExpectedMeterValue = Double.parseDouble(strOriginalValidTimePurchase) +dblCreditCardPaymentTime;
						}
					}
					if(dblExpectedMeterValue > Double.parseDouble(strMaximumDuration))
					{
						dblExpectedMeterValue = Double.parseDouble(strMaximumDuration);
					}
					if(dblExpectedMeterValue == Double.parseDouble(CurrentValidTimePurchase) ||dblExpectedMeterValue + 1 == Double.parseDouble(CurrentValidTimePurchase)||dblExpectedMeterValue - 1 == Double.parseDouble(CurrentValidTimePurchase)) // Added second condition
					{Reporter.log("Meter Time decremented correctly after coin payment-("+dblExpectedMeterValue+")");}
					else
					{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"Meter Time did not decremented correctly after coin payment-expected ("+dblExpectedMeterValue+")-actual ("+CurrentValidTimePurchase+")-"+strMethodName);}
				}
			}
			String strMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,strSpotNumber,strHostType);
			if(strTrueUp.equals("True"))
			{
				if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining)||dblExpectedMaxTimeRemaining + 1 == Double.parseDouble(strMaxRemaining)||dblExpectedMaxTimeRemaining + 2 == Double.parseDouble(strMaxRemaining))
			  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
			  	else
			  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName);}
			}
			else
			{
			  	if(dblExpectedMaxTimeRemaining == Double.parseDouble(strMaxRemaining)||dblExpectedMaxTimeRemaining + 1 == Double.parseDouble(strMaxRemaining)||dblExpectedMaxTimeRemaining - 1 == Double.parseDouble(strMaxRemaining))
			  	{Reporter.log("The meter max time remaining decremented correctly from ("+strOriginalMaxRemaining+") to ("+dblExpectedMaxTimeRemaining+")");}
			  	else
			  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The meter max time remaining ("+strMaxRemaining+") did not equal the expected time remaining ("+dblExpectedMaxTimeRemaining+")-"+strMethodName);}
			}
		  	//clsMeter.METER_ValidateExpectedMeterTime(objDictionary,driver, intExpectedRemainingTimeMinutes, strSpotNumber);

		}
	}
	//*******************************************************************************************************************************************************************************************


	public void METER_InsertCardPurchaseRemainingTime(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		//PRT: Purchase Remaining Time
		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","Before");
		//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_card_pay_max_spot_1.py");
		String strLocalHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testauto_card_pay_max_time_spot_"+strSpotNumber+".sh");
		//Credit Card Sync
		String CurrentValidTimePurchase = "0";
		int intCounter = 0;
		do
      	{
			CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"After");
			intCounter++;
      		if(intCounter>20){UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter Time did not increment correctly-"+strMethodName);}
      	} while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase));
	}




	//*******************************************************************************************************************************************************************************************
	//Insert Card Pay Purchase Remaining Time
	//*******************************************************************************************************************************************************************************************
	public void METER_InsertCardPurchaseRemainingTimeNoSpot(Map<String, String> objDictionary,WebDriver driver,Session sessionLocalMeter,Session sessionRemoteMeter,String strSpotNumber,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		Session sessionMeter = null;
		if(strHostType.equals("Local")){sessionMeter = sessionLocalMeter;}else{sessionMeter = sessionRemoteMeter;}
		clsMeter.StoreDumpStackValuesInDictionary(objDictionary,driver,sessionMeter,strSpotNumber,strHostType,"Before");
		String OriginalValidTimePurchase = objDictionary.get("strValidTimePurchased");
		String strOriginalMaxRemaining = objDictionary.get("strMaxRemaining");
		String strMeterFreeValue = objDictionary.get("strMeterFreeValue");
		Reporter.log("ValidTimePurchaseBeforeCreditCardSwipe: "+OriginalValidTimePurchase);
		String strLocalHost = objDictionary.get("strHost");
		//clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,"Local","testautof_touch_screen.py");
		//Get Current Screen
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,sessionMeter);
		String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,sessionLocalMeter,"1");
		String strMeterUser = "";
  		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		String strExpectedScreen = "SCREEN_MULTI_HOME";//Global
		if(strMeterUser.equals("root"))
		{
			//Single and Dual
			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
	  	}
		if(!strCurrentUIState.equals(strExpectedScreen)){UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"Unable to add a payent, Meter screen was not at the (SCREEN_MULTI_HOME) - The Unable to add a payment"+strMethodName,"False");}
 		//Local meter get success payment response from Remote Meter
		if(strMeterUser.equals("root"))
		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strHostType,"testauto_card_pay_max_time_spot_"+strSpotNumber+".py");}
		else
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strHostType,"testauto_card_pay_max_time_no_spot.sh");
			//METER_CopyMeterScreenShotLocally(objDictionary,sessionMeter,"NA");
		}
		//Calculate Time between Card Swipe and Credit Card Approval
		//clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "on_message_pass()", 70,"Card Response "+strHostType+" Space", strSpotNumber, strHostType);
		clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,sessionLocalMeter,strLocalHost, "on_message_pass", 70,"Card Response "+strHostType+" Space", strSpotNumber, strHostType);

		METER_CopyMeterScreenShotLocallyNoScreenTouch(objDictionary,sessionMeter,"NA");
		//Local meter get success payment response from Remote Meter
 		Reporter.log("METER_InsertCardPayNoSpot HostType: "+strHostType);
 		//Wait for UI_STATE_CARD_TRANSACTION FSM to change to UI_STATE_HOME
  		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		String strScanStartTime = dateFormatGmt.format(new Date());
  		int intCounter = 0;
		strExpectedScreen = "SCREEN_MULTI_HOME";
		if(strMeterUser.equals("root"))
		{
  			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
  		}
		do
		{
			strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,sessionLocalMeter,"1");
			intCounter++;
      		if(intCounter>45)//IDLE_TIMEOUT_SECONDS_MULTI
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,sessionLocalMeter,strHostType, "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"The expected meter screen ("+strExpectedScreen+") did not appear current screen equaled ("+strCurrentUIState+")-"+strMethodName,"False");
      		}
		} while (!strCurrentUIState.equals(strExpectedScreen));
		try
		{
			String strScanEndTime = dateFormatGmt.format(new Date());
			long d1=formater.parse(strScanStartTime).getTime();
	    	long d2=formater.parse(strScanEndTime).getTime();
	    	Database clsDatabase = new Database();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,"TransactionDetailsToAvailableTime",(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
	    	Reporter.log("TransactionDetailsToAvailableTime: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
		}catch (Exception e) {}
		//Take Screen Shot
		String strInvocationCounter = objDictionary.get("strInvocationCounter");
		String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
		if(strEnableAllImages.equals("True")){clsMeter.METER_CopyMeterScreenShotLocally(objDictionary,sessionLocalMeter,"NA"+strInvocationCounter);}
		//Add a Sleep to see if MaxTime Get Calculated Better
		try {Thread.sleep(10000);}catch (Exception e) {}
		String strMaxTimeRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,sessionMeter,strSpotNumber,strHostType);
		String strValidateMeterTime = objDictionary.get("strValidateMeterTime");if(strValidateMeterTime == null) {strValidateMeterTime = "True";}
		if (strValidateMeterTime.equals("True"))
		{
			if(!strMaxTimeRemaining.equals("0"))
			{
				UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionLocalMeter,"The ("+strHostType+") Meter Max Remaining Time ("+strMaxTimeRemaining+") did not equal zero-"+strMethodName,"False");
			}
		}
	}
	public void METER_InsertCardPurchaseRemainingTimeNoSpot(Map<String, String> objDictionary, WebDriver driver, String strSpotNumber, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		//PRT: Purchase Remaining Time
		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"Before");
		Reporter.log("ValidTimePurchaseBeforeCreditCardSwipe: "+OriginalValidTimePurchase);
		String strLocalHost = objDictionary.get("strHost");
		String strFamily = objDictionary.get("strFamily");if(strFamily == null) {strFamily = "";}
		String strMaximumDuration = objDictionary.get("strMaximumDuration");
		String strRemoteUser = objDictionary.get("strRemoteUser");if(strRemoteUser == null) {strRemoteUser = "";}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
		//Get Current Screen
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
		String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
		String strExpectedScreen = "SCREEN_MULTI_HOME";//Global
		//String strHostType
		clsMeter.StoreDumpStackValuesInDictionary(objDictionary, driver, strSpotNumber, strHostType, "Before");
		String strOriginalValidTimePurchase = objDictionary.get("strValidTimePurchased");
		String strOriginalMaxRemaining = objDictionary.get("strMaxRemaining");
		String strMeterFreeValue = objDictionary.get("strMeterFreeValue");
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,driver, "Local");
  		if(strForcedMulti.equals("false"))
  		{
  			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
  		}
  		else
  		{
  			//Enter Space
  			String strHost = objDictionary.get("strHost");
  			String strMeterSpotName = objDictionary.get("strMeterSpotName");
  			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterSpotName);
  			if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_MULTI_HOME";}
  			strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
  		}
  		if(!strCurrentUIState.equals(strExpectedScreen)){UpdateErrorMessageWithPivotalData(objDictionary,driver,"Unable to add a payent, Meter screen was not at the (SCREEN_MULTI_HOME) - The Unable to add a payment"+strMethodName);}
 		//Local meter get success payment response from Remote Meter
		if (strFamily.equals("Handicap"))
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost,"testauto_handicap_session_spot_1.sh");
	  		try {Thread.sleep(4000);}catch (Exception e) {}
		}
		if(strForcedMulti.equals("false"))
  		{clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testauto_card_pay_max_time_spot_"+strSpotNumber+".sh");}
		else
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "ytestauto_card_pay_max_time_no_spot.sh");
		}
  		//Calculate Time between Card Swipe and Credit Card Approval
 		clsMeter.METER_ScanMeterLogsUntilValueAppearsOrTimeOutReached(objDictionary,strLocalHost, "on_message_pass()", 60,"Card Response "+strHostType+" Space", strSpotNumber, strHostType);
		//Local meter get success payment response from Remote Meter
 		Reporter.log("METER_InsertCardPayNoSpot HostType: "+strHostType);
  		//Wait for UI_STATE_CARD_TRANSACTION FSM to change to UI_STATE_HOME
  		String strMeterUser = "";
  		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}else{strMeterUser = objDictionary.get("strRemoteUser"+strHostType.replace("Remote",""));}
  		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss.SSS");
		String strScanStartTime = dateFormatGmt.format(new Date());
  		int intCounter = 0;
		String CurrentUIState = "";
		if(strRemoteUser.equals(""))
  		{
  			strNumberOfSpots = METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,driver, strHostType);
  			if(strForcedMulti.equals("false"))
	  		{
  				if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_DUAL_NON_EMPTY_HOME";}
	  		}
  			else
  			{
  				if(strNumberOfSpots.equals("1")){strExpectedScreen = "SCREEN_HOME";}else{strExpectedScreen = "SCREEN_MULTI_HOME";}
  			}
  		}
		do
		{
			CurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,driver,strSpotNumber,"Local");
      		intCounter++;
      		if(intCounter>30)//IDLE_TIMEOUT_SECONDS_MULTI
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The expected meter screen ("+strExpectedScreen+") did not appear current screen equaled ("+CurrentUIState+")-"+strMethodName);
      		}
		} while (!CurrentUIState.equals(strExpectedScreen));

		try
		{
			String strScanEndTime = dateFormatGmt.format(new Date());
			long d1=formater.parse(strScanStartTime).getTime();
	    	long d2=formater.parse(strScanEndTime).getTime();
	    	Database clsDatabase = new Database();
	    	clsDatabase.WriteToActionTimeDatabase(objDictionary,"TransactionDetailsToAvailableTime",(int)TimeUnit.MILLISECONDS.toMillis(d2-d1),strMeterUser);
	    	Reporter.log("TransactionDetailsToAvailableTime: "+(int)TimeUnit.MILLISECONDS.toMillis(d2-d1));
		}catch (Exception e) {}
		//Take Screen Shot
		String strEnableAllImages = objDictionary.get("strEnableAllImages");if(strEnableAllImages == null) {strEnableAllImages = "False";}
		if(strEnableAllImages.equals("True"))
		{
			String strInvocationCounter = objDictionary.get("strInvocationCounter");
			String strTestSuiteName = objDictionary.get("strTestSuiteName");
			String strTestCaseName = objDictionary.get("strTestCaseName");
			clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"AfterCardRemaingTime"+strInvocationCounter);
		}

		String strCurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"After");
		intCounter = 0;
		do
      	{
			strCurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,strHostType,"After");
			Reporter.log("ValidTimePurchaseAfterCreditCardSwipe: "+strCurrentValidTimePurchase);
			intCounter++;
      		if(intCounter>30)
      		{
      			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strLocalHost, "testautof_touch_screen.py");
      			UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter Time did not increment correctly-"+strMethodName);
      		}
      	} while (strOriginalValidTimePurchase.equals(strCurrentValidTimePurchase));
		String strValidateMeterTime = objDictionary.get("strValidateMeterTime");if(strValidateMeterTime == null) {strValidateMeterTime = "True";}
		if (strValidateMeterTime.equals("True"))
		{
			if(strMeterFreeValue.equals("False"))
			{
				if(Double.parseDouble(strOriginalMaxRemaining) < Double.parseDouble(strMaximumDuration) - Double.parseDouble(strOriginalValidTimePurchase))
				{
	      			if(strOriginalValidTimePurchase.equals("0"))//A2038_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC
            		{
            			strMaximumDuration = strOriginalMaxRemaining;
            		}
				}
				String strMinutesBeforeFreeParking = objDictionary.get("strMinutesBeforeFreeParking");if(strMinutesBeforeFreeParking == null) {strMinutesBeforeFreeParking = "";}
				if(strMinutesBeforeFreeParking.equals(""))
				{
					if(Double.parseDouble(strMaximumDuration) == Double.parseDouble(strCurrentValidTimePurchase)||Double.parseDouble(strMaximumDuration)-1 == Double.parseDouble(strCurrentValidTimePurchase)||Double.parseDouble(strMaximumDuration) == Double.parseDouble(strCurrentValidTimePurchase)-1)
					{Reporter.log("Meter Time decremented correctly after credit card payment for max time-("+Double.parseDouble(strOriginalMaxRemaining)+")");}
					else
					{
						clsMeter.METER_CopyMeterScreenShotLocally(objDictionary, "ScreenShotAfterCreditCardPayment");
						clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"Meter Time did not decremented correctly after credit card payment-expected ("+Double.parseDouble(strOriginalMaxRemaining)+")-actual ("+Double.parseDouble(strCurrentValidTimePurchase+")-"+strMethodName));
					}
				}
				else
				{
					if(Double.parseDouble(strMaximumDuration) == Double.parseDouble(strCurrentValidTimePurchase) || Double.parseDouble(strMaximumDuration) == Double.parseDouble(strCurrentValidTimePurchase) -1|| Double.parseDouble(strMaximumDuration)-1  == Double.parseDouble(strCurrentValidTimePurchase))
					{Reporter.log("Meter Time decremented correctly after credit card payment for max time-("+Double.parseDouble(strOriginalMaxRemaining)+")");}
					else
					{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,driver,"Meter Time did not decremented correctly after credit card payment-expected ("+Double.parseDouble(strOriginalMaxRemaining)+")-actual ("+strCurrentValidTimePurchase+")-"+strMethodName);}
				}
			}
			//Need a Better Sync
			try {Thread.sleep(2000);}catch (Exception e) {}
			String strMaxTimeRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,strSpotNumber, strHostType);
			if(!strMaxTimeRemaining.equals("0"))
			{
				UpdateErrorMessageWithPivotalData(objDictionary,driver,"The ("+strHostType+") Meter Max Remaining Time did not equal zero-"+strMethodName);
			}
		}
	}
	//*******************************************************************************************************************************************************************************************


	public int METER_InsertCardPayMaxTime2(Map<String, String> objDictionary, WebDriver driver,String strMaximumDuration, String strMeterLogValue)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
  		String strSpotNumber = objDictionary.get("strSpotNumber");
  		String strTestCaseName = objDictionary.get("strTestCaseName");
	 	//PRT: Purchase Remaining Time
		String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,strSpotNumber,"Local","Before");
		//START CardPayMaxTime Log Trace
		clsMeter.METER_StartLogTrace2(objDictionary,driver,"CardPayMaxTime");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_card_pay_max_time_spot_"+strSpotNumber+".py");
		//Need when Extra Logging is Enabled
	  	Calendar calendar = Calendar.getInstance();
      	String strUsedTimeStart = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_card_pay_max_time_spot_1.sh");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_card_pay_max_time_spot_"+strSpotNumber+".py");
		//Credit Card Sync
		String CurrentValidTimePurchase = "0";
		int intCounter = 0;
		do
      	{
      		CurrentValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","After");
      		intCounter++;
      		if(intCounter>20){UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Meter Time did not increment correctly-"+strMethodName);}
      	} while (OriginalValidTimePurchase.equals(CurrentValidTimePurchase));
		//VALIDATE METER LOGS
		try {clsMeter.METER_CopyLogTraceLocally2(objDictionary, driver,strTestCaseName+"_CardPayMaxTime","CardPayMaxTime");}catch (Exception e) {}
		clsMeter.METER_ScanMeterLogForValue(objDictionary, driver,strTestCaseName+"_CardPayMaxTime", strMeterLogValue);
		int intTimeUsed = clsMeter.METER_CalculateUsedTime(objDictionary, null,  strUsedTimeStart);
      	return Integer.parseInt(strMaximumDuration) - intTimeUsed;
	}
	public void METER_InsertCardMaxTime(Map<String, String> objDictionary, WebDriver driver, int intNbrCardInsertsRequiredForMaxTime, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strHost = objDictionary.get("strHost");
  		String strFreeTimeMinutes = objDictionary.get("strFreeTimeMinutes");if(strFreeTimeMinutes == null) {strFreeTimeMinutes = "0";}
		int intAddCounter = 0;
//	  	String OriginalValidTimePurchase = clsMeter.GetMeterValidTimePurchased(objDictionary,driver,"1","Local","Before");
	  	String CurrentValidTimePurchase = "0";
		while (intAddCounter < intNbrCardInsertsRequiredForMaxTime)
		{
			//CCP1: Credit Card Payment Spot 1
			int intCounter = 0;
			clsMeter.METER_InsertCardPayNoSpot(objDictionary, null,strSpotNumber,"Local");
			if(intAddCounter == 0 && !strFreeTimeMinutes.equals("0"))
			{
				String strRemainingFreeTimeStart = objDictionary.get("strRemainingFreeTimeStart");
			  	int strRemainingFreeTimeFirstPaymentMinutes = clsMeter.METER_CalculateRemainingFreeTime(objDictionary, driver, strRemainingFreeTimeStart, strFreeTimeMinutes);
			  	objDictionary.put("strRemainingFreeTimeFirstPaymentMinutes",Integer.toString(strRemainingFreeTimeFirstPaymentMinutes));
		    }
			try {Thread.sleep(2000);}catch (Exception e) {}
	      	intAddCounter++;

	      	//This is for testing purpose delete
	      	//clsMeter.METER_MeterWaitWithMessage(objDictionary, 180, "Wait 3 minutes after violation");

	    }
	}

	//********************************************************************************************************
	//Calculate Meter Time
	//********************************************************************************************************
	public int METER_CalculateRemainingFreeTime(Map<String, String> objDictionary, WebDriver driver, String strRemainingFreeTimeStart, String strFreeTimeMinutes)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println(strMethodName.toUpperCase());
		Meter clsMeter = new Meter();
		int intRemainingFreeTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strRemainingTimeStop = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("strRemainingFreeTimeStart: "+strRemainingFreeTimeStart);
	  	Reporter.log("strRemainingTimeStop: "+strRemainingTimeStop);
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
		try
		{
			long d1=formater.parse(strRemainingFreeTimeStart).getTime();
	    	long d2=formater.parse(strRemainingTimeStop).getTime();
	    	long lUsedFreeSeconds = TimeUnit.MILLISECONDS.toSeconds(d2-d1);
	    	Reporter.log("UsedFreeSeconds: "+lUsedFreeSeconds);
//	    	int intUsedFreeMinutes = (int) Math.ceil(lUsedFreeSeconds / 60.00);
	    	int intUsedFreeMinutes = (int) Math.round(lUsedFreeSeconds / 60.00);
//	    	int intUsedFreeMinutes = (int) Math.floor(lUsedFreeSeconds / 60.00);
	    	Reporter.log("UsedFreeMinutes: "+intUsedFreeMinutes);
	    	intRemainingFreeTime = Integer.parseInt(strFreeTimeMinutes) -  intUsedFreeMinutes;
	    	if(intUsedFreeMinutes > Integer.parseInt(strFreeTimeMinutes))
	    	{
	    		//Validate Meter Value Free is False
	    		String strFreeVariable = clsMeter.METER_GetMeterFreeValue(objDictionary,driver,"Local");
	    		if(strFreeVariable.equals("False"))
	    		{Reporter.log("The free time on the meter expired and the meter no longe displays Free-"+strMethodName);}
	    		else
	    		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The free time on the meter expired but the meter still displays Free-"+strMethodName);}
	    	}
	    	Reporter.log("intRemainingFreeTime: "+intRemainingFreeTime);
    	}catch (Exception e)
		{
    		clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unable to calculate intTimeRemaining");
    	}
		return intRemainingFreeTime;
	}
	public int METER_CalculateRemainingFreeTimeSeconds(Map<String, String> objDictionary, WebDriver driver, String strRemainingFreeTimeStart, String strFreeTimeMinutes)
	{
		Meter clsMeter = new Meter();
		int intRemainingFreeTimeSec = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strRemainingTimeStop = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("strRemainingFreeTimeStart: "+strRemainingFreeTimeStart);
	  	Reporter.log("strRemainingTimeStop: "+strRemainingTimeStop);
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
		try
		{
			long d1=formater.parse(strRemainingFreeTimeStart).getTime();
		    	long d2=formater.parse(strRemainingTimeStop).getTime();
		    	long lUsedFreeSeconds = TimeUnit.MILLISECONDS.toSeconds(d2-d1);
		    	int intFreeTimeSeconds = Integer.parseInt(strFreeTimeMinutes) * 60;
		    	intRemainingFreeTimeSec = intFreeTimeSeconds - (int) lUsedFreeSeconds;
		    	Reporter.log("intRemainingFreeTimeSec: "+intRemainingFreeTimeSec);
	    	}catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unable to calculate intTimeRemaining");}
		return intRemainingFreeTimeSec;
	}
	public int METER_CalculateRemainingFreeTimeFloor(Map<String, String> objDictionary, WebDriver driver,String strRemainingFreeTimeStart, String strFreeTimeMinutes)
	{
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intRemainingFreeTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strRemainingTimeStop = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
		SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
		try
		{
			long d1=formater.parse(strRemainingFreeTimeStart).getTime();
		    	long d2=formater.parse(strRemainingTimeStop).getTime();
		    	long lUsedFreeSeconds = TimeUnit.MILLISECONDS.toSeconds(d2-d1);
		    	int intUsedFreeMinutes = (int) Math.floor(lUsedFreeSeconds / 60.00);
		    	intRemainingFreeTime = Integer.parseInt(strFreeTimeMinutes) -  intUsedFreeMinutes;
	    	}catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unable to calculate intTimeRemaining");}
		return intRemainingFreeTime;
	}
	public int METER_CalculateRemainingFreeTimeCeil(Map<String, String> objDictionary, WebDriver driver, String strRemainingFreeTimeStart, String strFreeTimeMinutes) {
	    Meter clsMeter = new Meter();
	    int intRemainingFreeTime = 0;

	    try {
	        // Parsing the start time using LocalTime for better handling of time-based operations
	        LocalTime startTime = LocalTime.parse(strRemainingFreeTimeStart);
	        LocalTime currentTime = LocalTime.now(); // Current time is used as the stop time

	        // Log the input times for debugging
	        Reporter.log("strRemainingFreeTimeStart: " + strRemainingFreeTimeStart);
	        Reporter.log("strRemainingTimeStop: " + currentTime);

	        // Calculate the duration between start and current time in seconds
	        long usedFreeSeconds = Duration.between(startTime, currentTime).getSeconds();
	        Reporter.log("UsedFreeSeconds: " + usedFreeSeconds);

	        // Calculate used free minutes, rounded up (ceil)
	        int intUsedFreeMinutes = (int) Math.ceil(usedFreeSeconds / 60.0);
	        Reporter.log("UsedFreeMinutes (ceil): " + intUsedFreeMinutes);

	        // Calculate remaining free time
	        intRemainingFreeTime = Integer.parseInt(strFreeTimeMinutes) - intUsedFreeMinutes;

	        // Log the remaining free time
	        Reporter.log("intRemainingFreeTime: " + intRemainingFreeTime);

	    } catch (Exception e) {
	        // Improved exception handling: log the stack trace and error message
	        Reporter.log("Error in METER_CalculateRemainingFreeTimeCeil: " + e.getMessage());
	        e.printStackTrace();
	        clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intTimeRemaining due to an error: " + e.getMessage());
	    }

	    return intRemainingFreeTime;
	}
	
	public int METER_CalculateRemainingFreeTimeCeil_New(Map<String, String> objDictionary, WebDriver driver,String strRemainingFreeTimeStart, String strFreeTimeMinutes) 
	{
		Meter clsMeter = new Meter();
	    try {
	        LocalTime startTime = LocalTime.parse(strRemainingFreeTimeStart);
	        LocalTime currentTime = LocalTime.now(); // or LocalTime.now(ZoneId.systemDefault())

	        Reporter.log("strRemainingFreeTimeStart: " + strRemainingFreeTimeStart);
	        Reporter.log("Current time: " + currentTime);

	        // Handle day rollover by using ChronoUnit or converting to minutes since midnight
	        long startMinutes = startTime.toSecondOfDay() / 60;
	        long currentMinutes = currentTime.toSecondOfDay() / 60;

	        long usedMinutesExact;
	        if (currentMinutes >= startMinutes) {
	            usedMinutesExact = currentMinutes - startMinutes;
	        } else {
	            // Crossed midnight (assuming < 24h span)
	            usedMinutesExact = (1440 - startMinutes) + currentMinutes; // 1440 = 24*60
	        }

	        int intUsedFreeMinutes = (int) Math.ceil(usedMinutesExact); // already integer, but keep ceil if you ever have seconds
	        Reporter.log("UsedFreeMinutes (ceil): " + intUsedFreeMinutes);

	        int totalFree = Integer.parseInt(strFreeTimeMinutes);
	        int intRemainingFreeTime = totalFree - intUsedFreeMinutes;

	        Reporter.log("intRemainingFreeTime: " + intRemainingFreeTime);
	        return Math.max(0, intRemainingFreeTime); // prevent negative remaining unless you want to allow it

	    } catch (Exception e) {
	        Reporter.log("Error in METER_CalculateRemainingFreeTimeCeil: " + e.getMessage());
	        e.printStackTrace();
	        clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unable to calculate remaining free time: " + e.getMessage());
	        return 0; // or throw new RuntimeException(e);
	    }
	}
	
	public int METER_CalculateRemainingMinutesBeforeFree(Map<String, String> objDictionary, WebDriver driver, String targetTimeStr) 
	{  
	    Meter clsMeter = new Meter();
	    try {
	        LocalTime target = LocalTime.parse(targetTimeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
	        LocalTime now = LocalTime.now();
	        Duration duration;
	        if (target.isAfter(now)) {
	            duration = Duration.between(now, target);
	        } else {
	            // Wraps to next day
	            duration = Duration.between(now, target).plus(Duration.ofDays(1));
	        }
	        long remainingSeconds = duration.getSeconds();
	        int remainingMinutes = (int) Math.ceil(remainingSeconds / 60.0);
	        Reporter.log("Target time: " + targetTimeStr);
	        Reporter.log("Current time: " + now);
	        Reporter.log("Remaining seconds: " + remainingSeconds);
	        Reporter.log("Remaining minutes: " + remainingMinutes);
	        return remainingMinutes;
	    } catch (Exception e) {
	        clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"Unable to calculate remaining free time. Target: " + targetTimeStr);
	        return 0; // or throw, depending on your policy
	    }
	}

	public int METER_CalculateRemainingMinutesBeforeFreeStartTime(Map<String, String> objDictionary, WebDriver driver)
	{
		String strFreeParkingStartTime = objDictionary.get("strFreeParkingStartTime");
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intRemainingNoParkingTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strCurrentTime = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("strNoParkingStartTime: "+strFreeParkingStartTime);
		Reporter.log("strCurrentTime "+strCurrentTime);
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
	  	try
		{
			long d1=formater.parse(strFreeParkingStartTime).getTime();
		    long d2=formater.parse(strCurrentTime).getTime();
		    long lRemainingNoParkingSeconds = 0;
		    if(d1 > d2)
		    {
		    	lRemainingNoParkingSeconds = TimeUnit.MILLISECONDS.toSeconds(d1-d2);
		    }
		    else
		    {
		    	long d3=formater.parse("24:00:00").getTime();
		    	lRemainingNoParkingSeconds = TimeUnit.MILLISECONDS.toSeconds(d3-d2+d1);
		    }
		    System.out.println("lRemainingNoParkingSeconds: "+lRemainingNoParkingSeconds);
			Reporter.log("lRemainingNoParkingSeconds "+lRemainingNoParkingSeconds);
	    	intRemainingNoParkingTime = (int) Math.round(lRemainingNoParkingSeconds / 60.00);//Udated Aug 1, 2118
	    	System.out.println("intRemainingNoParkingTime: "+intRemainingNoParkingTime);
	    	Reporter.log("intRemainingNoParkingTime "+intRemainingNoParkingTime);
		}
		catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intRemainingNoParkingTime");}
		System.out.println(intRemainingNoParkingTime);
		return intRemainingNoParkingTime;
	}
	public int METER_CalculateRemainingMinutesBeforeNoParking(Map<String, String> objDictionary, WebDriver driver, String strNoParkingStartTime)
	{
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intRemainingNoParkingTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strCurrentTime = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("strNoParkingStartTime: "+strNoParkingStartTime);
		Reporter.log("strCurrentTime "+strCurrentTime);
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
	  	try
		{
			long d1=formater.parse(strNoParkingStartTime).getTime();
		    long d2=formater.parse(strCurrentTime).getTime();
		    long lRemainingNoParkingSeconds = 0;
		    if(d1 > d2)
		    {
		    	lRemainingNoParkingSeconds = TimeUnit.MILLISECONDS.toSeconds(d1-d2);
		    }
		    else
		    {
		    	long d3=formater.parse("24:00:00").getTime();
		    	lRemainingNoParkingSeconds = TimeUnit.MILLISECONDS.toSeconds(d3-d2+d1);
		    }
		    System.out.println("lRemainingNoParkingSeconds: "+lRemainingNoParkingSeconds);
			Reporter.log("lRemainingNoParkingSeconds "+lRemainingNoParkingSeconds);
			//Changed to Ceil do to test case M1053_C
//			intRemainingNoParkingTime = (int) Math.ceil(lRemainingNoParkingSeconds / 60.00);//Udated Aug 1, 2118 //M1053_B
	    	intRemainingNoParkingTime = (int) Math.round(lRemainingNoParkingSeconds / 60.00);//Udated Aug 1, 2118
	    	System.out.println("intRemainingNoParkingTime: "+intRemainingNoParkingTime);
	    	Reporter.log("intRemainingNoParkingTime "+intRemainingNoParkingTime);
		}
		catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intRemainingNoParkingTime");}
		System.out.println(intRemainingNoParkingTime);
		return intRemainingNoParkingTime;
	}
	public int METER_CalculateRemainingMinutesBeforeNoParkingCeil(Map<String, String> objDictionary, WebDriver driver, String strNoParkingStartTime)
	{
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intRemainingNoParkingTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strCurrentTime = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("strNoParkingStartTime: "+strNoParkingStartTime);
		Reporter.log("strCurrentTime "+strCurrentTime);
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
	  	try
		{
			long d1=formater.parse(strNoParkingStartTime).getTime();
		    long d2=formater.parse(strCurrentTime).getTime();
		    long lRemainingNoParkingSeconds = 0;
		    if(d1 > d2)
		    {
		    	lRemainingNoParkingSeconds = TimeUnit.MILLISECONDS.toSeconds(d1-d2);
		    }
		    else
		    {
		    	long d3=formater.parse("24:00:00").getTime();
		    	lRemainingNoParkingSeconds = TimeUnit.MILLISECONDS.toSeconds(d3-d2+d1);
		    }
		    System.out.println("lRemainingNoParkingSeconds: "+lRemainingNoParkingSeconds);
			Reporter.log("lRemainingNoParkingSeconds "+lRemainingNoParkingSeconds);
			//Changed to Ceil do to test case M1053_C
			intRemainingNoParkingTime = (int) Math.ceil(lRemainingNoParkingSeconds / 60.00);//Udated Aug 1, 2118 //M1053_B
//	    	intRemainingNoParkingTime = (int) Math.round(lRemainingNoParkingSeconds / 60.00);//Udated Aug 1, 2118
	    	System.out.println("intRemainingNoParkingTime: "+intRemainingNoParkingTime);
	    	Reporter.log("intRemainingNoParkingTime "+intRemainingNoParkingTime);
		}
		catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intRemainingNoParkingTime");}
		System.out.println(intRemainingNoParkingTime);
		return intRemainingNoParkingTime;
	}
	public int METER_CalculateRemainingMinutesBeforeNextRate(Map<String, String> objDictionary, WebDriver driver, String strNextRateStartTime)
	{
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intMinutesBeforeNextRate = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strCurrentTime = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("strNextRateStartTime: "+strNextRateStartTime);
		Reporter.log("strCurrentTime "+strCurrentTime);
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
	  	try
		{
			long d1=formater.parse(strNextRateStartTime).getTime();
		    long d2=formater.parse(strCurrentTime).getTime();
		    long lSecondsBeforeNextRate = 0;
		    if(d1 > d2)
		    {
		    	lSecondsBeforeNextRate = TimeUnit.MILLISECONDS.toSeconds(d1-d2);
		    }
		    else
		    {
		    	long d3=formater.parse("24:00:00").getTime();
		    	lSecondsBeforeNextRate = TimeUnit.MILLISECONDS.toSeconds(d3-d2+d1);
		    }
		    System.out.println("lSecondsBeforeNextRate: "+lSecondsBeforeNextRate);Reporter.log("lSecondsBeforeNextRate "+lSecondsBeforeNextRate);
		    intMinutesBeforeNextRate = (int) Math.ceil(lSecondsBeforeNextRate / 60.00);//Udated Aug 1, 2118
	    	System.out.println("intMinutesBeforeNextRate: "+intMinutesBeforeNextRate);Reporter.log("intMinutesBeforeNextRate "+intMinutesBeforeNextRate);
		}
		catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intRemainingNoParkingTime");}
		return intMinutesBeforeNextRate;
	}


	public int METER_CalculateRemainingTrueUpTime(Map<String, String> objDictionary, WebDriver driver,String strRemainingTrueUpTimeStart, String strMeterIncrementTime) 
	{
	    String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	    System.out.println(strMethodName);
	    Reporter.log("<font color='orange'>     " + strMethodName + "</font>");
	    try {
	        // Use modern java.time API
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        LocalTime startTime = LocalTime.parse(strRemainingTrueUpTimeStart, formatter);
	        LocalTime now = LocalTime.now();  // or LocalTime.parse(strRemainingTimeStop, formatter)
	        // Handle midnight crossover properly
	        Duration durationUsed = Duration.between(startTime, now);
	        if (durationUsed.isNegative()) {
	            durationUsed = durationUsed.plusDays(1); // crossed midnight
	        }
	        long usedMinutes = durationUsed.toMinutes(); // or Math.round if you need rounding
	        int incrementMinutes = Integer.parseInt(strMeterIncrementTime.trim());
	        return Math.max(0, incrementMinutes - (int) usedMinutes);

	    } catch (Exception e) {
	        // Better logging
	        String errorMsg = "Unable to calculate Remaining TrueUp Time. Start: " + strRemainingTrueUpTimeStart + ", Increment: " + strMeterIncrementTime;
	        System.err.println(errorMsg);
	        e.printStackTrace();
	        // Your existing error reporter
	        new Meter().UpdateErrorMessageWithPivotalData(objDictionary, driver, errorMsg);
	        return 0; // or throw a custom exception
	    }
	}
	public int METER_CalculateUsedTime(Map<String, String> objDictionary, WebDriver driver, String strUsedTimeStart)
	{
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intUsedTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strTimeStop = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("CalculatedUsedTime-strTimeStop ("+strTimeStop+")");
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
		try
		{
			long d1=formater.parse(strUsedTimeStart).getTime();
	    	long d2=formater.parse(strTimeStop).getTime();
	    	long lUsedFreeSeconds = TimeUnit.MILLISECONDS.toSeconds(d2-d1);
	    	intUsedTime = (int) Math.round(lUsedFreeSeconds / 60.00);
    	}catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intTimeRemaining");}
		return intUsedTime;
	}
	public int METER_CalculateUsedTimeCeil(Map<String, String> objDictionary, WebDriver driver, String strUsedTimeStart)
	{
		Meter clsMeter = new Meter();
		//Calculate Remaining Free Time
	  	int intUsedTime = 0;
	  	Calendar calendar2 = Calendar.getInstance();
	  	String strTimeStop = new SimpleDateFormat("HH:mm:ss").format(calendar2.getTime());
	  	Reporter.log("CalculatedUsedTime-strTimeStop ("+strTimeStop+")");
	  	SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
		try
		{
			long d1=formater.parse(strUsedTimeStart).getTime();
		    	long d2=formater.parse(strTimeStop).getTime();
		    	long lUsedFreeSeconds = TimeUnit.MILLISECONDS.toSeconds(d2-d1);
		    	intUsedTime = (int) Math.ceil(lUsedFreeSeconds / 60.00);
	    	}catch (Exception e) {clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "Unable to calculate intTimeRemaining");}
		return intUsedTime;
	}

	//********************************************************************************************************
	//Convert Time
	//********************************************************************************************************
	public String METER_ConvertFirtPaymentTimeTo_hmma(Map<String, String> objDictionary)
	{
		String strFirstPaymentTime = objDictionary.get("strFirstPaymentTime");
		String strFirstPaymentTime_hmma = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	    SimpleDateFormat sdf2 = new SimpleDateFormat("h:mm a");
	    try
	    {
    		Date date1 = sdf1.parse(strFirstPaymentTime);
    		strFirstPaymentTime_hmma = sdf2.format(date1);
    	}catch (Exception e)
	    {}
	    return strFirstPaymentTime_hmma;
	}
	public String METER_ConvertSubsequentPaymentTimeTo_hmma(Map<String, String> objDictionary)
	{
		String strSubsequentPaymentTime = objDictionary.get("strSubsequentPaymentTime");
		String strSubsequentPaymentTime_hmma = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	    SimpleDateFormat sdf2 = new SimpleDateFormat("h:mm a");
	    try
	    {
    		Date date1 = sdf1.parse(strSubsequentPaymentTime);
    		strSubsequentPaymentTime_hmma = sdf2.format(date1);
    	}catch (Exception e)
	    {}
	    return strSubsequentPaymentTime_hmma;
	}
	
	public static String convertUtcToLocal(String utcTime) {
        // Parse the date-time string to a LocalDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(utcTime, formatter);

        // Convert LocalDateTime to ZonedDateTime in UTC
        ZonedDateTime utcZonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        // Convert UTC to local time zone
        ZonedDateTime localZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());

        // Format the ZonedDateTime to a string
        String localTime = localZonedDateTime.format(formatter);

        // Print the result
        return localTime;
    }
	
	 public static String calculateTimeDifference(String time1, String time2) {
        // Define the date-time formatter with the appropriate pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);

        Duration duration2 = Duration.between(dateTime2, dateTime1);
        long minutes1 = Math.round(duration2.toMinutes());
        // Calculate the duration between the two times
        Duration duration = Duration.between(dateTime1, dateTime2);

        // Convert the duration to total minutes and round to the nearest whole number
        long minutes = Math.round(duration.toMinutes());
        return Long.toString(minutes);
    }
	//********************************************************************************************************
	//Validate Meter
	//********************************************************************************************************
	public void METER_ValidateExpectedMeterTime(Map<String, String> objDictionary, WebDriver driver, int intExpectedRemainingTimeMinutes, String strSpotNumber)
	{
		Meter clsMeter = new Meter();
		//Dictionary Variables
  		String strVirtualMeter = objDictionary.get("strVirtualMeter"); if(strVirtualMeter == null) {strVirtualMeter = "False";}
		if(strVirtualMeter.equals("False"))
  		{
			String strMeterValidTimeRemaining = clsMeter.GetMeterValidTimeRemaining(objDictionary,driver, strSpotNumber);
			int intActualRemainingTimeMinutes = (int) Math.round(Double.parseDouble(strMeterValidTimeRemaining) / 60.00);
			int intExpectedRemainingTimeMinutesPlusOne = intExpectedRemainingTimeMinutes + 1;
		  	int intExpectedRemainingTimeMinutesMinusOne = intExpectedRemainingTimeMinutes - 1;
		  	if(intActualRemainingTimeMinutes == intExpectedRemainingTimeMinutesPlusOne)
		  	{
		  		Reporter.log("The Actual Meter Time Equalled the Expected Meter Time ("+intExpectedRemainingTimeMinutesPlusOne+")-PlusOne");
		  	}
		  	else if(intActualRemainingTimeMinutes == intExpectedRemainingTimeMinutes)
		  	{
		  		Reporter.log("The Actual Meter Time Equalled the Expected Meter Time ("+intExpectedRemainingTimeMinutes+")-Actual");
		  	}
		  	else if (intActualRemainingTimeMinutes == intExpectedRemainingTimeMinutesMinusOne)
		  	{
		  		Reporter.log("The Actual Meter Time Equalled the Expected Meter Time ("+intExpectedRemainingTimeMinutesMinusOne+")-MinusOne");
		  	}
		  	else
		  	{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver, "The Actual Meter Time ("+intActualRemainingTimeMinutes+") was not equal to expected meter time ("+intExpectedRemainingTimeMinutes+")");}
		  	objDictionary.put("strActualRemainingTimeMinutes", Integer.toString(intActualRemainingTimeMinutes));
		  	Reporter.log("strActualRemainingTimeMinutes equaled "+intActualRemainingTimeMinutes);
  		}
		else
		{
			objDictionary.put("strActualRemainingTimeMinutes", Integer.toString(intExpectedRemainingTimeMinutes));
		}
	}
	public void METER_ValidateMeterLevelMaintenanceMode(Map<String, String> objDictionary, WebDriver driver, String strMaintenanceMode, String strSpot1Value, String strSpot2Value)
  	{
  		Meter clsMeter = new Meter();
  		//Validate Maintenance Mode
  		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
  		if(strNumberOfSpots.equals("1"))
		{
  			clsMeter.METER_ValidateSpotMaintenanceMode(objDictionary, driver, strMaintenanceMode, "1", strSpot1Value);
		}
		else
		{
			clsMeter.METER_ValidateSpotMaintenanceMode(objDictionary, driver, strMaintenanceMode, "1", strSpot1Value);
			clsMeter.METER_ValidateSpotMaintenanceMode(objDictionary, driver, strMaintenanceMode, "2", strSpot2Value);
		}
  	}
	public void METER_ValidateSpotLevelMaintenanceMode(Map<String, String> objDictionary, WebDriver driver, String strMaintenanceMode, String strSpotNumber, String strSpotValue)
  	{
  		Meter clsMeter = new Meter();
  		//Validate Maintenance Mode
  		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver,"Local");
  		if(strNumberOfSpots.equals("1"))
		{
  			clsMeter.METER_ValidateSpotMaintenanceMode(objDictionary, driver, strMaintenanceMode, strSpotNumber, strSpotValue);
		}
		else
		{
			clsMeter.METER_ValidateSpotMaintenanceMode(objDictionary, driver, strMaintenanceMode, strSpotNumber, strSpotValue);
		}
  	}
	public void METER_ValidateSpotMaintenanceMode(Map<String, String> objDictionary, WebDriver driver, String strMaintenanceMode, String strSpotNumber, String strExpectedMode)
    {
  		Meter clsMeter = new Meter();
  		String strMaintenanceModeValue = "";
  		strMaintenanceModeValue = METER_GetMaintenanceModeStatus(objDictionary,driver,strSpotNumber,strMaintenanceMode);
  		if(strMaintenanceModeValue.equals(strExpectedMode))
		{Reporter.log("The ("+strMaintenanceMode+") Maintenance Mode for Meter (Spot "+strSpotNumber+") was set to ("+strExpectedMode+")");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The ("+strMaintenanceMode+") Maintenance Mode for Meter (Spot "+strSpotNumber+") was not set to ("+strExpectedMode+")");}
    }


	//********************************************************************************************************
	//Meter Performance Attributes
	//********************************************************************************************************
	public String[] METER_GetMemory(Map<String, String> objDictionary,Session sessionMeter,String strHostType)
	{
		String[] arrMemoryValues = new String[3];
		try
	    {
			Channel channel=sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("free");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	int i=in.read(tmp, 0, 1200);
        		if(i<0) {
					break;
				}
        		String strOutput = new String(tmp, 0, i);
        		String[] arrLines = strOutput.split("\n");
        		Reporter.log("****************METER MEMORY******************");
        		String[] arrRow1Columns = arrLines[0].split("\\s+");
        		String[] arrRow2Columns = arrLines[1].split("\\s+");
        		Reporter.log(arrRow1Columns[1] + "\t\t" + arrRow1Columns[2] + "\t\t" +arrRow1Columns[3]);
        		Reporter.log(arrRow2Columns[1] + "\t\t" + arrRow2Columns[2] + "\t\t" +arrRow2Columns[3]);
        		arrMemoryValues[0] = arrRow2Columns[1];
        		arrMemoryValues[1] = arrRow2Columns[2];
        		arrMemoryValues[2] = arrRow2Columns[3];
        		if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();
	    }catch(Exception e){}
		return arrMemoryValues;
    }
	public String[] METER_GetMemory(Map<String, String> objDictionary, String strHostType)
	{
		JSch jsch = new JSch();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}else{strHost = objDictionary.get("strRemoteHost");}
		String[] arrMemoryValues = new String[3];
		try
	    {
			Session session = jsch.getSession(strMeterUser, strHost, port);
			session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("free");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	int i=in.read(tmp, 0, 1200);
        		if(i<0) {
					break;
				}
        		String strOutput = new String(tmp, 0, i);
        		String[] arrLines = strOutput.split("\n");
        		Reporter.log("****************METER MEMORY******************");
        		String[] arrRow1Columns = arrLines[0].split("\\s+");
        		String[] arrRow2Columns = arrLines[1].split("\\s+");
        		Reporter.log(arrRow1Columns[1] + "\t\t" + arrRow1Columns[2] + "\t\t" +arrRow1Columns[3]);
        		Reporter.log(arrRow2Columns[1] + "\t\t" + arrRow2Columns[2] + "\t\t" +arrRow2Columns[3]);
        		arrMemoryValues[0] = arrRow2Columns[1];
        		arrMemoryValues[1] = arrRow2Columns[2];
        		arrMemoryValues[2] = arrRow2Columns[3];
        		if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }catch(Exception e){}
		return arrMemoryValues;
    }


	public void METER_GetUptime(Map<String, String> objDictionary,Session sessionLocalMeter,Session sessionRemoteMeter,String strHostType,String strActionName)
	{
		Database clsDatabase = new Database();
		String strHost = "";
		String strMeterType = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterType = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterType = objDictionary.get("strRemoteUser");}
		//String[] arrMemoryValues = METER_GetMemory(objDictionary, strHostType);
		try
	    {
			Channel channel=sessionLocalMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("uptime");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	int i=in.read(tmp, 0, 1200);
        		if(i<0) {
					break;
				}
        		String strOutput = new String(tmp, 0, i);
        		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
 				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
 				String strUTC = dateFormatGmt.format(new Date());
        		String strLoadValues = strOutput.substring(strOutput.indexOf("load average: ")+14,strOutput.length());
        		String[] arrLoadValues = strLoadValues.split(",");
        		String strInstantValue = arrLoadValues[0];
        		String str5MinAvg = arrLoadValues[1];
        		String str15MinAvg = arrLoadValues[2].trim();
        		Reporter.log("<font color='#00AEFF'>Local UPTIME: "+strHostType+" "+strHost+" "+strMeterType+": Instant Value-"+strInstantValue+", 5 Min Avg-"+str5MinAvg+", 15 Min Avg-"+str15MinAvg+" UTC:"+strUTC+"</font>");
        		clsDatabase.WriteToMeterLoadDatabase(objDictionary,strActionName, strHost, strUTC, strInstantValue, str5MinAvg, str15MinAvg, strMeterType) ;
        		if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();
	    }
		catch(Exception e){}
		if(sessionRemoteMeter != null)
		{
			try
		    {
				Channel channel=sessionRemoteMeter.openChannel("exec");
		        ((ChannelExec)channel).setCommand("uptime");
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        byte[] tmp=new byte[1200];
		        while(true)
		        {
		        	int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	 				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	 				String strUTC = dateFormatGmt.format(new Date());
	        		String strLoadValues = strOutput.substring(strOutput.indexOf("load average: ")+14,strOutput.length());
	        		String[] arrLoadValues = strLoadValues.split(",");
	        		String strInstantValue = arrLoadValues[0];
	        		String str5MinAvg = arrLoadValues[1];
	        		String str15MinAvg = arrLoadValues[2].trim();
	        		Reporter.log("<font color='#00AEFF'>Remote UPTIME: "+strHostType+" "+strHost+" "+strMeterType+": Instant Value-"+strInstantValue+", 5 Min Avg-"+str5MinAvg+", 15 Min Avg-"+str15MinAvg+" UTC:"+strUTC+"</font>");
	        		clsDatabase.WriteToMeterLoadDatabase(objDictionary,strActionName, strHost, strUTC, strInstantValue, str5MinAvg, str15MinAvg, strMeterType) ;
	        		if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
		        }
			    channel.disconnect();
		    }
			catch(Exception e){}
		}
    }
	public void METER_GetUptime(Map<String, String> objDictionary, String strHostType, String strActionName)
	{
		JSch jsch = new JSch();
		Database clsDatabase = new Database();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		String strHost = "";
		String strMeterType = "";
		if(strHostType.equals("Local"))
		{strHost = objDictionary.get("strHost");strMeterType = objDictionary.get("strMeterUser");}
		else
		{strHost = objDictionary.get("strRemoteHost");strMeterType = objDictionary.get("strRemoteUser");}
		//String[] arrMemoryValues = METER_GetMemory(objDictionary, strHostType);
		try
	    {
			Session session = jsch.getSession(strMeterUser, strHost, port);
			session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("uptime");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	int i=in.read(tmp, 0, 1200);
        		if(i<0) {
					break;
				}
        		String strOutput = new String(tmp, 0, i);
        		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
 				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
 				String strUTC = dateFormatGmt.format(new Date());
        		String strLoadValues = strOutput.substring(strOutput.indexOf("load average: ")+14,strOutput.length());
        		String[] arrLoadValues = strLoadValues.split(",");
        		String strInstantValue = arrLoadValues[0];
        		String str5MinAvg = arrLoadValues[1];
        		String str15MinAvg = arrLoadValues[2].trim();
        		Reporter.log("<font color='#00AEFF'>UPTIME: "+strHostType+" "+strHost+" "+strMeterType+": Instant Value-"+strInstantValue+", 5 Min Avg-"+str5MinAvg+", 15 Min Avg-"+str15MinAvg+" UTC:"+strUTC+"</font>");
        		clsDatabase.WriteToMeterLoadDatabase(objDictionary,strActionName, strHost, strUTC, strInstantValue, str5MinAvg, str15MinAvg, strMeterType) ;
        		if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
		catch(Exception e){}
    }


	public String METER_GetTOP(Map<String, String> objDictionary, String strHost, String strActionName)
	{
		JSch jsch = new JSch();
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		String strOutput = "";
		String strMeterType = "";
		//String[] arrMemoryValues = METER_GetMemory(objDictionary, strHostType);
		try
	    {
			Session session = jsch.getSession(strMeterUser, strHost, port);
			session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("top -n1");
	        channel.setInputStream(null);
	        //(≠).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	int i=in.read(tmp, 0, 1200);
        		if(i<0) {
					break;
				}
        		strOutput = new String(tmp, 0, i);

	        }
		    channel.disconnect();session.disconnect();
	    }
		catch(Exception e)
		{
			System.out.println(e);
		}
		return strOutput;
    }

	//Used for Message Logging
	public void METER_KillMessageTraceLogProcess(Map<String, String> objDictionary,WebDriver driver)
  	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		if(strHost != null)
		{
			String strPassword = objDictionary.get("strUniquePassword");
			String strMeterUser = objDictionary.get("strMeterUser");int port=22;
			if(strMeterUser.equals("Yocto")) {strMeterUser = "root";}
			try
			{
				Session session = jsch.getSession(strMeterUser, strHost, port);
				session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("ps | grep \"tail -f /var/lib/sentry/messages\" | grep -v grep | awk '{print $1}' | xargs kill -9");
		        channel.connect();
		        Reporter.log("<font color='orange'>Tracelog Process Where Ended</font>");
		    }
		    catch
		    (Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The script (nohup tail -f Xsession.log > /tmp/Tracelog.txt &) failed to execute ("+strHost+")");}
		}
    }
	public void METER_StartMessageLogTrace(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		if(strHost != null)
		{
			String strPassword = objDictionary.get("strUniquePassword");
			String strMeterUser = objDictionary.get("strMeterUser");
			if(strMeterUser.equals("Yocto")){strMeterUser = "root";}
			int port=22;
	    	try
	    	{
		    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    	session.setPassword(strPassword);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        Channel channel=session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("/usr/bin/nohup tail -f /var/lib/sentry/messages > /tmp/MessageTracelog.txt &");
		        channel.connect();
		        Reporter.log("<font color='orange'>Message Tracelog Started</font>");
	    	}
		    catch
		    (Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary, driver ,"The script (nohup tail -f Xsession.log > /tmp/Tracelog.txt &) failed to execute ("+strHost+")");}
		}
    }
	public void METER_CopyMessageTraceLocally(Map<String, String> objDictionary, String strMethodName) throws Exception
	{
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		File directory = new File(".");
		String strPath = directory.getCanonicalPath() +"/MessageLogs";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir())
			{System.out.println("Directory is created!");}
			else
			{System.out.println("Failed to create directory!");}
		}
		String strFullMeterLogsPath = strPath+"/"+strMethodName+".txt";
		String strMeterUser = objDictionary.get("strMeterUser");
		if(strMeterUser.equals("root"))
		{
			System.out.println("sshpass -p "+strPassword+" scp -r root@"+strHost+":/tmp/MessageTracelog.txt "+strFullMeterLogsPath);
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r root@"+strHost+":/tmp/MessageTracelog.txt "+strFullMeterLogsPath};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{
					System.out.println(strErrorMessage+"-"+strMethodName);
				}
			}
			catch (IOException e){e.printStackTrace();}
		}
		else
		{
			//Local
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r seco@"+strHost+":/tmp/MessageTracelog.txt "+strFullMeterLogsPath};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{System.out.println(strErrorMessage+"-"+strMethodName);}
			}
			catch (IOException e){e.printStackTrace();}
		}
	}
	public static void printStream(InputStream is, String type)
	{
		try
		   {
		      InputStreamReader isr = new InputStreamReader(is);
		      BufferedReader br = new BufferedReader(isr);
		      String line=null;
		      while ( (line = br.readLine()) != null)
		            System.out.println(type + ">" + line);
		   } catch (IOException ioe){
		           ioe.printStackTrace();
		   }
	}
	public void METER_GetTestCaseMeterLogs(Map<String, String> objDictionary, String strMeterStartTime, String strMeterEndTime, String strMethodName)
	{
		//ReadLogTrac

		//sdfs

		//This Doesn't currently work

		System.out.println(strMeterStartTime);
		System.out.println(strMeterEndTime);

		//String strMeterStartTime = objDictionary.get("strMeterStartTime");
		//String strMeterEndTime = objDictionary.get("strMeterEndTime");
		String strMeterLogCommand = "sed -n '/"+strMeterStartTime+"/,/"+strMeterEndTime+"/p' /var/log/Xsession.log";
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	int port=22;
	    try
	    {
		    	Session session = jsch.getSession(strMeterUser, host, port);
		    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(strMeterLogCommand);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1147483647];
	        while(true)
	        {
		        	while(in.available() != 0)
		        	{
		        		//int i=in.read(tmp, -2147483647, 2147483647);
		        		int i=in.read(tmp, 0, 1147483647);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		try
		                {
		        			File directory = new File(".");
			        		String strPath = directory.getCanonicalPath() +"/MeterLogs";
		        			//Check if MeterLogs Folder Exists
			        		File file = new File(strPath);
			        		if (!file.exists())
			        		{
			        			if (file.mkdir())
			        			{System.out.println("Directory is created!");}
			        			else
			        			{System.out.println("Failed to create directory!");}
			        		}
			        		file = new File(strPath+"/"+strMethodName+".txt");
			        		if(file.delete())
			        		{System.out.println(file.getName() + " is deleted!");}
			        		else{System.out.println("Delete operation is failed.");}
		        			//Create Logs
		        		    File statText = new File(strPath+"/"+strMethodName+".txt");
		                    FileOutputStream is = new FileOutputStream(statText);
		                    OutputStreamWriter osw = new OutputStreamWriter(is);
		                    Writer w = new BufferedWriter(osw);
		                    w.write(strOutput);
		                    w.close();
		                    return;
		                }
		                catch (IOException e)
		                {
		                    System.err.println("Problem writing to the file statsTest.txt");
		                }
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    	catch(Exception e)
	    {
	    		System.out.println(e);
	    }
//	    if (strPythonScriptExisted == "False")
//	    {
//	    	String strErrorMsg = UpdateErrorMessageWithPivotalData(objDictionary,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");
//         	Reporter.log("<font color='red'>"+strErrorMsg+"</font>"); Assert.fail(strErrorMsg);
//	    }
    }
	public void METER_GetTestCaseMeterMessages(Map<String, String> objDictionary, String strMethodName)
	{
		//String strMethod = new Object(){}.getClass().getEnclosingMethod().getName();
		String strMeterStartTime = objDictionary.get("strMeterStartTime");
		String strMeterEndTime = objDictionary.get("strMeterEndTime");
		String strMeterLogCommand = "sed -n '/"+strMeterStartTime+"/,/"+strMeterEndTime+"/p' /var/lib/sentry/messages";
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
		String host = strHost;
    	//String strPythonScriptExisted = "False";
    	int port=22;
	    try
    	{
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(strMeterLogCommand);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1000000000];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		//strPythonScriptExisted = "True";
	        		int i=in.read(tmp, 0, 1000000000);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		try
	                {
	        			File directory = new File(".");
		        		String strPath = directory.getCanonicalPath() +"/MessageLogs";
	        			//Check if MeterLogs Folder Exists
		        		File file = new File(strPath);
		        		if (!file.exists())
		        		{
		        			if (file.mkdir())
		        			{System.out.println("Directory is created!");}
		        			else
		        			{System.out.println("Failed to create directory!");}
		        		}
	        			//Create Logs
	        		    File statText = new File(strPath+"/"+strMethodName+".txt");
	                    FileOutputStream is = new FileOutputStream(statText);
	                    OutputStreamWriter osw = new OutputStreamWriter(is);
	                    Writer w = new BufferedWriter(osw);
	                    w.write(strOutput);
	                    w.close();
	                    break;
	                }
	                catch (IOException e)
	                {
	                    System.err.println("Problem writing to the file statsTest.txt");
	                }
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
    	}
	    catch(Exception e)
	    {System.out.println(e);}
//	    if (strPythonScriptExisted == "False")
//	    {
//	    	String strErrorMsg = UpdateErrorMessageWithPivotalData(objDictionary,"The command ("+strMeterLogCommand+") failed-"+strMethod);
//         	Reporter.log("<font color='red'>"+strErrorMsg+"</font>"); Assert.fail(strErrorMsg);
//	    }
    }


	//**************************************************************************************************************************************************************************************************************/
	//
	//**************************************************************************************************************************************************************************************************************/
	public String METER_CheckIfReservationExists(Map<String, String> objDictionary,Session sessionMeter,String strHostType, String strSpotNumber)
	{
		String strViolationValue = "";
		String strPythonScriptExisted = "False";
		try
	    {
	    	Channel channel = sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_reservations.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
	        {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strViolationValue = line.substring(line.indexOf("Violation,")+10, line.indexOf("|Unlocked"));
	        		break;
	        	}
	        }
	        in.close();
	        channel.disconnect();
	        Reporter.log("Dump Session Violation Status: "+strViolationValue);
	        return strViolationValue;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {
	    	UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter -ssh into the meter","False");
	    }
	    return strViolationValue;
	}
	//**********************************************************************************************************************
	//PERMITS & RESERVATIONS
	//**********************************************************************************************************************
	public String METER_CheckIfReservationExists(Map<String, String> objDictionary,String strHostType, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strReservationExists = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_reservations.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        if(s.contains("Reservation list is: []")){strReservationExists = "False";}
	        else{strReservationExists = "True";}
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        Reporter.log("Reservation Exists ("+strSpotNumber+") equals: "+strReservationExists);
	        return strReservationExists;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False") {UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_reservations.py) didn't exist or needs to be updated on the meter -ssh into the meter");}
	    return strReservationExists;
    }
	public String METER_CheckIfPermitExists(Map<String, String> objDictionary,String strHostType, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strReservationExists = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_reservations.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        if(s.contains("Reservation list is: []")){strReservationExists = "False";}
	        else{strReservationExists = "True";}
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        Reporter.log("Reservation Exists ("+strSpotNumber+") equals: "+strReservationExists);
	        return strReservationExists;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False") {UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_reservations.py) didn't exist or needs to be updated on the meter -ssh into the meter");}
	    return strReservationExists;
    }
	public String METER_GetPermitId(Map<String, String> objDictionary,String strHostType, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = "";
		String strPermitId = "";
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_reservations.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        strPermitId = s.substring(s.indexOf("u'")+2, s.indexOf("-"));
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        Reporter.log("The Permit Id ("+strSpotNumber+") equals: "+strPermitId);
	        return strPermitId;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False") {UpdateErrorMessageWithPivotalData(objDictionary,null,"The python script (testauto_dump_reservations.py) didn't exist or needs to be updated on the meter -ssh into the meter");}
	    return strPermitId;
    }
	
	//**************************************************************************************************************************************************************************************************************/
	//Get Meter Violation Value
	//**************************************************************************************************************************************************************************************************************/
	public String METER_GetMeterViolationValue(Map<String, String> objDictionary,Session sessionMeter,String strHostType, String strSpotNumber)
	{
		String strViolationValue = "";
		String strPythonScriptExisted = "False";
		try
	    {
	    	Channel channel = sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
	        {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strViolationValue = line.substring(line.indexOf("Violation,")+10, line.indexOf("|Unlocked"));
	        		break;
	        	}
	        }
	        in.close();
	        channel.disconnect();
	        Reporter.log("Dump Session Violation Status: "+strViolationValue);
	        return strViolationValue;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {
	    	UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter -ssh into the meter","False");
	    }
	    return strViolationValue;
	}
	public String METER_GetMeterViolationValue(Map<String, String> objDictionary,String strHostType, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else{strHost = objDictionary.get("strRemoteHost");}
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		String strPythonScriptExisted = "False";
		String strViolation = "False";
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		strPythonScriptExisted = "True";
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		String strSpotVariables = "";
	        		switch (strSpotNumber)
        			{
        				case "1":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
        					break;
        				case "2":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.indexOf("SPOT_1"));
        					break;
        			}
	        		System.out.println("Violation Status: "+strSpotVariables.substring(strSpotVariables.indexOf("Violation,")+10, strSpotVariables.indexOf("|Unlocked")));
	        		strViolation = strSpotVariables.substring(strSpotVariables.indexOf("Violation,")+10, strSpotVariables.indexOf("|Unlocked"));
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary, null, "The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return strViolation;
    }
	//**************************************************************************************************************************************************************************************************************/

	//**************************************************************************************************************************************************************************************************************/
	//Get Meter True Up Time
	//**************************************************************************************************************************************************************************************************************/
	public String METER_GetMeterTrueUpTimeValue(Map<String, String> objDictionary,Session sessionMeter,String strHostType, String strSpotNumber)
	{
		String strTrueUpTimeValue = "";
		String strPythonScriptExisted = "False";
		try
	    {
	    	Channel channel = sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
	        {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strTrueUpTimeValue = line.substring(line.indexOf("Trup,")+5, line.indexOf("|TrupT"));
	        		break;
	        	}
	        }
	        in.close();
	        channel.disconnect();
	        Reporter.log("Dump Session True Up Time: "+strTrueUpTimeValue);
	        return strTrueUpTimeValue;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {
	    	UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter -ssh into the meter","False");
	    }
	    return strTrueUpTimeValue;
	}
	public String METER_GetMeterTrueUpValue(Map<String, String> objDictionary,String strHostType, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else{strHost = objDictionary.get("strRemoteHost");}
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		String strPythonScriptExisted = "False";
		String strTrueUpValue = "";
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		strPythonScriptExisted = "True";
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		String strSpotVariables = "";
	        		switch (strSpotNumber)
        			{
        				case "1":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
        					break;
        				case "2":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.indexOf("SPOT_1"));
        					break;
        			}
	        		System.out.println("True Up Time: "+strSpotVariables.substring(strSpotVariables.indexOf("Trup,")+5, strSpotVariables.indexOf("|TrupT")));
	        		strTrueUpValue = strSpotVariables.substring(strSpotVariables.indexOf("Trup,")+5, strSpotVariables.indexOf("|TrupT"));
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary, null, "The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return strTrueUpValue;
    }
	//**************************************************************************************************************************************************************************************************************/

	//**************************************************************************************************************************************************************************************************************/
	//Get Meter True Up Time
	//**************************************************************************************************************************************************************************************************************/
	public String METER_GetMeterTrueUpTotalTimeValue(Map<String, String> objDictionary,Session sessionMeter,String strHostType, String strSpotNumber)
	{
		String strViolationValue = "";
		String strPythonScriptExisted = "False";
		try
	    {
	    	Channel channel = sessionMeter.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(Arrays.toString(lines));
	        for (String line : lines)
	        {
	        	//System.out.println(line);
	        	if(line.contains("SPOT_"+strSpotNumber+"|"))
	        	{
	        		//System.out.println(line);
	        		strViolationValue = line.substring(line.indexOf("Violation,")+10, line.indexOf("|Unlocked"));
	        		break;
	        	}
	        }
	        in.close();
	        channel.disconnect();
	        Reporter.log("Dump Session True Up Time: "+strViolationValue);
	        return strViolationValue;
	    }
	    catch(Exception e){System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {
	    	UpdateErrorMessageWithPivotalData(objDictionary,null,sessionMeter,"The python script (testauto_dump_sessions.py) didn't exist or needs to be updated on the meter -ssh into the meter","False");
	    }
	    return strViolationValue;
	}
	public String METER_GetMeterTrueUpTotalValue(Map<String, String> objDictionary,String strHostType, String strSpotNumber)
	{
		JSch jsch = new JSch();
		String strHost = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");}
		else{strHost = objDictionary.get("strRemoteHost");}
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");int port=22;
		String strPythonScriptExisted = "False";
		String strTrueUpTotalTime = "False";
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		strPythonScriptExisted = "True";
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		String strSpotVariables = "";
	        		switch (strSpotNumber)
        			{
        				case "1":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
        					break;
        				case "2":
        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.indexOf("SPOT_1"));
        					break;
        			}
	        		System.out.println("True Up Total Time: "+strSpotVariables.substring(strSpotVariables.indexOf("|TrupT")+7, strSpotVariables.length()));
	        		strTrueUpTotalTime = strSpotVariables.substring(strSpotVariables.indexOf("|TrupT,")+7, strSpotVariables.length());
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary, null, "The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return strTrueUpTotalTime;
    }
	//**************************************************************************************************************************************************************************************************************/



	public String GetMeterNextRateCost(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		System.out.println(strOutput);
        			strOutput = strOutput.substring(strOutput.indexOf("NextRateCost,")+13, strOutput.indexOf("|granted"));
        			return strOutput.replace(".0", "");
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
	        channel.disconnect();session.disconnect();
    	}catch(Exception e){}
	    return "False";
    }
	public String GetMeterTimeIncrementValue(Map<String, String> objDictionary,WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strNumberOfMeterSpots = objDictionary.get("strNumberOfMeterSpots");
		String strMeterUser = objDictionary.get("strMeterUser");
    	String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	String strPythonScriptExisted = "False";
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        String strSpotVariables = lines[0];
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	        String strOutput = strSpotVariables.substring(strSpotVariables.indexOf("granted,")+8, strSpotVariables.indexOf("|srbs_used"));
			System.out.println("MeterTimeIncrementValue: "+strOutput);
	        return strOutput.replace(".0", "");
	    }
	    catch(Exception e)
	    {System.out.println(e);}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary, driver, "The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }
	public String GetMeterTimeIncrementValue_old(Map<String, String> objDictionary, WebDriver driver)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
    	String strPythonScriptExisted = "False";
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		strPythonScriptExisted = "True";
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
	        			strOutput = strOutput.substring(strOutput.indexOf("granted,")+8, strOutput.indexOf("|srbs_used"));
	        			return strOutput.replace(".0", "");
		        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
    		}catch(Exception e){}
	    if (strPythonScriptExisted == "False")
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
        return "False";
    }
	public String GetMeterRemainingTimeSeconds(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
	    try
    	{
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
	        	while(in.available()>0)
	        	{
	        		int i=in.read(tmp, 0, 1200);
	        		if(i<0) {
						break;
					}
	        		String strOutput = new String(tmp, 0, i);
	        		System.out.println(strOutput);
	        		String strSpotVariables = "";
	        		while (true)
	        		{
	        			switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.indexOf("SPOT_1"));
	        					break;

	        			}
	        			return strSpotVariables.substring(strSpotVariables.indexOf("ValidTimeRemaining,")+19, strSpotVariables.indexOf("|Violation"));
        			}
	        	}
	        	if(channel.isClosed()){if(in.available()>0) {
					continue;
				}break;}
	        }
		    channel.disconnect();session.disconnect();
    	}catch(Exception e){}
	    return "0";
    }
	public void StoreMeterMaxDurationMinutesAndMeterCardIncrementTime(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[2048];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 2048);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		int sBlockType = 0;int sBlockId = 0;int sMaximumDurationMinutes = 0;
		        		int sPriority = 0;int sCardIncrementTime = 0;int sEndOfRow = 0;
		        		int sSchedule = 0;
		        		while (true)
		        		{
			        		int intBlockType = strOutput.indexOf("block_type", sBlockType);
			        		int intBlockId = strOutput.indexOf("block_id", sBlockId);
			        		int intMaximumDurationMinutes = strOutput.indexOf("maximum_duration_minutes", sMaximumDurationMinutes);
			        		int intPriority = strOutput.indexOf("priority", sPriority);
			        		int intCardIncrementTime = strOutput.indexOf("card_increment_time", sCardIncrementTime);
			        		int intSchedule = strOutput.indexOf("schedule", sSchedule);
			        		int intEndOfRow = strOutput.indexOf("}q", sEndOfRow);
			        		if (intBlockType != -1)
			        		{
			        			String strBlockType = strOutput.substring(intBlockType+14, intBlockId-4);
			        		    if(strBlockType.contains("fixed"))
			        		    {
			        		    	System.out.println(strOutput);
			        		    	//String strMeterMaxDurationMinutes = strOutput.substring(intMaximumDurationMinutes+27, intPriority-3);
			        		    	String strMeterMaxDurationMinutes = strOutput.substring(intMaximumDurationMinutes+27, intEndOfRow);
			        		    	objDictionary.remove("strMeterMaxDurationMinutes");objDictionary.put("strMeterMaxDurationMinutes", strMeterMaxDurationMinutes);
			        		    	Reporter.log("The meter maximum_duration_minutes value (" + strMeterMaxDurationMinutes + ") was stored as variable name (strMeterMaxDurationMinutes)"+"");
			        		    	//String strMeterCardIncrementMinutes = strOutput.substring(intCardIncrementTime+22, intEndOfRow);
			        		    	String strMeterCardIncrementMinutes = strOutput.substring(intCardIncrementTime+22, intSchedule-3);
			        		    	objDictionary.remove("strMeterCardIncrementMinutes");objDictionary.put("strMeterCardIncrementMinutes", strMeterCardIncrementMinutes);
			        		    	Reporter.log("The meter card_increment_time (minutes) value (" + strMeterCardIncrementMinutes + ") was stored as variable name (strMeterCardIncrementMinutes)"+"");
			        		    	break;
			        		    }
			        		}
			        		if (intBlockType == -1) {
								break;
							}
			        		sBlockType = intBlockType + 2; sBlockId = intBlockId + 2;
			        		sMaximumDurationMinutes = intMaximumDurationMinutes + 2;sPriority = intPriority + 2;
			        		sCardIncrementTime = intCardIncrementTime + 2;sEndOfRow = intEndOfRow + 2;
			        		sSchedule = intSchedule + 2;
		        		}
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }catch(Exception e){}
    }
	//
	//*******************************************************************************************************************************************************************************************************************
	//Global Wait
	//*******************************************************************************************************************************************************************************************************************
	public void GlobalWait(Map<String, String> objDictionary,WebDriver driver, Session sessionMeter,String strWaitObjects,int intWaitTime,String strSpotNumber,String strHostType)
	{
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		String strObjectValue2 = "";
		String strTimeoutFlag = "False";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intWaitTime);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
        do
        {
            String[] arrWaitObjects = strWaitObjects.split("\\|");
            for (String WaitObject : arrWaitObjects)
            {
                String[] arrWaitObject = WaitObject.split("}");
                String strWaitType = arrWaitObject[0].substring(1);
                String[] arrWaitValue = arrWaitObject[1].trim().split("~");
                int intWaitValueLenght = arrWaitValue.length;
                String strWaitValue = arrWaitValue[0];
                if (intWaitValueLenght == 2) {strObjectValue2 = arrWaitValue[1];}
                strActualWaitType = strWaitType;
                strActualWaitValue = strWaitValue;
                WebElement objName = null;
                String strBegunVariable = "";
                String strFreeVariable = "";
                String strCoinAcceptorValue = "";
                switch (strWaitType)
                {
                		case "WaitUntilFreeParkingBlockExists":
                			String strFreeRateBlockExists = CheckIfFreeParkingBlockExists(objDictionary);
	                		if(strFreeRateBlockExists.equals("True"))
	                		{
	                			Reporter.log("GlobalWait: FreeParkingBlockExisted");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting until a Free Parking Rate Exists"+strSpotNumber);}
	                		break;
	                	case "WaitUntilARateBlockIdIsNotNeg1": //New Rate Set Exists
	                		String strRateBlockExistFlag = CheckIfRateBlockIdIsNotNeg1(objDictionary,strHostType);
	                		if(strRateBlockExistFlag.equals("True"))
	                		{
	                			Reporter.log("GlobalWait: Rate Block values existed that were not -1");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
//	                		else
//	                		{System.out.println("Waiting until a Rate BlockId Is Not Negative-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilARateBlockIdIstNeg1"://Rate Sets Are Deleted
	                		String strRateBlockExistFlag2 = CheckIfRateBlockIdIsNotNeg1(objDictionary,strHostType);
	                		if(strRateBlockExistFlag2.equals("False"))
	                		{
	                			Reporter.log("GlobalWait: Rate Block values existed that were not -1");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting until a Rate BlockId Is Not Negative-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilMeterCoinAcceptorEqualsFalse":
	                		strCoinAcceptorValue = GetMeterCoinAcceptorValue(objDictionary,sessionMeter,strSpotNumber,strHostType);
	                		if(strCoinAcceptorValue.equals("False"))
	                		{
	                			long end = System.currentTimeMillis();
	                			float sec = (end - start) / 1000F;
	                			Reporter.log("GlobalWait: The Coin Acceptor Value was False-"+sec+" sec");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting until Coin Acceptor Equals False-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilMeterCoinAcceptorEqualsTrue":
	                		strCoinAcceptorValue = GetMeterCoinAcceptorValue(objDictionary,sessionMeter,strSpotNumber,strHostType);
	                		if(strCoinAcceptorValue.equals("True"))
	                		{
	                			long end = System.currentTimeMillis();
	                			float sec = (end - start) / 1000F;
	                			Reporter.log("GlobalWait: The Coin Acceptor Value was True-"+sec+" sec");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting until Coin Acceptor Equals True-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilMeterMaxRemainingEqualsTheAmount":
	                		String strMeterMaxRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,sessionMeter,strSpotNumber,strHostType);
	                		System.out.println("strMeterMaxRemaining: "+strMeterMaxRemaining);
	                		if(strMeterMaxRemaining.replace(".0", "").equals(strWaitValue))
	                		{
	                			Reporter.log("GlobalWait: Meter Max Remaining Equals Zero");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting until Coin Acceptor Equals False-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilMeterMaxRemainingEqualsZero":
	                		String strMeterValidTimeRemaining = clsMeter.METER_GetMeterMaxRemaining(objDictionary,sessionMeter,strSpotNumber,strHostType);
	                		if(strMeterValidTimeRemaining.equals("0"))
	                		{
	                			Reporter.log("GlobalWait: Meter Max Remaining Equals Zero");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting until Meter Max Remaining Equal Zero-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilMeterVariableBegunEqualTrue":
	                		if (strVirtualMeter.equals("True")){return;}
	                		else
	                		{
	                			strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,strSpotNumber,strHostType);
	                			if(strBegunVariable.equals("True"))
		                		{
	                				long end = System.currentTimeMillis();float sec = (end - start) / 1000F;
		                			Reporter.log("GlobalWait: The meter variable begun equalled True-"+sec+" sec");strWaitObjects = "";intWaitTime = 30;
		                			return;
	                			}
		                		else
		                		{System.out.println("Waiting for parking session to begin-Spot"+strSpotNumber);}
	                		}
	                		break;
	                	case "WaitUntilMeterVariableBegunEqualFalse":
	                		strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,strSpotNumber,strHostType);
	                		if(strBegunVariable.equals("False"))
	                		{Reporter.log("GlobalWait: The meter variable begun equalled False");strWaitObjects = "";intWaitTime = 30;return;}
	                		else
	                		{System.out.println("Waiting for meter Begin value to equal False-Spot"+strSpotNumber);}
	                		break;
	                	case "WaitUntilMeterFreeEqualsFalse":
	                		strFreeVariable = clsMeter.METER_GetMeterFreeValue(objDictionary,driver,strHostType);
		            		if(!strFreeVariable.equals("True"))
		            		{Reporter.log("GlobalWait: The meter variable free equalled False");strWaitObjects = "";intWaitTime = 30;return;}
		            		else
	                		{System.out.println("Waiting for meter to no longer displays free-Spot"+strSpotNumber);}
		            		break;
	                	case "WaitUntilMeterFreeEqualsTrue":
	                		if (strVirtualMeter.equals("True")){return;}
	                		else
	                		{
	                			strFreeVariable = clsMeter.METER_GetMeterFreeValue(objDictionary,driver,strHostType);
			            		if(strFreeVariable.equals("True"))
			            		{
			            			Reporter.log("GlobalWait: The meter variable free equalled True");
			            			strWaitObjects = "";intWaitTime = 30;return;
			            		}
	                		}
		            		break;
	                	case "WaitUntilMeterInMaintEqualsTrue":
	                		if (strVirtualMeter.equals("True")){return;}
	                		else
	                		{
	                			String strInMaintVariable = clsMeter.METER_GetMeterInMaintValue(objDictionary,sessionMeter,strSpotNumber);
	                			if(strInMaintVariable.equals("True"))
			            		{
			            			Reporter.log("GlobalWait: The meter variable InMaint equalled True");
			            			strWaitObjects = "";intWaitTime = 30;return;
			            		}
			            		else
		                		{System.out.println("Waiting for meter to displays InMaint-Spot"+strSpotNumber);}
	                		}
		            		break;
	                	case "WaitUntilMeterInMaintEqualsFalse":
	                		if (strVirtualMeter.equals("True")){return;}
	                		else
	                		{
	                			String strInMaintVariable = clsMeter.METER_GetMeterInMaintValue(objDictionary,sessionMeter,strSpotNumber);
	                			if(strInMaintVariable.equals("False"))
			            		{
			            			Reporter.log("GlobalWait: The meter variable InMaint equalled False");
			            			strWaitObjects = "";intWaitTime = 30;return;
			            		}
			            		else
		                		{System.out.println("Waiting for meter to displays InMaint-Spot"+strSpotNumber);}
	                		}
		            		break;
	                	case "WaitUntilMeterViolationEqualsTrue":
	                		strFreeVariable = clsMeter.METER_GetMeterViolationValue(objDictionary,sessionMeter,strHostType,strSpotNumber);
	                		if(strFreeVariable.equals("True"))
		            		{
		            			long end = System.currentTimeMillis();float sec = (end - start) / 1000F;
	                			Reporter.log("GlobalWait: The meter violation equalled true-"+sec+" sec");strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		break;
	                	case "WaitUntilMeterViolationEqualsFalse":
	                		strFreeVariable = clsMeter.METER_GetMeterViolationValue(objDictionary,strHostType,strSpotNumber);
		            		if(strFreeVariable.equals("False"))
		            		{
		            			Reporter.log("GlobalWait: The meter variable free equalled False");
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for violation to disappear-spot"+strSpotNumber);}
		            		break;
	                	case "WatiUntilMeterValidTimePurchasedEquals":
	                		String strValidTimePurchased = GetMeterValidTimePurchased(objDictionary,driver,"1",strHostType,"After").replace(".0", "");
	                		if(strValidTimePurchased.equals(strWaitValue))
		            		{
		            			Reporter.log("GlobalWait: The meter valid time purchased equals "+strWaitValue);
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for the meter valid time purchased to equal "+strWaitValue+"-Spot"+strSpotNumber);}
		            		break;
	                	case "WaitUntilMeterNoParkingEqualsFalse":
	                		strFreeVariable = clsMeter.METER_GetMeterNoParkingValue(objDictionary,driver);
		            		if(strFreeVariable.equals("False"))
		            		{
		            			Reporter.log("GlobalWait: The meter variable no parking equalled False");
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{
		            			System.out.println("Waiting for meter to no longer displays no parking-Spot"+strSpotNumber);
		            		}
		            		break;
	                	case "WaitUntilMeterNoParkingEqualsTrue":
	                		strFreeVariable = clsMeter.METER_GetMeterNoParkingValue(objDictionary,driver);
		            		if(strFreeVariable.equals("True"))
		            		{
		            			Reporter.log("GlobalWait: The meter variable no parking equalled True");
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for meter to displays no parking-Spot"+strSpotNumber);}
		            		break;
	                	case "WaitUntilMeterStateEquals":
	                		strFreeVariable = clsMeter.METER_GetMeterStateValue(objDictionary,driver);
		            		if(strFreeVariable.equals(strWaitValue))
		            		{
		            			Reporter.log("GlobalWait: The meter variable State equalled "+strWaitValue);
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for meter state to displays "+strWaitValue+" "+strSpotNumber);}
		            		break;
		            	case "WaitUntilParkTimeNotNone":
	                		String strParkTimeValue = clsMeter.METER_GetMeterParkTimeValue(objDictionary,sessionMeter,strSpotNumber);
                			if(!strParkTimeValue.equals("None"))
		            		{
		            			Reporter.log("GlobalWait: The meter ParkTime was "+strParkTimeValue);
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for ParkTime To Not Equal None"+strSpotNumber);}
	                		break;
	                	default:
	                		String strErrorMsg = "The strWaitType ("+strWaitType+") has not been added-"+strMethondName;
	                    	Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	                    	break;
                }
                currentTime = new Timestamp(System.currentTimeMillis());
//               	System.out.println("Current Time: "+currentTime.getTime());
//                System.out.println("End Time: "+endTime.getTime());
                if(currentTime.getTime() > endTime.getTime())
                {
                	if(strWaitObjects.contains("{WaitUntilMeterVariableBegunEqualFalse}"))
                	{
                		strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,sessionMeter,strSpotNumber,strHostType);
            			return;
            		}
                	else
                	{UpdateErrorMessageWithPivotalData(objDictionary,driver,sessionMeter,"None of the expected objects (" + strWaitObjects + ") existed after (" + intWaitTime + ") seconds-RMQ Cert may have expired","False");}
            	}
            }
        }while (strTimeoutFlag == "False");
	}
	public void GlobalWait(Map<String, String> objDictionary, WebDriver driver, String strWaitObjects, int intWaitTime, String strSpotNumber, String strHostType)
	{
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		HttpConnections clsHttpConnections = new HttpConnections();
  		String strObjectValue2 = "";
		String strTimeoutFlag = "False";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intWaitTime);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
        do
        {
            String[] arrWaitObjects = strWaitObjects.split("\\|");
            for (String WaitObject : arrWaitObjects)
            {
                String[] arrWaitObject = WaitObject.split("}");
                String strWaitType = arrWaitObject[0].substring(1);
                String[] arrWaitValue = arrWaitObject[1].trim().split("~");
                int intWaitValueLenght = arrWaitValue.length;
                String strWaitValue = arrWaitValue[0];
                if (intWaitValueLenght == 2) {strObjectValue2 = arrWaitValue[1];}
                strActualWaitType = strWaitType;
                strActualWaitValue = strWaitValue;
                WebElement objName = null;
                String strBegunVariable = "";
                String strFreeVariable = "";
                switch (strWaitType)
                {
                	case "WaitParkingSessionConciergeValueEqualsTrue":
                  		String strParkingSessionConciergeValue = clsHttpConnections.HTTPCONNECTIONS_GetParkingSessionConciergeValue(objDictionary, "Local", "1");
                  		if(strParkingSessionConciergeValue.equals("true"))
                		{
                			Reporter.log("GlobalWait: WaitParkingSessionConciergeValueEqualsTrue");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
                		else
                		{System.out.println("Waiting until a Parking Session Concierge Value Equals True"+strSpotNumber);}
                		break;
            		case "WaitUntilSentryLinkViolationIdExists":
            			String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetSLViolationNumber(objDictionary, "Local", strSpotNumber,1);
            	  		if(!strViolationId.equals(""))
                		{
                			Reporter.log("GlobalWait: Wait Until Sentry Link Violation Id Exists");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
                		else
                		{System.out.println("Waiting until a Free Parking Rate Exists"+strSpotNumber);}
            			break;
            		case "WaitUntilFreeParkingBlockExists":
            			String strFreeRateBlockExists = CheckIfFreeParkingBlockExists(objDictionary);
                		if(strFreeRateBlockExists.equals("True"))
                		{
                			Reporter.log("GlobalWait: FreeParkingBlockExisted");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
                		else
                		{System.out.println("Waiting until a Free Parking Rate Exists"+strSpotNumber);}
                		break;
            		case "WaitUntilNoParkingBlockExists":
            			String strNoRateBlockExists = CheckIfNoParkingBlockExists(objDictionary);
                		if(strNoRateBlockExists.equals("True"))
                		{
                			Reporter.log("GlobalWait: NoParkingBlockExisted");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
                		else
                		{System.out.println("Waiting until a No Parking Rate Exists"+strSpotNumber);}
                		break;
                	case "WaitUntilARateBlockIdIsNotNeg1": //New Rate Set Exists
                		String strRateBlockExistFlag = CheckIfRateBlockIdIsNotNeg1(objDictionary,strHostType);
                		if(strRateBlockExistFlag.equals("True"))
                		{
                			Reporter.log("GlobalWait: Rate Block values existed that were not -1");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
//	                		else
//	                		{System.out.println("Waiting until a Rate BlockId Is Not Negative-Spot"+strSpotNumber);}
                		break;
                	case "WaitUntilARateBlockIdIstNeg1"://Rate Sets Are Deleted
                		String strRateBlockExistFlag2 = CheckIfRateBlockIdIsNotNeg1(objDictionary,strHostType);
                		if(strRateBlockExistFlag2.equals("False"))
                		{
                			Reporter.log("GlobalWait: Rate Block values existed that were not -1");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
                		else
                		{System.out.println("Waiting until a Rate BlockId Is Not Negative-Spot"+strSpotNumber);}
                		break;
//	                	case "WaitUntilMeterCoinAcceptorEqualsFalse":
//	                		String strCoinAcceptorValue = GetCoinAcceptorValue(objDictionary,strHostType);
//	                		if(strCoinAcceptorValue.equals("False"))
//	                		{
//	                			Reporter.log("GlobalWait: The Coin Acceptor Value was False");
//	                			strWaitObjects = "";intWaitTime = 30;return;
//	                		}
//	                		else
//	                		{System.out.println("Waiting until Coin Acceptor Equals False-Spot"+strSpotNumber);}
//	                		break;
                	case "WaitUntilMeterVariableBegunEqualTrue":
                		if (strVirtualMeter.equals("True")){return;}
                		else
                		{
                			strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,strSpotNumber,strHostType);
	                		if(strBegunVariable.equals("True"))
	                		{Reporter.log("GlobalWait: The meter variable begun equalled True");strWaitObjects = "";intWaitTime = 30;return;}
	                		else
	                		{System.out.println("Waiting for parking session to begin-Spot"+strSpotNumber);}
                		}
                		break;
                	case "WaitUntilMeterVariableBegunEqualFalse":
                		strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,strSpotNumber,strHostType);
                		if(strBegunVariable.equals("False"))
                		{
                			Reporter.log("GlobalWait: The meter variable begun equalled False");
                			strWaitObjects = "";intWaitTime = 30;return;
                		}
                		else
                		{System.out.println("Waiting for meter Begin value to equal False-Spot"+strSpotNumber);}
                		break;
                	case "WaitUntilMeterFreeEqualsFalse":
                		strFreeVariable = clsMeter.METER_GetMeterFreeValue(objDictionary,driver,strHostType);
	            		if(!strFreeVariable.equals("True"))
	            		{Reporter.log("GlobalWait: The meter variable free equalled False");strWaitObjects = "";intWaitTime = 30;return;}
	            		else
                		{System.out.println("Waiting for meter to no longer displays free-Spot"+strSpotNumber);}
	            		break;
                	case "WaitUntilMeterFreeEqualsTrue":
                		if (strVirtualMeter.equals("True")){return;}
                		else
                		{
                			strFreeVariable = clsMeter.METER_GetMeterFreeValue(objDictionary,driver,strHostType);
		            		if(strFreeVariable.equals("True"))
		            		{
		            			Reporter.log("GlobalWait: The meter variable free equalled True");
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
                		}
	            		break;
                	case "WaitUntilMeterInMaintEqualsTrue":
                		if (strVirtualMeter.equals("True")){return;}
                		else
                		{
                			String strInMaintVariable = clsMeter.METER_GetMeterInMaintValue(objDictionary,driver,strWaitValue,strHostType);
                			if(strInMaintVariable.equals("True"))
		            		{
		            			Reporter.log("GlobalWait: The meter variable InMaint equalled True");
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for meter to displays InMaint-Spot"+strSpotNumber);}
                		}
	            		break;
                	case "WaitUntilMeterInMaintEqualsFalse":
                		if (strVirtualMeter.equals("True")){return;}
                		else
                		{
                			String strInMaintVariable = clsMeter.METER_GetMeterInMaintValue(objDictionary,driver,strWaitValue,strHostType);
		            		if(strInMaintVariable.equals("False"))
		            		{
		            			Reporter.log("GlobalWait: The meter variable InMaint equalled False");
		            			strWaitObjects = "";intWaitTime = 30;return;
		            		}
		            		else
	                		{System.out.println("Waiting for meter to displays InMaint-Spot"+strSpotNumber);}
                		}
	            		break;
                	case "WaitUntilMeterViolationEqualsTrue":
                		strFreeVariable = clsMeter.METER_GetMeterViolationValue(objDictionary,strHostType, strSpotNumber);
	            		if(strFreeVariable.equals("True"))
	            		{
	            			Reporter.log("GlobalWait: The meter violation equalled true");
	            			strWaitObjects = "";intWaitTime = 30;return;
	            		}
	            		break;
                	case "WaitUntilMeterViolationEqualsFalse":
                		strFreeVariable = clsMeter.METER_GetMeterViolationValue(objDictionary,strHostType,strSpotNumber);
	            		if(strFreeVariable.equals("False"))
	            		{
	            			Reporter.log("GlobalWait: The meter variable free equalled False");
	            			strWaitObjects = "";intWaitTime = 30;return;
	            		}
	            		else
                		{System.out.println("Waiting for violation to disappear-spot"+strSpotNumber);}
	            		break;
                	case "WatiUntilMeterValidTimePurchasedEquals":
                		String strValidTimePurchased = GetMeterValidTimePurchased(objDictionary,driver,"1",strHostType,"After").replace(".0", "");
                		if(strValidTimePurchased.equals(strWaitValue))
	            		{
	            			Reporter.log("GlobalWait: The meter valid time purchased equals "+strWaitValue);
	            			strWaitObjects = "";intWaitTime = 30;return;
	            		}
	            		else
                		{System.out.println("Waiting for the meter valid time purchased to equal "+strWaitValue+"-Spot"+strSpotNumber);}
	            		break;
                	case "WaitUntilMeterNoParkingEqualsFalse":
                		strFreeVariable = clsMeter.METER_GetMeterNoParkingValue(objDictionary,driver);
	            		if(strFreeVariable.equals("False"))
	            		{
	            			Reporter.log("GlobalWait: The meter variable no parking equalled False");
	            			strWaitObjects = "";intWaitTime = 30;return;
	            		}
	            		else
                		{
	            			System.out.println("Waiting for meter to no longer displays no parking-Spot"+strSpotNumber);
	            		}
	            		break;
                	case "WaitUntilMeterNoParkingEqualsTrue":
                		strFreeVariable = clsMeter.METER_GetMeterNoParkingValue(objDictionary,driver);
	            		if(strFreeVariable.equals("True"))
	            		{
	            			Reporter.log("GlobalWait: The meter variable no parking equalled True");
	            			strWaitObjects = "";intWaitTime = 30;return;
	            		}
	            		else
                		{System.out.println("Waiting for meter to displays no parking-Spot"+strSpotNumber);}
	            		break;
                	case "WaitUntilMeterStateEquals":
                		strFreeVariable = clsMeter.METER_GetMeterStateValue(objDictionary,driver);
	            		if(strFreeVariable.equals(strWaitValue))
	            		{
	            			Reporter.log("GlobalWait: The meter variable State equalled "+strWaitValue);
	            			strWaitObjects = "";intWaitTime = 30;return;
	            		}
	            		else
                		{System.out.println("Waiting for meter state to displays "+strWaitValue+" "+strSpotNumber);}
	            		break;

                	default:
                		String strErrorMsg = "The strWaitType ("+strWaitType+") has not been added-"+strMethondName;
                    	Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
                    	break;
                }
                currentTime = new Timestamp(System.currentTimeMillis());
                System.out.println("Current Time: "+currentTime.getTime());
                System.out.println("End Time: "+endTime.getTime());
                if (currentTime.getTime() > endTime.getTime())
                {
                	//This code was causing a endless loop when seting Rates
//                	if(strWaitObjects.contains("{WaitUntilMeterVariableBegunEqualFalse}"))
//                	{
//            			METER_SetBegunEqualFalse(objDictionary, driver,strSpotNumber,strHostType);
//            			return;
//            		}
//                	else
//                	{
                		UpdateErrorMessageWithPivotalData(objDictionary,driver,"None of the expected objects (" + strWaitObjects + ") existed after (" + intWaitTime + ") seconds-RMQ Cert may have expired");
//                	}
            	}
            }
        }while (strTimeoutFlag == "False");
	}
	//**************************************************************************************************************************************************************************************************************/



	public void WaitUntilMeterVariableBegunEqualFalse(Map<String, String> objDictionary, WebDriver driver, String strWaitObjects, int intWaitTime, String strSpotNumber, String strHostType)
	{
		String strNumberOfMeterSpots = objDictionary.get("strNumberOfMeterSpots");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter = "False";}
		if(strNumberOfMeterSpots == null){strNumberOfMeterSpots = METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,driver,strHostType);}
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		//String strSpotNumber = objDictionary.get("strSpotNumber");
		String strObjectValue2 = "";
		String strTimeoutFlag = "False";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime.getTime());
        cal.add(Calendar.SECOND, intWaitTime);
        Timestamp endTime = new Timestamp(cal.getTime().getTime());
        do
        {
            String[] arrWaitObjects = strWaitObjects.split("\\|");
            for (String WaitObject : arrWaitObjects)
            {
                String[] arrWaitObject = WaitObject.split("}");
                String strWaitType = arrWaitObject[0].substring(1);
                String[] arrWaitValue = arrWaitObject[1].trim().split("~");
                int intWaitValueLenght = arrWaitValue.length;
                String strWaitValue = arrWaitValue[0];
                if (intWaitValueLenght == 2) {strObjectValue2 = arrWaitValue[1];}
                strActualWaitType = strWaitType;
                strActualWaitValue = strWaitValue;
                WebElement objName = null;
                String strBegunVariable = "";
                String strFreeVariable = "";
                switch (strWaitType)
                {
	                	case "WaitUntilMeterVariableBegunEqualFalse":
	                		strBegunVariable = clsMeter.GetMeterBegunValue(objDictionary,strSpotNumber,strHostType);
	                		if(strBegunVariable.equals("False"))
	                		{
	                			Reporter.log("GlobalWait: The meter variable begun equalled False");
	                			strWaitObjects = "";intWaitTime = 30;return;
	                		}
	                		else
	                		{System.out.println("Waiting for meter Begin value to equal False-Spot"+strSpotNumber);}
	                		break;
	                	default:
	                		String strErrorMsg = "The strWaitType ("+strWaitType+") has not been added-"+strMethondName;
	                    	Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	                    	break;
                }
                currentTime = new Timestamp(System.currentTimeMillis());
                if (currentTime.getTime() > endTime.getTime())
                {
                	UpdateErrorMessageWithPivotalData(objDictionary,driver,"None of the expected objects (" + strWaitObjects + ") existed after (" + intWaitTime + ") seconds");
	            }
            }
        }while (strTimeoutFlag == "False");
	}
	public void StoreMaxTime_MaxRemaining_ValidTimePurchased_ValidTimeRemaining_Violation_Unlocked(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strMeterUser = objDictionary.get("strMeterUser");
		String strPassword = objDictionary.get("strUniquePassword");
    	String host = strHost;
    	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("/usr/local/bin/testauto_dump_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1200];
	        while(true)
	        {
		        	while(in.available()>0)
		        	{
		        		int i=in.read(tmp, 0, 1200);
		        		if(i<0) {
							break;
						}
		        		String strOutput = new String(tmp, 0, i);
		        		String strSpotVariables = "";
		        		switch (strSpotNumber)
	        			{
	        				case "1":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_1")+7, strOutput.length());
	        					break;
	        				case "2":
	        					strSpotVariables = strOutput.substring(strOutput.indexOf("SPOT_2")+7, strOutput.indexOf("SPOT_1"));
	        					break;

	        			}
	        			String[] arrVariables = strSpotVariables.split("\\|", -1);
	        			int intCounter = 0;
	        			for (String strVariable : arrVariables)
	        			{
	        				if(!arrVariables[intCounter].equals(""))
	        				{
	        					String[] arrVariableValues = strVariable.split("\\,", -1);
	        					System.out.print(arrVariableValues[0]);
	        					System.out.print(arrVariableValues[1]);
	        					objDictionary.remove("strMeter"+arrVariableValues[0]);objDictionary.put("strMeter"+arrVariableValues[0], arrVariableValues[1]);
		        		    		Reporter.log("The Value (" + arrVariableValues[1] + ") was stored as variable name (strMeter"+arrVariableValues[0]+")"+"");
	        				}
	        				intCounter++;
	        			}
		        	}
		        	if(channel.isClosed()){if(in.available()>0) {
						continue;
					}break;}
	        }
		    channel.disconnect();session.disconnect();
	    }
	    catch(Exception e)
	    {
			String strErrorMsg = e+" ";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
    		}
    }

	//**************************************************************************************************************************************************************************************************************/
	//Remove Rate Blocks(s)
	//**************************************************************************************************************************************************************************************************************/
	public void ClearGlobalPayRates(Map<String, String> objDictionary, WebDriver driver,Session sessionMeter,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
  		System.out.println("Start: "+strMethodName);
		Meter clsMeter = new Meter();
		if(sessionMeter != null)
		{
			try
		    {
				Channel channel=sessionMeter.openChannel("exec");
				((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/global_pay_rates.pdo");
				channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        String s = "";
		        int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        //System.out.println(lines.length);
		        for (String line : lines) {
		        	//System.out.println(lines[i]);
		        	if (line.charAt(0) != '{') continue;
			        try
			        {
			        	JSONParser jsonParser = new JSONParser();
			        	JSONObject jsonObject = (JSONObject) jsonParser.parse(line.substring(0, line.indexOf("}q")+1));
				    	String blockId = jsonObject.get("block_id").toString();
				    	if (!blockId.equals("-1"))
			        	{
			        		System.out.println("Deleting Block #: " + blockId);
			        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "parking_delete_global_pay_rate_blk.py " + blockId);
			    			try {Thread.sleep(7500);}catch (Exception e) {}
			        		System.out.println("Rate Block was deleted");
			        	}
			        }
			        catch(Exception d)
			        {UpdateErrorMessageWithPivotalData(objDictionary,driver,"Something changed in rates.pdo which caused issuing parsing rate blocks-"+strMethodName);}
		        }
		        in.close();
		        channel.disconnect();
			}
		    catch(Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"Something changed in rates.pdo which caused issuing deleting rate blocks-"+strMethodName);}
		}
		System.out.println("End: "+strMethodName);
    }
	public void ClearRates(Map<String, String> objDictionary, WebDriver driver,Session sessionMeter,String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
  		System.out.println("Start: "+strMethodName);
		Meter clsMeter = new Meter();
		if(sessionMeter != null)
		{
			try
		    {
				Channel channel=sessionMeter.openChannel("exec");
				((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
				channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        String s = "";
		        int c;
		        while((c = in.read()) != -1) {s += (char)c;}
		        String[] lines = s.split("\n");
		        //System.out.println(lines.length);
		        for (String line : lines) {
		        	//System.out.println(lines[i]);
		        	if (line.charAt(0) != '{') continue;
			        try
			        {
			        	JSONParser jsonParser = new JSONParser();
			        	JSONObject jsonObject = (JSONObject) jsonParser.parse(line.substring(0, line.indexOf("}q")+1));
				    	String blockId = jsonObject.get("block_id").toString();
				    	if (!blockId.equals("-1"))
			        	{
			        		System.out.println("Deleting Block #: " + blockId);
			        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, sessionMeter, strHostType, "parking_delete_rate_blk.py " + blockId);
			    			try {Thread.sleep(7500);}catch (Exception e) {}
			        		System.out.println("Rate Block was deleted");
			        	}
			        }
			        catch(Exception d)
			        {UpdateErrorMessageWithPivotalData(objDictionary,driver,"Something changed in rates.pdo which caused issuing parsing rate blocks-"+strMethodName);}
		        }
		        in.close();
		        channel.disconnect();
			}
		    catch(Exception e)
		    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"Something changed in rates.pdo which caused issuing deleting rate blocks-"+strMethodName);}
		}
		System.out.println("End: "+strMethodName);
    }
	public void RemoveGlobalRateBlocksFromMeter(Map<String, String> objDictionary, WebDriver driver, String strHostType)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
  		System.out.println("Start: "+strMethodName);
		Meter clsMeter = new Meter();
		JSch jsch = new JSch();
		String strHost = "";String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		String strPassword = objDictionary.get("strUniquePassword");
		int port=22;
		try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        //Run this on meter to see number of "global_pay_rates.pdo | wc"
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/global_pay_rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        //System.out.println(lines.length);
	        for (String line : lines) {
	        	System.out.println(line);
	        	if (line.charAt(0) != '{') continue;
		        try
		        {
		        	JSONParser jsonParser = new JSONParser();
		        	System.out.println(line);
		        	if(line.contains("}q"))
		        	{
			        	JSONObject jsonObject = (JSONObject) jsonParser.parse(line.substring(0, line.indexOf("}q")+1));
				    	String blockId = jsonObject.get("block_id").toString();
				    	if (!blockId.equals("-1"))
			        	{
			        		System.out.println("Deleting Block #: " + blockId);
			        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "parking_delete_global_pay_rate_blk.py " + blockId);
			        		try {Thread.sleep(2000);}catch (Exception e) {}
			        		System.out.println("Rate Block was deleted");
			        	}
		        	}
		        	else
		        	{
		        		JSONObject jsonObject = (JSONObject) jsonParser.parse(line.substring(0, line.indexOf("}r")+1));
				    	String blockId = jsonObject.get("block_id").toString();
				    	if (!blockId.equals("-1"))
			        	{
			        		System.out.println("Deleting Block #: " + blockId);
			        		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "parking_delete_global_pay_rate_blk.py " + blockId);
			        		try {Thread.sleep(2000);}catch (Exception e) {}
			        		System.out.println("Rate Block was deleted");
			        	}
		        	}
		        }
		        catch(Exception d)
		        {UpdateErrorMessageWithPivotalData(objDictionary,driver,"Something changed in parking_delete_global_pay_rate_blk.py which caused issuing parsing rate blocks-"+strMethodName);}
	        }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
		}
	    catch(Exception e)
	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"Something changed in rates.pdo which caused issuing deleting rate blocks-"+strMethodName);}
		System.out.println("End: "+strMethodName);
    }
	public void RemoveRateBlocksFromMeter(Map<String, String> objDictionary, WebDriver driver, String strHostType) {
	    String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	    System.out.println("Start: " + strMethodName);

	    Meter clsMeter = new Meter();
	    JSch jsch = new JSch();

	    String strHost = "";
	    String strMeterUser = "";

	    if (strHostType.equals("Local")) {
	        strHost = objDictionary.get("strHost");
	        strMeterUser = objDictionary.get("strMeterUser");
	    } else {
	        strHost = objDictionary.get("strRemoteHost");
	        strMeterUser = objDictionary.get("strRemoteUser");
	    }

	    String strPassword = objDictionary.get("strUniquePassword");
	    int port = 22;

	    try {
	        Session session = jsch.getSession(strMeterUser, strHost, port);
	        session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();

	        Channel channel = session.openChannel("exec");
	        ((ChannelExec) channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec) channel).setErrStream(System.err);

	        InputStream in = channel.getInputStream();
	        channel.connect();

	        // Read the entire output
	        StringBuilder sb = new StringBuilder();
	        int c;
	        while ((c = in.read()) != -1) {
	            sb.append((char) c);
	        }

	        String[] lines = sb.toString().split("\n");

	        // Create parser once outside the loop
	        JSONParser jsonParser = new JSONParser();

	        for (String line : lines) {
	            if (line == null || line.trim().isEmpty()) {
	                continue;
	            }

	            // Only process lines that look like JSON objects
	            line = line.trim();
	            if (line.charAt(0) != '{') {
	                continue;
	            }

	            try {
	                // Find the last closing brace and take everything up to it
	                int lastBrace = line.lastIndexOf('}');
	                if (lastBrace == -1) continue;

	                String jsonStr = line.substring(0, lastBrace + 1).trim();

	                System.out.println("Parsing: " + jsonStr);  // Optional: for debugging

	                JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);

	                Object blockIdObj = jsonObject.get("block_id");
	                if (blockIdObj == null) continue;

	                String blockId = blockIdObj.toString().trim();

	                if (!blockId.equals("-1") && !blockId.isEmpty()) {
	                    System.out.println("Deleting Block #: " + blockId);
	                    
	                    clsMeter.METER_ExecutePythonScriptAgainstMeter(
	                        objDictionary, strHost, "parking_delete_rate_blk.py " + blockId);
	                    
	                    try {
	                        Thread.sleep(2500);
	                    } catch (Exception ignored) {}
	                    
	                    System.out.println("Rate Block was deleted");
	                }

	            } catch (Exception d) {
	                // Uncomment for more details during debugging:
	                // System.out.println("Failed to parse line: " + line.substring(0, Math.min(300, line.length())));
	                // d.printStackTrace();
	                System.out.println("MIH - Parse failed for a block");
	            }
	        }

	        in.close();
	        channel.disconnect();
	        session.disconnect();

	    } catch (Exception e) {
	        System.out.println("Exception in RemoveRateBlocksFromMeter: " + e.getMessage());
	        UpdateErrorMessageWithPivotalData(objDictionary, driver,
	            "Something changed in rates.pdo which caused issue deleting rate blocks - " + strMethodName);
	    }

	    System.out.println("End: " + strMethodName);
	}


	//**************************************************************************************************************************************************************************************************************/



	public String CheckIfRateBlockIdIsNotNeg1(Map<String, String> objDictionary, String strHostType)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = "";
		if(strHostType.equals("Local")){strHost = objDictionary.get("strHost");strMeterUser = objDictionary.get("strMeterUser");}
		else{strHost = objDictionary.get("strRemoteHost");strMeterUser = objDictionary.get("strRemoteUser");}
		int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines) {
		        	if (line.charAt(0) != '{')
		        	continue;
		        	JSONParser jsonParser = new JSONParser();
		        	JSONObject jsonObject = (JSONObject) jsonParser.parse(line.toString().replace("}q", "}"));
			    	String blockId = jsonObject.get("block_id").toString();
			    	if (!blockId.equals("-1"))
		        	{
		        		System.out.println("Rate Block Id was not Neg: " + blockId);
		        		channel.disconnect();
		        		session.disconnect();
		        		return "True";
		        	}
		        	else
		        	{System.out.println("Rate Block Id was Neg: " + blockId);}
	        }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
		}
	    catch(Exception e){}
	    return "False";
    }
	public String CheckIfFreeParkingBlockExists(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines) {
	        		if(line.toString().contains("FreeParkingBlock"))
	        		{
		        		channel.disconnect();
		        		session.disconnect();
		        		System.out.println("Free Parking Block Existed");
		        		return "True";
		        	}
		        	else
		        	{System.out.println("Free Parking Block Did Not Exist");}
	        }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
		}
	    catch(Exception e){}
	    return "False";
    }
	public String CheckIfNoParkingBlockExists(Map<String, String> objDictionary)
	{
		JSch jsch = new JSch();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		String strMeterUser = objDictionary.get("strMeterUser");String host = strHost;int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, host, port);
	    	session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("strings /var/lib/sentry/repo/main/rates.pdo");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        String s = "";
	        int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        for (String line : lines) {
	        		if(line.toString().contains("NoParkingBlock"))
	        		{
		        		channel.disconnect();
		        		session.disconnect();
		        		System.out.println("No Parking Block Existed");
		        		return "True";
		        	}
		        	else
		        	{System.out.println("No Parking Block Did Not Exist");}
	        }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
		}
	    catch(Exception e){}
	    return "False";
    }
	public void METER_CopyPythonScriptFromMeterToLocalPythonFolder(Map<String, String> objDictionary, String strPythonScript, String strHostType) throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPassword = objDictionary.get("strUniquePassword");
		File directory = new File(".");
		String strPath = directory.getCanonicalPath() +"/PythonScripts";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir()) {System.out.println("Directory is created!");}
			else {System.out.println("Failed to create directory!");}
		}
		String strFullMeterLogsPath = strPath+"/"+strPythonScript;
//		String strMeterUser = objDictionary.get("strMeterUser");
//
		String strMeterUser = "";
		if(strHostType.equals("Local")){strMeterUser = objDictionary.get("strMeterUser");}
		else{strMeterUser = objDictionary.get("strRemoteUser");}

		if(strMeterUser.equals("root"))
		{
			//System.out.println("sshpass -p "+strPassword+" scp -r root@10.10.102.182:/usr/local/bin/"+strPythonScript+" "+strFullMeterLogsPath);
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r root@10.10.102.182:/usr/local/bin/"+strPythonScript+" "+strFullMeterLogsPath};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);}
			}
			catch (IOException e)
			{e.printStackTrace();}
		}
		else
		{
			System.out.println("sshpass -p "+strPassword+" scp -r seco@10.10.101.131:/usr/local/bin/"+strPythonScript+" "+strFullMeterLogsPath);
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r seco@10.10.101.131:/usr/local/bin/"+strPythonScript+" "+strFullMeterLogsPath};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{
					UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);
				}
			}
			catch (IOException e){e.printStackTrace();}
		}
	}
	public void METER_CopyPythonScriptFromLocalPythonFolderToMeter(Map<String, String> objDictionary, String strHost, String strPythonScript) throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strPassword = objDictionary.get("strUniquePassword");
		File directory = new File(".");
		String strPath = directory.getCanonicalPath() +"/PythonScripts";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir()) {System.out.println("Directory is created!");}
			else {System.out.println("Failed to create directory!");}
		}
		String strFullMeterLogsPath = strPath+"/"+strPythonScript;
		//Get StrMeterUser
		String strLocalHost = objDictionary.get("strHost");
		String strMeterUser = "";
		if(strHost.equals(strLocalHost)){strMeterUser = objDictionary.get("strMeterUser");}
		else{strMeterUser = objDictionary.get("strRemoteUser");}
		if(strMeterUser.equals("seco"))
		{
			String strSudo = "";
			System.out.println("sudo sshpass -p "+strPassword+" scp -r "+strFullMeterLogsPath +" seco@"+strHost+":/usr/local/bin/");
			String[] command1 = {"sh","-c",strSudo+"sshpass -p "+strPassword+" scp -r "+strFullMeterLogsPath +" seco@"+strHost+":/usr/local/bin/"};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);}
			}
			catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-host ("+strLocalHost+")-"+strMethodName);}
		}
		else
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r "+strFullMeterLogsPath +" "+strMeterUser+"@"+strHost+":/usr/local/bin/"};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals(""))
				{
					Reporter.log("Python Script that Failed: "+strPythonScript);
					UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName+"-Hint ssh into the meter from this computer");
				}
			}
			catch(Exception e)
			{{UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-host ("+strLocalHost+")-"+strMethodName);}}
		}
	}
	//NOT WORKING
	public void METER_CopyPythonScriptFromMeterToMeter(Map<String, String> objDictionary, String strPythonScript) throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strHost = objDictionary.get("strHost");
		String strPassword = objDictionary.get("strUniquePassword");
		File directory = new File(".");
		String strPath = directory.getCanonicalPath() +"/PythonScripts";
		File file = new File(strPath);
		if (!file.exists())
		{
			if (file.mkdir()) {System.out.println("Directory is created!");}
			else {System.out.println("Failed to create directory!");}
		}
		String strFullMeterLogsPath = strPath+"/"+strPythonScript;
		String strMeterUser = objDictionary.get("strMeterUser");
		if(strMeterUser.equals("root"))
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r root@10.10.102.182:"+strFullMeterLogsPath +"\\ root@"+strHost+":/usr/local/bin/"};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals("")){UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);}
			}
			catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		}
		else
		{
			String[] command1 = {"sh","-c","sshpass -p "+strPassword+" scp -r root@10.10.102.182:"+strFullMeterLogsPath +"\\ seco@"+strHost+":/usr/local/bin/"};
			try
			{
				Process proc = Runtime.getRuntime().exec(command1);
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				String strErrorMessage = "";
				while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
				if(!strErrorMessage.equals("")){UpdateErrorMessageWithPivotalData(objDictionary,null,strErrorMessage+"-"+strMethodName);}
			}
			catch(Exception e){UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-"+strMethodName);}
		}
	}

}

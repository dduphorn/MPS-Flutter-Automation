package AutomationCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.SupportsContextSwitching;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import io.appium.java_client.AppiumBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;


@Listeners({ EmailableReporter2.class, EmailableReporter3.class, ListenersClass.class})
public class Android_Lot_TestCases 
{
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	protected Map<String, String> objDictionary = new HashMap<String, String>();
//	
	@Parameters({"strBrowser","strStopAndStartAppiumServer","strRemotePath","strRole", "strVehicleParkType", "strVehicleDepartType", "strDeviceId","strAVDName","strMobileAPK","strPEOAPK","strAndroidUdid","strIOSUdid","strAppiumPort","strDeviceAppiumPort","strIOSDeviceName","strIOSVersion","strIOSBuild"})
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(final ITestContext testContext, @Optional Method method,@Optional String strBrowser,@Optional String strStopAndStartAppiumServer,@Optional String strRemotePath,@Optional String strRole, @Optional String strVehicleParkType, @Optional String strVehicleDepartType, @Optional String strDeviceId,@Optional String strAVDName, @Optional String strMobileAPK,@Optional String strPEOAPK, @Optional String strAndroidUdid,@Optional String strIOSUdid,@Optional String strAppiumPort,@Optional String strDeviceAppiumPort,@Optional String strIOSDeviceName,@Optional String strIOSVersion,@Optional String strIOSBuild)
	{
		GlobalClass clsGlobalClass = new GlobalClass();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonWeb.KillAllChromeDriver();
		//try{Runtime.getRuntime().exec("killall -9 instruments");}catch(Exception e){}
		//Handles Invocation Counter
		String strInvocationCounter = objDictionary.get("strInvocationCounter"); 
		if(strInvocationCounter == null) {strInvocationCounter = "1";}
		else 
		{
			//Increment strInvocationCounter
			int intInvocationCounter = Integer.parseInt(strInvocationCounter) + 1;
			strInvocationCounter = Integer.toString(intInvocationCounter);
		}
		objDictionary.clear();
		objDictionary.put("strInvocationCounter", strInvocationCounter);
		System.out.println("strInvocationCounter: "+strInvocationCounter);
		String strTestSuiteName = testContext.getName();
		String strTestCaseName = method.getName();
		objDictionary.put("strTestCaseName", strTestCaseName+"_"+strInvocationCounter);
		objDictionary.put("strTestSuiteName", strTestSuiteName);
		objDictionary.put("strDeviceAppiumPort", strDeviceAppiumPort);
		clsGlobalClass.AddUserVariablesToDictionaryObject(objDictionary, strBrowser, strStopAndStartAppiumServer, strRemotePath, strRole, strVehicleParkType, strVehicleDepartType, strDeviceId, strAVDName, strMobileAPK, strPEOAPK, strAndroidUdid, strIOSUdid, strAppiumPort, strIOSDeviceName, strIOSVersion, strIOSBuild);
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(dateFormatGmt.format(new Date()) );
		String strMeterStartTime = dateFormatGmt.format(new Date());
		objDictionary.remove("strMeterStartTime");objDictionary.put("strMeterStartTime", strMeterStartTime);
		objDictionary.remove("strTestCase");objDictionary.put("strTestCase", method.getName());
		objDictionary.remove("ExecuteFrom");objDictionary.put("ExecuteFrom", "TestCases");
		Meter clsMeter = new Meter();
		String strVirtualMeter = "False";
		String strHost = objDictionary.get("strHost");
		if(strHost == null) {strVirtualMeter = "True";}
		if(strVirtualMeter.equals("False"))
		{
			//Meter Logs
    		clsMeter.METER_KillTraceLogProcess(objDictionary, null);
    		clsMeter.METER_StartLogTrace(objDictionary, null);
    		//Message Logs
    		clsMeter.METER_KillMessageTraceLogProcess(objDictionary, null);
    		clsMeter.METER_StartMessageLogTrace(objDictionary, null);
		}
		strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
		
	}
	public WebDriver getDriver(){return threadDriver.get();}	
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(final ITestContext testContext, @Optional Method method, ITestResult result) throws Exception 
	{
//		CommonWeb clsCommonWeb = new CommonWeb();
//		CommonANDROID clsCommonMobile = new CommonANDROID();
		Database clsDatabase = new Database();
		Meter clsMeter = new Meter();
		String strErrorMessage = "";
		CommonWeb clsCommonWeb = new CommonWeb();
		String strTestCaseName = method.getName();
		String strChromeDriverPIDs = objDictionary.get("strChromeDriverPIDs");
	    System.out.println("strChromeDriverPIDs: "+strChromeDriverPIDs);
		//Pass clsCommonWeb into Function
	    if(strChromeDriverPIDs != null)
	    {
	    	clsCommonWeb.KillChromeDriver(strChromeDriverPIDs);
	    }
		if(strTestCaseName.contains("ProdPayment"))
		{
			if (!result.isSuccess())
			{	
				try
				{
					Throwable throwable = result.getThrowable();strErrorMessage = throwable.getMessage();
				}catch (Exception e) {}
				objDictionary.put("strErrorMessage", strErrorMessage);
			}
			if (ImgurClient_Old.getLastUrl() != null){objDictionary.put("link", ImgurClient_Old.getLastUrl());}
			clsDatabase.WriteTestResultsTo_PaymentSitesDatabase(objDictionary); 
		}
		else
		{
			if(result == null){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, "The ITestResult Variable was null-AfterMethod","Local");}
			System.out.println(result.getTestClass());
			if (!result.isSuccess())
			{	
				try
				{
					Throwable throwable = result.getThrowable();
					strErrorMessage = throwable.getMessage();
				}catch (Exception e) {}
				//clsMeter.METER_SetMeterEndTime(objDictionary);
			}
			clsMeter.METER_SetMeterEndTime(objDictionary);
			objDictionary.remove("strErrorMessage");objDictionary.put("strErrorMessage", strErrorMessage);
			objDictionary.remove("strTestCaseName");objDictionary.put("strTestCaseName", strTestCaseName);
			clsDatabase.WriteTestResultsToDatabase(objDictionary); 
			String strTestSuiteName = testContext.getName();
			strTestCaseName = method.getName();
			String strInvocationCounter = objDictionary.get("strInvocationCounter");
			//Get Meter Logs
			clsMeter.METER_CopyLogTraceLocally(objDictionary, null,  strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			//clsMeter.METER_CopyMessageTraceLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			String strResetRateBlocksCounter = objDictionary.get("strResetRateBlocksCounter");if(strResetRateBlocksCounter == null) {strResetRateBlocksCounter = "0";}
		}
	}
	

	
	//********************************************************************************************************************
	//ANDROID-LOT_TEST CASES - Hourly
	//********************************************************************************************************************
	@Test(priority=9000,groups={"Smoke"})
	public void AL1000_PRE_PPSL_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"BB").toUpperCase();
//		//Enable Ticket Service Lob Mailing
//  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"AutomationTicketService");
//  		//Enable Look Up Service s&p
//  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"AutomationLookUp");
//  		//Update Kiosk Group Ticket Service
//  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","AutomationLookUp");
  		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
		String strMeterName = objDictionary.get("strMeterName");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
		String strMobileValidTo = objDictionary.get("strMobileValidTo");
		String strMobileValidFromMinusMin = objDictionary.get("strMobileValidFromMinusMin");
		String strMobileValidToMinusMin = objDictionary.get("strMobileValidToMinusMin");
		String strPermitValidFrom = clsCommonMobile.StoreText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "strPermitValidFrom");
		String strPermitValidTo = clsCommonMobile.StoreText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "strPermitValidFrom");
		if(strPermitValidFrom.equals(strMobileValidFrom))
		{
			Reporter.log("The Text (Permit Valid From) with index (1) equaled (" + strMobileValidFrom + ")"+"");
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strPermitValidTo);
		}
		else if(strPermitValidFrom.equals(strMobileValidFromMinusMin))
		{
			Reporter.log("The Text (Permit Valid From) with index (1) equaled (" + strMobileValidFromMinusMin + ")"+"");
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidToMinusMin);
			strMobileValidFrom = strMobileValidFromMinusMin;
			strPermitValidFrom = strMobileValidToMinusMin;
		}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Permit Valid From) with index (1) did not equal (" + strMobileValidFrom + ") - actual value (" + strPermitValidFrom + ")");}
		//Format Dates for CSV
		String strMobileValidFromFormat = "";
		String strMobileValidToFormat = "";
		try
		{
	        DateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a");
			Date date = inputFormat.parse(strMobileValidFrom.replace("Valid From : ", "").replace(" at", ""));
	        DateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
	        strMobileValidFromFormat = outputFormat.format(date);
	        date = inputFormat.parse(strMobileValidTo.replace("Valid To : ", "").replace(" at", ""));
 	        outputFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
 			strMobileValidToFormat = outputFormat.format(date);
	   }
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,e.toString());}
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		try {Thread.sleep(2500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		// Navigate to Configure Meter
		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Parking Lot Sessions","Local");
		//Lot Sessions Date Filter
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -5);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        String formattedDate = dateFormat2.format(cal.getTime());
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Lot Sessions", "Populate Date Range", "{T} From Date Time Entered",formattedDate);
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Lot Sessions", "Find Sessions", 1, "Local");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Lot Sessions", "Show Total", 1, "Local");
		try {Thread.sleep(1500);}catch (Exception e) {} 
		String strRecordCount = clsCommonWeb.StoreText(objDictionary, driver, "Parking Lot Sessions", "Records", 0, "strRecordCount").replace("Records: ", "");
		if(!strRecordCount.equals("0"))
		{
			clsCommonWeb.ClickLink(objDictionary, driver, "Parking Lot Sessions", "(csv)", 1);
			try {Thread.sleep(1500);}catch (Exception e) {} 
			clsCommonWeb.VerificationPointText(objDictionary, driver,  "Parking Lot Sessions", "Alert Message", 0, "Contains", "Creating a CSV, can be found in CSV Reports");
			clsCommonWeb.ClickLink(objDictionary, driver, "Parking Lot Sessions", "CSV Reports", 1);
			if(strEnvironment.equals("SG")){try {Thread.sleep(18000);}catch (Exception e) {}}
			if(strEnvironment.equals("PROD")){try {Thread.sleep(60000);}catch (Exception e) {}}
			driver.navigate().refresh();
			//Click Download File
			try {Thread.sleep(5000);}catch (Exception e) {} 
			clsCommonWeb.ClickLink(objDictionary, driver, "CSV Reports", "Download Parking Sessions", 1);
			try {Thread.sleep(4000);}catch (Exception e) {} 
			String fileName = clsCommonWeb.StoreText(objDictionary, driver, "CSV Reports", "CSV File Name", 0, "strDownloadName").replace("Download ", "");
			String downloadDir = System.getProperty("user.home") + "/Downloads/";
	        String filePath = downloadDir + fileName;
	        try {
	            // Check if file exists
	            Path path = FileSystems.getDefault().getPath(filePath);
	            if (!Files.exists(path))
	            {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The file ("+fileName+") did not exist in ("+downloadDir+")", "Local");}
//	            else
//	            {driver.quit();}
	            // Open the CSV file and count rows
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            String line;
	            int rowCount = 0;
	            // Read each line from the file
	            while ((line = reader.readLine()) != null) {
	                rowCount++;
	                if(rowCount == 2)
	                {
	                	 // Split the line into values using a comma as the delimiter
	                    String[] values = line.split(",");
	                    // Define expected values
	                    String[] expectedValues = {"true", "plate", "plate", strParkStartTime, strParkStartTimeUTC, strMobileValidFromFormat, "2024-02-13 08:07 PM", "2 minutes", "2", "LA0LOTAA", "Minnesota", "Lot Auto One"};
	                    // Validate each value
	                    for (int i = 0; i < values.length; i++) {
	                    	String actualValue = values[i].trim().replaceAll("^\"|\"$", "");
	                        if (!actualValue.equals(expectedValues[i].replaceAll("^\"|\"$", ""))) {
	                            // Value doesn't match expected value
	                            System.out.println("Validation failed for value at index " + i + ". Expected: " + expectedValues[i].replaceAll("^\"|\"$", "") + ", Actual: " + values[i].replaceAll("^\"|\"$", ""));
	                        }
	                    }
	                    // Print success message if all values match
	                    System.out.println("Validation succeeded. All values match expected values.");
	                }
	            }  	        
	        	} catch (IOException e) 
	        {
	        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,e.toString(), "Local");
	        }
		}
		else
		{
			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"No Parking Lot Sessions Existed", "Local");
		}
		driver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1001_PRE_PPSL_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9001,groups={"Smoke"})
    public void AL1000_A_PRE_SMS_VPA_VPH() throws Exception
    {
        objDictionary.put("strAssociatedBug", "");
        objDictionary.put("strMobileDeviceType", "ANDROID");
        HttpConnections clsHttpConnections = new HttpConnections();
        CommonANDROID clsCommonMobile = new CommonANDROID ();
        CommonWeb clsCommonWeb = new CommonWeb();
        clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
        Reporter.log("***************TestCase Description******************************************");
        Reporter.log("PRE: Permit Rate Expired                                                     ");
        Reporter.log("SMS: Send Short Message Service                                              ");
        Reporter.log("VPA: Validate Page Alert = QR code parking is not enabled for this location. ");
        Reporter.log("VPH: Validate Page Header = The permit or QR code is no longer valid.");
        Reporter.log("****************************************************************");
        //Exit Lot If parked
        String strSpaceName = "LOT";
        String strLicensePlateNumber = ("LA0"+strSpaceName+"BB").toUpperCase();
        String strLotExitId = objDictionary.get("strLotExitId");
	    //Exit Lot
	    clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	    //Delete Active Permits
	    clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
	    //Remove all License Plate
	    clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	    //Add License Plate
	    clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
	    //Add Or Update Permit Group
  		objDictionary.put("strPermitCost","2.00");
  		objDictionary.put("strPermitGroup","Student");
  		objDictionary.put("strPermitRate","Daily");
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strDaysBeforePermitStarts","-14");
  		objDictionary.put("strDaysBeforePermitEnds","-7");
  		String strEnvironment = objDictionary.get("strEnvironment");
  		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
  		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
  		String strStartHour = "0";
  		String strEndHour = "23";
  		objDictionary.put("strStartHour",strStartHour);
  		objDictionary.put("strEndHour",strEndHour);
  		//Permit Rate Expired
  		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
	    //Add Or Update Permit Group
	    objDictionary.put("strPermitCost","2.00");
	    objDictionary.put("strPermitGroup","Student");
	    objDictionary.put("strPermitRate","Hourly 1");
	    objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
	    objDictionary.put("strDaysBeforePermitStarts","-14");
	    objDictionary.put("strDaysBeforePermitEnds","-7");
	    clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary);     
	    AndroidDriver smsdriver = clsCommonMobile.SetSmsDriver(objDictionary, "True");
	    try {Thread.sleep(1500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/continue_as_button")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/conversation_list_spam_popup_positive_button")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/start_chat_fab")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.xpath("//android.widget.EditText[@resource-id='ContactSearchField']")).sendKeys("6124825433");
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.xpath("//android.view.View[@resource-id='GlideMonogram']")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("1717");
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.xpath("//android.view.View[@resource-id=\"Compose:Draft:Send\"]/android.widget.Button")).click();
	    try {Thread.sleep(5000);}catch (Exception e) {}
	    String strMessage = smsdriver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"message_text\"])[1]")).getText();
	    if(strMessage.contains("opt-in"))
	    {
	        String strOptInNumber = strMessage.replaceAll("\\D+", "");  // removes everything that's not a digit
	        smsdriver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys(strOptInNumber);
	        try {Thread.sleep(1000);}catch (Exception e) {}
	        smsdriver.findElement(By.xpath("//android.view.View[@resource-id=\"Compose:Draft:Send\"]/android.widget.Button")).click();
	        try {Thread.sleep(1000);}catch (Exception e) {}
	        strMessage = smsdriver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"message_text\"])[1]")).getText();
	    }
	    String url = strMessage.substring(strMessage.lastIndexOf("https://"));
	    // Close the Messenger Session
	    smsdriver.quit();
	    //Open URL in Chrome Browser on Device
	    AndroidDriver chromeDriver = clsCommonMobile.SetChromeDriver(objDictionary);
	    // Navigate to URL
	    chromeDriver.get(url);
	    try {Thread.sleep(2000);}catch (Exception e) {}
	    //Validate Alert Text
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit", "Alert Text", 1, "Contains", "QR code parking is not enabled for this location.");
		//The permit or QR code is no longer valid.
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit", "Page Header", 1, "Contains", "The permit or QR code is no longer valid.");
	    chromeDriver.quit();
	}
	
	@Test(priority=9002,groups={"Smoke"})
	public void AL1001_PRE_ATPPCA_VNPF()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("ATPPCA: Attempt to Purchase Permit Consumer App                 ");
		Reporter.log("VNPF: Validate No Permit Found                                  ");
		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//De-enroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Error Message", 1, "Contains", "No matching rates available.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
		androiddriver.quit();
    	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit SL Then Park
	@Test(priority=9002,groups={"Smoke"})
	public void AL1002_PPSL_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
		String strMeterName = objDictionary.get("strMeterName");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
		String strMobileValidTo = objDictionary.get("strMobileValidTo");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1002_PPSL_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9002,groups={"Smoke"})
	public void AL1002_A_SMS_LP_VCAPS_LE_VSPS()throws Exception
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("SMS: Send Short Message Service                                 ");
		Reporter.log("PP: Purchase Permit                                             ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		String strLicensePlateState = "Minnesota";
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		String strPermitCost = "2.00";objDictionary.put("strPermitCost",strPermitCost);
		objDictionary.put("strPermitGroup","Student");
		String strPermitRate = "Hourly 1";objDictionary.put("strPermitRate",strPermitRate);
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		AndroidDriver smsdriver = clsCommonMobile.SetSmsDriver(objDictionary, "True");
	    try {Thread.sleep(1500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/continue_as_button")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/conversation_list_spam_popup_positive_button")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/start_chat_fab")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.xpath("//android.widget.EditText[@resource-id='ContactSearchField']")).sendKeys("6124825433");
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.xpath("//android.view.View[@resource-id='GlideMonogram']")).click();
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("1717");
	    try {Thread.sleep(500);}catch (Exception e) {}
	    smsdriver.findElement(By.xpath("//android.view.View[@resource-id=\"Compose:Draft:Send\"]/android.widget.Button")).click();
	    try {Thread.sleep(5000);}catch (Exception e) {}
	    String strMessage = smsdriver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"message_text\"])[1]")).getText();
	    if(strMessage.contains("opt-in"))
	    {
	        String strOptInNumber = strMessage.replaceAll("\\D+", "");  // removes everything that's not a digit
	        smsdriver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys(strOptInNumber);
	        try {Thread.sleep(1000);}catch (Exception e) {}
	        smsdriver.findElement(By.xpath("//android.view.View[@resource-id=\"Compose:Draft:Send\"]/android.widget.Button")).click();
	        try {Thread.sleep(1000);}catch (Exception e) {}
	        strMessage = smsdriver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"message_text\"])[1]")).getText();
	    }
	    //Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
	  	String url = strMessage.substring(strMessage.lastIndexOf("https://"));
	    // Close the Messenger Session
	    smsdriver.quit();
	    //Open URL in Chrome Browser on Device
	    AndroidDriver chromeDriver = clsCommonMobile.SetChromeDriver(objDictionary);
	    // Navigate to URL
	    chromeDriver.get(url);
	    objDictionary.put("strMobileChrome","True");
	    objDictionary.put("strHref",url);
	    try {Thread.sleep(2000);}catch (Exception e) {}
	    //Create Parking Permit
	    String strPageSource = chromeDriver.getPageSource();
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit", "Page Header", 1, "Contains", "Create Parking Permit");
	    //Populate Permit
	    clsCommonWeb.PopulateAction(objDictionary, chromeDriver, "Purchase Permit", "Populate Permit","{RB} SMS Parking Permits|{CB} Agree to Terms","Hourly 1|Checked");
		//Click Next to Purchase Payment
	    clsCommonWeb.ClickButton(objDictionary, chromeDriver, "Purchase Permit", "Next", 1, "Local");
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Create Parking Permit", "Description", 1, "Value", strPermitRate);
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Create Parking Permit", "Cost", 1, "Value", "$"+strPermitCost+" each");
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Create Parking Permit", "Selected Hours", 1, "Value", "1");
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Create Parking Permit", "Sales Tax", 1, "Value", "$0.00 (0.000%)");
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Create Parking Permit", "Total Cost", 1, "Value", "$"+strPermitCost);
		clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Create Parking Permit", "Permit Valid Until Message", 1, "Value", "Permit will be valid for a duration of 1 hrs from time of purchase");
		//Populate Parker Information
	    clsCommonWeb.PopulateAction(objDictionary, chromeDriver, "Create Parking Permit", "Populate Parker Information","{T} Plate #|{L} Plate State|{T} Email receipt",strLicensePlateNumber+"|"+strLicensePlateState+"|darin@mpspark.com");
	    //Populate Card
	    clsCommonWeb.ClickButton(objDictionary, chromeDriver, "Create Parking Permit", "Card", 1, "Local");
	    //Populate Parker Information
	    clsCommonWeb.PopulateAction(objDictionary, chromeDriver, "Create Parking Permit", "Populate Pay With Card","{T} Card Number|{T} Expiration Date|{T} CVV","4242424242424242|0228|123");
	    // Get the permit purchase date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        String strPermitStartDateTime = sdf.format(calendar.getTime());
        System.out.println(strPermitStartDateTime);
        String strPermitEndDateTime = clsCommonWeb.AddHoursToExistingTime(strPermitStartDateTime, "1","MM/dd/yyyy hh:mm a");
	    clsCommonWeb.ClickButton(objDictionary, chromeDriver, "Create Parking Permit", "CREATE PERMIT(S)", 1, "Local");
	    //Parking Session Permit Values
	    String strPSPermitStartDateTime = clsCommonWeb.AddHoursToExistingTime(strPermitStartDateTime, "0","MM/dd/yyyy h:mm a");
		String strPSPermitEndDateTime = clsCommonWeb.AddHoursToExistingTime(strPermitStartDateTime, "1","MM/dd/yyyy h:mm a");
		SimpleDateFormat tzOnly = new SimpleDateFormat("z");
		tzOnly.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
		objDictionary.put("strPermitInfomation", " Plate "+strLicensePlateNumber+" EXACT matched permit Valid From "+strPSPermitStartDateTime+" "+tzOnly.format(calendar.getTime())+" Valid To "+strPSPermitEndDateTime+" "+tzOnly.format(calendar.getTime()));
	    //Validate Permit Summary
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit Summary", "Success Message", 1, "Value", "Parking permit(s) successfully created.");
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit Summary", "Description", 1, "Contains", strPermitRate);
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit Summary", "Plate/State", 1, "Contains", strLicensePlateNumber+" / "+strLicensePlateState);
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit Summary", "Number Of Permits", 1, "Contains", "Permits (1)");
	    clsCommonWeb.VerificationPointText(objDictionary, chromeDriver, "Purchase Permit Summary", "Permit Duration", 1, "Value", strPermitStartDateTime+" to "+strPermitEndDateTime);
		chromeDriver.quit();
	    objDictionary.put("strMobileChrome","False");
	   	//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
//		//Validate Permit
//		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
//		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
//		String strMeterName = objDictionary.get("strMeterName");
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
//		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
//		String strMobileValidTo = objDictionary.get("strMobileValidTo");
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
//		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
//		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(20000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1002_A_SMS_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	
	//Park then Purchase Permit SL
	@Test(priority=9003,groups={"Smoke"})
	public void AL1003_LP_PPSL_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Update Kiosk Rate Blocks
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Minnetonka");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"sp_global");
	  		//Update Kiosk Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Minnetonka","sp_global");
		}
		else
		{
			//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
	  		//Update Kiosk Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","S&P Global");
	  	}
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
		String strMeterName = objDictionary.get("strMeterName");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
		String strMobileValidTo = objDictionary.get("strMobileValidTo");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1003_LP_PPSL_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park 
	@Test(priority=9004,groups={"Smoke"})
	public void AL1101_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  	 	String strStartTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "-1","hh:mm a");;
  	 	String strStartTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "1","hh:mm a");;
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "-1","hh:mm a");;
  	 	String strEndTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "1","hh:mm a");;
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
   	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTime+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+") - actual value (" + strValidForDates + ")");}
	 	objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 seconds before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		//Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
    	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		try {Thread.sleep(15000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		String strActualPermitParkTime =  datePermitFormat.format(currentTime);
		String strActualPermitParkTimePlusMinute =  clsCommonWeb.AddTimeToExistingTime(strActualPermitParkTime, "1","h:mm a");
		//Validate Parked At
		String strParkedAt = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strParkedAt");
	 	if(strParkedAt.equals("Parked Today at "+strActualPermitParkTime))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTime+")");}
	 	else if(strParkedAt.equals("Parked Today at "+strActualPermitParkTimePlusMinute))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Parked at) did not equal (Parked Today at "+strActualPermitParkTime+") - actual value (" + strParkedAt + ")");}
		//Validate Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		try {Thread.sleep(2000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	  	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1101_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9005,groups={"Smoke"})
	public void AL1102_PPMH_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
   	 	//Increment Number of Hour   	 	
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Plus", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 2");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $4.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $4.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  	 	String strStartTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "-1","hh:mm a");;
  	 	String strStartTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "1","hh:mm a");;
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "120","hh:mm a");
		String strEndTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "-1","hh:mm a");;
  	 	String strEndTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "1","hh:mm a");;
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "120","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
   	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTime+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+") - actual value (" + strValidForDates + ")");}
	 	objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 seconds before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		//Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
    	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		String strActualPermitParkTime =  datePermitFormat.format(currentTime);
		String strActualPermitParkTimePlusMinute =  clsCommonWeb.AddTimeToExistingTime(strActualPermitParkTime, "1","h:mm a");
		//Validate Parked At
		String strParkedAt = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strParkedAt");
	 	if(strParkedAt.equals("Parked Today at "+strActualPermitParkTime))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTime+")");}
	 	else if(strParkedAt.equals("Parked Today at "+strActualPermitParkTimePlusMinute))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Parked at) did not equal (Parked Today at "+strActualPermitParkTime+") - actual value (" + strParkedAt + ")");}
		//Validate Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	  	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - (dblPermitCost * 2);
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1102_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park Unlock
	@Test(priority=9006,groups={"Smoke"})
	public void AL1201_UL_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4500);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value", strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  	 	String strStartTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "-1","hh:mm a");;
  	 	String strStartTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "1","hh:mm a");;
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "-1","hh:mm a");;
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
     	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
     	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTime))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTime))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTime+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+") - actual value (" + strValidForDates + ")");}
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
		//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 minutes before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
    	//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		try {Thread.sleep(8000);}catch (Exception e) {}
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//Validate Park At
		String strActualPermitParkTime =  datePermitFormat.format(currentTime);
		String strActualPermitParkTimePlusMinute =  clsCommonWeb.AddTimeToExistingTime(strActualPermitParkTime, "1","h:mm a");
		//Validate Parked At
		String strParkedAt = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strParkedAt");
	 	if(strParkedAt.equals("Parked Today at "+strActualPermitParkTime))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTime+")");}
	 	else if(strParkedAt.equals("Parked Today at "+strActualPermitParkTimePlusMinute))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Parked at) did not equal (Parked Today at "+strActualPermitParkTime+") - actual value (" + strParkedAt + ")");}
		//Validate Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	 	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1201_UL_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park True_Up & Unlock 
	@Test(priority=9007,groups={"Smoke"})
	public void AL1301_TU_UL_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Consumer App                                ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "3";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "3";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary); 
		//UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Deenroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
		String strStartDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  	 	String strEndDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  	 	String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  	 	String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
  	 	int intPermitTime = 60 + Integer.parseInt(strFreeTimeFirstPayment);
  	 	String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime),"hh:mm a");
  	 	String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime),"h:mm a");
		String strStartTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "-1","hh:mm a");;
  	 	String strStartTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "1","hh:mm a");;
		String strEndTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "-1","hh:mm a");;
  	 	String strEndTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "1","hh:mm a");;
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	//Validate Permit Purchase
  	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute))
	 	{
   	 		Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute+")");
   	 		strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTimeMinusMinute, "0","h:mm a");
   	 		strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strEndTimeMinusMinute, "0","h:mm a");
   	 	}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime))
	 	{
	 		Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");
	 	}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute))
	 	{
	 		Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute+")");
	 		strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTimePlusMinute, "0","h:mm a");
	 		strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strEndTimeMinusMinute, "0","h:mm a");
	 	}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+") - actual value (" + strValidForDates + ")");}
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
    	//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times Before Park
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  		//Calculate Net Permit Time
  		String strPermitParkTimeWithInitialGrace = clsCommonMobile.AddTimeToExistingTime(strPermitParkTime, strInitialGracePeriod,"hh:mm a");
  		String strPermitParkTimePlusHour = clsCommonMobile.AddTimeToExistingTime(strPermitParkTimeWithInitialGrace, "60","hh:mm a");
  		//Store SL Permit Times After Park
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark))
	  	{Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else
	  	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark))
	  	{Reporter.log("The Permit End Time ("+strPermitParkTimePlusHour+") was correct");}
	  	else
	  	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInfomation", "Valid From "+strStartDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strEndDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	 	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
	 	//Validate Money Is deducted from users account
	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(15000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1301_TU_UL_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park Openlot placeholder sessions & True Up & Unlock
	@Test(priority=9008,groups={"Smoke"})
	public void AL1401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("OPS: Openlot placeholder sessions                               ");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "255";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "255";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "15";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "15";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "15";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "15";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "15";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		objDictionary.put("strOpenlotPlaceholderSessions","True");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value", strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
  	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	try {Thread.sleep(1000);}catch (Exception e) {}
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	//Calculate Permit Times
  	 	String strStartDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  	 	String strEndDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  	 	String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  	 	String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
  	 	int intPermitTime = 60 + Integer.parseInt(strFreeTimeFirstPayment);
  	 	String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime),"hh:mm a");
  	 	String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime),"h:mm a");
  	 	String strStartTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "-1","hh:mm a");;
  	 	String strStartTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "1","hh:mm a");;
		String strEndTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "-1","hh:mm a");;
  	 	String strEndTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "1","hh:mm a");;
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  	 	//Validate Permit Purchase
  	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute))
	 	{
   	 		Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTime+")");
   	 		strStartTime = strStartTimeMinusMinute;strEndTime = strEndTimeMinusMinute;
	 	}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime))
	 	{
	 		Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");
	 	}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute))
	 	{
	 		Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTime+")");
	 		strStartTime = strStartTimePlusMinute;strEndTime = strEndTimePlusMinute;
	 	}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+") - actual value (" + strValidForDates + ")");}
   	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInfomation", "Valid From "+strStartDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strEndDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 60 seconds before parking (This time gets added
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait 60 seconds before Parking"); 
	  	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	//Store Lot Parking Id
    	String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
    	//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strStartTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		try {Thread.sleep(5000);}catch (Exception e) {}
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strPermitParkTime);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		try {Thread.sleep(2000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  		//Store Lot Parking Id
		strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS_2(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Park Then Purchase Permit CA
	@Test(priority=9009,groups={"Smoke"})
	public void AL1501_LP_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PP: Purchase Permit Consumer app                                ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
	 	//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value", strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	 	objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 minutes before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
    	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
		//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}

		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1501_LP_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Park Then Purchase Permit CA Park True_Up & Unlock 
	@Test(priority=9010,groups={"Smoke"})
	public void AL1601_UL_LP_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4500);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	 	objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
		//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1601_UL_LP_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9011,groups={"Smoke"})
	public void AL1701_TU_UL_LP_OCA_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "186172262");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("OCA: Open Consumer App                                          ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "10";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//try {Thread.sleep(5000);}catch (Exception e) {}
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
    	//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value", strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	String strStartTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "-1","hh:mm a");;
  	 	String strStartTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strStartTime, "1","hh:mm a");;
		String strEndTimeMinusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "-1","hh:mm a");;
  	 	String strEndTimePlusMinute = clsCommonWeb.AddTimeToExistingTime(strEndTime, "1","hh:mm a");;
	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimeMinusMinute+" - "+strDay+" at "+strEndTimeMinusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTimeMinusMinute+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+")");}
	 	else if(strValidForDates.equals("Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute))
	 	{Reporter.log("The Text (Valid for dates) with equaled (Valid for dates : "+strDay+" at "+strStartTimePlusMinute+" - "+strDay+" at "+strEndTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal (Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+") - actual value (" + strValidForDates + ")");}
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	try {Thread.sleep(5000);}catch (Exception e) {}
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
		//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1701_TU_UL_LP_OCA_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9012,groups={"Smoke"})
	public void AL1702_TU_UL_OCA_LP_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("OCA: Open Consumer App                                          ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Deenroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//try {Thread.sleep(5000);}catch (Exception e) {}
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Hourly 1 - $2.00");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value", strMeterGroup.toUpperCase());
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $2.00");
  	 	//Get Transaction Fees
	  	try {clsHttpConnections.HTTPCONNECTIONS_StoreKioskTransactionFees(objDictionary);}catch (Exception e) {}
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$0.00");
	  	double dblTotalPermitCost;
	  	if(strFirstPayment == null){dblTotalPermitCost = dblPermitCost;}
	  	else
	  	{
	  		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
	  		dblTotalPermitCost = dblPermitCost + dblFirstPaymentFee;
	  	}
	  	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: "+dFormat.format(dblTotalPermitCost));
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  	 	//objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : "+strMeterGroup);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	try {Thread.sleep(25000);}catch (Exception e) {}
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+ " "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		try {Thread.sleep(4000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
		//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost;
  		if (strFirstPayment == null)
  	 	{dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;}
  	 	else
  	 	{
  	 		double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
  	 		dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost - dblFirstPaymentFee;
  	 	}
	 	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1702_TU_UL_OCA_LP_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");
	}
	}
	
	//Park Then Purchase Permit Kiosk Park True_Up & Unlock 
	@Test(priority=9013,groups={"Smoke"})
	public void AL1801_UL_LP_PHPK1_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "188080157");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PHP1: Purchase Hourly Permit Kiosk 1 Hours                      ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strMinutesBeforeFreeParking = "180";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "30";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
//		clsHttpConnections.JsonMeteDeletePermit(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Wait Until Rate Blocks Reach the Kiosk");
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		//Subtract Initial Grace
		int intPermitTime = 60 + Integer.parseInt(strFreeTimeFirstPayment) + Integer.parseInt(strInitialGracePeriod);
  	 	String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime) ,"hh:mm a");
  	 	String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime),"h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		//Purchase at Kiosk
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_select_permit.py 1");
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_enter_plate.py "+strLicensePlateNumber);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_card_pay.sh");
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_enter_email.py darin@mpspark.com");
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_done_button.py");
		try {Thread.sleep(2000);}catch (Exception e) {}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitDataForKioskPurchase(objDictionary,"");
  	 	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		
  		
//  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
//  		clsCommon Mobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
//  		String strMeterName = objDictionary.get("strMeterName");
//  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
//  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark;
//  		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
//  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
//  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
//  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
//		try {Thread.sleep(5000);}catch (Exception e) {}
//		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		//androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1801_UL_LP_PHPK1_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//Park Then Purchase Permit Kiosk Park True_Up & Unlock 
	@Test(priority=9014,groups={"Smoke"})
	public void AL1802_UL_LP_PHPK2_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "188081069|188080157");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PHP2: Purchase Hourly Permit Kiosk 2 Hours                      ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strMinutesBeforeFreeParking = "180";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "30";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//clsHttpConnections.JsonMeteDeletePermit(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		//Subtract Initial Grace
//		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"-"+strInitialGracePeriod ,"hh:mm a");
//		strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime2,"-"+strInitialGracePeriod,"h:mm a");
		int intPermitTime = 120 + Integer.parseInt(strFreeTimeFirstPayment) + Integer.parseInt(strInitialGracePeriod);
  	 	String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime) ,"hh:mm a");
  	 	String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, Integer.toString(intPermitTime),"h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		//Purchase at Kiosk
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_select_permit.py 0");
		try {Thread.sleep(4000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_enter_plate.py "+strLicensePlateNumber);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_add_hour.py");
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_card_pay.sh");
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_enter_email.py darin@mpspark.com");
		try {Thread.sleep(4000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_no_receipt.py");
		try {Thread.sleep(2000);}catch (Exception e) {}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitDataForKioskPurchase(objDictionary,"");
  	 	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		//Valide Account Details Active Permits  		
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		try {Thread.sleep(1500);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark;
  		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
//			try {Thread.sleep(5000);}catch (Exception e) {}
//			clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
//			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		//androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1802_UL_LP_PHPK2_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//LOT Concierge
	@Test(priority=9015,groups={"Smoke"})
	public void AL1901_CS_LP_LEBIG_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("LEBIG: Lot Exit Before Initial Grace                            ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait 60 seconds Until Parking Id gets created");
  		//Store Lot Parking Id
  		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
  		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1901_CS_LP_LEBIG_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9016,groups={"Smoke"})
	public void AL1902_CS_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"1","hh:mm a");
  		String strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime,"0","h:mm a");
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		//Validate Andriod Device Lot Session
      	String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
	    //Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Store Lot Parking Id
  		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
  		//Validate Parking Session Before Exist
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	 	//Add Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1902_CS_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9017,groups={"Smoke"})
	public void AL1902_A_CS_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("LPBPR: Lot Park Bad Plate Read                                 ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber+"BR", "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber+"BR", "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"1","hh:mm a");
  		String strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime,"0","h:mm a");
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		//Validate Andriod Device Lot Session
      	String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
	    //Update License Plate on Parking Session
	    String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
     	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession2(objDictionary, driver,"Plate","1");
	  	try {Thread.sleep(3000);}catch (Exception e) {}
	  	clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State", strLicensePlateNumber + "|Minnesota");
	  	clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1, "Local");
	  	driver.quit();
	  	try {Thread.sleep(1000);}catch (Exception e) {}
	    //Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Parking Session Before Exist
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	 	//Add Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
		String strParkingId = objDictionary.get("strParkingId");
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+ " "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1902_A_CS_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9018,groups={"Smoke"})
	public void AL1903_CS_TUD_EAPOEE_LP_VCAPS_LE_LP_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("TUD: True Up Disabled                                           ");
		Reporter.log("EAPOEE: End App Permit On Exit Enabled                          ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "True";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		String strStartingAccountBalance = objDictionary.get("strCurrentAccountBalance");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"1","hh:mm a");
  		String strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime,"0","h:mm a");
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		//Validate Andriod Device Lot Session
      	String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
	    //Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Store Lot Parking Id
  		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
  		//Validate Parking Session Before Exist
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	 	//Add Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait 60 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfterPermitPurchase = "";
		double dblAccountBalanceAfterPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfterPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfterPermitPurchase = Double.parseDouble(strAccountBalanceAfterPermitPurchase) * .01;
		}
		catch (Exception e) {}
		//Validate the permit was deducted correctly
		double dblExpectedAccountBalance = (Double.parseDouble(strStartingAccountBalance)) - dblPermitCost;
		if(dblExpectedAccountBalance == dblAccountBalanceAfterPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1903_CS_TUD_EAPOEE_LP_VCAPS_LE_LP_VSPS(objDictionary, strLicensePlateNumber); 
  	 	//Park in Lot - Again with the exist permit.
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Exit Before Initial Grace
		intInitialGracePeriodSeconds = (Integer.parseInt(strInitialGracePeriod) * 60) - 30; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Exit Before Initial Grace");
  		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfter2ndPermitPurchase = "";
		double dblAccountBalanceAfter2ndPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfter2ndPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfter2ndPermitPurchase = Double.parseDouble(strAccountBalanceAfter2ndPermitPurchase) * .01;
		}catch (Exception e) {}
		//Validate the re-park doesn't charge for another permit
		if(dblExpectedAccountBalance == dblAccountBalanceAfter2ndPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1903_CS_TUD_EAPOEE_LP_VCAPS_LE_LP_VSPS_2(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9019,groups={"Smoke"})
	public void AL1904_CS_TUE_EAPOEE_LP_VCAPS_LE_LP_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("TUE: True Up Enabled                                            ");
		Reporter.log("EAPOEE: End App Permit On Exit Enabled                          ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "True";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		String strStartingAccountBalance = objDictionary.get("strCurrentAccountBalance");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"1","hh:mm a");
  		String strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime,"0","h:mm a");
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		//Validate Andriod Device Lot Session
      	String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
	    //Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Store Lot Parking Id
  		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
  		//Validate Parking Session Before Exist
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	 	//Add Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfterPermitPurchase = "";
		double dblAccountBalanceAfterPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfterPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfterPermitPurchase = Double.parseDouble(strAccountBalanceAfterPermitPurchase) * .01;
		}
		catch (Exception e) {}
		//Validate the permit was deducted correctly
		double dblExpectedAccountBalance = (Double.parseDouble(strStartingAccountBalance)) - dblPermitCost;
		if(dblExpectedAccountBalance == dblAccountBalanceAfterPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1904_CS_TUE_EAPOEE_LP_VCAPS_LE_LP_VSPS(objDictionary, strLicensePlateNumber); 
  	 	//Park in Lot - Again with the exist permit.
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Exit Before Initial Grace
		intInitialGracePeriodSeconds = (Integer.parseInt(strInitialGracePeriod) * 60) - 30; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Exit Before Initial Grace");
  		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfter2ndPermitPurchase = "";
		double dblAccountBalanceAfter2ndPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfter2ndPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfter2ndPermitPurchase = Double.parseDouble(strAccountBalanceAfter2ndPermitPurchase) * .01;
		}catch (Exception e) {}
		//Validate the re-park doesn't charge for another permit
		if(dblExpectedAccountBalance == dblAccountBalanceAfter2ndPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1904_CS_TUE_EAPOEE_LP_VCAPS_LE_LP_VSPS_2(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9020,groups={"Smoke"})
	public void AL1905_CS_TUD_EAPOED_LP_VCAPS_LE_LP_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("TUD: True Up Disabled                                           ");
		Reporter.log("EAPOED: End App Permit On Exit Disabled                         ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		String strStartingAccountBalance = objDictionary.get("strCurrentAccountBalance");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"1","hh:mm a");
  		String strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime,"0","h:mm a");
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		//Validate Andriod Device Lot Session
      	String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
	    //Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Store Lot Parking Id
  		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
  		//Validate Parking Session Before Exist
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	 	//Add Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfterPermitPurchase = "";
		double dblAccountBalanceAfterPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfterPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfterPermitPurchase = Double.parseDouble(strAccountBalanceAfterPermitPurchase) * .01;
		}
		catch (Exception e) {}
		//Validate the permit was deducted correctly
		double dblExpectedAccountBalance = (Double.parseDouble(strStartingAccountBalance)) - dblPermitCost;
		if(dblExpectedAccountBalance == dblAccountBalanceAfterPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	//Park in Lot - Again with the exist permit.
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Exit Before Initial Grace
		intInitialGracePeriodSeconds = (Integer.parseInt(strInitialGracePeriod) * 60) - 30; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Exit Before Initial Grace");
		//Store Lot Parking Id
  		String strParkingId2 = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
  		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait 60 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfter2ndPermitPurchase = "";
		double dblAccountBalanceAfter2ndPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfter2ndPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfter2ndPermitPurchase = Double.parseDouble(strAccountBalanceAfter2ndPermitPurchase) * .01;
		}catch (Exception e) {}
		//Validate the re-park doesn't charge for another permit
		if(dblExpectedAccountBalance == dblAccountBalanceAfter2ndPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfter2ndPermitPurchase+")");}
		//Validate Initial Session
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1905_CS_TUD_EAPOED_LP_VCAPS_LE_LP_VSPS(objDictionary, strLicensePlateNumber); 
		//Validate 2nd Session
		objDictionary.put("strParkingId", strParkingId2);
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1905_CS_TUD_EAPOED_LP_VCAPS_LE_LP_VSPS_2(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9021,groups={"Smoke"})
	public void AL1906_CS_TUE_EAPOED_LP_VCAPS_LE_LP_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("CS: Concierge                                                   ");
		Reporter.log("TUD: True Up Enabled                                            ");
		Reporter.log("EAPOED: End App Permit On Exit Disabled                         ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		double dblPermitCost = 2.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("CA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		String strStartingAccountBalance = objDictionary.get("strCurrentAccountBalance");
		//Enroll In Concierge
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
  		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		strStartTime = clsCommonWeb.AddTimeToExistingTime(strStartTime,"1","hh:mm a");
  		String strStartTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime,"0","h:mm a");
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		//Validate Andriod Device Lot Session
      	String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
	    //Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Store Lot Parking Id
  		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
  		//Validate Parking Session Before Exist
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	 	//Add Wait
		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfterPermitPurchase = "";
		double dblAccountBalanceAfterPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfterPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfterPermitPurchase = Double.parseDouble(strAccountBalanceAfterPermitPurchase) * .01;
		}
		catch (Exception e) {}
		//Validate the permit was deducted correctly
		double dblExpectedAccountBalance = (Double.parseDouble(strStartingAccountBalance)) - dblPermitCost;
		if(dblExpectedAccountBalance == dblAccountBalanceAfterPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
  		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	  	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strStartTime2+ " "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strEndTime2+ " "+timeZoneAbbreviation);
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1906_CS_TUE_EAPOED_LP_VCAPS_LE_LP_VSPS(objDictionary, strLicensePlateNumber); 
  	 	//Park in Lot - Again with the exist permit.
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Exit Before Initial Grace
		intInitialGracePeriodSeconds = (Integer.parseInt(strInitialGracePeriod) * 60) - 30; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Exit Before Initial Grace");
  		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,45, "Wait 45 seconds Until Permit Information Get to Sentry Link");
  	 	String strAccountBalanceAfter2ndPermitPurchase = "";
		double dblAccountBalanceAfter2ndPermitPurchase = 0.0;
		try
		{
			strAccountBalanceAfter2ndPermitPurchase = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
			dblAccountBalanceAfter2ndPermitPurchase = Double.parseDouble(strAccountBalanceAfter2ndPermitPurchase) * .01;
		}catch (Exception e) {}
		//Validate the re-park doesn't charge for another permit
		if(dblExpectedAccountBalance == dblAccountBalanceAfter2ndPermitPurchase)
		{Reporter.log("The Permit Payment Deducted for the User Account Correctly");}
		else
		{clsMeter.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Permit Payment did not deduct correctly expected balance ("+dblExpectedAccountBalance+") actual balance ("+dblAccountBalanceAfterPermitPurchase+")");}
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1906_CS_TUE_EAPOED_LP_VCAPS_LE_LP_VSPS_2(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9022,groups={"Smoke"})
	public void AL1907_TS_PRE_PPSL_LP_VUE_LE_PEOASLV_VTN_UNOD_ROVP_VTN2_UNOD_ROVP_VTN3_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VUE: Violate Unpermitted Entrance                               ");
		Reporter.log("LE: Lot Exit                                                    ");
		Reporter.log("PEOASLV: PEO Officer Approve SL Violation                       ");
		Reporter.log("VTN: Validate Ticket Notification                               ");
		Reporter.log("UNOD: Update Next OverDue Date                                  ");
		Reporter.log("ROVP: Run Overdue Violation Processor                           ");
		Reporter.log("VTN2: Validate Ticket Notification #2                           ");
		Reporter.log("UNOD: Update Next OverDue Date                                  ");
		Reporter.log("ROVP: Run Overdue Violation Processor                           ");
		Reporter.log("VTN3: Validate Ticket Notification #3                           ");
		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = "";
  		String strLicensePlateState = "";
  		objDictionary.put("strLicensePlateState",strLicensePlateState);
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG"))
		{objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		if(strEnvironment.equals("PROD"))
		{
			//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Minnetonka");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"sp_global");
	  		//Update Kiosk Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Minnetonka","sp_global");
	  		strLicensePlateNumber = "MPSCB22";
	  		strLicensePlateState = "Washington";
		}
		else
		{
			//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
	  		//Update Kiosk Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","S&P Global");
	  		strLicensePlateNumber = "645786G";
	  		strLicensePlateState = "Washington";
	  	}
		strLicensePlateNumber = "645786G";
		strLicensePlateState = "Washington";
		objDictionary.put("strLicensePlateState",strLicensePlateState);
  		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "WA",strLotExitId,"False");
		//Deenroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//Update Kiosk Rate Blocks
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		objDictionary.put("strPermitStartTimePlusMinusMinutes","-65");
		objDictionary.put("strPermitEndTimePlusMinusMinutes","-5");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "WA",strLotEntryId);
		//Wait For Initial Grace To Expire
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Wait for Initial Grace Time to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 65;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for Initial Grace Period to expire");
		//Wait for Violatio  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,75, "Wait 75 seconds for Violation Id get to SL");
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
		objDictionary.put("strParkingId",strParkingId);
		//Get Lot Violation Id
		String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "WA",strLotExitId,"False");
		try {Thread.sleep(5000);}catch (Exception e) {}
		//CSR Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToLotViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLicensePlateState);
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(35000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","1");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","2");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","3");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #4 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF_Awaiting_Collections(objDictionary,strViolationId,"Test1.txt","3");
		//Awaiting Collections
		clsCommonWeb.SENTRYLINK_ValidateViolationAwaitingCollectionInSL(objDictionary, strViolationId);
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1907_TS_PRE_PPSL_LP_VUE_LE_PEOASLV_VTN_UNOD_ROVP_VTN2_UNOD_ROVP_VTN3_VSPS(objDictionary, strViolationId); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9023,groups={"Smoke"})
	public void AL1908_TS_PRE_PPSL_LP_VUE_LE_PEOASLV_VTN_UNOD_ROVP_VTN2_UNOD_ROVP_VTN3_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VUE: Violate Unpermitted Entrance                               ");
		Reporter.log("LE: Lot Exit                                                    ");
		Reporter.log("PEOASLV: PEO Officer Approve SL Violation                       ");
		Reporter.log("VTN: Validate Ticket Notification                               ");
		Reporter.log("UNOD: Update Next OverDue Date                                  ");
		Reporter.log("ROVP: Run Overdue Violation Processor                           ");
		Reporter.log("VTN2: Validate Ticket Notification #2                           ");
		Reporter.log("UNOD: Update Next OverDue Date                                  ");
		Reporter.log("ROVP: Run Overdue Violation Processor                           ");
		Reporter.log("VTN3: Validate Ticket Notification #3                           ");
		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = "76170T ";
  		String strLicensePlateState = "Kentucky";
  		objDictionary.put("strLicensePlateState",strLicensePlateState);
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Kiosk Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","S&P Global");
  		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		//clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "KY",strLotExitId,"False");
		//Deenroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//Update Kiosk Rate Blocks
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		objDictionary.put("strPermitStartTimePlusMinusMinutes","-65");
		objDictionary.put("strPermitEndTimePlusMinusMinutes","-5");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "KY",strLotEntryId);
		//Wait For Initial Grace To Expire
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Wait For Parking Id To get to SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Wait 30 seconds for Parking Id get to SL");
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
		objDictionary.put("strParkingId",strParkingId);
		//Wait for Initial Grace Time to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 65;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for Initial Grace Period to expire");
		//Wait for Violatio  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,50, "Wait 50 seconds for Violation Id get to SL");
		//Get Lot Violation Id
		String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "WA",strLotExitId,"False");
		//CSR Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToLotViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLicensePlateState);
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","1");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","2");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","3");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #4 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF_Awaiting_Collections(objDictionary,strViolationId,"Test1.txt","3");
		//Awaiting Collections
		clsCommonWeb.SENTRYLINK_ValidateViolationAwaitingCollectionInSL(objDictionary, strViolationId);
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1907_TS_PRE_PPSL_LP_VUE_LE_PEOASLV_VTN_UNOD_ROVP_VTN2_UNOD_ROVP_VTN3_VSPS(objDictionary, strViolationId); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9023,groups={"Smoke"})
	public void AL_TEST()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VUE: Violate Unpermitted Entrance                               ");
		Reporter.log("LE: Lot Exit                                                    ");
		Reporter.log("PEOASLV: PEO Officer Approve SL Violation                       ");
		Reporter.log("VTN: Validate Ticket Notification                               ");
		Reporter.log("UNOD: Update Next OverDue Date                                  ");
		Reporter.log("ROVP: Run Overdue Violation Processor                           ");
		Reporter.log("VTN2: Validate Ticket Notification #2                           ");
		Reporter.log("UNOD: Update Next OverDue Date                                  ");
		Reporter.log("ROVP: Run Overdue Violation Processor                           ");
		Reporter.log("VTN3: Validate Ticket Notification #3                           ");
		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = "76170T ";
  		String strLicensePlateState = "Kentucky";
  		objDictionary.put("strLicensePlateState",strLicensePlateState);
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Kiosk Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","S&P Global");
  		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		//clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "KY",strLotExitId,"False");
		//Deenroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//Update Kiosk Rate Blocks
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		objDictionary.put("strPermitStartTimePlusMinusMinutes","-65");
		objDictionary.put("strPermitEndTimePlusMinusMinutes","-5");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "KY",strLotEntryId);
		//Wait For Initial Grace To Expire
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Wait For Parking Id To get to SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Wait 30 seconds for Parking Id get to SL");
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
		objDictionary.put("strParkingId",strParkingId);
		//Wait for Initial Grace Time to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 65;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for Initial Grace Period to expire");
		//Wait for Violatio  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,50, "Wait 50 seconds for Violation Id get to SL");
		//Get Lot Violation Id
		String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "WA",strLotExitId,"False");
		//CSR Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToLotViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLicensePlateState);
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","1");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","2");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","3");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #4 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF_Awaiting_Collections(objDictionary,strViolationId,"Test1.txt","3");
		//Awaiting Collections
		clsCommonWeb.SENTRYLINK_ValidateViolationAwaitingCollectionInSL(objDictionary, strViolationId);
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL1907_TS_PRE_PPSL_LP_VUE_LE_PEOASLV_VTN_UNOD_ROVP_VTN2_UNOD_ROVP_VTN3_VSPS(objDictionary, strViolationId); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9000,groups={"Smoke"})
	public void AL_WIP_2()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = "645786G";
  		String strLicensePlateState = "Washington";
  		objDictionary.put("strLicensePlateState",strLicensePlateState);
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Kiosk Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","S&P Global");
  		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "WA",strLotExitId,"False");
		//Deenroll Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//Update Kiosk Rate Blocks
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		objDictionary.put("strPermitStartTimePlusMinusMinutes","-65");
		objDictionary.put("strPermitEndTimePlusMinusMinutes","-5");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "WA",strLotEntryId);
		String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
//  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
//		// Set the time zone to UTC
//	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//	    // Format the current date and time in UTC
//	    String strParkStartTimeUTC = dateFormat.format(new Date());

		
		
		//Wait For Initial Grace To Expire
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		
		
	    //Validate Mobile Parking Session
//		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
//      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
//      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
//      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
//      	String strMeterGroup = objDictionary.get("strMeterGroup");
//      	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
//		String strParkTimestamp = objDictionary.get("strParkTimestamp");
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Wait 30 seconds for Parking Id get to SL");
		
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
		objDictionary.put("strParkingId",strParkingId);
		
		//Wait for Initial Grace Time to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 65;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for Initial Grace Period to expire");
		//Wait for Violatio  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,50, "Wait 50 seconds for Violation Id get to SL");
		//Get Lot Violation Id
		String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
		if(strViolationId.isBlank())
		{
			System.out.println("MIH");
		}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "WA",strLotExitId,"False");
				
		
		
		//CSR Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToLotViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLicensePlateState);
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
				
		
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","1");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","2");
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","3");
	
		//Update Next OverDue Date
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #4 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","4");
			
		
		
		//Exit Lot
		//clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
//		try {Thread.sleep(15000);}catch (Exception e) {}
//		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "You have no active parking sessions", 1, "Exists", "");
//		androiddriver.quit();

//		driver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		//clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL_WIP(objDictionary, strViolationId); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=9000,groups={"Smoke"})
	public void AL_WIP_1()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		
		
//		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
//		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
//		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
//		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
//		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
//		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
//		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
//		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
//		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
//		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
//		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
//		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
//		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
//		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
//		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
//		//Update Kiosk Rate Blocks
//		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		
		
		//Add Or Update Permit Group
//		objDictionary.put("strPermitCost","2.00");
//		objDictionary.put("strPermitGroup","Student");
//		objDictionary.put("strPermitRate","Hourly 1");
//		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
//		objDictionary.put("strDaysBeforePermitStarts","-14");
//		objDictionary.put("strDaysBeforePermitEnds","-7");
//		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
//		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
//		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
//		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
//		String strMeterSpotName = objDictionary.get("strMeterSpotName");
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterSpotName);
//		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
//		String strMobileValidTo = objDictionary.get("strMobileValidTo");
//		String strMobileValidFromMinusMin = objDictionary.get("strMobileValidFromMinusMin");
//		String strMobileValidToMinusMin = objDictionary.get("strMobileValidToMinusMin");
//		String strPermitValidFrom = clsCommonMobile.StoreText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "strPermitValidFrom");
//		String strPermitValidTo = clsCommonMobile.StoreText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "strPermitValidFrom");
//		if(strPermitValidFrom.equals(strMobileValidFrom))
//		{
//			Reporter.log("The Text (Permit Valid From) with index (1) equaled (" + strMobileValidFrom + ")"+"");
//			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strPermitValidTo);
//		}
//		else if(strPermitValidFrom.equals(strMobileValidFromMinusMin))
//		{
//			Reporter.log("The Text (Permit Valid From) with index (1) equaled (" + strMobileValidFromMinusMin + ")"+"");
//			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidToMinusMin);
//			strMobileValidFrom = strMobileValidFromMinusMin;
//			strPermitValidFrom = strMobileValidToMinusMin;
//		}
//		else
//		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Permit Valid From) with index (1) did not equal (" + strMobileValidFrom + ") - actual value (" + strPermitValidFrom + ")");}
		//Format Dates for CSV
//		String strMobileValidFromFormat = "";
//		String strMobileValidToFormat = "";
//		try
//		{
//	        DateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a");
//			Date date = inputFormat.parse(strMobileValidFrom.replace("Valid From : ", "").replace(" at", ""));
//	        DateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
//	        strMobileValidFromFormat = outputFormat.format(date);
//	        date = inputFormat.parse(strMobileValidTo.replace("Valid To : ", "").replace(" at", ""));
// 	        outputFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
// 			strMobileValidToFormat = outputFormat.format(date);
//	    }
//		catch (Exception e) 
//		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,e.toString());}
//		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
//		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(20000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Dictionary Variables
//	  	String strBrowser = objDictionary.get("strBrowser");
//      	String strRemotePath = objDictionary.get("strRemotePath");
//      	//NTPS: Navigate To Parking Session
//	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
//	  	WebDriver driver = getDriver();
//  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
//		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
//		// Navigate to Configure Meter
//		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
//		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Parking Lot Sessions","Local");
//		//Lot Sessions Date Filter
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MINUTE, -5);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        String formattedDate = dateFormat2.format(cal.getTime());
//		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Lot Sessions", "Populate Date Range", "{T} From Date Time Entered",formattedDate);
//		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Lot Sessions", "Find Sessions", 1, "Local");
//		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Lot Sessions", "Show Total", 1, "Local");
//		try {Thread.sleep(1500);}catch (Exception e) {} 
//		String strRecordCount = clsCommonWeb.StoreText(objDictionary, driver, "Parking Lot Sessions", "Records", 0, "strRecordCount").replace("Records: ", "");
//		if(!strRecordCount.equals("0"))
//		{
//			clsCommonWeb.ClickLink(objDictionary, driver, "Parking Lot Sessions", "(csv)", 1);
//			try {Thread.sleep(1500);}catch (Exception e) {} 
//			clsCommonWeb.VerificationPointText(objDictionary, driver,  "Parking Lot Sessions", "Alert Message", 0, "Contains", "Creating a CSV, can be found in CSV Reports");
//			clsCommonWeb.ClickLink(objDictionary, driver, "Parking Lot Sessions", "CSV Reports", 1);
//			try {Thread.sleep(15000);}catch (Exception e) {} 
//			driver.navigate().refresh();
//			//Click Download File
//			clsCommonWeb.ClickLink(objDictionary, driver, "CSV Reports", "Download Parking Sessions", 1);
//			try {Thread.sleep(4000);}catch (Exception e) {} 
//			String fileName = clsCommonWeb.StoreText(objDictionary, driver, "CSV Reports", "CSV File Name", 0, "strDownloadName").replace("Download ", "");
//			String downloadDir = System.getProperty("user.home") + "/Downloads/";
//	        String filePath = downloadDir + fileName;
//	        try {
//	            // Check if file exists
//	            Path path = FileSystems.getDefault().getPath(filePath);
//	            if (!Files.exists(path))
//	            {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The file ("+fileName+") did not exist in ("+downloadDir+")", "Local");}
////	            else
////	            {driver.quit();}
//	            // Open the CSV file and count rows
//	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//	            String line;
//	            int rowCount = 0;
//	            // Read each line from the file
//	            while ((line = reader.readLine()) != null) {
//	                rowCount++;
//	                if(rowCount == 2)
//	                {
//	                	 // Split the line into values using a comma as the delimiter
//	                    String[] values = line.split(",");
//	                    // Define expected values
//	                    String[] expectedValues = {"true", "plate", "plate", strParkStartTime, strParkStartTimeUTC, strMobileValidFromFormat, "2024-02-13 08:07 PM", "2 minutes", "2", "LA0LOTAA", "Minnesota", "Lot Auto One"};
//	                    // Validate each value
//	                    for (int i = 0; i < values.length; i++) {
//	                    	String actualValue = values[i].trim().replaceAll("^\"|\"$", "");
//	                        if (!actualValue.equals(expectedValues[i].replaceAll("^\"|\"$", ""))) {
//	                            // Value doesn't match expected value
//	                            System.out.println("Validation failed for value at index " + i + ". Expected: " + expectedValues[i].replaceAll("^\"|\"$", "") + ", Actual: " + values[i].replaceAll("^\"|\"$", ""));
//	                        }
//	                    }
//	                    // Print success message if all values match
//	                    System.out.println("Validation succeeded. All values match expected values.");
//	                }
//	            }  	        
//	        	} catch (IOException e) 
//	        {
//	        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,e.toString(), "Local");
//	        }
//		}
//		else
//		{
//			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"No Parking Lot Sessions Existed", "Local");
//		}
//		driver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		//clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL_WIP(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//********************************************************************************************************************
	//ANDROID-LOT_TEST CASES - Daily
	//********************************************************************************************************************
	@Test(priority=9013,groups={"Smoke"})
	public void AL2000_PRE_PPSL_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "SL-7062|SL-7183");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
  		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		String strParkStartTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date());
  	 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		// Set the time zone to UTC
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    // Format the current date and time in UTC
	    String strParkStartTimeUTC = dateFormat.format(new Date());
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
		String strMeterName = objDictionary.get("strMeterName");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
		String strMobileValidTo = objDictionary.get("strMobileValidTo");
		String strMobileValidFromMinusMin = objDictionary.get("strMobileValidFromMinusMin");
		String strMobileValidToMinusMin = objDictionary.get("strMobileValidToMinusMin");
		String strPermitValidFrom = clsCommonMobile.StoreText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "strPermitValidFrom");
		String strPermitValidTo = clsCommonMobile.StoreText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "strPermitValidFrom");
		if(strPermitValidFrom.equals(strMobileValidFrom))
		{
			Reporter.log("The Text (Permit Valid From) with index (1) equaled (" + strMobileValidFrom + ")"+"");
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strPermitValidTo);
		}
		else if(strPermitValidFrom.equals(strMobileValidFromMinusMin))
		{
			Reporter.log("The Text (Permit Valid From) with index (1) equaled (" + strMobileValidFromMinusMin + ")"+"");
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidToMinusMin);
			strMobileValidFrom = strMobileValidFromMinusMin;
			strPermitValidFrom = strMobileValidToMinusMin;
		}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Permit Valid From) with index (1) did not equal (" + strMobileValidFrom + ") - actual value (" + strPermitValidFrom + ")");}
		//Format Dates for CSV
		String strMobileValidFromFormat = "";
		String strMobileValidToFormat = "";
		try
		{
	        DateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a");
			Date date = inputFormat.parse(strMobileValidFrom.replace("Valid From : ", "").replace(" at", ""));
	        DateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
	        strMobileValidFromFormat = outputFormat.format(date);
	        date = inputFormat.parse(strMobileValidTo.replace("Valid To : ", "").replace(" at", ""));
 	        outputFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
 			strMobileValidToFormat = outputFormat.format(date);
	   }
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,e.toString());}
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		// Navigate to Configure Meter
		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Parking Lot Sessions","Local");
		//Lot Sessions Date Filter
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -5);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        String formattedDate = dateFormat2.format(cal.getTime());
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Lot Sessions", "Populate Date Range", "{T} From Date Time Entered",formattedDate);
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Lot Sessions", "Find Sessions", 1, "Local");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Lot Sessions", "Show Total", 1, "Local");
		try {Thread.sleep(1500);}catch (Exception e) {} 
		String strRecordCount = clsCommonWeb.StoreText(objDictionary, driver, "Parking Lot Sessions", "Records", 0, "strRecordCount").replace("Records: ", "");
		if(!strRecordCount.equals("0"))
		{
			clsCommonWeb.ClickLink(objDictionary, driver, "Parking Lot Sessions", "(csv)", 1);
			try {Thread.sleep(1500);}catch (Exception e) {} 
			clsCommonWeb.VerificationPointText(objDictionary, driver,  "Parking Lot Sessions", "Alert Message", 0, "Contains", "Creating a CSV, can be found in CSV Reports");
			clsCommonWeb.ClickLink(objDictionary, driver, "Parking Lot Sessions", "CSV Reports", 1);
			try {Thread.sleep(15000);}catch (Exception e) {} 
			driver.navigate().refresh();
			//Click Download File
			clsCommonWeb.ClickLink(objDictionary, driver, "CSV Reports", "Download Parking Sessions", 1);
			try {Thread.sleep(4000);}catch (Exception e) {} 
			String fileName = clsCommonWeb.StoreText(objDictionary, driver, "CSV Reports", "CSV File Name", 0, "strDownloadName").replace("Download ", "");
			String downloadDir = System.getProperty("user.home") + "/Downloads/";
	        String filePath = downloadDir + fileName;
	        try {
	            // Check if file exists
	            Path path = FileSystems.getDefault().getPath(filePath);
	            if (!Files.exists(path))
	            {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The file ("+fileName+") did not exist in ("+downloadDir+")", "Local");}
//	            else
//	            {driver.quit();}
	            // Open the CSV file and count rows
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            String line;
	            int rowCount = 0;
	            // Read each line from the file
	            while ((line = reader.readLine()) != null) {
	                rowCount++;
	                if(rowCount == 2)
	                {
	                	 // Split the line into values using a comma as the delimiter
	                    String[] values = line.split(",");
	                    // Define expected values
	                    String[] expectedValues = {"true", "plate", "plate", strParkStartTime, strParkStartTimeUTC, strMobileValidFromFormat, "2024-02-13 08:07 PM", "2 minutes", "2", "LA0LOTAA", "Minnesota", "Lot Auto One"};
	                    // Validate each value
	                    for (int i = 0; i < values.length; i++) {
	                    	String actualValue = values[i].trim().replaceAll("^\"|\"$", "");
	                        if (!actualValue.equals(expectedValues[i].replaceAll("^\"|\"$", ""))) {
	                            // Value doesn't match expected value
	                            System.out.println("Validation failed for value at index " + i + ". Expected: " + expectedValues[i].replaceAll("^\"|\"$", "") + ", Actual: " + values[i].replaceAll("^\"|\"$", ""));
	                        }
	                    }
	                    // Print success message if all values match
	                    System.out.println("Validation succeeded. All values match expected values.");
	                }
	            }  	        
	        	} catch (IOException e) 
	        {
	        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,e.toString(), "Local");
	        }
		}
		else
		{
			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"No Parking Lot Sessions Existed", "Local");
		}
		driver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2000_PRE_PPSL_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9014,groups={"Smoke"})
	public void AL2001_PRE_ATPPCA_VNPF()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PRE: Permit Rate Expired (Allowed to purchase in SL only)       ");
		Reporter.log("ATPPCA: Attempt to Purchase Permit Consumer App                 ");
		Reporter.log("VNPF: Validate No Permit Found                                  ");
		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");}
		else if(strEnvironment.equals("PROD")){objDictionary.put("strLotTaxRate","(0.000%)-Lot Automation-ParkingFeeLineItem");}
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", strMeterGroup);
		try {Thread.sleep(4000);}catch (Exception e) {}
		//It uses to say Validate No Permit Found it now says No matching rates available.
		clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Error Message", 1, "Contains", "No matching rates available.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
		androiddriver.quit();
    	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit SL Then Park
	@Test(priority=9015,groups={"Smoke"})
	public void AL2002_PPSL_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "SL-7062");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
		String strMeterName = objDictionary.get("strMeterName");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
		String strMobileValidTo = objDictionary.get("strMobileValidTo");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2002_PPSL_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9016,groups={"Smoke"})
	public void AL2002_A_PPSL_LPILP_VVE_LE_AP_VSPS()
	{
		objDictionary.put("strAssociatedBug", "SL-7062");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LPILP: Lot Park Incorrect LP                                    ");
		Reporter.log("VVE: Validate Violation Exist                                   ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("AP: Approve Violation                                           ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
  		//Update Kiosk Rate Blocks 
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
  		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLicensePlateNumberBadRead = strLicensePlateNumber.substring(0, strLicensePlateNumber.length() - 1);
		objDictionary.put("strLicensePlateState","Minnesota");
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumberBadRead, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot - LP <> Match Permit
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumberBadRead, "MN",strLotEntryId);
		//Wait For Initial Grace To Expire
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Wait For Parking Id To get to SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Wait 30 seconds for Parking Id get to SL");
		//Wait for Initial Grace Time to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 65;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for Initial Grace Period to expire");
		//Wait for Violation  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,50, "Wait 50 seconds for Violation Id get to SL");
		//Get Parking Session Id
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strMeterGroup = objDictionary.get("strMeterGroup");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		String strParkingId = clsCommonWeb.SENTRYLINK_StoreLotParkingSessionId(objDictionary, driver);
		objDictionary.put("strParkingId",strParkingId);
		//Get Lot Violation Id
		String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
		//Exit Lot - Correct LP
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Wait for Violation  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Wait 15 seconds for images to get to SL");
		//Approve Violation Ticket SL
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Star 5", 1);
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "DoubleRightArrorBar", 1, "Local");
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Star 5", 1);
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Update Plate", 1, "Local");
		try {Thread.sleep(2000);} catch (Exception e) {}
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Display Violation", 1, "Local");
		try {Thread.sleep(2000);} catch (Exception e) {}
		clsCommonWeb.ClickLink(objDictionary, driver,  "Parking Session", "Approve/Reject Violation", 1);
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate Approve/Reject Violation","{RB} Violation Type|{T} Notes", "Approve Violation|Grace Period Exceeded");
		try {Thread.sleep(1000);} catch (Exception e) {}
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Approve/Reject Violation", 1, "Local");
		try {Thread.sleep(2000);} catch (Exception e) {}
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Yes, Proceed", 1, "Local");
		try {Thread.sleep(2000);} catch (Exception e) {}
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Session", "SuccessAlertMessage",1, "Contains","The violation has successfully been approved.");
		driver.quit();
		//VSPS: Validate Sentry Parking Session 
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2002_A_PPSL_LPILP_VVE_LE_AP_VSPS(objDictionary, strLicensePlateNumber, strViolationId); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9016,groups={"Smoke"})
	public void AL2002_B_PPSL_LPILP_VVE_LE_ULP_VSPS()
	{
		objDictionary.put("strAssociatedBug", "SL-7062");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Permit Match Tolerance = 0
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LPILP: Lot Park Incorrect LP                                    ");
		Reporter.log("VVE: Validate Violation Exist                                   ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("ULP: Update License Plate                                       ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
  		//Update Kiosk Rate Blocks 
//		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
//		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
//		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
//		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
//		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
//		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
//		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
//		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
//		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
//		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
//		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
//		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
//		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
//		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
//		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
//		//Update Kiosk Rate Blocks
//		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
  		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLicensePlateNumberBadRead = strLicensePlateNumber.substring(0, strLicensePlateNumber.length() - 1);
		objDictionary.put("strLicensePlateState","Minnesota");
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumberBadRead, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Park in Lot - LP <> Match Permit
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumberBadRead, "MN",strLotEntryId);
		//Wait For Initial Grace To Expire
		int intInitialGracePeriodSeconds = Integer.parseInt(strInitialGracePeriod) * 60; 
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriodSeconds, "Wait "+intInitialGracePeriodSeconds+" seconds Until Initial Grace Time Has Expired");
		//Wait For Parking Id To get to SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Wait 30 seconds for Parking Id get to SL");
		//Wait for Initial Grace Time to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 65;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for Initial Grace Period to expire");
		//Wait for Violatio  To get the SL
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait 60 seconds for Violation Id get to SL");
		//Get Parking Session Id
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strMeterGroup = objDictionary.get("strMeterGroup");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		String strParkingId = clsCommonWeb.SENTRYLINK_StoreLotParkingSessionId(objDictionary, driver);
		objDictionary.put("strParkingId",strParkingId);
		//Get Lot Violation Id
		String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
		//Exit Lot - Correct LP
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Approve Violation Ticket SL
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Star 5", 1);
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "DoubleRightArrorBar", 1, "Local");
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Star 5", 1);
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Update License Plate","{T} License Plate-Violation Dialog", strLicensePlateNumber);
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Update Plate", 1, "Local");
		driver.quit();
		//VSPS: Validate Sentry Parking Session 
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2002_B_PPSL_LPILP_VVE_LE_ULP_VSPS(objDictionary, strLicensePlateNumber, strViolationId); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//Purchase Permit CA Then Park 
	@Test(priority=9016,groups={"Smoke"})
	public void AL2003_LP_PPSL_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "SL-7062");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PPSL: Purchase Permit Sentry Link                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		//Exit Lot If parked
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA0"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Add Or Update Permit Group
		double dblPermitCost = 2.00;
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
		//Validate Mobile Parking Session
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Permit
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
		String strMeterName = objDictionary.get("strMeterName");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
		String strMobileValidFrom = objDictionary.get("strMobileValidFrom");
		String strMobileValidTo = objDictionary.get("strMobileValidTo");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
      	//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e) 
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2003_LP_PPSL_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park 
	@Test(priority=9017,groups={"Smoke"})
	public void AL2101_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		TimeConverter clsTimeConverter = new TimeConverter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP_CA: Purchase Permit Consumer App                              ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "8";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Delete Active Permits
		//clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
  		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
  		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//Select Date
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
   	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	String strExpectedValidForDates = "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime;
   	 	if(strValidForDates.equals(strExpectedValidForDates))
   	   	{Reporter.log("The Text (Valid for dates) with equaled ("+strExpectedValidForDates+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal ("+strExpectedValidForDates+") - actual value (" + strValidForDates + ")");}
	 	//Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convert12HourToSingleDigitHour(strStartTime);
   		String strSLEndTime = clsTimeConverter.convert12HourToSingleDigitHour(strEndTime);
   		objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 seconds before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		//Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
    	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
       	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		String strActualPermitParkTime =  datePermitFormat.format(currentTime);
		String strActualPermitParkTimePlusMinute =  clsCommonWeb.AddTimeToExistingTime(strActualPermitParkTime, "1","h:mm a");
		//Validate Parked At
		String strParkedAt = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strParkedAt");
	 	if(strParkedAt.equals("Parked Today at "+strActualPermitParkTime))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTime+")");}
	 	else if(strParkedAt.equals("Parked Today at "+strActualPermitParkTimePlusMinute))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Parked at) did not equal (Parked Today at "+strActualPermitParkTime+") - actual value (" + strParkedAt + ")");}
		//Validate Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	  	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2101_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9018,groups={"Smoke"})
	public void AL2102_PPMD_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		TimeConverter clsTimeConverter = new TimeConverter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit Multiple day                                ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
   	 	//Add Day
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Plus", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//Select Date
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $24.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $24.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
   	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	String strExpectedValidForDates = "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDayPlus1+" at "+strEndTime;
   	 	if(strValidForDates.equals(strExpectedValidForDates))
	 	{Reporter.log("The Text (Valid for dates) with equaled ("+strExpectedValidForDates+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal ("+strExpectedValidForDates+") - actual value (" + strValidForDates + ")");}
   	 	//Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convert12HourToSingleDigitHour(strStartTime);
		String strSLEndTime = clsTimeConverter.convert12HourToSingleDigitHour(strEndTime);
		objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2Plus2+" "+strSLEndTime+ " "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 seconds before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		//Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
    	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
       	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
		String strActualPermitParkTime =  datePermitFormat.format(currentTime);
		String strActualPermitParkTimePlusMinute =  clsCommonWeb.AddTimeToExistingTime(strActualPermitParkTime, "1","h:mm a");
		//Validate Parked At
		String strParkedAt = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strParkedAt");
	 	if(strParkedAt.equals("Parked Today at "+strActualPermitParkTime))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTime+")");}
	 	else if(strParkedAt.equals("Parked Today at "+strActualPermitParkTimePlusMinute))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Parked at) did not equal (Parked Today at "+strActualPermitParkTime+") - actual value (" + strParkedAt + ")");}
		//Validate Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDayPlus1+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	  	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - (dblPermitCost *2);
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2102_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park Unlock
	@Test(priority=9019,groups={"Smoke"})
	public void AL2201_UL_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		TimeConverter clsTimeConverter = new TimeConverter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "9";
		String strEndHour = "18";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4500);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
	 	//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
     	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
     	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	String strExpectedValidForDates = "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime+"";
     	if(strValidForDates.equals(strExpectedValidForDates))
	 	{Reporter.log("The Text (Valid for dates) with equaled ("+strExpectedValidForDates+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal ("+strExpectedValidForDates+") - actual value (" + strValidForDates + ")");}
	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convert12HourToSingleDigitHour(strStartTime);
		String strSLEndTime = clsTimeConverter.convert12HourToSingleDigitHour(strEndTime);
	 	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
		//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 minutes before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
    	//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//Validate Park At
		String strActualPermitParkTime =  datePermitFormat.format(currentTime);
		String strActualPermitParkTimePlusMinute =  clsCommonWeb.AddTimeToExistingTime(strActualPermitParkTime, "1","h:mm a");
		//Validate Parked At
		String strParkedAt = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strParkedAt");
	 	if(strParkedAt.equals("Parked Today at "+strActualPermitParkTime))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTime+")");}
	 	else if(strParkedAt.equals("Parked Today at "+strActualPermitParkTimePlusMinute))
	 	{Reporter.log("The Text (Parked at) with equaled (Parked Today at "+strActualPermitParkTimePlusMinute+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Parked at) did not equal (Parked Today at "+strActualPermitParkTime+") - actual value (" + strParkedAt + ")");}
		//Validate Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	 	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2201_UL_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//Purchase Permit CA Then Park True_Up & Unlock 
	@Test(priority=9020,groups={"Smoke"})
	public void AL2301_TU_UL_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		TimeConverter clsTimeConverter = new TimeConverter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Consumer App                                ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "3";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "3";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		//UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AC").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	//Validate Permit Purchase
  	 	String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	
  	 	//I think this changes depending on the time of day test is run. (7 pm)
  	 	
  	 	String strExpectedValidForDates = "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime;
  	 	if(strValidForDates.equals(strExpectedValidForDates))
	 	{Reporter.log("The Text (Valid for dates) with equaled ("+strExpectedValidForDates+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal ("+strExpectedValidForDates+") - actual value (" + strValidForDates + ")");}
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
    	//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
  		//Store SL Permit Times Before Park
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Store SL Permit Times After Park
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convert12HourToSingleDigitHour(strStartTime);
		String strSLEndTime = clsTimeConverter.convert12HourToSingleDigitHour(strEndTime);
	 	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
		//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
	 	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2301_TU_UL_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Purchase Permit CA Then Park Openlot placeholder sessions & True Up & Unlock
	@Test(priority=9021,groups={"Smoke"})
	public void AL2401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		TimeConverter clsTimeConverter = new TimeConverter();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("OPS: Openlot placeholder sessions                               ");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "255";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "255";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "15";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "15";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "15";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "15";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "15";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		objDictionary.put("strOpenlotPlaceholderSessions","True");
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AC").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	//Calculate Permit Times
  	 	String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
  	 	//Validate Permit Purchase
		String strValidForDates = clsCommonMobile.StoreText(objDictionary, androiddriver, "Lot Permit Payment", "Valid for dates", 1, "strValidForDates");
   	 	String strExpectedValidForDates = "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime;
		if(strValidForDates.equals(strExpectedValidForDates))
	 	{Reporter.log("The Text (Valid for dates) with equaled ("+strExpectedValidForDates+")");}
	 	else
	 	{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Text (Valid for dates) did not equal ("+strExpectedValidForDates+") - actual value (" + strValidForDates + ")");}
   	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convert12HourToSingleDigitHour(strStartTime);
		String strSLEndTime = clsTimeConverter.convert12HourToSingleDigitHour(strEndTime);
	    objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 60 seconds before parking (This time gets added
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Remain Parked for 60 seconds"); 
    	//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//Permit Park Time
  	 	Date currentTime = new Date();
  	 	SimpleDateFormat datePermitFormat = new SimpleDateFormat("h:mm a");
  	 	String strPermitParkTime =  datePermitFormat.format(currentTime);
  	 	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strStartTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strPermitParkTime);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Park Then Purchase Permit CA
	@Test(priority=9022,groups={"Smoke"})
	public void AL2501_LP_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		TimeConverter clsTimeConverter = new TimeConverter();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PP: Purchase Permit Consumer app                                ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
	 	//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convertTo24HourFormat(strStartTime);
		String strSLEndTime = clsTimeConverter.convertTo24HourFormat(strEndTime);
	    objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Wait 30 minutes before parking
	  	clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds"); 
    	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2501_LP_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Park Then Purchase Permit CA Park True_Up & Unlock 
	@Test(priority=9023,groups={"Smoke"})
	public void AL2601_UL_LP_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		TimeConverter clsTimeConverter = new TimeConverter();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
	 	//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  	 	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convertTo24HourFormat(strStartTime);
		String strSLEndTime = clsTimeConverter.convertTo24HourFormat(strEndTime);
	 	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2601_UL_LP_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9024,groups={"Smoke"})
	public void AL2701_TU_UL_LP_OCA_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "186172262");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		TimeConverter clsTimeConverter = new TimeConverter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("OCA: Open Consumer App                                          ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
     	//Function-Change Municipality Before Clicking On Meter Tag. 
      	//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	try {Thread.sleep(5000);}catch (Exception e) {}
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convertTo24HourFormat(strStartTime);
		String strSLEndTime = clsTimeConverter.convertTo24HourFormat(strEndTime);
	 	objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
  		//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2701_TU_UL_LP_OCA_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=9025,groups={"Smoke"})
	public void AL2702_TU_UL_OCA_LP_PP_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		TimeConverter clsTimeConverter = new TimeConverter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("TU: True Up                                                     ");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("OCA: Open Consumer App                                          ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PP: Purchase Permit Sentry Link                                 ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "True";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 12.00;
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AB").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		String strStartHour = "0";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		//SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
      	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		//try {Thread.sleep(5000);}catch (Exception e) {}
  		//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDayPlus1 =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","1");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strDay2Plus1 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
		//Navigate to Meter Payment
      	clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");try {Thread.sleep(1000);}catch (Exception e) {}
      	//Populate Selected Municipality
      	String strMunicipality = objDictionary.get("strMunicipality");
      	clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", 1, "Contains", "Select Municipality");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Municipality", strMunicipality);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Lot", "Lot Auto One");
		try {Thread.sleep(4000);}catch (Exception e) {}
   	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Lot Permit Payment", "Select Permit", "Daily - $12.00");
   	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK - Select Date", 0);
	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "Purchase Permit", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Lot Name",  1,  "Value",  "LOT AUTO ONE");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "License Plate",  1,  "Value",  "License Plate: "+strLicensePlateNumber);
  	 	//clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Selected Hour",  1,  "Value",  "Selected Hours: 1");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Parking Fee",  1,  "Value",  "Parking Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Total Fee",  1,  "Value",  "Total Fee: $12.00");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Note",  1,  "Value",  "Note: Parking reminders are provided as a best effort delivery service and not guaranteed.");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "YES - Purchase", 0);
  	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Lot Permit Payment", "SENTRY MOBILE ACCOUNT", 0);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Your payment was successful",  1,  "Value",  "Your payment was successful.");
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for dates",  1,  "Value",  "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for plates",  1,  "Value",  "Valid for plates : "+strLicensePlateNumber);
  	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver,  "Lot Permit Payment",  "Valid for lots",  1,  "Value",  "Valid for lots : Lot Auto One");
  	 	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Lot Permit Payment", "OK", 0);
  	 	//Store SL Permit Times
  	 	try {Thread.sleep(25000);}catch (Exception e) {}
  	 	clsHttpConnections.StoreActivePermitData(objDictionary,"");
	  	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	// Get time zone abbreviation
  	 	Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    //Store Permit Information
   	 	String strSLStartTime = clsTimeConverter.convertTo24HourFormat(strStartTime);
		String strSLEndTime = clsTimeConverter.convertTo24HourFormat(strEndTime);
	    objDictionary.put("strPermitInformation", "Valid From "+strDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strDay2+" "+strSLEndTime+" "+timeZoneAbbreviation);
		//Validate Parking Session.
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
      	String strMeterGroup = objDictionary.get("strMeterGroup");
      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
		//Validate Active Permit
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
  		String strMeterName = objDictionary.get("strMeterName");
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
		String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  	  	String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Validate Parking Session Removed from CA
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
		androiddriver.quit();
		//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2702_TU_UL_OCA_LP_PP_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//Park Then Purchase Permit Kiosk Park True_Up & Unlock 
	@Test(priority=9026,groups={"Smoke"})
	public void AL2801_UL_LP_PPK_VCAPS_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		TimeConverter clsTimeConverter = new TimeConverter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("UL: Unlock                                                      ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("PPK: Purchase Permit Kiosk                                      ");
		Reporter.log("VCAPS: Validate CA Parking Session                              ");
  		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "10";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "On"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		String strEndAppPermitsOnExit = "False";objDictionary.put("strEndAppPermitsOnExit",strEndAppPermitsOnExit);
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate.
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","12.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Daily");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		String strStartHour = "6";
		String strEndHour = "23";
		objDictionary.put("strStartHour",strStartHour);
		objDictionary.put("strEndHour",strEndHour);
		//Get Start and End times
		String strStartTime = clsTimeConverter.convertHourTo12HourFormat(strStartHour);
		//Subtract Initial Grace From Start Time (There currently is a bug for this and will eventually be removed)
		strStartTime = clsTimeConverter.addOrSubtractMinutesFromCurrentTime(strStartTime, "-"+strInitialGracePeriod,"hh:mm a");
		String strEndTime = clsTimeConverter.convertHourTo12HourFormat(strEndHour);
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-14");
		objDictionary.put("strDaysBeforePermitEnds","-7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		try {Thread.sleep(30000);}catch (Exception e) {}
		//Park in Lot
  		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
  		String strPermitParkTime = objDictionary.get("strPermitParkTimestamp");
  		String strFromDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		String strToDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","1");
		//Purchase at Kiosk
		String strHost = objDictionary.get("strHost");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_select_permit.py 0");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_enter_plate.py "+strLicensePlateNumber);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_add_hour.py");
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_card_pay.sh");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"parking_lot_no_receipt.py");
		try {Thread.sleep(9000);}catch (Exception e) {}
		//Store SL Permit Times
  	 	clsHttpConnections.StoreActivePermitDataForKioskPurchase(objDictionary,"");
  	 	String strValidFromTimeBeforePark = objDictionary.get("strValidFromTime");
	  	String strValidToTimeBeforePark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromTimeBeforePark)){Reporter.log("The Permit Start Time ("+strStartTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit Start time did not equal ("+strStartTime+")-Actual Value ("+strValidFromTimeBeforePark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToTimeBeforePark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToTimeBeforePark+")");}
	  	//Store SL Permit Times
    	clsHttpConnections.StoreActivePermitDataForKioskPurchase(objDictionary, "");
	  	String strValidFromAfterPark = objDictionary.get("strValidFromTime");
	  	String strValidToAfterPark = objDictionary.get("strValidToTime");
	  	//Validate Permit Start Time
	  	if(strStartTime.equals(strValidFromAfterPark)){Reporter.log("The Permit Start Time ("+strPermitParkTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit Start time did not equal ("+strEndTime+")-Actual Value ("+strValidFromAfterPark+")");}
	  	//Validate Permit End Time
	  	if(strEndTime.equals(strValidToAfterPark)){Reporter.log("The Permit End Time ("+strEndTime+") was correct");}
	  	else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "The permit End time did not equal ("+strEndTime+")-Actual Value ("+strValidToAfterPark+")");}
	  	//Validate Parking Session.
//	  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
//	      	String strMeterGroup = objDictionary.get("strMeterGroup");
//	      	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Value", "Lot : "+strMeterGroup);
//			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
//			String strParkTimestamp = objDictionary.get("strParkTimestamp");
//			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "Value", "Parked Today at "+strParkTimestamp);
//			//Validate Active Permit
//	  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
//	  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "Click to view active permits", 0);
//	  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted License Plate", 1, "Value", "Permit valid for Vehicle(s) : "+strLicensePlateNumber);
//	  		String strMeterName = objDictionary.get("strMeterName");
//	  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permitted Lots", 1, "Value", "Lot(s) : "+strMeterName);
//	  		String strMobileValidFrom = "Valid From : "+strDay+" at "+strValidFromTimeBeforePark; 
//			String strMobileValidTo = "Valid To : "+strDay+" at "+strValidToTimeBeforePark; 
//	  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid From", 1, "Value", strMobileValidFrom);
//	  		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permits", "Permit Valid To", 1, "Value", strMobileValidTo);
//	  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permits", "Account Details", 0);
//			clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Pay for lot");
  	 	//Validate Money Is deducted from users account
//  	 	String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
//  	 	double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
//  	  	String strCurrentAccountBalance = "";
//  		double dblStringUserAccountBalance = 0.0;
//  		try
//  		{
//  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
//  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);
//  		}catch (Exception e) {}
//  		DecimalFormat df = new DecimalFormat("#.##");
//		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
//		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
//		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
//		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
//		else
//		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Store Lot Parking Id
		String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		
		//Validate Parking Session Removed from CA
//			try {Thread.sleep(5000);}catch (Exception e) {}
//			clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
//			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 1, "Does Not Exist", "");
//			androiddriver.quit();
		//Validate Parking Session
		Date date = new Date();
  	    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
  	    boolean isDaylightSavingTime = timeZone.inDaylightTime(date);
  	    String timeZoneAbbreviation = isDaylightSavingTime ? "CDT" : "CST";
  	    String strSLStartTime = clsTimeConverter.convert12HourToSingleDigitHour(strStartTime);
	 	objDictionary.put("strPermitInformation", "Valid From "+strFromDay2+" "+strSLStartTime+" "+timeZoneAbbreviation+" Valid To "+strToDay2+" "+strEndTime+" "+timeZoneAbbreviation);
	 	//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_AL2801_UL_LP_PPK_VCAPS_LE_VSPS(objDictionary, strLicensePlateNumber); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	//BOOT NOTICE
	@Test(priority=93000,groups={"Smoke"})
	public void AL3000_LP_BN_UEV_VV_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("BN: Boot Notice                                                 ");
		Reporter.log("UEV: Unpermitted Entrance Violation                             ");
		Reporter.log("VV: Violation Voided                                            ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		
		//Enable Ticket Service Lob Mailing
		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"AutomationTicketService");
		//Enable Look Up Service s&p
		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"AutomationLookUp");
		//Update Kiosk Group Ticket Service
		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","AutomationLookUp");
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
	  	try {Thread.sleep(60000);}catch (Exception e) {}
	  	//Boot Time Before
	  	String takenAt = OffsetDateTime.now(ZoneId.of("America/Chicago")).toInstant().truncatedTo(ChronoUnit.SECONDS).toString();
	  	//1 Minute Initial Grace
	  	//try {Thread.sleep(9000);}catch (Exception e) {}
	  	clsHttpConnections.CURL_Lot_Boot_Notice(objDictionary,strLicensePlateNumber, takenAt); 
	  	try {Thread.sleep(85000);}catch (Exception e) {}
	  	//Store Lot Parking Id
	  	String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
	  	//Get Lot Violation Id
	  	String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
	  	try {Thread.sleep(25000);}catch (Exception e) {}
	  	clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	  	try {Thread.sleep(65000);}catch (Exception e) {}
	  	//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
		clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_BOOT_NOTICE(objDictionary, strViolationId); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=93001,groups={"Smoke"})
	public void AL3001_LP_UEV_BN_VV_LE_VSPS()
	{
		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("UEV: Unpermitted Entrance Violation                             ");
		Reporter.log("BN: Boot Notice                                                 ");
		Reporter.log("VV: Violation Voided                                            ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VSPS: Validate Sentry Parking Session                           ");
  		Reporter.log("****************************************************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";objDictionary.put("strCoinTimePuchaseLimit", strCoinTimePuchaseLimit);
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strCreditCardIncrementTime = "15";objDictionary.put("strCreditCardIncrementTime", strCreditCardIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "0";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "0";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "0";objDictionary.put("strHandicapViolationGrace", strHandicapViolationGrace);
		String strNoParkingGrace = "0";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";objDictionary.put("strParkingShortSessionSec", strParkingShortSessionSec);
		String strSetImageSendBeforeViolation = "45";objDictionary.put("strSetImageSendBeforeViolation", strSetImageSendBeforeViolation);
		String strUnlockValue = "Off"; objDictionary.put("strUnlockValue", strUnlockValue);
		String strUnlockTime = "1";objDictionary.put("strUnlockTime", strUnlockTime);
		String strUnlockMax = "1";objDictionary.put("strUnlockMax", strUnlockMax);
		String strTrueUp = "False";objDictionary.put("strTrueUp", strTrueUp);
		double dblPermitCost = 2.00;
		
//		//Enable Ticket Service Lob Mailing
//		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"AutomationTicketService");
//		//Enable Look Up Service s&p
//		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"AutomationLookUp");
//		//Update Kiosk Group Ticket Service
//		clsCommonWeb.SENTRYLINK_UpdateKioskGroupTicketService(objDictionary,"Lob Mailing","AutomationLookUp");

		
		//Update Kiosk Rate Blocks
		SENTRYLINK_UpdateKioskRateBlocks(objDictionary);
		try {Thread.sleep(30000);}catch (Exception e) {}
		
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		// UpdateMeterSettings
		clsMeter.SENTRYMETER_UpdateMetertSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		String strSpaceName = "LOT";
		String strLicensePlateNumber = ("LA1"+strSpaceName+"AA").toUpperCase();
		String strLotEntryId = objDictionary.get("strLotEntryId");
		String strLotExitId = objDictionary.get("strLotExitId");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
		//Add Or Update Permit Group
		objDictionary.put("strPermitCost","2.00");
		objDictionary.put("strPermitGroup","Student");
		objDictionary.put("strPermitRate","Hourly 1");
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strDaysBeforePermitStarts","-7");
		objDictionary.put("strDaysBeforePermitEnds","7");
		objDictionary.put("strLotTaxRate","(0.000%)-Lot Auto One-ParkingFeeLineItem");
		//Permit Rate Expired
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitRates(objDictionary);
		//Delete Active Permits
		clsHttpConnections.JsonDeleteAllActivePermits(objDictionary,"");
		//Remove all License Plate
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Park in Lot
	  	clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN",strLotEntryId);
	  	try {Thread.sleep(30000);}catch (Exception e) {}
	  	//1 Minute Initial Grace
	  	try {Thread.sleep(120000);}catch (Exception e) {}
	  	//Store Lot Parking Id
	  	String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);objDictionary.put("strParkingId", strParkingId);
	  	try {Thread.sleep(15000);}catch (Exception e) {}
	  	//Get Lot Violation Id
	  	String strViolationId = clsHttpConnections.GetJsonViolationIdUsingParkingSession(objDictionary);
	  	try {Thread.sleep(30000);}catch (Exception e) {}
	  	clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN",strLotExitId,"False");
	  	//Boot Time Before
	  	String takenAt = OffsetDateTime.now(ZoneId.of("America/Chicago")).toInstant().truncatedTo(ChronoUnit.SECONDS).toString();
	  	//Validate Parking Session
		Android_Lot_ParkingSessions clsAndroidLotParkingSessions = new Android_Lot_ParkingSessions ();
	 	clsHttpConnections.CURL_Lot_Boot_Notice(objDictionary,strLicensePlateNumber, takenAt); 
	  	clsAndroidLotParkingSessions.SENTRYLINK_ValidateParkingSessionHistory_BOOT_NOTICE_2(objDictionary, strViolationId); 
  	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	
	//KIOSK FUCTION
	public void SENTRYLINK_UpdateKioskRateBlocks(Map<String, String> objDictionary)
 	{
		//Update Kiosk Group Settings
		SENTRYLINK_UpdateKioskGroupSettings(objDictionary);
		//Set Lot Rate Reset
		String strDeviceId = objDictionary.get("strDeviceId");
		String strRateBlockGroup = objDictionary.get("strRateBlockGroup");
		String strMaximumDuration = objDictionary.get("strMaximumDuration");
		String strCoinTimePuchaseLimit = objDictionary.get("strCoinTimePuchaseLimit");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
		String strCreditCardIncrementTime = objDictionary.get("strCreditCardIncrementTime");
		String strInitialGracePeriod = objDictionary.get("strInitialGracePeriod");
		String strViolationGracePeriod = objDictionary.get("strViolationGracePeriod");
		String strHandicapInitialGracePeriod = objDictionary.get("strHandicapInitialGracePeriod");
		String strHandicapViolationGrace = objDictionary.get("strHandicapViolationGrace");
		String strNoParkingGrace = objDictionary.get("strNoParkingGrace");
		String strParkingShortSessionSec = objDictionary.get("strParkingShortSessionSec");
		String strSetImageSendBeforeViolation = objDictionary.get("strSetImageSendBeforeViolation");
		String strUnlockValue = objDictionary.get("strUnlockValue");
		String strUnlockTime = objDictionary.get("strUnlockTime");
		String strUnlockMax = objDictionary.get("strUnlockMax");
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strCurrentRateBlockGroup = clsHttpConnections.GET_KioskRateBlockNameUsingRateBlockId(objDictionary,strDeviceId);
		if (!strCurrentRateBlockGroup.equals(strRateBlockGroup)) {clsCommonWeb.AddOrUpdateKioskRateBlockGroup(objDictionary, strRateBlockGroup, "Local");}
		// Check If Remote spot Rate Block Is Set Correctly
		String strRemoteDeviceId = objDictionary.get("strRemoteDeviceId");
		if (strRemoteDeviceId != null) 
		{
			strCurrentRateBlockGroup = clsHttpConnections.GET_RateBlockNameUsingRateBlockId(objDictionary,strRemoteDeviceId);
			if (!strCurrentRateBlockGroup.equals(strRateBlockGroup)) {clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strRateBlockGroup, "Remote");}
		}
		// RRB: Remove Rate Blocks
		String strRateBlockIsBlank = objDictionary.get("strRateBlockIsBlank");
		// Check If Rate Block Is Blank
		if (strRateBlockIsBlank == null) {strRateBlockIsBlank = clsHttpConnections.CheckIfKioskRatBlockRateSetIsBlank(objDictionary, strDeviceId);
		objDictionary.put("strRateBlockIsBlank", strRateBlockIsBlank);}
		if (strRateBlockIsBlank.equals("False")) {clsCommonWeb.SENTRYLINK_KioskDeleteFreeAndNoParkingRateSetFromRateBlock(objDictionary);}
		// Remove Rate From Both Global Machines
		String strRemoteHost = objDictionary.get("strRemoteHost");
		if (strRemoteHost == null) {strRemoteHost = "";}
		if (!strRemoteHost.equals("")) {clsMeter.RemoveRateBlocksFromMeter(objDictionary, null, "Remote");}
		clsMeter.RemoveRateBlocksFromMeter(objDictionary, null, "Local");
		clsMeter.SENTRYMETER_UpdateKioskSetting(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation, strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax);
		clsCommonWeb.SENTRYLINK_KioskUpdateRateSetValues(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit,strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod,strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace);
 	}
	public void SENTRYLINK_UpdateKioskGroupSettings(Map<String, String> objDictionary)
 	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Set Lot True Up
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
  		//Store Grace Period Violation Number
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver); 
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary,driver); 
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary,driver, strPageName, "Lot Group","Local");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Open Parking Lots", "Edit This Open Parking Lot", 1,"Local");
  		//End app permits on exit
  		String strEndAppPermitsOnExit = objDictionary.get("strEndAppPermitsOnExit");if(strEndAppPermitsOnExit == null) {strEndAppPermitsOnExit = "False";}
  		String strConditional = clsCommonWeb.ConditionalStepCheckBox(objDictionary, driver, "Open Lot Settings","End app permits on exit", 1, "Value", "Checked");
  		if(strEndAppPermitsOnExit.equals("False") && strConditional.equals("True")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Enable Allow true up", "{CB} End app permits on exit","UnChecked");}
  		if(strEndAppPermitsOnExit.equals("True") && strConditional.equals("False")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Enable Allow true up", "{CB} End app permits on exit","Checked");}
  		//True Up
  		String strTrueUp = objDictionary.get("strTrueUp");
  		strConditional = clsCommonWeb.ConditionalStepCheckBox(objDictionary, driver, "Open Lot Settings","Allow true up", 1, "Value", "Checked");
  		if(strTrueUp.equals("False") && strConditional.equals("True")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Enable Allow true up", "{CB} Allow true up","UnChecked");}
  		if(strTrueUp.equals("True") && strConditional.equals("False")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Enable Allow true up", "{CB} Allow true up","Checked");}
  		//Unlock
  		String strUnlockValue = objDictionary.get("strUnlockValue");
  		strConditional = clsCommonWeb.ConditionalStepCheckBox(objDictionary, driver, "Open Lot Settings","Can unlock violations", 1, "Value", "Checked");
  		if (strUnlockValue.equals("Off")&& strConditional.equals("True")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Can unlock violations", "{CB} Can unlock violations","UnChecked");}
  		if (strUnlockValue.equals("On")&& strConditional.equals("False")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Can unlock violations", "{CB} Can unlock violations","Checked");}
  		String strInitialGracePeriod = objDictionary.get("strInitialGracePeriod");
		String strViolationGracePeriod = objDictionary.get("strViolationGracePeriod");
		String strMaximumDuration = objDictionary.get("strMaximumDuration");
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		int intViolationGracePeriod = Integer.parseInt(strViolationGracePeriod) * 60;
		int intMaximumDuration = Integer.parseInt(strMaximumDuration) * 60;
		clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Consumer Concierge Settings", "{T} Parking init grace period|{T} Parking violation grace period|{T} Parking maximum duration minutes",intInitialGracePeriod+"|"+intViolationGracePeriod+"|"+intMaximumDuration);
  		//Openlot placeholder sessions
		String strOpenlotPlaceholderSessions = objDictionary.get("strOpenlotPlaceholderSessions");if(strOpenlotPlaceholderSessions == null) {strOpenlotPlaceholderSessions = "False";}
  		strConditional = clsCommonWeb.ConditionalStepCheckBox(objDictionary, driver, "Open Lot Settings","Openlot placeholder sessions", 1, "Value", "Checked");
  		if(strOpenlotPlaceholderSessions.equals("False") && strConditional.equals("True")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Openlot placeholder sessions", "{CB} Openlot placeholder sessions","UnChecked");}
  		if(strOpenlotPlaceholderSessions.equals("True") && strConditional.equals("False")){clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Openlot placeholder sessions", "{CB} Openlot placeholder sessions","Checked");}
  		//Update
  		clsCommonWeb.ClickButton(objDictionary, driver, "Open Lot Settings", "Update", 1,"Local");
  		try {Thread.sleep(2000);}catch (Exception e) {}
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Open Lot Settings", "Lot was successfully updated", 1, "Contains", "Lot was successfully updated.");
  		driver.quit();
 	}	
	public void SENTRYLINK_UpdateLotTrueUp(Map<String, String> objDictionary, String strStatus)
 	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Set Lot True Up
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
  		//Store Grace Period Violation Number
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver); 
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary,driver); 
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary,driver, strPageName, "Lot Group","Local");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Open Parking Lots", "Edit This Open Parking Lot", 1,"Local");
  		String strConditional = clsCommonWeb.ConditionalStepCheckBox(objDictionary, driver, "Open Lot Settings","Allow true up", 1, "Value", strStatus);
  		if(strConditional.equals("False"))
  		{
  			clsCommonWeb.PopulateAction(objDictionary, driver, "Open Lot Settings", "Populate Enable Allow true up", "{CB} Allow true up",strStatus);
  		}
  		clsCommonWeb.ClickButton(objDictionary, driver, "Open Lot Settings", "Update", 1,"Local");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Open Lot Settings", "Lot was successfully updated", 1, "Contains", "Lot was successfully updated.");
  		driver.quit();
 	}	
	public String GetKioskRateBlockId(Map<String, String> objDictionary, String strDeviceId) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//String strDeviceId = objDictionary.get("strDeviceId");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);} 
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/meters.json";
  				break;
  		}
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig); 
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        System.out.println(response1.toString());
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("meter_groups");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("meters");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext()) 
	    	    		{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
		    	    		{
	    						if(innerMeter.get("rate_block_group_id") != null)
	    						{
	    							return innerMeter.get("rate_block_group_id").toString();
	    						}
		    	    		}
	    	    		}
		    		}
	    		}
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 401 Unauthorized"))
	    		{
	    			strErrorMsg = "Error: 401 Unauthorized-Hint Check if User ("+strAdminUser+") has expired-"+strMethondName;
	    		}
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e) 
	    {
	    	String strErrorMsg = e.toString()+"-"+strMethondName;
	    	if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 401 Unauthorized"))
    		{
    			strErrorMsg = "Error: 401 Unauthorized-Hint Check if User ("+strAdminUser+") has expired-"+strMethondName;
    		}
    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    }
	    return "";
	}
}

package AutomationCode;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;


@Listeners({ EmailableReporter2.class, EmailableReporter3.class, ListenersClass.class})
public class PEO_TestCases_Flutter
{
	public IOSDriver iosdriver;
	protected Map<String, String> objDictionary = new HashMap<>();
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
//		String strTestSuiteName = testContext.getName();
//		String strTestCaseName = method.getName();
//		switch(strTestSuiteName)
//		{
//			case "Meter_TestCases":
//			case "Meter_SetBegunEqualFalse":
//			case "AutomationCode.TestCases":
//				strTestCaseName = method.getName();
//				break;
//		}
		strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);

	}
	@Parameters({"strBrowser","strStopAndStartAppiumServer","strRemotePath","strRole", "strVehicleParkType", "strVehicleDepartType", "strDeviceId", "strAVDName","strMobileAPK","strPEOAPK","strAndroidUdid","strIOSUdid","strAppiumPort","strIOSDeviceName","strIOSVersion","strIOSBuild"})
	@BeforeTest
	public void StartIOSServer(@Optional String strBrowser,@Optional String strStopAndStartAppiumServer,@Optional String strRemotePath,@Optional String strRole, @Optional String strVehicleParkType, @Optional String strVehicleDepartType, @Optional String strDeviceId,@Optional String strAVDName, @Optional String strMobileAPK,@Optional String strPEOAPK, @Optional String strAndroidUdid,@Optional String strIOSUdid,@Optional String strAppiumPort,@Optional String strIOSDeviceName,@Optional String strIOSVersion,@Optional String strIOSBuild)
	{
		GlobalClass clsGlobalClass = new GlobalClass();
		objDictionary.clear();
		//clsGlobalClass.AddUserVariablesToDictionaryObject(objDictionary, strBrowser, strStopAndStartAppiumServer, strRemotePath, strRole, strVehicleParkType, strVehicleDepartType, strDeviceId, strAVDName, strMobileAPK, strPEOAPK, strAndroidUdid, strIOSUdid, strAppiumPort, strIOSDeviceName, strIOSVersion, strIOSBuild);
		CommonIOS clsCommonMobile = new CommonIOS();
		//Commented out Nov 14 2018
//		clsCommonMobile.stopServer(objDictionary);
//		clsCommonMobile.startServer(objDictionary);
	}
	@AfterTest
    public void StopIOSServer()
	{
//		CommonIOS clsCommonMobile = new CommonIOS();
//		clsCommonMobile.stopServer(objDictionary);
	}
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
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
   	//PEO-TEST CASES
   	//********************************************************************************************************************
	@Test(priority=100,groups={"Smoke"})
	public void A4000F_OpenApp_AirplaneModeEnabled()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-132");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//AirplaneMode test
		objDictionary.put("strWIFI","Enabled");
		objDictionary.put("strAirplaneMode","Enabled");
		String strUserName = "InvalidUser@gmail.com";
		String strPassword = "Invalid01";
		//Open Android Device
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Email|{T} Password",strUserName.toLowerCase()+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Please disable Airplane Mode to continue using the app.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Please disable Airplane Mode to continue using the app.) - actual value ("+strSnackbarText+")");}
 		androidDriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	@Test(priority=101)
	public void P4001F_ValidateInvalidCredentialMessage()
	{
  		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		Meter clsMeter = new Meter();
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "InvalidUser@gmail.com";
		String strPassword = "Invalid01";
		CommonWeb clsCommonWeb = new CommonWeb();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG"))
		{
			//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"AutomationTicketService");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"AutomationMunicipality");
	  		//Update Meter Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketServiceAndLookupService(objDictionary,"AutomationTicketService","AutomationMunicipality");
		}
		//Open Android Device
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Email|{T} Password",strUserName.toLowerCase()+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
 		androidDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=303)
 	public void P4002F_PEO_CMV_IGPE_VVXD() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "SL-7856");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMV: Create Manual Violation                                ");
  		Reporter.log("IGPE: Initial Grace Period Exceeded                         ");
  		Reporter.log("VVXD: Validate Volation XML Data                            ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strTicketAmountDue = "$30.00";
  		String strLicensePlateNumber = "KLFS01";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState","Minnesota");
  		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
		String strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		String strNumberOfViolationImages = "8";
		objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		clsCommonMobile.PEO_CreateManualViolationForDescriptionAndStatuteCodeValidation(objDictionary, androidDriver, strViolationDescription, strTicketAmountDue,"1");
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		androidDriver.quit();
		//Validate Manual Violation In SL
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		driver.navigate().refresh();
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 9, strDeviceId,10, "Initial Grace Period Exceeded", 12, "Notified", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "Notified");
  		String strViolationId = objDictionary.get("strViolationId");
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Vehicle Details", 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		//Click Image Link (Not Done)
		clsCommonWeb.ClickImage(objDictionary, driver, "Violations", "Best Violation Picture", 1);
		try {Thread.sleep(1000);} catch (Exception e) {}
		List<WebElement> uls = driver.findElements(By.xpath("//div[contains(@class,'jcarousel jcarousel-navigation')]//ul//li"));
    	int intActualNumberOfImages = uls.size();
    	if(intActualNumberOfImages == Integer.parseInt(strNumberOfViolationImages))
    	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
    	else
    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected 3 Actual "+intActualNumberOfImages,"Local");}
    	// VIAC: Validate Images Appear Correctly
		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).build().perform();
	    try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Plate",1, "Contains",strLicensePlateNumber);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Make",1, "Contains","AMC");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Model",1, "Contains","NA");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Color",1, "Contains","NA");
  		String strRandomVehicleBodyType = objDictionary.get("strRandomVehicleBodyType");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Body Type",1, "Contains",strRandomVehicleBodyType);
  		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);// Subtract 5 years
		Date previousYear = cal.getTime();
		String strVechileYear = new SimpleDateFormat("yyyy").format(previousYear);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Vehicle Year",1, "Contains",strVechileYear);
  		String strRandomUserRegistrationType = objDictionary.get("strRandomUserRegistrationType");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Registration Type",1, "Contains",strRandomUserRegistrationType);
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"1");
	    driver.quit();
	    threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Reports", 1);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Tickets With Officer", 1);
  		// Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String strCurrentDate = dateFormat.format(currentDate);
        String strEndDate =  clsCommonWeb.AddDaysToCurrentDate("yyyy-MM-dd","+1");
        clsCommonWeb.PopulateAction(objDictionary, driver, "Tickets By Officer Report", "Populate Date Range", "{T} Start Date|{T} End Date",strCurrentDate+"|"+strEndDate);
        try{Thread.sleep(500);}catch (Exception e) {}
  		clsCommonWeb.ClickButton(objDictionary, driver, "Tickets By Officer Report", "Generate Report", 1,"Local");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Tickets By Officer Report", "Officer Report", 1, "1", "1", "ColumnContainsValue", strViolationId);
		driver.quit();
	    clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=303)
 	public void P4002F_PEO_CMV_IGPE_VVXD_WIFI_And_WIFIScanning_Disabled() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-140");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMV: Create Manual Violation                                ");
  		Reporter.log("IGPE: Initial Grace Period Exceeded                         ");
  		Reporter.log("VVXD: Validate Volation XML Data                            ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strTicketAmountDue = "$30.00";
  		String strLicensePlateNumber = "KLFS01";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState","Minnesota");
  		//Test
  		objDictionary.put("strWIFI", "Disabled");
  		objDictionary.put("strWIFIScanning", "Disabled");
  		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
		String strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		//Handle "For a better experience your device will need to user Location Accuracy"
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "No thanks", 1);
		clsCommonMobile.VerificationPointButton(objDictionary, androidDriver, "New Violations", "No thanks", 1, "Does Not Exist");
		String strNumberOfViolationImages = "8";
		objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		clsCommonMobile.PEO_CreateManualViolationForDescriptionAndStatuteCodeValidation(objDictionary, androidDriver, strViolationDescription, strTicketAmountDue,"1");
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		androidDriver.quit();
		//Validate Manual Violation In SL
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		driver.navigate().refresh();
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 9, strDeviceId,10, "Initial Grace Period Exceeded", 12, "Notified", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "Notified");
  		String strViolationId = objDictionary.get("strViolationId");
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Vehicle Details", 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		//Click Image Link (Not Done)
		clsCommonWeb.ClickImage(objDictionary, driver, "Violations", "Best Violation Picture", 1);
		try {Thread.sleep(1000);} catch (Exception e) {}
		List<WebElement> uls = driver.findElements(By.xpath("//div[contains(@class,'jcarousel jcarousel-navigation')]//ul//li"));
    	int intActualNumberOfImages = uls.size();
    	if(intActualNumberOfImages == Integer.parseInt(strNumberOfViolationImages))
    	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
    	else
    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected 3 Actual "+intActualNumberOfImages,"Local");}
    	// VIAC: Validate Images Appear Correctly
		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).build().perform();
	    try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Plate",1, "Contains",strLicensePlateNumber);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Make",1, "Contains","AMC");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Model",1, "Contains","NA");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Color",1, "Contains","NA");
  		String strRandomVehicleBodyType = objDictionary.get("strRandomVehicleBodyType");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Body Type",1, "Contains",strRandomVehicleBodyType);
  		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);// Subtract 5 years
		Date previousYear = cal.getTime();
		String strVechileYear = new SimpleDateFormat("yyyy").format(previousYear);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Vehicle Year",1, "Contains",strVechileYear);
  		String strRandomUserRegistrationType = objDictionary.get("strRandomUserRegistrationType");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Registration Type",1, "Contains",strRandomUserRegistrationType);
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"1");
	    driver.quit();
	    threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Reports", 1);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Tickets With Officer", 1);
  		// Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String strCurrentDate = dateFormat.format(currentDate);
        String strEndDate =  clsCommonWeb.AddDaysToCurrentDate("yyyy-MM-dd","+1");
        clsCommonWeb.PopulateAction(objDictionary, driver, "Tickets By Officer Report", "Populate Date Range", "{T} Start Date|{T} End Date",strCurrentDate+"|"+strEndDate);
        try{Thread.sleep(500);}catch (Exception e) {}
  		clsCommonWeb.ClickButton(objDictionary, driver, "Tickets By Officer Report", "Generate Report", 1,"Local");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Tickets By Officer Report", "Officer Report", 1, "1", "1", "ColumnContainsValue", strViolationId);
		driver.quit();
	    clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=304)
 	public void P4002AF_PEO_CMV_IGPE_VVXD() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "SL-7856");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMV: Create Manual Violation                                ");
  		Reporter.log("IGPE: Initial Grace Period Exceeded                         ");
  		Reporter.log("VVXD: Validate Volation XML Data                            ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Unpaid Parking Fee 1";
  		String strTicketAmountDue = "$30.00";
  		String strLicensePlateNumber = "KLFS01";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState","Minnesota");
  		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
		String strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		String strNumberOfViolationImages = "8";
		objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		clsCommonMobile.PEO_CreateManualViolationForDescriptionAndStatuteCodeValidation(objDictionary, androidDriver, strViolationDescription, strTicketAmountDue,"1");
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		androidDriver.quit();
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		driver.navigate().refresh();
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 9, strDeviceId,10, "Unpaid Parking Fee 1", 12, "Notified", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "Notified");
  		String strViolationId = objDictionary.get("strViolationId");
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Vehicle Details", 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		//Click Image Link (Not Done)
		clsCommonWeb.ClickImage(objDictionary, driver, "Violations", "Best Violation Picture", 1);
		try {Thread.sleep(1000);} catch (Exception e) {}
		List<WebElement> uls = driver.findElements(By.xpath("//div[contains(@class,'jcarousel jcarousel-navigation')]//ul//li"));
    	int intActualNumberOfImages = uls.size();
    	if(intActualNumberOfImages == Integer.parseInt(strNumberOfViolationImages))
    	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
    	else
    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected 3 Actual "+intActualNumberOfImages,"Local");}
    	// VIAC: Validate Images Appear Correctly
		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).build().perform();
	    try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Plate",1, "Contains",strLicensePlateNumber);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Make",1, "Contains","AMC");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Model",1, "Contains","NA");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Color",1, "Contains","NA");
  		String strRandomVehicleBodyType = objDictionary.get("strRandomVehicleBodyType");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Body Type",1, "Contains",strRandomVehicleBodyType);
  		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);// Subtract 5 years
		Date previousYear = cal.getTime();
		String strVechileYear = new SimpleDateFormat("yyyy").format(previousYear);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Vehicle Year",1, "Contains",strVechileYear);
  		String strRandomUserRegistrationType = objDictionary.get("strRandomUserRegistrationType");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Registration Type",1, "Contains",strRandomUserRegistrationType);
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"1");
	    driver.quit();
	    clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=305)
 	public void P4003F_PEO_CMV2_IGPE_VVXD() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "SL-7856");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMV2: Create Manual Violation Spot 2                        ");
  		Reporter.log("IGPE: Initial Grace Period Exceeded                         ");
  		Reporter.log("VVXD: Validate Volation XML Data                            ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strStatuteCode = "250-50-B";
  		String strTicketAmountDue = "$30.00";
  		String strLicensePlateNumber = "KLFS01";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState","Minnesota");
		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		objDictionary.remove("strStatuteCode");objDictionary.put("strStatuteCode", strStatuteCode);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_CreateManualViolationForDescriptionAndStatuteCodeValidation(objDictionary, androidDriver, strViolationDescription, strTicketAmountDue,"2");
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		driver.navigate().refresh();
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 9, strDeviceId,10, "Initial Grace Period Exceeded", 12, "Notified", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "Notified");
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"2");
	    androidDriver.quit();
	    driver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=306)
 	public void P4004F_PEO_CMV_EIO60D_VVXD() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "SL-7856");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMV: Create Manual Violation                                ");
  		Reporter.log("EIO60D: Expired Inspection Over 60 Days                     ");
  		Reporter.log("VVXD: Validate Volation XML Data                            ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Expired Inspection Over 60 Days";
  		String strStatuteCode = "250-15";
  		String strTicketAmountDue = "$80.00";
  		String strLicensePlateNumber = "KLFS01";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState","Minnesota");
		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		objDictionary.remove("strStatuteCode");objDictionary.put("strStatuteCode", strStatuteCode);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_CreateManualViolationForDescriptionAndStatuteCodeValidation(objDictionary, androidDriver, strViolationDescription, strTicketAmountDue,"1");
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		driver.navigate().refresh();
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 9, strDeviceId,10, strViolationDescription, 12, "Notified", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "Notified");
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"1");
		driver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=302)
  	public void P4005F_FTFP0_CGPV_AV_CV_PLI_IT_PV_VPVE_ES1_VPSH_VICAE_VIAC()throws Exception
	{
 		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
 		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("AV: Approve Violation                                       ");
	  	Reporter.log("CV: Claim Violation                                         ");
	  	Reporter.log("PLI: Populate License Information                           ");
	  	Reporter.log("IT: Issue Ticket                                            ");
	  	Reporter.log("PV: Pay Violation                                           ");
	  	Reporter.log("VPVE: Validate Pay Violation Email                          ");
	  	Reporter.log("ES1: Exit Spot 1                                            ");
	  	Reporter.log("VPSH: Validate Parking Session History                      ");
	  	Reporter.log("VICAE: Validate Image Count After Exit                      ");
	  	Reporter.log("VIAC: Validate Images Appear Correctly                      ");
	  	Reporter.log("************************************************************");
	  	//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		strLicensePlateNumber = "645786G";
		String strLicensePlateState = "Washington";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
  	 	clsCommonWeb.SENTRYLINK_UpdateViolationsRequireAdminApproval(objDictionary, "Checked");
  	 	//UPDATE METER SETTING
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py PARKING_EVENT_ON_START");
		//Delete Reservation Remover Permeit
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		//ExitSpotAndSetMeterRateBlocks
		System.out.println("strHost: "+strHost);
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Clear existing violations
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver1 = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver1, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver1, "New Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver1, "New Violations", "Yes, clear them all", 1);
		androidDriver1.quit();
		//CGPV: Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
 		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//This needs to be here
 		String strRandomUserRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strRandomVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		//Open Android Device
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		//Populate Violation Fields
		//clsCommonMobile.PopulateAction(objDictionary, androidDriver, "Create Violation", "Populate Claim", "{T} License Plate",strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "License plate state", strLicensePlateState);
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "Create Violation", "Populate VIN Number", "{T} VIN Number", "1N4961EZXT2744492");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Year", "2024");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Month", "02");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Day", "21");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration type", strRandomUserRegistrationType);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", strRandomVehicleBodyType);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle make", "AMC");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);// Subtract 5 years
		Date previousYear = cal.getTime();
		String strVehicleYear = new SimpleDateFormat("yyyy").format(previousYear);
		objDictionary.put("strVehicleYear",strVehicleYear);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Select Year", strVehicleYear);
		try {Thread.sleep(500);}catch (Exception e) {}
		WebElement objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		String strNumberOfViolationImages = "7";objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		WebElement objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc='Violation Details:\nViolation Photos:']/android.view.View[3]/android.view.View[1]"));
		//WebElement objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\"Violation Photos:\"]/android.view.View/android.view.View"));
		clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
		if(!strNumberOfViolationImages.equals("0"))
		{
			int intNumberOfViolationImages = Integer.parseInt(strNumberOfViolationImages);
			for(int intViolationCount = 1; intViolationCount<= intNumberOfViolationImages; intViolationCount++)
			{
				//Add 1st Violation Picture
				if(intViolationCount > 1)
				{clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Add Photo",1);}
				try {Thread.sleep(1000);}catch (Exception e) {}
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Violation Photo",1);
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Picture Button",1);
				clsCommonMobile.ClickLink(objDictionary, androidDriver, "Create Violation", "OK", 1);
				objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
				objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\"Violation Details:\nViolation Photos:\"]/android.view.View/android.view.View"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
				if(intViolationCount > 4)//Additional Swipe need to center image.
				{
					clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
				}
			}
		}
		else
		{
			objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
			clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		}
		try {Thread.sleep(3500);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		androidDriver.quit();
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus_UsingLicensePlateAndState(objDictionary, driver, strLicensePlateState+" | "+strLicensePlateNumber, "Notified");
		driver.quit();
		//Pay Ticket
		clsCommonWeb.SENTRYLINK_PayViolation(objDictionary,strViolationId,strLicensePlateNumber,"True");
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4005_FTFP0_CGPV_AV_CV_PLI_IT_PV_VPVE_ES1(objDictionary,strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=306)
	public void P4006F_FTFP0_CGPV_PLI_AV_CV_IT_DV_DWDV_PDV_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-122");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("PLI: Populate License Information                           ");
	  	Reporter.log("AV: Approve Violation                                       ");
	  	Reporter.log("CV: Claim Violation                                         ");
	  	Reporter.log("IT: Issue Ticket                                            ");
		Reporter.log("DV: Dispute Violation                                       ");
		Reporter.log("DWDV: Disagree With Dispute Violation                       ");
		Reporter.log("PDV: Pay Disputed Violation                                 ");
	  	Reporter.log("ES1: Exit Spot 1                                            ");
	  	Reporter.log("NTVH: Navigate To Violation History                         ");
	  	Reporter.log("VPSH: Validate Parking Session History                      ");
	  	Reporter.log("VICAE: Validate Image Count After Exit                      ");
	  	Reporter.log("VIAC: Validate Images Appear Correctly                      ");
	  	Reporter.log("************************************************************");
	  	//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
		String strUnlockTime = "5";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"BB";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//CGPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//CGPV1: Create Grace Period Violation Spot 1
      	clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
      	//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Covert Iternal to External Violation Reason
      	String strRandomUserRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strRandomVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		//Navigate to Violations Page
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		//NavigateToNewViolation
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		//Add License Plate To Violation
		//clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		//Approve Violation
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		//Open Sentry Mobile
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		//Claim Violation
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		//Validate Violation Claimed
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//Issue Ticket
		try {Thread.sleep(3000);}catch (Exception e) {}
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		//Valid License From Sentry Link
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License Plate", 1, "Contains", "~strLicensePlate~");
		//Populate Violation Fields
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration exp year", "2024");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration exp month", "01");
		//Valid License State From Sentry Link
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "Truck");
		}
		else
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", strRandomVehicleBodyType);
		}
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle Make", "AMC");
		//Populate Registration Type
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration type", strRandomUserRegistrationType);
		//Validate Violation reason
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Validate Sentry Link Violation Status
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		driver.quit();
		//Validate Ticket Data
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		//Log Out of Mobile
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		androidDriver.quit();
		//Validate Statue Changes to Notified
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
  		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Notified");
		//DisputeViolation
		clsCommonWeb.SENTRYLINK_DisputeViolation(objDictionary,strViolationId,strLicensePlateNumber,"True");
		//Disagree With Dispute
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Disputed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violation Details", 1, "1","2", "CellValue", "Disputed");
  		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "End Dispute", 1,"Local");
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Violations", "End or Dismiss Dispute", "{T} Customer Only Comments|{T} Internal Only Comments","Stop listening to the Devil|Ruled in Favor of MPS");
 		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "Dialog End Dispute", 1,"Local");
 		try {Thread.sleep(3000);}catch (Exception e) {}
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violation Details", 1, "1","2", "CellValue", "Notified");
 		driver.quit();
 		//Pay Ticket
 		clsCommonWeb.SENTRYLINK_PayViolation(objDictionary,strViolationId,strLicensePlateNumber,"True");
 		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4006_FTFP0_CGPV_PLI_AV_CV_IT_DV_DWDV_PDV_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
	}
  	@Test(priority=307)
	public void P4007F_FTFP0_CGPV_PLI_AV_CV_CLI_IT_VLI_DV_AWDV_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-76");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("PLI: Populate License Information                           ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CLI: Change License Information                             ");
  		Reporter.log("IT: Issue Ticket                                            ");
  		Reporter.log("VLI: Validate License Information                           ");
  		Reporter.log("DV: Dispute Violation                                       ");
		Reporter.log("AWDV: Agree With Dispute Violation                          ");
		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
 		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
  		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Exist Spot and Create Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
 		//Covert Iternal to External Violation Reason
      	String strRandomUserRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strRandomVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"BB";
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "Create Violation", "Populate Claim", "{T} License Plate",strLicensePlateNumber);
		
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "Truck");
		}
		else
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", strRandomVehicleBodyType);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Registration Type", "Passenger");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		//clsCommonMobile.PEO_ValidateTicketValues(objDictionary, androidDriver,"$30.00","1");
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		//Log Out of Mobile
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		androidDriver.quit();
		//Validate Statue Changes to Notified
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
  		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Notified");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "License Plate", 1, "Contains", "~strLicensePlateNumber~");
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Province/State", 1, "Value", "Minnesota");
		driver.quit();
		//DisputeViolation
		clsCommonWeb.SENTRYLINK_DisputeViolation(objDictionary,strViolationId,strLicensePlateNumber,"True");
		//Agree With Dispute
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Disputed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violation Details", 1, "1","2", "CellValue", "Disputed");
  		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "End Dispute", 1,"Local");
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Violations", "End or Dismiss Dispute", "{T} Customer Only Comments|{T} Internal Only Comments","I am sorry you were possessed this one is on us|Ruled in Favor of Customer");
 		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "Dialog Dismiss Dispute", 1,"Local");
 		try {Thread.sleep(3000);}catch (Exception e) {}
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violation Details", 1, "1","2", "CellValue", "Dismissed");
 		driver.quit();
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4007_FTFP0_CGPV_PLI_AV_CV_CLI_IT_VLI_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=308)
  	public void P4008F_FTFP0_CGPV_AV_CV_TNI_VSM_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("TNI: Ticket Not Issued                                      ");
  		Reporter.log("VSM: Validate Status Missed                                 ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Create Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
 		//Covert Iternal to External Violation Reason
      	String strRandomUserRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strRandomVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Registration Type", strRandomUserRegistrationType);
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "Truck");
		}
		else
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", strRandomVehicleBodyType);
		}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		//clsCommonMobile.PEO_ValidateTicketValues(objDictionary, androidDriver,"$30.00","1");
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Not Issued", 1);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Ticket", "Reason", "Vehicle Departed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Submit", 1);
		driver.navigate().refresh();
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Province/State", 1, "Value", "~strLicenseState~");
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		androidDriver.quit();
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait For Ticket To Get Generated");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4008_FTFP0_CGPV_AV_CV_TNI_VSM_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=309)
  	public void P4009F_FTFP0_CGPV_AV_CV_VIPV_TNI_VSM_ES1_VPSH_VICAE_VIAC()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-79");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("VIPV: Validate In Progress Violations                       ");
  		Reporter.log("TNI: Ticket Not Issued                                      ");
  		Reporter.log("VSM: Validate Status Missed                                 ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Create Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//Navigate To In Progress Violations
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violation Details", "In Progress Violations", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PEO_ClickGenerateTicketOnInProgressViolationsScreen(objDictionary, androidDriver, strViolationId);
		
		//Not in Legacy App - Possible Error
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket", 1);
		
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate", 1, "Contains", "~strLicensePlate~");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Registration Type", "Passenger");
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "Truck");
		}
		else
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "TRUCK");
		}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Not Issued", 1);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Ticket", "Reason", "Vehicle Departed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Submit", 1);
		driver.navigate().refresh();
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Province/State", 1, "Value", "~strLicenseState~");
		driver.quit();
		try {androidDriver.findElement(By.xpath("//*[@text='OK']")).click();}catch(Exception b) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait For Ticket To Get Generated");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4009_FTFP0_CGPV_AV_CV_VIPV_TNI_VSM_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=310)
  	public void P4010F_FTFP0_CGPV_AV_CV_VIPV_CAV_RCV_TNI_VSM_ES1_VPSH_VICAE_VIAC()
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("VIPV: Validate In Progress Violations                       ");
  		Reporter.log("CAV: Clear All Violations                                   ");
  		Reporter.log("RCV: Re-Claim Violation                                     ");
  		Reporter.log("TNI: Ticket Not Issued                                      ");
  		Reporter.log("VSM: Validate Status Missed                                 ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"BB";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	//Delete Reservation Remover Permeit
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			HttpConnections clsHttpConnections = new HttpConnections();
  	  		clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Create Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//Navigate To In Progress Violations
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violation Details", "In Progress Violations", 1);
		clsCommonMobile.PEO_ValidateInProgressViolationsExists(objDictionary, androidDriver, strViolationId);
		//Clear All Violations
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "Yes, clear them all", 1);
		clsCommonMobile.DismissNotification(androidDriver);
    	clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "New Violations", 1);
		//Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate", 1, "Contains", "~strLicensePlate~");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Registration Type", "Passenger");
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "Truck");
		}
		else
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", "TRUCK");
		}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		//clsCommonMobile.PEO_ValidateTicketValues(objDictionary, androidDriver,"$30.00","1");
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Not Issued", 1);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Ticket", "Reason", "Vehicle Departed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Submit", 1);
		//Validate Violation Status Missed
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Province/State", 1, "Value", "~strLicenseState~");
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait For Ticket To Get Generated");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4010_FTFP0_CGPV_AV_CV_VIPV_CAV_RCV_TNI_VSM_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=311)
  	public void P4011F_FTFP0_CGPV_AV_SV_USV_CV_IT_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strAssociatedBug", "  |176466748");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("AV: Approve Violation                                       ");
		Reporter.log("SV: Snooze Violation                                        ");
		Reporter.log("USV: Unsnooze Violation                                     ");
		Reporter.log("CV: Claim Violation                                         ");
		Reporter.log("IT: Issue Ticket                                            ");
		Reporter.log("ES1: Exit Spot 1                                            ");
		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
  		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		String strRandomUserRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strRandomVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Clear existing violations
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Yes, clear them all", 1);
		androidDriver.quit();
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//NTAAV-NavigateToAndApproveViolations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState, "Initial Grace Period Exceeded",strViolationId,1);
	    //SVTS-Set Violation To Snooze
		objDictionary.put("strInstallApp", "True");
		androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Snooze",1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		driver.navigate().refresh();
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Snoozed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		//USV-Unsnooze Violation
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Snoozed Violations", 1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Snoozed Violations", "Unsnooze", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Violations", 1);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Year", "2024");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Month", "02");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Day", "21");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration type", strRandomUserRegistrationType);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", strRandomVehicleBodyType);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus_UsingLicensePlateAndState(objDictionary, driver, strLiceseState+" | "+strLicensePlateNumber, "Notified");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		androidDriver.quit();
		driver.quit();
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	//Additional Wait Before Opening Parking Session History
      	try {Thread.sleep(5000);}catch (Exception e) {}
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4011_FTFP0_CGPV_AV_SV_USV_CV_IT_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=312)
  	public void P4012F_FTFP0_CGPV_AV_CV_CNIT_RA_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RDV: Reason Ambulance                                       ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Ambulance";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");}//Chris Wrote a Bug
  		else
  		{clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4012_FTFP0_CGPV_AV_CV_CNIT_RA_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=313)
  	public void P4013F_FTFP0_CGPV_AV_CV_CNIT_RDV_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV: Create Grace Period Violation Spot 1                  ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RDV: Reason Different Vehicle                               ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Different Vehicle";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");//Chris Wrote a Bug
  		}
  		else
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);
  		}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		//Violation Removed
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4013_FTFP0_CGPV_AV_CV_CNIT_RDV_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
   		clsMeter.METER_SetMeterEndTime(objDictionary);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=314)
  	public void P4014F_FTFP0_CGPV_AV_CV_CNIT_RDIV_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV: Create Grace Period Violation Spot 1                  ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RDIV: Reason Driver in Vechile                              ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Different Vehicle";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");//Chris Wrote a Bug
  		}
  		else
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);
  		}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4014_FTFP0_CGPV_AV_CV_CNIT_RDIV_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=315)
  	public void P4015F_FTFP0_CGPV_AV_CV_CNIT_RBCE_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV: Create Grace Period Violation Spot 1                  ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RBCE: Reason Borough/City Employee                          ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Different Vehicle";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		//Convert ParkTime Stamp
  		String strParkTimestamp = objDictionary.get("strParkTimestamp");
  		try {
  		    // Input: The time is in UTC
  		    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  		    inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  		    Date date = inputFormat.parse(strParkTimestamp);

  		    // Output: Set the time zone to CST (Central Standard Time)
  		    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
  		    outputFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));  // Use 'America/Chicago' for CST
  		    strParkTimestamp = outputFormat.format(date);
  		} catch (Exception e) {
  		    e.printStackTrace();
  		}
  		System.out.println(strParkTimestamp);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Reports", 1);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Processed", 1);
  		// Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String strCurrentDate = dateFormat.format(currentDate);
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Processed Parking Session Reports", "Populate Date Range", "{T} Start Date|{T} End Date",strCurrentDate+"|"+strCurrentDate);
  		clsCommonWeb.ClickButton(objDictionary, driver, "Processed Parking Session Reports", "Find Violations", 1,"Local");
  		clsCommonWeb.StoreTableRowNumberBaseOnTwoColumnValue(objDictionary, driver, "Processed Parking Session Reports", "Parking Session Report", 1, 1, strParkTimestamp,2, "To Verify", "strRowNumber");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");//Chris Wrote a Bug
  		}
  		else
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);
  		}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4015_FTFP0_CGPV_AV_CV_CNIT_RBCE_VIR(objDictionary,strViolationId,strCouldNotIssueReason);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=316)
  	public void P4016F_FTFP0_CGPV_AV_CV_CNIT_RBS_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("GCPV: Create Grace Period Violation Spot 1                  ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RBS: B Sticker                                              ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Different Vehicle";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");}//Chris Wrote a Bug
  		else
  		{clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4016_FTFP0_CGPV_AV_CV_CNIT_RBS_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=317)
  	public void P4017F_FTFP0_CGPV_AV_CV_CNIT_RTC_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV: Create Grace Period Violation Spot 1                  ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RTC: Reason Teacher car                                     ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Different Vehicle";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");//Chris Wrote a Bug
  		}
  		else
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);
  		}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
    	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4017_FTFP0_CGPV_AV_CV_CNIT_RTC_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=318)
  	public void P4018F_FTFP0_CGPV_AV_CV_CNIT_RVU_VIR_ES1_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV: Create Grace Period Violation Spot 1                  ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("CNIT: Could Not Issue Ticket                                ");
  		Reporter.log("RVU: Reason Violation Unlocked                              ");
  		Reporter.log("VIR: Validate Issue Reason                                  ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Different Vehicle";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Clear existing violations
		AppiumDriver androidDriver1 = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver1, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver1, "New Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver1, "New Violations", "Yes, clear them all", 1);
		androidDriver1.quit();
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary,driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary,driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary,driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary,driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary,driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary,driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary,driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary,driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary,driver,"Initial Grace Period Exceeded", "Claimed");
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
  		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
  		driver.navigate().refresh();
  		try {Thread.sleep(3000);}catch (Exception e) {}
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary,driver, "Initial Grace Period Exceeded","Missed");//Chris Wrote a Bug
  		}
  		else
  		{
  			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary,driver, "Initial Grace Period Exceeded", "Unissued "+strCouldNotIssueReason);
  		}
  		clsCommonWeb.ClickLink(objDictionary,driver, "Violations", "~strViolationId~", 1);
  		driver.quit();
  		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
  		androidDriver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4018_FTFP0_CGPV_AV_CV_CNIT_RVU_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=319)
  	public void P4019F_FTFP0_CGPV_AV_CV_CNIT_RO_VIR_ES1_VPSH_VIACE_VIAC()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-81");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV: Create Grace Period Violation Spot 1                  ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("AV: Approve Violation                                       ");
		Reporter.log("CV: Claim Violation                                         ");
		Reporter.log("CNIT: Could Not Issue Ticket                                ");
		Reporter.log("RO: Reason Other                                            ");
		Reporter.log("VIR: Validate Issue Reason                                  ");
		Reporter.log("ES1: Exit Spot 1                                            ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		String strCouldNotIssueReason = "Other";
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
  		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "Violation Details", "Populate Other Reason", "{T} Other","FBIVehicle");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
		driver.navigate().refresh();
		try {Thread.sleep(3000);}catch (Exception e) {}
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
		{
			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");//Chris Wrote a Bug
		}
		else
		{
			clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Unissued FBIVehicle");
		}
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		driver.quit();
		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		androidDriver.quit();
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4019_FTFP0_CGPV_AV_CV_CNIT_RO_VIR_ES1(objDictionary,strViolationId,strCouldNotIssueReason);
  		driver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=320)
  	public void P4020F_FTFP0_CGPV_AV_PCV_CP1_VMU_VPVR_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strAssociatedBug", "183842654|172317409");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("PCV: PEO Claim Violation                                    ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("VMU: Validate Meter Unlocked                                ");
		Reporter.log("VPVR: Validate PEO Violation Removed                        ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	objDictionary.put("strTrueUp", "True");
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		//PEO Claim Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
		//StoreViolationNumber
		clsCommonMobile.StoreText(objDictionary, androidDriver, "Violation Details", "Violation Number", 1, "strViolationNo");
		driver.quit();
		//CP1: Coin Payment Spot 1
  		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  Integer.parseInt(strMeterIncrementTime),"1");
		//Validate Violation Removed from PEO Application
		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 30,strSpotNumber,"Local");
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "OK", 1);
		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "OK", 1);
  		androidDriver.quit();
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4020_FTFP0_CGPV_AV_PCV_CP1_VMU_VPVR(objDictionary, strMeterIncrementTime, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=321)
  	public void P4021F_CGPV1_AV_PCV_PIT_CP1_VMU_VPVR_VPSH_VICAE_VIAC()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-90");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("CGPV: Create Grace Period Violation                         ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("PCV: PEO Claim Violation                                    ");
		Reporter.log("PIT: PEO Issue Ticket                                       ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("VMU: Validate Meter Unlocked                                ");
		Reporter.log("VPVR: Validate PEO Violation Removed                        ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strMeterGroup = objDictionary.get("strMeterGroup");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		objDictionary.put("strTrueUp", "True");
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		//Filter Violations By MeterGroup
		clsCommonWeb.PopulateAction(objDictionary, driver, "Violations", "Populate Meter Groups", "{L} Meter Groups",strMeterGroup.toUpperCase());
		clsCommonWeb.ClickButton(objDictionary, driver, "Violations", "Find Violations", 1,"Local");
		//Navigate to Violation
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		//PEO Claim Violation
		objDictionary.put("strInstallApp", "True");		
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		//CP1-Coin Payment Spot 1
		clsMeter.METER_InsertCoin(objDictionary, null,"1","Local");
		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 30, strSpotNumber,"Local");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		//Click Back
		androidDriver.navigate().back();
		//Click OK
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Validate Violation Removed from PEO Application
		clsCommonMobile.PEO_ValidateViolationRemoved(objDictionary, androidDriver, strViolationId);
		androidDriver.quit();
		//Validate Meter Is No Longer in Violation Status
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local",strSpotNumber);
		if(!strMeterViolationValue.equals("True"))
		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") is no longer in Violation Status");}
		else
		{
			String strErrorMsg = "The Meter ("+strMeterName+") spot ("+strSpotNumber+") was still in Violation Status";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		}
		//ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitBothSpots(objDictionary, null,"Local");
      	//ValidateParkingSessionHistoryAndImages
	  	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
	  	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4021_CGPV1_AV_PCV_PIT_CP1_VMU_VPVR(objDictionary, strMeterIncrementTime, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=322)
  	public void P4022F_FTFP0_CGPV1_AV_PCV_PIT_PGT_CP1_VML_MPO2_VPSH_VICAE_VIAC()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-91");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("PCV: PEO Claim Violation                                    ");
		Reporter.log("PIT: PEO Issue Ticket                                       ");
		Reporter.log("PGT: PEO Generate Ticket                                    ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("VML: Validate Meter Locked                                  ");
		Reporter.log("MPO2: Meter Presumed occupied Spot 2                       ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strMeterName = objDictionary.get("strMeterName");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "15";
  		String strUnlockMax = "2";
  		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
		String strLiceseState = "Minnesota";
		objDictionary.put("strTrueUp", "True");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
 		String strRandomUserRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strRandomVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") is in Violation Status");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_touch_screen.py");
		//Approve Violation Ticket
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		//PEO Claim Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		//Populate Violation Fields
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Year", "2024");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Month", "02");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration Details Day", "21");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Registration type", strRandomUserRegistrationType);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Create Violation", "Vehicle body type", strRandomVehicleBodyType);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		try {Thread.sleep(2000);}catch (Exception e) {}
		androidDriver.quit();
		driver.quit();
		//CP1-Coin Payment Spot 1
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary, driver,"Local");
		if(strForcedMulti.equals("False"))
  		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testauto_insert_coins_spot_"+strSpotNumber+".py");
  		}
		else
		{
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "testautof_touch_screen.py");
			String strCurrentUIState = clsMeter.METER_GetMeterUIState(objDictionary,null,"1","Local");
			if(strCurrentUIState.equals("SCREEN_MULTI_SELECT_SPACE"))
  			{
				String strDeviceId = objDictionary.get("strDeviceId");
				String[] arrLocalMeterSpots = strDeviceId.split("-");
				String strSpotName = "";
				if(strSpotNumber.equals("1")){strSpotName = arrLocalMeterSpots[0];}else{strSpotName = arrLocalMeterSpots[1];}
		  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "testautof_enter_space.py "+strSpotName);
		  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_insert_coins_no_spot.py");
		  	}
		}
		String strMeterViolationValue = clsMeter.METER_GetMeterViolationValue(objDictionary,"Local",strSpotNumber);
		if(strMeterViolationValue.equals("True"))
		{Reporter.log("The Meter ("+strMeterName+") spot ("+strSpotNumber+") was still in Violation Status");}
		else
		{
			String strErrorMsg = "The Meter ("+strMeterName+") spot ("+strSpotNumber+") was not in Violation Status";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		}
		//Presumed occupied Spot 2
		String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, driver, "Local");
      	if(!strNumberOfSpots.equals("1") && strForcedMulti.equals("False"))
      	{
			clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterVariableBegunEqualTrue} NA", 30,"2","Local");
			String strMeterBegunValue = clsMeter.GetMeterBegunValue(objDictionary,"1","Local");
			if(strMeterBegunValue.equals("True"))
			{Reporter.log("A new parking session was created on Meter ("+strMeterName+") spot ("+strSpotNumber+") ");}
			else
			{
				String strErrorMsg = "A new parking session was not created on Meter ("+strMeterName+") spot ("+strSpotNumber+") ";
				Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
			}
			//ShortSessionWaitExitSpot
	      	clsMeter.SENTRYMETER_ShortSessionWaitExitBothSpots(objDictionary, null,"Local");
	    }
      	else
      	{
      		//ShortSessionWaitExitSpot
          	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	}
		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4022_FTFP0_CGPV1_AV_PCV_PIT_PGT_CP1_VML_MPO2(objDictionary, strMeterIncrementTime, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=323)
  	public void P4023F_PEO_CGPV1_NTAAV_PAIT_GT_RBT_VVR()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strAssociatedBug", "SL-3592");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("NTAAV: Navigate To And Approve Violation                    ");
		Reporter.log("PAIT: Populate And Issue Ticket                             ");
		Reporter.log("GT: Generate Ticket                                         ");
		Reporter.log("RBT: Re-Broadcast Ticket                                    ");
		Reporter.log("VVR: Validate Violation Re-Broadcast                        ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//CGPV1: Create Grace Period Violation Spot 1
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
		String strLiceseState = "Alabama";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//Generate Random Variables prior to Opening Browser
		String strRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		objDictionary.put("strRegistrationType",strRegistrationType);
		objDictionary.put("strVehicleBodyType",strVehicleBodyType);
		//NTAAV-NavigateToAndApproveViolations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationId,1);
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		//PAIT = Populate And Issue Ticket
		clsCommonMobile.PEO_PopulateAndGenerateTickets(objDictionary, androidDriver, strLicensePlateNumber,"$30.00","1");
		driver.navigate().refresh();
		//GT = Generate Ticket
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.DismissNotification(androidDriver);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		androidDriver.quit();
		driver.navigate().refresh();
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		//Re-Broadcast
		objDictionary.put("strWaitForPageLoad","False");
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonWeb.ClickButton(objDictionary, driver, "Violations", "Re-Broadcast", 1,"Local");
		objDictionary.put("strWaitForPageLoad","True");
		//Click OK Button to complete Re-Broadcast
		driver.switchTo().alert().accept();
		clsCommonWeb.GlobalObjectWait(objDictionary, driver, "Violation", "{TextExists} Violation~Re-BroadcastMessage", 30);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violation", "Re-BroadcastMessage", 1, "Contains", "Violation alert was re-broadcast to all registered (and watching) PEOs.");
		//Claim Violation
		androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
		//ValidateViolationInformation
		clsCommonMobile.PEO_ValidateViolationInformation(objDictionary, androidDriver);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "Issue Ticket",1);
		//PAIT = Populate And Issue Ticket
		clsCommonMobile.PEO_PopulateAndGenerateTickets(objDictionary, androidDriver, strLicensePlateNumber,"$30.00","1");
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Ticket Issued", 1);
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violations", "Log Out", 1);
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4023_PEO_CGPV1_NTAAV_PAIT_GT_RBT_VVR(objDictionary, strMeterIncrementTime, strViolationId);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=324)
  	public void P4024F_PEO_CGPV1_NTAAV_CAVV_CNIT_RV_RAV_GT_VPSH_VICAE_VIAC()
 	{
		objDictionary.put("strAssociatedBug", "123456789");//Reset Button Rework
 		objDictionary.put("strMobileDeviceType", "ANDROID");
 		//Classes
 		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
 		CommonWeb clsCommonWeb = new CommonWeb();
 		Meter clsMeter = new Meter();
 		clsCommonMobile.PEO_AddReportVariables(objDictionary);
 		Reporter.log("***************TestCase Description*************************");
 		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
 		Reporter.log("NTAAV: Navigate To And Approve Violation                    ");
 		Reporter.log("CAVV: Claim and Validate Violation                          ");
 		Reporter.log("CNIT: Could Not Issue Ticket                                ");
 		Reporter.log("RV-Reset Violation                                          ");
 		Reporter.log("RAV: Re-Approve Violation                                   ");
 		Reporter.log("GT: Generate Ticket                                         ");
 		Reporter.log("************************************************************");
 		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
 		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strCouldNotIssueReason = "Driver in Vehicle";
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
 		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
 		//Store Grace Period Violation Number
  		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
 		//NTAAV-NavigateToAndApproveViolations
 		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
 		String strLiceseState = "Alabama";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//Generate Random Variables prior to Opening Browser
 		String strRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		objDictionary.put("strRegistrationType",strRegistrationType);
		objDictionary.put("strVehicleBodyType",strVehicleBodyType);
 		//Open Browser
 		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
 		WebDriver driver = getDriver();
 		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationId,1);
 		try {Thread.sleep(5000);}catch (Exception e) {}
 		objDictionary.put("strInstallApp", "True");
 		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		//CAVV-Claim and Validate Violation
 		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
 		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
 		try {Thread.sleep(1000);}catch (Exception e) {}
 		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
 		//CNIT-Could Not Issue Ticket
 		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Could not issue ticket",1);
 		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Violation Details", "Reason", strCouldNotIssueReason);
 		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Submit", 1);
 		//RV-Reset Violation
 		driver.navigate().refresh();
 		try {Thread.sleep(3000);}catch (Exception e) {}
 		if(strCouldNotIssueReason.equals("Vehicle Departed"))
 		{clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");}//Chris Wrote a Bug
 		else
 		{clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Unissued "+strCouldNotIssueReason);}
 		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
 		objDictionary.put("strWaitForPageLoad","False");
 		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Reset", 1,"Local");
 		objDictionary.put("strWaitForPageLoad","True");
 		//Click OK Button Reset Alert
 		driver.switchTo().alert().accept();
 		clsCommonWeb.GlobalObjectWait(objDictionary, driver, "Parking Session", "{TextExists} Parking Session~TheParkingSessionWasReset", 30);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Session", "TheParkingSessionWasReset", 1, "Contains", "The parking session was reset.");
 		//RAV-Re-Approve  Violation
 		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Violations", 1);
 		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","New");
 		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
 		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "Issue Ticket",1);
 		clsCommonMobile.PEO_PopulateAndGenerateTickets(objDictionary, androidDriver, strLicensePlateNumber,"$30.00","1");
 		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Ticket Issued", 1);
 		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "LEAVE", 1);
 		clsCommonWeb.SENTRYLINK_ValidateViolationStatus_UsingLicensePlateAndState(objDictionary, driver, strLiceseState+" | "+strLicensePlateNumber, "Notified");
 		driver.quit();
 		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "More Options icon", 1);
 		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violations", "Log Out", 1);
 		androidDriver.quit();
 		//RPSS: Remain Parked Short Session
      	clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
      	//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4024_PEO_CGPV1_NTAAV_CAVV_CNIT_RV_RAV_GT(objDictionary, strMeterIncrementTime, strViolationId);
  	  	clsMeter.METER_SetMeterEndTime(objDictionary);
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
 	}
  	@Test(priority=325)
	public void P4025F_FTFP0_MUON_CGPV1_NTAAV_SV_CP1_USBDNE_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("MUON: Meter Unlock On                                       ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("NTAAV: Navigate To And Approve Violation                    ");
		Reporter.log("SV: Snooze Violation                                        ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("USBDNE: Unsnooze Button Does Not Exist                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue", strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "5";
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//CGPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//NTAAV-NavigateToAndApproveViolations
		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
 		String strLiceseState = "Alabama";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationId,1);
	    //SVTS-Set Violation To Snooze
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Snooze",1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		driver.navigate().refresh();
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Snoozed");
		driver.quit();
		//CP1-Coin Payment Spot 1 X 3
		clsMeter.METER_InsertCoin(objDictionary, null,"1","Local");
		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 45, strSpotNumber,"Local");
		//Wait Until Display Arrows are gone
		String strScreenTimeoutTime = clsMeter.METER_GetScreenTimeoutWhenNoSpotSelected(objDictionary, null);
      	int intScreenTimeoutTime = Integer.parseInt(strScreenTimeoutTime) * 2000;
      	try {Thread.sleep(intScreenTimeoutTime);}catch (Exception e) {}
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Unlocked");
		driver.quit();
		//USBDNE-Unsnooze Button Does Not Exist
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violation Details", "Snoozed Violations", 1);
		clsCommonMobile.VerificationPointButton(objDictionary, androidDriver, "Snoozed Violations", "Unsnooze", 1, "Does Not Exist");
		androidDriver.quit();
		//RPSS: Remain Parked Short Session
      	clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
      	//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4025_FTFP0_MUON_CGPV1_NTAAV_SV_CP1_USBDNE(objDictionary, strMeterIncrementTime, strViolationId);
  	 	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=326)
  	public void P4026F_FTFP10_MUON_CGPV1_NTAAV_SV_CP1_USBDNE_VPSH_VICAE_VIAC()
	{
  		objDictionary.put("strAssociatedBug", "184791360|176871794");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP10: Set Free Time First Payment 10                      ");
		Reporter.log("MUON: Meter Unlock On                                       ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("NTAAV: Navigate To And Approve Violation                    ");
		Reporter.log("SV: Snooze Violation                                        ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("USBDNE: Unsnooze Button Does Not Exist                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue", strUnlockValue);
  		String strUnlockTime = "15";
  		String strUnlockMax = "5";
  		objDictionary.put("strTrueUp", "True");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost, "settings_set_setting_true.py PARKING_SHOW_UNLOCK_TIME");
  		//Delete Reservation Remover Permeit
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			HttpConnections clsHttpConnections = new HttpConnections();
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Clear existing violations
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver1 = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver1, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver1, "New Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver1, "New Violations", "Yes, clear them all", 1);
		androidDriver1.quit();
  		//CGPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number API call?
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//NTAAV-NavigateToAndApproveViolations
		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
 		String strLiceseState = "Alabama";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationId,1);
	    //SVTS-Set Violation To Snooze
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Snooze",1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		driver.navigate().refresh();
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Snoozed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		driver.quit();
		//CP1-Occassionally the coin payment fails.
		clsMeter.METER_InsertCoin(objDictionary, null,"1","Local");
		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 45, strSpotNumber,"Local");
		//Wait Until Display Arrows are gone
		String strScreenTimeoutTime = clsMeter.METER_GetScreenTimeoutWhenNoSpotSelected(objDictionary, null);
      	int intScreenTimeoutTime = Integer.parseInt(strScreenTimeoutTime) * 2000;
      	try {Thread.sleep(intScreenTimeoutTime);}catch (Exception e) {}
      	driver.quit();
		//USBDNE-Unsnooze Button Does Not Exist
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Snoozed Violations", 1);
		clsCommonMobile.VerificationPointButton(objDictionary, androidDriver, "Snoozed Violations", "Unsnooze", 1, "Does Not Exist");
		androidDriver.quit();
		//RPSS: Remain Parked Short Session
      	clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
      	//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4026_FTFP10_MUON_CGPV1_NTAAV_SV_CP1_USBDNE(objDictionary, strMeterIncrementTime, strViolationId);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=327)
  	public void P4027F_AMMOEE_MUON_CGPV1_NTAAV_SV_LS1_USBDNE_VPSH_VICAE_VIAC()
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("AMMOEE: Automatically Mark Missed On Exit Enabled           ");
		Reporter.log("MUON: Meter Unlock On                                       ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("NTAAV: Navigate To And Approve Violation                    ");
		Reporter.log("SV: Snooze Violation                                        ");
		Reporter.log("LS: Leave Spot 1                                            ");
		Reporter.log("USBDNE: Unsnooze Button Does Not Exist                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
  		String strUnlockTime = "15";
  		String strUnlockMax = "5";
  		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
  		//Check If Parking Enforcement Officer Exists
  		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Enable Violation App Subscription
		clsCommonWeb.SENTRYLINK_PopulateOtherSubscriptions(objDictionary, "violation", "Checked", "app");
		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//NTAAV-NavigateToAndApproveViolations
 		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
 		String strLiceseState = "Alabama";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationId,1);
		//Open PEO
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		//Login
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		//Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(3000);}catch (Exception e) {}
		//Violation Status Claimed
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//Snooze Violation
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Snooze",1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		driver.navigate().refresh();
		try {Thread.sleep(3000);}catch (Exception e) {}
		//Violation Status Snoozed
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Snoozed");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		//RPSS: Remain Parked Short Session
      	clsMeter.METER_MeterWaitWithMessage(objDictionary,Integer.parseInt(strParkingShortSessionSec), "Remain Parked ("+Integer.parseInt(strParkingShortSessionSec)+") seconds-Spot"+strSpotNumber);
   		//Exit Spot
      	clsMeter.METER_ExitSpot(objDictionary, null, "1","Local");
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Violations", 1);
		//Violation Status Missed
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");
		driver.quit();
		//USBDNE-Unsnooze Button Does Not Exist
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Snoozed Violations", 1);
		try {Thread.sleep(15000);}catch (Exception e) {}
		//Click Back
		clsCommonMobile.VerificationPointButton(objDictionary, androidDriver, "Snoozed Violations", "Unsnooze", 1, "Does Not Exist");
		androidDriver.quit();
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4027_AMMOEE_MUON_CGPV1_NTAAV_SV_LS1_USBDNE(objDictionary, strMeterIncrementTime, strViolationId);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=328)
  	public void P4028F_DPEOU_CPEOU_MIL_UU_VPEOL()
	{
 		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("DPEOU: Delete PEO User                                      ");
		Reporter.log("CPEOU: Create PEO User                                      ");
		Reporter.log("MIL: Maximize Invalid Logins                                ");
		Reporter.log("UU: Unlock User                                             ");
		Reporter.log("VPEOL: Valid PEO Login                                      ");
		Reporter.log("************************************************************");
		//Delete User
		String strUserName = "PEODeleteUser1@gmail.com";
		objDictionary.put("strUserName","PEODeleteUser1@gmail.com");
		String strPassword = "PEODeleteMe02!";
		//DPEOU: Delete PEO User
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		//CPEOU: Create PEO User
		clsCommonWeb.SENTRYLINK_CreateUserAndCloseBrowser(objDictionary, strUserName, strPassword, "parking_enforcement_officer","PEODFirstName","PEODLastNames");
		//MIL: Maximize Invalid Logins
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		//Invalid Login 1
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Email|{T} Password",strUserName.toLowerCase()+"|PEODeleteMe01a");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
 		//Invalid Login 2
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Password","PEODeleteMe01b");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
 		//Invalid Login 3
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Password","PEODeleteMe01c");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
 		//Invalid Login 4
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Password","PEODeleteMe01d");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
 		//Invalid Login 5
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Password","PEODeleteMe01e");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
 		//Invalid Login 6
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Password","PEODeleteMe01e");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		strSnackbarText = objDictionary.get("strSnackbarText");
		if(strSnackbarText.equals("Invalid Credentials.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androidDriver,"The Text (Error Message) with index (1) did not contain (Invalid Credentials.) - actual value ("+strSnackbarText+")");}
		//UU Unlock User
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Approved Users","Local");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Approved Users", "Populate User Search", "{T} Email", strUserName);
 		clsCommonWeb.ClickButton(objDictionary, driver, "Approved Users", "Search", 1,"Local");
 		try {Thread.sleep(1000);}catch (Exception e) {}
 		clsCommonWeb.ClickLink(objDictionary, driver, "Approved Users", "Unlock", 1);
 		driver.quit();
 		//Login
 		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "PEO Login", "Populate Login", "{T} Email|{T} Password",strUserName.toLowerCase()+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "PEO Login", "Sign In",1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out",1);
		androidDriver.quit();
 		//DPEOU: Delete PEO User
 		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=329)
  	public void P4029F_FTFP0_CGPV_AV_ES1_VVR_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VVR: Validate Violation Removed                              ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
  		String strUnlockTime = "5";
  		String strUnlockMax = "5";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLicensePlateState = "Minnesota";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		driver.quit();
  		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	objDictionary.put("strInstallApp", "True");
      	AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		//Validate Violation Removed
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonMobile.PEO_ValidateViolationRemoved(objDictionary, androidDriver, strViolationId);
  		androidDriver.quit();
	  	//ValidateParkingSessionHistoryAndImages
  		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
  		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4029_FTFP0_CGPV_AV_ES1_VVR(objDictionary, strMeterIncrementTime, strViolationId);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=330)
  	public void P4030F_FTFP0_CGPV_AV_CV_ES1_VVR_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-123");
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VVR: Validate Violation Removed                             ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "3";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "2";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Minnesota";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
   		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		//Validated Claimed
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		//ShortSessionWaitExitSpot
  		objDictionary.put("strCheckIfSpotPresumedOccupied","False");
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//Validated Missed
      	clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");
      	driver.quit();
      	//Validate Violation Voided Dialog
      	clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "Violation Details", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
      	clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violation Details", "OK", 1);
      	//Validate Violation Removed
      	clsCommonMobile.PEO_ValidateViolationRemoved(objDictionary, androidDriver, strViolationId);
      	androidDriver.quit();
  		//ValidateParkingSessionHistoryAndImages
  		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
  		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4030_FTFP0_CGPV_AV_CV_ES1_VVR(objDictionary, strMeterIncrementTime, strViolationId);
  	  	clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=331)
  	public void P4031F_FTFP0_CGPV_AV_CV_IT_ES1_VVR_VPSH_VICAE_VIAC()
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERCA-124");
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
  		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("IT: Issue Ticket                                            ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VVR: Validate Violation Removed                             ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "3";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Minnesota";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
  		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Navigate to Violations
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
  		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
  		objDictionary.put("strInstallApp", "True");
  		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		//Validated Claimed
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver,"Initial Grace Period Exceeded", "Claimed");
  		//Click Issue Ticket
  		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		//ShortSessionWaitExitSpot
  		objDictionary.put("strCheckIfSpotPresumedOccupied","False");
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//Validated Missed
      	clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Missed");
      	driver.quit();
      	//Click Back
      	androidDriver.navigate().back();
      	//Validate Violation Removed
      	clsCommonMobile.PEO_ValidateViolationRemoved(objDictionary, androidDriver, strViolationId);
      	androidDriver.quit();
  		//ValidateParkingSessionHistoryAndImages
  		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
  		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4031_FTFP0_CGPV_AV_CV_IT_ES1_VVR(objDictionary, strMeterIncrementTime, strViolationId);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=332)
  	public void P4032F_CSLV_AV_CV_IT_ES1_VVR_VPSH_VICAE_VIAC()
  	{
		objDictionary.put("strAssociatedBug", "173360775|145555071");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CSLV: Create Sentry Link Violation                          ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CV: Claim Violation                                         ");
  		Reporter.log("IT: Issue Ticket                                            ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VVR: Validate Violation Removed                             ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Alabama";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//Dictionary Variables
		String strDeviceId = objDictionary.get("strDeviceId");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strEnvironment = objDictionary.get("strEnvironment");
		//PEO Claim Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		//Create New Violation
		clsCommonWeb.ClickLink(objDictionary, driver, "Violation", "New Violation", 1);
		String strViolationReason = "Expired Inspection Over 60 Days";
		objDictionary.put("strViolationReason", strViolationReason);
		clsCommonWeb.PopulateAction(objDictionary, driver, "New Violation", "Populate New Violation", "{T} Plate Number|{T} Province/State|{L} Device|{L} Violation reason", strLicensePlateNumber+"|Minnesota|"+strDeviceId+"|"+strViolationReason);
		clsCommonWeb.ClickButton(objDictionary, driver, "New Violation", "Create Violation", 1,"Local");
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Violations", 1);
		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 9,strDeviceId, 10, "Expired Inspection Over 60 Days", 12, "New", "strViolationRowNumber");
		String strViolationId = clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1,"strViolationId", "~strViolationRowNumber~", "2");
		driver.quit();
		//PEO Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
		//Issue Ticket
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "Issue Ticket",1);
		//Valid License From Sentry Link
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Issue ticket", "License plate", 1, "Contains", strLicensePlateNumber);
		//Populate Violation Fields
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Issue ticket", "Registration exp year", "2017");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Issue ticket", "Registration exp month", "01");
		//Valid License State From Sentry Link
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Issue ticket", "License plate state", 1, "Value", "Minnesota");
		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Issue ticket", "Vehicle body type", strVehicleBodyType);
		//Populate Registration Type
		String strRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androidDriver, "Issue ticket", "Registration type", strRegistrationType);
		//Validate Violation reason
		String strExtenalViolationReason = "";
		try{ strExtenalViolationReason = clsHttpConnections.Json_ExternalViolationId(objDictionary, strViolationReason);}catch (Exception e) {}
		if(strEnvironment.equals("QA"))
		{
			if(strExtenalViolationReason.equals("Additional Time Expired")){strExtenalViolationReason = "Time Expired";}
		}
		WebElement objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Issue ticket", "Violation reason", 1, "Value", strExtenalViolationReason);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Issue ticket", "Generate Ticket",1);
		//Validate Ticket Data
		clsCommonMobile.PEO_ValidateTicketValues(objDictionary, androidDriver,"$30.00","1");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Ticket Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "LEAVE", 1);
		androidDriver.quit();
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4032_CSLV_AV_CV_IT_ES1_VVR(objDictionary, strViolationId);
  	}

	@Test(priority=332)
  	public void P4032F_B_FTFP0_CGPV_AV_PCV()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID clsCommonMobile = new CommonANDROID();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("PCV: PEO Claim Violation                                    ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("VMU: Validate Meter Unlocked                                ");
		Reporter.log("VPVR: Validate PEO Violation Removed                        ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		//String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
  		//String strLicensePlateNumber = "7716674";// 	Finalized Errors No space left on device @ rb_sysopen - /tmp/c603ff32794f8c3a72944b2e6af6057920240906-3520-jkobvc.pdf
  		String strLicensePlateNumber = "7716674";
  		String strLiceseState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLiceseState);
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","1");
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDate(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","2");
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary,strViolationId,"Test1.txt","3");

		//Validate Next Overdue Date equal current date
		//String strNextOverdueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//clsCommonWeb.VerificationPointText(objDictionary, driver,"Violations", "Next Overdue", 1, "Value", strNextOverdueDate);


		//Navigate to

//		//CP1: Coin Payment Spot 1
//  		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
//  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  Integer.parseInt(strMeterIncrementTime),"1");
//		//Validate Violation Removed from PEO Application
//		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 30,strSpotNumber,"Local");
//		try {Thread.sleep(5000);}catch (Exception e) {}
//		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "OK", 1);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
//  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "New Violations", "OK", 1);
//  		androiddriver.quit();
//		//ShortSessionWaitExitSpot
//      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
//		//ValidateParkingSessionHistoryAndImages
//      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
//      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4020_FTFP0_CGPV_AV_PCV_CP1_VMU_VPVR(objDictionary, strMeterIncrementTime, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	@Test(priority=332)
  	public void P4032BF_FTFP0_CGPV_AV_PCV()
	{
		objDictionary.put("strAssociatedBug", "183842654|172317409");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID clsCommonMobile = new CommonANDROID();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("PCV: PEO Claim Violation                                    ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("VMU: Validate Meter Unlocked                                ");
		Reporter.log("VPVR: Validate PEO Violation Removed                        ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		//String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
  		//String strLicensePlateNumber = "7716674";// 	Finalized Errors No space left on device @ rb_sysopen - /tmp/c603ff32794f8c3a72944b2e6af6057920240906-3520-jkobvc.pdf
  		String strLicensePlateNumber = "7716674";
  		String strLiceseState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket SL
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		driver.quit();
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		try {Thread.sleep(3000);}catch (Exception e) {}

		//
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Notified");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		String pdfUrl = clsCommonWeb.StoreTableValueHREF(objDictionary, driver, "Violations", "Mail Printings", 1, "strHref", "1", "2");





		//PEO Claim Violation (Not used for every ticket service)
//		objDictionary.put("strInstallApp", "True");
//		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO","True","Parker");
//		clsCommonMobile.PEO_Login(objDictionary, androiddriver);
//		clsCommonMobile.PEO_ClaimViolation(objDictionary, androiddriver, strViolationId);
//		//StoreViolationNumber
//		clsCommonMobile.StoreText(objDictionary, androiddriver, "Violation Detail", "Violation No", 1, "strViolationNo");
//		//Issue Ticket
//		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Claimed");
//		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "Issue Ticket",1);
//		try {Thread.sleep(1000);}catch (Exception e) {}
//		//Populate Violation Fields
//		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Issue ticket", "Populate Claim", "{T} License plate",strLicensePlateNumber);
//		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Registration exp year", "2024");
//		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Registration exp month", "02");
//		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "License plate state", strLiceseState);
//		clsCommonMobile.StoreTextField(objDictionary, androiddriver, "Issue ticket", "License plate state", 1, "strLicenseState");
//		String strRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
//		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Registration Type", strRegistrationType);
//		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Vehicle Body Type", strVehicleBodyType);
//		WebElement objTicketFrame = androiddriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
//		ElementSwipe  clsElementSwipe  = new ElementSwipe();
//		clsElementSwipe.Swipe(androiddriver,objTicketFrame,"SWIPE_UP");
//		String strNumberOfViolationImages = "7";objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
//		if(!strNumberOfViolationImages.equals("0"))
//		{
//			int intNumberOfViolationImages = Integer.parseInt(strNumberOfViolationImages);
//			for(int intViolationCount = 2; intViolationCount<= intNumberOfViolationImages+1; intViolationCount++)
//			{
//				//Add 1st Violation Picture
//				if(intViolationCount == 1)
//				{clsCommonMobile.ClickButton(objDictionary, androiddriver, "Issue ticket", "Violation Picture",1);}
//				else
//				{clsCommonMobile.ClickButton(objDictionary, androiddriver, "Issue ticket", "Violation Picture "+intViolationCount,1);}
//				try {Thread.sleep(1500);}catch (Exception e) {}
//				clsCommonMobile.ClickButton(objDictionary, androiddriver, "Issue ticket", "Picture Button",1);
//				try {Thread.sleep(4000);}catch (Exception e) {}
//				clsCommonMobile.ClickLink(objDictionary, androiddriver, "Issue ticket", "OK", 1);
//				try {Thread.sleep(1500);}catch (Exception e) {}
//				objTicketFrame = androiddriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
//				clsElementSwipe.Swipe(androiddriver,objTicketFrame,"SWIPE_UP");
//				try {Thread.sleep(1000);}catch (Exception e) {}
//				if(intViolationCount > 1)
//				{
//					int intSwipeNumber = intViolationCount;
//					WebElement objPictureFrame = androiddriver.findElement(By.id("com.mpspark.mobileOfficer:id/imgViolationPicture"+intSwipeNumber));
//					clsElementSwipe.Swipe(androiddriver,objPictureFrame,"SWIPE_LEFT");
//					clsElementSwipe.Swipe(androiddriver,objPictureFrame,"SWIPE_LEFT");
//					try {Thread.sleep(1000);}catch (Exception e) {}
//				}
//			}
//		}
//		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Issue ticket", "Generate Ticket",1);
//		clsCommonMobile.PEO_ValidateTicketValues(objDictionary, androiddriver,"$30.00","1");
//		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Generating Ticket");
//		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Ticket", "Ticket Issued", 1);
//		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Ticket", "LEAVE", 1);
//		try {Thread.sleep(5000);}catch (Exception e) {}
//		clsCommonWeb.SENTRYLINK_ValidateViolationStatus_UsingLicensePlateAndState(objDictionary, driver, strLiceseState+" | "+strLicensePlateNumber, "Notified");
//		//driver.quit();
//		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "api_application");
		try {Thread.sleep(30000);}catch (Exception e) {}
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
//		String strSLViolationId = objDictionary.get("strSLViolationId");
//		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strSLViolationId, 1);
//		//Validate Next Over Due Time
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date currentDate = new Date();
//        String strCurrentDate = dateFormat.format(currentDate);
//		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violation", "Next Overdue", 1, "Value", strCurrentDate);
		//driver.quit();
		((Navigation) driver).refresh();

//		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
//		driver = getDriver();
//		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
//		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
//		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
//		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
//		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");

		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");


		//Function
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, "http://localhost:9999", objDictionary);
		WebDriver sidekiq = getDriver();
		sidekiq.get("https://sentrylink.staging.sentry-link.com/sidekiq/cron");
		String strUserName = "";
		String strRole = "admin";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		if(strEnvironment.equals("PROD"))
		{
			strUserName = "darin@mpspark.com";
			if(strRole.equals("admin")||strRole.equals("peo")){strUserName = "darinadmin@mpspark.com";}
		}
		else
		{
			if(strRole.equals("parking_enforcement_officer")){strRole = "peo";}
			strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
		}
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		clsCommonWeb.PopulateAction(objDictionary, sidekiq, "Sidekiq Login", "Populate Login", "{T} Email|{T} Password",strUserName.toLowerCase() + "|" + strPassword);
		clsCommonWeb.ClickButton(objDictionary, sidekiq, "Sidekiq Login", "Sign in", 1,"Local");
		sidekiq.navigate().to("https://sentrylink.staging.sentry-link.com/sidekiq/cron/OverdueViolationProcessor");
		clsCommonWeb.ClickButton(objDictionary, sidekiq, "OverdueViolationProcessor", "Enqueue Now", 1,"Local");
		sidekiq.quit();


		//Validate Date
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		//Validate Next Overdue Date equal current date
		String strNextOverdueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		clsCommonWeb.VerificationPointText(objDictionary, driver,"Violations", "Next Overdue", 1, "Value", strNextOverdueDate);


		//Navigate to

//		//CP1: Coin Payment Spot 1
//  		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
//  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  Integer.parseInt(strMeterIncrementTime),"1");
//		//Validate Violation Removed from PEO Application
//		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 30,strSpotNumber,"Local");
//		try {Thread.sleep(5000);}catch (Exception e) {}
//		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "OK", 1);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
//  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "New Violations", "OK", 1);
//  		androiddriver.quit();
//		//ShortSessionWaitExitSpot
//      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
//		//ValidateParkingSessionHistoryAndImages
//      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
//      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4020_FTFP0_CGPV_AV_PCV_CP1_VMU_VPVR(objDictionary, strMeterIncrementTime, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	@Test(priority=332)
  	public void P4033F_FTFP0_CGPV_AV_PCV()
	{
		objDictionary.put("strAssociatedBug", "183842654|172317409");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("PCV: PEO Claim Violation                                    ");
		Reporter.log("CP1: Coin Payment Spot 1                                    ");
		Reporter.log("VMU: Validate Meter Unlocked                                ");
		Reporter.log("VPVR: Validate PEO Violation Removed                        ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = clsCommonMobile.SENTRYMOBILE_CreateRandomLicensePlateNumber();
  		//Enable Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"salient");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"salient");

  		String strLiceseState = "Minnesota";
		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		//PEO Claim Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver, strViolationId);
		//StoreViolationNumber
		clsCommonMobile.StoreText(objDictionary, androidDriver, "Violation Detail", "Violation No", 1, "strViolationNo");
		//Issue Ticket
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violations", "Issue Ticket",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		//Populate Violation Fields
		clsCommonMobile.PopulateAction(objDictionary, androidDriver, "Issue ticket", "Populate Claim", "{T} License plate",strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Issue ticket", "Registration exp year", "2024");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Issue ticket", "Registration exp month", "02");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Issue ticket", "License plate state", strLiceseState);
		clsCommonMobile.StoreTextField(objDictionary, androidDriver, "Issue ticket", "License plate state", 1, "strLicenseState");
		String strRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Issue ticket", "Registration Type", strRegistrationType);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Issue ticket", "Vehicle Body Type", strVehicleBodyType);
		WebElement objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		String strNumberOfViolationImages = "7";objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		if(!strNumberOfViolationImages.equals("0"))
		{
			int intNumberOfViolationImages = Integer.parseInt(strNumberOfViolationImages);
			for(int intViolationCount = 2; intViolationCount<= intNumberOfViolationImages+1; intViolationCount++)
			{
				//Add 1st Violation Picture
				if(intViolationCount == 1)
				{clsCommonMobile.ClickButton(objDictionary, androidDriver, "Issue ticket", "Violation Picture",1);}
				else
				{clsCommonMobile.ClickButton(objDictionary, androidDriver, "Issue ticket", "Violation Picture "+intViolationCount,1);}
				try {Thread.sleep(1500);}catch (Exception e) {}
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Issue ticket", "Picture Button",1);
				try {Thread.sleep(4000);}catch (Exception e) {}
				clsCommonMobile.ClickLink(objDictionary, androidDriver, "Issue ticket", "OK", 1);
				try {Thread.sleep(1500);}catch (Exception e) {}
				objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
				try {Thread.sleep(1000);}catch (Exception e) {}
				if(intViolationCount > 1)
				{
					int intSwipeNumber = intViolationCount;
					WebElement objPictureFrame = androidDriver.findElement(By.id("com.mpspark.mobileOfficer:id/imgViolationPicture"+intSwipeNumber));
					clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_LEFT");
					clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_LEFT");
					try {Thread.sleep(1000);}catch (Exception e) {}
				}
			}
		}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Issue ticket", "Generate Ticket",1);
		clsCommonMobile.PEO_ValidateTicketValues(objDictionary, androidDriver,"$30.00","1");
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded", "Generating Ticket");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Ticket Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "LEAVE", 1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus_UsingLicensePlateAndState(objDictionary, driver, strLiceseState+" | "+strLicensePlateNumber, "Notified");
		//driver.quit();
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "api_application");
		String strSLViolationId = objDictionary.get("strSLViolationId");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strSLViolationId, 1);
		//Validate Next Over Due Time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String strCurrentDate = dateFormat.format(currentDate);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violation", "Next Overdue", 1, "Value", strCurrentDate);
		driver.quit();

		//Function
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, "http://localhost:9999", objDictionary);
		WebDriver sidekiq = getDriver();
		sidekiq.get("https://sentrylink.staging.sentry-link.com/sidekiq/cron");
		String strUserName = "";
		String strRole = "admin";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		if(strEnvironment.equals("PROD"))
		{
			strUserName = "darin@mpspark.com";
			if(strRole.equals("admin")||strRole.equals("peo")){strUserName = "darinadmin@mpspark.com";}
		}
		else
		{
			if(strRole.equals("parking_enforcement_officer")){strRole = "peo";}
			strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
		}
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		clsCommonWeb.PopulateAction(objDictionary, sidekiq, "Sidekiq Login", "Populate Login", "{T} Email|{T} Password",strUserName.toLowerCase() + "|" + strPassword);
		clsCommonWeb.ClickButton(objDictionary, sidekiq, "Sidekiq Login", "Sign in", 1,"Local");
		sidekiq.navigate().to(" https://sentrylink.staging.sentry-link.com/sidekiq/cron/OverdueViolationProcessor");
		clsCommonWeb.ClickButton(objDictionary, sidekiq, "OverdueViolationProcessor", "Enqueue Now", 1,"Local");
		sidekiq.quit();

		//Validate Date
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToOverdueViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
	    //Validate Next Overdue Date equal current date
		String strNextOverdueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		clsCommonWeb.VerificationPointText(objDictionary, driver,"Violations", "Next Overdue", 1, "Value", strNextOverdueDate);


		//Navigate to

//		//CP1: Coin Payment Spot 1
//  		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
//  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  Integer.parseInt(strMeterIncrementTime),"1");
//		//Validate Violation Removed from PEO Application
//		clsMeter.GlobalWait(objDictionary, null,  "{WaitUntilMeterViolationEqualsFalse} NA", 30,strSpotNumber,"Local");
//		try {Thread.sleep(5000);}catch (Exception e) {}
//		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "OK", 1);
//		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "New Violations", "This violation is now invalid. Returning to Violation List.", 1, "Exists", "");
//  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "New Violations", "OK", 1);
//  		androiddriver.quit();
//		//ShortSessionWaitExitSpot
//      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
//		//ValidateParkingSessionHistoryAndImages
//      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
//      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4020_FTFP0_CGPV_AV_PCV_CP1_VMU_VPVR(objDictionary, strMeterIncrementTime, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

  	//ALERTS
  	@Test(priority=333)//Unable to read email content
  	public void P4033F_AMMOE_CGPV1_AV_VAE_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		objDictionary.put("strAssociatedBug", "178800354");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		Gmail  clsGmail = new Gmail();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("AMMOEE: Automatically Mark Missed On Exit Enabled           ");
  		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Aprove Violation                                        ");
  		Reporter.log("VAE: Validate Alert Email                                   ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("VICAE: Validate Image Count After Exit                      ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
  		String strHost = objDictionary.get("strHost");
  		String strSpotNumber = objDictionary.get("strSpotNumber");
  		String strBrowser = objDictionary.get("strBrowser");
  		String strRemotePath = objDictionary.get("strRemotePath");
  		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		//Test Case Variables
  		String strMaximumDuration = "240";
  		String strCoinTimePuchaseLimit = "240";
  		String strFreeTimeFirstPayment = "0";
  		String strCreditCardIncrementTime = "3";
  		String strInitialGracePeriod = "1";
  		String strViolationGracePeriod = "1";
  		String strHandicapInitialGracePeriod = "5";
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";
  		String strSetImageSendBeforeViolation = "40";
  		String strParkingShortSessionSec = "15";
  		String strUnlockValue =  "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Alabama";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//Create PEO User
  		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//Enable Subscription violation email
  		clsCommonWeb.SENTRYLINK_PopulateOtherSubscriptions(objDictionary,"violation", "Checked", "email");
		//Get PEO Password
  		String strPassword = clsGmail.Gmail_GetPEOGmailPassword(objDictionary);
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+"peo@gmail.com";
 		//Delete All Email
  		clsGmail.Gmail_DeleteAllEmails(strUserName,strPassword);
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
  		//Create Grace Period Violation
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		String strViolationNumber = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Approve Violation
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
   		WebDriver driver = getDriver();
   		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationNumber,1);
  		try {Thread.sleep(40000);}catch (Exception e) {}
  		getDriver().quit();
  		String strSLViolationId = objDictionary.get("strSLViolationId");//488299
  		String strViolationAlertTime = clsHttpConnections.CURL_GetViolationsAlertTime(objDictionary,strSLViolationId);
  		//Validate Violation Alert Email
  		String strMeterSpotName = clsMeter.METER_GetMeterSpotName(objDictionary, strSpotNumber);
  		String strExpectedMessage = "A parking violation occurred for a vehicle with license plate "+strLicensePlateNumber+" at "+strViolationAlertTime+" at meter "+strMeterSpotName+" because: Initial Grace Period Exceeded";
		try {Thread.sleep(20000);}catch (Exception e) {}
		clsGmail.Gmail_ValidateViolationAlertEmailContains(objDictionary,strLicensePlateNumber,strUserName,strPassword,"Parking Violation at spot "+strMeterSpotName+": Initial Grace Period Exceeded", strExpectedMessage);
		//Delete All Email
  		clsGmail.Gmail_DeleteAllEmails(strUserName,strPassword);
  		//ShortSessionWaitExitSpot
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
    	try {Thread.sleep(20000);}catch (Exception e) {}
    	//Validate Violation Alert Email
      	clsGmail.Gmail_ValidateViolationAlertEmailContains(objDictionary,strLicensePlateNumber,strUserName,strPassword,"Vehicle Departed at spot "+strMeterSpotName+" (Violation: Initial Grace Period Exceeded)", strExpectedMessage);
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4033_AMMOE_CGPV1_AV_VAE_ES1(objDictionary,strViolationNumber);
 		//Disable Subscription violation email
      	clsCommonWeb.SENTRYLINK_PopulateOtherSubscriptions(objDictionary,"violation", "UnChecked", "email");
      	clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	//EXEMPTIONS
  	@Test(priority=334)
  	public void P4034F_SLPE_CGPV1_AV_VVV_ES1_VPSH()
  	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("SLPE: Set License Plate Exempt                              ");
  		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("VVV: Validate Violation Voided                              ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "40";
		String strParkingShortSessionSec = "15";
		String strUnlockValue =  "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Minnesota";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Add License To Exemption List
		clsCommonWeb.SENTRYLINK_AddLicenseToExemptionList(objDictionary,strLicensePlateNumber, strLiceseState);
		//GPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Grace Period Violation Number
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
   		//Check If Parking Enforcement Officer Exists
   		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
   		//ValidateParkingSessionHistoryAndImages
   		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
   		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4034_SLPE_CGPV1_AV_VVV_ES1(objDictionary,strViolationId,strLicensePlateNumber,strLiceseState);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=335)
 	public void P4035F_SLPE_CMV_IGPE_ES1_VPSH() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "SL-4161");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("SLPE: Set License Plate Exempt                              ");
  		Reporter.log("CMV: Create Manual Violation                                ");
  		Reporter.log("IGPE: Initial Grace Period Exceeded                         ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strTicketAmountDue = "$30.00";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strState", strState);
  		//Add License To Exemption List
  		clsCommonWeb.SENTRYLINK_AddLicenseToExemptionList(objDictionary,strLicensePlateNumber, strState);
  		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_CreateManualViolationExemptPlate(objDictionary, androidDriver, strViolationDescription, strTicketAmountDue,"1");
		androidDriver.quit();
		//Store Grace Period Violation Number
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		//Get Voided ViolationID
   		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumberVoided(objDictionary,driver);
   		driver.quit();
   		//ValidateParkingSessionHistoryAndImages
   		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
   		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4035_SLPE_CMV_IGPE_ES1(objDictionary,strViolationId,strLicensePlateNumber,strState);
  		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//UNLOCK
  	@Test(priority=336)
	public void P4036F_FTFP0_MUON_CGPV1_UM_CP1_LMV_AP_CV_SV_LSTE_ES_VPSH()
	{
  		objDictionary.put("strAssociatedBug", "186518801|184791360|184416425|178802735");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("MUON: Meter Unlock On                                       ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("AV: Approve Violation                                       ");
		Reporter.log("UM_CP1: Unlock Meter Coin Payment                           ");
		Reporter.log("LMV: Let Meter Violate                                      ");
		Reporter.log("AV: Approve Violation                                       ");
		Reporter.log("CV: Claim Violation                                         ");
		Reporter.log("SV: Snooze Violation                                        ");
		Reporter.log("LSTE: Let Snooze Time Expire                                ");
		Reporter.log("ES: Exit Spot                                               ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "5";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
  		String strUnlockTime = "10";
  		String strUnlockMax = "5";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Minnesota";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.put("strTrueUp", "True");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
		//Check If Parking Enforcement Officer Exists
  		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//CGPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 	    HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
 		String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetSLViolationNumber(objDictionary, "Local",strSpotNumber, 1);
  		String strViolationNumber = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", strSpotNumber);
	  	//AV: Approve Violation
 		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
 		WebDriver driver = getDriver();
 		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationNumber,1);
 		driver.quit();
	    //UM_CP1: Unlock Meter Coin Payment Spot 1
 		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
  		//LMV: Let Meter Violate
	    String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary, null, "1");
	    int intSecondsBeforeViolation = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime));
	    clsMeter.METER_MeterWaitWithMessage(objDictionary,intSecondsBeforeViolation, "Waiting for meter to expire-Spot"+strSpotNumber);
   		Reporter.log("Waited ("+intSecondsBeforeViolation+") seconds for meter to Violate");
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		//LVGPE: Let Violation Grace Period Expires
   		clsMeter.METER_WaitUntilViolationGracePeriodExpires(objDictionary, null);
   		//VMV1: Validate Meter Violated Spot 1
  		clsMeter.METER_ValidateMeterViolated(objDictionary, null,"1","Local");
  		//Get Violation Number
  		try {Thread.sleep(50000);}catch (Exception e) {}
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strViolationId2 = "";
		if(strEnvironment.equals("PROD"))
		{
			strViolationId2 = clsHttpConnections.HTTPCONNECTIONS_GetSLViolationNumber(objDictionary, "Local",strSpotNumber, 1);
		}
		else
		{
			strViolationId2 = clsHttpConnections.HTTPCONNECTIONS_GetSLViolationNumber(objDictionary, "Local",strSpotNumber, 2);
		}
  		String strViolationNumber2 = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", strSpotNumber);
  	  	//Approve Violation
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Time Expired",strViolationNumber2,2);
		driver.quit();
		//Open PEO Application
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		//Set Snooze Timer
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Snooze time setting", 1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		//CV: Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationNumber2);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Snooze",1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		//Let Snooze Time Expire
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Waiting for Snooze Time to expire");
   		Reporter.log("Waited (60) seconds for Snooze Timer to Violate");
   		androidDriver.quit();
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
    	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4036_FTFP0_MUON_CGPV1_UM_CP1_LMV_AP_CV_SV_LSTE_ES(objDictionary, strViolationNumber,strViolationNumber2);
    	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=337)
	public void P4037F_FTFP10_MUON_CGPV1_UM_CP1_LMV_AP_CV_SV_LSTE_ES_VPSH()
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP10: Set Free Time First Payment 10                      ");
		Reporter.log("MUON: Meter Unlock On                                       ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("AV: Approve Violation                                       ");
		Reporter.log("UM_CP1: Unlock Meter Coin Payment                           ");
		Reporter.log("LMV: Let Meter Violate                                      ");
		Reporter.log("AV: Approve Violation                                       ");
		Reporter.log("CV: Claim Violation                                         ");
		Reporter.log("SV: Snooze Violation                                        ");
		Reporter.log("LSTE: Let Snooze Time Expire                                ");
		Reporter.log("ES: Exit Spot                                               ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "3";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue", strUnlockValue);
  		String strUnlockTime = "10";objDictionary.put("strUnlockTime", strUnlockTime);
  		String strUnlockMax = "5";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
 		String strLiceseState = "Minnesota";
 		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.put("strTrueUp", "True");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Enabled Automatically Mark Missed On Exit
  		clsCommonWeb.SENTRYLINK_UpdateTicketingServices(objDictionary,"Checked");
		//Check If Parking Enforcement Officer Exists
  		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
  		//CGPV1: Create Grace Period Violation Spot 1
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
  		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//AV: Approve Violation
 		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
 		WebDriver driver = getDriver();
 		clsCommonWeb.SENTRYLINK_NavigateToAndApproveViolations(objDictionary,driver,strLicensePlateNumber, strLiceseState,"Initial Grace Period Exceeded",strViolationId,1);
 		driver.quit();
	    //UM_CP1: Unlock Meter Coin Payment Spot 1
 		clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
  		//LMV: Let Meter Violate
	    String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary, null, "1");
	    int intSecondsBeforeViolation = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime));
	    clsMeter.METER_MeterWaitWithMessage(objDictionary,intSecondsBeforeViolation, "Waiting for meter to expire-Spot"+strSpotNumber);
   		Reporter.log("Waited ("+intSecondsBeforeViolation+") seconds for meter to Violate");
   		try {Thread.sleep(10000);}catch (Exception e) {}
   		//LVGPE: Let Violation Grace Period Expires
   		clsMeter.METER_WaitUntilViolationGracePeriodExpires(objDictionary, null);
   		//VMV1: Validate Meter Violated Spot 1
  		clsMeter.METER_ValidateMeterViolated(objDictionary, null,"1","Local");
  		//Get Violation Number
  		String strViolationId2 = clsCommonWeb.SENTRYLINK_StoreTimeExpiredViolationNumber(objDictionary,"New");
  		//Approve Violation
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_UseLicensePlateToNavigateToAndApproveViolations(objDictionary,driver, strLicensePlateNumber, strLiceseState, "Time Expired", strViolationId2,2);
		driver.quit();
	    //Open PEO Application
		objDictionary.put("strInstallApp", "True");
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		//Set Snooze Timer
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Snooze time setting", 1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		//CV: Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId2);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Snooze",1);
		clsCommonMobile.PEO_SelectSnoozeTime(androidDriver, 1);
		//Let Snooze Time Expire
		clsMeter.METER_MeterWaitWithMessage(objDictionary,65, "Waiting for Snooze Time to expire");
   		Reporter.log("Waited (65) seconds for Snooze Timer to Violate");
   		androidDriver.quit();
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4037_FTFP10_MUON_CGPV1_UM_CP1_LMV_AP_CV_SV_LSTE_ES(objDictionary, strViolationId,strViolationId2);
    	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//Legacy Meter/Virtual Meters
  	@Test(priority=338)
  	public void P4038F_VM_JMP_VPLS_CLS_VPSH()throws Exception
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-107");
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		GlobalClass clsGlobal = new GlobalClass();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.PEO_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("VM: Virtual Meter                                           ");
  		Reporter.log("JMP: Json Meter Payment                                     ");
  		Reporter.log("VPLS: Validate PEO Legacy Session                           ");
  		Reporter.log("CLS: Clear Legacy Session                                   ");
  		Reporter.log("VPSH: Validate Parking Session History                                                             ");
  		Reporter.log("************************************************************");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
 		objDictionary.put("strInstallApp", "True");
 		objDictionary.put("strVirtualMeter", "True");
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter6");
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Remove Users Current License Plates
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate Sentry Link
		//clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Change Device to Virtual Device
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter6");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter6");
		objDictionary.put("strVirtualMeter", "True");
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strMeterIncrementTime = "3";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		Reporter.log("***************TestCase Description*************************");
		//clsCommonMobile.PEO_ClearLegacySessions(objDictionary);
		String strMunicipality = objDictionary.get("strMunicipality");if(strMunicipality == null) {strMunicipality = "";}
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary,"parker");
		clsHttpConnections.JSON_MobilePayment(objDictionary);
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Validate Legacy Session
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Legacy Sessions", 1);
		//Validate Lot Name
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "Legacy Sessions", "Lot Name", 1, "Contains", strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "Legacy Sessions", "License Plate", 1, "Contains", strLicensePlateNumber);
		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "Legacy Sessions", "Time Remaining", 1, "Contains", "Time remaining: 2 Mins");
		String strParkedAtDate = objDictionary.get("strParkedAtDate");
		SimpleDateFormat inputFormat  = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = inputFormat.parse(strParkedAtDate);
		String strParkedAtDateFormatted = outputFormat.format(date);  
		clsCommonMobile.VerificationPointText(objDictionary, androidDriver, "Legacy Sessions", "Parked At", 1, "Contains", strParkedAtDateFormatted);
		//Clear Legacy Session
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Legacy Sessions", "Clear", 1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Legacy Sessions", "Confirm", 1);
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_4038_VM_JMP_VPLS_CLS(objDictionary);
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=339)
  	public void P4039F_FTFP0_CGPV1_DNAATTPAV_AV_CAV_PCV_VIPV_CVP_AATTPAV_PGT_VGTV_PIT_VITV_ES1()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-108");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("DNAATTPAV: Do not allow App To Take Picture And Video       ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CAV: Clear All Violations                                   ");
  		Reporter.log("PCV: PEO Claim Violation                                    ");
  		Reporter.log("VIPV: Validate In Progress Violations                       ");
  		Reporter.log("CVP: Click Violation Picture                                ");
  		Reporter.log("AATTPAV: Allow App To Take Picture And Video                ");
  		Reporter.log("PGT: PEO Generate Ticket                                    ");
  		Reporter.log("VGTV: Validate Generated Ticket Violation                   ");
  		Reporter.log("PIT: PEO Issue Ticket                                       ");
  		Reporter.log("VITV: Validate Issued Ticket Violation                      ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
  		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"BB";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	//Delete Reservation Remover Permeit
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			HttpConnections clsHttpConnections = new HttpConnections();
  	  		clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Create Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		//DNAATTPAV: Do not allow App To Take Picture And Video
		objDictionary.put("strAllowAppToTakePictureAndVideo","False");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//Navigate To In Progress Violations
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Violation Details", "In Progress Violations", 1);
		clsCommonMobile.PEO_ValidateInProgressViolationsExists(objDictionary, androidDriver, strViolationId);
		//CAV: Clear All Violations
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "Yes, clear them all", 1);
		clsCommonMobile.DismissNotification(androidDriver);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "New Violations", 1);
		//Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate", 1, "Contains", "~strLicensePlate~");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Registration Type", "Passenger");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Vehicle Body Type", "TRUCK");
		try {Thread.sleep(500);}catch (Exception e) {}
		WebElement objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		String strNumberOfViolationImages = "1";
		WebElement objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc='Violation Details:\nViolation Photos:']/android.view.View[3]/android.view.View[1]"));
		clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
		if(!strNumberOfViolationImages.equals("0"))
		{
			int intNumberOfViolationImages = Integer.parseInt(strNumberOfViolationImages);
			for(int intViolationCount = 1; intViolationCount<= intNumberOfViolationImages; intViolationCount++)
			{
				//Add 1st Violation Picture
				if(intViolationCount > 1)
				{clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Add Photo",1);}
				try {Thread.sleep(1000);}catch (Exception e) {}
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Violation Photo",1);
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Picture Button",1);
				clsCommonMobile.ClickLink(objDictionary, androidDriver, "Create Violation", "OK", 1);
				objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
				objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\"Violation Details:\nViolation Photos:\"]/android.view.View/android.view.View"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
				if(intViolationCount > 4)//Additional Swipe need to center image.
				{
					clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
				}
			}
		}
		else
		{
			objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
			clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		}
		try {Thread.sleep(3500);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		//Validate Violation Status Missed
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		//Validate Notified
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Notified");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Province/State", 1, "Value", "~strLicenseState~");
		driver.quit();
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait For Ticket To Get Generated");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4039_FTFP0_CGPV1_DNAATTPAV_AV_CAV_PCV_VIPV_CVP_AATTPAV_PGT_VGTV_PIT_VITV_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=340)
  	public void P4040F_FTFP0_CGPV1_DNAATTPAV_AV_CAV_PCV_VIPV_CVP_AATTPAV_PGT_VGTV_PIT_VITV_ES1()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERPEO-108");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
  		CommonANDROID_Flutter  clsCommonMobile = new CommonANDROID_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("FTFP0: Set Free Time First Payment 0                        ");
		Reporter.log("CGPV1: Create Grace Period Violation Spot 1                 ");
		Reporter.log("DNAATTPAV: Do not allow App To Take Picture And Video       ");
  		Reporter.log("AV: Approve Violation                                       ");
  		Reporter.log("CAV: Clear All Violations                                   ");
  		Reporter.log("PCV: PEO Claim Violation                                    ");
  		Reporter.log("VIPV: Validate In Progress Violations                       ");
  		Reporter.log("CVP: Click Violation Picture                                ");
  		Reporter.log("AATTPAV: Allow App To Take Picture And Video                 ");
  		Reporter.log("PGT: PEO Generate Ticket                                    ");
  		Reporter.log("VGTV: Validate Generated Ticket Violation                   ");
  		Reporter.log("PIT: PEO Issue Ticket                                       ");
  		Reporter.log("VITV: Validate Issued Ticket Violation                      ");
  		Reporter.log("ES1: Exit Spot 1                                            ");
  		Reporter.log("NTVH: Navigate To Violation History                         ");
  		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";
		String strCreditCardIncrementTime = "3";
		String strInitialGracePeriod = "1";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "1";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"BB";
		String strLiceseState = "Minnesota";
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
  	 	//Delete Reservation Remover Permeit
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			HttpConnections clsHttpConnections = new HttpConnections();
  	  		clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Create Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Grace Period Violation Number
 		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
 		//Store Parking Id
 		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Check If Parking Enforcement Officer Exists
 		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Navigate to Violations
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
		clsCommonWeb.SENTRYLINK_ApproveViolation(objDictionary, driver);
		AppiumDriver androidDriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO", "True", "Parker");
		//DNAAATPAV: Do not allow App Access to Photos And Video
		objDictionary.put("strAllowAccessPhotosAndVideos","False");
		clsCommonMobile.PEO_Login(objDictionary, androidDriver);
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		//Navigate To In Progress Violations
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "In Progress Violations", 1);
		clsCommonMobile.PEO_ValidateInProgressViolationsExists(objDictionary, androidDriver, strViolationId);
		//CAV: Clear All Violations
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "Clear violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "Yes, clear them all", 1);
		clsCommonMobile.DismissNotification(androidDriver);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "In Progress Violations", "Menu", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "In Progress Violations", "New Violations", 1);
		//Claim Violation
		clsCommonMobile.PEO_ClaimViolation(objDictionary, androidDriver,strViolationId);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Violation Details", "Issue Ticket",1);
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate", 1, "Contains", "~strLicensePlate~");
		clsCommonMobile.VerificationPointTextField(objDictionary, androidDriver, "Create Violation", "License plate state", 1, "Value", "~strLicenseState~");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Registration Type", "Passenger");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androidDriver, "Create Violation", "Vehicle Body Type", "TRUCK");
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		WebElement objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		String strNumberOfViolationImages = "1";
		WebElement objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc='Violation Details:\nViolation Photos:']/android.view.View[3]/android.view.View[1]"));
		clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
		if(!strNumberOfViolationImages.equals("0"))
		{
			int intNumberOfViolationImages = Integer.parseInt(strNumberOfViolationImages);
			for(int intViolationCount = 1; intViolationCount<= intNumberOfViolationImages; intViolationCount++)
			{
				//Add 1st Violation Picture
				if(intViolationCount > 1)
				{clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Add Photo",1);}
				try {Thread.sleep(1000);}catch (Exception e) {}
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Violation Photo",1);
				clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Picture Button",1);
				clsCommonMobile.ClickLink(objDictionary, androidDriver, "Create Violation", "OK", 1);
				objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
				objPictureFrame = androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\"Violation Details:\nViolation Photos:\"]/android.view.View/android.view.View"));
				clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
				if(intViolationCount > 4)//Additional Swipe need to center image.
				{
					clsElementSwipe.Swipe_Flutter(androidDriver,objPictureFrame,"SWIPE_LEFT");
				}
			}
		}
		else
		{
			objTicketFrame = androidDriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
			clsElementSwipe.Swipe_Flutter(androidDriver,objTicketFrame,"SWIPE_UP");
		}
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Create Violation", "Generate Ticket",1);
		driver.quit();
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "Ticket", "Issued", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "Ticket", "Leave", 1);
		clsCommonMobile.DismissNotification(androidDriver);
		clsCommonMobile.ClickButton(objDictionary, androidDriver, "New Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androidDriver, "New Violations", "Log out", 1);
		//Validate Violation Status Missed
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		//Validate Notified
		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Notified");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "~strViolationId~", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "License Plate", 1, "Contains", "~strLicensePlate~");
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Province/State", 1, "Value", "~strLicenseState~");
		driver.quit();
		clsMeter.METER_MeterWaitWithMessage(objDictionary,60, "Wait For Ticket To Get Generated");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4040_FTFP0_CGPV1_DNAATTPAV_AV_CAV_PCV_VIPV_CVP_AATTPAV_PGT_VGTV_PIT_VITV_ES1(objDictionary,strViolationId);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=341)
 	public void P4041F_PEO_CMS() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "187010905");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMS: Create Manual Session                                  ");
  		Reporter.log("C: Clear Manual Session Violations                          ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strTicketAmountDue = "$30.00";
		//Dictionary Variables
  		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
		String strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO","True","Parker");
		try{Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.PEO_Login(objDictionary, androiddriver);
		String strNumberOfViolationImages = "3";
		objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		objDictionary.put("strSnoozeInterval", "2");
		clsCommonMobile.PEO_CreateManualSessionForDescriptionAndStatuteCodeValidation(objDictionary, androiddriver, strViolationDescription, strTicketAmountDue,"1");
		//Wait for Snooze Intervale to expire
		clsMeter.METER_MeterWaitWithMessage(objDictionary,150, "Wait For Chalked Snooze Time to Expire");
		try{Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "New Violation", "Refresh",1);
	  	String strViolationId = clsCommonMobile.StoreText(objDictionary, androiddriver, "New Violations", "Violation Number", 1, "strViolationNo");
		//Clear Violation
	  	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "More Options icon", 1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Violations", "Clear Violations", 1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Violations", "YES, CLEAR THEM ALL", 1);
		clsCommonMobile.PEO_ValidateViolationRemoved(objDictionary,androiddriver, strViolationId);
		androiddriver.quit();
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=341)
 	public void P4041F_PEO_CMS_IGPE_VVXD() throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID clsCommonMobile = new CommonANDROID();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMS: Create Manual Session                                  ");
  		
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strTicketAmountDue = "$30.00";
		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
		String strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Session
		objDictionary.put("strInstallApp", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO","True","Parker");
		try{Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.PEO_Login(objDictionary, androiddriver);
		String strNumberOfViolationImages = "3";
		objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		objDictionary.put("strSnoozeInterval", "2");
		clsCommonMobile.PEO_CreateManualSessionForDescriptionAndStatuteCodeValidation(objDictionary, androiddriver, strViolationDescription, strTicketAmountDue,"1");
		//Wait for Snooze Intervale to expire
		clsMeter.METER_MeterWaitWithMessage(objDictionary,150, "Wait For Chalked Snooze Time to Expire");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "New Violation", "Refresh",1);
	  	String strViolationId = clsCommonMobile.StoreText(objDictionary, androiddriver, "New Violations", "Violation Number", 1, "strViolationNo");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		objDictionary.put("strDeviceId", "No Meter");
  		objDictionary.put("strMeterGroup","No Meter");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "New");
  		driver.navigate().refresh();
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 8, "No Meter",9, "Initial Grace Period Exceeded", 11, "New", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "New");
  		//Claim Violation
  		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "Claim",1);
  		driver.navigate().refresh();
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Claimed");
  		//driver.quit();
  		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Violations", "Issue Ticket",1);
  		//Populate Violation Fields
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Issue ticket", "Populate Claim", "{T} License plate","Chalked");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Registration exp year", "2024");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Registration exp month", "02");
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "License plate state", "Alabama");
		clsCommonMobile.StoreTextField(objDictionary, androiddriver, "Issue ticket", "License plate state", 1, "strLicenseState");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strRegistrationType = clsHttpConnections.HTTPCONNECTION_ReturnRandomUserRegistrationType(objDictionary);
		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Registration Type", strRegistrationType);
		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		clsCommonMobile.PopulateScrollableListbox(objDictionary,androiddriver, "Issue ticket", "Vehicle Body Type", strVehicleBodyType);
		WebElement objTicketFrame = androiddriver.findElement(By.xpath(".//android.widget.ScrollView[1]"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe(androiddriver,objTicketFrame,"SWIPE_UP");
  		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Issue ticket", "Generate Ticket",1);
  		
  		//clsCommonMobile.ClickButton(objDictionary, androiddriver, "Ticket", "Ticket Issued", 1);
		
  		
  		//Validate Violation Status Generated
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  
  		
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, "Initial Grace Period Exceeded","Generating Ticket");
  		
  		
  		//clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
  		//ClickButton(objDictionary, driver, "Parking Session", "Approve/Reject Violation", 1, "Local");
  		
		
  		
  		
  		
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Vehicle Details", 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		//Click Image Link (Not Done)
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
		try {Thread.sleep(1000);} catch (Exception e) {}
		List<WebElement> uls = driver.findElements(By.xpath("//div[contains(@class,'jcarousel jcarousel-navigation')]//ul//li"));
    	int intActualNumberOfImages = uls.size();
    	if(intActualNumberOfImages == Integer.parseInt(strNumberOfViolationImages))
    	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
    	else
    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected 3 Actual "+intActualNumberOfImages,"Local");}
    	// VIAC: Validate Images Appear Correctly
		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).build().perform();
	    
	    
	    
	    
	    
	    try{Thread.sleep(1000);}catch (Exception e) {}
//  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Make",1, "Contains","AMC");
//  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Model",1, "Contains","");
//  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Color",1, "Contains","");
//  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Body Type",1, "Contains","TRUCK");
//  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Vehicle Year",1, "Contains","2017");
//  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Registration Type",1, "Contains","Commerical");
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"1");
	    driver.quit();
	    clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  

  	//Ticket Service
  	@Test(priority=342)
  	public void TS4042F_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_ATDD_VTPDF_ATDD_VTPDF_ES1_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("ATDD: Advance Ticket Due Date                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("ATDD: Advance Ticket Due Date                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		//String strLicensePlateNumber = "7716674";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		
//  		String strLicensePlateNumber = "645786G";
//  		String strLicensePlateState = "Washington";
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG"))
		{
	  		//Disable Require Admin Approval
	  		clsCommonWeb.SENTRYLINK_UpdateViolationsRequireAdminApproval(objDictionary, "UnChecked");
	  	 	//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
	  		//Update Meter Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketServiceAndLookupService(objDictionary,"Lob Mailing","S&P Global");
		}
  		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","2");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","3");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4042_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_ATDD_VTPDF_ATDD_VTPDF_ES1_VPSH(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=343)
  	public void TS4043F_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_DV_VNTPDF_EDV_VTPDF_ES1_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("DF: Dispute Violation                                       ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("EDV: End Disputed Violation                                 ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG"))
		{
	  		//Disable Require Admin Approval
	  		clsCommonWeb.SENTRYLINK_UpdateViolationsRequireAdminApproval(objDictionary, "UnChecked");
	  	 	//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
	  		//Update Meter Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketServiceAndLookupService(objDictionary,"Lob Mailing","S&P Global");
		}
  		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		//Dispute Violation
		clsCommonWeb.SENTRYLINK_DisputeViolationInSL(objDictionary, strViolationId);
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDate(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Disputed Violation Does not generate Ticket PDF
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "2", "2", "Row Does Not Exist", "");
		//End Dispute Validate Ticket PDF Generates
		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "End Dispute", 1,"Local");
		try {Thread.sleep(3000);}catch (Exception e) {}
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Violations", "End or Dismiss Dispute", "{T} Customer Only Comments|{T} Internal Only Comments","Dispute Not Valid|Ruled in Favor of MPS");
 		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "Dialog End Dispute", 1,"Local");
 		try {Thread.sleep(3000);}catch (Exception e) {}
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violation Details", 1, "1","2", "CellValue", "Notified");
 		driver.quit();
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","2");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4043_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_DV_VNTPDF_EDV_VTPDF_ES1_VPSH(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=344)
  	public void TS4044F_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_PVOH_VTPDF_DV_VNTPDF_EDV_VTPDF_ES1_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("PVOH:  Place Violation On Hold                              ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("DF: Dispute Violation                                       ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("EDV: End Disputed Violation                                 ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("SG"))
		{
	  		//Disable Require Admin Approval
	  		clsCommonWeb.SENTRYLINK_UpdateViolationsRequireAdminApproval(objDictionary, "UnChecked");
	  	 	//Enable Ticket Service Lob Mailing
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
	  		//Enable Look Up Service s&p
	  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
	  		//Update Meter Group Ticket Service
	  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketServiceAndLookupService(objDictionary,"Lob Mailing","S&P Global");
		}
  		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		//Dispute Violation
		clsCommonWeb.SENTRYLINK_PlaceViolationOnHoldInSL(objDictionary, strViolationId);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDate(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Disputed Violation Does not generate Ticket PDF
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "2", "2", "Row Does Not Exist", "");
		//End Dispute Validate Ticket PDF Generates
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "End Hold", 1);
		clsCommonWeb.ClickButton(objDictionary, driver,  "Violations", "End Violation Hold", 1,"Local");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violation Details", 1, "1","2", "CellValue", "Notified");
 		driver.quit();
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","2");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4044_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_PVOH_VTPDF_DV_VNTPDF_EDV_VTPDF_ES1_VPSH(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=345)
  	public void TS4045F_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_PV_VNTPDF_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("CGPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("PV:  Pay Violation                                          ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		//Pay Ticket
		clsCommonWeb.SENTRYLINK_PayViolation(objDictionary,strViolationId,strLicensePlateNumber,"True");
		//Run Curl
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date - PAID
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDatePaid(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Disputed Violation Does not generate Ticket PDF
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToPaidViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "2", "2", "Row Does Not Exist", "");
		driver.quit();
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4045_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_PV_VNTPDF_ES1_VPSH_VICAE_VIAC(objDictionary,strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=346)
  	public void TS4046F_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_VV_VNTPDF_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("CGPV: Create Grace Period Violation                         ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("VV: Void Violation                                          ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		//Void Ticket
		clsCommonWeb.SENTRYLINK_VoidViolationInSL(objDictionary, strViolationId);
		//Run Curl
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDate(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Disputed Violation Does not generate Ticket PDF
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "2", "2", "Row Does Not Exist", "");
		driver.quit();
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4046_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_VV_VNTPDF_ES1_VPSH_VICAE_VIAC(objDictionary,strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=347)
  	public void TS4047F_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_DV_DDV_VNTPDF_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("CGPV: Create Grace Period Violation                         ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("DV: Dispute Violation                                       ");
		Reporter.log("DDV: Dismiss Dispute Violation                              ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		//Dispute Violation
		clsCommonWeb.SENTRYLINK_DisputeViolationInSL(objDictionary, strViolationId);
		//Dismiss Disputed Violation
		clsCommonWeb.SENTRYLINK_DismissDisputedViolationInSL(objDictionary, strViolationId);
		//Run Curl
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDate(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Disputed Violation Does not generate Ticket PDF
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToNotifiedViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "2", "2", "Row Does Not Exist", "");
		driver.quit();
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4047_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_DV_DDV_VNTPDF_ES1_VPSH_VICAE_VIAC(objDictionary,strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=348)
  	public void TS4048F_TSLOB_FTFP0_CGPV_AV_VPEO_VTPDF1_VTPDF2_VTPDF3_VVAC_VNTPDF_PVAC_VVC_VNTPDF_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("CGPV: Create Grace Period Violation                         ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF1: Validate Ticket PDF                                 ");
		Reporter.log("VTPDF2: Validate Ticket PDF                                 ");
		Reporter.log("VTPDF3: Validate Ticket PDF                                 ");
		Reporter.log("VVAC: Validate Violation Awaiting Collections               ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("PVAC: Process Violation AwaitingCollections                 ");
		Reporter.log("VVC: Validate Violation Collections                         ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "616908P";
  		String strLicensePlateState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Add License Plate to the Parking Session
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, strLicensePlateState);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Lob Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","2");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		try {Thread.sleep(15000);}catch (Exception e) {}
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","3");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		try {Thread.sleep(5000);}catch (Exception e) {}
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF_Awaiting_Collections(objDictionary, strViolationId,"Test1.txt","3");
		//Awaiting Collections
		clsCommonWeb.SENTRYLINK_ValidateViolationAwaitingCollectionInSL(objDictionary, strViolationId);
		//Run Curl
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDateAwaitingCollections(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Awaiting Collections Violation Does not generate Ticket PDF
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToAwaitingCollectionsViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "4", "2", "Row Does Not Exist", "");
		driver.quit();
		//Navigate to
		clsCommonWeb.SENTRYLINK_ProcessViolationAwaitingCollectionsInSL(objDictionary,strViolationId);
		//Set Overdue To Now
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDateCollections(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Awaiting Collections Violation Does not generate Ticket PDF
		clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.SENTRYLINK_NavigateToCollectionsViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Notifications", 1);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations",  "Mail Printings", 1, "4", "2", "Row Does Not Exist", "");
		driver.quit();
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4048_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF1_VTPDF2_VTPDF3_VVAC_VNTPDF_PVAC_VVC_VNTPDF_ES1_VPSH_VICAE_VIAC(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=349)
  	public void P4049F_TSLOB_FTFP0_CGPV_AV_RVPEO_VNTPDF_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("CGPV: Create Grace Period Violation                         ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("RVPEO:  Reject Violation PEO                               ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "7543694";
  		String strLiceseState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Create Grace Period Violation
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLiceseState);
		//Lob Mailing PEO SL Officer Reject Violation.
		clsCommonWeb.SENTRYLINK_PEORejectViolationInSL(objDictionary, strViolationId);
		//Validate Violation Voided
		clsCommonWeb.SENTRYLINK_ValidateViolationVoidedInSL(objDictionary, strViolationId);
		//Set Overdue To Now
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDateDoesNotExist(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Missed Violation Does not generate Ticket PDF
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Notifications", 1, "Does Not Exist");
		driver.quit();
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4049_TSLOB_FTFP0_CGPV_AV_RVPEO_VNTPDF_ES1_VPSH_VICAE_VIAC(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=350)
  	public void P4050F_TSLOB_FTFP0_CGPV_ES1()throws Exception
	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSLOB: Ticket Service LOB                                   ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("CGPV: Create Grace Period Violation                         ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("RVPEO:  Reject Violation PEO                               ");
		Reporter.log("VNTPDF: Validate No Ticket PDF                              ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "7543694";
  		String strLiceseState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"Lob Mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"S&P Global");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"Lob Mailing");
		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Create Grace Period Violation
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		//Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLiceseState);
		//Lob Mailing PEO SL Officer Reject Violation.
		clsCommonWeb.SENTRYLINK_PEORejectViolationInSL(objDictionary, strViolationId);
		//Validate Violation Voided
		clsCommonWeb.SENTRYLINK_ValidateViolationVoidedInSL(objDictionary, strViolationId);
		//Set Overdue To Now
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Validate Violation Next Over Due Date
		clsCommonWeb.SENTRYLINK_ValidateViolationNextOverDueDateDoesNotExist(objDictionary, strViolationId);
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Missed Violation Does not generate Ticket PDF
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Notifications", 1, "Does Not Exist");
		driver.quit();
		//ShortSessionWaitExitSpot
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
		clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4049_TSLOB_FTFP0_CGPV_AV_RVPEO_VNTPDF_ES1_VPSH_VICAE_VIAC(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

  	
  	@Test(priority=361)
  	public void P4060_ShawonTest()
  	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "7716674";
  		String strLiceseState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"mps-salient-sim");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"mps-salient-sim");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"mps-salient-sim");
  		
  		System.out.println("MIH");
  	}
  	

  	@Test(priority=352)
  	public void P4052_TSMM_FTFP0_CGPV_AV_AVPEO_VTPDF_ATDD_VTPDF_ATDD_VTPDF_ES1_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		Reporter.log("***************TestCase Description*************************");
		Reporter.log("TSMM: Ticket Service Manual Mailing                         ");
		Reporter.log("FTFP0: Free Time First Payment 0                            ");
		Reporter.log("GPV: Create Grace Period Violation                          ");
		Reporter.log("AV:  Approve Violation                                      ");
		Reporter.log("AVPEO:  Approve Violation PEO                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("ATDD: Advance Ticket Due Date                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("ATDD: Advance Ticket Due Date                               ");
		Reporter.log("VTPDF: Validate Ticket PDF                                  ");
		Reporter.log("ES1: Exist Spot 1                                           ");
		Reporter.log("VPSH: Validate Parking Session History                      ");
  		Reporter.log("************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "3";
		String strViolationGracePeriod = "5";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strSetImageSendBeforeViolation = "45";
		String strParkingShortSessionSec = "15";
		String strUnlockValue = "On";objDictionary.put("strUnlockValue",strUnlockValue);
  		String strUnlockTime = "10";
  		String strUnlockMax = "1";
  		String strLicensePlateNumber = "7716674";
  		String strLiceseState = "Washington";
  		//Enable Ticket Service Lob Mailing
  		clsCommonWeb.SENTRYLINK_EnableAndDisableTicketServices(objDictionary,"manual mailing");
  		//Enable Look Up Service s&p
  		clsCommonWeb.SENTRYLINK_EnableAndDisableLookupServices(objDictionary,"manual mailing");
  		//Update Meter Group Ticket Service
  		clsCommonWeb.SENTRYLINK_UpdateMeterGroupTicketService(objDictionary,"manual mailing");
		//Get Vehicle Body Type prior to Login
 		String strVehicleBodyType = clsHttpConnections.HTTPCONNECTION_ReturnRandomVehicleBodyType(objDictionary);
 		//Populate Violation Fields
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  	 	objDictionary.put("strLicensePlateState",strLiceseState);
		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//De-enrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
  		//Check If Parking Enforcement Officer Exists
		clsCommonWeb.SENTRYLINK_CreatePEOUser(objDictionary);
		//Create Grace Period Violation
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
		//Store Parking Id
 		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
		try {Thread.sleep(25000);}catch (Exception e) {}
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//Approve Violation Ticket SL
		clsCommonWeb.SENTRYLINK_NavigateToViolationAndApproveViolation(objDictionary,strViolationId, strLicensePlateNumber,strLiceseState);
		//Manual Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOApproveViolationInSL(objDictionary, strViolationId);
		//Manual Mailing PEO SL Officer Approve Violation.
		clsCommonWeb.SENTRYLINK_PEOPopulateOwnerInformationInSL(objDictionary,strViolationId);
		//Wait Until Violation Updates to Notified
		try {Thread.sleep(25000);}catch (Exception e) {}
		//Validate Ticket Notification #1 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","1");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #2 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","2");
		clsHttpConnections.CURL_SetNextOverdueToNow(objDictionary, "admin");
		//Run OverdueViolationProcessor to Advance Ticket Due Date
		clsCommonWeb.SENTRYLINK_RunSidekiqJobOverdueViolationProcessor(objDictionary);
		//Validate Ticket Notification #3 PDF
		clsCommonWeb.SENTRYLINK_ValidateTicketPDF(objDictionary, strViolationId,"Test1.txt","3");
		//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
      	PEO_ParkingSessions clsPEO_ParkingSessions = new PEO_ParkingSessions();
      	clsPEO_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4042_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_ATDD_VTPDF_ATDD_VTPDF_ES1_VPSH(objDictionary, strViolationId);
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}



  	@Test(priority=302)
 	public void P4042_Test() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "184559229|178182836");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonANDROID clsCommonMobile = new CommonANDROID();
		SOAP clsSOAP = new SOAP();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.PEO_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*************************");
  		Reporter.log("CMV: Create Manual Violation                                ");
  		Reporter.log("IGPE: Initial Grace Period Exceeded                         ");
  		Reporter.log("VVXD: Validate Volation XML Data                            ");
  		Reporter.log("************************************************************");
  		//Function Variables
  		String strViolationDescription = "Initial Grace Period Exceeded";
  		String strTicketAmountDue = "$30.00";
		//Dictionary Variables
  		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		Reporter.log("strRemotePath:"+strRemotePath);
		String strAppiumPort = System.getProperty("strAppiumPort");
		Reporter.log("strAppiumPort:"+strAppiumPort);
  		//Add Function Variable To Dictionary
  		objDictionary.remove("strViolationDescription");objDictionary.put("strViolationDescription", strViolationDescription);
		//Update Notification Report Name
		//clsCommonWeb.SENTRYLINK_UpdateNotificationReportName(objDictionary,"CHNYTICKET2");
		//Create Parking Enforcement Officer
		//clsCommonWeb.SENTRYLINK_CreateUserParkingEnforcementOfficer(objDictionary);
		//Creates Manual Violation
		objDictionary.put("strInstallApp", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "PEO","True","Parker");
		try{Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.PEO_Login(objDictionary, androiddriver);
		String strNumberOfViolationImages = "8";
		objDictionary.put("strNumberOfViolationImages",strNumberOfViolationImages);
		clsCommonMobile.PEO_CreateManualViolationForDescriptionAndStatuteCodeValidation(objDictionary, androiddriver, strViolationDescription, strTicketAmountDue,"1");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Ticket", "LEAVE", 1);
		androiddriver.quit();
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		driver.navigate().refresh();
  		 try{Thread.sleep(2000);}catch (Exception e) {}
  		String strDeviceId = objDictionary.get("strDeviceId");
  		clsCommonWeb.StoreTableRowNumberBaseOnThreeColumnValue(objDictionary, driver, "Violations", "Violations", 1, 8, strDeviceId,9, "Initial Grace Period Exceeded", 11, "Notified", "strViolationRowNumber");
  		clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "~strViolationRowNumber~", "2");
  		clsCommonWeb.SENTRYLINK_ValidateViolationStatus(objDictionary, driver, strViolationDescription, "Notified");
  		String strViolationId = objDictionary.get("strViolationId");
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Vehicle Details", 1);
  		try{Thread.sleep(1000);}catch (Exception e) {}
  		//Click Image Link (Not Done)
		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
		try {Thread.sleep(1000);} catch (Exception e) {}
		List<WebElement> uls = driver.findElements(By.xpath("//div[contains(@class,'jcarousel jcarousel-navigation')]//ul//li"));
    	int intActualNumberOfImages = uls.size();
    	if(intActualNumberOfImages == Integer.parseInt(strNumberOfViolationImages))
    	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
    	else
    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected 3 Actual "+intActualNumberOfImages,"Local");}
    	// VIAC: Validate Images Appear Correctly
		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).build().perform();
	    try{Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Plate",1, "Contains","ARGOFY");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Make",1, "Contains","AMC");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Model",1, "Contains","NA");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Color",1, "Contains","NA");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Body Type",1, "Contains","ATV");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Vehicle Year",1, "Contains","2017");
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Registration Type",1, "Contains","Passenger");//Sometime is Commerical
		String strViolationXML = "";
		//VVXD: Validate Violation XML Data
		try{strViolationXML = clsSOAP.GetViolationXML(objDictionary);}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"Error Get Violation XML");}
	    clsSOAP.ValidateVolationXMLData(objDictionary,strViolationXML,"1");
	    driver.quit();
	    clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

}

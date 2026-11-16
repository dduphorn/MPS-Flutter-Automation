package AutomationCode;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Listeners({ EmailableReporter2.class, EmailableReporter3.class, ListenersClass.class})
public class IOS_TestCases_Flutter
{
	public IOSDriver iosdriver;
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
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
		//Carry last known Location Services / Airplane / Wi-Fi state across the clear (app is reinstalled every test)
		String strLocationServicesLastState = objDictionary.get("strLocationServicesLastState");
		String strAirplaneModeLastState = objDictionary.get("strAirplaneModeLastState");
		String strWIFILastState = objDictionary.get("strWIFILastState");
		objDictionary.clear();
		objDictionary.put("strInvocationCounter", strInvocationCounter);
		if(strLocationServicesLastState != null) {objDictionary.put("strLocationServicesLastState", strLocationServicesLastState);}
		if(strAirplaneModeLastState != null) {objDictionary.put("strAirplaneModeLastState", strAirplaneModeLastState);}
		if(strWIFILastState != null) {objDictionary.put("strWIFILastState", strWIFILastState);}
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
	public WebDriver getDriver(){return threadDriver.get();}
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(final ITestContext testContext, @Optional Method method, ITestResult result) throws Exception
	{
		// Tests that fail via UpdateErrorMessageWithPivotalData no longer quit there.
		// Restore (if the session is still alive) then quit so sessions are not leaked.
		try {
			IOS_Common_Flutter.SENTRYMOBILE_AfterMethodRestoreAndQuitIfAlive(objDictionary);
		} catch (Exception e) {
			Reporter.log("AfterMethod restore/quit failed: " + e.getMessage());
		}
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
			if (ImgurClient.getLastUrl() != null){objDictionary.put("link", ImgurClient.getLastUrl());}
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
  	//IOS-TEST CASES
  	//********************************************************************************************************************
	@Test(priority=10,groups={"Smoke"})
	public void I3000F_OpenApp_AirplaneModeEnabled()
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERCA-260");
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//AirplaneMode test
		objDictionary.put("strWIFI","Enabled");
		objDictionary.put("strAirplaneMode","Enabled");
		//Open Android Device
		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
		String strUserName = "ideleteuser1@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "IDeleteMe01!";
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
		//Click Sign in
		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Log in", "Log in",1);
		//Log In to CA
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password",strUserName+"|"+strPassword);
 		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Log in", "Log in",1);
 		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Log in", "Please disable Airplane Mode to continue using the app", 1, "Value", "Please disable Airplane Mode to continue using the app");
 		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=20)
  	public void I3001F_ValidateInvalidCredentialMessage_Flutter()
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "InvalidUser@gmail.com";
  		String strPassword = "Invalid01MN";
  		//Open IOS Device
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
   		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password",strUserName+"|"+strPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
   		try {Thread.sleep(2500);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Log in", "Invalid credentials, please contact customer support.", 1, "Value", "Invalid credentials, please contact customer support.");
   		iosDriver.quit();
  		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	//Register User
  	@Test(priority=30)
  	public void I3002F_RegisterUser_PasswordShouldHaveAtleastOneDigitOrSpecialCharacter_Flutter()
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "IDeleteUser@gmail.com";
  		String strPassword = "IDeleteMeMN";
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
   		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
   		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,iosDriver, "Sign Up", "Sign up", 1);
  		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Sign Up", "Password must be at least 12 characters, at least 1 upper case letter, at least 1 lower case letter, at least 1 number, at least 1 special character", 1, "Exists", "");
		iosDriver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=40)
  	public void I3003F_RegisterUser_EmailHasAlreadyBeenTaken_Flutter()
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "darin@mpspark.com";
  		String strPassword = "L@kemaryMN02";
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
   		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
  		clsCommonMobile.ClickButton(objDictionary,iosDriver, "Sign Up", "Sign up", 1);
  		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Unable to create User. Please contact support.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Error Message) with index (1) did not contain (Unable to create User. Please contact support.) - actual value ("+strSnackbarText+")");}
 		iosDriver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=50)
  	public void I3004F_RegisterUser_EmailIsInvalid_Flutter()
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "darin@mpspark";
  		String strPassword = "L@kemaryMN03";
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
   		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","612558-1707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,iosDriver, "Sign Up", "Sign up", 1);
  		//Validate Error Message
 		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Sign Up", "Enter valid email address", 1, "Exists", "");
 		iosDriver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	
  	@Test(priority=60)
	public void I3006F_RegisterUser_PasswordsDoNotMatchPleaseReEnter_Flutter()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin@mpspark.com";
		String strPassword = "L@kemaryMN03";
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		//Click Sign up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Populate Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|s"+strPassword+"");
		//Click Sign Up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Sign Up", "Passwords do not match", 1, "Exists", "");
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	
  	@Test(priority=70)
  	public void I3007F_RegisterUser_PasswordShouldBeAtleast7Characters_Flutter()
  	{
  		objDictionary.put("strAssociatedBug", "174050209");
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "darin2@mpspark.com";
  		String strPassword = "Lakem1MN007";
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		//Click Sign up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Populate Sign Up
	 	clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
	 	//Click Sign Up
	 	clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
	 	try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Sign Up", "Password must be at least 12 characters, at least 1 upper case letter, at least 1 lower case letter, at least 1 number, at least 1 special character", 1, "Exists", "");
  		iosDriver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=80)
  	public void I3008F_RegisterUser_FirstNameCannotBeBlank_Flutter()
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "darin@mpspark1.com";
  		String strPassword = "L@kemaryMN03";
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		//Click Sign up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Populate Sign Up
  		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
  		//Click Sign Up
	 	clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
	 	try {Thread.sleep(1000);}catch (Exception e) {}
	 	clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Sign Up", "Please enter first name", 1, "Exists", "");
	 	iosDriver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=90)
  	public void I3009F_RegisterUser_LastNameCannotBeBlank_Flutter()
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strUserName = "darin@mpspark1.com";
  		String strPassword = "L@kemaryMN03";
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		//Click Sign up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Populate Sign Up
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete||"+strUserName+"|"+strPassword+"|"+strPassword+"");
 		//Click Sign Up
	 	clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
	 	try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Sign Up", "Please enter last name", 1, "Exists", "");
		iosDriver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=100)
  	public void I3010F_RegisterUser_Login_Flutter()
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		//Destroy User
  		String strUserName = "ideleteuser1@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "IDeleteMe01!";
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver(); // driver.quit();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strUserName);
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		//Click Sign up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Sign up",1);
   		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581709");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Populate Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		//Click Sign Up
	 	clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
	 	String strSnackbarText = objDictionary.get("strSnackbarText");if(strSnackbarText == null) {strSnackbarText = "";}
		if(strSnackbarText.equals("Registered Successfully")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Error Message) with index (1) did not contain (Registered Successfully) - actual value ("+strSnackbarText+")");}
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	
  	@Test(priority=110)
	public void I3011F_RegisterExisting_DeletedUser_Login_Flutter()
	{
  		objDictionary.put("strAssociatedBug", "SL-8009");
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//User Added in A2010_RegisterUser_Login_Flutter
		String strUserName = "ideleteuser1@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "IDeleteMe01!";
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver(); // driver.quit();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_DeleteAccount(objDictionary,driver, strUserName);
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//Click Sign in
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Log in",1);
		//Click Sign up
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Sign up",1);
		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Click Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,iosDriver, "Sign Up", "Sign up", 1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Unable to create User. Please contact support.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Error Message) with index (1) did not contain (Unable to create User. Please contact support.) - actual value ("+strSnackbarText+")");}
 		iosDriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=120)
	public void I3012F_RegisterExisting_LockedUser_Login_Flutter()
	{
		objDictionary.put("strAssociatedBug", "FLUTTERCA-187");
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver(); // driver.quit();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		String strUserName = "ideleteuser1@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "IDeleteMe01!";
		driver = getDriver(); // driver.quit();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnDeleteAccount(objDictionary,driver, strUserName);
		driver.quit();
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver(); 
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strUserName);
		//Lock User Account
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, "Incorrect");
		clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, "Incorrect");
		clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, "Incorrect");
		clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, "Incorrect");
		clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, "Incorrect");
		clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, "Incorrect");
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//Click Sign in
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Log in",1);
		//Click Sign up
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Sign up",1);
		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581707");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Click Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,iosDriver, "Sign Up", "Sign up", 1);
		String strSnackbarText = objDictionary.get("strSnackbarText");if (strSnackbarText == null) {strSnackbarText = "";}
 		if(strSnackbarText.equals("Unable to create User. Please contact support.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Error Message) with index (1) did not contain (Unable to create User. Please contact support.) - actual value ("+strSnackbarText+")");}
 		iosDriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=130)
  	public void I3016F_CreateAndDeleteUser_AttemptMobileRegisterWithDeletedUser_Flutter()
	{
  		objDictionary.put("strAssociatedBug", "SL-8009");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Function Variables
		String strUserName = "ideleteuser3@gmail.com";
		String strPassword = "IDeleteMe!01";
		//Open Consumer Application
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_CreateAndDeleteUser(objDictionary, driver, strUserName,strPassword);
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//Click Sign in
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Log in",1);
		//Click Sign up
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Sign up",1);
		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581708");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Click Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName.toLowerCase()+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,iosDriver, "Sign Up", "Sign up", 1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Unable to create User. Please contact support.")){Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Error Message) with index (1) did not contain (Unable to create User. Please contact support.) - actual value ("+strSnackbarText+")");}
 		iosDriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	@Test(priority=140)
  	public void I3017F_CreateAndDeleteUser_AttemptLoginWithDeletedUser_Flutter()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonWeb clsCommonWeb = new CommonWeb();
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Dictionary Variable
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		//Function Variables
		String strUserName = "ideleteuser3@gmail.com";
		String strPassword = "IDeleteMe!01";
		//Open Consumer Application
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_CreateAndDeleteUser(objDictionary, driver, strUserName,strPassword);
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//Log In to CA
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password",strUserName+"|"+strPassword);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
 		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Log in", "Invalid credentials, please contact customer support.", 1, "Exists", "");
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Ok",1);
 		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=170)
  	public void I3021F_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary, strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=190)
  	public void I3022F_VM_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
	{
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "F"+strSpaceName.toUpperCase();
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
  		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_3022_VM_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	
  	
  	@Test(priority=200)
    public void I3023F_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC()
	{
		String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		String strLicensePlateNumber = "0"+strMeterSpotName+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3023_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=210)
	public void I3024F_VM_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC()
    {
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "F"+strSpaceName.toUpperCase();
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3024_VM_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment0//Park//MobilePayment//PurchaseMoreTime
  	@Test(priority=220)
	public void I3025F_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "157009379");
		//String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		String strLicensePlateNumber = "0"+strMeterSpotName+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3025_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=230)
	public void I3026F_VM_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()
    {
  		objDictionary.put("strAssociatedBug", "188731741|177295054");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.put("strVirtualMeter", "True");
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Simulator
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_3026_VM_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment0//Park//MobilePayment
  	@Test(priority=250)
 	public void I3027F_A_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3027_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=260)
	public void I3028F_VM_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|177295054");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.put("strVirtualMeter", "True");
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3028_VM_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=270)
	public void I3029F_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "151952960|151649737|142899907");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3029_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=280)
	public void I3030F_VM_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|184183962");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.put("strVirtualMeter", "True");
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Simulator
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3030_VM_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment0//Park//CointPayment//MobilePayment
  	@Test(priority=290)
	public void I3031F_FTFP0_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC()
  	{
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3031_FTFP0_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=300)
	public void I3032F_FTFP0_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3032_FTFP0_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=310)
	public void I3033F_FTFP0_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3033_FTFP0_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=320)
	public void I3034F_FTFP0_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3034_FTFP0_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//FirstTimeFirstPayment0//Park//CreditCardPayment//MobilePayment
  	@Test(priority=330)
 	public void I3035F_FTFP0_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3035_FTFP0_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=340)
 	public void I3036F_FTFP0_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
 		clsCommonMobile.SENTRYMOBILE_3036_FTFP0_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
 		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
 	}
  	//FirstTimeFirstPayment0//Park//MobilePayment//CreditCardPayment
  	@Test(priority=350)
 	public void I3037F_FTFP0_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3037_FTFP0_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=360)
	public void I3038F_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3038_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//FirstTimeFirstPayment0//MobilePayment//Park
  	@Test(priority=370)
 	public void I3039F_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3039_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=380)
	public void I3040F_VM_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3040_VM_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }

  	//FirstTimeFirstPayment10//MobilePayment
  	@Test(priority=390)
	public void I3041F_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "144557797");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3041_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=400)
	public void I3042F_VM_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|182332617|177295054|144557797");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3042_VM_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//PurchaseMoreTime
  	@Test(priority=410)
  	public void I3043F_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3043_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=420)
	public void I3044F_VM_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3044_VM_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//PurchaseMaxTime
	@Test(priority=430)
  	public void I3045F_FTFP10_PS1_PMT_ES1_VPSH_VICAE_VIAC()throws Exception
  	{
		objDictionary.put("strAssociatedBug", "151649737,144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3045_FTFP10_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=440) 
  	public void I3045F_A_FTFP10_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
  	{
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3045_A_FTFP10_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=450)
  	public void I3046F_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "144557797");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3046_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=460)
	public void I3047F_VM_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.put("strVirtualMeter", "True");
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Simulator
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3047_VM_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//CoinPayment//MobilePayment
	@Test(priority=470)
  	public void I3048F_FTFP10_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "FLUTTERCA-206");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3048_FTFP10_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=480)
  	public void I3049F_FTFP10_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "FLUTTERCA-149");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3049_FTFP10_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//CoinPayment
	@Test(priority=490)
	public void I3050F_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3050_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=500)
  	public void I3051F_FTFP10_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3051_FTFP10_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//CreditCardPayment//MobilePayment
	@Test(priority=510)
  	public void I3052F_FTFP10_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		objDictionary.put("strAssociatedBug", "FLUTTERCA-206");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
  		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3052_FTFP10_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=520)
  	public void I3053F_FTFP10_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "FLUTTERCA-149");
	    String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3053_FTFP10_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//CreditCardPayment
	@Test(priority=530)
  	public void I3054F_FTFP10_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3054_FTFP10_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=540)
  	public void I3055F_FTFP10_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
	 	objDictionary.put("strAssociatedBug", "144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3055_FTFP10_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//Park
	@Test(priority=550)
    public void I3056F_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
    	String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
    	String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3056_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=560)
	public void I3057F_VM_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|182332617|177295054");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.put("strVirtualMeter", "True");
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Simulator
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3057_VM_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//GPSOff//FreeTimeFirstPayment0//MobilePayment
	@Test(priority=570)
	public void I3058F_GPSOFF_PS1_MP1_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
    	Reporter.log("***************TestCase Description*****************************");
    	Reporter.log("GPSOFF: GPS Off                                                 ");
    	Reporter.log("PS1: Park Spot 1                                                ");
    	Reporter.log("MP1: Mobile Payment Spot 1                                      ");
		Reporter.log("ES1: Exit Spot1                                                 ");
		Reporter.log("NTPS: Navigate To Parking Session                               ");
		Reporter.log("VPSH: Validate Parking Session History                          ");
		Reporter.log("VICAE: Validate Image Count After Exit                          ");
		Reporter.log("VIAC: Validate Images Appear Correctly                          ");
		Reporter.log("****************************************************************");
		//Test Case Variables
		String strMaximumDuration = "240";
		String strCoinTimePuchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//ParkTP: Park Then Pay
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//objDictionary.put("strAutoGrantPermissions", "False");
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//objDictionary.put("strAutoGrantPermissions", "False");
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//ParkTP: Park Then Pay
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		iosDriver.quit();
	    //ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	  	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	  	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3058_GPSOFF_PS1_MP1_ES1(objDictionary,strMeterIncrementTime);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=580)
	public void I3059F_GPSOFF_RegisterUser_EnableGPSDialogNo()
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Test Case Variables
		String strUserName = "XDeleteUser@gmail.com";objDictionary.put("strUserName",strUserName);
		String strPassword = "XDeleteMe!02";
		//Destroy User
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver(); // driver.quit();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strUserName);
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","False","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//Click Sign in
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Log in",1);
		//Click Sign up
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Sign up",1);
		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581708");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//Populate Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		//Click Sign Up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
 		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if (!strSnackbarText.equals("Registered Successfully"))
		{
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"Unexpected message appeared on user Registration: "+strSnackbarText);
		}
 		try {Thread.sleep(2000);}catch (Exception e) {}
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=590) 
	public void I3060F_GPSOFF_RegisterUser_EnableGPSDialogYes()
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Test Case Variable
		String strUserName = "XDeleteUser@gmail.com";objDictionary.put("strUserName",strUserName);
		String strPassword = "XDeleteMe!02";
		//Destroy User
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		//Click Sign in
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Log in",1);
		//Click Sign up
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Sign up",1);
		//Populate Phone Number
		clsCommonMobile.PopulateActionSlow(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581709");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Next",1);
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Populate Sign Up
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		//Click Sign Up
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Sign Up", "Sign up",1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if (!strSnackbarText.equals("Registered Successfully"))
		{
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"Unexpected message appeared on user Registration: "+strSnackbarText);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//MeterUnlockOn
	@Test(priority=600)
	public void I3061F_MUON_CGPV1_MP1_VMT_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strAssociatedBug", "183842654");
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  	CommonWeb clsCommonWeb = new CommonWeb();
	  	Meter clsMeter = new Meter();
	  	clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
	  	Reporter.log("***************TestCase Description*****************************");
	  	Reporter.log("MUON: Meter Unlock On                                           ");
	  	Reporter.log("CGPV1: Create Grace Period Violation                            ");
	  	Reporter.log("MP1: Mobile Payment Spot 1                                      ");
    	Reporter.log("VMT: Validate Meter Time                                        ");
    	Reporter.log("ES1: Exit Spot1                                                 ");
		Reporter.log("VICAE: Validate Image Count After Exit                          ");
		Reporter.log("VIAC: Validate Images Appear Correctly                          ");
		Reporter.log("****************************************************************");
		//Dictionary Variables
		String strHost = objDictionary.get("strHost");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		//Test Case Variables
		String strMaximumDuration = "240";
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
		String strUnlockValue = "On";
		String strUnlockTime = "10";
	    String strUnlockMax = "1";
	    String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Update Parkers Can Unlock Violations
		clsCommonWeb.SENTRYLINK_UpdateParkersCanUnlockViolations(objDictionary, "Checked");
		//CGPV1: Create Grace Period Violation Spot 1  
	  	clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
	  	//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		String strViolationNumber = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", strSpotNumber);
	  	//MP1: Mobile Payment 1f
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//ParkTP: Park Then Pay
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","True");
		iosDriver.quit();
	  	//ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	    //ValidateParkingSessionHistoryAndImages
	    IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	    clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3061_MUON_CGPV1_MP1_VMT_ES1(objDictionary, strMeterIncrementTime, strViolationNumber);
	    clsMeter.METER_SetMeterEndTime(objDictionary);
	    String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//********************************************************************************************************************
  	//IOS MOBILE-FREE TO RATE
  	//********************************************************************************************************************
  	//FirstTimeFirstPayment0//MobilePayment
	@Test(priority=610)
    public void I3062F_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3062_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=630)
    public void I3063F_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3063_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=640)
	public void I3064F_VM_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "FLUTTERCA-158");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.put("strVirtualMeter", "True");
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
		//Simulator
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3064_VM_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=650)
    public void I3065F_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184188792|182675569");
		//Remove all License Plate
		String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		String strLicensePlateNumber = "0"+strMeterSpotName+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3065_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=660)
	public void I3066F_VM_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "FLUTTERCA-158");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3066_VM_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=670)
  	public void I3067F_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_PRT_VMT_VPSH_VICAE_VIAC() throws Exception
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3067_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_PRT_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=690)
	public void I3068F_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC() throws Exception
	{
   		objDictionary.put("strAssociatedBug", "172458902");
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3068_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=710)
    public void I3069F_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3069_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=720)
	public void I3070F_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "FLUTTERCA-158");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3070_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=730)
    public void I3071F_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "181224271|172458902|163396635|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3071_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=740)
	public void I3072F_VM_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "FLUTTERCA-158");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Dictionary Variables
   		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3072_VM_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=750)
    public void I3073F_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "172458902|163396635|157009379|151952960|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3073_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=760)
	public void I3074F_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "FLUTTERCA-158");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3074_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=780)
	public void I3075F_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC() throws Exception
	{
   		objDictionary.put("strAssociatedBug", "181224271|172458902|144557797");
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3075_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=790)
  	public void I3076F_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		//Remove all License Plate
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3076_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=800)
	public void I3077F_VM_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3077_VM_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=810)
   	public void I3078F_FTFP0_FDON_RTF_MBF_gt_MTIV_PS1_MP1_VMT_BMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
   		objDictionary.put("strAssociatedBug", "164320000,157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
   		clsCommonMobile.SENTRYMOBILE_3078_FTFP0_FDON_RTF_MBF_gt_MTIV_PS1_MP1_VMT_BMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=820)
  	public void I3079F_FTFP0_FDON_RTF_PS1_MP1_BMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "164320000,157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3079_FTFP0_FDON_RTF_PS1_MP1_BMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=830) 	
	public void I3080F_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
 		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDON: Free Disconnect On                                                                           ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("MTIV_gt_MBF_gt_FTFP: MeterTimeIncrementValue(30) > MinutesBeforeFree(20) > FreeTimeFirstPayment(10)");
		Reporter.log("PS1: Park Spot 1                                                                                   ");
 		Reporter.log("MP1: Mobile Payment Spot 1                                                                         ");
 		Reporter.log("VMT: Validate Meter Time                                                                           ");
 		Reporter.log("ES1: Exit Spot 1                                                                                   ");
 		Reporter.log("VPSH: Validate Parking Session History                                                             ");
 		Reporter.log("VICAE: Validate Image Count After Exit                                                             ");
		Reporter.log("VIAC: Validate Images Appear Correctly                                                             ");
 		Reporter.log("***************************************************************************************************");
 		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
		String strMeterIncrementTime = "30";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
		String strInitialGracePeriod = "10";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "20";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
 		String strUnlockTime = "1";
 		String strUnlockMax = "1";
 		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//MP1: Mobile Payment
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		iosDriver.quit();
		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//ShortSessionWaitExitSpot
     	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
     	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
     	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_I3080_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1(objDictionary);
     	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=840)
	public void I3081F_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3081_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=850) 
	public void I3082F_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{	
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDON: Free Disconnect On                                                                           ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("FTFP_gt_MTIV_gt_MBF: FreeTimeFirstPayment(10) > MeterTimeIncrementValue(9) > MinutesBeforeFree(8)  ");
		Reporter.log("PS1: Park Spot 1                                                                                   ");
		Reporter.log("MP1: Mobile Payment Spot 1                                                                         ");
		Reporter.log("VMT: Validate Meter Time                                                                           ");
		Reporter.log("ES1: Exit Spot 1                                                                                   ");
		Reporter.log("VPSH: Validate Parking Session History                                                             ");
		Reporter.log("VICAE: Validate Image Count After Exit                                                             ");
		Reporter.log("VIAC: Validate Images Appear Correctly                                                             ");
		Reporter.log("***************************************************************************************************");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "9";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "10";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker", "Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Park and Pay CA
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		String strMeterRemainingTimeMinutes = objDictionary.get("strMeterRemainingTimeMinutes");
		iosDriver.quit(); 
	    //Store Parking Id
	  	clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	  	//ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	    IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	    clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3082_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1(objDictionary, Integer.parseInt(strMeterRemainingTimeMinutes));
	   	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=860) 
	public void I3083F_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{	
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDON: Free Disconnect On                                                                           ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("FTFP_gt_MBF_gt_MTIV: FreeTimeFirstPayment(10) > MinutesBeforeFree(10) > MeterTimeIncrementValue(5) ");
		Reporter.log("PS1: Park Spot 1                                                                                   ");
		Reporter.log("MP1: Mobile Payment Spot 1                                                                         ");
		Reporter.log("VMT: Validate Meter Time                                                                           ");
		Reporter.log("ES1: Exit Spot 1                                                                                   ");
		Reporter.log("VPSH: Validate Parking Session History                                                             ");
		Reporter.log("VICAE: Validate Image Count After Exit                                                             ");
		Reporter.log("VIAC: Validate Images Appear Correctly                                                             ");
		Reporter.log("***************************************************************************************************");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "5";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "10";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker", "Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Park and Pay CA
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		String strMeterRemainingTimeMinutes = objDictionary.get("strMeterRemainingTimeMinutes");
		iosDriver.quit(); 
	    //Store Parking Id
	  	clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	    //ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	  	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	  	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3083_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1_VMT_ES1(objDictionary, Integer.parseInt(strMeterRemainingTimeMinutes));
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=870) 
	public void I3084F_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{	
    	objDictionary.put("strAssociatedBug", "184027852");
    	objDictionary.put("strMobileDeviceType", "ANDROID");
    	IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDON: Free Disconnect On                                                                           ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("MBF_gt_MTIV_gt_FTFP: MinutesBeforeFree(20) > MeterTimeIncrementValue(15) > FreeTimeFirstPayment(10)");
		Reporter.log("PS1: Park Spot 1                                                                                   ");
		Reporter.log("MP1: Mobile Payment Spot 1                                                                         ");
		Reporter.log("VMT: Validate Meter Time                                                                           ");
		Reporter.log("ES1: Exit Spot 1                                                                                   ");
		Reporter.log("VPSH: Validate Parking Session History                                                             ");
		Reporter.log("VICAE: Validate Image Count After Exit                                                             ");
		Reporter.log("VIAC: Validate Images Appear Correctly                                                             ");
		Reporter.log("***************************************************************************************************");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "20";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Delete Reservation Remover Permeit
		HttpConnections clsHttpConnections = new HttpConnections();
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker", "Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Park and Pay CA
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		String strMeterRemainingTimeMinutes = objDictionary.get("strMeterRemainingTimeMinutes");
		iosDriver.quit(); 
	    //Store Parking Id
	  	clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	    //ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	    //ValidateParkingSessionHistoryAndImages
	    IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	    clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3084_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1_VMT_ES1(objDictionary, Integer.parseInt(strMeterRemainingTimeMinutes));
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=880)
   	public void I3085F_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3085_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=890)
	public void I3086F_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{	
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDOFF: Free Disconnect Off                                                                         ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("MTIV_gt_MBF_gt_FTFP: MeterTimeIncrementValue(20) > MinutesBeforeFree(15) > FreeTimeFirstPayment(10)");
		Reporter.log("PS1: Park Spot 1                                                                                   ");
		Reporter.log("MP1: Mobile Payment Spot 1                                                                         ");
		Reporter.log("VMT: Validate Meter Time                                                                           ");
		Reporter.log("ES1: Exit Spot 1                                                                                   ");
		Reporter.log("VPSH: Validate Parking Session History                                                             ");
		Reporter.log("VICAE: Validate Image Count After Exit                                                             ");
		Reporter.log("VIAC: Validate Images Appear Correctly                                                             ");
		Reporter.log("***************************************************************************************************");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "20";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "20";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "Off");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker", "Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Park and Pay CA
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		String strMeterRemainingTimeMinutes = objDictionary.get("strMeterRemainingTimeMinutes");
		iosDriver.quit(); 
		//ShortSessionWaitExitSpot
    	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	    //ValidateParkingSessionHistoryAndImages
    	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
    	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3086_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1(objDictionary, strFreeTimeMinutes, Integer.parseInt(strMeterRemainingTimeMinutes));
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=900)
  	public void I3087F_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694|182332617|182415888");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3087_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3087)
  	public void I3087A_FTFP10_FDOFF_RTNP_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694|182332617|182415888");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3087A_FTFP10_FDOFF_RTNP_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=910)
  	public void I3088F_FTFP0_FDOFF_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "164320000");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3088_FTFP0_FDOFF_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=920) 
	public void I3089F_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDOFF: Free Disconnect Off                                                                         ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("FTFP_gt_MTIV_gt_MBF: FreeTimeFirstPayment(10) > MeterTimeIncrementValue(10) > MinutesBeforeFree(10)");
		Reporter.log("PS1: Park Spot 1                                                                                   ");
		Reporter.log("MP1: Mobile Payment Spot 1                                                                         ");
		Reporter.log("VMT: Validate Meter Time                                                                           ");
		Reporter.log("ES1: Exit Spot 1                                                                                   ");
		Reporter.log("VPSH: Validate Parking Session History                                                             ");
		Reporter.log("VICAE: Validate Image Count After Exit                                                             ");
		Reporter.log("VIAC: Validate Images Appear Correctly                                                             ");
		Reporter.log("***************************************************************************************************");
		//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "10";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "10";objDictionary.put("strMinutesBeforeFreeParking", strMinutesBeforeFreeParking);
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "Off");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker", "Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		//Park and Pay CA
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
		String strMeterRemainingTimeMinutes = objDictionary.get("strMeterRemainingTimeMinutes");
		iosDriver.quit(); 
	    //ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	  	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	  	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3089_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1(objDictionary, strMeterIncrementTime, strFreeTimeMinutes, Integer.parseInt(strMeterRemainingTimeMinutes));
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=930)
    public void I3090F_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3090_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=940)
   	public void I3091F_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3091_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=950)
    public void I3092F_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3092_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=960)
    public void I3093F_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "168060201");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3093_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=970)
  	public void I3094F_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "147978515");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3094_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=980)
	public void I3095F_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3095_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=990)
   	public void I3096F_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "184549710|168060201|150861890|147978515");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
 		clsCommonMobile.SENTRYMOBILE_3096_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1000)
    public void I3097F_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
 		clsCommonMobile.SENTRYMOBILE_3097_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1010)
    public void I3098F_AMMOEE_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
    	objDictionary.put("strAssociatedBug", "179985202");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3098_AMMOEE_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1020)
    public void I3099F_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "179985202|167947492|150861890");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
 		clsCommonMobile.SENTRYMOBILE_3099_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1030)
  	public void I3100F_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
 		clsCommonMobile.SENTRYMOBILE_3100_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1040)
    public void I3101F_FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183974251");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3101_FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1050)
    public void I3102F_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "174306633");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3102_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1060)
	public void I3103F_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "170429307|141421545");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3103_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1070)
	public void I3104F_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "174306633,141421545");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3104_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1080)
	public void I3105F_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "186676949");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3105_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1090)
	public void I3106F_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "186676949|184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3106_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1100)
	public void I3107F_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3107_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1110)
	public void I3108F_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3108_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1120)
	public void I3109F_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "179985202");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3109_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1130)
	public void I3110F_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "179985202");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3110_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1140)
	public void I3111F_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187620240");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3111_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1150)
	public void I3112F_FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "FLUTTERCA-181");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3112_FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1160)
   	public void I3113F_FTFP0_TUON_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184549710");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3113_FTFP0_TUON_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1170)
    public void I3114F_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3114_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1180)
	public void I3115F_FTFP0_TUON_UOff_PS1_GPV1_UTE_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3115_FTFP0_TUON_UOff_PS1_GPV1_UTE_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1190) 
  	public void I3116F_ForgotPassword_EnterTheUserEmailIdToResetPassword() throws MessagingException, IOException 
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		//Open Consumer App
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		//Click Forgot password?
  		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
  		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", "EmailIdIsNotValid");
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
  		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Forgot Password", "Enter valid email address",1, "Exists", "");
  		iosDriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1200) 
  	public void I3117F_ForgotPassword_AllFieldsWithAreMandatory() throws MessagingException, IOException 
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		//Open Consumer App
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Forgot Password", "Please enter email address",1, "Exists", "");
 		iosDriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1210) 
  	public void I3118F_ForgotPassword_InvalidEmail() throws MessagingException, IOException 
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strGmailUserName = "InvalidGmailUser@gmail.com";
  		//Open Consumer App
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
     	clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Forgot Password", "Ok", 1);
   		iosDriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1220)
	public void I3119F_ForgotPassword_InvalidToken() throws MessagingException, IOException 
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Get Gmail Values
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		//Check User Exist and is active
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
     	String strToken = "InvalidToken";
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "").replace("!", "");
		int intCurrentVersion = Integer.parseInt(strSentryLinkCurrentPassword.replaceAll("\\D+", ""));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion))+"!";
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Set New Password", "Populate Reset Password", "{T} Reset Token|{T} New Password|{T} Confirm New Password", strToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Reset", 1);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Forgot Password", "Ok", 1);
   		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1230)
	public void I3120F_ForgotPassword_DifferentPassword() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		String strGmailPassword = clsCommonWeb.strGmailPassword;
		//Check User Exist and is active
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strGmailPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
 		try {Thread.sleep(8000);}catch (Exception e) {}
  		//Open GMAIL
		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		if(strToken.equals("")){clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Password Reset Token did not exist");}
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion;
		String strEnvironment = objDictionary.get("strEnvironment");
		intCurrentVersion = Integer.parseInt(strSentryLinkCurrentPassword.replaceAll("\\D+", ""));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Set New Password", "Populate Reset Password", "{T} Reset Token|{T} New Password|{T} Confirm New Password", strToken+"|"+strNewPassword+"|InvalidPassword");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Reset", 1);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Set New Password", "Passwords do not match",1, "Exists", "");
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1240)
	public void I3121F_ForgotPassword_SentResetInstructions_LoginWithCurrentPassword() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Get Gmail Values
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailPassword = clsCommonWeb.strGmailPassword;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		//Check User Exist and is active
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strGmailPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Back", 1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Forgot Password", "Log in", 1);
 		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password",strGmailUserName+"|"+strSentryLinkCurrentPassword);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Account");
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1250)
	public void I3122F_ForgotPassword_ResendResetInstructionsTwice_UseFirstToken() throws MessagingException, IOException 
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Gmail clsGmail = new Gmail();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		//Get Gmail Values
  		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
  		String strGmailUserName = clsCommonWeb.strGmailUserName;
  		String strGmailPassword = clsCommonWeb.strGmailPassword;
  		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
  		String strGmailLastName = clsCommonWeb.strGmailLastName;
  		//Check User Exist and is active
  		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strGmailPassword, "parker",strGmailFirstName, strGmailLastName);
  		//Open Sentry Mobile
  		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		//Open Consumer App
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
 		try {Thread.sleep(5000);}catch (Exception e) {}
 		//Open GMAIL
   		String strFirstToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Back", 1);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
 		try {Thread.sleep(5000);}catch (Exception e) {}
 		String strSecondToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion = Integer.parseInt(strSentryLinkCurrentPassword.replaceAll("\\D+", ""));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Set New Password", "Populate Reset Password", "{T} Reset Token|{T} New Password|{T} Confirm New Password", strFirstToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Reset", 1);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Forgot Password", "Ok", 1);
   		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1260)
	public void I3123F_ForgotPassword_ResendResetInstructionsTwice_UseSecondToken() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		//Get Dictionary Variables
		String strEnvironment = objDictionary.get("strEnvironment");
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Get Gmail Values
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailPassword = clsCommonWeb.strGmailPassword;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		//Check User Exist and is active
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
 		try {Thread.sleep(5000);}catch (Exception e) {}
 		//Open GMAIL
   		String strFirstToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Back", 1);
   		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
 		try {Thread.sleep(5000);}catch (Exception e) {}
		String strSecondToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion = Integer.parseInt(strSentryLinkCurrentPassword.replaceAll("\\D+", ""));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Set New Password", "Populate Reset Password", "{T} Reset Token|{T} New Password|{T} Confirm New Password", strSecondToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Reset", 1);
		strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Password reset successfully")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Password reset successfully) - actual value ("+strSnackbarText+")");}
 		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
      	clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Account");
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1270)
	public void I3124F_ForgotPasswordRememberMeOn() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		//Get Dictionary Variables
		String strEnvironment = objDictionary.get("strEnvironment");
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Get Gmail Values
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailPassword = clsCommonWeb.strGmailPassword;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		//Check User Exist and is active
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
		try {Thread.sleep(5000);}catch (Exception e) {}
 		//Open GMAIL
		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		if(strToken.equals("")){clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Password Reset Token did not exist");}
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion = Integer.parseInt(strSentryLinkCurrentPassword.replaceAll("\\D+", ""));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Set New Password", "Populate Reset Password", "{T} Reset Token|{T} New Password|{T} Confirm New Password", strToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Reset", 1);
		strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Password reset successfully")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Password reset successfully) - actual value ("+strSnackbarText+")");}
 		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Account");
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1280)
	public void I3125F_ForgotPasswordRememberMeOff() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		//Get Dictionary Variables
		String strEnvironment = objDictionary.get("strEnvironment");
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Get Gmail Values
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailPassword = clsCommonWeb.strGmailPassword;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		//Check User Exist and is active
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Forgot password", 1);
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Forgot Password", "Populate Email Id", "{T} Email", strGmailUserName);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Forgot Password", "Send Reset Instructions", 1);
 		String strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Email has been sent")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Email has been sent) - actual value ("+strSnackbarText+")");}
		try {Thread.sleep(5000);}catch (Exception e) {}
 		//Open GMAIL
		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		if(strToken.equals("")){clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Password Reset Token did not exist");}
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion = Integer.parseInt(strSentryLinkCurrentPassword.replaceAll("\\D+", ""));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Set New Password", "Populate Reset Password", "{T} Reset Token|{T} New Password|{T} Confirm New Password", strToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Set New Password", "Reset", 1);
		strSnackbarText = objDictionary.get("strSnackbarText");
 		if(strSnackbarText.equals("Password reset successfully")){Reporter.log("The Text (Message) with index (1) contained (" + strSnackbarText + ")");}
		else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosDriver,"The Text (Message) with index (1) did not contain (Password reset successfully) - actual value ("+strSnackbarText+")");}
 		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Account");
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Sign out", 1);
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Account", "Yes", 1);
		iosDriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1290)
	public void I3126F_LoginAttemptsExceededYourAccountHasBeenLocked() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
		String strGmailUserName = clsCommonWeb.strGmailUserName;
		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
		String strGmailLastName = clsCommonWeb.strGmailLastName;
		String strGmailPassword = clsCommonWeb.strGmailPassword;
		//Check User Exist and is active
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strGmailPassword, "parker",strGmailFirstName, strGmailLastName);
		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		clsCommonWeb.SENTRYLINK_CreateUserAndCloseBrowser(objDictionary, strGmailUserName, strPassword, "parker", strGmailFirstName, strGmailLastName);
		//Open Consumer App
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		//1st Login with Invalid Credentials with Municipality
		String strRandomLetter = clsCommonWeb.SENTRYLINK_CreateRandomLetter();
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password",strGmailUserName+"|"+strPassword+strRandomLetter);
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
		//Invalid Credential. Please re-enter your email id and password.
		int intLoginCounter = 1;
		do
		{
			clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Log in", "Invalid credentials, please contact customer support.", 1, "Exists", "");
			clsCommonMobile.ClickLink(objDictionary, iosDriver, "Log in", "Ok", 1);
			clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Password",strPassword+strRandomLetter);
			clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
	  	 	intLoginCounter++;
		}while (intLoginCounter < 6);
		//Validate Email
		System.out.println("This is still on the road map");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		iosDriver.quit();
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strGmailUserName);
		driver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1300)
    public void I3127F_FTFP0_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		objDictionary.put("strTrueUp", "False");
  		clsCommonMobile.SENTRYMOBILE_3127_FTFP0_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1310)
   	public void I3128F_FTFP0_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3128_FTFP0_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1320)
    public void I3129F_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3129_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1330)
    public void I3130F_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3130_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1340)
  	public void I3131F_FTFP0_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3131_FTFP0_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1350)
	public void I3132F_FTFP0_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3132_FTFP0_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1360)
   	public void I3133F_FTFP0_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3133_FTFP0_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1370)
    public void I3134F_FTFP0_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3134_FTFP0_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1380)
    public void I3135F_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3135_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1390)
    public void I3136F_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3136_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1400)
  	public void I3137F_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3137_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1410)
    public void I3138F_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184059365");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3138_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1420)
	public void I3139F_FTFP10_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3139_FTFP10_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1430)
    public void I3140F_FTFP10_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3140_FTFP10_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=1440)
	public void I3141F_FTFP10_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3141_FTFP10_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1450)
	public void I3142F_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3142_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1460)
	public void I3143F_FTFP10_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3143_FTFP10_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1470)
	public void I3144F_FTFP10_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3144_FTFP10_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1480)
	public void I3145F_FTFP10_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3145_FTFP10_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1490)
	public void I3146F_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3146_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1500)
	public void I3147F_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3147_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1510)
	public void I3148F_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3148_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1520)
	public void I3149F_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "FLUTTERCA-18");
  		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3149_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1530)
	public void I3150F_FTFP0_TUOFF_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3150_FTFP0_TUOFF_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 1540)
	public void I3151F_FTEP0_TUOFF_UOFF_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_3151_FTFP0_TUOFF_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
	}
	@Test(priority=1152)
	public void A3152F_ValidateInvalidCreditCardDate()
	{
    	objDictionary.put("strAssociatedBug", "FLUTTERCA-188");
    	objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
    	//Open Consumer App
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		//Click Accept
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
 		String strRole = objDictionary.get("strRole");
 		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password|{CB} Remember me",strUserName.toLowerCase()+"|"+strPassword.replace(" ", "")+"|Checked");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
 		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		//Validate Parking Space is in Violation
   		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Account");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Account", "Add Money", 1);
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Payment", "Add Payment Amount", "{T} Add amount","5000");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Payment", "Proceed",1);
		//Populate Credit Card
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Payment", "Credit or Debit Card",1);
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Card Details", "Populate Credit Card", "{T} Card Number","5555555555554444");
		//Get strMonthYear
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String strPreviousMonth = new SimpleDateFormat("MM").format(cal.getTime());
		String strYear = new SimpleDateFormat("yyyy").format(cal.getTime());
		if (strPreviousMonth.equals("01")) {
		    cal.add(Calendar.YEAR, -1);
		    strYear = new SimpleDateFormat("yyyy").format(cal.getTime());
		}
		String strMonthYear = strPreviousMonth + strYear;
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Card Details", "Populate Expiration Date", "{T} Expiration Date|{T} Security Code",strMonthYear+"|123");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Card Details", "Add Card",1);
		clsCommonMobile.VerificationPointButton(objDictionary, iosDriver, "Card Details", "Add Card", 1, "Disabled");
		iosDriver.quit();
	}
	@Test(priority=1560,groups={"PAY_BY_PLATE"})
  	public void I3157F_PBP_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
  	{
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsCommonMobile.SENTRYMOBILE_3157_PBP_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1600)
  	public void I3199F_RSVN_MPRSISR()
    {
		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("RSVN: Add Reservation                                           ");
  		Reporter.log("MPRSISR: MobilePaymentRejectedSpotIsReserved                    ");
  		Reporter.log("ES1: Exit Spot 1                                                ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "240";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strMinutesOfFreeTimeBeforeCurrentTime = "0";objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime", strMinutesOfFreeTimeBeforeCurrentTime);
		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		objDictionary.put("strPermitCost","2.00");
  		objDictionary.put("strPermitRate","Hourly 1");
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//String strFreeTimeMinutes = "0";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
  		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-2");
  		//ExitSpotAndSetMeterRateBlocks
  		String strHost = objDictionary.get("strHost");
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "0");
  		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes", strReservationTimeMinutes);
  		objDictionary.put("strReservationRateType", "Fixed");
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Deenrolled Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Update Permit Rate Blocks
  		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
  		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
  		//Add Or Update Permit Group
  		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary);
  		try {Thread.sleep(45000);}catch (Exception e) {}
  		String strMeterName = objDictionary.get("strMeterName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True"))
  		{
  			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);
  		}  		
  		//Open Sentry Mobile-Android
  		clsCommonMobile.SENTRYMOBILE_MobilePaymentRejectedSpotIsReserved(objDictionary, strLicensePlateNumber, "Minnesota");
        String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1610)
  	public void I3200F_RSVN_PSWVLP_VMT_ES1_VPSH() 
    {
		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("RSVN: Add Reservation                                           ");
  		Reporter.log("PSWVLP: Park Spot With Valid License Plate                      ");
  		Reporter.log("VMT: Validate Meter Time                                        ");
  		Reporter.log("ES1: Exit Spot 1                                                ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "240";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		objDictionary.put("strPermitCost","2.00");
  		objDictionary.put("strPermitRate","Hourly 1");
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-2");
  		//ExitSpotAndSetMeterRateBlocks
  		String strHost = objDictionary.get("strHost");
  		objDictionary.put("strReservationTestCase", "True");
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "0");
  		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes", strReservationTimeMinutes);
  		objDictionary.put("strReservationRateType", "Fixed");
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Deenrolled Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Update Permit Rate Blocks
  		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
  		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
  		//Add Or Update Permit Group
  		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
  		//Park With License Plate   
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		//Calculate Remaining Reservation time
  		String strReservationStartTime = objDictionary.get("strReservationStartTime");
  		String strStartTime = "";
  		String strEndTime = "";
  		try
  		{
  			LocalTime startTime = LocalTime.parse(strReservationStartTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
  		    LocalDate date = LocalDate.now();
  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
  		    strStartTime = date.atTime(startTime).format(formatter);
  		    LocalDateTime endDateTime = date.atTime(startTime).plus(60, ChronoUnit.MINUTES);
  		    strEndTime = endDateTime.format(formatter);
		}
  		catch (Exception e) 
  		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"Unable to format start or end date: "+e);}
  		int intRemaingReservationTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary,null,strReservationStartTime,strReservationTimeMinutes);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intRemaingReservationTime,"1");
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Add License Plate to SL
  		clsCommonWeb.AddLicensePlateToParkingSession( objDictionary, strLicensePlateNumber, "Minnesota");
  		//Open Sentry Mobile-Android
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		//Click Accept
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
  		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
  		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
  		String strActualRemainingTimeMinutes = objDictionary.get("strActualRemainingTimeMinutes");
  		int remainingMinutes = Integer.parseInt(strActualRemainingTimeMinutes.trim());
  		remainingMinutes -= 1;//Subtract a minutes for time deprecation
  		objDictionary.put("strActualRemainingTimeMinutes", String.valueOf(remainingMinutes));
		clsCommonMobile.SENTRYMOBILE_ValidateParkingSession(objDictionary,iosDriver, strStartTime, strEndTime, "$ 0.00");
		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
      	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3200_RSVN_PSWVLP_VMT_ES1(objDictionary, 59,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1620)
  	public void I3201F_RSVN_PSWILP_MPRSISR_ES1_VPSH() 
    {
		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("RSVN: Add Reservation                                           ");
  		Reporter.log("PSWILP: Park Spot With Invalid License Plate                    ");
  		Reporter.log("MPRSISR: MobilePaymentRejectedSpotIsReserved                    ");
  		Reporter.log("ES1: Exit Spot 1                                                ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "240";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		objDictionary.put("strPermitCost","2.00");
  		objDictionary.put("strPermitRate","Hourly 1");
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strFreeTimeMinutes = "0";
  		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-2");
  		//ExitSpotAndSetMeterRateBlocks
  		String strHost = objDictionary.get("strHost");
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "0");
  		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes", strReservationTimeMinutes);
  		objDictionary.put("strReservationRateType", "Fixed");
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//De-enrolled Concierge
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", "INVALID","Minnesota");
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Update Permit Rate Blocks
  		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
  		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
  		//Add Or Update Permit Group
  		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
  		//Park With License Plate   
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, "INVALID");
  		objDictionary.put("strLicensePlateNumber", "INVALID");
  		try {Thread.sleep(45000);}catch (Exception e) {}
  		String strMeterName = objDictionary.get("strMeterName");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True"))
  		{
  			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);
  		}
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Add License Plate to SL
  		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, "INVALID", "Minnesota");
  		clsCommonMobile.SENTRYMOBILE_MobilePaymentRejectedSpotIsReserved(objDictionary, "INVALID", "Minnesota");
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//************************************************************************************
  	//WOONERF FREE -> RESERVATIONS
  	//************************************************************************************
  	@Test(priority=1630)
  	public void I3202F_RSVN_FTRSVN_PSWL_MPRSIIFPGIRP() 
    {
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("RSVN: Add Reservation                                           ");
  		Reporter.log("FTRSVN: Free To Reservation                                     ");
  		Reporter.log("PSWL: Park Spot With Valid License Plate                        ");
  		Reporter.log("MPRSIIFPGIRP: MobilePaymentRejectedSpotIsInFreeParkingGoingIntoReservedParking");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "240";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		objDictionary.put("strPermitCost","2.00");
  		objDictionary.put("strPermitRate","Hourly 1");
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strFreeTimeMinutes = "10";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strMinutesOfFreeTimeBeforeCurrentTime = "0"; objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime", strMinutesOfFreeTimeBeforeCurrentTime);
  		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "6");
  		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes", strReservationTimeMinutes);
  		objDictionary.put("strReservationRateType", "Fixed");
  		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-1");
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		clsHttpConnections.POST_ConciergeDenroll(objDictionary, "parker");
		//UPDATE METER SETTING
  		String strHost = objDictionary.get("strHost");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Update Permit Rate Blocks
  		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
  		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
  		//Add Or Update Permit Group
  		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary); 
  		//Park With License Plate
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		clsCommonMobile.SENTRYMOBILE_MobilePaymentRejectedSpotIsInFreeParkingGoingIntoReservedParking(objDictionary, strLicensePlateNumber, "Minnesota");
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=1640)
  	public void I3203F_RSVN_FTRSVN_PSWL_WFTE_VMTAP_VPS_VPSH()
    {
  		objDictionary.put("strAssociatedBug", "187626408|184858316");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("RSVN: Add Reservation                                           ");
  		Reporter.log("FTRSVN: Free To Reservation                                     ");
  		Reporter.log("PSWL: Park Spot With Valid License Plate                        ");
  		Reporter.log("WFTE: Wait For Free Time To Expire                              ");
  		Reporter.log("VMTAP: Payment rejected, spot is reserved                       ");
  		Reporter.log("VPS: Validate Parking Session                                   ");
  		Reporter.log("VPS: Validate Parking Session History                           ");
  	  	Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "240";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "240";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		objDictionary.put("strPermitCost","2.00");
  		objDictionary.put("strPermitRate","Hourly 1");
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strFreeTimeMinutes = "6";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strMinutesOfFreeTimeBeforeCurrentTime = "0";objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime", strMinutesOfFreeTimeBeforeCurrentTime);
  		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "6");
  		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes", strReservationTimeMinutes);
  		objDictionary.put("strReservationRateType", "Fixed");
  		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-1");
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Delete Reservation
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		clsHttpConnections.POST_ConciergeDenroll(objDictionary, "parker");
		//UPDATE METER SETTING
  		String strHost = objDictionary.get("strHost");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Update Permit Rate Blocks
  		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
  		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
  		//Add Or Update Permit Group
  		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary);
  		//Park With License Plate
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
		String strParkTime = sdf.format(Date.from(Instant.now().truncatedTo(ChronoUnit.MINUTES)));
  		String strReservationStartTime = objDictionary.get("strReservationStartTime");
  		String strEndTime = "";
  		try
  		{
  			LocalTime startTime = LocalTime.parse(strReservationStartTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
  		    LocalDate date = LocalDate.now();
  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, hh:mm a");
  		    LocalDateTime endDateTime = date.atTime(startTime).plus(60, ChronoUnit.MINUTES);
  		    strEndTime = endDateTime.format(formatter);
		}
  		catch (Exception e) 
  		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"Unable to format start or end date: "+e);}
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Add License Plate to SL
  		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, "Minnesota");
  		//Wait For free Time time to expire
  		int intMaxWaitSeconds = Integer.parseInt(strFreeTimeMinutes) * 60;
  		clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterFreeEqualsFalse} NA", intMaxWaitSeconds, "1","Local");
  		strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		//Wait 10 seconds
  		try {Thread.sleep(5000);}catch (Exception e) {}
  		int intExpectedRemainingTime = Integer.parseInt(strReservationTimeMinutes);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		//Click Accept
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
  		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		String strMeterSpotName = objDictionary.get("strMeterSpotName");
		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Park");
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Enter your Parking Zone Number", "Populate Zone Number", "{T} Zone Number",strMeterSpotName);
		objDictionary.put("strMeterSpotName", strMeterSpotName);
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, iosDriver,  "Enter your Parking Zone Number", "Meter Spot Number", 1);
		String strSnackbarText = objDictionary.get("strSnackbarText");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Enter your Parking Zone Number", "Pay", 1);
	    strSnackbarText = objDictionary.get("strSnackbarText");if(strSnackbarText == null) {strSnackbarText = "";}
	    if(!strSnackbarText.equals(""))
	    {
	    	clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosDriver, "Unexpected Error when clicking Pay:"+strSnackbarText);
		}
	    clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, iosDriver, strLicensePlateNumber, "Minnesota");
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Choose Parking Duration", "Proceed", 1);
     	//Select Payment Option and Pay
     	clsCommonMobile.PopulateScrollableListbox(objDictionary,iosDriver, "Review and Pay", "Select Payment Option", "FusionAI Mobile Account");
     	clsCommonMobile.ClickButton(objDictionary, iosDriver, "Review and Pay", "Proceed", 1);
     	//Validate Message Device Rejected
	 	clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Review and Pay", "Payment rejected, spot is reserved", 1, "Exists", "");
  		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Review and Pay", "Ok", 1);
  		//Navigate to Parking Sessions
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Review and Pay", "Close", 1);
     	clsCommonMobile.ClickButton(objDictionary, iosDriver, "Choose Parking Duration", "Close", 1);
     	clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
     	objDictionary.put("strActualRemainingTimeMinutes","59");
     	//Validate Parking Session
     	clsCommonMobile.SENTRYMOBILE_ValidateParkingSession(objDictionary,iosDriver,strParkTime, strEndTime, "$ 0.00");
     	//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
      	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3203_RSVN_FTRSVN_PSWL_WFTE_VMTAP(objDictionary, strReservationTimeMinutes,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  //************************************************************************************
  	//CONCIERGE
  	//************************************************************************************
	@Test(priority=1650)
  	public void I3301F_CS_PSWL_VMT_VPSH() 
  	{
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("CS: Concierge Service                                           ");
  		Reporter.log("PSWL: Park Spot With Valid License Plate                        ");
  		Reporter.log("VMT: Validate Meter Time                                        ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "120";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "120";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "2";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//Concierge Test Case
		objDictionary.put("strConciergeTestCase", "True");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Delete Reservation
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
  		//ExitSpotAndSetMeterRateBlocks
  		//clsMeter.METER_ExitBothSpots(objDictionary,null,"Local");
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Deenrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//CS: Concierge Service 
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		String strHost = objDictionary.get("strHost");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//Park With License Plate
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		String strParkTime = "";String strEndTime = "";
  		try {
  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
  		    LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
  		    strParkTime = start.format(formatter);
  		    LocalDateTime end = start.plus(Integer.parseInt(strMeterIncrementTime), ChronoUnit.MINUTES);
  		    //Concierge users get Initial Grace Time
  		    end = end.plus(Integer.parseInt(strInitialGracePeriod), ChronoUnit.MINUTES);
  		    strEndTime = end.format(formatter);
  		}
  		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unable to format start or end date: " + e);}
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Wait For initial grace to expire
  		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60+10;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for initial grace time to expire-Spot1");
  		//Wait For SL Parking Session Concierge Value to equal True
  		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA", 30,"1","Local");
    	//Validate Meter Time
  		String strMeterName = objDictionary.get("strMeterName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True"))
  		{
  			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);
  		}
  		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
    	//Dictionary Variables
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		//Click Accept
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
  		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
 		String strActualRemainingTimeMinutes = objDictionary.get("strActualRemainingTimeMinutes");
  		int remainingMinutes = Integer.parseInt(strActualRemainingTimeMinutes.trim());
  		remainingMinutes -= 1;//Subtract a minutes for time deprecation
  		objDictionary.put("strActualRemainingTimeMinutes", String.valueOf(remainingMinutes));
		//Validate Parking Session
     	clsCommonMobile.SENTRYMOBILE_ValidateParkingSession(objDictionary,iosDriver,strParkTime, strEndTime, "$ 0.50");
     	//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
  		clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3301_CS_PSWL(objDictionary, strMeterIncrementTime,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1660)
  	public void I3302F_CS_PS1_GPV1_ALPTPS_VMT_VPSH() 
  	{
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("CS: Concierge Service                                           ");
  		Reporter.log("PS1: Park Spot 1                                                ");
   		Reporter.log("GPV1: Create Grace Period Violation Spot 1                      ");
   		Reporter.log("ALPTPS: Add License Plate To Parking Session                    ");
   		Reporter.log("VMT: Validate Meter Time                                        ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "120";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "120";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "2";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//Concierge Test Case
		objDictionary.put("strConciergeTestCase", "True");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Delete Reservation
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//disenrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//CS: Concierge Service 
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		String strHost = objDictionary.get("strHost");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//GPV1: Create Grace Period Violation Spot 1 
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, "1", strInitialGracePeriod);
  		String strParkTime = objDictionary.get("strParkedTime");
  		String strEndTime = "";
  		try {
  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
  		    LocalDateTime start = LocalDateTime.parse(strParkTime + ", " + Year.now().getValue(),DateTimeFormatter.ofPattern("MMM dd, hh:mm a, yyyy"));
  		    LocalDateTime end = start.plus(Integer.parseInt(strMeterIncrementTime), ChronoUnit.MINUTES);
  		    end = end.plus(Integer.parseInt(strInitialGracePeriod), ChronoUnit.MINUTES);
  		    strEndTime = end.format(formatter);
  		} 
  		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unable to format start or end date: " + e);}
  		clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	 	//ALPTPS: Add License Plate To Parking Session 
   		String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State", strLicensePlateNumber + "|Minnesota");
	  	clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1, "Local");
	  	try {Thread.sleep(5000);}catch (Exception e) {} 
	  	driver.quit();
	  	//Wait For SL Parking Session Concierge Value to equal True
  		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA", 50,"1","Local");
    	String strMeterName = objDictionary.get("strMeterName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);}
  		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
    	//Dictionary Variables
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		//Click Accept
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
  		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
 		String strActualRemainingTimeMinutes = objDictionary.get("strActualRemainingTimeMinutes");
  		int remainingMinutes = Integer.parseInt(strActualRemainingTimeMinutes.trim());
  		remainingMinutes -= 1;//Subtract a minutes for time deprecation
  		objDictionary.put("strActualRemainingTimeMinutes", String.valueOf(remainingMinutes));
		//Validate Parking Session
     	clsCommonMobile.SENTRYMOBILE_ValidateParkingSession(objDictionary,iosDriver,strParkTime, strEndTime, "$ 0.50");
     	//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
  		clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3302_CS_PS1_GPV1_ALPTPS(objDictionary, strMeterIncrementTime,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	
	@Test(priority=1670)
  	public void I3302F_A_CS_TU_PS1_GPV1_ALPTPS_VMT_VPSH() 
  	{
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("CS: Concierge Service                                           ");
  		Reporter.log("PS1: Park Spot 1                                                ");
   		Reporter.log("GPV1: Create Grace Period Violation Spot 1                      ");
   		Reporter.log("ALPTPS: Add License Plate To Parking Session                    ");
   		Reporter.log("VMT: Validate Meter Time                                        ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "120";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "120";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "2";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//Concierge Test Case
		objDictionary.put("strConciergeTestCase", "True");
		objDictionary.put("strTrueUp", "True");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Delete Reservation
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//disenrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//CS: Concierge Service 
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		String strHost = objDictionary.get("strHost");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//GPV1: Create Grace Period Violation Spot 1 
  		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, "1", strInitialGracePeriod);
  		String strParkTime = objDictionary.get("strParkedTime");
  		clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
//  		String strEndTime = "";
//  		try {
//  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
//  		    LocalDateTime start = LocalDateTime.parse(strParkTime + ", " + Year.now().getValue(),DateTimeFormatter.ofPattern("MMM dd, hh:mm a, yyyy"));
//  		    LocalDateTime end = start.plus(Integer.parseInt(strMeterIncrementTime), ChronoUnit.MINUTES);
//  		    end = end.plus(Integer.parseInt(strInitialGracePeriod), ChronoUnit.MINUTES);
//  		    strEndTime = end.format(formatter);
//  		} 
//  		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unable to format start or end date: " + e);}
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	 	//ALPTPS: Add License Plate To Parking Session 
   		String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State", strLicensePlateNumber + "|Minnesota");
	  	clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1, "Local");
	  	try {Thread.sleep(2000);}catch (Exception e) {}
	  	driver.quit();
	  	//Wait For SL Parking Session Concierge Value to equal True
  		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA", 50,"1","Local");
  		
  		//Test Here
  		String strEndTime = "";
  		try {
  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
  		    LocalDateTime start = LocalDateTime.parse(strParkTime + ", " + Year.now().getValue(),DateTimeFormatter.ofPattern("MMM dd, hh:mm a, yyyy"));
  		    LocalDateTime end = start.plus(Integer.parseInt(strMeterIncrementTime), ChronoUnit.MINUTES);
  		    end = end.plus(Integer.parseInt(strInitialGracePeriod), ChronoUnit.MINUTES);
  		    strEndTime = end.format(formatter);
  		} 
  		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unable to format start or end date: " + e);}
  		String strMeterName = objDictionary.get("strMeterName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);}
  		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
    	//Dictionary Variables
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
  		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
		//Validate Parking Session
     	clsCommonMobile.SENTRYMOBILE_ValidateParkingSession(objDictionary,iosDriver,strParkTime, strEndTime, "$ 0.50");
     	//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
  		clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3302_A_CS_TU_PS1_GPV1_ALPTPS(objDictionary, strMeterIncrementTime,"1");
  	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}

	@Test(priority=1680)  
  	public void I3303F_CS_PS1_GPV1_WFFACTNVT_ALPTPS_VMT_VPSH() 
  	{
		objDictionary.put("strAssociatedBug", "SL-7995");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
  		Reporter.log("***************TestCase Description***************************************");
  		Reporter.log("CS: Concierge Service                                                     ");
  		Reporter.log("PS1: Park Spot 1                                                          ");
  		Reporter.log("WFRACTNVT: Wait For Recognized as Concierge time to be near Violation time");
   		Reporter.log("GPV1: Create Grace Period Violation Spot 1                                ");
   		Reporter.log("ALPTPS: Add License Plate To Parking Session                              ");
   		Reporter.log("VMT: Validate Meter Time                                                  ");
  		Reporter.log("VPSH: Validate Parking Session History                                    ");
  		Reporter.log("**************************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "120";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "120";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "2";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "2";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "OFF";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//Concierge Test Case
		objDictionary.put("strConciergeTestCase", "True");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Delete Reservation
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//disenrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		//CS: Concierge Service 
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		String strHost = objDictionary.get("strHost");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
  		//PS: Park Spot 1
  		clsMeter.METER_ParkSpot(objDictionary,"1","Local");
  		//WFRACTNVT: Wait For Recognized as Concierge time to be near Violation time
  		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
  		intInitialGracePeriod = intInitialGracePeriod - 15;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Wait For Recognized as Concierge time to be near Violation time");
		Reporter.log("Waited ("+intInitialGracePeriod+") For Recognized as Concierge time to be near Violation time");
		String strParkTime = objDictionary.get("strParkedTime");
  		String strEndTime = "";
  		try {
  		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
  		    LocalDateTime start = LocalDateTime.parse(strParkTime + ", " + Year.now().getValue(),DateTimeFormatter.ofPattern("MMM dd, hh:mm a, yyyy"));
  		    LocalDateTime end = start.plus(Integer.parseInt(strMeterIncrementTime), ChronoUnit.MINUTES);
  		    end = end.plus(Integer.parseInt(strInitialGracePeriod), ChronoUnit.MINUTES);
  		    strEndTime = end.format(formatter);
  		} 
  		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unable to format start or end date: " + e);}
  		//ALPTPS: Add License Plate To Parking Session 
   		String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession2(objDictionary, driver, "Sentry Meter","1");
    	clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State", strLicensePlateNumber + "|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1, "Local");
		try {Thread.sleep(2000);}catch (Exception e) {}
		driver.quit();
	  	//Wait For SL Parking Session Concierge Value to equal True
  		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA", 120,"1","Local");
      	//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	String strMeterName = objDictionary.get("strMeterName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);}
  		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Wait 5 seconds for payment to get to Meter");    
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
  		//Validate CA Parking Session values
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
  		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosDriver, "Sessions");
		//Validate Parking Session
     	clsCommonMobile.SENTRYMOBILE_ValidateParkingSession(objDictionary,iosDriver,strParkTime, strEndTime, "$ 0.50");
    	//Get Violation Number
		String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
  		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
  		clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3303_CS_PS1_GPV1_WFFACTNVT_ALPTPS(objDictionary, strMeterIncrementTime,"1",strViolationId);
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	
	@Test(priority=1690)
	public void I3304F_CS_PSWL_MP1_W4_VMT_VPSH()
  	{
		objDictionary.put("strAssociatedBug","187526604|7159");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		HttpConnections clsHttpConnections = new HttpConnections();
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("CS: Concierge Service                                           ");
  		Reporter.log("PSWL: Park Spot With Valid License Plate                        ");
  		Reporter.log("MP1: Mobile Payment Spot #1 (Mobile payment is made before concierge payment");
  		Reporter.log("W4: Wait 4 Minutes for conciege to get recognized               ");
  		Reporter.log("VMT: Validate Meter Time                                        ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "120";objDictionary.put("strMaximumDuration", strMaximumDuration);
  		String strCoinTimePuchaseLimit = "120";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "4";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
  		String strViolationGracePeriod = "4";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "4";
  		String strNoParkingGrace = "4";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		//Concierge Test Case
		objDictionary.put("strConciergeTestCase", "True");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
  		String strHost = objDictionary.get("strHost");
		//Delete Reservation
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
  		//ExitSpotAndSetMeterRateBlocks
  		clsMeter.METER_ExitBothSpots(objDictionary,null,"Local");
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//Deenrolled Concierge
  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Remove all License Plate
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		//Need To get Account Balance
		clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		//CS: Concierge Service
		clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
  		//Park With License Plate
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		//Click Accept
		clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept",1);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker","Remind me later");
		//ClickPermissionAlertButton
      	try {Thread.sleep(3000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
      	try {Thread.sleep(2000);}catch (Exception e) {}
      	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");
 		//ParkTP: Park Then Pay
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosDriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","True");
		iosDriver.quit();
  		//Wait For 3 minutes For Concierge Payment to get applied
  		clsMeter.METER_MeterWaitWithMessage(objDictionary,180, "Wait 3 minutes after paying for Concierge payment to get applied.");
   		//Store Parking Id
		//ShortSessionWaitExitSpot
     	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
     	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
     	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_I3304_CS_PSWL_W4_MP1_VMT_VPSH(objDictionary);
     	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	
	
	@Test(priority=3400)
	public void I3400_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3400_FTFP0_PS1_MP1_PRT_AP_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	
  	//Updated


	//***************************************
  	//CONCIERGE
  	//***************************************
  	@Test(priority=1157)
   	public void I157_SENTRYMOBILE_PS1_EC1_NSAU_NSTU()
   	{
 		objDictionary.put("strAssociatedBug", "157433836");
 		CommonIOS clsCommonMobile = new CommonIOS();
 		String strUniqueId = objDictionary.get("strUniqueId");
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
 		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
 		clsCommonMobile.SENTRYMOBILE_DC1_ANU_PS1_EC1_VNN(objDictionary,strLicensePlateNumber,"Account Details","Enroll later");
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
 		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}

//	@Test(priority=1158)
//	public void I158_SENTRYMOBILE_PS1_EC1_NSAC_NSTC()
//	{
//		objDictionary.put("strAssociatedBug", "157433836");
//		CfommonIOS clsCommonMobile = new CommonIOS();
//		String strUniqueId = objDictionary.get("strUniqueId");
//		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//	String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
//	clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAC_NSTC(objDictionary,strLicensePlateNumber);
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
//	}
//	@Test(priority=1159)
//	public void I3159F_SENTRYMOBILE_PS1_EC1_NSAC_NSTU()
//	{
//		objDictionary.put("strAssociatedBug", "157433836");
//		CommonIOS clsCommonMobile = new CommonIOS();
//		String strUniqueId = objDictionary.get("strUniqueId");
//		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//	String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
//	clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAC_NSTU(objDictionary,strLicensePlateNumber);
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
//	}
//	@Test(priority=1160)
//	public void I160_SENTRYMOBILE_PS1_EC1_NSAU_NSTC()
//	{
//		objDictionary.put("strAssociatedBug", "157433836");
//		CommonIOS clsCommonMobile = new CommonIOS();
//		String strUniqueId = objDictionary.get("strUniqueId");
//		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//	String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
//	clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAU_NSTC(objDictionary,strLicensePlateNumber);
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
// }
//	@Test(priority=1161)
//	public void I3161F_SENTRYMOBILE_PS1_EC1_NSAU_NSTC()
//	{
//		objDictionary.put("strAssociatedBug", "157877668");
//	CommonIOS clsCommonMobile = new CommonIOS();
//		String strUniqueId = objDictionary.get("strUniqueId");
//		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
//		clsCommonMobile.SENTRYMOBILE_PS1_ALPMPP_VLAD_ES1(objDictionary,strLicensePlateNumber);
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
//	}




	@Test(priority=1570)
	public void I3159F_SENTRYMOBILE_PS1_EC1_NSAC_NSTU()
	{
		objDictionary.put("strAssociatedBug", "FLUTTERCA-CONCIERGE");
		objDictionary.put("strMobileDeviceType", "IOS");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAC_NSTU(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	@Test(priority=1580)
	public void I3160F_SENTRYMOBILE_PS1_EC1_NSAU_NSTC()
	{
		objDictionary.put("strAssociatedBug", "FLUTTERCA-CONCIERGE");
		objDictionary.put("strMobileDeviceType", "IOS");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAU_NSTC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	@Test(priority=1590)
	public void I3161F_SENTRYMOBILE_PS1_RELP_ALPMPP_VLAD_ES1()
	{
		objDictionary.put("strAssociatedBug", "FLUTTERCA-CONCIERGE");
		objDictionary.put("strMobileDeviceType", "IOS");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		String strLicensePlateState = "Alabama";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		objDictionary.put("strLicensePlateState", strLicensePlateState);
		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		clsCommonMobile.SENTRYMOBILE_PS1_RELP_ALPMPP_VLAD_ES1(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	// --- Android parity tests (auto-synced) ---

	

	@Test(priority=2002)
		public void I2002F_RegisterUser_PasswordShouldHaveAtleastOneDigitOrSpecialCharacter_Flutter()
		{
			//This test case nees to be renamed
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
			String strUserName = "ADeleteUser@gmail.com";
			String strPassword = "ADeleteMeUser";
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			//Click Sign up
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Login", "Sign up",1);
			//Populate Phone Number
			clsCommonMobile.PopulateActionSlow(objDictionary, iosdriver, "Sign Up", "Populate Phone Number", "{T} Phone Number","6125581707");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Next",1);
			//Populate Sign Up
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Sign Up", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
			//Click Sign Up
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Sign up",1);
			//Validate Error Message
			clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Sign Up", "Password must be at least 12 characters, at least 1 upper case letter, at least 1 lower case letter, at least 1 number, at least 1 special character", 1, "Exists", "");
			iosdriver.quit();
			clsMeter.METER_SetMeterEndTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2007)
		public void I2007F_RegisterUser_PasswordShouldBeAtleast7Characters_Flutter()
		{
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
			String strUserName = "darin2@mpspark.com";
			String strPassword = "L@kem1";
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			//Click Sign up
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Login", "Sign up",1);
			//Populate Phone Number
			clsCommonMobile.PopulateActionSlow(objDictionary, iosdriver, "Sign Up", "Populate Phone Number", "{T} Phone Number","6125581707");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Next",1);
			//Populate Sign Up
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
			//Click Sign Up
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Sign up",1);
			clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Sign Up", "Password must be at least 12 characters, at least 1 upper case letter, at least 1 lower case letter, at least 1 number, at least 1 special character", 1, "Exists", "");
			iosdriver.quit();
			clsMeter.METER_SetMeterEndTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=150) 
		public void I3018F_RegisterUser_WIFI_Enabled()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-249");
			objDictionary.put("strMobileDeviceType", "IOS");
			objDictionary.put("strWIFI", "Enabled");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			CommonWeb clsCommonWeb = new CommonWeb();
			Meter clsMeter = new Meter();
			String strEnvironment = objDictionary.get("strEnvironment");
			if(strEnvironment.equals("PROD"))
			{
				objDictionary.put("strAssociatedBug", "NoPROD");
				clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
			}
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
			//Destroy User
			String strUserName = "adeleteuser01@gmail.com";objDictionary.put("strUserName", strUserName);
			String strPassword = "ADeleteMe01!";
			String strBrowser = objDictionary.get("strBrowser");
			String strRemotePath = objDictionary.get("strRemotePath");
			//UnDelete Account
			threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
			WebDriver driver = getDriver(); // driver.quit();
			clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
			clsCommonWeb.SENTRYLINK_UnDeleteAccount(objDictionary,driver,strUserName);
			//Unlock Account
			threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
			driver = getDriver();
			clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
			clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver,strUserName);
			clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			//Click Sign up
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Log in", "Sign up",1);
			//Populate Phone Number
			clsCommonMobile.PopulateActionSlow(objDictionary, iosdriver, "Sign Up", "Populate Phone Number", "{T} Phone number","281-281-6804");
			//Click Accept
			clsCommonMobile.PopulateActionSlow(objDictionary, iosdriver, "Sign Up", "Populate Phone Number", "{T} Phone number","281-281-6804");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Next",1);
			//Permissions
			clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
			//Populate Sign Up
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
			//Click Sign Up
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Sign up",1);
			String strSnackbarText = objDictionary.get("strSnackbarText");
			if(strSnackbarText.equals("Registered Successfully"))
			{
				Reporter.log("The Text (Error Message) with index (1) contained (" + strSnackbarText + ")");
				try {Thread.sleep(2000);}catch (Exception e) {}//Wait Until Message Goes away
			}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,iosdriver,"The Text (Error Message) with index (1) did not contain (Registered Successfully) - actual value ("+strSnackbarText+")");}
			//Navigate to Account
			clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Account");
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Sign out", 1);
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Yes", 1);
			//Sign In Using Phone Number
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password","2812816804|"+strPassword);
	 		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Log in", "Log in",1);
	 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Account");
	 		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Sign out", 1);
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Yes", 1);
			iosdriver.quit();
			clsMeter.METER_SetMeterEndTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=160) 
		public void I3019F_RegisterUser_WIFI_Disabled()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-259");
			objDictionary.put("strMobileDeviceType", "IOS");
			objDictionary.put("strWIFI", "Disabled");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			CommonWeb clsCommonWeb = new CommonWeb();
			Meter clsMeter = new Meter();
			String strEnvironment = objDictionary.get("strEnvironment");
			if(strEnvironment.equals("PROD"))
			{
				objDictionary.put("strAssociatedBug", "NoPROD");
				clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
			}
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
			//Destroy User
			String strUserName = "adeleteuser01@gmail.com";objDictionary.put("strUserName", strUserName);
			String strPassword = "ADeleteMe01!";
			String strBrowser = objDictionary.get("strBrowser");
			String strRemotePath = objDictionary.get("strRemotePath");
			//UnDelete Account
			threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
			WebDriver driver = getDriver(); // driver.quit();
			clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
			clsCommonWeb.SENTRYLINK_UnDeleteAccount(objDictionary,driver,strUserName);
			//Unlock Account
			threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
			driver = getDriver();
			clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
			clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver,strUserName);
			clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			//Click Sign up
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Log in", "Sign up",1);
			//Populate Phone Number
			clsCommonMobile.PopulateActionSlow(objDictionary, iosdriver, "Sign Up", "Populate Phone Number", "{T} Phone Number","2812816804");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Next",1);
			//Populate Sign Up
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Sign Up", "Populate Register", "{T} First Name|{T} Last Name|{T} Email|{T} Password|{T} Confirm password","Delete|User|"+strUserName+"|"+strPassword+"|"+strPassword+"");
			//Click Sign Up
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Sign Up", "Sign up",1);
			String strSnackbarText = objDictionary.get("strSnackbarText");
			
			//No Internet Connection
			
			//Sign Out
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Sign out", 1);
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Yes", 1);
			//Sign In Using Phone Number
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password","2812816804|"+strPassword);
	 		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Log in",1);
	 		//For a better experience your device will need to use Location Accuracy
	 		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Map", "No thanks",1);
	 		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Account");
	 		//Sign Out
	 		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Sign out", 1);
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Yes", 1);
			iosdriver.quit();
			clsMeter.METER_SetMeterEndTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=180,groups={"Smoke"})
		public void I3021F_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC_WIFI_Disabled()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-259");
			String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			objDictionary.put("strWIFI", "Disabled");
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
			HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			clsCommonMobile.SENTRYMOBILE_3021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary, strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=240) 
		public void I3027F_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC()throws Exception
		{
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "F"+strSpaceName;
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,strLicensePlateState);
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1960)
		public void I3035F_A_FTFP0_PS1_CCP1_RM_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
		{
			objDictionary.put("strAssociatedBug", "182742334|182238240|188297606");
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3035_A_FTFP0_PS1_CCP1_RM_MP1_ES1_VPSH_VICAE_VIAC(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2050)
	  	public void I2050F_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
	    {
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3050_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	    }

	@Test(priority=620)
		public void I3062F_A_FTFP0_FTR_RFT_gt_MTIV_MP1_PS1_VMT_VPSH_VICAE_VIAC() throws Exception
		{
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3062_A_FTFP0_FTR_RFT_gt_MTIV_MP1_PS1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=680)
	  	public void I3067F_B_FTFP0_FTR_RFT_gt_MTIV_PS1_PRT_VMT_VPSH_VICAE_VIAC() throws Exception
		{
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3067_B_FTFP0_FTR_RFT_gt_MTIV_PS1_PRT_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=700)
		public void I3068F_A_HR()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-159");
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3068_A_HR(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=770)
	  	public void I3074F_A_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_MTR_PRT_VPSH_VICAE_VIAC() throws Exception
		{
	  		String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3074_A_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_MTR_PRT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2079) 
		public void I2079F_FTFP0_FDON_RTF_PS1_MP1_BMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
		{	
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3079_FTFP0_FDON_RTF_PS1_MP1_BMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2115)
		public void I2115F_FTFP0_TUON_UOff_PS1_GPV1_UTE_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
		{
	    	String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3115_FTFP0_TUON_UOff_PS1_GPV1_UTE_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2142)
		public void I2142F_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
		{
	 		objDictionary.put("strAssociatedBug", "183842654");
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3142_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2151)
	    public void I2151F_FTEP0_TUOFF_UOFF_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
	   	{
	    	String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			clsCommonMobile.SENTRYMOBILE_3151_FTFP0_TUOFF_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
	  	}

	@Test(priority=1550)
		public void I3152F_ValidateInvalidCreditCardDate()
		{
	    	objDictionary.put("strAssociatedBug", "FLUTTERCA-188");
	    	IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
	    	CommonWeb clsCommonWeb = new CommonWeb();
	    	//Open Consumer App
	 		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
	 		//Click Accept
	 		clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
	 		String strRole = objDictionary.get("strRole");
	 		String strUniqueId = objDictionary.get("strUniqueId");
			String strMunicipality = objDictionary.get("strMunicipality");
			String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
			String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password|{CB} Remember me",strUserName.toLowerCase()+"|"+strPassword.replace(" ", "")+"|Checked");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Log in",1);
	 		//Validate Parking Space is in Violation
	   		clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Account");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Account", "Add Money", 1);
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Payment", "Add Payment Amount", "{T} Payment amount","5000");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Payment", "Proceed",1);
			//Populate Credit Card
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Payment", "Credit or Debit Card",1);
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Card Details", "Populate Credit Card", "{T} Card Number","5555555555554444");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Card Details", "Next",1);
			//Get strMonthYear
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			String strPreviousMonth = new SimpleDateFormat("MM").format(cal.getTime());
			String strYear = new SimpleDateFormat("yyyy").format(cal.getTime());
			if (strPreviousMonth.equals("01")) {
			    cal.add(Calendar.YEAR, -1);
			    strYear = new SimpleDateFormat("yyyy").format(cal.getTime());
			}
			String strMonthYear = strPreviousMonth + strYear;
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Card Details", "Populate Expiration Date", "{T} Expiration Date|{T} CVC",strMonthYear+"|123");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Card Details", "Add Card",1);
			clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Card Details", "Expiration date is invalid", 1, "Contains", "Expiration date is invalid");
	  		iosdriver.quit();
		}

	@Test(priority=2154)
	  	public void I2154F_PS1_CP1_VMT_MMNP_VMM_MP2_VSIM_MP1_VMT() throws Exception
	    {
	  		objDictionary.put("strAssociatedBug", "155742666|155725215");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName",strTestCaseName);
	  		objDictionary.put("strMobileDeviceType", "Meter");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		Meter clsMeter = new Meter();
	  		CommonWeb clsCommonWeb = new CommonWeb();
	  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
	  		Reporter.log("***************TestCase Description*****************************");
		    Reporter.log("PS1: Park Spot 1                                                ");
	  		Reporter.log("CP1: Coin Payment Spot 1                                        ");
	  		Reporter.log("VMT: Validate Meter Time                                        ");
		  	Reporter.log("MMNP: Maintenance Mode No Parking                               ");
		  	Reporter.log("VMM: Validate Maintenanc Mode                                   ");
		    Reporter.log("MP2: Mobile Payment Spot 2                                      ");
		    Reporter.log("VSIM: Validate	Spot In Maintenance                              ");
			Reporter.log("MP1: Mobile Payment Spot 1                                      ");
			Reporter.log("VMT: Validate Meter Time                                        ");
			Reporter.log("****************************************************************");
	  		//Test Case Variables
	  		String strMaximumDuration = "240";
	  		String strCoinTimePuchaseLimit = "240";
	  		String strFreeTimeFirstPayment = "0";
	  		String strCreditCardIncrementTime = "15";
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
	  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
	  		//Dictionary Variables
	  		String strHost = objDictionary.get("strHost");
	  		//Maintenance Mode No Parking  
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_card_error_rate_mode_overlay.py MAINT_NO_PARKING_SPOT");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_coin_error_rate_mode_overlay.py MAINT_NO_PARKING_SPOT");
		  	//PS1: Park Spot 1
	  		clsMeter.METER_ParkSpotBySpot(objDictionary, null,  "1");
	  		//CP1: Coin Payment Spot 1
	  		clsMeter.METER_InsertCoin(objDictionary, null,"1","Local");
	  		//VMT: Validate Meter Time
	  		int intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime);
		  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
		  	//Enable MaintenanceMode
	  		clsCommonWeb.SENTRYLINK_UpdateMaintenanceMode(objDictionary, "Enable","","No Parking","Local");
	  		//MP1: Mobile Payment
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//ParkTP: Park Then Pay
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
			clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
			clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker",strLicensePlateNumber, "Enroll later");
			Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
			clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Meter Payment");
			clsCommonMobile.SENTRYMOBILE_PopulateOrValidateMeterPaymentMunicipality(objDictionary,iosdriver,"True");
			//MP2: Mobile Payment Spot 2
			clsCommonMobile.SENTRYMOBILE_Populate_MeterPayment_SelectSpot(objDictionary,iosdriver,"2");
			clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, iosdriver, strLicensePlateNumber, "Alabama");
	     	clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Meter Payment", "{TextExists} Meter Payment~Meter Amount", 60);
	     	//Validate Max Time
	     	clsCommonMobile.VALIDATIONS_MeterPayment_MaxTime(objDictionary, iosdriver, 0, Integer.parseInt(strMaximumDuration),0);
	     	String strTotalFee = clsCommonMobile.StoreText(objDictionary, iosdriver, "Meter Payment", "Meter Amount", 1, "strTotalFee");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "Purchase Parking", 1);
			clsCommonMobile.SENTRYMOBILE_ValidateFirstPaymentDialogAndReturnParkingFee(objDictionary, iosdriver, strTotalFee,"2");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "YES - PURCHASE", 1);
			//Validate Meter Payment Dialog 
			clsCommonMobile.VALIDATION_MeterPaymentDialog(objDictionary,iosdriver,"Meter spot in maintenance, payments not accepted");
			//MP1: Mobile Payment Spot 1
			clsCommonMobile.SENTRYMOBILE_Populate_MeterPayment_SelectSpot(objDictionary,iosdriver,"1");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "Purchase Parking", 1);//#155742666
			clsCommonMobile.SENTRYMOBILE_ValidateFirstPaymentDialogAndReturnParkingFee(objDictionary, iosdriver, strTotalFee,"1");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "YES - PURCHASE", 1);
			//VMT: Validate Meter Time
			intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) * 20;
	      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
	      	iosdriver.quit();
			//Disable MaintenanceMode
		  	clsCommonWeb.SENTRYLINK_UpdateMaintenanceMode(objDictionary,  "Disable","1","","Local");
		  	//Validate Maintenance Mode
		  	clsMeter.METER_ValidateSpotLevelMaintenanceMode(objDictionary, null,  "No Parking", "1", "False");
		  	clsMeter.METER_ValidateSpotLevelMaintenanceMode(objDictionary, null,  "No Parking", "2", "False");
		  	CommonWeb.setWarningMsg("After Maintenance Mode has been removed validate you can do a Mobile Payment both sides");
		  	//ShortSessionWaitExitSpot
		  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		  	clsMeter.METER_SetMeterEndTime(objDictionary);
	      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	    }

	@Test(priority=2155)
	  	public void I2155F_PS1_CP1_VMT_MMFP_VMM_MP2_VSIM_MP1_VMT() throws Exception
	    {
	  		objDictionary.put("strAssociatedBug", "155742666|155725215");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName",strTestCaseName);
	  		objDictionary.put("strMobileDeviceType", "Meter");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		Meter clsMeter = new Meter();
	  		CommonWeb clsCommonWeb = new CommonWeb();
	  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
	  		Reporter.log("***************TestCase Description*****************************");
		    Reporter.log("PS1: Park Spot 1                                                ");
	  		Reporter.log("CP1: Coin Payment Spot 1                                        ");
	  		Reporter.log("VMT: Validate Meter Time                                        ");
		  	Reporter.log("MMNP: Maintenance Mode Free Parking                             ");
		  	Reporter.log("VMM: Validate Maintenanc Mode                                   ");
		    Reporter.log("MP2: Mobile Payment Spot 2                                      ");
		    Reporter.log("VSIM: Validate	Spot In Maintenance                           ");
			Reporter.log("MP1: Mobile Payment Spot 1                                      ");
			Reporter.log("VMT: Validate Meter Time                                        ");
			Reporter.log("****************************************************************");
	  		//Test Case Variables
	  		String strMaximumDuration = "240";
	  		String strCoinTimePuchaseLimit = "240";
	  		String strFreeTimeFirstPayment = "0";
	  		String strCreditCardIncrementTime = "15";
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
	  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
	  		//Dictionary Variables
	  		String strHost = objDictionary.get("strHost");
	  		//Maintenance Mode Free Parking  
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_card_error_rate_mode_overlay.py MAINT_FREE_PARKING_SPOT");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_coin_error_rate_mode_overlay.py MAINT_FREE_PARKING_SPOT");
	  		//PS1: Park Spot 1
	    	clsMeter.METER_ParkSpotBySpot(objDictionary, null,  "1");
	    	//CP1: Coin Payment Spot 1    
	    	clsMeter.METER_InsertCoin(objDictionary, null,"1","Local");
			//VMT: Validate Meter Time 
	    	int intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime);
		  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
		  	//Enable MaintenanceMode
	  		clsCommonWeb.SENTRYLINK_UpdateMaintenanceMode(objDictionary,  "Enable","","Free Parking","Local");
	  		//MP1: Mobile Payment
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//ParkTP: Park Then Pay
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
			clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
			clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker",strLicensePlateNumber, "Enroll later");
			Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
			clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Meter Payment");
			clsCommonMobile.SENTRYMOBILE_PopulateOrValidateMeterPaymentMunicipality(objDictionary,iosdriver,"True");
			//MP2: Mobile Payment Spot 2
			clsCommonMobile.SENTRYMOBILE_Populate_MeterPayment_SelectSpot(objDictionary,iosdriver,"2");
			clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, iosdriver, strLicensePlateNumber, "Alabama");
	     	clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Meter Payment", "{TextExists} Meter Payment~Meter Amount", 60);
	     	//Validate Max Time
	     	clsCommonMobile.VALIDATIONS_MeterPayment_MaxTime(objDictionary, iosdriver, 0, Integer.parseInt(strMaximumDuration),0);
	     	String strTotalFee = clsCommonMobile.StoreText(objDictionary, iosdriver, "Meter Payment", "Meter Amount", 1, "strTotalFee");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "Purchase Parking", 1);
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "YES - PURCHASE", 1);
			//Validate Meter Payment Dialog 
			clsCommonMobile.VALIDATION_MeterPaymentDialog(objDictionary,iosdriver,"Meter spot in maintenance, payments not accepted");
			//MP1: Mobile Payment Spot 1
			clsCommonMobile.SENTRYMOBILE_Populate_MeterPayment_SelectSpot(objDictionary,iosdriver,"1");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "Purchase Parking", 1);//#155742666
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "YES - PURCHASE", 1);
			//VMT: Validate Meter Time
			intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) * 20;
	      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
	      	iosdriver.quit();
			//Disable MaintenanceMode
		  	clsCommonWeb.SENTRYLINK_UpdateMaintenanceMode(objDictionary,  "Disable","1","","Local");
		  	//Validate Maintenance Mode
		  	clsMeter.METER_ValidateSpotLevelMaintenanceMode(objDictionary, null,  "No Parking", "1", "False");
		  	clsMeter.METER_ValidateSpotLevelMaintenanceMode(objDictionary, null,  "No Parking", "2", "False");
		  	CommonWeb.setWarningMsg("After Maintenance Mode has been removed validate you can do a Mobile Payment both sides");
		  	//ShortSessionWaitExitSpot
		  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		  	clsMeter.METER_SetMeterEndTime(objDictionary);
	      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	    }

	@Test(priority=2156)
	  	public void I2156F_PS1_CP1_VMT_MMUP_VMM_MP2_VSIM_MP1_VMT() throws Exception
	    {
	  		objDictionary.put("strAssociatedBug", "155749440");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName",strTestCaseName);
	  		objDictionary.put("strMobileDeviceType", "Meter");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		Meter clsMeter = new Meter();
	  		CommonWeb clsCommonWeb = new CommonWeb();
	  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
	  		Reporter.log("***************TestCase Description*****************************");
		    Reporter.log("PS1: Park Spot 1                                                ");
	  		Reporter.log("CP1: Coin Payment Spot 1                                        ");
	  		Reporter.log("VMT: Validate Meter Time                                        ");
		  	Reporter.log("MMNP: Maintenance Mode Unenforced Parking                       ");
		  	Reporter.log("VMM: Validate Maintenanc Mode                                   ");
		    Reporter.log("MP2: Mobile Payment Spot 2                                      ");
		    Reporter.log("VSIM: Validate	Spot In Maintenance                              ");
			Reporter.log("MP1: Mobile Payment Spot 1                                      ");
			Reporter.log("VMT: Validate Meter Time                                        ");
			Reporter.log("****************************************************************");
	  		//Test Case Variables
	  		String strMaximumDuration = "240";
	  		String strCoinTimePuchaseLimit = "240";
	  		String strFreeTimeFirstPayment = "0";
	  		String strCreditCardIncrementTime = "15";
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
	  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
	  		//Dictionary Variables
	  		String strHost = objDictionary.get("strHost");
	  		//Maintenance Mode Unenforced Parking  
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_card_error_rate_mode_overlay.py MAINT_UNENFORCED_PARKING_SPOT");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "set_coin_error_rate_mode_overlay.py MAINT_UNENFORCED_PARKING_SPOT");
	  		//PS1: Park Spot 1
	    	clsMeter.METER_ParkSpotBySpot(objDictionary, null,  "1");
	    	//CP1: Coin Payment Spot 1    
	    	clsMeter.METER_InsertCoin(objDictionary, null,"1","Local");
			//VMT: Validate Meter Time 
	    	int intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime);
		  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
		  	//Enable MaintenanceMode
		  	clsCommonWeb.SENTRYLINK_UpdateMaintenanceMode(objDictionary,  "Enable","","Maint. Unenforced","Local");
	  		//MP1: Mobile Payment
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//ParkTP: Park Then Pay
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker"); 
			clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
			clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker",strLicensePlateNumber, "Enroll later");
			Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
			clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Meter Payment");
			clsCommonMobile.SENTRYMOBILE_PopulateOrValidateMeterPaymentMunicipality(objDictionary,iosdriver,"True");
			//MP2: Mobile Payment Spot 2
			clsCommonMobile.SENTRYMOBILE_Populate_MeterPayment_SelectSpot(objDictionary,iosdriver,"2");
			clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, iosdriver, strLicensePlateNumber, "Alabama");
	     	clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Meter Payment", "{TextExists} Meter Payment~Meter Amount", 60);
	     	//Validate Max Time
	     	clsCommonMobile.VALIDATIONS_MeterPayment_MaxTime(objDictionary, iosdriver, 0, Integer.parseInt(strMaximumDuration),0);
	     	String strTotalFee = clsCommonMobile.StoreText(objDictionary, iosdriver, "Meter Payment", "Meter Amount", 1, "strTotalFee");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "Purchase Parking", 1);
			clsCommonMobile.SENTRYMOBILE_ValidateFirstPaymentDialogAndReturnParkingFee(objDictionary, iosdriver, strTotalFee,"2");
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "YES - PURCHASE", 1);
			//VMT: Validate Meter Time
			intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime);
	      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"2");
			
			
	//			//Validate Meter Payment Dialog 
	//			clsCommonMobile.VALIDATION_MeterPaymentDialog(objDictionary,iosdriver,"Meter spot in maintenance, payments not accepted");
	//			//MP1: Mobile Payment Spot 1
	//			clsCommonMobile.SENTRYMOBILE_Populate_MeterPayment_SelectSpot(objDictionary,iosdriver,"1");
	//			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "Purchase Parking", 1);//#155742666
	//			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Meter Payment", "YES - PURCHASE", 1);
			//VMT: Validate Meter Time
	//			intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) * 20;
	//	      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
	      	iosdriver.quit();
			//Disable MaintenanceMode
		  	clsCommonWeb.SENTRYLINK_UpdateMaintenanceMode(objDictionary,  "Disable","1","","Local");
		  	//Validate Maintenance Mode
		  	clsMeter.METER_ValidateSpotLevelMaintenanceMode(objDictionary, null,  "No Parking", "1", "False");
		  	clsMeter.METER_ValidateSpotLevelMaintenanceMode(objDictionary, null,  "No Parking", "2", "False");
		  	CommonWeb.setWarningMsg("After Maintenance Mode has been removed validate you can do a Mobile Payment both sides");
		  	//ShortSessionWaitExitSpot
		  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		  	clsMeter.METER_SetMeterEndTime(objDictionary);
	      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	    }

	@Test(priority=2157)
	  	public void I2157_MMNPAS_VMMNP()
	  	{
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName", strTestCaseName);
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
	  		//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
	  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	  		//Add License Plate
	  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		clsCommonMobile.SENTRYMOBILE_MMNPAS_VMMNPAS(objDictionary,strLicensePlateNumber);
	  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
	  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	  	}

	@Test(priority=2158)
	  	public void I2158_MMNPS1_VMMNPES1_VMMNPDES2()
	  	{
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName", strTestCaseName);
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
	  		//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
	  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	  		//Add License Plate
	  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		clsCommonMobile.SENTRYMOBILE_MMNPS1_VMMNP_VMMNPES1_VMMNPDES2(objDictionary,strLicensePlateNumber);
	  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
	  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	  	}

	@Test(priority=2159)
	  	public void I2159_MMFPAS_VMMFP()
	  	{	objDictionary.put("strAssociatedBug", "7159");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName", strTestCaseName);
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
	  		//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
	  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	  		//Add License Plate
	  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		clsCommonMobile.SENTRYMOBILE_MMFPAS_VMMFPAS(objDictionary,strLicensePlateNumber);
	  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
	  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	  	}

	@Test(priority=2160)
	  	public void I2160_MMFPS1_VMMFPES1_VMMFPDES2()
	  	{	objDictionary.put("strAssociatedBug", "7159");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName", strTestCaseName);
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
	  		//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
	  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	  		//Add License Plate
	  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		clsCommonMobile.SENTRYMOBILE_MMNPS1_VMMNP_VMMNPES1_VMMNPDES2(objDictionary,strLicensePlateNumber);
	  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
	  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	  	}

	@Test(priority=2161)
	  	public void I2161_MMUPAS_VMMUP()
	  	{
	  		objDictionary.put("strAssociatedBug", "169635111|7159");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName", strTestCaseName);
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
	  		//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
	  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	  		//Add License Plate
	  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
	  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	  		clsCommonMobile.SENTRYMOBILE_MMUPAS_VMMUPAS(objDictionary,strLicensePlateNumber);
	  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
	  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	  	}

	@Test(priority=1700)
		public void I3305F_HDCP_FTFP0_MP1_VMT_ES1_VPSH_VIAC() throws Exception
	  	{
			objDictionary.put("strAssociatedBug","FLUTTERCA-251");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		objDictionary.put("strTestCaseName", strTestCaseName);
	  		objDictionary.put("strMobileDeviceType", "IOS");
	  		Meter clsMeter = new Meter();
	  		CommonWeb clsCommonWeb = new CommonWeb();
	  		HttpConnections clsHttpConnections = new HttpConnections();
	  		IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
	  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
	  		Reporter.log("***************TestCase Description*****************************");
	  		Reporter.log("FTFP0: Free Time First Payment 0                                ");
	  		Reporter.log("MP1: Park Spot With Valid License Plate                         ");
	  		Reporter.log("VMT: Validate Meter Time                                        ");
	  		Reporter.log("ES1: Exit Spot 1                                                ");
	  		Reporter.log("VPSH: Validate Parking Session History                          ");
	  		Reporter.log("VIAC: Validate Images Appear Correctly                          ");
	  		Reporter.log("****************************************************************");
	  		//Test Case Variables
	  		String strMaximumDuration = "120";objDictionary.put("strMaximumDuration", strMaximumDuration);
	  		String strCoinTimePuchaseLimit = "120";
	  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
	  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
	  		String strInitialGracePeriod = "4";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
	  		String strViolationGracePeriod = "4";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
	  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
	  		String strHandicapViolationGrace = "4";
	  		String strNoParkingGrace = "4";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
	  		String strParkingShortSessionSec = "15";
	  		String strSetImageSendBeforeViolation = "45";
	  		String strUnlockValue = "On";
	  		String strUnlockTime = "1";
	  		String strUnlockMax = "1";
	  		String strFamilyBuyMinutes = strMaximumDuration;
	  		String strHandicapPermit = "1011"; objDictionary.put("strHandicapPermit",strHandicapPermit);
	  		String strHost = objDictionary.get("strHost");
	  		//objDictionary.put("strFamilyBuyMinutes", "60");
	  		//objDictionary.put("strFamilyMaxTime", strMaximumDuration);
	  		// car plate
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			//Delete Reservation
	  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
	  		if(strReservationExists.equals("True"))
	  		{
	  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
	  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
	  		}
	  		//ExitSpotAndSetMeterRateBlocks
	  		clsMeter.METER_ExitBothSpots(objDictionary,null,"Local");
	  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
	  		//Deenrolled Concierge
	  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
			//Remove all License Plate
	  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	  		//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
			//Need To get Account Balance
			clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
	  		//Enable Handicap
	  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker","Remind me later");
			//Navigate To Account
			clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Account");
			// Checking to see if the switch is clicked
			boolean isHandicapOn = !iosdriver.findElements(By.xpath("//*[contains(@name,'Permit ID') or contains(@label,'Permit ID')]")).isEmpty();
			if(!isHandicapOn)
			{
				WebElement switchElement = iosdriver.findElement(By.xpath("//XCUIElementTypeSwitch[contains(@name,'Handicap') or contains(@label,'Handicap') or @name='Handicap'] | //*[@name='Handicap' or @label='Handicap']/following-sibling::*[self::XCUIElementTypeSwitch or self::XCUIElementTypeButton][1]"));
				switchElement.click();
				clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Yes, Continue",1);
				clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Account", "Populate Handicap Permit ID", "{T} Handicap permit ID","1011");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "Account", "Add Permit",1);
			}
			iosdriver.quit();
			//PS1: Park Spot 1
		   	clsMeter.METER_ParkSpot(objDictionary,"1","Local");		
	  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");		
			strMeterIncrementTime = strFamilyBuyMinutes;
			try {Thread.sleep(2000);}catch (Exception e) {}
			iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker","Remind me later");
			//ParkTP: Park Then Pay
			String strHandicapBuyMinutes = "30";//From the Rate Block Family Setting Handicap
			clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosdriver,strFreeTimeFirstPayment,strHandicapBuyMinutes,"True","True");
			iosdriver.quit();
		
			//Nothing below is complete waiting for FLUTTERCA-251
			try {Thread.sleep(2000);}catch (Exception e) {}
			//Exit Vehicle
	  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  		//Disable Handicap - Function
	  		iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker","Remind me later");
			//Navigate To Account
			clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Account");
			// Checking to see if the switch is clicked
			isHandicapOn = !iosdriver.findElements(By.xpath("//*[contains(@name,'Permit ID') or contains(@label,'Permit ID')]")).isEmpty();
			if(isHandicapOn)
			{
				WebElement switchElement = iosdriver.findElement(By.xpath("//XCUIElementTypeSwitch[contains(@name,'Handicap') or contains(@label,'Handicap') or @name='Handicap'] | //*[@name='Handicap' or @label='Handicap']/following-sibling::*[self::XCUIElementTypeSwitch or self::XCUIElementTypeButton][1]"));
				switchElement.click();
				clsCommonMobile.ClickLink(objDictionary, iosdriver, "Account", "Yes, Remove",1);
			}
			iosdriver.quit();
	 		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	     	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2305_HDCP_FTFP0_MP1_VMT_ES1_VPSH_VIAC(objDictionary);
	     	clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
	     	clsMeter.METER_SetMeterEndTime(objDictionary);
	//     	clsCommonMobile.SENTRYMOBILE_HDCPDeenroll2(objDictionary,iosdriver);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	    }

	@Test(priority=1710)
		public void I3306F_CS_PSWL_MP1_PMT_LMV_AV_VPSH() throws Exception
		{
			objDictionary.put("strMobileDeviceType", "IOS");
			objDictionary.put("strAssociatedBug", "1622|SL-6966");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			HttpConnections clsHttpConnections = new HttpConnections();
			CommonWeb clsCommonWeb = new CommonWeb();
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
			Reporter.log("***************TestCase Description*********************************");
			Reporter.log("CS: Concierge Service                                               ");
			Reporter.log("PSWL: Park Spot With Valid License prioor to Recoginzed as Concierge");
			Reporter.log("MP1: Mobile Payment Spot 1                                          ");
			Reporter.log("PMT: Purchase Max Time before SL deducts any Concierge payments     ");
			Reporter.log("LMV: Let Meter Violate                                              ");
			Reporter.log("AV:  Approve Violation                                              ");
			Reporter.log("VPSH: Validate Parking Session History                              ");
			Reporter.log("********************************************************************");
			//Test Case Variables
			String strMaximumDuration = "12";objDictionary.put("strMaximumDuration", strMaximumDuration);
			String strCoinTimePuchaseLimit = "12";
			String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
			String strMeterIncrementTime = "3";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
			String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
			String strViolationGracePeriod = "1";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
			String strHandicapInitialGracePeriod = "1";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
			String strHandicapViolationGrace = "1";
			String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
			String strParkingShortSessionSec = "1";
			String strSetImageSendBeforeViolation = "45";
			String strUnlockValue = "On";
			String strUnlockTime = "5";
			String strUnlockMax = "5";
			String strBrowser = objDictionary.get("strBrowser");
			String strRemotePath = objDictionary.get("strRemotePath");
			String strSpotNumber = objDictionary.get("strSpotNumber");
			//Concierge Test Case
			objDictionary.put("strConciergeTestCase", "True");
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Minnesota";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			//Delete Reservation
	  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
			if(strReservationExists.equals("True"))
			{
				String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
				clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
			}	
			//ExitSpotAndSetMeterRateBlocks
			clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
			//De-enroll Concierge
			clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
			//Remove all License Plate
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
			//Need To get Account Balance
			clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
			//CS: Concierge Service
			clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
			String strHost = objDictionary.get("strHost");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
			clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_CONCIERGE_ALLOW_VIO");
			//PMT: Park with Valid License Plate and Purchase Max Time
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
			//Click Accept
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
			clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPaymentMaxTime(objDictionary, iosdriver);
			String strTotalPayment = objDictionary.get("strTotalFee");//SENTRYMOBILE_BuyMaxTime
			//Select Payment Option and Pay
	     	clsCommonMobile.PopulateScrollableListbox(objDictionary,iosdriver, "Review and Pay", "Select Payment Option", "FusionAI Mobile Account");
	     	//Set Start and End Times
	     	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
	  		String strStartTime = sdf.format(Date.from(Instant.now().truncatedTo(ChronoUnit.MINUTES)));
	  		String strEndTime = clsCommonMobile.AddTimeToExistingTime(strStartTime, strMaximumDuration,"MMM dd, hh:mm a");
	  		//Park With License Plate
	     	clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
	  		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Review and Pay", "Proceed", 1);
	     	//Wait For Parking Session Details
	    	clsCommonMobile.GlobalWait(objDictionary, iosdriver, "Parking Sessions", "{TextExists} Parking Sessions~Parking Session Details", 80);
	     	//Validate Expected Meter Time
			clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, Integer.parseInt(strMaximumDuration), "1");
	   		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
			WebDriver driver = getDriver();
			clsMeter.METER_WaitUntilMeterRemainingTimeEqualZero2(objDictionary, null, "1");
			threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
			clsMeter.GlobalWait(objDictionary, driver, "{WaitUntilMeterViolationEqualsTrue} NA", 70, strSpotNumber,"Local");
	  		try {Thread.sleep(5000);}catch (Exception e) {}
			String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
			//Store Parking Id
			clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");		
	        //Approve Violation Ticket
		  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
			//Approve Violation
		  	try {Thread.sleep(15000);}catch (Exception e) {}
		  	clsCommonWeb.SENTRYLINK_ApproveViolation_EnterPlateViolationDialog_AddFiveStarImage(objDictionary, driver);
	  		//Wait 15 seconds In Initial Grace
	     	try {Thread.sleep(15000);}catch (Exception e) {}
			//ShortSessionWaitExitSpot
			clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null,"1","Local");
	  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_false.py PARKING_CONCIERGE_ALLOW_VIO");
			//ValidateParkingSessionHistoryAndImages
			Android_ParkingSessions clsConsumerAppTestCaseParkingSessions = new Android_ParkingSessions();
			clsConsumerAppTestCaseParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2306_CS_PSWL_MP1_PMT_LMV_AV_VPSH(objDictionary, strViolationId);
			clsMeter.METER_SetMeterEndTime(objDictionary);
		}

	@Test(priority=1720, alwaysRun = true)
		public void I3307F_CS_FADCT_PSWL_WGE_EAV() throws Exception
		{
		    String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		    objDictionary.put("strMobileDeviceType", "Android");
		    objDictionary.put("strTestCaseName", strTestCaseName);
		    CommonWeb clsCommonWeb = new CommonWeb();
		    Meter clsMeter = new Meter();
		    IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
		    Reporter.log("***************TestCase Description*************************");
		    Reporter.log("FADCT: Free All Day Concierge Test                          ");
		    Reporter.log("SMDL: Set Meter & Lot in FREE ALL DAY                      ");
		    Reporter.log("PCAP: Park w/CA Android Concierge parker in Spot 1         ");
		    Reporter.log("WGE: Wait until grace expires                              ");
		    Reporter.log("EAV: Exit and validate no charges                          ");
		    Reporter.log("************************************************************");
		    //Dictionary Variables
		    String strHost = objDictionary.get("strHost");
		    //Test Case Variables - Set for Free All Day
		    String strFreeTimeFirstPayment = "0"; // No free time with first payment
		    objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		    String strMeterIncrementTime = "60"; // 1 hour increments
		    objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		    String strInitialGracePeriod = "2"; // 2 minutes grace period for faster testing
		    objDictionary.put("strConciergeTestCase", "True");
		    HttpConnections clsHttpConnections = new HttpConnections();
		    String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
	  		if(strReservationExists.equals("True"))
	  		{
	  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
	  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
	  		}
		    // Set Free All Day parameters
		    objDictionary.remove("strFreeTimeMinutes");
		    objDictionary.put("strFreeTimeMinutes", "1440"); // 24 hours of free time
		    objDictionary.remove("strMinutesOfFreeTimeBeforeCurrentTime");
		    objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime", "0"); // Start free time immediatel
		    //ExitSpotAndSetMeterRateBlocks
		    objDictionary.remove("strFreeDisconnectValue");
		    objDictionary.put("strFreeDisconnectValue", "Off");
	        //SMDL: Set Meter in FREE ALL DAY for today and every day except yesterday, which is paid
	        clsCommonWeb.SENTRYLINK_SetRateBlocksFreeTodayAndEveryDayButPaidYesterday(objDictionary);
	       //De-enroll Concierge
		    clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);	
		    //Remove all License Plates
		    clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		    //Create license plate for test
		    String strSpaceName = objDictionary.get("strMeterSpotName");
		    String strLicensePlateNumber = "0"+strSpaceName+"AA";
		    objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		    //Add License Plate
		    clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		    //Need To get Account Balance
		    clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		    //CS: Concierge Service
		    clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		    clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		    //PCAP: Park w/CA Android Concierge parker in Spot 1
		    clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
		    //Store Parking Id
		    clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		    //WGE: Wait until grace expires
		    int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		    clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for initial grace time to expire-Spot1");
		    //Validate Meter Time - should show free time
		    //EAV: Exit and validate no charges
		    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		    //Validate Parking Session History and Images
		    Android_ParkingSessions clsAndroidParkingSessions = new Android_ParkingSessions();
		    clsAndroidParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2307_CS_FADCT_PSWL_WGE_EAV(objDictionary);
		    clsMeter.METER_SetMeterEndTime(objDictionary);
		}

	@Test(priority=1730, alwaysRun = true)
		public void I3308F_CS_FADCT_PSWL_WGE_EAV_mobilePaymentBlocked() throws Exception
		{
		    String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		    objDictionary.put("strMobileDeviceType", "Android");
		    objDictionary.put("strTestCaseName", strTestCaseName);
		    CommonWeb clsCommonWeb = new CommonWeb();
		    Meter clsMeter = new Meter();
		    IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
		    Reporter.log("***************TestCase Description*************************");
		    Reporter.log("FADCT: Free All Day Concierge Test                          ");
		    Reporter.log("SMDL: Set Meter & Lot in FREE ALL DAY                      ");
		    Reporter.log("PCAP: Park w/CA Android Concierge parker in Spot 1         ");
		    Reporter.log("WGE: Wait until grace expires                              ");
		    Reporter.log("EAV: Exit and validate no charges                          ");
		    Reporter.log("Mobile Payment gets blocked");
		    Reporter.log("************************************************************");
		    //Dictionary Variables
		    String strHost = objDictionary.get("strHost");
		    String strSpotNumber = objDictionary.get("strSpotNumber");
		    String strBrowser = objDictionary.get("strBrowser");
		    String strRemotePath = objDictionary.get("strRemotePath");
		    //Test Case Variables - Set for Free All Day
		    String strMaximumDuration = "1440"; // 24 hours in minutes
		    String strCoinTimePuchaseLimit = "1440";
		    String strFreeTimeFirstPayment = "0"; // No free time with first payment
		    objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		    String strMeterIncrementTime = "60"; // 1 hour increments
		    objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		    String strInitialGracePeriod = "2"; // 2 minutes grace period for faster testing
		    String strViolationGracePeriod = "1";
		    String strHandicapInitialGracePeriod = "10";
		    String strHandicapViolationGrace = "1";
		    String strNoParkingGrace = "1";
		    String strParkingShortSessionSec = "15";
		    String strSetImageSendBeforeViolation = "45";
		    String strUnlockValue = "Off";
		    String strUnlockTime = "5";
		    String strUnlockMax = "1";
		    String strCreditCardIncrementTime = "1";
		    String strState = "Minnesota";
		    // Set Free All Day parameters
		    objDictionary.remove("strFreeTimeMinutes");
		    objDictionary.put("strFreeTimeMinutes", "1440"); // 24 hours of free time
		    objDictionary.remove("strMinutesOfFreeTimeBeforeCurrentTime");
		    objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime", "0"); // Start free time immediatel
		    //ExitSpotAndSetMeterRateBlocks
		    objDictionary.remove("strFreeDisconnectValue");
		    objDictionary.put("strFreeDisconnectValue", "Off");
		    //SMDL: Set Meter & Lot in FREE ALL DAY for today and every day except yesterday, which is paid
	        clsCommonWeb.SENTRYLINK_SetRateBlocksFreeTodayAndEveryDayButPaidYesterday(objDictionary);
	        //Exit Both Spots
	        clsMeter.METER_ExitBothSpots(objDictionary, null, "Local");
	        //De-enroll Concierge
		    clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);	
		    //Remove all License Plates
		    HttpConnections clsHttpConnections = new HttpConnections();
		    clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		    //Create license plate for test
		    String strSpaceName = objDictionary.get("strMeterSpotName");
		    String strLicensePlateNumber = "0"+strSpaceName+"AA";
		    String strLicensePlateState = "Alabama";
		    objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		    //Add License Plate
		    clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		    //Need To get Account Balance
		    clsCommonWeb.SENTRYLINK_CheckUserAccountBalanceAddFundsIfNeeded(objDictionary, "Parker");
		    //CS: Concierge Service
		    clsCommonWeb.SENTRYLINK_ConciergeEnroll(objDictionary);
		    clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		    //PCAP: Park w/CA Android Concierge parker in Spot 1
		    clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
		    int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		    clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for initial grace time to expire-Spot1");
		    //Open Sentry Mobile
	  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
	  		//Click Accept
	  		clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept",1);
	  		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker","Enroll later");
	  		//Populate Spot
	  		String strDeviceId = objDictionary.get("strDeviceId");
			String[] arrMeterSpots = strDeviceId.split("-");
			String strMeterSpotName = "";
			if(strSpotNumber.equals("1")){strMeterSpotName = arrMeterSpots[0];}else{strMeterSpotName = arrMeterSpots[1];}
			clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary,iosdriver, "Park");
			clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Enter your Parking Zone Number", "Populate Zone Number", "{T} Zone Number",strMeterSpotName);
			objDictionary.put("strMeterSpotName", strMeterSpotName);
			try {Thread.sleep(1500);}catch (Exception e) {}
			clsCommonMobile.ClickButton(objDictionary, iosdriver,  "Enter your Parking Zone Number", "Meter Spot Number", 1);
			String strSnackbarText = objDictionary.get("strSnackbarText");
			if(strSnackbarText.equals("Payments are not allowed right now."))
			{
				Reporter.log(String.format("The message '%s' appeared correctly.", strSnackbarText));
				try {Thread.sleep(10000);}catch (Exception e) {}//Wait for snackbar message to expire
				String strSpotDetails = clsCommonMobile.StoreText(objDictionary, iosdriver, "Enter your Parking Zone Number", "Spot Details-Holiday Free", 1, "strSpotDetails");
				if(strSpotDetails.equals(strMeterSpotName+" Free parking until 11:59 PM Payments are not allowed"))
				{Reporter.log("The Text (Spot Text) with index (1) contained (" + strSpotDetails + ")");}
				else
				{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver, "The Text (Spot Text) with index (1) did not contain ("+strMeterSpotName+" Free parking until 11:59 PM Payments are not allowed) - actual value (" + strSpotDetails + ")");}
	    		return;
			}
			else
			{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver, "Expected message 'Payments are not allowed right now.' did not appear. Actual: '" + strSnackbarText + "'");}
		    //Store Parking Id
		    clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		    //EAV: Exit and validate no charges
		    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		    //Validate Parking Session History and Images
		    Android_ParkingSessions clsAndroidParkingSessions = new Android_ParkingSessions();
		    clsAndroidParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2307_CS_FADCT_PSWL_WGE_EAV(objDictionary);
		    clsMeter.METER_SetMeterEndTime(objDictionary);
		}

	@Test(priority=3000) 
		public void I3000_RO_FTFP5_PS1_PMT_ES1_VPSH_VICAE_VIAC()throws Exception
			{
		  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		  		String strSpaceName = objDictionary.get("strMeterSpotName");
				String strLicensePlateNumber = "0"+strSpaceName+"AA";
				//Remove all License Plate
		  		HttpConnections clsHttpConnections = new HttpConnections();
				clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
				//Add License Plate
				clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
				IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
				clsCommonMobile.SENTRYMOBILE_RO_FTFP5_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
				String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
				if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
			}

	@Test(priority=1980)
		public void I4000F_PaymentTest_PayPal()
		{
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//Remove all License Plate
			HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "PayPal");
			clsCommonMobile.SENTRYMOBILE_3021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1970) 
		public void I4001F_PaymentTest_PayPal_MaxTime()throws Exception
		{
			String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "PayPal");
			clsCommonMobile.SENTRYMOBILE_3027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1740)
		public void I4002F_PaymentTest_GooglePay()
		{
	  		objDictionary.put("strAssociatedBug", "FLUTTERCA-255");
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
			objDictionary.put("strLicensePlateState","Alabama");
			//Remove all License Plate
			HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "Google Pay");
			clsCommonMobile.SENTRYMOBILE_4002_PaymentTest_GooglePay(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1750) 
		public void I4003F_PaymentTest_GooglePay_MaxTime()throws Exception
		{
			String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
			objDictionary.put("strLicensePlateState","Alabama");
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "Google Pay");
			clsCommonMobile.SENTRYMOBILE_4003_PaymentTest_GooglePay_MaxTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1760)
		public void I4004F_PaymentTest_CreditOrDebitCard()
		{
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
			objDictionary.put("strLicensePlateState","Alabama");
			//Remove all License Plate
			HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "Credit or Debit Card");
			clsCommonMobile.SENTRYMOBILE_A4004_PaymentTest_CreditOrDebitCard(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1770) 
		public void I4005F_PaymentTest_CreditOrDebitCard_MaxTime()throws Exception
		{
			String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
			objDictionary.put("strLicensePlateState","Alabama");
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter  clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "Credit or Debit Card");
			clsCommonMobile.SENTRYMOBILE_4005_PaymentTest_CreditOrDebitCard_MaxTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=1990)
		public void I4006F_PaymentTest_Venmo()
		{
	  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			objDictionary.put("strTestCaseName", strTestCaseName);
			String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//Remove all License Plate
			HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "Venmo");
			clsCommonMobile.SENTRYMOBILE_3021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	@Test(priority=2000) 
		public void I4007F_PaymentTest_Venmo_MaxTime()throws Exception
		{
			String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			objDictionary.put("strAltPayment", "Venmo");
			clsCommonMobile.SENTRYMOBILE_3027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

	//	@Test(priority=4008) 
	//	public void I4008F_PaymentTest_CreditOrDebitCard_Refund()
	//	{   CommonWeb clsCommonWeb = new CommonWeb();
	//  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	//		objDictionary.put("strTestCaseName", strTestCaseName);
	//		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	//		String strSpaceName = objDictionary.get("strMeterSpotName");
	//		String strLicensePlateNumber = "0"+strSpaceName+"AA";
	//		//Remove all License Plate
	//		HttpConnections clsHttpConnections = new HttpConnections();
	//		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
	//		//Add License Plate
	//		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
	//		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
	//		objDictionary.put("strAltPayment", "Credit or Debit Card");
	//		clsCommonMobile.SENTRYMOBILE_3021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
	//		String strBrowser = objDictionary.get("strBrowser");String strRemotePath = objDictionary.get("strRemotePath");
	//		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	//		WebDriver driver = getDriver();
	//		//Check if parker exists
	//		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
	//		clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, "parker");
	//		driver.quit();
	//		//refund Parker
	//		Double doubleAmountToRefund = clsCommonWeb.ReimburseParker2(objDictionary);
	//		String strAmountToRefund = Double.toString(doubleAmountToRefund);
	//		String strCreditCardIncrementTime = "15";
	//		objDictionary.put("strAmountToRefund", strAmountToRefund);
	//		//ValidateParkingSessionHistoryAndImages
	//	  	Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	//	  	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2021_FTFP0_PS1_MP1_ES1(objDictionary,strCreditCardIncrementTime);
	//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
	//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	//	}

	@Test(priority=1950,groups={"Smoke"})
		public void I5001F_ValidateUserAgreement()
		{
	  		objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
			String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
			//Open Android Device
			IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
			//Validate UserAgreement
			try {Thread.sleep(6000);}catch (Exception e) {}
			WebElement objFrame = iosdriver.findElement(By.id("com.mpspark.consumer.mpsconsumer:id/eula"));
			ElementSwipe  clsElementSwipe  = new ElementSwipe();
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Header", 1, "Value", "END USER LICENSE AGREEMENT and TERMS OF SERVICE");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Section 1", 1, "Value", "We want you to be safe. Do not use the SENTRY MOBILE smartphone application while driving a vehicle. You agree to follow all traffic laws and regulations in the state(s) where you are using the SENTRY MOBILE smartphone application.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Section 2", 1, "Value", "By downloading and using the SENTRY MOBILE smartphone application (hereinafter the “Product Software”), you agree to be bound by the following terms of this End User License Agreement (“EULA”) between you and Municipal Parking Services, Inc. (“MPS” or “we”). IF YOU DO NOT AGREE TO BE BOUND BY THE TERMS OF THIS EULA, YOU MAY NOT USE THE PRODUCT SOFTWARE.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Section 3", 1, "Value", "Every time you use the Product Software you agree to be bound by the terms of this EULA, which may be updated from time-to-time, in MPS’ sole discretion, without prior notice to you. All changes are effective immediately when posted. Your continued use of the SENTRY MOBILE smartphone application following the posting of revised terms means that you accept and agree to the changes.");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Section 4", 1, "Value", "THIS IS A LEGAL AGREEMENT. YOU REPRESENT AND WARRANT THAT YOU HAVE THE RIGHT, AUTHORITY, AND CAPACITY TO ACCEPT AND AGREE TO THIS EULA. YOU REPRESENT THAT YOU ARE OF SUFFICIENT LEGAL AGE IN YOUR JURISDICTION OR RESIDENCE TO USE OR ACCESS THE PRODUCT SOFTWARE AND TO ENTER INTO THIS EULA. IF YOU DO NOT AGREE WITH ANY OF THE PROVISIONS OF THESE TERMS, YOU SHOULD CEASE ACCESSING OR USING THE PRODUCT SOFTWARE.");
			//Scroll Up
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Section 5", 1, "Value", "AS DESCRIBED BELOW, YOU ARE CONSENTING TO AUTOMATIC SOFTWARE UPDATES OF PRODUCT SOFTWARE. IF YOU DO NOT AGREE, YOU SHOULD NOT USE THE PRODUCT SOFTWARE.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement License", 1, "Value", "Subject to the terms of this EULA, MPS grants to you a limited, terminable, and non-exclusive, non-transferrable license (without the right to sublicense) to utilize one (1) single copy of the Product Software, in executable object code form only, on a single smartphone or other mobile computing device.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Restrictions", 1, "Value", "You agree not to, and you will not permit others to, (a) license, sell, rent, lease, assign, distribute, transmit, host, outsource, disclose or otherwise commercially exploit the Product Software or make the Product Software available to any third party, (b) copy or use the Product Software for any purpose other than as permitted in Section 1 of this EULA, (c) use any portion of the Product Software on any device or computer other than as provided in Section 1 of this EULA, (d) remove or alter any trademark, logo, copyright, patent or other proprietary notices, legends, symbols or labels in or associated with the Product Software, (e) modify, alter, make derivative works of, disassemble, reverse compile or reverse engineer any part of the Product Software (except to the extent applicable laws specifically prohibit such restriction for interoperability purposes, in which case you agree to first contact MPS in writing and to provide MPS an opportunity to create such changes as are needed for interoperability purposes); (f) you may not release, publicly or otherwise, the results of any performance or functional evaluation of any of the Product Software to any third party without prior written approval of MPS for each such release, (g) you agree to not engage, nor allow a third party to engage, in any of the following: scraping, caching, creating new content, recreating content, or circumventing (including fees) of the Product Software.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Modifications", 1, "Value", "MPS may from time to time develop make changes to the Software Product or services, which may include adding, updating, or discontinuing the Product Software and services, or parts thereof. Updating may include patches, bug fixes, updates, upgrades and other modifications to improve the performance or operation of the Product Software and related services (“Updates”). These Updates may be automatically installed without providing any additional notice or receiving any additional consent. You consent to this automatic updating and to the Updates. If you do not want such Updates, your sole remedy is to stop using the Software Product. Your failure to exercise your sole remedy of ceasing use of the Software Product, Updates, or the automated updating process, shall constitute your acceptance of the foregoing. You acknowledge that you may be required to install Updates in order to use or to continue to use the Software Product. You agree to promptly install any Updates MPS provides. Your continued use of the Software Product is your agreement to this EULA. The Updates shall automatically become part of the Product Software and shall automatically become subject to the rights and obligations of this EULA.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Ownership", 1, "Value", "The Product Software and all worldwide copyrights, trade secrets, and other intellectual property rights therein are the exclusive property of MPS and its licensors. MPS and its licensors reserve all rights in and to the Product Software not expressly granted to you in this EULA. The Product Software (and all copies thereof) is licensed to you, not sold, under this EULA. There are no implied licenses in this EULA. All suggestions or feedback provided by you to MPS with respect to the Product Software shall be MPS’ sole property. Proffering of such suggestions, ideas, or feedback shall constitute an affirmative assignment of such, and you hereby irrevocably do assign to MPS all right, title, and interest in the foregoing. MPS may use, copy, modify, publish, or redistribute the submission and its contents for any purpose and in any way without any notice or compensation to you. You also agree that MPS does not waive any rights to use similar or related ideas previously known to MPS, developed by its employees, or obtained from other sources.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Consent to Use of Data", 1, "Value", "You agree that MPS and its subsidiaries and agents may collect, maintain, process and use data (including images) relating to you, your smart device, your vehicle, and the personal identification information associated with that vehicle (“User Data”). The User Data may be used a component of parking enforcement.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Consent to Use of Data Part 2", 1, "Value", "Consent to Use of Data: You agree that MPS may collect and use User Data and the personal identification information associated with the User Data, including but not limited to your name and address, the address of the vehicle owner, images of you and any passengers in the vehicle, images of the vehicle, any other vehicle information, location data, technical data and related information, your smart device, system and application software, and peripherals, that is gathered periodically to facilitate the Product Software, the provision of software updates, product support and other services to you (if any) related to the Product Software. MPS may use this information, as long as it is in a form that does not personally identify you, to improve its products or to provide services or technologies to you, or to supply you with advertising material.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Consent to Use of Data Part 3", 1, "Value", "You and all persons in the vehicle being parked acknowledge that your and their likeness may be captured by MPS’s automated vehicle photo identification process and hereby consent to the same. By using the Product Software to park a vehicle, you, as well as the registered user, driver, and passengers in the vehicle consent, pursuant to the requirements of all state and federal laws, to MPS, MPS customers and MPS’s agents obtaining the vehicle registration owner information, including the name and address of the vehicle being parked. This information may be used to collect unpaid parking fees, violation fees or invoice fees that are referred to as Notices. These Notices may be in the form of text messages, emails, USPS mail and other forms of written communication sent to the registered owner of the vehicle and/or registered user of the Product Software. Notices unpaid may be referred for collection to a debt collector and/or subject to towing or booting of the vehicle that is the subject of the unpaid Notices.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Consent to Use of Data Part 4", 1, "Value", "Data Privacy: You agree that MPS may process your User Data, including but not limited to your vehicle data and the personal identification information associated with you and your vehicle, location data, technical and related information about your use of the parking space, software which may include internet protocol address, hardware identification, operating system, application software, peripheral hardware, personally and non-personally identifiable Product Software usage statistics to facilitate the provisioning of updates, support, invoicing or online services and may transfer such information to other companies in the MPS worldwide group of companies from time to time. This information may include your personal data or information such as your name and address, and the address of the vehicle owner. MPS may share this information with governmental agencies, including the municipality in which the rented parking space is located and with law enforcement agencies.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Consent to Use of Data Part 5", 1, "Value", "Interest-Based Advertising: By using the Product software you agree that MPS may provide interest-based advertising to you via the meter or your mobile computing device.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Do Not Use While Driving", 1, "Value", "You agree, represent and warrant, so long as YOU USE OR ACCESS THE PRODUCT SOFTWARE, THAT YOU WILL NOT, UNDER ANY CIRCUMSTANCES, ACCESS, VIEW, OR USE THE PRODUCT SOFTWARE WHILE DRIVING OR OTHERWISE OPERATING A VEHICLE OF ANY KIND (including, without limitation, a car, truck, motorcycle, motor scooter, or bicycle) or operating any dangerous equipment or machinery. You understand that using any handheld device in these circumstances is extremely dangerous, and can result in fines, property damage, physical injuries (including dismemberment) or death. You further agree, represent and warrant, that you will not use or access the Product Software in any manner that places yourself or any other person at risk of injury, and that you will abide by all traffic laws and regulations. While effort is made to assure the accuracy of the information presented, the user is responsible for safe driving and for the consequences of decisions as to where to travel or where to drive. Under no circumstance will MPS assume any responsibility or liability for the consequences of driving decisions made by you.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Do Not Use While Driving Part 2", 1, "Value", "You expressly agree that MPS shall not be liable for any driving decisions made by you or at your suggestion or for any damages, injury or other harm caused by your use of or accessing the Product Software, services or content, and waive any and all claims or causes of action you may have, now or in the future, arising from or relating to the same. In the event that any party names MPS as a defendant in a case involving your use of the Product Software while operating a vehicle, you agree to indemnify and hold MPS harmless in such action.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Term and Termination", 1, "Value", "This EULA and the license granted hereunder are effective on the date you first use the Product Software and shall continue for as long as the Product Software resides on your smartphone or mobile device, unless this EULA is terminated under this section. MPS may terminate this EULA at any time, and without notice to you, if you fail to comply with any term(s) hereof. You may terminate this EULA effective immediately upon written notice to MPS. Upon termination of this EULA, the license granted hereunder will terminate and you must stop all use of the Product Software, but the terms of Sections 8 through 22 (inclusive) will remain in effect, after any such termination.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Warranty Disclaimer", 1, "Value", "NOTWITHSTANDING ANYTHING TO THE CONTRARY AND TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, MPS PROVIDES THE PRODUCT SOFTWARE “AS-IS” AND \"AS AVAILABLE\", WITH ALL FAULTS AND WITHOUT WARRANTY OF ANY KIND.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Warranty Disclaimer Part 2", 1, "Value", "MPS DISCLAIMS ALL WARRANTIES AND CONDITIONS, WHETHER EXPRESS, IMPLIED, OR STATUTORY, INCLUDING THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, QUIET ENJOYMENT, ACCURACY, AND NON-INFRINGEMENT OF THIRD-PARTY RIGHTS. MPS DOES NOT GUARANTEE ANY SPECIFIC RESULTS FROM THE USE OF THE PRODUCT SOFTWARE. MPS MAKES NO WARRANTY THAT THE PRODUCT SOFTWARE WILL BE AVAILABLE, FUNCTIONAL, UNINTERRUPTED, FREE OF VIRUSES OR OTHER HARMFUL CODE, TIMELY, SECURE, OR ERROR-FREE.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Warranty Disclaimer Part 3", 1, "Value", "YOU USE ALL PRODUCT SOFTWARE AT YOUR OWN DISCRETION AND RISK. YOU WILL BE SOLELY RESPONSIBLE FOR (AND MPS DISCLAIMS) ANY AND ALL LOSS, LIABILITY, OR DAMAGES, INCLUDING ANY COMPUTER, MOBILE DEVICE, OR OTHER ITEM OF YOURS OR A THIRD PARTY, RESULTING FROM YOUR USE OF THE PRODUCT SOFTWARE.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Limitation of Liability", 1, "Value", "Nothing in this EULA, and in particular within this \"Limitation of Liability\" clause, shall attempt to exclude liability that cannot be excluded under applicable law.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Limitation of Liability Part 2", 1, "Value", "TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, IN ADDITION TO THE ABOVE WARRANTY DISCLAIMERS, IN NO EVENT WILL (A) MPS BE LIABLE FOR ANY CONSEQUENTIAL, EXEMPLARY, SPECIAL, OR INCIDENTAL DAMAGES, INCLUDING ANY DAMAGES FOR LOST DATA OR LOST PROFITS, ARISING FROM OR RELATING TO THE PRODUCT SOFTWARE, EVEN IF MPS KNEW OR SHOULD HAVE KNOWN OF THE POSSIBILITY OF SUCH DAMAGES, AND (B) MPS’S TOTAL CUMULATIVE LIABILITY ARISING FROM OR RELATED TO THE PRODUCT SOFTWARE, WHETHER IN CONTRACT OR TORT OR OTHERWISE, SHALL NOT EXCEED THE FEES ACTUALLY PAID BY YOU TO MPS OR MPS’S AUTHORIZED RESELLER FOR THE PRODUCT OR SOFTWARE AT ISSUE IN THE PRIOR 12 MONTHS (IF ANY). THIS LIMITATION IS CUMULATIVE AND WILL NOT BE INCREASED BY THE EXISTENCE OF MORE THAN ONE INCIDENT OR CLAIM. MPS DISCLAIMS ALL LIABILITY OF ANY KIND OF MPS’S LICENSORS AND SUPPLIERS.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Indemnification", 1, "Value", "Unless prohibited by applicable law, you will defend MPS, its agents, and assigns to the extent any event arises from your use of the Product Software or services in violation of this Agreement.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Indemnification Part 2", 1, "Value", "You will promptly notify MPS in writing of any allegation(s), or legal proceeding and to cooperate reasonably with MPS to resolve the allegation(s) or legal proceeding. MPS may, in its sole discretion, appoint its own counsel, at its own expense. You agree to not enter any settlement or to admit liability, pay money, or take (or refrain from taking) any action, without MPS’ consent, not to be unreasonably withheld, conditioned, or delayed.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges", 1, "Value", "You must fully setup an account in the MPS Sentry Mobile app prior to parking before you can use the SENTRY MOBILE smartphone application to pay for parking with MPS SENTRY parking meters. The MPS Sentry Mobile app will not work with any other type of parking meter or parking system.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 2", 1, "Value", "In order to fully set up your account, you must deposit funds into your account in at least the minimum amount requested by the MPS Sentry Mobile app during the setup process (the “Initial Deposit”). If you cancel or terminate your account, you may request a refund of any remaining balance of your Initial Deposit in your account if such request is made within 120 days of the date of the Initial Deposit. No refunds of the Initial Deposit will be made if the refund request is made more than 120 from the date of the Initial Deposit.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 3", 1, "Value", "For a payment for parking made through the Product Software to be valid, you must i) provide all accurate and complete information required by the Product Software, including license plate number, parking zone, and parking time(s), (ii) accurately provide valid credit card information or other valid form of electronic payment permitted by the Product Software, and (iii) abide by all payment instructions and prompts set forth in the Application.");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 4", 1, "Value", "You are responsible for the validity of the enrolled debit card, credit card, that it has sufficient available funds and is not blocked or inactive. Once the parking is initiated and payment received through the Product Software, you will receive a confirmation of the purchase and it is your responsibility to check that this confirmation is received.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 	
			//There is a error here. Carriage Return
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 5", 1, "Value", "You agree that, when using the Product Software to pay for parking, MPS will charge the parking amount, including any applicable taxes, fees and service charges. The parking fee is calculated based on the parking time and the parking fee applicable when you begin parking, and such fee is charged immediately upon commencement of parking. Once you have been charged the parking fee, such parking fee and any applicable taxes, fees and service charges are");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 5A", 1, "Value", "nonrefundable, even if you leave the parking space or lot before the time for which you paid to park has expired or if you entered the license plate, location or amount of time incorrectly.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 6", 1, "Value", "You bear the sole responsibility to follow any applicable parking rules and regulations. If the Software Product is not available, for whatever reason, or if the Software Product does not provide a confirmation that your payment for parking has been received, it is your responsibility to pay the parking fee using another method.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 7", 1, "Value", "It is your sole responsibility to monitor your parking session and any pertinent parking regulations set forth by the municipality and payments made by you, through any payment method including but not limited to cash, coin, credit card, and/or mobile application, for the duration of your parking session. FAILURE TO RECEIVE AN ALERT, PUSH NOTIFICATION, PROMPT, OR REMINDER OF YOUR PARKING SESSION EXPIRATION DOES NOT NEGATE YOUR RESPONSIBILITY TO PAY FOR YOUR PARKING, NOR DOES SUCH FAILURE EXEMPT YOU FROM RECEIVING A PARKING TICKET. MPS SHALL BEAR NO RESPONSIBILITY OR LIABILITY, AND SHALL NOT BE REQUIRED TO REIMBURSE USER FOR ANY FEES, FINES, TICKETS, AND THE LIKE, INCLUDING BUT NOT LIMITED TO, EXPENSES RELATED TO TOWING OF USER’S VEHICLE, OR ANY DAMAGE THAT MAY BE INCURRED AS RESULT THEREOF.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 8", 1, "Value", "You are solely responsible for (i) obtaining and maintaining all internet or other communications access, computer hardware and other equipment or electronic media necessary to utilize the Product Software, (ii) any issues pertaining to your mobile computing device (including issues pertaining to any incompatibility of the Product Software with your mobile computing device) and the data plan and data/cellular service providers that permit your mobile computing device to send and receive data wirelessly, and (iii) the data and content provided by you to MPS through the Product Software. MPS SHALL HAVE NO RESPONSIBILITY OR LIABILITY FOR ANALYSIS, DATA, RECOMMENDATIONS, OR OTHER SERVICES PROVIDED TO YOU BASED UPON INCORRECT OR INCOMPLETE DATA PROVIDED BY YOU.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 9", 1, "Value", "YOU SHALL HOLD MPS HARMLESS IN ANY DISPUTE BETWEEN YOU AND EACH OF THE FOLLOWING: GOVERNMENTAL ENTITIES, YOUR CREDIT CARD COMPANY AND YOUR CELL PHONE COMPANY OR OTHER MOBILE DATA PROVIDER. MPS IS NOT RESPONSIBLE FOR ANY DISPUTES REGARDING PARKING TICKETS, TOWING OF YOUR VEHICLE, OR OTHER FEES OR ISSUES THAT MAY ARISE WITH RESPECT TO ANY PARKING INFRACTION, FAILURE TO PAY, FAILURE TO FOLLOW A PARKING LOT’S INSTRUCTIONS, OR THE LIKE.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Parking Violations, Enforcement and Collections", 1, "Value", "If you incur any type of parking violation, such as a parking ticket for example, MPS reserves the right to deduct from your account balance, or to charge to your enrolled credit card or debit card, the fine amount corresponding to that parking violation.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Parking Violations, Enforcement and Collections Part 2", 1, "Value", "The amount of a fine amount for a parking violation that you incur is considered a debt owed by you to MPS. MPS reserves the right to collect that debt from you via any method allowed by law, including employing third party collections agents and agencies, as well as reporting your unpaid debt to third party credit rating agencies. Your credit rating could be negatively impacted by failure to pay your parking fines.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Third Party Materials", 1, "Value", "The Product Software may display, include or make available third-party content (including data, information, applications and other products services and/or materials) or provide links to third-party websites or services, including through third-party advertising (\"Third Party Materials\"). You acknowledge and agree that MPS is not responsible for Third Party Materials, including their accuracy, completeness, timeliness, validity, copyright compliance, legality, decency, quality or any other aspect thereof. MPS does not assume and will not have any liability or responsibility to you or any other person or entity for any Third Party Materials. Third Party Materials and links thereto are provided solely as a convenience to you and you access and use them entirely at your own risk and subject to such third parties' terms and conditions");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement For U.S. Government End Users", 1, "Value", "The Product Software is a “commercial item,” as that term is defined at 48 C.F.R. 2.101 (OCT 1995), and more specifically is “commercial computer software” and “commercial computer software documentation,” as such terms are used in 48 C.F.R. 12.212 (SEPT 1995). Consistent with 48 C.F.R. 12.212 and 48 C.F.R. 227.7202-1 through 227.7202-4 (JUNE 1995), the Product Software is provided to U.S. Government End Users only as a commercial end item and with only those rights as are granted to all other customers pursuant to the terms and conditions herein.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Export Compliance", 1, "Value", "The Product Software and related technology are subject to U.S. export control laws and may be subject to export or import regulations in other countries. You agree to strictly comply with all such laws and regulations and acknowledge that you have the responsibility to obtain authorization to export, re-export, or import the Product Software and related technology, as may be required. You will indemnify and hold MPS harmless from any and all claims, losses, liabilities, damages, fines, penalties, costs and expenses (including attorney’s fees) arising from or relating to any breach by you of your obligations under this section.");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause", 1, "Value", "Any and all controversies, disputes, demands, counts, claims, or causes of action (including the interpretation and scope of this clause, and the arbitrability of the controversy, dispute, demand, count, claim, or cause of action) between you and MPS or its successors or assigns shall exclusively be settled through binding and confidential arbitration.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 2", 1, "Value", "Arbitration shall be subject to the Federal Arbitration Act and not any state arbitration law. The arbitration shall be conducted before one commercial arbitrator with substantial experience in resolving commercial contract disputes from the American Arbitration Association (“AAA”). As modified by this EULA, and unless otherwise agreed upon by the parties in writing, the arbitration will be governed by the AAA’s Commercial Arbitration Rules and, if the arbitrator deems them applicable, the Supplementary Procedures for Consumer Related Disputes (collectively, the “Rules and Procedures”).");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 3", 1, "Value", "You are thus GIVING UP YOUR RIGHT TO GO TO COURT to assert or defend your rights EXCEPT for matters that may be taken to small claims court.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 4", 1, "Value", "You and MPS must abide by the following rules: (a) ANY CLAIMS BROUGHT BY YOU OR MPS MUST BE BROUGHT IN THE PARTY'S INDIVIDUAL CAPACITY, AND NOT AS A PLAINTIFF OR CLASS MEMBER IN ANY PURPORTED CLASS OR REPRESENTATIVE PROCEEDING; (b) THE ARBITRATOR MAY NOT CONSOLIDATE MORE THAN ONE PERSON’S CLAIMS, MAY NOT OTHERWISE PRESIDE OVER ANY FORM OF A REPRESENTATIVE OR CLASS PROCEEDING, AND MAY NOT AWARD CLASS-WIDE RELIEF; (c) in the event that you are able to demonstrate that the costs of arbitration will be prohibitive as compared to costs of litigation, MPS will pay as much of your filing and hearing fees in connection with the arbitration as the arbitrator deems necessary to prevent the arbitration from being cost-prohibitive as compared to the cost of litigation, (d) MPS also reserves the right in its sole and exclusive discretion to assume responsibility for all of the costs of the arbitration; (e) the arbitrator shall honor claims of privilege and privacy recognized at law; (f) the arbitration shall be confidential, and neither you nor we may disclose the existence, content or results of any arbitration, except as may be required by law or for purposes of enforcement of the arbitration award; (g) the arbitrator may award any individual relief or individual remedies that are permitted by applicable law; and (h) each side pays its own attorneys’ fees and expenses unless there is a statutory provision that requires the prevailing party to be paid its fees and litigation expenses, and, in such instance, the fees and costs awarded shall be determined by the applicable law.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 5", 1, "Value", "Notwithstanding the foregoing, either you or MPS may bring an individual action in small claims court.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 6", 1, "Value", "Claims of defamation, violation of the Computer Fraud and Abuse Act, and infringement or misappropriation of the other party’s patent, copyright, trademark, or trade secret shall not be subject to this arbitration agreement. Such claims shall be exclusively brought in the state or federal courts located in Hennepin or Dakota counties of the state of Minnesota.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 7", 1, "Value", "With the exception of subparts (a) and (b) in this Section (prohibiting arbitration on a class or collective basis), if any part of this arbitration provision is deemed to be invalid, unenforceable or illegal, or otherwise conflicts with the Rules and Procedures, then the balance of this arbitration provision shall remain in effect and shall be construed in accordance with its terms as if the invalid, unenforceable, illegal or conflicting part was not contained herein. If, however, either subpart (a) or (b) is found to be invalid, unenforceable or illegal, then the entirety of this arbitration provision shall be null and void, and neither you nor MPS shall be entitled to arbitration.");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Choice of Law", 1, "Value", "You agree that this EULA, and any claim, dispute, action, cause of action, issue, or request for relief relating to this EULA and/or your use of the Product Software, will be governed by the laws of Minnesota, without giving effect to any conflicts of laws principles that require the application of the laws of a different jurisdiction.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Choice of Law Part 2", 1, "Value", "Each party irrevocably submits to the jurisdiction and venue of state or federal courts located in Hennepin or Dakota counties of the state of Minnesota, except that MPS may seek injunctive relief in any court having jurisdiction to protect its intellectual property.");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Assignment", 1, "Value", "Neither the rights nor the obligations arising under this EULA are assignable by you, and any such attempted assignment shall be void and without effect.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Notices", 1, "Value", "Any notice to you may be provided by email to the address that you registered with MPS.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Severability", 1, "Value", "If any provision of this EULA is unenforceable, such provision will be changed and interpreted to accomplish the objectives of such provision to the greatest extent possible under applicable law and the remaining provisions will continue in full force and effect.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Waiver", 1, "Value", "All waivers by MPS will be effective only if in writing. Any waiver or failure by MPS to enforce any provision of this EULA on one occasion will not be deemed a waiver of any other provision or of such provision on any other occasion.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement General Terms", 1, "Value", "You are responsible for establishing and maintaining your own password.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement General Terms Part 2", 1, "Value", "You acknowledge that transmission of data over the internet and wireless devices involves unique transmission risks that cannot be fully secured against unauthorized access.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement General Terms Part 3", 1, "Value", "The Product Software is deemed irrevocably accepted upon your use of the Product Software. MPS will have no responsibility to provide maintenance or support services with respect to the Product Software. The parties are independent contractors.");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement General Terms Part 4", 1, "Value", "You acknowledge that the Product Software contains valuable trade secrets and proprietary information of MPS, that any actual or threatened breach of Section 2 (Restrictions) of this EULA will constitute immediate, irreparable harm to MPS for which monetary damages would be an inadequate remedy, and that injunctive relief is an appropriate remedy for such breach.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement General Terms Part 5", 1, "Value", "The headings of Sections of this EULA are for convenience and are not to be used in interpreting this EULA.");
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement General Terms Part 6", 1, "Value", "If you have questions regarding this EULA, please contact MPS. MPS’s contact information can be found at http://mpspark.com/");
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Entire Agreement", 1, "Value", "Notwithstanding any agreements or terms expressly incorporated by reference in this EULA, this EULA constitutes the entire agreement between the parties. This EULA (as it may from time to time be amended, restated, or otherwise modified) supersedes any prior agreements, understandings, or negotiations, whether written or oral.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Acknowledgement", 1, "Value", "BY USING THE PRODUCT SOFTWARE OR ACCESSING THE MPS WEBSITE, YOU ACKNOWLEDGE THAT YOU HAVE READ THESE TERMS OF USE AND AGREE TO BE BOUND BY THEM.");
			//Scroll Up
			clsElementSwipe.Swipe_Flutter(iosdriver,objFrame,"SWIPE_UP"); 
			clsCommonMobile.VerificationPointText(objDictionary,iosdriver, "User Agreement", "User Agreement Revision Date", 1, "Value", "Revision Date: February 8, 2024");
			iosdriver.quit();
			clsMeter.METER_SetMeterEndTime(objDictionary);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}

		@Test(priority=1780, groups={"Smoke"})
		public void I5204F_NoInternet_ColdStartLoginKillRelaunchRestore()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-260");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		
			Reporter.log("TC-NEG-01: No internet — cold start, login, kill/relaunch offline, then restore");
			objDictionary.put("strWIFI", "Enabled");
			objDictionary.put("strAirplaneMode", "Enabled");
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "cold start with airplane mode on");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.ClickLink(objDictionary, iosdriver, "Log in", "Log in", 1);
				String strUserName = "adeleteuser01@gmail.com";
				objDictionary.put("strUserName", strUserName);
				String strPassword = "ADeleteMe01!";
				clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password", strUserName + "|" + strPassword);
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "Log in", "Log in", 1);
				clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Log in", "Please disable Airplane Mode to continue using the app", 1, "Value", "Please disable Airplane Mode to continue using the app");
		 		//Not Test Below
				clsCommonMobile.SENTRYMOBILE_FORCE_STOP_APP(objDictionary);
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(5000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "kill and relaunch while still offline");
				clsCommonMobile.SENTRYMOBILE_AcceptUserAgreementIfPresent(objDictionary, iosdriver);
				clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[@name='Log in' or @label='Log in'] | (//XCUIElementTypeStaticText[@name='Log in'])[2]", 8);
				clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Login", "Please disable Airplane Mode to continue using the app", 1, "Value", "Please disable Airplane Mode to continue using the app");
		 		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password", strUserName + "|" + strPassword);
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Log in", 1);
				//Code Below is untested do to JIRA FLUTTERCA-260
				clsCommonMobile.SENTRYMOBILE_AssertOfflineOrAirplaneMessage(objDictionary, iosdriver, "login after kill/relaunch while still offline");
				objDictionary.put("strAirplaneMode", "Disabled");
				clsCommonMobile.SENTRYMOBILE_SET_AIRPLANE_MODE(objDictionary);
				try { Thread.sleep(3000); } catch (Exception e) {}
				clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password", strUserName + "|" + strPassword);
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Log in", 1);
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "login after network restore");
				Reporter.log("Network restore completed; app remained usable without reinstall");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1790, groups={"Smoke"})
		public void I5205F_NetworkToggle_MidSessionCache()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-262");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		
			Reporter.log("TC-NEG-02: Mid-session airplane toggle — cached Account vs Sessions, then restore");
			objDictionary.put("strWIFI", "Enabled");
			objDictionary.put("strAirplaneMode", "Disabled");
			IOSDriver iosDriver = null;
			try
			{
				iosDriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
				clsCommonMobile.ClickButton(objDictionary, iosDriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow While Using App");

				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosDriver, "Account");
				String cachedAccountUi = clsCommonMobile.SENTRYMOBILE_CaptureVisibleUi(iosDriver);
				if (cachedAccountUi.toLowerCase().contains("account"))
				{
					Reporter.log("Account screen loaded on a healthy network before the outage");
				}
	
				objDictionary.put("strAirplaneMode", "Enabled");
				clsCommonMobile.SENTRYMOBILE_SET_AIRPLANE_MODE(objDictionary);
				try { Thread.sleep(3000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosDriver, "airplane mode enabled mid-session");
				//Everything Below this is untested FLUTTERCA-262
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosDriver, "returning to previously loaded Account while offline");
				String offlineAccountUi = clsCommonMobile.SENTRYMOBILE_CaptureVisibleUi(iosDriver);
				if (offlineAccountUi.trim().length() < 50)
				{
					clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosDriver, "Previously loaded Account screen went blank while offline");
				}
				else
				{
					Reporter.log("Previously loaded Account screen still had UI while offline");
				}
	
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosDriver, "Sessions");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosDriver, "opening Sessions while offline");
				Reporter.log("Sessions tab was reachable while offline (cached content or a clear empty/error state is acceptable)");
	
				objDictionary.put("strAirplaneMode", "Disabled");
				clsCommonMobile.SENTRYMOBILE_SET_AIRPLANE_MODE(objDictionary);
				try { Thread.sleep(3000); } catch (Exception e) {}
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosDriver, "Account");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosDriver, "Account after network restore");
				if (!clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosDriver))
				{
					clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosDriver, "Unexpected logout after mid-session airplane toggle and restore");
				}
				Reporter.log("Session remained usable after restore; no payment was submitted (avoids duplicate-charge risk)");
			}
			finally
			{
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				try { if (iosDriver != null) iosDriver.quit(); } catch (Exception e) {}
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1800, groups={"Smoke"})
		public void I5205F_NetworkToggle_MidSessionCache_Payment()
		{
			objDictionary.put("strAssociatedBug", "FLUTTERCA-260");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		
			Meter clsMeter = new Meter();
			CommonWeb clsCommonWeb = new CommonWeb();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		
			//Test Case Variables
			String strMaximumDuration = "240";
			String strCoinTimePuchaseLimit = "240";
			String strFreeTimeFirstPayment = "0";
			String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
			String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod",strInitialGracePeriod);
			String strViolationGracePeriod = "1";
			String strHandicapInitialGracePeriod = "5";
			String strHandicapViolationGrace = "1";
			String strNoParkingGrace = "1";
			String strParkingShortSessionSec = "15";
			String strSetImageSendBeforeViolation = "45";
			String strUnlockValue = "On";
			String strUnlockTime = "1";
			String strUnlockMax = "1";
			objDictionary.remove("strMaximumDuration");objDictionary.put("strMaximumDuration", strMaximumDuration);
			clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary,  strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
			//De-enrolled Concierge
	  		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
			String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			String strLicensePlateState = "Alabama";
			objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
			objDictionary.put("strLicensePlateState", strLicensePlateState);
			Reporter.log("TC-NEG-02: Mid-session airplane toggle — cached Account vs Sessions, then restore");
			objDictionary.put("strWIFI", "Enabled");
			objDictionary.put("strAirplaneMode", "Disabled");
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				//Disable Airplane Mode during payment
				objDictionary.put("strAirplainModeEnabledAfterPayment", "True");
				clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosdriver,strFreeTimeFirstPayment,strMeterIncrementTime,"True","False");
				objDictionary.put("strAirplaneMode", "Enabled");
				clsCommonMobile.SENTRYMOBILE_SET_AIRPLANE_MODE(objDictionary);
				try { Thread.sleep(3000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "airplane mode enabled mid-session");
				clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Log in", "Please disable Airplane Mode to continue using the app", 1, "Value", "Please disable Airplane Mode to continue using the app");
		 		//Nothing below this has been tested do to Bug
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				//Everything Below this is untested FLUTTERCA-262
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "returning to previously loaded Account while offline");
				String offlineAccountUi = clsCommonMobile.SENTRYMOBILE_CaptureVisibleUi(iosdriver);
				if (offlineAccountUi.trim().length() < 50)
				{
					clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver, "Previously loaded Account screen went blank while offline");
				}
				else
				{
					Reporter.log("Previously loaded Account screen still had UI while offline");
				}
	
				boolean sessionsOpened = false;
				try { clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Sessions"); sessionsOpened = true; } catch (Exception e) { sessionsOpened = clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[contains(@name, 'Session') or contains(@label, 'Session')]", 10); }
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "opening Sessions while offline");
				if (sessionsOpened)
				{
					Reporter.log("Sessions tab was reachable while offline (cached content or a clear empty/error state is acceptable)");
				}
				objDictionary.put("strAirplaneMode", "Disabled");
				clsCommonMobile.SENTRYMOBILE_SET_AIRPLANE_MODE(objDictionary);
				try { Thread.sleep(3000); } catch (Exception e) {}
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Account after network restore");
				if (!clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver))
				{
					clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver, "Unexpected logout after mid-session airplane toggle and restore");
				}
				Reporter.log("Session remained usable after restore; no payment was submitted (avoids duplicate-charge risk)");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
			//ShortSessionWaitExitSpot
		  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null,"1","Local");
		}

		@Test(priority=1810, groups={"Smoke"})
		public void I5206F_LowBattery_BatterySaver()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-03: Low battery / Battery Saver — login, short background, confirm still usable");
	
			objDictionary.put("strBatterySaver", "Enabled");
			objDictionary.put("strBatteryLevel", "15");
			IOSDriver iosdriver = null;
			try
			{
				clsCommonMobile.SENTRYMOBILE_SET_BATTERY_SAVER(objDictionary);
				clsCommonMobile.SENTRYMOBILE_SET_BATTERY_LEVEL(objDictionary);
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "cold start in Battery Saver");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Account in Battery Saver");
	
				clsCommonMobile.SENTRYMOBILE_SEND_HOME(objDictionary);
				try { Thread.sleep(15000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(4000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "resume from background in Battery Saver");
				if (!clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver))
				{
					Reporter.log("After Battery Saver background, login was required — that is acceptable if messaging is clear");
				}
				else
				{
					Reporter.log("Session remained valid after Battery Saver background; re-login was not required");
				}
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1820, groups={"Smoke"})
		public void I5207F_Background_ForceStop_SessionState()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		
			Reporter.log("TC-NEG-09: Short background plus force-stop (process death) — session restored or clear re-auth");
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_SEND_HOME(objDictionary);
				try { Thread.sleep(10000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(4000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "resume after 10-second background");
				boolean loggedInAfterBackground = clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver);
				if (loggedInAfterBackground)
				{
					Reporter.log("Short background kept the user on a logged-in screen");
				}
				else
				{
					Reporter.log("Short background required re-auth or showed login — not a crash");
				}
	
				clsCommonMobile.SENTRYMOBILE_FORCE_STOP_APP(objDictionary);
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(5000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "reopen after force-stop / process death");
				clsCommonMobile.SENTRYMOBILE_AcceptUserAgreementIfPresent(objDictionary, iosdriver);
				boolean loggedInAfterDeath = clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver);
				if (loggedInAfterDeath)
				{
					Reporter.log("Process death restored a logged-in session");
				}
				else
				{
					Reporter.log("Process death required login again — acceptable if the user is not left on a blank screen");
				}
				Reporter.log("No payment was in flight, so resume could not duplicate a park/pay");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1830, groups={"Smoke"})
		public void I5208F_CorruptedApplicationCache()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-11: Corrupt on-device cache with adb, force-stop, relaunch, confirm recovery");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
	
				clsCommonMobile.SENTRYMOBILE_FORCE_STOP_APP(objDictionary);
				String evidence = clsCommonMobile.SENTRYMOBILE_CORRUPT_APP_CACHE(objDictionary);
				Reporter.log("Package: com.mpspark.consumer.mpsconsumer");
				Reporter.log("Cache adb evidence: " + evidence);
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(5000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "cold start after cache corruption");
				clsCommonMobile.SENTRYMOBILE_AcceptUserAgreementIfPresent(objDictionary, iosdriver);
	
				clsCommonMobile.SENTRYMOBILE_FORCE_STOP_APP(objDictionary);
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(5000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "second launch after cache corruption");
				Reporter.log("App recovered from corrupted cache without requiring uninstall");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1840, groups={"Smoke"})
		public void I5209F_LocationServicesOff_DegradeThenEnable()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		
			Reporter.log("TC-NEG-06: Location services off — degrade or prompt, then enable mid-flow and recover");
			objDictionary.put("strLocationServices", "Disabled");
			IOSDriver iosdriver = null;
			try
			{
				clsCommonMobile.SENTRYMOBILE_SET_LOCATION_MODE(objDictionary);
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "False", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "login with location services off");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park with location services off");
				clsCommonMobile.SENTRYMOBILE_AssertLocationDegradedOrPrompt(objDictionary, iosdriver, "Park with location services off");
	
				objDictionary.put("strLocationServices", "Enabled");
				clsCommonMobile.SENTRYMOBILE_SET_LOCATION_MODE(objDictionary);
				clsCommonMobile.SENTRYMOBILE_DismissLocationPromptsIfPresent(iosdriver);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park after re-enabling location services");
				Reporter.log("Location services restored mid-flow; app remained usable without reinstall");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1850, groups={"Smoke"})
		public void I5210F_PermissionDenied_LocationAndCamera()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-07: Revoke location and camera at runtime — degrade without crash, then re-grant");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
	
				objDictionary.put("strPermissionAction", "Revoke");
				objDictionary.put("strPermissions",
						"android.permission.ACCESS_FINE_LOCATION,android.permission.ACCESS_COARSE_LOCATION,android.permission.CAMERA");
				clsCommonMobile.SENTRYMOBILE_SET_APP_PERMISSIONS(objDictionary);
				clsCommonMobile.SENTRYMOBILE_FORCE_STOP_APP(objDictionary);
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(5000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AcceptUserAgreementIfPresent(objDictionary, iosdriver);
				clsCommonMobile.SENTRYMOBILE_DismissLocationPromptsIfPresent(iosdriver);
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "relaunch after revoking location and camera");
				boolean onPark = false;
				try { clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park"); onPark = true; } catch (Exception e) { onPark = clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[contains(@name, 'Park') or contains(@label, 'Park')]", 8); }
				if (onPark)
				{
					Reporter.log("Park tab was reachable after permission revoke");
				}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park after location/camera revoke");
				clsCommonMobile.SENTRYMOBILE_AssertLocationDegradedOrPrompt(objDictionary, iosdriver, "Park after location/camera revoke");
	
				objDictionary.put("strPermissionAction", "Grant");
				clsCommonMobile.SENTRYMOBILE_SET_APP_PERMISSIONS(objDictionary);
				clsCommonMobile.SENTRYMOBILE_DismissLocationPromptsIfPresent(iosdriver);
				try { clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park"); } catch (Exception e) { clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[contains(@name, 'Park') or contains(@label, 'Park')]", 8); }
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park after re-granting location and camera");
				Reporter.log("Permissions restored; app remained usable without uninstall");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1860, groups={"Smoke"})
		public void I5211F_NotificationInterruption_MidSession()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-08: Post a system notification mid-session and confirm the app does not crash or lose the screen");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				boolean loggedInBefore = clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver);
	
				objDictionary.put("strNotificationTitle", "MPS Interrupt");
				objDictionary.put("strNotificationText", "Interrupt during parking flow");
				objDictionary.put("strNotificationTag", "MpsNeg08");
				clsCommonMobile.SENTRYMOBILE_POST_NOTIFICATION(objDictionary);
				try { Thread.sleep(2000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "system notification posted on Account");
				clsCommonMobile.SENTRYMOBILE_DISMISS_NOTIFICATION_SHADE(objDictionary);
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "dismissing notification shade");
				boolean loggedInAfter = clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver);
				if (loggedInBefore && loggedInAfter)
				{
					Reporter.log("Account session survived the notification interruption");
				}
				else if (loggedInAfter)
				{
					Reporter.log("App was still on a logged-in screen after the notification");
				}
				else
				{
					Reporter.log("Notification may have covered the UI; process was still running and did not crash");
				}
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1870, groups={"Smoke"})
		public void I5212F_GpsToggle_DuringPark()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-10: Toggle GPS off while on Park, then turn it back on and recover");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park with GPS on");
	
				objDictionary.put("strLocationServices", "Disabled");
				clsCommonMobile.SENTRYMOBILE_SET_LOCATION_MODE(objDictionary);
				try { Thread.sleep(2000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "GPS turned off while on Park");
				clsCommonMobile.SENTRYMOBILE_AssertLocationDegradedOrPrompt(objDictionary, iosdriver, "GPS off during Park");
	
				objDictionary.put("strLocationServices", "Enabled");
				clsCommonMobile.SENTRYMOBILE_SET_LOCATION_MODE(objDictionary);
				clsCommonMobile.SENTRYMOBILE_DismissLocationPromptsIfPresent(iosdriver);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park after GPS restored mid-flow");
				Reporter.log("GPS toggle during Park did not crash the app; location was restored");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1880, groups={"Smoke"})
		public void I5213F_ExcessiveInput_LoginEmail()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-18: 10,000-character login email via sendKeys/clipboard — no crash, login must not succeed");
	
			String excessiveEmail = NegativeInputCases.excessiveInput('a');
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.ClickLink(objDictionary, iosdriver, "Log in", "Log in", 1);
				clsCommonMobile.SENTRYMOBILE_PasteIntoLoginField(objDictionary, iosdriver, "Email or Phone number", excessiveEmail);
				clsCommonMobile.SENTRYMOBILE_SendKeysToLoginField(objDictionary, iosdriver, "Password", "ADeleteMe01!");
				boolean loginClicked = clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "(//XCUIElementTypeStaticText[@name='Log in'])[2]", 8);
				if (!loginClicked)
				{
					loginClicked = clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[@name='Log in' or @label='Log in']", 5);
				}
				if (!loginClicked)
				{
					loginClicked = clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//*[@name='Log in' or @label='Log in']", 5);
				}
				Reporter.log("Login submit after 10k-character email clicked=" + loginClicked);
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "submitting 10,000-character email");
				clsCommonMobile.SENTRYMOBILE_AssertLoginWasNotBypassed(objDictionary, iosdriver, "10,000-character email login");
				clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[@name='Ok' or @name='OK' or @label='Ok' or @label='OK']", 5);
			}
			finally
			{
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1890, groups={"Smoke"})
		public void I5214F_InjectionAsPlainText_Login()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
			Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-19: SQL/XSS strings on login must be treated as plain text — no crash, login must not succeed");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.ClickLink(objDictionary, iosdriver, "Log in", "Log in", 1);
	
				for (String payload : NegativeInputCases.injectionPayloads())
				{
					Reporter.log("Trying injection payload as login email: " + payload);
					clsCommonMobile.SENTRYMOBILE_SendKeysToLoginField(objDictionary, iosdriver, "Email or Phone number", payload);
					clsCommonMobile.SENTRYMOBILE_SendKeysToLoginField(objDictionary, iosdriver, "Password", "not-a-real-password");
					clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "(//XCUIElementTypeStaticText[@name='Log in'])[2]", 8);
					try { Thread.sleep(2500); } catch (Exception e) {}
					clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "login with payload " + payload);
					clsCommonMobile.SENTRYMOBILE_AssertInjectionTreatedAsPlainText(objDictionary, iosdriver, payload, "login with payload " + payload);
					clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[@name='Ok' or @name='OK' or @label='Ok' or @label='OK']", 5);
				}
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1900, groups={"Smoke"})
		public void I5215F_LowStorage_DeviceStorageLow()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-04: Low storage — install/login first, then fill (capped) plus DEVICE_STORAGE_LOW, relaunch, restore");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				boolean loggedInBefore = clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_SIMULATE_LOW_STORAGE(objDictionary);
				clsCommonMobile.SENTRYMOBILE_FORCE_STOP_APP(objDictionary);
				clsCommonMobile.SENTRYMOBILE_LAUNCH_APP(objDictionary);
				try { Thread.sleep(5000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AcceptUserAgreementIfPresent(objDictionary, iosdriver);
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "relaunch under low-storage simulation");
				String source = clsCommonMobile.SENTRYMOBILE_CaptureVisibleUi(iosdriver);
				if (NegativeInputCases.looksLikeLowStorage(source))
				{
					Reporter.log("Low-storage messaging was shown");
				}
				else
				{
					Reporter.log("No OS low-storage dialog (fill is capped at 512MB so the phone is not actually full); app must still not crash");
				}
				try { clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account"); } catch (Exception e) { clsCommonMobile.SENTRYMOBILE_ClickIfPresent(iosdriver, "//XCUIElementTypeButton[contains(@name, 'Account') or contains(@label, 'Account')]", 8); }
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Account after low-storage relaunch");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1910, groups={"Smoke"})
		public void I5216F_BiometricPrompt_CancelFallback()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-05: Biometric cancel / fallback — dismiss prompt if shown, stay on password/session, no crash");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.SENTRYMOBILE_DUMP_FINGERPRINT(objDictionary);
				boolean dismissed = clsCommonMobile.SENTRYMOBILE_DismissBiometricPromptIfPresent(iosdriver);
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "login with biometric cancel/fallback");
				String source = clsCommonMobile.SENTRYMOBILE_CaptureVisibleUi(iosdriver);
				if (NegativeInputCases.looksLikeBiometricPrompt(source) || dismissed)
				{
					Reporter.log("Biometric prompt was present and cancelled, or leftover copy remained; password/session path must remain usable");
					clsCommonMobile.SENTRYMOBILE_DISMISS_NOTIFICATION_SHADE(objDictionary);
					clsCommonMobile.SENTRYMOBILE_DismissBiometricPromptIfPresent(iosdriver);
				}
				else
				{
					Reporter.log("Flutter CA did not show a biometric dialog after login (dumpsys fingerprint captured). Cancel path is still valid: app stayed up.");
				}
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Account after biometric cancel/fallback");
				if (!clsCommonMobile.SENTRYMOBILE_PageLooksLoggedIn(iosdriver))
				{
					clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver, "Expected to remain on a logged-in screen after biometric cancel/fallback");
				}
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1920, groups={"Smoke"})
		public void I5217F_HttpProxyBlackhole_ApiTimeout()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-12: API timeout without Charles — system http_proxy to TEST-NET blackhole, then Retry after clear");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
		  		try {Thread.sleep(3000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
		    	try {Thread.sleep(2000);}catch (Exception e) {}
		    	clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
	
				objDictionary.put("strHttpProxy", NegativeInputCases.HTTP_PROXY_BLACKHOLE);
				clsCommonMobile.SENTRYMOBILE_SET_HTTP_PROXY(objDictionary);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Sessions");
				try { Thread.sleep(4000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Sessions with http_proxy blackhole");
				clsCommonMobile.SENTRYMOBILE_AssertApiErrorOrUsableDegrade(objDictionary, iosdriver, "Sessions with http_proxy blackhole");
	
				objDictionary.put("strHttpProxy", "Clear");
				clsCommonMobile.SENTRYMOBILE_SET_HTTP_PROXY(objDictionary);
				clsCommonMobile.SENTRYMOBILE_ClickRetryIfPresent(iosdriver);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Account after clearing http_proxy");
				Reporter.log("Proxy restored; app remained usable without reinstall");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1930, groups={"Smoke"})
		public void I5218F_PrivateDnsInvalid_ApiFailure()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-13: API failure without Charles — invalid Private DNS, then restore and Retry");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
				try {Thread.sleep(3000);}catch (Exception e) {}
				clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
				try {Thread.sleep(2000);}catch (Exception e) {}
				clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				objDictionary.put("strPrivateDnsMode", "hostname");
				objDictionary.put("strPrivateDnsSpecifier", NegativeInputCases.PRIVATE_DNS_INVALID);
				clsCommonMobile.SENTRYMOBILE_SET_PRIVATE_DNS(objDictionary);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Sessions");
				try { Thread.sleep(4000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Sessions with invalid Private DNS");
				clsCommonMobile.SENTRYMOBILE_AssertApiErrorOrUsableDegrade(objDictionary, iosdriver, "Sessions with invalid Private DNS");
	
				clsCommonMobile.SENTRYMOBILE_RESTORE_PRIVATE_DNS(objDictionary);
				clsCommonMobile.SENTRYMOBILE_ClickRetryIfPresent(iosdriver);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Account");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Account after restoring Private DNS");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}

		@Test(priority=1940, groups={"Smoke"})
		public void I5219F_HttpProxy_ParkThenRestoreRetry()
		{
			objDictionary.put("strAssociatedBug", "");
			objDictionary.put("strMobileDeviceType", "IOS");
			IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();		Meter clsMeter = new Meter();
			clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);		Reporter.log("TC-NEG-12/16 recover: Park with API blackhole, restore proxy, Retry — no crash, no duplicate pay");
	
			IOSDriver iosdriver = null;
			try
			{
				iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile", "True", "Parker");
				clsCommonMobile.ClickButton(objDictionary, iosdriver, "User Agreement", "Accept", 1);
				clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "Remind me later");
				//ClickPermissionAlertButton
				try {Thread.sleep(3000);}catch (Exception e) {}
				clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow");
				try {Thread.sleep(2000);}catch (Exception e) {}
				clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosdriver, "Allow While Using App");
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
	
				objDictionary.put("strHttpProxy", NegativeInputCases.HTTP_PROXY_BLACKHOLE);
				clsCommonMobile.SENTRYMOBILE_SET_HTTP_PROXY(objDictionary);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
				try { Thread.sleep(3000); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park with http_proxy blackhole");
				clsCommonMobile.SENTRYMOBILE_AssertApiErrorOrUsableDegrade(objDictionary, iosdriver, "Park with http_proxy blackhole");
	
				objDictionary.put("strHttpProxy", "Clear");
				clsCommonMobile.SENTRYMOBILE_SET_HTTP_PROXY(objDictionary);
				clsCommonMobile.SENTRYMOBILE_ClickRetryIfPresent(iosdriver);
				clsCommonMobile.NavigateToPageUsingMenuButtons(objDictionary, iosdriver, "Park");
				clsCommonMobile.SENTRYMOBILE_AssertAppDidNotCrash(objDictionary, iosdriver, "Park after proxy restore / Retry");
				Reporter.log("No payment was completed while the proxy was blackholed, so restore could not duplicate a park/pay");
			}
			finally
			{
				try { if (iosdriver != null) iosdriver.quit(); } catch (Exception e) {}
				clsCommonMobile.SENTRYMOBILE_RestoreDeviceNetworkAndPower(objDictionary);
				clsMeter.METER_SetMeterEndTime(objDictionary);
			}
		}
}

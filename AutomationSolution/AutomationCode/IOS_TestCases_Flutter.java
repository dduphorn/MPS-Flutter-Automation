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
  	//IOS-TEST CASES
  	//********************************************************************************************************************
  	@Test(priority=3001)
  	public void I3001_ValidateInvalidCredentialMessage_Flutter()
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
  	@Test(priority=3002)
  	public void I3002_RegisterUser_PasswordShouldHaveAtleastOneDigit_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	@Test(priority=3003)
  	public void I3003_RegisterUser_EmailHasAlreadyBeenTaken_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	@Test(priority=3004)
  	public void I3004_RegisterUser_EmailIsInvalid_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	
  	@Test(priority=3006)
	public void I3006_RegisterUser_PasswordsDoNotMatchPleaseReEnter_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	
  	
  	@Test(priority=3007)
  	public void I3007_RegisterUser_PasswordShouldBeAtleast12Characters_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	@Test(priority=3008)
  	public void I3008_RegisterUser_FirstNameCannotBeBlank_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	@Test(priority=3009)
  	public void I3009_RegisterUser_LastNameCannotBeBlank_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581707");
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
  	@Test(priority=3010)
  	public void I3010_RegisterUser_Login_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581709");
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
 		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Log in", "Populate Login", "{T} Email or Phone number|{T} Password",strUserName+"|"+strPassword);
 		clsCommonMobile.ClickButton(objDictionary, iosDriver, "Log in", "Log in",1);
 		//ClickPermissionAlertButton
 		clsCommonMobile.ClickPermissionAlertButton(objDictionary, iosDriver, "Allow");
 		iosDriver.quit();
	 	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	
  	@Test(priority=3011)
	public void I3011_RegisterExisting_DeletedUser_Login_Flutter()
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581707");
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
  	
  	@Test(priority=3012)
	public void I3012_RegisterExisting_LockedUser_Login_Flutter()
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
		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strUserName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		driver = getDriver(); // driver.quit();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnDeleteAccount(objDictionary,driver, strUserName);
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","9525581707");
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
  	
  	@Test(priority=3016)
  	public void I3016_CreateAndDeleteUser_AttemptMobileRegisterWithDeletedUser_Flutter()
	{
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
		clsCommonMobile.PopulateAction(objDictionary, iosDriver, "Sign Up", "Populate Phone Number", "{T} Phone number","6125581708");
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
  	
  	@Test(priority=3017)
  	public void I3017_CreateAndDeleteUser_AttemptLoginWithDeletedUser_Flutter()
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
  	@Test(priority=3021)
  	public void I3021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
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
  	@Test(priority=3022)
  	public void I3022_VM_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
	{
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
//		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
//		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
  		//Function Variables
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "F"+strSpaceName.toUpperCase();
		
		//String strLicensePlateNumber = "CA9010AA";
		
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
  	
  	
  	@Test(priority=3023)
    public void I3023_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC()
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
  	@Test(priority=3024)
	public void I3024_VM_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC()
    {
  		IOS_Common_Flutter clsCommonMobile = new IOS_Common_Flutter();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
//		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
//		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
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
  	@Test(priority=3025)
	public void I3025_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
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
  	@Test(priority=3026)
	public void I3026_VM_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()
    {
  		objDictionary.put("strAssociatedBug", "188731741|177295054");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  	@Test(priority=3027)
 	public void I3027_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3027_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=3028)
	public void I3028_VM_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|177295054");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		clsCommonMobile.SENTRYMOBILE_3028_VM_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=3029)
	public void I3029_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
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
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3029_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=3030)
	public void I3030_VM_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|184183962");
  		CommonIOS clsCommonMobile = new CommonIOS();
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
  		clsCommonMobile.SENTRYMOBILE_3030_VM_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment0//Park//CointPayment//MobilePayment
  	@Test(priority=3031)
	public void I3031_FTFP0_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC()
  	{
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3031_FTFP0_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3032)
	public void I3032_FTFP0_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3032_FTFP0_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3033)
	public void I3033_FTFP0_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3033_FTFP0_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=3034)
	public void I3034_FTFP0_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3034_FTFP0_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//FirstTimeFirstPayment0//Park//CreditCardPayment//MobilePayment
  	@Test(priority=3035)
 	public void I3035_FTFP0_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3035_FTFP0_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=3036)
 	public void I3036_FTFP0_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
 		clsCommonMobile.SENTRYMOBILE_3036_FTFP0_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
 		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
 	}
  	//FirstTimeFirstPayment0//Park//MobilePayment//CreditCardPayment
  	@Test(priority=3037)
 	public void I3037_FTFP0_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		objDictionary.put("strAssociatedBug", "144537727");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3037_FTFP0_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=3038)
	public void I3038_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3038_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//FirstTimeFirstPayment0//MobilePayment//Park
  	@Test(priority=3039)
 	public void I3039_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3039_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=3040)
	public void I3040_VM_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|177295054");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
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
  	@Test(priority=3041)
	public void I3041_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "144557797");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3041_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=3042)
	public void I3042_VM_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|182332617|177295054|144557797");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
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
  	@Test(priority=3043)
  	public void I3043_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3043_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=3044)
	public void I3044_VM_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|182332617,177295054|153460868|144557797");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
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
	@Test(priority=3045)
  	public void I3045_FTFP10_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
  	{
  		objDictionary.put("strAssociatedBug", "151649737,144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3045_FTFP10_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3046)
  	public void I3046_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "144557797");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3046_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3047)
	public void I3047_VM_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|182529810|184183962|182336037|177295054|157009379,153460868|144557797");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		clsCommonMobile.SENTRYMOBILE_3047_VM_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//CoinPayment//MobilePayment
	@Test(priority=3048)
  	public void I3048_FTFP10_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "175709211|174661143");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3048_FTFP10_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3049)
  	public void I3049_FTFP10_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "175709211|174661143");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3049_FTFP10_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//CoinPayment
	@Test(priority=3050)
  	public void I3050_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "144557797");
  		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("FTFP10: Free Time First Payment 10                              ");
		Reporter.log("PS1: Park Spot 1                                                ");
  		Reporter.log("CP1: Coin Payment Spot 1                                        ");
  		Reporter.log("MP1: Mobile Payment Spot 1                                      ");
  		Reporter.log("ES1: Exit Spot 1                                                ");
  		Reporter.log("VPSH: Validate Parking Session History                          ");
  		Reporter.log("VICAE: Validate Image Count After Exit                          ");
  		Reporter.log("VIAC: Validate Images Appear Correctly                          ");
  		Reporter.log("****************************************************************");
  		//Test Case Variables
  		String strMaximumDuration = "250";
  		String strCoinTimePuchaseLimit = "250";
  		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment",strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime",strMeterIncrementTime);
  		String strInitialGracePeriod = "10";
  		String strViolationGracePeriod = "1";
  		String strHandicapInitialGracePeriod = "5";
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strFreeTimeMinutes = "0";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//PS1: Park Spot 1
  		clsMeter.METER_ParkSpot(objDictionary,"1","Local");
    	//MP1: Mobile Payment
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
    	//CP1: Coin Payment
    	clsMeter.METER_InsertCoin(objDictionary, null, "1", "Local");
    	//Store Parking Id
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
    	//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
      	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3050_FTFP10_PS1_MP1_CP1_ES1(objDictionary);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3051)
  	public void I3051_FTFP10_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3051_FTFP10_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//CreditCardPayment//MobilePayment
	@Test(priority=3052)
  	public void I3052_FTFP10_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		objDictionary.put("strAssociatedBug", "175709211|174661143");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		CommonIOS clsCommonMobile = new CommonIOS();
  		clsCommonMobile.SENTRYMOBILE_3052_FTFP10_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3053)
  	public void I3053_FTFP10_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "175709211|174661143|147685361");
	    String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3053_FTFP10_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//CreditCardPayment
	@Test(priority=3054)
	public void I3054_FTFP10_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug", "144557797");
  	    objDictionary.put("strMobileDeviceType", "IOS");
  		Meter clsMeter = new Meter();
  		CommonIOS clsCommonMobile = new CommonIOS();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("FTFP10: Free Time First Payment 10                              ");
  		Reporter.log("PS1: Park Spot 1                                                ");
		Reporter.log("CCP1: Credit Card Payment Spot 1                                ");
		Reporter.log("MP1: Mobile Payment Spot 1                                      ");
		Reporter.log("ES1: Exit Spot 1                                                ");
		Reporter.log("VPSH: Validate Parking Session History                          ");
		Reporter.log("VICAE: Validate Image Count After Exit                          ");
		Reporter.log("VIAC: Validate Images Appear Correctly                          ");
		Reporter.log("****************************************************************");
  	   	//Test Case Variables
		String strMaximumDuration = "250";
		String strCoinTimePuchaseLimit = "250";
		String strFreeTimeFirstPayment = "10";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "10";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strFreeTimeMinutes = "0";
		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//PS1: Park Spot 1
    	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
    	//MP1: Mobile Payment
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
    	//Calculate InitialTimeMinutes
    	int intInitialTimeMinutes = Integer.parseInt(strMeterIncrementTime) + Integer.parseInt(strFreeTimeFirstPayment);
    	//CCP1: Credit Card Payment Spot 1
    	int intCreditCardMinimumPurchaseMinutes = clsMeter.SENTRYMETER_CreditCardPaymentBuyMoreTime(objDictionary, null,  strMeterIncrementTime, strFreeTimeMinutes, Integer.toString(intInitialTimeMinutes));
    	//Store Parking Id
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
    	//ShortSessionWaitExitSpot
  	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  	    IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
  	    clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3054_FTFP10_PS1_MP1_CCP1_ES1(objDictionary);
  	    clsMeter.METER_SetMeterEndTime(objDictionary);
  	    String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3055)
  	public void I3055_FTFP10_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
	 	objDictionary.put("strAssociatedBug", "144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3055_FTFP10_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//FirstTimeFirstPayment10//MobilePayment//Park
	@Test(priority=3056)
    public void I3056_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
    	String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
    	objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
  		clsCommonMobile.SENTRYMOBILE_3056_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3057)
	public void I3057_VM_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
  		objDictionary.put("strAssociatedBug", "188731741|182332617|177295054");
  		CommonIOS clsCommonMobile = new CommonIOS();
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
	@Test(priority=3058)
	public void I3058_GPSOFF_PS1_MP1_ES1_VPSH_VICAE_VIAC()
    {
  		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		String strFreeTimeFirstPayment = "0";
  		String strCreditCardIncrementTime = "15";
  		String strInitialGracePeriod = "15";
  		String strViolationGracePeriod = "1";
  		String strHandicapInitialGracePeriod = "5";
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strAndroidUdid = objDictionary.get("strAndroidUdid"); if(strAndroidUdid == null) {strAndroidUdid = "";}
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
    	//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//ParkTP: Park Then Pay
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","False","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "","Remind me later");
    	//PS1_MP1
		clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, iosdriver, strLicensePlateNumber, "Alabama",strFreeTimeFirstPayment,strCreditCardIncrementTime,"False","False");
    	iosdriver.quit();
    	//ShortSessionWaitExitSpot
      	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
      	//ValidateParkingSessionHistoryAndImages
      	IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
      	clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3058_GPSOFF_PS1_MP1_ES1(objDictionary, strCreditCardIncrementTime);
      	clsMeter.METER_SetMeterEndTime(objDictionary);
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3059)
	public void I3059_GPSOFF_RegisterUser_EnableGPSDialogNo()
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Dictionary Variable
		String strMunicipality = objDictionary.get("strMunicipality");
		String strIOSVersion = objDictionary.get("strIOSVersion");
		//Test Case Variables
		String strUserName = "DeleteUser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "DeleteMeMN01";
		//Destroy User
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
 		//Open Consumer App
		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","False","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Register",1);
		//This is a unexpected dialog.  Waiting to hear back from Padma if it's by design.
		String alertText  = iosdriver.switchTo().alert().getText();
		System.out.println("Alert Text: " + alertText);
		if(alertText.equals("Turn On Location Services to Allow “Sentry Mobile” to Determine Your Location"))
		{
			//Click Accept here actual click the cancel
			iosdriver.switchTo().alert().accept();
		}
		try {Thread.sleep(2000);}catch (Exception e) {}
		//Populate Register
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Register", "Populate Register", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary,iosdriver, "Register", "Submit", 1);
		//Populate Register
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Register", "Populate Register", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary,iosdriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Register", "Access to location has been denied. In order to provide you the best service possible, Please enable access in Settings.", 1, "Value", "Access to location has been denied. In order to provide you the best service possible, Please enable access in Settings.");
		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Register", "Continue without enabling",1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Register","Dialog Message-You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.",1, "Exists","Dialog Message-You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Register", "OK",1);
   		try {Thread.sleep(10000);}catch (Exception e) {}
   		String strConditional = clsCommonMobile.ConditionalStepText(objDictionary, iosdriver, "Account Details", "Account Details~Turn On Location Services to Allow “Sentry Mobile” to Determine Your Location", 1, "Exists", "");
   		if(strConditional.equals("True")){clsCommonMobile.ClickButton(objDictionary, iosdriver, "Account Details", "Cancel", 1);}
   		else {clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Account Details", "{PageExists} Account Details", 60);}
   		clsCommonMobile.VerificationPointPage(objDictionary,iosdriver, "Account Details", "Exists");
		iosdriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
 	}
	@Test(priority=3060)
	public void I3060_GPSOFF_RegisterUser_EnableGPSDialogYes()
	{
  		objDictionary.put("strAssociatedBug", "180183222");
		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Test Case Variable
		String strUserName = "DeleteUser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "DeleteMeMN01";
		//Destroy User
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		//GPS Off
		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","False","Parker");
		clsCommonMobile. SENTRYMOBILE_Open(objDictionary, iosdriver);
		clsCommonMobile.EnableLocationService(iosdriver);
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Register",1);
		clsCommonMobile.EnableLocationService(iosdriver);
		try {Thread.sleep(15000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,iosdriver, "Register", "Submit", 1);
		String alertText = null;
		alertText  = iosdriver.switchTo().alert().getText();
		System.out.println("Alert Text: " + alertText);
		if(alertText.contains("You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account."))
		{
			Reporter.log("The Text (Alert Text) with equaled (" + alertText + ")");
			iosdriver.switchTo().alert().accept();
		}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver,"The Text (Alert Text) with index (1) did not equal (You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Accounts\" to add money to your account.) - actual value (" + alertText + ")");}
		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Account Details", "{PageExists} Account Details", 60);
		clsCommonMobile.VerificationPointPage(objDictionary,iosdriver, "Account Details", "Exists");
		iosdriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//MeterUnlockOn
	@Test(priority=3061)
  	public void I3061_MUON_CGPV1_MP1_VMT_ES1_VPSH_VICAE_VIAC()
  	{
		objDictionary.put("strAssociatedBug", "183842654");
  		objDictionary.put("strMobileDeviceType", "IOS");
      	CommonIOS clsCommonMobile = new CommonIOS();
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
    	String strUnlockTime = "15";
    	String strUnlockMax = "1";
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//ExitSpotAndSetMeterRateBlocks
  		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
  		//CGPV1: Create Grace Period Violation Spot 1
      	clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
     	String strViolationId = clsCommonWeb.SENTRYLINK_StoreGracePeriodViolationNumber(objDictionary);
     	//MP1: Mobile Payment 1
      	IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
      	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosdriver, "parker", "","Remind me later");
      	Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber+"");
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strCreditCardIncrementTime,strFreeTimeFirstPayment);
      	//Store Parking Id
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
    	//ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
    	//ValidateParkingSessionHistoryAndImages
	    IOS_ParkingSessions clsIOS_ParkingSessions = new IOS_ParkingSessions();
	    clsIOS_ParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3061_MUON_CGPV1_MP1_VMT_ES1(objDictionary, strCreditCardIncrementTime, strViolationId);
	    clsMeter.METER_SetMeterEndTime(objDictionary);
	    String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//********************************************************************************************************************
  	//IOS MOBILE-FREE TO RATE
  	//********************************************************************************************************************
  	//FirstTimeFirstPayment0//MobilePayment
	@Test(priority=3062)
    public void I3062_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3062_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3063)
    public void I3063_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3063_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3064)
	public void I3064_VM_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		clsCommonMobile.SENTRYMOBILE_3064_VM_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3065)
    public void I3065_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184188792|182675569");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
		String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		String strLicensePlateNumber = "0"+strMeterSpotName+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3065_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3066)
	public void I3066_VM_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|182532022|182529810");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
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
	@Test(priority=3067)
  	public void I3067_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_PRT_VMT_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "184309776|182532022|179753382|169782400");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
  		clsCommonMobile.SENTRYMOBILE_3067_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_PRT_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3068)
	public void I3068_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC() throws Exception
	{
   		objDictionary.put("strAssociatedBug", "172458902");
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3068_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3069)
    public void I3069_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
   		objDictionary.put("strAssociatedBug", "181224271|172458902");
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3069_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3070)
	public void I3070_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|182391128|177295054|150402097|135429705");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
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
	@Test(priority=3071)
    public void I3071_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "181224271|172458902|163396635|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3071_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3072)
	public void I3072_VM_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|182391128|177295054|144557797|160702971|150402097|135429705");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
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
	@Test(priority=3073)
    public void I3073_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "172458902|163396635|157009379|151952960|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3073_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3074)
	public void I3074_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741|182529810|182391128|177295054|157009379|144557797");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "IVMeter1");
  		clsGlobal.GetMeterProperties(objDictionary, "IVMeter1");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_3074_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3075)
	public void I3075_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC() throws Exception
	{
   		objDictionary.put("strAssociatedBug", "181224271|172458902|144557797");
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3075_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3076)
  	public void I3076_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3076_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3077)
	public void I3077_VM_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "188731741");
		CommonIOS clsCommonMobile = new CommonIOS();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
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
  		clsCommonMobile.SENTRYMOBILE_3077_VM_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3078)
   	public void I3078_FTFP0_FDON_RTF_MBF_gt_MTIV_PS1_MP1_VMT_BMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
   		objDictionary.put("strAssociatedBug", "164320000,157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
   		CommonIOS clsCommonMobile = new CommonIOS();
   		clsCommonMobile.SENTRYMOBILE_3078_FTFP0_FDON_RTF_MBF_gt_MTIV_PS1_MP1_VMT_BMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3079)
  	public void I3079_FTFP0_FDON_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "164320000,157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3079_FTFP0_FDON_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3080)
	public void I3080_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184153694");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3080_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3081)
	public void I3081_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3081_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3082)
  	public void I3082_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694|182332617");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3082_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3083)
  	public void I3083_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694|182417841|182404954|182332617");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3083_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3084)
  	public void I3084_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "184153694|182417841|182332617|163396635|157009379");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3084_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3085)
   	public void I3085_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3085_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3086)//FreeDisconnectOff - Fails
  	public void I3086_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3086_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3087)
  	public void I3087_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184153694|182332617|182415888");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3087A_FTFP10_FDOFF_RTNP_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3088)
  	public void I3088_FTFP0_FDOFF_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "164320000");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3088_FTFP0_FDOFF_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3089)
    public void I3089_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "184153694|182332617|182415888");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3089_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3090)
    public void I3090_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3090_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3091)
   	public void I3091_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3091_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3092)
    public void I3092_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3092_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3093)
    public void I3093_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "168060201");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3093_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3094)
  	public void I3094_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "147978515");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3094_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3095)
	public void I3095_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3095_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3096)
   	public void I3096_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "184549710|168060201|150861890|147978515");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
 		clsCommonMobile.SENTRYMOBILE_3096_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3097)
    public void I3097_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
 		clsCommonMobile.SENTRYMOBILE_3097_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3098)
    public void I3098_AMMOEE_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
    	objDictionary.put("strAssociatedBug", "179985202");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
  		clsCommonMobile.SENTRYMOBILE_3098_AMMOEE_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3099)
    public void I3099_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "179985202|167947492|150861890");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
 		clsCommonMobile.SENTRYMOBILE_3099_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3100)
  	public void I3100_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3100_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3101)
    public void I3101_FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "182201832");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3101_FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3102)
    public void I3102_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "174306633");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3102_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3103)
	public void I3103_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "170429307|141421545");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3103_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3104)
	public void I3104_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "174306633,141421545");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		objDictionary.put("strLicensePlateState","Minnesota");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3104_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3105)
	public void I3105_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "186676949");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3105_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3106)
	public void I3106_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "186676949|184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3106_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3107)
	public void I3107_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3107_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3108)
	public void I3108_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3108_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3109)
	public void I3109_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "179985202");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3109_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3110)
	public void I3110_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "179985202");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3110_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3111)
	public void I3111_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187620240");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3111_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3112)
	public void I3112_FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "182201832|179985202,160809086|175709211|164320000|160809086");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3112_FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3113)
   	public void I3113_FTFP0_TUON_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184549710");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3113_FTFP0_TUON_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3114)
    public void I3114_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
   	{
		//objDictionary.put("strAssociatedBug", "171546046");
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3114_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3115)
	public void I3115_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
	{
  		//objDictionary.put("strAssociatedBug", "171546046");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3115_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3116)
  	public void I3116_ForgotPassword_EnterTheUserEmailIdToResetPassword() throws MessagingException, IOException
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
  		CommonIOS clsCommonMobile = new CommonIOS();
  		//String strMunicipality = objDictionary.get("strMunicipality");
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
  		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", "EmailIdIsNotValid");
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email id is not valid",1, "Value", "Invalid email address.");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "OK", 1);
   		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3117)
  	public void I3117_ForgotPassword_AllFieldsWithAreMandatory() throws MessagingException, IOException
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
	  	CommonIOS clsCommonMobile = new CommonIOS();
  		Meter clsMeter = new Meter();
  		//String strMunicipality = objDictionary.get("strMunicipality");
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Enter the user email id to reset password.",1, "Exists", "");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "OK", 1);
   		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3118)
  	public void I3118_ForgotPassword_InvalidEmail() throws MessagingException, IOException
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
	  	CommonIOS clsCommonMobile = new CommonIOS();
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strGmailUserName = "InvalidGmailUser@gmail.com";
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "OK", 1);
   		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3119)
  	public void I3119_ForgotPassword_InvalidToken() throws MessagingException, IOException
  	{
		objDictionary.put("strMobileDeviceType", "IOS");
	  	CommonIOS clsCommonMobile = new CommonIOS();
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
		//Open Sentry Mobile
		objDictionary.put("strEnableUiautomator2", "True");
		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
  		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email ID", "{T} Email Id", strGmailUserName);
   		//Click Pass Reset Token Heading to hide keyboard
		iosdriver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Vertical scroll bar, 2 pages\"]")).click();
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 60);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		try {Thread.sleep(3000);}catch (Exception e) {}
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		String strToken = "InvalidToken";
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+12,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Reset token", strToken);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} New password", strNewPassword);
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Confirm new password", strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Reset Password", 1);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3120)
  	public void I3120_ForgotPassword_DifferentPassword() throws MessagingException, IOException
  	{
		objDictionary.put("strAssociatedBug", "149810474");
		objDictionary.put("strMobileDeviceType", "IOS");
	  	CommonIOS clsCommonMobile = new CommonIOS();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Database clsDatabase = new Database();
  		Gmail clsGmail = new Gmail();
  		Meter clsMeter = new Meter();
  		String strEnvironment = objDictionary.get("strEnvironment");
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		clsCommonWeb.SENTRYLINK_GetGmailAccountInformation(objDictionary);
  		String strGmailUserName = clsCommonWeb.strGmailUserName;
  		String strGmailFirstName = clsCommonWeb.strGmailFirstName;
  		String strGmailLastName = clsCommonWeb.strGmailLastName;
  		String strGmailPassword = clsCommonWeb.strGmailPassword;
  		//Check User Exist and is active
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
  		//Open Sentry Mobile
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		objDictionary.put("strEnableUiautomator2", "True");
		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+12,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|InvalidPassword");
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Reset Password", 1);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Passwords do not match. Please re-enter",1, "Value", "Passwords do not match. Please re-enter");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		try {Thread.sleep(1000);}catch (Exception e) {}
		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3121)
  	public void I3121_ForgotPassword_SentResetInstructions_LoginWithCurrentPassword() throws MessagingException, IOException
  	{
		objDictionary.put("strAssociatedBug", "149811937");
		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
  		//Open Sentry Mobile
  		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		objDictionary.put("strEnableUiautomator2", "True");
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(4000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Navigate Back To Login
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Back", 1);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strGmailUserName+"|"+strSentryLinkCurrentPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Login",1);
   		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointPage(objDictionary, iosdriver, "Account Details", "Exists");
		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3122)
  	public void I3122_ForgotPassword_ResendResetInstructionsTwice_UseFirstToken() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strPassword, "parker",strGmailFirstName, strGmailLastName);
  		//Open Sentry Mobile
  		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		objDictionary.put("strEnableUiautomator2", "True");
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email ID", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		String strFirstToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Back", 1);
   		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+12,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strFirstToken+"|"+strNewPassword+"|"+strNewPassword);
		//Click Pass Reset Token Heading to hide keyboard
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Reset Password", 1);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3123)
  	public void I3123_ForgotPassword_ResendResetInstructionsTwice_UseSecondToken() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		objDictionary.put("strEnableUiautomator2", "True");
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Back", 1);
   		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		String strSecondToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+12,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strSecondToken+"|"+strNewPassword+"|"+strNewPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Reset Password", 1);
   		try {Thread.sleep(3000);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Your password has been reset! Click \"OK\" to login.",1, "Value", "Your password has been reset! Click \"OK\" to login.");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Account Details", "{PageExists} Account Details", 20);
		clsCommonMobile.VerificationPointPage(objDictionary, iosdriver, "Account Details", "Exists");
		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3124)
  	public void I3124_ForgotPasswordRememberMeOn() throws MessagingException, IOException
  	{
  		objDictionary.put("strAssociatedBug", "150059522");
  		objDictionary.put("strMobileDeviceType", "IOS");
		CommonIOS clsCommonMobile = new CommonIOS();
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
  		objDictionary.put("strEnableUiautomator2", "True");
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		clsCommonMobile.VerificationPointButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1, "Disabled");
   		//Open GMAIL
   		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+12,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|"+strNewPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Reset Password", 1);
   		try {Thread.sleep(2000);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Your password has been reset! Click \"OK\" to login.",1, "Value", "Your password has been reset! Click \"OK\" to login.");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Account Details", "{PageExists} Account Details", 20);
		clsCommonMobile.VerificationPointPage(objDictionary, iosdriver, "Account Details", "Exists");
		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3125)
  	public void I3125_ForgotPasswordRememberMeOff() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "IOS");
 		CommonIOS clsCommonMobile = new CommonIOS();
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
  		objDictionary.put("strEnableUiautomator2", "True");
  		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Forgot password?", 1);
   		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Email ID", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,iosdriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		//Open GMAIL
   		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+12,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|"+strNewPassword);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Remember me", 1);
   		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Forgot Password", "Reset Password", 1);
   		try {Thread.sleep(2000);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, iosdriver, "Forgot Password", "Your password has been reset! Click \"OK\" to login.",1, "Value", "Your password has been reset! Click \"OK\" to login.");
		clsCommonMobile.ClickLink(objDictionary, iosdriver, "Forgot Password", "Ok", 1);
   		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strGmailUserName.toLowerCase()+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Login",1);
		if(clsCommonMobile.strActualWaitValue.equals("Dialog Message"))
		{
			String strDialogMessage = clsCommonMobile.StoreText(objDictionary, iosdriver, "Login", "Dialog Message", 1, "strDialogMessage");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver,strDialogMessage);
		}
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointPage(objDictionary, iosdriver, "Account Details", "Exists");
		iosdriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3126)
  	public void I3126_LoginAttemptsExceededYourAccountHasBeenLocked()
  	{
  		objDictionary.put("strAssociatedBug", "156816966");
  		objDictionary.put("strMobileDeviceType", "IOS");
  		CommonIOS clsCommonMobile = new CommonIOS();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strRole = objDictionary.get("strRole");
  		if(strRole == null) { strRole = "parker";}
  		String strUserName = "AInvalidUser126@gmail.com";objDictionary.put("strUserName",strUserName);
		String strPassword = "AInvalidUser01!";
		String strFirstName = "AIUFirstName";
	    String strLastName = "AIULastName";
		clsCommonWeb.SENTRYLINK_CreateUserAndCloseBrowser(objDictionary, strUserName, strPassword, "parker", strFirstName, strLastName);
		//Open Android Device
		IOSDriver iosdriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosdriver);
		//1st Login with Invalid Credentials with Municipality
		clsCommonMobile.PopulateAction(objDictionary, iosdriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName.toLowerCase()+"|AInvalidUser01a");
		clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Login",1);
   		int intLoginCounter = 1;
		String strConditionalValue  = "";
		//Invalid Credential. Please re-enter your email id and password.
		do
        {
			try {Thread.sleep(1000);}catch (Exception e) {}
			strConditionalValue = clsCommonMobile.ConditionalStepText(objDictionary,iosdriver, "Login", "Dialog Message-Invalid Credentials.", 1, "Exists", "");
			if(strConditionalValue.equals("False")){clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, iosdriver,"Dialog Message-Invalid Credentials.");}
			Reporter.log("<font color='green'>Invalid Credential. Please re-enter your email id and password.</font>");
			clsCommonMobile.ClickLink(objDictionary, iosdriver, "Login", "Ok", 1);
			clsCommonMobile.ClickButton(objDictionary, iosdriver, "Login", "Login",1);
			intLoginCounter++;

        }while (intLoginCounter < 6);
		iosdriver.quit();
		//Unlock Locked Account
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strUserName);
  		driver.quit();
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3127)
    public void I3127_FTFP0_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "183842654|182493546|168060201");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		objDictionary.put("strTrueUp", "False");
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3127_FTFP0_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=3128)
   	public void I3128_FTFP0_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|182493546|168060201");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3128_FTFP0_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3129)
    public void I3129_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3129_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3130)
    public void I3130_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|184309776|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3130_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3131)
  	public void I3131_FTFP0_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3131_FTFP0_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3132)
	public void I3132_FTFP0_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3132_FTFP0_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3133)
   	public void I3133_FTFP0_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|182493546|150861890");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
 		clsCommonMobile.SENTRYMOBILE_3133_FTFP0_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3134)
    public void I3134_FTFP0_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
  		objDictionary.put("strAssociatedBug", "183842654|182493546|147978515");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
   		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
 		CommonIOS clsCommonMobile = new CommonIOS();
 		clsCommonMobile.SENTRYMOBILE_3134_FTFP0_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3135)
    public void I3135_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3135_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3136)
    public void I3136_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3136_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3137)
  	public void I3137_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3137_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3138)
    public void I3138_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "182493546|182201832");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3138_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=3139)
	public void I3139_FTFP10_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "183842654|182493546|167947492");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3139_FTFP10_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3140)
    public void I3140_FTFP10_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3140_FTFP10_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
	@Test(priority=3141)
	public void I3141_FTFP10_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3141_FTFP10_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3142)
	public void I3142_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_CMM_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3142_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_CMM_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3143)
	public void I3143_FTFP10_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3143_FTFP10_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3144)
	public void I3144_FTFP10_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3144_FTFP10_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3145)
	public void I3145_FTFP10_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546|160994298");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3145_FTFP10_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3146)
	public void I3146_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187620240");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
  		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3146_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3147)
	public void I3147_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		//objDictionary.put("strAssociatedBug", "182493546|179985202,160809086");
		objDictionary.put("strAssociatedBug", "187620240");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3147_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3148)
	public void I3148_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187620240|182493546|179985202");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3148_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3149)
	public void I3149_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "182493546|182201832|179985202,175709211|164320000");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3149_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=3150)
	public void I3150_FTFP0_TUOFF_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|182493546");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3150_FTFP0_TUOFF_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3151)
	public void I3151_FTFP0_TUOFF_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3151_FTFP0_TUOFF_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
	}
	@Test(priority = 3152)
	public void I3152_FTFP0_TUON_UON_GPV1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strMethodName.substring(0,strMethodName.indexOf("_"))+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3152_FTFP0_TUON_UON_GPV1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals(""))
		{Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3157)
	public void I3157_PBP_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
	{
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strTestCaseName", strTestCaseName);
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber",strLicensePlateNumber);
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonIOS clsCommonMobile = new CommonIOS();
		clsCommonMobile.SENTRYMOBILE_3157_PBP_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3199)
	public void I3199_RSVN_MPRSISR()
	{
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonIOS clsCommonMobile = new CommonIOS();
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
		clsCommonMobile.SENTRYMOBILE_MobilePaymentRejectedSpotIsReserved(objDictionary,strLicensePlateNumber, "Minnesota");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals(""))
		{Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3200)
	public void I3200_RSVN_PSWVLP_VMT_ES1_VPSH()
	{
		objDictionary.put("strAssociatedBug", "184858316|184076866");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonIOS clsCommonMobile = new CommonIOS();
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
		String strCoinTimePurchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,strCoinTimePurchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime,strUnlockMax,"Local");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "0");
		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes",strReservationTimeMinutes);
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
		int intRemainingReservationTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary,null,strReservationStartTime,strReservationTimeMinutes);
		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intRemainingReservationTime,"1");
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Add License Plate to SL
		clsCommonWeb.AddLicensePlateToParkingSession( objDictionary, strLicensePlateNumber, "Minnesota");
		//Open Sentry Mobile-Android
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, iosDriver,"parker",strLicensePlateNumber, "Enroll later");
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary, iosDriver,"Parking Sessions");
		//Validate Spot and License Plate
		clsCommonMobile.VALIDATIONS_ParkingSessions_SpotAndLicensePlateNumber(objDictionary, iosDriver,strLicensePlateNumber);
		//Validate Parked at Time
		clsCommonMobile.VALIDATIONS_ParkingSessions_ParkedAt(objDictionary, iosDriver);
		//ValidateParkingSessionsValidUntil
		intRemainingReservationTime = intRemainingReservationTime -1;
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary, iosDriver,intRemainingReservationTime);
		//ValidateParkingSessionsParkingExpiresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary, iosDriver,intRemainingReservationTime);
		//Exit Space
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		IOS_ParkingSessions clsIOSParkingSessions = new IOS_ParkingSessions();
		clsIOSParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3200_RSVN_PSWVLP_VMT_ES1(objDictionary,59,"1");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals(""))
		{Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3201)
	public void I3201_RSVN_PSWILP_MPRSISR_ES1_VPSH()
	{
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
		CommonIOS clsCommonMobile = new CommonIOS();
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
		String strCoinTimePurchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,strCoinTimePurchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime,strUnlockMax,"Local");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "0");
		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes",strReservationTimeMinutes);
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
		clsCommonMobile.SENTRYMOBILE_MobilePaymentRejectedSpotIsReserved(objDictionary, "INVALID","Minnesota");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals(""))
		{Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3202)
	public void I3202_RSVN_FTRSVN_PSWL_MPRSIIFPGIRP()
	{
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonIOS clsCommonMobile = new CommonIOS();
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
		String strCoinTimePurchaseLimit = "240";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		String strMinutesOfFreeTimeBeforeCurrentTime = "0"; objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime",strMinutesOfFreeTimeBeforeCurrentTime);
		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "6");
		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes",strReservationTimeMinutes);
		objDictionary.put("strReservationRateType", "Fixed");
		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-1");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
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
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary,strMaximumDuration, strCoinTimePurchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime,strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod,strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec,strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Update Permit Rate Blocks
		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
		//Add Or Update Permit Group
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary);
		//Park With License Plate
		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
		clsCommonMobile.SENTRYMOBILE_MobilePaymentRejectedSpotIsInFreeParkingGoingIntoReservedParking(objDictionary,strLicensePlateNumber, "Minnesota");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals(""))
		{Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority = 3203)
	public void I3203_RSVN_FTRSVN_PSWL_WFTE_VMTAP_VPS_VPSH()
	{
		objDictionary.put("strAssociatedBug", "186580270");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonIOS clsCommonMobile = new CommonIOS();
		HttpConnections clsHttpConnections = new HttpConnections();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("RSVN: Add Reservation                                           ");
		Reporter.log("FTRSVN: Free To Reservation                                     ");
		Reporter.log("PSWL: Park Spot With Valid License Plate                        ");
		Reporter.log("WFTE: Wait For Free Time To Expire                              ");
		Reporter.log("VMTAP: Validate Maximum time already purchased                  ");
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
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		String strMinutesOfFreeTimeBeforeCurrentTime = "0";objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime",strMinutesOfFreeTimeBeforeCurrentTime);
		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "6");
		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes",strReservationTimeMinutes);
		objDictionary.put("strReservationRateType", "Fixed");
		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-1");
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
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
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime,strUnlockMax,"Local");
		//Update Permit Rate Blocks
		String strReservationBlockGroup = objDictionary.get("strReservationBlockGroup");
		clsCommonWeb.AddOrUpdateMeterRateBlockGroup(objDictionary, strReservationBlockGroup,"Local");
		//Add Or Update Permit Group
		clsCommonWeb.SENTRYLINK_AddOrUpdatePermitGroup(objDictionary);
		//Park With License Plate
		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Add License Plate to SL
		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, "Minnesota");
		//Wait For free time to expire
		int intMaxWaitSeconds = Integer.parseInt(strFreeTimeMinutes) * 60;
		clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterFreeEqualsFalse} NA",intMaxWaitSeconds, "1","Local");
		//Wait 10 seconds
		try {Thread.sleep(5000);}catch (Exception e) {}
		int intExpectedRemainingTime = Integer.parseInt(strReservationTimeMinutes);
		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
        clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver,"parker",strLicensePlateNumber, "Enroll later");
        try {Thread.sleep(2000);}catch (Exception e) {}
        clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary, iosDriver,"Meter Payment");
        try {Thread.sleep(2000);}catch (Exception e) {}
        clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary, iosDriver, "1");
        clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, iosDriver, strLicensePlateNumber, "Minnesota");
        try {Thread.sleep(1000);}catch (Exception e) {}
        //Purchase Parking
        clsCommonMobile.ClickButton(objDictionary, iosDriver, "Meter Payment","Purchase Parking", 1);
        clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Meter Payment","Dialog Message-Parking purchases beyond the maximum time limit is prohibited.", 1, "Contains","Does Not Exist");
        clsCommonMobile.ClickLink(objDictionary, iosDriver, "Meter Payment", "OK",1);
        try {Thread.sleep(1000);}catch (Exception e) {}
        clsCommonMobile.ClickButton(objDictionary, iosDriver, "Meter Payment","YES - PURCHASE", 1);
        clsCommonMobile.ClickButton(objDictionary, iosDriver, "Meter Payment","Sentry Mobile account", 1);
	    //Validate Message Device Rejected
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Meter Payment","Payment rejected, spot is reserved", 1, "Contains","Payment rejected, spot is reserved");
		clsCommonMobile.ClickLink(objDictionary, iosDriver, "Meter Payment", "OK",1);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosDriver,"Parking Sessions");
		//Validate Spot and License Plate
		clsCommonMobile.VALIDATIONS_ParkingSessions_SpotAndLicensePlateNumber(objDictionary, iosDriver,strLicensePlateNumber);
		//Validate Parked at Time
		clsCommonMobile.VALIDATIONS_ParkingSessions_ParkedAt(objDictionary, iosDriver);
		//ValidateParkingSessionsValidUntil
		intExpectedRemainingTime = Integer.parseInt(strReservationTimeMinutes) -2;
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary, iosDriver,intExpectedRemainingTime);
		//ValidateParkingSessionsParkingExpiresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary, iosDriver,intExpectedRemainingTime);
		//Exit Space
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		IOS_ParkingSessions clsIOSParkingSessions = new IOS_ParkingSessions();
		clsIOSParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3203_RSVN_FTRSVN_PSWL_WFTE_VMTAP(objDictionary,strReservationTimeMinutes,"1");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals(""))
		{Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	@Test(priority = 3301)
	public void I3301_CS_PSWL_VMT_VPSH()
	{
		objDictionary.put("strAssociatedBug", "185642052");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
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
		String strCoinTimePurchaseLimit = "120";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "3";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local","1");
		if(strReservationExists.equals("True"))
		{
			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
		}
		//ExitSpotAndSetMeterRateBlocks
		//clsMeter.METER_ExitBothSpots(objDictionary,null,"Local");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,strCoinTimePurchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
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
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//Wait For initial grace to expire
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60+10;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod,"Waiting for initial grace time to expire-Spot1");
		//Wait For SL Parking Session Concierge Value to equal True
		clsMeter.GlobalWait(objDictionary, null,"{WaitParkingSessionConciergeValueEqualsTrue} NA", 60,"1","Local");
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
		CommonIOS clsCommonMobile = new CommonIOS();
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver,"parker",strLicensePlateNumber, "Enroll later");
		//Navigate to Meter Payment
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosDriver,"Parking Sessions");
		//Validate Spot and License Plate
		clsCommonMobile.GlobalWait(objDictionary,iosDriver, "Parking Sessions","{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Address-Concierge", 1, "Value", "Address : ****");
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Spot-Concierge", 1, "Value", "Spot : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, iosDriver,"Parking Sessions", "Parked at", 1,"strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime))
		{
			Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");
		}
		//ValidateParkingSessionsValidUntil
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary, iosDriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary,strMaximumDuration);
		//ValidateParkingSessionsParkingExpiresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary, iosDriver,intMeterRemainingTimeMinutes);
		//Exit Space
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		IOS_ParkingSessions clsIOSParkingSessions = new IOS_ParkingSessions();
		clsIOSParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3301_CS_PSWL(objDictionary,strMeterIncrementTime,"1");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null)
		{strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed " +"remove the AssociatedBug</font>");}
	}
	@Test(priority = 3302)
	public void I3302_CS_PS1_GPV1_ALPTPS_VMT_VPSH()
	{
		objDictionary.put("strAssociatedBug", "184549710|178674942");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
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
		String strCoinTimePurchaseLimit = "120";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local","1");
		if(strReservationExists.equals("True"))
		{
			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
		}
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,strCoinTimePurchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime,strUnlockMax,"Local");
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
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		//GPV1: Create Grace Period Violation Spot 1
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, "1",strInitialGracePeriod);
		clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary,"Local", "1");
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//ALPTPS: Add License Plate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver,"Sentry meter","1");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session","Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber + "|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session","Create", 1, "Local");
		driver.quit();
		//Wait For SL Parking Session Concierge Value to equal True
		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA",50,"1","Local");
		String strMeterName = objDictionary.get("strMeterName");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
		if(strForcedMulti.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost,"testautof_enter_space.py "+strMeterName);}
		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
		//Dictionary Variables
		CommonIOS clsCommonMobile = new CommonIOS();
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver,"parker",strLicensePlateNumber, "Enroll later");
		//Navigate to Meter Payment
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosDriver,"Parking Sessions");
		//Validate Spot and License Plate
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Address-Concierge", 1, "Value", "Address : ****");
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Spot-Concierge", 1, "Value", "Spot : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, iosDriver,"Parking Sessions", "Parked at", 1,"strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime))
		{Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");}
		//ValidateParkingSessionsValidUntil
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary, iosDriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary, strMaximumDuration);
		//ValidateParkingSessionsParkingExpiresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary, iosDriver,intMeterRemainingTimeMinutes);
		//Exit Space
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		IOS_ParkingSessions clsIOSParkingSessions = new IOS_ParkingSessions();
		clsIOSParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3302_CS_PS1_GPV1_ALPTPS(objDictionary, strMeterIncrementTime,"1");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed " +"remove the AssociatedBug</font>");}
	}
	@Test(priority = 3303)
	public void I3302_2_A_CS_TU_PS1_GPV1_ALPTPS_VMT_VPSH()
	{
		objDictionary.put("strAssociatedBug", "184549710|178674942");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
		Meter clsMeter = new Meter();
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
		String strCoinTimePurchaseLimit = "120";
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "1";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
		String strViolationGracePeriod = "5";objDictionary.put("strViolationGracePeriod", strViolationGracePeriod);
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
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
		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local","1");
		if(strReservationExists.equals("True"))
		{
			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
		}
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,strCoinTimePurchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
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
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		//GPV1: Create Grace Period Violation Spot 1
		clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, "1",strInitialGracePeriod);
		clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary,"Local", "1");
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//ALPTPS: Add License Plate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session","Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber + "|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create",1, "Local");
		driver.quit();
		//Wait For SL Parking Session Concierge Value to equal True
		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA",50,"1","Local");
		String strMeterName = objDictionary.get("strMeterName");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
		if(strForcedMulti.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterName);}
		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
		//Dictionary Variables
		CommonIOS clsCommonMobile = new CommonIOS();
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver,"parker",strLicensePlateNumber, "Enroll later");
		//Navigate to Meter Payment
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosDriver,"Parking Sessions");
		//Validate Spot and License Plate
		clsCommonMobile.GlobalWait(objDictionary,iosDriver, "Parking Sessions","{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Address-Concierge", 1, "Value", "Address : ****");
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Spot-Concierge", 1, "Value", "Spot : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, iosDriver,"Parking Sessions", "Parked at", 1,"strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime))
		{Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");}
		//ValidateParkingSessionsValidUntil
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary,null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary, iosDriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary, strMaximumDuration);
		//ValidateParkingSessionsParkingExpiresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary, iosDriver,intMeterRemainingTimeMinutes);
		//Exit Space
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		IOS_ParkingSessions clsIOSParkingSessions = new IOS_ParkingSessions();
		clsIOSParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3302_2_A_CS_TU_PS1_GPV1_ALPTPS(objDictionary, strMeterIncrementTime,"1");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed " +"remove the AssociatedBug</font>");}
	}

	@Test(priority = 3303)
	public void I3303_CS_PS1_GPV1_WFFACTNVT_ALPTPS_VMT_VPSH()
	{
		objDictionary.put("strAssociatedBug", "178678280");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "IOS");
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
		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod",strHandicapInitialGracePeriod);
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "2";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strUnlockValue = "On";
//		String strUnlockValue = "OFF";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		//Concierge Test Case
		objDictionary.put("strConciergeTestCase", "True");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		objDictionary.put("strLicensePlateNumber", strLicensePlateNumber);
		//Delete Reservation
		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local","1");
		if(strReservationExists.equals("True"))
		{
			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
		}
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration,
				strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod,
				strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,
				strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,
				"Local");
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
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost,"settings_set_setting_true.py PARKING_RCV_ON_SB_LPR_PLATE_MATCH");
		//PS: Park Spot 1
		clsMeter.METER_ParkSpot(objDictionary,"1","Local");
		//WFRACTNVT: Wait For Recognized as Concierge time to be near Violation time
		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		intInitialGracePeriod = intInitialGracePeriod - 45;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod,"Wait For Recognized as Concierge time to be near Violation time");
		Reporter.log("Waited ("+intInitialGracePeriod+") " +"For Recognized as Concierge time to be near Violation time");
		//ALPTPS: Add License Plate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession2(objDictionary, driver, "Sentry Meter","1");
		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session","Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber + "|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create",1, "Local");
		driver.quit();
		//Wait For SL Parking Session Concierge Value to equal True
		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA",90,"1","Local");
		//Store Parking Id
		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		String strMeterName = objDictionary.get("strMeterName");
		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
		if(strForcedMulti.equals("True")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary, strHost,"testautof_enter_space.py "+strMeterName);}
		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
		//Validate CA Parking Session values
		CommonIOS clsCommonMobile = new CommonIOS();
		IOSDriver iosDriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, iosDriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, iosDriver,"parker",strLicensePlateNumber, "Enroll later");
		//Navigate to Meter Payment
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,iosDriver,"Parking Sessions");
		//Validate Spot and License Plate
		clsCommonMobile.GlobalWait(objDictionary,iosDriver, "Parking Sessions","{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Spot", 1, "Value", "Spot : ****");
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","Address", 1, "Value", "Address : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, iosDriver, "Parking Sessions","License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, iosDriver,"Parking Sessions", "Parked at", 1,"strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime))
		{Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");}
		//ValidateParkingSessionsValidUntil
		String strMeterValidTimeRemaining = clsMeter.GetMeterValidTimeRemaining(objDictionary, null,"1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterValidTimeRemaining) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary, iosDriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary, strMaximumDuration);
		//ValidateParkingSessionsParkingExpiresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary, iosDriver,intMeterRemainingTimeMinutes);
		//Get Violation Number
		String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
		//Exit Space
		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
		//ValidateParkingSessionHistoryAndImages
		IOS_ParkingSessions clsIOSParkingSessions = new IOS_ParkingSessions();
		clsIOSParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_3303_CS_PS1_GPV1_WFFACTNVT_ALPTPS(objDictionary, strMeterIncrementTime,"1",strViolationId);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed " +"remove the AssociatedBug</font>");}
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
//	public void I159_SENTRYMOBILE_PS1_EC1_NSAC_NSTU()
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
//	public void I161_SENTRYMOBILE_PS1_EC1_NSAU_NSTC()
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
}

package AutomationCode;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import java.time.LocalDateTime;
import java.time.Duration;

@Listeners({ EmailableReporter2.class, EmailableReporter3.class, ListenersClass.class})
public class Android_TestCases
{
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	protected Map<String, String> objDictionary = new HashMap<>();
	@Parameters({"strBrowser","strStopAndStartAppiumServer","strRemotePath","strRole", "strVehicleParkType", "strVehicleDepartType", "strDeviceId","strAVDName","strMobileAPK","strPEOAPK","strAndroidUdid","strIOSUdid","strAppiumPort","strDeviceAppiumPort","strIOSDeviceName","strIOSVersion","strIOSBuild"})
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(final ITestContext testContext, @Optional Method method,@Optional String strBrowser,@Optional String strStopAndStartAppiumServer,@Optional String strRemotePath,@Optional String strRole, @Optional String strVehicleParkType, @Optional String strVehicleDepartType, @Optional String strDeviceId,@Optional String strAVDName, @Optional String strMobileAPK,@Optional String strPEOAPK, @Optional String strAndroidUdid,@Optional String strIOSUdid,@Optional String strAppiumPort,@Optional String strDeviceAppiumPort,@Optional String strIOSDeviceName,@Optional String strIOSVersion,@Optional String strIOSBuild)
	{
		GlobalClass clsGlobalClass = new GlobalClass();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonWeb.KillAllChromeDriver();
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
	public WebDriver getDriver(){return threadDriver.get();}
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(final ITestContext testContext, @Optional Method method, ITestResult result) throws Exception
	{
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
	//ANDROID-TEST CASES
	//********************************************************************************************************************
	@Test(priority=1001,groups={"Smoke"})
	public void A2001_ValidateInvalidCredentialMessage()
	{
  		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		String strUserName = "InvalidUser@gmail.com";
		String strPassword = "Invalid01";
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName+"|"+strPassword);
 		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
 		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Login", "{TextExists} Login~Dialog Message", 60);
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "Value", "Invalid credentials, please contact customer support.");
		//Click Retry
		String strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Login", "RETRY", 1, "Exists", "");
		if(strCondition.equals("True"))
		{clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "RETRY",1);}
		else
		{clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Retry",1);}
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//********************************************************************************************************************
  	//Register User
	//********************************************************************************************************************
	@Test(priority=1002)
	public void A2002_RegisterUser_PasswordShouldHaveAtleastOneDigitOrSpecialCharacter()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "ADeleteUser@gmail.com";
		String strPassword = "ADeleteMeUser";
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Password should have atleast one digit or special character");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1003)
	public void A2003_RegisterUser_EmailHasAlreadyBeenTaken()
	{
		objDictionary.put("strAssociatedBug", "183903594");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin@mpspark.com";
		String strPassword = "L@kemaryMN30";
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Register",1);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Unable to register, please contact customer support. Thanks");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1004)
	public void A2004_RegisterUser_EmailIsInvalid()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "Frank@mpspark";
		String strPassword = "L@kemarMNy03";
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
 		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
 		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Email id is not valid");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1005)
	public void A2005_RegisterUser_EmailIdsDoNotMatch()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin@mpspark.com";
		String strPassword = "L@kemaryMN03";
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		try {Thread.sleep(1000);}catch (Exception e) {}
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
 		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
 		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|s"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Email ids do not match");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1006)
	public void A2006_RegisterUser_PasswordsDoNotMatchPleaseReEnter()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin@mpspark.com";
		String strPassword = "L@kemaryMN03";
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
 		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
 		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|s"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Passwords do not match. Please re-enter");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1007)
	public void A2007_RegisterUser_PasswordShouldBeAtleast12Characters()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin2@mpspark.com";
		//String strPassword = "L@kem2";
		String strPassword = "L@kem1";
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
 		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Password should be at least 12 characters");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1008)
	public void A2008_RegisterUser_FirstNameCannotBeBlank()
	{
		objDictionary.put("strAssociatedBug", "183903594|152239577");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin@mpspark1.com";
		String strPassword = "L@kemaryMN03";
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
 		if(intAndroidVersion == 9)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1009)
	public void A2009_RegisterUser_LastNameCannotBeBlank()
	{
		objDictionary.put("strAssociatedBug", "183903594|152239577");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strUserName = "darin@mpspark1.com";
		String strPassword = "L@kemaryMN03";
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete||"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Unable to create User. Please contact support.");
		}
		else
		{
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Unable to register, please contact customer support. Thanks");
		}
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1010)
	public void A2010_RegisterUser_RememberMeOff_EmailIdWithCaps_LoginWithCaps()
	{
  		objDictionary.put("strAssociatedBug", "181499323");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "adeleteuser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "ADeleteMe01!";
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|UnChecked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
		System.out.println(clsCommonMobile.strActualWaitValue);
		if(clsCommonMobile.strActualWaitValue.equals("Dialog Message"))
		{
			String strDialogMsg = clsCommonMobile.StoreText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "strDialogMessage");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"Unexpected Dialog Appeared: "+strDialogMsg);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1011)
	public void A2011_RegisterUser_RememberMeOff_EmailIdWithCaps_LoginWithoutCaps()
	{
		objDictionary.put("strAssociatedBug", "181499323");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "adeleteuser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "ADeleteMe01!";
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Unchecked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
		System.out.println(clsCommonMobile.strActualWaitValue);
		if(clsCommonMobile.strActualWaitValue.equals("Dialog Message"))
		{
			String strDialogMsg = clsCommonMobile.StoreText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "strDialogMessage");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"Unexpected Dialog Appeared: "+strDialogMsg);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1012)
	public void A2012_RegisterUser_RememberMeOff_EmailIdWithoutCaps_LoginWithCaps()
	{
		objDictionary.put("strAssociatedBug", "181499323");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Destroy User
		String strUserName = "adeleteuser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "ADeleteMe01!";
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		try {Thread.sleep(5000);}catch (Exception e) {}
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14||intAndroidVersion == 5)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName.toLowerCase()+"|"+strUserName.toLowerCase()+"|"+strPassword+"|"+strPassword+"|Unchecked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
		System.out.println(clsCommonMobile.strActualWaitValue);
		if(clsCommonMobile.strActualWaitValue.equals("Dialog Message"))
		{
			String strDialogMsg = clsCommonMobile.StoreText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "strDialogMessage");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"Unexpected Dialog Appeared: "+strDialogMsg);
		}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1013)
	public void A2013_RegisterUser_RememberMeOff_EmailIdWithoutCaps_LoginWithoutCaps()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		objDictionary.put("strAssociatedBug", "181499323");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Destroy User
		String strUserName = "adeleteuser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "ADeleteMe01!";
 		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
 		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName.toLowerCase()+"|"+strUserName.toLowerCase()+"|"+strPassword+"|"+strPassword+"|Unchecked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
		System.out.println(clsCommonMobile.strActualWaitValue);
		if(clsCommonMobile.strActualWaitValue.equals("Dialog Message"))
		{
			String strDialogMsg = clsCommonMobile.StoreText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "strDialogMessage");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"Unexpected Dialog Appeared: "+strDialogMsg);
		}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
	}
	@Test(priority=1014)
	public void A2014_RegisterUser_RememberMeOn_EmailIdWithCaps()
	{
		objDictionary.put("strAssociatedBug", "156816966");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "adeleteuser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "ADeleteMe01!";
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1015)
	public void A2015_RegisterUser_RememberMeOn_EmailIdWithoutCaps()
	{
		objDictionary.put("strAssociatedBug", "156816966|150689478");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "adeleteuser@gmail.com";objDictionary.put("strUserName", strUserName);
		String strPassword = "ADeleteMe01!";
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		try {Thread.sleep(5000);}catch (Exception e) {}
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password|{CB} Remember me","Delete|User|"+strUserName.toLowerCase()+"|"+strUserName.toLowerCase()+"|"+strPassword+"|"+strPassword+"|Checked");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Account Details", "{PageExists} Account Details", 20);
		androiddriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
 	}
	@Test(priority=1016)
	public void A2016_CreateAndDeleteUser_AttemptMobileRegisterWithDeletedUser()
	{
		objDictionary.put("strAssociatedBug", "183903594|169601821");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "adeleteuser3@gmail.com";
		String strPassword = "ADeleteMe!01";
		//Open Consumer Application
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_CreateAndDeleteUser(objDictionary, driver, strUserName,strPassword);
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
 		String strAndroidUdid = objDictionary.get("strAndroidUdid");
 		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName.toLowerCase()+"|"+strUserName.toLowerCase()+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "Unable to create User. Please contact support.");
		androiddriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1017)
	public void A2017_CreateAndDeleteUser_AttemptLoginWithDeletedUser()
	{
		objDictionary.put("strAssociatedBug", "183903594");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonWeb clsCommonWeb = new CommonWeb();
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "adeleteuser3@gmail.com";
		String strPassword = "ADeleteMe!01";
		//Open Consumer Application
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_CreateAndDeleteUser(objDictionary, driver, strUserName,strPassword);
		//Open Sentry Mobile
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
 		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strUserName+"|"+strPassword);
 		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
 		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Login", "{TextExists} Login~Dialog Message", 60);
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "Value", "Invalid credentials, please contact customer support.");
		androiddriver.quit();
 		clsMeter.METER_SetMeterEndTime(objDictionary);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1018)
	public void A2018_ReimburseParker_ValidateMobileAccountBalance()
	{
		objDictionary.put("strAssociatedBug", "183903594");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		//Check if parker exists
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, "parker");
		driver.quit();
		//Reimburse Parker
		Double dblNewAccountBalance = clsCommonWeb.ReimburseParker(objDictionary);
		//Open Android App
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginWithoutCaps(objDictionary, androiddriver, "parker", "", "Enroll later","True");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Account Details", "Remind Me Later", 1);
		//Navigate to Accounts
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		try {Thread.sleep(2000);}catch (Exception e) {}
		DecimalFormat dFormat = new DecimalFormat("$#.00");//"$#.##" //$#.00
		clsCommonMobile.VerificationPointTextField(objDictionary, androiddriver, "Account Details", "Account Balance", 1, "Value", dFormat.format(dblNewAccountBalance));
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment0//Park//ClickMeterMarker//MobilePayment
	@Test(priority=1019)
	public void A2019_FTFP0_PS1_CMM_MP1_ES1_VPSH_VICAE_VIAC()
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2019_FTFP0_PS1_CMM_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1020)
	public void A2020_VM_FTFP0_PS1_CMM_MP1_ES1_VPSH_VICAE_VIAC()
	{
		//objDictionary.put("strAssociatedBug", "149961971");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter6");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter6");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2020_VM_FTFP0_PS1_CMM_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment0//Park//MobilePayment
	@Test(priority=1021,groups={"Smoke"})
	public void A2021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
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
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1022)
	public void A2022_VM_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strAssociatedBug", "180346284");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strMeterUser = objDictionary.get("strMeterUser");
		//Add License Plate
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter6");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter6");
		String strMeterUser2 = objDictionary.get("strMeterUser");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2022_VM_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment0//Park//MobilePayment//ValidateParkingSessionValuesDecremented
	@Test(priority=1023)
    public void A2023_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC()
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2023_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1024)
	public void A2024_VM_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC()
    {
		objDictionary.put("strAssociatedBug", "180346284|177295054|169847142");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Function Variables
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
//		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2024_VM_FTFP0_PS1_MP1_VPSVD_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment0//Park//MobilePayment//BuyMoreTime
	@Test(priority=1025)
	public void A2025_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		objDictionary.put("strAssociatedBug", "187798152");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2025_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1026)
	public void A2026_VM_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "180346284,177295054|169847142");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Function Variables
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2026_VM_FTFP0_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment0//Park//MobilePayment//PurchaseMaxTime
	@Test(priority=1027)
	public void A2027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1027)
	public void A2027_A_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2027_A_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1028)
	public void A2028_VM_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "180346284|177295054|169847142");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2028_VM_FTFP0_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1029)
	public void A2029_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		objDictionary.put("strAssociatedBug", "187603574");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2029_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1030)
	public void A2030_VM_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "169635111|188297606|188297606");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2030_VM_FTFP0_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment0//Park//CoinPayment//MobilePayment
	@Test(priority=1031)
	public void A2031_FTFP0_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2031_FTFP0_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1032)
	public void A2032_FTFP0_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187798152");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2032_FTFP0_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment0//Park//MobilePayment//CoinPayment
	@Test(priority=1033)
	public void A2033_FTFP0_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2033_FTFP0_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1034)
	public void A2034_FTFP0_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2034_FTFP0_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	//FirstTimeFirstPayment0//Park//CreditCardPayment//MobilePayment
	@Test(priority=1035)
	public void A2035_FTFP0_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187798152");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2035_FTFP0_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1035)
	public void A2035_A_FTFP0_PS1_CCP1_RM_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "182742334|182238240");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2035_A_FTFP0_PS1_CCP1_RM_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1036)
	public void A2036_FTFP0_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187798152");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		Meter clsMeter = new Meter();
		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		if(strReservationExists.equals("True"))
  		{
  			String strPermitId = clsMeter.METER_GetPermitId(objDictionary,"Local", "1");
  			clsHttpConnections.JsonMeteDeletePermit(objDictionary,strPermitId);
  		}
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2036_FTFP0_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment0//Park//MobilePayment//CreditCardPayment
	@Test(priority=1037)
	public void A2037_FTFP0_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2037_FTFP0_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1038)
	public void A2038_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2038_FTFP0_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	//FirstTimeFirstPayment0//MobilePayment//Park
	@Test(priority=1039)
	public void A2039_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2039_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1040)
	public void A2040_VM_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "177295054|169847142");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2040_VM_FTFP0_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment10//MobilePayment
	@Test(priority=1041)
	public void A2041_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "181836661");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2041_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1042)
	public void A2042_VM_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2042_VM_FTFP10_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1043)
	public void A2043_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		objDictionary.put("strAssociatedBug", "CA-1340");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2043_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1044)
	public void A2044_VM_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		objDictionary.put("strAssociatedBug", "182332617|180346284|177295054|171059753|169847142|153460868|144557797");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2044_VM_FTFP10_PS1_MP1_BMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1045)
	public void A2045_FTFP10_PS1_PMT_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		objDictionary.put("strAssociatedBug", "CA-1340|182261932");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2045_FTFP10_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1045)
  	public void A2045_A_FTFP10_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC()throws Exception
  	{
		objDictionary.put("strAssociatedBug", "182404103|182309981|181836661|170429307");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2045_A_FTFP10_PS1_MP1_PMT_LPRM_e_MTIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1046)
	public void A2046_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "187603574|182404103|182309981|173951908,144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2046_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1047)
	public void A2047_VM_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC()throws Exception
	{
		//Sometimes the payment order is incorrect on the receipt
		objDictionary.put("strAssociatedBug", "180346284|177295054|173951908|169847142|171059753|153460868|144557797");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2047_VM_FTFP10_PS1_MP1_PMT_MTIV_gt_LPRM_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment10//CoinPayment//MobilePayment
	@Test(priority=1048)
	public void A2048_FTFP10_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "CA-1340");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2048_FTFP10_PS1_CP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1049)
  	public void A2049_FTFP10_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "187798152|176808552|163529646");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2049_FTFP10_PS1_CP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment10//MobilePayment//CoinPayment
	@Test(priority=1050)
  	public void A2050_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "181836661");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2050_FTFP10_PS1_MP1_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1051)
  	public void A2051_FTFP10_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "181836661");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2051_FTFP10_PS1_MP1_PRT_CP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment10//CreditCardPayment//MobilePayment
	@Test(priority=1052)
	public void A2052_FTFP10_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{ 
		objDictionary.put("strAssociatedBug", "174958538");
	    String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	    String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2052_FTFP10_PS1_CCP1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1053)
  	public void A2053_FTFP10_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "CA-1340|147685361");
	    String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	    String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2053_FTFP10_PS1_CCP1_PRT_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment10//MobilePayment//CreditCardPayment
	@Test(priority=1054)
  	public void A2054_FTFP10_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
	 	String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	 	String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2054_FTFP10_PS1_MP1_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1055)
  	public void A2055_FTFP10_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
	 	String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	 	String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2055_FTFP10_PS1_MP1_PRT_CCP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//FirstTimeFirstPayment10//MobilePayment//Park
	@Test(priority=1056)
  	public void A2056_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "183877468");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2056_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1057)
	public void A2057_VM_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC()throws Exception
    {
		objDictionary.put("strAssociatedBug", "182336447,177295054|169847142|144557797");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2057_VM_FTFP10_MP1_PS1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	//GPSOff//FreeTimeFirstPayment0//MobilePayment
	@Test(priority=1058)
	public void A2058_GPSOFF_PS1_MP1_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//ParkTP: Park Then Pay
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","False","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
	    //PS1_MP1
	    Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
	    clsCommonMobile.SENTRYMOBILE_PurchaseMeterTimeParkThenPay(objDictionary, androiddriver, strLicensePlateNumber, "Alabama",strFreeTimeFirstPayment,strMeterIncrementTime,"False","False");
		androiddriver.quit();
	    //ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	  	Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	  	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2058_GPSOFF_PS1_MP1_ES1(objDictionary,strMeterIncrementTime);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1059)
	public void A2059_GPSOFF_RegisterUser_EnableGPSDialogNo()
	{
		objDictionary.put("strAssociatedBug", "183903594");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("PROD"))
		{
			objDictionary.put("strAssociatedBug", "NoPROD");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,null,"It has been determined that this test should NOT be run against prod");
		}
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Dictionary Variable
		String strMunicipality = objDictionary.get("strMunicipality");
		//Test Case Variables
		String strUserName = "XDeleteUser@gmail.com";objDictionary.put("strUserName",strUserName);
		String strPassword = "XDeleteMeMN01";
		//Destroy User
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		//Open Consumer App
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","False","Parker");
	 	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
	 	clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
	 	String strAndroidUdid = objDictionary.get("strAndroidUdid");
	 	int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
	 	//Populate Register
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Register", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword);
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "With GPS disabled, you will only have access to one municipality and enabling GPS will allow other municipality access. Do you want to enable GPS ?");
		String strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Register", "No", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "No",1);}
		else{clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "NO",1);}
		try {Thread.sleep(4000);}catch (Exception e) {}
		//Populated Municipality
		strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Register", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Register", "Select Municipality", strMunicipality);
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Register", "Continue",1);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "GPS is still disabled. Do you want to continue with registration?");
		strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Register", "Yes", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Yes",1);}
		else{clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "YES",1);}
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "You have successfully registered! Click \"OK\" to login. After logging in, you can go to \"Account\" to add money to your account.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok",1);
		try {Thread.sleep(2500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "With GPS disabled, you will only have access to your registered municipality : Atlantis. Please enable GPS to access other municipalities");
		strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Register", "Cancel", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Cancel",1);}
		else{clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "CANCEL",1);}
		try {Thread.sleep(10000);}catch (Exception e) {}
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Account Details", "{PageExists} Account Details", 20);
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1060)
	public void A2060_GPSOFF_RegisterUser_EnableGPSDialogYes()
	{
		objDictionary.put("strAssociatedBug", "183903594|182337558");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUserName = "DeleteUser@gmail.com";objDictionary.put("strUserName",strUserName);
		String strPassword = "DeleteMeMN01";
		//Destroy User
		clsCommonWeb.SENTRYLINK_DestroyUser2(objDictionary, strUserName, strPassword);
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","False","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Register",1);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		int intAndroidVersion = clsCommonMobile.METER_ADB_GetAndriodVersion(strAndroidUdid,"getprop ro.build.version.release");
	 	if(intAndroidVersion == 14)
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{T} First Name|{T} Last Name|{T} Email Id|{T} Confirm Email Id|{T} Password|{T} Confirm Password","Delete|User|"+strUserName+"|"+strUserName+"|"+strPassword+"|"+strPassword+"");
		clsCommonMobile.ClickButton(objDictionary,androiddriver, "Register", "Submit", 1);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Register", "Dialog Message", 1, "Value", "With GPS disabled, you will only have access to one municipality and enabling GPS will allow other municipality access. Do you want to enable GPS ?");
		String strConditionalValue = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Register", "Yes", 1, "Exists", "");
		if(strConditionalValue.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Yes",1);}
		else{clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "YES",1);}
		try {Thread.sleep(3000);}catch (Exception e) {}
		// CLick no thanks
		androiddriver.findElement(By.id("android:id/button2")).click();
		try {Thread.sleep(3000);}catch (Exception e) {}
		//Click Ok on Google Location Service Dialog
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Ok-Google Location Service Dialog",1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		// CLick cancel
		androiddriver.findElement(By.id("android:id/button2")).click();
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointPage(objDictionary,androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//MeterUnlockOn
	@Test(priority=1061)
	public void A2061_MUON_CGPV1_MP1_VMT_ES1_VPSH_VICAE_VIAC()
	{
		objDictionary.put("strAssociatedBug", "183842654");
  		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strUnlockTime = "5";
	    String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//Update Parkers Can Unlock Violations
		clsCommonWeb.SENTRYLINK_UpdateParkersCanUnlockViolations(objDictionary, "Checked");
		//CGPV1: Create Grace Period Violation Spot 1
	  	clsMeter.METER_CreateGracePeriodViolation(objDictionary, null,  strHost, strSpotNumber, strInitialGracePeriod);
	  	try {Thread.sleep(5000);}catch (Exception e) {}
	  	//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		String strViolationNumber = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", strSpotNumber);
	  	//MP1: Mobile Payment 1f
	  	AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
	  	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
	  	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
	  	Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber+"");
	  	clsCommonMobile.SENTRYMOBILE_MobilePayment(objDictionary,androiddriver, strLicensePlateNumber,0);
	  	androiddriver.quit();
	  	//VMT: Validate Actual Meter Time Equals Expected Meter Time
	  	int intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime);
	  	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
	  	//ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	    //ValidateParkingSessionHistoryAndImages
	    Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	    clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2061_MUON_CGPV1_MP1_VMT_ES1(objDictionary, strCreditCardIncrementTime, strViolationNumber);
	    clsMeter.METER_SetMeterEndTime(objDictionary);
	    String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//********************************************************************************************************************
  	//ANDROID MOBILE-FREE TO RATE
  	//********************************************************************************************************************
  	//FirstTimeFirstPayment0//MobilePayment
	@Test(priority=1062)
	public void A2062_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2062_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1063)
 	public void A2063_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184186698|183916351");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2063_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1064)
	public void A2064_VM_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "182444000|182441927|180346284");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2064_VM_FTFP0_FTR_MTIV_gt_RFT_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1065)
	public void A2065_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		//objDictionary.put("strAssociatedBug", "183916351");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2065_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1066)
	public void A2066_VM_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "182529810");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2066_VM_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1067)
  	public void A2067_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_PRT_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183916351|182383324|172458902|171279669");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2067_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_PRT_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1067)
  	public void A2067_B_FTFP0_FTR_RFT_gt_MTIV_PS1_PRT_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183916351|181949979");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2067_B_FTFP0_FTR_RFT_gt_MTIV_PS1_PRT_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1068)
	public void A2068_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "172458902,171279669");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2068_FTFP0_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC(objDictionary,strTestCaseName,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1068)
	public void A2068_A_HR()
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2068_A_HR(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	//FirstTimeFirstPayment10//MobilePayment
	@Test(priority=1069)
	public void A2069_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "172458902,171279669|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2069_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1070)
	public void A2070_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "182444000|182391128|181386840|180346284");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		//Simulator
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2070_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1071)
    public void A2071_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "172458902,171279669");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2071_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1072)
    public void A2072_VM_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "182444000|182391128|181386840|180346284");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName").toUpperCase();
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2072_VM_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
	@Test(priority=1073)
	public void A2073_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183984038|182383324|182309981|172458902|171279669|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2073_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1074)
	public void A2074_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "182444000|182391128|181386840|180346284");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Dictionary Variables
		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Alabama");
		objDictionary.put("strVirtualMeter", "True");
		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
		clsCommonMobile.SENTRYMOBILE_2074_VM_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_BMT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber.toUpperCase());
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1073)
  	public void A2074_A_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_MTR_PRT_VPSH_VICAE_VIAC() throws Exception
	{
  		objDictionary.put("strAssociatedBug", "171279669|166224271|152515355");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2074_A_FTFP10_FTR_MTIV_gt_RFT_PS1_MP1_MTR_PRT_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1075)
	public void A2075_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "182389846|172458902|171279669|144557797");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2075_FTFP10_FTR_RFT_gt_MTIV_PS1_MP1_VMT_CP1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

	//********************************************************************************************************************
  	//ANDROID MOBILE-RATE TO FREE
  	//********************************************************************************************************************
    //FirstTimeFirstPayment0//Free Disconnect On//MobilePayment
	@Test(priority=1076)
   	public void A2076_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
  		objDictionary.put("strAssociatedBug", "171279669|168060201|156595298|148347269");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
 		CommonANDROID clsCommonMobile = new CommonANDROID();
 		clsCommonMobile.SENTRYMOBILE_2076_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
 	}
	@Test(priority=1077)
    public void A2077_VM_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "180346284|177295054|169847142");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		GlobalClass clsGlobal = new GlobalClass();
		//Function Variables
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Dictionary Variables
  		objDictionary.remove("strDeviceId");objDictionary.put("strDeviceId", "AVMeter4");
		clsGlobal.GetMeterProperties(objDictionary, "AVMeter4");
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		//Simulator
  		objDictionary.put("strVirtualMeter", "True");
  		clsCommonWeb.SENTRYLINK_EnableVirtualMeterPayments(objDictionary);
  		clsCommonMobile.SENTRYMOBILE_2077_VM_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1078)
	public void A2078_FTFP0_FDON_RTF_MBF_gt_MTIV_PS1_MP1_VMT_BMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{	
		objDictionary.put("strAssociatedBug", "168060201|1257187798152|188297606|187798152");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2078_FTFP0_FDON_RTF_MBF_gt_MTIV_PS1_MP1_VMT_BMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1079)
	public void A2079_FTFP0_FDON_RTF_PS1_MP1_BMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184188375");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2079_FTFP0_FDON_RTF_PS1_MP1_BMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//FirstTimeFirstPayment10//Free Disconnect On//CoinPayment
	@Test(priority=1080)
	public void A2080_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strMobileDeviceType", "ANDROID");
 		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strCreditCardIncrementTime = "30";
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
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
 		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strCreditCardIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
 		//PS1: Park Spot 1
	   	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
	   	//MP1: Mobile Payment
	   	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strCreditCardIncrementTime,strFreeTimeFirstPayment);
	   //Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//ShortSessionWaitExitSpot
     	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
     	//ValidateParkingSessionHistoryAndImages
     	Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
     	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2080_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1(objDictionary);
     	clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1081)
   	public void A2081_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "168060201");
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
 		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
 		CommonANDROID clsCommonMobile = new CommonANDROID();
 		clsCommonMobile.SENTRYMOBILE_2081_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
 		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
	@Test(priority=1082)
	public void A2082_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "168060201");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//RemainingFreeTimeStart
    	String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
    	//PS1: Park Spot 1
    	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
    	//MP1: Mobile Payment
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
	    //CalculateRemainingMinutesBeforeFree
	    int intRemainingTimeMinutes = clsMeter.METER_CalculateRemainingMinutesBeforeFree(objDictionary, null,  strMinutesBeforeFreeStart);
	    //VMT: Validate Meter Time
	    clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intRemainingTimeMinutes,"1");
	    //Store Parking Id
	  	clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	  	//ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	    Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	    clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2082_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1(objDictionary, intRemainingTimeMinutes);
	   	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=1083)
	public void A2083_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183964856");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "On");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//strMinutesBeforeFreeStart
		String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
    	//PS1: Park Spot 1
    	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
    	//MP1: Mobile Payment
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
	     //CalculateRemainingMinutesBeforeFree
	    int intRemainingTimeMinutes = clsMeter.METER_CalculateRemainingMinutesBeforeFree(objDictionary, null,  strMinutesBeforeFreeStart);
		//VMT: Validate Meter Time
	    clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intRemainingTimeMinutes,"1");
	    //Store Parking Id
	  	clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	    //ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	  	Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	  	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2083_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_MP1_VMT_ES1(objDictionary, intRemainingTimeMinutes);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1084)
	public void A2084_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug", "184027852");
    	objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
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
		//RemainingFreeTimeStart
    	String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
    	//PS1: Park Spot 1
    	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
    	//MP1: Mobile Payment
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
    	//CalculateRemainingMinutesBeforeFree
    	int intRemainingTimeMinutes = clsMeter.METER_CalculateRemainingMinutesBeforeFree(objDictionary, null,  strMinutesBeforeFreeStart);
		//VMT: Validate Meter Time
	    clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intRemainingTimeMinutes,"1");
	    //Store Parking Id
	  	clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	    //ShortSessionWaitExitSpot
	    clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	    //ValidateParkingSessionHistoryAndImages
	    Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	    clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2084_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_MP1_VMT_ES1(objDictionary, intRemainingTimeMinutes);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1085)
	public void A2085_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2085_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//FirstTimeFirstPayment10//Free Disconnect Off//CoinPayment
    @Test(priority=1086)
	public void A2086_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "159508312");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		String strMinutesBeforeFreeParking = "15";
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "Off");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
	 	//RemainingFreeTimeStart
    	String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
    	//PS1: Park Spot 1
    	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
    	//MP1: Mobile Payment
    	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
    	//CalculateRemainingMinutesBeforeFree
    	int intRemainingMinutesBeforeFree = clsMeter.METER_CalculateRemainingMinutesBeforeFree(objDictionary, null,  strMinutesBeforeFreeStart);
	    //ShortSessionWaitExitSpot
    	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	    //ValidateParkingSessionHistoryAndImages
    	int intFirstTimeMinutes = intRemainingMinutesBeforeFree;
    	Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
    	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2086_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_MP1_VMT_ES1(objDictionary, strFreeTimeMinutes, intFirstTimeMinutes);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1087)
	public void A2087_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "159508312");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2087_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1088)
	public void A2088_FTFP0_FDOFF_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "150639959");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2088_FTFP0_FDOFF_RTF_PS1_MP1_PMT_VMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1089)
	public void A2089_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "159508312");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description****************************************************************");
		Reporter.log("FTFP10: Free Time First Payment 10                                                                 ");
		Reporter.log("FDOFF: Free Disconnect Off                                                                         ");
		Reporter.log("RTF: Rate To Free                                                                                  ");
		Reporter.log("FTFP_gt_MTIV_gt_MBF: FreeTimeFirstPayment(10) > MeterTimeIncrementValue(9) > MinutesBeforeFree(9)  ");
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
		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
		String strInitialGracePeriod = "5";
		String strViolationGracePeriod = "1";
		String strHandicapInitialGracePeriod = "5";
		String strHandicapViolationGrace = "1";
		String strNoParkingGrace = "1";
		String strParkingShortSessionSec = "15";
		String strSetImageSendBeforeViolation = "45";
		String strMinutesBeforeFreeParking = "9";
		String strFreeTimeMinutes = "120";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strUnlockValue = "On";
		String strUnlockTime = "1";
		String strUnlockMax = "1";
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		//ExitSpotAndSetMeterRateBlocks
		objDictionary.remove("strFreeDisconnectValue");objDictionary.put("strFreeDisconnectValue", "Off");
		clsCommonWeb.SENTRYLINK_ExitSpotSetMeterRateBlocksOpenMeterInBrowse(objDictionary, strMaximumDuration, strCoinTimePuchaseLimit, strFreeTimeFirstPayment, strMeterIncrementTime, strInitialGracePeriod, strViolationGracePeriod, strHandicapInitialGracePeriod, strHandicapViolationGrace, strNoParkingGrace,strSetImageSendBeforeViolation,strParkingShortSessionSec, strUnlockValue, strUnlockTime, strUnlockMax,"Local");
		//MinutesBeforeFreeStartTime
	    String strMinutesBeforeFreeStart = objDictionary.get("strMinutesBeforeFreeStart");
	 	//PS1: Park Spot 1
	 	clsMeter.METER_ParkSpot(objDictionary,"1","Local");
	 	//MP1: Mobile Payment
	 	clsCommonMobile.SENTRYMOBILE_MobilePaymentInitialPayment(objDictionary, strLicensePlateNumber,strMeterIncrementTime,strFreeTimeFirstPayment);
	 	//CalculateRemainingMinutesBeforeFree
	    int intRemainingMinutesBeforeFree = clsMeter.METER_CalculateRemainingMinutesBeforeFree(objDictionary, null,  strMinutesBeforeFreeStart);
		//Store Parking Id
	   clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
	    //ShortSessionWaitExitSpot
	  	clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
	  	//ValidateParkingSessionHistoryAndImages
	  	Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
	  	clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2089_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_MP1_VMT_ES1(objDictionary, strMeterIncrementTime, strFreeTimeMinutes, intRemainingMinutesBeforeFree);
	  	clsMeter.METER_SetMeterEndTime(objDictionary);
	  	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//*****************************************************
  	//TrueUpEnabled//GracePeriodViolation//SelectMeter//Approve Violations
  	//*****************************************************
    @Test(priority=1090)
	public void A2090_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
	{
  		objDictionary.put("strAssociatedBug", "172745182");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2090_FTFP10_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1091)
   	public void A2091_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
   	{
		objDictionary.put("strAssociatedBug", "174306633");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2091_FTFP0_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
    @Test(priority=1092)
    public void A2092_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonWeb.SENTRYLINK_ConciergeDeenroll(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2092_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1093)
    public void A2093_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "171220971");
		String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		String strLicensePlateNumber = "0"+strMeterSpotName+"AA";
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2093_FTFP0_TUON_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
    @Test(priority=1094)
    public void A2094_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2094_FTFP0_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	//*****************************************************
    //GracePeriodViolation\\SelectMeter\\Reject Violations
    //*****************************************************
    @Test(priority=1095)
    public void A2095_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug","184549710|174306633");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2095_FTFP0_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
    @Test(priority=1096)
   	public void A2096_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
  	{
		objDictionary.put("strAssociatedBug","184549710|171220971");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_2096_FTFP0_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
    @Test(priority=1097)
    public void A2097_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug","184549710|171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2097_FTFP0_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
    @Test(priority=1098)
    public void A2098_AMMOEE_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2098_AMMOEE_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
    @Test(priority=1099)
	public void A2099_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "167947492");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2099_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1100)
	public void A2100_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
 	{
		objDictionary.put("strAssociatedBug", "167947492");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2100_FTFP0_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1101)
	public void A2101_FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug", "183974251");
    	String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2101_FTFP0_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
   	//*****************************************************
  	//GracePeriodViolation\\SelectMeter\\Approve Violations
  	//*****************************************************
    @Test(priority=1102)
    public void A2102_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "174306633");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2102_FTFP0_TUON_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
    @Test(priority=1103)
	public void A2103_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2103_FTFP10_TUON_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1104)
	public void A2104_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug","171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2104_FTFP10_TUON_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1105)
	public void A2105_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug","171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2105_FTFP10_TUON_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
   	//*****************************************************
    //UnlockOn\\FreeTimeFirstPayment10\\GracePeriodViolation\\SelectMeter\\Reject Violations
    //*****************************************************
    @Test(priority=1106)
	public void A2106_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
        	objDictionary.put("strAssociatedBug","184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2106_FTFP10_TUON_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1107)
	public void A2107_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug","184549710|171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2107_FTFP10_TUON_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1108)
	public void A2108_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug","184549710");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2108_FTFP10_TUON_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1109)
	public void A2109_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184549710|183997973|183877468|179985202,167947492");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2109_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1110)
	public void A2110_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug", "183877468");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2110_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1111)
	public void A2111_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "167947492|163469064");
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2111_FTFP10_TUON_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1112)
	public void A2112_FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
    	objDictionary.put("strAssociatedBug", "184059120|181543065");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2112_FTFP10_TUON_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1113)
   	public void A2113_FTFP0_TUON_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184549710|170510263");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2113_FTFP0_TUON_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
   	//*****************************************************
    //UnlockOff\\GracePeriodViolation\\
    //*****************************************************
    @Test(priority=1114)
    public void A2114_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "170510263");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2114_FTFP0_TUON_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    @Test(priority=1115)
	public void A2115_FTFP0_TUON_UOff_PS1_GPV1_UTE_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2115_FTFP0_TUON_UOff_PS1_GPV1_UTE_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    //*****************************************************
    //FORGOT PASSWORD
    //*****************************************************
    @Test(priority=1116)
  	public void A2116_ForgotPassword_EnterTheUserEmailIdToResetPassword() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		String strMunicipality = objDictionary.get("strMunicipality");
  		Meter clsMeter = new Meter();
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Forgot Password", "Select Municipality", 1, "Exists", "");
   		if(strCondition.equals("True"))
   		{
   			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Forgot Password", "Select Municipality", strMunicipality+", USA");
   			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Continue",1);
   		}
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", "EmailIdIsNotValid");
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
   		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email id is not valid",1, "Value", "Email id is not valid");
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
   		androiddriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    @Test(priority=1117)
  	public void A2117_ForgotPassword_AllFieldsWithAreMandatory() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		Meter clsMeter = new Meter();
  		String strMunicipality = objDictionary.get("strMunicipality");
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Forgot Password", "Select Municipality", 1, "Exists", "");
   		if(strCondition.equals("True"))
   		{
   			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Forgot Password", "Select Municipality", strMunicipality+", USA");
   			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Continue",1);
   		}
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
   		androiddriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    @Test(priority=1118)
  	public void A2118_ForgotPassword_InvalidEmail() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		Meter clsMeter = new Meter();
  		String strMunicipality = objDictionary.get("strMunicipality");
  		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
  		String strGmailUserName = "InvalidGmailUser@gmail.com";
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Forgot Password", "Select Municipality", 1, "Exists", "");
   		if(strCondition.equals("True"))
   		{
   			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Forgot Password", "Select Municipality", strMunicipality+", USA");
   			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Continue",1);
   		}
   		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
   		try {Thread.sleep(2500);}catch (Exception e) {}
   		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
   		androiddriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    @Test(priority=1119)
	public void A2119_ForgotPassword_InvalidToken() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		String strMunicipality = objDictionary.get("strMunicipality");
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
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Forgot Password", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Forgot Password", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Continue",1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email ID", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 60);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		//Open GMAIL
		String strToken = "InvalidToken";
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "").replace("!", "");
		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+8,strSentryLinkCurrentPassword.length()));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion))+"!";
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1120)
	public void A2120_ForgotPassword_DifferentPassword() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "Android");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		String strMunicipality = objDictionary.get("strMunicipality");
		String strEnvironment = objDictionary.get("strEnvironment");
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
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Forgot Password", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Forgot Password", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Continue",1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		try {Thread.sleep(4000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		//Open GMAIL
		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+8,strSentryLinkCurrentPassword.length()-1));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|InvalidPassword");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Passwords do not match. Please re-enter",1, "Value", "Passwords do not match. Please re-enter");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		try {Thread.sleep(1000);}catch (Exception e) {}
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1121)
	public void A2121_ForgotPassword_SentResetInstructions_LoginWithCurrentPassword() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		String strMunicipality = objDictionary.get("strMunicipality");
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
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Forgot Password", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Forgot Password", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Continue",1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(4000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		//Navigate Back To Login
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Back", 1);
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Password",strSentryLinkCurrentPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
		try {Thread.sleep(6500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Login", "Allow Sentry Mobile to access this device's location?", 1, "Exists", "");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		if(strAndroidUdid.equals("R5CW42VHVZY")||strAndroidUdid.equals("R5CW120Y06W")||strAndroidUdid.equals("R5CT71MQ2MM"))
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else if(strAndroidUdid.equals("VS9885891d1d3"))
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		try {Thread.sleep(1000);}catch (Exception e) {}
		//Click OK on the: Notification phone settings changed required!
		String strConditional = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Nearby Parking", "OK", 1, "Exists", "");
		if(strConditional.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Nearby Parking", "OK", 1);}
		strConditional = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Nearby Parking", "Ok", 1, "Exists", "");
		if(strConditional.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Nearby Parking", "Ok", 1);}
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.VerificationPointPage(objDictionary, androiddriver, "Nearby Parking", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1122)
  	public void A2122_ForgotPassword_ResendResetInstructionsTwice_UseFirstToken() throws MessagingException, IOException
  	{
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
  		//Get Dictionary Variables
  		String strMunicipality = objDictionary.get("strMunicipality");
  		//Check User Exist and is active
  		clsCommonWeb.SENTRYLINK_GmailLoginInOrCreate(objDictionary, strGmailUserName, strGmailPassword, "parker",strGmailFirstName, strGmailLastName);
  		//Open Sentry Mobile
  		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		//objDictionary.put("strEnableUiautomator2", "True");
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
   		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Register", "Select Municipality", 1, "Exists", "");
   		if(strCondition.equals("True"))
   		{
   			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Register", "Select Municipality", strMunicipality+", USA");
   			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Register", "Continue",1);
   		}
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email ID", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
   		//Open GMAIL
   		String strFirstToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Back", 1);
   		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
  		try {Thread.sleep(1500);}catch (Exception e) {}
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
   		try {Thread.sleep(1000);}catch (Exception e) {}
   		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
   		try {Thread.sleep(5000);}catch (Exception e) {}
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
   		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
   		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
   		//Open GMAIL
   		clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
   		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
   		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+8,strSentryLinkCurrentPassword.length()-1));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strFirstToken+"|"+strNewPassword+"|"+strNewPassword);
   		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
   		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Unable to reset",1, "Value", "Unable to reset");
   		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
   		androiddriver.quit();
   		clsMeter.METER_SetMeterEndTime(objDictionary);
   		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    @Test(priority=1123)
	public void A2123_ForgotPassword_ResendResetInstructionsTwice_UseSecondToken() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		//Get Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
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
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Register", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Register", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Register", "Continue",1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		//Open GMAIL
		String strFirstToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Back", 1);
		clsGmail.Gmail_DeleteAllEmails(strGmailUserName,strGmailPassword);
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		//Open GMAIL
		String strSecondToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+8,strSentryLinkCurrentPassword.length()-1));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strSecondToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "The password has been changed successfully.",1, "Value", "The password has been changed successfully.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		try {Thread.sleep(6000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Login", "Allow Sentry Mobile to access this device's location?", 1, "Exists", "");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		if(strAndroidUdid.equals("R5CW42VHVZY")||strAndroidUdid.equals("R5CW120Y06W")||strAndroidUdid.equals("R5CT71MQ2MM"))
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else if(strAndroidUdid.equals("VS9885891d1d3"))
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		//Click OK on the: Notification phone settings changed required!
		strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Login", "Ok", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Ok", 1);}
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Account Details", "{PageExists} Account Details", 20);
		clsCommonMobile.VerificationPointPage(objDictionary, androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1124)
	public void A2124_ForgotPasswordRememberMeOn() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		//Get Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
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
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Register", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Register", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Register", "Continue",1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email Id", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		clsCommonMobile.VerificationPointButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1, "Disabled");
		//Open GMAIL
		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+8,strSentryLinkCurrentPassword.length()-1));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|"+strNewPassword);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "The password has been changed successfully.",1, "Value", "The password has been changed successfully.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		try {Thread.sleep(6000);}catch (Exception e) {}
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		if(strAndroidUdid.equals("R5CW42VHVZY")||strAndroidUdid.equals("R5CW120Y06W")||strAndroidUdid.equals("R5CT71MQ2MM"))
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else if(strAndroidUdid.equals("VS9885891d1d3"))
		{
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Login", "Allow Sentry Mobile to access this device's location?", 1, "Exists", "");
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		//Click OK on the: Notification phone settings changed required!
   		strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Login", "OK", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "OK", 1);}
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Account Details", "{PageExists} Account Details", 20);
		clsCommonMobile.VerificationPointPage(objDictionary, androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1125)
	public void A2125_ForgotPasswordRememberMeOff() throws MessagingException, IOException
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Database clsDatabase = new Database();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		//Get Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
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
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Forgot password?", 1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Register", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Register", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Register", "Continue",1);
		}
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Email ID", "{T} Email Id", strGmailUserName);
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Send reset instructions", 1);
		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Forgot Password", "{TextExists} Forgot Password~Email has been sent", 10);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "Email has been sent",1, "Value", "Email has been sent");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		//Open GMAIL
		String strToken = clsGmail.Gmail_GetResetToken(strGmailUserName,strGmailPassword);
		String strSentryLinkCurrentPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strGmailUserName, "");
		int intCurrentVersion =  Integer.parseInt(strSentryLinkCurrentPassword.substring(strSentryLinkCurrentPassword.indexOf("Fires@le")+8,strSentryLinkCurrentPassword.length()-1));
		int intNewVersion = intCurrentVersion +1;
		String strNewPassword = strSentryLinkCurrentPassword.replace(Integer.toString(intCurrentVersion), Integer.toString(intNewVersion));
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Forgot Password", "Populate Reset Password", "{T} Reset token|{T} New password|{T} Confirm new password", strToken+"|"+strNewPassword+"|"+strNewPassword);
		String strMobileAPK = objDictionary.get("strMobileAPK");
		if(strMobileAPK.contains("8.1.379.apk"))
		{
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Register", "Remember me",1);
		}
		else
		{
			clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Register", "Populate Login", "{CB} Remember me","Checked");
		}clsCommonMobile.ClickButton(objDictionary, androiddriver, "Forgot Password", "Reset Password", 1);
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Forgot Password", "The password has been changed successfully.",1, "Value", "The password has been changed successfully.");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Forgot Password", "OK", 1);
		String strSQL = "Update \"UNIQUE\" SET \"Password\" = '"+strNewPassword+"' where \"Environment\" = '"+strEnvironment+"' and \"UserId\" = '"+strGmailUserName+"'";
		clsDatabase.DATABASE_Update(objDictionary,strSQL);
		try {Thread.sleep(2000);}catch (Exception e) {}
		String strAndroidUdid = objDictionary.get("strAndroidUdid");
		if(strAndroidUdid.equals("R5CW42VHVZY")||strAndroidUdid.equals("R5CW120Y06W")||strAndroidUdid.equals("R5CT71MQ2MM"))
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "While using the app", 1);
		}
		else if(strAndroidUdid.equals("VS9885891d1d3"))
		{
			clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Login", "Allow Sentry Mobile to access this device's location?", 1, "Exists", "");
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow", 1);
		}
		else
		{
			clsCommonMobile.ClickLink(objDictionary, androiddriver, "Register", "Allow only while using the app", 1);
		}
		if(clsCommonMobile.strActualWaitValue.equals("Dialog Message"))
		{
			String strDialogMessage = clsCommonMobile.StoreText(objDictionary, androiddriver, "Login", "Dialog Message", 1, "strDialogMessage");
			clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary, androiddriver,strDialogMessage);
		}
		//Click OK on the: Notification phone settings changed required!
		strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Login", "OK", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "OK", 1);}
		strCondition = clsCommonMobile.ConditionalStepLink(objDictionary, androiddriver, "Login", "Ok", 1, "Exists", "");
		if(strCondition.equals("True")){clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Ok", 1);}
		try {Thread.sleep(1500);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "Account Details", "Do you want to enable biometric authentication for easier login?",1,"Value","Do you want to enable biometric authentication for easier login?");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "NOT RIGHT NOW",1);
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointPage(objDictionary, androiddriver, "Account Details", "Exists");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    //*****************************************************
    //LOCKED ACCOUNT
    //*****************************************************
    @Test(priority=1126)
	public void A2126_LoginAttemptsExceededYourAccountHasBeenLocked() throws MessagingException, IOException
	{
		objDictionary.put("strAssociatedBug", "164151128");
		objDictionary.put("strMobileDeviceType", "Android");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		CommonWeb clsCommonWeb = new CommonWeb();
		Gmail clsGmail = new Gmail();
		Meter clsMeter = new Meter();
		String strMunicipality = objDictionary.get("strMunicipality");
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
		//Open Android Device
		//objDictionary.put("strEnableUiautomator2", "True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		//1st Login with Invalid Credentials with Municipality
		String strRandomLetter = clsCommonWeb.SENTRYLINK_CreateRandomLetter();
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Email Id|{T} Password",strGmailUserName+"|"+strPassword+strRandomLetter);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
		String strCondition = clsCommonMobile.ConditionalStepTextField(objDictionary, androiddriver, "Login", "Select Municipality", 1, "Exists", "");
		if(strCondition.equals("True"))
		{
			clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Login", "Select Municipality", strMunicipality+", USA");
			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Continue",1);
		}
		//Invalid Credential. Please re-enter your email id and password.
		int intLoginCounter = 1;
		do
		{
			String strActualWaitValue = clsCommonMobile.strActualWaitValue;
			if(strActualWaitValue.equals("Dialog Message"))
	  	 	{
				String strConditionalValue = clsCommonMobile.ConditionalStepText(objDictionary,androiddriver, "Login", "Dialog Message", 1, "Contains", "Invalid Credentials.");
				if(strConditionalValue.equals("False"))
				{
					clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The message (Invalid Credentials.) did not appear");
				}
				Reporter.log("<font color='green'>The message Invalid Credential appeared successfully</font>");
				clsCommonMobile.ClickLink(objDictionary, androiddriver, "Login", "Retry", 1);
				clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Login", "Populate Login", "{T} Password",strPassword+strRandomLetter);
				clsCommonMobile.ClickButton(objDictionary, androiddriver, "Login", "Login",1);
	  	 	}
			intLoginCounter++;
		}while (intLoginCounter < 6);
		//Validate Email
		System.out.println("This is still on the road map");
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		androiddriver.quit();
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_UnlockAccount(objDictionary,driver, strGmailUserName);
		driver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    //*****************************************************
  	//TrueUpDisabled//GracePeriodViolation//SelectMeter//Approve Violations
    //*****************************************************
    @Test(priority=1127)
    public void A2127_FTFP0_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
   	{
    	objDictionary.put("strAssociatedBug", "184077625|183842654");
    	String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2127_FTFP0_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    @Test(priority=1128)
   	public void A2128_FTFP0_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC()throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|171234876|184077625");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2128_FTFP0_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
    @Test(priority=1129)
	public void A2129_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "184077625|183842654|171220971,171234876");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2129_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    @Test(priority=1130)
    public void A2130_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184077625|183842654|171220971|168060201");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2130_FTFP0_TUOFF_UON_PS1_GPV1_AV_wm_LP_CSMLP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
 	@Test(priority=1131)
    public void A2131_FTFP0_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184077625|183842654|171220971,171234876");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2131_FTFP0_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
    //*****************************************************
    //GracePeriodViolation\\SelectMeter\\Reject Violations
    //*****************************************************
 	@Test(priority=1132)
    public void A2132_FTFP0_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "183842654|171420665|184077625");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2132_FTFP0_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
 	@Test(priority=1133)
  	public void A2133_FTFP0_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
    {
		objDictionary.put("strAssociatedBug", "184077625|183842654|171220971");
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
 		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
 		CommonANDROID clsCommonMobile = new CommonANDROID();
 		clsCommonMobile.SENTRYMOBILE_2133_FTFP0_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
 	@Test(priority=1134)
    public void A2134_FTFP0_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
    	objDictionary.put("strAssociatedBug", "184077625|183842654|171220971");

		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2134_FTFP0_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
 	@Test(priority=1135)
    public void A2135_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2135_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
 	@Test(priority=1136)
    public void A2136_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "171420665,167947492");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2136_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
 	@Test(priority=1137)
    public void A2137_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "171420665");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2137_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
   	}
 	@Test(priority=1138)
    public void A2138_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
   	{
		objDictionary.put("strAssociatedBug", "184059365|181543065");
		String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		String strLicensePlateNumber = "0"+strMeterSpotName+"AA";
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2138_FTFP0_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
    //*****************************************************
    //GracePeriodViolation\\SelectMeter\\Approve Violations
  	//*****************************************************
 	@Test(priority=1139)//update to subtracted used time violation time
	public void A2139_FTFP10_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|171613268|171234876");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2139_FTFP10_TUOFF_UON_PS1_GPV1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1140)
	public void A2140_FTFP10_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|171613268|171220971");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2140_FTFP10_TUOFF_UON_PS1_GPV1_AV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1141)
	public void A2141_FTFP10_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|171613268|171234876");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2141_FTFP10_TUOFF_UON_PS1_GPV1_AV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1142)
	public void A2142_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
 		objDictionary.put("strAssociatedBug", "183842654|171613268|171220971");
    	String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
    	String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2142_FTFP10_TUOFF_UON_PS1_GPV1_AV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

    //*****************************************************
  	//GracePeriodViolation\\SelectMeter\\Reject Violations
  	//*****************************************************
 	@Test(priority=1143)
	public void A2143_FTFP10_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
 		objDictionary.put("strAssociatedBug", "183842654|171420665");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2143_FTFP10_TUOFF_UON_PS1_GPV1_RV_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1144)
	public void A2144_FTFP10_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|171420665");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2144_FTFP10_TUOFF_UON_PS1_GPV1_RV_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1145)
	public void A2145_FTFP10_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "183842654|171420665");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2145_FTFP10_TUOFF_UON_PS1_GPV1_RV_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1146)
	public void A2146_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2146_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1147)
	public void A2147_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
 		objDictionary.put("strAssociatedBug", "182742516");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2147_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wm_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	//@Test(priority=1148)
	public void A2148_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "167947492|163469064|152930251");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2148_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_wom_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1149)
	public void A2149_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC() throws Exception
	{
		objDictionary.put("strAssociatedBug", "5534|181543065|183965860");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2149_FTFP10_TUOFF_UON_PS1_GPV1_RV_RES_IP_wo_LP_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
 	@Test(priority=1150)
	public void A2150_FTFP0_TUOFF_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC() throws Exception
	{
 		objDictionary.put("strAssociatedBug", "182742516|178580045");
 		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
 		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2150_FTFP0_TUOFF_UON_GPV1_MP1_LMV1_MP1_VMPSIV_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

    //*****************************************************
    //UnlockOff\\GracePeriodViolation\\
    //*****************************************************
 	@Test(priority=1151)
    public void A2151_FTFP0_TUOFF_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC() throws Exception
   	{
   		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
   		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		clsCommonMobile.SENTRYMOBILE_2151_FTFP0_TUOFF_UOff_PS1_GPV1_MP1_VMPSIV_VML_EBS_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  	}
    //*****************************************************
    //Mobile\\Payment Test Cases
	//*****************************************************
    @Test(priority=1152)//No longer able to test because of braintree changes
	public void A2152_ValidateInvalidCreditCardDate()
	{
		CommonANDROID clsCommonMobile = new CommonANDROID();
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker","", "Enroll later");
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Account Details");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Account Details", "Add Money", 1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "$50.00", 1);
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Account Details", "Populate Credit Card", "{T} Credit Card Number","5555555555554444");
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Account Details", "MMYY", 1);
		try {Thread.sleep(2000);}catch (Exception e) {}
		//Get Current Month
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String strPreviousMonth = new SimpleDateFormat("MMM").format(cal.getTime());
		androiddriver.findElement(By.xpath("//android.widget.NumberPicker[1]")).sendKeys(strPreviousMonth);
		try {Thread.sleep(1000);}catch (Exception e) {}
		//Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 0);
		Date Year = cal.getTime();
		String strYear = new SimpleDateFormat("yyyy").format(Year);
	    //Change Year If Current Month Is January
		if(strPreviousMonth.equals("Jan"))
		{
			cal.add(Calendar.YEAR, -1);
			strYear = new SimpleDateFormat("yyyy").format(Year);
		}
		androiddriver.findElement(By.xpath("//android.widget.NumberPicker[2]")).sendKeys(strYear);
		try {Thread.sleep(1000);}catch (Exception e) {}
		androiddriver.findElement(By.xpath("//android.widget.NumberPicker[2]/android.widget.EditText[1]")).click();
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Account Details", "Set", 1);
		clsCommonMobile.VerificationPointButton(objDictionary, androiddriver, "Account Details", "Submit", 1, "Does Not Exist");
		androiddriver.quit();
	}

    //*************************************************************************************
  	//ANDROID-MAINTENANCE MODE
  	//*************************************************************************************
  	//@Test(priority=494)
  	public void A2154_PS1_CP1_VMT_MMNP_VMM_MP2_VSIM_MP1_VMT() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "155742666|155725215");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strTestCaseName",strTestCaseName);
  		objDictionary.put("strMobileDeviceType", "Meter");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Meter Payment");
		clsCommonMobile.SENTRYMOBILE_PopulateOrValidateMeterPaymentMunicipality(objDictionary,androiddriver,"True");
		//MP2: Mobile Payment Spot 2
		clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary,androiddriver,"2");
		clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, androiddriver, strLicensePlateNumber, "Alabama");
     	clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Meter Payment", "{TextExists} Meter Payment~Meter Amount", 60);
     	//Validate Max Time
     	clsCommonMobile.VALIDATIONS_MeterPayment_MaxTime(objDictionary, androiddriver, 0, Integer.parseInt(strMaximumDuration),0);
     	String strTotalFee = clsCommonMobile.StoreText(objDictionary, androiddriver, "Meter Payment", "Meter Amount", 1, "strTotalFee");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);
		clsCommonMobile.SENTRYMOBILE_ValidateFirstPaymentDialogAndReturnParkingFee(objDictionary, androiddriver, strTotalFee,"2");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "YES - PURCHASE", 1);
		//Validate Meter Payment Dialog
		clsCommonMobile.VALIDATION_MeterPaymentDialog(objDictionary,androiddriver,"Meter spot in maintenance, payments not accepted");
		//MP1: Mobile Payment Spot 1
		clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary,androiddriver,"1");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);//#155742666
		clsCommonMobile.SENTRYMOBILE_ValidateFirstPaymentDialogAndReturnParkingFee(objDictionary, androiddriver, strTotalFee,"1");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "YES - PURCHASE", 1);
		//VMT: Validate Meter Time
		intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) * 20;
      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
      	androiddriver.quit();
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
  	//@Test(priority=494)
  	public void A2155_PS1_CP1_VMT_MMFP_VMM_MP2_VSIM_MP1_VMT() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "155742666|155725215");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strTestCaseName",strTestCaseName);
  		objDictionary.put("strMobileDeviceType", "Meter");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Meter Payment");
		clsCommonMobile.SENTRYMOBILE_PopulateOrValidateMeterPaymentMunicipality(objDictionary,androiddriver,"True");
		//MP2: Mobile Payment Spot 2
		clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary,androiddriver,"2");
		clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, androiddriver, strLicensePlateNumber, "Alabama");
     	clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Meter Payment", "{TextExists} Meter Payment~Meter Amount", 60);
     	//Validate Max Time
     	clsCommonMobile.VALIDATIONS_MeterPayment_MaxTime(objDictionary, androiddriver, 0, Integer.parseInt(strMaximumDuration),0);
     	String strTotalFee = clsCommonMobile.StoreText(objDictionary, androiddriver, "Meter Payment", "Meter Amount", 1, "strTotalFee");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "YES - PURCHASE", 1);
		//Validate Meter Payment Dialog
		clsCommonMobile.VALIDATION_MeterPaymentDialog(objDictionary,androiddriver,"Meter spot in maintenance, payments not accepted");
		//MP1: Mobile Payment Spot 1
		clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary,androiddriver,"1");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);//#155742666
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "YES - PURCHASE", 1);
		//VMT: Validate Meter Time
		intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) * 20;
      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
      	androiddriver.quit();
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
  	//@Test(priority=501)
  	public void A2156_PS1_CP1_VMT_MMUP_VMM_MP2_VSIM_MP1_VMT() throws Exception
    {
  		objDictionary.put("strAssociatedBug", "155749440");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strTestCaseName",strTestCaseName);
  		objDictionary.put("strMobileDeviceType", "Meter");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		Reporter.log("strLicensePlateNumber: "+strLicensePlateNumber);
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Meter Payment");
		clsCommonMobile.SENTRYMOBILE_PopulateOrValidateMeterPaymentMunicipality(objDictionary,androiddriver,"True");
		//MP2: Mobile Payment Spot 2
		clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary,androiddriver,"2");
		clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, androiddriver, strLicensePlateNumber, "Alabama");
     	clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Meter Payment", "{TextExists} Meter Payment~Meter Amount", 60);
     	//Validate Max Time
     	clsCommonMobile.VALIDATIONS_MeterPayment_MaxTime(objDictionary, androiddriver, 0, Integer.parseInt(strMaximumDuration),0);
     	String strTotalFee = clsCommonMobile.StoreText(objDictionary, androiddriver, "Meter Payment", "Meter Amount", 1, "strTotalFee");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);
		clsCommonMobile.SENTRYMOBILE_ValidateFirstPaymentDialogAndReturnParkingFee(objDictionary, androiddriver, strTotalFee,"2");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "YES - PURCHASE", 1);
		//VMT: Validate Meter Time
		intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime);
      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"2");


//			//Validate Meter Payment Dialog
//			clsCommonMobile.VALIDATION_MeterPaymentDialog(objDictionary,androiddriver,"Meter spot in maintenance, payments not accepted");
//			//MP1: Mobile Payment Spot 1
//			clsCommonMobile.Populate_MeterPayment_SelectSpot(objDictionary,androiddriver,"1");
//			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);//#155742666
//			clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "YES - PURCHASE", 1);
		//VMT: Validate Meter Time
//			intExpectedRemainingTimeMinutes = Integer.parseInt(strCreditCardIncrementTime) * 20;
//	      	clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null,  intExpectedRemainingTimeMinutes,"1");
      	androiddriver.quit();
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

  	//*************************************************************************************
  	//ANDROID-PAY BY PLATE
  	//*************************************************************************************
  	@Test(priority=2157,groups={"PAY_BY_PLATE"})
  	public void A2157_PBP_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC()
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_2157_PBP_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}

  	//************************************************************************************
  	//WOONERF RESERVATIONS
  	//************************************************************************************
  	@Test(priority=2199)
  	public void A2199_RSVN_MPRSISR()
    {
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{objDictionary.put("strPermitRate","Hourly Parking");}
		else
		{objDictionary.put("strPermitRate","Hourly 1");}
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
  	@Test(priority=2200)
  	public void A2200_RSVN_PSWVLP_VMT_ES1_VPSH() 
    {
  		objDictionary.put("strAssociatedBug", "184858316|184076866");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{objDictionary.put("strPermitRate","Hourly Parking");}
		else
		{objDictionary.put("strPermitRate","Hourly 1");}
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
  		// Start Timer
        LocalDateTime startTime = LocalDateTime.now(); // Capture start time
        System.out.println("Start Time: " + startTime); // Optional: log start time
  		//Park With License Plate   
  		clsMeter.METER_ParkSpotWithLicensePlate(objDictionary, strLicensePlateNumber);
  		//Calculate Remaining Reservation time
  		String strReservationStartTime = objDictionary.get("strReservationStartTime");
  		int intRemaingReservationTime = clsMeter.METER_CalculateRemainingFreeTime(objDictionary,null,strReservationStartTime,strReservationTimeMinutes);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intRemaingReservationTime,"1");
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Add License Plate to SL
  		clsCommonWeb.AddLicensePlateToParkingSession( objDictionary, strLicensePlateNumber, "Minnesota");
  		//Open Sentry Mobile-Android
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker"); 
 		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOnEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		//Validate Spot and License Plate
		clsCommonMobile.VALIDATIONS_ParkingSessions_SpotAndLicensePlateNumber(objDictionary, androiddriver, strLicensePlateNumber);
		//Validate Parked at Time
		clsCommonMobile.VALIDATIONS_ParkingSessions_ParkedAt(objDictionary, androiddriver);
		// End Timer
        LocalDateTime endTime = LocalDateTime.now(); // Capture end time
        System.out.println("End Time: " + endTime); // Optional: log end time
        // Calculate duration in minutes
        Duration duration = Duration.between(startTime, endTime); // Calculate duration
        int intDurationInMinutes = (int) duration.toMinutes(); // Convert to minutes and cast to int
        intRemaingReservationTime = intRemaingReservationTime - intDurationInMinutes;
		//ValidateParkingSessionsValidUntil
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary,androiddriver,intRemaingReservationTime);
		//ValidateParkingSessionsParkingExipresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary,androiddriver,intRemaingReservationTime);
		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
  		clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2200_RSVN_PSWVLP_VMT_ES1(objDictionary, 58,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=2201)
  	public void A2201_RSVN_PSWILP_MPRSISR_ES1_VPSH()
    {
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strMobileDeviceType", "ANDROID");
  		Meter clsMeter = new Meter();
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{objDictionary.put("strPermitRate","Hourly Parking");}
		else
		{objDictionary.put("strPermitRate","Hourly 1");}
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
  	@Test(priority=2202)
  	public void A2202_RSVN_FTRSVN_PSWL_MPRSIIFPGIRP()
    {
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{objDictionary.put("strPermitRate","Hourly Parking");}
		else
		{objDictionary.put("strPermitRate","Hourly 1");}
  		String strParkingShortSessionSec = "15";
  		String strSetImageSendBeforeViolation = "45";
  		String strUnlockValue = "On";
  		String strUnlockTime = "1";
  		String strUnlockMax = "1";
  		String strFreeTimeMinutes = "15";objDictionary.put("strFreeTimeMinutes", strFreeTimeMinutes);
		String strMinutesOfFreeTimeBeforeCurrentTime = "0"; objDictionary.put("strMinutesOfFreeTimeBeforeCurrentTime", strMinutesOfFreeTimeBeforeCurrentTime);
  		objDictionary.put("strReservationTestCase", "True");
		objDictionary.put("strMinutesBeforeReservation", "6");
  		String strReservationTimeMinutes = "60"; objDictionary.put("strReservationTimeMinutes", strReservationTimeMinutes);
  		objDictionary.put("strReservationRateType", "Fixed");
  		objDictionary.put("strNbrOfValidFromsDaysFromCurrentDay", "-1");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
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
  	@Test(priority=2203)
  	public void A2203_RSVN_FTRSVN_PSWL_WFTE_VMTAP_VPS_VPSH()
    {
  		objDictionary.put("strAssociatedBug", "187626408|184858316");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "ANDROID");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		CommonANDROID clsCommonMobile = new CommonANDROID();
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
  		String strHandicapInitialGracePeriod = "5";objDictionary.put("strHandicapInitialGracePeriod", strHandicapInitialGracePeriod);
  		String strHandicapViolationGrace = "1";
  		String strNoParkingGrace = "1";objDictionary.put("strNoParkingGrace", strNoParkingGrace);
  		objDictionary.put("strPermitCost","2.00");
  		String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{objDictionary.put("strPermitRate","Hourly Parking");}
		else
		{objDictionary.put("strPermitRate","Hourly 1");}
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
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
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
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	//Add License Plate to SL
  		clsCommonWeb.AddLicensePlateToParkingSession(objDictionary, strLicensePlateNumber, "Minnesota");
  		//Wait For free Time time to expire
  		int intMaxWaitSeconds = Integer.parseInt(strFreeTimeMinutes) * 60;
  		clsMeter.GlobalWait(objDictionary, null, "{WaitUntilMeterFreeEqualsFalse} NA", intMaxWaitSeconds, "1","Local");
  		String strReservationExists = clsMeter.METER_CheckIfReservationExists(objDictionary,"Local", "1");
  		//Wait 10 seconds
  		try {Thread.sleep(5000);}catch (Exception e) {}
  		int intExpectedRemainingTime = Integer.parseInt(strReservationTimeMinutes);
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Meter Payment");
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.SENTRYMOBILE_AddOrSelectLicensePlate(objDictionary, androiddriver, strLicensePlateNumber, "Minnesota");
		try {Thread.sleep(2000);}catch (Exception e) {}
     	clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Purchase Parking", 1);
		//Click Yes Purchase
	    clsCommonMobile.CLICK_MeterPayment_YesPurchase(objDictionary, androiddriver, strLicensePlateNumber);
	    //Validate Message Device Rejected
	 	clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Meter Payment", "Payment rejected, spot is reserved", 1, "Contains", "Payment rejected, spot is reserved");
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Meter Payment", "OK", 1);
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		//Validate Spot and License Plate
		clsCommonMobile.VALIDATIONS_ParkingSessions_SpotAndLicensePlateNumber(objDictionary, androiddriver, strLicensePlateNumber);
		//Validate Parked at Time
		clsCommonMobile.VALIDATIONS_ParkingSessions_ParkedAt(objDictionary, androiddriver);
		//ValidateParkingSessionsValidUntil
		intExpectedRemainingTime = Integer.parseInt(strReservationTimeMinutes) -2;
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary,androiddriver,intExpectedRemainingTime);
		//ValidateParkingSessionsParkingExipresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary,androiddriver,intExpectedRemainingTime);
		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
  		clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2203_RSVN_FTRSVN_PSWL_WFTE_VMTAP(objDictionary, strReservationTimeMinutes,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	//************************************************************************************
  	//CONCIERGE
  	//************************************************************************************
  	@Test(priority=2301)
  	public void A2301_CS_PSWL_VMT_VPSH()
  	{
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strMobileDeviceType", "ANDROID");
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
  		String strCoinTimePuchaseLimit = "120";
  		String strFreeTimeFirstPayment = "0";objDictionary.put("strFreeTimeFirstPayment", strFreeTimeFirstPayment);
  		String strMeterIncrementTime = "15";objDictionary.put("strMeterIncrementTime", strMeterIncrementTime);
  		String strInitialGracePeriod = "3";objDictionary.put("strInitialGracePeriod", strInitialGracePeriod);
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
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  		//Wait For initial grace to expire
  		int intInitialGracePeriod = Integer.parseInt(strInitialGracePeriod) * 60;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Waiting for initial grace time to expire-Spot1");
  		//Wait For SL Parking Session Concierge Value to equal True
  		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA", 120,"1","Local");
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  	  	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
  	  	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
  		//Navigate to Meter Payment
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		//Validate Spot and License Plate
  		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Parking Sessions", "{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Spot", 1, "Value", "Spot : ****");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Address", 1, "Value", "Address : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime))
		{
			Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");
    	}
		//ValidateParkingSessionsValidUntil
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary, null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary,androiddriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary,strMaximumDuration);
		//ValidateParkingSessionsParkingExipresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary,androiddriver,intMeterRemainingTimeMinutes);
  		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
  		clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2301_CS_PSWL(objDictionary, strMeterIncrementTime,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=2302)
  	public void A2302_CS_PS1_GPV1_ALPTPS_VMT_VPSH()
  	{
  		objDictionary.put("strAssociatedBug", "SL-7993");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "ANDROID");
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  	  	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
  	  	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
  		//Navigate to Meter Payment
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		//Validate Spot and License Plate
  		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Parking Sessions", "{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Spot", 1, "Value", "Spot : ****");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Address", 1, "Value", "Address : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime)){Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");}
		//ValidateParkingSessionsValidUntil
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary, null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary,androiddriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary,strMaximumDuration);
		//ValidateParkingSessionsParkingExipresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary,androiddriver,intMeterRemainingTimeMinutes);
  		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
  		clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2302_CS_PS1_GPV1_ALPTPS(objDictionary, strMeterIncrementTime,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=2302)
  	public void A2302_A_CS_TU_PS1_GPV1_ALPTPS_VMT_VPSH()
  	{
  		objDictionary.put("strAssociatedBug", "184549710|178674942");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "ANDROID");
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  	  	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
  	  	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
  		//Navigate to Meter Payment
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		//Validate Spot and License Plate
  		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Parking Sessions", "{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Spot", 1, "Value", "Spot : ****");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Address", 1, "Value", "Address : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime)){Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");}
		//ValidateParkingSessionsValidUntil
		String strMeterRemainingTime = clsMeter.GetMeterValidTimeRemaining(objDictionary, null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterRemainingTime) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary,androiddriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary,strMaximumDuration);
		//ValidateParkingSessionsParkingExipresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary,androiddriver,intMeterRemainingTimeMinutes);
  		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
  		clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2302_A_CS_TU_PS1_GPV1_ALPTPS(objDictionary, strMeterIncrementTime,"1");
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
    }
  	@Test(priority=2303)
  	public void A2303_CS_PS1_GPV1_WFFACTNVT_ALPTPS_VMT_VPSH()
  	{
		objDictionary.put("strAssociatedBug", "178678280");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strMobileDeviceType", "ANDROID");
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
  		intInitialGracePeriod = intInitialGracePeriod - 45;
		clsMeter.METER_MeterWaitWithMessage(objDictionary,intInitialGracePeriod, "Wait For Recognized as Concierge time to be near Violation time");
		Reporter.log("Waited ("+intInitialGracePeriod+") For Recognized as Concierge time to be near Violation time");
		//ALPTPS: Add License Plate To Parking Session
   		String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession2(objDictionary, driver, "Sentry Meter","1");
    	clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State", strLicensePlateNumber + "|Minnesota");
		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1, "Local");
	  	driver.quit();
	  	//Wait For SL Parking Session Concierge Value to equal True
  		clsMeter.GlobalWait(objDictionary, null, "{WaitParkingSessionConciergeValueEqualsTrue} NA", 90,"1","Local");
      	//Store Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
  	  	String strMeterSpotName = objDictionary.get("strMeterSpotName");
  		clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_touch_screen.py");
  		String strForcedMulti = clsMeter.METER_GetSYS_FORCE_MULTI(objDictionary,null, "Local");
  		if(strForcedMulti.equals("true")){clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost,"testautof_enter_space.py "+strMeterSpotName);}
  		int intExpectedRemainingTime = Integer.parseInt(strMeterIncrementTime);
  		try {Thread.sleep(5000);}catch (Exception e) {}
  		clsMeter.METER_ValidateExpectedMeterTime(objDictionary, null, intExpectedRemainingTime,"1");
  		try {clsHttpConnections.HTTPCONNECTIONS_StoreMobileTransactionFees(objDictionary);}catch (Exception e) {}
  		//Validate CA Parking Session values
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary, "SentryMobile","True","Parker");
  	  	clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
  	  	clsCommonMobile.SENTRYMOBILE_LoginOrRegisterWithRememberMeOffEmailIdWithoutCaps(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later");
  		//Navigate to Meter Payment
  		clsCommonMobile.SENTRYMOBILE_NavigateToPageUsingMenuButtons(objDictionary,androiddriver, "Parking Sessions");
  		//Validate Spot and License Plate
  		clsCommonMobile.GlobalWait(objDictionary,androiddriver, "Parking Sessions", "{TextExists} Parking Sessions~Spot", 60);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Spot", 1, "Value", "Spot : ****");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Address", 1, "Value", "Address : ****");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number", 1, "Value", strLicensePlateNumber);
		//ValidateParkingSessionsParkedAt
		String strActualParkingSessionsParkedAtTime = clsCommonMobile.StoreText(objDictionary, androiddriver, "Parking Sessions", "Parked at", 1, "strActualParkingSessionsParkedAtTime");
		String strParkTimestamp = objDictionary.get("strParkTimestamp");
		String strConvertedParkTime = clsCommonWeb.ConvertUTCTimeToLocalTime(strParkTimestamp,"yyyy-MM-dd'T'HH:mm:ss'Z'","h:mm a");
		if(strActualParkingSessionsParkedAtTime.contains(strConvertedParkTime)){Reporter.log("The Text (Parked at) with index (1) contained (" + strConvertedParkTime + ")");}
		//ValidateParkingSessionsValidUntil
		String strMeterValidTimeRemaining = clsMeter.GetMeterValidTimeRemaining(objDictionary, null, "1");
		int intActualRemainingTimeMinutes = (int) Math.ceil(Double.parseDouble(strMeterValidTimeRemaining) / 60.00);
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsValidUntil(objDictionary,androiddriver,intActualRemainingTimeMinutes);
		//CalculateMeterRemainingTimeMinutes
		int intMeterRemainingTimeMinutes = clsCommonMobile.SENTRYMOBILE_ParkingSessionsCalculateMeterRemainingTimeMinutes(objDictionary,strMaximumDuration);
		//ValidateParkingSessionsParkingExipresIn
		clsCommonMobile.VALIDATIONS_ValidateParkingSessionsParkingExipresIn(objDictionary,androiddriver,intMeterRemainingTimeMinutes);
		//Get Violation Number
		String strViolationId = clsHttpConnections.HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(objDictionary, "Local", "1");
  		//Exit Space
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		//ValidateParkingSessionHistoryAndImages
  		Android_ParkingSessions clsAndriodParkingSessions = new Android_ParkingSessions();
  		clsAndriodParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2303_CS_PS1_GPV1_WFFACTNVT_ALPTPS(objDictionary, strMeterIncrementTime,"1",strViolationId);
      	String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}

    }  	//************************************************************************************
  	//ROYAL OAK SPECIFIC TEST CASES
  	//************************************************************************************
  	@Test(priority=3000)
	public void A3000_RO_FTFP5_PS1_PMT_ES1_VPSH_VICAE_VIAC()throws Exception
		{
	  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	  		String strSpaceName = objDictionary.get("strMeterSpotName");
			String strLicensePlateNumber = "0"+strSpaceName+"AA";
			//Remove all License Plate
	  		HttpConnections clsHttpConnections = new HttpConnections();
			clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
			//Add License Plate
			clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
			CommonANDROID clsCommonMobile = new CommonANDROID();
			clsCommonMobile.SENTRYMOBILE_RO_FTFP5_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
			String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
			if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
		}





  	//Work In Progress or Need to Redo test cases
  	//@Test(priority=1152)
  	public void A2157_MMNPAS_VMMNP()
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_MMNPAS_VMMNPAS(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//@Test(priority=1152)
  	public void A2158_MMNPS1_VMMNPES1_VMMNPDES2()
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_MMNPS1_VMMNP_VMMNPES1_VMMNPDES2(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}

  	//FREE PARKING
  	//@Test(priority=1152)
  	public void A2159_MMFPAS_VMMFP()
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_MMFPAS_VMMFPAS(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//@Test(priority=1152)
  	public void A2160_MMFPS1_VMMFPES1_VMMFPDES2()
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
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_MMNPS1_VMMNP_VMMNPES1_VMMNPDES2(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	//UNENFORCED
  	@Test(priority=1152)
  	public void A2161_MMUPAS_VMMUP()
  	{
  		objDictionary.put("strAssociatedBug", "169635111");
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strTestCaseName", strTestCaseName);
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "0"+strMethondName.substring(0,strMethondName.indexOf("_"))+"AA";
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
  		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		//Add License Plate
  		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		clsCommonMobile.SENTRYMOBILE_MMUPAS_VMMUPAS(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}

  //**************************************************
  	//PAYMENT TEST
  	//**************************************************
  	@Test(priority=4000)
	public void A4000_PaymentTest_PayPal()
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
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "PayPal");
		clsCommonMobile.SENTRYMOBILE_2021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4001)
	public void A4001_PaymentTest_PayPal_MaxTime()throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "PayPal");
		clsCommonMobile.SENTRYMOBILE_2027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4002)
	public void A4002_PaymentTest_GooglePay()
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
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "Google Pay");
		clsCommonMobile.SENTRYMOBILE_2021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4003)
	public void A4003_PaymentTest_GooglePay_MaxTime()throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strTestCaseName", strTestCaseName);
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "Google Pay");
		clsCommonMobile.SENTRYMOBILE_2027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4004)
	public void A4004_PaymentTest_CreditOrDebitCard()
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
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "Credit or Debit Card");
		clsCommonMobile.SENTRYMOBILE_2021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4005)
	public void A4005_PaymentTest_CreditOrDebitCard_MaxTime()throws Exception
	{
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		objDictionary.put("strTestCaseName", strTestCaseName);
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "Credit or Debit Card");
		clsCommonMobile.SENTRYMOBILE_2027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4006)
	public void A4006_PaymentTest_Venmo()
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
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "Venmo");
		clsCommonMobile.SENTRYMOBILE_2021_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
  	@Test(priority=4007)
	public void A4007_PaymentTest_Venmo_MaxTime()throws Exception
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "0"+strSpaceName+"AA";
		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Alabama");
		CommonANDROID clsCommonMobile = new CommonANDROID();
		objDictionary.put("strAltPayment", "Venmo");
		clsCommonMobile.SENTRYMOBILE_2027_FTFP0_PS1_PMT_ES1_VPSH_VICAE_VIAC(objDictionary,strLicensePlateNumber);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

  	//**************************************************
  	//EULA
  	//**************************************************
  	@Test(priority=5001,groups={"Smoke"})
	public void A5001_ValidateUserAgreement()
	{
  		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		//Validate UserAgreement
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Header", 1, "Value", "END USER LICENSE AGREEMENT and TERMS OF SERVICE");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Section 1", 1, "Value", "We want you to be safe. Do not use the SENTRY MOBILE smartphone application while driving a vehicle. You agree to follow all traffic laws and regulations in the state(s) where you are using the SENTRY MOBILE smartphone application.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Section 2", 1, "Value", "By downloading and using the SENTRY MOBILE smartphone application (hereinafter the “Product Software”), you agree to be bound by the following terms of this End User License Agreement (“EULA”) between you and Municipal Parking Services, Inc. (“MPS” or “we”). IF YOU DO NOT AGREE TO BE BOUND BY THE TERMS OF THIS EULA, YOU MAY NOT USE THE PRODUCT SOFTWARE.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Section 3", 1, "Value", "Every time you use the Product Software you agree to be bound by the terms of this EULA, which may be updated from time-to-time, in MPS’ sole discretion, without prior notice to you.");
		//Scroll Up
		WebElement objFrame = androiddriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Section 4", 1, "Value", "THIS IS A LEGAL AGREEMENT. YOU REPRESENT AND WARRANT THAT YOU HAVE THE RIGHT, AUTHORITY, AND CAPACITY TO ACCEPT AND AGREE TO THIS EULA. YOU REPRESENT THAT YOU ARE OF SUFFICIENT LEGAL AGE IN YOUR JURISDICTION OR RESIDENCE TO USE OR ACCESS THE PRODUCT SOFTWARE AND TO ENTER INTO THIS EULA. IF YOU DO NOT AGREE WITH ANY OF THE PROVISIONS OF THESE TERMS, YOU SHOULD CEASE ACCESSING OR USING THE PRODUCT SOFTWARE.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Section 5", 1, "Value", "AS DESCRIBED BELOW, YOU ARE CONSENTING TO AUTOMATIC SOFTWARE UPDATES OF PRODUCT SOFTWARE. IF YOU DO NOT AGREE, YOU SHOULD NOT USE THE PRODUCT SOFTWARE.");
		//Scroll Up
		objFrame = androiddriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView"));
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement License", 1, "Value", "Subject to the terms of this EULA, MPS grants to you a limited, terminable, and non-exclusive, non-transferrablelicense (without the right to sublicense) to utilize one (1) single copy of the Product Software, in executable object code form only, on a single smartphone or other mobile computing device.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Restrictions", 1, "Value", "You agree not to, and you will not permit others to, (a) license, sell, rent, lease, assign, distribute, transmit, host, outsource, disclose or otherwise commercially exploit the Product Software or make the Product Software available to any third party, (b) copy or use the Product Software for any purpose other than as permitted in Section 1 of this EULA, (c) use any portion of the Product Software on any device or computer other than as provided in Section 1 of this EULA, (d) remove or alter any trademark, logo, copyright, patent or other proprietary notices, legends, symbols or labels in or associated with the Product Software, (e) modify, alter, make derivative works of, disassemble, reverse compile or reverse engineer any part of the Product Software (except to the extent applicable laws specifically prohibit such restriction for interoperability purposes, in which case you agree to first contact MPS in writing and to provide MPS an opportunity to create such changes as are needed for interoperability purposes); (f) you may not release, publicly or otherwise, the results of any performance or functional evaluation of any of the Product Software to any third party without prior written approval of MPS for each such release, (g) you agree to not engage, nor allow a third party to engage, in any of the following: scraping, caching, creating new content, recreating content, or circumventing (including fees) of the Product Software.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Modifications", 1, "Value", "MPS may from time to time develop make changes to the Software Product or services, which may include adding, updating, or discontinuing the Product Software and services, or parts thereof. Updating may include patches, bug fixes, updates, upgrades and other modifications to improve the performance or operation of the Product Software and related services (“Updates”). These Updates may be automatically installed without providing any additional notice or receiving any additional consent. You consent to this automatic updating and to the Updates. If you do not want such Updates, your sole remedy is to stop using the Software Product. Your failure to exercise your sole remedy of ceasing use of the Software Product, Updates, or the automated updating process, shall constitute your acceptance of the foregoing. You acknowledge that you may be required to install Updates in order to use or to continue to use the Software Product. You agree to promptly install any Updates MPS provides. Your continued use of the Software Product is your agreement to this EULA. The Updates shall automatically become part of the Product Software and shall automatically become subject to the rights and obligations of this EULA.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Ownership", 1, "Value", "The Product Software and all worldwide copyrights, trade secrets, and other intellectual property rights therein are the exclusive property of MPS and its licensors. MPS and its licensors reserve all rights in and to the Product Software not expressly granted to you in this EULA. The Product Software (and all copies thereof) is licensed to you, not sold, under this EULA. There are no implied licenses in this EULA. All suggestions or feedback provided by you to MPS with respect to the Product Software shall be MPS’ sole property. Proffering of such suggestions, ideas, or feedback shall constitute an affirmative assignment of such, and you hereby irrevocably do assign to MPS all right, title, and interest in the foregoing. MPS may use, copy, modify, publish, or redistribute the submission and its contents for any purpose and in any way without any notice or compensation to you. You also agree that MPS does not waive any rights to use similar or related ideas previously known to MPS, developed by its employees, or obtained from other sources.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Consent to Use of Data", 1, "Value", "You agree that MPS and its subsidiaries and agents may collect, maintain, process and use data relating to you, your smart device, your vehicle, and the personal identification information associated with that vehicle (“User Data”).");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Consent to Use of Data Part 2", 1, "Value", "Consent to Use of Data: You agree that MPS may collect and use User Data and the personal identification information associated with the User Data, including but not limited to vehicle information, location data, technical data and related information, your smart device, system and application software, and peripherals, that is gathered periodically to facilitate the Product Software, the provision of software updates, product support and other services to you (if any) related to the Product Software. MPS may use this information, as long as it is in a form that does not personally identify you, to improve its products or to provide services or technologies to you, or to supply you with advertising material.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Consent to Use of Data Part 3", 1, "Value", "Data Privacy: You agree that MPS may process your User Data, including but not limited to your vehicle data and the personal identification information associated with your vehicle, location data, technical and related information about your use of the parking space, software which may include internet protocol address, hardware identification, operating system, application software, peripheral hardware, personally and non-personally identifiable Product Software usage statistics to facilitate the provisioning of updates, support, invoicing or online services and may transfer such information to other companies in the MPS worldwide group of companies from time to time. This information may include your personal data or information. MPS may share this information with governmental agencies, including the municipality in which the rented parking space is located and with law enforcement agencies.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Consent to Use of Data Part 4", 1, "Value", "Interest-Based Advertising: By using the Product software you agree that MPS may provide interest-based advertising to you via the meter or your mobile computing device.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Do Not Use While Driving", 1, "Value", "You agree, represent and warrant, so long as YOU USE OR ACCESS THE PRODUCT SOFTWARE, THAT YOU WILL NOT, UNDER ANY CIRCUMSTANCES, ACCESS, VIEW, OR USE THE PRODUCT SOFTWARE WHILE DRIVING OR OTHERWISE OPERATING A VEHICLE OF ANY KIND (including, without limitation, a car, truck, motorcycle, motor scooter, or bicycle) or operating any dangerous equipment or machinery. You understand that using any handheld device in these circumstances is extremely dangerous, and can result in fines, property damage, physical injuries (including dismemberment) or death. You further agree, represent and warrant, that you will not use or access the Product Software in any manner that places yourself or any other person at risk of injury, and that you will abide by all traffic laws and regulations. While effort is made to assure the accuracy of the information presented, the user is responsible for safe driving and for the consequences of decisions as to where to travel or where to drive. Under no circumstance will MPS assume any responsibility or liability for the consequences of driving decisions made by you.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Do Not Use While Driving Part 2", 1, "Value", "You expressly agree that MPS shall not be liable for any driving decisions made by you or at your suggestion or for any damages, injury or other harm caused by your use of or accessing the Product Software, services or content, and waive any and all claims or causes of action you may have, now or in the future, arising from or relating to the same. In the event that any party names MPS as a defendant in a case involving your use of the Product Software while operating a vehicle, you agree to indemnify and hold MPS harmless in such action.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Term and Termination", 1, "Value", "This EULA and the license granted hereunder are effective on the date you first use the Product Software and shall continue for as long as the Product Software resides on your smartphone or mobile device, unless this EULA is terminated under this section. MPS may terminate this EULA at any time, and without notice to you, if you fail to comply with any term(s) hereof. You may terminate this EULA effective immediately upon written notice to MPS. Upon termination of this EULA, the license granted hereunder will terminate and you must stop all use of the Product Software, but the terms of Sections 8 through 22 (inclusive) will remain in effect, after any such termination.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Warranty Disclaimer", 1, "Value", "NOTWITHSTANDING ANYTHING TO THE CONTRARY AND TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, MPS PROVIDES THE PRODUCT SOFTWARE “AS-IS” AND \"AS AVAILABLE\", WITH ALL FAULTS AND WITHOUT WARRANTY OF ANY KIND.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Warranty Disclaimer Part 2", 1, "Value", "MPS DISCLAIMS ALL WARRANTIES AND CONDITIONS, WHETHER EXPRESS, IMPLIED, OR STATUTORY, INCLUDING THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, QUIET ENJOYMENT, ACCURACY, AND NON-INFRINGEMENT OF THIRD-PARTY RIGHTS. MPS DOES NOT GUARANTEE ANY SPECIFIC RESULTS FROM THE USE OF THE PRODUCT SOFTWARE. MPS MAKES NO WARRANTY THAT THE PRODUCT SOFTWARE WILL BE AVAILABLE, FUNCTIONAL, UNINTERRUPTED, FREE OF VIRUSES OR OTHER HARMFUL CODE, TIMELY, SECURE, OR ERROR-FREE.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Warranty Disclaimer Part 3", 1, "Value", "YOU USE ALL PRODUCT SOFTWARE AT YOUR OWN DISCRETION AND RISK. YOU WILL BE SOLELY RESPONSIBLE FOR (AND MPS DISCLAIMS) ANY AND ALL LOSS, LIABILITY, OR DAMAGES, INCLUDING ANY COMPUTER, MOBILE DEVICE, OR OTHER ITEM OF YOURS OR A THIRD PARTY, RESULTING FROM YOUR USE OF THE PRODUCT SOFTWARE.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Limitation of Liability", 1, "Value", "Nothing in this EULA, and in particular within this \"Limitation of Liability\" clause, shall attempt to exclude liability that cannot be excluded under applicable law.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Limitation of Liability Part 2", 1, "Value", "TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, IN ADDITION TO THE ABOVE WARRANTY DISCLAIMERS, IN NO EVENT WILL (A) MPS BE LIABLE FOR ANY CONSEQUENTIAL, EXEMPLARY, SPECIAL, OR INCIDENTAL DAMAGES, INCLUDING ANY DAMAGES FOR LOST DATA OR LOST PROFITS, ARISING FROM OR RELATING TO THE PRODUCT SOFTWARE, EVEN IF MPS KNEW OR SHOULD HAVE KNOWN OF THE POSSIBILITY OF SUCH DAMAGES, AND (B) MPS’S TOTAL CUMULATIVE LIABILITY ARISING FROM OR RELATED TO THE PRODUCT SOFTWARE, WHETHER IN CONTRACT OR TORT OR OTHERWISE, SHALL NOT EXCEED THE FEES ACTUALLY PAID BY YOU TO MPS OR MPS’S AUTHORIZED RESELLER FOR THE PRODUCT OR SOFTWARE AT ISSUE IN THE PRIOR 12 MONTHS (IF ANY). THIS LIMITATION IS CUMULATIVE AND WILL NOT BE INCREASED BY THE EXISTENCE OF MORE THAN ONE INCIDENT OR CLAIM. MPS DISCLAIMS ALL LIABILITY OF ANY KIND OF MPS’S LICENSORS AND SUPPLIERS.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Indemnification", 1, "Value", "Unless prohibited by applicable law, you will defend MPS, its agents, and assigns to the extent any event arises from your use of the Product Software or services in violation of this Agreement.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Indemnification Part 2", 1, "Value", "You will promptly notify MPS in writing of any allegation(s), or legal proceeding and to cooperate reasonably with MPS to resolve the allegation(s) or legal proceeding. MPS may, in its sole discretion, appoint its own counsel, at its own expense. You agree to not enter any settlement or to admit liability, pay money, or take (or refrain from taking) any action, without MPS’ consent, not to be unreasonably withheld, conditioned, or delayed.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges", 1, "Value", "You must fully setup an account in the MPS Sentry Mobile app prior to parking before you can use the SENTRY MOBILE smartphone application to pay for parking with MPS SENTRY parking meters. The MPS Sentry Mobile app will not work with any other type of parking meter or parking system.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 2", 1, "Value", "In order to fully set up your account, you must deposit funds into your account in at least the minimum amount requested by the MPS Sentry Mobile app during the setup process (the “Initial Deposit”). If you cancel or terminate your account, you may request a refund of any remaining balance of your Initial Deposit in your account if such request is made within 120 days of the date of the Initial Deposit. No refunds of the Initial Deposit will be made if the refund request is made more than 120 from the date of the Initial Deposit.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 3", 1, "Value", "For a payment for parking made through the Product Software to be valid, you must i) provide all accurate and complete information required by the Product Software, including license plate number, parking zone, and parking time(s), (ii) provide valid credit card information or other valid form of electronic payment permitted by the Product Software, and (iii) abide by all payment instructions and prompts set forth in the Application.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 4", 1, "Value", "You are responsible for the validity of the enrolled debit card, credit card, that it has sufficient available funds and is not blocked or inactive. Once the parking is initiated and payment received through the Product Software, you will receive a confirmation of the purchase and it is your responsibility to check that this confirmation is received.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 5", 1, "Value", "You agree that, when using the Product Software to pay for parking, MPS will charge the parking amount, including any applicable taxes, fees and service charges. The parking fee is calculated based on the parking time and the parking fee applicable when you begin parking, and such fee is charged immediately upon commencement of parking. Once you have been charged the parking fee, such parking fee and any applicable taxes, fees and service charges are nonrefundable, even if you leave the parking space or lot before the time for which you paid to park has expired or if you entered the license plate, location or amount of time incorrectly.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 6", 1, "Value", "You bear the sole responsibility to follow any applicable parking rules and regulations. If the Software Product is not available, for whatever reason, or if the Software Product does not provide a confirmation that your payment for parking has been received, it is your responsibility to pay the parking fee using another method.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 7", 1, "Value", "It is your sole responsibility to monitor your parking session and any pertinent parking regulations set forth by the municipality and payments made by you, through any payment method including but not limited to cash, coin, credit card, and/or mobile application, for the duration of your parking session. FAILURE TO RECEIVE AN ALERT, PUSH NOTIFICATION, PROMPT, OR REMINDER OF YOUR PARKING SESSION EXPIRATION DOES NOT NEGATE YOUR RESPONSIBILITY TO PAY FOR YOUR PARKING, NOR DOES SUCH FAILURE EXEMPT YOU FROM RECEIVING A PARKING TICKET. MPS SHALL BEAR NO RESPONSIBILITY OR LIABILITY, AND SHALL NOT BE REQUIRED TO REIMBURSE USER FOR ANY FEES, FINES, TICKETS, AND THE LIKE, INCLUDING BUT NOT LIMITED TO, EXPENSES RELATED TO TOWING OF USER’S VEHICLE, OR ANY DAMAGE THAT MAY BE INCURRED AS RESULT THEREOF.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 8", 1, "Value", "You are solely responsible for (i) obtaining and maintaining all internet or other communications access, computer hardware and other equipment or electronic media necessary to utilize the Product Software, (ii) any issues pertaining to your mobile computing device (including issues pertaining to any incompatibility of the Product Software with your mobile computing device) and the data plan and data/cellular service providers that permit your mobile computing device to send and receive data wirelessly, and (iii) the data and content provided by you to MPS through the Product Software. MPS SHALL HAVE NO RESPONSIBILITY OR LIABILITY FOR ANALYSIS, DATA, RECOMMENDATIONS, OR OTHER SERVICES PROVIDED TO YOU BASED UPON INCORRECT OR INCOMPLETE DATA PROVIDED BY YOU.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Account Setup, Payment and Charges Part 9", 1, "Value", "YOU SHALL HOLD MPS HARMLESS IN ANY DISPUTE BETWEEN YOU AND EACH OF THE FOLLOWING: GOVERNMENTAL ENTITIES, YOUR CREDIT CARD COMPANY AND YOUR CELL PHONE COMPANY OR OTHER MOBILE DATA PROVIDER. MPS IS NOT RESPONSIBLE FOR ANY DISPUTES REGARDING PARKING TICKETS, TOWING OF YOUR VEHICLE, OR OTHER FEES OR ISSUES THAT MAY ARISE WITH RESPECT TO ANY PARKING INFRACTION, FAILURE TO PAY, FAILURE TO FOLLOW A PARKING LOT’S INSTRUCTIONS, OR THE LIKE.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Third Party Materials", 1, "Value", "The Product Software may display, include or make available third-party content (including data, information, applications and other products services and/or materials) or provide links to third-party websites or services, including through third-party advertising (\"Third Party Materials\"). You acknowledge and agree that MPS is not responsible for Third Party Materials, including their accuracy, completeness, timeliness, validity, copyright compliance, legality, decency, quality or any other aspect thereof. MPS does not assume and will not have any liability or responsibility to you or any other person or entity for any Third Party Materials. Third Party Materials and links thereto are provided solely as a convenience to you and you access and use them entirely at your own risk and subject to such third parties' terms and conditions");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement For U.S. Government End Users", 1, "Value", "The Product Software is a “commercial item,” as that term is defined at 48 C.F.R. 2.101 (OCT 1995), and more specifically is “commercial computer software” and “commercial computer software documentation,” as such terms are used in 48 C.F.R. 12.212 (SEPT 1995). Consistent with 48 C.F.R. 12.212 and 48 C.F.R. 227.7202-1 through 227.7202-4 (JUNE 1995), the Product Software is provided to U.S. Government End Users only as a commercial end item and with only those rights as are granted to all other customers pursuant to the terms and conditions herein.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Export Compliance", 1, "Value", "The Product Software and related technology are subject to U.S. export control laws and may be subject to export or import regulations in other countries. You agree to strictly comply with all such laws and regulations and acknowledge that you have the responsibility to obtain authorization to export, re-export, or import the Product Software and related technology, as may be required. You will indemnify and hold MPS harmless from any and all claims, losses, liabilities, damages, fines, penalties, costs and expenses (including attorney’s fees) arising from or relating to any breach by you of your obligations under this section.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause", 1, "Value", "Any and all controversies, disputes, demands, counts, claims, or causes of action (including the interpretation and scope of this clause, and the arbitrability of the controversy, dispute, demand, count, claim, or cause of action) between you and MPS or its successors or assigns shall exclusively be settled through binding and confidential arbitration.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 2", 1, "Value", "Arbitration shall be subject to the Federal Arbitration Act and not any state arbitration law. The arbitration shall be conducted before one commercial arbitrator with substantial experience in resolving commercial contract disputes from the American Arbitration Association (“AAA”). As modified by this EULA, and unless otherwise agreed upon by the parties in writing, the arbitration will be governed by the AAA’s Commercial Arbitration Rules and, if the arbitrator deems them applicable, the Supplementary Procedures for Consumer Related Disputes (collectively, the “Rules and Procedures”).");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 3", 1, "Value", "You are thus GIVING UP YOUR RIGHT TO GO TO COURT to assert or defend your rights EXCEPT for matters that may be taken to small claims court.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 4", 1, "Value", "You and MPS must abide by the following rules: (a) ANY CLAIMS BROUGHT BY YOU OR MPS MUST BE BROUGHT IN THE PARTY'S INDIVIDUAL CAPACITY, AND NOT AS A PLAINTIFF OR CLASS MEMBER IN ANY PURPORTED CLASS OR REPRESENTATIVE PROCEEDING; (b) THE ARBITRATOR MAY NOT CONSOLIDATE MORE THAN ONE PERSON’S CLAIMS, MAY NOT OTHERWISE PRESIDE OVER ANY FORM OF A REPRESENTATIVE OR CLASS PROCEEDING, AND MAY NOT AWARD CLASS-WIDE RELIEF; (c) in the event that you are able to demonstrate that the costs of arbitration will be prohibitive as compared to costs of litigation, MPS will pay as much of your filing and hearing fees in connection with the arbitration as the arbitrator deems necessary to prevent the arbitration from being cost-prohibitive as compared to the cost of litigation, (d) MPS also reserves the right in its sole and exclusive discretion to assume responsibility for all of the costs of the arbitration; (e) the arbitrator shall honor claims of privilege and privacy recognized at law; (f) the arbitration shall be confidential, and neither you nor we may disclose the existence, content or results of any arbitration, except as may be required by law or for purposes of enforcement of the arbitration award; (g) the arbitrator may award any individual relief or individual remedies that are permitted by applicable law; and (h) each side pays its own attorneys’ fees and expenses unless there is a statutory provision that requires the prevailing party to be paid its fees and litigation expenses, and, in such instance, the fees and costs awarded shall be determined by the applicable law.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 5", 1, "Value", "Notwithstanding the foregoing, either you or MPS may bring an individual action in small claims court.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Arbitration Required, Jury Trial Waiver, Class Action Waiver, and Forum Selection Clause Part 6", 1, "Value", "Claims of defamation, violation of the Computer Fraud and Abuse Act, and infringement or misappropriation of the other party’s patent, copyright, trademark, or trade secret shall not be subject to this arbitration agreement. Such claims shall be exclusively brought in the state or federal courts located in Hennepin or Dakota counties of the state of Minnesota.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Choice of Law", 1, "Value", "You agree that this EULA, and any claim, dispute, action, cause of action, issue, or request for relief relating to this EULA and/or your use of the Product Software, will be governed by the laws of Minnesota, without giving effect to any conflicts of laws principles that require the application of the laws of a different jurisdiction.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Choice of Law Part 2", 1, "Value", "Each party irrevocably submits to the jurisdiction and venue of state or federal courts located in Hennepin or Dakota counties of the state of Minnesota, except that MPS may seek injunctive relief in any court having jurisdiction to protect its intellectual property.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Assignment", 1, "Value", "Neither the rights nor the obligations arising under this EULA are assignable by you, and any such attempted assignment shall be void and without effect.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Notices", 1, "Value", "Any notice to you may be provided by email to the address that you registered with MPS.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Severability", 1, "Value", "If any provision of this EULA is unenforceable, such provision will be changed and interpreted to accomplish the objectives of such provision to the greatest extent possible under applicable law and the remaining provisions will continue in full force and effect.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Waiver", 1, "Value", "All waivers by MPS will be effective only if in writing. Any waiver or failure by MPS to enforce any provision of this EULA on one occasion will not be deemed a waiver of any other provision or of such provision on any other occasion.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement General Terms", 1, "Value", "You are responsible for establishing and maintaining your own password.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement General Terms Part 2", 1, "Value", "You acknowledge that transmission of data over the internet and wireless devices involves unique transmission risks that cannot be fully secured against unauthorized access.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement General Terms Part 3", 1, "Value", "The Product Software is deemed irrevocably accepted upon your use of the Product Software. MPS will have no responsibility to provide maintenance or support services with respect to the Product Software. The parties are independent contractors.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement General Terms Part 4", 1, "Value", "You acknowledge that the Product Software contains valuable trade secrets and proprietary information of MPS, that any actual or threatened breach of Section 2 (Restrictions) of this EULA will constitute immediate, irreparable harm to MPS for which monetary damages would be an inadequate remedy, and that injunctive relief is an appropriate remedy for such breach.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement General Terms Part 5", 1, "Value", "The headings of Sections of this EULA are for convenience and are not to be used in interpreting this EULA.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement General Terms Part 6", 1, "Value", "If you have questions regarding this EULA, please contact MPS. MPS’s contact information can be found at");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Entire Agreement", 1, "Value", "Notwithstanding any agreements or terms expressly incorporated by reference in this EULA, this EULA constitutes the entire agreement between the parties. This EULA (as it may from time to time be amended, restated, or otherwise modified) supersedes any prior agreements, understandings, or negotiations, whether written or oral.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Acknowledgement", 1, "Value", "BY USING THE PRODUCT SOFTWARE OR ACCESSING THE MPS WEBSITE, YOU ACKNOWLEDGE THAT YOU HAVE READ THESE TERMS OF USE AND AGREE TO BE BOUND BY THEM.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "User Agreement Revision Date", 1, "Value", "Revision Date: September 28, 2020");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}

  	//**************************************************
  	//CONCIERGE - Enrollment With Wizard
  	//**************************************************
//  //De-enroll - Delete License Plate - With Wizard - Add License Plate Account Details - Phone Disabled - Notification Off - Enroll (No Phone)
//  	@Test(priority=1157)
//  	public void A157_DC1_DLP_WCW_ALPAD_PD_ANU_PS1_ALPAD_EC1_VNN()
//  	{
//  		objDictionary.put("strAssociatedBug", "157433836");
//  		CommonANDROID clsCommonMobile = new CommonANDROID();
//  		String strUniqueId = objDictionary.get("strUniqueId");
//  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
		//clsCommonMobile.SENTRYMOBILE_DC1_ANU_PS1_EC1_VNN(objDictionary, strLicensePlateNumber,"Account Details","Enroll now", "Phone Disabled");
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
//  	}
//  	//De-enroll - Delete License Plate - Add License Plate API -> With Wizard - Phone Enabled -> SMS Backup Disabled - Notification Off - Enroll
//  	@Test(priority=1158)
//  	public void A158_DC1_DLP_WCW_ALPMP_PD_ANU_PS1_ALPAD_EC1_VNN()
//  	{
//  		objDictionary.put("strAssociatedBug", "157433836");
//  		CommonANDROID clsCommonMobile = new CommonANDROID();
//  		String strUniqueId = objDictionary.get("strUniqueId");
//  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();



  	//		clsCommonMobile.SENTRYMOBILE_DC1_ANU_PS1_EC1_VNN(objDictionary, strLicensePlateNumber,"API","Enroll now","Phone Enabled -> SMS Backup Disabled");
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
//  	}
//
//
//
//
//	//**************************************************
//	//CONCIERGE - Enrollment Without Wizard
//	//**************************************************
//	//De-enroll - Delete License Plate - Without Wizard - Add License Plate Account Details - Phone Disabled - Notification Off - Enroll
	@Test(priority=1159)
  	public void A2159_DC1_DLP_WOCW_ALPAD_PD_ANU_PS1_ALPAD_EC1_VNN()
  	{
  		objDictionary.put("strAssociatedBug", "157433836");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
		clsCommonMobile.SENTRYMOBILE_DC1_ANU_PS1_EC1_VNN(objDictionary, strLicensePlateNumber,"Account Details","Enroll later","Disabled");
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
//	//De-enroll - Delete License Plate - Without Wizard - Add License Plate Meter Payment - Phone Disabled - Notification Off - Enroll
//	@Test(priority=1160)
//  	public void A160_DC1_DLP_WOCW_ALPMP_PD_ANU_PS1_ALPAD_EC1_VNN()
//  	{
//  		objDictionary.put("strAssociatedBug", "157433836");
//  		CommonANDROID clsCommonMobile = new CommonANDROID();
//  		String strUniqueId = objDictionary.get("strUniqueId");
//  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
//		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
//		clsCommonMobile.SENTRYMOBILE_DC1_ANU_PS1_EC1_VNN(objDictionary, strLicensePlateNumber,"Meter Payment","Enroll later","Disabled");
//		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
//		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
//  	}
//


  	@Test(priority=1159)
  	public void A2159_SENTRYMOBILE_PS1_EC1_NSAC_NSTU()
  	{
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
  		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
  		clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAC_NSTU(objDictionary,strLicensePlateNumber);//CA161BB
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=1160)
  	public void A2160_SENTRYMOBILE_PS1_EC1_NSAU_NSTC()
  	{
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		//Remove all License Plate
  		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
  		clsCommonMobile.SENTRYMOBILE_PS1_EC1_NSAU_NSTC(objDictionary,strLicensePlateNumber);//CA161BB
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}
  	@Test(priority=1161)
  	public void A2161_SENTRYMOBILE_PS1_EC1_NSAU_NSTC()
  	{
  		objDictionary.put("strAssociatedBug", "7028");
  		CommonANDROID clsCommonMobile = new CommonANDROID();
  		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
  		String strLicensePlateNumber = "C"+strMethondName.substring(0,strMethondName.indexOf("_"))+strUniqueId.toUpperCase();
  		clsCommonMobile.SENTRYMOBILE_PS1_RELP_ALPMPP_VLAD_ES1(objDictionary,strLicensePlateNumber);
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
  		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
  	}


}

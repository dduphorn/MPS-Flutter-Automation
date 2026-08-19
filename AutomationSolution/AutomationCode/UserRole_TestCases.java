package AutomationCode;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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

import io.appium.java_client.ios.IOSDriver;

@Listeners({ EmailableReporter2.class, EmailableReporter3.class, ListenersClass.class})
public class UserRole_TestCases
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
//		objDictionary.put("strTestCaseName", strTestCaseName+"_"+strInvocationCounter);
		objDictionary.put("strTestCaseName", strTestCaseName);
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
			if(!strTestCaseName.contains("UserRoles"))
			{
				clsMeter.METER_CopyLogTraceLocally(objDictionary, null,  strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			}
			//clsMeter.METER_CopyMessageTraceLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			String strResetRateBlocksCounter = objDictionary.get("strResetRateBlocksCounter");if(strResetRateBlocksCounter == null) {strResetRateBlocksCounter = "0";}
		}
	}

	@Parameters({"strRole"})@Test(priority=8001,groups={"UserTest"})
	public void UserRoles1001_Compare_Meter_Settings(@Optional String strRole)
    {
		CompareMeterSettings clsCompareMeterSettings = new CompareMeterSettings();

		clsCompareMeterSettings.METER_CompareMeterSettingsDual();
		System.out.println(strRole);
    }
//	@Parameters({"strRole"})@Test(priority=8001,groups={"UserTest"})
//	public void UserRoles1002_Compare_Meter_Settings_Multi(@Optional String strRole)
//    {
//		Meter clsMeter = new Meter();
//
//		clsMeter.METER_CompareMeterSettingsDual();
//		System.out.println(strRole);
//    }


	@Parameters({"strRole"})@Test(priority=8001,groups={"UserTest"})
	public void UserRoles1001_InitialUserScreen(@Optional String strRole)
    {
		System.out.println(strRole);
		if(strRole.equals("api_application")||strRole.equals("api_application")||strRole.equals("api_ticket_service"))
		{
			objDictionary.put("strAssociatedBug", "185485920");
		}
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strFirstName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "");
		String strLastName = strRole.replace(" ", "");
		switch (strEnvironment)
		{
			case "SG":
				switch (strRole)
				{
					case "api_application":
					case "api_ticket_service":
						//This is tied to a SL pivotal 185485920
						clsCommonWeb.VerificationPointPage(objDictionary,driver, "Profile", "Exists");
						driver.quit();
						break;
					case "automated_process":
						clsCommonWeb.VerificationPointPage(objDictionary,driver, "Login", "Exists");
						//Signed in successfully
						clsCommonWeb.VerificationPointText(objDictionary,driver, "Login", "YouNeedToSignInOrSignUpBeforeContinuing", 1, "Innertext", "You need to sign in or sign up before continuing.");
						driver.quit();
						break;
					case "coin_collector":
						//MPS Logo
						clsCommonWeb.VerificationPointImage(objDictionary, driver, "Coin Collector Utilities", "Municipality Logo","Exists");
						//Validate Municipality Name
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Coin Collector Utilities", "AutomationMunicipality",1,"Exists");
						//Validate Signed in successfully
						clsCommonWeb.VerificationPointPage(objDictionary,driver, "Coin Collector Utilities", "Exists");
						//Validate User Link
						clsCommonWeb.VerificationPointLink(objDictionary,driver, "Coin Collector Utilities", strFirstName+" "+strLastName,1,"Exists");
						//Signed in successfully
						clsCommonWeb.VerificationPointText(objDictionary,driver, "Coin Collector Utilities", "Signed in successfully", 1, "Innertext", "Signed in successfully.");
						//Validate Header
						clsCommonWeb.VerificationPointText(objDictionary,driver, "Coin Collector Utilities", "Page Header", 1, "Value", "Coin Collector Utilities");
						driver.quit();
						break;
					case "collection_agency":
						//MPS Logo
						clsCommonWeb.VerificationPointImage(objDictionary, driver, "Violations", "Municipality Logo","Exists");
						//Validate link Violations
						clsCommonWeb.VerificationPointLink(objDictionary,driver, "Violations", "Violations",1,"Exists");
						//Validate Reports Link Dropdown Items
						clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Violations", "Reports", 1, "Past Collection Agency Exports|Individual Collections Report");
						//Validate Municipality Name
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "AutomationMunicipality",1,"Exists");
						//Validate User Link
						clsCommonWeb.VerificationPointLink(objDictionary,driver, "Violations", strFirstName+" "+strLastName,1,"Exists");
						//Validate Violation # is selected
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Violation #", 1, "Value", "Selected");
						//Validate Violation ID Exists
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Violation ID", 1, "Exists", "");
						//Validate Plate Exists
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Plate", 1, "Exists", "");
						//Validate Lookup ID Exists
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Lookup ID", 1, "Exists", "");
						//Validate Search
						clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Violations", "Search", 1, "Exists", "");
						//Validate FromDate
						clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Violations", "FromDate", 1, "Exists", "");
						//Validate ToDate
						clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Violations", "ToDate", 1, "Exists", "");
						//Validate Violations Status
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Violation Status", 1, "Item Order", "ALL|AWAITING COLLECTION|CLAIMED|COLLECTIONS|DISMISSED|DISPUTED|EMAILED|END DISPUTE|GENERATING TICKET|HOLD|MISSED|NEW|NOTIFIED|OVERDUE|PAID|PARTIAL|REFUNDED|RESET TICKETING WITH NEW OWNER|SNOOZED|TICKET GENERATED|TICKET GENERATION FAILED|UNHOLD|UNISSUED|UNLOCKED|UNVOIDED|VOIDED");
						//Validate Violation Notifications
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Violation Notifications", 1, "Item Order", "ALL|EXEMPT|NORMAL|NOT PROCESSABLE|REDO|SENT FOR VERIFICATION|SYSTEM FAILURE|TO VERIFY|VERIFICATION FAILED|VERIFIED");
						//Validate Violation Sources
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Violation Sources", 1, "Item Order", "ALL|BOLLARD|HEALTHKIOSK|KIOSK|METER|NOPARKINGSTICK|SOLARSTICK|VIRTUALDEVICE|VIRTUALKIOSK|VIRTUALMETER");
						//Validate Processing Status
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Processing Status", 1, "Item Order", "ALL|EXEMPT|NORMAL|NOT PROCESSABLE|REDO|SENT FOR VERIFICATION|SYSTEM FAILURE|TO VERIFY|VERIFICATION FAILED|VERIFIED");
						//Validate Meter Groups
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Meter Groups", 1, "Item Order", "AMG2|AMG3|AMG4|AUTOMATIONMETERGROUP|AUTOMATIONOPENLOT|DAKOTA|MPS|NO METER|VIRTUALAUTOMATIONMETERGROUP|WOONERFMETERGROUP");
						//Validate Spots
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Spots", 1, "Item Order", "1|5510|5511|5560|5561|5570|5571|5580|5581|9000|9001|9010|9011|9092|9094|9095|9096|9097|9203|9204|9205|9206|9209|9210|9211|9212|9213|9214|9215|9216|9217|9218|9219|9220|9221|9222|9223|9224|9227|9228|AUTOOPENENTRY|AUTOOPENEXIT|AUTOOPENPAY1|AVMETER1|AVMETER2|AVMETER4|AVMETER5|AVMETER6|DAKOTA KIOSK|IVMETER1|VMETER4");
						//Validate Sort By
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Sort By", 1, "Item Order", "ISSUED TIMESTAMP|VIOLATION NUMBER|TIME PARKED|DURATION");
						//Validate Find Violations
						clsCommonWeb.VerificationPointButton(objDictionary, driver, "Violations", "Find Violations",1,"Exists");
						//Validate Overdue 1
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Overdue 1",1,"Exists");
						//Validate Overdue 2
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Overdue 2",1,"Exists");
						//Validate Awaiting Collections
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Awaiting Collections",1,"Exists");
						//Signed in successfully
						clsCommonWeb.VerificationPointText(objDictionary,driver, "Violations", "Signed in successfully", 1, "Innertext", "Signed in successfully.");
						//Validate Show Total
						clsCommonWeb.VerificationPointButton(objDictionary, driver, "Violations", "Show Total",1,"Exists");
						driver.quit();
						break;
					case "court_clerk":
						//MPS Logo
						clsCommonWeb.VerificationPointImage(objDictionary, driver, "Violations", "Municipality Logo","Exists");
						//Validate link Violations
						clsCommonWeb.VerificationPointLink(objDictionary,driver, "Violations", "Violations",1,"Exists");
						//Validate link Parking Lots Sessions
						clsCommonWeb.VerificationPointLink(objDictionary,driver, "Violations", "Parking Lot Sessions",1,"Exists");
						//Validate Reports Link Dropdown Items
						clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Violations", "Reports", 1, "Daily Ticket Total|Meter Cash Outs|Notifications|Payments|Weekly Ticket Total|On Hold|Violation Payment Report|Tickets With Officer");
						//Validate Health Link Dropdown Items
						clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Violations", "Health", 1, "Sessions|Trends");
						//Validate Municipality Name
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "AutomationMunicipality",1,"Exists");
						//Validate User Link
						clsCommonWeb.VerificationPointLink(objDictionary,driver, "Violations", strFirstName+" "+strLastName,1,"Exists");
						//Validate Violation # is selected
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Violation #", 1, "Value", "Selected");
						//Validate Violation ID Exists
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Violation ID", 1, "Exists", "");
						//Validate Plate Exists
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Plate", 1, "Exists", "");
						//Validate Lookup ID Exists
						clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Violations", "Search Options", "Lookup ID", 1, "Exists", "");
						//Validate Search
						clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Violations", "Search", 1, "Exists", "");
						//Validate FromDate
						clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Violations", "FromDate", 1, "Exists", "");
						//Validate ToDate
						clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Violations", "ToDate", 1, "Exists", "");
						//Validate Violations Status
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Violation Status", 1, "Item Order", "ALL|AWAITING COLLECTION|CLAIMED|COLLECTIONS|DISMISSED|DISPUTED|EMAILED|END DISPUTE|GENERATING TICKET|HOLD|MISSED|NEW|NOTIFIED|OVERDUE|PAID|PARTIAL|REFUNDED|RESET TICKETING WITH NEW OWNER|SNOOZED|TICKET GENERATED|TICKET GENERATION FAILED|UNHOLD|UNISSUED|UNLOCKED|UNVOIDED|VOIDED");
						//Validate Violation Notifications
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Violation Notifications", 1, "Item Order", "ALL|EXEMPT|NORMAL|NOT PROCESSABLE|REDO|SENT FOR VERIFICATION|SYSTEM FAILURE|TO VERIFY|VERIFICATION FAILED|VERIFIED");
						//Validate Violation Sources
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Violation Sources", 1, "Item Order", "ALL|BOLLARD|HEALTHKIOSK|KIOSK|METER|NOPARKINGSTICK|SOLARSTICK|VIRTUALDEVICE|VIRTUALKIOSK|VIRTUALMETER");
						//Validate Processing Status
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Processing Status", 1, "Item Order", "ALL|EXEMPT|NORMAL|NOT PROCESSABLE|REDO|SENT FOR VERIFICATION|SYSTEM FAILURE|TO VERIFY|VERIFICATION FAILED|VERIFIED");
						//Validate Meter Groups
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Meter Groups", 1, "Item Order", "AMG2|AMG3|AMG4|AUTOMATIONMETERGROUP|AUTOMATIONOPENLOT|DAKOTA|MPS|NO METER|VIRTUALAUTOMATIONMETERGROUP|WOONERFMETERGROUP");
						//Validate Spots
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Spots", 1, "Item Order", "1|5510|5511|5560|5561|5570|5571|5580|5581|9000|9001|9010|9011|9092|9094|9095|9096|9097|9203|9204|9205|9206|9209|9210|9211|9212|9213|9214|9215|9216|9217|9218|9219|9220|9221|9222|9223|9224|9227|9228|AUTOOPENENTRY|AUTOOPENEXIT|AUTOOPENPAY1|AVMETER1|AVMETER2|AVMETER4|AVMETER5|AVMETER6|DAKOTA KIOSK|IVMETER1|VMETER4");
						//Validate Sort By
						clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Violations", "Sort By", 1, "Item Order", "ISSUED TIMESTAMP|VIOLATION NUMBER|TIME PARKED|DURATION");
						//Validate Find Violations
						clsCommonWeb.VerificationPointButton(objDictionary, driver, "Violations", "Find Violations",1,"Exists");
						//Validate Verified
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Verified",1,"Exists");
						//Validate Overdue 1
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Overdue 1",1,"Exists");
						//Validate Overdue 2
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Overdue 2",1,"Exists");
						//Validate Awaiting Collections
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Violations", "Awaiting Collections",1,"Exists");
						//Signed in successfully
						clsCommonWeb.VerificationPointText(objDictionary,driver, "Violations", "Signed in successfully", 1, "Innertext", "Signed in successfully.");
						//Validate Show Total
						clsCommonWeb.VerificationPointButton(objDictionary, driver, "Violations", "Show Total",1,"Exists");
						driver.quit();
						break;
					default:
						//MPS Logo
						clsCommonWeb.VerificationPointImage(objDictionary, driver, "Please choose a Municipality", "Municipality Logo","Exists");
						//Log Out Link
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Please choose a Municipality", "Log Out",1,"Exists");
						//SentryLink Admin Utilities Exists
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities",1,"Exists");
						//Parking Tab
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Please choose a Municipality", "Parking",1,"Exists");
						//Health Tab
						clsCommonWeb.VerificationPointLink(objDictionary, driver, "Please choose a Municipality", "Health",1,"Exists");
						//Validate Number of Municipalities
						clsCommonWeb.VerificationPointTable(objDictionary, driver, "Please choose a Municipality", "Municipalities", 1, "0", "0", "Row Count", "27");
						clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "Log Out", 0);
						driver.quit();
						break;
				}
		}
    }
	@Parameters({"strRole"})@Test(priority=8002,groups={"UserTest"})
	public void UserRoles1002_Municipalities(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		//MPS Logo
		clsCommonWeb.VerificationPointImage(objDictionary, driver, "Please choose a Municipality", "Municipality Logo","Exists");
		//Parking Link
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Please choose a Municipality", "Log Out",1,"Exists");
		//Signed in successfully
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Please choose a Municipality", "Signed in successfully", 1, "Innertext", "Signed in successfully.");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Please choose a Municipality", "Page Header", 1, "Value", "Please choose a Municipality");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Municipalities");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Municipalities", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Add a New Municipality
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Municipalities", "Add a New Municipality",1,"Exists");
		//Validate Purge Decommissioned Meters
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Municipalities", "Purge Decommissioned Meters",1,"Exists");
		//Parking Link
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Municipalities", "Parking",1,"Exists");
		//Health Link
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Municipalities", "Health",1,"Exists");
		//Validate Number of Municipalities/ Header Row Included.
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Municipalities", "Municipalities", 1, "0", "0", "Row Count", "28");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8003,groups={"UserTest"})
	public void UserRoles1003_AuditLogs(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Users", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Audit Logs", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Audit Logs");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Audit Logs", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Audit Logs", "Page Header", 1, "Value", "Audit Logs - Administrative Level");
		//Validate Begin Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Audit Logs", "Begin Date", 1, "Exists", "");
		//Validate End Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Audit Logs", "End Date", 1, "Exists", "");
		//Validate Event Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Audit Logs", "Event", 1, "Item Order", "-- any --|Add Time to Meter|Admin Activity|Bollard Destroyed|Concierge Communication|Device Upkeep|Forcibly Ended Parking Session|Kiosk Gate Override|Last Ticket Number Changed|License Plate Added to POI|License Plate Changed|Long Permit Expiring Warning|Manually Voided Block of Violations|Monitor Plate Hits Run|Parking Lot Reconciled|Parking Permit Auto Renewal|Permit Auto Renew Early Notification Event|Plates Near Crime Scene Run|Quick Plate Search Run|Rate Changes|Spot Destroyed|Spot Seq Changed|Violation State Change Request|Zero Balance Payment Created");
		//Validate Municipality
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Audit Logs", "Municipality", 1, "Item Order", "-- any --|(N/A)|Atlantis|AutomationMunicipality|AutomationTest|Concierge|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test|automationRickyMuni|delete me");
		//Validate Severity
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Audit Logs", "Severity", 1, "Item Order", "-- any --|1|5|10|50|100");
		//Validate Device
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Audit Logs", "Device", 1, "Item Order", "-- any --|1-2|100000|100001|100002|100003|100004|100005|100006|100007|100008|100009|100010|100011|100012|100013|100014|100015|100016|100017|100018|100019|100020|100021|100022|100023|100024|100025|100026|100027|100028|100029|100030|100031|100032|100033|100034|100035|100036|100037|100038|100039|123-456|1744-1745|2019-2020|2221-2222|2231-2232|2300-2301|5510-5511|5521-5552|5560-5561|5570-5571|5580-5581|5591-5592|7|7002-7003|7250-7251|7601-7602|7605|7607-7608|7610-7611|7612-7613|7616-7617|7621-7622|7625-7626|7631-7632|7771-7772|7775-7776|7781-7782|7846-7847|8001-8002|8308|8336-8337|8338|8361-8362|8370-8371|8675-8676|8681-8682|8691|8861-8862|8871-8872|8873-8874|8881-8882|8888-8889|9000-9001|9002-9003|9010-9011|9090|9092|9094-9095|9096-9097|9111-9112|9113-9114|9203-9204|9205-9206|9213-9214|9215-9216|9217-9218|9219-9220|9221-9222|9225-9226|9227-9228|92z|9301-9302|9305-9306|99991|99992|99993|99995|99996|99997|99998|99999|Aentry1|Aentry1-valet|Aentry2|Aentry3|Aexit1|Alex Cam|Apay1|AutoOpenEntry|AutoOpenExit|AutoOpenPay1|aw1-aw2|B001|B670A-B670|Back Street Lot Master|Backlot Pole Cam|Backlot-Cam-NX|brenden2|c343a-c343b|Coin-Test|Dave Test|decommissioned-for-brenden|Entry|Entry2|EntryLotOne|Exit|Exit|Exit Cam|Exit2|ExitLotOne|F2Labs1|F2Labs2|GS 250|GS01|GS02|GS05|GS06|GS07|GS08|HB001-HB002|HB003-HB004|Health Kiosk 2|Health Kiosk 3|Health Kiosk 4|HK Blue|ITK01|ITK04|ITK05|KS001|LBS1-LBS2|Left-Right|Lot Auto One Entry|Lot Auto One Exit|Lot Auto One Pay|LotDentry1|LotDpay1|Mark Lot A Pay|Mark LotD Pay|MarkPay|MarkPay2|MarkPay3|Mirro 1|MPS Demo-MPS DemoR|MPS Open Lot|NPLP1-NPLP2|NPLP3|NPM1|NPM3-NPM4|NPM6|ODI045|OneEntry|OneExit|OnePay3|P111 Entry|P111 Entry2|P111 Entry3|P111 Exit|P151-Entry SentryLite|P151-Exit SentryLite|P250 Entry|P250 Exit|P999 Entry|P999 Exit|Park Place Entry|Park Place Exit|Pay2|PCS40|Pentry|Pexit|PR007|Pwest2 Entry|PWest2 Exit|PWest3 Entry|PWest3 Exit|PWest5 Entry|PWest5 Exit|QA Cam 1|SAL1|SAMPLE-01:0|SAMPLE-17:2|SAMPLE-25:6|SAMPLE-43:7|SAMPLE-43:7|SAMPLE-58:8|SAMPLE-67:2|SAMPLE-67:4|SAMPLE-72:6|SAMPLE-74:6|SAMPLE-76:0|SAMPLE-78:5|SH68|SH69|SH72|SH79|SH80|SolarTest|SS23|SSG2 501|SSG2 502|SSG2 503|SSG2 504|SSG2 505|SSG2 506|SSG2 507|SSG2 508|SSG2 509|SSG2 510|Street Entry Valet|Street-Entry|Street-Exit|Test Nano|TwoPay2|XNPM1|XPRV3-XPRV4|Zone 1 Entry|Zone 1 Exit|Zone 1 Pay");
		//Validate User Email
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Audit Logs", "User Email", 1, "Exists", "");
		//Validate User Name
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Audit Logs", "User Name", 1, "Exists", "");
		//Validate Audit Logs Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Audit Logs", "Audit Logs", 1, "1", "1", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1004_Carts(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Users", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Carts", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Carts");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Carts", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Carts", "Page Header", 1, "Value", "Carts");
		//Validate Email Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Carts", "Email Search", 1, "Exists", "");
		//Validate Begin Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Carts", "Begin Date", 1, "Exists", "");
		//Validate End Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Carts", "End Date", 1, "Exists", "");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Carts", "Refresh",1,"Exists");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1004_ConciergeVehicleTagRegistrations(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Users", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Concierge Vehicles", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Concierge Vehicle Tag Registrations");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Concierge Vehicle Tag Registrations", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Concierge Vehicle Tag Registrations", "Page Header", 1, "Value", "Concierge Vehicle Tag Registrations");
		//Validate Email Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Concierge Vehicle Tag Registrations", "Email Search", 1, "Exists", "");
		//Validate Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Concierge Vehicle Tag Registrations", "Name Search", 1, "Exists", "");
		//Validate License Plate
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Concierge Vehicle Tag Registrations", "License Plate", 1, "Exists", "");
		//Validate Status Listbox
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Concierge Vehicle Tag Registrations", "Status", 1, "Item Order", "|ALL|Pending|Matching|Verified|Failed|Active|Deenrolled");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1005_ApprovedUsers(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Users", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Approved User", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Approved Users");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Approved Users", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Approved Users", "Page Header", 1, "Innertext", "Approved Users");
		//Validate Add a New Municipality
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Approved Users", "New User",1,"Exists");
		//Validate Email Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Approved Users", "First Name", 1, "Exists", "");
		//Validate Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Approved Users", "Last Name", 1, "Exists", "");
		//Validate License Plate
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Approved Users", "Email", 1, "Exists", "");
		//Validate License Plate
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Approved Users", "Municipality", 1, "Exists", "");
		//Validate Status Listbox
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Approved Users", "Roles", 1, "Item Order", "all|admin|api_application|api_ticket_service|automated_process|coin_collector|collection_agency|court_clerk|csr|employee|health_admin|health_clerk|lot_security|merchant|municipality_admin|parker|parking_enforcement_officer|read_only|session_voider|solar_stick_device|solar_stick_owner|violation_monitor");
		//Validate Status Listbox
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Approved Users", "Deleted?", 1, "Item Order", "true|false");
		//Validate Add a New Municipality
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Approved Users", "Search",1,"Exists");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1006_ActivityFeeItems(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alerts", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alert Activity Feed", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Activity Feed Items");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Activity Feed Items", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Activity Feed Items", "Page Header", 1, "Innertext", "Activity Feed Items");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Activity Feed Items", "Refresh",1,"Exists");
		//Validate Device Name
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Activity Feed Items", "Device Name", 1, "Exists", "");
		//Validate Device Mac
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Activity Feed Items", "Device Mac", 1, "Exists", "");
		//Validate Keyword
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Activity Feed Items", "Keyword", 1, "Exists", "");
		//Validate Begin Date Time
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Activity Feed Items", "Begin Date Time", 1, "Exists", "");
		//Validate End Date Time
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Activity Feed Items", "End Date Time", 1, "Exists", "");
		//Validate Activity Feed Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Activity Feed Item", "Activity Feed", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1007_AlertFamilies(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alerts", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alert Families", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Listing Alert Families");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Listing Alert Families", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Listing Alert Families", "Page Header", 1, "Value", "Listing Alert Families");
		//Validate Page Directions
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Listing Alert Families", "Page Directions", 1, "Value", "The Alert Family names can be edited here. These family names should be chosen carefully and should be consistent between the U.S. and Canada.");
		//Validate Activity Feed Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Listing Alert Families", "Alert Families", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1008_AlertForwardingHistories(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alerts", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alert Forwarding Histories", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Alert Forwarding Histories");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Alert Forwarding Histories", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Alert Forwarding Histories", "Page Header", 1, "Value", "Alert Forwarding Histories");
		//Validate Municipality
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Histories", "Municipality", 1, "Item Order", "NONE|Atlantis|AutomationMunicipality|automationRickyMuni|AutomationTest|Concierge|delete me|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test");
		//Validate Alert Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Forwarding Histories", "Alert Name Search", 1, "Exists", "");
		//Validate Device Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Forwarding Histories", "Device Name Search", 1, "Exists", "");
		//Validate Alert id Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Forwarding Histories", "Alert id Search", 1, "Exists", "");
		//Validate Municipality Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Histories", "Municipality", 1, "Item Order", "NONE|Atlantis|AutomationMunicipality|automationRickyMuni|AutomationTest|Concierge|delete me|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test");
		//Validate Alert Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Histories", "Alert Type", 1, "Item Order", "ALL|SentryLink|Sentry");
		//Validate Forwarding Options Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Histories", "Forwarding Options", 1, "Item Order", "ALL|Snmp|Email|Voice|SMS");
		//Validate Between This
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Forwarding Histories", "Between This", 1, "Exists", "");
		//Validate And This
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Forwarding Histories", "And This", 1, "Exists", "");
		//Validate Activity Feed Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Alert Forwarding Histories", "Alert Forwarding", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1009_AlertForwardingSummary(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alerts", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alert Forwarding Summary", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Alert Forwarding Summary");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Alert Forwarding Summary", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Alert Forwarding Summary", "Page Header", 1, "Value", "Alert Forwarding Summary");
		//Validate Municipality
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Summary", "Municipality", 1, "Item Order", "NONE|Atlantis|AutomationMunicipality|automationRickyMuni|AutomationTest|Concierge|delete me|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test");
		//Validate Device Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Forwarding Summary", "Device Name Search", 1, "Exists", "");
		//Validate Alert Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Summary", "Alert Type", 1, "Item Order", "ALL|SentryLink|Sentry");
		//Validate Forwarding Options Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Forwarding Summary", "Forwarding Options", 1, "Item Order", "ALL|Snmp|Email|Voice|SMS");
		//Validate Hide alerts with Default
		clsCommonWeb.VerificationPointCheckbox(objDictionary, driver, "Alert Forwarding Summary", "Hide alerts with Default", 1, "Exists", "");
		//Validate Activity Feed Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Alert Forwarding Summary", "Alert Summary", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1010_AlertRecipients(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alerts", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alert Recipients", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Alert Recipients");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Alert Recipients", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Alert Recipients", "Page Header", 1, "Value", "Alert Recipients");
		//Validate Recipient Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Recipients", "Recipient Type", 1, "Item Order", "|ALL|Snmp|Email|Voice|SMS");
		//Validate Validated Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Alert Recipients", "Validated Type", 1, "Item Order", "|ALL|Validated|NOT Validated");
		//Validate Target Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Alert Recipients", "Target Search", 1, "Exists", "");
		//Validate Activity Feed Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Alert Recipients", "Recipients", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1011_ListingSentryAlertTypes(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Alerts", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Sentry Alert Types", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Listing Sentry Alert Types");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Listing Sentry Alert Types", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Listing Sentry Alert Types", "Page Header", 1, "Innertext", "Listing Sentry Alert Types");
		//Validate New Sentry Alert Tyep
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Listing Sentry Alert Types", "New Sentry Alert Type",1,"Exists");
		//Validate Activity Feed Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Listing Sentry Alert Types", "Alert Types", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1012_AmberAlert(@Optional String strRole)
    {
		//Steps to enable Amber Alers per Chris
		//You have to enable in SL | ADMIN SETTINGS
		//Then you need to load a file to the meter and will display in between idle messages
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Amber Alerts", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Active Amber Alerts");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Active Amber Alerts", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Active Amber Alerts", "Page Header", 1, "Value", "Active Amber Alerts");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1012_AttendantCalls(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Attendant Calls", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Attendant Calls");
		//Validate From DateTime
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Calls", "From DateTime", 1, "Exists", "");
		//Validate To DateTime
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Calls", "To DateTime", 1, "Exists", "");
		//Validate Municipality Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Attendant Calls", "Municipality", 1, "Item Order", "(ALL)|ATLANTIS|AUTOMATIONMUNICIPALITY|AUTOMATIONRICKYMUNI|AUTOMATIONTEST|CONCIERGE|DELETE ME|EMPTY MUNI|EXCELSIOR, MN|HAPPYVILLE SOLAR|HEALTH MF|HEALTH QA|HIGHBRIDGE|ISS|KERNERSVILLE,NC|LIKE BELL|LIKE CHAVEZ|LIKE HAMTRAMCK|LIKE ISRAEL|LIKE LAS VEGAS|LIKE LOWELL|LIKE MILL CITY|LIKE PREMIUM|LIKE ROYAL OAK|LIKE TUFTS|LOT AUTOMATION|MANUFACTURING|SALIENT|TEST ANG|TEST QUESTIONS|TEXAS|VIA RAIL CANADA TEST");
		//Validate Id is selected
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Attendant Calls", "Device Options", "Id", 1, "Value", "Selected");
		//Validate Name Exists
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Attendant Calls", "Device Options", "Name", 1, "Exists", "");
		//Validate Mac Address
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Attendant Calls", "Device Options", "MAC Address", 1, "Exists", "");
		//Validate Device Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Calls", "Device Search", 1, "Exists", "");
		//Validate Find Attendant Calls
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Attendant Calls", "Find Attendant Calls",1,"Exists");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Attendant Calls", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Attendant Calls", "Page Header", 1, "Value", "Attendant Calls");
		//Validate Call Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Attendant Calls", "Calls", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1013_AttendantOverrides(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Attendant Overrides", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Attendant Overrides");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Attendant Overrides", "Page Header", 1, "Value", "Attendant Overrides");
		//Validate From DateTime
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Overrides", "From DateTime", 1, "Exists", "");
		//Validate To DateTime
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Overrides", "To DateTime", 1, "Exists", "");
		//Validate Municipality Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Attendant Overrides", "Municipality", 1, "Item Order", "(ALL)|ATLANTIS|AUTOMATIONMUNICIPALITY|AUTOMATIONRICKYMUNI|AUTOMATIONTEST|CONCIERGE|DELETE ME|EMPTY MUNI|EXCELSIOR, MN|HAPPYVILLE SOLAR|HEALTH MF|HEALTH QA|HIGHBRIDGE|ISS|KERNERSVILLE,NC|LIKE BELL|LIKE CHAVEZ|LIKE HAMTRAMCK|LIKE ISRAEL|LIKE LAS VEGAS|LIKE LOWELL|LIKE MILL CITY|LIKE PREMIUM|LIKE ROYAL OAK|LIKE TUFTS|LOT AUTOMATION|MANUFACTURING|SALIENT|TEST ANG|TEST QUESTIONS|TEXAS|VIA RAIL CANADA TEST");
		//Validate To User
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Overrides", "User", 1, "Exists", "");
		//Validate To Reason
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Overrides", "Reason", 1, "Exists", "");
		//Validate Id is selected
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Attendant Overrides", "Device Options", "Id", 1, "Value", "Selected");
		//Validate Name Exists
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Attendant Overrides", "Device Options", "Name", 1, "Exists", "");
		//Validate Device Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Attendant Overrides", "Device Search", 1, "Exists", "");
		//Validate Find Attendant Calls
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Attendant Overrides", "Find Attendant Overrides",1,"Exists");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Attendant Overrides", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Call Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Attendant Overrides", "Overrides", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1014_DeviceSummary(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Device Summary", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Device Summary");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Device Summary", "Page Header", 1, "Value", "Device Summary");
		//Validate MAC Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Summary", "MAC Search", 1, "Exists", "");
		//Validate IP Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Summary", "IP Search", 1, "Exists", "");
		//Validate Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Summary", "Name Search", 1, "Exists", "");
		//Validate Connection Latency worse than (ms)
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Summary", "Connection Latency worse than (ms)", 1, "Exists", "");
		//Validate AutoRefresh
		clsCommonWeb.VerificationPointCheckbox(objDictionary, driver, "Device Summary", "AutoRefresh", 1, "Exists", "");
		//Validate Municipality
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Device Summary", "Municipality", 1, "Item Order", "NONE|ALL|Atlantis|AutomationMunicipality|automationRickyMuni|AutomationTest|Concierge|delete me|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test");
		//Validate Group / Lot
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Device Summary", "Group / Lot", 1, "Item Order", "ALL|12450 Bacon Lot|(1) Tufts University Medford/Somerville|(1) Tufts University Medford/Somerville|80 George St Lot|80 George St Lot|a|A|A|A|A|A|A|A|A|A|A|A|A|A|Alex|Alex Minnetonka|AMG2|AMG3|AMG4|Angie's MG|Atlantic City|AutomationMeterGroup|AutomationMeterGroup|AutomationOpenLot|Back Lot|Back Lot Gated|Back Street Lot|Back Street Lot Valet|Build|Bus Stop|Cesar Batalla Way|Chris 2 Meter Group|Coin Test|Customer Service|Dakota|Dedham AE|Demo|Dev Sentry|Door Test|Dual Meter Test|dummy|El Paso|Garage A|Garage A Valet|GatedLot|GP Group 1|Grand Slam|Health Dev|Health Test|HoustonEV|Hudson|Intertek Meter Group|Iowa|ISS|Jim's and Paul's Office|Location 1|Location 2|LOT 1|Lot 10|LOT 10|Lot 11|LOT 2|LOT 3|LOT 4|LOT 5|LOT 6|LOT 7|LOT 9|Lot Auto One|Lot D|Lot E|Lot F|Lot One|Lot One Valet|Lot Two|Marcus' meter group|Merchant Lot|Meters|Meters Backlot|Meter Sim|MG Create|Miami|Mixed|More Meters|MPS|MPS|MPS CT Meters|MPS Dev|MPS Lab|MPS Lot East|MPS Test|Nahariya|New Group|NJ - DE - no auto ticketing|No Lot|No Lot|No Lot|No Meter|No Meter|No Meter|No Meter|OpenLot Test|P111|P1211|P144|P149-P150|P151 Boulder AI|P175|P2107|P250 Boulder AI|P2752|P618|P999|Padma's Meter Group|PWest2|PWest3|PWest5|Ricky meter group|Ricky meter group|ROAMG1|ROAMG2|Safety|Safety Test|Sales|Salient|Sample Gated Lot 1|Screen Test|SECTION M|SECTION N|SECTION O|SECTION P|Sentry Sim|Single Meter Test|South Dakota|SRO Meter Group|StagingTest|temp meter group|Tentative Lot|test|test|Test|Test|Test ANG|Test Automation|Test Health|Test Meters|Toronto|Tristan Meter Group|Ukraine|UL1|VIA Test|VIA Test|VirtualAutomationMeterGroup|VirtualAutomationMeterGroup|VirtualAutomationMeterGroup|WoonerfMeterGroup|Zone 1|Zone 1|Zone 2|Zone 2|Zone 3");
		//Validate Device Status
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Device Summary", "Device Status", 1, "Item Order", "ALL|DOWN|WARN|UP");
		//Validate Device Status
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Device Summary", "Device Type", 1, "Item Order", "ALL|HealthKiosk|Kiosk|Meter|NoParkingStick|SolarStick");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Device Summary", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Call Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Device Summary", "Device Summary", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1015_ExemptionReasons(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Exemption Reasons", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Exemption Reasons");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Exemption Reasons", "Page Header", 1, "Value", "Exemption Reasons");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Exemption Reasons", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Call Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Exemption Reasons", "Exemption Reasons", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1016_HolidayVoiding(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Holiday Voiding", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Void Violations");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Void Violations", "Page Header", 1, "Value", "Void Violations");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Void Violations", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Municipality
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Void Violations", "Municipality", 1, "Item Order", "Health QA|Atlantis|Concierge|Test ANG|AutomationTest|Like Premium|Kernersville,NC|Like Chavez|Like Mill City|Like Lowell|Health MF|Test Questions|Like Bell|Like Las Vegas|Empty Muni|Like Hamtramck|automationRickyMuni|HighBridge|Like Israel|Lot Automation|Via Rail Canada Test|Salient|AutomationMunicipality|Happyville Solar|delete me|Excelsior, MN|Texas|Manufacturing|ISS|Like Royal Oak|Like Tufts");
		//Validate Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Void Violations", "Date", 1, "Exists", "");
		//Validate Start Hour
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Void Violations", "Start Hour", 1, "Item Order", "0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24");
		//Validate End Hour
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Void Violations", "End Hour", 1, "Item Order", "0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24");
		//Validate Void Violations
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Void Violations", "Void Violations",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1017_ImportantWorkers(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Important Workers", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Important Workers");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Important Workers", "Page Header", 1, "Value", "Important Workers");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Important Workers", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Workers Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Important Workers", "Workers", 1, "0", "0", "Exists", "");
		//Validate Void Violations
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Important Workers", "New Important worker",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1018_MobileRegistrations(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Mobile Registrations", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Mobile Registrations");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Mobile Registrations", "Page Header", 1, "Value", "Mobile Registrations");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Mobile Registrations", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Email Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Mobile Registrations", "Email Search", 1, "Exists", "");
		//Validate Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Mobile Registrations", "Name Search", 1, "Exists", "");
		//Validate Version Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Mobile Registrations", "Version Search", 1, "Exists", "");
		//Validate Municipality Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Mobile Registrations", "Municipality", 1, "Item Order", "NONE|ALL|Atlantis|AutomationMunicipality|automationRickyMuni|AutomationTest|Concierge|delete me|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test");
		//Validate Role Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Mobile Registrations", "Role", 1, "Item Order", "ALL|Android|IOS");
		//Validate OS Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Mobile Registrations", "OS", 1, "Item Order", "ALL|admin|api_application|api_ticket_service|automated_process|coin_collector|collection_agency|court_clerk|csr|employee|health_admin|health_clerk|lot_security|merchant|municipality_admin|parker|parking_enforcement_officer|read_only|session_voider|solar_stick_device|solar_stick_owner|violation_monitor");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Mobile Registrations", "Refresh",1,"Exists");
		//Validate Registrations Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Mobile Registrations", "Registrations", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1019_NotificationTemplate(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Notification Templates", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Notification Templates");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Notification Templates", "Page Header", 1, "Value", "Notification Templates");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Notification Templates", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Registrations Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Notification Templates", "Templates", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1020_ProvisionService(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Provisioning Service", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Central Device Provision Service");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Central Device Provision Service", "Page Header", 1, "Value", "Central Device Provision Service");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Central Device Provision Service", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Municipality Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Central Device Provision Service", "Municipality", 1, "Item Order", "Health QA|Atlantis|Concierge|Test ANG|AutomationTest|Like Premium|Kernersville,NC|Like Chavez|Like Mill City|Like Lowell|Health MF|Test Questions|Like Bell|Like Las Vegas|Empty Muni|Like Hamtramck|automationRickyMuni|HighBridge|Like Israel|Lot Automation|Via Rail Canada Test|Salient|AutomationMunicipality|Happyville Solar|delete me|Excelsior, MN|Texas|Manufacturing|ISS|Like Royal Oak|Like Tufts");
		//Validate Device Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Central Device Provision Service", "Device Type", 1, "Item Order", "meter|kiosk");
		//Validate MAC Address
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Central Device Provision Service", "MAC Address", 1, "Exists", "");
		//Validate Video Card MAC Address
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Central Device Provision Service", "Video Card MAC Address", 1, "Exists", "");
		//Validate Provision Device Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Central Device Provision Service", "Provision Device",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1021_PurgeManager(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Purge Manager", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Purge Manager");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Purge Manager", "Page Header", 1, "Value", "Purge Manager");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Purge Manager", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate MAC Address
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Purge Manager", "Worker Name Search", 1, "Exists", "");
		//Validate Provision Device Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Purge Manager", "Refresh",1,"Exists");
		//Validate Registrations Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Purge Manager", "Purge Workers", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1022_DeviceCloudFiles(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Push Notification Files", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Device Cloud Files");
		//Validate Received is selected
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Device Cloud Files", "Filter Options", "Received", 1, "Value", "Selected");
		//Validate Queued Existed
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Device Cloud Files", "Filter Options", "Queued", 1, "Exists", "");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Device Cloud Files", "Page Header", 1, "Value", "Device Cloud Files");
		//Validate From Time Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Cloud Files", "From Time Date", 1, "Exists", "");
		//Validate From Time Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Cloud Files", "To Time Date", 1, "Exists", "");
		//Validate File Name
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Cloud Files", "File Name", 1, "Exists", "");
		//Validate Parking Session Id
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Cloud Files", "Parking Session Id", 1, "Exists", "");
		//Validate Id is selected
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Device Cloud Files", "Device Options", "Id", 1, "Value", "Selected");
		//Validate Name Existed
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Device Cloud Files", "Device Options", "Name", 1, "Exists", "");
		//Validate MAC Address Existed
		clsCommonWeb.VerificationPointRadioButton(objDictionary, driver, "Device Cloud Files", "Device Options", "MAC Address", 1, "Exists", "");
		//Validate Device
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Cloud Files", "Device", 1, "Exists", "");
		//Validate Device
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Device Cloud Files", "File Type", 1, "Exists", "");
		//Validate Error
		clsCommonWeb.VerificationPointCheckbox(objDictionary, driver, "Device Cloud Files", "Errors", 1, "Exists", "");
		//Validate Find Files Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Device Cloud Files", "Find Files",1,"Exists");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Device Cloud Files", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Registrations Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Device Cloud Files", "Device Cloud Files", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1023_Rpcs(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "RPC's", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Rpcs");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Rpcs", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Rpcs", "Page Header", 1, "Value", "Rpcs");
		//Validate Begin Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Rpcs", "Begin Date", 1, "Exists", "");
		//Validate End Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Rpcs", "End Date", 1, "Exists", "");
		//Validate MAC Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Rpcs", "MAC Search", 1, "Exists", "");
		//Validate Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Rpcs", "Name Search", 1, "Exists", "");
		//Validate Endpoint Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Rpcs", "Endpoint", 1, "Item Order", "ALL|camera|coin|epay|file_io|health|lot_parking|lot_rates|openlot_rates|parking|parking_lot|permits|plate_camera|settings|system|vehicle_detector");
		//Validate Method Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Rpcs", "Method", 1, "Item Order", "ALL|add_mobile_payment|add_rate_block|add_rate_blocks|add_reservation|add_support_payment|clear_concierge_violation|clear_maintenance_mode|clear_permitted_violation|clear_reservation_violation|clear_violation|delete|delete_reservation|directory|end_parking_session|force_puck_watchdog_event|get|get_available_settings|get_coins_inserted|get_coins_inserted_by_type|get_dispense_count|get_free_ram|get_last_harvest_datetime|get_last_harvest_value|get_parker_cards_in_lot|get_parker_plates_in_lot|get_parker_plates_n_cards_in_lot|get_percent_full|get_permitted_parker_list|get_persist_gate_arm_status|get_sessions_extended_data|get_setting|get_software_version|get_spot_session_info|get_stored_value|lock_session|minutes_for_money|reboot|save|sb_unicorn_settings_push|sb_unicorn_spot_reset|set_camera_configurations|set_maintenance_mode|set_pin|set_rates|set_setting|spot_ticket_issued|sync|update_all_permits|update_concierge_list|update_gatelot_tables|update_permit");
		//Validate State Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Rpcs", "State", 1, "Item Order", "ALL|new|sending|sent|failed|error");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Rpcs", "Refresh",1,"Exists");
		//Validate Registrations Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Rpcs", "Rpcs", 1, "0", "0", "Exists", "");
		//Validate Edit RPC Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Rpcs", "Edit RPC",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1024_SentryErrors(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Sentry Errors", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Sentry Errors");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Sentry Errors", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Sentry Errors", "Page Header", 1, "Value", "Sentry Errors");
		//Validate Begin Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Errors", "Name", 1, "Exists", "");
		//Validate Error Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Sentry Errors", "Error Type", 1, "Item Order", "|any|coin|session|card");
		//Validate Coin Reject Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Sentry Errors", "Coin Rejected", 1, "Item Order", "|any|Yes|Possible|Maybe|Unknown");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Errors", "Search",1,"Exists");
		//Validate Sentry Errors Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Sentry Errors", "Sentry Errors", 1, "0", "0", "Exists", "");
		//Validate Edit RPC Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Errors", "Edit",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1025_ScaleRules(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Scale Rules", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Scale Rules");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Scale Rules", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Scale Rules", "Page Header", 1, "Value", "Scale Rules");
		//Validate Error Type Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Scale Rules", "Scale Timeslot", 1, "Item Order", "Cooldown (0-2)|Nighttime (3-10)|Warmup (11-13)|Day Peak (14-20)|Evening Peak (21-23)|Maintenance (99-100)");
		//Validate Sentry Errors Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Scale Rules", "Scale Rules", 1, "0", "0", "Exists", "");
		//Validate Edit RPC Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Scale Rules", "Edit",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1026_ScaleTimeslots(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Scale Timeslots", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Scale Timeslots");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Scale Timeslots", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Scale Timeslots", "Page Header", 1, "Value", "Scale Timeslots");
		//Validate Sentry Errors Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Scale Timeslots", "Scale Timeslots", 1, "0", "0", "Exists", "");
		//Validate Edit RPC Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Scale Rules", "Edit",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1027_SerialQueues(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Serial Queues", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Serial Queues");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Scale Timeslots", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Serial Queues", "Page Header", 1, "Value", "Serial Queues");
		//Validate Scale Timeslots Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Serial Queues", "Queue Contents", 1, "0", "0", "Exists", "");
		//Validate Edit Queue Manager Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Serial Queues", "Edit Queue Manager",1,"Exists");
		//Validate Sentry Errors Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Serial Queues", "Queue Record", 1, "0", "0", "Exists", "");
		//Validate Edit Queue Manager Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Serial Queues", "Edit Serial Queue Record",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1028_SystemHealth(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "System Health", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "System Health");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "System Health", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "System Health", "Page Header", 1, "Value", "System Health");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1029_TicketServiceData(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Ticket Service Data", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Ticket Service Data");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Ticket Service Data", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Ticket Service Data", "Page Header", 1, "Value", "Ticket Service Data");
		//Validate Model Items
		clsCommonWeb.VerificationPointListbox( objDictionary, driver, "Ticket Service Data", "Model", 1, "Item Order", "CaseStatus|DriversLicenseRestrictionClass|DriversLicenseRestrictionType|EquipmentType|VehicleBodyType|VehicleColor|VehicleMake|VehicleModel|EyeColor");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Ticket Service Data", "Refresh",1,"Exists");
		//Validate Ticket Service Data Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Ticket Service Data", "Ticket Service Data", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1030_ViolationRejectionReasons(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Violation Rejection Reasons", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Violation Rejection Reasons");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Violation Rejection Reasons", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Violation Rejection Reasons", "Page Header", 1, "Value", "Violation Rejection Reasons");
		//Validate Ticket Service Data Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violation Rejection Reasons", "Violation Rejection Reasons", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1031_VoidParkingSessions(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Void Parking Sessions", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Void Parking Sessions");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Void Parking Sessions", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Void Parking Sessions", "Page Header", 1, "Value", "Void Parking Sessions");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1032_ImportantWorkerHistory(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Maintenance", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Worker History", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Important Worker History");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Important Worker History", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Important Worker History", "Page Header", 1, "Value", "Important Worker History");
		//Validate Worker Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Important Worker History", "Worker Name Search", 1, "Exists", "");
		//Validate Begin Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Important Worker History", "Begin Date", 1, "Exists", "");
		//Validate End Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Important Worker History", "End Date", 1, "Exists", "");
		//Validate Ignore PurgeManager and OpenLotViolations Workers Checked
		clsCommonWeb.VerificationPointCheckbox(objDictionary, driver, "Important Worker History", "Ignore PurgeManager and OpenLotViolations Workers", 1, "Value", "Checked");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Important Worker History", "Refresh",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1033_SentrySettings(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Meter Settings", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Sentry Settings", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Sentry Settings");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Sentry Settings", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Sentry Settings", "Page Header", 1, "Value", "Sentry Settings");
		//Validate Name
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings", "Name", 1, "Exists", "");
		//Validate Description
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings", "Description", 1, "Exists", "");
		//Validate Priority
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings", "Priority", 1, "Exists", "");
		//Validate Default Value
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings", "Default Value", 1, "Exists", "");
		//Validate Value Type
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings", "Value Type", 1, "Exists", "");
		//Validate VFirst Appeared in Egg
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings", "First Appeared in Egg", 1, "Exists", "");
		//Reboot Required
		clsCommonWeb.VerificationPointCheckbox(objDictionary, driver, "Sentry Settings", "Reboot Required", 1, "Value", "UnChecked");
		//Locked?
		clsCommonWeb.VerificationPointCheckbox(objDictionary, driver, "Sentry Settings", "Locked", 1, "Value", "UnChecked");
		//Validate Set Items
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Sentry Settings", "Set", 1, "Item Order", "||Camera|Detection|Fines|Health|Lot|M2M|Messaging|Other|Payments|System|Violation");
		//Validate Sentry Settings Table
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Sentry Settings", "Sentry Settings", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1034_SentrySettingSets(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Meter Settings", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Sentry Setting Sets", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Sentry Setting Sets");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Sentry Setting Sets", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Sentry Setting Sets", "Page Header", 1, "Value", "Sentry Setting Sets");
		//Validate Sentry Setting Sets
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Sentry Setting Sets", "Sentry Setting Set", 1, "0", "0", "Exists", "");
		//Validate
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Sentry Setting Sets", "New Sentry Setting Set",1,"Exists");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1035_SentrySettingsTemplateManager(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Meter Settings", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Templates", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Sentry Settings Template Manager");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Sentry Settings Template Manager", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Sentry Settings Template Manager", "Page Header", 1, "Value", "Sentry Settings Template Manager");
		//Validate Template Type
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Sentry Settings Template Manager", "Template Type", 1, "Item Order", "ALL|Device|MeterGroup|Municipality");
		//Validate Municipality
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Sentry Settings Template Manager", "Municipality", 1, "Item Order", "ALL|Atlantis|AutomationMunicipality|automationRickyMuni|AutomationTest|Concierge|delete me|Empty Muni|Excelsior, MN|Happyville Solar|Health MF|Health QA|HighBridge|ISS|Kernersville,NC|Like Bell|Like Chavez|Like Hamtramck|Like Israel|Like Las Vegas|Like Lowell|Like Mill City|Like Premium|Like Royal Oak|Like Tufts|Lot Automation|Manufacturing|Salient|Test ANG|Test Questions|Texas|Via Rail Canada Test");
		//Validate Group/Lot
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Sentry Settings Template Manager", "Group/Lot", 1, "Item Order", "ALL|12450 Bacon Lot|(1) Tufts University Medford/Somerville|(1) Tufts University Medford/Somerville|80 George St Lot|80 George St Lot|a|A|A|A|A|A|A|A|A|A|A|A|A|A|Alex|Alex Minnetonka|AMG2|AMG3|AMG4|Angie's MG|Atlantic City|AutomationMeterGroup|AutomationMeterGroup|AutomationOpenLot|Back Lot|Back Lot Gated|Back Street Lot|Back Street Lot Valet|Build|Bus Stop|Cesar Batalla Way|Chris 2 Meter Group|Coin Test|Customer Service|Dakota|Dedham AE|Demo|Dev Sentry|Door Test|Dual Meter Test|dummy|El Paso|Garage A|Garage A Valet|GatedLot|GP Group 1|Grand Slam|Health Dev|Health Test|HoustonEV|Hudson|Intertek Meter Group|Iowa|ISS|Jim's and Paul's Office|Location 1|Location 2|LOT 1|Lot 10|LOT 10|Lot 11|LOT 2|LOT 3|LOT 4|LOT 5|LOT 6|LOT 7|LOT 9|Lot Auto One|Lot D|Lot E|Lot F|Lot One|Lot One Valet|Lot Two|Marcus' meter group|Merchant Lot|Meters|Meters Backlot|Meter Sim|MG Create|Miami|Mixed|More Meters|MPS|MPS|MPS CT Meters|MPS Dev|MPS Lab|MPS Lot East|MPS Test|Nahariya|New Group|NJ - DE - no auto ticketing|No Lot|No Lot|No Lot|No Meter|No Meter|No Meter|No Meter|OpenLot Test|P111|P1211|P144|P149-P150|P151 Boulder AI|P175|P2107|P250 Boulder AI|P2752|P618|P999|Padma's Meter Group|PWest2|PWest3|PWest5|Ricky meter group|Ricky meter group|ROAMG1|ROAMG2|Safety|Safety Test|Sales|Salient|Sample Gated Lot 1|Screen Test|SECTION M|SECTION N|SECTION O|SECTION P|Sentry Sim|Single Meter Test|South Dakota|SRO Meter Group|StagingTest|temp meter group|Tentative Lot|test|test|Test|Test|Test ANG|Test Automation|Test Health|Test Meters|Toronto|Tristan Meter Group|Ukraine|UL1|VIA Test|VIA Test|VirtualAutomationMeterGroup|VirtualAutomationMeterGroup|VirtualAutomationMeterGroup|WoonerfMeterGroup|Zone 1|Zone 1|Zone 2|Zone 2|Zone 3");
		//Validate Name Search
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Sentry Settings Template Manager", "Name Search", 1, "Exists", "");
		//Validate Refresh Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Settings Template Manager", "Refresh",1,"Exists");
		//Validate Delete Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Settings Template Manager", "Delete",1,"Exists");
		//Validate Duplicate Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Settings Template Manager", "Duplicate",1,"Exists");
		//Validate Create From Generic (ALL Meters) Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Settings Template Manager", "Create From Generic (ALL Meters)",1,"Exists");
		//Validate Export Button
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Sentry Settings Template Manager", "Export",1,"Exists");
		//Validate Link Create a new template
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Sentry Settings Template Manager", "Create a new template",1,"Exists");
		//Validate Link Import JSON
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Sentry Settings Template Manager", "Import JSON",1,"Exists");
		//Validate Link Import from Device (Using Generic Template)
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Sentry Settings Template Manager", "Import from Device (Using Generic Template)",1,"Exists");
		//Validate Sentry Setting Sets
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Sentry Settings Template Manager", "Templates", 1, "0", "0", "Exists", "");
		//Close Browser
		driver.quit();
    }
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1036_DiffToolForTemplates(@Optional String strRole)
    {
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		String strEnvironment = objDictionary.get("strEnvironment");
		objDictionary.put("strRemainParkedShortSession","False");
		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", "SentryLink Admin Utilities", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Meter Settings", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Municipalities", "Template Diff Tool", 1);
		//Validate Admin Common Validations
		UserRoles_Admin_Commom(objDictionary, driver, "Diff Tool For Templates");
		//Validate Application Side Bar
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Diff Tool For Templates", "ApplicationSideBar", 1, "ACTIVITY FEED");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Diff Tool For Templates", "Page Header", 1, "Value", "Diff Tool For Templates");
		//Validate Compare This Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Diff Tool For Templates", "Compare This", 1, "Item Order", "|008703 Device Template|0B96L-0B96R Device Template|0E8308 Device Template|0E830A Device Template|0E8329 Device Template|0E906E Device Template|0E9090 Device Template|0E956E Device Template|0EA4F2 Device Template|0EA4F5 Device Template|0EA4F7 Device Template|0FAF21 Device Template|0FB363 Device Template|0FB473 Device Template|0FB476 Device Template|0FB478 Device Template|0FB4AF Device Template|0FB4B1 Device Template|0FB560 Device Template|0FB670 Device Template|0FB671 Device Template|0FB675 Device Template|0FB67B Device Template|0FB691 Device Template|0FB6BA Device Template|0FB6BB Device Template|0FB9FF Device Template|0FC340 Device Template|0FC343 Device Template|0FD725 Device Template|10C4BD Device Template|10CD05 Device Template|111265 Device Template|127D81 Device Template|127D82 Device Template|127DA1 Device Template|127F11 Device Template|128022 Device Template|128039 Device Template|12832E Device Template|128336 Device Template|128338 Device Template|128366 Device Template|12836A Device Template|128FEF Device Template|129110 Device Template|1-2 m2m settings (TEMP)|14757C Device Template|1476B4 Device Template|14782B Device Template|14782F Device Template|147846 Device Template|147846 Device Template|147851 Device Template|147865 Device Template|147878 Device Template|1479A6 Device Template|14B5CE Device Template|14B5E3 Device Template|14B60F Device Template|1B025B Device Template|1B0262 Device Template|1B0267 Device Template|1B026B Device Template|1B0271 Device Template|1B0441 Device Template|1B0441 Device Template|1B044F Device Template|1B0451 Device Template|1B0451 Device Template|1B0B96 Device Template|1B0BA8 Device Template|1B0BAC Device Template|2D1486 Device Template|7002-7003|7851L-7851R Device Template|830A Device Template|830F Device Template|89F520 Copy Device Template|89F520 Device Template|89F522 Device Template|89F541 Device Template|89F541 Device Template|89F545 Device Template|8CD8FD Device Template|8D34BB Device Template|8D34C0 Device Template|8EAE Device Template|8FE2 Device Template|9045AA Device Template|9045B1 Device Template|9045B1 Device Template|9045B1 Device Template|910B Device Template|9115 Device Template|928E30 Device Template|92A0EA Device Template|92A0FC Device Template|92A143 Device Template|937967 Device Template|937972 Device Template|937978 Device Template|93797F Device Template|947A5E Device Template|947A7E Device Template|947B13 Device Template|947B42 Device Template|947B87 Device Template|947BA8 Device Template|947BC0 Device Template|947CCD Device Template|947D36 Device Template|947D59 Device Template|A146 Device Template|A4F5L-A4F5R Device Template|AF21 Device Template|A Generic Device Template|Angie MG|Angies MG|Automation Gated Lot (Hartford) Meter Group|Automation Meter Group|Automation Meter Group 4|Automation Municipality Template|Automation OpenLot Meter Group|Automation Test Meter Group|Automation Test Municipality Template|B1FDD2 Device Template|B560L-B560R Device Template|B675L-B675R Device Template|B691 Device Template|Back Lot Gated (Hartford) Meter Group|Back Lot Gated (Tufts) Meter Group|Back Lot Gated (VIA) Meter Group|Back Lot Open Meter Group|C4BD Device Template|Cedar Rapids M12-M13 159D9F Device Template|Cedar Rapids Settings Master - Meter Group|Cedar Rapids Settings Master - Muni|Chavez|CH Dual 07312015|Chicago RSS Messages|CHRIS CH Dual 02082017|CHRIS DIFF D725L-D725R|CHRIS MG Template 1 02082017|CHRIS mpsct Template 02082017|CHRIS Original D725L-D725R|CH Sentry v1.16 Update|CH Unlock|CH Update|Coin Test Meter Group|Copy of 0FB363 Device Template|Copy of 0FB363 Device Template|Copy of 0FB363 Device Template|Copy of 0FB363 Device Template|Copy of 0FB675 Device Template|Copy of Provisioning Template|Customer Service Bridgeport Device Temp|Customer Service Cedarhurst Device Temp|Customer Service Default Meter Group|Customer Service Palisades Park Device Temp|Default Meter Group Template|Delete Meter Group|Device Template A4F7|Dev Sentry Meter Group|Dual Meter Test Meter Group|EB2A12 Device Template|EPAY Kiosk|EPAY Meter|Excelsior Demo 601-602|Excelsior Municipality Template|FEE63B Device Template|Forecast And RSS Idle Messages|Garage A Meter Group|GP Group1 Meter Group|Grand Slam Test Meter Group|Happyville Solar Test Meter Group|happyville Template|Hartford Idle Messages|hartford Template|Health Dev Meter Group|Health MF Meter Group|Health MF Municipality Template|Health QA Door Test Meter Group|Health QA Meter Group|Health QA Municipality Template|HF Checklist Coin Only Test|HF Checklist E911 Test|HF Checklist VIO|HF Update|Houston EV Meter Group|Intertek Test Meter Group|ISS Municipality Template|iss Template|Jake Test Soft VIO|Jim Office Meter Group|Like Bell Health Meter Group|Like Bell Municipality Template|Like Chavez Lot Meter Group|Like Chavez Municipality Template|Like Hamtramck Meter Group|Like Hamtramck Municipality Template|Like Israel 8001-8002 PayStation Template|Like Israel Meter Group|Like Israel Municipality Template|Like Lowell Meter Group|Like Lowell Municipality Template|Like Mill City Meter Group|Like Mill City Municipality Template|Like Premium Meter Group|Like Premium Meter Group Gated Lot|Like Tufts Municipality Template|Like Ukraine Meter Group|LLV Device Template|LLV Municipality Template|LLV Test Meter Group|Lot Auto One Meter Group|Lot D Meter Group|Lot One Meter Group|Lot Two Meter Group|Manufacturing Municipality Template|mpsct Template|MPS Dev Meter Group|MPS Lab Meter Group|MPS Lot East Meter Group|MPS Lot West Meter Group|MPS Meter Group|mps Template|MPS Test Meter Group|Nahariya, Israel Settings Master - Muni|Newhaven Muni Template|NJ AE Meter Group|NJ DE Meter Group|NJ Municipality Template|Old Provisioning Template|P111 Entry Device Template|P111 Exit Device Template|P999 Entry Device Template|P999 Exit Device Template|powderhorn Template|PP Dual 07272015|PP Idle Messages|PP New RSS Messages|PP Set Unlock|PP Single 8308|PP Update|Provision 2018-2019:2564|Provision 2019-2020:2543|Provision 2231-2232:2665|Provision 25:2514|Provision 520-521:2504|Provision 5510-5511:2535|Provision 5560-5561:2536|Provision 5570-5571:2541|Provision 5580-5581:2538|Provision 5591-5592:2544|Provision 7000-7001:2539|Provision 7002-7003:2698|Provision 7:2508|Provision 7254:2639|Provision 7254-7255:2625|Provision 7254-7255:2626|Provision 7254-7255:2627|Provision 7254-7255:2632|Provision 7254-7255:2633|Provision 7254-7255:2634|Provision 7254-7255:2635|Provision 7254-7255:2636|Provision 7254-7255:2637|Provision 7254-7255:2638|Provision 7254-7255:2647|Provision 7255:2640|Provision 7255:2641|Provision 7601-7602:2667|Provision 7605-7606:2759|Provision 7607-7608:2760|Provision 7610-7611:2721|Provision 7612-7613:2720|Provision 7612-7613:2742|Provision 7616:2779|Provision 7621-7622:2770|Provision 7625-7626:2787|Provision 7631-7632:2788|Provision 7775-7776:2670|Provision 7781-7782:2685|Provision 8001-8002:2662|Provision 8336-8337:2694|Provision 8338:2764|Provision 836AL-836AR:2487|Provision 8370-8371:2542|Provision 8650:2565|Provision 8871-8872:2768|Provision 8873-8874:2818|Provision 8883-8884:2512|Provision 8883-8884:2766|Provision 8888-8889:2669|Provision 9000-9001:2534|Provision 9002-9003:2628|Provision 9010-9011:2540|Provision 9090L-9090R:2486|Provision 9092:2523|Provision 9094-9095:2524|Provision 9096-9097:2527|Provision 9-10:2668|Provision 910-911:2516|Provision 9113-9114:2525|Provision 9201-9202:2545|Provision 9203-9204:2546|Provision 9205-9206:2547|Provision 9207-9208:2548|Provision 9209-9210:2549|Provision 9211-9212:2550|Provision 9213-9214:2551|Provision 9215-9216:2552|Provision 9217-9218:2553|Provision 9217-9218:2767|Provision 9219-9220:2554|Provision 9221-9222:2555|Provision 9223-9224:2556|Provision 9225-9226:2557|Provision 9225-9226:2659|Provision 9227-9228:2558|Provision 9301-9302:2678|Provision 9305-9306:2679|Provision 950-951:2520|Provision 99991:2560|Provision 99992:2561|Provision 99993:2562|Provision Aentry1:2752|Provision Aentry1-valet:2790|Provision Aentry2:2803|Provision Aentry3:2804|Provision Aexit1:2805|Provision Angies 1-2:2829|Provision Apay1:2736|Provision ATentry:2498|Provision ATexit:2499|Provision AutoOpenEntry:2699|Provision AutoOpenExit:2700|Provision AutoOpenPay1:2733|Provision B001:2687|Provision Backlot-Cam-NX:2775|Provision Backlot Pole Cam:2745|Provision brenden2:2510|Provision brenden:2509|Provision brenden92-brenden93:2566|Provision brenden94-brenden95:2567|Provision brenden96-brenden97:2568|Provision Cat:2481|Provision Dave Test:2693|Provision dcom1-dcom2:2664|Provision Entry:2478|Provision EntryLotOne:2819|Provision Exit:2513|Provision Exit Cam:2802|Provision F2Labs1:2726|Provision F2Labs2:2728|Provision GS01:2715|Provision GS02|Provision GS04:2731|Provision GS04:2732|Provision GS05:2737|Provision GS05:2738|Provision GS05:2753|Provision GS06:2755|Provision GS07:2756|Provision GS08:2781|Provision Health Kiosk 2:2674|Provision Health Kiosk 3:2676|Provision Health Kiosk 4:2690|Provision Health Kiosk Blue:2681|Provision Health Kiosk TH:2683|Provision HK Blue:2702|Provisioning Template|Provision ITK01:2761|Provision ITK02:2763|Provision ITK03:2769|Provision ITK04:2773|Provision ITK05:2774|Provision Jake-Test:2479|Provision Jimbo-Jones:2472|Provision KS001:2704|Provision LBS1-LBS2:2655|Provision LLV1-LLV2:2657|Provision Lot Auto One Entry:2792|Provision Lot Auto One Exit:2793|Provision Lot Auto One Pay:2794|Provision LotDentry1:2751|Provision Lot-D-Pay-1:2735|Provision Mark Lot A Pay:2839|Provision Mark LotD Pay:2776|Provision MarkPay2:2729|Provision MarkPay:2716|Provision MarkPay3:2730|Provision Mark-sim:2521|Provision Mirro 1:2765|Provision NPLP1-NPLP2:2518|Provision NPLP3-NPLP4:2666|Provision NPM1:2505|Provision NPM3:2503|Provision NPM6:2537|Provision NPM7:2629|Provision NPM7:2630|Provision NPM7-NPM8:2563|Provision NPM7-NPM8:2569|Provision NPM7-NPM8:2570|Provision NPM7-NPM8:2571|Provision NPM7-NPM8:2572|Provision NPM7-NPM8:2574|Provision NPM7-NPM8:2575|Provision NPM7-NPM8:2576|Provision NPM7-NPM8:2577|Provision NPM7-NPM8:2578|Provision NPM7-NPM8:2579|Provision NPM7-NPM8:2643|Provision NPM7-NPM8:2645|Provision NPM7-NPM8:2646|Provision NPM7-NPM8:2648|Provision NPM7-NPM8:2650|Provision NPM7-NPM8:2653|Provision ODI045:2782|Provision OneEntry:2500|Provision OneExit:2501|Provision OneExit:2816|Provision OnePay2:2723|Provision OnePay:2502|Provision OnePay3:2727|Provision P111 Entry 2:2705|Provision P111 Entry2:2830|Provision P111 Entry:2688|Provision P111 Entry3:2831|Provision P111 Exit:2689|Provision P151-Entry SentryLite:2709|Provision P151-Entry SentryLite:2710|Provision P151-Exit SentryLite:2711|Provision P151-Exit SentryLite:2712|Provision P250 Entry:2771|Provision P250-Entry SentryLite:2713|Provision P250 Exit:2772|Provision P250-Exit SentryLite:2714|Provision P999 Entry:2660|Provision P999 Exit:2661|Provision PA11-PA12:2573|Provision Park Place Entry:2799|Provision Park Place Exit:2800|Provision PCS40:2519|Provision Pentry:2651|Provision Pexit:2652|Provision Pipe Bomb:2741|Provision PR007:2786|Provision Pwest2 Entry:2832|Provision PWest2 Exit:2833|Provision PWest3 Entry:2834|Provision PWest3 Exit:2835|Provision PWest5 Entry:2836|Provision PWest5 Exit:2837|Provision QA Cam 1:2708|Provision RO Test 1:2783|Provision RO Test 2:2784|Provision SAL1-SAL2:2559|Provision SH68:2722|Provision SH69:2717|Provision SH72:2684|Provision SH79:2673|Provision SH80:2692|Provision SolarTest:2796|Provision SS098:2780|Provision SS099:2778|Provision SS23:2798|Provision SSG2 501:2824|Provision SSG2 502:2826|Provision SSG2 503:2827|Provision SSG2 504:2841|Provision SSG2 506:2823|Provision SSG2 507:2825|Provision SSG2 508:2822|Provision SSG2 509:2815|Provision SSG2 509:2828|Provision SSv2 001:2808|Provision Street Cam:2743|Provision Street-Entry:2718|Provision Street Entry Valet:2789|Provision Street-Exit:2840|Provision T1-T2:2511|Provision Test Nano:2758|Provision Todd-Test:2480|Provision Tommy-herr:2488|Provision Tommy-herr:2489|Provision Tommy-herr:2490|Provision XNPM1:2703|Provision XPRV1-XPRV2:2695|Provision XPRV3-XPRV4:2696|Provision Z1exit:2675|Provision Zone 1 Exit:2777|rahrahrah Template|Rapid City A2-A3 147878 Device Template|Rapid City Settings Master - Meter Group|Rapid City Settings Master - Muni|REMOVE_FROM_DEVICES|RichL-RichR m2m settings (TEMP)|Ricky Automation Municipality Template|Ricky's Meter Group|Safety Test Meter Group|salescentral Template|saleseast Template|Salient Municipality Template|Salient Muni Template|Sentry Sim Meter Group|Set IR Flash|Staging Test Meter Group|Template from 7846L-7846R|Template from 801-802|Template from 8308L-8308R|Template from B691|Template from B691|Template from HB001-HB002|Template from HB003-HB004|Temporary for setting|Test Connecticut Flint Meter Group|Test Connecticut Municipality Template|Test Connecticut Royal Oak Meter Group|Test Health Meter Group|Test MG Template 1|Test MG Template 2|Tommy-herr|Toronto RSS Messages|Tristan's Meter Group|Uma Meter Group|VIA PCS Sentry v1.04+|VIA PCS Sentry v1.05.1|VIA RSS Messages|VIA Test Meter Group|VIA Test Municipality Template|viatest Template|VIA Update|Zone 1|Zone 1 Meter Group");
		//Validate To This Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Diff Tool For Templates", "To This", 1, "Item Order", "|008703 Device Template|0B96L-0B96R Device Template|0E8308 Device Template|0E830A Device Template|0E8329 Device Template|0E906E Device Template|0E9090 Device Template|0E956E Device Template|0EA4F2 Device Template|0EA4F5 Device Template|0EA4F7 Device Template|0FAF21 Device Template|0FB363 Device Template|0FB473 Device Template|0FB476 Device Template|0FB478 Device Template|0FB4AF Device Template|0FB4B1 Device Template|0FB560 Device Template|0FB670 Device Template|0FB671 Device Template|0FB675 Device Template|0FB67B Device Template|0FB691 Device Template|0FB6BA Device Template|0FB6BB Device Template|0FB9FF Device Template|0FC340 Device Template|0FC343 Device Template|0FD725 Device Template|10C4BD Device Template|10CD05 Device Template|111265 Device Template|127D81 Device Template|127D82 Device Template|127DA1 Device Template|127F11 Device Template|128022 Device Template|128039 Device Template|12832E Device Template|128336 Device Template|128338 Device Template|128366 Device Template|12836A Device Template|128FEF Device Template|129110 Device Template|1-2 m2m settings (TEMP)|14757C Device Template|1476B4 Device Template|14782B Device Template|14782F Device Template|147846 Device Template|147846 Device Template|147851 Device Template|147865 Device Template|147878 Device Template|1479A6 Device Template|14B5CE Device Template|14B5E3 Device Template|14B60F Device Template|1B025B Device Template|1B0262 Device Template|1B0267 Device Template|1B026B Device Template|1B0271 Device Template|1B0441 Device Template|1B0441 Device Template|1B044F Device Template|1B0451 Device Template|1B0451 Device Template|1B0B96 Device Template|1B0BA8 Device Template|1B0BAC Device Template|2D1486 Device Template|7002-7003|7851L-7851R Device Template|830A Device Template|830F Device Template|89F520 Copy Device Template|89F520 Device Template|89F522 Device Template|89F541 Device Template|89F541 Device Template|89F545 Device Template|8CD8FD Device Template|8D34BB Device Template|8D34C0 Device Template|8EAE Device Template|8FE2 Device Template|9045AA Device Template|9045B1 Device Template|9045B1 Device Template|9045B1 Device Template|910B Device Template|9115 Device Template|928E30 Device Template|92A0EA Device Template|92A0FC Device Template|92A143 Device Template|937967 Device Template|937972 Device Template|937978 Device Template|93797F Device Template|947A5E Device Template|947A7E Device Template|947B13 Device Template|947B42 Device Template|947B87 Device Template|947BA8 Device Template|947BC0 Device Template|947CCD Device Template|947D36 Device Template|947D59 Device Template|A146 Device Template|A4F5L-A4F5R Device Template|AF21 Device Template|A Generic Device Template|Angie MG|Angies MG|Automation Gated Lot (Hartford) Meter Group|Automation Meter Group|Automation Meter Group 4|Automation Municipality Template|Automation OpenLot Meter Group|Automation Test Meter Group|Automation Test Municipality Template|B1FDD2 Device Template|B560L-B560R Device Template|B675L-B675R Device Template|B691 Device Template|Back Lot Gated (Hartford) Meter Group|Back Lot Gated (Tufts) Meter Group|Back Lot Gated (VIA) Meter Group|Back Lot Open Meter Group|C4BD Device Template|Cedar Rapids M12-M13 159D9F Device Template|Cedar Rapids Settings Master - Meter Group|Cedar Rapids Settings Master - Muni|Chavez|CH Dual 07312015|Chicago RSS Messages|CHRIS CH Dual 02082017|CHRIS DIFF D725L-D725R|CHRIS MG Template 1 02082017|CHRIS mpsct Template 02082017|CHRIS Original D725L-D725R|CH Sentry v1.16 Update|CH Unlock|CH Update|Coin Test Meter Group|Copy of 0FB363 Device Template|Copy of 0FB363 Device Template|Copy of 0FB363 Device Template|Copy of 0FB363 Device Template|Copy of 0FB675 Device Template|Copy of Provisioning Template|Customer Service Bridgeport Device Temp|Customer Service Cedarhurst Device Temp|Customer Service Default Meter Group|Customer Service Palisades Park Device Temp|Default Meter Group Template|Delete Meter Group|Device Template A4F7|Dev Sentry Meter Group|Dual Meter Test Meter Group|EB2A12 Device Template|EPAY Kiosk|EPAY Meter|Excelsior Demo 601-602|Excelsior Municipality Template|FEE63B Device Template|Forecast And RSS Idle Messages|Garage A Meter Group|GP Group1 Meter Group|Grand Slam Test Meter Group|Happyville Solar Test Meter Group|happyville Template|Hartford Idle Messages|hartford Template|Health Dev Meter Group|Health MF Meter Group|Health MF Municipality Template|Health QA Door Test Meter Group|Health QA Meter Group|Health QA Municipality Template|HF Checklist Coin Only Test|HF Checklist E911 Test|HF Checklist VIO|HF Update|Houston EV Meter Group|Intertek Test Meter Group|ISS Municipality Template|iss Template|Jake Test Soft VIO|Jim Office Meter Group|Like Bell Health Meter Group|Like Bell Municipality Template|Like Chavez Lot Meter Group|Like Chavez Municipality Template|Like Hamtramck Meter Group|Like Hamtramck Municipality Template|Like Israel 8001-8002 PayStation Template|Like Israel Meter Group|Like Israel Municipality Template|Like Lowell Meter Group|Like Lowell Municipality Template|Like Mill City Meter Group|Like Mill City Municipality Template|Like Premium Meter Group|Like Premium Meter Group Gated Lot|Like Tufts Municipality Template|Like Ukraine Meter Group|LLV Device Template|LLV Municipality Template|LLV Test Meter Group|Lot Auto One Meter Group|Lot D Meter Group|Lot One Meter Group|Lot Two Meter Group|Manufacturing Municipality Template|mpsct Template|MPS Dev Meter Group|MPS Lab Meter Group|MPS Lot East Meter Group|MPS Lot West Meter Group|MPS Meter Group|mps Template|MPS Test Meter Group|Nahariya, Israel Settings Master - Muni|Newhaven Muni Template|NJ AE Meter Group|NJ DE Meter Group|NJ Municipality Template|Old Provisioning Template|P111 Entry Device Template|P111 Exit Device Template|P999 Entry Device Template|P999 Exit Device Template|powderhorn Template|PP Dual 07272015|PP Idle Messages|PP New RSS Messages|PP Set Unlock|PP Single 8308|PP Update|Provision 2018-2019:2564|Provision 2019-2020:2543|Provision 2231-2232:2665|Provision 25:2514|Provision 520-521:2504|Provision 5510-5511:2535|Provision 5560-5561:2536|Provision 5570-5571:2541|Provision 5580-5581:2538|Provision 5591-5592:2544|Provision 7000-7001:2539|Provision 7002-7003:2698|Provision 7:2508|Provision 7254:2639|Provision 7254-7255:2625|Provision 7254-7255:2626|Provision 7254-7255:2627|Provision 7254-7255:2632|Provision 7254-7255:2633|Provision 7254-7255:2634|Provision 7254-7255:2635|Provision 7254-7255:2636|Provision 7254-7255:2637|Provision 7254-7255:2638|Provision 7254-7255:2647|Provision 7255:2640|Provision 7255:2641|Provision 7601-7602:2667|Provision 7605-7606:2759|Provision 7607-7608:2760|Provision 7610-7611:2721|Provision 7612-7613:2720|Provision 7612-7613:2742|Provision 7616:2779|Provision 7621-7622:2770|Provision 7625-7626:2787|Provision 7631-7632:2788|Provision 7775-7776:2670|Provision 7781-7782:2685|Provision 8001-8002:2662|Provision 8336-8337:2694|Provision 8338:2764|Provision 836AL-836AR:2487|Provision 8370-8371:2542|Provision 8650:2565|Provision 8871-8872:2768|Provision 8873-8874:2818|Provision 8883-8884:2512|Provision 8883-8884:2766|Provision 8888-8889:2669|Provision 9000-9001:2534|Provision 9002-9003:2628|Provision 9010-9011:2540|Provision 9090L-9090R:2486|Provision 9092:2523|Provision 9094-9095:2524|Provision 9096-9097:2527|Provision 9-10:2668|Provision 910-911:2516|Provision 9113-9114:2525|Provision 9201-9202:2545|Provision 9203-9204:2546|Provision 9205-9206:2547|Provision 9207-9208:2548|Provision 9209-9210:2549|Provision 9211-9212:2550|Provision 9213-9214:2551|Provision 9215-9216:2552|Provision 9217-9218:2553|Provision 9217-9218:2767|Provision 9219-9220:2554|Provision 9221-9222:2555|Provision 9223-9224:2556|Provision 9225-9226:2557|Provision 9225-9226:2659|Provision 9227-9228:2558|Provision 9301-9302:2678|Provision 9305-9306:2679|Provision 950-951:2520|Provision 99991:2560|Provision 99992:2561|Provision 99993:2562|Provision Aentry1:2752|Provision Aentry1-valet:2790|Provision Aentry2:2803|Provision Aentry3:2804|Provision Aexit1:2805|Provision Angies 1-2:2829|Provision Apay1:2736|Provision ATentry:2498|Provision ATexit:2499|Provision AutoOpenEntry:2699|Provision AutoOpenExit:2700|Provision AutoOpenPay1:2733|Provision B001:2687|Provision Backlot-Cam-NX:2775|Provision Backlot Pole Cam:2745|Provision brenden2:2510|Provision brenden:2509|Provision brenden92-brenden93:2566|Provision brenden94-brenden95:2567|Provision brenden96-brenden97:2568|Provision Cat:2481|Provision Dave Test:2693|Provision dcom1-dcom2:2664|Provision Entry:2478|Provision EntryLotOne:2819|Provision Exit:2513|Provision Exit Cam:2802|Provision F2Labs1:2726|Provision F2Labs2:2728|Provision GS01:2715|Provision GS02|Provision GS04:2731|Provision GS04:2732|Provision GS05:2737|Provision GS05:2738|Provision GS05:2753|Provision GS06:2755|Provision GS07:2756|Provision GS08:2781|Provision Health Kiosk 2:2674|Provision Health Kiosk 3:2676|Provision Health Kiosk 4:2690|Provision Health Kiosk Blue:2681|Provision Health Kiosk TH:2683|Provision HK Blue:2702|Provisioning Template|Provision ITK01:2761|Provision ITK02:2763|Provision ITK03:2769|Provision ITK04:2773|Provision ITK05:2774|Provision Jake-Test:2479|Provision Jimbo-Jones:2472|Provision KS001:2704|Provision LBS1-LBS2:2655|Provision LLV1-LLV2:2657|Provision Lot Auto One Entry:2792|Provision Lot Auto One Exit:2793|Provision Lot Auto One Pay:2794|Provision LotDentry1:2751|Provision Lot-D-Pay-1:2735|Provision Mark Lot A Pay:2839|Provision Mark LotD Pay:2776|Provision MarkPay2:2729|Provision MarkPay:2716|Provision MarkPay3:2730|Provision Mark-sim:2521|Provision Mirro 1:2765|Provision NPLP1-NPLP2:2518|Provision NPLP3-NPLP4:2666|Provision NPM1:2505|Provision NPM3:2503|Provision NPM6:2537|Provision NPM7:2629|Provision NPM7:2630|Provision NPM7-NPM8:2563|Provision NPM7-NPM8:2569|Provision NPM7-NPM8:2570|Provision NPM7-NPM8:2571|Provision NPM7-NPM8:2572|Provision NPM7-NPM8:2574|Provision NPM7-NPM8:2575|Provision NPM7-NPM8:2576|Provision NPM7-NPM8:2577|Provision NPM7-NPM8:2578|Provision NPM7-NPM8:2579|Provision NPM7-NPM8:2643|Provision NPM7-NPM8:2645|Provision NPM7-NPM8:2646|Provision NPM7-NPM8:2648|Provision NPM7-NPM8:2650|Provision NPM7-NPM8:2653|Provision ODI045:2782|Provision OneEntry:2500|Provision OneExit:2501|Provision OneExit:2816|Provision OnePay2:2723|Provision OnePay:2502|Provision OnePay3:2727|Provision P111 Entry 2:2705|Provision P111 Entry2:2830|Provision P111 Entry:2688|Provision P111 Entry3:2831|Provision P111 Exit:2689|Provision P151-Entry SentryLite:2709|Provision P151-Entry SentryLite:2710|Provision P151-Exit SentryLite:2711|Provision P151-Exit SentryLite:2712|Provision P250 Entry:2771|Provision P250-Entry SentryLite:2713|Provision P250 Exit:2772|Provision P250-Exit SentryLite:2714|Provision P999 Entry:2660|Provision P999 Exit:2661|Provision PA11-PA12:2573|Provision Park Place Entry:2799|Provision Park Place Exit:2800|Provision PCS40:2519|Provision Pentry:2651|Provision Pexit:2652|Provision Pipe Bomb:2741|Provision PR007:2786|Provision Pwest2 Entry:2832|Provision PWest2 Exit:2833|Provision PWest3 Entry:2834|Provision PWest3 Exit:2835|Provision PWest5 Entry:2836|Provision PWest5 Exit:2837|Provision QA Cam 1:2708|Provision RO Test 1:2783|Provision RO Test 2:2784|Provision SAL1-SAL2:2559|Provision SH68:2722|Provision SH69:2717|Provision SH72:2684|Provision SH79:2673|Provision SH80:2692|Provision SolarTest:2796|Provision SS098:2780|Provision SS099:2778|Provision SS23:2798|Provision SSG2 501:2824|Provision SSG2 502:2826|Provision SSG2 503:2827|Provision SSG2 504:2841|Provision SSG2 506:2823|Provision SSG2 507:2825|Provision SSG2 508:2822|Provision SSG2 509:2815|Provision SSG2 509:2828|Provision SSv2 001:2808|Provision Street Cam:2743|Provision Street-Entry:2718|Provision Street Entry Valet:2789|Provision Street-Exit:2840|Provision T1-T2:2511|Provision Test Nano:2758|Provision Todd-Test:2480|Provision Tommy-herr:2488|Provision Tommy-herr:2489|Provision Tommy-herr:2490|Provision XNPM1:2703|Provision XPRV1-XPRV2:2695|Provision XPRV3-XPRV4:2696|Provision Z1exit:2675|Provision Zone 1 Exit:2777|rahrahrah Template|Rapid City A2-A3 147878 Device Template|Rapid City Settings Master - Meter Group|Rapid City Settings Master - Muni|REMOVE_FROM_DEVICES|RichL-RichR m2m settings (TEMP)|Ricky Automation Municipality Template|Ricky's Meter Group|Safety Test Meter Group|salescentral Template|saleseast Template|Salient Municipality Template|Salient Muni Template|Sentry Sim Meter Group|Set IR Flash|Staging Test Meter Group|Template from 7846L-7846R|Template from 801-802|Template from 8308L-8308R|Template from B691|Template from B691|Template from HB001-HB002|Template from HB003-HB004|Temporary for setting|Test Connecticut Flint Meter Group|Test Connecticut Municipality Template|Test Connecticut Royal Oak Meter Group|Test Health Meter Group|Test MG Template 1|Test MG Template 2|Tommy-herr|Toronto RSS Messages|Tristan's Meter Group|Uma Meter Group|VIA PCS Sentry v1.04+|VIA PCS Sentry v1.05.1|VIA RSS Messages|VIA Test Meter Group|VIA Test Municipality Template|viatest Template|VIA Update|Zone 1|Zone 1 Meter Group");
		//Close Browser
		driver.quit();
    }

	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1037_ParkingLotSessions(@Optional String strRole)
    {
		System.out.println(strRole);
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Parking Lot Sessions", 1);
		objDictionary.put("strRemainParkedShortSession","False");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strFirstName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "");
		String strLastName = strRole.replace(" ", "");
		//MPS Logo
		clsCommonWeb.VerificationPointImage(objDictionary, driver, "Parking Lot Sessions", "Municipality Logo","Exists");
		//Validate link Violations
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Parking Lot Sessions", "Violations",1,"Exists");
		//Validate link Parking Lots Sessions
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Parking Lot Sessions", "Parking Lot Sessions",1,"Exists");
		//Validate Reports Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Parking Lot Sessions", "Reports", 1, "Daily Ticket Total|Meter Cash Outs|Notifications|Payments|Weekly Ticket Total|On Hold|Violation Payment Report|Tickets With Officer");
		//Validate Health Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Parking Lot Sessions", "Health", 1, "Sessions|Trends");
		//Validate Municipality Name
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Parking Lot Sessions", "AutomationMunicipality",1,"Exists");
		//Validate User Link
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Parking Lot Sessions", strFirstName+" "+strLastName,1,"Exists");
		//Plate Number
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Parking Lot Sessions", "Plate Number", 1, "Exists", "");
		//Province/State
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Parking Lot Sessions", "Province/State", 1, "Exists", "");
		//From Date Time Entered
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Parking Lot Sessions", "From Date Time Entered", 1, "Exists", "");
		//To Date Time Entered
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Parking Lot Sessions", "To Date Time Entered", 1, "Exists", "");
		//From Date Time Exited
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Parking Lot Sessions", "From Date Time Exited", 1, "Exists", "");
		//To Date Time Exited
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Parking Lot Sessions", "To Date Time Exited", 1, "Exists", "");
		//Validate Parking Lots Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Parking Lot Sessions", "Parking Lot", 1, "Item Order", "ENTRY AND/OR EXIT PRESENT|ENTRY AND EXIT PRESENT|ENTRY ONLY PRESENT (ACTIVE)|EXIT ONLY PRESENT (ASSUMED ENTRY)");
		//Validate Sort By Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Parking Lot Sessions", "Sort By", 1, "Item Order", "DESCENDING|ASCENDING");
		//Validate Include Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Parking Lot Sessions", "Include", 1, "Item Order", "|AUTOMATIONOPENLOT|DAKOTA");
		//Validate Find Sessions
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Parking Lot Sessions", "Find Sessions",1,"Exists");
		//Validate Link Bad Entrance Events
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Parking Lot Sessions", "Bad Entrance Events",1,"Exists");
		//Validate Link Vehicles In Lot
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Parking Lot Sessions", "Vehicles In Lot",1,"Exists");
		//Validate Link Daily Occupancy Rate
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Parking Lot Sessions", "Daily Occupancy Rate",1,"Exists");
		//Validate Link Permits Purchased
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Parking Lot Sessions", "Permits Purchased",1,"Exists");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Parking Lot Sessions", "Page Header", 1, "Value", "Parking Lot Sessions");
		//Validate Show Total
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Parking Lot Sessions", "Show Total",1,"Exists");
		//Validate Sentry Setting Sets
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Lot Sessions", "Parking Lot Sessions", 1, "0", "0", "Exists", "");
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1038_PreviewDailyTicketTotals(@Optional String strRole)
    {
		System.out.println(strRole);
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Reports", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Daily Ticket Total", 1);
		objDictionary.put("strRemainParkedShortSession","False");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strFirstName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "");
		String strLastName = strRole.replace(" ", "");
		//MPS Logo
		clsCommonWeb.VerificationPointImage(objDictionary, driver, "Preview Daily Ticket Totals", "Municipality Logo","Exists");
		//Validate link Violations
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Preview Daily Ticket Totals", "Violations",1,"Exists");
		//Validate link Parking Lots Sessions
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Preview Daily Ticket Totals", "Parking Lot Sessions",1,"Exists");
		//Validate Reports Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Preview Daily Ticket Totals", "Reports", 1, "Daily Ticket Total|Meter Cash Outs|Notifications|Payments|Weekly Ticket Total|On Hold|Violation Payment Report|Tickets With Officer");
		//Validate Health Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Preview Daily Ticket Totals", "Health", 1, "Sessions|Trends");
		//Validate Municipality Name
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Preview Daily Ticket Totals", "AutomationMunicipality",1,"Exists");
		//Validate User Link
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Preview Daily Ticket Totals", strFirstName+" "+strLastName,1,"Exists");
		//From Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Preview Daily Ticket Totals", "From Date", 1, "Exists", "");
		//To Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Preview Daily Ticket Totals", "To Date", 1, "Exists", "");
		//Validate Device Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Preview Daily Ticket Totals", "Device", 1, "Item Order", "ALL|9096-9097|9094-9095|9010-9011|5510-5511|5560-5561|5570-5571|5580-5581|9000-9001|9092|AUTOOPENENTRY|AUTOOPENEXIT|AUTOOPENPAY1|DAKOTA KIOSK|9213-9214|9215-9216|9219-9220|9221-9222|9227-9228|92T (D)|92U (D)|92V (D)|92W (D)|92X (D)|92Y (D)|92Z|NO METER|AVMETER1|AVMETER2|AVMETER4|AVMETER5|AVMETER6|IVMETER1|VMETER4|9203-9204|9205-9206");
		//Validate Generate Report
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Preview Daily Ticket Totals", "Generate Report",1,"Exists");
		//Validate Link (csv)
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Preview Daily Ticket Totals", "(csv)",1,"Exists");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Preview Daily Ticket Totals", "Page Header", 1, "Contains", "Preview Daily Ticket Totals");
		//Validate Meter Groups
		clsCommonWeb.VerificationPointUnorderList(objDictionary, driver, "Preview Daily Ticket Totals", "Meter Groups", 1, "AMG2|AMG3|AMG4|AutomationMeterGroup|AutomationOpenLot|Dakota|MPS|No Meter|VirtualAutomationMeterGroup|WoonerfMeterGroup");
		//Validate Jump to a Device Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Preview Daily Ticket Totals", "Jump to a Device", 1, "Item Order", "|9096-9097");
		//Validate Link Back to Top
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Preview Daily Ticket Totals", "Back to Top",1,"Exists");
		//Validate Sentry Setting Sets
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Preview Daily Ticket Totals", "Ticket Totals Table", 1, "0", "0", "Exists", "");
	}
	@Parameters({"strRole"})@Test(priority=8004,groups={"UserTest"})
	public void UserRoles1039_MeterCashOuts(@Optional String strRole)
    {
		System.out.println(strRole);
		objDictionary.put("strMobileDeviceType", "Meter");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		Meter clsMeter = new Meter();
		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		if(strRole.equals("admin"))
		{clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);}
		else
		{clsCommonWeb.SENTRYLINK_LoginInOrCreate(objDictionary, driver, strRole);}
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Reports", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Meter Cash Outs", 1);
		objDictionary.put("strRemainParkedShortSession","False");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strFirstName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "");
		String strLastName = strRole.replace(" ", "");
		//MPS Logo
		clsCommonWeb.VerificationPointImage(objDictionary, driver, "Meter Cash Outs", "Municipality Logo","Exists");
		//Validate link Violations
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Meter Cash Outs", "Violations",1,"Exists");
		//Validate link Parking Lots Sessions
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Meter Cash Outs", "Parking Lot Sessions",1,"Exists");
		//Validate Reports Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Meter Cash Outs", "Reports", 1, "Daily Ticket Total|Meter Cash Outs|Notifications|Payments|Weekly Ticket Total|On Hold|Violation Payment Report|Tickets With Officer");
		//Validate Health Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, "Meter Cash Outs", "Health", 1, "Sessions|Trends");
		//Validate Municipality Name
		clsCommonWeb.VerificationPointLink(objDictionary, driver, "Meter Cash Outs", "AutomationMunicipality",1,"Exists");
		//Validate User Link
		clsCommonWeb.VerificationPointLink(objDictionary,driver, "Meter Cash Outs", strFirstName+" "+strLastName,1,"Exists");
		//From Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Meter Cash Outs", "From Date", 1, "Exists", "");
		//To Date
		clsCommonWeb.VerificationPointTextField(objDictionary,driver, "Meter Cash Outs", "To Date", 1, "Exists", "");
		//Validate Device Item Order
		clsCommonWeb.VerificationPointListbox(objDictionary, driver, "Meter Cash Outs", "Meter", 1, "Item Order", "All|9096-9097|9094-9095|9010-9011|5510-5511|5560-5561|5570-5571|5580-5581|9000-9001|9092|9213-9214|9215-9216|9219-9220|9221-9222|9227-9228|92t (D)|92u (D)|92v (D)|92w (D)|92x (D)|92y (D)|92z|9203-9204|9205-9206");
		//Validate Generate Report
		clsCommonWeb.VerificationPointButton(objDictionary, driver, "Meter Cash Outs", "Find Cash Outs",1,"Exists");
		//Validate Header
		clsCommonWeb.VerificationPointText(objDictionary,driver, "Meter Cash Outs", "Page Header", 1, "Contains", "Meter Cash Outs");
		//Validate Sentry Setting Sets
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Meter Cash Outs", "Cash Out Results", 1, "0", "0", "Exists", "");
		System.out.println("MIH");
	}


	//Common
	public void UserRoles_Admin_Commom(Map<String, String> objDictionary, WebDriver driver, String strPageName)
    {
		CommonWeb clsCommonWeb = new CommonWeb();
		//MPS Logo
		clsCommonWeb.VerificationPointImage(objDictionary, driver, strPageName, "Municipality Logo","Exists");
		//Municipalities Link
		clsCommonWeb.VerificationPointLink(objDictionary, driver, strPageName, "Municipalities",1,"Exists");
		//Validate User Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, strPageName, "Users", 1, "Audit Logs|Carts|Concierge Vehicles|Users");
		//Validate Alerts Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, strPageName, "Alerts", 1, "Alert Activity Feed|Alert Families|Alert Forwarding Histories|Alert Forwarding Summary|Alert Recipients|Sentry Alert Types");
		//Validate Maintenance Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, strPageName, "Maintenance", 1, "Amber Alerts|Attendant Calls|Attendant Overrides|Device Summary|Exemption Reasons|Holiday Voiding|Important Workers|Mobile Registrations|Notification Templates|Provisioning Service|Purge Manager|Push Notification Files|RPC's|Sentry Errors|Scale Rules|Scale Timeslots|Serial Queues|System Health|Ticket Service Data|Violation Rejection Reasons|Void Parking Sessions|Worker History");
		//Validate Meter Settings Link Dropdown Items
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, strPageName, "Meter Settings", 1, "Sentry Settings|Sentry Setting Sets|Scheduled Deployments|Templates|Template Diff Tool");
		//Validate Admin Settings Link
		clsCommonWeb.VerificationPointLink(objDictionary, driver, strPageName, "Admin Settings",1,"Exists");
		//Validate Meter Settings user admin Dropdown Items
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strAdminLink = strUniqueId+strMunicipality+" admin";
		clsCommonWeb.VerificationPointLinkDropdown(objDictionary, driver, strPageName, strAdminLink, 1, "Profile|SentryLink Admin|Log Out");
    }
}

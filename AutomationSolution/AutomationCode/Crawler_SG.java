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
public class Crawler_SG
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
			//clsMeter.METER_CopyLogTraceLocally(objDictionary, null,  strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			//clsMeter.METER_CopyMessageTraceLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			String strResetRateBlocksCounter = objDictionary.get("strResetRateBlocksCounter");if(strResetRateBlocksCounter == null) {strResetRateBlocksCounter = "0";}
		}
	}

	//************************************************************************************
  	//SG CLient Crawler Test
  	//************************************************************************************
  	@Test(priority=7000)
  	public void SG_Atlantis_SiteCrawler() {SGClientCrawler("Atlantis");}
	@Test(priority=7001)
  	public void SG_AutomationMunicipality_SiteCrawler() {SGClientCrawler("AutomationMunicipality");}
	@Test(priority=7002)
  	public void SG_automationRickyMuni_SiteCrawler() {SGClientCrawler("automationRickyMuni");}
	@Test(priority=7003)
  	public void SG_AutomationTest_SiteCrawler() {SGClientCrawler("AutomationTest");}
	@Test(priority=7004)
  	public void SG_Excelsior_MN_SiteCrawler() {SGClientCrawler("Excelsior, MN");}
	@Test(priority=7005)
  	public void SG_Happyville_Solar_SiteCrawler() {SGClientCrawler("Happyville Solar");}
	@Test(priority=7006)
  	public void SG_HighBridge_Solar_SiteCrawler() {SGClientCrawler("HighBridge");}
	@Test(priority=7007)
  	public void SG_Interstate_Sandbox_SiteCrawler() {SGClientCrawler("Interstate Sandbox");}
	@Test(priority=7008)
  	public void SG_ISS_SiteCrawler() {SGClientCrawler("ISS");}
	@Test(priority=7009)
  	public void SG_JIS_Consolidation_SiteCrawler() {SGClientCrawler("JIS Consolidation");}
	@Test(priority=7010)
  	public void SG_JIS_Consolidation_failed_SiteCrawler() {SGClientCrawler("JIS Consolidation failed");}
	@Test(priority=7011)
  	public void SG_JIS_Consolidation_July_SiteCrawler() {SGClientCrawler("JIS Consolidation July");}
	@Test(priority=7012)
  	public void SG_Kernersville_NC_SiteCrawler() {SGClientCrawler("Kernersville,NC");}
	@Test(priority=7013)
  	public void SG_Like_Augsburg_SiteCrawler() {SGClientCrawler("Like Augsburg");}
	@Test(priority=7014)
  	public void SG_Like_Bell_SiteCrawler() {SGClientCrawler("Like Bell");}
	@Test(priority=7015)
  	public void SG_Like_Chavez_SiteCrawler() {SGClientCrawler("Like Chavez");}
	@Test(priority=7016)
  	public void SG_Like_Hamtramck_SiteCrawler() {SGClientCrawler("Like Hamtramck");}
	@Test(priority=7017)
  	public void SG_Like_Interstate_SiteCrawler() {SGClientCrawler("Like Interstate");}
	@Test(priority=7017)
  	public void SG_Like_Lowell_SiteCrawler() {SGClientCrawler("Like Lowell");}
	@Test(priority=7019)
  	public void SG_Like_Mill_City_SiteCrawler() {SGClientCrawler("Like Mill City");}
	@Test(priority=7020)
  	public void SG_Like_PMC_SiteCrawler() {SGClientCrawler("Like PMC");}
	@Test(priority=7021)
  	public void SG_Like_Royal_Oak_SiteCrawler() {SGClientCrawler("Like Royal Oak");}
	@Test(priority=7022)
  	public void SG_Like_Tufts_SiteCrawler() {SGClientCrawler("Like Tufts");}
	@Test(priority=7023)
  	public void SG_likemanualinterstate_SiteCrawler() {SGClientCrawler("likemanualinterstate");}
	@Test(priority=7024)
  	public void SG_Lot_Automation_SiteCrawler() {SGClientCrawler("Lot Automation");}
	@Test(priority=7025)
  	public void SG_Manufacturing_SiteCrawler() {SGClientCrawler("Manufacturing");}
	@Test(priority=7026)
  	public void SG_Premium_Sandbox_SiteCrawler() {SGClientCrawler("Premium Sandbox");}
	@Test(priority=7027)
  	public void SG_Salient_SiteCrawler() {SGClientCrawler("Salient");}
	@Test(priority=7028)
  	public void SG_Test_ANG_SiteCrawler() {SGClientCrawler("Test ANG");}
	@Test(priority=7029)
  	public void SG_Texas_SiteCrawler() {SGClientCrawler("Texas");}
	@Test(priority=7030)
  	public void SG_TicketServiceTesting_SiteCrawler() {SGClientCrawler("TicketServiceTesting");}


  	//SG Client Crawler Test
  	public void SGClientCrawler(String strMunicipality)
    {
		String strTestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
  		objDictionary.put("strMobileDeviceType", "Meter");
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		clsMeter.Meter_AddReportVariables(objDictionary, null,  strTestCaseName);
  		Reporter.log("***************TestCase Description*****************************");
  		Reporter.log("Validate Violations Page                                        ");
  		Reporter.log("Validate Violation Page                                         ");
  		Reporter.log("Validate Parking Sessions Page                                  ");
  		Reporter.log("Validate Parking Session Page                                   ");
  		Reporter.log("Validate Parking Lot Sessions Page                              ");
  		Reporter.log("Validate Parking Lot Session Page                               ");
  		Reporter.log("Validate Payments Reports Page                                  ");
  		Reporter.log("Validate Payment Page                                           ");
  		Reporter.log("****************************************************************");
  		String strBrowser = objDictionary.get("strBrowser");String strRemotePath = objDictionary.get("strRemotePath");
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
  		WebDriver driver = getDriver(); //driver.quit();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		//Select Municipality
  		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Please choose a Municipality", strMunicipality, 1);
  		//Validate Violations Page
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Violations", "Something went wrong.", 1, "Does Not Exist", "");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violations", "Violations", 1, "1", "1", "Exists", "");
  		//Validate Violations Page
  		String strViolationId = clsCommonWeb.StoreTableValue(objDictionary, driver, "Violations", "Violations", 1, "strViolationId", "2", "2");
		try {Thread.sleep(1000);}catch (Exception e) {}
		String strConditional = clsCommonWeb.ConditionalStepText(objDictionary, driver, "Violations", "No results found", 1, "Exists", "");
		if(strConditional.equals("False"))
		{
			clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strViolationId, 1);
			clsCommonWeb.VerificationPointText(objDictionary, driver, "Violation", "Something went wrong.", 1, "Does Not Exist", "");
			try {Thread.sleep(1000);}catch (Exception e) {}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Violation", "Session Details", 1, "1", "1", "Exists", "");
		}
  		//Validate Parking Sessions Page
  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", "Settings", 1);
  		clsCommonWeb.ClickLink(objDictionary, driver, "Meter Groups", "Parking Sessions", 1);
  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Sessions", "Something went wrong.", 1, "Does Not Exist", "");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Sessions", "Parking Sessions", 1, "1", "1", "Exists", "");
  		//Validate Parking Session
  		String strParkingSessionId = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Sessions", "Parking Sessions", 1, "strParkingSessionId", "1", "1");
  		if(!strParkingSessionId.equals(""))
  		{
	  		clsCommonWeb.ClickLink(objDictionary, driver, "Violations", strParkingSessionId, 1);
	  		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Session", "Something went wrong.", 1, "Does Not Exist", "");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Session Details", 1, "1", "1", "Exists", "");
  		}
		//Validate Parking Lot Sessions Page
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Parking Lot Sessions", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Lot Sessions", "Something went wrong.", 1, "Does Not Exist", "");
		//Validate Parking Lot Session
		strConditional = clsCommonWeb.ConditionalStepImage(objDictionary, driver, "Parking Lot Sessions", "Overview Photo", "Exists");
		if(strConditional.equals("True"))
		{
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Lot Sessions", "Overview Photo", 1);
			clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Lot Session", "Something went wrong.", 1, "Does Not Exist", "");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Lot Session", "Session Details", 1, "1", "1", "Exists", "");
		}
		else{driver.navigate().back();}
		//Validate Payments Reports
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Reports", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Payments", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Payment Reports", "Something went wrong.", 1, "Does Not Exist", "");
		//clsCommonWeb.VerificationPointText(objDictionary, driver, "Payment Reports", "Report does not exist. Please click 'Find Payments'", 1, "Contains", "Report does not exist. Please click 'Find Payments'");
		driver.quit();
		//Validate Payments Reports
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Reports", 1);
		clsCommonWeb.ClickLink(objDictionary, driver, "Parking Session", "Payments", 1);
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Payment Reports", "Something went wrong.", 1, "Does Not Exist", "");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Payment Reports", "Payment Report Transactions", 1, "1", "1", "Exists", "");
  		//Validate Payment
		String strPaymentId = clsCommonWeb.StoreTableValue(objDictionary, driver, "Payment Reports", "Payment Report Transactions", 1, "strParkingSessionId", "1", "1");
		if(!strPaymentId.equals(""))
  		{
			clsCommonWeb.ClickLink(objDictionary, driver, "Payment Reports", strPaymentId, 1);
			clsCommonWeb.VerificationPointText(objDictionary, driver, "Payment", "Something went wrong.", 1, "Does Not Exist", "");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Payment", "Payment Section", 1, "1", "1", "Exists", "");
  		}
		driver.quit();
	}

}

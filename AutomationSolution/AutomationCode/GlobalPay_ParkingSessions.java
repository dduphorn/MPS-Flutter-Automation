package AutomationCode;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GlobalPay_ParkingSessions
{
	// public static SeleniumServer server;
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver() {return threadDriver.get();}

	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test1_LocalSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test1_RemoteSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes (Global Pay from "+strDeviceId+")");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test2_LocalSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test2_RemoteSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes (Global Pay from "+strDeviceId+")");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test3_LocalSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	int intCreditCardMinutes = Integer.parseInt(strMeterIncrementTime) * 4;
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test3_RemoteSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	int intCreditCardMinutes = Integer.parseInt(strMeterIncrementTime) * 4;
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinutes+" Minutes (Global Pay from "+strDeviceId+")");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test4_LocalSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	int intCreditCardMinutes = Integer.parseInt(strMeterIncrementTime) * 4;
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_G100_Global_RM_Test4_RemoteSpot1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes (Global Pay from "+strDeviceId+")");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_GP1021A_LM_TrueUp_LS1_CGPV_CCPLS_CCPLS(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValueContains", "Violation");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 20 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValueContains", "Unlocked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValueContains", "CSR Rejected");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValueContains", "Automated Process");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValueContains", "Credit card Payment #2");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValueContains", "Meter Purchased 20 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		driver.quit();
  	}


	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_GP1032_RM_169354167_1(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payment #9");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Cash Payment #10");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Cash Payment #11");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Cash Payment #12");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Cash Payment #13");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Cash Payment #14");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Cash Payment #15");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","3", "CellValue", "Cash Payment #16");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","5", "CellValue", "Meter Purchased 15 Minutes");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "19","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_GP1032_RM_169354167_2(Map<String, String> objDictionary, String strSpotNumber)
  	{
		//The test fails before this, so this needs to be updated
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strDeviceId = objDictionary.get("strDeviceId");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 15 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 60 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 30 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased 30 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased 15 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased 45 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased 15 Minutes (Global Pay from 5580-5581)");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased 15 Minutes (Global Pay from 5580-5581)");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payments");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased 15 Minutes (Global Pay from 5580-5581)");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Exited");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	

}

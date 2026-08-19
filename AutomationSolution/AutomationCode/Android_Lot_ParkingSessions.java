package AutomationCode;

import java.text.DecimalFormat;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Android_Lot_ParkingSessions 
{
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver() {return threadDriver.get();}
	
	//********************************************************************************************************************
	//ANDROID-LOT_TEST CASES - Hourly
	//********************************************************************************************************************
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1001_PRE_PPSL_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$2.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1002_PPSL_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$2.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1002_A_SMS_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Credit card Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1003_LP_PPSL_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	
	public void SENTRYLINK_ValidateParkingSessionHistory_BOOT_NOTICE(Map<String, String> objDictionary, String strViolationId) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Booted ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 55.00 ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Unpermitted Entrance");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Reason: booted ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_BOOT_NOTICE_2(Map<String, String> objDictionary, String strViolationId) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 55.00 ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Unpermitted Entrance");
		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
  	  	if(strRow3Column3.equals("Exited"))
  	  	{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read ");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Booted ");
  	  	}
  	  	else
  	  	{
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Booted ");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read ");
	  	}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Reason: booted ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1101_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		String strFirstPayment = objDictionary.get("strFirstPayment");
	  	DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		if(strFirstPayment == null)
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		else
		{
			double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$ 2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1102_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$4.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 4.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1201_UL_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		String strFirstPayment = objDictionary.get("strFirstPayment");
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		if(strFirstPayment == null)
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		else
		{
			double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");

		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1301_TU_UL_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		String strFirstPayment = objDictionary.get("strFirstPayment");
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		if(strFirstPayment == null)
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		else
		{
			double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Placeholder session created for purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
		String strFirstPayment = objDictionary.get("strFirstPayment");
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		if(strFirstPayment == null)
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValueContains", "Re-Entry");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "5", "CellValueContains", "Re-Entry");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "7", "0", "Row Does Not Exist", "");
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS_2(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		String strFirstPayment = objDictionary.get("strFirstPayment");
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		if(strFirstPayment == null)
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValueContains", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		else
		{
			double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValueContains", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		driver.quit();
	}
	
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1501_LP_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mobile Push");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInfomation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			String strFirstPayment = objDictionary.get("strFirstPayment");
			DecimalFormat dFormat = new DecimalFormat("$ 0.00");
			if(strFirstPayment == null)
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
			}
			else
			{
				double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Transaction Fee Payment");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
			}
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1601_UL_LP_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mobile Push");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strFirstPayment = objDictionary.get("strFirstPayment");
			DecimalFormat dFormat = new DecimalFormat("$ 0.00");
			if(strFirstPayment == null)
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
			}
			else
			{
				double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Transaction Fee Payment");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
			}
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1701_TU_UL_LP_OCA_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInformation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strPermitInfomation = objDictionary.get("strPermitInformation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
			String strFirstPayment = objDictionary.get("strFirstPayment");
			DecimalFormat dFormat = new DecimalFormat("$ 0.00");
			if(strFirstPayment == null)
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
			}
			else
			{
				double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Transaction Fee Payment");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
			}
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1702_TU_UL_OCA_LP_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		String strFirstPayment = objDictionary.get("strFirstPayment");
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		if(strFirstPayment == null)
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		else
		{
			double dblFirstPaymentFee = Integer.parseInt(strFirstPayment)*.01;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", dFormat.format(dblFirstPaymentFee));
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	
	
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1801_UL_LP_PHPK1_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mobile Push");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$2.00");
			String strPermitInformation = objDictionary.get("strPermitInformation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Credit card Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 2.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
			String strPermitInformation = objDictionary.get("strPermitInformation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1802_UL_LP_PHPK2_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$4.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mobile Push");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$ 4.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Credit card Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 4.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1901_CS_LP_LEBIG_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1902_CS_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1902_A_CS_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		
		String strRow2Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "2", "3");
		String strEnvironment = objDictionary.get("strEnvironment");
		if(strRow2Column3.contains("License Plate Changed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "License Plate Changed");
			if(strEnvironment.equals("SG"))
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "z3LotAutomation admin changed plate FROM MN CA1LOTAABR TO MN CA1LOTAA");}
			else if(strEnvironment.equals("PROD"))
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Darin Admin changed plate FROM MN CA1LOTAABR TO MN CA1LOTAA");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Recognized as Concierge");
		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "License Plate Changed");
  			if(strEnvironment.equals("SG"))
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "z3LotAutomation admin changed plate FROM MN CA1LOTAABR TO MN CA1LOTAA");}
			else if(strEnvironment.equals("PROD"))
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Darin Admin changed plate FROM MN CA1LOTAABR TO MN CA1LOTAA");}
  		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1903_CS_TUD_EAPOEE_LP_VCAPS_LE_LP_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1903_CS_TUD_EAPOEE_LP_VCAPS_LE_LP_VSPS_2(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1904_CS_TUE_EAPOEE_LP_VCAPS_LE_LP_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1904_CS_TUE_EAPOEE_LP_VCAPS_LE_LP_VSPS_2(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1905_CS_TUD_EAPOED_LP_VCAPS_LE_LP_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1905_CS_TUD_EAPOED_LP_VCAPS_LE_LP_VSPS_2(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$2.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited ");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1906_CS_TUE_EAPOED_LP_VCAPS_LE_LP_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1906_CS_TUE_EAPOED_LP_VCAPS_LE_LP_VSPS_2(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Concierge Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "4", "CellValue", "$2.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		//Function Refund Payments from Parking Session Screen
		clsCommonWeb.SENTRYLINK_RefundPaymentsFromParkingSessionPage(objDictionary, driver);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2201_UL_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 12.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL1907_TS_PRE_PPSL_LP_VUE_LE_PEOASLV_VTN_UNOD_ROVP_VTN2_UNOD_ROVP_VTN3_VSPS(Map<String, String> objDictionary, String strViolationId) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 55.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Unpermitted Entrance");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.contains("License Plate Changed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "7", "3", "CellValue", "Officer Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "8", "3", "CellValue", "Sent For Verification");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "9", "3", "CellValue", "Verified");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "10", "3", "CellValue", "Generating Ticket "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "11", "3", "CellValue", "Ticket Generated "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "12", "3", "CellValue", "Notified "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "13", "3", "CellValue", "Owner updated");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "14", "3", "CellValue", "Overdue "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "15", "3", "CellValue", "Generating Ticket "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "16", "3", "CellValue", "Ticket Generated "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "17", "3", "CellValue", "Notified "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "18", "3", "CellValue", "Owner updated");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "19", "3", "CellValue", "Overdue "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "20", "3", "CellValue", "Generating Ticket "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "21", "3", "CellValue", "Ticket Generated "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "22", "3", "CellValue", "Notified "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "23", "3", "CellValue", "Owner updated");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "24", "3", "CellValue", "Overdue "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "25", "3", "CellValue", "Awaiting Collection "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "26", "0", "Row Does Not Exist", "");
  		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "3", "CellValue", "Officer Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "7", "3", "CellValue", "Sent For Verification");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "8", "3", "CellValue", "Verified");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "9", "3", "CellValue", "Generating Ticket "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "10", "3", "CellValue", "Ticket Generated "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "11", "3", "CellValue", "Notified "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "12", "3", "CellValue", "Owner updated");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "13", "3", "CellValue", "Overdue "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "14", "3", "CellValue", "Generating Ticket "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "15", "3", "CellValue", "Ticket Generated "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "16", "3", "CellValue", "Notified "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "17", "3", "CellValue", "Owner updated");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "18", "3", "CellValue", "Overdue "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "19", "3", "CellValue", "Generating Ticket "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "20", "3", "CellValue", "Ticket Generated "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "21", "3", "CellValue", "Notified "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "22", "3", "CellValue", "Owner updated");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "23", "3", "CellValue", "Overdue "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "24", "3", "CellValue", "Awaiting Collection "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "25", "0", "Row Does Not Exist", "");
		}
		driver.quit();
	}
	
	
	//********************************************************************************************************************
	//ANDROID-LOT_TEST CASES - Daily
	//********************************************************************************************************************
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2000_PRE_PPSL_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$12.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2002_PPSL_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$12.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2002_A_PPSL_LPILP_VVE_LE_AP_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber, String strViolationId) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$12.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		//clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "7", "3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "8", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2002_B_PPSL_LPILP_VVE_LE_ULP_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber, String strViolationId) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$12.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		//clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2003_LP_PPSL_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2101_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 12.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2102_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 24.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$24.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2301_TU_UL_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 12.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2401_OPS_TU_UL_PP_LP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$ 12.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2501_LP_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mobile Push");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2601_UL_LP_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mobile Push");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2701_TU_UL_LP_OCA_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Expiring Session 30,15,5 minutes Error");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2702_TU_UL_OCA_LP_PP_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Permit Purchase");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$12.00");
		String strPermitInformation = objDictionary.get("strPermitInformation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$ 12.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistory_AL2801_UL_LP_PPK_VCAPS_LE_VSPS(Map<String, String> objDictionary, String strLicensePlateNumber) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Get Fees On First Payment
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "3", "CellValue", "Parked");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strNotificationSetting = clsHttpConnections.HTTPCONNECTION_GetMobileSubscriptionPreference(objDictionary, "Session Notifications", "app");
		if (strNotificationSetting.equals("true"))
		{
			//Not finished
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Credit card Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
			String strPermitInformation = objDictionary.get("strPermitInformation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");

		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Credit card Payment #0");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 12.00");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Permit Purchase");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "4", "CellValue", "$12.00");
			String strPermitInformation = objDictionary.get("strPermitInformation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "5", "CellValue", "Plate "+strLicensePlateNumber+" EXACT matched permit "+strPermitInformation);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "5", "0", "Row Does Not Exist", "");
		}
		driver.quit();
	}
}


package AutomationCode;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Android_TuffPark_ParkingSessions
{
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver() {return threadDriver.get();}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2004_PurchasePermit_Park_Leave_VPSH(Map<String, String> objDictionary, String strLicensePlateNumber)
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
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$1.25");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 1.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_2007_PurchasePermit_LP_LE_LP_LE_VPSH_1(Map<String, String> objDictionary, String strLicensePlateNumber)
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
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$1.25");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 1.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_2007_PurchasePermit_LP_LE_LP_LE_VPSH_2(Map<String, String> objDictionary, String strLicensePlateNumber)
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
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$1.25");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 1.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "5", "CellValue", "Plate Read");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2009_LP_MLE_LP_VPSH_1(Map<String, String> objDictionary, String strLicensePlateNumber)
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
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$1.25");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 1.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "VEHICLE STILL PARKED");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2009_LP_MLE_LP_VPSH_2(Map<String, String> objDictionary, String strLicensePlateNumber)
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
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "4", "CellValue", "$1.25");
		String strPermitInfomation = objDictionary.get("strPermitInfomation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "1", "5", "CellValue", "Plate "+strLicensePlateNumber+" matched permit "+strPermitInfomation);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "3", "CellValue", "Mps account Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "4", "CellValue", "$ 1.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "2", "5", "CellValue", "Permit Payment");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "3", "3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History",1, "4", "3", "CellValue", "VEHICLE STILL PARKED");
		driver.quit();
	}
}

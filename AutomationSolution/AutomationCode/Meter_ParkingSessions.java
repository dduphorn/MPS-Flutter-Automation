package AutomationCode;

import java.text.DecimalFormat;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver.Navigation;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Properties;

public class Meter_ParkingSessions
{
	// public static SeleniumServer server;
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver() {return threadDriver.get();}

	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1001_FTFP0_PS1_CP1_VMT_ES1_BeforeExit(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag, String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1001_FTFP0_PS1_CP1_VMT_ES1_AfterExit(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1002_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1002A_FTFP0_PO_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		if(strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1003_FTFP0_PS1_CP1_VMT_BMT_VMT_ES1(Map<String, String> objDictionary, String strMeterIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1003A_FTFP0_PO_CP1_VMT_W5_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow1Column3", "1", "3");
		if(strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");
			String strRow2Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "2", "3");
			if(strRow2Column3.equals("Cash Payment #2"))
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #2");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Parked");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");
			}
		}
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Exited"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValueContains", "Virt Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1004_FTFP0_PS1_CP1_PMT_LPRM_e_MTIV_VMT_ES1(Map<String, String> objDictionary, String strMaximumDuration, String strMeterIncrementTimeMins)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		int intCashPaymentCounter = 0;
		int intPaymentCounter = 1;
		// ValidateParkingSessionHistoryAndImages
		Double dblCoinIncrementValue = Double.parseDouble(strMaximumDuration)/ Double.parseDouble(strMeterIncrementTimeMins);
		int intNbrCoinRequiredForMaxTime = (int) Math.ceil(dblCoinIncrementValue);
		while (intCashPaymentCounter < intNbrCoinRequiredForMaxTime)
		{
			int intRowNumber = intCashPaymentCounter + 2;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "3", "CellValue", "Cash Payment #" + intPaymentCounter);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "4", "CellValue", "$ 0.25");
			String strFamily = objDictionary.get("strFamily");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
			intCashPaymentCounter++;
			intPaymentCounter++;
		}
		int intExitRowNumber = intCashPaymentCounter + 2;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 3;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intExitRowNumber), "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowDoesntExistRowNumber), "0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1005_FTFP0_PS1_CP1_PMT_MTIV_gt_LPRM_VMT_ES1(Map<String, String> objDictionary, String strMaximumDuration, String strMeterIncrementTimeMins)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		int intCashPaymentCounter = 1;
  		int intPaymentCounter = 1;
  		Double dblCoinIncrementValue = Double.parseDouble(strMaximumDuration)/Double.parseDouble(strMeterIncrementTimeMins);
  		double dblRemainingMinutes = dblCoinIncrementValue - Math.floor(dblCoinIncrementValue);
      	int intRemainingMinutes = (int)Math.ceil(dblRemainingMinutes * Integer.parseInt(strMeterIncrementTimeMins)) % 60;
      	int intNbrCoinRequiredForMaxTime = (int)Math.ceil(dblCoinIncrementValue);
      	while (intCashPaymentCounter < intNbrCoinRequiredForMaxTime)
		{
  			int intRowNumber = intCashPaymentCounter + 1;
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strMeterIncrementTimeMins+" Minutes");
  	  		intCashPaymentCounter++;
  	  		intPaymentCounter++;
        }
  		//Validate LastPaymentRemainingMinutes
  		int intRowNumber = intCashPaymentCounter+1;
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
	  	int intExitRowNumber = intCashPaymentCounter + 3;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 4;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1006_FTFP0_PS1_CP1_VMT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strMeterIncrementTimeMins,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1007_FTFP0_PS1_CP1_VMT_PRT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strMeterIncrementTimeMins, String strMaximumDuration)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		double dblMaximumDuration = Double.parseDouble(strMaximumDuration);
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		String strCreditCardRemainingAmount = SENTRYLINK_CalculateCreditCardRemainingAmount(dblMaximumDuration,strMeterIncrementTimeMins, dFormat);
		int intCreditCardPurchaseTime = (int) dblMaximumDuration - Integer.parseInt(strMeterIncrementTimeMins);
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", strCreditCardRemainingAmount);}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");}
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + intCreditCardPurchaseTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_FTFP0_PS1_CCP1_VMT_ES1_VPSH_VICAE_VIAC_BeforeExit(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "VEHICLE STILL PARKED");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  			driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1008_FTFP0_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1009_FTFP0_PS1_CCP1_VMT_BMT_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strFamily = objDictionary.get("strFamily");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1010_FTFP0_PS1_CCP1_PMT_LPRM_e_MTIV_VMT_ES1(Map<String, String> objDictionary, String strMaximumDuration, String strCreditCardIncrementTime, String strCoinTimePuchaseLimit)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		 double dblAmount = Double.parseDouble(strMaximumDuration)/Double.parseDouble(strCreditCardIncrementTime) * .25;
       	DecimalFormat dFormat = new DecimalFormat("$ 0.00");
       	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", dFormat.format(dblAmount));
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCoinTimePuchaseLimit+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
	    	driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1011_FTFP0_PS1_CCP1_PMT_LPRM_gt_MTIV_VMT_ES1(Map<String, String> objDictionary, int intAddCounter, double dblCreditCardMinimumPurchaseMinutes, int intRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History

	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		int intCashPaymentCounter = 1;
  		int intPaymentCounter = 1;
  		while (intCashPaymentCounter < intAddCounter)
		{
  			int intRowNumber = intCashPaymentCounter + 1;
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Credit card Payment #"+intPaymentCounter);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 1.00");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
  			intCashPaymentCounter++;
  			intPaymentCounter++;
        }
  		//Validate LastPaymentRemainingMinutes
  		int intRowNumber = intCashPaymentCounter+1;
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Credit card Payment #"+intPaymentCounter);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 1.00");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
	  	int intExitRowNumber = intCashPaymentCounter + 2;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 3;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
	    	driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1012_FTFP0_PS1_CCP1_VMT_CP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes,String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1013_FTFP0_PS1_CCP1_VMT_PRT_CP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes, String strCreditCardIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payment #9");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Cash Payment #10");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Cash Payment #11");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Cash Payment #12");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Cash Payment #13");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  			driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
  		//
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1014_FTFP10_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1014A_FTFP10_PO_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow1Column3", "1", "3");
  		if(strRow1Column3.equals("Parked"))
      	{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
      	}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Cash Payment #1 ");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Virt Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 1 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		}
  		if(strValidateImageFlag.equals("True"))
	  	{
  			driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1015_FTFP10_PS1_CP1_VMT_BMT_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
  		if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.17");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	    }
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.17");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  	}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
   			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
   	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1016_FTFP10_PS1_CP1_PMT_LPRM_e_MTIV_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intCoinIncrementValue)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
	    int intCashPaymentCounter = 0;
	    if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	    	int intPaymentCounter = 3;
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.09");
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
    		intCashPaymentCounter = 1;
    		while (intCashPaymentCounter < intCoinIncrementValue)
	    	{
      			int intRowNumber = intCashPaymentCounter + 3;
      			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
      	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
      	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
      			intCashPaymentCounter++;
      			intPaymentCounter++;
	      	}
        }
      	else
      	{
      		int intPaymentCounter = 2;
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.09");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			while (intCashPaymentCounter < intCoinIncrementValue)
			{
	  			int intRowNumber = intCashPaymentCounter + 3;
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  			intCashPaymentCounter++;
	  			intPaymentCounter++;
	        }
	    }
	    int intExitRowNumber = intCashPaymentCounter + 3;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 4;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1017_FTFP10_PS1_CP1_PMT_MTIV_gt_LPRM_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intRemainingMinutes, int intAddCounter)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			int intCashPaymentCounter = 2;
			int intPaymentCounter = 3;
	  		while (intCashPaymentCounter < intAddCounter)
			{
	  			int intRowNumber = intCashPaymentCounter + 2;
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  	  		intCashPaymentCounter++;
	  	  		intPaymentCounter++;
	        }
	  		//Validate LastPaymentRemainingMinutes
	  		int intRowNumber = intCashPaymentCounter+2;
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
		  	int intExitRowNumber = intCashPaymentCounter + 4;
			int intRowDoesntExistRowNumber = intCashPaymentCounter + 5;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.06");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			int intCashPaymentCounter = 2;
			int intPaymentCounter = 2;
	  		while (intCashPaymentCounter < intAddCounter)
			{
	  			int intRowNumber = intCashPaymentCounter + 2;
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  	  		intCashPaymentCounter++;
	  	  		intPaymentCounter++;
	        }
	  		//Validate LastPaymentRemainingMinutes
	  		int intRowNumber = intCashPaymentCounter+2;
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
		  	int intExitRowNumber = intCashPaymentCounter + 3;
			int intRowDoesntExistRowNumber = intCashPaymentCounter + 4;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
      	}
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1018_FTFP10_PS1_CP1_VMT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		if (strInitialPaymentRow.equals("Cash Payment #1"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Virt Payment #2");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased 10 Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Credit card Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "4", "CellValue", "$ 1.00");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.17");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased 10 Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Credit card Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "4", "CellValue", "$ 1.00");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"6", "0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1019_FTFP10_PS1_CP1_VMT_PRT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strMaximumDuration, String strFreeTimeFirstPayment)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	double dblMaximumDuration = Double.parseDouble(strMaximumDuration) - Double.parseDouble(strFreeTimeFirstPayment);
      	//Calculate Credit Card Remaining Amount
      	DecimalFormat dFormat = new DecimalFormat("$ 0.00");
      	String  strCreditCardRemainingAmount = SENTRYLINK_CalculateCreditCardRemainingAmount(dblMaximumDuration, strCreditCardIncrementTime,dFormat);
      	int intCreditCardPurchaseTime = (int)dblMaximumDuration - Integer.parseInt(strCreditCardIncrementTime);
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	    if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.17");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", strCreditCardRemainingAmount);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+intCreditCardPurchaseTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		}
	    else
	    {
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.17");
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", strCreditCardRemainingAmount);
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+intCreditCardPurchaseTime+" Minutes");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
	  		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1020_FTFP10_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4", "CellValue","$ 0.17");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.09");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1021_FTFP10_PS1_CCP1_VMT_BMT_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
  		String strPaymentRow3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strPaymentRow3", "3", "3");
		if(strPaymentRow3.equals("Virt Payment #2"))
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.17");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.17");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 10 Minutes");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1022_FTFP10_PS1_CCP1_PMT_LPRM_e_MTIV_VMT_ES1(Map<String, String> objDictionary, int intPurchaseTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 4.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intPurchaseTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.17");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1023_FTFP10_PS1_CCP1_PMT_LPRM_gt_MTIV_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, double dblCreditCardMinimumPurchaseMinutes, int intRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
    	if(strInitialPaymentRow.equals("Virt Payment #1"))
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.10");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Credit card Payment #4");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
  	  		String strRowThreeEvent = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "3", "3");
  	  		if(strRowThreeEvent.equals("Virt Payment #2"))
  	  		{
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.10");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
		  	}
  	  		else
  	  		{
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Virt Payment #3");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.10");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 10 Minutes");
			}
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Credit card Payment #4");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
  	 	}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
	    	//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1024_FTFP10_PS1_CCP1_VMT_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intCreditCardMinimumPurchaseMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Credit card Payment #1"))
      	{
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1025_FTFP10_PS1_CCP1_VMT_PRT_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intCreditCardMinimumPurchaseMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Credit card Payment #1"))
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payment #9");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Cash Payment #10");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Cash Payment #11");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Cash Payment #12");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Cash Payment #13");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Cash Payment #14");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payment #9");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Cash Payment #10");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Cash Payment #11");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Cash Payment #12");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Cash Payment #13");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Cash Payment #14");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","0", "Row Does Not Exist", "");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "19","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1025A_FTFP0_FTR_MTIV_gt_RFT_PS1_GPV_UL_CCP1_PMT_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strViolationNumber = objDictionary.get("strViolationNumber");
		String strFamily =  objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
		if(strFamily == null)
		{
			// Validate First Payment
			String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			String strMaximumDuration = objDictionary.get("strMaximumDuration");
			int intMaximumDurationMinusOne = Integer.parseInt(strMaximumDuration) - 1;
			if (strRow3Column5.contains("Meter Purchased " + intMaximumDurationMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow3Column5 + ")");}
			else if (strRow3Column5.contains("Meter Purchased " + strMaximumDuration + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow3Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ strMaximumDuration + " Minutes) - actual value (" + strRow3Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Handicap Payment");}
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked " + strViolationNumber))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1025B_FTFP0_FTR_MTIV_gt_RFT_PS1_ES1_PS1_GPV_UL_CCP1_PMT_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strViolationNumber = objDictionary.get("strViolationNumber");
		String strFamily =  objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
		if(strFamily == null)
		{
			// Validate First Payment
			String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			String strMaximumDuration = objDictionary.get("strMaximumDuration");
			int intMaximumDurationMinusOne = Integer.parseInt(strMaximumDuration) - 1;
			if (strRow3Column5.contains("Meter Purchased " + intMaximumDurationMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow3Column5 + ")");}
			else if (strRow3Column5.contains("Meter Purchased " + strMaximumDuration + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow3Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ strMaximumDuration + " Minutes) - actual value (" + strRow3Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Handicap Payment");}
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked " + strViolationNumber))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Reason: Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True")) 
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1025C_FTFP0_PS1_GPV_UL_CCP1_CP1_CP1_CP1_VMT_ES1(Map<String, String> objDictionary)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	String strViolationNumber = objDictionary.get("strViolationNumber");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationNumber);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "3", "5");
		if(strRow2Column5.contains("Meter Purchased 8 Minutes")||strRow2Column5.contains("Meter Purchased 9 Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Purchased 8 Minutes) - actual value ("+strRow2Column5+")","Local");}
  	  	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked " + strViolationNumber))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased 2 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased 2 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #4");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased 2 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 2 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased 2 Minutes");
			String strRow6Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow6Column3", "6", "3");
			if(strRow6Column3.equals("Cash Payment #4"))
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #4");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased 2 Minutes");
				String strRow7Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow7Column3", "7", "3");
				if(strRow7Column3.equals("Unlocked " + strViolationNumber))
				{
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unlocked "+strViolationNumber);
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: Unlocked");
			  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "CSR Rejected "+strViolationNumber);
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
			  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
				}
				else
				{
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
			  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Unlocked "+strViolationNumber);
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Reason: Unlocked");
			  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "CSR Rejected "+strViolationNumber);
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
					clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
				}
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Unlocked "+strViolationNumber);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Reason: Unlocked");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "CSR Rejected "+strViolationNumber);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #4");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased 2 Minutes");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
			}
		}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1026_FTFP0_FTR_RFT_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intFirstPaymentTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		//Validate First Payment
  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		int intFirstPaymentTimeMinusOne = intFirstPaymentTime -1;
		int intFirstPaymentTimePlusOne = intFirstPaymentTime +1;
		if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentTimePlusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentTime+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentTimeMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strRow2Column5+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1027_FTFP0_FTR_MTIV_gt_RFT_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intFirstPaymentTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		//Validate First Payment
  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		int intFirstPaymentTimeMinusOne = intFirstPaymentTime -1;
		int intFirstPaymentTimePlusOne = intFirstPaymentTime +1;
		if(strRow2Column5.contains("Purchased "+intFirstPaymentTime+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Purchased "+intFirstPaymentTimePlusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Purchased "+intFirstPaymentTimeMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1028_FTFP0_FTR_RFT_gt_MTIV_PS1_CP1_VMT_BMT_ES1(Map<String, String> objDictionary, int intFirstPaymentMinutes, String strCreditCardIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  		int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
  		if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutesPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  			driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1029_FTFP0_FTR_RFT_gt_MTIV_PS1_CP1_PMT_LPRM_e_MTIV_ES1(Map<String, String> objDictionary, int intMeterTimeFirstPayment, String strCreditCardIncrementTime, int intCoinIncrementValue)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  	String strRowNumber = "2";
		String strColumnNumber = "5";
		int intMeterTimeFirstPaymentPlus1 = intMeterTimeFirstPayment + 1;
		int intMeterTimeFirstPaymentMinus1 = intMeterTimeFirstPayment - 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPayment+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPayment+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intMeterTimeFirstPayment+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		int intCashPaymentCounter = 2;
		int intPaymentCounter = 2;
  		while (intCashPaymentCounter < intCoinIncrementValue)
		{
  			int intRowNumber = intCashPaymentCounter + 1;
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  			intCashPaymentCounter++;
  			intPaymentCounter++;
        }
  		int intExitRowNumber = intCashPaymentCounter + 2;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 3;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  			//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1030_FTFP0_FTR_RFT_gt_MTIV_PS1_CP1_PMT_LPRM_gt_MTIV_ES1(Map<String, String> objDictionary, int intMeterTimeFirstPayment, String strCreditCardIncrementTime ,int intCoinIncrementValue, int intRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
    	String strRowNumber = "2";
		String strColumnNumber = "5";
		int intMeterTimeFirstPaymentPlus1 = intMeterTimeFirstPayment +1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPayment+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPayment+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intMeterTimeFirstPayment+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		int intCashPaymentCounter = 2;
		int intPaymentCounter = 2;
  		while (intCashPaymentCounter < intCoinIncrementValue)
		{
  			int intRowNumber = intCashPaymentCounter + 1;
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  			intCashPaymentCounter++;
  			intPaymentCounter++;
        }
  		//Validate LastPaymentRemainingMinutes
  		int intRowNumber = intCashPaymentCounter+1;
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
	  	int intExitRowNumber = intCashPaymentCounter + 2;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 3;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1031_FTFP0_FTR_PS1_CP1_VMT_CCP1_VMT_ES1(Map<String, String> objDictionary, int intMeterTimeFirstPayment, String strCreditCardIncrementTime, int intCreditCardMinimumPurchaseMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intMeterTimeFirstPaymentPlus1 = intMeterTimeFirstPayment +1;
		int intMeterTimeFirstPaymentMinus1 = intMeterTimeFirstPayment -1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPayment+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPayment+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intMeterTimeFirstPayment+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1032_FTFP0_FTR_PS1_CP1_VMT_PRT_CCP1_VMT_ES1(Map<String, String> objDictionary, int intMeterTimeFirstPayment, String strCreditCardIncrementTime, String strMaximumDuration, String strFreeTimeMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	double dblMaximumDuration = Double.parseDouble(strMaximumDuration);
      	//Calculate Credit Card Remaining Amount
      	DecimalFormat dFormat = new DecimalFormat("$ 0.00");
      	String  strCreditCardRemainingAmount = SENTRYLINK_CalculateCreditCardRemainingAmount(dblMaximumDuration, strCreditCardIncrementTime, dFormat);
      	int intCreditCardPurchaseTime = (int)dblMaximumDuration - Integer.parseInt(strCreditCardIncrementTime);
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intMeterTimeFirstPaymentPlus1 = intMeterTimeFirstPayment +1;
		int intMeterTimeFirstPaymentMinus1 = intMeterTimeFirstPayment -1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPayment+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPayment+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intMeterTimeFirstPayment+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", strCreditCardRemainingAmount);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardPurchaseTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1033_FTFP0_FTR_RFT_gt_MTIV_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intMeterTimeFirstPayment, String strCreditCardIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		String strRowNumber = "2";
  		String strColumnNumber = "4";
  		String strPurchasedAmount = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
  		if(strPurchasedAmount.contains("$ 1.00"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled ($ 1.00)");}
  		else
  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal ($ 1.00) - actual value ("+strPurchasedAmount+")","Local");}
 	   	strRowNumber = "2";
  		strColumnNumber = "5";
  		int intMeterTimeFirstPaymenPlus1 = intMeterTimeFirstPayment + 1;
  		int intMeterTimeFirstPaymenMinus1 = intMeterTimeFirstPayment - 1;
  		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
  		if(strPurchasedMinutes.contains("Meter Purchased "+intMeterTimeFirstPaymenPlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Purchased "+intMeterTimeFirstPaymenPlus1+" Minutes");}
  		else if(strPurchasedMinutes.contains("Meter Purchased "+intMeterTimeFirstPayment+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Purchased "+intMeterTimeFirstPayment+" Minutes");}
  		else if(strPurchasedMinutes.contains("Meter Purchased "+intMeterTimeFirstPaymenMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Purchased "+intMeterTimeFirstPaymenMinus1+" Minutes");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intMeterTimeFirstPayment+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1034_FTFP0_FTR_MTIV_gt_RFT_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intMeterTimeFirstPayment, String strCreditCardIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intMeterTimeFirstPaymentMinus1 = intMeterTimeFirstPayment -1;
		int intMeterTimeFirstPaymentPlus1 = intMeterTimeFirstPayment +1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentMinus1+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPayment+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPayment+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intMeterTimeFirstPaymentPlus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intMeterTimeFirstPayment+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1035_FTFP0_FTR_RFT_gt_MTIV_PS1_CCP1_VMT_BMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intTimePurchased+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
		    clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
		    try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
		    //VIAC: Validate Images Appear Correctly
		    clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1036_FTFP0_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_LPRM_e_MTIV_ES1(Map<String, String> objDictionary, double dblAmount, int intTimePurchased)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	DecimalFormat dFormat = new DecimalFormat("$ 0.00");
      	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", dFormat.format(dblAmount));
  	  	//Validate First Payment
  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  		int intTimePurchasedPlusOne = intTimePurchased +1;
  		if(strRow2Column5.contains("Meter Purchased "+intTimePurchased+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intTimePurchased+" Minutes) - actual value ("+strRow2Column5+")","Local");}
        clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	       	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	       	try {Thread.sleep(1000);}catch (Exception e) {}
	   		//VICAE: Validate Image Count After Exit
	   		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	       	//VIAC: Validate Images Appear Correctly
	       	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	   		driver.quit();
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1037_FTFP0_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_LPRM_gt_MTIV_ES1(Map<String, String> objDictionary, int intFirstPaymentMinutes, int intCreditCardIncrementTime, int intLastPaymentMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
	  	String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  		int strFirstPaymentMinutesPlusOne = intFirstPaymentMinutes + 1;
  		int strFirstPaymentMinutesMinusOne = intFirstPaymentMinutes - 1;
  		if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+strFirstPaymentMinutesPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+strFirstPaymentMinutesMinusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
  		String strRow4Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column5", "4", "5");
  		int intLastPaymentMinutesMinusOne = intLastPaymentMinutes -1;
  		if(strRow4Column5.contains("Meter Purchased "+intLastPaymentMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled ("+strRow4Column5+")");}
  		else if(strRow4Column5.contains("Meter Purchased "+intLastPaymentMinutesMinusOne+" Minutes"))
  		{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled ("+strRow4Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (4) column (5) of the table (Parking Session History) did not equal (Purchased "+intLastPaymentMinutes+" Minutes) - actual value ("+strRow4Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
      	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1038_FTFP0_FTR_PS1_CCP1_VMT_CP1_VMT_ES1(Map<String, String> objDictionary, int intFirstPaymentMinutes,String strCreditCardIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
     	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
   		//Validate First Payment
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
		int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
		if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutes+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesPlusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
   	   	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
   		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	       	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	       	try {Thread.sleep(1000);}catch (Exception e) {}
	   		//VICAE: Validate Image Count After Exit
	   		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	       	//VIAC: Validate Images Appear Correctly
	       	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	   		driver.quit();
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1039_FTFP0_FTR_PS1_CCP1_VMT_PRT_CP1_VMT_ES1(Map<String, String> objDictionary, String strTimePurchased,String strCreditCardIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
     	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strTimePurchased+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payment #9");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Cash Payment #10");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Cash Payment #11");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Cash Payment #12");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Cash Payment #13");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Exited");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","0", "Row Does Not Exist", "");
   		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	       	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	       	try {Thread.sleep(1000);}catch (Exception e) {}
	   		//VICAE: Validate Image Count After Exit
	   		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	       	//VIAC: Validate Images Appear Correctly
	       	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	   		driver.quit();
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1040_FTFP10_FTR_RFT_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		int intTimePurchasedPlusOne = intTimePurchased + 1;
		String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
		if (strInitialPaymentRow.equals("Cash Payment #1"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{
				// Validate Purchase Time
				String strRowNumber = "2";
				String strColumnNumber = "5";
				String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
				if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else
				{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased "+ intTimePurchased + " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
			}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", "Handicap Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Virt Payment #2");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", "Meter Purchased 10 Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", "Handicap Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Virt Payment #1");
			if(strFamily == null)
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.17");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", "Meter Purchased 10 Minutes");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.09");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", "Handicap Payment");
			}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{
				// Validate Purchase Time
				String strRowNumber = "3";
				String strColumnNumber = "5";
				String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
				if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else
				{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased "+ intTimePurchased + " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}

			}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", "Handicap Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
			driver.quit();
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1041_FTFP10_FTR_MTIV_gt_RFT_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		String strRowNumber = "2";
			String strColumnNumber = "5";
			int intTimePurchasedPlus1 = intTimePurchased +1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
			else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlus1+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchasedPlus1+" Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.09");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		String strRowNumber = "3";
			String strColumnNumber = "5";
			int intTimePurchasedMinus1 = intTimePurchased -1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
			else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedMinus1+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchasedMinus1+" Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1042_FTFP10_FTR_RFT_gt_MTIV_PS1_CP1_VMT_BMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	    String strPaymentType = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPaymentType", "2", "3");
	    String strRowNumber = null;String strColumnNumber = null;String strPurchasedMinutes = null;
	    int intTimePurchasedPlusOne = intTimePurchased + 1;
	    switch (strPaymentType)
	    {
	    	case "Cash Payment #1":
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
    	  		//Validate Purchase Time
    	  		strRowNumber = "2";strColumnNumber = "5";
    	  		strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
    	  		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
    			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
    			else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
    			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
    			else
    			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" minutes) - actual value ("+strPurchasedMinutes+")","Local");}
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.17");
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
    	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
    	  		break;
	    	case "Virt Payment":
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.17");
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
    	  		//Validate Purchase Time
    	  		strRowNumber = "3";strColumnNumber = "5";
    	  		strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
    	  		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
    			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
    			else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
    			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
    			else
    			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" minutes) - actual value ("+strPurchasedMinutes+")","Local");}
    			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
    	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
    	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
    	  		break;
	    }
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1043_FTFP10_FTR_RFT_gt_MTIV_PS1_CP1_PMT_LPRM_e_MTIV_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intCoinIncrementValue)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	String strRemainingFreeTimeFirstPaymentMinutes = objDictionary.get("strRemainingFreeTimeFirstPaymentMinutes");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
    	int intCashPaymentCounter = 1;
    	int intPaymentCounter = 3;
    	//Calculate inFirstPaymentMinutes
    	int intFirstPaymentMinutes = Integer.parseInt(strRemainingFreeTimeFirstPaymentMinutes) + Integer.parseInt(strCreditCardIncrementTime);
    	if(strInitialPaymentRow.equals("Cash Payment #1"))
    	{
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		  	//Validate First Payment
		    String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
	    	int strFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
	    	int strFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
	  		if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutes+" Minutes"))
	  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Purchased "+strFirstPaymentMinutesMinusOne+" Minutes"))
	  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Purchased "+strFirstPaymentMinutesPlusOne+" Minutes"))
	  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.09");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
			while (intCashPaymentCounter < intCoinIncrementValue)
			{
	  			int intRowNumber = intCashPaymentCounter + 3;
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  			intCashPaymentCounter++;
	  			intPaymentCounter++;
	        }
	  		int intExitRowNumber = intCashPaymentCounter + 3;
			int intRowDoesntExistRowNumber = intCashPaymentCounter + 4;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.09");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
			//Validate First Payment
			String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "3", "5");
	  		int strFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
	  		int strFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
	  		if(strRow3Column5.contains("Purchased "+intFirstPaymentMinutes+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
	  		else if(strRow3Column5.contains("Purchased "+strFirstPaymentMinutesMinusOne+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
	  		else if(strRow3Column5.contains("Purchased "+strFirstPaymentMinutesPlusOne+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
	  		else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow3Column5+")","Local");}
	  	  	while (intCashPaymentCounter < intCoinIncrementValue)
			{
	  			int intRowNumber = intCashPaymentCounter + 3;
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"3", "CellValue", "Cash Payment #"+intPaymentCounter);
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"4", "CellValue", "$ 0.25");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  			intCashPaymentCounter++;
	  			intPaymentCounter++;
	        }
	  		int intExitRowNumber = intCashPaymentCounter + 3;
			int intRowDoesntExistRowNumber = intCashPaymentCounter + 4;
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intExitRowNumber),"3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowDoesntExistRowNumber),"0", "Row Does Not Exist", "");
      	}
    	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1044_FTFP10_FTR_RFT_gt_MTIV_PS1_CP1_PMT_LPRM_gt_MTIV_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intCoinIncrementValue, int intRemainingMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strRemainingFreeTimeFirstPaymentMinutes = objDictionary.get("strRemainingFreeTimeFirstPaymentMinutes");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//Calculate inFirstPaymentMinutes
	  	int intFirstPaymentMinutes = Integer.parseInt(strRemainingFreeTimeFirstPaymentMinutes) + Integer.parseInt(strCreditCardIncrementTime);
     	//VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  	String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
	  	int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
	  	int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
  		if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutesMinusOne+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+intFirstPaymentMinutesPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1045_FTFP10_FTR_PS1_CP1_VMT_CCP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased, int intCreditCardMinimumPurchaseMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intTimePurchasedPlusOne = intTimePurchased + 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
  		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
  		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
  		else
  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" minutes) - actual value ("+strPurchasedMinutes+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1046_FTFP10_FTR_PS1_CP1_VMT_PRT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strFreeTimeFirstPayment, String strCreditCardIncrementTime, String strMaximumDuration,int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	double dblMaximumDuration = Double.parseDouble(strMaximumDuration) - Double.parseDouble(strFreeTimeFirstPayment);
      	DecimalFormat dFormat = new DecimalFormat("$ 0.00");
      	String  strCreditCardRemainingAmount = SENTRYLINK_CalculateCreditCardRemainingAmount(dblMaximumDuration, strCreditCardIncrementTime,dFormat);
      	int intTimePurchasedPlusOne = intTimePurchased -1;
      	int intCreditCardPurchaseTime = (int)dblMaximumDuration - Integer.parseInt(strCreditCardIncrementTime);
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
  		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
  		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
  		else
  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", strCreditCardRemainingAmount);
		String strRow4Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column5", "4", "5");
  		int intCreditCardPurchaseTimeMinusOne = intCreditCardPurchaseTime - 1;
  		if(strRow4Column5.contains("Meter Purchased "+intCreditCardPurchaseTime+" Minutes"))
  		{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled (App Purchased "+intCreditCardPurchaseTime+" Minutes)");}
		else if(strRow4Column5.contains("Meter Purchased "+intCreditCardPurchaseTime+" Minutes"))
  		{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled (App Purchased "+intCreditCardPurchaseTimeMinusOne+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (4) column (5) of the table (Parking Session History) did not equal (App Purchased "+intTimePurchased+" Minutes) - actual value ("+strRow4Column5+")","Local");}
	 	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1046_A_FTFP0_FTR_PS1_WFTE_CP1_VMT(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot1");
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1046_B_FTFP10_FTR_PS1_WFTE_CP1(Map<String, String> objDictionary, String intExpectedRemainingTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot1");
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1046_C_FTFP0_FTR_PS1_WFTE_CCP1_VMT(Map<String, String> objDictionary, int intExpectedTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedTimeMinutes+" Minutes ");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		//clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1047_FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + intTimePurchased + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3",	"3", "CellValue", "Virt Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1048_FTFP10_FTR_MTIV_gt_RFT_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intTimePurchased+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1049_FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_VMT_BMT_ES1(Map<String, String> objDictionary, int intFirstPaymentMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
		int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
		if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutes+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesPlusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1050_FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_LPRM_e_MTIV_ES1(Map<String, String> objDictionary, int intFirstTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 4.00");
  	  	String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  	  	int intFirstTimeMinutesMinusOne = intFirstTimeMinutes -1;
  	  	int intFirstTimeMinutesMinusTwo = intFirstTimeMinutes -2;
  	  	int intFirstTimeMinutesPlusOne = intFirstTimeMinutes +1;
  	  	int intFirstTimeMinutesPlusTwo = intFirstTimeMinutes +2;
  	  	if(strRow2Column5.contains("Meter Purchased "+intFirstTimeMinutesMinusTwo+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstTimeMinutesMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstTimeMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intFirstTimeMinutesPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intFirstTimeMinutesPlusTwo+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intFirstTimeMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1051_FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_LPRM_gt_MTIV_ES1(Map<String, String> objDictionary, String strFreeTimeMinutes, double dblCreditCardMinimumPurchaseMinutes, int intNbrCardInsertsRequiredForMaxTime, int intRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strRemainingFreeTimeFirstPaymentMinutes = objDictionary.get("strRemainingFreeTimeFirstPaymentMinutes");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	int intFirstPaymentMinutes = Integer.parseInt(strRemainingFreeTimeFirstPaymentMinutes) + (int)dblCreditCardMinimumPurchaseMinutes;
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		//Validate First Payment
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
		int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
		if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutes+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesPlusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+(int)dblCreditCardMinimumPurchaseMinutes+" Minutes");
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Credit card Payment #4");
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 1.00");
 		String strRow5Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column5", "5", "5");
  		int intRemainingMinutesMinusOne = intRemainingMinutes - 1;
  		if(strRow5Column5.contains("Meter Purchased "+intRemainingMinutesMinusOne+" Minutes"))
  		{Reporter.log("The cell value in row (5) column (5) of the table (Parking Session History) equalled ("+strRow5Column5+")");}
		else if (strRow5Column5.contains("Meter Purchased "+intRemainingMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (5) column (5) of the table (Parking Session History) equalled ("+strRow5Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (5) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intRemainingMinutes+" Minutes) - actual value ("+strRow5Column5+")","Local");}
	 	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
 		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1052_FTFP10_FTR_PS1_CCP1_VMT_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intFirstPaymentMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History

		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate First Payment
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes - 1;
			int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes + 1;
			if (strRow2Column5.contains("Meter Purchased " + intFirstPaymentMinutes + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intFirstPaymentMinutesMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intFirstPaymentMinutesPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intFirstPaymentMinutes + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_FTFP10_FTR_PS1_CCP1_VMT_PRT_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intFirstPaymentMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
     	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
   		//Validate First Payment
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes -1;
		int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes +1;
		if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutes+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesMinusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intFirstPaymentMinutesPlusOne+" Minutes"))
		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intFirstPaymentMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Cash Payment #9");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Cash Payment #10");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Cash Payment #11");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Cash Payment #12");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Cash Payment #13");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Cash Payment #14");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","4", "CellValue", "$ 0.25");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Exited");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","0", "Row Does Not Exist", "");
   		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_A_FTFP0_RNP_MTIV_gt_MBNB_PS1_CP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History

		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1056_FTFP0_FTNP_MBNB_gt_FTR_gt_MTIV_PS1_CCP1_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intTimePurchased)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intTimePurchasedMinusOne = intTimePurchased -1;
		int intTimePurchasedPlusOne = intTimePurchased +1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedMinusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchasedMinusOne+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_B_FTFP10_RNP_MTIV_gt_MBNB_PS1_CP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		if(strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			int intTimePurchasedMinusOne = intTimePurchased - 1;
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedPlusOne+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedMinusOne+ " Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Meter Purchased "+ intTimePurchased + " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
			String strRowNumber = "1";
			String strColumnNumber = "5";
			int intTimePurchasedMinusOne = intTimePurchased - 1;
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedPlusOne+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedMinusOne+ " Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Meter Purchased "+ intTimePurchased + " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True")) {
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_C_FTFP0_RNP_MTIV_gt_MBNB_PS1_CCP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_D_FTFP10_RNP_MTIV_gt_MBNB_PS1_CCP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History

		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_E_FTFP0_RNP_MTIV_gt_MBNB_PS1_CP1_CCP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 15 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "3";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			intTimePurchased = intTimePurchased - 15;
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_F_FTFP10_RNP_MTIV_gt_MBNB_PS1_CP1_CCP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 15 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Credit card Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "4";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			intTimePurchased = intTimePurchased - 15;
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_G_FTFP0_RNP_MTIV_gt_MBNB_PS1_CGPV1_W2_CP1_UL_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strViolationNumber = objDictionary.get("strViolationNumber");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "3";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked "+strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_H_FTFP0_TU_RNP_MTIV_gt_MBNB_PS1_CGPV1_CP1_UL_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strViolationNumber = objDictionary.get("strViolationNumber");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "3";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked "+strViolationNumber))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True")) 
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1053_I_FTFP0_TU_RNP_MBNB_gt_MBNP_PS1_CGPV1_W4_CP1_UL_BMT_ES1(Map<String, String> objDictionary, int intTimePurchased) 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strViolationNumber = objDictionary.get("strViolationNumber");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 15 Minutes");
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked "+strViolationNumber))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{
				// Validate Purchase Time
				String strRowNumber = "6";
				String strColumnNumber = "5";
				String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
				int intTimePurchase = intTimePurchased - 15;
				int intTimePurchasedPlusOne = intTimePurchase+1;
				if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchase + " Minutes")) 
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchase+ " Minutes)");}
				else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes")) 
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedPlusOne+ " Minutes)");}
				else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchase+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
			}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Handicap Payment");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{
				// Validate Purchase Time
				String strRowNumber = "4";
				String strColumnNumber = "5";
				String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
				int intTimePurchase = intTimePurchased - 15;
				int intTimePurchasedPlusOne = intTimePurchase+1;
				if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchase + " Minutes")) 
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchase+ " Minutes)");}
				else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes")) 
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedPlusOne+ " Minutes)");}
				else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchase+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
			}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Handicap Payment");}
		
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked "+strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationNumber);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True")) 
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1054_FTFP0_FTNP_MBNB_gt_FTR_gt_MTIV_PS1_CCP_PRT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History

		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			int intTimePurchasedMinusOne = intTimePurchased - 1;
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchasedMinusOne+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1055_FTFP0_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History

		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1057_FTFP0_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intTimePurchasedPlusOne = intTimePurchased + 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1057B_FTFP0_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_PRT_ES1(Map<String, String> objDictionary, int intTimePurchased)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 2.00");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intTimePurchasedPlusOne = intTimePurchased + 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1057C_FTFP0_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CP1_PRT_ES1(Map<String, String> objDictionary, int intRemainingFirstPayment)
  	{
		//This Parking Session is not finished.
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intFirstPaymentTime = intRemainingFirstPayment + Integer.parseInt(strMeterIncrementTime);
		int intFirstPaymentTimePlus1 = intFirstPaymentTime + 1;
		int intFirstPaymentTimeMinus1 = intFirstPaymentTime - 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimePlus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimePlus1+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTime+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTime+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Meter Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #4");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #5");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #6");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #7");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Cash Payment #8");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","5", "CellValue", "Meter Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1058_FTFP10_FTNP_MBNB_gt_FTR_gt_MTIV_PS1_CCP1_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intTimePurchased)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		//Validate Purchase Time
  		String strRowNumber = "2";
  		String strColumnNumber = "5";
  		int intTimePurchasedPlusOne = intTimePurchased +1;
  		int intTimePurchasedMinusOne = intTimePurchased -1;
  		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
  		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedMinusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1058B_FTFP10_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_PRT_ES1(Map<String, String> objDictionary, int intTimePurchased)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 2.00");
  		//Validate Purchase Time
  		String strRowNumber = "2";
  		String strColumnNumber = "5";
  		int intTimePurchasedPlusOne = intTimePurchased +1;
  		int intTimePurchasedMinusOne = intTimePurchased -1;
  		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
  		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedMinusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1058C_FTFP10_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CP1_PRT_ES1(Map<String, String> objDictionary, String strMeterIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		String strRemainingFreeTimeFirstPaymentMinutes  = objDictionary.get("strRemainingFreeTimeFirstPaymentMinutes");
		int intPurchasedTimeFirstPayment = Integer.parseInt(strRemainingFreeTimeFirstPaymentMinutes ) + Integer.parseInt(strMeterIncrementTime);
		int intPurchasedTimeFirstPaymentPlusOne = intPurchasedTimeFirstPayment + 1;
		int intPurchasedTimeFirstPaymentMinusOne = intPurchasedTimeFirstPayment - 1;
		int intPurchasedTimeFirstPaymentMinusTwo = intPurchasedTimeFirstPayment - 2;
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		if(strRow2Column5.contains("Meter Purchased "+intPurchasedTimeFirstPayment+" Minutes"))
  		{Reporter.log("The cell value in row 2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intPurchasedTimeFirstPaymentPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row 2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intPurchasedTimeFirstPaymentMinusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intPurchasedTimeFirstPaymentMinusTwo+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intPurchasedTimeFirstPayment+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
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
		String strTimeRemainingBeforeLastPayment = objDictionary.get("strTimeRemainingBeforeLastPayment");
		int intTimeRemainingBeforeLastPaymentPlus1 = Integer.parseInt(strTimeRemainingBeforeLastPayment) + 1;
		String strRow10Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow10Column5", "10", "5");
		if(strRow10Column5.contains("Meter Purchased "+intTimeRemainingBeforeLastPaymentPlus1+" Minutes"))
  		{Reporter.log("The cell value in row 2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow10Column5.contains("Meter Purchased "+strTimeRemainingBeforeLastPayment+" Minutes"))
  		{Reporter.log("The cell value in row 2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+strTimeRemainingBeforeLastPayment+" Minutes) - actual value ("+strRow10Column5+")","Local");}
  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1059_FTFP10_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CP1_ES1(Map<String, String> objDictionary, int intMeterIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		int intMeterIncrementTimePlus1 = intMeterIncrementTime +1;
		int intMeterIncrementTimeMinus1 = intMeterIncrementTime -1;
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
		if(strRow2Column5.contains("Meter Purchased "+intMeterIncrementTimePlus1+" Minutes"))
  		{Reporter.log("The cell value in row 2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
		else if(strRow2Column5.contains("Meter Purchased "+intMeterIncrementTime+" Minutes"))
  		{Reporter.log("The cell value in row 2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intMeterIncrementTimeMinus1+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intMeterIncrementTime+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1060_FTFP10_FTNP_MBNB_gt_FTR_gt_MTIV_PS1_CCP1_ES1(Map<String, String> objDictionary,int intFirstPaymenTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intFirstPaymenTimePlusOne = intFirstPaymenTime + 1;
		int intFirstPaymenTimeMinusOne = intFirstPaymenTime - 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymenTimePlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymenTimePlusOne+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymenTime+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymenTime+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymenTimeMinusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymenTimePlusOne+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intFirstPaymenTime+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1061_FTFP10_FTNP_MBNB_gt_MTIV_gt_FTR_PS1_CCP1_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intTimePurchased)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		//Validate Purchase Time
  		String strRowNumber = "2";
  		String strColumnNumber = "5";
  		int intTimePurchasedPlusOne = intTimePurchased +1;
  		int intTimePurchasedMinusOne = intTimePurchased -1;
  		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
  		if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
  		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchased+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intTimePurchasedMinusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intTimePurchased+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intTimePurchased+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
   		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1062_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strMinutesBeforeFreeParking)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strMinutesBeforeFreeParking + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1063_FTFP0_FDOFF_RTF_MTIV_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1063_A_FTFP0_FDOFF_RTF_MBF_gt_MTIV_PS1_CP1_BMT_CP1_ES1(Map<String, String> objDictionary, int intMeterIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intMeterIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("Exited"))
		{
			int intMeterIncrementTimePlus1 = intMeterIncrementTime + 1;
			if(strRow3Column3.equals("Meter Purchased "+intMeterIncrementTime+" Minutes"))
	  	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column3+")");}
	  	  	else if(strRow3Column3.equals("Meter Purchased "+intMeterIncrementTimePlus1+" Minutes"))
	  	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column3+")");}
	  	  	else
		  	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intMeterIncrementTime+" Minutes - actual value ("+strRow3Column3+")","Local");}
		}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1063_B_FTFP0_FDOFF_RTF_MBF_gt_MTIV_PS1_CP1_BMT_CCP1_ES1(Map<String, String> objDictionary, int intMeterIncrementTime)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intMeterIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("Exited"))
		{
			int intMeterIncrementTimePlus1 = intMeterIncrementTime + 1;
			if(strRow3Column3.equals("Meter Purchased "+intMeterIncrementTime+" Minutes"))
	  	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column3+")");}
	  	  	else if(strRow3Column3.equals("Meter Purchased "+intMeterIncrementTimePlus1+" Minutes"))
	  	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column3+")");}
	  	  	else
		  	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intMeterIncrementTime+" Minutes - actual value ("+strRow3Column3+")","Local");}
		}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1064_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strMinutesBeforeFreeParking)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMinutesBeforeFreeParking+" Minutes");
	  		//Master vs Prod
	  		String strMeterVersion = objDictionary.get("strMeterVersion").replace("\"", "");
	  		if(strMeterVersion.trim().equals("meter-sentry-4.2.2"))
	  		{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
	  		}
	  		else
	  		{
	  	 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
	  		}
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strMinutesBeforeFreeParking+" minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1065_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strMinutesBeforeFreeParking)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMinutesBeforeFreeParking+" Minutes");
  		String strMeterVersion = objDictionary.get("strMeterVersion").replace("\"", "");
  		if(strMeterVersion.trim().equals("meter-sentry-4.2.2"))
  		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		}
  		else
  		{
  	 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		}
  		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1066_FTFP10_FDON_RTF_FTFP_gt_MTIV_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intMinutesBeforeFreeParking)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		int intMinutesBeforeFreeParkingPlus1 = intMinutesBeforeFreeParking + 1;
  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  	  	if(strRow2Column5.equals("Meter Purchased "+intMinutesBeforeFreeParkingPlus1+" Minutes"))
  	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  	  	else if(strRow2Column5.equals("Meter Purchased "+intMinutesBeforeFreeParking+" Minutes"))
  	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  	  	else
	  	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intMinutesBeforeFreeParking+" Minutes - actual value ("+strRow2Column5+")","Local");}
	  	String strMeterVersion = objDictionary.get("strMeterVersion").replace("\"", "");
		if(strMeterVersion.trim().equals("meter-sentry-4.2.2"))
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		}
		else
		{
	 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		}
  	  	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1067_FTFP10_FDON_RTF_FTFP_gt_MBF_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	int intRemainingMinutesPlusOne = intRemainingMinutes +1;
      	int intRemainingMinutesPlusTwo = intRemainingMinutes +2;
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
	  		if(strRow2Column5.contains("Meter Purchased "+intRemainingMinutes+" Minutes"))
		  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Meter Purchased "+intRemainingMinutesPlusOne+" Minutes"))
	  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Meter Purchased "+intRemainingMinutesPlusTwo+" Minutes"))
	  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intRemainingMinutes+" Minutes)","Local");}
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intRemainingMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1068_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intVirtRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History

      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intVirtRemainingMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1069_FTFP10_FDON_RTF_MBF_gt_FTFP_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strFreeTimeFirstPayment)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1070_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intPurchaseMinutes, String strFreeTimeFirstPayment)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
         	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
         	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1071_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intPurchaseMinutes, String strFreeTimeFirstPayment)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
	  	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1072_FTFP10_FDOFF_RTF_FTFP_gt_MTIV_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intPurchaseMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intPurchaseMinutes+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1073_FTFP10_FDOFF_RTF_FTFP_gt_MBF_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intVirtRemainingMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	 		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	 		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1074_FTFP10_FDOFF_RTF_MBF_gt_MTIV_gt_FTFP_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intVirtRemainingMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1075_FTFP10_FDOFF_RTF_MBF_gt_FTFP_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strFreeTimeFirstPayment)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.equals("Cash Payment #1"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1076_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intFirstTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  	  	int intFirstTimeMinutesPlusOne = intFirstTimeMinutes +1;
  	  	int intFirstTimeMinutesMinusOne = intFirstTimeMinutes -1;
  		if(strRow2Column5.contains("Purchased "+intFirstTimeMinutesPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+intFirstTimeMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Purchased "+intFirstTimeMinutesMinusOne+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Purchased "+intFirstTimeMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1077_FTFP0_FDOFF_RTF_MTIV_gt_MBF_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1078_FTFP10_FDON_RTF_MTIV_gt_MBF_gt_FTFP_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intPurchaseTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	 	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intPurchaseTimeMinutesMinusOne = intPurchaseTimeMinutes - 1;
		int intPurchaseTimeMinutesPlusOne = intPurchaseTimeMinutes + 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intPurchaseTimeMinutesMinusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intPurchaseTimeMinutesMinusOne+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intPurchaseTimeMinutes+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intPurchaseTimeMinutes+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intPurchaseTimeMinutesPlusOne+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intPurchaseTimeMinutesPlusOne+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intPurchaseTimeMinutes+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1079_FTFP10_FDON_RTF_MTIV_gt_FTFP_gt_MBF_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intFirstPaymentMinutes, String strFreeTimeFirstPayment)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1080_FTFP10_FDON_RTF_MBF_gt_MTIV_gt_FTFP_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intVirtRemainingMinutes, int intFirstPaymentTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.contains("Credit card Payment"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
	  		String strRowNumber = "2";
			String strColumnNumber = "5";
			int intFirstPaymentTimeMinus1 = intFirstPaymentTime - 1;
			int intFirstPaymentTimePlus1 = intFirstPaymentTime + 1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes)");}
			else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTime+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTime+" Minutes)");}
			else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimePlus1+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimePlus1+" Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
	  		String strRowNumber = "3";
			String strColumnNumber = "5";
			int intFirstPaymentTimeMinus1 = intFirstPaymentTime - 1;
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTime+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTime+" Minutes)");}
			else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes"))
			{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes)");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
      	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1081_FTFP10_FDOFF_RTF_MTIV_gt_MBF_gt_FTFP_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intFirstPaymentTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intFirstPaymentTimeMinus1 = intFirstPaymentTime - 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTime+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTime+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1082_FTFP10_FDOFF_RTF_MTIV_gt_FTFP_gt_MBF_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intFirstPaymentTime, String strFreeTimeFirstPayment)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		//Validate Purchase Time
		String strRowNumber = "2";
		String strColumnNumber = "5";
		int intFirstPaymentTimeMinus1 = intFirstPaymentTime - 1;
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
		if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTime+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTime+" Minutes)");}
		else if(strPurchasedMinutes.equals("Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes"))
		{Reporter.log("The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) equalled (Meter Purchased "+intFirstPaymentTimeMinus1+" Minutes)");}
		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row ("+strRowNumber+") column ("+strColumnNumber+") of the table (Parking Session History) did not equal (Purchased "+intFirstPaymentTime+" Minutes) - actual value ("+strPurchasedMinutes+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1083_FTFP10_FDOFF_RTF_MBF_gt_MTIV_gt_FTFP_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes, int intVirtRemainingMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strInitialParkingSessionRow", "2", "3");
      	if(strInitialPaymentRow.contains("Credit card Payment"))
      	{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	else
      	{
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 10 Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
      	}
      	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1084_AMMOEE_CGPV1_RV_VMVE_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  	  	if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
	  		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1085_AMMOEE_CGPV1_RV_RES_VMVD_ES1_VPSH_VICAE_VIAC_PS1_CP1(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  		String strPaymentRow3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strPaymentRow3", "3", "3");
  		if(strPaymentRow3.equals("Exited"))
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
  		}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	  		driver.navigate().refresh();
	  		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  	      	try {Thread.sleep(1000);}catch (Exception e) {}
  	  		//VICAE: Validate Image Count After Exit
  	  		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
  	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
  	  		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
  	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
  	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
  	      	//VIAC: Validate Images Appear Correctly
  	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  	  	}
	  	driver.quit();
  	}
	public void ValidateViolationHistory_1086_AMMOEE_FTFP0_CGPV1_RV_RES_IP_VMVD_ES1_1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	//VPSH: Validate Parking Session History
  		if (strNumberOfSpots.equals("2"))
    	{
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
	  	  	String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "3", "3");
			if(strRow3Column3.equals("Exited"))
			{
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
		  	}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		  	}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		}
  		else
  		{
  			//This is incorrect, because we can still add a coin payment with python scripts.  Real could would get returned.
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Missed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		}
      	if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void ValidateViolationHistory_1086_AMMOEE_FTFP0_CGPV1_RV_RES_IP_VMVD_ES1_2(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary, null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	//VPSH: Validate Parking Session History
  		if (strNumberOfSpots.equals("2"))
    	{
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
	  	  	String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "3", "3");
			if(strRow3Column3.equals("Exited"))
			{
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
	  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
		  	}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
			  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		  	}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		}
  		else
  		{
  			//This is incorrect, because we can still add a coin payment with python scripts.  Real could would get returned.
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Missed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		}
      	if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1086_PS1_ES1_VPSH(Map<String, String> objDictionary, String strCreditCardIncrementTime)
 	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
 		//Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
	   	String strSpotNumber = objDictionary.get("strSpotNumber");
	   	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
	   	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
	   	//NTPS: Navigate To Parking Session
		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSessionHistory_FindParkingRowByMeterNameAndViolationZero(objDictionary, driver, "Sentry meter","1");
		//VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		//Sometimes there
		String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", "2", "3");
 		if(strPurchasedMinutes.equals("Virt Payment #1"))
 		{
 			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
 			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
 		}
 		else
 		{
 			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Exited");
  		}
 		if(strValidateImageFlag.equals("True"))
		{
 			//Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	  		driver.navigate().refresh();
			//Click Image Link
	  		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	  		try {Thread.sleep(1000);}catch (Exception e) {}
			//VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
			//VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
 		}
		driver.quit();
 	}
	public void ValidateViolationHistory_1087_AMMOEE_FTFP0_CGPV1_RV_RES_IP_VMVD_CP1_VMT_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  		String strRowThreeValue = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRowThreeValue", "3", "3");
	    if(strRowThreeValue.equals("Exited"))
    	{
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
    	}
    	else
    	{
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);

    		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
    	    if(strRow4Column3.equals("Exited"))
        	{
    	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
    	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
        	}
    	    else
    	    {
	    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
	      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
    	    }
      	}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1087_AMMOEE_FTFP0_CGPV1_RV_RES_IP_VMVD_CP1_VMT_ES1(Map<String, String> objDictionary, String strViolationId)
	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  		String strRowThreeValue = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRowThreeValue", "3", "3");
	    if(strRowThreeValue.equals("Exited"))
    	{
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
    	}
    	else
    	{
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
      	}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void ValidateViolationHistory_1088_AMMOEE_FTFP10_CGPV1_RV_RES_IP_VMVD_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  		String strRowThreeValue = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRowThreeValue", "3", "3");
	    if(strRowThreeValue.equals("Exited"))
	    {
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
	    }
	    else
	    {
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
	    	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column3", "4", "3");
			if (strRow4Column3.contains("CSR Rejected "+strViolationId))
			{
		    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
	      		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
	      		
			}
      	}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1088_AMMOEE_FTFP10_CGPV1_RV_RES_IP_VMVD_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSessionHistory_FindParkingRowByMeterNameAndViolationZero(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	 	//Sometimes there 
	 	String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session", "Parking Session History", 1, "strPurchasedMinutes", "2", "3");
  		if(strPurchasedMinutes.contains("Virt Payment #"))
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValueContains", "Virt Payment #");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Exited");
  		
  		}
  		if(strValidateImageFlag.equals("True"))
	  	{
  			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1089_FTFP0_PCUV_CGPV_AV_CP1_VMU(Map<String, String> objDictionary, String strViolationId, String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strEnvironment = objDictionary.get("strEnvironment");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation" + strViolationId);
		if(strEnvironment.equals("PROD"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial grace period exceeded");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved " + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Handicap Payment");}
		String strRow7Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow6Column3", "7", "3");
		if (strRow7Column3.contains("Unlocked " + strViolationId))
		{
			Reporter.log("The cell value in row 7) column (3) of the table (Parking Session History) equalled ("+ strRow7Column3 + ")");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7", "5", "CellValue", "Reason: Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8", "3", "CellValue", "Exited");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8", "3", "CellValue", "Voided " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8", "5", "CellValue", "Reason: Unlocked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 12,"Waiting for 12 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			// VICAE: Validate Image Count After Exit
			String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageDurationExit = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, driver);
			int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance)+ Integer.parseInt(strParkingImageLookAheadEntrance)+ Integer.parseInt(strParkingImageDurationExit);
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, intTotalImageSeconds, 1,"False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1090_FTFP0_PCNUV_CGPV_AV_CP1_VMU_ES1(Map<String, String> objDictionary, String strViolationId, String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strEnvironment = objDictionary.get("strEnvironment");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation" + strViolationId);
		if(strEnvironment.equals("PROD"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial grace period exceeded");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved " + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 12,"Waiting for 12 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			// VICAE: Validate Image Count After Exit
			String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageDurationExit = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, driver);
			int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance)+ Integer.parseInt(strParkingImageLookAheadEntrance)+ Integer.parseInt(strParkingImageDurationExit);
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, intTotalImageSeconds, 1,"False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1090_FTFP0_PCNUV_CGPV_AV_CP1_VMU_ES2(Map<String, String> objDictionary, String strViolationId, String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		if (strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 15 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", "Meter Purchased 15 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 12,"Waiting for 12 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			// VICAE: Validate Image Count After Exit
			String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageDurationExit = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, driver);
			int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance)+ Integer.parseInt(strParkingImageLookAheadEntrance)+ Integer.parseInt(strParkingImageDurationExit);
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, intTotalImageSeconds, 1,"False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void ValidateViolationHistory_1091_AMMOEE_MUOFF_CGPV1_CP1_VMV1_VMPO2_SES_EBS(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
	  	//VPSH: Validate Parking Session History
  		if (strNumberOfSpots.equals("2"))
    	{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Missed "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
    	}
  		else
  		{
  			//This is incorrect, because we can still add a coin payment with python scripts.  Real could would get returned.
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
  	  	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  			//Wait For Images to Load
  	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
  	   		driver.navigate().refresh();
  	  		//Click Image Link
  	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  	      	try {Thread.sleep(1000);}catch (Exception e) {}
  	  		//VICAE: Validate Image Count After Exit
  	  		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
  	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
  	  		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
  	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
  	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
  	      	//VIAC: Validate Images Appear Correctly
  	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  	    }
	  	driver.quit();
  	}
	public void ValidateViolationHistory_1092_AMMOED_MUOFF_CGPV1_CP1_VMV1_VMPO2_SES_EBS(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
	  	//VPSH: Validate Parking Session History
  		if (strNumberOfSpots.equals("2"))
    	{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
    	}
  		else
  		{
  			//This is incorrect, because we can still add a coin payment with python scripts.  Real could would get returned.
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
  	  	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
  			//Wait For Images to Load
  	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
  	   		driver.navigate().refresh();
  	  		//Click Image Link
  	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  	      	try {Thread.sleep(1000);}catch (Exception e) {}
  	  		//VICAE: Validate Image Count After Exit
  	  		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
  	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
  	  		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
  	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
  	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
  	      	//VIAC: Validate Images Appear Correctly
  	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  	    }
	  	driver.quit();
  	}
	public void ValidateViolationHistory_1093_AMMOEE_MUOFF_CGPV1_CP1_VMV1_VMPO2_RES_EBS(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	//VPSH: Validate Parking Session History
  		if (strNumberOfSpots.equals("2"))
    	{
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Missed "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
    	}
  		else
  		{
  			//This is incorrect, because we can still add a coin payment with python scripts.  Real could would get returned.
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
  		}
      	if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void ValidateViolationHistory_1094_AMMOED_MUOFF_CGPV1_CP1_VMV1_VMPO2_RES_EBS(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strNumberOfSpots = clsMeter.METER_GetSYS_NUMBER_OF_SPOTS(objDictionary,null,"Local");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
       	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToViolationHistory(objDictionary, driver, strViolationId);
      	//VPSH: Validate Parking Session History
  		if (strNumberOfSpots.equals("2"))
  		{
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
    	}
  		else
  		{
  			//This is incorrect, because we can still add a coin payment with python scripts.  Real could would get returned.
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
  	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
  		}
      	if(strValidateImageFlag.equals("True"))
	  	{
      		//Wait For Images to Load
      		clsMeter.METER_MeterWaitWithMessage(objDictionary,10, "Waiting for 10 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
       		driver.navigate().refresh();
      		//Click Image Link
          	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
          	try {Thread.sleep(1000);}catch (Exception e) {}
      		//VICAE: Validate Image Count After Exit
      		String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
          	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
      		String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
          	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
          	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"True");
          	//VIAC: Validate Images Appear Correctly
          	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1095_MMNP_VMM_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1096_MMFP_VMM_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1097_MMUP_VMM_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1098_MMM_VMM_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1099_PS1_CP1_MMNP_VMM_ES1_VMM_RMM_VMM(Map<String, String> objDictionary, String strCreditCardIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1103_MMNPS1_VMM_FTFP0_PS2_CPNS_VMT_ES2(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1104_MMFPS1_VMM_FTFP0_PS2_CPNS_VMT_ES2(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1105_MMUPS1_VMM_FTFP0_PS2_CPNS_VMT_ES2(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1106_MMMS1_VMM_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1107_MMUPS2_VMM_FTFP0_PS2_CPNS_VMT_ES2(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes, String strCoinDangleFlag, String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1108_FTFP0_FDOFF_RTF_PS1_PMT_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
  	{
		//THIS IS COMPLETE
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History

	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" Minutes");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1109_FTFP0_FDON_RTF_PS1_PMT_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		String strFamily = objDictionary.get("strFamily");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		String strRow2Column4 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column4", "2", "4");
		if(strRow2Column4.equals("$ 1.00"))
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.01");}
		if(strFamily == null)
		{
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "2", "5");
			int intExpectedRemainingTimeMinutesPlusOne = intExpectedRemainingTimeMinutes + 1;
			if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinutes + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinutesPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intExpectedRemainingTimeMinutes + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Handicap Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1110_FTFP0_FDON_MUON_MUT5_RTF_CGPV_W4_PMT_VMT_ES1(Map<String, String> objDictionary, String strViolationId,int intExpectedRemainingTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 30.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "3", "5");
		int intExpectedRemainingTimeMinutesPlus1 = intExpectedRemainingTimeMinutes +1;
		int intExpectedRemainingTimeMinutesPlus2 = intExpectedRemainingTimeMinutes +2;
		int intExpectedRemainingTimeMinutesMinus1 = intExpectedRemainingTimeMinutes -1;
		if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutesPlus2+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
		else if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutesPlus1+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutesMinus1+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Purchased "+intExpectedRemainingTimeMinutes+" Minutes) - actual value ("+strRow3Column5+")","Local");}
  	  	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "4", "3");
  		if(strRow4Column3.contains("Unlocked "+strViolationId))
  		{
  			Reporter.log("The cell value in row (4) column (3) of the table (Parking Session History) equalled ("+strRow4Column3+")");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Reason: Unlocked");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
  		}
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1111_FTFP0_TUON_FDON_MUON_MUT5_RTF_CGPV_W4_PMT_VMT_ES1(Map<String, String> objDictionary, String strViolationId, int intExpectedRemainingTimeMinutes)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 30.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "3", "5");
		int intExpectedRemainingTimeMinutesPlus1 = intExpectedRemainingTimeMinutes +1;
		int intExpectedRemainingTimeMinutesMinus1 = intExpectedRemainingTimeMinutes -1;
  		if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutesPlus1+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutes+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Purchased "+intExpectedRemainingTimeMinutesMinus1+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Purchased "+intExpectedRemainingTimeMinutes+" Minutes) - actual value ("+strRow3Column5+")","Local");}
  	  	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "4", "3");
  		if(strRow4Column3.contains("Unlocked "+strViolationId))
  		{
  			Reporter.log("The cell value in row (4) column (3) of the table (Parking Session History) equalled ("+strRow4Column3+")");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Reason: Unlocked");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
  		}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1112_FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_PMT_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes, String strFreeTimeFirstPayment)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	int intExpectedRemainingTimeMinutesMinusFreeTimeFirstPayment = intExpectedRemainingTimeMinutes - Integer.parseInt(strFreeTimeFirstPayment);
	  	int intExpectedRemainingTimeMinutesMinusFreeTimeFirstPaymentPlusOne = intExpectedRemainingTimeMinutesMinusFreeTimeFirstPayment + 1;
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 2.00");
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  		if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinutesMinusFreeTimeFirstPayment+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinutesMinusFreeTimeFirstPaymentPlusOne+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTimeMinutesMinusFreeTimeFirstPayment+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1113_FTFP0_TUOFF_MUON_PCUV_CGPV_CCP1_PMT_VMU(Map<String, String> objDictionary, String strViolationId, String strMaximumDuration,String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation" + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace PeriodExceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		double dblAmount = Double.parseDouble(strMaximumDuration) / Double.parseDouble(strCreditCardIncrementTime)* .25;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", dFormat.format(dblAmount));
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
  		if(strRow4Column3.contains("Unlocked " + strViolationId))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected " + strViolationId);
		}
		if (strValidateImageFlag.equals("True")) 
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 12,"Waiting for 12 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			// VICAE: Validate Image Count After Exit
			String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageDurationExit = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, driver);
			int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance)+ Integer.parseInt(strParkingImageLookAheadEntrance)	+ Integer.parseInt(strParkingImageDurationExit);
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, intTotalImageSeconds, 1,"False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1114_FTFP0_TUON_MUON_PCUV_CGPV_CCP1_PMT_VMU(Map<String, String> objDictionary, String strViolationId, String strMaximumDuration, String strCreditCardIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{
			try {Thread.sleep(30000);} catch (Exception e) {}
			driver.navigate().refresh();
		}
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
  		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
      	double dblAmount = Double.parseDouble(strMaximumDuration)/Double.parseDouble(strCreditCardIncrementTime) * .25;
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", dFormat.format(dblAmount));
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strMaximumDuration+" Minutes");
      	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
  		if(strRow4Column3.equals("Unlocked " + strViolationId))
  		{
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked "+strViolationId);
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked "+strViolationId);
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Reason: Unlocked");
	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationId);
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
	  	}
	  	if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1115_FTFP0_TUON_MUON_PCUV_RSMDNEMT_CGPV_CCP1_PMT_VMU(Map<String, String> objDictionary, String strViolationId, String strMaximumDuration, String strCreditCardIncrementTime)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	String strEnvironment = objDictionary.get("strEnvironment");
		if(strEnvironment.equals("QA"))
		{
			try {Thread.sleep(30000);} catch (Exception e) {}
			driver.navigate().refresh();
		}
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 2.00");
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 120 Minutes");
      	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked " + strViolationId))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked "+strViolationId);
	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Unlocked "+strViolationId);
	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Reason: Unlocked");
	  	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationId);
	      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary,driver);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	  	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1117_TU_CGPV1_W10_VTU10_CP1_VTU6_CP1_VTU2_CP1_VMT_ES1(Map<String, String> objDictionary)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	String strGracePeriodViolationId = objDictionary.get("strGracePeriodViolationId");if(strGracePeriodViolationId == null){strGracePeriodViolationId = "";}
      	String strTimeExpiredViolation = objDictionary.get("strTimeExpiredViolation");if(strTimeExpiredViolation == null){strTimeExpiredViolation = "";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValueContains", "Violation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Cash Payment #3");
		String strRow6Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow6Column3", "6", "3");
		if(strRow6Column3.contains("Unlocked "))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValueContains", "Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValueContains", "CSR Rejected");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValueContains", "Violation");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValueContains", "Violation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValueContains", "Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValueContains", "CSR Rejected");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValueContains", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValueContains", "Missed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValueContains", "CSR Rejected");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1118_HDCP_FTFP0_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1119_HDCP_FTFP0_PO_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strCoinDangleFlag,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		if (strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Handicap");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1120_HDCP_PS1_CP1_VMT_BMT_VMT_ES1(Map<String, String> objDictionary, String strMeterIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1121_HDCP_FTFP0_PS1_CP1_PMT_LPRM_e_MTIV_VMT_ES1(Map<String, String> objDictionary, String strMaximumDuration, String strMeterIncrementTimeMins)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		int intCashPaymentCounter = 1;
		int intPaymentCounter = 1;
		// ValidateParkingSessionHistoryAndImages
		Double dblCoinIncrementValue = Double.parseDouble(strMaximumDuration)/ Double.parseDouble(strMeterIncrementTimeMins);
		int intNbrCoinRequiredForMaxTime = (int) Math.ceil(dblCoinIncrementValue);
		while (intCashPaymentCounter < intNbrCoinRequiredForMaxTime)
		{
			int intRowNumber = intCashPaymentCounter + 1;
//			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "3", "CellValue", "Handicap" + intPaymentCounter);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "3", "CellValue", "Cash Payment #"+intCashPaymentCounter);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "4", "CellValue", "$ 0.25");
			String strFamily = objDictionary.get("strFamily");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
			intCashPaymentCounter++;
			intPaymentCounter++;
		}
		int intExitRowNumber = intCashPaymentCounter + 2;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 3;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intExitRowNumber), "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowDoesntExistRowNumber), "0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1122_HDCP_FTFP0_PS1_CP1_VMT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strMeterIncrementTimeMins,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1123_HDCP_FTFP0_PS1_CP1_VMT_PRT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strMeterIncrementTimeMins, String strMaximumDuration)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		double dblMaximumDuration = Double.parseDouble(strMaximumDuration);
		DecimalFormat dFormat = new DecimalFormat("$ 0.00");
		String strCreditCardRemainingAmount = SENTRYLINK_CalculateCreditCardRemainingAmount(dblMaximumDuration,strMeterIncrementTimeMins, dFormat);
		int intCreditCardPurchaseTime = (int) dblMaximumDuration - Integer.parseInt(strMeterIncrementTimeMins);
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", strCreditCardRemainingAmount);}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");}
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + intCreditCardPurchaseTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}

	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVN_PSVWL_VVMT_ES1_VPS(Map<String, String> objDictionary, int intExpectedRemainingTime, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History

		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		int intExpectedRemainingTimePlus1 = intExpectedRemainingTime + 1;
		int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime - 1;
		if (strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Virt Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimePlus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTime + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intExpectedRemainingTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Virt Payment #1");
			String strRow1Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "1", "5");
			if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTimePlus1 + " Minutes"))
			{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTime + " Minutes"))
			{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intExpectedRemainingTime + " Minutes) - actual value (" + strRow1Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Parked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_M2002_RSVN_PSVWIL_CGPV1_VMT_ES1_VPSH(Map<String, String> objDictionary, String strViolationId,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Missed "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVN_PSVWL_VVMT_ES1_VPSH(Map<String, String> objDictionary, int intExpectedRemainingTime, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		int intExpectedRemainingTimePlus1 = intExpectedRemainingTime + 1;
		int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime - 1;
		if (strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Virt Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimePlus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTime + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intExpectedRemainingTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Virt Payment #1");
			String strRow1Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "1", "5");
			if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTimePlus1 + " Minutes"))
			{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTime + " Minutes"))
			{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intExpectedRemainingTime + " Minutes) - actual value (" + strRow1Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Parked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVN_PS1_CGPV1_ALPTPS_VMT_ES1_VPSH(Map<String, String> objDictionary, String strViolationId, int intExpectedRemainingTime,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History

	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #1");
		String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "3", "5");
  		int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime -1;
  		int intExpectedRemainingTimePlus1 = intExpectedRemainingTime +1;
  		if(strRow3Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Meter Purchased "+intExpectedRemainingTimePlus1+" Minutes"))
  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow3Column5+")","Local");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVN_FTRSVN_PSWL_WFTE_VMT_VPSH(Map<String, String> objDictionary, String strReservationTimeMinutes, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "1", "3");
		if(strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int strReservationTimeMinutesPlus1 = Integer.parseInt(strReservationTimeMinutes) + 1;
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strReservationTimeMinutes) - 1;
			if (strRow2Column5.contains("Meter Purchased " + strReservationTimeMinutesPlus1 + " Minutes")) {Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + strReservationTimeMinutes + " Minutes")) {Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + strReservationTimeMinutes + " Minutes")) {Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinus1 + " Minutes")) {Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ strReservationTimeMinutes + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Virt Payment #1");
			String strRow1Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow1Column5", "1", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strReservationTimeMinutes) - 1;
			if (strRow1Column5.contains("Meter Purchased " + strReservationTimeMinutes + " Minutes")) {Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else if (strRow1Column5.contains("Meter Purchased " + strReservationTimeMinutes + " Minutes")) {Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else if (strRow1Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinus1 + " Minutes")) {Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+ strRow1Column5 + ")");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ strReservationTimeMinutes + " Minutes) - actual value (" + strRow1Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");

		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVNTR_PSVWL_VMT_ES1_VPSH(Map<String, String> objDictionary, int intExpectedRemainingTime,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "1", "3");
	  	int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime -1;
		if(strRow1Column3.equals("Parked"))
	  	{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
	  		if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
		  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow2Column5+")","Local");}
	  	}
	  	else
	  	{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Virt Payment #1");
	  		String strRow1Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "1", "5");
		  	if(strRow1Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
		  	{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+strRow1Column5+")");}
	  		else if(strRow1Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow1Column5+")");}
	  		else
	  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow1Column5+")","Local");}
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVNTR_PSVWL_VMT_LMV_VV_ES1_VPSH(Map<String, String> objDictionary, int intExpectedRemainingTime,  String strSpotNumber, String strViolationId)
  	{
		//RSVNTR_CS_PSVWL_VMT_LMV_VV_ES1_VPSH
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "1", "3");
	  	int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime -1;
		if(strRow1Column3.equals("Parked"))
	  	{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
	  		if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
		  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow2Column5+")","Local");}
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
	  	}
	  	else
	  	{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Virt Payment #1");
	  		String strRow1Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "1", "5");
		  	if(strRow1Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
		  	{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+strRow1Column5+")");}
	  		else if(strRow1Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
	  		{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+strRow1Column5+")");}
	  		else
	  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow1Column5+")","Local");}
		  	String strRow2Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "2", "3");
		  	if(strRow2Column3.equals("Parked"))
		  	{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		  	}
		  	else
		  	{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Rejected "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Parked");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		  	}
		}
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVNTR_TU_PSVWL_VMT_LMV_VV_ULCP_ES1_VPSH(Map<String, String> objDictionary, int intExpectedRemainingTime,  String strSpotNumber, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "1", "3");
	  	int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime -1;
		if(strRow1Column3.equals("Parked"))
	  	{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
	  		if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
		  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
	  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
	  		else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow2Column5+")","Local");}
	  	}
	  	else
	  	{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Virt Payment #1");
	  		String strRow2Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "2", "3");
	  		if(strRow2Column3.equals("Parked"))
		  	{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Cash Payment #1");
		   		String strRow4Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column5", "4", "5");
			  	if(strRow4Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
				{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled ("+strRow4Column5+")");}
		  		else if(strRow4Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
		  		{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled ("+strRow4Column5+")");}
		  		else
		  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (4) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow4Column5+")","Local");}
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Voided "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationId);
			}
	  		else
	  		{
	  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #1");
		   		String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column5", "", "5");
			  	if(strRow3Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
				{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
		  		else if(strRow3Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
		  		{Reporter.log("The cell value in row (4) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
		  		else
		  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow3Column5+")","Local");}
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Parked");
	  		}
	  	}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVNTR_CS_PSVWL_VMT_LRTE_VCP_ES1_VPSH(Map<String, String> objDictionary, int intExpectedRemainingTime,  String strSpotNumber)
  	{
		//RSVNTR_CS_PSVWL_VMT_LMV_VV_ES1_VPSH
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	int intExpectedRemainingTimeMinus1 = intExpectedRemainingTime -1;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Virt Payment #1");
  		String strRow1Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "1", "5");
	  	if(strRow1Column5.contains("Meter Purchased "+intExpectedRemainingTime+" Minutes"))
	  	{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+strRow1Column5+")");}
  		else if(strRow1Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
  		{Reporter.log("The cell value in row (1) column (5) of the table (Parking Session History) equalled ("+strRow1Column5+")");}
  		else
  		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (1) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+intExpectedRemainingTime+" Minutes) - actual value ("+strRow1Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Recognized as Concierge ");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Mps account Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Concierge Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_RSVN_FTRSVN_PSWL_WFTE_VMTAP_VPS_VPSH(Map<String, String> objDictionary, String strReservationTimeMinutes,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Virt Payment #1");
		String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "2", "5");
  		int intExpectedRemainingTimeMinus1 = Integer.parseInt(strReservationTimeMinutes) -1;
		if(strRow2Column5.contains("Meter Purchased "+strReservationTimeMinutes+" Minutes"))
	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else if(strRow2Column5.contains("Meter Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow2Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+strReservationTimeMinutes+" Minutes) - actual value ("+strRow2Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PSWL_VMT_VPSH(Map<String, String> objDictionary, String strMeterIncrementTime,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Recognized as Concierge");
		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "3", "3");
		if(strRow3Column3.equals("Mps account Payment #1"))
		{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Concierge Purchased "+strMeterIncrementTime+" Minutes");
		  	String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
		  	if(strRow4Column3.equals("Exited"))
			{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
			}
		  	else
		  	{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValueContains", "Violation");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValueContains", "Unlocked");
			  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValueContains", "CSR Rejected");
			  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		  	}
		}
		else
		{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValueContains", "Violation");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Mps account Payment #1");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Concierge Purchased "+strMeterIncrementTime+" Minutes");
		  	String strRow5Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow5Column3", "5", "3");
		  	if(strRow5Column3.contains("Unlocked"))
			{
			  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValueContains", "Unlocked");
			  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValueContains", "CSR Rejected");
			  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
			}
		  	else
		  	{
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValueContains", "Missed");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValueContains", "CSR Rejected");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValueContains", "System Failure");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValueContains", "Voided");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
		  	}
		}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_FTR_PSWL_WFTE_ALPTPS_VMT_VPSH(Map<String, String> objDictionary, String strViolationId, String strMeterIncrementTime,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		String strRowColumn3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "3");
		if(strRowColumn3.contains("Violation"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation " + strViolationId);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Voided " + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PSWL_CGPV1_ES1_ALPTPS_VPSH(Map<String, String> objDictionary, String strViolationId, String strMeterIncrementTime,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		String strRowColumn3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "3");
		if(strRowColumn3.contains("Violation"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int intExpectedRemainingTimePlus1 = Integer.parseInt(strMeterIncrementTime) + 1;
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimePlus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation " + strViolationId);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Recognized as Concierge AFTER EXIT");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PSWL_CGPV1_ES1_ALPTPS_AV_VPSH(Map<String, String> objDictionary, String strViolationId, String strMeterIncrementTime,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		String strRowColumn3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "3");
		if(strRowColumn3.contains("Violation"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation " + strViolationId);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Missed " + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+ strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Recognized as Concierge AFTER EXIT");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PSWL_CGPV1_ES1_ALPTPSD_AV_VPSH(Map<String, String> objDictionary, String strViolationId, String strMeterIncrementTime,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		String strRowColumn3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "3");
		if(strRowColumn3.contains("Violation"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int intExpectedRemainingTimeMinus1 = Integer.parseInt(strMeterIncrementTime) - 1;
			if (strRow2Column5.contains("Concierge Purchased " + strMeterIncrementTime + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Concierge Purchased " + intExpectedRemainingTimeMinus1 + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+ strMeterIncrementTime + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation " + strViolationId);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Missed " + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+ strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Recognized as Concierge AFTER EXIT");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PSWL_RAC_ESDIG(Map<String, String> objDictionary, String strMeterIncrementTime,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Recognized as Concierge");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PSWL_SLPR_VMT_VPSH(Map<String, String> objDictionary,String strMeterIncrementTime,String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", strSpotNumber);
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Recognized as Concierge");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Concierge Purchased 15 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_FTR_RSVN_PSWL_WFTE_VMT_VPSH(Map<String, String> objDictionary, String strReservationTimeMinutes,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History

	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Recognized as Concierge");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
		String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column5", "3", "5");
  		int intExpectedRemainingTimeMinus1 = Integer.parseInt(strReservationTimeMinutes) -1;
		if(strRow3Column5.contains("Concierge Purchased "+strReservationTimeMinutes+" Minutes"))
	  	{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else if(strRow3Column5.contains("Concierge Purchased "+intExpectedRemainingTimeMinus1+" Minutes"))
  		{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+strRow3Column5+")");}
  		else
		{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Concierge Purchased "+strReservationTimeMinutes+" Minutes) - actual value ("+strRow3Column5+")","Local");}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if(strValidateImageFlag.equals("True"))
	  	{
  	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,5, "Waiting for 5 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PS1_GPV1_ALPTPS_VMT_VPS(Map<String, String> objDictionary, String strMeterIncrementTime,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Concierge Purchased "+strMeterIncrementTime+" Minutes");
		String strViolationNumber = objDictionary.get("strViolationNumber");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationNumber);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Recognized as Concierge");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Voided "+strViolationNumber);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_A_CS_TU_PS1_GPV1_ALPTPS_VMT_VPSH(Map<String, String> objDictionary, String strMeterIncrementTime,  String strSpotNumber)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Concierge Purchased "+strMeterIncrementTime+" Minutes");
		String strViolationNumber = objDictionary.get("strViolationNumber");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationNumber);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Recognized as Concierge");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Voided "+strViolationNumber);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_CS_PS1_GPV1_WFFACTNVT_ALPTPS_VMT_VPSH(Map<String, String> objDictionary, String strMeterIncrementTime,  String strSpotNumber, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "False";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter",strSpotNumber);
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	String strRow2Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow2Column3", "2", "3");
		if(strRow2Column3.equals("Mps account Payment #1"))
		{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Concierge Purchased "+strMeterIncrementTime+" Minutes");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationId);
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Recognized as Concierge");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Voided "+strViolationId);
		}
		else
		{
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Recognized as Concierge");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Voided "+strViolationId);
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Mps account Payment #1");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Concierge Purchased "+strMeterIncrementTime+" Minutes");
		}
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1124_HDCP_FTFP0_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	String strFamily = objDictionary.get("strFamily");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
  		if(strValidateImageFlag.equals("True"))
	  	{
  			driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	  		//VICAE: Validate Image Count After Exit
	  		clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,0,2,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1125_HDCP_FTFP0_PS1_CCP1_VMT_BMT_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strFamily = objDictionary.get("strFamily");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1126_HDCP_FTFP0_PS1_CCP1_VMT_CP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes,String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Cash Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1127_HDCP_FTFP10_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1128_HDCP_FTFP10_PS1_CP1_VMT_CCP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strInitialParkingSessionRow", "2", "5");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		if (strInitialPaymentRow.equals("Handicap Payment")) 
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Virt Payment #2");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased 10 Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Credit card Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "4", "CellValue", "$ 1.00");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5", "CellValue","Meter Purchased " + intCreditCardMinimumPurchaseMinutes + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"6", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Virt Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.17");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5", "CellValue","Meter Purchased 10 Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Credit card Payment #3");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "4", "CellValue", "$ 1.00");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5", "CellValue","Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"6", "0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1129_HDCP_FTFP10_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4", "CellValue","$ 0.17");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 0.09");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1130_HDCP_FTFP0_FTR_MTIV_gt_RFT_PS1_GPV_UL_CCP1_PMT_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strViolationNumber = objDictionary.get("strViolationNumber");
		String strFamily =  objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation " + strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Credit card Payment #1");
		if(strFamily == null)
		{
			// Validate First Payment
			String strRow3Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "3", "5");
			String strMaximumDuration = objDictionary.get("strMaximumDuration");
			int intMaximumDurationMinusOne = Integer.parseInt(strMaximumDuration) - 1;
			if (strRow3Column5.contains("Meter Purchased " + intMaximumDurationMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow3Column5 + ")");}
			else if (strRow3Column5.contains("Meter Purchased " + strMaximumDuration + " Minutes"))
			{Reporter.log("The cell value in row (3) column (5) of the table (Parking Session History) equalled ("+ strRow3Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (3) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ strMaximumDuration + " Minutes) - actual value (" + strRow3Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		
		String strRow4Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow4Column3", "4", "3");
		if(strRow4Column3.equals("Unlocked " + strViolationNumber))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Unlocked " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Reason: Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "CSR Rejected " + strViolationNumber);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Automated Process Violation was unlocked (admin rejection)");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1131_HDCP_FTFP10_FTR_RFT_gt_MTIV_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		int intTimePurchasedPlusOne = intTimePurchased + 1;
		String strInitialPaymentRow = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strInitialParkingSessionRow", "2", "5");
		if (strInitialPaymentRow.equals("Handicap Payment"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{
				// Validate Purchase Time
				String strRowNumber = "2";
				String strColumnNumber = "5";
				String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
				if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else
				{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased "+ intTimePurchased + " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
			}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Virt Payment #2");
			if(strFamily == null)
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", "Meter Purchased 10 Minutes");}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1", "3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "3", "CellValue", "Virt Payment #2");
			if(strFamily == null)
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.17");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", "Meter Purchased 10 Minutes");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "4", "CellValue", "$ 0.09");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", strFamily+" Payment");
			}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "3", "CellValue", "Cash Payment #2");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "4", "CellValue", "$ 0.25");
			if(strFamily == null)
			{
				// Validate Purchase Time
				String strRowNumber = "3";
				String strColumnNumber = "5";
				String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
				if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
				{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
				else
				{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased "+ intTimePurchased + " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}

			}
			else
			{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", strFamily+" Payment");}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5", "0", "Row Does Not Exist", "");
		}
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 5,"Waiting for 5 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
			driver.quit();
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1132_HDCP_FTFP10_FTR_RFT_gt_MTIV_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + intTimePurchased + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2", "5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3",	"3", "CellValue", "Virt Payment #2");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3", "5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1133_HDCP_FTFP10_FTR_PS1_CCP1_VMT_CP1_VMT_ES1(Map<String, String> objDictionary, String strCreditCardIncrementTime, int intFirstPaymentMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History

		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate First Payment
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column5", "2", "5");
			int intFirstPaymentMinutesMinusOne = intFirstPaymentMinutes - 1;
			int intFirstPaymentMinutesPlusOne = intFirstPaymentMinutes + 1;
			if (strRow2Column5.contains("Meter Purchased " + intFirstPaymentMinutes + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intFirstPaymentMinutesMinusOne + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intFirstPaymentMinutesPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intFirstPaymentMinutes + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Virt Payment #2");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Meter Purchased 10 Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","4", "CellValue", "$ 0.25");
		if(strFamily == null){clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1134_HDCP_FTFP0_RNP_MBNB_gt_MTIV_gt_PS1_CP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "2";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1135_HDCP_FTFP0_RNP_MTIV_gt_MBNB_PS1_CP1_CCP1_ES1(Map<String, String> objDictionary, int intTimePurchased)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 15 Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", " Credit card Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			// Validate Purchase Time
			String strRowNumber = "3";
			String strColumnNumber = "5";
			String strPurchasedMinutes = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strPurchasedMinutes", strRowNumber, strColumnNumber);
			intTimePurchased = intTimePurchased - 15;
			int intTimePurchasedPlusOne = intTimePurchased + 1;
			if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchased + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else if (strPurchasedMinutes.equals("Meter Purchased " + intTimePurchasedPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) equalled (Meter Purchased " + intTimePurchased+ " Minutes)");}
			else {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (" + strRowNumber + ") column (" + strColumnNumber+ ") of the table (Parking Session History) did not equal (Purchased " + intTimePurchased+ " Minutes) - actual value (" + strPurchasedMinutes + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1136_HDCP_FTFP0_FDON_RTF_MTIV_gt_MBF_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, String strMinutesBeforeFreeParking)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// NTPS: Navigate To Parking Session
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// Function Variable
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased " + strMinutesBeforeFreeParking + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1137_HDCP_FTFP0_PCUV_CGPV_AV_CP1_VMU(Map<String, String> objDictionary, String strViolationId, String strCreditCardIncrementTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		String strFamily = objDictionary.get("strFamily");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation" + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved " + strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
		if(strFamily == null)
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased " + strCreditCardIncrementTime + " Minutes");}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", strFamily+" Payment");}
		String strRow7Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow4Column5", "7", "3");
		if (strRow7Column3.contains("Unlocked " + strViolationId))
		{
			Reporter.log("The cell value in row (7) column (3) of the table (Parking Session History) equalled ("+ strRow7Column3 + ")");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7", "5", "CellValue", "Reason: Unlocked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8", "3", "CellValue", "Exited");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7", "3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8", "3", "CellValue", "Unlocked " + strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8", "5", "CellValue", "Reason: Unlocked");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 12,"Waiting for 12 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			// VICAE: Validate Image Count After Exit
			String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary,driver);
			String strParkingImageDurationExit = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, driver);
			int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance)+ Integer.parseInt(strParkingImageLookAheadEntrance)+ Integer.parseInt(strParkingImageDurationExit);
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, intTotalImageSeconds, 1,"False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_1138_HDCP_FTFP0_FDON_RTF_PS1_PMT_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		String strFamily = objDictionary.get("strFamily");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 1.00");
		if(strFamily == null)
		{
			String strRow2Column5 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "2", "5");
			int intExpectedRemainingTimeMinutesPlusOne = intExpectedRemainingTimeMinutes + 1;
			if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinutes + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else if (strRow2Column5.contains("Meter Purchased " + intExpectedRemainingTimeMinutesPlusOne + " Minutes"))
			{Reporter.log("The cell value in row (2) column (5) of the table (Parking Session History) equalled ("+ strRow2Column5 + ")");}
			else
			{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, driver,"The cell value in row (2) column (5) of the table (Parking Session History) did not equal (Meter Purchased "+ intExpectedRemainingTimeMinutes + " Minutes) - actual value (" + strRow2Column5 + ")","Local");}
		}
		else
		{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", strFamily+" Payment");}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		driver.quit();
	}
	// *****************************************************************************************************************************************************************************
	// MAX DURATION EXPIRED
	// *****************************************************************************************************************************************************************************
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_2320_FTFP0_PS1_MTCP1_VMTAP1_WFVTE1_CP_POS2_VSDA2_EBS_VPSH_1(Map<String, String> objDictionary, String strViolationId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 3 Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Maxtime Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Missed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "CSR Rejected "+strViolationId);
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_2320_FTFP0_PS1_MTCP1_VMTAP1_WFVTE1_CP_POS2_VSDA2_EBS_VPSH_2(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if (strValidateImageFlag == null) {strValidateImageFlag = "False";}
		String strFamily = objDictionary.get("strFamily");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		String strRow1Column3 = clsCommonWeb.StoreTableValue(objDictionary, driver, "Parking Session","Parking Session History", 1, "strRow2Column3", "1", "3");
		if(strRow1Column3.equals("Parked"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Meter Purchased 3 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","4", "CellValue", "$ 0.25");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","5", "CellValue", "Meter Purchased 3 Minutes");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Parked");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
		}
		driver.quit();
	}
	
	// *****************************************************************************************************************************************************************************
	// ROYAL OAK PARKING SESSIONS
	// *****************************************************************************************************************************************************************************
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3001_RO_FTFP5_PS1_FTFP5_w_MIT12_gt_MRFRB5_CP1_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3002_RO_FTFP5_PS1_FTFP5_w_MIT12_gt_MRFRB15_CP1_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3003_RO_FTFP5_PS1_CP1_VMT_BMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Cash Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","4","CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5","CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"6","0","Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3002_RO_FTFP5_PS1_CP1_PMT_VMT_ES1(Map<String, String> objDictionary, String strMaximumDuration, String strMeterIncrementTimeMins)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		int intCashPaymentCounter = 0;
		int intPaymentCounter = 1;
		// ValidateParkingSessionHistoryAndImages
		Double dblCoinIncrementValue = Double.parseDouble(strMaximumDuration)/ Double.parseDouble(strMeterIncrementTimeMins);
		int intNbrCoinRequiredForMaxTime = (int) Math.ceil(dblCoinIncrementValue);
		while (intCashPaymentCounter < intNbrCoinRequiredForMaxTime)
		{
			int intRowNumber = intCashPaymentCounter + 2;
			if(intRowNumber == 3)
			{
				//Free Time First Payment
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "3", "CellValue", "Virt Payment #" + intPaymentCounter);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "4", "CellValue", "$ 0.10");
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "5", "CellValue","Meter Purchased 5 Minutes");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "3", "CellValue", "Cash Payment #" + intPaymentCounter);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber), "4", "CellValue", "$ 0.25");
				String strFamily = objDictionary.get("strFamily");
				if(strFamily == null)
				{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowNumber),"5", "CellValue","Meter Purchased " + strMeterIncrementTimeMins + " Minutes");}
				else
				{clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, Integer.toString(intRowNumber),"5", "CellValue", strFamily+" Payment");}
			}
			intCashPaymentCounter++;
			intPaymentCounter++;
		}
		int intExitRowNumber = intCashPaymentCounter + 2;
		int intRowDoesntExistRowNumber = intCashPaymentCounter + 3;
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intExitRowNumber), "3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,Integer.toString(intRowDoesntExistRowNumber), "0", "Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}


	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3005_RO_FTFP5_PS1_FTFP5_w_MIT12_gt_MRFRB5_CP1_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 3.74");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();
	}

	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3007_RO_FTFP5_PS1_FTFP5_w_MIT12_gt_MRFRB5_CC1_VMT_ES1(Map<String, String> objDictionary,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
 // 		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String expectedTime = objDictionary.get("expectedTime");

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3008_RO_FTFP5_PS1_FTFP5_w_MIT10_gt_MRFRB5_CC1_VMT_ES1(Map<String, String> objDictionary,int intCreditCardMinimumPurchaseMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
 // 		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String expectedTime = objDictionary.get("expectedTime");

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+intCreditCardMinimumPurchaseMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.12");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3009_RO_FTFP5_PS1_FTFP5_w_MIT10_gt_MRFRB5_CC1_VMT_ES1(Map<String, String> objDictionary,int totalMaximumTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
 // 		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String expectedTime = objDictionary.get("expectedTime");

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 3.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+totalMaximumTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();

	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_M3009A_RO_FTFP5_PS1_FTFP5_w_MIT2_gt_MRFRB2_CC1_VMT_ES1_VPSH_VICAE_VIAC(Map<String, String> objDictionary,int totalMaximumTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
 // 		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String expectedTime = objDictionary.get("expectedTime");

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+expectedTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		driver.quit();

	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3010_RO_FTFP5_PS1_FTFP5_w_MIT10_gt_MRFRB5_CC1_VMT_ES1(Map<String, String> objDictionary,int totalMaximumTime)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  	    String strViolationId = objDictionary.get("strViolationId");
  		String expectedTime = objDictionary.get("expectedTime");

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Violation "+ strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 30.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Initial Grace Period Exceeded");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+expectedTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","3","CellValue", "Unlocked "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8","0","Row Does Not Exist", "");
		driver.quit();





	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3012_RO_FTFP5_PS1_FTFP5_w_MIT12_gt_MRFRB5_CP1_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Violation");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 30.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strMeterIncrementTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","3","CellValue", "Unlocked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"9","0","Row Does Not Exist", "");
		driver.quit();
	}
	// *****************************************************************************************************************************************************************************
	// ROYAL OAK PARKING SESSIONS
	// *****************************************************************************************************************************************************************************
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3000_RO_FTR_FTFP5_PS1_CP1_VMT_ES1(Map<String, String> objDictionary, int intExpectedRemainingTimeMinutes)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Cash Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.25");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","5","CellValue", "Meter Purchased "+intExpectedRemainingTimeMinutes+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3000_RO_FTR_FTFP5_PS1_CCP1_VMT_ES1(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strSpotNumber = objDictionary.get("strSpotNumber");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
  		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if (strValidateImageFlag == null) {strValidateImageFlag = "True";}
		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.10");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","0","Row Does Not Exist", "");
		if (strValidateImageFlag.equals("True"))
		{
			// Wait For Images to Load
			clsMeter.METER_MeterWaitWithMessage(objDictionary, 10,"Waiting for 10 seconds for Images to load to Sentry Link-Spot" + strSpotNumber);
			driver.navigate().refresh();
			// Click Image Link
			clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
			try {Thread.sleep(1000);} catch (Exception e) {}
			// VICAE: Validate Image Count After Exit
			clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary, driver, 0, 2, "False");
			// VIAC: Validate Images Appear Correctly
			clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
		}
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3001_RO_RTF_FTFP5_PS1_CGPV1_CCP(Map<String, String> objDictionary, String strViolationNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
 // 		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String expectedTime = objDictionary.get("expectedTime");
  		String totalMaximumTime = "30";
  		String totalMaximumTime2 = "15";
  		String totalMaximumTime3 = "1";

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3", "CellValue", "Violation" + strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 30.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "CSR Approved "+strViolationNumber);
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Automated Process");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+totalMaximumTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","3","CellValue", "Virt Payment #2");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","4","CellValue", "$ 0.12");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"4","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","3","CellValue", "Unlocked " +strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","5","CellValue", "Reason: Unlocked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"6","3","CellValue", "CSR Rejected "+strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7","3","CellValue", "Credit card Payment #3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"7","5","CellValue", "Meter Purchased "+totalMaximumTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"8","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"9","0","Row Does Not Exist", "");
		driver.quit();
	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_3002_RO_RTF_FTFP5_PS1_CGPV1_CCP(Map<String, String> objDictionary, String strViolationNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		// Dictionary Variables
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		String strFreeTimeFirstPayment = objDictionary.get("strFreeTimeFirstPayment");
 // 		String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
  		String expectedTime = objDictionary.get("expectedTime");
  		String totalMaximumTime = "30";
  		String totalMaximumTime2 = "15";
  		String totalMaximumTime3 = "1";

		// NTPS: Navigate To Parking Session
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter", "1");
		// VPSH: Validate Parking Session History
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"1","3","CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","3", "CellValue", "Violation" + strViolationNumber);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"2","4","CellValue", "$ 30.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "CSR Approved "+strViolationNumber);
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Automated Process");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","3","CellValue", "Credit card Payment #1");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"3","5","CellValue", "Meter Purchased "+totalMaximumTime+" Minutes");
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","3","CellValue", "Virt Payment #2");
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","4","CellValue", "$ 0.10");
//		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"5","5","CellValue", "Meter Purchased "+strFreeTimeFirstPayment+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"10","3","CellValue", "Credit card Payment #6");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"10","4","CellValue", "$ 0.75");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"10","5","CellValue", "Meter Purchased "+totalMaximumTime+" Minutes");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"11","3","CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1,"12","0","Row Does Not Exist", "");
		driver.quit();
	}
	// *****************************************************************************************************************************************************************************
	// SENTRYLINK-CALCULATION
	// *****************************************************************************************************************************************************************************
	public String SENTRYLINK_CalculateCreditCardRemainingAmount(Double dblMaximumDuration, String strCreditCardIncrementTime, DecimalFormat dFormat)
  	{
		//M32_FTFP0_FTR_PS1_CP1_VMT_PRT_CCP1_VMT_ES1_VPSH_VICAE_VIAC
		double dblCreditCardPaymentAmount = Math.ceil(((dblMaximumDuration - Double.parseDouble(strCreditCardIncrementTime)) / Double.parseDouble(strCreditCardIncrementTime)) * .25 * 100.0)/100.0;
		//M39A_FTFP10_FTR_PS1_CP1_VMT_PRT_CCP1_VMT_ES1_VPSH_VICAE_VIAC
		//double dblCreditCardPaymentAmount = Math.round(((dblMaximumDuration - Double.parseDouble(strCreditCardIncrementTime)) / Double.parseDouble(strCreditCardIncrementTime)) * .25 * 100.0)/100.0;
		return dFormat.format(dblCreditCardPaymentAmount);
  	}


}

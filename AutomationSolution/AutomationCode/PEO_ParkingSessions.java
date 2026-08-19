package AutomationCode;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import java.util.ArrayList;

public class PEO_ParkingSessions
{
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public WebDriver getDriver() {return threadDriver.get();}

	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4005_FTFP0_CGPV_AV_CV_PLI_IT_PV_VPVE_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//Click Image Link (Not Done)
	  	String strNumberOfViolationImages = objDictionary.get("strNumberOfViolationImages");
  		clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  		try {Thread.sleep(5000);} catch (Exception e) {}
  		//List<WebElement> images = driver.findElements(By.xpath("//div[contains(@class,'jcarousel jcarousel-navigation')]//ul//li//img[contains(@src,'IMG')]"));
  		
  		//FLUTTER
  		List<WebElement> allImages = driver.findElements(By.cssSelector("div.jcarousel-navigation ul li img"));
  		List<WebElement> images = new ArrayList<>();
		for (WebElement img : allImages) {
		    String src = img.getAttribute("src");
		    
		    // Skip images that contain "SPOT_1" in the filename
		    if (src != null && !src.contains("SPOT_1")) {
		    	images.add(img);
		    }
		}
 		System.out.println("Total images found: " + allImages.size());
 		System.out.println("Images after filtering out SPOT_1: " + images.size());
  		
  		int intActualNumberOfImages = images.size();
  		if(intActualNumberOfImages == Integer.parseInt(strNumberOfViolationImages))
      	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
      	else
      	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected "+intActualNumberOfImages+" Actual "+intActualNumberOfImages,"Local");}
      	// VIAC: Validate Images Appear Correctly
  		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  		Actions actions = new Actions(driver);
  	    actions.sendKeys(Keys.ESCAPE).build().perform();
  	    try{Thread.sleep(1000);}catch (Exception e) {}
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
	  		String strEnvironment = objDictionary.get("strEnvironment");
	    	if(strEnvironment.equals("PROD"))
	    	{
	    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","0", "Row Does Not Exist", "");
	    	}
	    	else
	    	{
		    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Credit card Payment #0");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Paid "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
	    	}
		}
		else
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Notified "+strViolationId);
	  		String strEnvironment = objDictionary.get("strEnvironment");
	    	if(strEnvironment.equals("PROD"))
	    	{
	    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","0", "Row Does Not Exist", "");
	    	}
	    	else
	    	{
		    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Credit card Payment #0");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Paid "+strViolationId);
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Exited");
		  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","0", "Row Does Not Exist", "");
	    	}
		}
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	  		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4006_FTFP0_CGPV_PLI_AV_CV_IT_DV_DWDV_PDV_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Disputed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "End Dispute "+strViolationId);
  		String strEnvironment = objDictionary.get("strEnvironment");
    	if(strEnvironment.equals("PROD"))
    	{
    		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
    	}
    	else
    	{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Credit card Payment #0");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Paid "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","0", "Row Does Not Exist", "");
    	}
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4007_FTFP0_CGPV_PLI_AV_CV_CLI_IT_VLI_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
	 		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Disputed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Dismissed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
		}
		else
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Notified "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Disputed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Dismissed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","0", "Row Does Not Exist", "");
		}
	  	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4008_FTFP0_CGPV_AV_CV_TNI_VSM_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Missed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Missed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
		}
	  	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4009_FTFP0_CGPV_AV_CV_VIPV_TNI_VSM_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Missed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4010_FTFP0_CGPV_AV_CV_VIPV_CAV_RCV_TNI_VSM_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "New "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Missed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","0", "Row Does Not Exist", "");
		}
		else
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "New "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Missed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","0", "Row Does Not Exist", "");
		}
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4011_FTFP0_CGPV_AV_SV_USV_CV_IT_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Snoozed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Notified "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
		}
		else
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Snoozed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "New "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Notified "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
		}
	  	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,20, "Waiting for 20 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
	  		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4012_FTFP0_CGPV_AV_CV_CNIT_RA_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4013_FTFP0_CGPV_AV_CV_CNIT_RDV_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Waiting for 30 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4014_FTFP0_CGPV_AV_CV_CNIT_RDIV_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
	    }
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4015_FTFP0_CGPV_AV_CV_CNIT_RBCE_VIR(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
	    driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4016_FTFP0_CGPV_AV_CV_CNIT_RBS_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4017_FTFP0_CGPV_AV_CV_CNIT_RTC_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		
		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
			if(strCouldNotIssueReason.equals("Vehicle Departed"))
	  		{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
			}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
			if(strCouldNotIssueReason.equals("Vehicle Departed"))
	  		{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Missed "+strViolationId);
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Unissued "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Reason: "+strCouldNotIssueReason);
			}
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		}
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4018_FTFP0_CGPV_AV_CV_CNIT_RVU_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		if(strCouldNotIssueReason.equals("Vehicle Departed"))
  		{
			clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: "+strCouldNotIssueReason);
		}
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary,driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary,driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary,driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4019_FTFP0_CGPV_AV_CV_CNIT_RO_VIR_ES1(Map<String, String> objDictionary, String strViolationId, String strCouldNotIssueReason)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
  		if(strCouldNotIssueReason.equals("Vehicle Departed"))
		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
  		}
  		else
  		{
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unissued "+strViolationId);
  			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: FBI Vehicle");
  		}
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
  		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4020_FTFP0_CGPV_AV_PCV_CP1_VMU_VPVR(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
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
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		//clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #1");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","4", "CellValue", "$ 0.25");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","5", "CellValue", "Meter Purchased "+strCreditCardIncrementTime+" Minutes");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unlocked "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","5", "CellValue", "Reason: Unlocked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
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
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4021_CGPV1_AV_PCV_PIT_CP1_VMU_VPVR(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Unlocked "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #1");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unlocked "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
		}
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4022_FTFP0_CGPV1_AV_PCV_PIT_PGT_CP1_VML_MPO2(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
		}
		else
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
		}
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4023_PEO_CGPV1_NTAAV_PAIT_GT_RBT_VVR(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		//clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "Initial Grace Period Exceeded");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Verified");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Generating Ticket "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4024_PEO_CGPV1_NTAAV_CAVV_CNIT_RV_RAV_GT(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Unissued "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Normal");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "New "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "To Verify");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Claimed "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Verified");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Generating Ticket "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Ticket Generated"+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Notified "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Exited");
    	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
    	if(strValidateImageFlag.equals("True"))
    	{
    		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
    	}
      	driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4025_FTFP0_MUON_CGPV1_NTAAV_SV_CP1_USBDNE(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
    	String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Snoozed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #1");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Unlocked "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Exited");
		}
		else
		{
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Snoozed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #1");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Unlocked "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Exited");
		}
    	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
    	if(strValidateImageFlag.equals("True"))
    	{
    		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,20, "Waiting for 25 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	    }
      	driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4026_FTFP10_MUON_CGPV1_NTAAV_SV_CP1_USBDNE(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
    	String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Snoozed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Cash Payment #1");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Virt Payment #2 ");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Unlocked "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Exited");
	 	}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Snoozed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Cash Payment #1");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Virt Payment #2 ");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Unlocked "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Exited");
	   }
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
    	if(strValidateImageFlag.equals("True"))
    	{
    		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,20, "Waiting for 25 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	    }
      	driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4027_AMMOEE_MUON_CGPV1_NTAAV_SV_LS1_USBDNE(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
    	String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Snoozed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Exited");
	    	String strRow9Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow9Column3", "9", "3");
			if(strRow9Column3.equals("Missed "+strViolationId))
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Missed "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","0", "Row Does Not Exist", "");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
			}
		}
		else
		{
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Snoozed "+strViolationId);
	    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
	    	String strRow8Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "8", "3");
			if(strRow8Column3.equals("Missed "+strViolationId))
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Missed "+strViolationId);
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","0", "Row Does Not Exist", "");
			}
			else
			{
				clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
			}
		}
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
    	if(strValidateImageFlag.equals("True"))
    	{
    		//Wait For Images to Load
	   		clsMeter.METER_MeterWaitWithMessage(objDictionary,20, "Waiting for 20 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
	   		//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	    }
      	driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4029_FTFP0_CGPV_AV_ES1_VVR(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Missed "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
	    if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4030_FTFP0_CGPV_AV_CV_ES1_VVR(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4031_FTFP0_CGPV_AV_CV_IT_ES1_VVR(Map<String, String> objDictionary, String strCreditCardIncrementTime, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4032_CSLV_AV_CV_IT_ES1_VVR(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	//NTPS: Navigate To Parking Session
    	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
    	WebDriver driver = getDriver();
    	clsCommonWeb.SENTRYLINK_NavigateToParkingSession2(objDictionary, driver, "No EntranceParkingEvent!","1");
    	//VPSH: Validate Parking Session History
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","0", "Row Does Not Exist", "");
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4042_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_ATDD_VTPDF_ATDD_VTPDF_ES1_VPSH(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "19","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "20","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "20","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "21","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "21","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "22","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "23","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4043_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_DV_VNTPDF_EDV_VTPDF_ES1_VPSH(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Disputed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "ffticketservicetestingadmin@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "End Dispute "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "ffticketservicetestingadmin@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "19","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "20","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4044_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_PVOH_VTPDF_DV_VNTPDF_EDV_VTPDF_ES1_VPSH(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Hold "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Reason: Possible Error ffticketservicetestingadmin@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Unhold "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","5", "CellValue", "ffticketservicetestingadmin@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "19","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "20","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4045_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_PV_VNTPDF_ES1_VPSH_VICAE_VIAC(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Credit card Payment #0");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","4", "CellValue", "$ 37.00");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","5", "CellValue", "Fine Payment ");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Paid "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4046_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_VV_VNTPDF_ES1_VPSH_VICAE_VIAC(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4047_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF_DV_DDV_VNTPDF_ES1_VPSH_VICAE_VIAC(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Disputed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Dismissed "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4048_TSLOB_FTFP0_CGPV_AV_AVPEO_VTPDF1_VTPDF2_VTPDF3_VVAC_VNTPDF_PVAC_VVC_VNTPDF_ES1_VPSH_VICAE_VIAC(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Officer Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Sent For Verification");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Verified");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","5", "CellValue", "Owner info updated by automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "17","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "18","3", "CellValue", "Generating Ticket "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "19","3", "CellValue", "Ticket Generated "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "20","3", "CellValue", "Notified "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "20","5", "CellValue", "Reason: Ticket has been mailed. automated-process3@mpspark.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "21","3", "CellValue", "Owner updated");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "22","3", "CellValue", "Overdue "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "23","3", "CellValue", "Awaiting Collection "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "24","3", "CellValue", "Collections "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "24","5", "CellValue", "ffticketservicetestingadmin@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "25","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "26","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4049_TSLOB_FTFP0_CGPV_AV_RVPEO_VNTPDF_ES1_VPSH_VICAE_VIAC(Map<String, String> objDictionary, String strViolationId)
  	{
		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//Function Variable
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
      	WebDriver driver = getDriver();
      	//NTPS: Navigate To Parking Session
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","5", "CellValue", "ffticketservicetestingadmin@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","5", "CellValue", "Reason: Rejected by officer. Technical Difficulties (User) ffticketservicetestingpeo@gmail.com");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Officer Rejected "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Exited");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
		if(strValidateImageFlag.equals("True"))
	  	{
			//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,12, "Waiting for 12 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	   		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4033_AMMOE_CGPV1_AV_VAE_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//NTPS: Navigate To Parking Session
      	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
    	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
    	WebDriver driver = getDriver();
    	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow8Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Missed "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","0", "Row Does Not Exist", "");
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Missed "+strViolationId);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		}
		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
		if(strValidateImageFlag.equals("True"))
  	  	{
  	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,20, "Waiting for 20 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
  	 		driver.navigate().refresh();
  	  		//Click Image Link
  	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  	      	try {Thread.sleep(1000);}catch (Exception e) {}
  	      	//VICAE: Validate Image Count After Exit
  	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
  	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
  	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
  	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
  	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
  	      	//VIAC: Validate Images Appear Correctly
  	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  	  	}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4034_SLPE_CGPV1_AV_VVV_ES1(Map<String, String> objDictionary, String strViolationId,String strLicensePlateNumber, String strState)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Navigate to Violations
  	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
  		String strPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
  		clsCommonWeb.SENTRYLINK_NavigateToViolationsPage(objDictionary, driver, strPageName);
  		clsCommonWeb.SENTRYLINK_NavigateToNewViolation(objDictionary, driver, "Initial Grace Period Exceeded",strViolationId,"");
  		clsCommonWeb.PopulateAction(objDictionary, driver, "Parking Session", "Populate License Plate Information", "{T} License Plate|{T} Province/State",strLicensePlateNumber+"|"+strState);
  		clsCommonWeb.ClickButton(objDictionary, driver, "Parking Session", "Create", 1,"Local");
	  	//ShortSessionWaitExitSpot
  		objDictionary.put("strCheckIfSpotPresumedOccupied","False");
  		clsMeter.SENTRYMETER_ShortSessionWaitExitSpot(objDictionary, null, "1","Local");
  		try {Thread.sleep(5000);}catch (Exception e) {}
  		driver.navigate().refresh();
  		String strURL = driver.getCurrentUrl();
  		Reporter.log("Parking Session: " + strURL);
  		//VPSH: Validate Parking Session History
      	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Voided "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Reason: Exempt: Test Vehicle");
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exempt");
		String strRow5Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow5Column3", "5", "3");
		if(strRow5Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "License Plate Changed");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
	
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","0", "Row Does Not Exist", "");
		}
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4035_SLPE_CMV_IGPE_ES1(Map<String, String> objDictionary, String strViolationId,String strLicensePlateNumber, String strState)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Navigate to Violations
  	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
   		clsCommonWeb.SENTRYLINK_NavigateToParkingSession2(objDictionary, driver, "No EntranceParkingEvent!","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Exited");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Violation "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Voided "+strViolationId);
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Exempt");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","0", "Row Does Not Exist", "");
		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4036_FTFP0_MUON_CGPV1_UM_CP1_LMV_AP_CV_SV_LSTE_ES(Map<String, String> objDictionary, String strViolationId,String strViolationId2)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Navigate to Violations
  	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strViolationNumber = objDictionary.get("strViolationNumber");
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #1");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Unlocked "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Violation "+strViolationId2);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "CSR Approved "+strViolationId2);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Claimed "+strViolationId2);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Snoozed "+strViolationId2);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "New "+strViolationId2);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Missed "+strViolationId2);
	    clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","0", "Row Does Not Exist", "");
  		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4037_FTFP10_MUON_CGPV1_UM_CP1_LMV_AP_CV_SV_LSTE_ES(Map<String, String> objDictionary, String strViolationId,String strViolationId2)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		//Navigate to Violations
  	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
  		threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
  		WebDriver driver = getDriver();
  		clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
      	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Cash Payment #1");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Virt Payment #2");
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Unlocked "+strViolationId);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Violation "+strViolationId2);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "CSR Approved "+strViolationId2);
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Claimed "+strViolationId2);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Snoozed "+strViolationId2);
    	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "New "+strViolationId2);
		String strRow14Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow14Column3", "14", "3");
		if(strRow14Column3.equals("Exited"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Missed "+strViolationId2);
		}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","3", "CellValue", "Missed "+strViolationId2);
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "15","3", "CellValue", "Exited");
		}
		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "16","0", "Row Does Not Exist", "");
  		driver.quit();
    }
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_4038_VM_JMP_VPLS_CLS(Map<String, String> objDictionary)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
	  	//Get Fees On First Payment
	  	String strFirstPayment = objDictionary.get("strFirstPayment");
	  	String strSpotNumber = objDictionary.get("strSpotNumber");
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strValidateImageFlag = objDictionary.get("strValidateImageFlag");
      	if(strValidateImageFlag == null){strValidateImageFlag = "True";}
      	String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
      	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath,objDictionary);
      	WebDriver driver = getDriver();
      	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	//VPSH: Validate Parking Session History
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Mps account Payment #1");
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","5", "CellValue", "App Purchased "+strMeterIncrementTime+" Minutes");
	  	String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("Mps account Payment #1"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Mps account Payment #1");
		  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","5", "CellValue", "Transaction Fee Payment");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","0", "Row Does Not Exist", "");
	  	}
		else
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "Exited");
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","0", "Row Does Not Exist", "");
	  	}
		driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4039_FTFP0_CGPV1_DNAATTPAV_AV_CAV_PCV_VIPV_CVP_AATTPAV_PGT_VGTV_PIT_VITV_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  		try {Thread.sleep(1000);} catch (Exception e) {}
  		List<WebElement> images = driver.findElements(By.xpath("//div[contains(@class,'jcarousel-navigation')]//ul/li/img"));
  		int intActualNumberOfImages = images.size();
  		if(intActualNumberOfImages == 7)
      	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
      	else
      	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected "+intActualNumberOfImages+" Actual "+intActualNumberOfImages,"Local");}
      	// VIAC: Validate Images Appear Correctly
  		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  		Actions actions = new Actions(driver);
  	    actions.sendKeys(Keys.ESCAPE).build().perform();
  	    try{Thread.sleep(1000);}catch (Exception e) {}
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		String strRow3Column3 = clsCommonWeb.StoreTableValue(objDictionary,  driver, "Parking Session", "Parking Session History", 1, "strRow3Column3", "3", "3");
		if(strRow3Column3.equals("License Plate Changed"))
		{
			clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "New "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Notified "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
		}
		else
		{
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "CSR Approved "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "To Verify");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "New "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "Claimed "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Verified");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Generating Ticket "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Ticket Generated "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Notified "+strViolationId);
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Exited");
	  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","0", "Row Does Not Exist", "");
		}
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
	public void SENTRYLINK_ValidateParkingSessionHistoryAndImages_P4040_FTFP0_CGPV1_DNAATTPAV_AV_CAV_PCV_VIPV_CVP_AATTPAV_PGT_VGTV_PIT_VITV_ES1(Map<String, String> objDictionary, String strViolationId)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		Meter clsMeter = new Meter();
  		//Dictionary Variables
	  	String strBrowser = objDictionary.get("strBrowser");
      	String strRemotePath = objDictionary.get("strRemotePath");
      	String strSpotNumber = objDictionary.get("strSpotNumber");
      	//NTPS: Navigate To Parking Session
	  	threadDriver =clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
	  	WebDriver driver = getDriver();
	  	clsCommonWeb.SENTRYLINK_NavigateToParkingSession(objDictionary, driver, "Sentry meter","1");
	  	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
  		try {Thread.sleep(1000);} catch (Exception e) {}
  		List<WebElement> images = driver.findElements(By.xpath("//div[contains(@class,'jcarousel-navigation')]//ul/li/img"));
  		int intActualNumberOfImages = images.size();
  		if(intActualNumberOfImages == 7)
      	{Reporter.log(intActualNumberOfImages+" Violation Images Correctly Appeared in the Parking Session");}
      	else
      	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Number of Violation Images were incorrect expected "+intActualNumberOfImages+" Actual "+intActualNumberOfImages,"Local");}
      	// VIAC: Validate Images Appear Correctly
  		clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
  		Actions actions = new Actions(driver);
  	    actions.sendKeys(Keys.ESCAPE).build().perform();
  	    try{Thread.sleep(1000);}catch (Exception e) {}
	  	clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "1","3", "CellValue", "Parked");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "2","3", "CellValue", "Violation"+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "3","3", "CellValue", "License Plate Changed");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "4","3", "CellValue", "CSR Approved "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "5","3", "CellValue", "To Verify");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "6","3", "CellValue", "Claimed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "7","3", "CellValue", "New "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "8","3", "CellValue", "Claimed "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "9","3", "CellValue", "Verified");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "10","3", "CellValue", "Generating Ticket "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "11","3", "CellValue", "Ticket Generated "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "12","3", "CellValue", "Notified "+strViolationId);
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "13","3", "CellValue", "Exited");
  		clsCommonWeb.VerificationPointTable(objDictionary, driver, "Parking Session", "Parking Session History", 1, "14","0", "Row Does Not Exist", "");
  		String strValidateImageFlag = objDictionary.get("strValidateImageFlag");if(strValidateImageFlag == null){strValidateImageFlag = "True";}
  		if(strValidateImageFlag.equals("True"))
	  	{
	  		//Wait For Images to Load
	  		clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Waiting for 15 seconds for Images to load to Sentry Link-Spot"+strSpotNumber);
	 		driver.navigate().refresh();
			//Click Image Link
	      	clsCommonWeb.ClickImage(objDictionary, driver, "Parking Session", "Best Violation Picture", 1);
	      	try {Thread.sleep(1000);}catch (Exception e) {}
	      	//VICAE: Validate Image Count After Exit
	      	String strParkingImageDurationEntrance = clsMeter.METER_GetPARKING_IMAGE_DURATION_ENTRANCE(objDictionary, null);
	      	String strParkingImageLookAheadEntrance = clsMeter.METER_GetPARKING_IMAGE_LOOK_AHEAD_ENTRANCE(objDictionary, null);
	      	String strParkingImageDurationExit  = clsMeter.METER_GetPARKING_IMAGE_DURATION_EXIT(objDictionary, null);
	      	int intTotalImageSeconds = Integer.parseInt(strParkingImageDurationEntrance) + Integer.parseInt(strParkingImageLookAheadEntrance) + Integer.parseInt(strParkingImageDurationExit);
	      	clsCommonWeb.SENTRYLINK_ValidateImageCountEqualExpected(objDictionary,driver,intTotalImageSeconds,1,"False");
	      	//VIAC: Validate Images Appear Correctly
	      	clsCommonWeb.SENTRYLINK_ValidateAllImagesAppearedCorrectly(objDictionary, driver);
	  	}
      	driver.quit();
  	}
}

package AutomationCode;

//import com.gurock.testrail.APIClient;
//import com.gurock.testrail.APIException;
//import java.util.Map;
//import java.util.HashMap;
//import org.json.simple.JSONObject;



//public class TestRails
//{
//
//}


//@Parameters({"strBrowser","strStopAndStartAppiumServer","strRemotePath","strRole", "strVehicleParkType", "strVehicleDepartType", "strDeviceId","strAVDName","strMobileAPK","strPEOAPK","strAndroidUdid","strIOSUdid","strAppiumPort","strIOSDeviceName","strIOSVersion","strIOSBuild"})
//@BeforeMethod
//public void BeforeMethod(Method method,@Optional String strBrowser,@Optional String strStopAndStartAppiumServer,@Optional String strRemotePath,@Optional String strRole, @Optional String strVehicleParkType, @Optional String strVehicleDepartType, @Optional String strDeviceId,@Optional String strAVDName, @Optional String strMobileAPK,@Optional String strPEOAPK, @Optional String strAndroidUdid,@Optional String strIOSUdid,@Optional String strAppiumPort,@Optional String strIOSDeviceName,@Optional String strIOSVersion,@Optional String strIOSBuild)
//{
//	GlobalClass clsGlobalClass = new GlobalClass();
//	CommonWeb clsCommonWeb = new CommonWeb();
//	clsCommonWeb.KillChromeDriver();
//	try{Runtime.getRuntime().exec("killall -9 instruments");}catch(Exception e){}
//	objDictionary.clear();
//	clsGlobalClass.AddUserVariablesToDictionaryObject(objDictionary, strBrowser, strStopAndStartAppiumServer, strRemotePath, strRole, strVehicleParkType, strVehicleDepartType, strDeviceId, strAVDName, strMobileAPK, strPEOAPK, strAndroidUdid, strIOSUdid, strAppiumPort, strIOSDeviceName, strIOSVersion, strIOSBuild);
//	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:");
//	dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//	System.out.println(dateFormatGmt.format(new Date()) );
//	String strMeterStartTime = dateFormatGmt.format(new Date());
//	objDictionary.remove("strMeterStartTime");objDictionary.put("strMeterStartTime", strMeterStartTime);
//	objDictionary.remove("strTestCase");objDictionary.put("strTestCase", method.getName());
//	Meter clsMeter = new Meter();
//	//Meter Logs
//	clsMeter.METER_KillTraceLogProcess(objDictionary);
//	clsMeter.METER_StartLogTrace(objDictionary);
//	//Message Logs
//	clsMeter.METER_KillMessageTraceLogProcess(objDictionary);
//		clsMeter.METER_StartMessageLogTrace(objDictionary);
//}




//public WebDriver getDriver(){return threadDriver.get();}
//@AfterMethod(alwaysRun = true)
//public void AfterMethod(ITestResult result) throws Exception
//{
//	CommonANDROID clsCommonANDROID = new CommonANDROID();
//	Database clsDatabase = new Database();
//	CommonIOS clsCommonIOS = new CommonIOS();
//	CommonWeb clsCommonWeb = new CommonWeb();
//	Meter clsMeter = new Meter();
//	String strErrorMessage = "";
//	clsCommonWeb.DeletePreviousScreenShot(result);
//	if (!result.isSuccess())
//	{
//		File directory = new File(".");
//		String strPath = directory.getCanonicalPath() +"/ScreenShots/Error.png";
//		File file = new File(strPath);
//		if(!file.exists()){try{if(getDriver() != null){clsCommonWeb.TakeScreenShoot(getDriver(), result, result.getMethod(), result.getTestContext());}}catch(Exception e){}}
//		try{clsCommonANDROID.RenameScreenShot(result, result.getMethod(), result.getTestContext());}
//		catch(Exception f){}
//		try{clsCommonIOS.TakeScreenShoot(result, result.getMethod(), result.getTestContext());}
//		catch(Exception f){}
//		//Get Error Message
//		Throwable throwable = result.getThrowable();
//		strErrorMessage = throwable.getMessage();
//	}
//	//THE ENDTIME DOESN'T DISPLAY ON THE REPORT IN BLUE
//	clsMeter.METER_SetMeterEndTime(objDictionary);
//	//Write Test Results to Database
//	String strMethodName = result.getName();
//	objDictionary.remove("strErrorMessage");objDictionary.put("strErrorMessage", strErrorMessage);
//    objDictionary.remove("strTestCaseName");objDictionary.put("strTestCaseName", strMethodName);
//	//clsDatabase.WriteToDatabase(objDictionary);
//    clsMeter.METER_CopyLogTraceLocally(objDictionary, strMethodName);
//    clsMeter.METER_CopyMessageTraceLocally(objDictionary, strMethodName);
//}
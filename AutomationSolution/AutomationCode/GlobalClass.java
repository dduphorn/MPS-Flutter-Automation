package AutomationCode;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.testng.Assert;
import org.testng.Reporter;
public class GlobalClass
{
	/**
	 * @param objDictionary
	 * @param strBrowser
	 * @param strStopAndStartAppiumServer
	 * @param strRemotePath
	 * @param strRole
	 * @param strVehicleParkType
	 * @param strVehicleDepartType
	 * @param strDeviceId
	 * @param strAVDName
	 * @param strMobileAPK
	 * @param strPEOAPK
	 * @param strAndroidUdid
	 * @param strIOSUdid
	 * @param strAppiumPort
	 * @param strIOSDeviceName
	 * @param strIOSVersion
	 * @param strIOSBuild
	 */
	public void AddUserVariablesToDictionaryObject(Map<String, String> objDictionary, String strBrowser,String strStopAndStartAppiumServer, String strRemotePath,String strRole, String strVehicleParkType, String strVehicleDepartType,String strDeviceId,String strAVDName,String strMobileAPK, String strPEOAPK, String strAndroidUdid, String strIOSUdid, String strAppiumPort, String strIOSDeviceName,String strIOSVersion,String strIOSBuild)
	{
		String strAutomationUser = System.getProperty("user.name");
		String strDeviceAppiumPort = objDictionary.get("strDeviceAppiumPort");
		//Default Test Case Values
		if(strStopAndStartAppiumServer == null) {strStopAndStartAppiumServer = "True";}
		if(strBrowser == null) {strBrowser = "Chrome";}
		//if(strRemotePath == null){strRemotePath = "http://localhost:4723";}
		//Sauce Labs
//		if(strRemotePath == null){strRemotePath = "ondemand.saucelabs.com:80";}
		//if(strRemotePath == null){strRemotePath = "http://10.10.101.121:5560";}
		if(strRole == null) {strRole = "parker";}
		if(strAndroidUdid == null){strAndroidUdid = "";}
//		if(strVehicleParkType == null) {strVehicleParkType = "occupy";} //(park,occupy)
//		if(strVehicleDepartType == null) {strVehicleDepartType = "empty";}//(leave, empty)
		if(strVehicleParkType == null) {strVehicleParkType = "park";} //(park,occupy)
		if(strVehicleDepartType == null) {strVehicleDepartType = "leave";}//(leave, empty)
		objDictionary.put("strVehicleParkType", strVehicleParkType);
		objDictionary.put("strVehicleDepartType", strVehicleDepartType);
		//UnqiuePassword
		String strUniquePassword = System.getenv("UNIQUE");
		//Work Around for Jenkins
		String strErrorMsg = "";
		String strRunFromJenkens = "False";
		String strTestSuiteName = objDictionary.get("strTestSuiteName");
		if(strUniquePassword == null)
		{
			strUniquePassword = "firesale";
			if(strTestSuiteName != null)
			{
				if(strTestSuiteName.equals("PaymentSiteTestCases"))
				{
					strRunFromJenkens = "True";
				}
			}
		}
		objDictionary.put("strUniquePassword", strUniquePassword);
		//if(strAppiumPort == null){strAppiumPort = "4723";}
		//********************************************************************
		objDictionary.put("strParkingShortSessionSec","15");
		Reporter.log("Automation User: "+strAutomationUser);
		Properties props = System.getProperties();
		//Get Headless Setting form Jenkins
		String strHeadless = System.getProperty("Headless");
		String strHeadless2 = objDictionary.get("strHeadless");
		System.out.println("strAutomationUser: "+ strAutomationUser);
		//Global Pay Payment Sync Times
		objDictionary.put("strDoRabbitmgSendEventWaitTimeCoin","120");//Original value 40
		switch (strAutomationUser)
		{ 
			case "darinduphorn":
				strAppiumPort = "6666";
				if(strHeadless == null) {props.setProperty("Headless", "True");}
				objDictionary.put("strEnableAllImages", "False");
				objDictionary.put("strValidateImageFlag", "False");
				GetMeterProperties(objDictionary, strDeviceId);
				break;
			case "RickyWalz":
			case "jenkins":
			case "mpsadmin"://Mac Book Pro
				strAppiumPort = "6666";
				if(strHeadless == null) {props.setProperty("Headless", "True");}
				objDictionary.put("strEnableAllImages", "False");
				objDictionary.put("strValidateImageFlag", "False");
				GetMeterProperties(objDictionary, strDeviceId);
				break;
			case "mpsdev": //Mac Book Air
				strAppiumPort = "6666";
				if(strHeadless == null) {props.setProperty("Headless", "True");}
				objDictionary.put("strEnableAllImages", "False");
				objDictionary.put("strValidateImageFlag", "False");
				GetMeterProperties(objDictionary, strDeviceId);
				break;
			default:
				strErrorMsg = "The user ("+strAutomationUser+") has not been added to the function-AddUserVariablesToDictionaryObject-GlobalClass.java";
				//JOptionPane.showMessageDialog(null, strErrorMsg);
				Reporter.log(strErrorMsg);Assert.fail(strErrorMsg);
		}
		//Current Releases
		String strEnvironment = objDictionary.get("strEnvironment");if(strEnvironment == null) {strEnvironment = "";}
		//DEVICE SETTINGS
		switch (strEnvironment)
		{
			case "QA":
				if(strMobileAPK == null) {strMobileAPK ="SENTRYMOBILE-Quality-2.6.171.apk";}
				if(strPEOAPK == null) {strPEOAPK ="MPSN5PEO-Quality-2.1.3.125.apk";}
				if(strIOSDeviceName == null){strIOSDeviceName = "iPhone 6";}
				if(strIOSVersion == null){strIOSVersion = "2.4.190";}
				if(strIOSBuild == null){strIOSBuild = "190s";}
				break;
			case "SG":
				if(strIOSDeviceName == null){strIOSDeviceName = "iPhone 8.0";}
				if(strIOSUdid == null){strIOSUdid = "c14f91a4a519bbcc8a8550ca21f60d2c42a940e0";}
				if(strMobileAPK == null) {strMobileAPK = "SENTRYMOBILE-Staging-8.2.384.apk";}
				if(strPEOAPK == null) {strPEOAPK = "MPSN5PEO-Staging-3.4(187).apk";}
				if(strIOSVersion == null){strIOSVersion = "8.5.445";}
				if(strIOSVersion == null){strIOSVersion = "8.5.444";}
				break;
			case "PROD":
				if(strMobileAPK == null) {strMobileAPK = "SENTRYMOBILE-Production-8.0.378.apk";}
				if(strPEOAPK == null) {strPEOAPK ="MPSN5PEO-Production-3.3.179.apk";}
				if(strIOSDeviceName == null){strIOSDeviceName = "iPhone 5";}
				if(strIOSVersion == null){strIOSVersion = "4.0.214.13";}
				if(strIOSUdid == null){strIOSUdid = "966968a344d5946cd12eb11300d5306f0fde7e81";}
				break;
		}
		if(strAndroidUdid !=null)
		{
			switch (strAndroidUdid)
	    	{
	    		case "VS986a4741414":
	    			objDictionary.put("strDevicePhone", "16513413525");
	    			break;
	    		case "LGTP2605408c68a":
	    			objDictionary.put("strDevicePhone", "19522007219");
	    			break;
	    		//Need Code to Handle Emulators and Phone
	    	}
		}
		objDictionary.put("strStopAndStartAppiumServer", strStopAndStartAppiumServer);
		Reporter.log("Global-Appium Port: "+strAppiumPort);
		objDictionary.put("strAppiumPort", strAppiumPort);
		objDictionary.put("strBrowser", strBrowser);
		objDictionary.put("strRemotePath", strRemotePath);
		objDictionary.put("strRole", strRole);
		//objDictionary.put("strSpotNumber", strSpotNumber);
		String strMunicipality = objDictionary.get("strMunicipality");if(strMunicipality == null) {strMunicipality = "";}
		objDictionary.put("strUserName", strMunicipality.replace(" ","").replace(",", "")+strRole+"@mpspark.com");
		objDictionary.put("strPassword", strMunicipality.replace(", ", "")+strRole+"1");
		objDictionary.put("strAVDName", strAVDName);
		objDictionary.put("strMobileAPK", strMobileAPK);
		objDictionary.put("strPEOAPK", strPEOAPK);
		objDictionary.put("strAndroidUdid", strAndroidUdid);
		objDictionary.put("strIOSUdid", strIOSUdid);
		objDictionary.put("strIOSDeviceName", strIOSDeviceName);
		objDictionary.put("strIOSVersion", strIOSVersion);
		objDictionary.put("strIOSBuild", strIOSBuild);
		objDictionary.put("strSpotNumber", "1");
		//Add store strPageName for corresponding url
		String strMeterGroup = objDictionary.get("strMeterGroup"); 
		if(strMeterGroup == null) {strMeterGroup = "";}
		objDictionary.put("https://mpstest.mpspark.com/","Violations");
		HttpConnections clsHttpConnections = new HttpConnections();
		//Need to be here
		switch (strEnvironment)
		{
			case "QA":
				//https://testautomation.quality.sentry-link.com/
				objDictionary.put("https://launchpad.quality.sentry-link.com/", "Please choose a Municipality");
				objDictionary.put("https://launchpad.quality.sentry-link.com/users/login","Login");
				objDictionary.put("https://"+strMunicipality.replace(" ", "").toLowerCase()+".quality.sentry-link.com/", "Violations");
			case "SG":
				objDictionary.put("https://launchpad.staging.sentry-link.com/","Please choose a Municipality");
				objDictionary.put("https://launchpad.staging.sentry-link.com/users/login","Login");
				objDictionary.put("https://"+strMeterGroup.replace(" ", "").toLowerCase()+".staging.sentry-link.com/", "Violations");
			case "PROD":
				objDictionary.put("https://launchpad.mpspark.com/","Please choose a Municipality");
				objDictionary.put("https://launchpad.mpspark.com/users/login","Login");
				objDictionary.put("https://sentrylink.mpspark.com/users/login","Login");
		}
		//**********************************************************
		//Need to relocate this in case test fails in Before Methods - Maybe to Add Variables
		//**********************************************************
		if(strDeviceId != null && !strDeviceId.isEmpty())
		{
			CommonWeb clsCommonWeb = new CommonWeb();
			String strUserName = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "parker");
			String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "parker");
			objDictionary.put("strUserNameAndPassword","User Name: "+strUserName.toLowerCase()+" Password: "+strPassword);
			objDictionary.put("https://sentrylink.staging.sentry-link.com/admin/users","Approved Users");
			objDictionary.put("https://sentrylink.quality.sentry-link.com/admin/users","Approved Users");
			objDictionary.put("https://sentrylink.mpspark.com/users/login","Login");
			String strMunicipalitySubdomain = clsHttpConnections.GET_MunicipalitySubdomain(objDictionary, strUserName.toLowerCase(), strPassword);
			objDictionary.put("strMunicipalitySubdomain",strMunicipalitySubdomain);
			objDictionary.put("https://"+strMunicipalitySubdomain+".staging.sentry-link.com/","Violations");
			objDictionary.put("https://"+strMunicipalitySubdomain+".staging.sentry-link.com/users/login","Login");
			objDictionary.put("https://"+strMunicipalitySubdomain+".quality.sentry-link.com/","Violations");
			objDictionary.put("http://http://"+strMunicipalitySubdomain+".quality.sentry-link.com/", "Violations");
			objDictionary.put("http://sentrylink.quality.sentry-link.com/admin/users","User Search");
			objDictionary.put("https://sentrylink.quality.sentry-link.com/", "Municipalities");
			objDictionary.put("https://sentrylink.staging.sentry-link.com/", "Municipalities");
			objDictionary.put("https://sentrylink.mpspark.com/users/password_expired","Renew your password");
			objDictionary.put("https://launchpad.staging.sentry-link.com/users/password_expired","Renew your password");
			objDictionary.put("http://launchpad.quality.sentry-link.com/users/password_expired","Renew your password");
			//Rates Sets
			objDictionary.put("https://"+strMunicipalitySubdomain+".staging.sentry-link.com/rating/rate_sets/new", "Rate Sets");
			objDictionary.put("https://"+strMunicipalitySubdomain+".staging.sentry-link.com/rating/rate_sets","Rate Sets");
			objDictionary.put("http://"+strMunicipalitySubdomain+".quality.sentry-link.com/rating/rate_sets/new", "Rate Sets");
			objDictionary.put("http://"+strMunicipalitySubdomain+".quality.sentry-link.com/rating/rate_sets", "Rate Sets");
			//Rate Block Groups
			objDictionary.put("https://"+strMunicipalitySubdomain+".staging.sentry-link.com/rating/rate_block_groups", "Rate Block Groups");
			objDictionary.put("http://"+strMunicipalitySubdomain+".quality.sentry-link.com/rating/rate_block_groups", "Rate Block Groups");
			objDictionary.put("https://sentrylink.mpspark.com/", "Municipalities");
			objDictionary.put("http://sentrylink.quality.sentry-link.com/admin/settings", "Settings");
			objDictionary.put("http://sentrylink.quality.sentry-link.com/activity_feed_items", "Activity Feed");
			objDictionary.put("https://"+strMunicipalitySubdomain+".staging.sentry-link.com/parker/payment_website", "Payment Site");
			objDictionary.put("https://mps.quality.sentry-link.com/parker/payment_website", "Payment Site");
			objDictionary.put("https://"+strMunicipalitySubdomain+".quality.sentry-link.com/parker/payment_website", "Payment Site");
		}
	}
	public void GetMeterProperties(Map<String, String> objDictionary, String strDeviceId)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//objDictionary.put("strMeterUser", "root");
		if(strDeviceId == null)
		{
			String strErrorMsg = "The variable strDeviceId wasn't set in the GlobalClass";
			//JOptionPane.showMessageDialog(null, strErrorMsg);
			objDictionary.put("strErrorMsg", strErrorMsg);
			Reporter.log(strErrorMsg);
		}
		else
		{
			switch (strDeviceId)
			{
				//****Quality Start****
				case "76A7L-76A7R"://Darin
					objDictionary.put("strBootstrapPort","6680");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.140");
					objDictionary.put("strSilverBullet", "10.10.103.141");
					objDictionary.put("strLocation", "-93.436295 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterName", "76A7L");
					objDictionary.put("strMeterSpotName", "76A7L");
					objDictionary.put("strMunicipality", "MPS");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup1");
					objDictionary.put("strUniqueId", "aa");
					break;
				case "6014L-6014R"://Chris
					objDictionary.put("strBootstrapPort","6681");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.166");
					objDictionary.put("strSilverBullet", "10.10.100.62");
					objDictionary.put("strLocation", "-93.32372099999998 44.84326");
					objDictionary.put("strMeterGroup", "MPS Load");
					objDictionary.put("strMeterName", "6014");
					objDictionary.put("strMeterSpotName", "6014L");
					objDictionary.put("strMunicipality", "Test");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup2");
					objDictionary.put("strUniqueId", "bb");
					break;
				case "906AL-906AR"://Chris
					objDictionary.put("strBootstrapPort","6682");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.132");
					objDictionary.put("strLocation", "-93.4362958 44.972176");
					objDictionary.put("strSilverBullet", "10.10.103.133");
					objDictionary.put("strMeterGroup", "MPS Load");
					objDictionary.put("strMeterName", "906A");
					objDictionary.put("strMeterSpotName", "906AL");
					objDictionary.put("strMunicipality", "Test");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup3");
					objDictionary.put("strUniqueId", "cc");
					break;
				//****Quality End****

				//****Staging Start****
				case "555"://Darin - Single Meter
					objDictionary.put("strBootstrapPort","6683");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strLocation", "-93.4307609 44.9605167");
					objDictionary.put("strMeterGroup", "LOT 1");
					objDictionary.put("strMeterName", "555");
					objDictionary.put("strMeterSpotName", "555");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup1");
					objDictionary.put("strUniqueId", "");
					break;
				//#######################################################################################
				//DARIN's WOOFNER METERS: ROOT
				//#######################################################################################
				case "9205-9206"://Global Pay Darin's
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9997");
					objDictionary.put("strBootstrapPort","7000");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.21");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "10.10.101.81");
					objDictionary.put("strLocation", "-93.436343  44.972167");
					objDictionary.put("strMeterGroup", "WoonerfMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9205");//Space
					objDictionary.put("strMeterSpotName", "9206");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup30");
					objDictionary.put("strFamily", "Normal");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup30");
					objDictionary.put("strPermitGroup", "Woonerf Permit Group");
					objDictionary.put("strUniqueId", "wa");//yy
	//				objDictionary.put("strRemoteUser1", "root");//Remote User
	//				objDictionary.put("strRemoteDeviceId1", "9203-9204");//10.10.101.214
	//				objDictionary.put("strRemoteHost1", "10.10.101.214");
					break;
					//#######################################################################################
					//TUFTS: ROOT
					//#######################################################################################
					case "Tufts"://Global Pay Darin's
						objDictionary.remove("strRemotePath");
						objDictionary.put("strRemotePath", "http://localhost:9997");
						objDictionary.put("strBootstrapPort","3489");
						objDictionary.put("strEnvironment", "SG");
						objDictionary.put("strDeviceId", strDeviceId);
						//objDictionary.put("strHost", "192.168.77.211");
						//objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
						//objDictionary.put("strSilverBullet", "192.168.77.251");
						//objDictionary.put("strLocation", "-93.436343  44.972167");
						objDictionary.put("strMeterGroup", "No Meter");
						//objDictionary.put("strMeterAddress", "");
						//objDictionary.put("strMeterName", "9205");//Space
						//objDictionary.put("strMeterSpotName", "9206");
						objDictionary.put("strMunicipality", "Like Tufts");
						//objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup30");
						//objDictionary.put("strFamily", "Normal");
						//objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup30");
						//objDictionary.put("strPermitGroup", "Woonerf Permit Group");
						objDictionary.put("strUniqueId", "ta");//yy
	//					objDictionary.put("strRemoteUser1", "root");//Remote User
	//					objDictionary.put("strRemoteDeviceId1", "9203-9204");//10.10.101.214
	//					objDictionary.put("strRemoteHost1", "10.10.101.214");
						break;
				//#######################################################################################
				//DARIN's GLOBAY PAY METERS: SECO - SECO
				//#######################################################################################
				case "5510-5511"://Global Pay Darin's
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9997");
					objDictionary.put("strBootstrapPort","7000");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.202");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.101.202");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5510");//Space
					objDictionary.put("strMeterSpotName", "5510");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup10");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup10");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strUniqueId", "nn");//yy
					objDictionary.put("strFamily", "Normal");
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					//Global Pay Settings #1
					objDictionary.put("strRemoteUser1", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId1", "5560-5561");//10.10.101.214
					objDictionary.put("strRemoteHost1", "10.10.101.214");
					//Global Pay Settings #2
//					objDictionary.put("strRemoteUser2", "seco");//Remote User
//					objDictionary.put("strRemoteDeviceId3", "5580-5581");
//					objDictionary.put("strRemoteHost2", "10.10.101.201");;

					break;
				case "5560-5561"://Global Pay Darin's
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9996");
					objDictionary.put("strBootstrapPort","6999");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.214");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.101.203");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5560");//Space
					objDictionary.put("strMeterSpotName", "5560");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup10");
					objDictionary.put("strUniqueId", "oo");//yy
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					//Global Pay Settings
					objDictionary.put("strRemoteUser1", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId1", "5510-5511");
					objDictionary.put("strRemoteHost1", "10.10.101.202");
					break;

				//#######################################################################################
				//TYLER"S GLOBAY PAY METERS: YOCTO - SECO
				//#######################################################################################
				case "8881-8882"://Tylers SECO
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9995");
					objDictionary.put("strBootstrapPort","6998");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.16");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.102.16");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "8881");//Space
					objDictionary.put("strMeterSpotName", "8881");
					objDictionary.put("strMunicipality", "Excelsior, MN");///
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup11");
					objDictionary.put("strUniqueId", "mm");//yy
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "yocto");
					objDictionary.put("strRemoteDeviceId", "8888-8889");
					objDictionary.put("strRemoteHost", "10.10.101.94");
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					break;
				case "8888-8889"://Tylers / Stamati's YOCTO -LOCAL
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9994");
					objDictionary.put("strBootstrapPort","6997");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.94");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "10.10.101.94");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS Lab");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "8888");//Space
					objDictionary.put("strMeterSpotName", "8888");
					objDictionary.put("strMunicipality", "Excelsior, MN");///
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup11");
					objDictionary.put("strUniqueId", "mm");//yy
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId", "8881-8882");
					objDictionary.put("strRemoteHost", "10.10.102.16");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				//#######################################################################################
				//DARIN'S 2 GLOBAY PAY METERS: SECO - SECO
				//#######################################################################################
				case "5570-5571"://Global Pay Darin's 2
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9993");
					objDictionary.put("strBootstrapPort","6996");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.246");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.216");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5570");//Space
					objDictionary.put("strMeterSpotName", "5570");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup15");
					objDictionary.put("strFamily", "Normal");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup15");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strUniqueId", "ga");
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					//Global Pay Settings #1
					objDictionary.put("strRemoteUser1", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId1", "5580-5581");
					objDictionary.put("strRemoteHost1", "10.10.101.201");
					//Global Pay Settings #2
					objDictionary.put("strRemoteUser2", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId2", "5510-5511");
					objDictionary.put("strRemoteHost2", "10.10.101.202");
					break;
				case "5580-5581"://Global Pay Darin's 2
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9992");
					objDictionary.put("strBootstrapPort","6995");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.104");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.101.104");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5580");//Space
					objDictionary.put("strMeterSpotName", "5580");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup15");
					objDictionary.put("strUniqueId", "rh");//yy
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					//Global Pay Settings #2
					objDictionary.put("strRemoteUser1", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId1", "5510-5511");
					objDictionary.put("strRemoteHost1", "10.10.101.202");
//					//Global Pay Settings #3
					objDictionary.put("strRemoteUser2", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId2", "5560-5561");
					objDictionary.put("strRemoteHost2", "10.10.101.214");
					break;
				//#######################################################################################
				//STEVE'S GLOBAY PAY METERS: SECO - SECO
				//#######################################################################################
				case "5521-5522"://SG Multi Space Meter
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9991");
					objDictionary.put("strBootstrapPort","6994");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.125");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.125");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5521");//Space
					objDictionary.put("strMeterSpotName", "5521");
					objDictionary.put("strMunicipality", "MPS");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup16");
					objDictionary.put("strUniqueId", "gc");
					break;
				case "5521-5552"://Global Pay Steve's
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9991");
					objDictionary.put("strBootstrapPort","6994");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.146");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.146 ");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Dev Sentry");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5521");//Space
					objDictionary.put("strMeterSpotName", "5521");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup16");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup16");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strUniqueId", "gc");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "seco");//Remote User
					objDictionary.put("strRemoteDeviceId", "5591-5592");
					objDictionary.put("strRemoteHost", "10.10.100.201");
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					break;
				case "5591-5592"://Global Pay Steve's
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9990");
					objDictionary.put("strBootstrapPort","6993");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.201");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.201");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "5591");//Space
					objDictionary.put("strMeterSpotName", "5591");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup16");
					objDictionary.put("strUniqueId", "gd");//yy
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup1");
					
					//Global Pay Settings
//					objDictionary.put("strRemoteUser1", "seco");//Remote User
//					objDictionary.put("strRemoteDeviceId1", "5521-5552");
//					objDictionary.put("strRemoteHost1", "10.10.100.146");
//					objDictionary.put("strVehicleParkType", "occupy");
//					objDictionary.put("strVehicleDepartType", "empty");
					break;	
				case "9301-9302"://Taxes Muni
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9991");
					objDictionary.put("strBootstrapPort","6993");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.143");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.143");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Group A");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9301");//Space
					objDictionary.put("strMeterSpotName", "9301");
					objDictionary.put("strMunicipality", "A Taxes Test");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup1");
					objDictionary.put("strUniqueId", "tm");//yy
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup1");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					//Global Pay Settings
//					objDictionary.put("strRemoteUser1", "seco");//Remote User
//					objDictionary.put("strRemoteDeviceId1", "5521-5552");
//					objDictionary.put("strRemoteHost1", "10.10.100.146");
//					objDictionary.put("strVehicleParkType", "occupy");
//					objDictionary.put("strVehicleDepartType", "empty");
					break;	
				case "9305-9306"://Taxes Muni
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9991");
					objDictionary.put("strBootstrapPort","6993");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.35");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.35");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Group B");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9305");//Space
					objDictionary.put("strMeterSpotName", "9305");
					objDictionary.put("strMunicipality", "A Taxes Test");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup2");
					objDictionary.put("strUniqueId", "tm");//yy
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					//Global Pay Settings
//					objDictionary.put("strRemoteUser1", "seco");//Remote User
//					objDictionary.put("strRemoteDeviceId1", "5521-5552");
//					objDictionary.put("strRemoteHost1", "10.10.100.146");
//					objDictionary.put("strVehicleParkType", "occupy");
//					objDictionary.put("strVehicleDepartType", "empty");
					break;	
				//#######################################################################################
				//LAB's GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
				case "9201-9202"://Global Pay LAB1
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9989");
					objDictionary.put("strBootstrapPort","6993");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.206");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "192.168.77.206");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9201");//Space
					objDictionary.put("strMeterSpotName", "9201");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup17");
					objDictionary.put("strUniqueId", "ge");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");
					objDictionary.put("strRemoteDeviceId", "9203-9204");
					objDictionary.put("strRemoteHost", "192.168.77.237");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				case "9203-9204"://Global Pay LAB1
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6992");
					objDictionary.put("strRemotePath", "http://localhost:4723");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.250");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "10.10.100.199");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "WoonerfMeterGroup");
					//objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "9203");//Space
					objDictionary.put("strMeterSpotName", "9203");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup6");
					objDictionary.put("strUniqueId", "wg");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup6");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				//#######################################################################################
				//LAB2 GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
				case "9207-9208"://Global Pay LAB2
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9986");
					objDictionary.put("strBootstrapPort","6990");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.250");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "192.168.77.250");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9207");
					objDictionary.put("strMeterSpotName", "9207");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup18");
					objDictionary.put("strUniqueId", "gi");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");//Remote User
					objDictionary.put("strRemoteDeviceId", "9201-9202");
					objDictionary.put("strRemoteHost", "192.168.77.221");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				//#######################################################################################
				//LAB3 GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
				case "9209-9210"://LOCAL LAB3
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9985");
					objDictionary.put("strBootstrapPort","6989");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.243");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "192.168.77.221");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9209");//Space
					objDictionary.put("strMeterSpotName", "9209");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup19");
					objDictionary.put("strUniqueId", "gj");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "seco");
					objDictionary.put("strRemoteDeviceId", "9211-9212");
					objDictionary.put("strRemoteHost", "192.168.77.204");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				case "9211-9212"://Remote Pay LAB3
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9984");
					objDictionary.put("strBootstrapPort","6988");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.204");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "127.0.0.1");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9211");
					objDictionary.put("strMeterSpotName", "9211");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup19");
					objDictionary.put("strUniqueId", "gk");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");//Remote User
					objDictionary.put("strRemoteDeviceId", "9209-9210");
					objDictionary.put("strRemoteHost", "192.168.77.221");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				//#######################################################################################
				//LAB4 GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
				case "9213-9214"://LOCAL LAB4
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9983");
					objDictionary.put("strBootstrapPort","6987");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.247");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "192.168.77.247");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9213");//Space
					objDictionary.put("strMeterSpotName", "9213");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup20");
					objDictionary.put("strUniqueId", "gl");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");
					objDictionary.put("strRemoteDeviceId", "9215-9216");
					objDictionary.put("strRemoteHost", "192.168.77.245");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				case "9215-9216"://Remote Pay LAB4
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9982");
					objDictionary.put("strBootstrapPort","6986");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.245");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "192.168.77.245");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9215");
					objDictionary.put("strMeterSpotName", "9215");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup20");
					objDictionary.put("strUniqueId", "gm");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");//Remote User
					objDictionary.put("strRemoteDeviceId", "9213-9214");
					objDictionary.put("strRemoteHost", "192.168.77.247");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				//#######################################################################################
				//LAB5 GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
				case "9219-9220"://Remote LAB5
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9980");
					objDictionary.put("strBootstrapPort","6984");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.246");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "192.168.77.246");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9219");
					objDictionary.put("strMeterSpotName", "9219");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup21");
					objDictionary.put("strUniqueId", "go");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");//Remote User
					objDictionary.put("strRemoteDeviceId", "9217-9218");
					objDictionary.put("strRemoteHost", "192.168.77.242");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;
				//#######################################################################################
				//LAB6 GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
				case "9223-9224"://Local
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9978");
					objDictionary.put("strBootstrapPort","6983");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "192.168.77.200");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "192.168.77.200");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "MPS");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9223");
					objDictionary.put("strMeterSpotName", "9223");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup22");
					objDictionary.put("strUniqueId", "gp");
					//Global Pay Settings
					objDictionary.put("strRemoteUser", "root");//Remote User
					objDictionary.put("strRemoteDeviceId", "9225-9226");
					objDictionary.put("strRemoteHost", "192.168.77.202");
					objDictionary.put("strRemoteMeterGroup", "MPS");
					objDictionary.put("strVehicleParkType", "park");
					objDictionary.put("strVehicleDepartType", "leave");
					break;

				//#######################################################################################
				//LAB6 GLOBAY PAY METERS: YOCTO - YOCTO
				//#######################################################################################
	//			case "9221-9222"://LOCAL LAB6
	//				objDictionary.remove("strRemotePath");
	//				objDictionary.put("strRemotePath", "http://localhost:9979");
	//				objDictionary.put("strBootstrapPort","6983");
	//				objDictionary.put("strEnvironment", "SG");
	//				objDictionary.put("strDeviceId", strDeviceId);
	//				objDictionary.put("strHost", "192.168.77.244");
	//				objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
	//				objDictionary.put("strSilverBullet", "192.168.77.244");
	//				objDictionary.put("strLocation", "-93.43552 44.972176");
	//				objDictionary.put("strMeterGroup", "MPS");
	//				objDictionary.put("strMeterAddress", "");
	//				objDictionary.put("strMeterName", "9221");//Space
	//				objDictionary.put("strMeterSpotName", "9221");
	//				objDictionary.put("strMunicipality", "AutomationMunicipality");
	//				objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup22");
	//				objDictionary.put("strUniqueId", "gp");
	//				//Global Pay Settings
	//				objDictionary.put("strRemoteUser", "root");
	//				objDictionary.put("strRemoteDeviceId", "9223-9224");
	//				objDictionary.put("strRemoteHost", "192.168.77.200");
	//				objDictionary.put("strRemoteMeterGroup", "MPS");
	//				objDictionary.put("strVehicleParkType", "park");
	//				objDictionary.put("strVehicleDepartType", "leave");
	//				break;
	//			case "9223-9224"://Remote LAB6
	//				objDictionary.remove("strRemotePath");
	//				objDictionary.put("strRemotePath", "http://localhost:9978");
	//				objDictionary.put("strBootstrapPort","6982");
	//				objDictionary.put("strEnvironment", "SG");
	//				objDictionary.put("strDeviceId", strDeviceId);
	//				objDictionary.put("strHost", "192.168.77.200");
	//				objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
	//				objDictionary.put("strSilverBullet", "192.168.77.200");
	//				objDictionary.put("strLocation", "-93.43552 44.972176");
	//				objDictionary.put("strMeterGroup", "MPS");
	//				objDictionary.put("strMeterAddress", "");
	//				objDictionary.put("strMeterName", "9223");
	//				objDictionary.put("strMeterSpotName", "9223");
	//				objDictionary.put("strMunicipality", "AutomationMunicipality");
	//				objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup22");
	//				objDictionary.put("strUniqueId", "gq");
	//				//Global Pay Settings
	//				objDictionary.put("strRemoteUser", "root");//Remote User
	//				objDictionary.put("strRemoteDeviceId", "9221-9222");
	//				objDictionary.put("strRemoteHost", "192.168.77.244");
	//				objDictionary.put("strRemoteMeterGroup", "MPS");
	//				objDictionary.put("strVehicleParkType", "park");
	//				objDictionary.put("strVehicleDepartType", "leave");
	//				break;
				//#######################################################################################
				//Sentry Health
				//#######################################################################################
				case "Health Kiosk Blue":
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:6989");
					objDictionary.put("strBootstrapPort","3993");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.78");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "192.168.100.101");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Health Dev");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "Health Kiosk Blue");//Space
					objDictionary.put("strMeterSpotName", "SH72");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "Health RBG");
					objDictionary.put("strUniqueId", "ha");
					break;
				case "SH72":
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:6990");
					objDictionary.put("strBootstrapPort","3994");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "223.168.254.160");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "192.168.100.101");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Test Health");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "SH72");//Space
					objDictionary.put("strMeterSpotName", "SH72");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "Health RBG");
					objDictionary.put("strUniqueId", "hb");
					break;

				case "SH79":
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:6991");
					objDictionary.put("strBootstrapPort","3994");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.144");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "192.168.100.101");
//					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Test Health");
//					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "SH79");//Space
//					objDictionary.put("strMeterSpotName", "SH79");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "Health RBG");
					objDictionary.put("strUniqueId", "hc");
					break;
				//#######################################################################################
				case "7846L-7846R"://Steve
					objDictionary.put("strBootstrapPort","6684");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.96");
					objDictionary.put("strSilverBullet", "10.10.103.97");
					objDictionary.put("strLocation", "-93.4329995 44.9765394");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterName", "D725L");
					objDictionary.put("strMeterSpotName", "D725L");
					objDictionary.put("strMunicipality", "Test");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup2");
					objDictionary.put("strUniqueId", "aa");
					break;
				case "9000-9001":
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.242");
					objDictionary.put("strSilverBullet", "10.10.102.243");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "TicketServiceMeterGroup");
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "9000");//Space
					objDictionary.put("strMeterSpotName", "9000");
					objDictionary.put("strMunicipality", "TicketServiceTesting");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup1");
//					objDictionary.put("strUniqueId", "fg");//Android Tests
					objDictionary.put("strUniqueId", "ff");//PEO Tests
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup1");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "9010-9011"://Shawon Office
					objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strBootstrapPort","7089");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.168");
					objDictionary.put("strSilverBullet", "10.10.101.168");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AMG4");
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "9010");//Space
					objDictionary.put("strMeterSpotName", "9010");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup31");
//					objDictionary.put("strUniqueId", "fg");//Android Tests
					objDictionary.put("strUniqueId", "sb");//PEO Tests
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup17");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					break;
					
				case "8813-8814"://Not Real Meter
//					objDictionary.put("strMeterUser", "root");
//					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
//					objDictionary.put("strDeviceId", strDeviceId);
//					objDictionary.put("strHost", "10.10.102.242");
//					objDictionary.put("strSilverBullet", "10.10.102.243");
//					objDictionary.put("strLocation", "-93.43552 44.972176");
//					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
//					objDictionary.put("strMeterAddress", "900 Demo Street");
//					objDictionary.put("strMeterName", "9000");//Space
//					objDictionary.put("strMeterSpotName", "9000");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
//					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup6");
					objDictionary.put("strUniqueId", "ur");//PEO Tests
//					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup6");
//					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "8873-8874": //Ricky's excelsior meter-Staging
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.98");
					objDictionary.put("strSilverBullet", "10.10.102.243");
					objDictionary.put("strLocation", "40 90");
					objDictionary.put("strMeterGroup","Ricky meter group");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "8873");
					objDictionary.put("strMeterSpotName", "8873");
					objDictionary.put("strMunicipality", "automationRickyMuni");
					objDictionary.put("strRateBlockGroup", "automationRateBlockGroup6");
					objDictionary.put("strUniqueId", "rb");
					objDictionary.put("strReservationBlockGroup", "AutomationRateBlockGroup6");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "9217-9218":
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.158");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "10.10.101.248");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "ROAMG1");//Royal Oak Automated Meter Group 1
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "9217");
					objDictionary.put("strMeterSpotName", "9217");
					objDictionary.put("strMunicipality", "AutomationTest");
					objDictionary.put("strRateBlockGroup", "RORBG1");
					objDictionary.put("strUniqueId", "fg");
					objDictionary.put("strReservationBlockGroup", "RRORBG1");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "9225-9226":
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.130");
					objDictionary.put("strSilverBullet", "10.10.101.91");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "ROAMG2");//Royal Oak Automated Meter Group 1
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "9225");
					objDictionary.put("strMeterSpotName", "9225");
					objDictionary.put("strMunicipality", "AutomationTest");
					objDictionary.put("strRateBlockGroup", "RORBG2");
					objDictionary.put("strUniqueId", "fh");
					objDictionary.put("strReservationBlockGroup", "RRORBG2");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "9090L-9090R":
					objDictionary.put("strBootstrapPort","6686");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.138");
					objDictionary.put("strSilverBullet", "10.10.102.183");//Using John Tudor silver bullet
					objDictionary.put("strLocation", "-93.433914 44.96334");//44.878221 / -93.2442467
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "123 Fake Street");
					objDictionary.put("strMeterName", "9090L");
					objDictionary.put("strMeterSpotName", "9090L");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup4");
					objDictionary.put("strUniqueId", "jj");
					break;
				case "9092"://Darin - Single Meter
					objDictionary.put("strBootstrapPort","7687");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.240");
					objDictionary.put("strSilverBullet", "10.10.102.241");
					objDictionary.put("strLocation", "-93.4366500377655 44.9720393753185");
					objDictionary.put("strMeterGroup", "AutomationMeterGroup");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "9092");
					objDictionary.put("strMeterSpotName", "9092");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup5");
					objDictionary.put("strUniqueId", "da");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup5");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "9094-9095":
					objDictionary.put("strMeterUser", "root");
//					objDictionary.remove("strRemotePath");
//					objDictionary.put("strRemotePath", "http://localhost:7777");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","7688");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.182");
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "10.10.102.183");
					objDictionary.put("strLocation", "-93.436572 44.97199");
					objDictionary.put("strMeterGroup", "AMG3");
					objDictionary.put("strMeterAddress", "Short Address");
					objDictionary.put("strMeterName", "9094");
					objDictionary.put("strMeterSpotName", "9094");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup9");
					objDictionary.put("strUniqueId", "qa");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup9");
					break;
				case "9096-9097"://Jack and Lydia
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:8888");
					objDictionary.put("strBootstrapPort","6689");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.172");
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strSilverBullet", "10.10.102.173");
					objDictionary.put("strLocation", "-93.436117 44.971886");
					objDictionary.put("strMeterGroup", "AMG2");
					objDictionary.put("strMeterAddress", "J&L Lane");
					objDictionary.put("strMeterName", "9096");
					objDictionary.put("strMeterSpotName", "9096");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup8");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup8");
					objDictionary.put("strUniqueId", "yy");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "Padma-Ramuni":
					objDictionary.put("strBootstrapPort","6690");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.204");
					objDictionary.put("strSilverBullet", "10.10.102.205");
					objDictionary.put("strLocation", "-93.4329995 44.9765394");
					objDictionary.put("strMeterGroup", "Padma's Meter Group");
					objDictionary.put("strMeterName", "Padma");
					objDictionary.put("strMeterSpotName", "Padma");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup8");
					objDictionary.put("strUniqueId", "gg");
					break;
				case "Uma-1213":
					objDictionary.put("strBootstrapPort","6691");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.190");
					objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.4329995 44.9765394");
					objDictionary.put("strMeterGroup", "Uma's Meter Group");
					objDictionary.put("strMeterName", "Uma");
					objDictionary.put("strMeterSpotName", "Uma");
					objDictionary.put("strMunicipality", "Excelsior, MN");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup9");
					objDictionary.put("strUniqueId", "hh");
					break;
				case "AVMeter1":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436453 44.971744");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "VMeter1");
					objDictionary.put("strMeterAddress", "726 Groveland Ave");
					objDictionary.put("strMeterSpotName", "AVMeter1");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup11");
					objDictionary.put("strUniqueId", "ii");
					break;
				case "AVMeter2":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436252 44.972099");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "VMeter1");
					objDictionary.put("strMeterAddress", "726 Groveland Ave");
					objDictionary.put("strMeterSpotName", "AVMeter2");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup12");
					objDictionary.put("strUniqueId", "jj");
					break;
				case "AVMeter4":
					String strMunicipality = objDictionary.get("strMunicipality");
					if(strMunicipality.equals ("Excelsior, MN"))
					{
						objDictionary.put("strBootstrapPort","6692");
						objDictionary.put("strEnvironment", "SG");
						objDictionary.put("strDeviceId", strDeviceId);
						//objDictionary.put("strHost", "10.10.102.190");
						//objDictionary.put("strSilverBullet", "10.10.102.191");
						objDictionary.put("strLocation", "-93.436315 44.972122");
						objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
						objDictionary.put("strMeterName", "AVMeter4");
						objDictionary.put("strMeterAddress", "726 Groveland Ave");
						objDictionary.put("strMeterSpotName", "AVMeter4");
						objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup7");
						objDictionary.put("strUniqueId", "az");
					}
					else if (strMunicipality.equals ("A Taxes Test"))
					{
						objDictionary.put("strBootstrapPort","6692");
						objDictionary.put("strEnvironment", "QA");
						objDictionary.put("strDeviceId", strDeviceId);
						//objDictionary.put("strHost", "10.10.102.190");
						//objDictionary.put("strSilverBullet", "10.10.102.191");
						objDictionary.put("strLocation", "-93.436315 44.972122");
						objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
						objDictionary.put("strMeterName", "AVMeter4");
						objDictionary.put("strMeterAddress", "726 Groveland Ave");
						objDictionary.put("strMeterSpotName", "AVMeter4");
						objDictionary.put("strMunicipality", "A Taxes Test");
						objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup13");
						objDictionary.put("strUniqueId", "av");
					}
					else
					{
						objDictionary.put("strBootstrapPort","6692");
						objDictionary.put("strEnvironment", "SG");
						objDictionary.put("strDeviceId", strDeviceId);
						//objDictionary.put("strHost", "10.10.102.190");
						//objDictionary.put("strSilverBullet", "10.10.102.191");
						objDictionary.put("strLocation", "-93.436315 44.972122");
						objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
						objDictionary.put("strMeterName", "AVMeter4");
						objDictionary.put("strMeterAddress", "726 Groveland Ave");
						objDictionary.put("strMeterSpotName", "AVMeter4");
						objDictionary.put("strMunicipality", "AutomationMunicipality");
						objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup13");
						objDictionary.put("strUniqueId", "av");
					}
					break;
				case "AVMeter5":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436388 44.972142");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "AVMeter5");
					objDictionary.put("strMeterAddress", "726 Groveland Ave");
					objDictionary.put("strMeterSpotName", "AVMeter5");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup13");
					objDictionary.put("strUniqueId", "kk");
					break;
				case "AVMeter6":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436273 44.972307");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "AVMeter6");
					objDictionary.put("strMeterAddress", "726 Groveland Ave");
					objDictionary.put("strMeterSpotName", "AVMeter6");
					//objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup13");
					objDictionary.put("strUniqueId", "kk");
					break;
				case "AVMeter7":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.435925 44.972249");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "AVMeter5");
					objDictionary.put("strMeterAddress", "726 Groveland Ave");
					objDictionary.put("strMeterSpotName", "AVMeter7");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup13");
					objDictionary.put("strUniqueId", "kk");
					break;
				case "IVMeter1":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436318 44.972204");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "IVMeter1");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterSpotName", "IVMeter1");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup14");
					objDictionary.put("strUniqueId", "mm");
					break;
				case "IVMeter4":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436318 43.972204");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "IVMeter4");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterSpotName", "IVMeter4");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup14");
					objDictionary.put("strUniqueId", "mm");
					break;
				case "VMeter4":
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					//objDictionary.put("strHost", "10.10.102.190");
					//objDictionary.put("strSilverBullet", "10.10.102.191");
					objDictionary.put("strLocation", "-93.436318 44.972204");
					objDictionary.put("strMeterGroup", "VirtualAutomationMeterGroup");
					objDictionary.put("strMeterName", "VMeter4");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterSpotName", "VMeter4");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup14");
					objDictionary.put("strUniqueId", "mm");
					break;
				case "7616-7617"://Global Ricky's
					objDictionary.remove("strRemotePath");
					objDictionary.put("strRemotePath", "http://localhost:9993");
					objDictionary.put("strBootstrapPort","6693");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.65");
					objDictionary.remove("strMeterUser");objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strSilverBullet", "10.10.100.65");
					objDictionary.put("strLocation", "44.9728 -93.439");
					objDictionary.put("strMeterGroup", "Test Meters");
					objDictionary.put("strMeterAddress", "");
					objDictionary.put("strMeterName", "7616");//Space
					objDictionary.put("strMeterSpotName", "7616");
					objDictionary.put("strMunicipality", "Like Royal Oak");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup15");
					objDictionary.put("strFamily", "Normal");
					objDictionary.put("strReservationBlockGroup", "ReservationRateBlockGroup15");
					objDictionary.put("strPermitGroup", "Automation Permit Group");
					objDictionary.put("strUniqueId", "ga");
					objDictionary.put("strVehicleParkType", "occupy");
					objDictionary.put("strVehicleDepartType", "empty");
					//objDictionary.put("strRemoteUser1", "seco");//Remote User
					//Global Pay Settings #1
					//objDictionary.put("strRemoteUser1", "seco");//Remote User
					//objDictionary.put("strRemoteDeviceId1", "7616-7617");
					//objDictionary.put("strRemoteHost1", "10.10.101.201");
					//Global Pay Settings #2
					//objDictionary.put("strRemoteUser2", "seco");//Remote User
					//objDictionary.put("strRemoteDeviceId2", "5510-5511");
					//objDictionary.put("strRemoteHost2", "10.10.101.202");
					break;
				//****Staging End****

				//****Production Start****
				case "9107-9108":
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "PROD");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.102.198");
					objDictionary.put("strSilverBullet", "10.10.102.199");
					objDictionary.put("strLocation", "-93.433898 44.951701");
					objDictionary.put("strMeterGroup", "Automation");
					objDictionary.put("strMeterName", "9107-9108");
					objDictionary.put("strMeterSpotName", "9107");
					objDictionary.put("strMunicipality", "Minnetonka");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup");
					objDictionary.put("strUniqueId", "aa");
					break;
				//LOTS 
				case "Zone 1 Entry":
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
		 			objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.59");
					objDictionary.put("strSilverBullet", "10.10.102.243");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Zone 1");
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "Lot Auto On");//Space
					objDictionary.put("strMeterSpotName", "Lot");
					objDictionary.put("strMunicipality", "Like Tufts");
					objDictionary.put("strRateBlockGroup", "Ang Tufts ");
					objDictionary.put("strUniqueId", "z2");
					objDictionary.put("strReservationBlockGroup", "");	
					break;
				case "Lot Auto One Pay"://Staging
					objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.6");
					objDictionary.put("strSilverBullet", "10.10.101.6");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Lot Auto One");
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "Lot Auto One");//Space
					//objDictionary.put("strMeterSpotName", "Lot Auto One");
					objDictionary.put("strMeterSpotName", "Lot Auto One Entry");
					objDictionary.put("strMunicipality", "Lot Automation");
					objDictionary.put("strRateBlockGroup", "LotAutomationRateBlockGroup6");
					objDictionary.put("strUniqueId", "z3");
					objDictionary.put("strReservationBlockGroup", "");
					objDictionary.put("strLotEntryId", "laoneentry");
					objDictionary.put("strLotExitId", "laoneexit");
					//objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "LA Pay"://PROD
					objDictionary.put("strMeterUser", "seco");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "PROD");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.101.5");
					objDictionary.put("strSilverBullet", "10.10.101.5");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Lot Automation");
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "Lot Automation");//Space
					//objDictionary.put("strMeterSpotName", "Lot Auto One");
					objDictionary.put("strMeterSpotName", "LA Entry");
					objDictionary.put("strMunicipality", "Minnetonka");
					objDictionary.put("strRateBlockGroup", "LotAutomationRateBlockGroup1");
					objDictionary.put("strUniqueId", "aa");
					objDictionary.put("strReservationBlockGroup", "");
					objDictionary.put("strLotEntryId", "prodlaentry");
					objDictionary.put("strLotExitId", "prodlaexit");
					//objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "Taxes Test Lot R":
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "QA");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.152");
					objDictionary.put("strSilverBullet", "10.10.102.243");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "Taxes Test Lot R");
					objDictionary.put("strMeterAddress", "900 Demo Street");
					objDictionary.put("strMeterName", "Taxes Pay R");//Space
					objDictionary.put("strMeterSpotName", "Taxes Test Lot R");
					objDictionary.put("strMunicipality", "A Taxes Test");
					objDictionary.put("strRateBlockGroup", "LotAutomationRateBlockGroup6");
					objDictionary.put("strUniqueId", "z3");
					objDictionary.put("strReservationBlockGroup", "");
					objDictionary.put("strLotEntryId", "qtaxentryr");
					objDictionary.put("strLotExitId", "qtaxexitr");
					//objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				case "AutoOpenPay1":
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6685");
					objDictionary.put("strEnvironment", "SG");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.100.136");
					objDictionary.put("strSilverBullet", "10.10.102.243");
					objDictionary.put("strLocation", "-93.43552 44.972176");
					objDictionary.put("strMeterGroup", "AutomationOpenLot");
					//objDictionary.put("strMeterGroup", "Lot Parking");
					objDictionary.put("strMeterAddress", "- - - NA - - -");
					objDictionary.put("strMeterName", "AutoOpenPay1");//Space
					objDictionary.put("strMeterSpotName", "Lot");
					objDictionary.put("strMunicipality", "AutomationMunicipality");
					objDictionary.put("strRateBlockGroup", "AutomationMunicipalityRateBlockGroup10");
					objDictionary.put("strUniqueId", "z4");
					objDictionary.put("strReservationBlockGroup", "");
					objDictionary.put("strLotEntryId", "AutoOpenEntry");
					objDictionary.put("strLotExitId", "AutoOpenExit");
					//objDictionary.put("strPermitGroup", "Automation Permit Group");
					break;
				//****Production Canada****
				case "8336-8337":
					objDictionary.put("strMeterUser", "root");
					objDictionary.put("strBootstrapPort","6692");
					objDictionary.put("strEnvironment", "PROD");
					objDictionary.put("strDeviceId", strDeviceId);
					objDictionary.put("strHost", "10.10.103.128");
					objDictionary.put("strSilverBullet", "10.10.103.129");
					objDictionary.put("strLocation", "-93.433898 44.951701");
					objDictionary.put("strMeterGroup", "Automation");
					objDictionary.put("strMeterName", "8336-8337");
					objDictionary.put("strMeterSpotName", "8336");
					objDictionary.put("strMunicipality", "Minnetonka");
					objDictionary.put("strRateBlockGroup", "AutomationRateBlockGroup");
					objDictionary.put("strUniqueId", "aa");
					break;
				default:
					String strErrorMsg = "The user ("+strDeviceId+") has not been added to "+strMethodName+"-GlobalClass";
					//JOptionPane.showMessageDialog(null, strErrorMsg);
					objDictionary.put("strErrorMsg", strErrorMsg);
					Reporter.log(strErrorMsg);
					//Assert.fail(strErrorMsg);
			}
		}
	}

	public void startServer(Map<String, String> objDictionary)
	{
    	String strAppiumPort = objDictionary.get("strAppiumPort");
    	String strBootstrapPort = objDictionary.get("strBootstrapPort");
    	String strAutomationUser = System.getProperty("user.name");
    	if(strAutomationUser.equals("Padma")){strAutomationUser = "chris";}
    	//Used When Executing from Jenkins
    	if(strAutomationUser.equals("jenkins")){strAutomationUser = "mpsadmin";}
    	Reporter.log("Start Server Automation User: "+strAutomationUser);
		CommandLine command = new CommandLine("appium");
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument(strAppiumPort);
		command.addArgument("-bp", false);
		command.addArgument(strBootstrapPort);
		command.addArgument("--session-override", true);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try
		{
			executor.execute(command, resultHandler);
			Thread.sleep(5000);
			String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
			System.out.println("Appium server started-"+timeStamp);
			Reporter.log("Appium server started-"+timeStamp);
		}
		catch (IOException e)
		{e.printStackTrace();}
		catch (InterruptedException e)
		{e.printStackTrace();}
	}
    public void stopServer(Map<String, String> objDictionary)
	{
    	//Kill Existing AppiumPort
		String strAppiumPort = objDictionary.get("strAppiumPort");
		//Test Code Connection refused (Connection refused)
		String[] command3 = {"sh", "-c", "ps -ef | grep "+strAppiumPort+"| awk '{print $2'} | xargs kill -9"};
		try
		{
			Runtime.getRuntime().exec(command3);
			System.out.println("Appium server stopped. Port("+strAppiumPort+")");
		}catch (IOException e) {e.printStackTrace();}
		try {Thread.sleep(5000);}catch (Exception e) {}
		String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
		Reporter.log("Appium server stopped-"+timeStamp);

	}

}

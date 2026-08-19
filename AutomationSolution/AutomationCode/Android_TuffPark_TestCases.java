package AutomationCode;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
@Listeners({ EmailableReporter2.class, EmailableReporter3.class, ListenersClass.class})
public class Android_TuffPark_TestCases
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
			clsMeter.METER_CopyLogTraceLocally(objDictionary, null,  strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			//clsMeter.METER_CopyMessageTraceLocally(objDictionary, strTestSuiteName+"_"+strTestCaseName+"_"+strInvocationCounter);
			String strResetRateBlocksCounter = objDictionary.get("strResetRateBlocksCounter");if(strResetRateBlocksCounter == null) {strResetRateBlocksCounter = "0";}
		}
	}

	//********************************************************************************************************************
	//ANDROID-TEST CASES
	//********************************************************************************************************************
	@Test(priority=2001,groups={"Smoke"})
	public void A2001_ValidateTuffUserAgreement()
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
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Header", 1, "Value", "END USER LICENSE AGREEMENT and TERMS OF SERVICE");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Section 1", 1, "Value", "Tufts University in conjunction with Municipal Parking Services, Inc. (“MPS” or “Licensor”) want you to be safe.  Do not use the Tufts Parking Application while driving a vehicle.  ");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Section 2", 1, "Value", "By using any of the online features, services, or applications (hereinafter the “Tufts Parking Application”), you agree to be bound by the following terms of this End User License Agreement (“EULA”) between you and Tufts University (“Tufts”) in conjunction with MPS. IF YOU DO NOT AGREE TO BE BOUND BY THE TERMS OF THIS EULA, YOU MAY NOT USE THE TUFTS PARKING APPLICATION AND WILL BE REQUIRED TO USE THE KIOSK LOCATED NEARBY.  ");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Section 3", 1, "Value", "Every time you use the Tufts Parking Application you agree to be bound by the terms of this EULA, which may be updated from time-to-time, by Tufts’ and Licensor’s mutual agreement and in their discretion, without prior notice to you.  ");
		//Scroll Up
		WebElement objFrame = androiddriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView"));
		ElementSwipe  clsElementSwipe  = new ElementSwipe();
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Section 4", 1, "Value", "THIS IS A LEGAL AGREEMENT. YOU REPRESENT AND WARRANT THAT YOU HAVE THE RIGHT, AUTHORITY, AND CAPACITY TO ACCEPT AND AGREE TO THIS EULA. YOU REPRESENT THAT YOU ARE OF SUFFICIENT LEGAL AGE IN YOUR JURISDICTION OR RESIDENCE TO USE OR ACCESS THE TUFTS PARKING APPLICATION AND TO ENTER INTO THIS EULA. IF YOU DO NOT AGREE WITH ANY OF THE PROVISIONS OF THESE TERMS, YOU SHOULD CEASE ACCESSING OR USING THE TUFTS PARKING APPLICATION AND YOU WILL BE REQUIRED TO USE THE KIOSK LOCATED NEARBY.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Convenience", 1, "Value", "The Tufts Parking Application is provided to you as a convenience only. By merely providing access to the Tufts Parking Application, Tufts and Licensor do not warrant or represent that: (a) any materials, documents, images, graphics, logos, design, audio, video and any other information provided from or on the Tufts Parking Application (collectively, the “Content”) is accurate or complete; (b) the Content is up-to-date or current; (c) Tufts or Licensor have any obligation to update any Content; (d) the Content is free from technical inaccuracies or programming or typographical errors; (e) the Content is free from changes caused by a third party; (f) your access to the Tufts Parking Application will be free from interruptions, errors, computer viruses or other harmful components; and/or (g) any information obtained in response to questions asked through the Tufts Parking Application is accurate or complete.");
		//Scroll Up
		objFrame = androiddriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView"));
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Modifications", 1, "Value", "Licensor and Tufts may from time to time, by mutual agreement, make changes to the Tufts Parking Application, which may include adding, updating, or discontinuing the Tufts Parking Application, or parts thereof.  These changes may be made without providing any notice to you or receiving your consent. ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Restrictions", 1, "Value", "You agree not to use the Tufts Parking Application for any use other than for the utilization of the services as contemplated herein or in any other applicable agreement. You will not permit others or third parties to utilize the Tufts Parking Application.  You further agree to not engage, nor allow a third party to engage, in any of the following: scraping, copying, caching, creating new content, recreating content, hacking, or circumventing (including fees) of the Tufts Parking Application.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Ownership", 1, "Value", "The Tufts Parking Application and all worldwide copyrights, trademarks, service marks, trade secrets, and other intellectual property rights therein (other than Tufts’ tradenames, trademarks, services marks and logos) are the exclusive property of Licensor. Tufts’ tradenames, trademarks, service marks and logos are the exclusive property of Tufts.  Licensor and Tufts reserve all rights in and to the Tufts Parking Application. Except as otherwise set forth herein, there are no explicit or implied licenses in this EULA. All suggestions or feedback provided by you to Licensor with respect to the Tufts Parking Application shall be Licensor’s sole property. Proffering of such suggestions, ideas, or feedback shall constitute an affirmative assignment of such, and you hereby irrevocably do assign to Licensor all right, title, and interest in the foregoing. Licensor may use, copy, modify, publish, or redistribute such submission and its contents for any purpose and in any way without any notice or compensation to you. You also agree that Licensor does not waive any rights to use similar or related ideas previously known to Licensor, developed by its employees, or obtained from other sources.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Consent to Use of Data, Images and Other Information", 1, "Value", "You agree that Licensor and its subsidiaries and agents may collect, maintain, process and use data, images and audio relating to you, your vehicle and any information you provide while using the Tufts Parking Application; such information includes but it not limited to, your name, birthdate, address, phone number, email address, home address, billing information, information about your vehicle, and any other personal identification information you provide (“User Data”), provided that such User Data may only be used as set forth below.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Consent to Use of Data, Images and Other Information Part 2", 1, "Value", "For example, when you create an account to use the Tufts Parking Application, you will provide User Data that may include, but is not limited to, the following: ");
		clsCommonMobile.VerificationPointView(objDictionary,androiddriver,"User Agreement", "Data Types", 1, "Item Order", "Name;|User Profile;|Physical Address;|Phone Number (Preferably A Mobile Phone Capable Of Receiving Text Messages);|Email Address;|Payment Information such as Paypal, Credit Card and Bank Account Information (Processed Via Third Parties) That You Provide To Us;|Vehicle License Plate Number; |Vehicle Information (Make, Model, Color And Year);|Feedback; and|Account Password.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Consent to Use of Data, Images and Other Information Part 3", 1, "Value", "By using the Tufts Parking Application you agree to the automatic collection of User Data, including, but not limited to, the following:");
//		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointView(objDictionary,androiddriver,"User Agreement", "User Data", 1, "Item Order", "Photo View Of The Parking Space;|Photo/Video Of Your Vehicle;|Photo/Video Image Of User;|Audio Of User;|Physical Location;|Length Of Time In A Parking Space;|Length Of Time In An Expired Parking Space; and |Length Of Time Searching For A Parking Space.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Consent to Use of Data, Images and Other Information Part 4", 1, "Value", "Location information is generally collected from your mobile phone’s GPS location at the time you access the Tufts Parking Application.  Licensor may also use your GPS location data upon driving in proximity to an area using the Tufts Parking Application.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Consent to Use of Data, Images and Other Information Part 5", 1, "Value", "Consent to Use of Data: You agree that Licensor and Tufts may collect and use the User Data and the personal identification information associated with the User Data that is gathered only to facilitate the Tufts Parking Application and to processing parking permits, parking payments, tickets and other parking approvals.  Licensor and Tufts may also use this information to improve its products or services, or to provide services or technologies to you.  YOUR USER DATA WILL NOT BE USED TO PLACE THIRD PARTY ADVERTISING MATERIALS ON YOUR DEVICES OR TO SOLICIT BUSINESS FROM YOU OR OTHERWISE PROMOTE OTHER PRODUCTS OR SERVICES. NEITHER LICENSOR NOR TUFTS UNIVERSITY WILL SELL YOUR INFORMATION TO THIRD PARTIES OR OTHERWISE ASSIGN OR TRANSFER YOUR INFORMATION FOR COMMERCIALIZATION PURPOSES.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Consent to Use of Data, Images and Other Information Part 6", 1, "Value", "Data Privacy: You agree that Licensor and Tufts may process your User Data, including but not limited to the data and information listed in this section, your vehicle data and the personal identification information associated with your vehicle, location data, technical and related information about your use of a parking space, software which may include internet protocol address, hardware identification, operating system, application software, peripheral hardware, personally and non-personally identifiable Tufts Parking Application usage statistics to facilitate the online services and Licensor may transfer such information to other related companies of the Licensor from time to time for the use allowed herein. To the extent legally required, Licensor may share this information with governmental agencies and with law enforcement agencies.  Tufts may share this information with Tufts’ departments/divisions, with governmental agencies or law enforcement agencies for policy violation investigations or criminal enforcements, health and safety purposes and/or for other legal purposes.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		try {Thread.sleep(1000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Do No Use While Driving", 1, "Value", "You agree, represent and warrant, so long as YOU USE OR ACCESS THE TUFTS PARKING APPLICATION, THAT YOU WILL NOT, UNDER ANY CIRCUMSTANCES, ACCESS, VIEW, OR USE THE TUFTS PARKING APPLICATION WHILE DRIVING OR OTHERWISE OPERATING A VEHICLE OF ANY KIND (including, without limitation, a car, truck, motorcycle, motor scooter, or bicycle) or operating any dangerous equipment or machinery. You understand that using any handheld device in these circumstances is extremely dangerous, and can result in fines, property damage, physical injuries (including dismemberment) or death. You further agree, represent and warrant, that you will not use or access the Tufts Parking Application in any manner that places yourself or any other person at risk of injury, and that you will abide by all traffic laws and regulations. While effort is made to assure the accuracy of the information presented, the user is responsible for safe driving and for the consequences of decisions as to where to travel or where to drive.  Under no circumstance will Licensor or Tufts assume any responsibility or liability for the consequences of driving decisions made by you.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Do No Use While Driving Part 2", 1, "Value", "You expressly agree that Licensor and Tufts shall not be liable for any driving decisions made by you or at your suggestion or for any damages, injury or other harm caused by your use of or accessing the Tufts Parking Application, services or content, and waive any and all claims or causes of action you may have, now or in the future, arising from or relating to the same. In the event that any party names Licensor or Tufts as a defendant in a case involving your use of the Tufts Parking Application while operating a vehicle, you agree to indemnify and hold Licensor and Tufts harmless in such action.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Term and Termination", 1, "Value", "This EULA is effective on the date you begin using the Tufts Parking Application, and shall continue unless this EULA is terminated under this section. Licensor or Tufts may terminate this EULA and your ability to use the Tufts Parking Application at any time, and without notice to you, if you fail to comply with any term(s) hereof. You may terminate this EULA effective immediately upon written notice to Licensor. You may also terminate this EULA by deleting the Tufts Parking Application.   Upon termination of this EULA, your right to use the Tufts Parking Application will terminate immediately and you must stop all use of the Tufts Parking Application, but the terms of Sections 8 through 19 (inclusive) will remain in effect, after any such termination.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Warranty Disclaimer", 1, "Value", "NOTWITHSTANDING ANYTHING TO THE CONTRARY AND TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, LICENSOR AND TUFTS PROVIDE THE TUFTS PARKING APPLICATION “AS-IS” AND \"AS AVAILABLE\", WITH ALL FAULTS AND WITHOUT WARRANTY OF ANY KIND.  ");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Warranty Disclaimer Part 2", 1, "Value", "LICENSOR AND TUFTS DISCLAIM ALL WARRANTIES AND CONDITIONS, WHETHER EXPRESS, IMPLIED, OR STATUTORY, INCLUDING THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, QUIET ENJOYMENT, ACCURACY, AND NON-INFRINGEMENT OF THIRD-PARTY RIGHTS. LICENSOR AND TUFTS DO NOT GUARANTEE ANY SPECIFIC RESULTS FROM THE USE OF THE TUFTS PARKING APPLICATION. LICENSOR AND TUFTS MAKE NO WARRANTY THAT THE TUFTS PARKING APPLICATION WILL BE AVAILABLE, FUNCTIONAL, UNINTERRUPTED, FREE OF VIRUSES OR OTHER HARMFUL CODE, TIMELY, SECURE, OR ERROR-FREE.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Warranty Disclaimer Part 3", 1, "Value", "YOU USE THE TUFTS PARKING APPLICATION AT YOUR OWN DISCRETION AND RISK.  YOU WILL BE SOLELY RESPONSIBLE FOR (AND LICENSOR AND TUFTS DISCLAIM) ANY AND ALL LOSS, LIABILITY, OR DAMAGES, INCLUDING ANY COMPUTER, MOBILE DEVICE, OR OTHER ITEM OF YOURS OR A THIRD PARTY, RESULTING FROM YOUR USE OF THE TUFTS PARKING APPLICATION.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Limitation of Liability", 1, "Value", "Nothing in this EULA, and in particular within this \"Limitation of Liability\" clause, shall attempt to exclude liability that cannot be excluded under applicable law.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Limitation of Liability Part 2", 1, "Value", "TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, IN ADDITION TO THE ABOVE WARRANTY DISCLAIMERS, IN NO EVENT WILL (A) LICENSOR AND/OR TUFTS BE LIABLE FOR ANY CONSEQUENTIAL, EXEMPLARY, SPECIAL, OR INCIDENTAL DAMAGES, INCLUDING ANY DAMAGES FOR LOST DATA OR LOST PROFITS, ARISING FROM OR RELATING TO THE TUFTS PARKING APPLICATION, EVEN IF LICENSOR OR TUFTS KNEW OR SHOULD HAVE KNOWN OF THE POSSIBILITY OF SUCH DAMAGES, AND (B) LICENSOR’S AND TUFTS’ TOTAL CUMULATIVE LIABILITY ARISING FROM OR RELATED TO THE TUFTS PARKING APPLICATION , WHETHER IN CONTRACT OR TORT OR OTHERWISE, SHALL NOT EXCEED THE FEES ACTUALLY PAID BY YOU TO LICENSOR FOR ACCESS TO THE TUFTS PARKING APPLICATION IN THE PRIOR 12 MONTHS (IF ANY). THIS LIMITATION IS CUMULATIVE AND WILL NOT BE INCREASED BY THE EXISTENCE OF MORE THAN ONE INCIDENT OR CLAIM. LICENSOR AND TUFTS DISCLAIM ALL LIABILITY OF ANY KIND OF LICENSOR’S LICENSORS AND SUPPLIERS OR TUFTS AND ITS AFFILIATES AND VENDORS.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Indemnification", 1, "Value", "Unless prohibited by applicable law, you will indemnify and defend Licensor and Tufts, their agents, and assigns to the extent any event arises from your use of the Tufts Parking Application or services in violation of this Agreement.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Indemnification Part 2", 1, "Value", "You will promptly notify Licensor and Tufts in writing of any allegation(s), or legal proceeding at Tufts University: 62R Talbot Ave., Medford, MA 02155; Licensor: Municipal Parking Services, Inc., 12450 Wayzata Blvd., Suite 200, Minnetonka, MN 55305, and cooperate reasonably with Licensor and Tufts to resolve the allegation(s) or legal proceeding.  Licensor and Tufts may, in their respective sole discretion, appoint their own counsel, respectively, at their own expense.  You agree to not enter any settlement or to admit liability, pay money, or take (or refrain from taking) any action, without Licensor’s and Tufts’ consent, not to be unreasonably withheld, conditioned, or delayed.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges", 1, "Value", "You may be asked to setup an account prior to making any payments.  For a payment for parking made through the Tufts Parking Application to be valid, you must provide all accurate and complete information required by the Tufts Parking Application.  Information requested may include license plate numbers, parking zones, and parking time(s).  You will have to provide valid credit card information or other valid form of electronic payment permitted by the Tufts Parking Application.  Lastly, you must abide by all payment instructions and prompts set forth in the Tufts Parking Application.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges Part 2", 1, "Value", "You are solely responsible for the validity of the provided debit card or credit card, that it has sufficient available funds and is not blocked or inactive.  Once payment is received through the Tufts Parking Application, you will receive a confirmation.  It is your responsibility to check that this confirmation is received.  ");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges Part 3", 1, "Value", "You agree that, when using the Tufts Parking Application to make a payment, Licensor will charge the payment amount, including any applicable taxes, fees and service charges. All payments are non-refundable.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges Part 4", 1, "Value", "You bear the sole responsibility to follow any applicable parking rules, regulations, and timely payments.  If the Tufts Parking Application is not available, for whatever reason, or if you do not receive a confirmation that your payment has been received, it is your responsibility to pay the payment using another method or to pay the parking fee using the local kiosk. ");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges Part 5", 1, "Value", "It is your sole responsibility to monitor your parking session and any pertinent parking regulations set forth by Tufts and payments made by you, through any payment method including but not limited to cash, coin, credit card, and/or mobile application, for the duration of your parking session.  FAILURE TO RECEIVE AN ALERT, PUSH NOTIFICATION, PROMPT, OR REMINDER OF YOUR PARKING SESSION EXPIRATION DOES NOT NEGATE YOUR RESPONSIBILITY TO PAY FOR YOUR PARKING, NOR DOES SUCH FAILURE EXEMPT YOU FROM RECEIVING A PARKING TICKET.  LICENSOR AND TUFTS SHALL BEAR NO RESPONSIBILITY OR LIABILITY, AND SHALL NOT BE REQUIRED TO REIMBURSE USER FOR ANY FEES, FINES, TICKETS, AND THE LIKE, INCLUDING BUT NOT LIMITED TO, EXPENSES RELATED TO TOWING OF USER’S VEHICLE, OR ANY DAMAGE THAT MAY BE INCURRED AS RESULT THEREOF. ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges Part 6", 1, "Value", "You are solely responsible for (i) obtaining and maintaining all internet or other communications access, computer hardware and other equipment or electronic media necessary to utilize the Tufts Parking Application, (ii) any issues pertaining to your mobile computing device (including issues pertaining to any incompatibility of the Tufts Parking Application with your mobile computing device) and the data plan and data/cellular service that permit your mobile computing device to send and receive data wirelessly, and (iii) the data and content provided by you to Licensor and Tufts through the Tufts Parking Application.  LICENSOR AND TUFTS SHALL HAVE NO RESPONSIBILITY OR LIABILITY FOR ANALYSIS, DATA, RECOMMENDATIONS, OR OTHER SERVICES PROVIDED TO YOU BASED UPON INCORRECT OR INCOMPLETE DATA PROVIDED BY YOU.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Account Setup, Payment and Charges Part 7", 1, "Value", "YOU SHALL HOLD LICENSOR AND TUFTS HARMLESS IN ANY DISPUTE BETWEEN YOU AND EACH OF THE FOLLOWING: GOVERNMENTAL ENTITIES, YOUR CREDIT CARD COMPANY AND YOUR CELL PHONE COMPANY OR OTHER MOBILE DATA PROVIDER.  LICENSOR AND TUFTS ARE NOT RESPONSIBLE FOR ANY DISPUTES REGARDING PARKING TICKETS, TOWING OF YOUR VEHICLE, OR OTHER FEES OR ISSUES THAT MAY ARISE WITH RESPECT TO ANY PARKING INFRACTION, FAILURE TO PAY, FAILURE TO FOLLOW A PARKING LOT’S INSTRUCTIONS, OR THE LIKE. ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Forum Selection Clause", 1, "Value", "All disputes shall be exclusively brought in state or federal court located in the Commonwealth of Massachusetts.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Choice of Law", 1, "Value", "You agree that this EULA, and any claim, dispute, action, cause of action, issue, or request for relief relating to this EULA and/or your use of the Tufts Parking Application, will be governed by the laws of Massachusetts, without giving effect to any conflicts of laws principles that require the application of the laws of a different jurisdiction. ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Assignment", 1, "Value", " Neither the rights nor the obligations arising under this EULA are assignable by you, and any such attempted assignment shall be void and without effect.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Notices", 1, "Value", "Any notice to you may be provided by email to the address that you registered with Licensor.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Severability", 1, "Value", "If any provision of this EULA is unenforceable, such provision will be changed and interpreted to accomplish the objectives of such provision to the greatest extent possible under applicable law and the remaining provisions will continue in full force and effect.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Waiver", 1, "Value", "All waivers by Licensor or Tufts will be effective only if in writing. Any waiver or failure by Licensor or Tufts to enforce any provision of this EULA on one occasion will not be deemed a waiver of any other provision or of such provision on any other occasion.   Licensor may not waive Tufts’ rights and Tufts may not waive Licensor’s rights.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement General Terms", 1, "Value", "You are responsible for establishing and maintaining your own password (if establishing an account). Licensor may provide you with a single sign-on option, whereby another account and password (e.g., Tufts University Student Account) (“external account”) may be used by you to access the Tufts Parking Application. By using the single sign-on option you hereby grant Licensor the right to receive, link, and use information from the external account, including but not limited to passwords associated with the external account, to access and use the Tufts Parking Application.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement General Terms Part 2", 1, "Value", "You acknowledge that transmission of data over the internet and wireless devices involves unique transmission risks that cannot be fully secured against unauthorized access.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement General Terms Part 3", 1, "Value", "The Tufts Parking Application is deemed irrevocably accepted upon your use of the Tufts Parking Application. Licensor and Tufts will have no responsibility to provide maintenance or support services with respect to the Tufts Parking Application. The parties are independent contractors.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement General Terms Part 4", 1, "Value", "You acknowledge that the Tufts Parking Application contains valuable trade secrets and proprietary information of Licensor and Tufts, that any actual or threatened breach of Section 3 (Restrictions) or Section 4 (Ownership) of this EULA will constitute immediate, irreparable harm to Licensor and/or Tufts, respectively, for which monetary damages would be an inadequate remedy, and that Licensor and/or Tufts may seek injunctive relief for such breach.");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Entire Agreement", 1, "Value", "This EULA constitute the entire agreement among the parties regarding the use of the Tufts Parking Application.  This EULA (as it may from time to time be amended, restated, or otherwise modified) supersedes any prior agreements, understandings, or negotiations, whether written or oral.  ");
		//Scroll Up
		clsElementSwipe.Swipe(androiddriver,objFrame,"SWIPE_UP");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Acknowledgement", 1, "Value", "BY USING THE TUFTS PARKING APPLICATION OR ACCESSING LICENSOR’S WEBSITE(S), YOU ACKNOWLEDGE THAT YOU HAVE READ THESE TERMS OF USE AND AGREE TO BE BOUND BY THEM.");
		clsCommonMobile.VerificationPointText(objDictionary,androiddriver, "User Agreement", "Tufts User Agreement Revision Date", 1, "Value", "Revision Date:  December 16, 2021");
		androiddriver.quit();
		clsMeter.METER_SetMeterEndTime(objDictionary);
		String strAssociatedBug = objDictionary.get("strAssociatedBug");if(strAssociatedBug == null){strAssociatedBug = "";}
		if(!strAssociatedBug.equals("")){Reporter.log("<font color='Blue'>"+strAssociatedBug+"-This Test passed remove the AssociatedBug</font>");}
	}
	@Test(priority=2002,groups={"Smoke"})
	public void A2002_PP_DPSL()
	{
  		objDictionary.put("strAssociatedBug", "");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit                                             ");
  		Reporter.log("DPSL: Delte Permit in Sentry Link                               ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "02"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","False","Parker"); //GPS is False
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
  	 	clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
  		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
  		try {Thread.sleep(2000);}catch (Exception e) {}
  		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
  		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
  		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "App Account",1);
  		CommonWeb clsCommonWeb = new CommonWeb();
  		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
  		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		androiddriver.quit();
		//Manually Delete Permit
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Permit Groups", "Local");
		//Click Permit Group
		clsCommonWeb.ClickLink(objDictionary, driver, "Permit Group", "Visitor", 1);
		//Populate Search
		clsCommonWeb.PopulateAction(objDictionary, driver, "Permit Group", "Populate Search Permit Group", "{T} Search Permit Groups",strLicensePlateNumber);
  		//Click Find Permits
		clsCommonWeb.ClickButton(objDictionary,driver, "Permit Group", "Find Permits", 1,"Local");
		//Click View Permits
		clsCommonWeb.ClickButton(objDictionary,driver, "Permit Groups", "View Permits", 1,"Local");
		//Populate Search
		clsCommonWeb.PopulateAction(objDictionary, driver, "Permit Groups", "Populate Search", "{T} Search",strLicensePlateNumber);
		//Click Find Permit
		clsCommonWeb.ClickButton(objDictionary,driver, "Permit Groups", "Find Permit", 1,"Local");
		//Click Find Permit
		clsCommonWeb.ClickButton(objDictionary,driver, "Parking Permits", "Delete Now", 1,"Local");
		//Validate the Message Permit was successfully deleted.
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Permits", "Permit was successfully deleted.", 1, "Contains", "Permit was successfully deleted.");
		driver.quit();
	}
	@Test(priority=2003,groups={"Smoke"})
	public void A2003_PP_LP_ATDPSL()
	{
		objDictionary.put("strAssociatedBug", "185922382");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit                                             ");
  		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("ATDPSL: Attempt to Delte Permin in Sentry Link                  ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "03"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber.toUpperCase(),"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		try {Thread.sleep(2000);}catch (Exception e) {}
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "App Account",1);
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		androiddriver.quit();
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		//Manually Delete Permit
		String strBrowser = objDictionary.get("strBrowser");
		String strRemotePath = objDictionary.get("strRemotePath");
		threadDriver = clsCommonWeb.SetDriverBrowser(strBrowser, strRemotePath, objDictionary);
		WebDriver driver = getDriver();
		clsCommonWeb.SENTRYLINK_OpenLoginPage(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_AdminLoginIn(objDictionary, driver);
		String strCurrentPageName = clsCommonWeb.GetCurrentPageName(objDictionary, driver);
		clsCommonWeb.SENTRYLINK_NavigateToPage2(objDictionary, driver, strCurrentPageName, "Permit Groups", "Local");
		//Click Permit Group
		clsCommonWeb.ClickLink(objDictionary, driver, "Permit Group", "Visitor", 1);
		//Populate Search
		clsCommonWeb.PopulateAction(objDictionary, driver, "Permit Group", "Populate Search Permit Group", "{T} Search Permit Groups",strLicensePlateNumber);
  		//Click Find Permits
		clsCommonWeb.ClickButton(objDictionary,driver, "Permit Group", "Find Permits", 1,"Local");
		//Click View Permits
		clsCommonWeb.ClickButton(objDictionary,driver, "Permit Groups", "View Permits", 1,"Local");
		//Populate Search
		clsCommonWeb.PopulateAction(objDictionary, driver, "Permit Groups", "Populate Search", "{T} Search",strLicensePlateNumber);
		//Click Find Permit
		clsCommonWeb.ClickButton(objDictionary,driver, "Permit Groups", "Find Permit", 1,"Local");
		//Click Find Permit
		clsCommonWeb.ClickButton(objDictionary,driver, "Parking Permits", "Delete Now", 1,"Local");
		//Validate the Message Permit was successfully deleted.
		clsCommonWeb.VerificationPointText(objDictionary, driver, "Parking Permits", "Permit was successfully deleted.", 1, "Does Not Exist", "");
		driver.quit();
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
	}
	@Test(priority=2004,groups={"Smoke"})
	public void A2004_PP_LP_LE_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit                                             ");
  		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VPSH: Validate Parking Session                                  ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "04"+strSpaceName.toUpperCase()+"AA";
		double dblPermitCost = 1.25;
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		//Validate 2 Tabs Exist (Create a Function Around)
		try
		{
			WebElement ObjScrollView = androiddriver.findElement(By.id("com.mpspark.tuftspark.MPS:id/tabs"));
			List<WebElement> tabs = ObjScrollView.findElements(By.id("com.mpspark.tuftspark.MPS:id/textViewIcon"));
			int intNumberOfTabs = tabs.size();
			if(intNumberOfTabs == 2){Reporter.log("The Tufts App correctly displayed 2 Tabs");}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Tufts did not display 2 Tabs - Actual Number ("+intNumberOfTabs+")");}
		}
		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"No Tabs existed on the Tufts App");}
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "App Account",1);
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN", "zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Validate Active Sessions
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "Click to view active permits",1);
		clsCommonMobile.VerificationPointLink(objDictionary, androiddriver, "Permits", "Account Details", 1, "Exists");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permit", "Permit Valid From", 0, "Value", "Valid From : "+strDay+" at "+strStartTime);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permit", "Permit Valid To", 0, "Value", "Valid To : "+strDay+" at "+strEndTime);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permit", "Account Details",1);
		//Validate the Active Session Tab Exists.
		try
		{
			WebElement ObjScrollView = androiddriver.findElement(By.id("com.mpspark.tuftspark.MPS:id/tabs"));
			List<WebElement> tabs = ObjScrollView.findElements(By.id("com.mpspark.tuftspark.MPS:id/textViewIcon"));
			int intNumberOfTabs = tabs.size();
			if(intNumberOfTabs == 3){Reporter.log("The Tufts App correctly displayed 3 Tabs");}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Tufts did not display 3 Tabs - Actual Number ("+intNumberOfTabs+")");}
			if (tabs.size() > 1) {
				WebElement secondTab = tabs.get(1);secondTab.click();
	            System.out.println("The Active sesson tab was clicked");
	            try {Thread.sleep(4000);}catch (Exception e) {}
	        }
			else {
	        	clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Active Session Tab Did not exist");
	        }
		}
		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"No Tabs existed on the Tufts App");}
		//Validate Tufts Parking Sessions
		String strParkedTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number - Tufts", 0, "Value",strLicensePlateNumber);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 0, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at - Tufts", 0, "Value", "Parked Today at "+strParkedTimestamp);
		//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		//The below method no longer works
		//clsHttpConnections.HTTPCONNECTIONS_StoreLotParkingId(objDictionary, "Local", strLicensePlateNumber);
  		String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  		double dblOriginalAccountBalanceMinusPermitCost = Double.parseDouble(strOriginalAccountBalance) - dblPermitCost;
  		String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = (Double.parseDouble(strCurrentAccountBalance) * .01);

  		}catch (Exception e) {}
  		DecimalFormat df = new DecimalFormat("#.##");
		String strUserAccountBalance = df.format(dblStringUserAccountBalance);
		String strOriginalAccountBalanceMinusPermitCost = df.format(dblOriginalAccountBalanceMinusPermitCost);
		if(strOriginalAccountBalanceMinusPermitCost.equals(strUserAccountBalance))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		Android_TuffPark_ParkingSessions clsAndroidTuffParkParkingSessions = new Android_TuffPark_ParkingSessions();
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2004_PurchasePermit_Park_Leave_VPSH(objDictionary,strLicensePlateNumber);
	}
	@Test(priority=2005,groups={"Smoke"})
	public void A2005_PP_GP_LP_LE_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP_GP: Purchase Permit Google Pay                               ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VPSH: Validate Parking Session                                  ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "04"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		//Validate 2 Tabs Exist (Create a Function Around)
		try
		{
			WebElement ObjScrollView = androiddriver.findElement(By.id("com.mpspark.tuftspark.MPS:id/tabs"));
			List<WebElement> tabs = ObjScrollView.findElements(By.id("com.mpspark.tuftspark.MPS:id/textViewIcon"));
			int intNumberOfTabs = tabs.size();
			if(intNumberOfTabs == 2){Reporter.log("The Tufts App correctly displayed 2 Tabs");}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Tufts did not display 2 Tabs - Actual Number ("+intNumberOfTabs+")");}
		}
		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"No Tabs existed on the Tufts App");}
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		try {Thread.sleep(2000);}catch (Exception e) {}
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Meter Payment", "OTHER PAYMENT OPTIONS", 1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Account Details", "Google Pay", 1);
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Google Pay", "Continue", 1);
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Validate Active Sessions
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "Click to view active permits",1);
		clsCommonMobile.VerificationPointLink(objDictionary, androiddriver, "Permits", "Account Details", 1, "Exists");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permit", "Permit Valid From", 0, "Value", "Valid From : "+strDay+" at "+strStartTime);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permit", "Permit Valid To", 0, "Value", "Valid To : "+strDay+" at "+strEndTime);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permit", "Account Details",1);
		//Validate the Active Session Tab Exists.
		try
		{
			WebElement ObjScrollView = androiddriver.findElement(By.id("com.mpspark.tuftspark.MPS:id/tabs"));
			List<WebElement> tabs = ObjScrollView.findElements(By.id("com.mpspark.tuftspark.MPS:id/textViewIcon"));
			int intNumberOfTabs = tabs.size();
			if(intNumberOfTabs == 3){Reporter.log("The Tufts App correctly displayed 3 Tabs");}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Tufts did not display 3 Tabs - Actual Number ("+intNumberOfTabs+")");}
			if (tabs.size() > 1) {
				WebElement secondTab = tabs.get(1);secondTab.click();
	            System.out.println("The Active sesson tab was clicked");
	            try {Thread.sleep(4000);}catch (Exception e) {}
	        }
			else {
	        	clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Active Session Tab Did not exist");
	        }
		}
		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"No Tabs existed on the Tufts App");}
		//Validate Tufts Parking Sessions
		String strParkedTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number - Tufts", 0, "Value",strLicensePlateNumber);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 0, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at - Tufts", 0, "Value", "Parked Today at "+strParkedTimestamp);
		//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  		String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = Double.parseDouble(strCurrentAccountBalance) * .01;
  		}catch (Exception e) {}
  		if(strOriginalAccountBalance.equals(String.valueOf(dblStringUserAccountBalance)))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		Android_TuffPark_ParkingSessions clsAndroidTuffParkParkingSessions = new Android_TuffPark_ParkingSessions();
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2004_PurchasePermit_Park_Leave_VPSH(objDictionary,strLicensePlateNumber);
	}
	@Test(priority=2006,groups={"Smoke"})
	public void A2006_PP_CC_LP_LE_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP_GP: Purchase Permit Credit Card                              ");
		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VPSH: Validate Parking Session                                  ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "04"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		//Validate 2 Tabs Exist (Create a Function Around)
		try
		{
			WebElement ObjScrollView = androiddriver.findElement(By.id("com.mpspark.tuftspark.MPS:id/tabs"));
			List<WebElement> tabs = ObjScrollView.findElements(By.id("com.mpspark.tuftspark.MPS:id/textViewIcon"));
			int intNumberOfTabs = tabs.size();
			if(intNumberOfTabs == 2){Reporter.log("The Tufts App correctly displayed 2 Tabs");}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Tufts did not display 2 Tabs - Actual Number ("+intNumberOfTabs+")");}
		}
		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"No Tabs existed on the Tufts App");}
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		try {Thread.sleep(2000);}catch (Exception e) {}
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Meter Payment", "OTHER PAYMENT OPTIONS", 1);
		try {Thread.sleep(3500);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Account Details", "Credit or Debit Card", 1);
		try {Thread.sleep(2000);}catch (Exception e) {}
		//clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Card Details", "Populate Credit Card", "{T} Credit Card Number-Tufts","5555555555554444");
		clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Card Details", "Populate Credit Card", "{T} Credit Card Number-Tufts","4242424242424242");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Card Details", "Next", 1);
	    String strStartDate =  clsCommonWeb.AddDaysToCurrentDate("MM/yy","+30");
	    clsCommonMobile.PopulateAction(objDictionary, androiddriver, "Card Details", "Populate Credit Card", "{T} Expiration Date-Tufts|{T} CVC-Tufts|{T} Postal Code-Tufts",strStartDate+"|3746|55438");
	    clsCommonMobile.ClickButton(objDictionary, androiddriver, "Card Details", "Add Card", 1);
	    //Save Card Details
	    try {Thread.sleep(3000);}catch (Exception e) {}
	    clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Meter Payment", "Save Card Details", 1, "Value", "Please tap on Ok button to re-use the card");
	    clsCommonMobile.ClickButton(objDictionary, androiddriver, "Meter Payment", "Cancel-Tufts", 1);
	    String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		try {Thread.sleep(5000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Validate Active Sessions
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "Click to view active permits",1);
		clsCommonMobile.VerificationPointLink(objDictionary, androiddriver, "Permits", "Account Details", 1, "Exists");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permit", "Permit Valid From", 0, "Value", "Valid From : "+strDay+" at "+strStartTime);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Permit", "Permit Valid To", 0, "Value", "Valid To : "+strDay+" at "+strEndTime);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Permit", "Account Details",1);
		//Validate the Active Session Tab Exists.
		try
		{
			WebElement ObjScrollView = androiddriver.findElement(By.id("com.mpspark.tuftspark.MPS:id/tabs"));
			List<WebElement> tabs = ObjScrollView.findElements(By.id("com.mpspark.tuftspark.MPS:id/textViewIcon"));
			int intNumberOfTabs = tabs.size();
			if(intNumberOfTabs == 3){Reporter.log("The Tufts App correctly displayed 3 Tabs");}
			else{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Tufts did not display 3 Tabs - Actual Number ("+intNumberOfTabs+")");}
			if (tabs.size() > 1) {
				WebElement secondTab = tabs.get(1);secondTab.click();
	            System.out.println("The Active sesson tab was clicked");
	            try {Thread.sleep(4000);}catch (Exception e) {}
	        }
			else {
	        	clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Active Session Tab Did not exist");
	        }
		}
		catch (Exception e) {clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"No Tabs existed on the Tufts App");}
		//Validate Tufts Parking Sessions
		String strParkedTimestamp = objDictionary.get("strParkTimestamp");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "License Plate Number - Tufts", 0, "Value",strLicensePlateNumber);
		String strMeterGroup = objDictionary.get("strMeterGroup");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Lot", 0, "Value", "Lot : "+strMeterGroup);
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Parking Sessions", "Parked at - Tufts", 0, "Value", "Parked Today at "+strParkedTimestamp);
		//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
		String strOriginalAccountBalance = objDictionary.get("strCurrentAccountBalance");
  		String strCurrentAccountBalance = "";
  		double dblStringUserAccountBalance = 0.0;
  		try
  		{
  			strCurrentAccountBalance = clsHttpConnections.CURL_ReturnCurrentParkingAccountBalance(objDictionary,"parker");
  			dblStringUserAccountBalance = Double.parseDouble(strCurrentAccountBalance) * .01;
  		}catch (Exception e) {}
  		if(strOriginalAccountBalance.equals(String.valueOf(dblStringUserAccountBalance)))
		{Reporter.log("The account balance correctly equaled ("+strOriginalAccountBalance+")");}
		else
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The Account Balance did not equal ("+strOriginalAccountBalance+") Actual value ("+strCurrentAccountBalance+")");}
		//Exit Lot
  		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		Android_TuffPark_ParkingSessions clsAndroidTuffParkParkingSessions = new Android_TuffPark_ParkingSessions();
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2004_PurchasePermit_Park_Leave_VPSH(objDictionary,strLicensePlateNumber);
	}
	@Test(priority=2007,groups={"Smoke"})
	public void A2007_PP_LP_LE_VPSH_LP_LE_VPSH()
	{
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit                                             ");
  		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VPSH: Validate Parking Session                                  ");
  		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VPSH: Validate Parking Session                                  ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "05"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		try {Thread.sleep(2000);}catch (Exception e) {}
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "App Account",1);
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		androiddriver.quit();
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
  		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		Android_TuffPark_ParkingSessions clsAndroidTuffParkParkingSessions = new Android_TuffPark_ParkingSessions();
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2007_PurchasePermit_LP_LE_LP_LE_VPSH_1(objDictionary,strLicensePlateNumber);
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,30, "Remain Parked for 30 seconds");
		//Store Lot Parking Id
		try
		{
			String strParkingId = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
			objDictionary.put("strParkingId", strParkingId);
		}
		catch (Exception e)
		{clsCommonMobile.UpdateErrorMessageWithPivotalData(objDictionary,androiddriver,"The GetJsonParkingSessionId Failed");}
  		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_2007_PurchasePermit_LP_LE_LP_LE_VPSH_2(objDictionary,strLicensePlateNumber);
	}
	@Test(priority=2008,groups={"Smoke"})
	public void A2008_MLP_PP_LE_VPSH()
	{
		objDictionary.put("strAssociatedBug", "185948458");
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit                                             ");
  		Reporter.log("MLP: Miss Lot Park                                              ");
		Reporter.log("LE: Lot Exit                                                    ");
  		Reporter.log("VPSH: Validate Parking Session                                  ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "06"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		try {Thread.sleep(2000);}catch (Exception e) {}
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "App Account",1);
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		androiddriver.quit();
		clsMeter.METER_MeterWaitWithMessage(objDictionary,90, "Remain Parked for 90 seconds");
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
    	//Store Lot Parking Id
  		clsHttpConnections.HTTPCONNECTIONS_StoreLotParkingId2(objDictionary, "Local", strLicensePlateNumber);
  		Android_TuffPark_ParkingSessions clsAndroidTuffParkParkingSessions = new Android_TuffPark_ParkingSessions();
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2004_PurchasePermit_Park_Leave_VPSH(objDictionary,strLicensePlateNumber);
	}
	@Test(priority=2009,groups={"Smoke"})
	public void A2009_LP_MLE_LP_VPSH()
	{
		//Chris Needs to get you a Pivotal
		objDictionary.put("strMobileDeviceType", "ANDROID");
		objDictionary.put("strMunicipality", "Like Tufts");
		objDictionary.put("strMunicipalitySubdomain","liketufts");
		CommonANDROID clsCommonMobile = new CommonANDROID ();
		CommonWeb clsCommonWeb = new CommonWeb();
		Meter clsMeter = new Meter();
		clsCommonMobile.SENTRYMOBILE_AddReportVariables(objDictionary);
		Reporter.log("***************TestCase Description*****************************");
		Reporter.log("PP: Purchase Permit                                             ");
  		Reporter.log("LP: Lot Park                                                    ");
		Reporter.log("MLE: Miss Lot Exit                                              ");
		Reporter.log("LP: Lot Park                                                    ");
  		Reporter.log("VPSH: Validate Parking Sessions                                 ");
  		Reporter.log("****************************************************************");
		String strSpaceName = objDictionary.get("strMeterSpotName");
		String strLicensePlateNumber = "06"+strSpaceName.toUpperCase()+"AA";
		//Remove all License Plate
		HttpConnections clsHttpConnections = new HttpConnections();
		clsHttpConnections.HTTPCONNECTIONS_DeleteAllParkerLicensePlates(objDictionary);
		//Add License Plate
		clsHttpConnections.POST_LicensePlate(objDictionary,"Parker", strLicensePlateNumber,"Minnesota");
		clsHttpConnections.JsonDeleteAllActivePermitsForUser(objDictionary,"");
		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null) {strAndroidUdid = "";}
		//Exit Lot
		clsHttpConnections.CURL_ExitLot(objDictionary,strLicensePlateNumber, "MN","zone1entry","True");
		//Open Android Device
		AndroidDriver androiddriver = clsCommonMobile.SetMobileDriver(objDictionary,"SentryMobile","True","Parker");
		clsCommonMobile.SENTRYMOBILE_Open(objDictionary, androiddriver);
		clsCommonMobile.SENTRYMOBILE_TuftsLogin(objDictionary, androiddriver, "parker",strLicensePlateNumber, "Enroll later","True");
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Plate", strLicensePlateNumber);
		clsCommonMobile.PopulateScrollableListbox(objDictionary, androiddriver, "Buy a permit", "Select Permit", "Hourly Park - $1.25");
		String strStartTime = new SimpleDateFormat("hh:mm a").format(new Date());
		String strStartTime2 = new SimpleDateFormat("h:mm a").format(new Date());
		try {Thread.sleep(2000);}catch (Exception e) {}
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Purchase Permit",1);
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "Confirm",1);
		clsCommonMobile.ClickLink(objDictionary, androiddriver, "Buy a permit", "App Account",1);
		String strEndTime = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","hh:mm a");
		String strEndTime2 = clsCommonWeb.AddTimeToExistingTime(strStartTime, "60","h:mm a");
		try {Thread.sleep(3000);}catch (Exception e) {}
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Success Message", 0, "Value", "Your payment was successful.");
		String strDay =  clsCommonWeb.AddDaysToCurrentDate("MM-dd-yyyy","0");
		String strDay2 =  clsCommonWeb.AddDaysToCurrentDate("MM/dd/yyyy","0");
		clsCommonMobile.VerificationPointText(objDictionary, androiddriver, "Buy a permit", "Valid Until Message", 0, "Value", "Valid for dates : "+strDay+" at "+strStartTime+" - "+strDay+" at "+strEndTime);
		objDictionary.put("strPermitInfomation", "Valid From "+strDay2+" "+strStartTime2+" Valid To "+strDay2+" "+strEndTime2+ " CDT");
		clsCommonMobile.ClickButton(objDictionary, androiddriver, "Buy a permit", "OK",1);
		androiddriver.quit();
		clsMeter.METER_MeterWaitWithMessage(objDictionary,90, "Remain Parked for 90 seconds");
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,90, "Remain Parked for 30 seconds");
		String strParkingId1 = "";
		strParkingId1 = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
		//Park in Lot
		clsHttpConnections.CURL_ParkInLot(objDictionary,strLicensePlateNumber, "MN","zone1entry");
		clsMeter.METER_MeterWaitWithMessage(objDictionary,90, "Remain Parked for 30 seconds");
		//Store Lot Parking Id
		String strParkingId2 = clsHttpConnections.GetJsonParkingSessionId(objDictionary);
  		Android_TuffPark_ParkingSessions clsAndroidTuffParkParkingSessions = new Android_TuffPark_ParkingSessions();
  		objDictionary.put("strParkingId",strParkingId1);
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2009_LP_MLE_LP_VPSH_1(objDictionary,strLicensePlateNumber);
		objDictionary.put("strParkingId",strParkingId2);
		clsAndroidTuffParkParkingSessions.SENTRYLINK_ValidateParkingSessionHistoryAndImages_A2009_LP_MLE_LP_VPSH_2(objDictionary,strLicensePlateNumber);
	}
}

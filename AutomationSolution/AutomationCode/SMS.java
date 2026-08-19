package AutomationCode;

import java.io.File;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;


public class SMS
{
//    //private final static String SMS_POPUP_CLASSPATH = "C:\\Your\\PATH\\in\\here\\";
//    //private final static String SMS_POPUP_APP_NAME = "SMSPopup.apk";
//    private final static String SMS_POPUP_PACKAGE_NAME = "net.everythingandroid.smspopup";
//    private final static String DEVICE_ID = "your_device_id";
//
//    private final static String ADB_INSTALL_COMMAND = "adb -s %s install -r %s";
//    private final static String ADB_UNINSTALL_COMMAND = "adb -s %s uninstall %s";
//    private final static String ADB_START_ACTIVITY = "adb -s %s shell am start -n net.everythingandroid.smspopup/.ui.SmsPopupConfigActivity";
//    private final static String ADB_GO_BACK = "adb -s %s shell input keyevent 4";
//
//    private final static String TYPE_SEARCH_TEXT_STARTSWITH = "textStartsWith";
//    private final static String TYPE_SEARCH_TEXT_CONTAINS = "textContains";
//    private final static String TYPE_SEARCH_TEXT = "text";
//
    private WebDriverWait wait60;
    private Wait<WebDriver> fluentWait30;
    private AppiumDriver localDriver;
//
//    public void SMS_MESSAGE(AndroidDriver<MobileElement> androiddriver)
//    {
//        wait60 = new WebDriverWait(androiddriver, 60);
//        fluentWait30 = new FluentWait<WebDriver>(androiddriver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
//    }

    public SMS(AppiumDriver driver)
    {
        localDriver = driver;
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));

//        fluentWait30 = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    public void InstallAPK(Map<String, String> objDictionary)
    {
        /*
        If you do not always want to install SMS APK then
        check if package already exists in device:
        adb shell pm list packages net.everythingandroid.smspopup
        it will reply
        package:[your.package.name]
        if it is installed already or anything otherwise
        FOR NOW I'll junst uninstall it always or else current configureSMSAPK() would fail
        */
		try
		{
	        this.uninstallSMSAPK(objDictionary);
	        String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null){strAndroidUdid = "";}
	        String strUserDir = System.getProperty("user.dir");
	        String strPath = strUserDir+"/APK/";
	        String strAPK = "SMSPopup.apk";
	        String strInstallCommand = "adb -s %s install -r %s";
	        runADBCommand(String.format(strInstallCommand, strAndroidUdid, new File(strPath, strAPK).getAbsolutePath()));
	        Thread.sleep(6000); //--> This can be replaced by checking if package is already installed. In slower devices this sleep may not be enough
		}catch (Exception e)
		{
			System.out.println("MIH");
		}
    }

    public void uninstallSMSAPK(Map<String, String> objDictionary)
    {
    	try
		{
    		String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null){strAndroidUdid = "";}
    		String strUninstallCommand = "adb -s %s uninstall %s";
    		String strPopUpPackageName  = "net.everythingandroid.smspopup";
    	    runADBCommand(String.format(strUninstallCommand, strAndroidUdid, strPopUpPackageName));
    	    Thread.sleep(2000);
		}catch (Exception e)
    	{
			System.out.println("MIH");
		}
    }

    public void ConfigureSMS(Map<String, String> objDictionary)
    {
    	String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null){strAndroidUdid = "";}
    	String strADBGoBack = "adb -s %s shell input keyevent 4";
	    try
		{
			String strStartActivity = "adb -s %s shell am start -n net.everythingandroid.smspopup/.ui.SmsPopupConfigActivity";
	    	runADBCommand(String.format(strStartActivity, strAndroidUdid));
	        try {Thread.sleep(1000);}catch (Exception e) {}
	        runADBCommand(String.format(strADBGoBack, strAndroidUdid));
		}catch (Exception e)
		{
			try
			{
//				runADBCommand(String.format(strADBGoBack, strAndroidUdid));
//				waitForIDwithText("android:id/button1","OK","text").click();
				System.out.println("MIH");
			} catch (Exception f) {}
			System.out.println("MIH");
		}
    }

    public String ReturnSMS(Map<String, String> objDictionary)
    {
    	String strAndroidUdid = objDictionary.get("strAndroidUdid");if(strAndroidUdid == null){strAndroidUdid = "";}
		String SMS = "";
        String strADBGoBack = "adb -s %s shell input keyevent 4";
        try
		{
        	//
//        	String strStartActivity = "adb -s %s shell am start -n net.everythingandroid.smspopup/.ui.SmsPopupConfigActivity";
//	    	runADBCommand(String.format(strStartActivity, strAndroidUdid));

//	        waitForID("net.everythingandroid.smspopup:id/popupMessageMainlayout");
//	        SMS = waitForID("net.everythingandroid.smspopup:id/messageTextView").getText();

	        waitForID("com.argonremote.smspopup:id/sMain");
	        SMS = waitForID("com.argonremote.smspopup:id/tMessage").getText();

	        runADBCommand(String.format(strADBGoBack, strAndroidUdid));
	        return SMS;
		}catch (Exception e)
        {
			System.out.println("MIH");
		}
    	return SMS;
    }


    /*
    * PRIVATE METHODS
    * */

    private void runADBCommand(String command) throws Exception
    {

    	String strAutomationUser = System.getProperty("user.name");
    	try{Runtime.getRuntime().exec("/Users/"+strAutomationUser+"/Library/Android/sdk/platform-tools//"+command);}
    	catch (Exception e){System.out.print("("+command+") abd command failed");}
    	//Runtime.getRuntime().exec(command);
    }

    /*
    * @parameter id Indicates the id of Element to be searched
    * @parameter value Indicates the text in Element to be searched
    * @parameter typeSearch Indicates the type of search [textStartsWith|textContains|text]
    * */
//    private WebElement waitForIDwithText(String id, String value, String typeSearch) throws Exception
//    {
//        try
//        {
//        	String query = "new UiSelector().resourceId(\"" + id + "\")." + typeSearch + "(\"" + value + "\")";
//        	return fluentWait30.until(webDriver -> ((AndroidDriver) localDriver).findElementByAndroidUIAutomator(query));
//        } catch (Exception ex)
//        {
//            throw new Exception("The following id was not found: "+id);
//        }
//    }

    /*
    * @parameter id Indicates the id of Element to be searched
    * */
    private WebElement waitForID(String id) throws Exception
    {
        try {
            return wait60.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        } catch (Exception ex) {
            throw new Exception("The following id was not found: "+id);
        }
    }
}
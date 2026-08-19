package AutomationCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.functions.ExpectedCondition;

public class PaymentSite
{
	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,WebDriver driver,String strErrorMsg)
  	{
  		CommonWeb clsCommonWeb = new CommonWeb();
  		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
  		clsCommonWeb.TakeWebScreenShot(objDictionary, driver);
  		if (ImgurClient_Old.getLastUrl() != null){objDictionary.put("link", ImgurClient_Old.getLastUrl());}
  		driver.quit();
  		SendEmail(strTestCase, ImgurClient_Old.getLastUrl());
		Assert.fail(strErrorMsg);
  	}

	public void SendEmail(String strTestCase, String strImageUrl)
    {
    	final String username = "sentryquality@gmail.com";
		final String password = "Fires@le";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,new javax.mail.Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sentryquality@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("darin@mpspark.com"));
			message.setSubject("Payment Site Test");
			BodyPart messageBodyPart1 = new MimeBodyPart();
		    messageBodyPart1.setText
		    (
		    		"Test Case: "+strTestCase+"\n\n"+
		    		strImageUrl
		    );
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			//String filename = strDirectory+"/TestResults.html";//change accordingly
		    //DataSource source = new FileDataSource(filename);
		    //messageBodyPart2.setDataHandler(new DataHandler(source));
		    //messageBodyPart2.setFileName(filename);
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart1);
		    //multipart.addBodyPart(messageBodyPart2);
		    message.setContent(multipart);
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			//Need to through up a message telling user there is a email issue.
			CommonWeb.setWarningMsg("Unable to email Automation results-"+e);
		}
	}

	//VERIFICATION POINTS
	public void VerificationPointTextField(Map<String, String> objDictionary,WebDriver driver, String strPageName, String strTextFieldName, int intTextFieldIndex, String strValidationType, String strTextFieldVaue)
    {
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		WebElement objTextField = GetTextFieldObj(objDictionary, driver, strPageName, strTextFieldName);
		strTextFieldVaue = ConvertValues(objDictionary, driver, strTextFieldVaue);
		if (objTextField != null)
	    {
			switch (strValidationType)
            {
	            case "Contains":
	        		if(objTextField.getText().contains(strTextFieldVaue))
	        		{Reporter.log("The textfield (" + strTextFieldName + ") with index (" + intTextFieldIndex + ") contained (" + strTextFieldVaue + ")"+"");}
	        		else
	        		{UpdateErrorMessageWithPivotalData(objDictionary,driver,"The textfield (" + strTextFieldName + ") with index (" + intTextFieldIndex + ") did not contain (" + strTextFieldVaue + ") - actual value (" + objTextField.getText() + ")-"+strMethondName);}
	        		return;
            	case "Value":
            		if(objTextField.getText().equals(strTextFieldVaue))
            		{Reporter.log("The textfield (" + strTextFieldName + ") with index (" + intTextFieldIndex + ") equaled (" + strTextFieldVaue + ")"+"");}
            		else
            		{UpdateErrorMessageWithPivotalData(objDictionary, driver, "The textfield (" + strTextFieldName + ") with index (" + intTextFieldIndex + ") did not equal (" + strTextFieldVaue + ") - actual value (" + objTextField.getText() + ")");}
            		return;
                case "Disabled":
	                if (!objTextField.isEnabled()){Reporter.log("The TextField (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") was disabled"+"");}
	                else
	                {UpdateErrorMessageWithPivotalData(objDictionary, driver, "The TextField  (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") was not disabled");}
	                return;
	            case "Does Not Exist":
	            	UpdateErrorMessageWithPivotalData(objDictionary, driver, "The TextField  (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") existed");
	            case "Enabled":
	            	if (objTextField.isEnabled()){Reporter.log("The TextField (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") was enabled"+"");}
	            	else
	            	{UpdateErrorMessageWithPivotalData(objDictionary, driver, "The TextField (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") was not enabled");}
	            	return;
	            case "Exists":
	            	Reporter.log("The TextField (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") existed");
	                return;
	            default:
	            	UpdateErrorMessageWithPivotalData(objDictionary, driver, "The TextField validation type (" + strValidationType + ") hasn't been coded yet - VerificationPointTextField");
            }
		}
	    else
	    {
	    	switch (strValidationType)
            {
                case "Does Not Exist":
                	Reporter.log("The TextField  (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") did not exist"+"");
	                return;
                case "Exists":
                	UpdateErrorMessageWithPivotalData(objDictionary, driver, "The Textfield (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") did not exist");
                default:
                	UpdateErrorMessageWithPivotalData(objDictionary, driver, "The Textfield (" + strTextFieldName + ") at index (" + intTextFieldIndex + ") did not exist, so it could not perform the (" + strValidationType + ") validation");
            }
	    }
    }
	public void VerificationPointButton(Map<String, String> objDictionary, WebDriver driver, String strPageName, String strButtonName, int intButtonIndex, String strValidationType)
    {
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		WebElement objButton = GetButtonObj(objDictionary, driver, strPageName, strButtonName,intButtonIndex);
		if (objButton != null)
	    {
			switch (strValidationType)
            {
	            case "Disabled":
	                if (!objButton.isEnabled()){Reporter.log("The button (" + strButtonName + ") at index (" + intButtonIndex + ") was disabled");}
	                else
	                {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The button  (" + strButtonName + ") at index (" + intButtonIndex + ") was not disabled-"+strMethondName);}
	                return;
	            case "Does Not Exist":
	            	UpdateErrorMessageWithPivotalData(objDictionary,driver,"The button  (" + strButtonName + ") at index (" + intButtonIndex + ") existed-"+strMethondName);
	            case "Enabled":
	            	if (objButton.isEnabled()){Reporter.log("The button (" + strButtonName + ") at index (" + intButtonIndex + ") was enabled");}
	            	else
	            	{UpdateErrorMessageWithPivotalData(objDictionary,driver,"The button (" + strButtonName + ") at index (" + intButtonIndex + ") was not enabled-"+strMethondName);}
	            	return;
	            case "Exists":
	            	Reporter.log("The button (" + strButtonName + ") at index (" + intButtonIndex + ") existed");
	                return;
	            default:
	            	UpdateErrorMessageWithPivotalData(objDictionary,driver,"The button validation type (" + strValidationType + ") has not been coded yet-"+strMethondName);
            }
		}
	    else
	    {
	    	switch (strValidationType)
            {
                case "Does Not Exist":
                	Reporter.log("The button  (" + strButtonName + ") at index (" + intButtonIndex + ") did not exist");
	                return;
                case "Exists":
                	UpdateErrorMessageWithPivotalData(objDictionary,driver,"The button (" + strButtonName + ") at index (" + intButtonIndex + ") did not exist-"+strMethondName);
                default:
                	UpdateErrorMessageWithPivotalData(objDictionary,driver,"The button (" + strButtonName + ") at index (" + intButtonIndex + ") did not exist, so it could not perform the (" + strValidationType + ") validation-"+strMethondName);
            }
	    }
    }

	//OBJECT PROPERTIES
	public WebElement GetTextFieldObj(Map<String, String> objDictionary, WebDriver driver, String strPageName, String strTextFieldName)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement objTextfield = null;
		String strTextfieldIndex = objDictionary.get("strTextfieldIndex"); if (strTextfieldIndex == null) {strTextfieldIndex = "1";}
		int intTextfieldIndex = 0;
		switch (strPageName)
		{
			case "Home":
				switch (strTextFieldName)
				{
					case "Violation Number":
						try { return driver.findElement(By.xpath("//input[contains(@name,'ticket')]"));}
						catch(Exception e) {return null;}
					default:
						UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Textfield (" + strTextFieldName + ") for the page (" + strPageName + ") had not been added-"+strMethodName);
				}
				break;
			default:
				UpdateErrorMessageWithPivotalData(objDictionary,driver,"The Page ("+strPageName+") does not have any existing Textfield-"+strMethodName);
		}
		return objTextfield;
	}
	public WebElement GetButtonObj(Map<String, String> objDictionary,WebDriver driver, String strPageName, String strButtonName, int intButtonIndex)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//driver = SeleniumSwitchBrowser(objDictionary,driver,strPageName, strPageURL);
		//new WebDriverWait(driver, 5);
		switch (strPageName)
		{
			case "Home":
				switch (strButtonName)
				{
					case "Pay Violations":
						try { return driver.findElement(By.xpath("//a[contains(text(),'Pay Violations')]"));}
						catch(Exception e) {return null;}
					default:
						UpdateErrorMessageWithPivotalData(objDictionary,null,"The Button (" + strButtonName + ") had not been added to GetButtonObj for the page (" + strPageName + ")-"+strMethodName);
				}
			default:
				UpdateErrorMessageWithPivotalData(objDictionary,null,"The Page ("+strPageName+") does not have any existing Buttons-"+strMethodName);
		}
		return null;
	}

	//COMMON
	public void waitForPageLoaded(Map<String, String> objDictionary, WebDriver driver)
	{
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				System.out.println("Page Load Status: "+((JavascriptExecutor) driver).executeScript("return document.readyState").toString());
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(80));
			wait.until(expectation);
		} catch (Throwable error)
		{
			//Need Web Screen Shot
			UpdateErrorMessageWithPivotalData(objDictionary, driver, "Timeout waiting for Page Load Request to complete.");
		}
	}
	public String ConvertValues(Map<String, String> objDictionary, WebDriver driver, String strValue)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		if (strValue != null)
		{
			//Date Match
			String strDatePattern = "(#[-0-9]*\\|[-0-9]*\\|[-0-9]*\\|.*#)";
		    Pattern pDate = Pattern.compile(strDatePattern);
		    Matcher mDate = pDate.matcher(strValue);
		    //Variable Match
		    String strVariablePattern = "~.*~";
		    Pattern pVariable = Pattern.compile(strVariablePattern);
		    Matcher mVariable = pVariable.matcher(strValue);
		    while (mDate.find( )||mVariable.find( ))
		    {
			    	int intFirstDateCharacter = strValue.indexOf("#") + "#".length();
			    	int intFirstTilde = strValue.indexOf("~") + "~".length();
			    	if (intFirstDateCharacter != 0){strValue = ConvertDateFormat(strValue, intFirstDateCharacter);}
			    	if (intFirstTilde != 0){strValue = ConvertVariable(objDictionary, strValue, intFirstTilde);}
		    }
		    strValue = strValue.replace("[tilda]", "~").trim();
		}
		else
		{UpdateErrorMessageWithPivotalData(objDictionary,driver,"The value of the String was null-ConvertValues-"+strMethondName);}
		return strValue;
	}
	public String ConvertDateFormat(String strDataValue,int intFirstDateCharacter)
    {
		String[] arrDateValues = strDataValue.replace("#", "").split("[|]", 10);
		Date dateCurrent = new Date();
		DateFormat dateFormat = new SimpleDateFormat(arrDateValues[3]);
		int intMonthValue = Integer.parseInt(arrDateValues[0]);
		int intDayValue = Integer.parseInt(arrDateValues[1]);
		int intYearValue = Integer.parseInt(arrDateValues[2]);
		dateCurrent = addDay(dateCurrent, intDayValue);
		dateCurrent = addMonth(dateCurrent, intMonthValue);
		dateCurrent = addYear(dateCurrent, intYearValue);
		strDataValue = dateFormat.format(dateCurrent);
		return strDataValue;
    }
	public String ConvertVariable(Map<String, String> objDictionary, String strValue,int intFirstDateCharacter)
	{
		StringBuilder clsStringBuilder = new StringBuilder();
		String strVariableValue = objDictionary.get(strValue.replaceAll("~", ""));
		if(strVariableValue == null){Assert.fail(clsStringBuilder.toString()+"The variable ("+strValue.replaceAll("~", "")+") did not exist in the objDictionary");}
		return strVariableValue;
	}

	//DATES
	public static Date addDay(Date date, int i)
	{
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
    public static Date addMonth(Date date, int i)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
    public static Date addYear(Date date, int i)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }

}

package AutomationCode;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class CommonUserRole
{
	// ASSOCIATE ERROR MESSAGE TO PIVOTAL
	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary, WebDriver driver,String strErrorMsg, String strHostType)
	{
		Meter clsMeter = new Meter();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strTestCase = objDictionary.get("strTestCase");
		if (strTestCase == null) {strTestCase = "";}
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if (strAssociatedBug == null) {strAssociatedBug = "";}
		String strPivotalId = "";
		String strRemainParkedShortSession = "True";
		String strPattern = "";
		Pattern CompilePattern = null;
		Matcher MatchPattern = null;
		// Copy Screen Shot Locall
		clsCommonWeb.TakeWebScreenShot(objDictionary, driver);
		// Create a request to the Pivotal API to get bug status and assigned to
		switch (strErrorMsg)
		{
			case "":
				Reporter.log(strErrorMsg);
				strPivotalId = "";
				strErrorMsg = "";
				break;

	    	default:
	    		if(strErrorMsg.contains("500 Internal Server Error"))
	    		{
	    			strPivotalId = "";Reporter.log(strErrorMsg);
			    	strErrorMsg = "";
			    	break;
	    		}
	    		strPattern = "The cell value in row (.*) column (.*) of the table \\(Parking Session History\\) did not containl (.*) - actual value \\(System Failure\\)";
	    		CompilePattern = Pattern.compile(strPattern);
				MatchPattern = CompilePattern.matcher(strErrorMsg);
				if (MatchPattern.find())
				{
					strPivotalId = "184549710";Reporter.log(strErrorMsg);
					strErrorMsg = "Getting an unexpected System Failure event in the parking session when making a payment after a rejected violation.";
					break;
				}
  	    }
  		String strExecutedGlobalPayActions = objDictionary.get("strExecutedGlobalPayActions");
		String strExecutedGlobalPayAbbr = objDictionary.get("strExecutedGlobalPayAbbr");
		if(strExecutedGlobalPayActions != null)
		{
			Reporter.log("******Global Pay Actions******");
			Reporter.log(strExecutedGlobalPayAbbr.replaceFirst("<br>", ""));
			Reporter.log(strExecutedGlobalPayActions);
			Reporter.log("******Global Pay Actions******");
		}
  		//String strRemainParkedShortSession = "";
  		if(!strPivotalId.equals(""))
  		{
  			if(driver != null) {driver.quit();}
  			if((strAssociatedBug.contains(strPivotalId) && !strAssociatedBug.equals("")) || strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
  			{
  				objDictionary.put("strAssociatedBug",strPivotalId);
  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  			}
  			else
  			{Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");}
  			//RPSS: Remain Parked Short Session
  	      	if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
  	      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  	    }
  		else
  		{
  			if(strRemainParkedShortSession.equals("True")){clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Error Remain Parked (15) seconds-Spot 1");}
  			Assert.fail(strErrorMsg);
  		}
  	}
}

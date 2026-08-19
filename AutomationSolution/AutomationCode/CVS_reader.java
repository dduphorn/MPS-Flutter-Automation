package AutomationCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.Reporter;

public class CVS_reader
{

	public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,String strErrorMsg)
  	{
  		Meter clsMeter = new Meter();
  		CommonWeb clsCommonWeb = new CommonWeb();
  		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
  		String strAssociatedBug = objDictionary.get("strAssociatedBug");
  		String strHost = objDictionary.get("strHost");
  		if(strAssociatedBug == null){strAssociatedBug = "";}
  		String strPivotalId = "";
  		String strPattern = "";
  		Pattern CompilePattern = null;
  		Matcher MatchPattern = null;
  		//Create a request to the Pivotal API to get bug status and assigned to
  		switch (strErrorMsg)
  	    {
  			case "The Button (Set to normal mode)  did not exist-ClickButton":
  				Reporter.log(strErrorMsg);
				strErrorMsg = "The maintenance mode \"MAINT_PARKING_SPOT\" is putting the meter in Free Parking instead of Maintenance mode.";
				strPivotalId = "154970036";
				clsMeter.METER_ExecutePythonScriptAgainstMeter(objDictionary,strHost, "sys_reboot.py");
				clsMeter.METER_MeterWaitWithMessage(objDictionary,120, "Waiting for Meter To Reboot");
				break;
  			default:
	    		strPattern = "The cell value in row (.*) column (.*) of the table \\(Parking Session History\\) did not equal \\(CSR Approved .*\\) - actual value \\(Claimed .*\\)";
	    		CompilePattern = Pattern.compile(strPattern);
	    		MatchPattern = CompilePattern.matcher(strErrorMsg);
	    		if(MatchPattern.find( ))
	    		{
	    			strPivotalId = "155234278";Reporter.log(strErrorMsg);
	    			strErrorMsg = "When Generate Ticket is clicked in PEO app it overrides the CSR Approval  time and email address in Sentry Link History page.";
	    			break;
	    		}
  	    }
  		if(!strPivotalId.equals(""))
  		{
  			if(strAssociatedBug.contains(strPivotalId)||strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
  			{
  				//Yellow Means Know Issue
  				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				//RPSS: Remain Parked Short Session
  		      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked on error (15) seconds-Spot 1");
  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  			}
  			else
  			{
  				//Red Means New Issue
  				Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
  				//RPSS: Remain Parked Short Session
  		      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked or error (15) seconds-Spot 1");
  		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
  			}
  		}
  		else
  		{
  	        	Reporter.log("<font color='red'>"+strErrorMsg+"</font>");
  	        	//if(driver != null){TakeWebScreenShoot(objDictionary,driver);}
  	        	//RPSS: Remain Parked Short Session
  	      	clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked (15) seconds-Spot 1");
  	      	Assert.fail(strErrorMsg);
  		}
  	}
    public void ValidateReport_KPI(Map<String, String> objDictionary,String strReportName,String[] arrExpectedValues)
    {
    	String strAutomationUser = System.getProperty("user.name");
        String csvFile = "/Users/"+strAutomationUser+"/Downloads/"+strReportName;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "|";
        int intNumberOfExpectedValues = arrExpectedValues.length;
        int intExpectedValueCounter = 0;
        String strExpectedValue = "";
        try
        {
        	//Loop through all rows
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null)
            {
            	strExpectedValue = arrExpectedValues[intExpectedValueCounter];
            	if(line.equals(strExpectedValue)){intExpectedValueCounter++;}
            	if(intNumberOfExpectedValues == intExpectedValueCounter){break;}
            }
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        finally {if (br != null) {try {br.close();} catch (IOException e) {e.printStackTrace();}}}
        if(intNumberOfExpectedValues == intExpectedValueCounter)
        {Reporter.log("The expected values existed in the report");}
        else
        {UpdateErrorMessageWithPivotalData(objDictionary,"The expected report value ("+strExpectedValue+") did not exist in the report");}
    }

}
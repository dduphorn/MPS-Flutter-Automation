package AutomationCode;

import java.io.File;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class ListenersClass extends TestListenerAdapter
{
	@Override
	public void onTestFailure(ITestResult tr)
	{
		String strPath = "";
		String strTestSuiteName = (String) tr.getAttribute("strTestSuiteName");
		String strTestCaseName = (String) tr.getAttribute("strTestCaseName");
		File directory = new File(".");
//		// Check If Path Exists
		File file = new File(strPath);
		if(strTestCaseName == null)
		{
			System.out.println("MIH");
		}
		else if(strTestCaseName.contains("Global"))
		{
			if(strTestCaseName.contains("Global"))
			{
				//Global Pay Local Meter Logs
				try {strPath = directory.getCanonicalPath() + "/MeterLogs/Local_"+ strTestSuiteName +"_"+ strTestCaseName + ".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				file = new File(strPath);
				Reporter.log("<a href=" + strPath + "/>Local Meter Logs</a>");

				//Global Pay Remote Meter Logs
				try {strPath = directory.getCanonicalPath() + "/MeterLogs/Remote_" +strTestSuiteName+"_"+ strTestCaseName + ".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				file = new File(strPath);
				Reporter.log("<a href=" + strPath + "/>Remote Meter Logs</a>");
			}
			else
			{
				//Meter Logs
				try {strPath = directory.getCanonicalPath() + "/MeterLogs/Local_"+ strTestSuiteName +"_"+ strTestCaseName + ".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				file = new File(strPath);
				Reporter.log("<a href=" + strPath + "/>Meter Logs</a>");
				//Messages Logs
				file = new File(strPath);
				try {strPath = directory.getCanonicalPath() + "/MessageLogs/" + strTestCaseName + ".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				Reporter.log("<a href=" + strPath + "/>Message Logs</a>");
			}
		}
	}

	@Override
	public void onTestSuccess(ITestResult tr)
	{
		String strPath = "";
		//String strMethodName = tr.getName();
		File directory = new File(".");
		String strTestCaseName = (String) tr.getAttribute("strTestCaseName");
		String strTestSuiteName = (String) tr.getAttribute("strTestSuiteName");
		if(strTestCaseName != null)
		{
			if(strTestCaseName.contains("Global"))
			{
				//Global Pay Local Meter Logs
				try {strPath = directory.getCanonicalPath() + "/MeterLogs/Local_"+strTestSuiteName+"_"+strTestCaseName+".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				File file = new File(strPath);
				Reporter.log("<a href=" + strPath + "/>Local Meter Logs</a>");

				//Global Pay Remote Meter Logs
				try {strPath = directory.getCanonicalPath() + "/MeterLogs/Remote_"+strTestSuiteName+"_"+strTestCaseName+".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				file = new File(strPath);
				Reporter.log("<a href=" + strPath + "/>Remote Meter Logs</a>");
			}
			else
			{
				//Meter Logs
				try {strPath = directory.getCanonicalPath() + "/MeterLogs/"+strTestSuiteName+"_"+strTestCaseName+".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				File  file = new File(strPath);
				Reporter.log("<a href=" + strPath + "/>Meter Logs</a>");
				//Messages Logs
				file = new File(strPath);
				try {strPath = directory.getCanonicalPath() + "/MessageLogs/"+strTestSuiteName+"_"+strTestCaseName+".txt";}
				catch (Exception exp) {strPath = "ERROR";}
				Reporter.log("<a href=" + strPath + "/>Message Logs</a>");
			}
		}
	}
}






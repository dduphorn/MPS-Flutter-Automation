package AutomationCode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.Reporter;

public class Database
{
   public void ConnectToDatabase(Map<String, String> objDictionary)
   {
	   String strPassword = objDictionary.get("strUniquePassword");
	   Connection c = null;
	   try
	   {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
		   System.err.println(e.getClass().getName()+": "+e.getMessage());
		   System.exit(0);
	   }System.out.println("Opened database successfully");
   }
   //Database Error Messages
   public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,String strErrorMsg)
   {
 		Meter clsMeter = new Meter();
 		String strTestCase = objDictionary.get("strTestCase");if(strTestCase == null){strTestCase = "";}
 		String strAssociatedBug = objDictionary.get("strAssociatedBug");
 		if(strAssociatedBug == null){strAssociatedBug = "";}
 		String strPivotalId = "";
 		//Create a request to the Pivotal API to get bug status and assigned to
 		switch (strErrorMsg)
 	    {
 			case "Error Message":
 				strPivotalId = "";
 				break;
 		    	default:
 		    		if(strErrorMsg.contains("Example"))
 		    		{strPivotalId = "137885163";break;}
 		    		String strPattern = "The cell value in row (.*) column (.*) of the table \\(Parking Session History\\) did not equal \\(Meter Purchased 10 Minutes\\) - actual value \\(Meter Purchased 11 Minutes\\)";
 		    		Pattern CompilePattern = Pattern.compile(strPattern);
 		    		Matcher MatchPattern = CompilePattern.matcher(strErrorMsg);
 		    		if(MatchPattern.find( ))
 		    		{
 		    			Reporter.log(strErrorMsg);
 		    			strErrorMsg = "Meter Rounding Error Free To Rate - Virt Payment Displays 11 Minutes instead of 10 Minutes";
 		    			strPivotalId = "153447041";
 		    			break;
 		    		}
 	    }
 		String strRemainParkedShortSession = objDictionary.get("strRemainParkedShortSession"); if(strRemainParkedShortSession == null) {strRemainParkedShortSession = "True";}
		if(!strPivotalId.equals(""))
 		{
 			if(strAssociatedBug.contains(strPivotalId)||strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
 			{
 				//Yellow Means Know Issue
 				Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
 				if(strRemainParkedShortSession.equals("True"))
 				{
 					//RPSS: Remain Parked Short Session
 					clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked on error (15) seconds-Spot 1");
 				}
 		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
 			}
 			else
 			{
 				//Red Means New Issue
 				Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");
 				//RPSS: Remain Parked Short Session
 				if(strRemainParkedShortSession.equals("True"))
 				{
 					clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked or error (15) seconds-Spot 1");
 				}
 		      	Assert.fail(strPivotalId+"-"+strErrorMsg);
 			}
 		}
 		else
 		{
 			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");
 			//RPSS: Remain Parked Short Session'
 			if(strRemainParkedShortSession.equals("True"))
			{
 				clsMeter.METER_MeterWaitWithMessage(objDictionary,15, "Remain Parked (15) seconds-Spot 1");
			}
 	      	Assert.fail(strErrorMsg);
 		}
 	}

   public void DATABASE_Insert(Map<String, String> objDictionary,String strSQL)
   {
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	   Connection c = null;
	   Statement stmt = null;
	   String strPassword = objDictionary.get("strUniquePassword");

	   try
	   {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   System.out.println(strSQL);
		   stmt.executeUpdate(strSQL);
		   stmt.close();
		   c.commit();
		   c.close();
	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getMessage()+"-"+strMethodName);}
	   System.out.println("Records created successfully");
   }
   public String DATABASE_Select(Map<String, String> objDictionary,String strSQL)
   {
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
 	   Connection c = null;
	   Statement stmt = null;
	   String strPassword = objDictionary.get("strUniquePassword");
	   try
	   {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   //System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   //System.out.println(strSQL);
		   ResultSet rs = stmt.executeQuery(strSQL);
		   if(!rs.next())
		   {
			   rs.close();c.close();stmt.close();return "";
		   }
		   else
		   {
			   String strValue  = rs.getString(1);
			   rs.close();c.close();stmt.close();return strValue;}
	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getClass().getName()+": "+ e.getMessage()+"-"+strMethodName);}
	   return "";
   }
   public void DATABASE_Update(Map<String, String> objDictionary,String strSQL)
   {
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	   Connection c = null;
	   Statement stmt = null;
	   String strPassword = objDictionary.get("strUniquePassword");
	   try
	   {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   System.out.println(strSQL);
		   stmt.executeUpdate(strSQL);
		   stmt.close();
		   c.commit();
		   c.close();
	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getMessage()+"-"+strMethodName);}
   }

   public void WriteToActionTimeDatabase(Map<String, String> objDictionary,String strActionName, int intMilliSeconds,String MeterType)
   {
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	   Statement stmt = null;
	   String strPassword = objDictionary.get("strUniquePassword");
	   String strEnvironment = objDictionary.get("strEnvironment");
	   SimpleDateFormat dateFormatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	   String strDate = dateFormatDateTime.format(new Date());
	   try
	   {
		   Connection c = null;
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   String sql = "INSERT INTO \"ACTIONTIMES\" (\"ActionName\",\"MilliSeconds\",\"Date\",\"Environment\",\"MeterType\")"
				   + "VALUES ('"+strActionName+"','"+intMilliSeconds+"', '"+strDate+"', '"+strEnvironment+"', '"+MeterType+"')";
		   System.out.println(sql);
		   stmt.executeUpdate(sql);
		   stmt.close();
		   c.commit();
		   c.close();
	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getMessage()+"-"+strMethodName);}
	   System.out.println("Records created successfully");
   }
   public void WriteToMeterLoadDatabase(Map<String, String> objDictionary,String strActionName, String strHost, String strUTC, String strInstantValue, String str5MinAvg, String str15MinAvg, String strMeterType)
   {
	   Meter clsMeter = new Meter();
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	   Statement stmt = null;
	   String strPassword = objDictionary.get("strUniquePassword");
	   //Get Top
	   String strTop = "";
	   //String strTop = clsMeter.METER_GetTOP(objDictionary, strHost, strActionName);
	   try
	   {
		   Connection c = null;
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   String sql = "INSERT INTO \"METERLOAD\" (\"ActionName\",\"Host\",\"UTC\",\"InstantValue\",\"5MinAvg\",\"15MinAvg\",\"MeterType\",\"TOP\")"
				   + "VALUES ('"+strActionName+"','"+strHost+"','"+strUTC+"','"+strInstantValue+"','"+str5MinAvg+"','"+str15MinAvg+"','"+strMeterType+"','"+strTop+"')";
		   System.out.println(sql);
		   stmt.executeUpdate(sql);
		   stmt.close();
		   c.commit();
		   c.close();
	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getMessage()+"-"+strMethodName);}
   }


   public void WriteTestResultsToDatabase(Map<String, String> objDictionary)
   {
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	   String strPassword = objDictionary.get("strUniquePassword");
	   String strErrorMessage = objDictionary.get("strErrorMessage");
	   if(strErrorMessage == null){strErrorMessage = "";}
	   else{strErrorMessage = strErrorMessage.replace("\n", "").replace("/", "//").replace("'", "''");}
	   String strEnvironment = objDictionary.get("strEnvironment");
	   if(strEnvironment != null)
	   {
		   strEnvironment = strEnvironment.replace("\n", "").replace("'","''");
	   }
	   String strMobileDeviceType = objDictionary.get("strMobileDeviceType");
	   String strTestCaseName = objDictionary.get("strTestCaseName");
	   if(strTestCaseName != null)
	   {
		  //Remove Carriage Returns
		  strTestCaseName = objDictionary.get("strTestCaseName").replace("\n", "");
	   }
	   SimpleDateFormat dateFormatDateTime = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	   String strDate = dateFormatDateTime.format(new Date());
	   String strSentryLinkVersion = objDictionary.get("strSentryLinkVersion");
	   String strMeterVersion = objDictionary.get("strMeterVersion");
	   String strMeterMD5SUMVersion = objDictionary.get("strMeterMD5SUMVersion");
	   strMeterVersion = strMeterVersion + " "+strMeterMD5SUMVersion;
	   String strAndroidVersion = objDictionary.get("strAndroidVersion");
	   String strIOSFullVersion = objDictionary.get("strIOSFullVersion");
	   String strMunicipality = objDictionary.get("strMunicipality");
	   String strMeterGroup = objDictionary.get("strMeterGroup");
	   String strMeterName = objDictionary.get("strMeterName");
	   String strHost = objDictionary.get("strHost");
	   String strRemotePath = objDictionary.get("strRemotePath");
	   String strAndroidUdid = objDictionary.get("strAndroidUdid");
	   String strParkingId = objDictionary.get("strParkingId");
	   String strViolationId = objDictionary.get("strViolationId");
	   String strSilverBulletVersion = objDictionary.get("strSilverBulletVersion");
	   String strLink = objDictionary.getOrDefault("link", "");
	   String strAssociatedBug = objDictionary.get("strAssociatedBug");
	   String strPivotalPath = objDictionary.get("strPivotalPath");
	   if(strAssociatedBug == null){strAssociatedBug = "";}
	   Connection c = null;
	   Statement stmt = null;
	   String strColor = "Red";
	   if(strErrorMessage.equals(""))
	   {strColor = "Green";}
	   else
	   {
		   if(strErrorMessage.contains("-"))
		   {
			   String strError = strErrorMessage.substring(0,strErrorMessage.indexOf("-"));
			   if(strAssociatedBug.contains(strError))
			   {strColor = "Yellow";}
		   }
	   }
	   try
	   {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   if(strErrorMessage.contains("-") && strColor.equals("Red"))
		   {
			   String strPivotalNumber = strErrorMessage.substring(0, strErrorMessage.indexOf("-")+1);
			   strErrorMessage = strErrorMessage.replace(strPivotalNumber,"");
		   }
		   String sql = "INSERT INTO \"TESTRESULTS\" (\"TestCase\",\"MobileDeviceType\",\"Environment\",\"ErrorMessage\",\"ExecuteDate\",\"SentryLinkVersion\",\"MeterVersion\",\"AndroidVersion\",\"IOSVersion\",\"Municipality\",\"MeterGroup\",\"MeterName\",\"Host\",\"RemotePath\",\"Udid\",\"ParkingId\",\"ViolationId\",\"SilverBulletVersion\",\"ImageLink\",\"Color\",\"pivotalpath\")"
				   + "VALUES ('"+strTestCaseName+"','"+strMobileDeviceType+"', '"+strEnvironment+"', '"+strErrorMessage+"', '"+strDate+"', '"+strSentryLinkVersion+"', '"+strMeterVersion+"', '"+strAndroidVersion+"', '"+strIOSFullVersion+"', '"+strMunicipality+"','"+strMeterGroup+"','"+strMeterName+"','"+strHost+"','"+strRemotePath+"','"+strAndroidUdid+"','"+strParkingId+"','"+strViolationId+"','"+strSilverBulletVersion+"','"+strLink+"','"+strColor+"','"+strPivotalPath+"');";
		   if(strMobileDeviceType != null)
		   {
			   System.out.println(sql);
			   stmt.executeUpdate(sql);
		   }
		   stmt.close();
		   c.commit();
		   c.close();

	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getMessage()+"-"+strMethodName);}
	   System.out.println("Records created successfully");
   }

   public void WriteTestResultsTo_PaymentSitesDatabase(Map<String, String> objDictionary)
   {
	   String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	   String strPassword = objDictionary.get("strUniquePassword");
	   String strErrorMessage = objDictionary.get("strErrorMessage");
	   String strPaymentSite = objDictionary.get("strPaymentSite");
	   String strBrowser = objDictionary.get("strBrowser");
	   String strRemotePath = objDictionary.get("strRemotePath");
	   String strLink = objDictionary.getOrDefault("link", "");
	   if(strErrorMessage == null){strErrorMessage = "";}
	   else{strErrorMessage = strErrorMessage.replace("\n", "").replace("/", "//").replace("'", "''");}
	   String strEnvironment = objDictionary.get("strEnvironment").replace("\n", "").replace("'","''");
	   String strTestCaseName = objDictionary.get("strTestCaseName");
	   if(strTestCaseName != null){strTestCaseName = objDictionary.get("strTestCaseName").replace("\n", "");}
	   SimpleDateFormat dateFormatDateTime = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	   String strDate = dateFormatDateTime.format(new Date());
	   Connection c = null;
	   Statement stmt = null;
	   String strColor = "Red";
	   try
	   {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://10.10.101.63:5432/TestAutomationResults","mpsadmin", strPassword);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");
		   stmt = c.createStatement();
		   if(strErrorMessage.contains("-") && strColor.equals("Red"))
		   {
			   String strPivotalNumber = strErrorMessage.substring(0, strErrorMessage.indexOf("-")+1);
			   strErrorMessage = strErrorMessage.replace(strPivotalNumber,"");
		   }
		   String sql = "INSERT INTO \"PAYMENT\" (\"TestCase\",\"Environment\",\"PaymentSite\",\"Browser\",\"ErrorMessage\",\"ExecuteDate\",\"ImageLink\",\"remotepath\")"
				   + "VALUES ('"+strTestCaseName+"','"+strEnvironment+"', '"+strPaymentSite+"', '"+strBrowser+"', '"+strErrorMessage+"', '"+strDate+"', '"+strLink+"','"+strRemotePath+"');";
		   System.out.println(sql);
		   stmt.executeUpdate(sql);
		   stmt.close();
		   c.commit();
		   c.close();
	   }
	   catch (Exception e)
	   {UpdateErrorMessageWithPivotalData(objDictionary,e.getMessage()+"-"+strMethodName);}
	   System.out.println("Records created successfully");
   }
}
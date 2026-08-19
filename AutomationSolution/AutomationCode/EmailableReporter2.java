package AutomationCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.collections.Lists;
import org.testng.internal.Utils;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.Authenticator;
import jakarta.activation.DataSource;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Session;

public class EmailableReporter2 implements IReporter 
{
    private static final Logger LOG = Logger.getLogger(EmailableReporter2.class);

    protected PrintWriter writer;

    protected List<SuiteResult> suiteResults = Lists.newArrayList();

    // Reusable buffer
    private StringBuilder buffer = new StringBuilder();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,String outputDirectory) 
    {
        try 
        {
            writer = createWriter(outputDirectory);
        } 
        catch (IOException e) 
        {
            LOG.error("Unable to create output file", e);
            return;
        }
        String strTestSuiteName = "";
        for (ISuite suite : suites) 
        {
        	 if(suite != null){suiteResults.add(new SuiteResult(suite));}
        	 for (SuiteResult suiteResult : suiteResults) 
             {
             	if(suiteResult != null)
             	{
             		strTestSuiteName = suiteResult.getSuiteName();
             	}
             }
        }
        writeDocumentStart();
        writeHead();
        writeBody();
        writeDocumentEnd();
        writer.close();
        try
        {
//        	if(!strTestSuiteName.equals("Default suite"))
//        	{
	        	SendEmail(outputDirectory,strTestSuiteName);
//        	}
        }
        catch (Exception e){}
        System.out.println("Email Report To Team");
    }

	public void SendEmail(String strDirectory, String strTestSuiteName)
	{
		final String username = "sentryquality@gmail.com";
		final String password = "kcoeryhtkquikwyr";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Authenticator authenticator = new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		};
		Session session = Session.getInstance(props, authenticator);
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sentryquality@gmail.com"));
			// Parse the email addresses and obtain an array of InternetAddress objects
			InternetAddress[] recipients = InternetAddress.parse("darin@mpspark.com, " +
					"shawon.biswas@mpspark.com, rick.walz@mpspark.com");
			// Set the recipients using the Message.RecipientType.TO enum and the InternetAddress array
			message.setRecipients(Message.RecipientType.TO, recipients);
			//set email subject field
			message.setSubject("Automation Results-"+strTestSuiteName);
			// Create the first body part for the text content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Attached are the automation results \n\n" +
					"Green = Passed = Passed\n" +
					"Green With Pivotal = Check if Bug has been fixed\n" +
					"Yellow = Bug Exists and developer needs to fix\n" +
					"Red With Pivotal = Bug Exists and developer needs to fix\n" +
					"Red = Test team needs to determine if this is a bug\n\n");

			// Create the second body part for the file attachment
			BodyPart messageBodyPart2 = new MimeBodyPart();
			String filename = strDirectory + "/TestResults.html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			// Create a multipart message and add the body parts
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);
			// Set the multipart content to the message
			message.setContent(multipart);
			// Send the message
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			System.out.println("MIH");
			//Need to through up a message telling user there is a email issue.
			//CommonWeb.setWarningMsg("Unable to email Automation results-"+e);
		}
		System.out.println("Email sent successfully!");
	}
    
    protected PrintWriter createWriter(String outdir) throws IOException 
    {
        new File(outdir).mkdirs();
        return new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, "TestResults.html"))));
    }

    protected void writeDocumentStart() 
    {
        writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        writer.print("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    }

    protected void writeHead() 
    {
        writer.print("<head>");
        writer.print("<title>TestNG Report</title>");
        writeStylesheet();
        writer.print("</head>");
    }

    protected void writeStylesheet() 
    {
        writer.print("<style type=\"text/css\">");
        writer.print("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}");
        writer.print("th,td {border:1px solid #009;padding:.25em .5em}");
        writer.print("th {vertical-align:bottom}");
        writer.print("td {vertical-align:top}");
        writer.print("table a {font-weight:bold}");
        writer.print(".stripe td {background-color: #E6EBF9}");
        writer.print(".num {text-align:right}");
        writer.print(".blankrow td {background-color: #FFFFFF}");
        writer.print(".passed td {background-color: #52BE80}");
        writer.print(".passedeven td {background-color: #0A0}");
        writer.print(".skippedodd td {background-color: #DDD}");
        writer.print(".skippedeven td {background-color: #CCC}");
        //writer.print(".failedodd td,.attn {background-color: #F33}");
        writer.print(".failed td {background-color: #D00}");
        writer.print(".warning td {background-color: gold;}");
        writer.print(".fixed td {background-color: #52BE80;}");
        //writer.print(".failedeven td,.stripe .attn {background-color: #D00}");
        writer.print(".failedeven td {background-color: #D00}");
        writer.print(".stacktrace {white-space:pre;font-family:monospace}");
        writer.print(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}");
        writer.print("</style>");
    }

    protected void writeBody() 
    {
        writer.print("<body>");
        writeSuiteSummary();
        writeScenarioSummary();
        //writeScenarioDetails(arrTestCasesWithVariables);
        writer.print("</body>");
    }

    protected void writeDocumentEnd() 
    {
        writer.print("</html>");
    }

    protected void writeSuiteSummary() 
    {
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormat decimalFormat = NumberFormat.getNumberInstance();

        int totalPassedTests = 0;
        int totalSkippedTests = 0;
        int totalFailedTests = 0;
        long totalDuration = 0;

        writer.print("<table>");
        writer.print("<tr>");
        writer.print("<th>Test</th>");
        writer.print("<th># Passed</th>");
        writer.print("<th># Skipped</th>");
        writer.print("<th># Failed</th>");
        writer.print("<th>Time (s)</th>");
        writer.print("<th>Included Groups</th>");
        writer.print("<th>Excluded Groups</th>");
        writer.print("</tr>");
        writer.print("<h3 id=\"summary\"></h3>");
        int testIndex = 0;
        for (SuiteResult suiteResult : suiteResults) 
        {
        	if(suiteResult != null)
        	{
        		//System.out.println(suiteResult.getSuiteName());
        		writer.print("<tr><th colspan=\"7\">");
	            writer.print(Utils.escapeHtml(suiteResult.getSuiteName()));
	            writer.print("</th></tr>");
	            System.out.println("Total Tests Executed: "+suiteResult.getTestResults().size());
	            for (TestResult testResult : suiteResult.getTestResults()) 
	            {
	            	int passedTests = testResult.getPassedTestCount();
	                int skippedTests = testResult.getSkippedTestCount();
	                int failedTests = testResult.getFailedTestCount();
	                long duration = testResult.getDuration();
	                //Check the Pass Fail Counts here.
	                writer.print("<tr");
	                if ((testIndex % 2) == 1) 
	                {
	                    writer.print(" class=\"stripe\"");
	                }
	                writer.print(">");
	                buffer.setLength(0);
	                writeTableData(buffer.append("<a href=\"#t").append(testIndex).append("\">").append(Utils.escapeHtml(testResult.getTestName())).append("</a>").toString());
	                writeTableData(integerFormat.format(passedTests), "num");
	                writeTableData(integerFormat.format(skippedTests),(skippedTests > 0 ? "num attn" : "num"));
	                writeTableData(integerFormat.format(failedTests),(failedTests > 0 ? "num attn" : "num"));
	                long lDurationSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
	                writeTableData(decimalFormat.format(lDurationSeconds), "num");
	                writeTableData(testResult.getIncludedGroups());
	                writeTableData(testResult.getExcludedGroups());
	                writer.print("</tr>");
	                totalPassedTests += passedTests;
	                totalSkippedTests += skippedTests;
	                totalFailedTests += failedTests;
	                totalDuration += lDurationSeconds;
	                testIndex++;
	            }
	        }
        }
        long lTotalDurationSeconds = TimeUnit.MILLISECONDS.toSeconds(totalDuration);
        // Print totals if there was more than one test
        if (testIndex > 1) 
        {
            writer.print("<tr>");
            writer.print("<th>Total</th>");
            writeTableHeader(integerFormat.format(totalPassedTests), "num");
            writeTableHeader(integerFormat.format(totalSkippedTests),(totalSkippedTests > 0 ? "num attn" : "num"));
            writeTableHeader(integerFormat.format(totalFailedTests),(totalFailedTests > 0 ? "num attn" : "num"));
            writeTableHeader(decimalFormat.format(lTotalDurationSeconds), "num");
            writer.print("<th colspan=\"2\"></th>");
            writer.print("</tr>");
        }
        writer.print("</table>");
    }
    protected void writeScenarioSummary()
    {
    	Pivotal clsPivotal = new Pivotal();
        int scenarioIndex = 0;
        List<String> arrTestCasesWithVariables = new ArrayList<String>();
        List<String> arrTestCaseForScenarioDetails = new ArrayList<String>();
        List<String> arrTestVariables = new ArrayList<String>();
        for (SuiteResult suiteResult : suiteResults) 
        {
        	if(suiteResult != null)
        	{
        		writer.print("<table>");
        		writer.print("<tbody><tr><th colspan=\"5\">");
        		writer.print("<col span=\"1\" style=\"width: 100%;\">");
        		writer.print("<td bgcolor=\"#F5B041\">Test Suite</td>");
        		writer.print("<td bgcolor=\"#F5B041\">"+Utils.escapeHtml(suiteResult.getSuiteName())+"</td>");
	            writer.print("</th></tr></tbody>");
	            //Create Array Of Test Cases Variables From Passed And Failed Test Results
	            String strTestName = "";
	            String strPivotalNumber = "";
	            String strPivotalStatus = "";
	            for (TestResult testResult : suiteResult.getTestResults()) 
	            {
	            	strTestName = testResult.getTestName();
	            	//Get Failed Test Case And stores the Report Variables arrTestCases
	            	List<ClassResult> classFailResults = testResult.getFailedTestResults();
	            	for (ClassResult classResult : classFailResults) 
		            {
		            	for (MethodResult methodResult : classResult.getMethodResults()) 
		            	{
		            		List<ITestResult> results = methodResult.getResults();
		            		int resultsCount = results.size();
		            		assert resultsCount > 0;
		            		ITestResult firstResult = results.iterator().next();
		            		String methodName = Utils.escapeHtml(firstResult.getMethod().getMethodName());
		            		for (ITestResult result : results) 
		            		{
		            			//Moved from above
		            			long start = result.getStartMillis();
			            		long duration = result.getEndMillis() - start;
		            			strPivotalNumber = "";
		            			strPivotalStatus = "";
		            			String strBugStatus = "failed";
		            			List<String> reporterMessages2 = Reporter.getOutput(result);
		            			for (int i=1;i < reporterMessages2.size();i++)
		            			{
		            				String strErrorMessage = reporterMessages2.get(reporterMessages2.size() - i);
		            				if(reporterMessages2.get(reporterMessages2.size() -i).contains("Gold"))
		            				{
		            					strPivotalNumber = strErrorMessage.substring(strErrorMessage.indexOf("stories/") +8, strErrorMessage.indexOf("\" target"));
			            				strBugStatus = "warning";break;
		            				}
		            				else if(reporterMessages2.get(reporterMessages2.size() -i).contains("Red"))
		            				{
		            					strPivotalNumber = strErrorMessage.substring(strErrorMessage.indexOf("stories/") +8, strErrorMessage.indexOf("\" target"));
			            				strBugStatus = "failed";break;
		            				}
		            			}
//		            			try{strPivotalStatus = clsPivotal.GetPivotalStatus(strPivotalNumber);}catch (Exception e){}
		            			arrTestCasesWithVariables.add(strTestName+"|"+start+"|"+methodName+"|"+strBugStatus+"|"+duration+"|"+strPivotalNumber+"|"+strPivotalStatus);
		            		}
		            	}
		            }
	            	// Get Passed Test Case And stores the Report Variables arrTestCases
		            List<ClassResult> classPassResults = testResult.getPassedTestResults();
		            strPivotalNumber = "";
		            strPivotalStatus = "";
		            for (ClassResult classResult : classPassResults) 
		            {
		            	strPivotalStatus = "";
		            	for (MethodResult methodResult : classResult.getMethodResults()) 
		            	{
		            		List<ITestResult> results = methodResult.getResults();
		            		int resultsCount = results.size();
		            		assert resultsCount > 0;
		            		//HERE
		            		for (ITestResult result : results) 
		            		{
		            			//Moved this from above
		            			ITestResult firstResult = results.iterator().next();
			            		String methodName = Utils.escapeHtml(firstResult.getMethod().getMethodName());
			            		long start = result.getStartMillis();
			            		long duration = result.getEndMillis() - start;
			            		strPivotalNumber = "";
		            			strPivotalStatus = "";
		            			List<String> reporterMessages2 = Reporter.getOutput(result);
		            			for (int i=1;i < reporterMessages2.size();i++)
		            			{
		            				String strErrorMessage = reporterMessages2.get(reporterMessages2.size() - i);
		            				//System.out.println(strErrorMessage);
		            				if(reporterMessages2.get(reporterMessages2.size() -i).contains("<font color='Blue'"))
		            				{
		            					strPivotalNumber  = strErrorMessage.substring(strErrorMessage.indexOf(">")+1, strErrorMessage.indexOf("-"));
		            				}
		            			}
		            			try
		            			{
		            				if(!strPivotalNumber.equals("")){strPivotalStatus = clsPivotal.GetPivotalStatus(strPivotalNumber);}
		            			}
		            			catch (Exception e){}
		            			//Need to get last line of test case Moved this here 
			            		arrTestCasesWithVariables.add(strTestName+"|"+start+"|"+methodName+"|passed|"+duration+"|"+strPivotalNumber+"|"+strPivotalStatus);
			            	}
		            	}
		            }
		            List<ClassResult> classSkippedResults = testResult.getSkippedTestResults();
		            strPivotalNumber = "";
		            strPivotalStatus = "";
		            for (ClassResult classResult : classSkippedResults) 
		            {
		            	strPivotalStatus = "";
		            	for (MethodResult methodResult : classResult.getMethodResults()) 
		            	{
		            		List<ITestResult> results = methodResult.getResults();
		            		int resultsCount = results.size();
		            		assert resultsCount > 0;
		            		ITestResult firstResult = results.iterator().next();
		            		String methodName = Utils.escapeHtml(firstResult.getMethod().getMethodName());
		            		long start = firstResult.getStartMillis();
		                    long duration = firstResult.getEndMillis() - start;
		                    //Need to get last line of test case
		                    arrTestCasesWithVariables.add(strTestName+"|"+start+"|"+methodName+"|skipped|"+duration+"|"+strPivotalNumber+"|"+strPivotalStatus);
			            }
		            }
	            }
	            
	            //Create Table Header
//		        writer.print("<tbody id=\"t");
//		        writer.print(testIndex);
//		        writer.print("\">");
		        //Sort By Test Name
		        Collections.sort(arrTestCasesWithVariables);
		        //Cycle Through all TestCases 
		        int a = 0;
		        for (int i=0; i<arrTestCasesWithVariables.size();i++)
		        {
		        	String strCurrentTestName = "";
		        	String strNextTestName = "";
		        	//Separate Test Case By Test Case Name
		        	List<String> arrTestCaseTestMethods = new ArrayList<String>();
		        	
		        	do
		    	    {
		        		String[] arrTestResultVariables = arrTestCasesWithVariables.get(a).split("\\|", -1);
		        		strCurrentTestName = arrTestResultVariables[0];
		        		String strStart = arrTestResultVariables[1];
		            	String strMethodName = arrTestResultVariables[2];
		            	//System.out.println(strMethodName);
		            	String strPassFail = arrTestResultVariables[3];
		            	String strDuration = arrTestResultVariables[4];
		            	strPivotalNumber = arrTestResultVariables[5];
		            	strPivotalStatus = arrTestResultVariables[6];
		            	//Create a New Array Methods within TestCase     
		        		arrTestCaseTestMethods.add(strStart+"|"+strMethodName+"|"+strPassFail+"|"+strDuration+"|"+strPivotalNumber+"|"+strPivotalStatus);
		        		arrTestVariables.add(strCurrentTestName+"|"+i);
		        		arrTestCaseForScenarioDetails.add(strStart+"|"+strMethodName+"|"+strPassFail+"|"+strDuration+"|"+strPivotalNumber+"|"+strPivotalStatus+"|"+i);
		        		a++;
		        		if(arrTestCasesWithVariables.size() > a)
		        		{
		        			arrTestResultVariables = arrTestCasesWithVariables.get(a).split("\\|", -1);
		        			strNextTestName = arrTestResultVariables[0];
		        			if(strCurrentTestName.equals(strNextTestName)){i++;}
		        		}
		        		else
		        		{strNextTestName = "";}
		    	    }while (strCurrentTestName.equals(strNextTestName));
			        
		        	writer.print("</table>");
		            writer.print("<table>");
		            writer.print("<col span=\"1\" style=\"width: 15%;\">");
		            writer.print("<col span=\"1\" style=\"width: 55%;\">");
		            writer.print("<col span=\"1\" style=\"width: 10%;\">");
		            writer.print("<col span=\"1\" style=\"width: 10%;\">");
		            writer.print("<col span=\"1\" style=\"width: 10%;\">");
		            writer.print("<col span=\"1\" style=\"width: 10%;\">");
		            writer.print("<thead>");
		            writer.print("<tr>");
		            writer.print("<th>Test Suite</th>");
		            writer.print("<th>Test Case</th>");
		            writer.print("<th>Start</th>");
		            writer.print("<th>Time (sec)</th>");
		            writer.print("<th>Pivotal Number</th>");
		            writer.print("<th>Pivotal Status</th>");
		            writer.print("</tr>");
		            writer.print("</thead>");
		        	
		        	//Sort By Time And write to Report
		            Collections.sort(arrTestCaseTestMethods);

		            for (int j = 0; j < arrTestCaseTestMethods.size(); j++) {
		                // Split Array
		                String[] arrTestMethodVariables = arrTestCaseTestMethods.get(j).split("\\|", -1);
		                String strStart = arrTestMethodVariables[0];
		                String strTestCase = arrTestMethodVariables[1];
		                String strPassFail = arrTestMethodVariables[2];
		                String strDuration = arrTestMethodVariables[3];
		                strPivotalNumber = arrTestMethodVariables[4];

		                // Format Start Date
		                DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
		                strStart = formatter.format(Long.parseLong(strStart));

		                // Converts Milliseconds to Seconds
		                long lDurationSeconds = TimeUnit.MILLISECONDS.toSeconds(Integer.parseInt(strDuration));

		                buffer.setLength(0);
		                String cssClass = strPassFail;

		                // Start the new table row
		                writer.print("<tr class=\"");
		                writer.print(cssClass);
		                writer.print("\">");

		                // Test Class Name
		                writer.print("<td>");
		                writer.print(Utils.escapeHtml(strCurrentTestName));
		                writer.print("</td>");

		                // Test Case Column
		                if (strPassFail.equals("passed")) {
		                    writer.print("<td>");
		                    writer.print(strTestCase);
		                    writer.print("</td>");
		                } else {
		                    writer.print("<td><a href=\"#m");
		                    writer.print(scenarioIndex);
		                    writer.print("\">");
		                    writer.print(strTestCase);
		                    writer.print("</a></td>");
		                    scenarioIndex++;
		                }

		                // Time, Duration, Pivotal Number, and Status
		                writer.print("<td>");
		                writer.print(strStart);
		                writer.print("</td>");

		                writer.print("<td>");
		                writer.print(lDurationSeconds);
		                writer.print("</td>");

		                writer.print("<td>");
		                writer.print(strPivotalNumber);
		                writer.print("</td>");

		                writer.print("<td>");
		                writer.print(strPivotalStatus);
		                writer.print("</td>");

		                // End the table row
		                writer.print("</tr>");
		            }

		            // Add a blank row at the end for spacing
		            writer.print("<tr class=\"blankrow\"><td bgcolor=\"#FFFFFF\" colspan=\"6\">&nbsp;</td></tr>");
		            writer.print("</table>");
		        }
	        }
        }
        //writer.print("<tr class=\"blankrow\"><td bgcolor=\"#FFFFFF\" colspan=\"4\">&nbsp;</td></tr>");
	    writeScenarioDetails();
    }
    protected void writeScenarioDetails() 
    {
    	//Create a array of Test Suite Names
    	//Sort the list
    	//Loop through the the test suite
    	
    	for (SuiteResult suiteResult : suiteResults) 
    	{
    		System.out.println("Test Suite Name: "+suiteResult.getSuiteName());//ParallelTest 1
    		if(suiteResult != null)
    	  	{
    			String strTestName = "";
    			//Create An Array of TestCase Names
    			List<String> arrTestCasesNames = new ArrayList<String>();
    			for (TestResult testResult : suiteResult.getTestResults()) 
		    	{
    				strTestName = testResult.getTestName();
    				arrTestCasesNames.add(strTestName);
    			}
    			Collections.sort(arrTestCasesNames);
    			int intTestCounter = 0;
    			//for (TestResult testResult : suiteResult.getTestResults()) 
    			int intLinkIndex = 0;
    			int intTestCaseNumberCounter = 0;
    			do
    			{
    				//String strTestCaseNumbers = arrTestCasesNumbers.get(intTestCaseNumberCounter);
    				TestResult testResult = suiteResult.getTestResults().get(intTestCaseNumberCounter);
    				strTestName = testResult.getTestName();
    				if(arrTestCasesNames.get(intTestCounter).equals(strTestName))
    				{
			         	//Get Failed Test Case And stores the Report Variables arrTestCases
			         	List<ClassResult> classFailResults = testResult.getFailedTestResults();
			         	for (ClassResult classResult : classFailResults) 
			         	{
			         		String className = classResult.getClassName();
			         		for (MethodResult methodResult : classResult.getMethodResults()) 
			            	{
			            		List<ITestResult> results = methodResult.getResults();
			            		Collections.sort(results);
			            		int resultsCount = results.size();
			            		assert resultsCount > 0;
			            		ITestResult firstResult = results.iterator().next();
//			            		String methodName = Utils.escapeHtml(firstResult.getMethod().getMethodName());
//			            		for (ITestResult result : results);
//			            		{
			            			System.out.println("resultsize:"+results.size());
		        					//Loop Here
		        					int intInvocationCount = 0;
		        					do
		        					{
				            			String label = Utils.escapeHtml(className+ "#"+ results.get(intInvocationCount).getMethod().getMethodName());
		        						ITestResult result = results.get(intInvocationCount);
		        						List<String> reporterMessages = Reporter.getOutput(result);
		        						Collections.sort(reporterMessages);
		        						writeScenario(intLinkIndex, label, result);
		        						intLinkIndex++;
		        						intInvocationCount++;
		        					}while (intInvocationCount < resultsCount);
//			            		}
			            	}
			            }
			         	arrTestCasesNames.remove(strTestName);
			         	//Reset Counter
			         	intTestCaseNumberCounter = 0;
			         	intTestCounter = 0;
    				}
    				else
    				{
    					intTestCaseNumberCounter++;
    				}
	    		}while (arrTestCasesNames.size() > 0);
    		}
    	}
    	//END TEST
    }	
    	
    	
    	
//    	int intNbrOTestSuites = suiteResults.size();
//    	System.out.println("Number Of Test Suites: "+intNbrOTestSuites);
//		for (SuiteResult suiteResult : suiteResults) 
//		{	
//			//ParallelTest
//			System.out.println("Test Suite Name: "+suiteResult.getSuiteName());//ParallelTest 1
//			if(suiteResult != null)
//    		{
//    			int intFaileCounter = 0;
////	    		for (int i = 0; i<arrTestCasesWithVariables.size(); i++)
////	    		{
//	    			int i = 0;
////	        		String[] arrTestResultVariables = arrTestCasesWithVariables.get(i).split("\\|", -1);
////	            	String strTestCase = arrTestResultVariables[1];
////	            	String strPassFail = arrTestResultVariables[2];
////	            	String strErrorIndex = arrTestResultVariables[6];
//	            	int intCounter = 0;
//	        		for (TestResult testResult : suiteResult.getTestResults()) 
//	        		{
//	        			System.out.println("Number Of Test Suites: "+suiteResult.getTestResults().size());//8 SOrt
//	        			
////	        			List<TestResult> LTestResults = suiteResult.getTestResults();
////	        			
////	        			
////	        			
////	        			outputList = (List<ITestResult>) LTestResults.getAttribute(listName);
////	        			
////	        			LTestResults
//	        			
////	        			Cycle threw the lis and order them by testName.
////	        			
////	        			Or we could cycle through the list for each element
//	        			
//	        			//Collections.sort("testName",LTestResults);
//	        			
//	        			List<ClassResult> classFailResults = testResult.getFailedTestResults();
//	        			
//	        			
////	        			List<Student> studentList = new LinkedList<>();
////	        			Collections.sort(studentList, Student.Comparators.AGE);
//	        			
//	        			
////	        			List<MethodResult> resultsPerClass = Lists.newArrayList();
////	                    List<ITestResult> resultsPerMethod = Lists.newArrayList();
////
////	                    List<ITestResult> resultsList = Lists.newArrayList(results);
////	                    Collections.sort(resultsList, RESULT_COMPARATOR);
////	                    Iterator<ITestResult> resultsIterator = resultsList.iterator();
////	                    assert resultsIterator.hasNext();
//	        			
//	        			
//	        			
//						int intNbrOFailedResults = classFailResults.size();
//						System.out.println("Number Of Failed Results: "+intNbrOFailedResults);//1
//						//Order By Test Suite?
//						for (ClassResult classResult : classFailResults) 
//	        			{
//							
//							String className = classResult.getClassName();
//	        				for (MethodResult methodResult : classResult.getMethodResults()) 
//	        				{
//	        					List<ITestResult> results = methodResult.getResults();
//	        					Collections.sort(results);
//	        					
//	        					
//	        					//Order by Timestamp
//	        					
//	        					int resultsCount = results.size();
//	        					//assert resultsCount > 0;
//	        					System.out.println("resultsize:"+results.size());
//	        					
//	        					//Loop Here
//	        					int intInvocationCount = 0;
//	        					do
//	        					{
//		        					//ITestResult teststeps = results.iterator().next();
//		        					
//	        						ITestResult teststeps = results.get(intInvocationCount);
//		        					
//				        					String methodName = Utils.escapeHtml(teststeps.getMethod().getMethodName());
//				        					
//				        					//Cycle throw to get 
//				        					//arrTestCasesWithVariables
//				        			
////				        					for (int z = 0; i<arrTestVariables.size(); i++)
////				        		    		{
////				        		        		String[] arrTestResultVariables = arrTestVariables.get(z).split("\\|", -1);
////				        		        		
////				        		        		String strTestName = arrTestResultVariables[0];
////				        		        		String strSummaryIndex = arrTestResultVariables[0];
////				        		        		
////				        		        		System.out.println("MIH");
////				        		            	
////				        		    		}
//				        				
//				        					
//				        					
////				        					if(methodName.equals(strTestCase))
////				        					{
//		        						System.out.println(className+ "#"+ results.iterator().next().getMethod().getMethodName());
//			        					String label = Utils.escapeHtml(className+ "#"+ results.get(intInvocationCount).getMethod().getMethodName());
//		        						ITestResult result = results.get(intInvocationCount);
//		        						List<String> reporterMessages = Reporter.getOutput(result);
//		        						Collections.sort(reporterMessages);
//		        						
//		        				        if (!reporterMessages.isEmpty()) 
//		        				        {
//		        				           System.out.println(reporterMessages);
//		        				        }
//		        						writeScenario(intCounter, label, result);
//		        						intCounter++;
//		        						intInvocationCount++;
////				        					}
//	        					}while (intInvocationCount < resultsCount);
//	        					break;
//	        				}
//	        			} 
//			        }
	        		//break;
//	            } 
//	    	}
//        }
//    }
    private void writeScenario(int scenarioIndex, String label,ITestResult result) 
    {
        writer.print("<h3 id=\"m");//I think this is the link index
        writer.print(scenarioIndex);
        writer.print("\">");
        writer.print(label);
        writer.print("</h3>");
        writer.print("<table class=\"result\">");
        // Write test parameters (if any)
        Object[] parameters = result.getParameters();
        int parameterCount = (parameters == null ? 0 : parameters.length);
        if (parameterCount > 0) 
        {
            writer.print("<tr class=\"param\">");
            for (int i = 1; i <= parameterCount; i++) 
            {
                writer.print("<th>Parameter #");
                writer.print(i);
                writer.print("</th>");
            }
            writer.print("</tr><tr class=\"param stripe\">");
            for (Object parameter : parameters) 
            {
                writer.print("<td>");
                writer.print(Utils.escapeHtml(Utils.toString(parameter)));
                writer.print("</td>");
            }
            writer.print("</tr>");
        }

        // Write reporter messages (if any)
        List<String> reporterMessages = Reporter.getOutput(result);
        if (!reporterMessages.isEmpty()) 
        {
            writer.print("<tr><th");
            if (parameterCount > 1) {
                writer.print(" colspan=\"");
                writer.print(parameterCount);
                writer.print("\"");
            }
            writer.print(">Test Steps</th></tr>");

            writer.print("<tr><td");
            if (parameterCount > 1) 
            {
                writer.print(" colspan=\"");
                writer.print(parameterCount);
                writer.print("\"");
            }
            writer.print(">");
            writeReporterMessages(reporterMessages);
            writer.print("</td></tr>");
        }

        // Write exception (if any)
        Throwable throwable = result.getThrowable();
        if (throwable != null) 
        {
            writer.print("<tr><th");
            if (parameterCount > 1) 
            {
                writer.print(" colspan=\"");
                writer.print(parameterCount);
                writer.print("\"");
            }
            writer.print(">");
            writer.print((result.getStatus() == ITestResult.SUCCESS ? "Expected Exception": "Exception"));
            writer.print("</th></tr>");
            writer.print("<tr><td");
            if (parameterCount > 1) 
            {
                writer.print(" colspan=\"");
                writer.print(parameterCount);
                writer.print("\"");
            }
            writer.print(">");
            writeStackTrace(throwable);
            writer.print("</td></tr>");
        }
        writer.print("</table>");
        writer.print("<p class=\"totop\"><a href=\"#summary\">back to summary</a></p>");
    }
    protected void writeReporterMessages(List<String> reporterMessages) 
    {
        writer.print("<div class=\"messages\">");
        Iterator<String> iterator = reporterMessages.iterator();
        assert iterator.hasNext();
        writer.print(iterator.next());
        while (iterator.hasNext())
        {
            writer.print("<br/>");
            writer.print(iterator.next());
        }
        writer.print("</div>");
    }
//    protected void writeStackTrace(Throwable throwable) 
//    {
//        writer.print("<div class=\"stacktrace\">");
//        writer.print(Utils.stackTrace(throwable, true)[0]);
//        writer.print("</div>");
//    }
    protected void writeStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);  // standard method to get stack trace
        writer.print("<div class=\"stacktrace\">");
        writer.print(sw.toString());
        writer.print("</div>");
    }
    /**
     * Writes a TH element with the specified contents and CSS class names.
     * 
     * @param html
     *            the HTML contents
     * @param cssClasses
     *            the space-delimited CSS classes or null if there are no
     *            classes to apply
     */
    protected void writeTableHeader(String html, String cssClasses) 
    {
        writeTag("th", html, cssClasses);
    }
    /**
     * Writes a TD element with the specified contents.
     * 
     * @param html
     *            the HTML contents
     */
    protected void writeTableData(String html) 
    {
        writeTableData(html, null);
    }
    /**
     * Writes a TD element with the specified contents and CSS class names.
     * 
     * @param html
     *            the HTML contents
     * @param cssClasses
     *            the space-delimited CSS classes or null if there are no
     *            classes to apply
     */
    protected void writeTableData(String html, String cssClasses) 
    {
        writeTag("td", html, cssClasses);
    }
    /**
     * Writes an arbitrary HTML element with the specified contents and CSS
     * class names.
     * 
     * @param tag
     *            the tag name
     * @param html
     *            the HTML contents
     * @param cssClasses
     *            the space-delimited CSS classes or null if there are no
     *            classes to apply
     */
    protected void writeTag(String tag, String html, String cssClasses) 
    {
        writer.print("<");
        writer.print(tag);
        if (cssClasses != null) 
        {
            writer.print(" class=\"");
            writer.print(cssClasses);
            writer.print("\"");
        }
        writer.print(">");
        writer.print(html);
        writer.print("</");
        writer.print(tag);
        writer.print(">");
    }
    /**
     * Groups {@link TestResult}s by suite.
     */
    protected static class SuiteResult 
    {
        private final String suiteName;
        private final List<TestResult> testResults = Lists.newArrayList();

        public SuiteResult(ISuite suite) 
        {
            suiteName = suite.getName();
            for (ISuiteResult suiteResult : suite.getResults().values()) 
            {
                testResults.add(new TestResult(suiteResult.getTestContext()));
            }
        }
        public String getSuiteName() 
        {
            return suiteName;
        }

        /**
         * @return the test results (possibly empty)
         */
        public List<TestResult> getTestResults() 
        {
            return testResults;
        }
    }
    /**
     * Groups {@link ClassResult}s by test, type (configuration or test), and
     * status.
     */
    protected static class TestResult 
    {
        /**
         * Orders test results by class name and then by method name (in
         * lexicographic order).
         */
        protected static final Comparator<ITestResult> RESULT_COMPARATOR = new Comparator<ITestResult>() 
        {
            @Override
            public int compare(ITestResult o1, ITestResult o2) 
            {
                int result = o1.getTestClass().getName().compareTo(o2.getTestClass().getName());
                if (result == 0) 
                {
                    result = o1.getMethod().getMethodName().compareTo(o2.getMethod().getMethodName());
                }
                return result;
            }
        };

        private final String testName;
        private final List<ClassResult> failedConfigurationResults;
        private final List<ClassResult> failedTestResults;
        private final List<ClassResult> skippedConfigurationResults;
        private final List<ClassResult> skippedTestResults;
        private final List<ClassResult> passedTestResults;
        private final int failedTestCount;
        private final int skippedTestCount;
        private final int passedTestCount;
        private final long duration;
        private final String includedGroups;
        private final String excludedGroups;

        public TestResult(ITestContext context) 
        {
            testName = context.getName();
            Set<ITestResult> failedConfigurations = context.getFailedConfigurations().getAllResults();
            Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
            Set<ITestResult> skippedConfigurations = context.getSkippedConfigurations().getAllResults();
            Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
            Set<ITestResult> passedTests = context.getPassedTests().getAllResults();
            failedConfigurationResults = groupResults(failedConfigurations);
            failedTestResults = groupResults(failedTests);
            skippedConfigurationResults = groupResults(skippedConfigurations);
            skippedTestResults = groupResults(skippedTests);
            passedTestResults = groupResults(passedTests);
            failedTestCount = failedTests.size();
            skippedTestCount = skippedTests.size();
            passedTestCount = passedTests.size();
            duration = context.getEndDate().getTime()
                    - context.getStartDate().getTime();
            includedGroups = formatGroups(context.getIncludedGroups());
            excludedGroups = formatGroups(context.getExcludedGroups());
        }
        /**
         * Groups test results by method and then by class.
         */
        protected List<ClassResult> groupResults(Set<ITestResult> results) 
        {
            List<ClassResult> classResults = Lists.newArrayList();
            if (!results.isEmpty()) 
            {
                List<MethodResult> resultsPerClass = Lists.newArrayList();
                List<ITestResult> resultsPerMethod = Lists.newArrayList();

                List<ITestResult> resultsList = Lists.newArrayList(results);
                Collections.sort(resultsList, RESULT_COMPARATOR);
                Iterator<ITestResult> resultsIterator = resultsList.iterator();
                assert resultsIterator.hasNext();

                ITestResult result = resultsIterator.next();
                resultsPerMethod.add(result);

                String previousClassName = result.getTestClass().getName();
                String previousMethodName = result.getMethod().getMethodName();
                while (resultsIterator.hasNext()) 
                {
                    result = resultsIterator.next();
                    String className = result.getTestClass().getName();
                    if (!previousClassName.equals(className)) 
                    {
                        // Different class implies different method
                        assert !resultsPerMethod.isEmpty();
                        resultsPerClass.add(new MethodResult(resultsPerMethod));
                        resultsPerMethod = Lists.newArrayList();

                        assert !resultsPerClass.isEmpty();
                        classResults.add(new ClassResult(previousClassName,resultsPerClass));
                        resultsPerClass = Lists.newArrayList();

                        previousClassName = className;
                        previousMethodName = result.getMethod().getMethodName();
                    } 
                    else 
                    {
                        String methodName = result.getMethod().getMethodName();
                        if (!previousMethodName.equals(methodName)) 
                        {
                            assert !resultsPerMethod.isEmpty();
                            resultsPerClass.add(new MethodResult(resultsPerMethod));
                            resultsPerMethod = Lists.newArrayList();
                            previousMethodName = methodName;
                        }
                    }
                    resultsPerMethod.add(result);
                }
                assert !resultsPerMethod.isEmpty();
                resultsPerClass.add(new MethodResult(resultsPerMethod));
                assert !resultsPerClass.isEmpty();
                classResults.add(new ClassResult(previousClassName,resultsPerClass));
            }
            return classResults;
        }

        public String getTestName() 
        {
            return testName;
        }

        /**
         * @return the results for failed configurations (possibly empty)
         */
        public List<ClassResult> getFailedConfigurationResults() 
        {
            return failedConfigurationResults;
        }

        /**
         * @return the results for failed tests (possibly empty)
         */
        public List<ClassResult> getFailedTestResults() 
        {
        	return failedTestResults;
        }

        /**
         * @return the results for skipped configurations (possibly empty)
         */
        public List<ClassResult> getSkippedConfigurationResults() 
        {
            return skippedConfigurationResults;
        }

        /**
         * @return the results for skipped tests (possibly empty)
         */
        public List<ClassResult> getSkippedTestResults() 
        {
            return skippedTestResults;
        }

        /**
         * @return the results for passed tests (possibly empty)
         */
        public List<ClassResult> getPassedTestResults() 
        {
            return passedTestResults;
        }

        public int getFailedTestCount() 
        {
            return failedTestCount;
        }

        public int getSkippedTestCount() 
        {
            return skippedTestCount;
        }

        public int getPassedTestCount() 
        {
            return passedTestCount;
        }

        public long getDuration() 
        {
            return duration;
        }

        public String getIncludedGroups() 
        {
            return includedGroups;
        }

        public String getExcludedGroups() 
        {
            return excludedGroups;
        }

        /**
         * Formats an array of groups for display.
         */
        protected String formatGroups(String[] groups) 
        {
            if (groups.length == 0) 
            {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            builder.append(groups[0]);
            for (int i = 1; i < groups.length; i++) 
            {
                builder.append(", ").append(groups[i]);
            }
            return builder.toString();
        }
    }
    /**
     * Groups {@link MethodResult}s by class.
     */
    protected static class ClassResult 
    {
        private final String className;
        private final List<MethodResult> methodResults;

        /**
         * @param className
         *            the class name
         * @param methodResults
         *            the non-null, non-empty {@link MethodResult} list
         */
        public ClassResult(String className, List<MethodResult> methodResults) 
        {
            this.className = className;
            this.methodResults = methodResults;
        }

        public String getClassName() 
        {
            return className;
        }

        /**
         * @return the non-null, non-empty {@link MethodResult} list
         */
        public List<MethodResult> getMethodResults() 
        {
            return methodResults;
        }
    }
    /**
     * Groups test results by method.
     */
    protected static class MethodResult 
    {
        private final List<ITestResult> results;

        /**
         * @param results
         *            the non-null, non-empty result list
         */
        public MethodResult(List<ITestResult> results) 
        {
            this.results = results;
        }

        /**
         * @return the non-null, non-empty result list
         */
        public List<ITestResult> getResults() 
        {
            return results;
        }
    }
}
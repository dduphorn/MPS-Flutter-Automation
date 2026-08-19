package AutomationCode;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import javax.xml.soap.MessageFactory;
//import javax.xml.soap.MimeHeaders;
//import javax.xml.soap.SOAPBody;
//import javax.xml.soap.SOAPException;
//import javax.xml.soap.SOAPMessage;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//import org.w3c.css.sac.InputSource;
//import java.io.StringReader;
//import java.io.InputStream;
//import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class SOAP
{
    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    public String GetViolationXML(Map<String, String> objDictionary) throws Exception
    {
    	String strEnvironment = objDictionary.get("strEnvironment");
    	String strXMLResponse ="";
        try
        {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            // Send SOAP Message to SOAP Server
            SOAPMessage soapRequest = createSOAPRequest(objDictionary,strEnvironment);
            //What is the SOAP server URL?  Is this the end pointl
            SOAPMessage soapResponse = null;
            String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
      		switch (strEnvironment)
     		{
     			case "QA":
     				soapResponse = soapConnection.call(soapRequest, "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/violation_exports/action");
     				break;
     			case "SG":
     				soapResponse = soapConnection.call(soapRequest, "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/violation_exports/action");
     				break;
     			case "PROD":
     				soapResponse = soapConnection.call(soapRequest, "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/violation_exports/action");
     				break;
     		}
      		//Process the SOAP Response
            strXMLResponse = ReturnSOAPResponse(soapResponse);
            System.out.println(strXMLResponse);
            soapConnection.close();
        }
        catch (Exception e)
        {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
        return strXMLResponse;
    }

    public SOAPMessage createSOAPRequest(Map<String, String> objDictionary,String strEnvironment) throws Exception
    {
    	CommonWeb clsCommonWeb = new CommonWeb();
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String serverURI = "https://mpspark.com/api/v1/violation_exports";
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("SoapNS", serverURI);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("ExportNotifications", "SoapNS");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Useremail", "SoapNS");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("Userpassword", "SoapNS");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("Daysback", "SoapNS");
        //SOAP Values
        String strPassword = "";
        if(strEnvironment.equals("PROD"))
        {
        	soapBodyElem1.addTextNode("darinadmin@mpspark.com");
        	strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, "darinadmin@mpspark.com", "admin");
        }
        else
        {
	        soapBodyElem1.addTextNode("darin@mpspark.com");
	        strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, "darin@mpspark.com", "admin");
        }
        soapBodyElem2.addTextNode(strPassword);
        soapBodyElem3.addTextNode("1");
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI+ "ExportNotifications");
        soapMessage.saveChanges();
        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    public String ReturnSOAPResponse(SOAPMessage soapResponse) throws Exception
    {
	    	StreamResult result = new StreamResult();
	    	StringWriter writer= new StringWriter();
	    	result.setWriter(writer);
	    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        transformer.transform(sourceContent, result);
        //System.out.println(writer.toString());
        return writer.toString();
    }

    public void ValidateVolationXMLData(Map<String, String> objDictionary, String strXMLResponse, String strSpotNumber) throws IOException, SOAPException
    {
    	String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		System.out.println(strXMLResponse);
		String strViolationId = objDictionary.get("strViolationId");
    	//ViolateDate
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date dateobj = new Date();
    	String strViolationDate = df.format(dateobj);
    	String strEnvironment = objDictionary.get("strEnvironment");
    	String strLicensePlateNumber = objDictionary.get("strLicensePlateNumber");
    	String strRegistrationType = "PAS";
    	String strViolationCharged = objDictionary.get("strViolationExternalReason");
    	String strTypeOfLaw = "VO";
    	String strLawSection = objDictionary.get("strStatuteCode");
    	//CourtDate
    	Date dateCourtDate = addDay(dateobj, 30);
    	String strCourtDate = df.format(dateCourtDate);
    	String strArrestingOfficerInitials = objDictionary.get("strUniqueId").substring(0, 1).toUpperCase()+"P";
    	String strBadgeNumber = "";
    	String strDeviceId = objDictionary.get("strDeviceId");
		String[] arrMeterSpots = strDeviceId.split("-");
		String strMeterNumber = "";
		if(strSpotNumber.equals("1")){strMeterNumber = arrMeterSpots[0];}
		else
		{
			if(arrMeterSpots.length < 2){strMeterNumber = strDeviceId;}else{strMeterNumber = arrMeterSpots[1];}
		}
		String strYear = objDictionary.get("strVehicleYear");
    	//if(strYear == null){strYear = "2017";}
    	if(strYear == null){strYear = "NA";}
    	String strMake = objDictionary.get("strVehicleMake");
    	String strVehColor = "";
    	String strRegExpires = objDictionary.get("strRegistrationExpMonth")+"/"+objDictionary.get("strRegistrationExpDay")+"/"+objDictionary.get("strRegistrationExpYear");
    	String strVIN = "1N4961EZXT2744492";
    	String strMuniCode = "";
    	String strOfficerNotes = objDictionary.get("strOfficerNotes");if(strOfficerNotes == null){strOfficerNotes = "";}
    	String strErrorMsg = "";

    	NodeList nodes = null;
    	try
    	{
    		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    		InputSource is = new InputSource();
    		is.setCharacterStream(new StringReader(strXMLResponse));
    		Document doc = db.parse(is);
    		nodes = doc.getElementsByTagName("Value");
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	//Cycle Through Violations Until Match is Found
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element element = (Element) nodes.item(i);
		    NodeList name = element.getElementsByTagName("TicketNumber");
		    Element line = (Element) name.item(0);
		    //Ticket Prefix
		    String strEnvViolationId = strViolationId;
		    //if(strEnvironment.equals("PROD")){strEnvViolationId = "MPS"+strViolationId;}
		    if(strEnvironment.equals("PROD")){strEnvViolationId = strViolationId;}
		    else if (strEnvironment.equals("SG"))
	    	{
	    		String strMunicipality = objDictionary.get("strMunicipality");
	    		if(strMunicipality.equals("Excelsior, MN")){strEnvViolationId = "CM"+strViolationId;}
	    	}
	    	else{strEnvViolationId = "E15"+strViolationId;}
		    if(getCharacterDataFromElement(line).contains(strEnvViolationId))
		    {
		    	NodeList PlateNumber = element.getElementsByTagName("PlateNumber");
	    		line = (Element) PlateNumber.item(0);
	    		if(getCharacterDataFromElement(line).equals(strLicensePlateNumber))
	    		{Reporter.log("The PlateNumber Node value was ("+strLicensePlateNumber+")");}
	    		else
	    		{
	    			UpdateErrorMessageWithPivotalData(objDictionary,"The PlateNumber Node value was not ("+strLicensePlateNumber+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);
	    		}
        		NodeList ViolationDate = element.getElementsByTagName("ViolationDate");
    		    line = (Element) ViolationDate.item(0);
    		    if(getCharacterDataFromElement(line).equals(strViolationDate))
		    	{Reporter.log("The ViolationDate Node value was ("+strViolationDate+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The ViolationDate Node value was not ("+strViolationDate+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}
        		NodeList RegistrationType = element.getElementsByTagName("RegistrationType");
    		    line = (Element) RegistrationType.item(0);
    		    if(getCharacterDataFromElement(line).equals(strRegistrationType))
		    	{Reporter.log("The RegistrationType Node value was ("+strRegistrationType+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The RegistrationType Node value was not ("+strRegistrationType+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}
        		NodeList ViolationCharged = element.getElementsByTagName("ViolationCharged");
    		    line = (Element) ViolationCharged.item(0);
    		    if(getCharacterDataFromElement(line).equals(strViolationCharged))
		    	{Reporter.log("The ViolationCharged Node value was ("+strViolationCharged+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The ViolationCharged Node value was not ("+strViolationCharged+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}
                NodeList TypeOfLaw = element.getElementsByTagName("TypeOfLaw");
    		    line = (Element) TypeOfLaw.item(0);
    		    if(getCharacterDataFromElement(line).equals(strTypeOfLaw))
		    	{Reporter.log("The TypeOfLaw Node value was ("+strTypeOfLaw+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The TypeOfLaw Node value was not ("+strTypeOfLaw+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}
    		    NodeList LawSection = element.getElementsByTagName("LawSection");
    		    line = (Element) LawSection.item(0);
    		    if(getCharacterDataFromElement(line).equals(strLawSection))
		    	{Reporter.log("The LawSection Node value was ("+strLawSection+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The LawSection Node value was not ("+strLawSection+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

//    		    NodeList CourtDate = element.getElementsByTagName("CourtDate");
//    		    line = (Element) CourtDate.item(0);
//    		    if(getCharacterDataFromElement(line).equals(strCourtDate))
//		    	{Reporter.log("The CourtDate Node value was ("+strCourtDate+")");}
//        		else
//        		{UpdateErrorMessageWithPivotalData(objDictionary,"The CourtDate Node value was not ("+strCourtDate+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList ArrestingOfficerInitials = element.getElementsByTagName("ArrestingOfficerInitials");
    		    line = (Element) ArrestingOfficerInitials.item(0);
    		    if(getCharacterDataFromElement(line).equals(strArrestingOfficerInitials))
		    	{Reporter.log("The ArrestingOfficerInitials Node value was ("+strArrestingOfficerInitials+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The ArrestingOfficerInitials Node value was not ("+strArrestingOfficerInitials+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList BadgeNumber = element.getElementsByTagName("BadgeNumber");
    		    line = (Element) BadgeNumber.item(0);
    		    if(getCharacterDataFromElement(line).equals(strBadgeNumber))
		    	{Reporter.log("The BadgeNumber Node value was ("+strBadgeNumber+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The BadgeNumber Node value was not ("+strBadgeNumber+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList MeterNumber = element.getElementsByTagName("MeterNumber");
    		    line = (Element) MeterNumber.item(0);
    		    if(getCharacterDataFromElement(line).equals(strMeterNumber))
		    	{Reporter.log("The MeterNumber Node value was ("+strMeterNumber+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The MeterNumber Node value was not ("+strMeterNumber+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList Year = element.getElementsByTagName("Year");
    		    line = (Element) Year.item(0);
    		    if(getCharacterDataFromElement(line).equals(strYear))
		    	{Reporter.log("The Year Node value was ("+strYear+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The Year Node value was not ("+strYear+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList Make = element.getElementsByTagName("Make");
    		    line = (Element) Make.item(0);
    		    if(getCharacterDataFromElement(line).equals(strMake))
		    	{Reporter.log("The Make Node value was ("+strMake+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The Make Node value was not ("+strMake+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList VehColor = element.getElementsByTagName("VehColor");
    		    line = (Element) VehColor.item(0);
    		    if(getCharacterDataFromElement(line).equals(strVehColor))
		    	{Reporter.log("The VehColor Node value was ("+strVehColor+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The VehColor Node value was not ("+strVehColor+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList RegExpires = element.getElementsByTagName("RegExpires");
    		    line = (Element) RegExpires.item(0);
    		    if(getCharacterDataFromElement(line).equals(strRegExpires))
		    	{Reporter.log("The ;RegExpires Node value was ("+strRegExpires+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The RegExpires Node value was not ("+strRegExpires+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}
    		    
//    		    NodeList VIN = element.getElementsByTagName("VIN");
//    		    line = (Element) VIN.item(0);
//    		    if(getCharacterDataFromElement(line).equals(strVIN))
//		    	{Reporter.log("The VIN Node value was ("+strVIN+")");}
//        		else
//        		{UpdateErrorMessageWithPivotalData(objDictionary,"The VIN Node value was not ("+strVIN+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList MuniCode = element.getElementsByTagName("MuniCode");
    		    line = (Element) MuniCode.item(0);
    		    if(getCharacterDataFromElement(line).equals(strMuniCode))
		    	{Reporter.log("The MuniCode Node value was ("+strMuniCode+")");}
        		else
        		{UpdateErrorMessageWithPivotalData(objDictionary,"The MuniCode Node value was not ("+strMuniCode+") - Actual Value ("+getCharacterDataFromElement(line)+")-"+strMethodName);}

    		    NodeList OfficerNotesList = element.getElementsByTagName("OfficerNotes");
    		    if (OfficerNotesList.getLength() > 0) {
    		        line = (Element) OfficerNotesList.item(0);
    		        String actualNotes = getCharacterDataFromElement(line).trim();
    		        if (actualNotes.equals(strOfficerNotes)) 
    		        {Reporter.log("The OfficerNotes Node value was (" + strOfficerNotes + ")");}
    		        else 
    		        {UpdateErrorMessageWithPivotalData(objDictionary,"The OfficerNotes Node value was not (" + strOfficerNotes + ") - Actual Value (" + actualNotes + ")-" + strMethodName);}
    		    } 
    		    else 
    		    {UpdateErrorMessageWithPivotalData(objDictionary,"OfficerNotes node not found in XML");}
                return;
		    }
		}
		UpdateErrorMessageWithPivotalData(objDictionary,"The Violation ("+strViolationId+") didn't exist in the Violation Export XML)-"+strMethodName);
	}
    //ERROR HANDLING
    public void UpdateErrorMessageWithPivotalData(Map<String, String> objDictionary,String strErrorMsg)
	{
		String strAssociatedBug = objDictionary.get("strAssociatedBug");
		if(strAssociatedBug == null){strAssociatedBug = "";}
		String strPivotalId = "";
		Meter clsMeter = new Meter();
		switch (strErrorMsg)
	    {
	    	case "The VIN Node value was not (1N4961EZXT2744492) - Actual Value ()-ValidateVolationXMLData":
	    		strPivotalId = "SL-7856";Reporter.log(strErrorMsg);
				strErrorMsg = "VIN Number is not displayed in the Violation XML data.";
				break;
			case "The Year Node value was not (NA) - Actual Value (2025)-ValidateVolationXMLData":
			case "The Year Node value was not (NA) - Actual Value (2017)-ValidateVolationXMLData":
				strPivotalId = "184768975";Reporter.log(strErrorMsg);
				strErrorMsg = "When you leave the Car Year blank when populating a manual violation the ViolationXML shows the year as 2025 when is uses to display NA";
				break;
			case "The Year Node value was not (2010) - Actual Value (NA)-ValidateVolationXMLData":
				strPivotalId = "148185703";
				break;
		}
		if(!strPivotalId.equals(""))
		{
			if((strAssociatedBug.contains(strPivotalId) && !strAssociatedBug.equals("")) || strPivotalId.contains(strAssociatedBug) && !strAssociatedBug.equals(""))
			{Reporter.log("<a style='color:Gold' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");}
			else
			{Reporter.log("<a style='color:Red' href=\"https://www.pivotaltracker.com/n/projects/980790/stories/"+strPivotalId+"\" target=\"_blank\">"+strPivotalId+"-"+strErrorMsg+"</a>");}
			Assert.fail(strPivotalId+"-"+strErrorMsg);
		}
		else
  		{
  			Assert.fail(strErrorMsg);
  		}
	}
    public static String getCharacterDataFromElement(Element e)
    {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
          CharacterData cd = (CharacterData) child;
          return cd.getData();
        }
        return "";
     }
    public static Date addDay(Date date, int i)
	{
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }

}

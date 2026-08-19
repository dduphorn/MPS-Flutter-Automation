package AutomationCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.client.config.CookieSpecs;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.Iterator;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import java.util.Iterator;
//import java.util.ArrayList;



public class Pivotal
{
	public String GetPivotalToken() throws IOException
	{
		String username="darinduphorn";
	    String password="207414dd";
	    String url="https://www.pivotaltracker.com/services/v4/me";
	    String[] command = {"curl", "-s" ,"Accept:application/json", "--user", username+":"+password , url};
	    ProcessBuilder process = new ProcessBuilder(command);
        Process p;
        try
        {
            p = process.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            //System.out.print(result);
            //Parse XML
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    		InputSource is = new InputSource();
    		is.setCharacterStream(new StringReader(result));
    		Document doc = db.parse(is);
    		NodeList nodes = doc.getElementsByTagName("token");
    		for (int i = 0; i < nodes.getLength(); i++)
    		{
    			Element element = (Element) nodes.item(i);
    		    NodeList name = element.getElementsByTagName("guid");
    		    Element eline = (Element) name.item(0);
    		    System.out.println("Pivotal Token: " + getCharacterDataFromElement(eline));
    		    return getCharacterDataFromElement(eline).toString();
    		 }
        }
        catch (Exception e)
	    {
        	 System.out.print(e);
	    }
        return "";
	}
	public String GetPivotalProjectId() throws IOException
	{
		Pivotal clsPivotal = new Pivotal();
		String url="https://www.pivotaltracker.com/services/v5/projects";
	    String strToken = clsPivotal.GetPivotalToken();
	    //curl -H "X-TrackerToken: $TOKEN" -X GET http://www.pivotaltracker.com/services/v3/projects
	    String[] command = {"curl", "-H" , "X-TrackerToken:" +strToken, url};
	    ProcessBuilder process = new ProcessBuilder(command);
        Process p;
        try
        {
            p = process.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            result = result.replace("[", "").replaceAll("]", "");
        	//Parse JSON
        	JSONParser jsonParser = new JSONParser();
    		JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
    		System.out.println("Project Id: "+jsonObject.get("id"));
    		return jsonObject.get("id").toString();
        }
        catch (Exception e)
	    {
        	//java.lang.ClassCastException: org.json.simple.JSONArray cannot be cast to org.json.simple.JSONObject
        	 System.out.print(e);
	    }
        return "";
	}
	public String GetPivotalStatus(String strPivotalNumber) throws IOException
	{
		String strProjectId = GetPivotalProjectId();
		Pivotal clsPivotal = new Pivotal();
		//https://www.pivotaltracker.com/n/projects/2315564/stories/184057945
		String url="https://www.pivotaltracker.com/services/v5/projects/"+strProjectId+"/stories/"+strPivotalNumber;
	    String strToken = clsPivotal.GetPivotalToken();
	    //curl -H "X-TrackerToken: $TOKEN" -X GET http://www.pivotaltracker.com/services/v3/projects
	    String[] command = {"curl", "-H" , "X-TrackerToken:" +strToken, url};
	    ProcessBuilder process = new ProcessBuilder(command);
        Process p;
        try
        {
            p = process.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
           //Parse JSON
        	JSONParser jsonParser = new JSONParser();
    		JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
    		JSONArray lang= (JSONArray) jsonObject.get("labels");
    		for(int i=0; i<lang.size(); i++){System.out.println("The " + i + " element of the array: "+lang.get(i));}
    		Iterator<?> i = lang.iterator();
    		// Take Each Value from the json Array Separtely
    		while (i.hasNext())
    		{
    			JSONObject innerObj = (JSONObject) i.next();
    			System.out.println(innerObj.get("name").toString());
    			if(innerObj.get("name").toString().contains("sev"))
    			{
    				System.out.println(innerObj.get("name").toString());
    				return innerObj.get("name").toString();
    			}
    		}
        }
        catch (Exception e)
	    {
        	System.out.print(e);
	    }
        return "";
	}
	public static String getCharacterDataFromElement(Element e)
    {
		Node child = e.getFirstChild();
        if (child instanceof CharacterData)
        {
          CharacterData cd = (CharacterData) child;
          return cd.getData();
        }
        return "";
     }
}

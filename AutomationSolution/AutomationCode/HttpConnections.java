package AutomationCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.Reporter;
import com.google.common.base.Stopwatch;



public class HttpConnections
{
	public String StoreJsonUserId(Map<String, String> objDictionary, String strUserName, String strPassword)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Function Variables
		String strUrl = "";
  		String strJsonUserId = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		switch (strEnvironment)
  		{
  			case "QA":
  				//strUrl= "http://quality.sentry-link.com/api/v1/tokens.json";
  				strUrl="https://mps.quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "PROD":
  				strUrl="https://sentrylink.mpspark.com//api/v1/tokens.json";
  				break;
  			default:
  		}
  		try
  		{
  			URL object = new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestMethod("POST");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<>();
			ObjCredentials.put("email",strUserName);
			ObjCredentials.put("password", strPassword);
			Map<String, Object> ObjParent =  new HashMap<>();
			ObjParent.put("user",ObjCredentials);
			String jsonText = JSONValue.toJSONString(ObjParent);
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(jsonText);
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();
			int HttpResult = ObjConnection.getResponseCode();
			//System.out.println(ObjConnection.getResponseMessage());
			if (HttpResult == HttpURLConnection.HTTP_OK)
			{
			    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
			    String line = null;
			    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
			    br.close();
			    String strResponse = sb.toString();
			    strJsonUserId = strResponse.substring(strResponse.indexOf("user\":{\"id\":")+12, strResponse.indexOf("email")-2);
			}
			else
			{
				//This Value is Set to UnDelete the User
				strJsonUserId = "CheckIfUserIsDeleted";
			}
		}
		catch (Exception e)
		{
			String strErrorMsg =e+"-"+strMethodName;
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		}
		if(strUserName.contains("admin"))
		{objDictionary.remove("strJsonAdminId");objDictionary.put("strJsonAdminId",strJsonUserId);}
		else
		{objDictionary.remove("strJsonUserId");objDictionary.put("strJsonUserId",strJsonUserId);}
		Reporter.log("The value (" + strJsonUserId + ") was stored as variable name (strJsonUserId)"+"");
		return strJsonUserId;
	}

	public String StoreJsonUserToken(Map<String, String> objDictionary, String strUserName, String strPassword)
	{	
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Function Variables
		String strUrl = "";
  		String strJsonUserToken = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "PROD":
  				strUrl="https://sentrylink.mpspark.com/api/v1/tokens.json";
  				break;
  			default:
  		}
  		try
  		{
	  		URL object=new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestMethod("POST");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<String, String>();
			ObjCredentials.put("email",strUserName.toLowerCase());
			ObjCredentials.put("password", strPassword);
			Map<String, Object> ObjParent =  new HashMap<String, Object>();
			ObjParent.put("user",ObjCredentials);
			String jsonText = JSONValue.toJSONString(ObjParent);  
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(jsonText);
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();  
			int HttpResult = ObjConnection.getResponseCode(); 
			//System.out.println(ObjConnection.getResponseMessage());
			if (HttpResult == HttpURLConnection.HTTP_OK) 
			{
			    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
			    String line = null;  
			    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
			    br.close();
			    //System.out.println("" + sb.toString());  
			    String strResponse = sb.toString();
			    strJsonUserToken = strResponse.substring(strResponse.indexOf("token")+8, strResponse.indexOf("sentrylink_version")-3);
			}
			objDictionary.remove("strJsonUserToken");objDictionary.put("strJsonUserToken",strJsonUserToken);
			Reporter.log("The value (" + strJsonUserToken + ") was stored as variable name (strJsonToken)"+"");
  		}
  		catch (Exception e)
  		{
  			String strErrorMsg =e+"-"+strMethodName;
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
  		}
		return strJsonUserToken;
	}

	//**************************************************************************************************************
	//Deposits
	//****************************Parked**********************************************************************************
	//BrainTree
	public String GET_GetPaymentGatewayClientToken(Map<String, String> objDictionary, String strUserName, String strPassword)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Function Variables
		String strUrl = "";
  		String strClientToken = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strSubdomain = GET_MunicipalitySubdomain(objDictionary, strUserName, strPassword);
        String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="http://"+strSubdomain+"/api/v1/payment_gateway/client_token.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strSubdomain+".staging.sentry-link.com/api/v1/payment_gateway/client_token.json";
  				break;
  			case "PROD":
  				strUrl="https://"+strSubdomain+"/api/v1/payment_gateway/client_token.json";
  				break;
  			default:
  		}
  		//Create Get Request
		HttpGet httpGet = new HttpGet(strUrl);
		httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
		httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
		httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	    		BufferedReader in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		strClientToken = jsonObject.get("client_token").toString();
	    	}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethodName,"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,  strMethodName+"-"+e.toString(),"Local");}
	    return strClientToken;
	}
	public void JsonDepositFundsBrainTree(Map<String, String> objDictionary, String strRole)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		String strPaymentGatewayClientToken = GET_GetPaymentGatewayClientToken(objDictionary, strUserName, strPassword);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		switch (strEnvironment)
  		{
  			case "QA1":strUrl="http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/deposits.json";break;
  			case "SG1":	strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/payment_gateway/checkout.json";break;
  			case "PROD":clsCommonWeb.ReimburseParker(objDictionary);return;
  			default:
  				clsCommonWeb.ReimburseParker(objDictionary);
  				return;
  		}
  		//Create POST Request
		HttpPost httpPost = new HttpPost(strUrl);
		httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
		httpPost.addHeader(new BasicHeader("X-User-Token",strJsonToken));
		httpPost.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    String strBody = "{\"payment_method_nonce\":\""+strPaymentGatewayClientToken+"\",\"amount\":"+1000+"}";
//		String strBody = "{\"payment_method_nonce\":\""+strPaymentGatewayClientToken+"\",\"amount\":\"500\"}";
	    StringEntity entity = new StringEntity("{\"payment_method_nonce\":\""+strPaymentGatewayClientToken+"\",\"amount\":"+1000+"}", "UTF-8");
	    httpPost.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPost.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPost);
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	    		BufferedReader in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    	}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethodName,"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,  strMethodName+"-"+e.toString(),"Local");}
	}
	//SentryLink
	public void POST_JsonDepositFunds(Map<String, String> objDictionary, String strRole)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	    CommonWeb clsCommonWeb = new CommonWeb();
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//JsonToken
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		//Function Variables
		String strUrl = "";
		//DateFormat dateFormat = new SimpleDateFormat("MM//dd//yyyy HH:mm:ss a");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		Date date = new Date();
		//Random Transaction Number
		Random rnd = new Random();
		int intTransactionNumber = 100000 + rnd.nextInt(900000);
		String strEnvironment = objDictionary.get("strEnvironment");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/deposits.json";
  				break;
  			case "SG":
  				strUrl="https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/deposits.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.sentry-link.com/api/v1/deposits.json";

  				//Add Funds Through SL.
  				//clsCommonWeb.ReimburseParker(objDictionary);return;
  		}
  		 //Create PUT Request
  		HttpPost httpPost = new HttpPost(strUrl);
  		httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
  		httpPost.addHeader(new BasicHeader("X-User-Token",strJsonToken));
  		httpPost.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
  		System.out.println(dateFormat.format(date));
	    StringEntity entity = new StringEntity("{\"payment\":{\"payment_timestamp\":\""+dateFormat.format(date)+"\",\"transaction_number\":\""+Integer.toString(intTransactionNumber)+"\",\"amount\":\"2000\"}}", "UTF-8");
	    httpPost.setEntity(entity);
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPost.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpPost);
	        if(response1.toString().contains("200"))
	    	{
	    		BufferedReader in = null;
	            String data = null;
	    		response1.getStatusLine().getStatusCode();
        		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strNewAccountBalance = jsonObject.get("balance").toString();
	            double dblStringUserAccountBalance = Double.parseDouble(strNewAccountBalance) * .1;
	            objDictionary.remove("strCurrentParkingAccountBalance");
	            objDictionary.put("strCurrentParkingAccountBalance", Double.toString(dblStringUserAccountBalance));
	       		Reporter.log("The account balance for the parker ("+strUserName.toLowerCase()+") is ("+dblStringUserAccountBalance+")");
	    	}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethodName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	//This doesn't work for PROD
	    	String strErrorMsg = strMethodName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethodName,"Local");
	    }
	}
	public String StoreJsonAdminToken(Map<String, String> objDictionary)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethodName+"</font>");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strEnvironment = objDictionary.get("strEnvironment");
		//Function Variables
		String strUrl = "";
  		String strJsonAdminToken = "";
  		String strPassword  = "";
  		String strAdminUser = "";
  		if(strEnvironment.equals("PROD"))
  		{strAdminUser = "darinadmin@mpspark.com";}
  		else
  		{
  			strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		}
  		String strUserExists = clsCommonWeb.SENTRYLINK_CheckIfUserExists(objDictionary, "admin");
  		if(strUserExists.equals("False"))
  		{
  			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The User ("+strAdminUser+") Doesn't exists","Local");
  		}
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="http://quality.sentry-link.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strAdminUser.toLowerCase(), "Admin");
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strAdminUser.toLowerCase(), "Admin");
  				break;
  			case "PROD":
  				strUrl="https://mpstest.mpspark.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strAdminUser.toLowerCase(), "Admin");
  				break;
  		}
 		try
 		{
	  		URL object=new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestMethod("POST");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<>();
			ObjCredentials.put("email",strAdminUser);
			ObjCredentials.put("password", strPassword);
			Map<String, Object> ObjParent =  new HashMap<>();
			ObjParent.put("user",ObjCredentials);
			String jsonText = JSONValue.toJSONString(ObjParent);
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(jsonText);
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();
			int HttpResult = ObjConnection.getResponseCode();
			//System.out.println(ObjConnection.getResponseMessage());
			if (HttpResult == HttpURLConnection.HTTP_OK)
			{
			    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
			    String line = null;
			    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
			    br.close();
			    //System.out.println("" + sb.toString());
			    String strResponse = sb.toString();
			    strJsonAdminToken = strResponse.substring(strResponse.indexOf("token")+8, strResponse.indexOf("sentrylink_version")-3);
			    objDictionary.put("strJsonAdminToken", strJsonAdminToken);
			}
			else
			{
				Reporter.log("<font color='Orange'>401 error when getting Admin Toke</font>");
				clsCommonWeb.SENTRYLINK_CheckIfUserPasswordHasExpired(objDictionary,"admin");
				strJsonAdminToken =  StoreJsonAdminToken(objDictionary);
			}
		}
		catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethodName+" "+e,"Local");}
		Reporter.log("The value (" + strJsonAdminToken + ") was stored as variable name (strJsonToken)"+"");
		return strJsonAdminToken;
	}
	public void ReportJsonUserToken(Map<String, String> objDictionary, String strRole) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strEnvironment = objDictionary.get("strEnvironment");
		//Function Variables
		String strUrl = "";
  		String strJsonToken = "";
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//String strPassword = strUniqueId+strMunicipality.replace(", ", "")+strRole+"1";
		//Dictionary Variables
 		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="http://quality.sentry-link.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "Admin");
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				//strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "Admin");
  				break;
  		}
  		URL object=new URL(strUrl);
		HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
		ObjConnection.setDoOutput(true);
		ObjConnection.setDoInput(true);
		ObjConnection.setRequestProperty("Content-Type", "application/json");
		ObjConnection.setRequestProperty("Accept", "application/json");
		ObjConnection.setRequestMethod("POST");
		//Set JSON Object Values
		Map<String, String> ObjCredentials =  new HashMap<>();
		ObjCredentials.put("email",strUserName);
		ObjCredentials.put("password", strPassword);
		Map<String, Object> ObjParent =  new HashMap<>();
		ObjParent.put("user",ObjCredentials);
		String jsonText = JSONValue.toJSONString(ObjParent);
		//Creates the connection string
		OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
		wr.write(jsonText);
		wr.flush();
		//Execute the JSON POST request and displays the Response
		StringBuilder sb = new StringBuilder();
		int HttpResult = ObjConnection.getResponseCode();
		//System.out.println(ObjConnection.getResponseMessage());
		if (HttpResult == HttpURLConnection.HTTP_OK)
		{
		    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
		    String line = null;
		    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
		    br.close();
		    //System.out.println("" + sb.toString());
		    String strResponse = sb.toString();
		    strJsonToken = strResponse.substring(strResponse.indexOf("token")+8, strResponse.indexOf("sentrylink_version")-3);
		}
		else
		{
			String strErrorMsg = ObjConnection.getResponseMessage();
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		}
		Reporter.log("The token for ("+strUserName+") was ("+strJsonToken+")");
	}
	public void JsonStoreSentryVersion(Map<String, String> objDictionary) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMunicipality = objDictionary.get("strMunicipality");
		String strRole = "parker";
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strTestSuiteName = objDictionary.get("strTestSuiteName");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="http://quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "PROD":
  				strUrl="https://sentrylink.mpspark.com/api/v1/tokens.json";
  				break;
  			default:
  		}
  		URL object=new URL(strUrl);
		HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
		ObjConnection.setDoOutput(true);
		ObjConnection.setDoInput(true);
		ObjConnection.setRequestProperty("Content-Type", "application/json");
		ObjConnection.setRequestProperty("Accept", "application/json");
		ObjConnection.setRequestMethod("POST");
		//Set JSON Object Values
		Map<String, String> ObjCredentials =  new HashMap<>();
		ObjCredentials.put("email",strUserName);
		ObjCredentials.put("password", strPassword);
		Map<String, Object> ObjParent =  new HashMap<>();
		ObjParent.put("user",ObjCredentials);
		String jsonText = JSONValue.toJSONString(ObjParent);
		//Creates the connection string
		OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
		wr.write(jsonText);
		wr.flush();
		//Execute the JSON POST request and displays the Response
		StringBuilder sb = new StringBuilder();
		int HttpResult = ObjConnection.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK)
		{
		    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
		    String line = null;
		    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
		    br.close();
		    //System.out.println("" + sb.toString());
		    String strResponse = sb.toString();
		    String strVersion = "Version " + strResponse.substring(strResponse.indexOf("sentrylink_version")+21, strResponse.indexOf("municipalities")-3);
		    objDictionary.remove("strSentryLinkVersion");objDictionary.put("strSentryLinkVersion", strVersion);
		}
		strTestSuiteName = objDictionary.get("strTestSuiteName");
		System.out.println("MIH");
	}
	public String JsonCheckIfLicensePlatesExistsForUser(Map<String, String> objDictionary, String strLicensePlate, String strRole) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMunicipality = objDictionary.get("strMunicipality");
		//String strRole = "parker";
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="https://quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "PROD":
  				strUrl="https://sentrylink.mpspark.com/api/v1/tokens.json";
  				break;
  			default:
  		}
  		URL object=new URL(strUrl);
		HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
		ObjConnection.setDoOutput(true);
		ObjConnection.setDoInput(true);
		ObjConnection.setRequestProperty("Content-Type", "application/json");
		ObjConnection.setRequestProperty("Accept", "application/json");
		ObjConnection.setRequestMethod("POST");
		//Set JSON Object Values
		Map<String, String> ObjCredentials =  new HashMap<>();
		ObjCredentials.put("email",strUserName);
		ObjCredentials.put("password", strPassword);
		Map<String, Object> ObjParent =  new HashMap<>();
		ObjParent.put("user",ObjCredentials);
		String jsonText = JSONValue.toJSONString(ObjParent);
		//Creates the connection string
		OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
		wr.write(jsonText);
		wr.flush();
		//Execute the JSON POST request and displays the Response
		StringBuilder sb = new StringBuilder();
		int HttpResult = ObjConnection.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK)
		{
		    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
		    String line = null;
		    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
		    br.close();
		    //System.out.println("" + sb.toString());
		    String strResponse = sb.toString();
		    if(strResponse.contains(strLicensePlate))
		    {Reporter.log("The License Plate (" + strLicensePlate + ") already exist for User ("+strUserName+")");return "True";}
		    else
		    {Reporter.log("The License Plate (" + strLicensePlate + ") did not exist for User ("+strUserName+")");return "False";}
		}
		return "False";
	}
	public String CURL_ReturnCurrentParkingAccountBalance(Map<String, String> objDictionary, String strRole)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		CommonWeb clsCommonWeb = new CommonWeb();
 		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
  		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
  		String strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
  		if(!strJsonUserId.equals("CheckIfUserIsDeleted"))
  		{
	  		switch (strEnvironment)
	  		{
	  			case "QA":strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
	  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
	  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
	  		}
	  		String[] command = {
	  				"curl",
					"-s",
					"-X",
					"GET",
					"-H",
					"Accept:application/json",
					"-H",
					"X-User-Token:"+strJsonToken,
					"-H",
					"X-User-Email:"+strUserName.toLowerCase(),
					"-H",
					"Content-Type: application/json",
					strUrl
			};
			ProcessBuilder process = new ProcessBuilder(command);
			Process p;
			try
			{
				p = process.start();
				BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				line = reader.readLine();
				String strResponse = line.toString();
				if(!strResponse.contains("User specified was not authenticated user."))
				{
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
					String strUser = jsonObject.get("user").toString();
					JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUser);
					return jsonObject1.get("current_parking_account_balance").toString();
				}
			}
			catch (Exception e)
			{Reporter.log("<font color='red'>"+strMethodName+"-"+e.toString()+"</font>");Assert.fail(strMethodName+"-"+e.toString());}
  		}
		return "0";
	}
	public String JsonCheckIfUserExists(Map<String, String> objDictionary) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMunicipality = objDictionary.get("strMunicipality");
		String strRole = objDictionary.get("strRole");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl= "http://quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl= "https://staging.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "PROD":
  				strUrl = "https://mpstest.mpspark.com/api/v1/tokens.json";
  			default:
  		}
  		URL object=new URL(strUrl);
		HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
		ObjConnection.setDoOutput(true);
		ObjConnection.setDoInput(true);
		ObjConnection.setRequestProperty("Content-Type", "application/json");
		ObjConnection.setRequestProperty("Accept", "application/json");
		ObjConnection.setRequestMethod("POST");
		//Set JSON Object Values
		Map<String, String> ObjCredentials =  new HashMap<>();
		ObjCredentials.put("email",strUserName);
		ObjCredentials.put("password", strPassword);
		Map<String, Object> ObjParent =  new HashMap<>();
		ObjParent.put("user",ObjCredentials);
		String jsonText = JSONValue.toJSONString(ObjParent);
		//Creates the connection string
		OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
		wr.write(jsonText);
		wr.flush();
		//Execute the JSON POST request and displays the Response
		int HttpResult = ObjConnection.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK)
		{return "True";}
		else
		{return "False";}
	}
	public void JsonDeleteUser(Map<String, String> objDictionary, String strJsonUserId,  String strUserName) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://mps.quality.sentry-link.com/api/v1/users/"+strJsonUserId+"/quality_and_staging_user_removal";
  				break;
  			case "SG":
  				strUrl = "https://sentrylink.staging.sentry-link.com/api/v1/users/"+strJsonUserId+"/quality_and_staging_user_removal";
  				break;
  			default:
  		}
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    //Create Delete Request
	    HttpDelete httpDelete = new HttpDelete(strUrl);
	    //httpDelete.addHeader(new BasicHeader("Accept", "application/json"));
	    httpDelete.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpDelete.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpDelete.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	CloseableHttpResponse response1 = httpclient.execute(httpDelete);
	    	//System.out.println(response1);
	    	if(response1.toString().contains("200"))
			{
	    		response1.close();
	    		Reporter.log("The User ("+strUserName+") was destroyed"+"");
			}
	    	else if(response1.toString().contains("406"))
			{
	    		System.out.println("MIH");
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	//**************************************************************************************************************
	//TICKETING
	//**************************************************************************************************************
	public void CURL_SetNextOverdueToNow(Map<String, String> objDictionary, String strRole) {
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        String strMunicipality = objDictionary.get("strMunicipality");
        String strUniqueId = objDictionary.get("strUniqueId");
        // Prepare user details
        String strUserName = (strUniqueId + strMunicipality.replace(" ", "").replace(",", "") + strRole+"@gmail.com").toLowerCase();
        CommonWeb clsCommonWeb = new CommonWeb();
        String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
        String strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
        String strSLViolationId = objDictionary.get("strSLViolationId");
        String strUrl = "";
        // Construct URL based on environment
        switch (strEnvironment) {
            case "QA":
                strUrl = "http://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/violations/" + strSLViolationId + "/set_next_overdue_to_now";
                break;
            case "SG":
                strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/api/v1/violations/" + strSLViolationId + "/set_next_overdue_to_now";
                break;
            case "PROD":
                strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.com/api/v1/violations/" + strSLViolationId + "/set_next_overdue_to_now";
                break;
        }
        // Using HttpClient to perform the PUT request
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(strUrl);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("X-User-Token", strJsonToken);
            httpPut.setHeader("X-User-Email", strUserName.toLowerCase());
            httpPut.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                System.out.println("Response status: " + response.getStatusLine());
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity);
                    System.out.println("Response body: " + responseBody);
                }
                EntityUtils.consume(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Optional: You may want to add more sophisticated error handling here
        }
    }
//	public void CURL_SetNextOverdueToNow(Map<String, String> objDictionary, String strRole)
//	{
//	    String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//	    String strUrl = "";
//	    String strEnvironment = objDictionary.get("strEnvironment");
//	    String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
//
//	    String strMunicipality = objDictionary.get("strMunicipality");
//		String strUniqueId = objDictionary.get("strUniqueId");
//
//		CURL_ResetAPIToken(objDictionary, "api_ticket_service");
//
//		//ffAutomationMunicipalityapi_ticket_service@gmail.com
//
//		String strUserName = (strUniqueId+strMunicipality.replace(" ","").replace(",", "")+"api_ticket_service@gmail.com").toLowerCase();
//		CommonWeb clsCommonWeb = new CommonWeb();
//		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
//		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
//
//
//
////	    String strUserName = "mpssnoopybrown@gmail.com"; // Hardcoded based on curl example
////	    // Assuming tokens and password retrieval are handled elsewhere
////	    String strJsonToken = "DsfHhSmxcMUqzFxorJrC"; // Hardcoded based on curl example
//	    String strSLViolationId = objDictionary.get("strSLViolationId");
//	    switch (strEnvironment) {
//	        case "QA":
//	            strUrl = "http://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/violations/" + strSLViolationId + "/set_next_overdue_to_now";
//	            break;
//	        case "SG":
//	            strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/api/v1/violations/" + strSLViolationId + "/set_next_overdue_to_now";
//	            break;
//	        case "PROD":
//	            strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.com/api/v1/violations/" + strSLViolationId + "/set_next_overdue_to_now";
//	            break;
//	    }
//	    String[] command = {
//	        "curl",
//	        "-s",
//	        "-X",
//	        "PUT",
//	        "-H",
//	        "Accept: application/json",
//	        "-H",
//	        "X-User-Token:" + strJsonToken,
//	        "-H",
//	        "X-User-Email:" + strUserName.toLowerCase(),
//	        "-H",
//	        "Content-Type: application/json",
//	        strUrl
//	    };
//	    ProcessBuilder processBuilder = new ProcessBuilder(command);
//	    Process process;
//	    try {
//	        process = processBuilder.start();
//	        process.waitFor();  // Wait for the process to complete
//
//	        // Read the response if needed
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//	        String line;
//	        StringBuilder stringBuilder = new StringBuilder();
//	        while ((line = reader.readLine()) != null) {
//	            stringBuilder.append(line).append("\n");
//	        }
//	        String strResponse = stringBuilder.toString();
//	        System.out.println(strResponse);
//
//	        // Use strResponse as needed
//
//	    } catch (IOException | InterruptedException e) {
//	        // Handle exceptions
//	        Reporter.log("<font color='red'>" + strMethodName + "-" + e.toString() + "</font>");
//	        Assert.fail(strMethodName + "-" + e.toString());
//	    }
//	}
	public void CURL_ResetAPIToken(Map<String, String> objDictionary, String strRole) {
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");

        // Generate the username
        String strUserName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "") + strRole.replace(" ", "") + "@gmail.com";

        // Retrieve the password
        CommonWeb clsCommonWeb = new CommonWeb();
        String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);

        // Define the endpoint URL
        String url = "https://sentrylink.staging.sentry-link.com/api/v1/tokens.json";

        // Define JSON payload
        String jsonPayload = "{\"user\":{\"email\":\"" + strUserName.toLowerCase() + "\",\"password\":\"" + strPassword + "\"}}";

        // Use try-with-resources to ensure resources are closed properly
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity jsonEntity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(jsonEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Response status: " + response.getStatusLine());
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity);
                    System.out.println("Response body: " + responseBody);
                }
                EntityUtils.consume(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//**************************************************************************************************************
	//AUTOMATION REPORT
	//**************************************************************************************************************
	public String CheckJsonParkingSessionInformationIsBlank(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strRole = objDictionary.get("strRole");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strUrl = "";
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/parking_sessions.json";
  				break;
  			case "SG":
  				strUrl=  "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_sessions.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_sessions.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
			{
	    		response1.getStatusLine().getStatusCode();
	    		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            Reporter.log("The Parking Session Information was - "+data);
	            if(data.contains("{\"parking_sessions\":[]}"))
	            {
	            	return "True";
	            }
	            else
	            {
	            	return "False";
	            }
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethodName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "False";
	}
	public void ReportJsonParkingSessionInformation(Map<String, String> objDictionary) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strRole = objDictionary.get("strRole");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strUrl = "";
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/parking_sessions.json";
  				break;
  			case "SG":
  				strUrl=  "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_sessions.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_sessions.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
			{
	    		response1.getStatusLine().getStatusCode();
	    		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            if(data.contains("{\"parking_sessions\":[]}"))
	            {
	            	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "No Parking Session Information Existed-"+strMethodName,"Local");
	            }
	            else
	            {
		            //Parse out Parked At Time
		            String strPaymentTimeFull = data.substring(data.indexOf("payment_timestamp"), data.indexOf("transaction_number"));
		            String strPaymentTime = strPaymentTimeFull.substring(strPaymentTimeFull.indexOf("T")+1,strPaymentTimeFull.indexOf("Z"));
		            System.out.print(TimeZone.getDefault().inDaylightTime( new Date() ));
					if(TimeZone.getDefault().inDaylightTime( new Date()))
					{strPaymentTime = SubtractTimeToExistingTime(strPaymentTime, "300","HH:mm:ss");}
					else
					{strPaymentTime = SubtractTimeToExistingTime(strPaymentTime, "360","HH:mm:ss");}
					SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
				    SimpleDateFormat sdf2 = new SimpleDateFormat("h:mm a");
				    try
				    {
			    		Date date = sdf1.parse(strPaymentTime);
			    		System.out.println(sdf1.format(date));//ParkingTimeStamp - Add To Dictionary
			    		objDictionary.remove("strJsonParkedAtTimeStamp");objDictionary.put("strJsonParkedAtTimeStamp", sdf1.format(date));
			    		System.out.println(sdf2.format(date));
			    		strPaymentTime = sdf2.format(date);
				    }catch (Exception e) {System.out.println("MIH");}
				    Reporter.log("strPaymentTime: "+strPaymentTime);
				    Reporter.log("***************************************PARKING SESSION JSON*************************************************************************************************************************");
		            Reporter.log(data);
		            Reporter.log("************************************************************************************************************************************************************************************");
	            }
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethodName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    }
	}
	public String HTTPCONNECTION_GetMobileSubscriptionPreference(Map<String, String> objDictionary,String strNotifications, String strNotificationPreference)
	{
		//strNotificatoinPreference (app, text, email)
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strRole = objDictionary.get("strRole");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		String strUrl = "";
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/mobile/message_subscription_preferences.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/mobile/message_subscription_preferences.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/mobile/message_subscription_preferences.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));

	    //Notification
	    switch(strNotifications)
	    {
	    		case "Session Notifications":
	    			strNotifications = "notification";
	    			break;
	    		case "Warnings":
	    			strNotifications = "warning";
	    			break;
	    		case "Information":
	    			strNotifications = "info";
	    			break;
	    }

	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
		    		response1.close();
		    		JSONParser jsonParser = new JSONParser();
		    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		    		String strNotificationValues = jsonObject.get(strNotifications).toString();
		    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strNotificationValues);
		    		objDictionary.put("strNotificationSetting",jsonObject1.get(strNotificationPreference).toString());
		    		return jsonObject1.get(strNotificationPreference).toString();
			}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}
	public String HTTPCONNECTION_ReturnRandomUserRegistrationType(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strRole = objDictionary.get("strRole");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strUrl = "";
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/predefined_data.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/predefined_data.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/predefined_data.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray lsRegistrationType= (JSONArray) jsonObject.get("vehicle_registration_types");
	    		//Get Random
	    		Random rnd = new Random();
	    		int intNumber = 0 + rnd.nextInt(lsRegistrationType.size());
	    		System.out.println(lsRegistrationType.get(intNumber));
	    		JSONObject innerObj = (JSONObject) lsRegistrationType.get(intNumber);
	    		return innerObj.get("name").toString();
	    	}
	        else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");}
	    return "";
	}
	public String HTTPCONNECTION_ReturnRandomVehicleBodyType(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUrl = "";
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/ticket_services/vehicle_body_types";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/ticket_services/vehicle_body_types";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/ticket_services/vehicle_body_types";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray lsTicketServices= (JSONArray) jsonObject.get("ticket_services");
	    		for(int i=0; i<lsTicketServices.size();)
	    		{
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(lsTicketServices.get(i).toString());
	    			JSONArray lsVehicleBodyTypes= (JSONArray) jsonObject2.get("vehicle_body_types");
	    			Random rnd = new Random();
		    		int intNumber = 0 + rnd.nextInt(lsVehicleBodyTypes.size());
		    		JSONObject innerObj = (JSONObject) lsVehicleBodyTypes.get(intNumber);
		    		return innerObj.get("description").toString();
	    		}
	    	}
	    	else
	    	{
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");
	    	}
	    }
	    catch (Exception e)
	    {
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");
	    }
	    clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "No Vehicle Body Type Existed For This Municipality","Local");
	    return "";
	}
	public String HTTPCONNECTION_ReturnRandomVehicleMake(Map<String, String> objDictionary, String str)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		String strJsonToken = StoreJsonAdminToken(objDictionary);
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/ticket_services/vehicle_make_type";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/ticket_services/vehicle_make";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/ticket_services/vehicle_make_type";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", "darin@mpspark.com"));
	    //Send Request
	    try
	    {
	    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
		    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
		    	if(response1.toString().contains("200"))
			{
		    		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
		    		response1.close();
		    		JSONParser jsonParser = new JSONParser();
		    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		    		JSONArray lsTicketServices= (JSONArray) jsonObject.get("ticket_services");
		    		for(int i=0; i<lsTicketServices.size();)
		    		{
		    			JSONParser jsonParser2 = new JSONParser();
		    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(lsTicketServices.get(i).toString());
		    			JSONArray lsVehicleBodyTypes= (JSONArray) jsonObject2.get("vehicle_body_types");
		    			Random rnd = new Random();
			    		int intNumber = 0 + rnd.nextInt(lsVehicleBodyTypes.size());
			    		JSONObject innerObj = (JSONObject) lsVehicleBodyTypes.get(intNumber);
			    		return innerObj.get("description").toString();
		    		}
		    	}
		    	else
		    	{
		    		String strErrorMsg = response1.toString();
		    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
		    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		    	}
	    }
	    catch (Exception e)
	    {
	    		String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}


	//**************************************************************************************************************
	//Delete PERMIT or Delete Reservation
	//**************************************************************************************************************
	public void JsonDeleteAllActivePermitsForUser(Map<String, String> objDictionary,String strPermitId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		//String strJsonToken = objDictionary.get("strJsonUserToken");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strRole = "parker";
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//JsonToken
		HttpConnections clsHttpConnections = new HttpConnections();
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_permits/active_for_user.json";
  				break;
  			default:
  		}
  		 //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    String strArrErolledLicensePlates = "";
		try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	BufferedReader in = null;
	        String data = null;
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray violationreasons = (JSONArray) jsonObject.get("parking_permits");
	    		for(int i=0; i < violationreasons.size(); i++)
	    		{
	    			Iterator<?> z = violationreasons.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println(innerMeter.get("id").toString());
    					//Delete Permit
    					JsonMeteDeletePermit(objDictionary,innerMeter.get("id").toString());
    	    		}
	    		}
	    	}
		    else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
	    	response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}

	}
	public void JsonDeleteAllActivePermits(Map<String, String> objDictionary, String strPermitId) 
	{
        CommonWeb clsCommonWeb = new CommonWeb();
        String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
        String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipality = objDictionary.get("strMunicipality");
        String strUniqueId = objDictionary.get("strUniqueId");
        String strRole = "admin";
        HttpConnections clsHttpConnections = new HttpConnections();
        String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
        String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment) {
            case "QA":
            	//This needs to be updated
                strUrl = "https://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/parking_permits/active_for_user.json";
                break;
            case "SG":
                strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/permit_groups/225/parking_permits.json";
                break;
            case "PROD":
                strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.com/permit_groups/686/parking_permits.json";
                break;
            default:
                // Handle default case
                break;
        }
          
        // Create GET Request
        HttpGet httpGet = new HttpGet(strUrl);
        httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
        httpGet.addHeader(new BasicHeader("X-User-Token", strJsonAdminToken));
        httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser.toLowerCase()));

        try {
            RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
            RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
            httpGet.setConfig(localConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseData = EntityUtils.toString(response.getEntity());
                JSONParser jsonParser = new JSONParser();
                Object parsedObject = jsonParser.parse(responseData);
                
                if (parsedObject instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) parsedObject;
                    for (Object arrayElement : jsonArray) {
                        JSONObject jsonObject = (JSONObject) arrayElement;
                        // Extract "id" from the JSON object
	                    Object idObject = jsonObject.get("id");
	
	                    if (idObject instanceof Long) {
	                        Long permitIdLong = (Long) idObject;
	                        String permitId = permitIdLong.toString();
	                        // Delete Permit
	                        JsonMeteDeletePermit(objDictionary, permitId);
	                    } else if (idObject instanceof Integer) {
	                        Integer permitIdInt = (Integer) idObject;
	                        String permitId = permitIdInt.toString();
	                        // Delete Permit
	                        JsonMeteDeletePermit(objDictionary, permitId);
	                    } else {
	                        clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unexpected ID type: " + idObject.getClass().getName(), "Local");
	                    }
                    }
                
//                if (parsedObject instanceof JSONArray) {
//                    JSONArray jsonArray = (JSONArray) parsedObject;
//                    for (Object arrayElement : jsonArray) {
//                        JSONObject jsonObject = (JSONObject) arrayElement;
//                        JSONArray permitPlates = (JSONArray) jsonObject.get("permit_plates");
//                        if (permitPlates != null) {
//                            for (Object plateObj : permitPlates) {
//                                JSONObject permitPlate = (JSONObject) plateObj; // Cast each element to JSONObject
//                                String permitPlateId = permitPlate.get("parking_permit_id").toString();
//
//                                // Delete Permit
//                                JsonMeteDeletePermit(objDictionary, permitPlateId);
//                            }
//                        }
//                    }
                } else {
                    // Handle case where parsedObject is not a JSONArray
                    clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unexpected JSON format: Not a JSONArray", "Local");
                }
            } else {
                clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, response.toString() + ":" + strMethondName, "Local");
            }
            response.close();
        } catch (Exception e) {
            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, e.toString() + ":" + strMethondName, "Local");
        }
    }
	public void JsonMeteDeletePermit(Map<String, String> objDictionary,String strPermitId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		//String strJsonToken = objDictionary.get("strJsonUserToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonToken = StoreJsonAdminToken(objDictionary); 
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/parking_permits/"+strPermitId;
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/parking_permits/"+strPermitId;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_permits/"+strPermitId;  				
  				break;
  			default:
  		}
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    //Create Delete Request
	    HttpDelete httpDelete = new HttpDelete(strUrl);
	    httpDelete.addHeader(new BasicHeader("Accept", "application/json"));
	    httpDelete.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpDelete.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpDelete.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	CloseableHttpResponse response1 = httpclient.execute(httpDelete);
	    	if(response1.toString().contains("204")||response1.toString().contains("200"))
			{	
	    		response1.close();
	    		Reporter.log("The Parking Permint ("+strPermitId+") was Deleted");
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e) 
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	public void StoreActivePermitData(Map<String, String> objDictionary,String strPermitId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		//String strJsonToken = objDictionary.get("strJsonUserToken");
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strRole = "parker";
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//JsonToken
		HttpConnections clsHttpConnections = new HttpConnections();
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_permits/active_for_user.json";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_permits/active_for_user.json";
  				break;
  			default:
  		}
  		 //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    String strArrErolledLicensePlates = "";
		try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	BufferedReader in = null;
	        String data = null;
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray violationreasons = (JSONArray) jsonObject.get("parking_permits");
	    		for(int i=0; i < violationreasons.size(); i++)
	    		{
	    			Iterator<?> z = violationreasons.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					String strValidFrom = innerMeter.get("valid_from").toString();
    					String strValidTo = innerMeter.get("valid_to").toString();
    					SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    		            // Set the time zone to UTC (assuming the input date is in UTC)
    		            inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    		            // Parse the input date string
    		            Date dtValidFrom = inputDateFormat.parse(strValidFrom);
    		            Date dtValidTo = inputDateFormat.parse(strValidTo);
    		            SimpleDateFormat outputDateFormat = new SimpleDateFormat("hh:mm a");
    		            outputDateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
    		            // Format the date as a string with the new time
    		            objDictionary.put("strValidFromTime", outputDateFormat.format(dtValidFrom));
    		            objDictionary.put("strValidToTime", outputDateFormat.format(dtValidTo));
    		            return;
    	    		}
	    		}
	    	}
		    else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
	    	response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}
		objDictionary.put("strValidFromTime", "");
        objDictionary.put("strValidToTime", "");
	}
	public void StoreActivePermitDataForKioskPurchase(Map<String, String> objDictionary,String strPermitId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
        String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
        String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipality = objDictionary.get("strMunicipality");
        String strUniqueId = objDictionary.get("strUniqueId");
        String strRole = "admin";
        String strUserName = strUniqueId + strMunicipality.replace(" ","").replace(",", "") + strRole + "@gmail.com";
        HttpConnections clsHttpConnections = new HttpConnections();
        String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
        String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment) {
            case "QA":
            	//This needs to be updated
                strUrl = "https://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/parking_permits/active_for_user.json";
                break;
            case "SG":
                strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/permit_groups/225/parking_permits.json";
                break;
            case "PROD":
            	strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.com/permit_groups/686/parking_permits.json";
            	break;
            default:
                // Handle default case
                break;
        }

        // Create GET Request
        HttpGet httpGet = new HttpGet(strUrl);
        httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
        httpGet.addHeader(new BasicHeader("X-User-Token", strJsonAdminToken));
        httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser.toLowerCase()));
        try {
            RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
            RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
            httpGet.setConfig(localConfig);

            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseData = EntityUtils.toString(response.getEntity());
                JSONParser jsonParser = new JSONParser();
                Object parsedObject = jsonParser.parse(responseData);

                if (parsedObject instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) parsedObject;
                    for (Object arrayElement : jsonArray) {
                        JSONObject jsonObject = (JSONObject) arrayElement;
                        String strValidFrom = (String) jsonObject.get("valid_from");
                        String strValidTo = (String) jsonObject.get("valid_to");

                        System.out.println("Valid From: " + strValidFrom);
                        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    		            // Set the time zone to UTC (assuming the input date is in UTC)
    		            inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    		            // Parse the input date string
    		            Date dtValidFrom = inputDateFormat.parse(strValidFrom);
    		            Date dtValidTo = inputDateFormat.parse(strValidTo);
    		            SimpleDateFormat outputDateFormat = new SimpleDateFormat("hh:mm a");
    		            outputDateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
    		            // Format the date as a string with the new time
    		            objDictionary.put("strValidFromTime", outputDateFormat.format(dtValidFrom));
    		            objDictionary.put("strValidToTime", outputDateFormat.format(dtValidTo));
    		            return;
                    }
                } else {
                    // Handle case where parsedObject is not a JSONArray
                    clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, "Unexpected JSON format: Not a JSONArray", "Local");
                }
            } else {
                clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, response.toString() + ":" + strMethondName, "Local");
            }
            response.close();
        } catch (Exception e) {
            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, e.toString() + ":" + strMethondName, "Local");
        }
	}

	//***************
	//Alert
	//***************
	public void PUT_SubscriptionPreferences(Map<String, String> objDictionary, String strRole, String strNotificationType, Boolean bApp, Boolean bText, Boolean bEmail)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//JsonToken
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/mobile/message_subscription_preferences.json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/mobile/message_subscription_preferences.json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/mobile/message_subscription_preferences.json";break;
  			default:clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Environment ("+strEnvironment+") has not been added-"+strMethodName,"Local");
  		}
  		switch(strNotificationType)
	    {
	    		case "Session":strNotificationType = "notification";break;
	    		case "Warnings":strNotificationType = "warning";break;
	    		case "Information":strNotificationType = "info";break;
	    		default: clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Notification Type ("+strNotificationType+") has not been added-"+strMethodName,"Local");
	    }
  	    //Create Put Request
		HttpPut httpPut = new HttpPut(strUrl);
	    httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPut.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpPut.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    System.out.println("{\""+strNotificationType+"\":{\"app\":\""+bApp.toString()+"\",\"email\":\""+bEmail.toString()+"\",\"text\":\""+bText.toString()+"\"}}");
	    StringEntity entity = new StringEntity("{\""+strNotificationType+"\":{\"app\":\""+bApp.toString()+"\",\"email\":\""+bEmail.toString()+"\",\"text\":\""+bText.toString()+"\"}}", "UTF-8");
	    httpPut.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPut.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPut);
	    	if(response1.toString().contains("200"))
	    	{Reporter.log("Subscription Preferences for "+strNotificationType+" were set app:"+bApp.toString()+",email:"+bEmail.toString()+",text:"+bText.toString());}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethodName,"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,  strMethodName+"-"+e.toString(),"Local");}
	}

	public String GET_GetSubscriptionPreference(Map<String, String> objDictionary,String strNotificationType, String strNotificationPreference,String strRole)
	{
		//strNotificatoinPreference (app, text, email)
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strUrl = "";
		String strJsonToken = objDictionary.get("strJsonToken");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/mobile/message_subscription_preferences.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/mobile/message_subscription_preferences.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/mobile/message_subscription_preferences.json";
  				break;
  			default:
  				clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Environment ("+strEnvironment+") has not been added-"+strMethodName,"Local");
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    //Notification
	    switch(strNotificationType)
	    {
	    		case "Session":strNotificationType = "notification";break;
	    		case "Warnings":strNotificationType = "warning";break;
	    		case "Information":strNotificationType = "info";break;
	    		default: clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Notification Type ("+strNotificationType+") has not been added-"+strMethodName,"Local");
	    }
	    try
	    {
	    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
		    CloseableHttpResponse response1 = httpClient.execute(httpGet);
		    if(response1.toString().contains("200"))
			{
		    		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
		    		response1.close();
		    		JSONParser jsonParser = new JSONParser();
		    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		    		String strNotificationValues = jsonObject.get(strNotificationType).toString();
		    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strNotificationValues);
		    		return jsonObject1.get(strNotificationPreference).toString();
			}
	    }
	    catch (Exception e)
	    {
	    		String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "false";
	}

	//***************
	//Concierge
	//***************
	public void POST_ConciergeDenroll(Map<String, String> objDictionary, String strRole)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) {strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
  		}
	    //Create GET Request
		HttpPut httpPut = new HttpPut(strUrl);
	    httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPut.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpPut.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
  		System.out.println(sdf.format(new Date()));
  		StringEntity entity = new StringEntity("{\"user\":{\"concierge_de_enrolled_at\":\""+sdf.format(new Date())+"\",\"concierge_enrolled\":\"false\",\"concierge_enrolled_at\":\"\"}}", "UTF-8");
	    httpPut.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPut.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPut);
	    	if(response1.toString().contains("200"))
	    	{Reporter.log("The user ("+strUserName.toLowerCase()+") was de-enrolled from concierge program");}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethondName,"Local");
	    }
	}
	public void POST_ConciergeEnroll(Map<String, String> objDictionary, String strRole)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) {strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
  		}
	    //Create GET Request
		HttpPut httpPut = new HttpPut(strUrl);
	    httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPut.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpPut.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
  		System.out.println(sdf.format(new Date()));
  		StringEntity entity = new StringEntity("{\"user\":{\"concierge_enrolled_at\":\""+sdf.format(new Date())+"\",\"concierge_enrolled\":\"true\",\"concierge_de_enrolled_at\":\"\"}}", "UTF-8");
  		httpPut.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPut.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPut);
	    	if(response1.toString().contains("200"))
	    	{Reporter.log("The user ("+strUserName.toLowerCase()+") was Enrolled from concierge program");}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethondName,"Local");
	    }
	}
	public void POST_ConciergeUpdateStatus(Map<String, String> objDictionary,String strPlateNumber, String strRole, String strState, String strMacId,  String strSessionId, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Function Variables
		//Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		//JsonToken
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName, strPassword);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strTerritoryId = GET_TerritoryCode(objDictionary, strState);
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/concierge/parker_validating_ownership.json";
  				break;
  			case "SG":
  				strUrl="https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/concierge/parker_validating_ownership.json";
  				break;
  			default:
  		}
  		try
  		{
  			URL object=new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			//ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestProperty("X-User-Token",strJsonToken);//8YHyJLzPhPYrE_bGZskz
			ObjConnection.setRequestProperty("X-User-Email",strUserName.toLowerCase());//yyExcelsiorMNconcierge@gmail.com
			ObjConnection.setRequestMethod("POST");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<>();
			ObjCredentials.put("plate_number",strPlateNumber);//CA157YY
			ObjCredentials.put("territory", strTerritoryId);//104
			ObjCredentials.put("mac", strMacId.replace("\"", ""));//00:13:95:0F:AF:21
			ObjCredentials.put("session_id", strSessionId);//3a18fbea8d0911e8b6db0013950faf21
			ObjCredentials.put("spot", "SPOT_"+strSpotNumber);
			Map<String, String> ObjParent =  new HashMap<>();
			ObjParent.put("license_plate", ObjCredentials.toString());
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(ObjParent.toString());
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();
			int HttpResult = ObjConnection.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) //422
			{
				try
				{
					BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
				    String line = null;
				    while ((line = br.readLine()) != null)
				    {sb.append(line + "\n");}
				    br.close();
				    //System.out.println("" + sb.toString());
				    String strResponse = sb.toString();
				    System.out.println(strResponse);
				}
				catch (Exception e)
				{
					clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e+"-"+strMethodName,"Local");
				}
			}
			else
			{
				//Need to check if User Exists
				clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "Unable to Update Concierge Status-"+strMethodName,"Local");
			}
  		}
  		catch (Exception e)
	    {
	    	String strErrorMsg = strMethodName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	public String HTTPCONNECTIONS_GetConciergeStatus(Map<String, String> objDictionary,String strLicensePlate)
	{
		// Basic HTTP Authentication
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strRole = objDictionary.get("strRole");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUserName = "darin@mpspark.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/mobile/parking_sessions/legacy_meter_status.json?zoneid=VirtualAutomationMeterGroup";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/mobile/parking_sessions/legacy_meter_status.json?zoneid=No%20Lot";
  				break;
  			default:
  		}
  		try
  		{
  			URL url = new URL (strUrl);
  			String encoding = Base64.getEncoder().encodeToString((strUserName+":"+strPassword).getBytes("utf-8"));
  			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  			connection.setRequestMethod("GET");
  			connection.setDoOutput(true);
  			connection.setRequestProperty  ("Authorization", "Basic " + encoding);
  			InputStream content = connection.getInputStream();
  			BufferedReader in   = new BufferedReader (new InputStreamReader (content));
  			String line;
  			while ((line = in.readLine()) != null)
       	    {
  				System.out.println(line);
//   	    		if (line.contains(strDeviceId) && line.contains(strLicensePlate))
//   	    		{return "Occupied-Current Vehicle";}
//   	    		else if (line.contains(strDeviceId))
//   	    		{return "Occupied";}
       	    }
 		    in.close();
  		}
  		catch(Exception e)
  		{
  			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");
  		}
  		return "Empty";
	}
	//***************
	//Concierge
	//***************
	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}
	//***************
	//LicensePlate
	//***************
	public void CURL_DeleteLicensePlate(Map<String, String> objDictionary,String strRole,String strLicensePlateNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/license_plates.json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/license_plates/delete_by_plate.json";break;
  			//case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/license_plates.json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/license_plates.json";break;
  		}
		//JsonToken
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		//Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		//Get License Plate Territory
		String strTerritoryId = clsHttpConnections.GET_LicensePlatesTerritoryCode(objDictionary, strRole, strLicensePlateNumber);
		if(!strTerritoryId.equals(""))
		{
			String[] command = {
					"curl",
					"-s",
					"-X",
					"DELETE",
					"-H",
					"Accept:application/json",
					"-H",
					"X-User-Token:"+strJsonToken,
					"-H",
					"X-User-Email:"+strUserName.toLowerCase(),
					"-H",
					"Content-Type: application/json",
					"-d",
					"{\"plate_number\":\""+strLicensePlateNumber+"\",\"territory\":"+strTerritoryId+"}",
					strUrl
				};
			//Process Curl
			ProcessBuilder process = new ProcessBuilder(command);
			Process p;
			try
			{
				p = process.start();
				//System.out.println("Deleted License Plate: "+strLicensePlateNumber);
				//System.out.print("command output: " + output(p.getInputStream()));
			}
			catch (Exception e)
			{Reporter.log("<font color='red'>"+strMethodName+"-"+e.toString()+"</font>");Assert.fail(strMethodName+"-"+e.toString());}
		}
	}
	public void POST_LicensePlate(Map<String, String> objDictionary, String strRole, String strLicensePlate,String strState)
	{
		//this fails if the license plate exists for another user
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){
  			strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) {
  			strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/license_plates.json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/license_plates.json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/license_plates.json";break;
  		}
  	    //Create PUT Request
  		HttpPost httpPost = new HttpPost(strUrl);
  		httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
  		httpPost.addHeader(new BasicHeader("X-User-Token",strJsonToken));
  		httpPost.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    String strTerritoryId = GET_TerritoryCode(objDictionary, strState);
	    StringEntity entity = new StringEntity("{\"license_plate\":{\"plate_number\":\""+strLicensePlate+"\",\"territory\":\""+strTerritoryId+"\"}}", "UTF-8");
	    httpPost.setEntity(entity);
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPost.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpPost);
	    	if(response1.toString().contains("201"))
	    	{Reporter.log("The License Plate ("+strLicensePlate+") for state ("+strState+") was added for user ("+strUserName.toLowerCase()+")");}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethondName,"Local");
	    }
	}
	public void PUT_LicensePlate2(Map<String, String> objDictionary, String strRole, String strLicensePlateNumber,String strState)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Classes
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Function Variables
		//Dictionary Variables
		String strMunicipality = objDictionary.get("strMunicipality");
		String strUniqueId = objDictionary.get("strUniqueId");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		//JsonToken
		String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strTerritoryId = GET_TerritoryCode(objDictionary, strState);
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/license_plates.json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/license_plates.json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/license_plates.json";break;
  		}
  		try
  		{
	  		URL object=new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestProperty("X-User-Token",strJsonToken);
			ObjConnection.setRequestProperty("X-User-Email",strUserName.toLowerCase());
			ObjConnection.setRequestMethod("PUT");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<>();
			ObjCredentials.put("plate_number",strLicensePlateNumber);
			ObjCredentials.put("territory", strTerritoryId);
			Map<String, String> ObjParent =  new HashMap<>();
			ObjParent.put("license_plate", ObjCredentials.toString());
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(ObjCredentials.toString());
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();
			int HttpResult = ObjConnection.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK)
			{
				try
				{
					BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
				    String line = null;
				    while ((line = br.readLine()) != null)
				    {sb.append(line + "\n");}
				    br.close();
				    //System.out.println("" + sb.toString());
				    String strResponse = sb.toString();
				    System.out.println(strResponse);
				}
				catch (Exception e)
				{
					clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e+"-"+strMethodName,"Local");
				}
			}
			else
			{
				if(HttpResult == 400){Reporter.log("No license plates existed for user ("+strUserName.toLowerCase()+")"+strMethodName);}
				else
				{
					clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "Unable to Delete License Plate-"+strMethodName,"Local");
				}
			}
  		}
  		catch (Exception e)
	    {
	    	String strErrorMsg = strMethodName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	public void PUT_LicensePlateConciergeState(Map<String, String> objDictionary, String strRole, String strLicensePlate,String strState, String strConciergeState)
	{
		//DOESN"T WORK
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) {strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strTerritoryId = GET_TerritoryCode(objDictionary, strState);
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://mps.quality.sentry-link.com/api/v1/license_plates.json";break;
  			case "SG":strUrl = "https://mps.staging.sentry-link.com/api/v1/license_plates.json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/license_plates.json";break;
  		}
  	    //Create PUT Request
  		HttpPost httpPost = new HttpPost(strUrl);
  		httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
  		httpPost.addHeader(new BasicHeader("X-User-Token",strJsonToken));
  		httpPost.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    StringEntity entity = new StringEntity("{\"license_plate\":{\"plate_number\":\""+strLicensePlate+"\",\"territory\":\""+strTerritoryId+"\",\"concierge_state\":\""+strConciergeState+"\"}}", "UTF-8");
	    httpPost.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPost.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPost);
	    	if(response1.toString().contains("201"))
	    	{Reporter.log("The License Plate ("+strLicensePlate+") for Concierge Status was updated to ("+strConciergeState+") was added for user ("+strUserName.toLowerCase()+")");}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethondName,"Local");
	    }
	}
	public String GET_ReturnUserLicensePlates(Map<String, String> objDictionary, String strRole)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = ""; BufferedReader in = null; String data = null;
        String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null)
  		{
  			strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null)
  		{
  			strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
  		}
	    //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    String strArrErolledLicensePlates = "";
		try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("user").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		JSONArray lang= (JSONArray) jsonObject1.get("license_plates_registered");
	    		Iterator<?> i = lang.iterator();
	    		while (i.hasNext())
	    		{
	    			JSONObject innerObj = (JSONObject) i.next();
	    			strArrErolledLicensePlates = strArrErolledLicensePlates + "|" + innerObj.get("plate_number");
	    		}
	    		if(!strArrErolledLicensePlates.equals("")) {strArrErolledLicensePlates = strArrErolledLicensePlates.substring(1);}
			}
		    else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
	    	response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}
		return strArrErolledLicensePlates;
	}
	public String GET_LicensePlatesTerritoryCode(Map<String, String> objDictionary, String strRole, String strLicensePlate)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = ""; BufferedReader in = null; String data = null;
        String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");if(strJsonToken.equals("")) {strJsonToken = null;}
  		if(strJsonToken == null)
  		{
  			strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) 
  		{
  			strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";
  				break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
  		}
	    //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("user").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		JSONArray lang= (JSONArray) jsonObject1.get("license_plates_registered");
	    		Iterator<?> i = lang.iterator();
	    		while (i.hasNext())
	    		{
	    			JSONObject innerObj = (JSONObject) i.next();
	    			if(innerObj.get("plate_number").equals(strLicensePlate))
	    			{
	    				if(strEnvironment.equals("QA")||strEnvironment.equals("SG"))
	    				{
	    					System.out.println(innerObj.get("state_prov_id").toString());
	    					return innerObj.get("state_prov_id").toString();
//	    					System.out.println(innerObj.get("province_id").toString());
//	    					return innerObj.get("province_id").toString();
	    				}
	    				else
	    				{
	    					System.out.println(innerObj.get("state_prov_id").toString());
	    					return innerObj.get("state_prov_id").toString();
	    				}
	    			}
	    		}
	    	}
		    else
		    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
	    	response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}
		return "";
	}

	public void HTTPCONNECTIONS_DeleteAllUserLicensePlates(Map<String, String> objDictionary, String strLicensePlateNumber, String strState, String strRole)
	{
		HttpConnections clsHttpConnections = new HttpConnections();
		String strConciergeState = clsHttpConnections.API_ReturnConciergeState(objDictionary,strRole,strLicensePlateNumber);
		if(strConciergeState.equals("matching"))//failed
		{
			clsHttpConnections.PUT_LicensePlateConciergeState(objDictionary, strRole, strState, strLicensePlateNumber,"failed");
		}
		//Need To Deenrolled From Concierge to delete all license plates
		String strEnrolledState = clsHttpConnections.GET_ConciergeEnrolledState(objDictionary, strRole);
		if(strEnrolledState.equals("true"))
		{
			clsHttpConnections.POST_ConciergeDenroll(objDictionary, strRole);
		}
		String strArrErolledLicensePlates = clsHttpConnections.GET_ReturnUserLicensePlates(objDictionary, strRole);
		//Delete License Plates that are not current license plate. Eliminates Reseting All User License Plates.
		String[] arrLicensePlates = strArrErolledLicensePlates.split("\\|", -1);
		for (String strUserLicensePlate : arrLicensePlates)
		{
			clsHttpConnections.CURL_DeleteLicensePlate(objDictionary, strRole, strUserLicensePlate);
		}
	}
	public void HTTPCONNECTIONS_DeleteAllParkerLicensePlates(Map<String, String> objDictionary)
	{
		System.out.println("Delete All Parkers License Plates");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strRole = objDictionary.get("strRole");
		String strArrErolledLicensePlates = clsHttpConnections.GET_ReturnUserLicensePlates(objDictionary, strRole);
		//Delete License Plates that are not current license plate. Eliminates Reseting All User License Plates.
		String[] arrLicensePlates = strArrErolledLicensePlates.split("\\|", -1);
		//String strEnrolledState = clsHttpConnections.GET_ConciergeEnrolledState(objDictionary, strRole);
		for (String strUserLicensePlate : arrLicensePlates)
		{
			//String strConciergeState = clsHttpConnections.API_ReturnConciergeState(objDictionary,strRole,strUserLicensePlate);
			clsHttpConnections.CURL_DeleteLicensePlate(objDictionary, strRole, strUserLicensePlate);
		}
	}

	public String GET_GetConciergeAdminSetting(Map<String, String> objDictionary, String strConciergeAdminSetting)
	{	
		//Concierge Admin Options
		//strConciergeAdminSetting options 
		//min_amount_before_warning
		//min_amount_before_de_enrolling
		//no_parking_warning_minutes,minutes_advance_subsequent
		//subsequent_amount_purchased
		//initial_amount_purchased
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("<font color='orange'>     "+strMethondName+"</font>");
		CommonWeb clsCommonWeb = new CommonWeb();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipality = objDictionary.get("strMunicipality");
		//Function Variables
		String strUrl = "";
  		String strJsonToken = "";
  		String strPassword  = "";
  		//String strUserName = "darin@mpspark.com";
  		String strUserName = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		//Dictionary Variables
 		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl="https://quality.sentry-link.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "Admin");
  				break;
  			case "SG":
  				strUrl="https://staging.sentry-link.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "Admin");
  				break;
  			case "PROD":
  				strUrl="https://mpstest.mpspark.com/api/v1/tokens.json";
  				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "Admin");
  				break;
  		}
 		try
 		{
	  		URL object=new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestMethod("POST");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<String, String>();
			ObjCredentials.put("email",strUserName);
			ObjCredentials.put("password", strPassword);
			Map<String, Object> ObjParent =  new HashMap<String, Object>();
			ObjParent.put("user",ObjCredentials);
			String jsonText = JSONValue.toJSONString(ObjParent);  
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(jsonText);
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();  
			int HttpResult = ObjConnection.getResponseCode(); 
			//System.out.println(ObjConnection.getResponseMessage());
			if (HttpResult == HttpURLConnection.HTTP_OK) 
			{
			    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
			    String line = null;  
			    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
			    br.close();
			    //System.out.println("" + sb.toString());  
			    String strResponse = sb.toString();
			    JSONParser jsonParser = new JSONParser();
			    JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
	    		JSONArray municipalities = (JSONArray) jsonObject.get("municipalities");
	    		for(int i=0; i < municipalities.size(); i++)
	    		{
	    			Iterator<?> z = municipalities.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(innerMeter.get("name").toString().equals(strMunicipality))
    					{
    						jsonObject = (JSONObject) jsonParser.parse(innerMeter.get("consumer_concierge_settings").toString());
    						return jsonObject.get(strConciergeAdminSetting).toString();
    					}
    				}
    			}
			} 
			else 
			{
				String strErrorMsg = ObjConnection.getResponseMessage()+"-"+strMethondName+"-Hint Check If Account is Locked";
				Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
			} 
 		}
		catch (Exception e)
		{
			String strErrorMsg = e+"-"+strMethondName;
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		}
		Reporter.log("The value (" + strJsonToken + ") was stored as variable name (strJsonToken)"+"");
		return strJsonToken;
	}

	public String GET_TerritoryCode(Map<String, String> objDictionary,String strState)
	{
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		String strJsonToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		BufferedReader in = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/state_provinces.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/state_provinces.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/state_provinces.json";
  				break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));

	    CommonWeb clsCommonWeb = new CommonWeb();
	    String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();

	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray violationreasons = (JSONArray) jsonObject.get("state_provinces");
	    		for(int i=0; i < violationreasons.size(); i++)
	    		{
	    			Iterator<?> z = violationreasons.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(innerMeter.get("name").toString().equals(strState)){return innerMeter.get("id").toString();}
    	    		}
	    		}
			}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}
	public String API_ReturnConciergeState(Map<String, String> objDictionary, String strRole, String strLicensePlateNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = ""; BufferedReader in = null; String data = null;
        String strEnvironment = objDictionary.get("strEnvironment");
        String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com".toLowerCase();
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strJsonUserId = objDictionary.get("strJsonUserId");

//  		if(strJsonUserId == null)
//  		{
  			//STOP check the strJsonUserId
  			strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
//  		}
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        if(strMunicipalitySubdomain == null) {strMunicipalitySubdomain = clsHttpConnections.GET_MunicipalitySubdomain(objDictionary, strUserName, strPassword);}
 		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
  		}
	    //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
		try
	    {
			RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("user").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		JSONArray lang= (JSONArray) jsonObject1.get("license_plates_registered");
	    		Iterator<?> i = lang.iterator();
	    		while (i.hasNext())
	    		{
	    			JSONObject innerObj = (JSONObject) i.next();
	    			if(innerObj.get("plate_number").equals(strLicensePlateNumber))
	    			{
	    				String strConciergeState = innerObj.get("concierge_state").toString();
	    				response1.close();
	    				Reporter.log("Concierge State: "+strConciergeState);
	    				return strConciergeState;
	    			}
	    		}
			}
		    else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
		    response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}
		return "";
	}
	public String GET_ConciergeEnrolledState(Map<String, String> objDictionary, String strRole)  
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = ""; BufferedReader in = null; String data = null;
        String strEnvironment = objDictionary.get("strEnvironment");
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
  		String strJsonToken = "";
  		String strJsonUserId = "";
  		if(strEnvironment.equals("PROD"))
  		{
  			strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
  			strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
  		else
  		{
  			strJsonToken = objDictionary.get("strJsonUserToken");
  			if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  			strJsonUserId = objDictionary.get("strJsonUserId");
  			if(strJsonUserId == null) {strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);}
  		}
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/users/"+strJsonUserId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/users/"+strJsonUserId+".json";break;
  		}
	    //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
		try
	    {
			RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
	    	{	
	    		response1.getStatusLine().getStatusCode();
	    		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	    		StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("user").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		String strConciergeEnrolled = jsonObject1.get("concierge_enrolled").toString();
	    		Reporter.log("strConciergeEnrolled: "+strConciergeEnrolled);
	    		return strConciergeEnrolled;
			}
		    else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
		    response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}
		return "";
	}

	public String GET_MunicipalitySubdomain(Map<String, String> objDictionary, String strUserName, String strPassword)
	{	
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		switch (strEnvironment)
  		{
  			case "QA":strUrl="https://quality.sentry-link.com/api/v1/tokens.json";break;
  			case "SG":strUrl="https://staging.sentry-link.com/api/v1/tokens.json";break;
  			case "PROD":strUrl="https://mpstest.mpspark.com/api/v1/tokens.json";break;
  		}
  		try
  		{
	  		URL object=new URL(strUrl);
			HttpURLConnection ObjConnection = (HttpURLConnection) object.openConnection();
			ObjConnection.setDoOutput(true);
			ObjConnection.setDoInput(true);
			ObjConnection.setRequestProperty("Content-Type", "application/json");
			ObjConnection.setRequestProperty("Accept", "application/json");
			ObjConnection.setRequestMethod("POST");
			//Set JSON Object Values
			Map<String, String> ObjCredentials =  new HashMap<String, String>();
			ObjCredentials.put("email",strUserName.toLowerCase());
			ObjCredentials.put("password", strPassword);
			Map<String, Object> ObjParent =  new HashMap<String, Object>();
			ObjParent.put("user",ObjCredentials);
			String jsonText = JSONValue.toJSONString(ObjParent);  
			//Creates the connection string
			OutputStreamWriter wr = new OutputStreamWriter(ObjConnection.getOutputStream());
			wr.write(jsonText);
			wr.flush();
			//Execute the JSON POST request and displays the Response
			StringBuilder sb = new StringBuilder();  
			int HttpResult = ObjConnection.getResponseCode(); 
			//System.out.println(ObjConnection.getResponseMessage());
			if (HttpResult == HttpURLConnection.HTTP_OK) 
			{
 			    BufferedReader br = new BufferedReader(new InputStreamReader(ObjConnection.getInputStream(), "utf-8"));
			    String line = null;  
			    while ((line = br.readLine()) != null) {sb.append(line + "\n");}
			    br.close();
			    //System.out.println("" + sb.toString());  
			    String strResponse = sb.toString();
			    JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
	    		JSONArray municipalities = (JSONArray) jsonObject.get("municipalities");
	    		for(int i=0; i < municipalities.size(); i++)
	    		{
	    			Iterator<?> z = municipalities.iterator();
	    			while (z.hasNext()) 
	    			{
	    				JSONObject innerMeter = (JSONObject) z.next();
    					if(innerMeter.get("name").toString().equals(strMunicipality))
    					{
    						return innerMeter.get("subdomain").toString();
    					}
	    			}
	    		}
			} 
			else 
			{
				CommonWeb clsCommonWeb = new CommonWeb();
				clsCommonWeb.SENTRYLINK_CheckIfUserPasswordHasExpired(objDictionary, "parker");
				strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "parker");
				HttpConnections clsHttpConnections = new HttpConnections();
				String strMunicipalitySubdomain = clsHttpConnections.GET_MunicipalitySubdomain(objDictionary, strUserName.toLowerCase(), strPassword);
				return strMunicipalitySubdomain;
			} 
  		}
  		catch (Exception e)
  		{
  			objDictionary.put("strSubdomainError", e+"-"+strMethodName);
  		}
  		return "";
	}
	public String GET_NearbyMunicipalityVariables(Map<String, String> objDictionary,String strState)
	{
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		String strJsonToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		BufferedReader in = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/nearby_municipalities.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/nearby_municipalities.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/nearby_municipalities.json";
  				break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", "darin@mpspark.com"));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
		    if(response1.toString().contains("200"))
			{
		    	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray violationreasons = (JSONArray) jsonObject.get("state_provinces");
	    		for(int i=0; i < violationreasons.size(); i++)
	    		{
	    			Iterator<?> z = violationreasons.iterator();
    				while (z.hasNext())
    	    			{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(innerMeter.get("name").toString().equals(strState)){return innerMeter.get("id").toString();}
    	    			}
		    		}
			}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}

	//******************************************************************************************************************************************************
	//Mobile Payments
	//******************************************************************************************************************************************************
	public void JSON_MobilePayment(Map<String, String> objDictionary) throws Exception {
	    HttpConnections clsHttpConnections = new HttpConnections();
	    CommonWeb clsCommonWeb = new CommonWeb();
	    String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	    String strUrl = "";
	    String strRole = "parker";
	    String strEnvironment = objDictionary.get("strEnvironment");
	    String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
	    String strLicensePlateNumber = objDictionary.get("strLicensePlateNumber");
	    String strMeterIncrementTime = objDictionary.get("strMeterIncrementTime");
	    String strTerritoryId = clsHttpConnections.GET_TerritoryCode(objDictionary, "Minnesota");

	    // Get Meter Id
	    String strMeterId = "";
	    String strVirtualMeter = objDictionary.get("strVirtualMeter");
	    if (strVirtualMeter.equals("True")) {
	        strMeterId = clsHttpConnections.Json_VirtualMeterId(objDictionary);
	    } else {
	        strMeterId = clsHttpConnections.Json_MeterId(objDictionary, "Local");
	    }
	    String strMunicipality = objDictionary.get("strMunicipality");
	    String strUniqueId = objDictionary.get("strUniqueId");
	    String strUserName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "") + strRole + "@gmail.com";
	    String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
	    String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
	    switch (strEnvironment) {
	        case "SG":
	            strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/api/v1/purchase_parking.json";
	            break;
	        // Add other environments if needed
	    }
	    HttpPost httpPost = new HttpPost(strUrl);
	    httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPost.addHeader(new BasicHeader("X-User-Token", strJsonToken));
	    httpPost.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    // Set the timestamp for the payment
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String strPaymentTimeStamp = sdf.format(new Date());
	    // Formatting parking date (optional, depends on your usage)
	    String strParkedAtDate = clsCommonWeb.ConvertUTCtoCST_UTC(strPaymentTimeStamp, "yyyy-MM-dd'T'HH:mm:ss'Z'", "MM/dd/yyyy");
	    objDictionary.put("strParkedAtDate", strParkedAtDate);
	    // Get Rate Block Rate Set Id
	    String strDeviceId = objDictionary.get("strDeviceId");
	    String RatBlockRateSetId = clsHttpConnections.GetMeterRatBlockRateSetId(objDictionary, strDeviceId);
	    // Constructing the JSON body for the POST request
	    String jsonPayload = "{"
	        + "\"payment\": {"
	        + "\"device_id\": \"" + strMeterId + "\", "
	        + "\"spot_id\": \"SPOT_1\", "
	        + "\"plate_number\": \"" + strLicensePlateNumber + "\", "
	        + "\"state_prov_id\": \"" + strTerritoryId + "\", "
	        + "\"payment_timestamp\": \"" + strPaymentTimeStamp + "\", "
	        + "\"rate_block_id\": \"" + RatBlockRateSetId + "\", "
	        + "\"total_payment\": 25, "
	        + "\"normal_payment\": 25, "
	        + "\"no_fine_payment\": 0, "
	        + "\"handicap_payment\": false, "
	        + "\"no_fine_tax\": 0, "
			+ "\"fine_tax\": 0, "
			+ "\"fine_payment\": 0, "
	        + "\"payment_currency\": \"USD\", "
			+ "\"payment_number\": 1, "
			+ "\"parking_duration\": \"" + strMeterIncrementTime + "\", "
			+ "\"is_handicap_parking\": false "
			+ "}}";
	    StringEntity entity = new StringEntity(jsonPayload, "UTF-8");
	    httpPost.setEntity(entity);
	    try {
	        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPost.setConfig(localConfig);
	        // Send the request
	        CloseableHttpResponse response1 = httpClient.execute(httpPost);
	        int statusCode = response1.getStatusLine().getStatusCode();
	        if (statusCode == 200) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line;
	            while ((line = in.readLine()) != null) {
	                sb.append(line).append(System.getProperty("line.separator"));
	            }
	            in.close();
	            String responseData = sb.toString();
	            response1.close();
	            // Optional: Parse the response if needed
	        } else {
	            // Log the full response for further analysis
	            BufferedReader in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line;
	            while ((line = in.readLine()) != null) {
	                sb.append(line).append(System.getProperty("line.separator"));
	            }
	            in.close();
	            String responseData = sb.toString();
	            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, "Error: " + response1.getStatusLine().toString() + " Response: " + responseData, "Local");
	        }
	    } catch (Exception e) {
	        String errorMsg = strMethodName + "-" + e.toString();
	        clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, errorMsg, "Local");
	    }
	}
	//**********************
	//METER RATE BLOCK GROUP
	//**********************
	public String CheckIfRatBlockRateSetIsBlank(Map<String, String> objDictionary,String strDeviceId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");if(strJsonAdminToken == null) {strJsonAdminToken = "";}
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		if(strJsonAdminToken.equals(""))
		{
			strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter= "False";}
		String strRateBlockId = "";
		if(strVirtualMeter.equals("True"))
		{strRateBlockId = GetVirtualMeterRateBlockId(objDictionary);}
		else
		{strRateBlockId = GetMeterRateBlockId(objDictionary,strDeviceId);}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("rate_block_group").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		String strRateBlock = jsonObject1.get("rate_blocks").toString();
	    		if(strRateBlock.equals("[]"))
	    		{return "True";}
	    		else
	    		{return "False";}
			}
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,e+":"+strMethondName,"Local");}
	    return "";
	}
	public String CheckIfRateBlockExistsInSentryLink(Map<String, String> objDictionary, String strRateBlockGroup)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		CommonWeb clsCommonWeb = new CommonWeb();
		String strUserName = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "Parker");
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), "Parker");
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
		String strSubdomain = GET_MunicipalitySubdomain(objDictionary, strUserName, strPassword);
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/rate_block_groups.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strSubdomain+".staging.sentry-link.com/api/v1/rate_block_groups.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/rate_block_groups.json";
  				break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig); 
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200 OK"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray rateblockgroups = (JSONArray) jsonObject.get("rate_block_groups");
	    		if(rateblockgroups.size() != 0)
	    		{
	    			Iterator<?> z = rateblockgroups.iterator();
    				while (z.hasNext()) 
	    			{
    					JSONObject innerMeter = (JSONObject) z.next();
						if(innerMeter.get("name").toString().equals(strRateBlockGroup))
						{return "True";}
	    			}
	    		}
			}
	    }
	    catch (Exception e) 
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "False";
	}
	public String GET_RateBlockNameUsingRateBlockId(Map<String, String> objDictionary, String strDeviceId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken"); if (strJsonAdminToken == null) {strJsonAdminToken = "";}
  		if(strJsonAdminToken.equals("")){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter= "False";}
		String strRateBlockId = "";
		if(strVirtualMeter.equals("True")){strRateBlockId = GetVirtualMeterRateBlockId(objDictionary);}
		else{strRateBlockId = GetMeterRateBlockId(objDictionary,strDeviceId);}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("rate_block_group").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		return jsonObject1.get("name").toString();
			}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,e+":"+strMethondName,"Local");}
	    return "";
	}
	public String CheckIfMeterRateBlockIsNull(Map<String, String> objDictionary)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strDeviceId = objDictionary.get("strDeviceId");
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/meters.json";
  				break;
  		}
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("meter_groups");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("meters");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext())
	    				{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
	    					{
	    						if(innerMeter.get("rate_block_group_id") == null){return "True";}
	    						else{return "False";}
	    					}
	    				}
	    			}
	    		}
			}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethodName+": "+response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethodName+": "+e.toString(),"Local");}
	    return "";
	}
	public String GetMeterRateBlockId(Map<String, String> objDictionary, String strDeviceId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//String strDeviceId = objDictionary.get("strDeviceId");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/meters.json";
  				break;
  		}
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        System.out.println(response1.toString());
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("meter_groups");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("meters");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext())
	    	    		{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
		    	    		{
	    						if(innerMeter.get("rate_block_group_id") != null)
	    						{
	    							return innerMeter.get("rate_block_group_id").toString();
	    						}
		    	    		}
	    	    		}
		    		}
	    		}
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 401 Unauthorized"))
	    		{
	    			strErrorMsg = "Error: 401 Unauthorized-Hint Check if User ("+strAdminUser+") has expired-"+strMethondName;
	    		}
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = e.toString()+"-"+strMethondName;
	    	if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 401 Unauthorized"))
    		{
    			strErrorMsg = "Error: 401 Unauthorized-Hint Check if User ("+strAdminUser+") has expired-"+strMethondName;
    		}
    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    }
	    return "";
	}
	public String GetMeterRatBlockRateSetId(Map<String, String> objDictionary, String strDeviceId)
	{
	    CommonWeb clsCommonWeb = new CommonWeb();
	    HttpConnections clsHttpConnections = new HttpConnections();
	    String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
	    String strEnvironment = objDictionary.get("strEnvironment");
	    String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
	    String strUrl = "";
	    BufferedReader in = null;
	    String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
	    String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
	    String strVirtualMeter = objDictionary.get("strVirtualMeter");
	    if(strVirtualMeter == null){strVirtualMeter = "False";}
	    String strRateBlockId = "";

	    if(strVirtualMeter.equals("True")) {
	        strRateBlockId = GetVirtualMeterRateBlockId(objDictionary);
	    } else {
	        strRateBlockId = GetMeterRateBlockId(objDictionary, strDeviceId);
	    }

	    switch (strEnvironment) {
	        case "QA":
	            strUrl = "http://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/rate_block_groups/" + strRateBlockId + ".json";
	            break;
	        case "SG":
	            strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/api/v1/rate_block_groups/" + strRateBlockId + ".json";
	            break;
	        case "PROD":
	            strUrl = "https://mpstest.mpspark.com/api/v1/rate_block_groups/" + strRateBlockId + ".json";
	            break;
	    }

	    // Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token", strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));

	    try {
	        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);

	        if(response1.toString().contains("200")) {
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) != null) {
	                sb.append(l + nl);
	            }
	            in.close();
	            String data = sb.toString();
	            response1.close();

	            // Parse the response into a JSONObject
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	            JSONObject rateBlockGroup = (JSONObject) jsonObject.get("rate_block_group");

	            // Extract the rate_blocks JSON array
	            JSONArray rateBlocksArray = (JSONArray) rateBlockGroup.get("rate_blocks");

	            // Ensure there is at least one element in the rate_blocks array
	            if (rateBlocksArray != null && rateBlocksArray.size() > 0) {
	                // Use get() instead of getJSONObject()
	                JSONObject firstRateBlock = (JSONObject) rateBlocksArray.get(0); // Get the first rate block
	                String strRateBlockRateId = firstRateBlock.get("id").toString(); // Get the 'id' from the first block

	                return strRateBlockRateId; // Return the id
	            }
	        }
	    } catch (Exception e) {
	        clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, e + ":" + strMethondName, "Local");
	    }
	    return "";
	}



	public String GetVirtualMeterRateBlockId(Map<String, String> objDictionary)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		String strDeviceId = objDictionary.get("strDeviceId");//IVMeter1
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/virtual_meters.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/virtual_meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/virtual_meters.json";
  				break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray rateblockgroups = (JSONArray) jsonObject.get("virtual_meters");
	    		if(rateblockgroups.size() != 0)
	    		{
	    			Iterator<?> z = rateblockgroups.iterator();
    				while (z.hasNext())
    	    		{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println(innerMeter.get("friendly_name").toString());
    					if(innerMeter.get("friendly_name").toString().equals(strDeviceId))
    					{
    						if(innerMeter.get("rate_block_group_id") != null)
    						{
    							System.out.println(innerMeter.get("rate_block_group_id").toString());
    							return innerMeter.get("rate_block_group_id").toString();
    						}
    						else
    						{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Rate Block Group Id was null-"+strMethondName,"Local");}
    					}
    				}
	    		}
			}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,response1+":"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}
	public String CheckIfVirtualMeterRateBlockIsNull(Map<String, String> objDictionary)
	{
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		HttpConnections clsHttpConnections = new HttpConnections();
		String strJsonToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		String strDeviceId = objDictionary.get("strDeviceId");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/virtual_meters.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/virtual_meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/virtual_meters.json";
  				break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    CommonWeb clsCommonWeb = new CommonWeb();
	    String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray rateblockgroups = (JSONArray) jsonObject.get("virtual_meters");
	    		if(rateblockgroups.size() != 0)
	    		{
	    			Iterator<?> z = rateblockgroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println(innerMeter.get("friendly_name").toString());
    					if(innerMeter.get("friendly_name").toString().equals(strDeviceId))
    					{
    						System.out.println(innerMeter.get("rate_block_group_id"));
    						if(innerMeter.get("rate_block_group_id") == null)
    						{return "True";}
    						else
    						{return "False";}
    					}
    				}
	    		}
			}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}


	//**********************
	//KIOSK RATE BLOCK GROUP
	//**********************
	public String CheckIfKioskRatBlockRateSetIsBlank(Map<String, String> objDictionary,String strDeviceId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter= "False";}
		String strRateBlockId = "";
		strRateBlockId = GetKioskRateBlockId(objDictionary,strDeviceId);
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("rate_block_group").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		String strRateBlock = jsonObject1.get("rate_blocks").toString();
	    		if(strRateBlock.equals("[]"))
	    		{return "True";}
	    		else
	    		{return "False";}
			}
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,e+":"+strMethondName,"Local");}
	    return "";
	}
	public String GET_KioskRateBlockNameUsingRateBlockId(Map<String, String> objDictionary, String strDeviceId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strUrl = "";
		BufferedReader in = null;
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		//if(strJsonAdminToken == null){
  			strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
  		//}
		String strVirtualMeter = objDictionary.get("strVirtualMeter");if(strVirtualMeter == null){strVirtualMeter= "False";}
		String strRateBlockId = "";
		if(strVirtualMeter.equals("True")){strRateBlockId = GetVirtualMeterRateBlockId(objDictionary);}
		else{strRateBlockId = GetKioskRateBlockId(objDictionary,strDeviceId);}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "SG":strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  			case "PROD":strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/rate_block_groups/"+strRateBlockId+".json";break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("rate_block_group").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		return jsonObject1.get("name").toString();
			}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null,e+":"+strMethondName,"Local");}
	    return "";
	}
	public String GetKioskRateBlockId(Map<String, String> objDictionary, String strDeviceId)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//String strDeviceId = objDictionary.get("strDeviceId");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/kiosks.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/kiosks.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/kiosks.json";
  				break;
  		}
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        System.out.println(response1.toString());
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray open_parking_lots= (JSONArray) jsonObject.get("open_parking_lots");
	    		for(int i=0; i<open_parking_lots.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(open_parking_lots.get(i).toString());
	    			JSONArray kiosks= (JSONArray) jsonObject2.get("kiosks");
	    			for(int j=0; j<kiosks.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = kiosks.iterator();
	    				while (z.hasNext())
	    	    		{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					//System.out.println(innerMeter.get("friendly_name").toString());
	    					//System.out.println(strDeviceId);
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
		    	    		{
	    						if(innerMeter.get("rate_block_group_id") != null)
	    						{
	    							return innerMeter.get("rate_block_group_id").toString();
	    						}
		    	    		}
	    	    		}
		    		}
	    		}
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 401 Unauthorized"))
	    		{
	    			strErrorMsg = "Error: 401 Unauthorized-Hint Check if User ("+strAdminUser+") has expired-"+strMethondName;
	    		}
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = e.toString()+"-"+strMethondName;
	    	if(strErrorMsg.contains("HttpResponseProxy{HTTP/1.1 401 Unauthorized"))
    		{
    			strErrorMsg = "Error: 401 Unauthorized-Hint Check if User ("+strAdminUser+") has expired-"+strMethondName;
    		}
    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    }
	    return "";
	}
	public String CheckIfKioskRateBlockIsNull(Map<String, String> objDictionary)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strDeviceId = objDictionary.get("strDeviceId");
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/kiosks.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/kiosks.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/kiosks.json";
  				break;
  		}
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("open_parking_lots");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("kiosks");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext())
	    				{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
	    					{
	    						if(innerMeter.get("rate_block_group_id") == null){return "True";}
	    						else{return "False";}
	    					}
	    				}
	    			}
	    		}
			}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethodName+": "+response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethodName+": "+e.toString(),"Local");}
	    return "";
	}
	public String Json_KioskId(Map<String, String> objDictionary, String strHostType)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Get Device Id
		String strDeviceId = "";
		strDeviceId = objDictionary.get("strDeviceId");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		BufferedReader in = null;
        String data = null;
        String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
        //sECcbN5wq8XzbzisoxF4

		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		//sECcbN5wq8XzbzisoxF4
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/kiosks.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/kiosks.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/kiosks.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email",strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("open_parking_lots");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("kiosks");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext())
	    				{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					System.out.println(innerMeter.get("friendly_name").toString());
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
	    	    			{
	    						return innerMeter.get("id").toString();
	    	    			}
    	    			}
		    		}
	    		}
			}
	        else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");}
	    return "";
	}
	//***************
	//RATE SETS
	//***************
	public String CheckIfRateSetExistsInSentryLink(Map<String, String> objDictionary, String strRateSet)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		System.out.println("Check If Rate Set Exists In Sentry Link");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		String strRateType = "";
		if(strRateSet.contains("Reservation")){strRateType = objDictionary.get("strReservationRateType");if(strRateType == null) {strRateType = "Fixed";}}
		else{strRateType = objDictionary.get("strRateType");if(strRateType == null) {strRateType = "Fixed";}}
		String strUrl = "";
		BufferedReader in = null;
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/rate_sets.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/rate_sets.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/rate_sets.json";
  				break;
  		}
	    //Create Get Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            String data = sb.toString();
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray rateblockgroups = (JSONArray) jsonObject.get("rate_sets");
	    		if(rateblockgroups.size() != 0)
	    		{
	    			Iterator<?> z = rateblockgroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println(innerMeter.get("name").toString());
    					System.out.println(strRateSet+strRateType);
    					if(innerMeter.get("name").toString().equals(strRateSet+strRateType))
    					{return "True";}
    	    		}
	    		}
			}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");}
	    return "False";
	}

	//***************
	//Common
	//***************

	public String GET_ReturnUserPreference(Map<String, String> objDictionary, String strRole, String StrUserPreferences)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = ""; BufferedReader in = null; String data = null;
        String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null)
  		{
  			strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null)
  		{
  			strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);
  		}
		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://mps.quality.sentry-link.com/api/v1/user_preferences.json";break;
  			case "SG":strUrl = "https://mps.staging.sentry-link.com/api/v1/user_preferences.json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/users/user_preferences.json";break;
  		}
	    //Create GET Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
		try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    	if(response1.toString().contains("200"))
	    	{
	    		response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";String nl = System.getProperty("line.separator");while ((l = in.readLine()) !=null){sb.append(l + nl);}in.close();
	            data = sb.toString();response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String strUserValues = jsonObject.get("user_preferences").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strUserValues);
	    		if(jsonObject1.get(StrUserPreferences) == null)
	    		{
	    			return "";
	    		}
	    		else
	    		{
	    			return jsonObject1.get(StrUserPreferences).toString();
	    		}
			}
		    else{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+":"+strMethondName,"Local");}
	    	response1.close();
	    }
	    catch (Exception e) {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+":"+strMethondName,"Local");}
		return "";
	}
	public void PUT_PhoneNumber(Map<String, String> objDictionary, String strRole, String strPhoneNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) {strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);}
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://mps.quality.sentry-link.com/api/v1/user_preferences.json";break;
  			case "SG":strUrl = "https://mps.staging.sentry-link.com/api/v1/user_preferences.json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/users/user_preferences.json";break;
  		}
  	    //Create Pur Request
		HttpPut httpPut = new HttpPut(strUrl);
	    httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPut.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpPut.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    StringEntity entity = new StringEntity("{\"user_preferences\":{\"phone_number\":\""+strPhoneNumber+"\"}}", "UTF-8");
	    httpPut.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPut.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPut);
	    	if(response1.toString().contains("200"))
	    	{Reporter.log("The Phone number ("+strPhoneNumber+") was added for user ("+strUserName.toLowerCase()+")");}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethondName,"Local");
	    }
	}
	public void PUT_SMSBackUp(Map<String, String> objDictionary, String strRole, String strBackUpStatus)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
        String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
        String strMunicipality = objDictionary.get("strMunicipality");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
		String strJsonToken = objDictionary.get("strJsonUserToken");
  		if(strJsonToken == null){strJsonToken = StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);}
  		String strJsonUserId = objDictionary.get("strJsonUserId");
  		if(strJsonUserId == null) {strJsonUserId = StoreJsonUserId(objDictionary, strUserName.toLowerCase(), strPassword);}
  		switch (strEnvironment)
  		{
  			case "QA":strUrl = "http://mps.quality.sentry-link.com/api/v1/user_preferences.json";break;
  			case "SG":strUrl = "https://mps.staging.sentry-link.com/api/v1/user_preferences.json";break;
  			case "PROD":strUrl= "https://mpstest.mpspark.com/api/v1/users/user_preferences.json";break;
  		}
  	    //Create GET Request
		HttpPut httpPut = new HttpPut(strUrl);
	    httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPut.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpPut.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    StringEntity entity = new StringEntity("{\"user_preferences\":{\"send_sms_as_backup_for_push_notification\":\""+strBackUpStatus+"\"}}", "UTF-8");
	    httpPut.setEntity(entity);
	    try
	    {
    		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpPut.setConfig(localConfig);
	    	CloseableHttpResponse response1 = httpClient.execute(httpPut);
	    	if(response1.toString().contains("200"))
	    	{Reporter.log("The SMS Backup was set to ("+strBackUpStatus+") for the user ("+strUserName.toLowerCase()+")");}
	    	else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString()+"-"+strMethondName,"Local");}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg+"-"+strMethondName,"Local");
	    }
	}

	//LOT Functions
	public void CURL_ParkInLot(Map<String, String> objDictionary, String strLicensePlateNumber, String strLicensePlateState, String strEntryId) //throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
	 	String url = "http://10.10.100.106:3000/api/"+strEntryId;
	 	String strConfidence = "85";
	 	String data = "{\"plate\":\"" + strLicensePlateNumber + "\",\"state\":\"" + strLicensePlateState + "\",\"confidence\":" + Integer.parseInt(strConfidence) + "}";
	 	//String data = "{\"plate\":\""+strLicensePlateNumber+"\",\"state\":\""+strLicensePlateState+"\"}";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try
        {
        	StringEntity entity = new StringEntity(data);
        	httpPost.setEntity(entity);
        	httpPost.setHeader("Content-type", "application/json");
        	SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        	// Get the current time
            Date currentTime = new Date();
            // Format the current time using the SimpleDateFormat
            objDictionary.put("strParkTimestamp", dateFormat.format(currentTime));
            Reporter.log("strParkTimestamp: "+dateFormat.format(currentTime));
			HttpResponse response = httpClient.execute(httpPost);
	        int intResponseCode = response.getStatusLine().getStatusCode();
	        if(intResponseCode == 200){
	        	Reporter.log("The vehicle with license plate ("+strLicensePlateNumber+") and State ("+strLicensePlateState+") has entered the lot");
	        }
	        else{
	        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "Unable to enter log.  Response Code"+intResponseCode,"Local");
	        }
        }
        catch (Exception e)
	    {
        	System.out.println("MIH");
        }
	}
	public void CURL_ExitLot(Map<String, String> objDictionary, String strLicensePlateNumber, String strLicensePlateState,String strExitId,String strUseTowardsCamera)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
        String url = "http://10.10.100.106:3000/api/"+strExitId;
        String strConfidence = "90";
        String data = "{\"plate\":\"" + strLicensePlateNumber + "\",\"state\":\"" + strLicensePlateState + "\",\"confidence\":" + Integer.parseInt(strConfidence) + "}";
        //String data = "{\"plate\":\""+strLicensePlateNumber+"\",\"state\":\""+strLicensePlateState+"\"}";
        if(strUseTowardsCamera.equals("True"))
        {data = "{\"plate\":\""+strLicensePlateNumber+"\",\"state\":\""+strLicensePlateState+"\",\"towardsCamera\":true}";}
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try
        {
        	StringEntity entity = new StringEntity(data);
        	httpPost.setEntity(entity);
        	httpPost.setHeader("Content-type", "applicatioAn/json");
	        HttpResponse response = httpClient.execute(httpPost);
	        int intResponseCode = response.getStatusLine().getStatusCode();
	        if(intResponseCode == 200){
	        	Reporter.log("The vehicle with license plate ("+strLicensePlateNumber+") and State ("+strLicensePlateState+") has exited the lot");
			}
	        else{
	        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "Unable to exit log.  Response Code"+intResponseCode,"Local");
	        }
        }
        catch (Exception e){
        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "Unable to exit log.  Exeception"+e.getMessage(),"Local");
        }
	}
	public String HTTPCONNECTIONS_StoreLotParkingId(Map<String, String> objDictionary, String strHostType, String strLicensePlateNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strKioskId = "";
		String strParkingId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");//tUbPmX4osmUg3p6sgCoy
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){
  			strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
  		}
  		//Get Kiosk Id
		strKioskId = Json_KioskId(objDictionary, strHostType);
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strKioskId+"/current_rates.json";
  				break;
  			case "SG":
  				//https://liketufts.staging.sentry-link.com/api/v1/devices/2675/parking_sessions.json?
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strKioskId+"/parking_sessions.json?";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strKioskId+"/parking_sessions.json?";
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		System.out.println(parkingsessiongroups.size());
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					String strPlateNumber = innerMeter.get("plate_number").toString();
    					System.out.println(strPlateNumber);
    					System.out.println(strLicensePlateNumber);
    					if(strPlateNumber.equals(strLicensePlateNumber))
    					{
    						strParkingId =  innerMeter.get("id").toString();
	    					objDictionary.put("strParkingId", innerMeter.get("id").toString());
	    					System.out.println("strParkingId: "+innerMeter.get("id").toString());
	    					Reporter.log("<font color='purple'> strParkingId: "+innerMeter.get("id").toString()+"</font>");
	    					//Store ParkTimestamp
	    					String strParkTimestamp =  innerMeter.get("park_timestamp").toString();
	    					objDictionary.put("strParkTimestamp", strParkTimestamp);
	    					System.out.println("strParkTimestamp: "+strParkTimestamp);
	    					Reporter.log("<font color='purple'> strParkTimestamp: "+strParkTimestamp+"</font>");
	    					return strParkingId;
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return strParkingId;
	}
	public String HTTPCONNECTIONS_StoreLotParkingId2(Map<String, String> objDictionary, String strHostType, String strLicensePlateNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strKioskId = "";
		String strParkingId = "";
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strRole = objDictionary.get("strRole");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);
		//Get Kiosk Id
		strKioskId = Json_KioskId(objDictionary, strHostType);
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strKioskId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strKioskId+"/parking_sessions.json?";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strKioskId+"/parking_sessions.json?";
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		System.out.println(parkingsessiongroups.size());
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					String strPlateNumber = innerMeter.get("plate_number").toString();
    					System.out.println(strPlateNumber);
    					System.out.println(strLicensePlateNumber);
    					if(strPlateNumber.equals(strLicensePlateNumber))
    					{
    						strParkingId =  innerMeter.get("id").toString();
	    					objDictionary.put("strParkingId", innerMeter.get("id").toString());
	    					System.out.println("strParkingId: "+innerMeter.get("id").toString());
	    					Reporter.log("<font color='purple'> strParkingId: "+innerMeter.get("id").toString()+"</font>");
	    					//Store ParkTimestamp
	    					String strParkTimestamp =  innerMeter.get("park_timestamp").toString();
	    					objDictionary.put("strParkTimestamp", strParkTimestamp);
	    					System.out.println("strParkTimestamp: "+strParkTimestamp);
	    					Reporter.log("<font color='purple'> strParkTimestamp: "+strParkTimestamp+"</font>");
	    					return strParkingId;
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return strParkingId;
	}
	
	//Boot Notice
	/**
	 * Sends a "boot notice" to SentryLink
	 *
	 * @param objDictionary   Your existing dictionary with credentials, environment, etc.
	 * @param plateState      The state on the license plate, e.g. "ARIZONA", "TEXAS", "FLORIDA"
	 * @param takenAt         The exact date/time the action was taken (with timezone offset)
	 *                        Example: "2025-11-03T23:40:00-04:00"
	 *                        You can pass it as String or use OffsetDateTime
	 * @return API response body on success, null on error
	 */
	public String CURL_Lot_Boot_Notice(Map<String, String> objDictionary, String strLicensePlateNumber, String takenAt) {
	    
	    HttpConnections clsHttpConnections = new HttpConnections();
	    CommonWeb clsCommonWeb = new CommonWeb();

	    String strDeviceId = objDictionary.get("strDeviceId");
	    String strEnvironment = objDictionary.get("strEnvironment");
	    String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
	    String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
	    //g8k-cvYC54J2R2Pc9bqp
	    if (strJsonAdminToken == null || strJsonAdminToken.trim().isEmpty()) {
	        strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
	    }
	    String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
	    // Determine Lot ID
	    String strLotId;
	    switch (strDeviceId) {
	        case "Lot Auto One Pay":
	            strLotId = "175";
	            break;
	        default:
	            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null,"Unknown strDeviceId: " + strDeviceId, "Local");
	            return null;
	    }
	    // Determine API URL
	    String strUrl;
	    switch (strEnvironment) {
	        case "QA":
	            strUrl = "http://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/license_plates/action_taken.json";
	            break;
	        case "SG":
	            strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/api/v1/license_plates/action_taken.json";
	            break;
	        default:
	            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null,"Unknown environment: " + strEnvironment, "Local");
	            return null;
	    }
	    // Build JSON payload – location_id as number, no quotes!
	    String jsonPayload = String.format(
	        "{\"location_id\":%s,\"action_taken\":\"booted\",\"taken_at\":\"%s\",\"plate_attributes\":[{\"plate\":\"%s\"}]}",
	        strLotId, takenAt, strLicensePlateNumber
	    ).trim();
	    HttpPost httpPost = new HttpPost(strUrl);
	    httpPost.addHeader("Content-Type", "application/json");
	    httpPost.addHeader("Accept", "application/json");           // REQUIRED for Rails APIs
	    httpPost.addHeader("X-User-Token", strJsonAdminToken);
	    httpPost.addHeader("X-User-Email", strAdminUser);

	    StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
	    httpPost.setEntity(entity);

	    CloseableHttpClient httpClient = HttpClients.createDefault();

	    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
	        int statusCode = response.getStatusLine().getStatusCode();
	        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

	        if (statusCode >= 200 && statusCode < 300) {
	            return responseBody;  // Success
	        } else {
	            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null,
	                "Boot notice failed → HTTP " + statusCode + " | Response: " + responseBody, "Local");
	            return null;
	        }

	    } catch (Exception e) {
	        clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null,
	            "CURL_Lot_Boot_Notice exception: " + e.getMessage(), "Local");
	        return null;
	    }
	}
	//******************************************************************************************************************************************************
	//METER GROUP SETTING
	//******************************************************************************************************************************************************
	public String GET_MeterGroupInitialGraceTimeSecond(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		String strMeterGroup =  objDictionary.get("strMeterGroup");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strMeterGroupName = "";
		String strMeterGroupInitialGrace = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");//tUbPmX4osmUg3p6sgCoy
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/meter_groups.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meter_groups.json";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/meter_groups.json";
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("meter_groups");
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					strMeterGroupName =  innerMeter.get("name").toString();
    					if(strMeterGroupName.equals(strMeterGroup))
    					{
    						strMeterGroupInitialGrace =  innerMeter.get("PARKING_INIT_GRACE_PERIOD").toString();
    						Reporter.log("<font color='purple'> strMeterGroupInitialGrace: "+innerMeter.get("PARKING_INIT_GRACE_PERIOD").toString()+"</font>");
    						return strMeterGroupInitialGrace;
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return strMeterGroupInitialGrace;
	}

	public void ConvertGreenwichMeanTime(String dateInString)
	{
		try
		{

			//Date date = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//			Date date = formatter.parse("2018-03-12 18:13:23");

			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date date = formatter.parse("18:13:23");

			DateFormat cstFormat = new SimpleDateFormat();
			DateFormat gmtFormat = new SimpleDateFormat();
			TimeZone gmtTime = TimeZone.getTimeZone("GMT");
			TimeZone cstTime = TimeZone.getTimeZone("CST");

			cstFormat.setTimeZone(gmtTime);
			gmtFormat.setTimeZone(cstTime);
			System.out.println("GMT Time: " + cstFormat.format(date));
			System.out.println("CST Time: " + gmtFormat.format(date));


			SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
			sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
			sdf3.setTimeZone(TimeZone.getTimeZone("CST"));
			sdf3.parse("18:13:23");



			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			sdf.parse("2018-03-12T18:13:23Z");

			System.out.println(sdf.format(new Date()));


			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
			sdf2.parse("18:13:23");


			System.out.println(sdf2.format(new Date()));
//
//			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("CST"));
//			String strMeterEndTime = dateFormatGmt.format("2018-03-12T18:13:23Z");
//			Reporter.log("<font color='Green'>Meter End Time "+strMeterEndTime+"</font>");
//			//objDictionary.remove("strMeterEndTime");objDictionary.put("strMeterEndTime", strMeterEndTime);
//
//			SimpleDateFormat("hh:mm:ss").format(new Date())
//

//			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//	 		Reporter.log(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));



			final DateFormat formatter1 = DateFormat.getDateTimeInstance();
			Date timezone = formatter1.parse("2012-04-14 14:23:34");
			formatter1.setTimeZone(TimeZone.getTimeZone("GMT"));
			System.out.println(formatter1.format(timezone));

//			//SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//	        //dateInString = "22-01-2015 10:15:55 AM";
//	        Date date = formatter.parse(dateInString);
//	        TimeZone tz = TimeZone.getDefault();
//
//	        // From TimeZone Asia/Singapore
//	        System.out.println("TimeZone : " + tz.getID() + " - " + tz.getDisplayName());
//	        System.out.println("TimeZone : " + tz);
//	        System.out.println("Date (Singapore) : " + formatter.format(date));
//
//	        // To TimeZone America/New_York
//	        SimpleDateFormat sdfAmerica = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//	        TimeZone tzInAmerica = TimeZone.getTimeZone("GMT");
//	        sdfAmerica.setTimeZone(tzInAmerica);
//
//	        String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
//	        Date dateInAmerica = formatter.parse(sDateInAmerica); // Create a new Date object
//
//	        System.out.println("\nTimeZone : " + tzInAmerica.getID() + " - " + tzInAmerica.getDisplayName());
//	        System.out.println("TimeZone : " + tzInAmerica);
//	        System.out.println("Date (New York) (String) : " + sDateInAmerica);
//	        System.out.println("Date (New York) (Object) : " + formatter.format(dateInAmerica));
//	        System.out.println("MIH");
		}catch (Exception e)
		{
	           e.printStackTrace();
	    }

//		try
//		{
//
//		 Calendar currentdate = Calendar.getInstance();
//         String strdate = null;
//         DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//         strdate = formatter.format(dateInString);
//         TimeZone obj = TimeZone.getTimeZone("CST");
//
//         formatter.setTimeZone(obj);
//         //System.out.println(strdate);
//         //System.out.println(formatter.parse(strdate));
//         Date theResult = formatter.parse(strdate);
//
//         System.out.println("The current time in India is  :: " +currentdate.getTime());
//		}catch (Exception e)
//		{
//	           e.printStackTrace();
//	    }


	}
	public void JsonMeterInformation(Map<String, String> objDictionary, String strJsonUserId, String strJsonToken, String strUserName) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl = "https://mps.staging.sentry-link.com/api/v1/meters.json";
  				break;
  			default:
  		}
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    //Create Delete Request
	    HttpDelete httpDelete = new HttpDelete(strUrl);
	    httpDelete.addHeader(new BasicHeader("Accept", "application/json"));
	    httpDelete.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpDelete.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpDelete.addHeader(new BasicHeader("X-User-Email", "darin@mpspark.com"));
	    //Send Request
	    try
	    {
	    	CloseableHttpResponse response1 = httpclient.execute(httpDelete);
	    	if(response1.toString().contains("200"))
			{
	    		response1.close();
	    		Reporter.log("The User ("+strUserName+") was destroyed"+"");
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	public void HTTPCONNECTIONS_StoreMobileTransactionFees(Map<String, String> objDictionary) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, "Local");}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    		if(response1.toString().contains("200"))
				{
		    			response1.getStatusLine().getStatusCode();
		            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		            StringBuffer sb = new StringBuffer("");
		            String l = "";
		            String nl = System.getProperty("line.separator");
		            while ((l = in.readLine()) !=null){sb.append(l + nl);}
		            in.close();
		            data = sb.toString();
			    		response1.close();
			    		JSONParser jsonParser = new JSONParser();
			    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			    		JSONArray lang= (JSONArray) jsonObject.get("transaction_fee_schedules");
			    		for(int i=0; i<lang.size(); i++)
			    		{
			    			System.out.println("The " + i + " element of the array: "+lang.get(i));
			    		}
			    		Iterator<?> i = lang.iterator();
			    		// Take Each Value from the json Array Separtely
			    		while (i.hasNext())
			    		{
			    			JSONObject innerObj = (JSONObject) i.next();
			    			switch (innerObj.get("fee_type").toString())
			    			{
				    			case "FirstPayment":
				    				objDictionary.remove("strFirstPayment");objDictionary.put("strFirstPayment", innerObj.get("fee").toString());
				    				Reporter.log("The value (strFirstPayment) was stored as variable name (" + innerObj.get("fee").toString() + ")");
				    				break;
				    			case "SubsequentPayment":
				    				objDictionary.remove("strSubsequentPayment");objDictionary.put("strSubsequentPayment", innerObj.get("fee").toString());
				    				Reporter.log("The value (strSubsequentPayment) was stored as variable name (" + innerObj.get("fee").toString() + ")");
				    				break;
			    			}
			    		}
				}
		    	else
		    	{
		    		String strErrorMsg = response1.toString();
		    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
		    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		    	}
	    }
	    catch (Exception e)
	    {
	    		String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	
	public void HTTPCONNECTIONS_StoreKioskTransactionFees(Map<String, String> objDictionary) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strKioskId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		strKioskId = Json_KioskId(objDictionary, "Local");
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strKioskId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strKioskId+"/current_rates.json";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strKioskId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    		if(response1.toString().contains("200"))
				{
		    			response1.getStatusLine().getStatusCode();
		            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		            StringBuffer sb = new StringBuffer("");
		            String l = "";
		            String nl = System.getProperty("line.separator");
		            while ((l = in.readLine()) !=null){sb.append(l + nl);}
		            in.close();
		            data = sb.toString();
			    		response1.close();
			    		JSONParser jsonParser = new JSONParser();
			    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			    		JSONArray lang= (JSONArray) jsonObject.get("transaction_fee_schedules");
			    		for(int i=0; i<lang.size(); i++)
			    		{
			    			System.out.println("The " + i + " element of the array: "+lang.get(i));
			    		}
			    		Iterator<?> i = lang.iterator();
			    		// Take Each Value from the json Array Separtely
			    		while (i.hasNext())
			    		{
			    			JSONObject innerObj = (JSONObject) i.next();
			    			switch (innerObj.get("fee_type").toString())
			    			{
				    			case "FirstPayment":
				    				objDictionary.remove("strFirstPayment");objDictionary.put("strFirstPayment", innerObj.get("fee").toString());
				    				Reporter.log("The value (strFirstPayment) was stored as variable name (" + innerObj.get("fee").toString() + ")");
				    				break;
				    			case "SubsequentPayment":
				    				objDictionary.remove("strSubsequentPayment");objDictionary.put("strSubsequentPayment", innerObj.get("fee").toString());
				    				Reporter.log("The value (strSubsequentPayment) was stored as variable name (" + innerObj.get("fee").toString() + ")");
				    				break;
			    			}
			    		}
				}
		    	else
		    	{
		    		String strErrorMsg = response1.toString();
		    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
		    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	
	public void HTTPCONNECTIONS_StoreMeterTransactionFees(Map<String, String> objDictionary) throws IOException 
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);} 
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, "Local");}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				//https://subdomain.domain/api/v1/devices/:id/current_rates.json
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig); 
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	    		if(response1.toString().contains("200"))
				{	
		    			response1.getStatusLine().getStatusCode();
		            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		            StringBuffer sb = new StringBuffer("");
		            String l = "";
		            String nl = System.getProperty("line.separator");
		            while ((l = in.readLine()) !=null){sb.append(l + nl);}
		            in.close();
		            data = sb.toString();
			    		response1.close();
			    		JSONParser jsonParser = new JSONParser();
			    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			    		JSONArray lang= (JSONArray) jsonObject.get("transaction_fee_schedules");
			    		for(int i=0; i<lang.size(); i++)
			    		{
			    			System.out.println("The " + i + " element of the array: "+lang.get(i));
			    		}
			    		Iterator<?> i = lang.iterator();
			    		// Take Each Value from the json Array Separtely
			    		while (i.hasNext()) 
			    		{
			    			JSONObject innerObj = (JSONObject) i.next();
			    			switch (innerObj.get("fee_type").toString())
			    			{
				    			case "FirstPayment":
				    				objDictionary.remove("strFirstPayment");objDictionary.put("strFirstPayment", innerObj.get("fee").toString());
				    				Reporter.log("The value (strFirstPayment) was stored as variable name (" + innerObj.get("fee").toString() + ")");
				    				break;
				    			case "SubsequentPayment":
				    				objDictionary.remove("strSubsequentPayment");objDictionary.put("strSubsequentPayment", innerObj.get("fee").toString());
				    				Reporter.log("The value (strSubsequentPayment) was stored as variable name (" + innerObj.get("fee").toString() + ")");
				    				break;
			    			}
			    		}
				}
		    	else
		    	{
		    		String strErrorMsg = response1.toString();
		    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
		    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		    	}
	    }
	    catch (Exception e) 
	    {
	    		String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	}
	
	public String HTTPCONNECTIONS_FetchLegacyMeterStatus(Map<String, String> objDictionary,String strLicensePlate)
	{
		// Basic HTTP Authentication
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strRole = objDictionary.get("strRole");
		String strDeviceId = objDictionary.get("strDeviceId");
		String strUserName = "darin@mpspark.com";
		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		//Function Variables
		String strUrl = "";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strLicensePlateNumber = objDictionary.get("strLicensePlateNumber");
  		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://quality.sentry-link.com/api/v1/tokens.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/mobile/parking_sessions/legacy_meter_status.json?zoneid=VirtualAutomationMeterGroup";
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/mobile/parking_sessions/legacy_meter_status.json?zoneid=No%20Lot";
  				break;
  			default:
  		}
  		try
  		{
  			URL url = new URL (strUrl);
  			String encoding = Base64.getEncoder().encodeToString((strUserName+":"+strPassword).getBytes("utf-8"));
  			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  			connection.setRequestMethod("GET");
  			connection.setDoOutput(true);
  			connection.setRequestProperty  ("Authorization", "Basic " + encoding);
  			InputStream content = connection.getInputStream();
  			BufferedReader in   = new BufferedReader (new InputStreamReader (content));
  			String line;
  			while ((line = in.readLine()) != null)
       	    {
  				String[] parts = line.split(":");
  				String strCurrentOccupiedLicensePlate = parts[3];
  				if (line.contains(strDeviceId) && line.contains(strLicensePlateNumber))
   	    		{return "Occupied-Current Vehicle";}
   	    		else if (line.contains(strDeviceId))
   	    		{
   	    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "There is another Vechile Parked in this spot with the license plate ("+strCurrentOccupiedLicensePlate+")","Local");
   	    		}
   	    	}
 		    in.close();
  		}
  		catch(Exception e)
  		{
  			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");
  		}
  		return "Empty";
	}
	public String Json_ExternalViolationId(Map<String, String> objDictionary, String strViolationReason) throws IOException
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/violation_reasons.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/violation_reasons.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/violation_reasons.json";
  				if(strViolationReason.equals("Initial Grace Period Exceeded")){strViolationReason = "Initial grace period exceeded";}
  				if(strViolationReason.equals("Expired Inspection Over 60 Days")){strViolationReason = "expired_inspection_over_60_days";}
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray violationreasons = (JSONArray) jsonObject.get("violation_reasons");
	    		for(int i=0; i < violationreasons.size(); i++)
	    		{
	    			System.out.println("The " + i + " element of the array: "+violationreasons.get(i));
    				Iterator<?> z = violationreasons.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println(innerMeter.get("description").toString());
    					objDictionary.remove("strStatuteCode");objDictionary.put("strStatuteCode", innerMeter.get("statute_code").toString());
    					if(innerMeter.get("description").toString().contains(strViolationReason))
    					{
    						String strExternalDescription = innerMeter.get("external_description").toString();
    						objDictionary.remove("strViolationExternalReason");objDictionary.put("strViolationExternalReason", strExternalDescription);
    						Reporter.log("External Reason Code ("+strExternalDescription+")  was stored as (strViolationExternalReason) for the violation reason ("+strViolationReason+")");
    						return strExternalDescription;
    					}
    				}
	    		}
			}
	        else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");
	    }
	    clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Violation Reason ("+strViolationReason+") does not exist-"+strMethodName,"Local");
	    return "";
	}
	public String Json_MeterId(Map<String, String> objDictionary, String strHostType)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Get Device Id
		String strDeviceId = "";
		if(strHostType.equals("Local")){strDeviceId = objDictionary.get("strDeviceId");}
		else if(strHostType.contains("Remote"))
		{
			if(strHostType.equals("Remote2"))
			{
				System.out.println("MIH");
			}
			strDeviceId = objDictionary.get("strRemoteDeviceId"+strHostType.substring(strHostType.length() - 1));
		}
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		BufferedReader in = null;
        String data = null;
        String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
        String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		if(strJsonAdminToken == null)
  		{strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/meters.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email",strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("meter_groups");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("meters");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext())
	    				{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
	    	    			{
	    						return innerMeter.get("id").toString();
	    	    			}
    	    			}
		    		}
	    		}
			}
	        else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");}
	    return "";
	}
	public String Json_MeterMaintenanceMode(Map<String, String> objDictionary, String strHostType)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		//Get Device Id
		String strDeviceId = "";
		if(strHostType.equals("Local")){strDeviceId = objDictionary.get("strDeviceId");}
		else if(strHostType.contains("Remote"))
		{strDeviceId = objDictionary.get("strRemoteDeviceId"+strHostType.substring(strHostType.length() - 1));}
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		BufferedReader in = null;
        String data = null;
        String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meters.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/meters.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email",strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("meter_groups");
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			JSONParser jsonParser2 = new JSONParser();
	    			JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(metergroups.get(i).toString());
	    			JSONArray meters= (JSONArray) jsonObject2.get("meters");
	    			//System.out.println(meters);
	    			for(int j=0; j<meters.size(); j++)
		    		{
	    				//System.out.println("The " + j + " element of the array: "+meters.get(j));
	    				Iterator<?> z = meters.iterator();
	    				while (z.hasNext())
	    				{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
	    	    			{
	    						return innerMeter.get("maintenance_mode").toString();
	    	    			}
    	    			}
		    		}
	    		}
			}
	        else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");}
	    return "";
	}
	public String HTTPCONNECTIONS_IsMeterSpotMainteanceMode(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/devices/"+strMeterId+"/spots.json";
  				//strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONArray spots= (JSONArray) jsonParser.parse(data);
	    		//System.out.println(metergroups);
	    		for(int i=0; i<spots.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			Iterator<?> z = spots.iterator();
    				while (z.hasNext())
	    			{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(innerMeter.get("seq").toString().equals(strSpotNumber))
    					{
    						//System.out.println(innerMeter.get("id").toString());
    	    				System.out.println(innerMeter.get("maintenance_mode").toString());
    	    				return innerMeter.get("maintenance_mode").toString();
    	    			}
	    			}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}
	public String HTTPCONNECTIONS_AnyMeterSpotMainteanceMode(Map<String, String> objDictionary, String strHostType)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/devices/meters/"+strMeterId+"/spots.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/devices/"+strMeterId+"/spots.json";
  				//strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONArray spots= (JSONArray) jsonParser.parse(data);
	    		Iterator<?> z = spots.iterator();
				while (z.hasNext())
    			{
					JSONObject innerMeter = (JSONObject) z.next();
					System.out.println(innerMeter.get("maintenance_mode").toString());
					if(innerMeter.get("maintenance_mode").toString().equals("true"))
    				{
    					return "true";
    				}
    			}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "false";
	}
	public String Json_VirtualMeterId(Map<String, String> objDictionary)
	{
		HttpConnections clsHttpConnections = new HttpConnections();
		CommonWeb clsCommonWeb = new CommonWeb();
		String strDeviceId = objDictionary.get("strDeviceId");
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();//kkautomationmunicipalityadmin@gmail.com
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");//AjChG5X2mD4dMsNg1UxC
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/virtual_devices.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/virtual_devices.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/virtual_devices.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray metergroups= (JSONArray) jsonObject.get("virtual_devices");
	    		//System.out.println(metergroups);
	    		for(int i=0; i<metergroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			Iterator<?> z = metergroups.iterator();
    				while (z.hasNext())
    	    		{
    					JSONObject innerMeter = (JSONObject) z.next();
    					//System.out.println(innerMeter.get("friendly_name").toString());
	    	    		if(innerMeter.get("friendly_name").toString().contains(strDeviceId))
	    	    		{
	    	    			//System.out.println(innerMeter.get("id").toString());
	    	    			return innerMeter.get("id").toString();
	    	    		}
    	    		}
	    		}
			}
	        else
	        {
	        	String strErrorMsg = response1.toString();
	        	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	        	Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	        }
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}
	public String CURL_EndParkingSession(Map<String, String> objDictionary) throws IOException
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Function Variables
		String strUrl = "";
		String strRole = "parker";
  		String strEnvironment = objDictionary.get("strEnvironment");
  		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
  		String strParkingSessionId = GetJsonParkingSessionId(objDictionary);//Commented it out for new VM User
  		if(!strParkingSessionId.equals(""))
		{
	  		switch (strEnvironment)
	  		{
	  			case "QA":
					strUrl = "http://sentrylink.staging.sentry-link.com/api/v1/parking_sessions/"+strParkingSessionId+"/end_session.json";
					break;
				case "SG":
					strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_sessions/"+strParkingSessionId+"/end_session.json";
					break;
				case "PROD":
					strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_sessions/"+strParkingSessionId+"/end_session.json";
					break;
	  		}
			HttpConnections clsHttpConnections = new HttpConnections();
			CommonWeb clsCommonWeb = new CommonWeb();
			//Dictionary Variables
			String strMunicipality = objDictionary.get("strMunicipality");
			String strUniqueId = objDictionary.get("strUniqueId");
			String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole+"@gmail.com";
			String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName.toLowerCase(), strRole);
			String strJsonToken = clsHttpConnections.StoreJsonUserToken(objDictionary, strUserName.toLowerCase(), strPassword);
			Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	  		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	  		System.out.println(sdf.format(new Date()));
	  		String strExitTimeStamp = sdf.format(new Date());
			try
			{
				String stCommand = "curl -H \"Content-Type:application/json\" -H \"X-User-Token:"+strJsonToken+"\" -H \"X-User-Email:"+strUserName.toLowerCase()+"\" -X PUT -d \'{\"exit_timestamp\":\""+strExitTimeStamp+"\"}\' "+strUrl;
				System.out.print(stCommand);
				String[] command = {stCommand};
				System.out.print("MIH");
				Process process = Runtime.getRuntime().exec(command);
				System.out.print("MIH");
			}
			catch (Exception e)
			{
				Reporter.log("<font color='red'>"+strMethodName+"-"+e.toString()+"</font>");Assert.fail(strMethodName+"-"+e.toString());
			}
		}
		return "";
	}
	public void JsonEndParkingSession(Map<String, String> objDictionary) throws IOException
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strRole = objDictionary.get("strRole");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		String strUrl = "";
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);//SeByN9disUYnFbxQkJsG
		//This Fails if New User
		//String strParkingSessionId = "";
		//If this fails add user type
		String strParkingSessionId = objDictionary.get("strParkingSessionId");
		if(strParkingSessionId == null)
		{strParkingSessionId = GetJsonParkingSessionId(objDictionary);}//Commented it out for new VM User
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		if(!strParkingSessionId.equals(""))
		{
	        switch (strEnvironment)
	  		{
	  			case "QA":
	  				strUrl = "http://sentrylink.staging.sentry-link.com/api/v1/parking_sessions/"+strParkingSessionId+"/end_session.json";
	  				break;
	  			case "SG":
	  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_sessions/"+strParkingSessionId+"/end_session.json";
	  				break;
	  			case "PROD":
	  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_sessions/"+strParkingSessionId+"/end_session.json";
	  				break;
	  		}
	        HttpPut httpPut = new HttpPut(strUrl);
	        httpPut.addHeader(new BasicHeader("Accept", "application/json"));
	        httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
	        httpPut.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	        httpPut.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
			//System.out.println(calendar.getTime());

			String strExitTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//	  		String strExitTimeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(calendar.getTime());
			StringEntity entity = new StringEntity("{\"exit_timestamp\":\""+strExitTimeStamp+"\"}", "UTF-8");
	        httpPut.setEntity(entity);
		    //Send Request
		    try
		    {
		    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
		        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
		        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
		        httpPut.setConfig(localConfig);
		        CloseableHttpResponse response1 = httpClient.execute(httpPut);
		    	if(response1.toString().contains("200"))
		    	{Reporter.log("Virtual Meter Session was ended at " + strExitTimeStamp);}
		    	else
		    	{
		    		String strErrorMsg = response1.toString();
		    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
		    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		    	}
		    }
		    catch (Exception e)
		    {
		    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
				Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
		    }
		}
		else
		{Reporter.log("No Virtual Meter Parking Session Existed");}
	}
	public String GetJsonParkingSessionId(Map<String, String> objDictionary)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  		String strRole = objDictionary.get("strRole");
  		String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		String strUrl = "";
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.staging.sentry-link.com/api/v1/parking_sessions.json";
  				break;
  			case "SG":
  				strUrl= "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/parking_sessions.json";
  				break;
  			case "PROD":
  				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/parking_sessions.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strUserName.toLowerCase()));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            //System.out.println(data);
	            response1.close();
	    		JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		//System.out.println(parkingsessiongroups);
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			//System.out.println("The " + i + " element of the array: "+metergroups.get(i));
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					return innerMeter.get("id").toString();
    	    		}
	    		}
			}
	    	else
	    	{
	    		String strErrorMsg = response1.toString();
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    		Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    	}
	    }
	    catch (Exception e)
	    {
	    	String strErrorMsg = strMethondName+"-"+e.toString()+"";
			Reporter.log("<font color='red'>"+strErrorMsg+"</font>");Assert.fail(strErrorMsg);
	    }
	    return "";
	}
	public String GetJsonViolationIdUsingParkingSession(Map<String, String> objDictionary) {
	    CommonWeb clsCommonWeb = new CommonWeb();
	    String strMethondName = new Object() {}.getClass().getEnclosingMethod().getName();
	    String strEnvironment = objDictionary.get("strEnvironment");
	    String strUniqueId = objDictionary.get("strUniqueId");
	    String strMunicipality = objDictionary.get("strMunicipality");
	    String strRole = "admin";
	    String strAdminUserName = "";
	    if(strEnvironment.equals("PROD"))
  		{strAdminUserName = "darinadmin@mpspark.com";}
  		else
  		{
  			 strAdminUserName = strUniqueId + strMunicipality.replace(" ", "").replace(",", "") + strRole.replace(" ", "") + "@gmail.com";
  		}
	    String strAdminPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strAdminUserName, strRole);
	    String strUrl = "";
	    String strJsonToken = StoreJsonUserToken(objDictionary, strAdminUserName, strAdminPassword);
	    String strParkingId = objDictionary.get("strParkingId");
	    BufferedReader in = null;
	    String data = null;
	    String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");

	    switch (strEnvironment) {
	        case "QA":
	            strUrl = "https://" + strMunicipalitySubdomain + ".quality.sentry-link.com/parking_sessions/" + strParkingId + ".json";
	            break;
	        case "SG":
	            strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/parking_sessions/" + strParkingId + ".json";
	            break;
	        case "PROD":
	            //strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.sentry-link.com/parking_sessions/" + strParkingId + ".json";
	            strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.com/parking_sessions/" + strParkingId + ".json";
	            break;
	    }

	    // Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token", strJsonToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUserName.toLowerCase()));

	    // Send Request
	    try {
	        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        
	        if (response1.getStatusLine().getStatusCode() == 200) {
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuilder sb = new StringBuilder();
	            String l;
	            while ((l = in.readLine()) != null) {
	                sb.append(l);
	            }
	            data = sb.toString();
	            in.close();
	            response1.close();

	            // Parse the JSON response
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	            JSONArray violationsGroups = (JSONArray) jsonObject.get("violations");

	            // Extract violation_number from the first violation
	            if (violationsGroups != null && !violationsGroups.isEmpty()) {
	                JSONObject firstViolation = (JSONObject) violationsGroups.get(0);
	                return firstViolation.get("violation_number").toString();
	            }
	        } else {
	            String strErrorMsg = response1.toString();
	            clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary, null, strErrorMsg, "Local");
	            Reporter.log("<font color='red'>" + strErrorMsg + "</font>");
	            Assert.fail(strErrorMsg);
	        }
	    } catch (Exception e) {
	        String strErrorMsg = strMethondName + "-" + e.toString();
	        Reporter.log("<font color='red'>" + strErrorMsg + "</font>");
	        Assert.fail(strErrorMsg);
	    }
	    clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "There was no Violation in Sentry Link For This Session","Local");
	    return "";
	}
	public String SENTRYMOBILE_CreateRandomLicensePlateNumber()
	{
//		String strAutomationUser = System.getProperty("user.name");
//		return strAutomationUser.substring(0, 6).toUpperCase();
		Random r = new Random();
	    String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String LicencePlateNumber = "";
	    for (int i = 0; i < 6; i++) {LicencePlateNumber = LicencePlateNumber + alphabet.charAt(r.nextInt(alphabet.length()));}
	    return LicencePlateNumber;
	}
	public String AddTimeToExistingTime(String strTime, String strPlusMinutes,String strDateFormat)
	{
		String newTime = "";
		try
		{
			DateFormat sdf = new SimpleDateFormat(strDateFormat);
			Date startDate = sdf.parse(strTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MINUTE, Integer.parseInt(strPlusMinutes));
			newTime = sdf.format(cal.getTime());
		}
		catch(Exception e) {}
		return newTime;
	}
	public String SubtractTimeToExistingTime(String strTime, String strPlusMinutes,String strDateFormat)
	{
		String newTime = "";
		try
		{
			DateFormat sdf = new SimpleDateFormat(strDateFormat);
			Date startDate = sdf.parse(strTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MINUTE, -Integer.parseInt(strPlusMinutes));
			newTime = sdf.format(cal.getTime());
		}
		catch(Exception e) {}
		return newTime;
	}
	public String GetMeterSettings(Map<String, String> objDictionary, String strSentrySetting)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strEnvironment = objDictionary.get("strEnvironment");
		HttpConnections clsHttpConnections = new HttpConnections();
		String strUrl = "";
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strMeterId = Json_MeterId(objDictionary, "Local");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/meters/"+strMeterId+"/get_sentry_settings.json";
  				break;
  			case "SG":
  				strUrl="https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/meters/"+strMeterId+"/get_sentry_settings.json";
  				break;
  		}
	    //Create Delete Request
	    HttpPost httpPost = new HttpPost(strUrl);
	    //httpPost.addHeader(new BasicHeader("Accept", "application/json"));
	    httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpPost.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpPost.addHeader(new BasicHeader("X-User-Email",strAdminUser));
	    //Send Request
	    try
	    {
		    //Add Body
		    StringEntity entity = new StringEntity("{\"sentry_settings\":[\""+ strSentrySetting+ "\"]}");
		    httpPost.setEntity(entity);
		    //Send Request
		    try
		    {
		    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
		        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
		        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
		        httpPost.setConfig(localConfig);
		        CloseableHttpResponse response1 = httpClient.execute(httpPost);
		        if(response1.toString().contains("200"))
				{
		        	response1.getStatusLine().getStatusCode();
		        	in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		            StringBuffer sb = new StringBuffer("");
		            String l = "";
		            String nl = System.getProperty("line.separator");
		            while ((l = in.readLine()) !=null){sb.append(l + nl);}
		            in.close();
		            data = sb.toString();
		            if(data.contains(strSentrySetting))
		            {return data.substring(data.indexOf(strSentrySetting)+strSentrySetting.length()+2, data.indexOf("}"));}
		            else
		            {return "";}
		        }
			    	else
			    	{
			    		String strErrorMsg = response1.toString();
			    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
			    	}
		    }
		    catch (Exception e)
		    {
		    		String strErrorMsg = strMethondName+"-"+e.toString()+"";
		    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
		    }
	    }
	    catch (Exception e)
	    {
	    		String strErrorMsg = strMethondName+"-"+e.toString()+"";
	    		clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strErrorMsg,"Local");
	    }
	    return "";
	}
	
	public String CURL_GetViolationsAlertTime(Map<String, String> objDictionary, String strViolationId) {
		CommonWeb clsCommonWeb = new CommonWeb();
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	    String strUrl = "";
	    String strRole = "peo";
	    String strUniqueId = objDictionary.get("strUniqueId");
  		String strMunicipality = objDictionary.get("strMunicipality");
  	    String strUserName = strUniqueId+strMunicipality.replace(" ","").replace(",", "")+strRole.replace(" ","")+"@gmail.com";
  		String strPassword = clsCommonWeb.SENTRYLINK_GetPassword(objDictionary, strUserName, strRole);
		String strJsonToken = StoreJsonUserToken(objDictionary, strUserName, strPassword);
	    String strEnvironment = objDictionary.get("strEnvironment");
	    String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
	    switch (strEnvironment) {
	        case "QA":
	            strUrl = "http://" + strMunicipalitySubdomain + ".quality.sentry-link.com/api/v1/license_plates.json";
	            break;
	        case "SG":
	            strUrl = "https://" + strMunicipalitySubdomain + ".staging.sentry-link.com/api/v1/violations/" + strViolationId + ".json";
	            break;
	        case "PROD":
	            strUrl = "https://" + strMunicipalitySubdomain + ".mpspark.com/api/v1/license_plates.json";
	            break;
	    }
	    HttpURLConnection connection = null;
	    try {
	        URL url = new URL(strUrl);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setRequestProperty("X-User-Token", strJsonToken);
	        connection.setRequestProperty("X-User-Email", strUserName.toLowerCase());
	        connection.setRequestProperty("Content-Type", "application/json");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder output = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            output.append(line).append("\n");
	        }
	        reader.close();
	        String strResponse = output.toString();
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
	        String strViolationData = jsonObject.get("violation").toString();
	        JSONObject jsonObject1 = (JSONObject) jsonParser.parse(strViolationData);
	        String strIssuedTimeStamp = jsonObject1.get("issued_timestamp").toString();
	        String GetViolationsAlertTime = strIssuedTimeStamp.replace("T", " ");
	        if (TimeZone.getDefault().inDaylightTime(new Date())) {
	            return GetViolationsAlertTime.replace("-05:00", " -0500");
	        } else {
	            return GetViolationsAlertTime.replace("-06:00", " -0600");
	        }
	    } catch (Exception e) {
	        Reporter.log("<font color='red'>" + strMethodName + "-" + e.toString() + "</font>");
	        Assert.fail(strMethodName + "-" + e.toString());
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	    return "";
	}

	///*******************
	//LOTS
	//********************
	public String CheckIfPermitHolderCategoryExistsInSentryLink(Map<String, String> objDictionary, String strCategory)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		String strUrl = "";
		String strEnvironment = objDictionary.get("strEnvironment");
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		BufferedReader in = null;
        String data = null;
        switch (strEnvironment)
  		{
  			case "QA":
				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/permit_holder_categories.json";
				break;
			case "SG":
				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/permit_holder_categories.json";
				break;
			case "PROD":
				strUrl= "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/permit_holder_categories.json";
				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
			{
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            JSONParser parser = new JSONParser();
	            JSONArray jsonArray = (JSONArray) parser.parse(data);
	            for (Object obj : jsonArray)
	            {
	            	JSONObject jsonObject = (JSONObject) obj;
	            	String name = (String) jsonObject.get("name");
	            	if(name.equals(name))
	            	{
	            		return "True";
	            	}
	            }
			}
	        else
	    	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {
	    	clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, strMethondName+"-"+e.toString(),"Local");
	    }
	    return "False";
	}


	//*******************
	//PARKING SESSIONS
	//******************
	public String HTTPCONNECTIONS_StoreViolationDateIssued(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String extendeddata = jsonObject.get("extended_data").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(extendeddata);
	    		String payload = jsonObject1.get("payload").toString();
	    		JSONArray arrPayload =  (JSONArray) jsonParser.parse(payload);
	    		for(int i=0; i<arrPayload.size(); i++)
	    		{
	    			Iterator<?> z = arrPayload.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(!innerMeter.isEmpty())
    					{
	    					String settings = innerMeter.get("violation_info").toString();
	    					JSONObject jsonObject3 = (JSONObject) jsonParser.parse(settings);
	    					String strViolationData = jsonObject3.get("violation").toString();
	    			    	JSONObject jsonObject4 = (JSONObject) jsonParser.parse(strViolationData);
	    			    	String strViolationTimeStamp = jsonObject4.get("timestamp").toString();
	    					Reporter.log("<font color='purple'> str"+strHostType+" Violation Time Stamp"+strSpotNumber+": "+strViolationTimeStamp+"</font>");
	    					return strViolationTimeStamp;
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}


	public String HTTPCONNECTIONS_GetParkingSessionConciergeValue(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
  		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String extendeddata = jsonObject.get("extended_data").toString();
	        	JSONObject jsonObject1 = (JSONObject) jsonParser.parse(extendeddata);
	    		String payload = jsonObject1.get("payload").toString();
	        	System.out.println("payload: "+payload);
	    		JSONArray arrPayload =  (JSONArray) jsonParser.parse(payload);
	    		for(int i=0; i<arrPayload.size(); i++)
	    		{
	    			Iterator<?> z = arrPayload.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(!innerMeter.isEmpty())
    					{
	    					String settings = innerMeter.get("session_info").toString();
	    					JSONObject jsonObject3 = (JSONObject) jsonParser.parse(settings);
	    					String strConcierge = jsonObject3.get("concierge").toString();
	    					Reporter.log("<font color='purple'> str"+strHostType+"Parking Session Concierge Value "+strSpotNumber+": "+strConcierge+"</font>");
	    					return strConcierge;
    					}
    					else
    					{
    						return "Add ERROR";
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}


	public void HTTPCONNECTIONS_StorePaymentReceiptValues(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

	    		//Get Payment Timestamp
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
        		for(int i=0; i<parkingsessiongroups.size(); i++)
        		{
        			Iterator<?> z = parkingsessiongroups.iterator();
        			while (z.hasNext())
        			{
					    JSONObject innerMeter = (JSONObject) z.next();
					    String strPaymentVariables = innerMeter.get("payments").toString();
					    if(strPaymentVariables != null)
					    {
						    try
						    {
						    	JSONArray arrPaymentVariables =  (JSONArray) jsonParser.parse(strPaymentVariables);
						    	int intNbrOfarrPaymentVariables = arrPaymentVariables.size();
						    	objDictionary.put("intNbrOfarrPaymentVariables",Integer.toString(intNbrOfarrPaymentVariables));
						    	Iterator<?> x = arrPaymentVariables.iterator();
						    	while (x.hasNext())
			        			{
			        				JSONObject paymentvalues = (JSONObject) x.next();
			        				String strPaymentTimestamp = paymentvalues.get("payment_timestamp").toString();
			        				String strTransactionNumber = paymentvalues.get("transaction_number").toString();
			        				String strViolationAmount = paymentvalues.get("amount").toString();
			        				objDictionary.put("strPaymentTimestamp", strPaymentTimestamp);
			        				objDictionary.put("strTransactionNumber", strTransactionNumber);
			        				objDictionary.put("strViolationAmount", strViolationAmount);
			        				break;
					 			}
						    }catch (Exception e){System.out.println(e);}
					    }
        			}
        		}
	    		//Get Initial Violation Timestamp
	    		String extendeddata = jsonObject.get("extended_data").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(extendeddata);
	    		String payload = jsonObject1.get("payload").toString();
	        	JSONArray arrPayload =  (JSONArray) jsonParser.parse(payload);
	    		for(int i=0; i<arrPayload.size(); i++)
	    		{
	    			Iterator<?> z = arrPayload.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(!innerMeter.isEmpty())
    					{
	    					String settings = innerMeter.get("session_info").toString();
	    					JSONObject jsonObject3 = (JSONObject) jsonParser.parse(settings);
	    					String strInitialViolationTimestamp = jsonObject3.get("initial_violation_timestamp").toString();
	    					System.out.println("strInitialViolationTimestamp: "+strInitialViolationTimestamp);
	    					objDictionary.put("strInitialViolationTimestamp", strInitialViolationTimestamp);
	    					Reporter.log("<font color='purple'> str"+strHostType+"Initial Violation TimeStamp"+strSpotNumber+": "+strInitialViolationTimestamp+"</font>");
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	}

	public String HTTPCONNECTIONS_StoreParkingId(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strParkingId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");//tUbPmX4osmUg3p6sgCoy
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null)
  		{
  			strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
  		} 
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		if(parkingsessiongroups.size() > 1)
	    		{
	    			//2541//2527
	    			//https://sentrylink.staging.sentry-link.com/admin/devices/2527/open_parking_sessions
	    			Reporter.log("https://sentrylink.staging.sentry-link.com/admin/devices/"+strMeterId+"/open_parking_sessions");
	    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
	    		}
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					strParkingId =  innerMeter.get("id").toString();
    					objDictionary.put("strParkingId", innerMeter.get("id").toString());
    					System.out.println("strParkingId: "+innerMeter.get("id").toString());
    					Reporter.log("<font color='purple'> strParkingId: "+innerMeter.get("id").toString()+"</font>");
    					//Store ParkTimestamp
    					String strParkTimestamp =  innerMeter.get("park_timestamp").toString();
    					objDictionary.put("strParkTimestamp", strParkTimestamp);
    					System.out.println("strParkTimestamp: "+strParkTimestamp);
    					Reporter.log("<font color='purple'> strParkTimestamp: "+strParkTimestamp+"</font>");
    					break;
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return strParkingId;
	}

	public String HTTPCONNECTIONS_StoreHandicapEnabled(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strIsHandicap = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");//tUbPmX4osmUg3p6sgCoy
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
//  		if(strJsonAdminToken == null){
  			strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
//  		}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  		}
        Reporter.log("Parking Session Url: "+strUrl);
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
		    	JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		    	String extendeddata = jsonObject.get("extended_data").toString();
		        System.out.println("extendeddata: "+extendeddata);
				JSONObject jsonObject1 = (JSONObject) jsonParser.parse(extendeddata);
	    		String payload = jsonObject1.get("payload").toString();
	        	System.out.println("payload: "+payload);
	    		JSONArray arrPayload =  (JSONArray) jsonParser.parse(payload);
	    		if(arrPayload.size() > 1)
	    		{
	    			//https://sentrylink.staging.sentry-link.com/admin/devices/2544/open_parking_sessions
	    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
	    		}
	    		for(int i=0; i<arrPayload.size(); i++)
	    		{
	    			Iterator<?> z = arrPayload.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(!innerMeter.isEmpty())
    					{
	    					String settings = innerMeter.get("session_info").toString();
	    					JSONObject jsonObject3 = (JSONObject) jsonParser.parse(settings);
	    					String strHandicap = jsonObject3.get("handicap").toString();
	    					System.out.println("strHandicap: "+strHandicap);
	    					objDictionary.put("strHandicap", strHandicap);
	    					Reporter.log("<font color='purple'> str"+strHostType+"Parking Spot Handicap"+strSpotNumber+": "+strHandicap+"</font>");
	    					return strHandicap;
    					}
    				}
	    		}
	        }
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return strIsHandicap;
	}
	public String HTTPCONNECTIONS_StoreParkingSessionId(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		if(parkingsessiongroups.size() > 1)
	    		{
	    			//https://sentrylink.staging.sentry-link.com/admin/devices/2535/open_parking_sessions
	    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
	    		}
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					objDictionary.put("str"+strHostType+"ParkingSessionSpot"+strSpotNumber, innerMeter.get("session_id").toString());
    					System.out.println("Parkin Session Id"+innerMeter.get("session_id").toString());
    					Reporter.log("<font color='purple'> str"+strHostType+"ParkingSessionSpot"+strSpotNumber+": "+innerMeter.get("session_id").toString()+"</font>");
    					return innerMeter.get("session_id").toString();
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}
	public String HTTPCONNECTIONS_GetSLViolationNumber(Map<String, String> objDictionary, String strHostType, String strSpotNumber, int intViolationNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String id = "";
		//String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		String strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
        {
        	case "QA":
        		strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
        		break;
	        case "SG":
	        	strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
	        	break;
	        case "PROD":
	        	strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
	        	break;
        }
        //Create Delete Request
        HttpGet httpGet = new HttpGet(strUrl);
        httpGet.addHeader(new BasicHeader("Accept", "application/json"));
        httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
        httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
        httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
        //Send Request
        try
        {
        	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
        	CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        	RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
        	httpGet.setConfig(localConfig);
        	CloseableHttpResponse response1 = httpClient.execute(httpGet);
        	if(response1.toString().contains("200"))
        	{
        		response1.getStatusLine().getStatusCode();
        		in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
        		StringBuffer sb = new StringBuffer("");
        		String l = "";
        		String nl = System.getProperty("line.separator");
        		while ((l = in.readLine()) !=null){sb.append(l + nl);}
        		in.close();
        		data = sb.toString();
        		response1.close();
        		JSONParser jsonParser = new JSONParser();
        		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
        		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
        		if(parkingsessiongroups.size() > 1)
        		{
        			//https://sentrylink.staging.sentry-link.com/admin/devices/2524/open_parking_sessions
        			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
			    }
        		for(int i=0; i<parkingsessiongroups.size(); i++)
        		{
        			Iterator<?> z = parkingsessiongroups.iterator();
        			while (z.hasNext())
        			{
					    JSONObject innerMeter = (JSONObject) z.next();
					    String strViolations = innerMeter.get("violations").toString();
					    if(strViolations != null)
					    {
						    try
						    {
						    	JSONArray arrOfViolations =  (JSONArray) jsonParser.parse(strViolations);
						    	int intNbrOfViolation = arrOfViolations.size();
						    	objDictionary.put("strNbrOfViolations",Integer.toString(intNbrOfViolation));
						    	Iterator<?> x = arrOfViolations.iterator();
						    	int ViolationCounter = 1;
			        			while (x.hasNext())
			        			{
			        				JSONObject violation = (JSONObject) x.next();
			 					  	if(intViolationNumber == ViolationCounter)
			        				{
			        					id = violation.get("id").toString();
			        					objDictionary.put("strViolationId", id);

			        					break;
					 				}
			        				ViolationCounter++;
			        			}
						    }catch (Exception e){System.out.println(e);}
					    }
        			}
        		}
        	}
        	else
        	{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	   }
	   catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	   return id;
	}
	public String HTTPCONNECTIONS_GetViolationNumberUsingParkingSessionId(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strViolationNumber = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		String strParkingSessionId = clsHttpConnections.HTTPCONNECTIONS_StoreParkingId(objDictionary, "Local", "1");
		//String strViolationNumber = HTTPCONNECTIONS_GetViolationId(objDictionary, strHostType, strSpotNumber);
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
		String data = null;
		String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
		switch (strEnvironment)
		{
			case "QA":
				strUrl = "https://"+strMunicipalitySubdomain+".quality.sentry-link.com/parking_sessions/"+strParkingSessionId+".json";
				break;
			case "SG":
				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/parking_sessions/"+strParkingSessionId+".json";
				break;
			case "PROD":
				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/parking_sessions/"+strParkingSessionId+".json";
				break;
		}
	   //Create Delete Request
	   HttpGet httpGet = new HttpGet(strUrl);
	   httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	   httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	   httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	   httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	   //Send Request
	   try
	   {
		   RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	       CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	       RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	       httpGet.setConfig(localConfig);
	       CloseableHttpResponse response1 = httpClient.execute(httpGet);
	       if(response1.toString().contains("200"))
	       {
	    	   response1.getStatusLine().getStatusCode();
	           in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	           StringBuffer sb = new StringBuffer("");
	           String l = "";
	           String nl = System.getProperty("line.separator");
	           while ((l = in.readLine()) !=null){sb.append(l + nl);}
	           in.close();
	           data = sb.toString();
	           response1.close();
	           JSONParser jsonParser = new JSONParser();
	           JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	           JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("violations");
//	           if(parkingsessiongroups.size() > 1)
//	           {
//	        	   //https://sentrylink.staging.sentry-link.com/admin/devices/2544/open_parking_sessions
//	        	   clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
//	           }
	           for(int i=0; i<parkingsessiongroups.size(); i++)
	           {
	        	   Iterator<?> z = parkingsessiongroups.iterator();
	        	   while (z.hasNext())
	        	   {
	        		   JSONObject innerMeter = (JSONObject) z.next();
	        		   if(innerMeter.get("violation_number")== null)
	        		   {
	        			   clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Violation Number was Null","Local");
	        		   }
	        		   else
	        		   {
	        			   strViolationNumber = innerMeter.get("violation_number").toString();
	        			   objDictionary.put("strViolationNumber", strViolationNumber);
	        		   }
	        		   if(strEnvironment.equals("PROD"))
	        		   {
	        			   break;
	        		   }
	        	   }
	           }
	       }
	       else
	       {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	   }
	   catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	   Reporter.log("The Violation Number: "+strViolationNumber);
	   return strViolationNumber;
	}
	public String HTTPCONNECTIONS_StoreParkingSpotState(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		Reporter.log("StoreParkingSpotState -2");
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null)
  		{
  			strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);
  		}
  		Reporter.log("StoreParkingSpotState -1");
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else
		{
			strMeterId = Json_MeterId(objDictionary, strHostType);
		}
		Reporter.log("strMeterId "+strMeterId);
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://"+strMunicipalitySubdomain+".quality.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	System.out.println("StoreParkingSpotState 0");
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        System.out.println("StoreParkingSpotState 1A");
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String extendeddata = jsonObject.get("extended_data").toString();
	        	System.out.println("extendeddata: "+extendeddata);
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(extendeddata);
	    		String payload = jsonObject1.get("payload").toString();
	        	System.out.println("payload: "+payload);
	    		JSONArray arrPayload =  (JSONArray) jsonParser.parse(payload);
	    		if(arrPayload.size() > 1)
	    		{
	    			//https://sentrylink.staging.sentry-link.com/admin/devices/2544/open_parking_sessions
	    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
	    		}
	    		for(int i=0; i<arrPayload.size(); i++)
	    		{
	    			Iterator<?> z = arrPayload.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					if(!innerMeter.isEmpty())
    					{
	    					String settings = innerMeter.get("session_info").toString();
	    					JSONObject jsonObject3 = (JSONObject) jsonParser.parse(settings);
	    					String strParkingSpotState = jsonObject3.get("parking_spot_state").toString();
	    					System.out.println("strParkingSpotState: "+strParkingSpotState);
	    					Reporter.log("<font color='purple'> str"+strHostType+"Parking Spot State"+strSpotNumber+": "+strParkingSpotState+"</font>");
	    					if(strParkingSpotState.equals("PARKING_STATE_EMPTY"))
	    					{
	    						System.out.println("MIH");
	    					}

	    					return strParkingSpotState;
    					}
    					else
    					{
    						return "Add ERROR";
    					}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e)
	    {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}
	public String HTTPCONNECTIONS_StoreCoinErrorStatusType(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		String extendeddata = jsonObject.get("extended_data").toString();
	    		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(extendeddata);
	    		String payload = jsonObject1.get("payload").toString();
	    		JSONArray arrPayload =  (JSONArray) jsonParser.parse(payload);
//	    		if(arrPayload.size() > 1)
//	    		{
//	    			//https://sentrylink.staging.sentry-link.com/admin/devices/2544/open_parking_sessions
//	    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
//	    		}
	    		for(int i=0; i<arrPayload.size(); i++)
	    		{
	    			Iterator<?> z = arrPayload.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					String settings = innerMeter.get("error_status").toString();
    					JSONObject jsonObject3 = (JSONObject) jsonParser.parse(settings);
    					String strCoinErrorStatus = jsonObject3.get("coin_error_status").toString();
    					JSONArray arrCoinErrorStatus =  (JSONArray) jsonParser.parse(strCoinErrorStatus);
    					for(int j=0; i<arrCoinErrorStatus.size(); j++)
    		    		{
    		    			Iterator<?> x = arrCoinErrorStatus.iterator();
    	    				while (x.hasNext())
    	    				{
    	    					JSONObject innerCoinErrorStatus = (JSONObject) x.next();
    	    					String strType = innerCoinErrorStatus.get("type").toString();
    	    					Reporter.log("<font color='purple'> str"+strHostType+"Coin Error Status Type"+strSpotNumber+": "+strType+"</font>");
    	    					return strType;
    	    				}
    		    		}
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}
	public void HTTPCONNECTIONS_ValidateParkingSessionId(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        String strExpectedParkingSessionId = objDictionary.get("str"+strHostType+"ParkingSessionSpot"+strSpotNumber);
		if (strExpectedParkingSessionId != null)
		{
	        switch (strEnvironment)
	  		{
	  			case "QA":
	  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
	  				break;
	  			case "SG":
	  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
	  				//https://subdomain.domain/api/v1/devices/:id/current_rates.json
	  				break;
	  			case "PROD":
	  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
	  				break;
	  		}
		    //Create Delete Request
		    HttpGet httpGet = new HttpGet(strUrl);
		    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
		    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
		    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
		    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
		    //Send Request
		    try
		    {
		    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
		        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
		        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
		        httpGet.setConfig(localConfig);
		        CloseableHttpResponse response1 = httpClient.execute(httpGet);
		        if(response1.toString().contains("200"))
		        {
		        	response1.getStatusLine().getStatusCode();
		            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
		            StringBuffer sb = new StringBuffer("");
		            String l = "";
		            String nl = System.getProperty("line.separator");
		            while ((l = in.readLine()) !=null){sb.append(l + nl);}
		            in.close();
		            data = sb.toString();
		            response1.close();
		            JSONParser jsonParser = new JSONParser();
		    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
		    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
		    		if(parkingsessiongroups.size() > 1)
		    		{
		    			//https://sentrylink.staging.sentry-link.com/admin/devices/2544/open_parking_sessions
		    			clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The Meter Id ("+strMeterId+") has multiple parking session opened","Local");
		    		}
		    		for(int i=0; i<parkingsessiongroups.size(); i++)
		    		{
		    			Iterator<?> z = parkingsessiongroups.iterator();
	    				while (z.hasNext())
	    				{
	    					JSONObject innerMeter = (JSONObject) z.next();
	    					if(!innerMeter.get("session_id").toString().equals(strExpectedParkingSessionId))
	    					{clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, "The expected parking session ("+strExpectedParkingSessionId+") did not match the current ("+innerMeter.get("session_id").toString()+")","Local");}
	    					else
	    					{Reporter.log("The current parking sesssion id ("+innerMeter.get("session_id").toString()+") matched the expected parking session id ("+strExpectedParkingSessionId+")");}
	    					break;
	    				}
		    		}
		    	}
		        else
		        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
		    }
		    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
		}
	}
	public void HTTPCONNECTIONS_GetParkingSessionId(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Stopwatch timer = Stopwatch.createStarted();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";
		String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				//strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?include_extended_data=true;spot_identifier=SPOT_"+strSpotNumber;
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println(innerMeter.get("session_id").toString());
    					Reporter.log("<font color='purple'>"+strHostType+" Parking Session Spot "+strSpotNumber+": "+innerMeter.get("session_id").toString()+"</font>");
    					Reporter.log("Method ("+strMethodName+") took: " + timer.stop());
    					Reporter.log("Method ("+strMethodName+") took: " + timer.stop());
    					break;
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	}
	public String HTTPCONNECTIONS_GetSpotEstimatedTimeRemaining(Map<String, String> objDictionary, String strHostType, String strSpotNumber)
	{
		String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Stopwatch timer = Stopwatch.createStarted();
		CommonWeb clsCommonWeb = new CommonWeb();
		HttpConnections clsHttpConnections = new HttpConnections();
		String strMethondName = new Object(){}.getClass().getEnclosingMethod().getName();
		//Get Dictionary Values
		String strEnvironment = objDictionary.get("strEnvironment");
		String strVirtualMeter = objDictionary.get("strVirtualMeter");
		if(strVirtualMeter == null){strVirtualMeter= "False";}
		//Function Variables
		String strUrl = "";String strMeterId = "";
		String strJsonAdminToken = objDictionary.get("strJsonAdminToken");
		String strAdminUser = clsCommonWeb.SENTRYLINK_GetUserName(objDictionary, "admin").toLowerCase();
  		if(strJsonAdminToken == null){strJsonAdminToken = clsHttpConnections.StoreJsonAdminToken(objDictionary);}
  		//Get Meter Id
		if(strVirtualMeter.equals("True")){strMeterId = Json_VirtualMeterId(objDictionary);}
		else{strMeterId = Json_MeterId(objDictionary, strHostType);}
		BufferedReader in = null;
        String data = null;
        String strMunicipalitySubdomain = objDictionary.get("strMunicipalitySubdomain");
        switch (strEnvironment)
  		{
  			case "QA":
  				strUrl = "http://sentrylink.quality.sentry-link.com/api/v1/meters/"+strMeterId+"/current_rates.json";
  				break;
  			case "SG":
  				strUrl = "https://"+strMunicipalitySubdomain+".staging.sentry-link.com/api/v1/devices/"+strMeterId+"/parking_sessions.json?spot_identifier=SPOT_"+strSpotNumber;
  				break;
  			case "PROD":
  				strUrl = "https://"+strMunicipalitySubdomain+".mpspark.com/api/v1/devices/"+strMeterId+"/current_rates.json";
  				break;
  		}
	    //Create Delete Request
	    HttpGet httpGet = new HttpGet(strUrl);
	    httpGet.addHeader(new BasicHeader("Accept", "application/json"));
	    httpGet.addHeader(new BasicHeader("Content-Type", "application/json"));
	    httpGet.addHeader(new BasicHeader("X-User-Token",strJsonAdminToken));
	    httpGet.addHeader(new BasicHeader("X-User-Email", strAdminUser));
	    //Send Request
	    try
	    {
	    	RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
	        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD).build();
	        httpGet.setConfig(localConfig);
	        CloseableHttpResponse response1 = httpClient.execute(httpGet);
	        if(response1.toString().contains("200"))
	        {
	        	response1.getStatusLine().getStatusCode();
	            in = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String l = "";
	            String nl = System.getProperty("line.separator");
	            while ((l = in.readLine()) !=null){sb.append(l + nl);}
	            in.close();
	            data = sb.toString();
	            response1.close();
	            JSONParser jsonParser = new JSONParser();
	    		JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
	    		JSONArray parkingsessiongroups= (JSONArray) jsonObject.get("parking_sessions");
	    		for(int i=0; i<parkingsessiongroups.size(); i++)
	    		{
	    			Iterator<?> z = parkingsessiongroups.iterator();
    				while (z.hasNext())
    				{
    					JSONObject innerMeter = (JSONObject) z.next();
    					System.out.println("Estimated Time Remaining: "+innerMeter.get("estimated_time_remaining").toString());
    					Reporter.log("<font color='purple'>"+strHostType+" Meter Spot ("+strSpotNumber+") Estimated Time Remaining: "+innerMeter.get("estimated_time_remaining").toString()+"</font>");
    					Reporter.log("<font color='#5533ff'>Method ("+strMethodName+") took: " + timer.stop()+"</font>");
    					return innerMeter.get("estimated_time_remaining").toString();
    				}
	    		}
	    	}
	        else
	        {clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, response1.toString(),"Local");}
	    }
	    catch (Exception e){clsCommonWeb.UpdateErrorMessageWithPivotalData(objDictionary,null, e.toString()+"_"+strMethondName,"Local");}
	    return "";
	}
}
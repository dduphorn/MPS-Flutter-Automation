package AutomationCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Reporter;

//import com.google.gson.Gson;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ImgurClient_Old
{
	private static ImgurClient_Old instance;
//	Old
//  private static final String IMGUR_CLIENT_ID = "7f40689fb675d30";
//  private static final String IMGUR_CLIENT_SECRET = "2c54c74a5b1b54d9fcb4813b05a16bea21a64a17";

//  private static final String IMGUR_CLIENT_ID = "3cc5843b98650c2";
//  private static final String IMGUR_CLIENT_SECRET = "033caef8f10e77bfb8df13651e0d4c5bfd9d2587";

//  private static final String REFRESH_TOKEN = "0dd06309632c9f8d2da40f23bef407a57f1ebc3f";
//  private static final String MASHAPE_KEY = "a3nR5VhKE7mshFmqyu55wtlwCORWp1Wipq0jsn0ys4SCwSqkDy";
	private static String accessToken = "";
	private static String lastUrl = null;

//Imgur Images
//1. Navigate to: https://imgur.com/
//2. User Name: mpsautomationimages
//3. Password: firesale

//Rapid API
//1. Navigate to: https://rapidapi.com/hub
//2. User Name: accounts@mpspark.com
//3. Password: WQisja832Lsk!jf

  public static ImgurClient_Old getInstance()
  {
	  System.out.println("[ImgurClient] obtaining instance");
	  if (instance == null)
	  {
		  org.apache.http.client.HttpClient client = org.apache.http.impl.client.HttpClientBuilder.create().disableCookieManagement().build();
	      com.mashape.unirest.http.Unirest.setHttpClient(client);
	      instance = new ImgurClient_Old();
	      try
	      {

	    	  HttpResponse<JsonNode> jsonResponse = com.mashape.unirest.http.Unirest.post("https://api.imgur.com/oauth2/token")
		  			 .field("refresh_token", "bb086bea8ae7af98d14a0505b9c8485f256d9c92")
		  			 .field("client_id", "3cc5843b98650c2")
		  			 .field("client_secret", "033caef8f10e77bfb8df13651e0d4c5bfd9d2587")
		  			 .field("grant_type", "refresh_token")
		  			 .asJson();
		  	 accessToken = jsonResponse.getBody().getObject().getString("access_token");
	    	 System.out.println(accessToken); // verify access token was acquired correctly
	      }
	      catch (UnirestException e)
	      {System.out.println("[ImgurClient] something went wrong while trying to login to Imgur: " + e.toString());}
	  }
	  System.out.println("[ImgurClient] Login success.");
	  return instance;
  }
  public void CycleThroughAlbumImages(String strAlbumName)
  {
	  HttpResponse<JsonNode> jsonResponse;//mpstestautomation //firesale
	  try
	  {
		  jsonResponse = com.mashape.unirest.http.Unirest
    			.get("https://imgur-apiv3.p.rapidapi.com/3/album/jubd3qO/images")
    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
    			.header("Authorization", "Bearer " + accessToken).asJson();
		  String strStatus = jsonResponse.getStatusText();
		  if(strStatus.toString().contains("OK"))
		  {System.out.println("Images Exist In This Exists in Album");}
		  else
		  {System.out.println("The Get Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
		  try
		  {
			  String strResponse = jsonResponse.getBody().toString();
			  JSONParser jsonParser = new JSONParser();
			  JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
	          JSONArray jaImages= (JSONArray) jsonObject.get("data");
	          Iterator<?> i = jaImages.iterator();
	          // Cycle through the images and delete them.
	          while (i.hasNext())
	          {
	        	  JSONObject innerObj = (JSONObject) i.next();
	        	  //System.out.println(innerObj.get("deletehash"));
	        	  //String strImageDeleteHash = innerObj.get("deletehash").toString();
	        	  System.out.println(innerObj.get("id"));
	        	  String strImageId = innerObj.get("id").toString();
	        	  System.out.println(innerObj.get("title"));
	        	  String strImageTitle = "";
	        	  if(innerObj.get("title") != null) {strImageTitle = innerObj.get("title").toString();}
	        	  try
				  {
		        	  //Delete Image From Album
		        	  jsonResponse = com.mashape.unirest.http.Unirest
				    			.delete("https://imgur-apiv3.p.rapidapi.com/3/image/"+strImageId)
				    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
				    			.header("Authorization", "Bearer " + accessToken).asJson();
		        	  strStatus = jsonResponse.getStatusText();
		    		  if(strStatus.toString().contains("OK"))
		    		  {System.out.println("The image ("+strImageTitle+") was delected from imgur");}
					  else
					  {System.out.println("The Delete Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
				  }
	        	  catch (UnirestException e)
				  {System.out.println("Something failed when attempting to delete an imgur image");}
	          }
		  }
		  catch (Exception e)
		  {System.out.println("Something failed when attempting to parser the imgur images");}
	  }
	  catch (UnirestException e)
	  {System.out.println("Something failed when attempting to get an imgur images");}
  }


  public void DeleteNonAlbumImages()
  {
	  //This will delete all images, so don't use unless your clearly all pics from imgur
//	  HttpResponse<JsonNode> jsonResponse;
//	  try
//	  {
//		  jsonResponse = com.mashape.unirest.http.Unirest
//    			.get("https://imgur-apiv3.p.rapidapi.com/3/account/mpsautomation/images/count")
//    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
//    			.header("Authorization", "Bearer " + accessToken).asJson();
//		  String strStatus = jsonResponse.getStatusText();
//		  if(strStatus.toString().contains("OK")){System.out.println("Images Count json request was successful");}
//		  else{System.out.println("The Image Count json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
//		  JSONParser jsonParser = new JSONParser();
//		  String strCountResponse = ((JsonNode)jsonResponse.getBody()).toString();
//		  JSONObject jsonObject = (JSONObject) jsonParser.parse(strCountResponse);
//		  String strNumberOfImages = jsonObject.get("data").toString();
//		  int intDeletedImageCounter = 0;
//		  do
//		  {
//			  try
//			  {
//				  jsonResponse = com.mashape.unirest.http.Unirest
//		    			.get("https://imgur-apiv3.p.rapidapi.com/3/account/mpsautomation/images/ids")
//		    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
//		    			.header("Authorization", "Bearer " + accessToken).asJson();
//				  strStatus = jsonResponse.getStatusText();
//				  if(strStatus.toString().contains("OK"))
//				  {System.out.println("Images Exist In This Exists in Album");}
//				  else
//				  {System.out.println("The Get Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
//				  try
//				  {
//					  String strResponse = ((JsonNode)jsonResponse.getBody()).toString();
//					  jsonObject = (JSONObject) jsonParser.parse(strResponse);
//			          JSONArray jaImages= (JSONArray) jsonObject.get("data");
//
//			          for(int i=0; i<jaImages.size(); i++)
//			          {
//			        	  String strImageId = jaImages.get(i).toString();
//			        	  try
//						  {
//				        	  //Delete Image From Album
//				        	  jsonResponse = com.mashape.unirest.http.Unirest
//						    			.delete("https://imgur-apiv3.p.rapidapi.com/3/image/"+strImageId)
//						    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
//						    			.header("Authorization", "Bearer " + accessToken).asJson();
//				        	  strStatus = jsonResponse.getStatusText();
//				    		  if(strStatus.toString().contains("OK"))
//				    		  {
//				    			  System.out.println("The image ("+strImageId+") was deleted from imgur");
//				    			  intDeletedImageCounter++;
//				    		  }
//							  else
//							  {System.out.println("The Delete Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
//						  }
//			        	  catch (UnirestException e)
//						  {System.out.println("Something failed when attempting to delete an imgur image");}
//			          }
//				  }
//				  catch (Exception e)
//				  {System.out.println("Something failed when attempting to parser the imgur images");}
//			  }
//			  catch (UnirestException e)
//			  {System.out.println("Something failed when attempting to get an imgur images");}
//			  System.out.println(Integer.toString(intDeletedImageCounter));
//		  } while (!strNumberOfImages.equals(Integer.toString(intDeletedImageCounter)));
//	  }
//	  catch (Exception e)
//	  {System.out.println("Something failed when attempting to parser the imgur images");}
  }
  public void DeleteAlbumImages()
  {
	  HttpResponse<JsonNode> jsonResponse;
	  try
	  {
		  jsonResponse = com.mashape.unirest.http.Unirest
    			.get("https://imgur-apiv3.p.rapidapi.com/3/album/jubd3qO/images")
    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
    			.header("Authorization", "Bearer " + accessToken).asJson();
		  String strStatus = jsonResponse.getStatusText();
		  if(strStatus.toString().contains("OK"))
		  {System.out.println("Images Exist In This Exists in Album");}
		  else
		  {System.out.println("The Get Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
		  try
		  {
			  String strResponse = jsonResponse.getBody().toString();
			  JSONParser jsonParser = new JSONParser();
			  JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
	          JSONArray jaImages= (JSONArray) jsonObject.get("data");
	          Iterator<?> i = jaImages.iterator();
	          // Cycle through the images and delete them.
	          while (i.hasNext())
	          {
	        	  JSONObject innerObj = (JSONObject) i.next();
	        	  System.out.println(innerObj.get("id"));
	        	  String strImageId = innerObj.get("id").toString();
	        	  System.out.println(innerObj.get("title"));
	        	  String strImageTitle = "";
	        	  if(innerObj.get("title") != null) {strImageTitle = innerObj.get("title").toString();}
	        	  try
				  {
		        	  //Delete Image From Album
		        	  jsonResponse = com.mashape.unirest.http.Unirest
				    			.delete("https://imgur-apiv3.p.rapidapi.com/3/image/"+strImageId)
				    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
				    			.header("Authorization", "Bearer " + accessToken).asJson();
		        	  strStatus = jsonResponse.getStatusText();
		    		  if(strStatus.toString().contains("OK"))
		    		  {System.out.println("The image ("+strImageTitle+") was delected from imgur");}
					  else
					  {System.out.println("The Delete Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
				  }
	        	  catch (UnirestException e)
				  {System.out.println("Something failed when attempting to delete an imgur image");}
	          }
		  }
		  catch (Exception e)
		  {System.out.println("Something failed when attempting to parser the imgur images");}
	  }
	  catch (UnirestException e)
	  {System.out.println("Something failed when attempting to get an imgur images");}
  }

  public String GetAlbumHash(String strAlbumName)
  {
	  String strAlbumHash = "";
	  HttpResponse<JsonNode> jsonResponse;
	  try
	  {
		  jsonResponse = com.mashape.unirest.http.Unirest
    		.get("https://imgur-apiv3.p.rapidapi.com/3/account/mpsautomation/albums/ids")
    		.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
    		.header("Authorization", "Bearer " + accessToken).asJson();
		  System.out.println(jsonResponse.getStatus());
		  String strStatus = jsonResponse.getStatusText();
		  if(strStatus.toString().contains("OK"))
		  {System.out.println("Album Exists for mpsautomation account");}
		  else
		  {System.out.println("The Get Albums ids json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
		  try
		  {
			  String strResponse = jsonResponse.getBody().toString();
			  JSONParser jsonParser = new JSONParser();
			  JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
	          JSONArray jaAlbumHashes= (JSONArray) jsonObject.get("data");
	          for (Object jaAlbumHash : jaAlbumHashes) {
	        	  strAlbumHash = jaAlbumHash.toString();
	        	  jsonResponse = com.mashape.unirest.http.Unirest
	          			.get("https://imgur-apiv3.p.rapidapi.com/3/account/mpsautomation/album/"+strAlbumHash)
	          			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
	          			.header("Authorization", "Bearer " + accessToken).asJson();
	      		  strStatus = jsonResponse.getStatusText();
	      		  if(strStatus.toString().contains("OK")){System.out.println("Images Exist In This Exists in Album");}
	      		  else{System.out.println("The Get Image json request unexpectedly returned the status ("+jsonResponse.getStatus()+")");}
	      		  String strResponse2 = jsonResponse.getBody().toString();
	      		  JSONObject jsonAlbumObject = (JSONObject) jsonParser.parse(strResponse2);
	      		  String strAlbumDetails = jsonAlbumObject.get("data").toString();
	      		  JSONObject jsonAlbumDetails = (JSONObject) jsonParser.parse(strAlbumDetails);
	      		  String strImageTitle = jsonAlbumDetails.get("title").toString();
	      		  if(strImageTitle.equals(strAlbumName))
	      		  {return strAlbumHash;}
	          }
	      }
		  catch (Exception e){System.out.println("Something failed when attempting to delete an album data");}
	  }
	  catch (UnirestException e){System.out.println("Something failed when attempting to get Album ids");}
	  return strAlbumHash;
  }
  public String GetAllAlbumnImageIds()//Not Done, switched to Delete
  {
//	  if(file != null)
//	  {
//		  if(file.getName() != null)
//		  {
//			  System.out.println("[ImgurClient] Uploading image: " + file.getName());
			  HttpResponse<JsonNode> jsonResponse;//mpstestautomation //firesale
			  try
			  {
				  //X-RapidAPI-Host: "https://imgur-apiv3.p.mashape.com/3/image"
				  //X-RapidAPI-Key/X-Mashape-Key: tl6wK9MhFTmshqiriJ51qy0e4PQKp1jJ1a8jsnunyHl2yfKxEX - Original
				  //Directions to this Key
				  //1. Navigate to: https://rapidapi.com/
				  //2. User: darin@mpspark.com
				  //3. Password: firesale
				  //4. Expand My Apps
				  //5. Click Security: The Mashape-Key Displays
				  jsonResponse = com.mashape.unirest.http.Unirest
		    			.get("https://imgur-apiv3.p.rapidapi.com/3/album/jubd3qO/images")//https://imgur.com/yrITYj3
		    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
		    			.header("Authorization", "Bearer " + accessToken).asJson();
		    			//.field("album", "mpstestautomation")
//		    			.field("image", file)
//		    			.field("title", name).asJson();

			  }
			  catch (UnirestException e)
			  {
				  System.out.println("[ImgurClient] Upload failed: " + e.toString());
				  lastUrl = null;
				  return "NOIMAGE";
			  }
			  System.out.println(jsonResponse.getStatus());
			  if(jsonResponse.toString().contains("200"))
			  {
				  System.out.println("MIH");
			  }
			  else
			  {
				  System.out.println("MIH");
			  }
			  try
			  {
				  String strResponse = jsonResponse.getBody().toString();

				  JSONParser jsonParser = new JSONParser();
				  JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
		          JSONArray jaImages= (JSONArray) jsonObject.get("data");
		          System.out.println(jaImages.size());
		          lastUrl = jsonResponse.getBody().getObject().getJSONObject("data").toString();
				  System.out.println("MIH");
			  }
			  catch (Exception e)
			  {
				  System.out.println(e);
			  }
			  Reporter.log("[ImgurClient] Upload success. Link to image: " + lastUrl);
			  System.out.println("[ImgurClient] Upload success. Link to image: " + lastUrl);
//		  }
//	  }
	  return lastUrl;
  }

  public String IMGUR_CopyImageFromImgurToHealthKiosk(Map<String, String> objDictionary, String strImageId)
  {
//	  if(file != null)
//	  {
//		  if(file.getName() != null)
//		  {
//			  System.out.println("[ImgurClient] Uploading image: " + file.getName());
			  HttpResponse<JsonNode> jsonResponse;//mpstestautomation //firesale
			  try
			  {
				  //X-RapidAPI-Host: "https://imgur-apiv3.p.mashape.com/3/image"
				  //X-RapidAPI-Key/X-Mashape-Key: tl6wK9MhFTmshqiriJ51qy0e4PQKp1jJ1a8jsnunyHl2yfKxEX - Original
				  //Directions to this Key
				  //1. Navigate to: https://rapidapi.com/
				  //2. User: darin@mpspark.com
				  //3. Password: firesale
				  //4. Expand My Apps
				  //5. Click Security: The Mashape-Key Displays
				  jsonResponse = com.mashape.unirest.http.Unirest
		    			.get("https://imgur-apiv3.p.rapidapi.com/3/account/mpsautomation/image/"+strImageId)
		    			.header("X-Mashape-Key", "289a91ef05msh7be61205ba00da5p160b81jsnd42081c64055")
		    			.header("Authorization", "Bearer " + accessToken).asJson();
		    			//.field("album", "mpstestautomation")
//		    			.field("image", file)
//		    			.field("title", name).asJson();
				  System.out.println(jsonResponse.getStatus());
				  String strStatus = jsonResponse.getStatusText();
				  if(strStatus.toString().contains("OK")){System.out.println("Image Existed for mpsautomation account");}
				  else{System.out.println("The Get Image Hash json request unexpectedly returned the status ("+strStatus+")");}
				  //Get image url from json
				  String strResponse = jsonResponse.getBody().toString();
				  JSONParser jsonParser = new JSONParser();
				  JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
				  String strNotificationValues = jsonObject.get("data").toString();
				  JSONObject jsonObject2 = (JSONObject) jsonParser.parse(strNotificationValues);
				  String strImageUrl = jsonObject2.get("link").toString();
				  //Save Imgur Image To Desktop
				  saveImgurImageToDesktop(strImageUrl, "/Users/darinduphorn/git/TestAutomation/ImgurImages/TestImage.jpg");
				  //Copy imgur Image from Desktop to Sentry Health
				  CopyImageToSentryHealth(objDictionary);
				  //Process the Image against the
				  //SentryHealth clsHealth = new SentryHealth();
				  //clsHealth.SENTRYHEALTH_ExecutePythonScriptAgainstMeter( objDictionary,strHost,"testauto_simulate_health_session.py  /tmp/TestImage.jpg");


				  System.out.println("MIH");



			  }
			  catch (Exception e)
			  {
				  System.out.println(e);
			  }

//			  try
//			  {
//				  String strResponse = ((JsonNode)jsonResponse.getBody()).toString();
//
//				  JSONParser jsonParser = new JSONParser();
//				  JSONObject jsonObject = (JSONObject) jsonParser.parse(strResponse);
//		          JSONArray jaImages= (JSONArray) jsonObject.get("data");
//		          System.out.println(jaImages.size());
//		          lastUrl = ((JsonNode)jsonResponse.getBody()).getObject().getJSONObject("data").toString();
//				  System.out.println("MIH");
//			  }
//			  catch (Exception e)
//			  {
//				  System.out.println(e);
//			  }
//			  Reporter.log("[ImgurClient] Upload success. Link to image: " + lastUrl);
//			  System.out.println("[ImgurClient] Upload success. Link to image: " + lastUrl);
//		  }
//	  }
	  return "";

  }

  public static void saveImgurImageToDesktop(String imageUrl, String destinationFile) throws IOException
  {
	  URL url = new URL(imageUrl);
	  InputStream is = url.openStream();
	  OutputStream os = new FileOutputStream(destinationFile);
	  byte[] b = new byte[2048];
	  int length;
	  while ((length = is.read(b)) != -1) {os.write(b, 0, length);}
	  is.close();
	  os.close();
  }
  public void CopyImageToSentryHealth(Map<String, String> objDictionary) throws Exception
  {
	  String strMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	  String strPassword = "firesale";
	  File directory = new File(".");
	  String strPath = directory.getCanonicalPath() +"/ImgurImages";
	  File file = new File(strPath);
	  if (!file.exists())
	  {
		  if (file.mkdir()) {System.out.println("Directory is created!");}
		  else {System.out.println("Failed to create directory!");}
	  }
	  String strFullMeterLogsPath = strPath+"/TestImage.jpg";
	  //Get StrMeterUser
	  String strHost = objDictionary.get("strHost");
	  String strSudo = "";
	  System.out.println("sudo sshpass -p "+strPassword+" scp -r "+strFullMeterLogsPath +" seco@"+strHost+":/tmp");
	  String[] command1 = {"sh","-c",strSudo+"sshpass -p "+strPassword+" scp -r "+strFullMeterLogsPath +" seco@"+strHost+":/tmp"};
	  try
	  {
		  Process proc = Runtime.getRuntime().exec(command1);
		  BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		  String s = null;
		  String strErrorMessage = "";
		  while ((s = stdError.readLine()) != null) {strErrorMessage = s;}
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
		  //UpdateErrorMessageWithPivotalData(objDictionary,null,e+"-host ("+strLocalHost+")-"+strMethodName);
	  }
  }

  public void StoreSentryHealthDumpStackValuesInDictionary(Map<String, String> objDictionary)
  {
		JSch jsch = new JSch();
		String strMeterUser = "seco";
		String strPassword = objDictionary.get("strUniquePassword");
		String strHost = "10.10.100.212";
	  	String strPythonScriptExisted = "False";
	  	int port=22;
	    try
	    {
	    	Session session = jsch.getSession(strMeterUser, strHost, port);
		    session.setPassword(strPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("testauto_dump_health_sessions.py");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        //Get Begun Value
	        String s = "";int c;
	        while((c = in.read()) != -1) {s += (char)c;}
	        String[] lines = s.split("\n");
	        System.out.println(Arrays.toString(lines));
	        strPythonScriptExisted = "True";
	        for (String line : lines)
	        {
	        	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss.SSS");
				dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

				String strSessionBegun = line.substring(line.indexOf("SessB,")+6, line.indexOf("|SessE"));
				objDictionary.put("strSessionBegun", strSessionBegun);
        		Reporter.log("The strSessionBegun value equaled ("+strSessionBegun+")-UTC Time:"+dateFormatGmt.format(new Date()));
        		String strSessionEnd = line.substring(line.indexOf("SessE,")+6, line.indexOf("|CallB"));
				objDictionary.put("strSessionEnd", strSessionEnd);
        		Reporter.log("The strSessionEnd value equaled ("+strSessionEnd+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strPhoneCallBegin = line.substring(line.indexOf("CallB,")+6, line.indexOf("|CallE"));
				objDictionary.put("strPhoneCallBegin", strPhoneCallBegin);
        		Reporter.log("The strPhoneCallBegin value equaled ("+strPhoneCallBegin+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strPhoneCallEnd = line.substring(line.indexOf("CallE,")+6, line.indexOf("|CallR"));
				objDictionary.put("strPhoneCallEnd", strPhoneCallEnd);
        		Reporter.log("The strPhoneCallEnd value equaled ("+strPhoneCallEnd+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strPhoneCallReason = line.substring(line.indexOf("CallR,")+6, line.indexOf("|CallS"));
				objDictionary.put("strPhoneCallReason", strPhoneCallReason);
        		Reporter.log("The strPhoneCallReason value equaled ("+strPhoneCallReason+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strPhoneCallStatus = line.substring(line.indexOf("CallS,")+6, line.indexOf("|ConfB"));
				objDictionary.put("strPhoneCallStatus", strPhoneCallStatus);
        		Reporter.log("The strPhoneCallStatus value equaled ("+strPhoneCallStatus+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strConferenceCallBegin = line.substring(line.indexOf("ConfB,")+6, line.indexOf("|ConfE"));
				objDictionary.put("strConferenceCallBegin", strConferenceCallBegin);
        		Reporter.log("The strConferenceCallBegin value equaled ("+strConferenceCallBegin+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strConferenceCallEnd = line.substring(line.indexOf("ConfE,")+6, line.indexOf("|ConfR"));
				objDictionary.put("strConferenceCallEnd", strConferenceCallEnd);
        		Reporter.log("The strConferenceCallEnd value equaled ("+strConferenceCallEnd+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strConferenceCallReason = line.substring(line.indexOf("ConfR,")+6, line.indexOf("|ConfS"));
				objDictionary.put("strConferenceCallReason", strConferenceCallReason);
        		Reporter.log("The strConferenceCallReason value equaled ("+strConferenceCallReason+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strConferenceCallStatus = line.substring(line.indexOf("ConfS,")+6, line.indexOf("|Temp"));
				objDictionary.put("strConferenceCallStatus", strConferenceCallStatus);
        		Reporter.log("The strConferenceCallStatus value equaled ("+strConferenceCallStatus+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strTemp = line.substring(line.indexOf("Temp,")+5, line.indexOf("|TTH"));
				objDictionary.put("strTemp", strTemp);
        		Reporter.log("The strTemp value equaled ("+strTemp+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strTempThreshold = line.substring(line.indexOf("TTH,")+4, line.indexOf("|San"));
				objDictionary.put("strTempThreshold", strTempThreshold);
        		Reporter.log("The strTempThreshold value equaled ("+strTempThreshold+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strSanitized = line.substring(line.indexOf("San,")+4, line.indexOf("|AC"));
				objDictionary.put("strSanitized", strSanitized);
        		Reporter.log("The strSanitized value equaled ("+strSanitized+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strAccessControl = line.substring(line.indexOf("AC,")+3, line.indexOf("|PB"));
				objDictionary.put("strAccessControl", strAccessControl);
        		Reporter.log("The strAccessControl value equaled ("+strAccessControl+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strBeginPicture = line.substring(line.indexOf("PB,")+3, line.indexOf("|PBid"));
				objDictionary.put("strBeginPicture", strBeginPicture);
        		Reporter.log("The strBeginPicture value equaled ("+strBeginPicture+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strEndPictureId = line.substring(line.indexOf("PEid,")+5, line.indexOf("|FRbb"));
				objDictionary.put("strEndPictureId", strEndPictureId);
        		Reporter.log("The strEndPictureId value equaled ("+strEndPictureId+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strFacialRecoBoundingBox = line.substring(line.indexOf("FRbb,")+5, line.indexOf("|FRuid"));
				objDictionary.put("strFacialRecoBoundingBox", strFacialRecoBoundingBox);
        		Reporter.log("The strFacialRecoBoundingBox value equaled ("+strFacialRecoBoundingBox+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strFacialRecoUserId = line.substring(line.indexOf("FRuid,")+6, line.indexOf("|FRage"));
				objDictionary.put("strFacialRecoUserId", strFacialRecoUserId);
        		Reporter.log("The strFacialRecoUserId value equaled ("+strFacialRecoUserId+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strFacialRecoAge = line.substring(line.indexOf("FRage,")+6, line.indexOf("|FRem"));
				objDictionary.put("strFacialRecoAge", strFacialRecoAge);
        		Reporter.log("The strFacialRecoAge value equaled ("+strFacialRecoAge+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strFacialRecoEmotion = line.substring(line.indexOf("FRem,")+5, line.indexOf("|FRgen"));
				objDictionary.put("strFacialRecoEmotion", strFacialRecoEmotion);
        		Reporter.log("The strFacialRecoEmotion value equaled ("+strFacialRecoEmotion+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strFacialRecoGender = line.substring(line.indexOf("FRgen,")+6, line.indexOf("|FRpose"));
				objDictionary.put("strFacialRecoGender", strFacialRecoGender);
        		Reporter.log("The strFacialRecoGender value equaled ("+strFacialRecoGender+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strFacialRecoPose = line.substring(line.indexOf("FRpose,")+7, line.indexOf("|HC"));
				objDictionary.put("strFacialRecoPose", strFacialRecoPose);
        		Reporter.log("The strFacialRecoPose value equaled ("+strFacialRecoPose+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strHandicap = line.substring(line.indexOf("HC,")+3, line.indexOf("|MM"));
				objDictionary.put("strHandicap", strHandicap);
        		Reporter.log("The strHandicap value equaled ("+strHandicap+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strMaintenance = line.substring(line.indexOf("MM,")+3, line.indexOf("|BSS"));
				objDictionary.put("strMaintenance", strMaintenance);
        		Reporter.log("The strMaintenance value equaled ("+strMaintenance+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strBeginStatusSent = line.substring(line.indexOf("BSS,")+3, line.indexOf("|ESS"));
				objDictionary.put("strBeginStatusSent", strBeginStatusSent);
        		Reporter.log("The strBeginStatusSent value equaled ("+strBeginStatusSent+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strEndStatusSent = line.substring(line.indexOf("ESS,")+3, line.indexOf("|Serr"));
				objDictionary.put("strEndStatusSent", strEndStatusSent);
        		Reporter.log("The strEndStatusSent value equaled ("+strEndStatusSent+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strSessionError = line.substring(line.indexOf("Serr,")+5, line.indexOf("|Scrn"));
				objDictionary.put("strSessionError", strSessionError);
        		Reporter.log("The strSessionError value equaled ("+strSessionError+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		String strScreenName = line.substring(line.indexOf("Scrn,")+5, line.length());
				objDictionary.put("strScreenName", strScreenName);
        		Reporter.log("The strScreenName value equaled ("+strScreenName+")-UTC Time:"+dateFormatGmt.format(new Date()));

        		System.out.println("MIH");

////				if(line.contains("SPOT_"+strSpotNumber+"|"))
////	        	{
//	        		//strValidTimePurchased
//	        		String strValidTimePurchased = line.substring(line.indexOf("ValidTimePurchased,")+19, line.indexOf("|ValidTimeRemaining"));
//	        		objDictionary.put("strValidTimePurchased", strValidTimePurchased);
//	        		Reporter.log("The meter purchase amount equaled ("+strValidTimePurchased+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		//strValidTimeRemaining
//	        		String strValidTimeRemaining = line.substring(line.indexOf("ValidTimeRemaining,")+19, line.indexOf("|Violation"));
//	        		objDictionary.put("strValidTimeRemaining", strValidTimeRemaining);
//	        		Reporter.log("The meter Valid Time Remining equaled ("+strValidTimeRemaining+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		//strMaxRemaining
//	        		String strMaxRemaining = line.substring(line.indexOf("MaxRemaining,")+13, line.indexOf("|ValidTimePurchased"));
//	        		objDictionary.put("strMaxRemaining", strMaxRemaining);
//	        		Reporter.log("The meter max remaining amount equaled ("+strMaxRemaining+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		//strMeterFreeValue
//	        		String strMeterFreeValue = line.substring(line.indexOf("Free,")+5, line.indexOf("|No"));
//	        		objDictionary.put("strMeterFreeValue", strMeterFreeValue);
//	        		Reporter.log("The meter free value equaled ("+strMeterFreeValue+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		//strBegunVariable
//	        		String strBegunVariable = line.substring(line.indexOf("Begun,")+6, line.indexOf("|ParkTime"));
//	        		objDictionary.put("strBegunVariable", strBegunVariable);
//	        		Reporter.log("The meter begin value equaled ("+strBegunVariable+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		//strViolation
//	        		String strViolation = line.substring(line.indexOf("Violation,")+10, line.indexOf("|Unlocked"));
//	        		objDictionary.put("strViolation", strViolation);
//	        		Reporter.log("The meter violation value equaled ("+strViolation+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		//strCurrentMaintenanceMode
//	        		String strMaintenanceModeEnabled = line.substring(line.indexOf("InMaint,")+8, line.indexOf("|MtFree"));
//	        		objDictionary.put("strMaintenanceModeEnabled", strMaintenanceModeEnabled);
//	        		Reporter.log("The meter maintenance mode value equaled ("+strMeterFreeValue+") "+strBeforeOrAfterPayment+" a payment on spot ("+strSpotNumber+")-UTC Time:"+dateFormatGmt.format(new Date()));
//	        		if(strMaintenanceModeEnabled.equals("True"))
//	        		{
//	        			String strMtFreeParking = line.substring(line.indexOf("MtFree,")+7, line.indexOf("|MtNo"));
//	        			if(strMtFreeParking.equals("False"))
//	        			{
//	        				String strMtNoParking = line.substring(line.indexOf("MtNo,")+5, line.indexOf("|MtUn"));
//	        				if(strMtNoParking.equals("False"))
//		        			{
//	        					String strMtUnenforcedParking = line.substring(line.indexOf("MtUn,")+5, line.indexOf("|MtPark"));
//	        					if(strMtUnenforcedParking.equals("False"))
//			        			{
//	        						String strMtParking = line.substring(line.indexOf("MtPark,")+7, line.indexOf("|SrateS"));
//	        						if(strMtParking.equals("False")) {objDictionary.put("strMaintenanceMode", "");}
//	        						else{objDictionary.put("strMaintenanceMode", "Parking");}
//	        					}else{objDictionary.put("strMaintenanceMode", "UnenforcedParking");}
//	        				}else{objDictionary.put("strMaintenanceMode", "NoParking");}
//	        			}else{objDictionary.put("strMaintenanceMode", "FreeParking");}
//	        		}
//	        		break;
//	        	}
          }
	        in.close();
	        channel.disconnect();
	        session.disconnect();
	    }
	    catch(Exception e)
	    {System.out.println(e);}
//	    if (strPythonScriptExisted == "False")
//	    {UpdateErrorMessageWithPivotalData(objDictionary,driver,"The python script (testauto_dump_sessions.py) didn't exist on the meter-ssh into the meter");}
  }

  public String UploadImage1(java.io.File file, String name)
  {
	  if(file != null)
	  {
          System.out.println("[ImgurClient] Uploading image: " + file.getName());
          //HttpResponse<String> stringResponse;
          HttpResponse<String> response;
          HttpResponse<JsonNode> jsonResponse;//mpstestautomation //firesale
          try
          {
              jsonResponse = Unirest
                    .post("https://imgur-apiv3.p.rapidapi.com/3/image")
                    .header("X-Mashape-Key", "3f8b0ef615mshe88c8c9e5387b95p1bc826jsn13ebc47ec25b")
                    .header("Authorization", "Bearer " + accessToken)
                    .field("album", "C6vCiCT")
                    .field("image", file)
                    .field("title", name).asJson();
          }
          catch (UnirestException e)
          {
              System.out.println("[ImgurClient] Upload failed: " + e.toString());
              lastUrl = null;
              return "NOIMAGE";
          }
          System.out.println(jsonResponse.getStatus());
          int intStatusCode = jsonResponse.getStatus();
          if(intStatusCode == 400)
          {
              System.out.println("MIH (Response Status: 400)");
          }
          else if(intStatusCode == 403)
          {
              System.out.println("MIH (Response Status: 403)");
          }
          else
          {
              System.out.println("MIH");
          }
          try
          {
              // lastUrl = jsonResponse.getBody().getObject().getJSONObject("data").getString("link");
              // Check response status code
              System.out.println("HttpResponse Status Code: " + jsonResponse.getBody()); // prints error message if you are not subscribed to Imgur API via RapidAPI
              setLastUrl(jsonResponse.getBody().getObject().getJSONObject("data").getString("link"));
          }
          catch (Exception e)
          {
              System.out.println(e);
          }
          Reporter.log("[ImgurClient] Upload success. Link to image: " + lastUrl);
          System.out.println("[ImgurClient] Upload success. Link to image: " + lastUrl);
      }
	  return lastUrl;
  }


  public static String getLastUrl() {
    return lastUrl;
  }

  public static void setLastUrl(String newUrl) {
    lastUrl = newUrl;
  }
}

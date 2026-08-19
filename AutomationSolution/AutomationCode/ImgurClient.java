package AutomationCode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class ImgurClient {

    private static ImgurClient instance;
    private static final String albumId = "rAiUOgk";
    private static final String xRapidApiKey = "d8f5117eb7mshed61c05bf66a35dp180ffcjsn7981690a1b83";
    private static final String xRapidApiHost = "imgur-apiv3.p.rapidapi.com";
    private static String accessToken;
    private static String lastUrl;
    private static String lastImageId;
    private static String lastImageTitle;

    private ImgurClient() {
    }

    public static ImgurClient getInstance() {
        System.out.println("[ImgurClient] obtaining instance");
        if (instance == null) {
            instance = new ImgurClient();
            try {
                OkHttpClient client = new OkHttpClient();
                MediaType.parse("text/plain");

                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("refresh_token", "b8c945537b3bb7d8e3d6adcecb63b04d355cd014")
                        .addFormDataPart("client_id", "cb01d33bdbf02c6")
                        .addFormDataPart("client_secret", "7bd6f03d6d272be7372cd1ea4693cd3f351e1af4")
                        .addFormDataPart("grant_type", "refresh_token")
                        .build();

                Request request = new Request.Builder()
                        .url("https://api.imgur.com/oauth2/token")
                        .method("POST", body)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.code() == 200) {
                    assert response.body() != null;
                    String responseBody = response.body().string();

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody);

                    accessToken = jsonNode.get("access_token").asText();
                    System.out.println("[ImgurClient] Successfully logged in and retrieved Access Token: "
                            + accessToken);

                } else {
                    System.err.println("[ImgurClient] Request failed with HTTP status code: " + response.code());
                }
            } catch (Exception e) {
                System.err.println("[ImgurClient] Something went wrong while trying to log in to Imgur: "
                        + e);
            }
        }

        return instance;
    }

    public String UploadImage(File file, String name) {
        if (file != null) {
            System.out.println("[ImgurClient] Uploading image: " + file.getName());

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/octet-stream");

            try {
                RequestBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image", file.getName(), RequestBody.create(file, mediaType))
                        .addFormDataPart("title", name)
                        .addFormDataPart("album", albumId)
                        .build();

                Request request = new Request.Builder()
                        .url("https://imgur-apiv3.p.rapidapi.com/3/image")
                        .post(body)
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .addHeader("X-RapidAPI-Key", xRapidApiKey)
                        .addHeader("X-RapidAPI-Host", xRapidApiHost)
                        .build();

                Response response = client.newCall(request).execute();

                ObjectMapper objectMapper = new ObjectMapper();
                assert response.body() != null;
                JsonNode postJsonResponse = objectMapper.readTree(response.body().byteStream());

                int statusCode = response.code();
                if (statusCode == 200) {
                    lastImageId = postJsonResponse.get("data").get("id").asText();
                    lastImageTitle = postJsonResponse.get("data").get("title").asText();
                    String imageUrl = postJsonResponse.get("data").get("link").asText();
                    setLastUrl(imageUrl);

                    System.out.println("[ImgurClient] Upload success. Link to image: " + lastUrl);
                    return lastUrl;
                } else {
                    System.out.println("MIH (Response Status: " + statusCode + ")");
                    Reporter.log("[ImgurClient] Upload failed with status code: " + statusCode);
                    return "Upload failed with status code: " + statusCode;
                }
            } catch (IOException e) {
                System.out.println("[ImgurClient] Upload failed: " + e);
                lastUrl = null;
                return "Upload failed: " + e.getMessage();
            }
        }
        return "No Image to Upload";
    }

    public String DeleteImage() {
        if (lastImageId != null) {
            System.out.println("[ImgurClient] Deleting image: " + lastImageTitle);

            OkHttpClient client = new OkHttpClient();

            try {
                Request request = new Request.Builder()
                        .url("https://imgur-apiv3.p.rapidapi.com/3/image/" + lastImageId)
                        .delete(null)
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .addHeader("X-RapidAPI-Key", xRapidApiKey)
                        .addHeader("X-RapidAPI-Host", xRapidApiHost)
                        .build();

                Response response = client.newCall(request).execute();

                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("MIH (Delete Success)");
                    Reporter.log("[ImgurClient] Delete success.");
                    System.out.println("[ImgurClient] Delete success.");
                    return "Delete success.";
                } else {
                    System.out.println("MIH (Delete Failed with Status: " + statusCode + ")");
                    Reporter.log("[ImgurClient] Delete failed with status code: " + statusCode);
                    return "Delete failed with status code: " + statusCode;
                }
            } catch (Exception e) {
                System.out.println("[ImgurClient] Delete failed: " + e);
                lastUrl = null;
                return "Delete failed: " + e.getMessage();
            }
        }
        return "No Image to Delete";
    }

    public static String getLastUrl() {
        return lastUrl;
    }

    public static void setLastUrl(String newUrl) {
        lastUrl = newUrl;
    }
}
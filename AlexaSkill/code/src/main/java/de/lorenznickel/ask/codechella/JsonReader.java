package main.java.de.lorenznickel.ask.codechella;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public User getUserDataFromAmazon(String token){
        JSONObject json = null;
        try {
            json = readJsonFromUrl("https://api.amazon.com/user/profile?access_token=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setUser_id(json.get("user_id").toString());
        user.setEmail(json.get("email").toString());
        user.setName(json.get("name").toString());
        return user;
    }
}

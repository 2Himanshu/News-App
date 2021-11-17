package com.example.newsappjava;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QyeryUtilis {
    private static  final String LOG_TAG = QyeryUtilis.class.getSimpleName();

    public static List<News> fetchNews(String mUrl) {
        URL url = createUrl(mUrl);

        String jsonResponse = "";
        jsonResponse = makeHttpRequest(url);

        List<News> news = extractFromJson(jsonResponse);
        return news;
    }

    private static List<News> extractFromJson(String jsonResponse) {
        if(TextUtils.isEmpty(jsonResponse)){
            return null;
        }

        List<News> news = new ArrayList<>();

        try {
            JSONObject baseObject = new JSONObject(jsonResponse);
            JSONObject responseObject = baseObject.getJSONObject("response");
            JSONArray resultArray = responseObject.getJSONArray("results");
            for(int i=0;i<resultArray.length();i++){
                JSONObject currentObject = resultArray.getJSONObject(i);
                JSONObject fields = currentObject.getJSONObject("fields");

                String title = currentObject.getString("webTitle");
                String url = currentObject.getString("webUrl");
                String section = currentObject.getString("sectionName");
                String date = currentObject.getString("webPublicationDate");
                String img = fields.getString("thumbnail");
                String trailText = fields.getString("trailText");
                JSONArray tags = currentObject.getJSONArray("tags");

                String author = "";
                for(int j=0;j<tags.length();j++){
                    JSONObject currentTag = tags.getJSONObject(j);
                    author = currentTag.getString("webTitle");
                }
                news.add(new News(title,date,section,img,url,author,trailText));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    return news;
    }

    private static String makeHttpRequest(URL url) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse="";
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1500);
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG,"failed due to "+urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();

    }

    private static URL createUrl(String mUrl) {
        URL url = null;
        try {
            url = new URL(mUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }
}

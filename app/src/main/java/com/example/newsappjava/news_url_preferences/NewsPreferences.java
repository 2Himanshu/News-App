package com.example.newsappjava.news_url_preferences;

import android.content.Context;
import android.net.Uri;
import android.provider.SyncStateContract;

import com.example.newsappjava.Constants;

public class NewsPreferences {

    public static Uri.Builder getPreferredUri(Context context){
    Uri baseUri = Uri.parse(Constants.NEWS_REQUEST_URL);
    Uri.Builder uriBuilder = baseUri.buildUpon();

    uriBuilder.appendQueryParameter("api-key","test");
    uriBuilder.appendQueryParameter("show-fields","thumbnail,trailText");
    uriBuilder.appendQueryParameter("show-tags","contributor");

    return uriBuilder;
    }

    public static String getPreferredUrl(Context context,String section){
        Uri.Builder urlBuilder = getPreferredUri(context);
        urlBuilder.appendQueryParameter("section",section);
        return  urlBuilder.toString();
    }

}

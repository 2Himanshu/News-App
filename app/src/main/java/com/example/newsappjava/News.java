package com.example.newsappjava;

public class News {
    private String mTitle;
    private String mDate;
    private  String mSection;
    private String mImg;
    private String mUrl;
    private String mAuthor;
    private String mTrailText;
    public News(String title, String Date, String section, String img, String Url, String author, String trailText){
        mDate=Date;
        mTitle=title;
        mSection=section;
        mImg=img;
        mUrl=Url;
        mAuthor = author;
        mTrailText = trailText;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getImg(){
        return mImg;
    }


    public String getDate(){
        return mDate;
    }


    public String getSection(){
        return mSection;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getTrialText(){
        return mTrailText;
    }
}

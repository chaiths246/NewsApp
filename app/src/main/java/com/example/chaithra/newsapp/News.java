package com.example.chaithra.newsapp;

/**
 * Created by chaithra on 10/13/17.
 */

public class News {

    private String title;
    private String sectionName;
    private String url;
    private String published_date;
    private String author;

    public News(String title, String sectionName, String url, String published_date, String author) {
        this.title = title;
        this.sectionName = sectionName;
        this.url = url;
        this.published_date = published_date;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthor() {
        return author;
    }


}

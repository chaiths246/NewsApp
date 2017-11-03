package com.example.chaithra.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by chaithra on 10/14/17.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {
    public static final String LOG_TAG = NewsLoader.class.getSimpleName();
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (url == null) {
            return null;
        }
        ArrayList<News> newslist = Utils.fetchNewsData(url);
        if (newslist.size() == 0) {
            Log.d(LOG_TAG, "news list is empty");
        }
        return newslist;
    }
}

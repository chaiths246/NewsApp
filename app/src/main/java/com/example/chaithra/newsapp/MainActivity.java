package com.example.chaithra.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {
    private static final String NEWS_URL = "http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test&page-size=30&show-tags=contributor";
    private static final int NEWS_LOADER_ID = 1;
    ListView listView;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.newslist);
        newsAdapter = new NewsAdapter(this, 0);
        listView.setAdapter(newsAdapter);
        TextView emptystate = (TextView) findViewById(R.id.emptyview);
        emptystate.setText(getResources().getText(R.string.loading));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = newsAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                if (currentNews.getUrl() == null || TextUtils.isEmpty(currentNews.getUrl())) {
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.no_resource), Toast.LENGTH_SHORT).show();
                } else {
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(websiteIntent);
                }
            }
        });
        boolean isonline = isOnline();
        if (isonline == false) {
            emptystate.setText(getResources().getText(R.string.no_internet));
            View loadingIndicator = findViewById(R.id.progressbar);
            loadingIndicator.setVisibility(View.GONE);
        } else {
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        }
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, NEWS_URL);

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> data) {
        View loadingIndicator = findViewById(R.id.progressbar);
        loadingIndicator.setVisibility(View.GONE);

        newsAdapter.clear();
        if (data != null) {
            newsAdapter.addAll(data);
        }
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        if (newsAdapter != null) {
            newsAdapter.clear();
        }
    }
}

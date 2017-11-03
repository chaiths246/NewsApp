package com.example.chaithra.newsapp;

/**
 * Created by chaithra on 10/14/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        News news = getItem(position);
        ViewHolder holder;
        if (v == null) {
            holder = new ViewHolder();
            v = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
            holder.sectionname = (TextView) v.findViewById(R.id.sectionname);
            holder.publisheddate = (TextView) v.findViewById(R.id.published_date);
            holder.title = (TextView) v.findViewById(R.id.title);
            holder.url = (TextView) v.findViewById(R.id.url);
            holder.author = (TextView) v.findViewById(R.id.author);
            v.setTag(holder);

        } else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
        }
        holder.sectionname.setText(news.getSectionName());
        holder.publisheddate.setText(news.getPublished_date());
        holder.title.setText(news.getTitle());
        holder.url.setText(news.getUrl());
        holder.author.setText(news.getAuthor());
        return v;
    }

    private class ViewHolder {
        TextView sectionname;
        TextView publisheddate;
        TextView title;
        TextView url;
        TextView author;

    }
}

package com.example.newsappjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> news){
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        News news = getItem(position);



        TextView title = convertView.findViewById(R.id.title);
        title.setText(news.getTitle());

        

        ImageView image = convertView.findViewById(R.id.image_view);
        Picasso.get().load(news.getImg()).into(image);

        TextView section = convertView.findViewById(R.id.section);
        section.setText(news.getSection());

        String trimDate = news.getDate().substring(0,10);

        TextView date  =  convertView.findViewById(R.id.date);
        date.setText(trimDate);

        TextView author = convertView.findViewById(R.id.author);
        author.setText(news.getAuthor());

        TextView trialText = convertView.findViewById(R.id.trial_text);
        trialText.setText(news.getTrialText());

        return convertView;
    }
}

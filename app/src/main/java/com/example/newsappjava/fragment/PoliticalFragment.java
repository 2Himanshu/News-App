package com.example.newsappjava.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.newsappjava.News;
import com.example.newsappjava.NewsAdapter;
import com.example.newsappjava.NewsLoader;
import com.example.newsappjava.R;
import com.example.newsappjava.news_url_preferences.NewsPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PoliticalFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter newsAdapter;
    private ListView listView;
    private LoaderManager loaderManager;
    private int ID = 3;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView  =  inflater.inflate(R.layout.fragment_political, container, false);

        newsAdapter = new NewsAdapter(getContext(),new ArrayList<>());

        loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(ID,null,this);

        progressBar = convertView.findViewById(R.id.progress_bar_p);

        listView = convertView.findViewById(R.id.listView);

        listView.setAdapter(newsAdapter);

        return convertView;
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        String url = NewsPreferences.getPreferredUrl(getContext(),"politics");

        return new NewsLoader(getContext(),url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
    newsAdapter.addAll();

    progressBar.setVisibility(View.GONE);

    if(data!=null && !data.isEmpty()){
        newsAdapter.addAll(data);
    }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
    newsAdapter.clear();
    }
}
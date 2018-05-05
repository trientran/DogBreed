package com.example.pbp22.dogbreed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class DogLoader extends AsyncTaskLoader<List<Product>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = DogLoader.class.getName();

    public DogLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Product> loadInBackground() {

        // Perform the network request, parse the response, and extract a list of earthquakes.
        return QueryUtils.fetchData();
    }
}

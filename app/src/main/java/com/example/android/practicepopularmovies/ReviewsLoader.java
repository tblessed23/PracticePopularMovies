package com.example.android.practicepopularmovies;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class ReviewsLoader extends AsyncTaskLoader<List<Reviews>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ReviewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link ReviewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */

    public ReviewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }




    @Override
    public List<Reviews> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of movies.
        List<Reviews> reviews = ReviewUtils.fetchMovieData(mUrl);
        return reviews;
    }
}


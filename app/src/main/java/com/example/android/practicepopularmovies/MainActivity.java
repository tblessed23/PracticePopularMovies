package com.example.android.practicepopularmovies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Reviews>> {


    RecyclerView movieReviewsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewAdapter mReviewAdapter;
    private List<Reviews> movieReviews;


    /**
     * TextView that is displayed when the list is empty
     */

    private static final int MOVIESARTICLE_LOADER_ID = 1;

    /**
     * URL for data data from the themoviedb.org website
     */

    private static final String MOVIES_REQUEST_URL = "https://api.themoviedb.org/3/movie/popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  // Find a reference to the {@link RecyclerView} in the layout
        movieReviewsRecyclerView = (RecyclerView) findViewById(R.id.movie_reviews);



        // use a grid layout manager
        layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        movieReviewsRecyclerView.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        movieReviewsRecyclerView.setHasFixedSize(true);

        // Create a new adapter that takes an empty list of moviess as input
        mReviewAdapter = new ReviewAdapter(this, new ArrayList<Reviews>());
        movieReviewsRecyclerView.setAdapter(mReviewAdapter);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        movieReviewsRecyclerView.setAdapter(mReviewAdapter);
    }





    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(NetworkHelper.networkIsAvailable(this)){

            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.initLoader(MOVIESARTICLE_LOADER_ID, null, this);
        }
    }

    @Override
    public Loader<List<Reviews>> onCreateLoader(int i, Bundle bundle) {

        // parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(MOVIES_REQUEST_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter and its value.
        uriBuilder.appendQueryParameter("api_key", "543e8145fb4bd3a4d9f616fb389b7356");
        uriBuilder.appendQueryParameter("language", "en-US");
        uriBuilder.appendQueryParameter("sort_by", "popularity.desc");



        // Return the completed url
        return new ReviewsLoader(this, uriBuilder.toString());
    }



    @Override
    public void onLoadFinished(Loader < List < Reviews >> loader, List < Reviews > movies) {


        // Clear the adapter of previous movie data
        mReviewAdapter.clear(new ArrayList<Reviews>());

        // If there is a valid list of {@link Movies}s, then add them to the adapter's
        // data set. This will trigger the RecyclerView to update.
        if (movies != null && !movies.isEmpty()) {
            mReviewAdapter.setReviewData(movies);
        }
    }



    @Override
    public void onLoaderReset(Loader<List<Reviews>> loader) {
        //Clear the existing data
        mReviewAdapter.clear(new ArrayList<Reviews>());
    }




}







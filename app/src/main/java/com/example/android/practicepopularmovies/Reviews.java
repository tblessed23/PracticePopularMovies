package com.example.android.practicepopularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Reviews {



    private String mTitle;
    private String mReleaseDate;



    /**
     * No args constructor for use in serialization
     *
     * @param reviews
     */
    public Reviews(String reviews) {
    }

    //Regular Constructor

    public Reviews(String title, String releasedate) {

        this.mTitle = title;
        this.mReleaseDate = releasedate;

    }


//    public Reviews(int id, String title, String releasedate, String userrating, String synopsis, String image) {
//
//        this.mTitle = title;
//        this.mReleaseDate = releasedate;
//        this.mUserRating = userrating;
//        this.mSynopsis = synopsis;
//        this.mImage = image;
//    }



    public String getmTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setReleasedate(String releasedate) {
        this.mReleaseDate = releasedate;
    }

}


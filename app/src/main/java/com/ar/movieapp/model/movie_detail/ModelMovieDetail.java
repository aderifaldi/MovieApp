package com.ar.movieapp.model.movie_detail;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aderifaldi on 25-Jan-17.
 */

public class ModelMovieDetail implements Serializable {

    private String backdrop_path;
    private String homepage;
    private String id;
    private String overview;
    private String poster_path;
    private Date release_date;
    private String title;
    private String vote_average;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }
}

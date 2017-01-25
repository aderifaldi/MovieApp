package com.ar.movieapp.model.movie_list;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aderifaldi on 20-Jan-17.
 */

public class MovieListResult implements Serializable {

    private String poster_path;
    private String overview;
    private Date release_date;
    private String id;
    private String title;
    private String backdrop_path;
    private String vote_average;

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }
}

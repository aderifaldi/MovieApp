package com.ar.movieapp.model.movie_list;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aderifaldi on 20-Jan-17.
 */

public class MovieListResult implements Serializable {

    private String poster_path;
    private String overview;
    private String release_date;
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

    public String getRelease_date() {
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

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}

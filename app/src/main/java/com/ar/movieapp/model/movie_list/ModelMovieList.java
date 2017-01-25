package com.ar.movieapp.model.movie_list;

import java.io.Serializable;

/**
 * Created by aderifaldi on 25-Jan-17.
 */

public class ModelMovieList implements Serializable {

    private int page;
    private MovieListResult[] results;

    public int getPage() {
        return page;
    }

    public MovieListResult[] getResults() {
        return results;
    }
}

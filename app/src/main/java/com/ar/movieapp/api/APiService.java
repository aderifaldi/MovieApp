package com.ar.movieapp.api;

import com.ar.movieapp.model.movie_detail.ModelMovieDetail;
import com.ar.movieapp.model.movie_list.ModelMovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aderifaldi on 25-Jan-17.
 */

public interface APIService {

    /**
     * END POINT
     */
    String POPULAR_MOVIE = "popular";
    String NOW_PLAYING = "now_playing";
    String DETAIL_MOVIE = "{id}";

    @GET(POPULAR_MOVIE)
    Call<ModelMovieList> popular(@Query("api_key") String api_key,
                                      @Query("language") String language);

    @GET(NOW_PLAYING)
    Call<ModelMovieList> nowPlaying(@Query("api_key") String api_key,
                                      @Query("language") String language);

    @GET(DETAIL_MOVIE)
    Call<ModelMovieDetail> detail(@Path("id") String id,
                                      @Query("api_key") String api_key,
                                      @Query("language") String language);

}

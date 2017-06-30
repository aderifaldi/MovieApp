package com.ar.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ar.movieapp.R;
import com.ar.movieapp.fragment.BaseActivity;
import com.ar.movieapp.helper.sqlite.SQLiteQuery;
import com.ar.movieapp.model.movie_detail.ModelMovieDetail;
import com.ar.movieapp.model.movie_list.MovieListResult;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends BaseActivity {

    private static final String PHOTO_PATH = "http://image.tmdb.org/t/p/w185/";

    private String id, title, summary, posterPath;
    private String releaseDate;
    private Intent intentData;
    private Context context;

    @BindView(R.id.pbLoading) ProgressBar pbLoading;
    @BindView(R.id.txtTitle) TextView txtTitle;
    @BindView(R.id.txtSummary) TextView txtSummary;
    @BindView(R.id.txtDateRelease) TextView txtDateRelease;
    @BindView(R.id.imgMovie) ImageView imgMovie;

    private SQLiteQuery sqLiteQuery;
    private List<MovieListResult> movieLocal;
    private MovieListResult movie;
    private ModelMovieDetail movieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        context = getApplicationContext();

        sqLiteQuery = new SQLiteQuery(context);
        movie = new MovieListResult();

        intentData = getIntent();
        id = intentData.getStringExtra("id");

        getMovieDetail(id);

    }

    @OnClick(R.id.btnBack)
    protected void back(){
        onBackPressed();
    }

    @OnClick(R.id.btnSave)
    protected void btnSave(){
        saveMovie();
    }

    @OnClick(R.id.btnShare)
    protected void btnShare(){

        Uri uri = Uri.parse(PHOTO_PATH + posterPath);

        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/html");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        shareIntent.putExtra(Intent.EXTRA_TEXT, summary);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(shareIntent, "Share Movie"));
    }

    private void getMovieDetail(String id){
        Call<ModelMovieDetail> call = apiService.detail(id, APIKEY, LANGUAGE);

        call.enqueue(new Callback<ModelMovieDetail>() {
            @Override
            public void onResponse(Call<ModelMovieDetail> call, Response<ModelMovieDetail> response) {
                pbLoading.setVisibility(View.GONE);

                movieDetail = response.body();

                if (response.isSuccessful()){

                    title = movieDetail.getTitle();
                    summary = movieDetail.getOverview();
                    posterPath = movieDetail.getPoster_path();
                    releaseDate = movieDetail.getRelease_date();


                    Glide.with(context).load(PHOTO_PATH + posterPath)
                            .crossFade()
                            .into(imgMovie);

                    txtDateRelease.setText(releaseDate);
                    txtTitle.setText(title);
                    txtSummary.setText(summary);
                }else {
                    call.cancel();
                }

            }

            @Override
            public void onFailure(Call<ModelMovieDetail> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                call.cancel();
            }
        });

    }

    private void saveMovie(){
        sqLiteQuery.open();

        movieLocal = sqLiteQuery.cekMovie(id);

        if (movieLocal.size() == 0){

            movie.setId(movieDetail.getId());
            movie.setPoster_path(movieDetail.getPoster_path());
            movie.setVote_average(movieDetail.getVote_average());
            movie.setBackdrop_path(movieDetail.getBackdrop_path());
            movie.setOverview(movieDetail.getOverview());
            movie.setRelease_date(movieDetail.getRelease_date());
            movie.setTitle(movieDetail.getTitle());

            sqLiteQuery.insertMovie(movie);
            Toast.makeText(context, "Tersimpan ke Favorit!", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Sudah Tersimpan!", Toast.LENGTH_SHORT).show();
        }

    }

}

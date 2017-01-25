package com.ar.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ar.movieapp.R;
import com.ar.movieapp.fragment.BaseActivity;
import com.ar.movieapp.model.movie_detail.ModelMovieDetail;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends BaseActivity {

    private static final String PHOTO_PATH = "http://image.tmdb.org/t/p/w185/";

    private String id, title, summary, posterPath;
    private Date releaseDate;
    private Intent intentData;

    private SimpleDateFormat dateFormat;
    private Context context;

    @BindView(R.id.progressLoading) ProgressBar progressLoading;
    @BindView(R.id.movieTitle) TextView movieTitle;
    @BindView(R.id.movieSummary) TextView movieSummary;
    @BindView(R.id.dateRelease) TextView dateRelease;
    @BindView(R.id.imgMovie) ImageView imgMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        context = getApplicationContext();

        intentData = getIntent();
        id = intentData.getStringExtra("id");

        getMovieDetail(id);

    }

    @OnClick(R.id.btnBack)
    protected void back(){
        onBackPressed();
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
                progressLoading.setVisibility(View.GONE);

                ModelMovieDetail data = response.body();

                if (response.isSuccessful()){

                    title = data.getTitle();
                    summary = data.getOverview();
                    posterPath = data.getPoster_path();
                    releaseDate = data.getRelease_date();


                    Glide.with(context).load(PHOTO_PATH + posterPath)
                            .crossFade()
                            .into(imgMovie);

                    dateRelease.setText(dateFormat.format(releaseDate));
                    movieTitle.setText(title);
                    movieSummary.setText(summary);
                }else {
                    call.cancel();
                }

            }

            @Override
            public void onFailure(Call<ModelMovieDetail> call, Throwable t) {
                progressLoading.setVisibility(View.GONE);
                call.cancel();
            }
        });

    }

}

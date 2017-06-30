package com.ar.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.ar.movieapp.R;
import com.ar.movieapp.adapter.MovieListAdapter;
import com.ar.movieapp.helper.sqlite.SQLiteQuery;
import com.ar.movieapp.model.movie_list.MovieListResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteMovieActivity extends AppCompatActivity {

    @BindView(R.id.listFavoriteMovie)
    RecyclerView listFavoriteMovie;

    private LinearLayoutManager linearLayoutManager;
    private MovieListAdapter adapter;
    private List<MovieListResult> listPropertyLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);
        ButterKnife.bind(this);

        adapter = new MovieListAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        listFavoriteMovie.setLayoutManager(linearLayoutManager);
        listFavoriteMovie.setAdapter(adapter);

        loadFavoriteMovie();
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieListResult movie = adapter.getData().get(i);

                Intent intentToMovieDetail = new Intent(getApplicationContext(), MovieDetailActivity.class);
                intentToMovieDetail.putExtra("id", movie.getId());
                startActivity(intentToMovieDetail);

            }
        });

    }

    @OnClick(R.id.btnBack)
    protected void btnBack(){
        onBackPressed();
    }

    private void loadFavoriteMovie(){
        adapter.getData().clear();

        SQLiteQuery historyAct = new SQLiteQuery(this);
        historyAct.open();
        listPropertyLocal = historyAct.selectMovie();
        historyAct.close();

        adapter.getData().addAll(listPropertyLocal);
        adapter.notifyDataSetChanged();
    }


}

package com.ar.movieapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ar.movieapp.R;
import com.ar.movieapp.activity.MovieDetailActivity;
import com.ar.movieapp.adapter.MovieListAdapter;
import com.ar.movieapp.model.movie_list.ModelMovieList;
import com.ar.movieapp.model.movie_list.MovieListResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aderifaldi on 10-Nov-16.
 */

public class NowPlayingFragment extends BaseFragment {

    private View view;
    private LinearLayoutManager linearLayoutManager;
    private MovieListAdapter adapter;
    private ModelMovieList data;
    private Context context;

    @BindView(R.id.listMovie) RecyclerView listMovie;
    @BindView(R.id.progressLoading) ProgressBar progressLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.fragment_list_movie, container, false);
        }
        ButterKnife.bind(this, view);

        context = getActivity();

        linearLayoutManager = new LinearLayoutManager(context);
        adapter = new MovieListAdapter(context);

        listMovie.setLayoutManager(linearLayoutManager);
        listMovie.setAdapter(adapter);

        getNowPlayingMovie();

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieListResult movie = adapter.getData().get(i);

                Intent intentToMovieDetail = new Intent(context, MovieDetailActivity.class);
                intentToMovieDetail.putExtra("id", movie.getId());
                startActivity(intentToMovieDetail);

            }
        });

        return view;
    }

    private void getNowPlayingMovie(){

        Call<ModelMovieList> call = apiService.nowPlaying(APIKEY, LANGUAGE);

        call.enqueue(new Callback<ModelMovieList>() {
            @Override
            public void onResponse(Call<ModelMovieList> call, Response<ModelMovieList> response) {

                progressLoading.setVisibility(View.GONE);
                data = response.body();

                if (response.isSuccessful()){
                    for (MovieListResult result : data.getResults()){
                        adapter.getData().add(result);
                        adapter.notifyItemInserted(adapter.getData().size() - 1);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    call.cancel();
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelMovieList> call, Throwable t) {
                progressLoading.setVisibility(View.GONE);
                call.cancel();
            }
        });

    }

}

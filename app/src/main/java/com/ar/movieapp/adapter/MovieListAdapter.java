package com.ar.movieapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar.movieapp.R;
import com.ar.movieapp.model.movie_list.MovieListResult;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private static final String PHOTO_PATH = "http://image.tmdb.org/t/p/w185/";

    private ArrayList<MovieListResult> data;
    private LayoutInflater inflater;
    private Context context;
    private AdapterView.OnItemClickListener onItemClickListener;
    private SimpleDateFormat dateFormat;

    public MovieListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArrayList<MovieListResult> getData() {
        return data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_movie_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MovieListResult movie = data.get(position);
        holder.position = position;

        final long identity = System.currentTimeMillis();
        holder.identity = identity;

        holder.movieImageUrl = movie.getPoster_path();

        if (holder.identity == identity){
            Glide.with(context).load(PHOTO_PATH + movie.getPoster_path())
                    .crossFade()
                    .into(holder.imgMovie);
        }

        holder.dateRelease.setText(dateFormat.format(movie.getRelease_date()));
        holder.movieTitle.setText(movie.getTitle());
        holder.movieSummary.setText(movie.getOverview());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int position;
        String movieImageUrl;
        long identity;

        @BindView(R.id.movieTitle) TextView movieTitle;
        @BindView(R.id.movieSummary) TextView movieSummary;
        @BindView(R.id.dateRelease) TextView dateRelease;
        @BindView(R.id.imgMovie) ImageView imgMovie;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(null, v, position, 0);
            }
        }
    }
}

package com.ar.movieapp.helper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.ar.movieapp.model.movie_list.MovieListResult;

//import net.sqlcipher.Cursor;
//import net.sqlcipher.SQLException;
//import net.sqlcipher.database.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by aderifaldi on 02/03/2016.
 */
public class SQLiteQuery {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public SQLiteQuery(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase("password");
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //INSERT MOVIEW
    public void insertMovie(MovieListResult data){

        open();
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.id, data.getId());
        values.put(MySQLiteHelper.overview, data.getOverview());
        values.put(MySQLiteHelper.release_date, data.getRelease_date());
        values.put(MySQLiteHelper.poster_path, data.getPoster_path());
        values.put(MySQLiteHelper.title, data.getTitle());
        values.put(MySQLiteHelper.backdrop_path, data.getBackdrop_path());
        values.put(MySQLiteHelper.vote_average, data.getVote_average());

        database.insert(MySQLiteHelper.MOVIE, null, values);
        database.close();
    }

    //SELECT MOVIE
    public List<MovieListResult> selectMovie(){
        List<MovieListResult> data = new LinkedList<MovieListResult>();

        String query = "SELECT "
                + MySQLiteHelper.id + ", "
                + MySQLiteHelper.overview + ", "
                + MySQLiteHelper.release_date + ", "
                + MySQLiteHelper.poster_path + ", "
                + MySQLiteHelper.title + ", "
                + MySQLiteHelper.backdrop_path + ", "
                + MySQLiteHelper.vote_average +
                " FROM "
                + MySQLiteHelper.MOVIE;
        Log.d("db", "query " + query);

        Cursor cursor = database.rawQuery(query, null);
        MovieListResult value = null;

        if (cursor.moveToFirst()) {
            do {
                value = new MovieListResult();
                value.setId(cursor.getString(0));
                value.setOverview(cursor.getString(1));
                value.setRelease_date(cursor.getString(2));
                value.setPoster_path(cursor.getString(3));
                value.setTitle(cursor.getString(4));
                value.setBackdrop_path(cursor.getString(5));
                value.setVote_average(cursor.getString(6));

                data.add(value);
            } while (cursor.moveToNext());
        }

        return data;
    }

    //CEK PROPERTY
    public List<MovieListResult> cekMovie(String movieId){
        List<MovieListResult> data = new LinkedList<>();

        String query = "SELECT "
                + MySQLiteHelper.id +
                " FROM "
                + MySQLiteHelper.MOVIE +
                " WHERE "
                + MySQLiteHelper.id + " = '" + movieId + "'";
        Log.d("db", "query " + query);

        Cursor cursor = database.rawQuery(query, null);
        MovieListResult value = null;

        if (cursor.moveToFirst()) {
            do {
                value = new MovieListResult();
                value.setId(cursor.getString(0));

                data.add(value);
            } while (cursor.moveToNext());
        }

        return data;
    }

    //DELETE PROPERTY
    public void deleteMovie(String movieId){
        database.delete(MySQLiteHelper.MOVIE, "id=?", new String[]{movieId});
    }


}

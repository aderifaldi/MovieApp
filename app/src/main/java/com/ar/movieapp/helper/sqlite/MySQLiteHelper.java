package com.ar.movieapp.helper.sqlite;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * Created by aderifaldi on 02/03/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MovieApp.db";
    public static final int DATABASE_VERSION = 1;

    public static final String MOVIE = "movie";

    public static final String id = "id";
    public static final String overview = "overview";
    public static final String release_date = "release_date";
    public static final String poster_path = "poster_path";
    public static final String title = "title";
    public static final String backdrop_path = "backdrop_path";
    public static final String vote_average = "vote_average";

    /**
     * CREATE TABLE
     */
    private static final String CREATE_TABLE_MOVIE = "CREATE TABLE if not EXISTS "
            + MOVIE + "("
            + id +" text primary key autoincrement,"
            + overview +" text,"
            + release_date +" text,"
            + poster_path +" text,"
            + title +" text,"
            + backdrop_path +" text,"
            + vote_average +" text" +
            ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_MOVIE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIStS " + MOVIE);

        onCreate(db);

    }
}

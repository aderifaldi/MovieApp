package com.ar.movieapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aderifaldi on 13/01/2016.
 */
public class GlobalVariable {

    public static final String PREF_NAME = "MOVIE_APP_PREF";
    private static final String IS_LOGIN = "isLogin";
    private static final String FACEBOOK_NAME = "facebookName";
    private static final String FACEBOOK_ID = "facebookId";
    private static final String FACEBOOK_PP = "facebookPP";
    private static final String FCM_TOKEN = "fcmToken";
    public static final String NOTIFICATION_ID = "notificationId";
    public static final String NOTIFICATION_REQUEST_CODE = "requestCode";

    public static void saveFBName(Context context, String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FACEBOOK_NAME, data);
        editor.apply();
    }

    public static String getFBName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACEBOOK_NAME, null);
    }

    public static void saveNotificationId(Context context, int data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NOTIFICATION_ID, data);
        editor.apply();
    }

    public static int getNotificationId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        int data = sharedPreferences.getInt(NOTIFICATION_ID, 0);

        return data;
    }

    public static void saveRequestCode(Context context, int data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NOTIFICATION_REQUEST_CODE, data);
        editor.apply();
    }

    public static int getRequestCode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        int data = sharedPreferences.getInt(NOTIFICATION_REQUEST_CODE, 0);

        return data;
    }

    public static void saveFCMToken(Context context, String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FCM_TOKEN, data);
        editor.apply();
    }

    public static String getFCMToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FCM_TOKEN, null);
    }

    public static void saveFBId(Context context, String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FACEBOOK_ID, data);
        editor.apply();
    }

    public static String getFBId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACEBOOK_ID, null);
    }

    public static void saveFBPP(Context context, String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FACEBOOK_PP, data);
        editor.apply();
    }

    public static String getFBPP(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACEBOOK_PP, null);
    }

    public static boolean getIsLogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public static void saveIsLogin(Context context, boolean data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariable.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, data);
        editor.apply();
    }

}

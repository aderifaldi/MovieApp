package com.ar.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ar.movieapp.R;
import com.ar.movieapp.helper.sharedpreference.GlobalVariable;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    private boolean isLogin;
    private Context context;
    private Intent goToIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = getApplicationContext();
        isLogin = GlobalVariable.getIsLogin(context);

        isLoginHandler();

    }

    private void isLoginHandler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isLogin){
                    goToIntent = new Intent(context, MainActivity.class);
                }else {
                    goToIntent = new Intent(context, LoginActivity.class);
                }
                startActivity(goToIntent);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }

}

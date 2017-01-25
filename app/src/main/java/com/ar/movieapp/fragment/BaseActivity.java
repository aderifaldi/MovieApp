package com.ar.movieapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ar.movieapp.api.APIService;
import com.ar.movieapp.api.ServiceGenerator;

/**
 * Created by aderifaldi on 08/08/2016.
 */
public class BaseActivity extends AppCompatActivity{

    public static final String APIKEY = "0aa8196e97b948f8b2c98d8eb020fafc";
    public static final String LANGUAGE = "en-US";
    private static final String TAG = "APIResponse";

    public APIService apiService = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = ServiceGenerator.createService(APIService.class);

    }

}

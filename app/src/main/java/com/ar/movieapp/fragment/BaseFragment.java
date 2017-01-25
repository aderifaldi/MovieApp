package com.ar.movieapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ar.movieapp.api.APIService;
import com.ar.movieapp.api.ServiceGenerator;

/**
 * Created by aderifaldi on 25/08/2016.
 */
public class BaseFragment extends Fragment {

    public static final String APIKEY = "0aa8196e97b948f8b2c98d8eb020fafc";
    public static final String LANGUAGE = "en-US";
    private static final String TAG = "APIResponse";

    public APIService apiService = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = ServiceGenerator.createService(APIService.class);

    }

}

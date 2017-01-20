package com.ar.movieapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar.movieapp.R;

/**
 * Created by aderifaldi on 10-Nov-16.
 */

public class EmptyFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.fragment_empty, container, false);
        }

        return view;
    }

}

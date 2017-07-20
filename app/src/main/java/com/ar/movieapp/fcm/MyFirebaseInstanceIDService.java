package com.ar.movieapp.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by aderifaldi on 08/12/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "fcm token: " + refreshedToken);

//        AppUtility.logD(TAG, "Refreshed token: " + refreshedToken);
//        GlobalVariable.saveFCMToken(this, refreshedToken);

    }

}

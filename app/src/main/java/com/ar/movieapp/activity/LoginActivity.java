package com.ar.movieapp.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ar.movieapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback{

    private String facebooId, facebookProfilePictureURL, facebookName;

    private CallbackManager callbackManager;

    @BindView(R.id.loginFacebook) LoginButton loginFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginFacebook.setReadPermissions(Arrays.asList("public_profile"));

    }

    @OnClick(R.id.loginFacebook)
    protected void loginFacebook(){
        registerFacebookCallback();
    }

    private void registerFacebookCallback(){
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                facebooId = loginResult.getAccessToken().getUserId();
                facebookProfilePictureURL = "http://graph.facebook.com/" + facebooId + "/picture?type=large";

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    org.json.JSONObject object,
                                    GraphResponse response) {

                                String contentRaw = String.valueOf(object);

                                try {
                                    String rawContent = new String(contentRaw);

                                    org.json.JSONObject raw = new org.json.JSONObject(rawContent);
                                    facebookName = raw.getString("name");

                                    Toast.makeText(LoginActivity.this, "welcome " + facebookName, Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}

package com.ar.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ar.movieapp.R;
import com.ar.movieapp.helper.GlobalVariable;
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
    private Context context;

    @BindView(R.id.loginFacebook) LoginButton loginFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        context = getApplicationContext();

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

                GlobalVariable.saveFBId(context, facebooId);
                GlobalVariable.saveFBPP(context, facebookProfilePictureURL);

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

                                    GlobalVariable.saveIsLogin(context, true);
                                    GlobalVariable.saveFBName(context, facebookName);

                                    goToMainActivity();
                                    Toast.makeText(LoginActivity.this, "Welcome " + facebookName, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(context, "Login Facebook Cancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void goToMainActivity(){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}

package com.ar.movieapp.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.ar.movieapp.R;
import com.ar.movieapp.adapter.PagerHomeAdapter;
import com.ar.movieapp.helper.GlobalVariable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String PACKAGE_NAME = "com.ar.movieapp";

    private String facebookName;
    private Intent intentGetData;

    private PagerAdapter adapter;
    private CharSequence pagerTitle[] = {"Popular ", "Now Playing"};
    private int numbOfTabs;

    @BindView(R.id.helloUser) TextView helloUser;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        intentGetData = getIntent();
        facebookName = GlobalVariable.getFBName(this);

        helloUser.setText("Hello " + facebookName);

        numbOfTabs = pagerTitle.length;
        adapter = new PagerHomeAdapter(getSupportFragmentManager(), pagerTitle, numbOfTabs);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        printFacebookKeyHash();

    }

    private void printFacebookKeyHash(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    PACKAGE_NAME,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("MainActivity", "Facebok Hash Key: "
                        + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.d("KeyHash:", e.toString());
        }
    }

}

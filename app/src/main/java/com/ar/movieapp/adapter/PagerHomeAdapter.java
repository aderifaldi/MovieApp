package com.ar.movieapp.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ar.movieapp.fragment.NowPlayingFragment;
import com.ar.movieapp.fragment.PopularFragment;

/**
 * Created by Edwin on 15/02/2015.
 */
public class PagerHomeAdapter extends FragmentStatePagerAdapter {

    private CharSequence Titles[];
    private int NumbOfTabs;
    private PopularFragment tab1;
    private NowPlayingFragment tab2;

    public PagerHomeAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            tab1 = new PopularFragment();
            return tab1;
        } else {
            tab2 = new NowPlayingFragment();
            return tab2;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
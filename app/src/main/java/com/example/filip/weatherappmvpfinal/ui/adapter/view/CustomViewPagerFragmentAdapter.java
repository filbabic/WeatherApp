package com.example.filip.weatherappmvpfinal.ui.adapter.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.weather.view.WeatherFragment;

import java.util.ArrayList;

/**
 * Created by Filip on 09/02/2016.
 */
public class CustomViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<LocationWrapper> mCitiesList = new ArrayList<>();

    public CustomViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(mCitiesList.get(position).getLocation());
    }

    @Override
    public int getCount() {
        return mCitiesList.size();
    }

    public void setAdapterData(ArrayList<LocationWrapper> dataSource) {
        this.mCitiesList.clear();
        this.mCitiesList.addAll(dataSource);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCitiesList.get(position).getLocation();
    }
}

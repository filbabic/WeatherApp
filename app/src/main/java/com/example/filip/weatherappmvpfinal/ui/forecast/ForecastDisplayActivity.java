package com.example.filip.weatherappmvpfinal.ui.forecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.ui.weather.view.ForecastFragment;

/**
 * Created by Filip on 26/03/2016.
 */
public class ForecastDisplayActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_display);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragment();
        initToolbar();
    }

    private void initFragment() {
        if (getSupportFragmentManager().findFragmentById(R.id.forecast_display_activity_frame_layout) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .add(R.id.forecast_display_activity_frame_layout, ForecastFragment.newInstance(getIntent().getStringExtra(Constants.CITY_BUNDLE_KEY)))
                    .commit();
        }
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Forecast: " + getIntent().getStringExtra(Constants.CITY_BUNDLE_KEY));
            setSupportActionBar(mToolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

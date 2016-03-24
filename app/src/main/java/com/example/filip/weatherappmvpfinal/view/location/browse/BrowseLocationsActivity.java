package com.example.filip.weatherappmvpfinal.view.location.browse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.filip.weatherappmvpfinal.R;

/**
 * Created by Filip on 10/02/2016.
 */
public class BrowseLocationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_locations);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragment();
    }

    private void initFragment() {
        if (getSupportFragmentManager().findFragmentById(R.id.browse_locations_activity_frame_layout) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.browse_locations_activity_frame_layout, new BrowseLocationsFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }
}

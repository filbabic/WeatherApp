package com.example.filip.weatherappmvpfinal.view.location.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.filip.weatherappmvpfinal.R;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddNewLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        initFragment();
    }

    private void initFragment() {
        if (getSupportFragmentManager().findFragmentById(R.id.add_location_activity_frame_layout) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_location_activity_frame_layout, new AddLocationFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }
}

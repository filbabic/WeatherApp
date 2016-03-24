package com.example.filip.weatherappmvpfinal.view.main;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.location.LocationHelper;
import com.example.filip.weatherappmvpfinal.helpers.location.LocationHelperImpl;
import com.example.filip.weatherappmvpfinal.adapter.CustomViewPagerFragmentAdapter;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.presenter.main.MainActivityPresenter;
import com.example.filip.weatherappmvpfinal.presenter.main.MainActivityPresenterImpl;
import com.example.filip.weatherappmvpfinal.view.location.add.AddNewLocationActivity;
import com.example.filip.weatherappmvpfinal.view.location.browse.BrowseLocationsActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MainView, GoogleApiClient.ConnectionCallbacks {
    private ViewPager viewPager;
    private CustomViewPagerFragmentAdapter adapter;
    private MainActivityPresenter presenter;
    private LocationHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initLocationHelper();
    }

    @Override
    protected void onStart() {
        super.onStart();
        helper.connectClient();
        initPresenter();
        initAdapter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.disconnectClient();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_add_new_location) {
            startActivity(new Intent(this, AddNewLocationActivity.class));
        }
        if (id == R.id.menu_browse_recent_locations) {
            startActivity(new Intent(this, BrowseLocationsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        viewPager = (ViewPager) findViewById(R.id.main_activity_view_pager);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initLocationHelper() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        GoogleApiClient client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
        helper = new LocationHelperImpl(geocoder, client);
    }

    private void initPresenter() {
        LocationDatabase database = new LocationDatabase(this);
        presenter = new MainActivityPresenterImpl(this, database);
    }

    private void initAdapter() {
        adapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());
        adapter.setAdapterData(presenter.getLocations());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location location = helper.getLocation();
        if (location != null) {
            String locationName = helper.getLocationFromGeocoder(location.getLatitude(), location.getLongitude());
            presenter.receiveDataFromLocationService(locationName);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void currentLocationOnSuccess(String locationName) {
        Toast.makeText(MainActivity.this, getString(R.string.added_current_location_toast_message) + locationName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAdapterData() {
        adapter.setAdapterData(presenter.getLocations());
    }

    @Override
    public void currentLocationOnFailure() {
        Toast.makeText(MainActivity.this, getString(R.string.current_location_failure_toast_message), Toast.LENGTH_SHORT).show();
    }
}
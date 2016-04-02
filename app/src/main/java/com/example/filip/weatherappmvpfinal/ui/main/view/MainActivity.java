package com.example.filip.weatherappmvpfinal.ui.main.view;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.filip.weatherappmvpfinal.helpers.database.DataManagerImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.location.LocationHelper;
import com.example.filip.weatherappmvpfinal.helpers.location.LocationHelperImpl;
import com.example.filip.weatherappmvpfinal.ui.adapter.view.CustomViewPagerFragmentAdapter;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.ui.main.presenter.MainActivityPresenter;
import com.example.filip.weatherappmvpfinal.ui.main.presenter.MainActivityPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.location.view.add.AddNewLocationActivity;
import com.example.filip.weatherappmvpfinal.ui.location.view.browse.BrowseLocationsActivity;
import com.example.filip.weatherappmvpfinal.utils.NetworkUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MainView, GoogleApiClient.ConnectionCallbacks {
    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;
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
        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPresenter();
        initAdapter();
        initNavigationDrawer();
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(this))
            helper.connectClient();
        updateAdapterData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.disconnectClient();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_activity_drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.main_activity_view_pager);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        mNavigationView = (NavigationView) findViewById(R.id.main_activity_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initLocationHelper() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        GoogleApiClient client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
        helper = new LocationHelperImpl(geocoder, client);
    }

    private void initPresenter() {
        LocationDatabase database = new LocationDatabase(this);
        presenter = new MainActivityPresenterImpl(this, new DatabaseHelperImpl(database, null, new DataManagerImpl()));
    }

    private void initAdapter() {
        adapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location location = helper.getCurrentLocation();
        if (location != null) {
            String currentLocation = helper.getLocationFromGeocoder(location.getLatitude(), location.getLongitude());
            addCurrentLocationToDatabase(currentLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onSuccess(String locationName) {
        Toast.makeText(MainActivity.this, getString(R.string.added_current_location_toast_message, locationName), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(MainActivity.this, getString(R.string.current_location_cannot_be_added_error_toast_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAdapterData() {
        adapter.setAdapterData(presenter.getLocations());
    }

    @Override
    public void addCurrentLocationToDatabase(@NonNull String location) {
        presenter.receiveDataFromLocationService(location);
    }

    private void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddNewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
            case R.id.menu_browse_recent_locations: {
                startActivity(new Intent(this, BrowseLocationsActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }
}
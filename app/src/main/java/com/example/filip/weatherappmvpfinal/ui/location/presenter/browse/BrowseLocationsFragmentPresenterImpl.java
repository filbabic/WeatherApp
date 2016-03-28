package com.example.filip.weatherappmvpfinal.ui.location.presenter.browse;

import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.database.WeatherDatabase;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public class BrowseLocationsFragmentPresenterImpl implements BrowseLocationsFragmentPresenter {
    private final DatabaseHelper databaseHelper;

    public BrowseLocationsFragmentPresenterImpl(LocationDatabase locationDatabase, WeatherDatabase weatherDatabase) {
        this.databaseHelper = new DatabaseHelperImpl(locationDatabase, weatherDatabase);
    }

    @Override
    public void deleteLocationFromDatabase(LocationWrapper wrapper) {
        databaseHelper.deleteLocation(wrapper);
    }

    @Override
    public ArrayList<LocationWrapper> getLocationsFromDatabase() {
        return databaseHelper.getLocations();
    }
}

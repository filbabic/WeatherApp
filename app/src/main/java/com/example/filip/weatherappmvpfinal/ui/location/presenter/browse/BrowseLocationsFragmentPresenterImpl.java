package com.example.filip.weatherappmvpfinal.ui.location.presenter.browse;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public class BrowseLocationsFragmentPresenterImpl implements BrowseLocationsFragmentPresenter {
    private final DatabaseHelper databaseHelper;

    public BrowseLocationsFragmentPresenterImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void deleteLocationFromDatabase(String locationName) {
        databaseHelper.deleteLocation(locationName);
    }

    @Override
    public ArrayList<LocationWrapper> getLocationsFromDatabase() {
        return databaseHelper.getLocations();
    }
}

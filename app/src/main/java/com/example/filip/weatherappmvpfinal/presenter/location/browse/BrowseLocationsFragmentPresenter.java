package com.example.filip.weatherappmvpfinal.presenter.location.browse;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public interface BrowseLocationsFragmentPresenter {
    void deleteLocationFromDatabase(LocationWrapper wrapper);

    ArrayList<LocationWrapper> getLocationsFromDatabase();
}

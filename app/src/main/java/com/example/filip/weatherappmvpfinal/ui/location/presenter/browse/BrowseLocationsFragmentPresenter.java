package com.example.filip.weatherappmvpfinal.ui.location.presenter.browse;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public interface BrowseLocationsFragmentPresenter {
    void deleteLocationFromDatabase(LocationWrapper wrapper);

    ArrayList<LocationWrapper> getLocationsFromDatabase();
}

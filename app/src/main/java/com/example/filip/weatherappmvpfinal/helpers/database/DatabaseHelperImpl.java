package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public class DatabaseHelperImpl implements DatabaseHelper {
    private final LocationDatabase database;

    public DatabaseHelperImpl(LocationDatabase database) {
        this.database = database;
    }

    @Override
    public void addLocation(LocationWrapper locationWrapper) {
        database.addLocation(locationWrapper);
    }

    @Override
    public void deleteLocation(LocationWrapper locationWrapper) {
        database.deleteLocation(locationWrapper);
    }

    @Override
    public boolean checkIfLocationExists(LocationWrapper locationWrapper) {
        return database.checkIfLocationAlreadyExists(locationWrapper);
    }

    @Override
    public ArrayList<LocationWrapper> getLocations() {
        return database.getLocations();
    }
}

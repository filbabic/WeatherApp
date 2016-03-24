package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public interface DatabaseHelper {
    void addLocation(LocationWrapper locationWrapper);

    void deleteLocation(LocationWrapper locationWrapper);

    boolean checkIfLocationExists(LocationWrapper locationWrapper);

    ArrayList<LocationWrapper> getLocations();
}

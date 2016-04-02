package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.List;

/**
 * Created by Filip on 02/04/2016.
 */
public class DataManagerImpl implements DataManager {
    @Override
    public boolean checkIfAlreadyExists(String locationToCheck, List<LocationWrapper> locationsThatExist) {
        for (LocationWrapper current : locationsThatExist) {
            if (current.getLocation().equalsIgnoreCase(locationToCheck)) return true;
        }
        return false;
    }
}

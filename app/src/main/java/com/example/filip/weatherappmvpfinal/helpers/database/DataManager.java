package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.List;

/**
 * Created by Filip on 02/04/2016.
 */
public interface DataManager {

    boolean checkIfAlreadyExists(String locationToCheck, List<LocationWrapper> locationsThatExist);
}

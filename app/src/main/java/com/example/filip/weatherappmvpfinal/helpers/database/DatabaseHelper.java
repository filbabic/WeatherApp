package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public interface DatabaseHelper {
    void addLocation(String locationName);

    void saveWeatherResponseToDatabase(WeatherResponse response, String city); //

    void updateWeatherResponseInDatabase(WeatherResponse response, String city); //

    boolean checkIfLocationIsCached(String city);//

    void deleteLocation(String locationName); //

    boolean checkIfLocationExists(String locationName);

    ArrayList<LocationWrapper> getLocations(); //

    WeatherResponse getResponseFromDatabase(String city); //
}

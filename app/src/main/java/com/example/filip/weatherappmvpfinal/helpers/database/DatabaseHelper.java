package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public interface DatabaseHelper {
    void addLocation(String locationName);

    void deleteLocation(String locationName);

    void saveWeatherResponseToDatabase(WeatherResponse response);

    void updateWeatherResponseInDatabase(WeatherResponse response);

    boolean alreadyExists(String cityName, String whichDatabase);

    WeatherResponse getCachedResponseFromWeatherDatabase(String city);

    ArrayList<LocationWrapper> getLocations(String whichDatabase);
}

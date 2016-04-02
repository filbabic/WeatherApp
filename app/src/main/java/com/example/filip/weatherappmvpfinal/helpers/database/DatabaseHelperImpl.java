package com.example.filip.weatherappmvpfinal.helpers.database;

import android.support.annotation.NonNull;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public class DatabaseHelperImpl implements DatabaseHelper {
    private final WeatherDatabase weatherDatabase;
    private final LocationDatabase locationDatabase;
    private final DataManager dataManager;

    public DatabaseHelperImpl(LocationDatabase locationDatabase, WeatherDatabase weatherDatabase, DataManager dataManager) {
        this.locationDatabase = locationDatabase;
        this.weatherDatabase = weatherDatabase;
        this.dataManager = dataManager;
    }

    @Override
    public void addLocation(String locationName) {
        locationDatabase.addLocation(locationName);
    }

    @Override
    public void saveWeatherResponseToDatabase(WeatherResponse response) {
        weatherDatabase.addWeatherResponseToDatabase(response);
    }

    @Override
    public void updateWeatherResponseInDatabase(WeatherResponse response) {
        weatherDatabase.updateWeatherResponseInDatabase(response);
    }

    @Override
    public boolean alreadyExists(@NonNull String cityName, String whichDatabase) {
        if (whichDatabase.equals(Constants.LOCATIONS_DATABASE))
            return dataManager.checkIfAlreadyExists(cityName, locationDatabase.getLocations());
        else if (whichDatabase.equals(Constants.WEATHER_DATABASE))
            return dataManager.checkIfAlreadyExists(cityName, weatherDatabase.getLocations());
        return true;
    }

    @Override
    public void deleteLocation(String locationName) {
        locationDatabase.deleteLocation(locationName);
        weatherDatabase.deleteAResponseFromDatabase(locationName);
    }

    @Override
    public WeatherResponse getCachedResponseFromWeatherDatabase(String city) {
        return weatherDatabase.getWeatherResponseForCertainCity(city);
    }

    @Override
    public ArrayList<LocationWrapper> getLocations(String whichDatabase) {
        if (whichDatabase.equals(Constants.LOCATIONS_DATABASE))
            return locationDatabase.getLocations();
        else if (whichDatabase.equals(Constants.WEATHER_DATABASE))
            return weatherDatabase.getLocations();
        return null;
    }
}

package com.example.filip.weatherappmvpfinal.helpers.database;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by Filip on 24/03/2016.
 */
public class DatabaseHelperImpl implements DatabaseHelper {
    private final WeatherDatabase weatherDatabase;
    private final LocationDatabase locationDatabase;

    public DatabaseHelperImpl(LocationDatabase locationDatabase, WeatherDatabase weatherDatabase) {
        this.locationDatabase = locationDatabase;
        this.weatherDatabase = weatherDatabase;
    }

    @Override
    public void addLocation(LocationWrapper locationWrapper) {
        locationDatabase.addLocation(locationWrapper);
    }

    @Override
    public void saveWeatherResponseToDatabase(WeatherResponse response, String city) {
        weatherDatabase.addWeatherResponseToDatabase(response, city);
    }

    @Override
    public void updateWeatherResponseInDatabase(WeatherResponse response, String city) {
        weatherDatabase.updateWeatherResponseInDatabase(response, city);
    }

    @Override
    public boolean locationIsCached(String city) {
        return weatherDatabase.locationExistsInDatabase(city);
    }

    @Override
    public void deleteLocation(LocationWrapper locationWrapper) {
        locationDatabase.deleteLocation(locationWrapper);
        weatherDatabase.deleteAResponseFromDatabase(locationWrapper.getLocation());
    }

    @Override
    public boolean checkIfLocationExists(LocationWrapper locationWrapper) {
        return locationDatabase.checkIfLocationAlreadyExists(locationWrapper);
    }

    @Override
    public ArrayList<LocationWrapper> getLocations() {
        return locationDatabase.getLocations();
    }

    @Override
    public WeatherResponse getResponseFromDatabase(String city) {
        return weatherDatabase.getWeatherResponseForCertainCity(city);
    }
}

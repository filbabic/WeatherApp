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
    public void addLocation(String locationName) {
        locationDatabase.addLocation(locationName);
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
    public boolean checkIfLocationIsCached(String city) {
        return weatherDatabase.checkIfLocationIsCachedInDatabase(city);
    }

    @Override
    public void deleteLocation(String locationName) {
        locationDatabase.deleteLocation(locationName);
        weatherDatabase.deleteAResponseFromDatabase(locationName);
    }

    @Override
    public boolean checkIfLocationExists(String locationName) {
        return locationDatabase.checkIfLocationAlreadyExistsInDatabase(locationName);
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

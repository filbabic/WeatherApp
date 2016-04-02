package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DataManager;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.database.WeatherDatabase;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Filip on 30/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseHelperTest {
    @Mock
    private LocationDatabase locationDatabase;
    @Mock
    private WeatherDatabase weatherDatabase;
    @Mock
    private DataManager dataManager;

    private DatabaseHelper databaseHelper;
    @Mock
    private WeatherResponse weatherResponse;

    private ArrayList<LocationWrapper> locationWrappers;

    private final String cityMock = "cityMock";

    @Before
    public void setUp() throws Exception {
        databaseHelper = new DatabaseHelperImpl(locationDatabase, weatherDatabase, dataManager);
        locationWrappers = new ArrayList<>();
    }

    @Test
    public void shouldCallAddLocationToDatabase() throws Exception {
        databaseHelper.addLocation(cityMock);

        verify(locationDatabase).addLocation(cityMock);
    }

    @Test
    public void shouldCallDeleteMethodInDatabase() throws Exception {
        databaseHelper.deleteLocation(cityMock);

        verify(weatherDatabase).deleteAResponseFromDatabase(cityMock);
        verify(locationDatabase).deleteLocation(cityMock);
    }

    @Test
    public void shouldVerifyIfLocationExistsInLocationDatabase() throws Exception {
        when(dataManager.checkIfAlreadyExists(anyString(), anyListOf(LocationWrapper.class))).thenReturn(false);
        when(locationDatabase.getLocations()).thenReturn(locationWrappers);

        assertEquals(databaseHelper.alreadyExists(cityMock, Constants.LOCATIONS_DATABASE), false);
        assertEquals(databaseHelper.getLocations(Constants.LOCATIONS_DATABASE), locationWrappers);
        verify(locationDatabase, times(2)).getLocations();
        verify(dataManager).checkIfAlreadyExists(cityMock, locationWrappers);
    }

    @Test
    public void shouldVerifyIfLocationExistsInWeatherDatabase() throws Exception {
        when(dataManager.checkIfAlreadyExists(anyString(), anyListOf(LocationWrapper.class))).thenReturn(false);
        when(weatherDatabase.getLocations()).thenReturn(locationWrappers);

        assertEquals(databaseHelper.alreadyExists(cityMock, Constants.WEATHER_DATABASE), false);
        assertEquals(databaseHelper.getLocations(Constants.WEATHER_DATABASE), locationWrappers);
        verify(weatherDatabase, times(2)).getLocations();
        verify(dataManager, times(1)).checkIfAlreadyExists(cityMock, locationWrappers);
    }

    @Test
    public void shouldCallSaveResponseToDatabase() throws Exception {
        databaseHelper.saveWeatherResponseToDatabase(weatherResponse);

        verify(weatherDatabase).addWeatherResponseToDatabase(weatherResponse);
    }

    @Test
    public void shouldCallUpdateResponseToDatabase() throws Exception {
        databaseHelper.updateWeatherResponseInDatabase(weatherResponse);

        verify(weatherDatabase).updateWeatherResponseInDatabase(weatherResponse);
    }

    @Test
    public void shouldCallGetResponseFromDatabase() throws Exception {
        databaseHelper.getCachedResponseFromWeatherDatabase(cityMock);

        verify(weatherDatabase).getWeatherResponseForCertainCity(cityMock);
    }

    @Test
    public void shouldCallGetLocationsMethodInDatabase() throws Exception {
        databaseHelper.getLocations(Constants.LOCATIONS_DATABASE);

        verify(locationDatabase).getLocations();
    }

    @Test
    public void shouldReturnMockedWeatherResponse() throws Exception {
        when(weatherDatabase.getWeatherResponseForCertainCity(anyString())).thenReturn(weatherResponse);
        assertEquals(databaseHelper.getCachedResponseFromWeatherDatabase(cityMock), weatherResponse);
        verify(weatherDatabase).getWeatherResponseForCertainCity(cityMock);
    }
}

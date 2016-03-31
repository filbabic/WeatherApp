package com.example.filip.weatherappmvpfinal;

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

    private DatabaseHelper databaseHelper;
    @Mock
    private WeatherResponse weatherResponse;

    private ArrayList<LocationWrapper> locationWrappers;

    private final String cityMock = "cityMock";

    @Before
    public void setUp() throws Exception {
        databaseHelper = new DatabaseHelperImpl(locationDatabase, weatherDatabase);
        locationWrappers = new ArrayList<>();
    }

    @Test
    public void shouldCallDeleteMethodInDatabase() throws Exception {
        databaseHelper.deleteLocation(cityMock);

        verify(weatherDatabase).deleteAResponseFromDatabase(cityMock);
        verify(locationDatabase).deleteLocation(cityMock);
    }

    @Test
    public void shouldCallGetLocationsMethodInDatabase() throws Exception {
        databaseHelper.getLocations();

        verify(locationDatabase).getLocations();
    }

    @Test
    public void shouldCallSaveResponseToDatabase() throws Exception {
        databaseHelper.saveWeatherResponseToDatabase(weatherResponse, cityMock);

        verify(weatherDatabase).addWeatherResponseToDatabase(weatherResponse, cityMock);
    }

    @Test
    public void shouldCallUpdateResponseToDatabase() throws Exception {
        databaseHelper.updateWeatherResponseInDatabase(weatherResponse, cityMock);

        verify(weatherDatabase).updateWeatherResponseInDatabase(weatherResponse, cityMock);
    }

    @Test
    public void shouldCallCheckIfLocationAlreadyExists() throws Exception {
        databaseHelper.checkIfLocationExists(cityMock);

        verify(locationDatabase).checkIfLocationAlreadyExistsInDatabase(cityMock);
    }

    @Test
    public void shouldCallCheckIfLocationIsCached() throws Exception {
        databaseHelper.checkIfLocationIsCached(cityMock);

        verify(weatherDatabase).checkIfLocationIsCachedInDatabase(cityMock);
    }

    @Test
    public void shouldCallGetResponseFromDatabase() throws Exception {
        databaseHelper.getResponseFromDatabase(cityMock);

        verify(weatherDatabase).getWeatherResponseForCertainCity(cityMock);
    }

    @Test
    public void shouldCallAddLocationToDatabase() throws Exception {
        databaseHelper.addLocation(cityMock);

        verify(locationDatabase).addLocation(cityMock);
    }

    @Test
    public void shouldReturnFalseWhenCallingResponseIsCachedInDatabaseMethod() throws Exception {
        when(weatherDatabase.checkIfLocationIsCachedInDatabase(cityMock)).thenReturn(false);
        assertEquals(databaseHelper.checkIfLocationIsCached(cityMock), false);
        verify(weatherDatabase).checkIfLocationIsCachedInDatabase(cityMock);
    }

    @Test
    public void shouldReturnTrueWhenCallingResponseIsCachedInDatabaseMethod() throws Exception {
        when(weatherDatabase.checkIfLocationIsCachedInDatabase(cityMock)).thenReturn(true);
        assertEquals(databaseHelper.checkIfLocationIsCached(cityMock), true);
        verify(weatherDatabase).checkIfLocationIsCachedInDatabase(cityMock);
    }

    @Test
    public void shouldReturnFalseWhenCallingLocationExistsInDatabase() throws Exception {
        when(locationDatabase.checkIfLocationAlreadyExistsInDatabase(cityMock)).thenReturn(false);
        assertEquals(databaseHelper.checkIfLocationExists(cityMock), false);
        verify(locationDatabase).checkIfLocationAlreadyExistsInDatabase(cityMock);
    }

    @Test
    public void shouldReturnTrueWhenCallingLocationExistsInDatabase() throws Exception {
        when(locationDatabase.checkIfLocationAlreadyExistsInDatabase(cityMock)).thenReturn(true);
        assertEquals(databaseHelper.checkIfLocationExists(cityMock), true);
        verify(locationDatabase).checkIfLocationAlreadyExistsInDatabase(cityMock);
    }

    @Test
    public void shouldReturnMockedLocationWrappers() throws Exception {
        when(locationDatabase.getLocations()).thenReturn(locationWrappers);
        assertEquals(databaseHelper.getLocations(), locationWrappers);
        verify(locationDatabase).getLocations();
    }

    @Test
    public void shouldReturnMockedWeatherResponse() throws Exception {
        when(weatherDatabase.getWeatherResponseForCertainCity(cityMock)).thenReturn(weatherResponse);
        assertEquals(databaseHelper.getResponseFromDatabase(cityMock), weatherResponse);
        verify(weatherDatabase).getWeatherResponseForCertainCity(cityMock);
    }
}

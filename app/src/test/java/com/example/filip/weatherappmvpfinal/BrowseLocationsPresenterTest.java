package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.browse.BrowseLocationsFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.browse.BrowseLocationsFragmentPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by Filip on 30/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class BrowseLocationsPresenterTest {

    private BrowseLocationsFragmentPresenter presenter;
    @Mock
    private DatabaseHelper databaseHelper;

    private ArrayList<LocationWrapper> locationWrappers;

    @Before
    public void setUp() throws Exception {
        presenter = new BrowseLocationsFragmentPresenterImpl(databaseHelper);
        locationWrappers = new ArrayList<>();
    }

    @Test
    public void shouldCallDeleteLocationFromDatabase() throws Exception {
        String cityMock = "cityMock";
        presenter.deleteLocationFromDatabase(cityMock);

        verify(databaseHelper).deleteLocation(cityMock);
    }

    @Test
    public void shouldReturnMockedLocationWrappers() throws Exception {
        when(databaseHelper.getLocations()).thenReturn(locationWrappers);
        assertEquals(presenter.getLocationsFromDatabase(), locationWrappers);

        verify(databaseHelper).getLocations();
    }
}

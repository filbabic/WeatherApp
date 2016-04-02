package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.main.presenter.MainActivityPresenter;
import com.example.filip.weatherappmvpfinal.ui.main.presenter.MainActivityPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.main.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by Filip on 02/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    private MainActivityPresenter presenter;

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private MainView mainView;

    @Mock
    private ArrayList<LocationWrapper> locationWrappers;

    private final String cityMock = "cityMock";

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenterImpl(mainView, databaseHelper);
    }

    @Test
    public void shouldCallLocationAddedSuccessfully() throws Exception {
        when(databaseHelper.alreadyExists(anyString(), anyString())).thenReturn(false);
        presenter.receiveDataFromLocationService(cityMock);

        verify(databaseHelper).alreadyExists(cityMock, Constants.LOCATIONS_DATABASE);
        verify(databaseHelper).addLocation(cityMock);
        verify(mainView).onSuccess(cityMock);
        verify(mainView).updateAdapterData();
    }

    @Test
    public void shouldCallLocationAlreadyAdded() throws Exception {
        when(databaseHelper.alreadyExists(anyString(), anyString())).thenReturn(true);
        presenter.receiveDataFromLocationService(cityMock);

        verify(databaseHelper).alreadyExists(cityMock, Constants.LOCATIONS_DATABASE);
        verify(mainView).onFailure();
    }

    @Test
    public void shouldReturnLocationWrappers() throws Exception {
        when(databaseHelper.getLocations(anyString())).thenReturn(locationWrappers);
        assertEquals(presenter.getLocations(), locationWrappers);

        verify(databaseHelper).getLocations(Constants.LOCATIONS_DATABASE);
    }
}

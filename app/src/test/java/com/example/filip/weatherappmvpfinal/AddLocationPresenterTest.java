package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DataManager;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.add.AddLocationFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.add.AddLocationPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.location.view.add.AddLocationView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Filip on 30/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddLocationPresenterTest {
    @Mock
    private AddLocationView addLocationView;

    private AddLocationFragmentPresenter presenter;

    @Mock
    private DatabaseHelper databaseHelper;
    @Mock
    private DataManager dataManager;
    @Mock
    private ArrayList<LocationWrapper> locationWrappers;

    private final String cityMock = "cityMock";


    @Before
    public void setUp() throws Exception {
        presenter = new AddLocationPresenterImpl(addLocationView, databaseHelper);
    }

    @Test
    public void shouldShowLocationAlreadyExistsError() throws Exception {
        when(databaseHelper.alreadyExists(anyString(), anyString())).thenReturn(true);
        presenter.addLocationToDatabase(cityMock);

        verify(databaseHelper).alreadyExists(cityMock, Constants.LOCATIONS_DATABASE);
        verify(addLocationView).onLocationAlreadyExistsError();
    }

    @Test
    public void shouldShowOnLocationAddedSuccessfullyMessage() throws Exception {
        when(databaseHelper.alreadyExists(anyString(), anyString())).thenReturn(false);
        presenter.addLocationToDatabase(cityMock);
        verify(databaseHelper).alreadyExists(cityMock, Constants.LOCATIONS_DATABASE);
        verify(databaseHelper).addLocation(cityMock);
        verify(addLocationView).onSuccess();
    }

    @Test
    public void shouldShowLocationCannotBeEmptyError() throws Exception {
        String emptyCityMock = "";
        presenter.addLocationToDatabase(emptyCityMock);

        verify(addLocationView).onEmptyStringRequestError();
    }
}

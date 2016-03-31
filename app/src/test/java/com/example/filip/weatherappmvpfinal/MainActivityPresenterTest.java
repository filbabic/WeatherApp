package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.ui.main.presenter.MainActivityPresenter;
import com.example.filip.weatherappmvpfinal.ui.main.presenter.MainActivityPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.main.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Filip on 30/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    @Mock
    private DatabaseHelper databaseHelper;
    @Mock
    private MainView mainView;

    private String cityMock = "cityMock";

    private MainActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenterImpl(mainView, databaseHelper);
    }

    @Test
    public void shouldCallMainViewOnSuccessMethods() throws Exception {
        when(databaseHelper.checkIfLocationExists(cityMock)).thenReturn(false);
        presenter.receiveDataFromLocationService(cityMock);

        verify(databaseHelper).addLocation(cityMock);
        verify(mainView).updateAdapterData();
        verify(mainView).onSuccess(cityMock);
    }

    @Test
    public void shouldCallMainViewOnFailure() throws Exception {
        when(databaseHelper.checkIfLocationExists(cityMock)).thenReturn(true);
        presenter.receiveDataFromLocationService(cityMock);

        verify(mainView).onFailure();
    }
}

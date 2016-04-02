package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelper;
import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.weather.view.WeatherFragmentView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Filip on 30/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherFragmentPresenterTest {
    private WeatherFragmentPresenter presenter;

    @Mock
    private DatabaseHelper databaseHelper;
    @Mock
    private NetworkingHelper networkingHelper;
    @Mock
    private WeatherFragmentView weatherFragmentView;

    @Mock
    private Wind wind;
    @Mock
    private Main main;

    private WeatherResponse weatherResponse;

    @Mock
    private ResponseListener<WeatherResponse> listener;

    private final String cityMock = "cityMock";
    private final String emptyString = "";

    @Before
    public void setUp() throws Exception {
        presenter = new WeatherFragmentPresenterImpl(weatherFragmentView, databaseHelper, networkingHelper);
        Weather weather = new Weather(Constants.SNOW_CASE, emptyString);
        weatherResponse = new WeatherResponse(new Weather[]{weather}, main, wind, null, cityMock);
    }

    @Test
    public void shouldCallViewOnFailure() throws Exception {
        when(databaseHelper.getCachedResponseFromWeatherDatabase(anyString())).thenReturn(null);

        presenter.getLastStoredRequestFromDatabase(cityMock);
        verify(databaseHelper).getCachedResponseFromWeatherDatabase(cityMock);
        verify(weatherFragmentView).onFailure();
    }

    @Test
    public void shouldCallViewSetTextMethods() throws Exception {
        when(databaseHelper.getCachedResponseFromWeatherDatabase(cityMock)).thenReturn(weatherResponse);
        presenter.getLastStoredRequestFromDatabase(cityMock);

        verify(databaseHelper).getCachedResponseFromWeatherDatabase(cityMock);
        verify(weatherFragmentView).setDescriptionValues(emptyString);
        verify(weatherFragmentView).setPressureValues(0);
        verify(weatherFragmentView).setCurrentTemperatureValues(-273);
        verify(weatherFragmentView).setMinTemperatureValues(-273);
        verify(weatherFragmentView).setMaxTemperatureValues(-273);
        verify(weatherFragmentView).setWeatherIcon(Constants.SNOW);
        verify(weatherFragmentView).setWindValues(0);
    }
}

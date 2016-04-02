package com.example.filip.weatherappmvpfinal;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;
import com.example.filip.weatherappmvpfinal.ui.adapter.presenter.ViewHolderPresenter;
import com.example.filip.weatherappmvpfinal.ui.adapter.presenter.ViewViewHolderPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.adapter.view.ViewHolderView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by Filip on 02/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewHolderPresenterTest {

    @Mock
    private ViewHolderView viewHolderView;
    @Mock
    private Main main;
    @Mock
    private Wind wind;

    private Weather weather;
    private WeatherResponse weatherResponse;
    private ViewHolderPresenter presenter;

    private final String snowMock = "Snow";
    private final String emptyString = "";
    private final String dateMock = "11.12.1996";
    private final String cityMock = "cityMock";

    @Before
    public void setUp() throws Exception {
        weather = new Weather(snowMock, emptyString);
        weatherResponse = new WeatherResponse(new Weather[]{weather}, main, wind, dateMock, cityMock);
        presenter = new ViewViewHolderPresenterImpl(viewHolderView);
    }

    @Test
    public void shouldCallHolderViewMethods() throws Exception {
        presenter.createValuesForViewToDisplay(weatherResponse);

        verify(viewHolderView).setHumidityTextView(0);
        verify(viewHolderView).setPressureTextView(0);
        verify(viewHolderView).setWindDirectionTextView(Constants.WIND_NORTH);
        verify(viewHolderView).setWeatherIcon(Constants.SNOW);
        verify(viewHolderView).setWindSpeedTextView(0);
        verify(viewHolderView).setCurrentTemperatureTextView(-273);
        verify(viewHolderView).setMaxTemperatureTextView(-273);
        verify(viewHolderView).setMinTemperatureTextView(-273);
        verify(viewHolderView).setDescription(emptyString);
        verify(viewHolderView).setTimeOfDay(dateMock);
    }
}

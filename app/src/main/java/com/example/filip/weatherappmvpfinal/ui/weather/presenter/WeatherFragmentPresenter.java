package com.example.filip.weatherappmvpfinal.ui.weather.presenter;

import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

/**
 * Created by Filip on 26/03/2016.
 */
public interface WeatherFragmentPresenter {
    void sendRequestToAPI(String city);

    void getLastStoredRequestFromDatabase(String city);

    void createWeatherStringsForView(WeatherResponse response);
}

package com.example.filip.weatherappmvpfinal.presenter.adapter;

import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;

/**
 * Created by Filip on 19/02/2016.
 */
public interface ViewHolderPresenter {
    void createValues(WeatherResponse response);

    void createTemperatureValues(Main main);

    void createWindValues(Wind wind);

    void createPressureValues(Main main);

    void createDescriptionValues(Weather weather);

    void createTimeOfDay(String timeOfDay);

    void createWeatherIconValue(String description);

    double toCelsiusFromKelvin(double temperature);

    String directionOfWind(double degrees);
}

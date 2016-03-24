package com.example.filip.weatherappmvpfinal.adapter;

import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

/**
 * Created by Filip on 19/02/2016.
 */
public interface ViewHolderView {
    void sendDataToPresenter(WeatherResponse response);
    void setTemperature(String temperature);
    void setPressure(String pressure);
    void setWind(String wind);
    void setDescription(String description);
    void setWeatherIcon(String weatherIcon);
    void setTimeOfDay(String timeOfDay);
}

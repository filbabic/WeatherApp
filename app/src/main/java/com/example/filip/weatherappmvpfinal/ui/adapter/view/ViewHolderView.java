package com.example.filip.weatherappmvpfinal.ui.adapter.view;

import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

/**
 * Created by Filip on 19/02/2016.
 */
public interface ViewHolderView {
    void sendDataToPresenter(WeatherResponse response);

    void setCurrentTemperatureTextView(double currentTemperature);

    void setMinTemperatureTextView(double minTemperature);

    void setMaxTemperatureTextView(double maxTemperature);

    void setPressureTextView(double pressure);

    void setHumidityTextView(int humidity);

    void setWindSpeedTextView(double windSpeed);

    void setWindDirectionTextView(String direction);

    void setDescription(String description);

    void setWeatherIcon(String weatherIcon);

    void setTimeOfDay(String timeOfDay);
}

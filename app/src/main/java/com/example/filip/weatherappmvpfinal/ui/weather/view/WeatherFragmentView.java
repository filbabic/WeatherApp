package com.example.filip.weatherappmvpfinal.ui.weather.view;

/**
 * Created by Filip on 26/03/2016.
 */
public interface WeatherFragmentView {
    void setTemperatureValues(String temperatureValues);

    void setPressureValues(String pressureValues);

    void setWindValues(String windValues);

    void setWeatherIcon(String iconPath);

    void setDescriptionValues(String descriptionValues);

    void onFailure();
}

package com.example.filip.weatherappmvpfinal.ui.weather.view;

/**
 * Created by Filip on 26/03/2016.
 */
public interface WeatherFragmentView {
    void setCurrentTemperatureValues(double currentTemperatureValues);

    void setMinTemperatureValues(double minTemperatureValues);

    void setMaxTemperatureValues(double maxTemperatureValues);

    void setPressureValues(double pressureValues);

    void setWindValues(double windValues);

    void setWeatherIcon(String iconPath);

    void setDescriptionValues(String descriptionValues);

    void onFailure();
}

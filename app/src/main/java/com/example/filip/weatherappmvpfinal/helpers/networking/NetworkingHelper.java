package com.example.filip.weatherappmvpfinal.helpers.networking;

import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.pojo.Forecast;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

/**
 * Created by Filip on 24/03/2016.
 */
public interface NetworkingHelper {
    void requestForecastFromAPI(String city, ResponseListener<Forecast> listener);

    void requestWeatherFromAPI(String city, ResponseListener<WeatherResponse> listener);
}

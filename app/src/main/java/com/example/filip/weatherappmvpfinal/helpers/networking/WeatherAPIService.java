package com.example.filip.weatherappmvpfinal.helpers.networking;

import com.example.filip.weatherappmvpfinal.pojo.Forecast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Filip on 10/02/2016.
 */
public interface WeatherAPIService {
    @GET("/data/2.5/forecast")
    Call<Forecast> getForecast(@QueryMap Map<String, String> map);
}

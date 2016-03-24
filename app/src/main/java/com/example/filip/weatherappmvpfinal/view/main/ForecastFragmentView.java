package com.example.filip.weatherappmvpfinal.view.main;

import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public interface ForecastFragmentView {
    void onSuccess(ArrayList<WeatherResponse> response);
    void onFailure();
}

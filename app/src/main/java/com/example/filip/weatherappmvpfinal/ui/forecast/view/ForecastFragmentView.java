package com.example.filip.weatherappmvpfinal.ui.forecast.view;

import android.support.annotation.NonNull;

import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public interface ForecastFragmentView {
    void onSuccess(@NonNull ArrayList<WeatherResponse> response);

    void onFailure();
}

package com.example.filip.weatherappmvpfinal.helpers.networking;

import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.pojo.Forecast;

/**
 * Created by Filip on 24/03/2016.
 */
public interface NetworkingHelper {
    void sendRequestToAPI(String city, ResponseListener<Forecast> listener);
}

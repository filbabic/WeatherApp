package com.example.filip.weatherappmvpfinal.helpers.networking;

import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.pojo.Forecast;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Filip on 24/03/2016.
 */
public class NetworkingHelperImpl implements NetworkingHelper {
    private final WeatherAPIService service;

    public NetworkingHelperImpl(WeatherAPIService service) {
        this.service = service;
    }

    @Override
    public void requestForecastFromAPI(String city, final ResponseListener<Forecast> listener) {
        Call<Forecast> getForecast = service.getForecast(RetrofitUtils.createQueryMap(city));
        getForecast.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response != null)
                    listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    @Override
    public void requestWeatherFromAPI(final String city, final ResponseListener<WeatherResponse> listener) {
        Call<WeatherResponse> getWeather = service.getWeather(RetrofitUtils.createQueryMap(city));
        getWeather.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response != null) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        weatherResponse.setCityName(city);
                        listener.onSuccess(weatherResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}

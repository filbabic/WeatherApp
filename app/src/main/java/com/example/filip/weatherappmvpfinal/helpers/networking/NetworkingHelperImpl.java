package com.example.filip.weatherappmvpfinal.helpers.networking;

import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.pojo.Forecast;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 24/03/2016.
 */
public class NetworkingHelperImpl implements NetworkingHelper {
    private WeatherAPIService service = new Retrofit.Builder().baseUrl(Constants.WEATHER_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(WeatherAPIService.class);

    @Override
    public void requestForecastFromAPI(String city, final ResponseListener<Forecast> listener) {
        Call<Forecast> getForecast = service.getForecast(RetrofitRequestHelper.createQueryMap(city));
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
    public void requestWeatherFromAPI(String city, final ResponseListener<WeatherResponse> listener) {
        Call<WeatherResponse> getWeather = service.getWeather(RetrofitRequestHelper.createQueryMap(city));
        getWeather.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response != null)
                    listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}

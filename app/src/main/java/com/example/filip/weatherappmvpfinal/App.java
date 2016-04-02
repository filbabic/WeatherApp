package com.example.filip.weatherappmvpfinal;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelper;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.networking.WeatherAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 01/04/2016.
 */
public class App extends Application {

    private NetworkingHelper networkingHelper;


    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        Retrofit retrofit = provideRestClient();

        WeatherAPIService weatherAPIService = createWeatherAPIService(retrofit);

        networkingHelper = new NetworkingHelperImpl(weatherAPIService);
    }

    public static App getInstance() {
        return sInstance;
    }

    public NetworkingHelper getNetworkingHelper() {
        return networkingHelper;
    }

    @NonNull
    private WeatherAPIService createWeatherAPIService(Retrofit retrofit) {
        return retrofit.create(WeatherAPIService.class);
    }

    @NonNull
    private Retrofit provideRestClient() {
        return new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

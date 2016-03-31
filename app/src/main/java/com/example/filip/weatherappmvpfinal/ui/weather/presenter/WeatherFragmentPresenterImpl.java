package com.example.filip.weatherappmvpfinal.ui.weather.presenter;

import android.annotation.SuppressLint;

import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelper;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelperImpl;
import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;
import com.example.filip.weatherappmvpfinal.ui.weather.view.WeatherFragmentView;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragmentPresenterImpl implements WeatherFragmentPresenter {
    private final NetworkingHelper networkingHelper;
    private final WeatherFragmentView weatherFragmentView;
    private final DatabaseHelper databaseHelper;

    public WeatherFragmentPresenterImpl(WeatherFragmentView weatherFragmentView, DatabaseHelper helper, NetworkingHelper networkingHelper) {
        this.networkingHelper = networkingHelper;
        this.weatherFragmentView = weatherFragmentView;
        this.databaseHelper = helper;
    }

    @Override
    public void sendRequestToAPI(final String city) {
        networkingHelper.requestWeatherFromAPI(city, new ResponseListener<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse callback) {
                if (callback != null) {
                    createWeatherStringsForView(callback);
                    if (databaseHelper.checkIfLocationIsCached(city))
                        databaseHelper.updateWeatherResponseInDatabase(callback, city);
                    else databaseHelper.saveWeatherResponseToDatabase(callback, city);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getLastStoredRequestFromDatabase(String city) {
        WeatherResponse response = databaseHelper.getResponseFromDatabase(city);
        if (response != null)
            createWeatherStringsForView(response);
        else weatherFragmentView.onFailure();
    }

    @Override
    public void createWeatherStringsForView(WeatherResponse response) {
        Weather weatherObject = response.getWeatherObject();
        if (weatherObject != null) {
            createDescriptionValues(weatherObject);
            createWeatherIconValue(weatherObject.getMain());
        }
        Main main = response.getMain();
        if (main != null) {
            createTemperatureValues(response.getMain());
            createPressureValues(response.getMain());
        }
        Wind wind = response.getWind();
        if (wind != null)
            createWindValues(response.getWind());
    }

    @SuppressLint("DefaultLocale")
    private void createTemperatureValues(Main main) {
        String current = String.format("%.2f", toCelsiusFromKelvin(main.getTemp()));
        String max = String.format("%.2f", toCelsiusFromKelvin(main.getTemp_max()));
        String min = String.format("%.2f", toCelsiusFromKelvin(main.getTemp_min()));
        String temperature = "Current: " + current + "C" + "\n Average: " + min + "C - " + max + "C";
        weatherFragmentView.setTemperatureValues(temperature);
    }

    private void createWindValues(Wind wind) {
        String windValues = "Wind: " + wind.getSpeed() + "km/h";
        weatherFragmentView.setWindValues(windValues);
    }

    private void createPressureValues(Main main) {
        String pressure = main.getPressure() + " hpa";
        weatherFragmentView.setPressureValues(pressure);
    }

    private void createDescriptionValues(Weather weather) {
        String description = weather.getDescription();
        weatherFragmentView.setDescriptionValues(description);
    }

    private void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case "Snow": {
                    weatherFragmentView.setWeatherIcon("13d.png");
                    break;
                }
                case "Rain": {
                    weatherFragmentView.setWeatherIcon("09d.png");
                    break;
                }
                case "Clear": {
                    weatherFragmentView.setWeatherIcon("01d.png");
                    break;
                }
                case "Mist": {
                    weatherFragmentView.setWeatherIcon("50d.png");
                    break;
                }
                case "Fog": {
                    weatherFragmentView.setWeatherIcon("50d.png");
                    break;
                }
                case "Haze": {
                    weatherFragmentView.setWeatherIcon("50d.png");
                    break;
                }

                case "Clouds": {
                    weatherFragmentView.setWeatherIcon("03d.png");
                    break;
                }
            }
    }

    private double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }
}

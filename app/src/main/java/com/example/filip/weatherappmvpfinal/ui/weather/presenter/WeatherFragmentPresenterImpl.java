package com.example.filip.weatherappmvpfinal.ui.weather.presenter;

import android.annotation.SuppressLint;

import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
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

    public WeatherFragmentPresenterImpl(WeatherFragmentView weatherFragmentView) {
        this.networkingHelper = new NetworkingHelperImpl();
        this.weatherFragmentView = weatherFragmentView;
    }

    @Override
    public void sendRequestToAPI(String city) {
        networkingHelper.requestWeatherFromAPI(city, new ResponseListener<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse callback) {
                if (callback != null)
                    createWeatherStringsForView(callback);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void createWeatherStringsForView(WeatherResponse response) {
        createTemperatureValues(response.getMain());
        createDescriptionValues(response.getWeatherObject());
        createPressureValues(response.getMain());
        createWeatherIconValue(response.getWeatherObject().getMain());
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
        String windValues = "Wind is blowing " + wind.getSpeed() + "m/s" + " " + directionOfWind(wind.getDeg());
        weatherFragmentView.setWindValues(windValues);
    }

    private void createPressureValues(Main main) {
        String pressure = "Air pressure is: " + main.getPressure() + "hpa";
        weatherFragmentView.setPressureValues(pressure);
    }

    private void createDescriptionValues(Weather weather) {
        String description = "Weather status: " + weather.getMain() + ", details: " + weather.getDescription();
        weatherFragmentView.setDescriptionValues(description);
    }

    private void createWeatherIconValue(String description) {
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

    private String directionOfWind(double degrees) {
        if (degrees >= 315 && degrees < 45) return "North";
        if (degrees >= 45 && degrees < 135) return "East";
        if (degrees >= 135 && degrees < 225) return "South";
        if (degrees >= 225 && degrees < 315) return "West";
        return null;
    }
}

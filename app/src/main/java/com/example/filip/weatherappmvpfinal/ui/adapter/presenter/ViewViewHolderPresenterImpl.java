package com.example.filip.weatherappmvpfinal.ui.adapter.presenter;

import android.annotation.SuppressLint;

import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;
import com.example.filip.weatherappmvpfinal.ui.adapter.view.ViewHolderView;

/**
 * Created by Filip on 19/02/2016.
 */
public class ViewViewHolderPresenterImpl implements ViewHolderPresenter {
    private final ViewHolderView viewHolderView;

    public ViewViewHolderPresenterImpl(ViewHolderView view) {
        this.viewHolderView = view;
    }

    @Override
    public void createValuesForViewToDisplay(WeatherResponse response) {
        createTemperatureValues(response.getMain());
        createDescriptionValues(response.getWeatherObject());
        createPressureValues(response.getMain());
        createTimeOfDay(response.getDt_txt());
        createWindValues(response.getWind());
        createWeatherIconValue(response.getWeatherObject().getMain());
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void createTemperatureValues(Main main) {
        String current = String.format("%.2f", toCelsiusFromKelvin(main.getTemp()));
        String max = String.format("%.2f", toCelsiusFromKelvin(main.getTemp_max()));
        String min = String.format("%.2f", toCelsiusFromKelvin(main.getTemp_min()));
        String temperature = "Current: " + current + "C" + "\n Average: " + min + "C - " + max + "C";
        viewHolderView.setTemperature(temperature);
    }

    @Override
    public void createWindValues(Wind wind) {
        String windValues = "Wind is blowing " + wind.getSpeed() + "m/s" + " " + directionOfWind(wind.getDeg());
        viewHolderView.setWind(windValues);
    }

    @Override
    public void createPressureValues(Main main) {
        String pressure = "Air pressure is: " + main.getPressure() + "hpa";
        viewHolderView.setPressure(pressure);
    }

    @Override
    public void createDescriptionValues(Weather weather) {
        String desc = "Weather status: " + weather.getMain() + ", details: " + weather.getDescription();
        viewHolderView.setDescription(desc);
    }

    @Override
    public void createTimeOfDay(String timeOfDay) {
        viewHolderView.setTimeOfDay(timeOfDay);
    }

    @Override
    public void createWeatherIconValue(String description) {
        switch (description) {
            case "Snow": {
                viewHolderView.setWeatherIcon("13d.png");
                break;
            }
            case "Rain": {
                viewHolderView.setWeatherIcon("09d.png");
                break;
            }
            case "Clear": {
                viewHolderView.setWeatherIcon("01d.png");
                break;
            }
            case "Mist": {
                viewHolderView.setWeatherIcon("50d.png");
                break;
            }
            case "Fog": {
                viewHolderView.setWeatherIcon("50d.png");
                break;
            }
            case "Haze": {
                viewHolderView.setWeatherIcon("50d.png");
                break;
            }

            case "Clouds": {
                viewHolderView.setWeatherIcon("03d.png");
                break;
            }
        }
    }

    @Override
    public double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }

    @Override
    public String directionOfWind(double degrees) {
        if (degrees >= 315 || degrees < 45) return "North";
        if (degrees >= 45 && degrees < 135) return "East";
        if (degrees >= 135 && degrees < 225) return "South";
        if (degrees >= 225 && degrees < 315) return "West";
        return null;
    }
}

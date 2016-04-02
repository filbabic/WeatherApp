package com.example.filip.weatherappmvpfinal.ui.adapter.presenter;

import com.example.filip.weatherappmvpfinal.constants.Constants;
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
        Main main = response.getMain();
        if (main != null) {
            createTemperatureValues(response.getMain());
            createPressureValues(response.getMain());
            createHumidityValues(response.getMain());
        }
        Wind wind = response.getWind();
        if (wind != null) {
            createWindValues(response.getWind());
        }
        Weather weather = response.getWeatherObject();
        if (weather != null) {
            createDescriptionValues(response.getWeatherObject());
            createWeatherIconValue(response.getWeatherObject().getMain());
        }
        createTimeOfDay(response.getDt_txt());
    }

    @Override
    public void createTemperatureValues(Main main) {
        viewHolderView.setCurrentTemperatureTextView(toCelsiusFromKelvin(main.getTemp()));
        viewHolderView.setMinTemperatureTextView(toCelsiusFromKelvin(main.getTemp_min()));
        viewHolderView.setMaxTemperatureTextView(toCelsiusFromKelvin(main.getTemp_max()));

    }

    @Override
    public void createWindValues(Wind wind) {
        viewHolderView.setWindSpeedTextView(wind.getSpeed());
        viewHolderView.setWindDirectionTextView(directionOfWind(wind.getDeg()));
    }

    @Override
    public void createPressureValues(Main main) {
        viewHolderView.setPressureTextView(main.getPressure());
    }

    @Override
    public void createDescriptionValues(Weather weather) {
        viewHolderView.setDescription(weather.getDescription());
    }

    @Override
    public void createTimeOfDay(String timeOfDay) {
        if (timeOfDay != null)
            viewHolderView.setTimeOfDay(timeOfDay);
    }

    @Override
    public void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    viewHolderView.setWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    viewHolderView.setWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    viewHolderView.setWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    viewHolderView.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    viewHolderView.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    viewHolderView.setWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    viewHolderView.setWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }

    @Override
    public void createHumidityValues(Main main) {
        viewHolderView.setHumidityTextView(main.getHumidity());
    }

    @Override
    public double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }

    @Override
    public String directionOfWind(double degrees) {
        if (degrees >= 315 || degrees < 45) return Constants.WIND_NORTH;
        if (degrees >= 45 && degrees < 135) return Constants.WIND_WEST;
        if (degrees >= 135 && degrees < 225) return Constants.WIND_SOUTH;
        if (degrees >= 225 && degrees < 315) return Constants.WIND_EAST;
        return null;
    }
}

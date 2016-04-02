package com.example.filip.weatherappmvpfinal.ui.weather.presenter;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelper;
import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;
import com.example.filip.weatherappmvpfinal.ui.weather.view.WeatherFragmentView;
import com.example.filip.weatherappmvpfinal.utils.StringUtils;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragmentPresenterImpl implements WeatherFragmentPresenter, ResponseListener<WeatherResponse> {
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
        networkingHelper.requestWeatherFromAPI(city, this);
    }

    @Override
    public void onSuccess(WeatherResponse callback) {
        if (callback != null) {
            if (databaseHelper.alreadyExists(callback.getCityName(), Constants.WEATHER_DATABASE)) {
                databaseHelper.updateWeatherResponseInDatabase(callback);
            } else databaseHelper.saveWeatherResponseToDatabase(callback);
            createWeatherStringsForView(callback);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        StringUtils.logError(throwable);
        weatherFragmentView.onFailure();
    }

    @Override
    public void getLastStoredRequestFromDatabase(String city) {
        WeatherResponse response = databaseHelper.getCachedResponseFromWeatherDatabase(city);
        if (response != null)
            createWeatherStringsForView(response);
        else
            weatherFragmentView.onFailure();
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

    private void createTemperatureValues(Main main) {
        weatherFragmentView.setMinTemperatureValues(toCelsiusFromKelvin(main.getTemp_min()));
        weatherFragmentView.setMaxTemperatureValues(toCelsiusFromKelvin(main.getTemp_max()));
        weatherFragmentView.setCurrentTemperatureValues(toCelsiusFromKelvin(main.getTemp()));
    }

    private void createWindValues(Wind wind) {
        weatherFragmentView.setWindValues(wind.getSpeed());
    }

    private void createPressureValues(Main main) {
        weatherFragmentView.setPressureValues(main.getPressure());
    }

    private void createDescriptionValues(Weather weather) {
        weatherFragmentView.setDescriptionValues(weather.getDescription());
    }

    private void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    weatherFragmentView.setWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }

    private double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }
}

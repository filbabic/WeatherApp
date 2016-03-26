package com.example.filip.weatherappmvpfinal.ui.weather.presenter;

import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelper;
import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.pojo.Forecast;
import com.example.filip.weatherappmvpfinal.ui.weather.view.ForecastFragmentView;

/**
 * Created by Filip on 16/02/2016.
 */
public class ForecastFragmentFragmentPresenterImpl implements ForecastFragmentPresenter {
    private final ForecastFragmentView forecastFragmentView;
    private final NetworkingHelper networkingHelper;


    public ForecastFragmentFragmentPresenterImpl(ForecastFragmentView view) {
        this.forecastFragmentView = view;
        this.networkingHelper = new NetworkingHelperImpl();
    }

    @Override
    public void sendARequestToAPI(String city) {
        networkingHelper.requestForecastFromAPI(city, new ResponseListener<Forecast>() {
            @Override
            public void onSuccess(Forecast callback) {
                if (callback != null)
                    forecastFragmentView.onSuccess(callback.getList());
            }

            @Override
            public void onFailure(Throwable throwable) {
                forecastFragmentView.onFailure();
                throwable.printStackTrace();
            }
        });
    }
}

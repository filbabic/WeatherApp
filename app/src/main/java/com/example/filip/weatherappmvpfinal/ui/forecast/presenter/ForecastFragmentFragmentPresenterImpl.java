package com.example.filip.weatherappmvpfinal.ui.forecast.presenter;

import com.example.filip.weatherappmvpfinal.helpers.networking.NetworkingHelper;
import com.example.filip.weatherappmvpfinal.helpers.ResponseListener;
import com.example.filip.weatherappmvpfinal.pojo.Forecast;
import com.example.filip.weatherappmvpfinal.ui.forecast.view.ForecastFragmentView;
import com.example.filip.weatherappmvpfinal.utils.StringUtils;

/**
 * Created by Filip on 16/02/2016.
 */
public class ForecastFragmentFragmentPresenterImpl implements ForecastFragmentPresenter, ResponseListener<Forecast> {
    private final ForecastFragmentView forecastFragmentView;
    private final NetworkingHelper networkingHelper;


    public ForecastFragmentFragmentPresenterImpl(ForecastFragmentView view, NetworkingHelper networkingHelper) {
        this.forecastFragmentView = view;
        this.networkingHelper = networkingHelper;
    }

    @Override
    public void sendARequestToAPI(String city) {
        networkingHelper.requestForecastFromAPI(city, this);
    }

    @Override
    public void onSuccess(Forecast callback) {
        if (callback != null)
            forecastFragmentView.onSuccess(callback.getList());
    }

    @Override
    public void onFailure(Throwable throwable) {
        StringUtils.logError(throwable);
        forecastFragmentView.onFailure();
    }
}

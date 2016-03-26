package com.example.filip.weatherappmvpfinal.ui.weather.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenterImpl;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherFragmentView {
    private TextView mTemperature;
    private TextView mPressure;
    private TextView mWind;
    private TextView mDescription;
    private ImageView mWeatherIcon;

    private WeatherFragmentPresenter presenter;

    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forecast_weather_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        initPresenter();
        if (checkIfNetworkIsAvailable()) {
            presenter.sendRequestToAPI(this.getArguments().getString(Constants.CITY_BUNDLE_KEY));
        }
    }

    private void initUI(View itemView) {
        mTemperature = (TextView) itemView.findViewById(R.id.forecast_view_temperature_text_view);
        mPressure = (TextView) itemView.findViewById(R.id.forecast_view_pressure_text_view);
        mWind = (TextView) itemView.findViewById(R.id.forecast_view_wind_text_view);
        mDescription = (TextView) itemView.findViewById(R.id.forecast_view_description_text_view);
        mWeatherIcon = (ImageView) itemView.findViewById(R.id.forecast_view_weather_icon_image_view);
    }

    private void initPresenter() {
        presenter = new WeatherFragmentPresenterImpl(this);
    }

    @Override
    public void setTemperatureValues(String temperatureValues) {
        mTemperature.setText(temperatureValues);
    }

    @Override
    public void setPressureValues(String pressureValues) {
        mPressure.setText(pressureValues);

    }

    @Override
    public void setWindValues(String windValues) {
        mWind.setText(windValues);
    }

    @Override
    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity()).load(Constants.IMAGE_BASE_URL + iconPath).into(mWeatherIcon);
    }

    @Override
    public void setDescriptionValues(String descriptionValues) {
        mDescription.setText(descriptionValues);
    }

    private boolean checkIfNetworkIsAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}

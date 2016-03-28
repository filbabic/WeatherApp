package com.example.filip.weatherappmvpfinal.ui.weather.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.WeatherDatabase;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenterImpl;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherFragmentView, View.OnClickListener {
    private TextView mTemperature;
    private TextView mPressure;
    private TextView mWind;
    private TextView mDescription;
    private ImageView mWeatherIcon;
    private FloatingActionButton mRefreshFloatingActionButton;
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
        return inflater.inflate(R.layout.fragment_weather, container, false);
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
        } else {
            presenter.getLastStoredRequestFromDatabase(this.getArguments().getString(Constants.CITY_BUNDLE_KEY));
        }
    }

    private void initUI(View view) {
        mRefreshFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        mRefreshFloatingActionButton.setOnClickListener(this);
        mTemperature = (TextView) view.findViewById(R.id.forecast_view_temperature_text_view);
        mPressure = (TextView) view.findViewById(R.id.forecast_view_pressure_text_view);
        mWind = (TextView) view.findViewById(R.id.forecast_view_wind_text_view);
        mDescription = (TextView) view.findViewById(R.id.forecast_view_description_text_view);
        mWeatherIcon = (ImageView) view.findViewById(R.id.forecast_view_weather_icon_image_view);
    }

    private void initPresenter() {
        WeatherDatabase database = new WeatherDatabase(getActivity());
        presenter = new WeatherFragmentPresenterImpl(this, database);
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

    private void refreshCurrentData() {
        if (checkIfNetworkIsAvailable()) {
            presenter.sendRequestToAPI(this.getArguments().getString(Constants.CITY_BUNDLE_KEY));
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof FloatingActionButton)
            refreshCurrentData();
    }
}

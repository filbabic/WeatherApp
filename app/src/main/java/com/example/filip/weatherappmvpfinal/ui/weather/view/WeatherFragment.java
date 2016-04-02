package com.example.filip.weatherappmvpfinal.ui.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.filip.weatherappmvpfinal.App;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DataManagerImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.WeatherDatabase;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.WeatherFragmentPresenterImpl;
import com.example.filip.weatherappmvpfinal.utils.NetworkUtils;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherFragmentView, View.OnClickListener {
    private TextView mCurrentTemperature;
    private TextView mMinTemperature;
    private TextView mMaxTemperature;
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
        String cityToDisplay = this.getArguments().getString(Constants.CITY_BUNDLE_KEY);
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity())) {
            presenter.sendRequestToAPI(cityToDisplay);
        } else {
            presenter.getLastStoredRequestFromDatabase(cityToDisplay);
        }
    }

    private void initUI(View view) {
        mRefreshFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        mRefreshFloatingActionButton.setOnClickListener(this);

        mCurrentTemperature = (TextView) view.findViewById(R.id.weather_display_current_temperature_text_view);
        mMinTemperature = (TextView) view.findViewById(R.id.weather_fragment_min_temperature_text_view);
        mMaxTemperature = (TextView) view.findViewById(R.id.weather_fragment_max_temperature_text_view);
        mPressure = (TextView) view.findViewById(R.id.weather_display_pressure_text_view);
        mWind = (TextView) view.findViewById(R.id.weather_display_wind_text_view);
        mDescription = (TextView) view.findViewById(R.id.weather_display_detailed_description_text_view);
        mWeatherIcon = (ImageView) view.findViewById(R.id.weather_display_weather_icon_image_view);
    }

    private void initPresenter() {
        WeatherDatabase database = new WeatherDatabase(getActivity());
        presenter = new WeatherFragmentPresenterImpl(this, new DatabaseHelperImpl(null, database, new DataManagerImpl()), App.getInstance().getNetworkingHelper());
    }

    @Override
    public void setCurrentTemperatureValues(double temperatureValues) {
        mCurrentTemperature.setText(getString(R.string.current_temperature_message, temperatureValues));
    }

    @Override
    public void setMinTemperatureValues(double minTemperatureValues) {
        mMinTemperature.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }

    @Override
    public void setMaxTemperatureValues(double maxTemperatureValues) {
        mMaxTemperature.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }

    @Override
    public void setPressureValues(double pressureValues) {
        mPressure.setText(getString(R.string.pressure_message, pressureValues));

    }

    @Override
    public void setWindValues(double windValues) {
        mWind.setText(getString(R.string.wind_speed_message, windValues));
    }

    @Override
    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity().getApplicationContext()).load(Constants.IMAGE_BASE_URL + iconPath).into(mWeatherIcon);
    }

    @Override
    public void setDescriptionValues(String descriptionValues) {
        mDescription.setText(descriptionValues);
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }

    private void refreshCurrentData() {
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity())) {
            presenter.sendRequestToAPI(this.getArguments().getString(Constants.CITY_BUNDLE_KEY));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mRefreshFloatingActionButton)
            refreshCurrentData();
    }
}

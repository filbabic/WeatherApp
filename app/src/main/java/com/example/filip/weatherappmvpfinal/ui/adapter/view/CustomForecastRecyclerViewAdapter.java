package com.example.filip.weatherappmvpfinal.ui.adapter.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.ui.adapter.presenter.ViewHolderPresenter;
import com.example.filip.weatherappmvpfinal.ui.adapter.presenter.ViewViewHolderPresenterImpl;

import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public class CustomForecastRecyclerViewAdapter extends RecyclerView.Adapter<CustomForecastRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<WeatherResponse> mItemList = new ArrayList<>();

    public void fillData(ArrayList<WeatherResponse> mDataSource) {
        this.mItemList.clear();
        this.mItemList.addAll(mDataSource);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_weather_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sendDataToPresenter(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements ViewHolderView {
        private final ViewHolderPresenter presenter;
        private final TextView mCurrentTemperature;
        private final TextView mMinTemperature;
        private final TextView mMaxTemperature;
        private final TextView mPressure;
        private final TextView mHumidity;
        private final TextView mWindSpeed;
        private final TextView mWindDirection;
        private final TextView mDescription;
        private final TextView mTimeOfDay;
        private final ImageView mWeatherIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mCurrentTemperature = (TextView) itemView.findViewById(R.id.weather_display_current_temperature_text_view);
            mMinTemperature = (TextView) itemView.findViewById(R.id.weather_fragment_min_temperature_text_view);
            mMaxTemperature = (TextView) itemView.findViewById(R.id.weather_fragment_max_temperature_text_view);
            mHumidity = (TextView) itemView.findViewById(R.id.weather_display_humidity_text_view);
            mWindDirection = (TextView) itemView.findViewById(R.id.weather_display_wind_direction_text_view);
            mPressure = (TextView) itemView.findViewById(R.id.weather_display_pressure_text_view);
            mWindSpeed = (TextView) itemView.findViewById(R.id.weather_display_wind_text_view);
            mDescription = (TextView) itemView.findViewById(R.id.weather_display_detailed_description_text_view);
            mTimeOfDay = (TextView) itemView.findViewById(R.id.weather_display_forecast_time_of_day_text_view);
            mWeatherIcon = (ImageView) itemView.findViewById(R.id.weather_display_weather_icon_image_view);
            presenter = new ViewViewHolderPresenterImpl(this);
        }

        @Override
        public void sendDataToPresenter(WeatherResponse response) {
            presenter.createValuesForViewToDisplay(response);
        }

        @Override
        public void setCurrentTemperatureTextView(double currentTemperature) {
            mCurrentTemperature.setText(itemView.getContext().getString(R.string.current_temperature_message, currentTemperature));
        }

        @Override
        public void setMinTemperatureTextView(double minTemperature) {
            mMinTemperature.setText(itemView.getContext().getString(R.string.minimum_temperature_message, minTemperature));
        }

        @Override
        public void setMaxTemperatureTextView(double maxTemperature) {
            mMaxTemperature.setText(itemView.getContext().getString(R.string.maximum_temperature_message, maxTemperature));
        }

        @Override
        public void setPressureTextView(double pressure) {
            mPressure.setText(itemView.getContext().getString(R.string.pressure_message, pressure));
        }

        @Override
        public void setHumidityTextView(int humidity) {
            mHumidity.setText(itemView.getContext().getString(R.string.humidity_message, humidity));
        }

        @Override
        public void setWindSpeedTextView(double windSpeed) {
            mWindSpeed.setText(itemView.getContext().getString(R.string.wind_speed_message, windSpeed));
        }

        @Override
        public void setWindDirectionTextView(String direction) {
            mWindDirection.setText(itemView.getContext().getString(R.string.wind_direction_message, direction));
        }

        @Override
        public void setDescription(String description) {
            mDescription.setText(description);
        }

        @Override
        public void setTimeOfDay(String timeOfDay) {
            mTimeOfDay.setText(timeOfDay);
        }

        @Override
        public void setWeatherIcon(String description) {
            Glide.with(itemView.getContext()).load(Constants.IMAGE_BASE_URL + description).into(mWeatherIcon);
        }
    }
}

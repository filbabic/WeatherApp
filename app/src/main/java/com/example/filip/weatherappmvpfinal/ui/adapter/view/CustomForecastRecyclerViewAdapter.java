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
        private ViewHolderPresenter presenter;
        private TextView mTemperature;
        private TextView mPressure;
        private TextView mWind;
        private TextView mDescription;
        private TextView mTimeOfDay;
        private ImageView mWeatherIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mTemperature = (TextView) itemView.findViewById(R.id.forecast_view_temperature_text_view);
            mPressure = (TextView) itemView.findViewById(R.id.forecast_view_pressure_text_view);
            mWind = (TextView) itemView.findViewById(R.id.forecast_view_wind_text_view);
            mDescription = (TextView) itemView.findViewById(R.id.forecast_view_description_text_view);
            mTimeOfDay = (TextView) itemView.findViewById(R.id.forecast_view_time_of_day_text_view);
            mWeatherIcon = (ImageView) itemView.findViewById(R.id.forecast_view_weather_icon_image_view);
            presenter = new ViewViewHolderPresenterImpl(this);
        }

        @Override
        public void sendDataToPresenter(WeatherResponse response) {
            presenter.createValuesForViewToDisplay(response);
        }

        @Override
        public void setTemperature(String temperature) {
            mTemperature.setText(temperature);
        }

        @Override
        public void setPressure(String pressure) {
            mPressure.setText(pressure);
        }

        @Override
        public void setWind(String wind) {
            mWind.setText(wind);
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

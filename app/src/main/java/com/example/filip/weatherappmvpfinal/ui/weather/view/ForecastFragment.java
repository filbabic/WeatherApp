package com.example.filip.weatherappmvpfinal.ui.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.ui.adapter.view.CustomForecastRecyclerViewAdapter;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.ForecastFragmentFragmentPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.weather.presenter.ForecastFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public class ForecastFragment extends Fragment implements ForecastFragmentView {
    private RecyclerView mRecyclerView;
    private CustomForecastRecyclerViewAdapter adapter;
    private ForecastFragmentPresenter presenter;

    public static ForecastFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        ForecastFragment f = new ForecastFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast_display, container, false);
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
        initAdapter();
        presenter.sendARequestToAPI(getArguments().getString(Constants.CITY_BUNDLE_KEY));
    }

    private void initUI(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.forecast_fragment_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initPresenter() {
        presenter = new ForecastFragmentFragmentPresenterImpl(this);
    }

    private void initAdapter() {
        adapter = new CustomForecastRecyclerViewAdapter();
    }

    @Override
    public void onSuccess(ArrayList<WeatherResponse> response) {
        if (response != null) {
            adapter.fillData(response);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getActivity().getString(R.string.request_failed_toast_message), Toast.LENGTH_SHORT).show();
    }
}

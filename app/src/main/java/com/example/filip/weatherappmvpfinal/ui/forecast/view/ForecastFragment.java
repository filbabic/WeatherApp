package com.example.filip.weatherappmvpfinal.ui.forecast.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.filip.weatherappmvpfinal.App;
import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.ui.adapter.view.CustomForecastRecyclerViewAdapter;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.ui.forecast.presenter.ForecastFragmentFragmentPresenterImpl;
import com.example.filip.weatherappmvpfinal.ui.forecast.presenter.ForecastFragmentPresenter;
import com.example.filip.weatherappmvpfinal.utils.NetworkUtils;

import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public class ForecastFragment extends Fragment implements ForecastFragmentView, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private CustomForecastRecyclerViewAdapter adapter;
    private ForecastFragmentPresenter presenter;
    private FloatingActionButton mFloatingActionButton;

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
        getForecastData();
    }

    private void initUI(View view) {
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.forecast_fragment_floating_action_button);
        mFloatingActionButton.setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.forecast_fragment_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    private void initPresenter() {
        presenter = new ForecastFragmentFragmentPresenterImpl(this, App.getInstance().getNetworkingHelper());
    }

    private void initAdapter() {
        adapter = new CustomForecastRecyclerViewAdapter();
    }

    private void getForecastData() {
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity()))
            presenter.sendARequestToAPI(getArguments().getString(Constants.CITY_BUNDLE_KEY));
    }

    @Override
    public void onSuccess(@NonNull ArrayList<WeatherResponse> response) {
        adapter.fillData(response);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getActivity().getString(R.string.request_failed_toast_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == mFloatingActionButton)
            getForecastData();
    }
}

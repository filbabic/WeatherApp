package com.example.filip.weatherappmvpfinal.ui.location.view.browse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DataManagerImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.database.WeatherDatabase;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.adapter.view.CustomLocationsListRecyclerViewAdapter;
import com.example.filip.weatherappmvpfinal.ui.forecast.view.ForecastDisplayActivity;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.browse.BrowseLocationsFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.browse.BrowseLocationsFragmentPresenterImpl;

/**
 * Created by Filip on 24/03/2016.
 */
public class BrowseLocationsFragment extends Fragment implements LocationItemListener {
    private BrowseLocationsFragmentPresenter presenter;
    private RecyclerView mRecyclerView;
    private CustomLocationsListRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browse_locations, container, false);
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
    }

    private void initUI(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.browse_locations_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    private void initPresenter() {
        LocationDatabase locationDatabase = new LocationDatabase(getActivity());
        WeatherDatabase weatherDatabase = new WeatherDatabase(getActivity());
        presenter = new BrowseLocationsFragmentPresenterImpl(new DatabaseHelperImpl(locationDatabase, weatherDatabase, new DataManagerImpl()));
    }

    private void initAdapter() {
        adapter = new CustomLocationsListRecyclerViewAdapter(this);
        adapter.fillData(presenter.getLocationsFromDatabase());
        mRecyclerView.setAdapter(adapter);
    }

    private void openDeleteDialog(final String locationName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getActivity().getString(R.string.dialog_message, locationName));
        builder.setPositiveButton(getActivity().getString(R.string.dialog_positive_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteLocationFromDatabase(locationName);
                adapter.fillData(presenter.getLocationsFromDatabase());
            }
        });
        builder.setNegativeButton(getActivity().getString(R.string.dialog_negative_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onLongClick(LocationWrapper locationWrapper) {
        openDeleteDialog(locationWrapper.getLocation());
    }

    @Override
    public void onItemClick(LocationWrapper locationWrapper) {
        Intent i = new Intent(getActivity(), ForecastDisplayActivity.class);
        i.putExtra(Constants.CITY_BUNDLE_KEY, locationWrapper.getLocation());
        startActivity(i);
    }
}

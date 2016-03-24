package com.example.filip.weatherappmvpfinal.presenter.main;

import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.view.main.MainView;

import java.util.ArrayList;


/**
 * Created by Filip on 14/02/2016.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    private final MainView mainView;
    private final DatabaseHelper databaseHelper;

    public MainActivityPresenterImpl(MainView mainView, LocationDatabase database) {
        this.mainView = mainView;
        this.databaseHelper = new DatabaseHelperImpl(database);
    }

    @Override
    public void receiveDataFromLocationService(String locationName) {
        LocationWrapper locationToAdd = new LocationWrapper(locationName);
        if (!databaseHelper.checkIfLocationExists(locationToAdd)) {
            databaseHelper.addLocation(locationToAdd);
            mainView.currentLocationOnSuccess(locationName);
            mainView.updateAdapterData();
        } else
            mainView.currentLocationOnFailure();
    }

    @Override
    public ArrayList<LocationWrapper> getLocations() {
        return databaseHelper.getLocations();
    }
}

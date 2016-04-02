package com.example.filip.weatherappmvpfinal.ui.main.presenter;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.main.view.MainView;

import java.util.ArrayList;


/**
 * Created by Filip on 14/02/2016.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    private final MainView mainView;
    private final DatabaseHelper databaseHelper;

    public MainActivityPresenterImpl(MainView mainView, DatabaseHelper databaseHelper) {
        this.mainView = mainView;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void receiveDataFromLocationService(String locationName) {
        if (!databaseHelper.alreadyExists(locationName, Constants.LOCATIONS_DATABASE)) {
            databaseHelper.addLocation(locationName);
            mainView.onSuccess(locationName);
            mainView.updateAdapterData();
        } else
            mainView.onFailure();
    }

    @Override
    public ArrayList<LocationWrapper> getLocations() {
        return databaseHelper.getLocations(Constants.LOCATIONS_DATABASE);
    }
}

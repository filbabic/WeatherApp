package com.example.filip.weatherappmvpfinal.ui.location.presenter.add;

import com.example.filip.weatherappmvpfinal.helpers.database.WeatherDatabase;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.ui.location.view.add.AddLocationView;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationPresenterImpl implements AddLocationFragmentPresenter {
    private final AddLocationView addLocationView;
    private final DatabaseHelper databaseHelper;

    public AddLocationPresenterImpl(AddLocationView view, LocationDatabase locationDatabase, WeatherDatabase weatherDatabase) {
        this.addLocationView = view;
        this.databaseHelper = new DatabaseHelperImpl(locationDatabase, weatherDatabase);
    }

    @Override
    public void addLocationToDatabase(LocationWrapper locationWrapper) {
        if (!databaseHelper.checkIfLocationExists(locationWrapper)) {
            databaseHelper.addLocation(locationWrapper);
            addLocationView.onSuccess();
        } else
            addLocationView.onFailure();
    }
}

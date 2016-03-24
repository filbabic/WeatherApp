package com.example.filip.weatherappmvpfinal.presenter.location.add;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.view.location.add.AddLocationView;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationPresenterImpl implements AddLocationFragmentPresenter {
    private final AddLocationView addLocationView;
    private final DatabaseHelper databaseHelper;

    public AddLocationPresenterImpl(AddLocationView view, LocationDatabase database) {
        this.addLocationView = view;
        this.databaseHelper = new DatabaseHelperImpl(database);
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

package com.example.filip.weatherappmvpfinal.ui.location.presenter.add;

import com.example.filip.weatherappmvpfinal.constants.Constants;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelper;
import com.example.filip.weatherappmvpfinal.ui.location.view.add.AddLocationView;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationPresenterImpl implements AddLocationFragmentPresenter {
    private final AddLocationView addLocationView;
    private final DatabaseHelper databaseHelper;

    public AddLocationPresenterImpl(AddLocationView view, DatabaseHelper databaseHelper) {
        this.addLocationView = view;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void addLocationToDatabase(String cityName) {
        if (cityName.equals("")) addLocationView.onEmptyStringRequestError();
        else if (!databaseHelper.alreadyExists(cityName, Constants.LOCATIONS_DATABASE)) {
            databaseHelper.addLocation(cityName);
            addLocationView.onSuccess();
        } else addLocationView.onLocationAlreadyExistsError();
    }
}

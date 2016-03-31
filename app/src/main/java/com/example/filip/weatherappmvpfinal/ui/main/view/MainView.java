package com.example.filip.weatherappmvpfinal.ui.main.view;

/**
 * Created by Filip on 10/02/2016.
 */
public interface MainView {
    void onSuccess(String locationName);

    void onFailure();

    void updateAdapterData();

    void addCurrentLocationToDatabase(String location);
}

package com.example.filip.weatherappmvpfinal.view.main;

/**
 * Created by Filip on 10/02/2016.
 */
public interface MainView {
    void currentLocationOnSuccess(String locationName);

    void updateAdapterData();

    void currentLocationOnFailure();
}

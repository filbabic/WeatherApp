package com.example.filip.weatherappmvpfinal.helpers.location;

import android.location.Location;

/**
 * Created by Filip on 24/03/2016.
 */
public interface LocationHelper {
    void connectClient();

    void disconnectClient();

    Location getCurrentLocation();

    String getLocationFromGeocoder(double latitude, double longitude);
}

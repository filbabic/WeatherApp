package com.example.filip.weatherappmvpfinal.helpers.location;

import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;

/**
 * Created by Filip on 17/02/2016.
 */
public class LocationHelperImpl implements LocationHelper {
    private final Geocoder geocoder;
    private final GoogleApiClient googleApiClient;

    public LocationHelperImpl(Geocoder geo, GoogleApiClient client) {
        this.geocoder = geo;
        this.googleApiClient = client;
    }

    @Override
    public String getLocationFromGeocoder(double lat, double lng) {
        try {
            return geocoder.getFromLocation(lat, lng, 1).get(0).getLocality(); //city
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void connectClient() {
        googleApiClient.connect();
    }

    @Override
    public void disconnectClient() {
        googleApiClient.disconnect();
    }

    @Override
    public Location getLocation() {
        try {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                return location;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.filip.weatherappmvpfinal;

import android.location.Address;
import android.location.Geocoder;

import com.example.filip.weatherappmvpfinal.helpers.location.LocationHelper;
import com.example.filip.weatherappmvpfinal.helpers.location.LocationHelperImpl;
import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

/**
 * Created by Filip on 30/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationHelperTest {
    @Mock
    private Geocoder geocoder;
    @Mock
    private GoogleApiClient googleApiClient;

    private LocationHelper locationHelper;

    private final String locationMock = "locationMock";

    @Mock
    private List<Address> mockedList;
    @Mock
    private Address mockedAddress;

    @Before
    public void setUp() throws Exception {
        locationHelper = new LocationHelperImpl(geocoder, googleApiClient);
    }

    @Test
    public void shouldConnectClient() throws Exception {
        locationHelper.connectClient();
        verify(googleApiClient).connect();
    }

    @Test
    public void shouldDisconnectClient() throws Exception {
        locationHelper.disconnectClient();

        verify(googleApiClient).disconnect();
    }

    @Test
    public void shouldReturnMockedLocationAddressLine() throws Exception {
        when(geocoder.getFromLocation(anyDouble(), anyDouble(), anyInt())).thenReturn(mockedList);
        when(mockedList.get(anyInt())).thenReturn(mockedAddress);
        when(mockedAddress.getLocality()).thenReturn((locationMock));

        assertEquals(locationHelper.getLocationFromGeocoder(0, 0), locationMock);

        verify(geocoder).getFromLocation(anyDouble(), anyDouble(), anyInt());
        verify(mockedList).get(anyInt());
        verify(mockedAddress).getLocality();
    }
}

package com.example.filip.weatherappmvpfinal.helpers;

/**
 * Created by Filip on 24/03/2016.
 */
public interface ResponseListener<T> {
    void onSuccess(T callback);

    void onFailure(Throwable throwable);
}

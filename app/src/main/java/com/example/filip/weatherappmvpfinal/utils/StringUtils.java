package com.example.filip.weatherappmvpfinal.utils;

import com.example.filip.weatherappmvpfinal.BuildConfig;

/**
 * Created by Filip on 01/04/2016.
 */
public class StringUtils {

    public static void logError(Throwable throwable) {
        if (BuildConfig.DEBUG)
            logError(throwable);
    }
}

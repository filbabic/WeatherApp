package com.example.filip.weatherappmvpfinal.helpers.networking;

import com.example.filip.weatherappmvpfinal.constants.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 24/03/2016.
 */
public class RetrofitRequestHelper {

    public static Map<String, String> createQueryMap(String city) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.APP_ID_KEY, Constants.APP_ID);
        map.put(Constants.CITY_QUERY_KEY, city);
        return map;
    }
}

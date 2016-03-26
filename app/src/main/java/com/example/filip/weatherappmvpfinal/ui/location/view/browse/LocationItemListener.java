package com.example.filip.weatherappmvpfinal.ui.location.view.browse;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

/**
 * Created by Filip on 10/02/2016.
 */
public interface LocationItemListener {
    void onLongClick(LocationWrapper locationWrapper);

    void onItemClick(LocationWrapper locationWrapper);
}

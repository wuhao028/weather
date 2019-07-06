package com.wuhao.weather.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wuhao.weather.WeatherApplication;

public class NetworkUtil {

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) WeatherApplication.getApplicaiton()
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
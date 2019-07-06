package com.wuhao.weather.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.wuhao.weather.WeatherApplication;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class UIUtil {

    public static float convertDpToPixel(float dp) {
        Resources resources = WeatherApplication.getApplicaiton().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    public static float convertPixelsToDp(float px) {
        Resources resources = WeatherApplication.getApplicaiton().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }


    public static void showToast(String message, int length) {
        Toast toast = Toast.makeText(WeatherApplication.getApplicaiton(), message, length);
        toast.show();
    }
}

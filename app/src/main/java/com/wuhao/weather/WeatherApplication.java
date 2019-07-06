package com.wuhao.weather;

import android.app.Application;
import android.content.Context;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class WeatherApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getApplicaiton() {
        return context;
    }

}

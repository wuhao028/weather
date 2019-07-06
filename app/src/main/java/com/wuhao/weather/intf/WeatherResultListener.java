package com.wuhao.weather.intf;

import com.wuhao.weather.model.CurrentWeatherResult;
import com.wuhao.weather.model.ForcastWeatherResult;

/**
 * Created by WuHao028 on 2019-07-06
 */
public interface WeatherResultListener {
    void onCurrentWeatherResult(int fragmentCode, boolean isSuccess, CurrentWeatherResult result);

    void onForecastResult(int fragmentCode, boolean isSuccess, ForcastWeatherResult result);

    void onFailure();
}

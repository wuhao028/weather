package com.wuhao.weather.intf;

import com.wuhao.weather.model.CurrentWeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WuHao028 on 2019-07-06
 */

public interface IFetchWeather {
    //        api.openweathermap.org/data/2.5/weather?q=London
    @GET("weather")
    Call<CurrentWeatherResult> getWheatherResult(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String ApiId, @Query("units") String units);
}

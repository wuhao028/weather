package com.wuhao.weather.intf;

import com.wuhao.weather.model.ForcastWeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WuHao028 on 2019-07-06
 */
public interface IFetchForecast {
    //        api.openweathermap.org/data/2.5/forecast/daily?q=London&mode=xml&units=metric&cnt=7
    @GET("forecast")
    Call<ForcastWeatherResult> getForecastWeatherResult(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String ApiId, @Query("cnt") int cnt, @Query("units") String units);
}
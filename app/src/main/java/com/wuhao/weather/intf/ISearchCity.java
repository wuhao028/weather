package com.wuhao.weather.intf;

import com.wuhao.weather.model.CitySearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISearchCity {
    //    api.openweathermap.org/data/2.5/find?q={CITY_NAME}&type=like&APPID={YOUR APP ID}
    @GET("find")
    Call<CitySearchResult> getSearchResult(@Query("q") String cityName, @Query("type") String type, @Query("appid") String appid, @Query("units") String units);

}

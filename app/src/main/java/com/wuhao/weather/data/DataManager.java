package com.wuhao.weather.data;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.wuhao.weather.model.CityBean;
import com.wuhao.weather.model.CitySearchResult;
import com.wuhao.weather.model.CurrentWeatherResult;
import com.wuhao.weather.model.ForcastWeatherResult;
import com.wuhao.weather.intf.CitySearchListener;
import com.wuhao.weather.intf.IFetchForecast;
import com.wuhao.weather.intf.IFetchWeather;
import com.wuhao.weather.intf.ISearchCity;
import com.wuhao.weather.intf.WeatherResultListener;

import java.util.HashMap;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wuhao.weather.data.Constants.APP_ID;
import static com.wuhao.weather.data.Constants.BASE_URL;
import static com.wuhao.weather.data.Constants.DATA_UNIT;
import static com.wuhao.weather.data.Constants.SEARCH_TYPE;

public class DataManager {

    private double DEFAULT_LAT = -36.8535;
    private double DEFAULT_LON = 174.7656;
    private HashMap<Integer, WeatherResultListener> listenerHashMap;
    private CitySearchListener citySearchListener;
    private LinkedList<CityBean> linkedList;

    private DataManager() {
        linkedList = new LinkedList<>();
        linkedList.add(new CityBean("Auckland", DEFAULT_LON, DEFAULT_LAT));
        linkedList.add(new CityBean("London", -0.04, 51.53));
    }

    public static DataManager getInstance() {
        return DataManagerHolder.instance;
    }

    public static class DataManagerHolder {
        public static final DataManager instance = new DataManager();
    }

    public void requestCurrentWeather(int fragmentCode, double lat, double lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        IFetchWeather weatherService = retrofit.create(IFetchWeather.class);
        Call<CurrentWeatherResult> call = weatherService.getWheatherResult(lat, lon, APP_ID, DATA_UNIT);
        call.enqueue(new Callback<CurrentWeatherResult>() {
            @Override
            public void onResponse(Call<CurrentWeatherResult> call, Response<CurrentWeatherResult> response) {
                if (response.isSuccessful()) {
                    CurrentWeatherResult result = response.body();
                    if (listenerHashMap != null && listenerHashMap.get(fragmentCode) != null) {
                        listenerHashMap.get(fragmentCode).onCurrentWeatherResult(fragmentCode, true, result);
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResult> call, Throwable t) {
                if (listenerHashMap != null && listenerHashMap.get(fragmentCode) != null) {
                    listenerHashMap.get(fragmentCode).onFailure();
                }
            }
        });
    }

    public void requestForecastWeather(int fragmentCode, double lat, double lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        IFetchForecast weatherService = retrofit.create(IFetchForecast.class);
        Call<ForcastWeatherResult> call = weatherService.getForecastWeatherResult(lat, lon, APP_ID, 40, DATA_UNIT);
        call.enqueue(new Callback<ForcastWeatherResult>() {
            @Override
            public void onResponse(Call<ForcastWeatherResult> call, Response<ForcastWeatherResult> response) {
                if (response.isSuccessful()) {
                    ForcastWeatherResult result = response.body();
                    if (listenerHashMap != null && listenerHashMap.get(fragmentCode) != null) {
                        listenerHashMap.get(fragmentCode).onForecastResult(fragmentCode, true, result);
                    }
                }
            }

            @Override
            public void onFailure(Call<ForcastWeatherResult> call, Throwable t) {
            }
        });

    }

    public void requestCitySearch(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ISearchCity weatherService = retrofit.create(ISearchCity.class);
        Call<CitySearchResult> call = weatherService.getSearchResult(cityName, SEARCH_TYPE, APP_ID, DATA_UNIT);
        call.enqueue(new Callback<CitySearchResult>() {
            @Override
            public void onResponse(Call<CitySearchResult> call, Response<CitySearchResult> response) {
                if (response.isSuccessful()) {
                    CitySearchResult result = response.body();
                    if (citySearchListener != null) {
                        citySearchListener.onResult(result);
                    }
                } else {
                    if (citySearchListener != null) {
                        citySearchListener.onFail();
                    }
                }
            }

            @Override
            public void onFailure(Call<CitySearchResult> call, Throwable t) {
                if (citySearchListener != null) {
                    citySearchListener.onFail();
                }
            }
        });
    }

    public void addListener(int fragmentCode, WeatherResultListener listener) {
        if (listenerHashMap == null) {
            listenerHashMap = new HashMap<>();
        }
        listenerHashMap.put(fragmentCode, listener);
    }

    public void setDefaultCity(double lon, double lat) {
        this.DEFAULT_LAT = lat;
        this.DEFAULT_LON = lon;
        linkedList = new LinkedList<>();
        linkedList.add(new CityBean("Auckland", DEFAULT_LON, DEFAULT_LAT));
        linkedList.add(new CityBean("London", -0.1277, 51.5073));
    }

    public boolean isLocationAvailable(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void setCitySearchListener(CitySearchListener citySearchListener) {
        this.citySearchListener = citySearchListener;
    }

    public LinkedList<CityBean> getCities() {
        return linkedList;
    }

}

package com.wuhao.weather.model;

/**
 * Created by WuHao028 on 2019-07-07
 */
public class CityBean {
    public String cityName;
    public double lat, lon;

    public CityBean(String cityName, double lon, double lat) {
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

package com.wuhao.weather.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.wuhao.weather.WeatherApplication;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class LocationUtil {
    public static double[] getLatAndLon() {
        if (ActivityCompat.checkSelfPermission(WeatherApplication.getApplicaiton(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(WeatherApplication.getApplicaiton(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            double latitude = 200.0;
            double longitude = 200.0;
            LocationManager locationManager = (LocationManager) WeatherApplication.getApplicaiton().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (locationManager == null) {
                    return new double[]{longitude, latitude};
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {
                    return getLngAndLatWithNetwork();
                }
            } else {
                return getLngAndLatWithNetwork();
            }
            return new double[]{longitude, latitude};
        }
        return null;
    }

    public static double[] getLngAndLatWithNetwork() {
        if (ActivityCompat.checkSelfPermission(WeatherApplication.getApplicaiton(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(WeatherApplication.getApplicaiton(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            double latitude = 200.0;
            double longitude = 200.0;
            LocationManager locationManager = (LocationManager) WeatherApplication.getApplicaiton().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager == null) {
                return new double[]{longitude, latitude};
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
            return new double[]{longitude, latitude};
        }
        return null;
    }


    static LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
        }
    };
}

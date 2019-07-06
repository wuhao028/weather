package com.wuhao.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wuhao.weather.view.MainActivity;
import com.wuhao.weather.view.WeatherFragment;

import java.util.List;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class WeatherAdapter extends FragmentStatePagerAdapter {

    private List<WeatherFragment> weatherScreens;
    public int curPageNumber = 0;

    public WeatherAdapter(MainActivity activity, List<WeatherFragment> screens) {
        super(activity.getSupportFragmentManager());
        weatherScreens = screens;
    }

    @Override
    public Fragment getItem(int position) {
        curPageNumber = position;
        return weatherScreens.get(position);
    }

    @Override
    public int getCount() {
        return weatherScreens.size();
    }

}

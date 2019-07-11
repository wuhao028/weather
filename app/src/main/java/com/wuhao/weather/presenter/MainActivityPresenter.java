package com.wuhao.weather.presenter;

import com.wuhao.weather.data.DataManager;
import com.wuhao.weather.view.MainActivity;

public class MainActivityPresenter {

    MainActivity activity;
    public MainActivityPresenter(MainActivity activity){
        this.activity = activity;
    }

    public void setDefaultLocation(double[] location){
        DataManager.getInstance().setDefaultCity(location[0],location[1]);
    }

}

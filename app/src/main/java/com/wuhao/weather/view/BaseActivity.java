package com.wuhao.weather.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by WuHao028 on 2019-07-06
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }
}

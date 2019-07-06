package com.wuhao.weather.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wuhao.weather.R;
import com.wuhao.weather.adapter.WeatherAdapter;
import com.wuhao.weather.data.DataManager;
import com.wuhao.weather.model.CityBean;
import com.wuhao.weather.utils.LocationUtil;
import com.wuhao.weather.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.indicator_layout)
    LinearLayout llContainer;
    @BindView(R.id.add_new)
    ImageView addView;
    private List<WeatherFragment> screens;
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initLocation();
        initData();
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initData() {
        screens = new ArrayList<>();
        for (CityBean cityBean : DataManager.getInstance().getCities()) {
            WeatherFragment weatherFragment = new WeatherFragment();
            weatherFragment.setLocation(new double[]{cityBean.getLat(), cityBean.getLon()});
            screens.add(weatherFragment);
        }
        for (int i = 0; i < screens.size(); i++) {
            screens.get(i).setCode(i);
        }
        viewPager.setAdapter(new WeatherAdapter(this, screens));
        setCircle(screens.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                llContainer.getChildAt(mCurrentIndex).setEnabled(false);
                llContainer.getChildAt(i).setEnabled(true);
                mCurrentIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (WeatherFragment fragment : screens) {
            fragment.update();
        }
    }

    private void setCircle(int count) {
        View view;
        llContainer.removeAllViews();
        for (int i = 0; i < count; i++) {
            view = new View(this);
            view.setBackground(getDrawable(R.drawable.viewpager_dot));
            view.setEnabled(false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = 10;
            }
            llContainer.addView(view, params);
        }
        llContainer.getChildAt(mCurrentIndex).setEnabled(true);
    }

    private void initLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
            return;
        } else {
            if (LocationUtil.getLatAndLon() != null && LocationUtil.getLatAndLon()[0] != 200) {
                double[] result = LocationUtil.getLatAndLon();
                DataManager.getInstance().setDefaultCity(result[0], result[1]);
                initData();
                for (WeatherFragment fragment : screens) {
                    fragment.update();
                }
            } else {
                UIUtil.showToast(getString(R.string.gps_error), Toast.LENGTH_LONG);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (LocationUtil.getLatAndLon() != null && LocationUtil.getLatAndLon()[0] != 200) {
                double[] result = LocationUtil.getLatAndLon();
                DataManager.getInstance().setDefaultCity(result[0], result[1]);
                initData();
                for (WeatherFragment fragment : screens) {
                    fragment.update();
                }
            } else {
                UIUtil.showToast(getString(R.string.gps_error), Toast.LENGTH_LONG);
            }
        }
    }


}

package com.wuhao.weather.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wuhao.weather.R;
import com.wuhao.weather.WeatherApplication;
import com.wuhao.weather.adapter.ForecastAdapter;
import com.wuhao.weather.data.Constants;
import com.wuhao.weather.data.DataManager;
import com.wuhao.weather.model.ForcastWeatherResult;
import com.wuhao.weather.presenter.WeatherFragmentPresenter;
import com.wuhao.weather.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class WeatherFragment extends Fragment {

    @BindView(R.id.high_low_temperature)
    TextView highLowTemperature;
    @BindView(R.id.temperature)
    TextView currentTemperature;
    @BindView(R.id.city_name)
    TextView cityNameView;
    @BindView(R.id.forecast_list_view)
    RecyclerView recyclerView;
    @BindView(R.id.last_update_time)
    TextView lastUpdateDate;
    @BindView(R.id.weather_description)
    TextView currentWeather;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.wind_direction)
    TextView windDirView;
    @BindView(R.id.wind_speed)
    TextView windSpeed;
    @BindView(R.id.humidity_value)
    TextView humidityView;
    @BindView(R.id.visibility_value)
    TextView visibilityView;
    @BindView(R.id.pressure_value)
    TextView pressureView;
    @BindView(R.id.sunrise_value)
    TextView sunriseView;
    @BindView(R.id.sunset_value)
    TextView sunsetView;
    @BindView(R.id.location_icon)
    ImageView locationImage;
    @BindView(R.id.sticky_header)
    ConstraintLayout header;
    @BindView(R.id.header_high_low_temperature)
    TextView headerHighLowTemperature;
    @BindView(R.id.header_temperature)
    TextView headerCurrentTemperature;
    @BindView(R.id.header_weather_description)
    TextView headerCurrentWeather;
    @BindView(R.id.main_scroll)
    ScrollView scrollView;
    @BindView(R.id.unit)
    TextView unitView;
    @BindView(R.id.header_bottom_line)
    View headerBottomLine;
    @BindView(R.id.header_forecast_icon)
    ImageView headerWeatherIcon;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.wind_image)
    WindView windView;
    @BindView(R.id.wind_image_0)
    WindView windViewTwo;
    @BindView(R.id.fragment_progress_bar)
    ProgressBar processBar;
    private WeatherFragmentPresenter presenter;
    private int fragmentNumber;
    private double[] location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_weather, container, false);
        presenter = new WeatherFragmentPresenter();
        ButterKnife.bind(this, rootView);
        presenter.init(this);
        header.setAlpha(0);
        headerBottomLine.setAlpha(0);
        windView.startAnim();
        windViewTwo.startAnim();
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float scroll = UIUtil.convertPixelsToDp(scrollY);
                if (scroll >= 80) {
                    header.setAlpha(1);
                    setTempretureAlpha(0);
                } else if (scroll < 80) {
                    header.setAlpha(0);
                    setTempretureAlpha(1);
                }
                if (scroll > 80 && scroll < 100) {
                    setLocationViewAlpha(1 - (scroll - 80) / 20);
                    headerBottomLine.setAlpha((scroll - 80) / 20);
                } else if (scroll > 100) {
                    setLocationViewAlpha(0);
                    headerBottomLine.setAlpha(1);
                } else if (scroll < 80) {
                    headerBottomLine.setAlpha(0);
                    setLocationViewAlpha(1);
                }

            }
        });
        presenter.fetchData(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchData(true);
            }
        });
        locationImage.setVisibility((DataManager.getInstance().isLocationAvailable(WeatherApplication.getApplicaiton())
                && 0 == getCode()) ? View.VISIBLE : View.GONE);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        locationImage.setVisibility((DataManager.getInstance().isLocationAvailable(WeatherApplication.getApplicaiton())
                && 0 == getCode()) ? View.VISIBLE : View.GONE);
    }

    public void update() {
        if (presenter != null)
            presenter.fetchData(false);
    }

    public double[] getLocation() {
        return location;
    }

    public int getCode() {
        return fragmentNumber;
    }

    public void setCode(int code) {
        this.fragmentNumber = code;
    }

    public void setLocation(double[] location) {
        if (location != null)
            this.location = location;
    }

    public void setHighLowTemperature(String temperature) {
        highLowTemperature.setText(temperature);
        headerHighLowTemperature.setText(temperature);
    }

    public void updateCityName(String name) {
        cityNameView.setText(name);
    }

    public void setCurrentTemperature(int temperature) {
        currentTemperature.setText(String.valueOf(temperature));
        headerCurrentTemperature.setText(String.valueOf(temperature));
    }

    public void setUpdateTime(String date) {
        lastUpdateDate.setText(date);
    }

    public void setCurrentWeather(String weather) {
        currentWeather.setText(weather);
        headerCurrentWeather.setText(weather);
    }

    public void updateForecast(ForcastWeatherResult result) {
        if (result == null || result.getList() == null)
            return;
        ForecastAdapter adapter = new ForecastAdapter(WeatherApplication.getApplicaiton());
        adapter.setData(result.getList());
        recyclerView.setLayoutManager(new LinearLayoutManager(WeatherApplication.getApplicaiton()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void updateWind(String directon, String speed) {
        String format = WeatherApplication.getApplicaiton().getResources().getString(R.string.wind_direction);
        String str = String.format(format, directon);
        windDirView.setText(str);

        String format1 = WeatherApplication.getApplicaiton().getResources().getString(R.string.wind_speed);
        String str1 = String.format(format1, speed);
        windSpeed.setText(str1);
        windView.setWindVelocity(Double.valueOf(speed).intValue() / 2);
        windView.startAnim();
        windViewTwo.setWindVelocity(Double.valueOf(speed).intValue() / 2);
        windView.startAnim();
    }

    public void updateOtherInfo(String visibility, String pressure, String humidity) {
        humidityView.setText(humidity + "%");
        String format = WeatherApplication.getApplicaiton().getResources().getString(R.string.pressure);
        String str = String.format(format, pressure);
        pressureView.setText(str);

        String format1 = WeatherApplication.getApplicaiton().getResources().getString(R.string.visibility);
        String str1 = String.format(format1, visibility);
        visibilityView.setText(str1);
    }

    public void updateSunriseSunset(String sunrise, String sunset) {
        sunriseView.setText(sunrise);
        sunsetView.setText(sunset);
    }

    private void setLocationViewAlpha(float alpha) {
        locationImage.setAlpha(alpha);
        cityNameView.setAlpha(alpha);
        lastUpdateDate.setAlpha(alpha);
    }

    private void setTempretureAlpha(float alpha) {
        currentTemperature.setAlpha(alpha);
        highLowTemperature.setAlpha(alpha);
        currentWeather.setAlpha(alpha);
        unitView.setAlpha(alpha);
    }

    public void updateIcon(String icon) {
        Picasso.get().load(Constants.ICON_URL + icon + Constants.ICON_URL_ENDING)
                .into(headerWeatherIcon);
        Picasso.get().load(Constants.ICON_URL + icon + Constants.ICON_URL_ENDING)
                .into(weatherIcon);
    }

    public void setProgreeBarVisibilitiy(int visibility) {
        processBar.setVisibility(visibility);
        if (View.VISIBLE == visibility) {
            scrollView.setVisibility(View.INVISIBLE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
        }
    }
}



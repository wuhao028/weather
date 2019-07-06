package com.wuhao.weather.presenter;

import android.view.View;
import android.widget.Toast;

import com.wuhao.weather.R;
import com.wuhao.weather.WeatherApplication;
import com.wuhao.weather.data.DataManager;
import com.wuhao.weather.model.CurrentWeatherResult;
import com.wuhao.weather.model.ForcastWeatherResult;
import com.wuhao.weather.intf.WeatherResultListener;
import com.wuhao.weather.utils.NetworkUtil;
import com.wuhao.weather.utils.UIUtil;
import com.wuhao.weather.utils.Util;
import com.wuhao.weather.view.WeatherFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class WeatherFragmentPresenter implements WeatherResultListener {

    private WeatherFragment weatherFragment;

    public void init(WeatherFragment weatherFragment) {
        this.weatherFragment = weatherFragment;
        DataManager.getInstance().addListener(weatherFragment.getCode(), this);
    }

    public void fetchData(boolean isSwipeRefresh) {
        if (weatherFragment == null) {
            return;
        }
        weatherFragment.setProgreeBarVisibilitiy(isSwipeRefresh ? View.GONE : View.VISIBLE);
        if (!NetworkUtil.isNetworkAvailable()) {
            UIUtil.showToast(WeatherApplication.getApplicaiton().getString(R.string.network_error), Toast.LENGTH_LONG);
            weatherFragment.setProgreeBarVisibilitiy(View.GONE);
            weatherFragment.stopRefresh();
            return;
        }
        DataManager.getInstance().requestCurrentWeather(weatherFragment.getCode(), weatherFragment.getLocation()[0], weatherFragment.getLocation()[1]);
        DataManager.getInstance().requestForecastWeather(weatherFragment.getCode(), weatherFragment.getLocation()[0], weatherFragment.getLocation()[1]);

    }

    @Override
    public void onCurrentWeatherResult(int city, boolean isSuccess, CurrentWeatherResult result) {
        weatherFragment.stopRefresh();
        weatherFragment.setProgreeBarVisibilitiy(View.GONE);
        if (result != null && city == weatherFragment.getCode() && isSuccess) {
            if (result.getMain() != null) {
                weatherFragment.setCurrentTemperature((int) result.getMain().getTemp());
                weatherFragment.setHighLowTemperature(Util.getHighLowTemperature(result.getMain().getTemp_max(),
                        result.getMain().getTemp_min()));
                weatherFragment.updateOtherInfo(String.valueOf(result.getVisibility() / 1000), result.getMain().getPressure() + "", result.getMain().getHumidity() + "");
            }

            weatherFragment.updateCityName(result.getName());
            if (result.getWeather() != null && result.getWeather().size() > 0) {
                weatherFragment.setCurrentWeather(result.getWeather().get(0).getMain());
                weatherFragment.updateIcon(result.getWeather().get(0).getIcon());
            }
            if (result.getSys() != null) {
                String sunrise = Util.getTime(result.getSys().getSunrise() +
                        Util.getTimeZoneOffSet(new Date(), result.getTimezone()));
                String sunset = Util.getTime(result.getSys().getSunset() +
                        Util.getTimeZoneOffSet(new Date(), result.getTimezone()));
                weatherFragment.updateSunriseSunset(sunrise, sunset);
            }
            if (result.getWind() != null) {
                weatherFragment.updateWind(Util.getWindDirection(result.getWind().getDeg()), String.valueOf(result.getWind().getSpeed()));
            }
            weatherFragment.setUpdateTime(WeatherApplication.getApplicaiton().getString(R.string.last_update_time) + Util.getCurrentTime());
        }
    }

    @Override
    public void onForecastResult(int city, boolean isSuccess, ForcastWeatherResult result) {
        weatherFragment.stopRefresh();
        weatherFragment.setProgreeBarVisibilitiy(View.GONE);
        if (city == weatherFragment.getCode() && isSuccess) {
            weatherFragment.updateForecast(getFiveDaysForecast(result));

        }
    }

    @Override
    public void onFailure() {
        weatherFragment.setProgreeBarVisibilitiy(View.GONE);
        weatherFragment.stopRefresh();
    }

    private ForcastWeatherResult getFiveDaysForecast(ForcastWeatherResult rawResult) {
        if (rawResult == null || rawResult.getList() == null || rawResult.getList().size() == 0)
            return rawResult;
        LinkedHashMap<String, ForcastWeatherResult.ListBean> dates = new LinkedHashMap<>();
        List<ForcastWeatherResult.ListBean> forecasts = rawResult.getList();
        for (ForcastWeatherResult.ListBean listBean : forecasts) {
            if (dates.containsKey(Util.getDateFromBean(listBean.getDt_txt()))) {
                if (listBean.getMain().getTemp_max() > dates.get(Util.getDateFromBean(listBean.getDt_txt())).getMain().getTemp_max()) {
                    dates.get(Util.getDateFromBean(listBean.getDt_txt())).getMain().setTemp_max(listBean.getMain().getTemp_max());
                }
                if (listBean.getMain().getTemp_min() < dates.get(Util.getDateFromBean(listBean.getDt_txt())).getMain().getTemp_min()) {
                    dates.get(Util.getDateFromBean(listBean.getDt_txt())).getMain().setTemp_min(listBean.getMain().getTemp_min());
                }
            } else {
                dates.put(Util.getDateFromBean(listBean.getDt_txt()), listBean);
            }
        }
        ArrayList<ForcastWeatherResult.ListBean> list = new ArrayList<>();

        Iterator it = dates.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entity = (Map.Entry) it.next();
            list.add((ForcastWeatherResult.ListBean) entity.getValue());
        }
        rawResult.setList(list);
        return rawResult;
    }

}


package com.wuhao.weather.presenter;

import com.wuhao.weather.data.DataManager;
import com.wuhao.weather.model.CitySearchResult;
import com.wuhao.weather.intf.CitySearchListener;
import com.wuhao.weather.view.SearchActivity;

/**
 * Created by WuHao028 on 2019-07-07
 */
public class SearchActivityPresenter implements CitySearchListener {

    private SearchActivity activity;

    public SearchActivityPresenter(SearchActivity activity) {
        this.activity = activity;
        DataManager.getInstance().setCitySearchListener(this);
    }

    public void requestData(String cityName) {
        DataManager.getInstance().requestCitySearch(cityName);
    }

    @Override
    public void onResult(CitySearchResult result) {
        if (activity != null) {
            activity.hideProgressbar();
            activity.updateSearchResult(result);
        }
    }

    @Override
    public void onFail() {
        activity.hideProgressbar();
    }
}

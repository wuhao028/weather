package com.wuhao.weather.intf;

import com.wuhao.weather.model.CitySearchResult;

/**
 * Created by WuHao028 on 2019-07-07
 */
public interface CitySearchListener {
    void onResult(CitySearchResult result);

    void onFail();
}

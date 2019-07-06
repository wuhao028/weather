package com.wuhao.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wuhao.weather.R;
import com.wuhao.weather.data.Constants;
import com.wuhao.weather.model.ForcastWeatherResult;
import com.wuhao.weather.utils.Util;

import java.util.Date;
import java.util.List;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.WeatherHolder> {

    private LayoutInflater layoutInflater;
    private List<ForcastWeatherResult.ListBean> data;

    public ForecastAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<ForcastWeatherResult.ListBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WeatherItemHolder(layoutInflater.inflate(R.layout.forecast_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder viewHolder, int i) {
        viewHolder.bindHolder(data.get(i), i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract class WeatherHolder extends RecyclerView.ViewHolder {

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bindHolder(ForcastWeatherResult.ListBean model, int i);
    }

    public class WeatherItemHolder extends WeatherHolder {

        TextView date;
        TextView weather;
        TextView temperature;
        ImageView iconView;

        public WeatherItemHolder(View view) {
            super(view);
            date = view.findViewById(R.id.forecast_date);
            weather = view.findViewById(R.id.forecast_weather);
            temperature = view.findViewById(R.id.forecast_temperature);
            iconView = view.findViewById(R.id.forecast_icon);
        }

        public void bindHolder(ForcastWeatherResult.ListBean data, int i) {
            date.setText(Util.getWeekOfDate(new Date(), i));
            weather.setText(data.getWeather().get(0).getMain());
            temperature.setText(Util.getHighLowTemperature(data.getMain().getTemp_max(),
                    data.getMain().getTemp_min()));
            Picasso.get().load(Constants.ICON_URL + data.getWeather().get(0).getIcon() + Constants.ICON_URL_ENDING)
                    .into(iconView);
        }

    }


}
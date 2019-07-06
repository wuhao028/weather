package com.wuhao.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuhao.weather.R;
import com.wuhao.weather.data.Constants;
import com.wuhao.weather.model.CityBean;
import com.wuhao.weather.model.CitySearchResult;

import java.util.List;

/**
 * Created by WuHao028 on 2019-07-07
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchResultHolder> {

    private LayoutInflater layoutInflater;
    private List<CitySearchResult.ListBean> data;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position, CityBean cityBean);
    }

    public SearchAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<CitySearchResult.ListBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SearchAdapter.SearchResultHolder viewHolder = new SearchAdapter.SearchResultHolder(layoutInflater.inflate(R.layout.search_result_item, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchResultHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i, new CityBean(data.get(i).getName(),
                            data.get(i).getCoord().getLon(), data.get(i).getCoord().getLat()));
                }
            }
        });
        viewHolder.bindHolder(data.get(i), i);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract class SearchHolder extends RecyclerView.ViewHolder {

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bindHolder(CitySearchResult.ListBean model, int i);
    }

    public class SearchResultHolder extends SearchAdapter.SearchHolder {

        TextView cityName, weather, temperature;

        public SearchResultHolder(View view) {
            super(view);
            weather = view.findViewById(R.id.search_weather);
            temperature = view.findViewById(R.id.search_temperature);
            cityName = view.findViewById(R.id.search_city_name);
        }

        public void bindHolder(@NonNull CitySearchResult.ListBean data, int i) {
            if (data.getSys() != null)
                cityName.setText(data.getName() + "," + data.getSys().getCountry());
            if (data.getMain() != null)
                temperature.setText(data.getMain().getTemp() + Constants.TEMPERATURE_UNIT);
            if (data.getWeather() != null && data.getWeather().size() > 0)
                weather.setText(data.getWeather().get(0).getDescription());
        }

    }

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
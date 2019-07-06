package com.wuhao.weather.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wuhao.weather.R;
import com.wuhao.weather.WeatherApplication;
import com.wuhao.weather.adapter.SearchAdapter;
import com.wuhao.weather.model.CityBean;
import com.wuhao.weather.model.CitySearchResult;
import com.wuhao.weather.presenter.SearchActivityPresenter;
import com.wuhao.weather.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_list)
    RecyclerView recyclerView;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.error_view)
    TextView error;
    private SearchActivityPresenter presenter;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        presenter = new SearchActivityPresenter(this);
        adapter = new SearchAdapter(WeatherApplication.getApplicaiton());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.requestData(query);
                progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.setAdapter(null);
                error.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                return false;
            }
        });
        adapter.setmOnItemClickLitener(new SearchAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, CityBean cityBean) {
                UIUtil.showToast("click on " + cityBean.getCityName(), Toast.LENGTH_LONG);
            }
        });
    }

    public void updateSearchResult(CitySearchResult result) {
        if (result == null || result.getList() == null || result.getCount() == 0) {
            error.setVisibility(View.VISIBLE);
            return;
        }
        adapter.setData(result.getList());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(WeatherApplication.getApplicaiton()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void hideProgressbar() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }

}
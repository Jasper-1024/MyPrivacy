package com.jasperhale.myprivacy.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.jasperhale.myprivacy.Activity.Base.BaseActivity;
import com.jasperhale.myprivacy.Activity.View.SettingActivity;
import com.jasperhale.myprivacy.Activity.ViewModel.mViewModel;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.presenter.Presenter;
import com.jasperhale.myprivacy.Activity.presenter.mPresenter;
import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends BaseActivity implements MainActicityinterface ,SearchView.OnQueryTextListener{

    private ActivityMainBinding binding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    private com.jasperhale.myprivacy.Activity.ViewModel.mViewModel mViewModel;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化 binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //获取swipeRefreshLayout实例
        swipeRefreshLayout = binding.swipeRefreshLayout;
        //设置toolbar
        toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        //获取mViewModel实例,初始化adapter
        mViewModel = new mViewModel(this);
        //获取Presenter实例
        presenter = new mPresenter(mViewModel);

        //下拉刷新绑定
        initSwipeRefresh();
        //获取应用列表
        presenter.RefreshView();
    }


    @Override
    public void onResume() {
        //界面重新刷新
        //presenter.RefreshView();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        //释放viemmodel
        mViewModel = null;
        super.onDestroy();
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.RefreshView();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void initDataBinding(BindingAdapter adapter) {
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.getLayoutManager().setAutoMeasureEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onQueryTextChange(String query) {
        presenter.SeaechView(query);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}



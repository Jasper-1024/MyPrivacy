package com.jasperhale.myprivacy.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.jasperhale.myprivacy.Activity.View.SettingActivity;
import com.jasperhale.myprivacy.Activity.ViewModel.mViewModel;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.presenter.Presenter;
import com.jasperhale.myprivacy.Activity.presenter.mPresenter;
import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainActicityinterface {

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
        //界面重新刷新 需要?
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
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
}



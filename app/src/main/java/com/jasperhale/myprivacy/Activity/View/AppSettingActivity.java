package com.jasperhale.myprivacy.Activity.View;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.jasperhale.myprivacy.Activity.Base.BaseActivity;
import com.jasperhale.myprivacy.Activity.ViewModel.mViewModeApp;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.presenter.PresenterApp;
import com.jasperhale.myprivacy.Activity.presenter.mPresenterApp;
import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityAppSettingBinding;


public class AppSettingActivity extends BaseActivity  {

    private PresenterApp presenterApp;
    private mViewModeApp mViewModeApp;

    private ActivityAppSettingBinding binding;
    private BindingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        String PackageName = getIntent().getStringExtra("PackageName");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_app_setting);
        adapter = new BindingAdapter();

        mViewModeApp = new mViewModeApp(PackageName);
        mViewModeApp.setAdapter(adapter);
        initDataBinding();

        presenterApp = new mPresenterApp(mViewModeApp);

        Toolbar toolbar = binding.appsettingToolbar;
        //Toolbar toolbar = findViewById(R.id.recyclerView_appsetting);
        toolbar.setTitle(PackageName);
        setSupportActionBar(toolbar);
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void onStart() {
        //加载设置
        presenterApp.loadAppSetting();
        super.onStart();
    }
    @Override
    public void onDestroy(){
        //保存设置
        presenterApp.saveAppSetting();
        mViewModeApp = null;
        super.onDestroy();
    }

    //返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initDataBinding() {
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerViewAppsetting.setLayoutManager(manager);
        binding.recyclerViewAppsetting.setAdapter(adapter);
        binding.recyclerViewAppsetting.setNestedScrollingEnabled(false);
        binding.recyclerViewAppsetting.setHasFixedSize(true);
        binding.recyclerViewAppsetting.getLayoutManager().setAutoMeasureEnabled(true);
    }

}

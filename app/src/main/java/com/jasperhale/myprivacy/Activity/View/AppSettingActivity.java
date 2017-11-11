package com.jasperhale.myprivacy.Activity.View;

import android.content.pm.PackageInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.jasperhale.myprivacy.Activity.ViewModel.ViewModel;
import com.jasperhale.myprivacy.Activity.ViewModel.ViewModelApp;
import com.jasperhale.myprivacy.Activity.ViewModel.mViewModeApp;
import com.jasperhale.myprivacy.Activity.presenter.PresenterApp;
import com.jasperhale.myprivacy.Activity.presenter.mPresenterApp;
import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityAppSettingBinding;
import com.jasperhale.myprivacy.databinding.ActivitySettingBinding;

import static android.content.ContentValues.TAG;

public class AppSettingActivity extends AppCompatActivity {

    private String PackageName;
    PresenterApp presenterApp;
    mViewModeApp mViewModeApp;

    ActivityAppSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);
        PackageName = getIntent().getStringExtra("PackageName");

        mViewModeApp = new mViewModeApp(PackageName);
        presenterApp = new mPresenterApp(mViewModeApp);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_app_setting);
        binding.setAppsetting(mViewModeApp.getAppSetting());


        Toolbar toolbar = binding.appsettingToolbar;
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
        presenterApp.initAppSetting();
        super.onStart();
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

}

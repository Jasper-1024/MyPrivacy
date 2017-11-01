package com.jasperhale.myprivacy.Activity.ViewModel;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;


import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;
import com.jasperhale.myprivacy.Activity.presenter.Presenter;
import com.jasperhale.myprivacy.Activity.presenter.mPresenter;
import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Acticityinterface {

    private ActivityMainBinding binding;
    private mViewModel mViewModel;
    private Presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取mViewModel实例,初始化adapter
        mViewModel = new mViewModel(this);
        //获取Presenter实例
        presenter = new mPresenter(mViewModel);
        //获取应用列表
        presenter.RefreshView();
    }


    @Override
    public void initDataBinding(BindingAdapter adapter) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
    }
}

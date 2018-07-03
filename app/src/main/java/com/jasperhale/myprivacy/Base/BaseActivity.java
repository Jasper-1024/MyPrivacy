package com.jasperhale.myprivacy.Base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by ZHANG on 2017/11/12.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("Activity", getClass().getSimpleName()+"onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("Activity", getClass().getSimpleName()+"onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("Activity", getClass().getSimpleName()+"onDestroy");
    }

}

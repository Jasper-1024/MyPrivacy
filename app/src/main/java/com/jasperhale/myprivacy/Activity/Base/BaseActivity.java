package com.jasperhale.myprivacy.Activity.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by ZHANG on 2017/11/12.
 */

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

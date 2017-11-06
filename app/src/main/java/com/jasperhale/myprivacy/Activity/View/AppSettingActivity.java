package com.jasperhale.myprivacy.Activity.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.jasperhale.myprivacy.R;

public class AppSettingActivity extends AppCompatActivity {

    private AppSettingFragment appSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appsetting_toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

       appSettingFragment = new AppSettingFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.appsetting_FrameLayout,appSettingFragment)
                .commit();

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

package com.jasperhale.myprivacy.Activity.View;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.jasperhale.myprivacy.R;

import static android.content.ContentValues.TAG;

public class AppSettingActivity extends AppCompatActivity {

    private String PackageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        PackageName = getIntent().getStringExtra("PackageName");
        Log.d(TAG, "onClick: "+PackageName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appsetting_toolbar);
        toolbar.setTitle(PackageName);
        setSupportActionBar(toolbar);
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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

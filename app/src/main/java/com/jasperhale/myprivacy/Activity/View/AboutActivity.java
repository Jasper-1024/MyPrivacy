package com.jasperhale.myprivacy.Activity.View;

import android.didikee.donate.AlipayDonate;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jasperhale.myprivacy.Activity.Base.BaseActivity;
import com.jasperhale.myprivacy.R;

public class AboutActivity extends BaseActivity {

    private final String payCode = "FKX09422HHZKGGZXYXYI8D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar_about);

        toolbar.setTitle("关于");
        setSupportActionBar(toolbar);
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onClick(View view){
        donateAlipay(payCode);
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

    private void donateAlipay(String payCode) {
        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(this);
        if (hasInstalledAlipayClient) {
            AlipayDonate.startAlipayClient(this, payCode);
        }
    }

}

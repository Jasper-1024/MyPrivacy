package Ui.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ViewModel.HookViewModel;

import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityHookBinding;

@SuppressWarnings("ALL")
public class HookActivity extends AppCompatActivity {
    private final String TAG = "HookActivity";

    private HookViewModel hookViewModel;

    private ActivityHookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        String packageName = getIntent().getStringExtra("PackageName");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_hook);

        /*实例化hookViewModel*/
        hookViewModel = ViewModelProviders.of(this).get(HookViewModel.class);
        hookViewModel.packageName = packageName;
        /*绑定生命周期*/
        this.getLifecycle().addObserver(hookViewModel);

        binding.setHookViewModel(hookViewModel);
        binding.setLifecycleOwner(this);

        binding.rvGroup.setNestedScrollingEnabled(false);
        binding.rvGroup.setHasFixedSize(false);

        Toolbar toolbar = binding.appsettingToolbar;
        toolbar.setTitle(packageName);
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
        hookViewModel.save();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

// --Commented out by Inspection START (2018/6/29 0:20):
//    public void test(){
//        LogUtil.d(TAG,"test");
//        hookViewModel.appHook.postValue(hookViewModel.appHook.getValue());
//    }
// --Commented out by Inspection STOP (2018/6/29 0:20)
}

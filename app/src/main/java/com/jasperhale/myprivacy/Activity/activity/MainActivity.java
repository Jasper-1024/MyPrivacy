package com.jasperhale.myprivacy.Activity.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;
import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initListView();

    }

    private void initListView() {
        List<BindingAdapterItem> items = new ArrayList<>();
        items.add(new ApplistItem("终于完成了"));
        items.add(new ApplistItem("睡觉"));
        items.add(new ApplistItem("commit"));
        BindingAdapter adapter = new BindingAdapter();
        adapter.setItems(items);
        //这也是一个坑，经常忘了加LayoutManger导致东西Item无法显示，RecyclerView把测量，布局的工作甩给了LayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

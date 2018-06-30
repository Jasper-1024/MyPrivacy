package Ui.Activity;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import Base.BaseActivity;
import Base.LogUtil;
import Base.MyApplicantion;
import Ui.Databind.RecycleAdapter;
import ViewModel.MainViewModel;

import com.jasperhale.myprivacy.R;
import com.jasperhale.myprivacy.databinding.ActivityMainBinding;

import static android.support.constraint.Constraints.TAG;

public class MainActivity extends BaseActivity {

    private final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private Menu menu = null;
    private RecyclerView recyclerView = null;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //设置toolbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);


        recyclerView = binding.recyclerView;
        /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);

        RecycleAdapter adapter = new RecycleAdapter();
        recyclerView.setAdapter(adapter);
        */

        recyclerView.setHasFixedSize(true);
        /*分割线*/
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //获取mainViewModel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //绑定监听
        this.getLifecycle().addObserver(mainViewModel);
        //绑定数据
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(this);
        /*SwipeRefresh*/
        initSwipeRefresh();

    }

    private void initSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            LogUtil.d(TAG, "Refresh");
            mainViewModel.RefreshList();
            binding.swipeRefreshLayout.setRefreshing(false);
        });
    }

    /*获取键值*/
    private String get_menu_list() {
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.menu_list), Context.MODE_PRIVATE);
        return sharedPref.getString(getString(R.string.menu_list_key), getString(R.string.menu_list_default));
    }

    /*设置键值*/
    private void set_menu_list(String s) {
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.menu_list), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();//获取编辑器
        editor.putString(getString(R.string.menu_list_key), s);
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "Create options");
        this.menu = menu;
        getMenuInflater().inflate(R.menu.mainactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.i(TAG, "Selected option " + item.getTitle());
        switch (item.getItemId()) {
            case R.id.menu_show: {
                String show = get_menu_list();
                switch (show) {
                    case "user":
                        this.menu.findItem(R.id.menu_show_user).setChecked(true);
                        break;
                    case "system":
                        this.menu.findItem(R.id.menu_show_system).setChecked(true);
                        break;
                    case "all":
                        this.menu.findItem(R.id.menu_show_all).setChecked(true);
                        break;
                }
                return true;
            }

            case R.id.menu_show_user:
            case R.id.menu_show_system:
            case R.id.menu_show_all:
                item.setChecked(!item.isChecked());
                switch (item.getItemId()) {
                    case R.id.menu_show_user:
                        set_menu_list(getString(R.string.menu_list_user));
                        break;
                    case R.id.menu_show_system:
                        set_menu_list(getString(R.string.menu_list_system));
                        break;
                    case R.id.menu_show_all:
                        set_menu_list(getString(R.string.menu_list_all));
                        break;
                    default:
                        set_menu_list(getString(R.string.menu_list_default));
                        break;
                }
                mainViewModel.changList();
                return true;

            case R.id.menu_help: {

                return true;
            }
            case R.id.menu_about: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.i(TAG, "Prepare options");

        // Search
        MenuItem menuSearch = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "Search submit=" + query);
                if (recyclerView != null) {
                    RecycleAdapter adapter = (RecycleAdapter) recyclerView.getAdapter();
                    adapter.getFilter().filter(query);
                    searchView.clearFocus(); // close keyboard
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "Search change=" + newText);
                if (recyclerView != null) {
                    RecycleAdapter adapter = (RecycleAdapter) recyclerView.getAdapter();
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }


}

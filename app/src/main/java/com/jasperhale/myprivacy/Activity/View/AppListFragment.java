package com.jasperhale.myprivacy.Activity.View;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.ViewModel.ViewModel;
import com.jasperhale.myprivacy.Activity.ViewModel.mViewModel;
import com.jasperhale.myprivacy.Activity.adapter.BindAdapter_applist;
import com.jasperhale.myprivacy.Activity.presenter.Presenter;
import com.jasperhale.myprivacy.Activity.presenter.mPresenter;
import com.jasperhale.myprivacy.R;


public class AppListFragment extends Fragment {

    private com.jasperhale.myprivacy.AppListFragment binding;
    private BindAdapter_applist adapter;
    private int position;
    private Presenter presenter;
    private ViewModel viewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.d("UI","onCreate"+String.valueOf(position));
        viewModel = new mViewModel();
        presenter = new mPresenter(viewModel,position);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.d("UI","onCreateView"+String.valueOf(position));

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_app_list, container, false);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(viewModel.getAdapter());

        binding.recyclerView.setNestedScrollingEnabled(false);
        //binding.recyclerView.setHasFixedSize(true);
        //binding.recyclerView.getLayoutManager().setAutoMeasureEnabled(true);

        initSwipeRefresh();
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        LogUtil.d("UI","onStart()"+String.valueOf(position));
        presenter.RefreshView();
        super.onStart();
    }

    @Override
    public void onPause() {
        LogUtil.d("UI","onPause"+String.valueOf(position));
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtil.d("UI","onStop("+String.valueOf(position));
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtil.d("UI","onDestroyView"+String.valueOf(position));
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtil.d("UI","onDestroy"+String.valueOf(position));
        viewModel = null;
        presenter = null;
        super.onDestroy();
    }


    private void initSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.RefreshView();
            binding.swipeRefreshLayout.setRefreshing(false);
        });
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public Presenter getPresenter() {
        return presenter;
    }
    public void Search(String query){
        presenter.SeaechView(query);
    }
}

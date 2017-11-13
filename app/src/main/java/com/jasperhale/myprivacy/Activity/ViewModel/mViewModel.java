package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.MainActicityinterface;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public class mViewModel implements ViewModel {
    //List<BindingAdapterItem> items = new ArrayList<>();
    private BindingAdapter adapter = new BindingAdapter();

    public mViewModel(MainActicityinterface acticityinterface){
        //初始化adapter绑定
        acticityinterface.initDataBinding(adapter);
    }

    @Override
    public List<BindingAdapterItem> getItems() {
        return adapter.getItems();
    }
    @Override
    public void setItem(BindingAdapterItem item) {
        adapter.setItem(item);
    }

    @Override
    public void setItems(List<BindingAdapterItem> items) {
        adapter.setItems(items);
    }
    @Override
    public void addItem(BindingAdapterItem item) {
        adapter.addItem(item);
    }

    @Override
    public void addItem(BindingAdapterItem item, int position) {
        adapter.addItem(item,position);
    }

    @Override
    public void addItems(List<BindingAdapterItem> items) {
        adapter.addItems(items);
    }

    @Override
    public void removeItem(BindingAdapterItem item) {
        adapter.removeItem(item);
    }

    @Override
    public void replaceItem(BindingAdapterItem item, int position) {
        adapter.replaceItem(item, position);
    }

    @Override
    public void clearItems() {
        adapter.clearItems();
    }

    @Override
    public void RefreshRecycleView() {
        adapter.notifyDataSetChanged();
    }
}

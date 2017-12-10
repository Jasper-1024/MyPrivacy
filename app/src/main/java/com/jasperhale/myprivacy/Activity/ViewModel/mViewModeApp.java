package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/11/8.
 */

public class mViewModeApp implements ViewModelApp{
    private BindingAdapter adapter ;
    private final String PackageName;

    public mViewModeApp(String packageName){
        this.PackageName = packageName;
    }

    @Override
    public void setAdapter(BindingAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public String getPackageName(){
        return PackageName;
    }

    @Override
    public List<BindingAdapterItem> getItems() {
        return adapter.getItems();
    }

    @Override
    public void setItems(List<BindingAdapterItem> items) {
        adapter.setItems(items);
    }

    @Override
    public void clearItems() {
        adapter.clearItems();
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }
}

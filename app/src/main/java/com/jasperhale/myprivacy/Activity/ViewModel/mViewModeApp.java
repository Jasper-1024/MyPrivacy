package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.MainActicityinterface;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/11/8.
 */

public class mViewModeApp implements ViewModelApp{
    private BindingAdapter adapter = new BindingAdapter();;

    private final String PackageName;
    /*public void mViewModeApp(AppSetting appSetting){
        this.appSetting = appSetting;
    }*/
    public mViewModeApp(MainActicityinterface acticityinterface,String packageName){
        acticityinterface.initDataBinding(adapter);
        this.PackageName = packageName;
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

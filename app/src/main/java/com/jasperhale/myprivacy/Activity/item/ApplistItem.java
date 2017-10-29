package com.jasperhale.myprivacy.Activity.item;


import android.databinding.Bindable;


import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/10/29.
 */

public class ApplistItem extends BaseItem implements BindingAdapterItem{

    @Override
    public int getViewType(){
        return R.layout.item_applist;
    }

    public ApplistItem(String AppId){
        this.AppId = AppId;
    }

    private String AppId;

    @Bindable
    public String getAppId(){
        return AppId;
    }

    public void setAppId(String AppId){
        this.AppId = AppId;
        notifyPropertyChanged(BR.appId);
    }

}

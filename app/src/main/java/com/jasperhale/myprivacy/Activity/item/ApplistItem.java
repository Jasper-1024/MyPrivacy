package com.jasperhale.myprivacy.Activity.item;


import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.view.View;


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

    public ApplistItem(String AppId,Drawable AppIcon){
        this.AppId = AppId;
        this.AppIcon = AppIcon;
    }

    private String AppId;
    private Drawable AppIcon;

    @Bindable
    public String getAppId(){
        return AppId;
    }

    public void setAppId(String AppId){
        this.AppId = AppId;
        notifyPropertyChanged(BR.appId);
    }

    @Bindable
    public Drawable getAppIcon(){
        AppIcon.setBounds(0,0,5,5);
        return AppIcon;
    }
    public void setAppIcon(Drawable AppIcon){
        this.AppIcon = AppIcon;
        notifyPropertyChanged(BR.appIcon);
    }


}

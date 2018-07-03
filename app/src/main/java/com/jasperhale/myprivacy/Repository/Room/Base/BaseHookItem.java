package com.jasperhale.myprivacy.Repository.Room.Base;

import android.databinding.Bindable;
import android.support.annotation.Keep;

import com.jasperhale.myprivacy.Base.LogUtil;
import com.jasperhale.myprivacy.Ui.Item.BaseItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

@SuppressWarnings("WeakerAccess")
@Keep
public abstract class BaseHookItem extends BaseItem {

    public boolean value = false;
    public String item_name = "default";

    @Override
    public int getViewType() {
        return R.layout.item_hook;
    }

    @Bindable
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        LogUtil.d("BaseHookItem",""+ value);
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public String getItem_name(){
        LogUtil.d("BaseHookItem","getItem_name");
        return item_name;
    }
    public void setItem_name(String name){
        this.item_name = name;
        notifyPropertyChanged(BR.item_name);
    }
}

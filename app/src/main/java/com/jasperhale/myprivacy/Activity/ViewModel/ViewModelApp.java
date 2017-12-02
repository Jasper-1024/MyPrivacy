package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/11/8.
 */

public interface ViewModelApp {
    String getPackageName();
    //显示list<item>
    void setItems(List<BindingAdapterItem> items);
    //获取List<BindingAdapterItem> 实例
    List<BindingAdapterItem> getItems();

    //notifyDataSetChanged
    void notifyDataSetChanged();

    //清除item
    void clearItems();
}

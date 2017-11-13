package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface ViewModel {
    //获取List<BindingAdapterItem> 实例
    List<BindingAdapterItem> getItems();

    //显示list<item>
    void setItems(List<BindingAdapterItem> items);

    //显示单个item
    void setItem(BindingAdapterItem item);

    //添加item
    void addItem(BindingAdapterItem item);

    //指定位置添加item
    void addItem(BindingAdapterItem item, int position);

    //添加list<item>
    void addItems(List<BindingAdapterItem> items);

    //替换item
    void replaceItem(BindingAdapterItem item, int position);

    //移除item
    void removeItem(BindingAdapterItem item);

    //清除item
    void clearItems();

    //notifyDataSetChanged
    void RefreshRecycleView();
}

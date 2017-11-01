package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface ViewModel {
    //获取List<BindingAdapterItem> 实例
    public List<BindingAdapterItem> getItems();

    //显示list<item>
    public void setItems(List<BindingAdapterItem> items);

    //显示单个item
    public void setItem(BindingAdapterItem item);

    //添加item
    public void addItem(BindingAdapterItem item);

    //指定位置添加item
    public void addItem(BindingAdapterItem item, int position);

    //添加list<item>
    public void addItems(List<BindingAdapterItem> items);

    //替换item
    public void replaceItem(BindingAdapterItem item, int position);

    //移除item
    public void removeItem(BindingAdapterItem item);

    //清除item
    public void clearItems();

    //notifyDataSetChanged
    public void RefreshRecycleView();
}

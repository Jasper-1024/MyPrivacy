package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface ViewModel {
    //设置adapter
    void setAdapter(BindingAdapter adapter);

    //获取List<BindingAdapterItem>
    List<BindingAdapterItem> getItems();

    //获取List_Backup<BindingAdapterItem> 实例
    List<BindingAdapterItem> getItems_backup();

    //显示list<item>
    void setItems(List<BindingAdapterItem> items);
    //显示item
    void setItem(BindingAdapterItem items);

    //备份
    void setItems_backup();
    void setItems_backup(List<BindingAdapterItem> items);

    //清除item
    void clearItems();

    //notifyDataSetChanged
    void notifyDataSetChanged();

    void RefreshRecycleView(List<BindingAdapterItem> items);
}

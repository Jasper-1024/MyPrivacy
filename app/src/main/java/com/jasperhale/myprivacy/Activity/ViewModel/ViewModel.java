package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindAdapter_applist;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface ViewModel {
    //设置adapter
    void setAdapter(BindAdapter_applist adapter);

    //获取List<BindingAdapterItem>
    List<ApplistItem> getItems();

    //获取List_Backup<BindingAdapterItem> 实例
    List<ApplistItem> getItems_backup();

    //显示list<item>
    void setItems(List<ApplistItem> items);
    //显示item
    void setItem(ApplistItem items);

    //备份
    void setItems_backup();
    void setItems_backup(List<ApplistItem> items);

    //清除item
    void clearItems();

    //notifyDataSetChanged
    void notifyDataSetChanged();

    void RefreshRecycleView(List<ApplistItem> items);
}

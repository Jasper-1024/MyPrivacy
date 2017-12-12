package com.jasperhale.myprivacy.Activity.ViewModel;

import android.support.v7.util.DiffUtil;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.adapter.BindAdapter_applist;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;
import com.jasperhale.myprivacy.Activity.item.DiffCallBack_ApplistItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ZHANG on 2017/10/31.
 */

public class mViewModel implements ViewModel {
    private BindAdapter_applist adapter = new BindAdapter_applist();

    @Override
    public BindAdapter_applist getAdapter() {
        return adapter;
    }

    @Override
    public void setAdapter(BindAdapter_applist adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<ApplistItem> getItems() {
        return adapter.getItems();
    }

    @Override
    public List<ApplistItem> getItems_backup() {
        return adapter.getItems_backup();
    }

    @Override
    public void setItems(List<ApplistItem> items) {
        adapter.setItems(items);
    }

    @Override
    public void setItem(ApplistItem item) {
        adapter.setItem(item);
    }

    @Override
    public void setItems_backup() {
        adapter.setItems_backup();
    }

    @Override
    public void setItems_backup(List<ApplistItem> items) {
        adapter.setItems_backup(items);
    }


    @Override
    public void clearItems() {
        adapter.clearItems();
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void RefreshRecycleView(List<ApplistItem> items) {
        LogUtil.d("UI", String.valueOf(items.size()));
        Observable
                .create((ObservableOnSubscribe<String>)
                        emitter -> emitter.onNext("")
                )
                .subscribeOn(Schedulers.trampoline())
                //cpu密集
                .observeOn(Schedulers.newThread())
                .map(s -> DiffUtil.calculateDiff(new DiffCallBack_ApplistItem(adapter.getItems(), items), false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(diffResult -> {
                    diffResult.dispatchUpdatesTo(adapter);
                    adapter.setItems(items);
                });
    }
}

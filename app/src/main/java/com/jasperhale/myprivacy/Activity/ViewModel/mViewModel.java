package com.jasperhale.myprivacy.Activity.ViewModel;

import android.support.v4.util.Pair;
import android.support.v7.util.DiffUtil;

import com.jasperhale.myprivacy.Activity.MainActicityinterface;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.DiffCallBack_ApplistItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by ZHANG on 2017/10/31.
 */

public class mViewModel implements ViewModel {
    //List<BindingAdapterItem> items = new ArrayList<>();
    private BindingAdapter adapter = new BindingAdapter();
    private Disposable disposable;

    public mViewModel(MainActicityinterface acticityinterface) {
        //初始化adapter绑定
        acticityinterface.initDataBinding(adapter);
    }

    @Override
    public List<BindingAdapterItem> getItems() {
        return adapter.getItems();
    }

    @Override
    public List<BindingAdapterItem> getItems_backup() {
        return adapter.getItems_backup();
    }

    @Override
    public void setItem(BindingAdapterItem item) {
        adapter.setItem(item);
    }

    @Override
    public void setItems_backup() {
        adapter.setItems_backup();
    }

    @Override
    public void setItems_backup(List<BindingAdapterItem> items) {
        adapter.setItems_backup(items);
    }

    @Override
    public void setItems(List<BindingAdapterItem> items) {
        adapter.setItems(items);
    }

    @Override
    public void addItem(BindingAdapterItem item) {
        adapter.addItem(item);
    }

    @Override
    public void addItem(BindingAdapterItem item, int position) {
        adapter.addItem(item, position);
    }

    @Override
    public void addItems(List<BindingAdapterItem> items) {
        adapter.addItems(items);
    }

    @Override
    public void removeItem(BindingAdapterItem item) {
        adapter.removeItem(item);
    }

    @Override
    public void replaceItem(BindingAdapterItem item, int position) {
        adapter.replaceItem(item, position);
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
    public void RefreshRecycleView(List<BindingAdapterItem> items) {

        Pair<DiffUtil.DiffResult, List<BindingAdapterItem>> initialPair = Pair.create(null, items);

        Observable
                .create((ObservableOnSubscribe<Pair<DiffUtil.DiffResult, List<BindingAdapterItem>>>)
                        emitter -> emitter.onNext(initialPair)
                )
                //.subscribeOn(Schedulers.newThread())
                //cpu密集 排序
                .observeOn(Schedulers.computation())
                .map(Pair_Applist -> {
                    List<BindingAdapterItem> items1 = Pair_Applist.second;
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack_ApplistItem(adapter.getItems(), items1), true);
                    return Pair.create(diffResult, items1);
                })
                .observeOn(mainThread())
                .subscribe(Pair_Applist -> {
                    DiffUtil.DiffResult diffResult = Pair_Applist.first;
                    diffResult.dispatchUpdatesTo(adapter);
                    adapter.setItems(Pair_Applist.second);
                });

    }
}

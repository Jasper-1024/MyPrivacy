package com.jasperhale.myprivacy.Activity.ViewModel;

import android.support.v4.util.Pair;
import android.support.v7.util.DiffUtil;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.adapter.BindAdapter_applist;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
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

    public BindAdapter_applist getAdapter(){
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
                .map(s -> {
                    return DiffUtil.calculateDiff(new DiffCallBack_ApplistItem(adapter.getItems(), items), false);
                })
                //Android.mainThread()
                //Schedulers.trampoline()
                //.observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.trampoline())
                .subscribe(diffResult -> {
                    LogUtil.d("UI", "DiffUtil.calculateDiff");
                    diffResult.dispatchUpdatesTo(adapter);
                    LogUtil.d("UI", "DiffUtil.calculateDiff2");
                    adapter.setItems(items);
                    LogUtil.d("UI", "DiffUtil.calculateDiff3");
                    adapter.notifyDataSetChanged();
                });

        /*
        DiffUtil.DiffResult diffResults = DiffUtil.calculateDiff(new DiffCallBack_ApplistItem(adapter.getItems(), items), true);
        //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
        diffResult.dispatchUpdatesTo(adapter);
        //别忘了将新数据给Adapter
        adapter.setItems(items);
        */
        /*
        Pair<DiffUtil.DiffResult, List<ApplistItem>> initialPair = Pair.create(null, items);
        Observable
                .create((ObservableOnSubscribe<Pair<DiffUtil.DiffResult, List<ApplistItem>>>)
                        emitter -> emitter.onNext(initialPair)
                )
                .subscribeOn(Schedulers.trampoline())
                //cpu密集
                .observeOn(Schedulers.computation())
                //.observeOn(Schedulers.trampoline())
                .map(Pair_Applist -> {
                    List<ApplistItem> items1 = Pair_Applist.second;
                    //利用DiffUtil.calculateDiff()方法，传入一个规则DiffUtil.Callback对象，和是否检测移动item的 boolean变量，得到DiffUtil.DiffResult 的对象
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack_ApplistItem(adapter.getItems(), items1), false);
                    return Pair.create(diffResult, items1);
                })
                //Android.mainThread()
                //Schedulers.trampoline()
                //.observeOn(AndroidSchedulers.mainThread())
                //.observeOn(Schedulers.newThread())
                .observeOn(Schedulers.trampoline())
                .subscribe(Pair_Applist -> {
                    DiffUtil.DiffResult diffResult = Pair_Applist.first;
                    //传入RecyclerView的Adapter
                    diffResult.dispatchUpdatesTo(adapter);
                    adapter.setItems(Pair_Applist.second);
                });*/

    }
}

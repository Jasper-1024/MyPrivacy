package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapter;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;

import java.util.List;


/**
 * Created by ZHANG on 2017/10/31.
 */

public class mViewModel implements ViewModel {
    private BindingAdapter adapter;

    @Override
    public void setAdapter(BindingAdapter adapter) {
        this.adapter = adapter;
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
    public void setItems(List<BindingAdapterItem> items) {
        adapter.setItems(items);
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
    public void clearItems() {
        adapter.clearItems();
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void RefreshRecycleView(List<BindingAdapterItem> items) {

        /*
        Pair<DiffUtil.DiffResult, List<BindingAdapterItem>> initialPair = Pair.create(null, items);

        Observable
                .create((ObservableOnSubscribe<Pair<DiffUtil.DiffResult, List<BindingAdapterItem>>>)
                        emitter -> emitter.onNext(initialPair)
                )
                .subscribeOn(Schedulers.trampoline())
                //cpu密集 排序
                .observeOn(Schedulers.computation())
                //.observeOn(Schedulers.trampoline())
                .map(Pair_Applist -> {
                    List<BindingAdapterItem> items1 = Pair_Applist.second;
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack_ApplistItem(adapter.getItems(), items1), true);
                    return Pair.create(diffResult, items1);
                })
                //Android.mainThread()
                //Schedulers.trampoline()
                //.observeOn(AndroidSchedulers.mainThread())
                //.observeOn(Schedulers.newThread())
                .observeOn(Schedulers.trampoline())
                .subscribe(Pair_Applist -> {
                    DiffUtil.DiffResult diffResult = Pair_Applist.first;
                    diffResult.dispatchUpdatesTo(adapter);
                    adapter.setItems(Pair_Applist.second);
                });*/

    }
}

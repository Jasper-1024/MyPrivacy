package com.jasperhale.myprivacy.Activity.presenter;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;


import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.ViewModel.ViewModel;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;
import com.jasperhale.myprivacy.Activity.model.Model;
import com.jasperhale.myprivacy.Activity.model.mModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;


/**
 * Created by ZHANG on 2017/11/1.
 */

public class mPresenter implements Presenter {
    private static boolean RefreshView = true;
    private static boolean SeaechView = true;
    private final Model model;
    private final ViewModel viewModel;

    public mPresenter(ViewModel viewModel) {
        this.viewModel = viewModel;
        model = new mModel();
    }

    @Override
    public void RefreshView() {
        if (RefreshView) {
            RefreshView = false;
            Observable
                    .create((ObservableOnSubscribe<String>) emitter -> emitter.onNext("")
                    )
                    //等待
                    .subscribeOn(Schedulers.trampoline())
                    //io密集
                    .observeOn(Schedulers.computation())
                    .map(s -> {
                        return model.getPackages();
                    })
                    //cpu密集
                    .observeOn(Schedulers.computation())
                    .map(packages -> {
                        //剔除系统应用?
                        if (!model.ShowSystemApp()) {
                            short i = 0;
                            for (i = 0; i < packages.size(); i++) {
                                if (model.isSystemApp(packages.get(i))) {
                                    packages.remove(i);
                                }
                            }
                            return packages;
                        }
                        return packages;
                    })
                    //cpu密集 创建Appitems
                    .observeOn(Schedulers.computation())
                    .map(packages -> {
                        List<ApplistItem> Appitems = new ArrayList<>();
                        for (PackageInfo pac : packages) {
                            Appitems.add(model.creatApplistItem(pac));
                        }
                        //排序
                        Collections.sort(Appitems);
                        return Appitems;
                    })
                    //cpu密集 排序
                    /*
                    .observeOn(Schedulers.computation())
                    .map(Appitems -> {
                        Collections.sort(Appitems);
                        return Appitems;
                    })*/
                    .map(Appitems -> {
                        List<BindingAdapterItem> items = new ArrayList<>();
                        items.addAll(Appitems);
                        return items;
                    })
                    //等待
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(items -> {
                        viewModel.setItems(items);
                        viewModel.notifyDataSetChanged();
                        //viewModel.RefreshRecycleView(items);
                        return items;
                    })
                    //新线程
                    .observeOn(Schedulers.newThread())
                    .subscribe(s -> {
                        viewModel.setItems_backup(s);
                    });
            RefreshView = true;
        }
    }

    @Override
    public void SeaechView(String query) {
        if (TextUtils.isEmpty(query)) {
            viewModel.setItems(viewModel.getItems_backup());
            viewModel.notifyDataSetChanged();
            //viewModel.RefreshRecycleView(viewModel.getItems_backup());
        } else {
            if (SeaechView) {
                Observable
                        .create((ObservableOnSubscribe<String>)
                                emitter -> emitter.onNext(MyApplicantion.transformPinYin(query))
                        )
                        //等待
                        .subscribeOn(Schedulers.trampoline())
                        //cpu密集 搜索
                        .observeOn(Schedulers.computation())
                        .map(result -> {
                            List<BindingAdapterItem> items = new ArrayList<>();
                            for (BindingAdapterItem item : viewModel.getItems_backup()) {
                                if (((ApplistItem) item).getAppName_compare().contains(result)) {
                                    items.add(item);
                                }
                            }
                            return items;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                            viewModel.setItems(items);
                            viewModel.notifyDataSetChanged();
                        });
            }
        }
    }
}

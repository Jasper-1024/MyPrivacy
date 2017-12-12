package com.jasperhale.myprivacy.Activity.presenter;

import android.content.pm.PackageInfo;
import android.text.TextUtils;


import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.ViewModel.ViewModel;
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


/**
 * Created by ZHANG on 2017/11/1.
 */

public class mPresenter implements Presenter {
    private static boolean RefreshView = true;
    private static boolean SeaechView = true;
    private final Model model;
    private ViewModel viewModel;
    private boolean isRefreshView = true;
    private int position;

    public mPresenter(ViewModel viewModel, int position) {
        this.viewModel = viewModel;
        this.position = position;
        model = new mModel();
    }

    @Override
    public void RefreshView() {
        LogUtil.d("UI", "RefreshView()" + String.valueOf(position));
        if (isRefreshView) {
            isRefreshView = false;
            Observable
                    .create((ObservableOnSubscribe<String>) emitter -> emitter.onNext("")
                    )
                    //等待
                    .subscribeOn(Schedulers.trampoline())
                    //io密集
                    .observeOn(Schedulers.computation())
                    //获取已安装应用
                    .map(s -> model.getPackages())
                    //cpu密集
                    //.observeOn(Schedulers.computation())
                    //剔除对应应用
                    .map(packages -> {
                        short i = 0;
                        List<PackageInfo> items = new ArrayList<>();
                        switch (position) {
                            case 0: {
                                for (PackageInfo packageInfo : packages) {
                                    if (!model.isSystemApp(packageInfo)) {
                                        items.add(packageInfo);
                                    }
                                }
                                break;
                            }
                            case 1: {
                                for (PackageInfo packageInfo : packages) {
                                    if (model.isSystemApp(packageInfo)) {
                                        items.add(packageInfo);
                                    }
                                }
                                break;
                            }
                            case 2: {
                                for (PackageInfo packageInfo : packages) {
                                    if (model.isLimited(packageInfo)) {
                                        items.add(packageInfo);
                                    }
                                }
                                break;
                            }
                            default:
                                break;
                        }
                        return items;
                    })
                    //cpu密集
                    //.observeOn(Schedulers.computation())
                    //创建Appitems
                    .map(packages -> {
                        List<ApplistItem> Appitems = new ArrayList<>();
                        for (PackageInfo pac : packages) {
                            Appitems.add(model.creatApplistItem(pac));
                        }
                        //排序
                        Collections.sort(Appitems);
                        return Appitems;
                    })
                    .map(items -> {
                        //viewModel.setItems(items);
                        viewModel.setItems_backup(items);
                        return items;
                    })
                    //主线程
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(items -> {
                        viewModel.RefreshRecycleView(items);
                        //viewModel.notifyDataSetChanged();
                    });
            isRefreshView = true;
        }
    }

    @Override
    public void SeaechView(String query) {
        LogUtil.d("Search", "SeaechView");
        if (TextUtils.isEmpty(query)) {
            //viewModel.setItems(viewModel.getItems_backup());
            //viewModel.notifyDataSetChanged();
            viewModel.RefreshRecycleView(viewModel.getItems_backup());
        } else {
            if (SeaechView) {
                Observable
                        .create((ObservableOnSubscribe<String>)
                                emitter -> emitter.onNext(MyApplicantion.transformPinYin(query))
                        )
                        .subscribeOn(Schedulers.newThread())
                        //cpu密集 搜索
                        .observeOn(Schedulers.computation())
                        .map(result -> {
                            LogUtil.d("Search", result);
                            List<ApplistItem> items = new ArrayList<>();
                            for (ApplistItem item : viewModel.getItems_backup()) {
                                if ((item).getAppName_compare().contains(result)) {
                                    items.add(item);
                                }
                            }
                            //viewModel.setItems(items);
                            LogUtil.d("Search", "setItems");
                            return items;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                            //viewModel.setItems(items);
                            //viewModel.notifyDataSetChanged();
                            viewModel.RefreshRecycleView(items);
                            LogUtil.d("Search", "notifyDataSetChanged()");
                        });
            }
        }
    }
}

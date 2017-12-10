package com.jasperhale.myprivacy.Activity.presenter;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;


import com.jasperhale.myprivacy.Activity.Base.LogUtil;
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
    private List<ViewModel> viewModels;

    public mPresenter(ViewModel User_viewModel,ViewModel System_viewModel,ViewModel Limted_viewModel) {
        viewModels.add(0,User_viewModel);

        model = new mModel();
    }

    @Override
    public void Refresh_User() {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> emitter.onNext("")
                )
                //等待
                .subscribeOn(Schedulers.trampoline())
                //io密集
                .observeOn(Schedulers.computation())
                //获取已安装应用
                .map(s -> {
                    return model.getPackages();
                })
                //cpu密集
                .observeOn(Schedulers.computation())
                //剔除系统应用
                .map(packages -> {
                    short i = 0;
                    for (i = 0; i < packages.size(); i++) {
                        if (model.isSystemApp(packages.get(i))) {
                            packages.remove(i);
                        }
                    }
                    return packages;
                })
                //cpu密集
                .observeOn(Schedulers.computation())
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
                .map(Appitems -> {
                    List<BindingAdapterItem> items = new ArrayList<>();
                    items.addAll(Appitems);
                    return items;
                })
                //等待
                .observeOn(Schedulers.trampoline())
                .map(items -> {
                    viewModels.get(0).setItems(items);
                    viewModels.get(0).setItems_backup(items);
                    return "";
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    viewModels.get(0).notifyDataSetChanged();
                });
    }

    @Override
    public void Refresh_System() {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> emitter.onNext("")
                )
                //等待
                .subscribeOn(Schedulers.trampoline())
                //io密集
                .observeOn(Schedulers.computation())
                //获取已安装应用
                .map(s -> {
                    return model.getPackages();
                })
                //cpu密集
                .observeOn(Schedulers.computation())
                //剔除用户应用
                .map(packages -> {
                    short i = 0;
                    for (i = 0; i < packages.size(); i++) {
                        if (!model.isSystemApp(packages.get(i))) {
                            packages.remove(i);
                        }
                    }
                    return packages;
                })
                //cpu密集
                .observeOn(Schedulers.computation())
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
                .map(Appitems -> {
                    List<BindingAdapterItem> items = new ArrayList<>();
                    items.addAll(Appitems);
                    return items;
                })
                //等待
                .observeOn(Schedulers.trampoline())
                .map(items -> {
                    viewModels.get(1).setItems(items);
                    viewModels.get(1).setItems_backup(items);
                    return "";
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    viewModels.get(1).notifyDataSetChanged();
                });
    }

    @Override
    public void Refresh_Limted() {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> emitter.onNext("")
                )
                //等待
                .subscribeOn(Schedulers.trampoline())
                //io密集
                .observeOn(Schedulers.computation())
                //获取已安装应用
                .map(s -> {
                    return model.getPackages();
                })
                //cpu密集
                .observeOn(Schedulers.computation())
                //剔除用户应用
                .map(packages -> {
                    short i = 0;
                    for (i = 0; i < packages.size(); i++) {
                        if (!model.isSystemApp(packages.get(i))) {
                            packages.remove(i);
                        }
                    }
                    return packages;
                })
                //cpu密集
                .observeOn(Schedulers.computation())
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
                .map(Appitems -> {
                    List<BindingAdapterItem> items = new ArrayList<>();
                    items.addAll(Appitems);
                    return items;
                })
                //等待
                .observeOn(Schedulers.trampoline())
                .map(items -> {
                    viewModels.get(1).setItems(items);
                    viewModels.get(1).setItems_backup(items);
                    return "";
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    viewModels.get(1).notifyDataSetChanged();
                });

    }


    @Override
    public void RefreshView() {
        this.Refresh_User();
        this.Refresh_System();
        this.Refresh_Limted();
        LogUtil.d("Refresh_Limted",String.valueOf(viewModels.size()));
    }

    @Override
    public void SeaechView(String query, int position) {
        if (TextUtils.isEmpty(query)) {
            viewModels.get(position).setItems(viewModels.get(position).getItems_backup());
            viewModels.get(position).notifyDataSetChanged();
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
                            for (BindingAdapterItem item : viewModels.get(position).getItems_backup()) {
                                if (((ApplistItem) item).getAppName_compare().contains(result)) {
                                    items.add(item);
                                }
                            }
                            viewModels.get(position).setItems(items);
                            return items;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                            //viewModel.setItems(items);
                            viewModels.get(position).notifyDataSetChanged();
                        });
            }
        }
    }
}

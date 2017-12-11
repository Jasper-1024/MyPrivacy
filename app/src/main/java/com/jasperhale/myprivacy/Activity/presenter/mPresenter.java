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
    private List<ViewModel> viewModels = new ArrayList<>(3);

    public mPresenter(ViewModel viewModel) {

        model = new mModel();
    }

    @Override
    public void addViewModel(ViewModel viewModel) {
        viewModels.add(viewModel);
    }

    @Override
    public void addViewModel(ViewModel viewModel, int position) {
        viewModels.add(position,viewModel);
    }

    @Override
    public void Refresh_User() {
        LogUtil.d("UI","Refresh_User()");
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
                .map(items -> {
                    //viewModels.get(0).setItems(items);
                    viewModels.get(0).setItems_backup();
                    return items;
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    viewModels.get(0).RefreshRecycleView(items);
                    //viewModels.get(0).notifyDataSetChanged();
                });
    }

    @Override
    public void Refresh_System() {
        LogUtil.d("UI","Refresh_System");
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
                    viewModels.get(1).setItems(items);
                    viewModels.get(1).setItems_backup();
                    return items;
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    //viewModels.get(1).RefreshRecycleView(items);
                    viewModels.get(1).notifyDataSetChanged();
                });
    }

    @Override
    public void Refresh_Limted() {
        LogUtil.d("UI","Refresh_Limted");
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
                //剔除未限制应用
                .map(packages -> {
                    short i = 0;
                    List<PackageInfo> packageInfos = new ArrayList<>();
                    for (PackageInfo packageInfo:packages) {
                        if (model.isLimited(packages.get(i))) {
                            packageInfos.add(packageInfo);
                        }
                    }
                    return packageInfos;
                })
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
                    viewModels.get(2).setItems(items);
                    viewModels.get(2).setItems_backup();
                    return items;
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    //viewModels.get(2).RefreshRecycleView(items);
                    viewModels.get(2).notifyDataSetChanged();
                });

    }


    @Override
    public void RefreshView() {
        this.Refresh_User();
        this.Refresh_System();
        this.Refresh_Limted();
    }

    @Override
    public void SeaechView(String query, int position) {
        LogUtil.d("Search","SeaechView");
        if (TextUtils.isEmpty(query)) {
            viewModels.get(position).setItems(viewModels.get(position).getItems_backup());
            viewModels.get(position).notifyDataSetChanged();
            //viewModels.get(position).RefreshRecycleView(viewModels.get(position).getItems_backup());
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
                            LogUtil.d("Search",result);
                            List<ApplistItem> items = new ArrayList<>();
                            for (ApplistItem item : viewModels.get(position).getItems_backup()) {
                                if ((item).getAppName_compare().contains(result)) {
                                    items.add(item);
                                }
                            }
                            //viewModels.get(position).setItems(items);
                            LogUtil.d("Search","setItems");
                            return items;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                            viewModels.get(position).setItems(items);
                            viewModels.get(position).notifyDataSetChanged();
                            //viewModels.get(position).RefreshRecycleView(items);
                            LogUtil.d("Search","notifyDataSetChanged()");
                        });
            }
        }
    }
}

package com.jasperhale.myprivacy.Activity.presenter;


import android.util.Log;

import com.jasperhale.myprivacy.Activity.ViewModel.ViewModelApp;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.AppSetting_appinstall;
import com.jasperhale.myprivacy.Activity.item.AppSetting_cell;
import com.jasperhale.myprivacy.Activity.item.AppSetting_wifi;
import com.jasperhale.myprivacy.Activity.model.ModelApp;
import com.jasperhale.myprivacy.Activity.model.mModelApp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ZHANG on 2017/11/8.
 */

public class mPresenterApp implements PresenterApp {
    private final ViewModelApp viewModelApp;
    private final ModelApp modelApp;

    public mPresenterApp(ViewModelApp viewModelApp) {
        this.viewModelApp = viewModelApp;
        this.modelApp = new mModelApp();
    }

    @Override
    public void loadAppSetting() {
        Observable
                .create((ObservableOnSubscribe<List<BindingAdapterItem>>) emitter -> emitter.onNext(new ArrayList<>())
                )
                //新线程
                //.subscribeOn(Schedulers.newThread())
                //io密集
                .observeOn(Schedulers.io())
                .map(items -> {
                    items.clear();
                    items.add(modelApp.getAppSetting_appinstall(viewModelApp.getPackageName()));
                    items.add(modelApp.getAppSetting_wifi(viewModelApp.getPackageName()));
                    items.add(modelApp.getAppSetting_cell(viewModelApp.getPackageName()));
                    viewModelApp.setItems(items);
                    return items;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    //viewModelApp.setItems(items);
                    viewModelApp.notifyDataSetChanged();
                });
    }

    @Override
    public void saveAppSetting() {
        Observable
                .create((ObservableOnSubscribe<List<BindingAdapterItem>>) emitter -> emitter.onNext(viewModelApp.getItems())
                )
                //新线程
                .subscribeOn(Schedulers.newThread())
                //io密集
                .observeOn(Schedulers.io())
                .subscribe(items -> {
                    modelApp.setAppSetting_appinstall(viewModelApp.getPackageName(),(AppSetting_appinstall) items.get(0));
                    modelApp.setAppSetting_wifi(viewModelApp.getPackageName(),(AppSetting_wifi) items.get(1));
                    modelApp.setAppSetting_cell(viewModelApp.getPackageName(),(AppSetting_cell)items.get(2));
                });

        Observable
                .create((ObservableOnSubscribe<Boolean>) emitter -> emitter.onNext(modelApp.getApp(viewModelApp.getPackageName()))
                )
                //新线程
                .subscribeOn(Schedulers.newThread())
                .subscribe(value -> {
                    modelApp.setApp(viewModelApp.getPackageName(),value);
                });
    }
}

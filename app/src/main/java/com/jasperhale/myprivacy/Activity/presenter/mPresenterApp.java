package com.jasperhale.myprivacy.Activity.presenter;


import com.jasperhale.myprivacy.Activity.ViewModel.ViewModelApp;
import com.jasperhale.myprivacy.Activity.item.AppSetting;
import com.jasperhale.myprivacy.Activity.model.ModelApp;
import com.jasperhale.myprivacy.Activity.model.mModelApp;

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
                .create((ObservableOnSubscribe<AppSetting>) emitter -> emitter.onNext(viewModelApp.getAppSetting())
                )
                //io密集
                .observeOn(Schedulers.io())
                .map(appSetting -> {
                    modelApp.getAppSetting(viewModelApp.getPackageName(), appSetting);
                    return appSetting;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewModelApp::setAppSetting);
    }

    @Override
    public void saveAppSetting() {
        Observable
                .create((ObservableOnSubscribe<AppSetting>) emitter -> emitter.onNext(viewModelApp.getAppSetting())
                )
                //新线程
                .subscribeOn(Schedulers.newThread())
                //io密集
                .observeOn(Schedulers.io())
                .subscribe(appSetting -> modelApp.setAppSetting(viewModelApp.getPackageName(), appSetting));
    }
}

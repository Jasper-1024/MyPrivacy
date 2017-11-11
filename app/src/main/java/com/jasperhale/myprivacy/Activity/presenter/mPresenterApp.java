package com.jasperhale.myprivacy.Activity.presenter;


import com.jasperhale.myprivacy.Activity.ViewModel.ViewModelApp;

import com.jasperhale.myprivacy.Activity.model.ModelApp;

import com.jasperhale.myprivacy.Activity.model.mModelApp;


/**
 * Created by ZHANG on 2017/11/8.
 */

public class mPresenterApp implements PresenterApp {
    ViewModelApp viewModelApp;
    ModelApp modelApp;

    public mPresenterApp(ViewModelApp viewModelApp) {
        this.viewModelApp = viewModelApp;
        this.modelApp = new mModelApp();
    }

    @Override
    public void initAppSetting() {
        modelApp.getAppSetting(viewModelApp.getPackageName(), viewModelApp.getAppSetting());
    }
}

package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.model.AppSetting;

/**
 * Created by ZHANG on 2017/11/8.
 */

public class mViewModeApp implements ViewModelApp{
    private AppSetting appSetting;
    private final String PackageName;
    /*public void mViewModeApp(AppSetting appSetting){
        this.appSetting = appSetting;
    }*/
    public mViewModeApp(String packageName){
        this.PackageName = packageName;
        appSetting = new AppSetting();
    }
    @Override
    public AppSetting getAppSetting(){
        return appSetting;
    }
    @Override
    public void setAppSetting(AppSetting appSetting){
        this.appSetting = appSetting;
    }
    @Override
    public String getPackageName(){
        return PackageName;
    }
}

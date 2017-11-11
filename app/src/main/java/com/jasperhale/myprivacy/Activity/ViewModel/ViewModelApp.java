package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.model.AppSetting;

/**
 * Created by ZHANG on 2017/11/8.
 */

public interface ViewModelApp {
    public AppSetting getAppSetting();
    public void setAppSetting(AppSetting appSetting);
    public String getPackageName();
}

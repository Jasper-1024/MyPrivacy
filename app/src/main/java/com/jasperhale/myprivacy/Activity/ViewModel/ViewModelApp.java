package com.jasperhale.myprivacy.Activity.ViewModel;

import com.jasperhale.myprivacy.Activity.item.AppSetting;

/**
 * Created by ZHANG on 2017/11/8.
 */

public interface ViewModelApp {
    AppSetting getAppSetting();
    void setAppSetting(AppSetting appSetting);
    String getPackageName();
}

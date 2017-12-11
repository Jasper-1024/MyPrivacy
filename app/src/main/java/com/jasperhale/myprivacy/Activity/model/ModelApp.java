package com.jasperhale.myprivacy.Activity.model;

import com.jasperhale.myprivacy.Activity.item.AppSetting_appinstall;
import com.jasperhale.myprivacy.Activity.item.AppSetting_cell;
import com.jasperhale.myprivacy.Activity.item.AppSetting_wifi;

/**
 * Created by ZHANG on 2017/11/11.
 */

public interface ModelApp {

    AppSetting_appinstall getAppSetting_appinstall(String packageName);
    AppSetting_wifi getAppSetting_wifi(String packageName);
    AppSetting_cell getAppSetting_cell(String packageName);

    void setAppSetting_appinstall(String packageName,AppSetting_appinstall appSetting_appinstall);
    void setAppSetting_wifi(String packageName, AppSetting_wifi appSetting_wifi);
    void setAppSetting_cell(String packageName, AppSetting_cell appSetting_cell);

    boolean getApp(String AppId);
    void setApp(String AppId,boolean value);
}

package com.jasperhale.myprivacy.Activity.model;

/**
 * Created by ZHANG on 2017/11/11.
 */

public interface ModelApp {
    //读取AppSetting
    public void getAppSetting(String packageName,AppSetting appSetting);

    //设置AppSetting
    public void setAppSetting(String packageName,AppSetting appSetting);
}

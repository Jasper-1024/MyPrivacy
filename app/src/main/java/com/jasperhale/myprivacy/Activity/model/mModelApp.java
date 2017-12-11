package com.jasperhale.myprivacy.Activity.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.item.AppSetting_appinstall;
import com.jasperhale.myprivacy.Activity.item.AppSetting_cell;
import com.jasperhale.myprivacy.Activity.item.AppSetting_wifi;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ZHANG on 2017/11/11.
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class mModelApp implements ModelApp {
    //键值
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public mModelApp() {
        //preferences = MyApplicantion.getContext().getSharedPreferences(MyApplicantion.getContext().getPackageName() + "_preferences", Activity.MODE_WORLD_READABLE);
        preferences = MyApplicantion.getContext().getSharedPreferences("AppSetting", Context.MODE_WORLD_READABLE);
        //preferences = getPreferences(MyApplicantion.getContext(), "AppSetting");
        //preferences = MyApplicantion.getContext().getSharedPreferences("AppSetting", MODE_WORLD_READABLE);
        editor = preferences.edit();
    }

    @Override
    public AppSetting_appinstall getAppSetting_appinstall(String packageName) {
        AppSetting_appinstall appSetting_appinstall = new AppSetting_appinstall();
        appSetting_appinstall.setInstalledApp(getSharedPreferences(packageName + "/InstalledApp"));
        appSetting_appinstall.setRunningApp(getSharedPreferences(packageName + "/RunningApp"));
        return appSetting_appinstall;
    }

    @Override
    public void setAppSetting_appinstall(String packageName, AppSetting_appinstall appSetting_appinstall) {
        setSharedPreferences(packageName + "/InstalledApp", appSetting_appinstall.getInstalledApp());
        setSharedPreferences(packageName + "/RunningApp", appSetting_appinstall.getRunningApp());
    }

    @Override
    public AppSetting_wifi getAppSetting_wifi(String packageName) {
        AppSetting_wifi appSetting_wifi = new AppSetting_wifi();
        appSetting_wifi.setWifiScan(getSharedPreferences(packageName + "/WifiScan", false));
        appSetting_wifi.setConnectionWifi(getSharedPreferences(packageName + "/ConnectionWifi", false));
        appSetting_wifi.setSSID(getSharedPreferences(packageName + "/SSID", "1900"));
        appSetting_wifi.setMac(getSharedPreferences(packageName + "/Mac", "A5:A2:6F:35:D0:CF"));
        appSetting_wifi.setNetworkId(getSharedPreferences(packageName + "/NetworkId", -1));
        return appSetting_wifi;
    }

    @Override
    public void setAppSetting_wifi(String packageName, AppSetting_wifi appSetting_wifi) {
        setSharedPreferences(packageName + "/WifiScan", appSetting_wifi.getWifiScan());
        setSharedPreferences(packageName + "/ConnectionWifi", appSetting_wifi.getConnectionWifi());
        setSharedPreferences(packageName + "/SSID", appSetting_wifi.getSSID());
        setSharedPreferences(packageName + "/Mac", appSetting_wifi.getMac());
        setSharedPreferences(packageName + "/NetworkId", appSetting_wifi.getNetworkId());
    }

    @Override
    public AppSetting_cell getAppSetting_cell(String packageName) {
        AppSetting_cell appSetting_cell = new AppSetting_cell();
        appSetting_cell.setCellInfo(getSharedPreferences(packageName + "/CellInfo"));
        appSetting_cell.setMcc(getSharedPreferences(packageName + "/Mcc", 460));
        appSetting_cell.setMnc(getSharedPreferences(packageName + "/Mnc", 1));
        appSetting_cell.setCid(getSharedPreferences(packageName + "/Cid", 41070));
        appSetting_cell.setLac(getSharedPreferences(packageName + "/Lac", 8309067));
        return appSetting_cell;
    }

    @Override
    public void setAppSetting_cell(String packageName, AppSetting_cell appSetting_cell) {
        setSharedPreferences(packageName + "/CellInfo", appSetting_cell.getCellInfo());
        setSharedPreferences(packageName + "/Mcc", appSetting_cell.getMcc());
        setSharedPreferences(packageName + "/Mnc", appSetting_cell.getMnc());
        setSharedPreferences(packageName + "/Cid", appSetting_cell.getCid());
        setSharedPreferences(packageName + "/Lac", appSetting_cell.getLac());
    }

    @Override
    public boolean getApp(String packageName) {
        return getSharedPreferences(packageName+"/InstalledApp")||
                getSharedPreferences(packageName+"/RunningApp")||
                getSharedPreferences(packageName+"/WifiScan")||
                getSharedPreferences(packageName+"/ConnectionWifi")||
                getSharedPreferences(packageName+"/CellInfo");
    }

    @Override
    public void setApp(String packageName,boolean value) {
        setSharedPreferences(packageName , value);
    }

    //设置普通键值
    private void setSharedPreferences(String key, boolean value) {
        editor.putBoolean(key, value).commit();
        MakeFileReadable(MyApplicantion.getContext(),"AppSetting");
    }

    private void setSharedPreferences(String key, String value) {
        editor.putString(key, value).commit();
        MakeFileReadable(MyApplicantion.getContext(),"AppSetting");
    }

    private void setSharedPreferences(String key, int value) {
        editor.putInt(key, value).commit();
        MakeFileReadable(MyApplicantion.getContext(),"AppSetting");
    }

    //获取普通键值
    private boolean getSharedPreferences(String key) {
        return preferences.getBoolean(key, false);
    }

    private boolean getSharedPreferences(String key, boolean bool) {
        return preferences.getBoolean(key, bool);
    }

    private String getSharedPreferences(String key, String string) {
        return preferences.getString(key, string);
    }

    private int getSharedPreferences(String key, int i) {
        return preferences.getInt(key, i);
    }

    private static SharedPreferences getPreferences(Context ctx, String prefName) {
        return ctx.getSharedPreferences(prefName, MODE_PRIVATE);
    }

    private static void MakeFileReadable(Context ctx, String prefName){
  /*
        File prefsDir = new File(ctx.getApplicationInfo().dataDir, "shared_prefs");
        File prefsFile = new File(prefsDir, prefName + ".xml");
        if (prefsFile.exists()) {
            prefsFile.setReadable(true, false);
            LogUtil.d("mModelApp", "FileReadable");
        }
 */
    }
}

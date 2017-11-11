package com.jasperhale.myprivacy.Activity.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ZHANG on 2017/11/11.
 */

public class mModelApp implements ModelApp {
    //键值
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public mModelApp(){
        preferences = getPreferencesAndKeepItReadable(MyApplicantion.getContext(), "AppSetting");
        //preferences = MyApplicantion.getContext().getSharedPreferences("AppSetting", MODE_WORLD_READABLE);
        editor = preferences.edit();
    }
    @Override
    public void getAppSetting(String packageName, AppSetting appSetting) {
        appSetting.setInstalledApp(getSharedPreferences("InstalledApp"));
        appSetting.setRunningApp(getSharedPreferences("RunningApp"));

        appSetting.setConnectionWifi(getSharedPreferences("ConnectionWifi"));
        appSetting.setSSID(getSharedPreferences("SSID", "1900"));
        appSetting.setMac(getSharedPreferences("Mac", "A5:A2:6F:35:D0:CF"));
        appSetting.setNetworkId(getSharedPreferences("NetworkId", -1));

        appSetting.setCellInfo(getSharedPreferences("CellInfo"));

    }

    @Override
    public void setAppSetting(String packageName, AppSetting appSetting) {
        setSharedPreferences(packageName + "InstalledApp", appSetting.getInstalledApp());
        setSharedPreferences(packageName + "RunningApp", appSetting.getRunningApp());

        setSharedPreferences(packageName + "ConnectionWifi", appSetting.getConnectionWifi());
        setSharedPreferences(packageName + "SSID", appSetting.getSSID());
        setSharedPreferences(packageName + "Mac", appSetting.getMac());
        setSharedPreferences(packageName + "NetworkId", appSetting.getNetworkId());

        setSharedPreferences(packageName + "CellInfo", appSetting.getCellInfo());
    }

    //获取设置键值
    private boolean getPreferences(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplicantion.getContext());
        return prefs.getBoolean(key, false);
    }

    //设置普通键值
    private void setSharedPreferences(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    private void setSharedPreferences(String key, String value) {
        editor.putString(key, value).commit();
    }

    private void setSharedPreferences(String key, int value) {
        editor.putInt(key, value).commit();
    }

    //获取普通键值
    private boolean getSharedPreferences(String key) {
        return preferences.getBoolean(key, false);
    }
    //获取普通键值
    private boolean getSharedPreferences(String key,boolean bool) {
        return preferences.getBoolean(key, bool);
    }

    private String getSharedPreferences(String key, String string) {
        return preferences.getString(key, string);
    }

    private int getSharedPreferences(String key, int i) {
        return preferences.getInt(key, i);
    }

    public static SharedPreferences getPreferencesAndKeepItReadable(Context ctx, String prefName) {
        SharedPreferences prefs = ctx.getSharedPreferences(prefName, MODE_PRIVATE);
        File prefsFile = new File(ctx.getFilesDir() + "/../shared_prefs/" + prefName + ".xml");
        prefsFile.setReadable(true, false);
        return prefs;
    }
}

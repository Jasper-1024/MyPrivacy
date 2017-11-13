package com.jasperhale.myprivacy.Activity.model;

import android.content.Context;
import android.content.SharedPreferences;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import java.io.File;

import de.robv.android.xposed.XposedBridge;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ZHANG on 2017/11/11.
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class mModelApp implements ModelApp {
    //键值
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public mModelApp(){
        preferences = MyApplicantion.getContext().getSharedPreferences("AppSetting", Context.MODE_WORLD_READABLE);
        //preferences = getPreferencesAndKeepItReadable(MyApplicantion.getContext(), "AppSetting");
        //preferences = MyApplicantion.getContext().getSharedPreferences("AppSetting", MODE_WORLD_READABLE);
        editor = preferences.edit();
    }
    @Override
    public void getAppSetting(String packageName, AppSetting appSetting) {
        appSetting.setInstalledApp(getSharedPreferences(packageName+"InstalledApp"));
        appSetting.setRunningApp(getSharedPreferences(packageName+"RunningApp"));

        appSetting.setConnectionWifi(getSharedPreferences(packageName+"ConnectionWifi",false));
        appSetting.setSSID(getSharedPreferences(packageName+"SSID", "1900"));
        appSetting.setMac(getSharedPreferences(packageName+"Mac", "A5:A2:6F:35:D0:CF"));
        appSetting.setNetworkId(getSharedPreferences(packageName+"NetworkId", -1));

        appSetting.setCellInfo(getSharedPreferences(packageName+"CellInfo"));

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

    private static SharedPreferences getPreferencesAndKeepItReadable(Context ctx, String prefName) {
        SharedPreferences prefs = ctx.getSharedPreferences(prefName, MODE_PRIVATE);
        File prefsFile = new File(ctx.getFilesDir() + "/../shared_prefs/" + prefName + ".xml");
        prefsFile.setReadable(true, false);
        return prefs;
    }
}

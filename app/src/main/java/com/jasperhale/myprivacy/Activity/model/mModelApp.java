package com.jasperhale.myprivacy.Activity.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.item.AppSetting;

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
    public void getAppSetting(String packageName, AppSetting appSetting) {
        appSetting.setInstalledApp(getSharedPreferences(packageName + "/InstalledApp"));
        appSetting.setRunningApp(getSharedPreferences(packageName + "/RunningApp"));

        appSetting.setConnectionWifi(getSharedPreferences(packageName + "/ConnectionWifi", false));
        appSetting.setSSID(getSharedPreferences(packageName + "/SSID", "1900"));
        appSetting.setMac(getSharedPreferences(packageName + "/Mac", "A5:A2:6F:35:D0:CF"));
        appSetting.setNetworkId(getSharedPreferences(packageName + "/NetworkId", -1));

        appSetting.setCellInfo(getSharedPreferences(packageName + "/CellInfo"));
        appSetting.setMcc(getSharedPreferences(packageName + "/Mcc", 460));
        appSetting.setMnc(getSharedPreferences(packageName + "/Mnc", 1));
        appSetting.setCid(getSharedPreferences(packageName + "/Cid", 41070));
        appSetting.setLac(getSharedPreferences(packageName + "/Lac", 8309067));

    }

    @Override
    public void setAppSetting(String packageName, AppSetting appSetting) {
        setSharedPreferences(packageName + "/InstalledApp", appSetting.getInstalledApp());
        setSharedPreferences(packageName + "/RunningApp", appSetting.getRunningApp());

        setSharedPreferences(packageName + "/ConnectionWifi", appSetting.getConnectionWifi());
        setSharedPreferences(packageName + "/SSID", appSetting.getSSID());
        setSharedPreferences(packageName + "/Mac", appSetting.getMac());
        setSharedPreferences(packageName + "/NetworkId", appSetting.getNetworkId());

        setSharedPreferences(packageName + "/CellInfo", appSetting.getCellInfo());
        setSharedPreferences(packageName + "/Mcc", appSetting.getMcc());
        setSharedPreferences(packageName + "/Mnc", appSetting.getMnc());
        setSharedPreferences(packageName + "/Cid", appSetting.getCid());
        setSharedPreferences(packageName + "/Lac", appSetting.getLac());


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

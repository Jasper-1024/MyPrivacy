package com.jasperhale.myprivacy.Activity.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.preference.PreferenceManager;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.List;



/**
 * Created by ZHANG on 2017/10/31.
 */

public class mModel implements Model {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public mModel(){
        preferences = MyApplicantion.getContext().getSharedPreferences("AppSetting", Context.MODE_WORLD_READABLE);
        editor = preferences.edit();
    }

    @Override
    public List<PackageInfo> getPackages() {
        return MyApplicantion.getContext().getPackageManager().getInstalledPackages(0);
    }

    @Override
    public ApplistItem creatApplistItem(PackageInfo packageInfo) {
        ApplicationInfo appInfo = packageInfo.applicationInfo;
        return new ApplistItem(appInfo.packageName, appInfo.loadLabel(MyApplicantion.getContext().getPackageManager()).toString(),
                appInfo.loadIcon(MyApplicantion.getContext().getPackageManager()));
    }

    @Override
    public boolean isLimited(PackageInfo pac) {
        return getSharedPreferences(pac.packageName,false);
    }

    @Override
    public boolean isSystemApp(PackageInfo pac) {
        return (pac.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0;
    }

    //获取设置键值
    private boolean getPreferences(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplicantion.getContext());
        return prefs.getBoolean(key, false);
    }
    //获取普通键值
    private boolean getSharedPreferences(String key, boolean bool) {
        return preferences.getBoolean(key, bool);
    }
}

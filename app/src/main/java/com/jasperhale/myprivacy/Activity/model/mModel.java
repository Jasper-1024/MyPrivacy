package com.jasperhale.myprivacy.Activity.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.preference.PreferenceManager;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by ZHANG on 2017/10/31.
 */

public class mModel implements Model {


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
    public boolean ShowSystemApp() {
        if (getPreferences("ShowSystemApp")) {
            LogUtil.d("mModel", "233");
        }
        return getPreferences("ShowSystemApp");
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
}

package com.jasperhale.myprivacy.Activity.model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public class mModel implements Model {
    @Override
    public List<PackageInfo> getPackages() {
        return MyApplicantion.getContext().getPackageManager().getInstalledPackages(0);
    }

    @Override
    public BindingAdapterItem creatApplistItem(PackageInfo packageInfo) {
        ApplicationInfo appInfo = packageInfo.applicationInfo;
        return new ApplistItem(appInfo.packageName,
                MyApplicantion.getContext().getPackageManager().getApplicationIcon(appInfo));
    }
}

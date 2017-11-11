package com.jasperhale.myprivacy.Activity.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;
import com.jasperhale.myprivacy.R;

import java.io.File;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.MODE_WORLD_READABLE;

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
                appInfo.loadIcon(MyApplicantion.getContext().getPackageManager()));
    }


}

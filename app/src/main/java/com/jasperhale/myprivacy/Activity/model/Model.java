package com.jasperhale.myprivacy.Activity.model;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface Model {
    //获取Packages
    public List<PackageInfo> getPackages();

    //转换创建ApplistItem
    public BindingAdapterItem creatApplistItem(PackageInfo packageInfo);

}

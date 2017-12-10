package com.jasperhale.myprivacy.Activity.model;

import android.content.pm.PackageInfo;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface Model {
    //获取Packages
    List<PackageInfo> getPackages();

    //转换创建ApplistItem
    ApplistItem creatApplistItem(PackageInfo packageInfo);

    //是否为系统应用
    boolean isSystemApp(PackageInfo pac);

    //时候已限制权限
    boolean isLimited(PackageInfo pac);

}

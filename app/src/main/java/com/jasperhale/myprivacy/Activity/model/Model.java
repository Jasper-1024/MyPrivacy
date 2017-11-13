package com.jasperhale.myprivacy.Activity.model;

import android.content.pm.PackageInfo;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface Model {
    //获取Packages
    List<PackageInfo> getPackages();

    //转换创建ApplistItem
    BindingAdapterItem creatApplistItem(PackageInfo packageInfo);

    //是否显示系统应用
    boolean ShowSystemApp();

    //是否为系统应用
    boolean isSystemApp(PackageInfo pac);

}

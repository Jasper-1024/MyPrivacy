package com.jasperhale.myprivacy.Activity.Base;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by ZHANG on 2017/11/16.
 */

public class BaseXposedClass {
    XC_LoadPackage.LoadPackageParam lpparam;
    XSharedPreferences prefs;
    public BaseXposedClass(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs){
        this.lpparam = lpparam;
        this.prefs = prefs;
    }
}

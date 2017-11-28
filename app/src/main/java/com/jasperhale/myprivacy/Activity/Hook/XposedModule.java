package com.jasperhale.myprivacy.Activity.Hook;


import com.jasperhale.myprivacy.Activity.Base.Xposed_user;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;




/**
 * Created by ZHANG on 2017/11/13.
 */

public class XposedModule implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    private static XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        prefs = new XSharedPreferences(Xposed_user.PACKAGE_NAME, "AppSetting");
        prefs.makeWorldReadable();
        XposedBridge.log("MyPrivacy" + "preferences init");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (prefs == null) {
            prefs = new XSharedPreferences(Xposed_user.PACKAGE_NAME, "AppSetting");
            prefs.makeWorldReadable();
            XposedBridge.log("MyPrivacy" + "preferences init");
        } else {
            prefs.reload();
        }

        //XposedBridge.log("MyPrivacy" + lpparam.packageName);
        InstalledApp.getInstalledApp(lpparam,prefs).handle();

        //XposedBridge.log("MyPrivacy hook " + "/RunningApp");
        RunningApp.getRunningApp(lpparam,prefs).handle();

        //XposedBridge.log("MyPrivacy hook " + "/ConnectionWifi");
        ConnectionWifi.getConnectionWifi(lpparam,prefs).handle();

        //XposedBridge.log("MyPrivacy hook " + "/CellInfo");
        CellInfo.getCellInfo(lpparam,prefs).handle();

    }

}

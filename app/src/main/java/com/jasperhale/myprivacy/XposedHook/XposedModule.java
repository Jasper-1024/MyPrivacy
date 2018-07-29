package com.jasperhale.myprivacy.XposedHook;

import com.jasperhale.myprivacy.XposedHook.Hook.ActivityManager;
import com.jasperhale.myprivacy.XposedHook.Hook.AppWidgetManager;
import com.jasperhale.myprivacy.XposedHook.Hook.Build;
import com.jasperhale.myprivacy.XposedHook.Hook.Location;
import com.jasperhale.myprivacy.XposedHook.Hook.LocationManager;
import com.jasperhale.myprivacy.XposedHook.Hook.NetworkInfo;
import com.jasperhale.myprivacy.XposedHook.Hook.NetworkInterface;
import com.jasperhale.myprivacy.XposedHook.Hook.PackageManager;
import com.jasperhale.myprivacy.XposedHook.Hook.SensorManager;
import com.jasperhale.myprivacy.XposedHook.Hook.TelephonyManager;
import com.jasperhale.myprivacy.XposedHook.Hook.WifiInfo;
import com.jasperhale.myprivacy.XposedHook.Hook.WifiManager;
import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedModule implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    private final String TAG = "Xposed.MyPrivacy ";

    @Override
    public void initZygote(StartupParam startupParam) {
        mXpModel.prefs_init();
        XposedBridge.log(TAG + "initZygote");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {

        XposedBridge.log(TAG + lpparam.packageName + " handleLoadPackage");
        //if (mXpModel.getAppLimted(lpparam.packageName)) {
            ActivityManager.get(lpparam).handle();
            AppWidgetManager.get(lpparam).handle();
            Build.get(lpparam).handle();
            Location.get(lpparam).handle();
            LocationManager.get(lpparam).handle();
            NetworkInfo.get(lpparam).handle();
            NetworkInterface.get(lpparam).handle();
            PackageManager.get(lpparam).handle();
            //SensorManager.get(lpparam).handle();
            TelephonyManager.get(lpparam).handle();
            WifiInfo.get(lpparam).handle();
            WifiManager.get(lpparam).handle();
        //}
    }

}

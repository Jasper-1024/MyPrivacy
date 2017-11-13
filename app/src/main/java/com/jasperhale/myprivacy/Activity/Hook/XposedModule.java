package com.jasperhale.myprivacy.Activity.Hook;


import android.content.Intent;

import com.jasperhale.myprivacy.Activity.Xposed_user;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * Created by ZHANG on 2017/11/13.
 */

public class XposedModule implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    private static XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        prefs = new XSharedPreferences(Xposed_user.PACKAGE_NAME,"AppSetting");
        //prefs = new XSharedPreferences("AppSetting");
        prefs.makeWorldReadable();
        XposedBridge.log("Xposed"+"preferences init");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        prefs.reload();

        XposedBridge.log("Xposed"+lpparam.packageName );

        if (prefs.getBoolean(lpparam.packageName + "InstalledApp", false)) {

            XposedBridge.log("Xposed"+lpparam.packageName + "InstalledApp");

            Class contextWrapperClass = XposedHelpers.findClass("android.content.pm.PackageManager", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(contextWrapperClass, "getInstalledApplications", Intent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (prefs.getBoolean(lpparam.packageName + "InstalledApp", false)) {
                        param.setResult(null);
                        return;
                    }
                }
            });
            XposedHelpers.findAndHookMethod(contextWrapperClass, "getInstalledPackages", Intent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (prefs.getBoolean(lpparam.packageName + "InstalledApp", false)) {
                        param.setResult(null);
                        return;
                    }
                }
            });
            XposedHelpers.findAndHookMethod(contextWrapperClass, "getInstallerPackageName", Intent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (prefs.getBoolean(lpparam.packageName + "InstalledApp", false)) {
                        param.setResult(null);
                        return;
                    }
                }
            });
        }


    }

}

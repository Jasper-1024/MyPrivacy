package com.jasperhale.myprivacy.Activity.Hook;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.jasperhale.myprivacy.Activity.Base.BaseXposedClass;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by ZHANG on 2017/11/16.
 */

public class InstalledApp {

    private static XC_LoadPackage.LoadPackageParam lpparam;
    private static XSharedPreferences prefs;


    public static InstalledApp getInstalledApp(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        InstalledApp.lpparam = lpparam;
        InstalledApp.prefs = prefs;
        return InstalledAppHolder.installedApp;
    }

    private static class InstalledAppHolder {
        private static final InstalledApp installedApp = new InstalledApp();
    }

    public void handle() {
        if (prefs.getBoolean(lpparam.packageName + "/InstalledApp", false)) {

            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp");


            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledApplications", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ApplicationInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp");
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);

                }
            });
/*
            Class<?> PackageMager = XposedHelpers.findClass("android.app.ApplicationPackageManager", lpparam.classLoader);

            XposedBridge.hookAllMethods(PackageMager, "getApplicationInfo", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    modifyHookedMethodArguments(param);
                }
            });


            XposedBridge.hookAllMethods(PackageMager, "getPackageInfo", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    logDebug("getPackageInfo");
                    modifyHookedMethodArguments(param);
                }
            });

            XposedBridge.hookAllMethods(PackageMager, "getInstalledApplications", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    logDebug("getInstalledApplications");
                    modifyHookedMethodResult(param, new ApplicationInfoData());
                }
            });

            XposedBridge.hookAllMethods(PackageMager, "getInstalledPackages", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    logDebug("getInstalledPackages");
                    modifyHookedMethodResult(param, new PackageInfoData());
                }
            });

            XposedBridge.hookAllMethods(PackageMager, "getPackagesForUid", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    logDebug("getPackagesForUid");
                    modifyHookedMethodResult(param, new PackageNameStringData());
                }
            });

            XposedBridge.hookAllMethods(PackageMager, "queryIntentActivities", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    logDebug("queryIntentActivities");
                    modifyHookedMethodResult(param, new ResolveInfoData());
                }
            });

            XposedBridge.hookAllMethods(PackageMager, "queryIntentActivityOptions", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    logDebug("queryIntentActivityOptions");
                    modifyHookedMethodResult(param, new ResolveInfoData());

                }
            });
*/

        }
    }
}

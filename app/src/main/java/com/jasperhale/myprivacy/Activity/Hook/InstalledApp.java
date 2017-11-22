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
            hook_getInstalledApplications();
            hook_getInstalledPackages();
        }
    }

    private static void hook_getInstalledApplications() {
        findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledApplications", int.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                List<ApplicationInfo> xp = new ArrayList<>();
                xp.clear();
                param.setResult(xp);
            }
        });
    }

    private static void hook_getInstalledPackages() {
        findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledPackages", int.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                List<PackageInfo> xp = new ArrayList<>();
                xp.clear();
                param.setResult(xp);

            }
        });
    }
}

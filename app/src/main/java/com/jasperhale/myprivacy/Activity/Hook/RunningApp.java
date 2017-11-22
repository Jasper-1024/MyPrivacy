package com.jasperhale.myprivacy.Activity.Hook;

import android.app.ActivityManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by ZHANG on 2017/11/20.
 */

public class RunningApp {
    private static XC_LoadPackage.LoadPackageParam lpparam;
    private static XSharedPreferences prefs;

    public static RunningApp getRunningApp(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        RunningApp.lpparam = lpparam;
        RunningApp.prefs = prefs;
        return RunningAppHolder.runningApp;
    }

    private static class RunningAppHolder {
        private static final RunningApp runningApp = new RunningApp();
    }

    public void handle() {
        if (prefs.getBoolean(lpparam.packageName + "/RunningApp", false)) {

            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/RunningApp");
            hook_getRunningServices();

        }
    }

    private static void hook_getRunningServices() {
        findAndHookMethod("android.app.ActivityManager", lpparam.classLoader, "getRunningServices", int.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                List<ActivityManager.RunningServiceInfo> processes = new ArrayList<>();
                processes.clear();
                param.setResult(processes);
            }
        });
    }
}

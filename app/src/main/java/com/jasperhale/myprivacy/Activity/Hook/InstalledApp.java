package com.jasperhale.myprivacy.Activity.Hook;

import android.app.ActivityManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;


import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
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

    //获取已安装应用列表
    private static void hook_getInstalledApplications() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getInstalledApplications");
        try {
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledApplications", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ApplicationInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }


    private static void hook_getInstalledPackages() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getInstalledPackages");
        try {

            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);

                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //检索传入的应用名称,在系统上的已安装的整体信息
    private static void hook_getPackageInfo() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getPackageInfo");
        try {
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getPackageInfo", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    //伪造传入应用名称
                    param.args[0] = "000.000.000";
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //检索传入的应用名称,在系统上的已安装的整体信息
    private static void hook_getApplicationInfo() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getApplicationInfo");
        try {
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getApplicationInfo", int.class, new XC_MethodHook() {
                /*
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    //伪造传入应用名称
                    param.args[0] = "000.000.000";
                }*/
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ApplicationInfo xp = null;
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //小部件列表
    private static void hook_getInstalledProviders() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getInstalledProviders");
        try {
            findAndHookMethod("android.appwidget.AppWidgetManager", lpparam.classLoader, "getInstalledProviders", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<AppWidgetProviderInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);

                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //小部件列表
    private static void hook_getInstalledProvidersForProfile() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getInstalledProvidersForProfile()");
        try {
            findAndHookMethod("android.appwidget.AppWidgetManager", lpparam.classLoader, "getInstalledProvidersForProfile", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<AppWidgetProviderInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);

                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //根据uid获取包名
    private static void hook_getPackagesForUid() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getPackagesForUid");
        try {
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getPackagesForUid", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String xp = "";
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //根据权限返回对应应用列表
    private static void hook_getPackagesHoldingPermissions() {
        try {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getPackagesHoldingPermissions");
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getPackagesHoldingPermissions", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //某默认打开注册的APP总数
    private static void hook_getPreferredActivities() {
        try {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getPreferredActivities");
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getPreferredActivities", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    int xp = 0;
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //某默认打开注册的APP列表
    private static void hook_getPreferredPackages() {
        try {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getRunningServices");
            findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getPreferredPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //获取正在运行的服务
    private static void hook_getRunningServices() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getRunningServices");
        try {
            findAndHookMethod("android.app.ActivityManager", lpparam.classLoader, "getRunningServices", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ActivityManager.RunningServiceInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);

                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    //获取正在运行的应用列表
    private static void hook_getRunningAppProcesses() {
        XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + "hook_getRunningAppProcesses");
        try {
            findAndHookMethod("android.app.ActivityManager", lpparam.classLoader, "getRunningAppProcesses", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //List<ActivityManager.RunningAppProcessInfo> xp = (List<ActivityManager.RunningAppProcessInfo>) param.getResult();
                    List<ActivityManager.RunningAppProcessInfo> xp = null;
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp" + e.toString());
        }
    }

    public void handle() {
        if (prefs.getBoolean(lpparam.packageName + "/InstalledApp", false)) {

            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/InstalledApp");
            hook_getApplicationInfo();

            hook_getPackagesHoldingPermissions();
            hook_getPackagesForUid();
            hook_getPackageInfo();

            hook_getInstalledApplications();
            hook_getInstalledPackages();
            hook_getInstalledProviders();
            hook_getInstalledProvidersForProfile();

            hook_getPreferredActivities();
            hook_getPreferredPackages();
        }
        if (prefs.getBoolean(lpparam.packageName + "/RunningApp", false)) {

            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/RunningApp");
            hook_getRunningServices();
            hook_getRunningAppProcesses();
        }
    }

    private static class InstalledAppHolder {
        private static final InstalledApp installedApp = new InstalledApp();
    }
}

package com.jasperhale.myprivacy.XposedHook.Hook;

import android.appwidget.AppWidgetProviderInfo;
import android.os.UserHandle;

import java.util.ArrayList;
import java.util.List;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class AppWidgetManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "AWM";
    private static final String Class = "AWM";
    private static final String ClassName = "android.appwidget.AppWidgetManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static AppWidgetManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        AppWidgetManager.lpparam = lpparam;
        return AppWidgetManagerAppHolder.activityManager;
    }

    public static void handle() {

        if (mXpModel.getLimted(lpparam.packageName, Class, "getInstalledProvidersForPackage")) {
            getInstalledProvidersForPackage();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getInstalledProviders")) {
            getInstalledProviders();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getInstalledProvidersForProfile")) {
            getInstalledProvidersForProfile();
        }
    }


    //返回当前已安装的AppWidget列表
    private static void getInstalledProviders() {
        //XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledProviders");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledProviders", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<AppWidgetProviderInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    private static class AppWidgetManagerAppHolder {
        private static final AppWidgetManager activityManager = new AppWidgetManager();
    }

    //获取给定包和用户配置文件的AppWidget提供程序
    private static void getInstalledProvidersForPackage() {
        //XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledProvidersForPackage");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledProvidersForPackage", String.class,UserHandle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<AppWidgetProviderInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }


    //获取给定包和用户配置文件的AppWidget提供程序
    private static void getInstalledProvidersForProfile() {
        //XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledProviders");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledProvidersForProfile", UserHandle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<AppWidgetProviderInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }


}

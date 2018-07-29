package com.jasperhale.myprivacy.XposedHook.Hook;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Build {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "Build";
    private static final String Class = "Build";
    //private static final String ClassName = "android.appwidget.AppWidgetManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static Build get(XC_LoadPackage.LoadPackageParam lpparam) {
        Build.lpparam = lpparam;
        return Build.BuildHolder.build;
    }

    private static class BuildHolder {
        private static final Build build = new Build();
    }

    public static void handle() {

        if (mXpModel.getLimted(lpparam.packageName, Class, "BOARD")) {
            HookString(Build.class, "BOARD", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "BOOTLOADER")) {
            HookString(Build.class, "BOOTLOADER", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "BRAND")) {
            HookString(Build.class, "BRAND", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "DEVICE")) {
            HookString(Build.class, "DEVICE", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "DISPLAY")) {
            HookString(Build.class, "DISPLAY", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "FINGERPRINT")) {
            HookString(Build.class, "FINGERPRINT", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "HARDWARE")) {
            HookString(Build.class, "HARDWARE", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "HOST")) {
            HookString(Build.class, "HOST", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "ID")) {
            HookString(Build.class, "ID", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "MANUFACTURER")) {
            HookString(Build.class, "MANUFACTURER", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "MODEL")) {
            HookString(Build.class, "MODEL", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "PRODUCT")) {
            HookString(Build.class, "PRODUCT", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "RADIO")) {
            HookString(Build.class, "RADIO", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "TAGS")) {
            HookString(Build.class, "TAGS", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "TIME")) {
            HookString(Build.class, "TIME", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "TYPE")) {
            HookString(Build.class, "TYPE", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "USER")) {
            HookString(Build.class, "USER", "");
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getRadioVersion")) {
            HookString(Build.class, "getRadioVersion", "");
        }


    }

    public static void Hookint(final Class<?> cls, String fieldName, final int result) {
        XposedBridge.log(TAG+"->Hookint:"+result);
        try {
            XposedHelpers.setStaticObjectField(cls, fieldName, result);
        } catch (Throwable e) {
            XposedBridge.log("ERROR:Hookint:"+ e.getMessage());
        }
    }
    public static void HookString(final Class<?> cls, String fieldName, final String result) {
        XposedBridge.log(TAG+"->HookString:"+result);
        try {
            XposedHelpers.setStaticObjectField(cls, fieldName, result);
        } catch (Throwable e) {
            XposedBridge.log("HookString:"+ e.getMessage());
        }
    }

    /*
    private static void getInstalledProvidersForPackage() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledProvidersForPackage");
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
            log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }*/


}

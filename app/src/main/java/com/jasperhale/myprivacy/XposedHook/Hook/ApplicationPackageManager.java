package com.jasperhale.myprivacy.XposedHook.Hook;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class ApplicationPackageManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "APM";
    private static final String Class = "APM";
    private static final String ClassName = "android.app.ApplicationPackageManager";


    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static ApplicationPackageManager get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        ApplicationPackageManager.lpparam = lpparam;
        return ApplicationPackageManagerAppHolder.applicationPackageManager;
    }

    public void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledPackages")){
            getInstalledPackages();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getPackageInfo")){
            getPackageInfo();
        }
    }

    private static class ApplicationPackageManagerAppHolder {
        private static final ApplicationPackageManager applicationPackageManager = new ApplicationPackageManager();
    }

    //返回所有ApplicationInfo
    private static void getInstalledApplications() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledApplications");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledApplications", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<ApplicationInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回所有PackageInfo
    private static void getInstalledPackages() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledPackages");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //检索传入的应用名称,在系统上的已安装的整体信息
    private static void getPackageInfo() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getPackageInfo");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPackageInfo", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    //伪造传入应用名称
                    param.args[0] = "000.000.000";
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //根据uid获取包名
    private static void getPackagesForUid() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getPackagesForUid");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPackagesForUid", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    String xp = "";
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //根据权限返回对应应用列表
    private static void getPackagesHoldingPermissions() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getPackagesHoldingPermissions");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPackagesHoldingPermissions", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //某默认打开注册的APP总数
    private static void getPreferredActivities() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getPreferredActivities");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPreferredActivities", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    int xp = 0;
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //某默认打开注册的APP列表
    private static void getPreferredPackages() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getRunningServices");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPreferredPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

}

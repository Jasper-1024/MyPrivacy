package XposedHook.Hook;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class PackageManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "PM";
    private static final String Class = "PM";
    //private static final String ClassName = "android.content.pm.PackageManager";
    private static final String ClassName = "android.app.ApplicationPackageManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static PackageManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        PackageManager.lpparam = lpparam;
        return PackageManagerAppHolder.packageManager;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName, Class, "getInstalledApplications")) {
            getInstalledApplications();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getInstalledPackages")) {
            getInstalledPackages();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getInstallerPackageName")) {
            getInstallerPackageName();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getPackagesHoldingPermissions")) {
            getPackagesHoldingPermissions();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getPreferredPackages")) {
            getPreferredPackages();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "queryIntentActivities")) {
            queryIntentActivities();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "queryIntentActivityOptions")) {
            queryIntentActivityOptions();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "ueryIntentContentProviders")) {
            getPreferredPackages();
        }
    }

    private static class PackageManagerAppHolder {
        private static final PackageManager packageManager = new PackageManager();
    }


    //获取已安装应用列表
    private static void getInstalledApplications() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledApplications");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledApplications", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ApplicationInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledApplications"+ e.toString());
        }
    }

    //返回为当前用户安装的所有软件包的列表
    private static void getInstalledPackages() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstalledPackages");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstalledPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回检索的软件包名
    private static void getInstallerPackageName() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInstallerPackageName");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInstallerPackageName", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("");
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回当前持有任何给定权限的所有已安装软件包的列表。
    private static void getPackagesHoldingPermissions() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getPackagesHoldingPermissions");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPackagesHoldingPermissions", String.class, int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<PackageInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //检索当前所有首选软件包列表
    private static void getPreferredPackages() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getPreferredPackages");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getPreferredPackages", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ApplicationInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //检索initent响应的所有activity
    private static void queryIntentActivities() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "queryIntentActivities");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "queryIntentActivities", Intent.class, int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ResolveInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //检索initent响应的所有activity
    private static void queryIntentActivityOptions() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "queryIntentActivityOptions");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "queryIntentActivityOptions", ComponentName.class, Intent[].class, Intent.class, int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ResolveInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //检索initent响应的所有Providers
    private static void queryIntentContentProviders() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "queryIntentContentProviders");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "queryIntentContentProviders", Intent.class, int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<ResolveInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (java.lang.NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

}

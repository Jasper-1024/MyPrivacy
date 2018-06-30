package XposedHook.Hook;

import java.util.ArrayList;
import java.util.List;

import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class ActivityManager {
    private static final String Mark = "Xposed.MyPrivacy.AM ";
    private static final String TAG = " ";
    private static final String Class = "AM";
    private static final String ClassName = "android.app.ActivityManager";


    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static ActivityManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        ActivityManager.lpparam = lpparam;
        return ActivityManagerAppHolder.activityManager;
    }

    public static void handle() {
        XposedBridge.log("MyPrivacy" + lpparam.packageName+ClassName);
        if (mXpModel.getLimted(lpparam.packageName, Class, "getRecentTasks")) {
            getRecentTasks();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getRunningAppProcesses")) {
            getRunningAppProcesses();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getRunningServices")) {
            getRunningServices();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getRunningTasks")) {
            getRunningTasks();
        }
    }

    private static class ActivityManagerAppHolder {
        private static final ActivityManager activityManager = new ActivityManager();
    }

    //返回设备最近启动任务列表 第一个位最近启动 API21以上已取消
    private static void getRecentTasks() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getRecentTasks");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getRecentTasks", int.class,int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<android.app.ActivityManager.RecentTaskInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回设备上正在运行的应用程序进程的列表 仅用于调试
    private static void getRunningAppProcesses() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getRunningAppProcesses");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getRecentTasks", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<android.app.ActivityManager.RunningAppProcessInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回当前正在运行的服务的列表 API26以上已取消
    private static void getRunningServices() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getRunningServices");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getRunningServices", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<Object> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回当前正在运行的服务的列表 API26以上已取消
    private static void getRunningTasks() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getRunningTasks");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getRunningTasks", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<android.app.ActivityManager.RunningTaskInfo> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }


}

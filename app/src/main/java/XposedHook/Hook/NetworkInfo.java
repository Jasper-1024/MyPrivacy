package XposedHook.Hook;

import android.app.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class NetworkInfo {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "NI";
    private static final String Class = "NI";
    private static final String ClassName = "android.net.NetworkInfo";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static NetworkInfo get(XC_LoadPackage.LoadPackageParam lpparam) {
        NetworkInfo.lpparam = lpparam;
        return NetworkInfoHolder.networkInfo;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"getExtraInfo")){
            getExtraInfo();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getTypeName")){
            getExtraInfo();
        }
    }

    private static class NetworkInfoHolder {
        private static final NetworkInfo networkInfo = new NetworkInfo();
    }
    //报告有关网络状态的额外信息
    private static void getExtraInfo() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getExtraInfo");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getExtraInfo", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //List<ActivityManager.RunningTaskInfo> xp = new ArrayList<>();
                    //xp.clear();
                    param.setResult("");
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    private static void getTypeName() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getTypeName");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getTypeName", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //List<ActivityManager.RunningTaskInfo> xp = new ArrayList<>();
                    //xp.clear();
                    param.setResult("WIFI");
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }
}

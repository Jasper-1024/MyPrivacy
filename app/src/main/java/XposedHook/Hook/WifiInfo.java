package XposedHook.Hook;

import android.telephony.CellInfo;

import java.util.ArrayList;
import java.util.List;

import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class WifiInfo {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "WI";
    private static final String Class = "WI";
    private static final String ClassName = "android.net.wifi.WifiInfo";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static WifiInfo get(XC_LoadPackage.LoadPackageParam lpparam) {
        WifiInfo.lpparam = lpparam;
        return WifiInfoHolder.wifiInfo;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"getBSSID")){
            getBSSID();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getIpAddress")){
            getIpAddress();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getMacAddress")){
            getMacAddress();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getSSID")){
            getSSID();
        }
    }

    private static class WifiInfoHolder {
        private static final WifiInfo wifiInfo= new WifiInfo();
    }

    //返回主mac地址
    private static void getBSSID() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getBSSID");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getBSSID", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("02:00:00:00:00:00");
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回ipv4 地址
    private static void getIpAddress() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getIpAddress");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getIpAddress", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(0);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回mac地址
    private static void getMacAddress() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getMacAddress");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getMacAddress", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("02:00:00:00:00:00");
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回SSID
    private static void getSSID() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getSSID");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getSSID", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("\" \"");
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

}

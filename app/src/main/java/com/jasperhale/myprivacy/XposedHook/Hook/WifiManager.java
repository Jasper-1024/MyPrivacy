package com.jasperhale.myprivacy.XposedHook.Hook;

import android.net.wifi.WifiConfiguration;

import java.util.ArrayList;
import java.util.List;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class WifiManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "WM";
    private static final String Class = "WM";
    private static final String ClassName = "android.net.wifi.WifiManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static WifiManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        WifiManager.lpparam = lpparam;
        return WifiManagerHolder.wifiManager;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"getConfiguredNetworks")){
            getConfiguredNetworks();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getScanResults")){
            getScanResults();
        }
    }

    private static class WifiManagerHolder {
        private static final WifiManager wifiManager= new WifiManager();
    }

    //返回用户配置的所有wifi网络列表
    private static void getConfiguredNetworks() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getConfiguredNetworks");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getConfiguredNetworks", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) {
                    List<WifiConfiguration> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult(xp);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回wifi扫描结果
    private static void getScanResults() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getScanResults");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getScanResults", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) {
                    //List<ScanResult> xp = new ArrayList<>();
                    //xp.clear();
                    param.setResult(null);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }
}

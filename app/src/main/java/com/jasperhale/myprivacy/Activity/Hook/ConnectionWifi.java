package com.jasperhale.myprivacy.Activity.Hook;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;



/**
 * Created by ZHANG on 2017/11/20.
 */

public class ConnectionWifi {
    private static XC_LoadPackage.LoadPackageParam lpparam;
    private static XSharedPreferences prefs;
    private static String Mac;
    private static String SSID;

    public static ConnectionWifi getConnectionWifi(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        ConnectionWifi.lpparam = lpparam;
        ConnectionWifi.prefs = prefs;
        return ConnectionWifiHolder.connectionWifi;
    }

    public static void hook_fake_getConnectionInfo(final Class<?> cls, final String method,
                                                   final String mBSSID, final String mMacAddress, final String mSSID, InetAddress mIpAddress) {
        try {
            Object[] obj = new Object[]{new XC_MethodHook() {
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    WifiInfo result = (WifiInfo) param.getResult();
                    WifiInfo wInfo = WifiInfo.class.getConstructor(WifiInfo.class).newInstance(result);
                    //
                    Field fieldBssid = WifiInfo.class.getDeclaredField("mBSSID");
                    fieldBssid.setAccessible(true);
                    fieldBssid.set(wInfo, mBSSID);
                    //
                    Field fieldMac = WifiInfo.class.getDeclaredField("mMacAddress");
                    fieldMac.setAccessible(true);
                    fieldMac.set(wInfo, mMacAddress);
                    //
                    Field fieldIpAddress = WifiInfo.class.getDeclaredField("mIpAddress");
                    fieldMac.setAccessible(true);
                    fieldMac.set(wInfo, mIpAddress);
                    //
                    try {
                        Field fieldSSID = WifiInfo.class.getDeclaredField("mSSID");
                        fieldSSID.setAccessible(true);
                        fieldSSID.set(wInfo, mSSID);
                    } catch (Throwable ex) {
                        try {
                            Field fieldWifiSsid = WifiInfo.class.getDeclaredField("mWifiSsid");
                            fieldWifiSsid.setAccessible(true);
                            Object mWifiSsid = fieldWifiSsid.get(wInfo);
                            if (mWifiSsid != null) {
                                Method methodCreateFromAsciiEncoded = mWifiSsid.getClass().getDeclaredMethod(
                                        "createFromAsciiEncoded", String.class);
                                fieldWifiSsid.set(wInfo, methodCreateFromAsciiEncoded.invoke(null, mSSID));
                            }
                        } catch (Throwable exex) {
                            LogUtil.d("MyPrivacy", exex.toString());
                        }
                    }
                    //
                    param.setResult(wInfo);
                }
            }};
            XposedHelpers.findAndHookMethod(cls, method, obj);
        } catch (Throwable e) {
            LogUtil.d("MyPrivacy", "ERROR:HookWifiGetConnectionInfo:" + e.getMessage());
        }
    }

    //WIFI扫描信息
    public static void hook_WifiGetScanResult(final Class<?> cls, final String method) {
        try {
            Object[] obj = new Object[]{new XC_MethodHook() {
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //List<ScanResult> rtn = (List<ScanResult>) param.getResult();
                    List<ScanResult> scanResults = new ArrayList<>();
                    scanResults.clear();
                    param.setResult(scanResults);
                }
            }};
            XposedHelpers.findAndHookMethod(cls, method, obj);
        } catch (Throwable e) {
            LogUtil.d("MyPrivacy", "ERROR:HookWifiGetConnectionInfo:" + e.getMessage());
        }
    }

    //wifi状态  返回 Wi-Fi is disabled.
    private static void hook_getWifiState(){
        XposedHelpers.findAndHookMethod("android.net.wifi.WifiManager", lpparam.classLoader, "getWifiState", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(1);
            }
        });
    }
    //wifi打开?
    private static void hook_isWifiEnabled(){
        XposedHelpers.findAndHookMethod("android.net.wifi.WifiManager", lpparam.classLoader, "isWifiEnabled", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });
    }
    //getMacAddress
    private static void hook_fake_getMacAddress(String Mac){
        XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", lpparam.classLoader, "getMacAddress", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(Mac);
            }
        });
    }
    //getSSID
    private static void hook_fake_getSSID(String SSID){
        XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", lpparam.classLoader, "getSSID", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(SSID);
            }
        });
    }
    //getBSSID
    private static void hook_getBSSID(){
        XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", lpparam.classLoader, "getBSSID", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult("00-00-00-00-00-00-00-00");
            }
        });
    }



    public void handle() {
        if (prefs.getBoolean(lpparam.packageName + "/ConnectionWifi", false)) {

            Mac = prefs.getString(lpparam.packageName + "/Mac", "");
            SSID = prefs.getString(lpparam.packageName + "/SSID", "");

            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/ConnectionWifi");

            hook_fake_getConnectionInfo(WifiManager.class, "getConnectionInfo",
                    "",
                    Mac,
                    SSID,
                    null);
            hook_fake_getMacAddress(Mac);
            hook_fake_getSSID(SSID);
            hook_getBSSID();
            hook_WifiGetScanResult(WifiManager.class, "getScanResults");
            hook_getWifiState();
            hook_isWifiEnabled();
        }
    }

    private static class ConnectionWifiHolder {
        private static final ConnectionWifi connectionWifi = new ConnectionWifi();
    }
}

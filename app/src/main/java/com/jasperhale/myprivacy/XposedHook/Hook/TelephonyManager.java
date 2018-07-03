package com.jasperhale.myprivacy.XposedHook.Hook;

import android.telephony.PhoneStateListener;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class TelephonyManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "TM";
    private static final String Class = "TM";
    private static final String ClassName = "android.telephony.TelephonyManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static TelephonyManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        TelephonyManager.lpparam = lpparam;
        return TelephonyManagerHolder.telephonyManager;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"getAllCellInfo")){
            getAllCellInfo();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getCellLocation")){
            getCellLocation();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"getNeighboringCellInfo")){
            getNeighboringCellInfo();
        }
        if (mXpModel.getLimted(lpparam.packageName,Class,"listen")){
            listen();
        }
    }

    private static class TelephonyManagerHolder {
        private static final TelephonyManager telephonyManager = new TelephonyManager();
    }

    //返回设备搜索到所有小区信息 主+邻
    private static void getAllCellInfo() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getAllCellInfo");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getAllCellInfo", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    //List<CellInfo> xp = new ArrayList<>();
                    //xp.clear();
                    param.setResult(null);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回设备位置 api26取消
    private static void getCellLocation() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getCellLocation");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getCellLocation", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    param.setResult(null);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //返回设备临近小区列表 api23取消
    private static void getNeighboringCellInfo() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getNeighboringCellInfo");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "listen",new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    //List<NeighboringCellInfo> xp = new ArrayList<>();
                    //xp.clear();
                    param.setResult(null);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //注册一个小区变化监听
    private static void listen() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "listen");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "listen", PhoneStateListener.class,int.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    param.args[0] = null;
                    param.args[1] = 0;
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }
}

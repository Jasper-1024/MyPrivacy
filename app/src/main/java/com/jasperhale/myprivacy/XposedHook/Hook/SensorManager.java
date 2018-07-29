package com.jasperhale.myprivacy.XposedHook.Hook;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SensorManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "SM";
    private static final String Class = "SM";
    private static final String ClassName = "android.hardware.SensorManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static SensorManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        SensorManager.lpparam = lpparam;
        return SensorManagerHolder.sensorManager;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName, Class, "getDefaultSensor")) {
            getDefaultSensor();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getDynamicSensorList")) {
            getDynamicSensorList();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getSensorList")) {
            getSensorList();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getSensors")) {
            getSensors();
        }
    }

    private static class SensorManagerHolder {
        private static final SensorManager sensorManager = new SensorManager();
    }

    //
    private static void getDefaultSensor() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getDefaultSensor");
        try {
            final Class<?> LocationManager;
            LocationManager = XposedHelpers.findClass(ClassName, lpparam.classLoader);
            XposedBridge.hookAllMethods(LocationManager, "getDefaultSensor", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(null);
                    XposedBridge.log(Mark + lpparam.packageName + TAG + "getDefaultSensor"+1);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //
    private static void getDynamicSensorList() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getDynamicSensorList");
        XposedHelpers.findAndHookMethod(LocationManager.class, "getDynamicSensorList", Integer.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) {
                param.setResult(null);
                XposedBridge.log(Mark + lpparam.packageName + TAG + "getDynamicSensorList"+1);

            }
        });
    }

    //
    private static void getSensorList() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getSensorList");
        XposedHelpers.findAndHookMethod(LocationManager.class, "getSensorList", Integer.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) {
                param.setResult(null);
                XposedBridge.log(Mark + lpparam.packageName + TAG + "getSensorList"+1);
            }
        });
    }

    //
    private static void getSensors() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getSensors");
        XposedHelpers.findAndHookMethod(LocationManager.class, "getSensors", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) {
                param.setResult(null);
                XposedBridge.log(Mark + lpparam.packageName + TAG + "getSensors"+1);
            }
        });
    }

}

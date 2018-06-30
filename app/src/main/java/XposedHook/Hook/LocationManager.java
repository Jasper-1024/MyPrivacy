package XposedHook.Hook;

import android.app.PendingIntent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.os.Build;
import android.os.SystemClock;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class LocationManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "LM";
    private static final String Class = "LM";
    private static final String ClassName = "android.location.LocationManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static LocationManager get(XC_LoadPackage.LoadPackageParam lpparam) {
        LocationManager.lpparam = lpparam;
        return LocationManagerHolder.locationManager;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName, Class, "addGpsStatusListener")) {
            addGpsStatusListener();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "addNmeaListener")) {
            addNmeaListener();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "addProximityAlert")) {
            addProximityAlert();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getBestProvider")) {
            getBestProvider();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getLastLocation")) {
            getLastLocation();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getLastKnownLocation")) {
            getLastKnownLocation();
        }
        if (mXpModel.getLimted(lpparam.packageName, Class, "getProviders")) {
            getProviders();
        }
    }

    private static class LocationManagerHolder {
        private static final LocationManager locationManager = new LocationManager();
    }

    //Adds a GPS status listener.
    private static void addGpsStatusListener() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "addNmeaListener");
        XposedHelpers.findAndHookMethod(LocationManager.class, "addGpsStatusListener", GpsStatus.Listener.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if (param.args[0] != null) {
                    XposedHelpers.callMethod(param.args[0], "onGpsStatusChanged", 1);
                    XposedHelpers.callMethod(param.args[0], "onGpsStatusChanged", 3);
                }
            }
        });
    }

    //添加监听
    private static void addNmeaListener() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "addNmeaListener");
        try {
            final Class<?> LocationManager;
            LocationManager = XposedHelpers.findClass(ClassName, lpparam.classLoader);
            XposedBridge.hookAllMethods(LocationManager, "addNmeaListener", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.args[0] = null;
                    //param.setResult(false);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //报告有关网络状态的额外信息
    private static void addProximityAlert() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "addProximityAlert");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "addProximityAlert", double.class, double.class, float.class, long.class, PendingIntent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.args[0] = 0;
                    param.args[1] = 0;
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }

    //最好的定位提供商 指定gps
    private static void getBestProvider() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getBestProvider");
        XposedHelpers.findAndHookMethod(LocationManager.class, "getBestProvider", Criteria.class, Boolean.TYPE, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult("gps");
            }
        });
    }

    //提供商 位置
    private static void getLastLocation() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getLastLocation");
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastLocation", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(null);
            }
        });
    }

    //最近一次位置
    private static void getLastKnownLocation() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getLastKnownLocation");
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastKnownLocation", String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(null);
            }
        });
    }

    //定位提供商
    private static void getProviders() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getProviders");
        XposedBridge.hookAllMethods(LocationManager.class, "getProviders", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("gps");
                param.setResult(arrayList);
            }
        });
    }
}

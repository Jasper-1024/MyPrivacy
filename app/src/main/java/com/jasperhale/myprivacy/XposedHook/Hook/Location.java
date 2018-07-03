package com.jasperhale.myprivacy.XposedHook.Hook;

import android.os.Parcel;

import com.jasperhale.myprivacy.XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Location {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "L";
    private static final String Class = "L";
    private static final String ClassName = "android.location.Location";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static Location get(XC_LoadPackage.LoadPackageParam lpparam) {
        Location.lpparam = lpparam;
        return LocationHolder.location;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"createFromParcel")){
            createFromParcel();
        }
    }

    private static class LocationHolder {
        private static final Location location = new Location();
    }

    private static void createFromParcel() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "createFromParcel");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "createFromParcel", Parcel.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) {
                    //List<ActivityManager.RunningTaskInfo> xp = new ArrayList<>();
                    //xp.clear();
                    param.setResult(null);
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }
}

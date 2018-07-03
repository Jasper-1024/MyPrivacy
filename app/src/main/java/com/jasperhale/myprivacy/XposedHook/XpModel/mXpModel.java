package com.jasperhale.myprivacy.XposedHook.XpModel;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;

public class mXpModel {
    private static final String TAG = "Xposed.MyPrivacy.mXpModel ";

    //private final String APP_NAME = "MyPrivacy";
    private static final String PACKAGE_NAME = "com.jasperhale.myprivacy";//Xposed_user .class.getPackage().getName();
    //private final String PREFS_NAME = PACKAGE_NAME + "_preferences";

    private static XSharedPreferences prefs ;

    /*
    static {
        prefs = new XSharedPreferences(PACKAGE_NAME, "MyPrivacy");
        prefs.makeWorldReadable();
    }*/

    public static void prefs_init() {
        prefs = new XSharedPreferences(PACKAGE_NAME, "MyPrivacy");
        prefs.makeWorldReadable();
        XposedBridge.log(TAG + "preferences init");
    }

    private static XSharedPreferences getPrefs() {
        if (prefs == null) {
            prefs = new XSharedPreferences(PACKAGE_NAME, "MyPrivacy");
            prefs.makeWorldReadable();
        } else {
            prefs.reload();
        }
        return prefs;
    }

    public static Boolean getLimted(String packageName, String Class, String method) {
        XposedBridge.log(TAG + packageName + Class +"_"+ method);
        return getPrefs().getBoolean(packageName + Class +"_"+ method, false);
    }

}

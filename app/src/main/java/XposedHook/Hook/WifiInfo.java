package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WifiInfo {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "WM";
    private static final String Class = "WM";
    private static final String ClassName = "android.net.wifi.WifiInfo";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static WifiInfo get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        WifiInfo.lpparam = lpparam;
        return WifiInfoHolder.wifiInfo;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class WifiInfoHolder {
        private static final WifiInfo wifiInfo= new WifiInfo();
    }
}

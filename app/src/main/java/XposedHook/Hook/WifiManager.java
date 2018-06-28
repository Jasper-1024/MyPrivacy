package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WifiManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "WM";
    private static final String Class = "WM";
    private static final String ClassName = "android.net.wifi.WifiManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static WifiManager get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        WifiManager.lpparam = lpparam;
        return WifiManagerHolder.wifiManager;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class WifiManagerHolder {
        private static final WifiManager wifiManager= new WifiManager();
    }
}

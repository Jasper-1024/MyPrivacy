package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TelephonyManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "TM";
    private static final String Class = "TM";
    private static final String ClassName = "android.telephony.TelephonyManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static TelephonyManager get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        TelephonyManager.lpparam = lpparam;
        return TelephonyManagerHolder.telephonyManager;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class TelephonyManagerHolder {
        private static final TelephonyManager telephonyManager = new TelephonyManager();
    }
}

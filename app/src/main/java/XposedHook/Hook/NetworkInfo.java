package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class NetworkInfo {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "NI";
    private static final String Class = "NI";
    private static final String ClassName = "android.net.NetworkInfo";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static NetworkInfo get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        NetworkInfo.lpparam = lpparam;
        return NetworkInfoHolder.networkInfo;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class NetworkInfoHolder {
        private static final NetworkInfo networkInfo = new NetworkInfo();
    }
}

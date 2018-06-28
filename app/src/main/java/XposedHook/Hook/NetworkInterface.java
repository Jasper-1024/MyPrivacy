package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class NetworkInterface {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "NI";
    private static final String Class = "NI";
    private static final String ClassName = "java.net.NetworkInterface";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static NetworkInterface get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        NetworkInterface.lpparam = lpparam;
        return NetworkInterfaceHolder.networkInterface;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class NetworkInterfaceHolder {
        private static final NetworkInterface networkInterface = new NetworkInterface();
    }
}

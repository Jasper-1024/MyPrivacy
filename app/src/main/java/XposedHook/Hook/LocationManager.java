package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LocationManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "LM";
    private static final String Class = "LM";
    private static final String ClassName = "android.location.LocationManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static LocationManager get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        LocationManager.lpparam = lpparam;
        return LocationManagerHolder.locationManager;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class LocationManagerHolder {
        private static final LocationManager locationManager = new LocationManager();
    }
}

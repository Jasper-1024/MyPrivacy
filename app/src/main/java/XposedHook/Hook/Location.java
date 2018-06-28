package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Location {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "L";
    private static final String Class = "L";
    private static final String ClassName = "android.location.Location";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static Location get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        Location.lpparam = lpparam;
        return LocationHolder.location;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class LocationHolder {
        private static final Location location = new Location();
    }
}

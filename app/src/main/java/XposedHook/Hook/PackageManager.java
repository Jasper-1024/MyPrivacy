package XposedHook.Hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PackageManager {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "AWM";
    private static final String Class = "AWM";
    private static final String ClassName = "android.appwidget.AppWidgetManager";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static PackageManager get(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        PackageManager.lpparam = lpparam;
        return PackageManagerAppHolder.packageManager;
    }

    public static void handle() {
        /*
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInstalledApplications")){
            getInstalledApplications();
        }*/
    }

    private static class PackageManagerAppHolder {
        private static final PackageManager packageManager = new PackageManager();
    }
}

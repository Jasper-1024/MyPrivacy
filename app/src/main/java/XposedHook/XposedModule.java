package XposedHook;

import XposedHook.Hook.ActivityManager;
import XposedHook.Hook.AppWidgetManager;
import XposedHook.Hook.Location;
import XposedHook.Hook.LocationManager;
import XposedHook.Hook.NetworkInfo;
import XposedHook.Hook.NetworkInterface;
import XposedHook.Hook.PackageManager;
import XposedHook.Hook.TelephonyManager;
import XposedHook.Hook.WifiInfo;
import XposedHook.Hook.WifiManager;
import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedModule implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    private final String TAG = "Xposed.MyPrivacy ";

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        mXpModel.prefs_init();
        XposedBridge.log(TAG + "initZygote");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        XposedBridge.log(TAG + lpparam.packageName + " handleLoadPackage");
        ActivityManager.get(lpparam).handle();
        AppWidgetManager.get(lpparam).handle();
        Location.get(lpparam).handle();
        LocationManager.get(lpparam).handle();
        NetworkInfo.get(lpparam).handle();
        NetworkInterface.get(lpparam).handle();
        PackageManager.get(lpparam).handle();
        TelephonyManager.get(lpparam).handle();
        WifiInfo.get(lpparam).handle();
        WifiManager.get(lpparam).handle();
    }

}

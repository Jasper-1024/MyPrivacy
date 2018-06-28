package XposedHook;

import XposedHook.Hook.ActivityManager;
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

        XposedBridge.log(TAG + lpparam.packageName + "  handleLoadPackage");
        //InstalledApp.getInstalledApp(lpparam,prefs).handle();
        ActivityManager.get(lpparam).handle();
    }

}

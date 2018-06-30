package XposedHook.Hook;

import android.app.ActivityManager;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

import XposedHook.XpModel.mXpModel;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class NetworkInterface {
    private static final String Mark = "MyprivacyHook";
    private static final String TAG = "NIF";
    private static final String Class = "NIF";
    private static final String ClassName = "java.net.NetworkInterface";

    private static XC_LoadPackage.LoadPackageParam lpparam;

    public static NetworkInterface get(XC_LoadPackage.LoadPackageParam lpparam) {
        NetworkInterface.lpparam = lpparam;
        return NetworkInterfaceHolder.networkInterface;
    }

    public static void handle() {
        if (mXpModel.getLimted(lpparam.packageName,Class,"getInterfaceAddresses")){
            getInterfaceAddresses();
        }
    }

    private static class NetworkInterfaceHolder {
        private static final NetworkInterface networkInterface = new NetworkInterface();
    }

    //报告有关网络状态的额外信息
    private static void getInterfaceAddresses() {
        XposedBridge.log(Mark + lpparam.packageName + TAG + "getInterfaceAddresses");
        try {
            findAndHookMethod(ClassName, lpparam.classLoader, "getInterfaceAddresses", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<InterfaceAddress> xp = new ArrayList<>();
                    xp.clear();
                    param.setResult("");
                }
            });
        } catch (NoSuchMethodError e) {
            XposedBridge.log(Mark + lpparam.packageName + TAG + e.toString());
        }
    }
}

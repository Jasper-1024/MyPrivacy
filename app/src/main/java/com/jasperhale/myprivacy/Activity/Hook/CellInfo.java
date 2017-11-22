package com.jasperhale.myprivacy.Activity.Hook;

import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.gsm.GsmCellLocation;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.log;

/**
 * Created by ZHANG on 2017/11/22.
 */

public class CellInfo {
    private static XC_LoadPackage.LoadPackageParam lpparam;
    private static XSharedPreferences prefs;
    private static int mcc = 460;
    private static int mnc = 1;
    private static int lac = 41070;
    private static int cid = 8309067;

    public static CellInfo getCellInfo(XC_LoadPackage.LoadPackageParam lpparam, XSharedPreferences prefs) {
        CellInfo.lpparam = lpparam;
        CellInfo.prefs = prefs;
        return CellInfoHolder.cellInfo;
    }

    private static void hook_getAllCellInfo() {
        XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                "getAllCellInfo", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(getCell(mcc,mnc,lac,cid,0,0));
                    }
                });
    }

    private static void hook_onCellInfoChanged() {
        XposedHelpers.findAndHookMethod("android.telephony.PhoneStateListener", lpparam.classLoader,
                "onCellInfoChanged", List.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(getCell(mcc,mnc,lac,cid,0,0));
                    }
                });
    }

    //根据cellid定位.fake,rip
    private static void hook_fake_getCellLocation() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                    "getCellLocation", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            GsmCellLocation gsmCellLocation = new GsmCellLocation();
                            gsmCellLocation.setLacAndCid(4289, 18001);
                            param.setResult(gsmCellLocation);
                        }
                    });

        }
    }

    //hookl getPhoneCount,返回0 不支持通话,1单卡,2双卡
    private static void hook_fake_getPhoneCount(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                    "getPhoneCount", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(0);
                        }
                    });
        }
    }

    //返回临近cell信息,api23以后被取代
    private static void hook_fake_getNeighboringCellInfo(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", lpparam.classLoader,
                    "getNeighboringCellInfo", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                            param.setResult(new ArrayList<>());
                        }
                    });
        }
    }

    //设备位置改变时
    private static void hook_fake_onCellLocationChanged(){
        XposedHelpers.findAndHookMethod("android.telephony.PhoneStateListener", lpparam.classLoader,
                "onCellLocationChanged", CellLocation.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        GsmCellLocation gsmCellLocation = new GsmCellLocation();
                        gsmCellLocation.setLacAndCid(4289, 18001);
                        param.setResult(gsmCellLocation);
                    }
                });
    }

    //基站信息变动时
    private static void hook_fake_onCellInfoChanged(){
        XposedHelpers.findAndHookMethod("android.telephony.PhoneStateListener", lpparam.classLoader,
                "onCellInfoChanged", List.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(getCell(460, 0, lac, cid, 0,0));
                    }
                });
    }

    private static void hook_fake_isRegistered(){
        XposedHelpers.findAndHookMethod("android.telephony.CellInfo", lpparam.classLoader,
                "isRegistered", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                    }
                });
    }

    private static ArrayList getCell(int mcc, int mnc, int lac, int cid, int sid, int networkType) {
        ArrayList arrayList = new ArrayList();
        CellInfoGsm cellInfoGsm = (CellInfoGsm) XposedHelpers.newInstance(CellInfoGsm.class);
        XposedHelpers.callMethod(cellInfoGsm, "setCellIdentity", XposedHelpers.newInstance(CellIdentityGsm.class, new Object[]{Integer.valueOf(mcc), Integer.valueOf(mnc), Integer.valueOf(
                lac), Integer.valueOf(cid)}));
        CellInfoCdma cellInfoCdma = (CellInfoCdma) XposedHelpers.newInstance(CellInfoCdma.class);
        XposedHelpers.callMethod(cellInfoCdma, "setCellIdentity", XposedHelpers.newInstance(CellIdentityCdma.class, new Object[]{Integer.valueOf(lac), Integer.valueOf(sid), Integer.valueOf(cid), Integer.valueOf(0), Integer.valueOf(0)}));
        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) XposedHelpers.newInstance(CellInfoWcdma.class);
        XposedHelpers.callMethod(cellInfoWcdma, "setCellIdentity", XposedHelpers.newInstance(CellIdentityWcdma.class, new Object[]{Integer.valueOf(mcc), Integer.valueOf(mnc), Integer.valueOf(lac), Integer.valueOf(cid), Integer.valueOf(300)}));
        CellInfoLte cellInfoLte = (CellInfoLte) XposedHelpers.newInstance(CellInfoLte.class);
        XposedHelpers.callMethod(cellInfoLte, "setCellIdentity", XposedHelpers.newInstance(CellIdentityLte.class, new Object[]{Integer.valueOf(mcc), Integer.valueOf(mnc), Integer.valueOf(cid), Integer.valueOf(300), Integer.valueOf(lac)}));
        if (networkType == 1 || networkType == 2) {
            arrayList.add(cellInfoGsm);
        } else if (networkType == 13) {
            arrayList.add(cellInfoLte);
        } else if (networkType == 4 || networkType == 5 || networkType == 6 || networkType == 7 || networkType == 12 || networkType == 14) {
            arrayList.add(cellInfoCdma);
        } else if (networkType == 3 || networkType == 8 || networkType == 9 || networkType == 10 || networkType == 15) {
            arrayList.add(cellInfoWcdma);
        }
        return arrayList;
    }

    public void handle() {
        if (prefs.getBoolean(lpparam.packageName + "/CellInfo", false)) {
            mcc = prefs.getInt(lpparam.packageName + "/Mcc",460);
            mnc = prefs.getInt(lpparam.packageName + "/Mnc",1);
            cid = prefs.getInt(lpparam.packageName + "/Cid",8309067);
            lac = prefs.getInt(lpparam.packageName + "/Lac",41070);

            XposedBridge.log("MyPrivacy hook " + lpparam.packageName + "/CellInfo");

            hook_fake_getCellLocation();
            hook_fake_getNeighboringCellInfo();
            hook_fake_getPhoneCount();
            hook_fake_isRegistered();
            hook_fake_onCellInfoChanged();
            hook_getAllCellInfo();
            hook_onCellInfoChanged();

        }
    }

    private static class CellInfoHolder {
        private static final CellInfo cellInfo = new CellInfo();
    }

}



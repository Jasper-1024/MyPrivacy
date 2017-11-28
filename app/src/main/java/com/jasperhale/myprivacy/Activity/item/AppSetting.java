package com.jasperhale.myprivacy.Activity.item;


import android.databinding.Bindable;

import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/11/10.
 */

public class AppSetting extends BaseItem {
    private boolean InstalledApp = false;
    private boolean RunningApp = false;
    private boolean ConnectionWifi = false;
    private String SSID = "1900";
    private String Mac = "A5:A2:6F:35:D0:CF";
    //the network ID, or -1 if there is no currently connected network
    private int NetworkId = -1;

    private boolean CellInfo = false;
    private int mcc = 460;
    private int mnc = 1;
    private int lac = 41070;
    private int cid = 8309067;
    private String smcc ="460";
    private String smnc = "1";
    private String slac = "41070";
    private String scid = "8309067";

    @Override
    public int getViewType() {
        return R.layout.activity_app_setting;
    }


    @Bindable
    public boolean getInstalledApp() {
        return InstalledApp;
    }

    public void setInstalledApp(boolean bool) {
        this.InstalledApp = bool;
        notifyPropertyChanged(BR.installedApp);
    }

    @Bindable
    public boolean getRunningApp() {
        return RunningApp;
    }

    public void setRunningApp(boolean bool) {
        this.RunningApp = bool;
        notifyPropertyChanged(BR.runningApp);
    }

    @Bindable
    public boolean getConnectionWifi() {
        return ConnectionWifi;
    }

    public void setConnectionWifi(boolean bool) {
        this.ConnectionWifi = bool;
        notifyPropertyChanged(BR.connectionWifi);
    }

    @Bindable
    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
        notifyPropertyChanged(BR.sSID);
    }

    @Bindable
    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
        notifyPropertyChanged(BR.mac);
    }

    @Bindable
    public int getNetworkId() {
        return NetworkId;
    }

    public void setNetworkId(int networkId) {
        NetworkId = networkId;
        notifyPropertyChanged(BR.networkId);
    }

    @Bindable
    public boolean getCellInfo() {
        return CellInfo;
    }

    public void setCellInfo(boolean bool) {
        this.CellInfo = bool;
        notifyPropertyChanged(BR.cellInfo);
    }

    @Bindable
    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
        notifyPropertyChanged(BR.mcc);
    }

    @Bindable
    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
        notifyPropertyChanged(BR.mnc);
    }

    @Bindable
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
        notifyPropertyChanged(BR.cid);
    }

    @Bindable
    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
        notifyPropertyChanged(BR.lac);
    }

    @Bindable
    public String getSmcc() {
        return String.valueOf(mcc);
    }

    public void setSmcc(String smcc) {
        this.mcc = Integer.valueOf(smcc);
        notifyPropertyChanged(BR.smcc);
    }
    @Bindable
    public String getSmnc() {
        return String.valueOf(mnc);
    }

    public void setSmnc(String smnc) {
        this.mnc = Integer.valueOf(smnc);
        notifyPropertyChanged(BR.smnc);
    }
    @Bindable
    public String getScid() {
        return String.valueOf(cid);
    }

    public void setScid(String scid) {
        this.cid = Integer.valueOf(scid);
        notifyPropertyChanged(BR.scid);
    }
    @Bindable
    public String getSlac() {
        return String.valueOf(lac);
    }

    public void setSlac(String slac) {
        this.lac = Integer.valueOf(slac);
        notifyPropertyChanged(BR.slac);
    }

}

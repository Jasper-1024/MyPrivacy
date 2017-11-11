package com.jasperhale.myprivacy.Activity.model;

import android.content.Intent;

import com.jasperhale.myprivacy.Activity.Base.BaseModel;

/**
 * Created by ZHANG on 2017/11/10.
 */

public class AppSetting extends BaseModel {
    private boolean InstalledApp = false;
    private boolean RunningApp = false;
    private boolean ConnectionWifi = false;
    private String SSID = "1900";
    private String Mac = "A5:A2:6F:35:D0:CF";
    //the network ID, or -1 if there is no currently connected network
    private int NetworkId = -1;

    private boolean CellInfo = false;

    public boolean getInstalledApp() {
        return InstalledApp;
    }
    public void setInstalledApp(boolean bool){
        this.InstalledApp = bool;
    }
    public boolean getRunningApp() {
        return RunningApp;
    }
    public void setRunningApp(boolean bool){
        this.RunningApp = bool;
    }
    public boolean getConnectionWifi() {
        return ConnectionWifi;
    }
    public void setConnectionWifi(boolean bool){
        this.ConnectionWifi = bool;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    public int getNetworkId() {
        return NetworkId;
    }

    public void setNetworkId(int networkId) {
        NetworkId = networkId;
    }

    public boolean getCellInfo() {
        return CellInfo;
    }
    public void setCellInfo(boolean bool){
        this.CellInfo = bool;
    }




}

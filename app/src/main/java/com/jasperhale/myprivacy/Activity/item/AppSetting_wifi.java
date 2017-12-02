package com.jasperhale.myprivacy.Activity.item;

import android.databinding.Bindable;

import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/11/29.
 */

public class AppSetting_wifi extends BaseItem{
    @Override
    public int getViewType() {
        return R.layout.item_wifi;
    }

    private boolean ConnectionWifi = false;
    private boolean WifiScan = false;
    private String SSID = "1900";
    private String Mac = "A5:A2:6F:35:D0:CF";
    //the network ID, or -1 if there is no currently connected network
    private int NetworkId = -1;
    @Bindable
    public boolean getConnectionWifi() {
        return ConnectionWifi;
    }

    public void setConnectionWifi(boolean bool) {
        this.ConnectionWifi = bool;
        notifyPropertyChanged(BR.connectionWifi);
    }
    @Bindable
    public boolean getWifiScan() {
        return WifiScan;
    }

    public void setWifiScan(boolean bool) {
        this.WifiScan = bool;
        notifyPropertyChanged(BR.wifiScan);
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

}

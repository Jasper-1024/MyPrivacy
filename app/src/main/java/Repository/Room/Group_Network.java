package Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import Repository.Room.Base.BaseHookGroup;
import Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;

@SuppressWarnings("WeakerAccess")
public class Group_Network extends BaseHookGroup {

    public String group_Name;
    public boolean value = false;
    public final ObservableArrayList<BindingAdapterItem> items;

    public final ItemHook IA_getLocalHost;
    public final ItemHook NI_getExtraInfo;
    public final ItemHook NI_getTypeName;
    public final ItemHook NIF_getInterfaceAddresses;
    public final ItemHook TM_getAllCellInfo;
    public final ItemHook TM_getCellLocation;
    public final ItemHook TM_getNeighboringCellInfo;
    public final ItemHook TM_listen;
    public final ItemHook WI_getBSSID;
    public final ItemHook WI_getIpAddress;
    public final ItemHook WI_getMacAddress;
    public final ItemHook WI_getSSID;
    public final ItemHook WM_getConfiguredNetworks;
    public final ItemHook WM_getScanResults;


    public Group_Network(String group_Name) {
        this.group_Name = group_Name;
        items = new ObservableArrayList<>();
        IA_getLocalHost = new ItemHook("InetAddress.getLocalHost");
        NI_getExtraInfo = new ItemHook("NetworkInfo.getExtraInfo");
        NI_getTypeName = new ItemHook("NetworkInfo.getTypeName");
        NIF_getInterfaceAddresses = new ItemHook("NetworkInterface.getInterfaceAddresses");
        TM_getAllCellInfo = new ItemHook("TelephonyManager.getAllCellInfo");
        TM_getCellLocation = new ItemHook("TelephonyManager.getCellLocation");
        TM_getNeighboringCellInfo = new ItemHook("TelephonyManager.getNeighboringCellInfo");
        TM_listen = new ItemHook("TelephonyManager.listen");
        WI_getBSSID = new ItemHook("WifiInfo.getBSSID");
        WI_getIpAddress = new ItemHook("WifiInfo.getIpAddress");
        WI_getMacAddress = new ItemHook("WifiInfo.getMacAddress");
        WI_getSSID = new ItemHook("WifiInfo.getSSID");
        WM_getConfiguredNetworks = new ItemHook("WifiManager.getConfiguredNetworks");
        WM_getScanResults = new ItemHook("WifiManager.getScanResults");
    }


    @Bindable
    public String getGroup_Name() {
        return group_Name;
    }

    public void setGroup_Name(String group_Name) {
        this.group_Name = group_Name;
        notifyPropertyChanged(BR.group_Name);
    }

    @Bindable
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }
}

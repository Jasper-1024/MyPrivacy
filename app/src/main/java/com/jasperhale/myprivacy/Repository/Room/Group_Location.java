package com.jasperhale.myprivacy.Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.support.annotation.Keep;

import com.jasperhale.myprivacy.Repository.Room.Base.BaseHookGroup;
import com.jasperhale.myprivacy.Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;

@SuppressWarnings("WeakerAccess")
@Keep
public class Group_Location extends BaseHookGroup {
    public String group_Name;
    public boolean value = false;
    public final ObservableArrayList<BindingAdapterItem> items;

    public final ItemHook L_createFromParcel;
    public final ItemHook LM_addGpsStatusListener;
    public final ItemHook LM_addNmeaListener;
    public final ItemHook LM_addProximityAlert;
    public final ItemHook LM_getBestProvider;
    public final ItemHook LM_getLastLocation;
    public final ItemHook LM_getLastKnownLocation;
    public final ItemHook LM_getProviders;



    public Group_Location(String group_Name) {
        this.group_Name = group_Name;
        items = new ObservableArrayList<>();
        L_createFromParcel = new ItemHook("Location.createFromParcel");
        LM_addGpsStatusListener = new ItemHook("LocationManager.addGpsStatusListener");
        LM_addNmeaListener = new ItemHook("LocationManager.addNmeaListener");
        LM_addProximityAlert = new ItemHook("LocationManager.addProximityAlert");
        LM_getBestProvider = new ItemHook("LocationManager.getBestProvider");
        LM_getLastLocation = new ItemHook("LocationManager.getLastLocation");
        LM_getLastKnownLocation = new ItemHook("LocationManager.getLastKnownLocation");
        LM_getProviders = new ItemHook("LocationManager.getProviders");
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

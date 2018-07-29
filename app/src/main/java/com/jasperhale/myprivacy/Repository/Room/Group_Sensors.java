package com.jasperhale.myprivacy.Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.support.annotation.Keep;

import com.jasperhale.myprivacy.Repository.Room.Base.BaseHookGroup;
import com.jasperhale.myprivacy.Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;

@SuppressWarnings("WeakerAccess")
@Keep
public class Group_Sensors extends BaseHookGroup {

    public String group_Name;
    public boolean value = false;
    public final ObservableArrayList<BindingAdapterItem> items;

    public final ItemHook SM_getDefaultSensor;
    public final ItemHook SM_getDynamicSensorList;
    public final ItemHook SM_getSensorList;
    public final ItemHook SM_getSensors;


    public Group_Sensors(String group_Name) {
        this.group_Name = group_Name;
        items = new ObservableArrayList<>();

        SM_getDefaultSensor = new ItemHook("SensorManager.getDefaultSensor");
        SM_getDynamicSensorList = new ItemHook("SensorManager.getDynamicSensorList");
        SM_getSensorList = new ItemHook("SensorManager.getSensorList");
        SM_getSensors = new ItemHook("SensorManager.getSensors");
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

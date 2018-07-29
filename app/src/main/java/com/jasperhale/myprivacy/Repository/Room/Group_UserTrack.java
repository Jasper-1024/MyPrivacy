package com.jasperhale.myprivacy.Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import com.jasperhale.myprivacy.BR;

import com.jasperhale.myprivacy.Repository.Room.Base.BaseHookGroup;
import com.jasperhale.myprivacy.Ui.Databind.BindingAdapterItem;

public class Group_UserTrack extends BaseHookGroup {
    public String group_Name;
    public boolean value = false;
    public final ObservableArrayList<BindingAdapterItem> items;

    public final ItemHook Build_BOARD;
    public final ItemHook Build_BOOTLOADER;
    public final ItemHook Build_BRAND;
    public final ItemHook Build_DEVICE;
    public final ItemHook Build_DISPLAY;
    public final ItemHook Build_FINGERPRINT;
    public final ItemHook Build_HARDWARE;
    public final ItemHook Build_HOST;
    public final ItemHook Build_ID;
    public final ItemHook Build_MANUFACTURER;
    public final ItemHook Build_MODEL;
    public final ItemHook Build_PRODUCT;
    public final ItemHook Build_RADIO;
    public final ItemHook Build_TAGS;
    public final ItemHook Build_TIME;
    public final ItemHook Build_TYPE;
    public final ItemHook Build_USER;
    public final ItemHook Build_getRadioVersion;


    public Group_UserTrack(String group_Name) {
        this.group_Name = group_Name;
        items = new ObservableArrayList<>();

        Build_BOARD = new ItemHook("Build.BOARD");
        Build_BOOTLOADER = new ItemHook("Build.BOOTLOADER");
        Build_BRAND = new ItemHook("Build.BRAND");
        Build_DEVICE = new ItemHook("Build.DEVICE");
        Build_DISPLAY = new ItemHook("Build.DISPLAY");
        Build_FINGERPRINT = new ItemHook("Build.FINGERPRINT");
        Build_HARDWARE = new ItemHook("Build.HARDWARE");
        Build_HOST = new ItemHook("Build.HOST");
        Build_ID = new ItemHook("Build.ID");
        Build_MANUFACTURER = new ItemHook("Build.MANUFACTURER");
        Build_MODEL = new ItemHook("Build.MODEL");
        Build_PRODUCT = new ItemHook("Build.PRODUCT");
        Build_RADIO = new ItemHook("Build.RADIO");
        Build_TAGS = new ItemHook("Build.TAGS");
        Build_TIME = new ItemHook("Build.TIME");
        Build_TYPE = new ItemHook("Build.TYPE");
        Build_USER = new ItemHook("Build.USER");
        Build_getRadioVersion = new ItemHook("Build.getRadioVersion");
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

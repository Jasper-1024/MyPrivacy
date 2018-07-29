package com.jasperhale.myprivacy.Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.support.annotation.Keep;

import com.jasperhale.myprivacy.Base.LogUtil;
import com.jasperhale.myprivacy.Base.MyApplicantion;
import com.jasperhale.myprivacy.Repository.Room.Base.BaseHook;
import com.jasperhale.myprivacy.Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

@SuppressWarnings("ALL")
@Keep
public class AppHook extends BaseHook {
    public String packageName;
    public ObservableArrayList<BindingAdapterItem> items;
    public final Group_AppLication appLication;
    public final Group_Network network;
    public final Group_Location location;
    //public final Group_Sensors sensors;
    public final Group_UserTrack userTrack;

    public AppHook(String packageName) {
        this.packageName = packageName;
        items = new ObservableArrayList<>();
        appLication = new Group_AppLication(MyApplicantion.getContext().getString(R.string.AppLication));
        network = new Group_Network(MyApplicantion.getContext().getString(R.string.Network));
        location = new Group_Location(MyApplicantion.getContext().getString(R.string.Location));
        //sensors = new Group_Sensors(MyApplicantion.getContext().getString(R.string.Sensors));
        userTrack = new Group_UserTrack(MyApplicantion.getContext().getString(R.string.UserTrack));

    }

    @Override
    public ObservableArrayList<BindingAdapterItem> getitems() {
        if (items == null) {
            items = new ObservableArrayList<>();
        }
        items.clear();
        items.add(appLication);
        items.add(network);
        items.add(location);
        //items.add(sensors);
        items.add(userTrack);
        LogUtil.d("AppHook","getitems");
        return items;
    }

    @Bindable
    public String getPackageName() {
        return packageName;
    }

    @Override
    public void setGroup_Name(String packageName) {
        this.packageName = packageName;
        notifyPropertyChanged(BR.packageName);
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}

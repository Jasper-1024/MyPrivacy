package Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import Repository.Room.Base.BaseHookGroup;
import Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;

@SuppressWarnings("WeakerAccess")
public class Group_Location extends BaseHookGroup {
    public String group_Name;
    public boolean value = false;
    public final ObservableArrayList<BindingAdapterItem> items;

    public final ItemHook L_createFromParcel;
    public final ItemHook LM_addNmeaListener;
    public final ItemHook LM_addProximityAlert;


    public Group_Location(String group_Name) {
        this.group_Name = group_Name;
        items = new ObservableArrayList<>();
        L_createFromParcel = new ItemHook("Location.createFromParcel");
        LM_addNmeaListener = new ItemHook("LocationManager.addNmeaListener");
        LM_addProximityAlert = new ItemHook("LocationManager.addProximityAlert");
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

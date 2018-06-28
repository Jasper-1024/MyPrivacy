package Repository.Room;

import android.databinding.Bindable;

import Base.LogUtil;
import Repository.Room.Base.BaseHookItem;
import com.jasperhale.myprivacy.BR;

@SuppressWarnings("ALL")
public class ItemHook extends BaseHookItem {
    public boolean value = false;
    public String item_name = "default";

    public ItemHook(String item_name) {
        this.item_name = item_name;
    }

    public ItemHook(boolean value) {
        this.value = value;
    }

    @Bindable
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        LogUtil.d("ItemHook",""+ value);
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String name) {
        this.item_name = name;
        notifyPropertyChanged(BR.item_name);
    }
}

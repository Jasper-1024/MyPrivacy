package Repository.Room;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import Base.LogUtil;
import Base.MyApplicantion;
import Repository.Room.Base.BaseHook;
import Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

@SuppressWarnings("ALL")
public class AppHook extends BaseHook {
    public String packageName;
    public ObservableArrayList<BindingAdapterItem> items;
    public final Group_AppLication appLication;
    public final Group_Network network;
    public final Group_Location location;

    public AppHook(String packageName) {
        this.packageName = packageName;
        items = new ObservableArrayList<>();
        appLication = new Group_AppLication(MyApplicantion.getContext().getString(R.string.AppLication));
        network = new Group_Network(MyApplicantion.getContext().getString(R.string.Network));
        location = new Group_Location(MyApplicantion.getContext().getString(R.string.Location));
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

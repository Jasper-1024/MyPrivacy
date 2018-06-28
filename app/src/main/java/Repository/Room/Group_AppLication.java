package Repository.Room;

import android.arch.persistence.room.Entity;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import java.lang.reflect.Field;

import Base.LogUtil;
import Repository.Room.Base.BaseHookGroup;
import Ui.Databind.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;

@SuppressWarnings("WeakerAccess")
@Entity
public class Group_AppLication extends BaseHookGroup {
    public String group_Name;
    public boolean value = false;
    public final ObservableArrayList<BindingAdapterItem> items;

    public final ItemHook AM_getRecentTasks;
    public final ItemHook AM_getRunningAppProcesses;
    public final ItemHook AM_getRunningServices;
    public final ItemHook AM_getRunningTasks;
    public final ItemHook AWM_getInstalledProviders;
    public final ItemHook AWM_getInstalledProvidersForPackage;
    public final ItemHook AWM_getInstalledProvidersForProfile;
    public final ItemHook PM_getInstalledApplications;
    public final ItemHook PM_getInstalledPackages;
    public final ItemHook PM_getInstallerPackageName;
    public final ItemHook PM_getPackagesHoldingPermissions;
    public final ItemHook PM_getPreferredPackages;
    public final ItemHook PM_queryIntentActivities;
    public final ItemHook PM_queryIntentActivityOptions;
    public final ItemHook PM_queryIntentContentProviders;
    public final ItemHook PM_queryIntentServices;

    public Group_AppLication(String group_Name) {
        super();
        this.group_Name = group_Name;
        items = new ObservableArrayList<>();

        AM_getRecentTasks = new ItemHook("ActivityManager.getRecentTasks");
        AM_getRunningAppProcesses = new ItemHook("ActivityManager.getRunningAppProcesses");
        AM_getRunningServices = new ItemHook("ActivityManager.getRunningServices");
        AM_getRunningTasks = new ItemHook("ActivityManager.getRunningTasks");
        AWM_getInstalledProvidersForPackage = new ItemHook("AppWidgetManager.getInstalledProvidersForPackage");
        AWM_getInstalledProviders = new ItemHook("AppWidgetManager.getInstalledProviders");
        AWM_getInstalledProvidersForProfile = new ItemHook("AppWidgetManager.getInstalledProvidersForProfile");
        PM_getInstalledApplications = new ItemHook("PackageManager.getInstalledApplications");
        PM_getInstalledPackages = new ItemHook("PackageManager.getInstalledPackages");
        PM_getInstallerPackageName = new ItemHook("PackageManager.getInstallerPackageName");
        PM_getPackagesHoldingPermissions = new ItemHook("PackageManager.getPackagesHoldingPermissions");
        PM_getPreferredPackages = new ItemHook("PackageManager.getPreferredPackages");
        PM_queryIntentActivities = new ItemHook("PackageManager.queryIntentActivities");
        PM_queryIntentActivityOptions = new ItemHook("PackageManager.queryIntentActivityOptions");
        PM_queryIntentContentProviders = new ItemHook("PackageManager.queryIntentContentProviders");
        PM_queryIntentServices = new ItemHook("PackageManager.queryIntentServices");
    }

    /*
    @Override
    public ObservableArrayList<BindingAdapterItem> getitems() {
        if (items == null) {
            items = new ObservableArrayList<>();
        }
        items.clear();
        items.add(AM_getRunningAppProcesses);
        items.add(AM_getRunningServices);
        items.add(AWM_getInstalledProviders);
        items.add(AWM_getInstalledProvidersForProfile);
        items.add(PM_getInstalledApplications);
        items.add(PM_getInstalledPackages);
        items.add(PM_getInstallerPackageName);
        items.add(PM_getPackagesHoldingPermissions);
        items.add(PM_getPreferredPackages);
        items.add(PM_queryIntentActivities);
        items.add(PM_queryIntentActivityOptions);
        items.add(PM_queryIntentContentProviders);
        items.add(PM_queryIntentServices);
        LogUtil.d("Group_AppLication", "getitems " + items.size());
        return items;
    }*/

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

    public  <T> void initItems(T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        itemHook = new ItemHook(field.getName());
                        LogUtil.d(this.group_Name, "initItems" + field.getName());
                        break;
                    }
                    default:
                        LogUtil.d(this.group_Name, "nomatch" + field.getType().toString());
                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(group_Name, e.toString());
        }
    }
}

package com.jasperhale.myprivacy.Repository.Model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.ObservableArrayList;

import com.jasperhale.myprivacy.Base.LogUtil;
import com.jasperhale.myprivacy.Base.MyApplicantion;
import com.jasperhale.myprivacy.Repository.HookModel.SPTools;
import com.jasperhale.myprivacy.Ui.Item.AppItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;


public class mModel implements Model {
    private final String TAG = "Model";

    private Observable<ObservableArrayList<AppItem>> observable_AppList;

    public mModel() {
        observable_AppList = Observable_AppList_creat();
    }


    public Observable<ObservableArrayList<AppItem>> AppList() {
        if (observable_AppList == null) {
            observable_AppList = Observable_AppList_creat();
        }
        return observable_AppList;
    }

    @Override
    public Boolean isSystemApp(String packageName) {
        try {
            PackageInfo mPackageInfo = MyApplicantion.getContext().getPackageManager().getPackageInfo(packageName, 0);

            return !((mPackageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.d(TAG, "NameNotFoundException");
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public Boolean isLimtedApp(String packageName) {
        return SPTools.getSharedPreferences(packageName);
    }

    private Observable<ObservableArrayList<AppItem>> Observable_AppList_creat() {
        return Observable.create((ObservableOnSubscribe<List<ApplicationInfo>>)
                emitter -> emitter.onNext(MyApplicantion.getContext().getPackageManager().getInstalledApplications(0)))
                //cpu密集
                .observeOn(Schedulers.computation())
                .map(applicationInfo -> {
                    /*创建List<AppInfo>*/
                    PackageManager pm = MyApplicantion.getContext().getPackageManager();
                    List<AppInfo> appInfos = new ArrayList<>();
                    for (ApplicationInfo ai : applicationInfo) {
                        /*属性*/
                        int esetting = pm.getApplicationEnabledSetting(ai.packageName);
                        boolean enabled = (ai.enabled &&
                                (esetting == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT ||
                                        esetting == PackageManager.COMPONENT_ENABLED_STATE_ENABLED));
                        boolean persistent = ((ai.flags & ApplicationInfo.FLAG_PERSISTENT) != 0 ||
                                "android".equals(ai.packageName));
                        boolean system = ((ai.flags &
                                (ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0);

                        AppInfo appInfo = new AppInfo(ai.loadLabel(pm).toString(), ai.processName, ai.icon);
                        appInfo.enabled = enabled;
                        appInfo.persistent = persistent;
                        //appInfo.system = isSystemApp(ai.processName);
                        appInfo.system = system;
                        appInfo.label = (String) pm.getApplicationLabel(ai);
                        appInfos.add(appInfo);
                    }
                    LogUtil.d(TAG, "AppList1" + appInfos.toString());
                    return appInfos;
                })
                .map(appInfos -> {
                    ObservableArrayList<AppItem> items = new ObservableArrayList<>();
                    for (AppInfo ai : appInfos) {
                        items.add(new AppItem(ai));
                    }
                    LogUtil.d(TAG, "AppList1" + items.toString());
                    return items;
                });
    }
}

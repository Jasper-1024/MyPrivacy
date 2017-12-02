package com.jasperhale.myprivacy.Activity.item;

import android.databinding.Bindable;

import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/11/29.
 */

public class AppSetting_appinstall extends BaseItem {
    @Override
    public int getViewType() {
        return R.layout.item_appinstall;
    }

    private boolean InstalledApp = false;
    private boolean RunningApp = false;

    @Bindable
    public boolean getInstalledApp() {
        return InstalledApp;
    }

    public void setInstalledApp(boolean bool) {
        this.InstalledApp = bool;
        notifyPropertyChanged(BR.installedApp);
    }

    @Bindable
    public boolean getRunningApp() {
        return RunningApp;
    }

    public void setRunningApp(boolean bool) {
        this.RunningApp = bool;
        notifyPropertyChanged(BR.runningApp);
    }
}

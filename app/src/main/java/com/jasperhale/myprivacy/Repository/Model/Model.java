package com.jasperhale.myprivacy.Repository.Model;

import android.databinding.ObservableArrayList;

import com.jasperhale.myprivacy.Ui.Item.AppItem;
import io.reactivex.Observable;

public interface Model {

    Observable<ObservableArrayList<AppItem>> AppList();

    Boolean isSystemApp(String packageName);

    Boolean isLimtedApp(String packageName);
}

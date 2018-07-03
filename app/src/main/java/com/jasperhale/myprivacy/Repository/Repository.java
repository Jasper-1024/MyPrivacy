package com.jasperhale.myprivacy.Repository;

import android.databinding.ObservableArrayList;

import com.jasperhale.myprivacy.Ui.Item.AppItem;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public interface Repository {

    Observable<ObservableArrayList<AppItem>> searchAppList(String key);

    BehaviorSubject<ObservableArrayList<AppItem>> getBehaviorSubject();

    void creat();

    void destory();

    void refresh();

    //void onNext();
}

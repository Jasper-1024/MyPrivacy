package com.jasperhale.myprivacy.ViewModel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;


import java.text.Collator;
import java.util.Collections;
import java.util.Locale;

import com.jasperhale.myprivacy.Base.LogUtil;
import com.jasperhale.myprivacy.Base.MyApplicantion;
import com.jasperhale.myprivacy.Repository.HookModel.SPTools;
import com.jasperhale.myprivacy.Repository.MainRepository;
import com.jasperhale.myprivacy.Repository.Model.AppInfo;
import com.jasperhale.myprivacy.Repository.Repository;
import com.jasperhale.myprivacy.Ui.Item.AppItem;
import com.jasperhale.myprivacy.Ui.Item.LoadItem;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.jasperhale.myprivacy.R;


/**
 * Created by ZHANG on 2017/12/31.
 */

public class MainViewModel extends ViewModel implements LifecycleObserver {
    private final String TAG = "MainViewModel";

    /*RcycleView.items*/
    public MutableLiveData<ObservableArrayList<AppItem>> ListItem = new MutableLiveData<>();
    private Observer<ObservableArrayList<AppItem>> observer_ListItem;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable mDisposable;
    private Repository repository;


    public MainViewModel() {
        repository = new MainRepository();
        observer_ListItem = Observer_ListItem_creat();

        ObservableArrayList<AppItem> items = new ObservableArrayList<AppItem>();
        items.add(new LoadItem());
        ListItem.postValue(items);
    }

    public MutableLiveData<ObservableArrayList<AppItem>> getListData() {
        if (ListItem == null) {
            ListItem = new MutableLiveData<>();
            return ListItem;
        }
        return ListItem;
    }

    //activity creat
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void creat() {
        LogUtil.d(TAG, "caeat");
        repository.creat();
        changList();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        LogUtil.d(TAG, "start");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void resume() {
        LogUtil.d(TAG, "resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void pause() {
        LogUtil.d(TAG, "pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        LogUtil.d(TAG, "stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destory() {
        LogUtil.d(TAG, "destory");
        compositeDisposable.clear();
        repository.destory();
    }


    public void RefreshList() {
        repository.refresh();
    }


    public void changList() {
        String key = get_menu_list();
        LogUtil.d(TAG, "changList  " + key);
        //清除已订阅
        compositeDisposable.clear();
        switch (key) {
            case "user":
                Behavior_Observer_user();
                break;
            case "system":
                Behavior_Observer_system();
                break;
            case "limted":
                Behavior_Observer_limted();
                break;
            case "all":
                Behavior_Observer_all();
                break;
            default:
                break;
        }
    }


    private void Behavior_Observer_user() {
        LogUtil.d(TAG, "user");
        repository.getBehaviorSubject()
                .observeOn(Schedulers.computation())
                .map(items -> {
                    LogUtil.d(TAG, "user" + "1");
                    ObservableArrayList<AppItem> items1 = new ObservableArrayList<>();
                    for (AppItem appItem : items) {
                        if (!isSystemapp(appItem.appInfo)) {
                            items1.add(appItem);
                        }
                    }
                    return items1;
                })
                .map(appItems -> {
                    final Collator collator = Collator.getInstance(Locale.getDefault());
                    collator.setStrength(Collator.SECONDARY); // Case insensitive, process accents etc
                    Collections.sort(appItems, (app1, app2) -> collator.compare(app1.appInfo.label, app2.appInfo.label));
                    return appItems;
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_ListItem);
    }

    private void Behavior_Observer_system() {
        LogUtil.d(TAG, "system");
        repository.getBehaviorSubject()
                .observeOn(Schedulers.computation())
                .map(items -> {
                    ObservableArrayList<AppItem> items1 = new ObservableArrayList<>();
                    for (AppItem appItem : items) {
                        if (isSystemapp(appItem.appInfo)) {
                            items1.add(appItem);
                        }
                    }
                    return items1;
                })
                .map(appItems -> {
                    final Collator collator = Collator.getInstance(Locale.getDefault());
                    collator.setStrength(Collator.SECONDARY); // Case insensitive, process accents etc
                    Collections.sort(appItems, (app1, app2) -> collator.compare(app1.appInfo.label, app2.appInfo.label));
                    return appItems;
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_ListItem);
    }

    private void Behavior_Observer_limted() {
        LogUtil.d(TAG, "limted");
        repository.getBehaviorSubject()
                .observeOn(Schedulers.computation())
                .map(items -> {
                    ObservableArrayList<AppItem> items1 = new ObservableArrayList<>();
                    for (AppItem appItem : items) {
                        if (isLimtedapp(appItem.appInfo)) {
                            items1.add(appItem);
                        }
                    }
                    return items1;
                })
                .map(appItems -> {
                    final Collator collator = Collator.getInstance(Locale.getDefault());
                    collator.setStrength(Collator.SECONDARY); // Case insensitive, process accents etc
                    Collections.sort(appItems, (app1, app2) -> collator.compare(app1.appInfo.label, app2.appInfo.label));
                    return appItems;
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_ListItem);
    }

    private void Behavior_Observer_all() {
        LogUtil.d(TAG, "all");
        repository.getBehaviorSubject()
                .observeOn(Schedulers.computation())
                .map(appItems -> {
                    final Collator collator = Collator.getInstance(Locale.getDefault());
                    collator.setStrength(Collator.SECONDARY); // Case insensitive, process accents etc
                    Collections.sort(appItems, (app1, app2) -> collator.compare(app1.appInfo.label, app2.appInfo.label));
                    return appItems;
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_ListItem);
    }

    /*获取键值*/
    private String get_menu_list() {
        Context context = MyApplicantion.getContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.menu_list), Context.MODE_PRIVATE);
        return sharedPref.getString(context.getString(R.string.menu_list_key), context.getString(R.string.menu_list_default));
    }

    /*系统APP?*/
    private boolean isSystemapp(AppInfo appInfo) {
        return appInfo.system;
    }
    /*限制App*/
    private boolean isLimtedapp(AppInfo appInfo){
        return SPTools.getSharedPreferences(appInfo.packageName);
    }

    /*创建Observer*/
    private Observer<ObservableArrayList<AppItem>> Observer_ListItem_creat() {
        return new Observer<ObservableArrayList<AppItem>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                compositeDisposable.add(mDisposable);
            }

            @Override
            public void onNext(ObservableArrayList<AppItem> items) {
                /*未解除绑定*/
                if (!mDisposable.isDisposed()) {
                    LogUtil.d(TAG, "appItemsList" + items.toString());
                    ListItem.postValue(items);
                    //ListItem.setValue(items);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}

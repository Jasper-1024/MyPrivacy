package com.jasperhale.myprivacy.Repository;

import android.databinding.ObservableArrayList;

import com.jasperhale.myprivacy.Base.LogUtil;

import com.jasperhale.myprivacy.Repository.Model.Model;
import com.jasperhale.myprivacy.Repository.Model.mModel;
import com.jasperhale.myprivacy.Ui.Item.AppItem;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by ZHANG on 2017/12/31.
 */

public class MainRepository implements Repository {
    @SuppressWarnings("FieldCanBeLocal")
    private final String TAG = "MainRepository";
    private Model model;

    private BehaviorSubject<ObservableArrayList<AppItem>> behaviorSubject;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable mDisposable;

    public MainRepository() {
        model = new mModel();
        behaviorSubject = behaviorSubject_creat();
    }


    @Override
    public Observable<ObservableArrayList<AppItem>> searchAppList(String key) {
        return null;
    }

    @Override
    public BehaviorSubject<ObservableArrayList<AppItem>> getBehaviorSubject() {
        if (behaviorSubject == null) {
            behaviorSubject = behaviorSubject_creat();
        }
        return behaviorSubject;
    }

    @Override
    public void creat() {
        model.AppList().subscribe(getBehaviorSubject());
    }

    @Override
    public void destory() {
        /*解除全部绑定*/
        compositeDisposable.clear();
        //getBehaviorSubject().onComplete();
    }

    @Override
    public void refresh() {
        LogUtil.d(TAG, "refresh" + "");
        model.AppList().subscribe(behaviorSubject);
    }


    private BehaviorSubject<ObservableArrayList<AppItem>> behaviorSubject_creat() {
        return BehaviorSubject.create();
    }
}


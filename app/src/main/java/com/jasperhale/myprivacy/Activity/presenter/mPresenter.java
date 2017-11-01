package com.jasperhale.myprivacy.Activity.presenter;

import android.content.pm.PackageInfo;
import android.util.Log;

import com.jasperhale.myprivacy.Activity.ViewModel.ViewModel;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.model.Model;
import com.jasperhale.myprivacy.Activity.model.mModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;


/**
 * Created by ZHANG on 2017/11/1.
 */

public class mPresenter implements Presenter {
    private Model model;
    private ViewModel viewModel;

    public mPresenter(ViewModel viewModel) {
        this.viewModel = viewModel;
        model = new mModel();
    }

    @Override
    public void RefreshView() {
        Log.d("log", "onCreate: presenter");
        Observable
                .create(new ObservableOnSubscribe<List<PackageInfo>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<PackageInfo>> emitter) throws Exception {
                                emitter.onNext(model.getPackages());
                            }
                        }
                )
                //新线程
                .subscribeOn(Schedulers.newThread())
                //io密集
                .observeOn(Schedulers.io())
                .map(t -> {
                    List<BindingAdapterItem> items = new ArrayList<>();
                    for (PackageInfo pac : t) {
                        items.add(model.creatApplistItem(pac));
                    }
                    return items;
                })
                //主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d(TAG, "RefreshView: "+ s.get(25).getViewType());
                    viewModel.setItems(s);
                    viewModel.RefreshRecycleView();
                });


    }
}

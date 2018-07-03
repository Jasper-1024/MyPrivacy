package com.jasperhale.myprivacy.Repository.HookModel;

import com.jasperhale.myprivacy.Repository.Room.AppHook;

import io.reactivex.Observable;

public interface HookModel {
    Observable<AppHook> getAppHook(String packageName);
    void setAppHook(AppHook appHook);
}

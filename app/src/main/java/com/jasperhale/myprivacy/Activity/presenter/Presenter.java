package com.jasperhale.myprivacy.Activity.presenter;

/**
 * Created by ZHANG on 2017/10/31.
 */

public interface Presenter {
    void RefreshView();
    void Refresh_User();
    void Refresh_System();
    void Refresh_Limted();
    void SeaechView(String query,int position);
}

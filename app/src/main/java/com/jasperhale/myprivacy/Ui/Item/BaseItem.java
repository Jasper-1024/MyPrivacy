package com.jasperhale.myprivacy.Ui.Item;

import android.databinding.BaseObservable;
import android.view.View;

import com.jasperhale.myprivacy.Ui.Databind.BindingAdapterItem;


/**
 * Created by ZHANG on 2017/10/29.
 */

public abstract class BaseItem extends BaseObservable implements BindingAdapterItem {

    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public int getHash() {
        return 0;
    }

    @Override
    public String getSearchkey() {
        return "";
    }
}

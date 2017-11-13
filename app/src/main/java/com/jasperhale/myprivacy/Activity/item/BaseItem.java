package com.jasperhale.myprivacy.Activity.item;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * Created by ZHANG on 2017/10/29.
 */

public abstract class BaseItem extends BaseObservable {
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

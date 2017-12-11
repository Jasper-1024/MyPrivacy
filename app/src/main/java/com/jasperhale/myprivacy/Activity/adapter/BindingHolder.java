package com.jasperhale.myprivacy.Activity.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.jasperhale.myprivacy.BR;

/**
 * Created by ZHANG on 2017/11/26.
 */

public class BindingHolder  extends RecyclerView.ViewHolder {

    ViewDataBinding binding;

    public BindingHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindData(BindingAdapterItem item) {
        binding.setVariable(BR.item, item);
    }

}

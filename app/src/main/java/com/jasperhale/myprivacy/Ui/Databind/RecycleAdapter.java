package com.jasperhale.myprivacy.Ui.Databind;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.jasperhale.myprivacy.Base.LogUtil;

import java.util.Objects;

public class RecycleAdapter extends RecyclerView.Adapter<BindingHolder> implements Filterable {

    private final String TAG = "BindAdapter_applist";
    private ObservableArrayList<BindingAdapterItem> items;
    private ObservableArrayList<BindingAdapterItem> items_back = new ObservableArrayList<>();


    public RecycleAdapter(@Nullable ObservableArrayList<BindingAdapterItem> items) {
        super();
        this.items = items;
    }

    public RecycleAdapter() {
        super();
        this.items = new ObservableArrayList<>();
    }

    //获取ObservableList<BindingAdapterItem> 实例
    public ObservableArrayList<BindingAdapterItem> getItems() {
        return items;
    }

    //显示list<item>
    public void setItems(@Nullable ObservableArrayList<BindingAdapterItem> items) {
        this.items = items;
        items_back.clear();
        items_back.addAll(Objects.requireNonNull(items));
        /*
        this.items.clear();
        this.items.addAll(items);
        */
    }

    //清除item
    public void clearItems() {
        items.clear();
    }

    /*创建ViewHolder*/
    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BindingHolder(binding);
    }

    /*数据绑定*/
    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    /*返回长度*/
    @Override
    public int getItemCount() {
        return items.size();
    }

    /*返回类型*/
    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence query) {

                ObservableArrayList<BindingAdapterItem> results = new ObservableArrayList<>();
                /*为空?*/
                if (TextUtils.isEmpty(query)) {
                    results.clear();
                    results.addAll(items_back);
                } else {
                    results.clear();
                    /*小写 去掉两端空白字符*/
                    String q = query.toString().toLowerCase().trim();
                    LogUtil.d(TAG, "performFiltering  " + q);
                    //String q = query.toString().toLowerCase();
                    for (BindingAdapterItem app : items_back) {

                        if (app.getSearchkey().toLowerCase().contains(q)) {
                            LogUtil.d(TAG, "performFiltering  " + app.getSearchkey().toLowerCase());
                            results.add(app);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = results;
                filterResults.count = results.size();
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence query, FilterResults result) {
                final ObservableArrayList<BindingAdapterItem> apps = (result.values == null
                        ? new ObservableArrayList<>()
                        : (ObservableArrayList<BindingAdapterItem>) result.values);
                /*
                DiffUtil.DiffResult diff =
                        DiffUtil.calculateDiff(new DiffCallBack(getItems(), apps), true);
                diff.dispatchUpdatesTo(RecycleAdapter.this);*/
                items = apps;
                RecycleAdapter.this.notifyDataSetChanged();
            }
        };
    }

}

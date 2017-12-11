package com.jasperhale.myprivacy.Activity.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZHANG on 2017/12/10.
 */

public class BindAdapter_applist extends RecyclerView.Adapter<BindingHolder> {

    private List<ApplistItem> items = new ArrayList<>();
    private List<ApplistItem> items_backup = new ArrayList<>();

    public ApplistItem getItem(int position) {
        return items.get(position);
    }
    //获取List<BindingAdapterItem> 实例
    public List<ApplistItem> getItems() {
        return items;
    }

    public void setItems_backup() {
        items_backup.clear();
        Observable
                .create((ObservableOnSubscribe<List<ApplistItem>>)
                        emitter -> emitter.onNext(items)
                )
                //cpu密集
                .observeOn(Schedulers.computation())
                .subscribe(items -> items_backup.addAll(items));
    }
    public void setItems_backup( List<ApplistItem> items) {
        items_backup.clear();
        Observable
                .create((ObservableOnSubscribe<List<ApplistItem>>)
                        emitter -> emitter.onNext(items)
                )
                //cpu密集 排序
                .observeOn(Schedulers.computation())
                .subscribe(itemsp -> items_backup.addAll(itemsp));
    }


    public List<ApplistItem> getItems_backup() {
        return items_backup;
    }

    //显示list<item>
    public void setItems(List<ApplistItem> items) {
        clearItems();
        addItems(items);
    }

    //显示单个item
    public void setItem(ApplistItem item) {
        clearItems();
        addItem(item);
    }

    //添加item
    public void addItem(ApplistItem item) {
        items.add(item);
    }

    //指定位置添加item
    public void addItem(ApplistItem item, int position) {
        items.add(position, item);
    }

    //添加list<item>
    public void addItems(List<ApplistItem> items) {
        this.items.addAll(items);
    }

    //替换item
    public void replaceItem(ApplistItem item, int position) {
        items.set(position, item);
    }

    //移除item
    public void removeItem(ApplistItem item) {
        items.remove(item);
    }

    //清除item
    public void clearItems() {
        items.clear();
    }


    /**
     * @return 返回的是adapter的view
     */
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BindingHolder(binding);
    }

    /*
    * 数据绑定
    * */
    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindData(items.get(position));
    }



    @Override
    public void onBindViewHolder(BindingHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            LogUtil.d("Adatper", "");
            for (String key : payload.keySet()) {
                switch (key) {
                    case "AppId":
                        items.get(position).setAppId(payload.getString("AppId"));
                        break;
                    case "AppName":
                        items.get(position).setAppName(payload.getString("AppName"));
                        break;
                    case "AppIcon":
                        Bitmap bitmap = (Bitmap)payload.getParcelable("AppIcon");
                        items.get(position).setAppIcon(new BitmapDrawable(MyApplicantion.getContext().getResources(),bitmap));
                        break;
                    default:
                        break;
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }
}

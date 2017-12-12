package com.jasperhale.myprivacy.Activity.item;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;


import java.util.List;

/**
 * Created by ZHANG on 2017/11/26.
 */

public class DiffCallBack_ApplistItem extends DiffUtil.Callback{
    private List<ApplistItem> mOldDatas, mNewDatas;//看名字

    public DiffCallBack_ApplistItem(List<ApplistItem> mOldDatas, List<ApplistItem> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    //老数据集size
    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    //新数据集size
    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * For example, if your items have unique ids, this method should check their id equality.
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等。
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getAppId().equals(mNewDatas.get(newItemPosition).getAppId());
    }

    /**
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     * DiffUtil 用这个方法替代equals方法去检查是否相等。
     * 所以你可以根据你的UI去改变它的返回值
     * 例如，如果你用RecyclerView.Adapter 配合DiffUtil使用，你需要返回Item的视觉表现是否相同。
     * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getAppId().equals(mNewDatas.get(newItemPosition).getAppId());
    }

    /**
     * When {@link #areItemsTheSame(int, int)} returns {@code true} for two items and
     * {@link #areContentsTheSame(int, int)} returns false for them, DiffUtil
     * calls this method to get a payload about the change.
     *
     * 当{@link #areItemsTheSame(int, int)} 返回true，且{@link #areContentsTheSame(int, int)} 返回false时，DiffUtils会回调此方法，
     * 去得到这个Item（有哪些）改变的payload。
     *
     * For example, if you are using DiffUtil with , you can return the
     * particular field that changed in the item and your
     * {@link android.support.v7.widget.RecyclerView.ItemAnimator ItemAnimator} can use that
     * information to run the correct animation.
     *
     * 例如，如果你用RecyclerView配合DiffUtils，你可以返回  这个Item改变的那些字段，
     * {@link android.support.v7.widget.RecyclerView.ItemAnimator ItemAnimator} 可以用那些信息去执行正确的动画
     *
     * Default implementation returns {@code null}.\
     * 默认的实现是返回null
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return A payload object that represents the change between the two items.
     * 返回 一个 代表着新老item的改变内容的 payload对象，
     */

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画
        ApplistItem oldBean = mOldDatas.get(oldItemPosition);
        ApplistItem newBean = mNewDatas.get(newItemPosition);

        //传回变化部分
        Bundle payload = new Bundle();
        /*
        if (!oldBean.getAppId().equals(newBean.getAppId())) {
            payload.putParcelable("ApplistItem",newBean);
        }*/
        if (!oldBean.getAppId().equals(newBean.getAppId())) {
            payload.putString("AppId", newBean.getAppId());
        }
        if (!oldBean.getAppName().equals(newBean.getAppName())) {
            payload.putString("AppName", newBean.getAppName());
        }
        if (!oldBean.getAppIcon().equals(newBean.getAppIcon())) {
            Bitmap bitmap = ((BitmapDrawable) newBean.getAppIcon()).getBitmap();
            payload.putParcelable("AppIcon", bitmap);
        }
        /*
        if (!oldBean.getAppId().equals(newBean.getAppId())) {
            payload.putString("AppId", newBean.getAppId());
        }*/

        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;
    }

}

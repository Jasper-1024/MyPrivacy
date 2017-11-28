package com.jasperhale.myprivacy.Activity.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;


import java.util.List;

/**
 * Created by ZHANG on 2017/11/26.
 */

public class DiffCallBack_ApplistItem extends DiffUtil.Callback{
    private List<BindingAdapterItem> mOldDatas, mNewDatas;//看名字

    public DiffCallBack_ApplistItem(List<BindingAdapterItem> mOldDatas, List<BindingAdapterItem> mNewDatas) {
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
     * 本例判断name字段是否一致
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ApplistItem beanOld = (ApplistItem)mOldDatas.get(oldItemPosition);
        ApplistItem beanNew = (ApplistItem)mNewDatas.get(newItemPosition);
        return beanOld.getAppId().equals(beanNew.getAppId());
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
        ApplistItem beanOld = (ApplistItem) mOldDatas.get(oldItemPosition);
        ApplistItem beanNew = (ApplistItem) mNewDatas.get(newItemPosition);
        //如果有内容不同，就返回false
        return beanOld.getAppName().equals(beanNew.getAppName()) && beanOld.getAppId() == beanNew.getAppId();
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
        //实现这个方法 就能成为文艺青年中的文艺青年
        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
        ApplistItem oldBean = (ApplistItem)mOldDatas.get(oldItemPosition);
        ApplistItem newBean = (ApplistItem)mNewDatas.get(newItemPosition);

        Bundle payload = new Bundle();
        if (!oldBean.getAppId().equals(newBean.getAppId())) {
            payload.putParcelable("ApplistItem",newBean);
        }

        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;//
    }
}

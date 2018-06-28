package Ui.Databind;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import Base.LogUtil;
import Repository.Model.AppInfo;
import Repository.Room.Base.BaseHookGroup;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Viewbind {
    private final static String TAG = "Viewbind";

    @BindingAdapter("loadIcon")
    public static void LoadIcon(ImageView imageView, AppInfo appInfo) {

        RequestOptions options = new RequestOptions()
                .error(android.R.drawable.sym_def_app_icon)
                .placeholder(android.R.drawable.sym_def_app_icon);

        Uri uri = Uri.parse("android.resource://" + appInfo.packageName + "/" + appInfo.icon);
        Glide.with(imageView.getContext())
                .applyDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("binditems")
    public static void bindList(RecyclerView recyclerView, ObservableArrayList items) {


        RecycleAdapter adapter = (RecycleAdapter) recyclerView.getAdapter();

        if (adapter != null) {
            if ((null != adapter.getItems()) && (null != items)) {
                LogUtil.d(TAG, "binditems" + "1");

                /*
                Observable
                        .create((ObservableOnSubscribe<DiffUtil.DiffResult>)
                                emitter -> emitter.onNext(DiffUtil.calculateDiff(new DiffCallBack(adapter.getItems(), items), true))
                        )
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(diffResult -> {
                            LogUtil.d(TAG, "binditems"+"2");
                            adapter.setItems(items);
                            diffResult.dispatchUpdatesTo(adapter);
                        });
                */

                Observable
                        .create((ObservableOnSubscribe<String>)
                                emitter -> emitter.onNext("")
                        )
                        //.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(string -> {
                            adapter.setItems(items);
                            LogUtil.d(TAG, "binditems" + "2");
                            adapter.notifyDataSetChanged();
                        });
            }
        } else {
            LogUtil.d(TAG, "binditems" + "3");
            if (items == null) {
                recyclerView.setAdapter(new RecycleAdapter(new ObservableArrayList<>()));
            } else {
                recyclerView.setAdapter(new RecycleAdapter(items));
            }
            //recyclerView.setAdapter(new RecycleAdapter(new ObservableArrayList<>()));
            /*
            Observable
                    .create((ObservableOnSubscribe<String>)
                            emitter -> emitter.onNext("")
                    )
                    //.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(string -> {
                        adapter.setItems(items);
                        LogUtil.d(TAG, "binditems" + "2");
                        adapter.notifyDataSetChanged();
                    });*/

        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("bindHookitems")
    public static void bindHookList(RecyclerView recyclerView, BaseHookGroup baseHookGroup) {
        ObservableArrayList items;

        RecycleAdapter adapter = (RecycleAdapter) recyclerView.getAdapter();
        if (baseHookGroup != null) {
            LogUtil.d(TAG, "bindHookitems" + "getitems");
            items = baseHookGroup.getitems();
        } else {
            LogUtil.d(TAG, "bindHookitems" + "null");
            items = new ObservableArrayList<BindingAdapterItem>();
        }

        if (adapter != null) {
            if ((null != adapter.getItems()) && (null != items)) {
                LogUtil.d(TAG, "bindHookitems" + "1");

                /*
                Observable
                        .create((ObservableOnSubscribe<DiffUtil.DiffResult>)
                                emitter -> emitter.onNext(DiffUtil.calculateDiff(new DiffCallBack(adapter.getItems(), items), true))
                        )
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(diffResult -> {
                            LogUtil.d(TAG, "binditems"+"2");
                            adapter.setItems(items);
                            diffResult.dispatchUpdatesTo(adapter);
                        });
                */
                Observable
                        .create((ObservableOnSubscribe<String>)
                                emitter -> emitter.onNext("")
                        )
                        //.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(string -> {
                            adapter.setItems(items);
                            LogUtil.d(TAG, "bindHookitems" + "2");
                            adapter.notifyDataSetChanged();
                        });
            }
        } else {
            LogUtil.d(TAG, "bindHookitems" + "3");
            if (items == null) {
                recyclerView.setAdapter(new RecycleAdapter(new ObservableArrayList<>()));
            } else {
                recyclerView.setAdapter(new RecycleAdapter(items));
            }
        }
    }
}

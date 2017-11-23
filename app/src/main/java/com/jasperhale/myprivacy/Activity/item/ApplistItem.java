package com.jasperhale.myprivacy.Activity.item;


import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;



import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.tinypinyin.lexicons.android.cncity.CnCityDict;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.View.AppSettingActivity;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static java.io.File.separator;

/**
 * Created by ZHANG on 2017/10/29.
 */

public class ApplistItem extends BaseItem implements BindingAdapterItem ,Comparable<ApplistItem>{

    @Override
    public int getViewType() {
        return R.layout.item_applist;
    }

    public ApplistItem(String AppId, String AppName, Drawable AppIcon) {
        this.AppId = AppId;
        this.AppName = AppName;
        this.AppIcon = AppIcon;
        this.AppName_compare = ApplistItem.transformPinYin(AppName);


        setOnClickListener(view -> {
            Intent intent = new Intent(MyApplicantion.getContext(), AppSettingActivity.class);
            intent.putExtra("PackageName", AppId);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            switch (view.getId()) {
                case R.id.ApplistIcon:
                    Log.d(TAG, "onClick: " + AppId);
                    MyApplicantion.getContext().startActivity(intent);
                    break;
                case R.id.ApplistPackageName:
                    MyApplicantion.getContext().startActivity(intent);
                    break;
            }
        });
    }

    private String AppId;
    private String AppName;
    private String AppName_compare;
    private Drawable AppIcon;

    @Bindable
    public String getAppId() {
        return AppId;
    }

    public void setAppId(String AppId) {
        this.AppId = AppId;
        notifyPropertyChanged(BR.appId);
    }

    @Bindable
    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
        notifyPropertyChanged(BR.appName);
    }


    @Bindable
    public Drawable getAppIcon() {
        AppIcon.setBounds(0, 0, 5, 5);
        return AppIcon;
    }

    public void setAppIcon(Drawable AppIcon) {
        this.AppIcon = AppIcon;
        notifyPropertyChanged(BR.appIcon);
    }

    private static String transformPinYin(String character) {
        String vp ;
        vp = character.toUpperCase();

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < vp.length(); i++) {
            buffer.append(Pinyin.toPinyin(vp.charAt(i)));
        }
        return buffer.toString();

    }

    @Override
    public int compareTo(@NonNull ApplistItem another) {
        return this.AppName_compare.compareTo(another.AppName_compare);
    }

    @BindingAdapter("loadAppIcon")
    public static void LoadDrawable(ImageView imageView, Drawable Icon) {
        Context context = imageView.getContext();
        //BitmapDrawable bd = (BitmapDrawable)Icon;
        //Bitmap bitmap = bd.getBitmap();

        RequestOptions options = new RequestOptions()
                .error(Icon)
                .placeholder(Icon);

        Glide.with(context)
                .load("")
                .apply(options)
                .into(imageView);
    }
}

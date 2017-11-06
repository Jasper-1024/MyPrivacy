package com.jasperhale.myprivacy.Activity.item;


import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/10/29.
 */

public class ApplistItem extends BaseItem implements BindingAdapterItem{

    @Override
    public int getViewType(){
        return R.layout.item_applist;
    }

    public ApplistItem(String AppId,Drawable AppIcon){
        this.AppId = AppId;
        this.AppIcon = AppIcon;
    }

    private String AppId;
    private Drawable AppIcon;

    @Bindable
    public String getAppId(){
        return AppId;
    }

    public void setAppId(String AppId){
        this.AppId = AppId;
        notifyPropertyChanged(BR.appId);
    }

    @Bindable
    public Drawable getAppIcon(){
        AppIcon.setBounds(0,0,5,5);
        return AppIcon;
    }
    public void setAppIcon(Drawable AppIcon){
        this.AppIcon = AppIcon;
        notifyPropertyChanged(BR.appIcon);
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

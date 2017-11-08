package com.jasperhale.myprivacy.Activity.item;


import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.View.AppSettingActivity;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

import static android.content.ContentValues.TAG;

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

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplicantion.getContext(), AppSettingActivity.class);
                intent.putExtra("PackageName",AppId);
                switch (view.getId()) {
                    case R.id.ApplistIcon:
                        Log.d(TAG, "onClick: "+AppId);
                        MyApplicantion.getContext().startActivity(intent);
                        break;
                    case R.id.ApplistPackageName:
                        MyApplicantion.getContext().startActivity(intent);
                        break;
                }
            }
        });
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

    public void set(){
        Intent intent = new Intent(MyApplicantion.getContext(), AppSettingActivity.class);
        intent.putExtra("PackageName",AppId);
        MyApplicantion.getContext().startActivity(intent);
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

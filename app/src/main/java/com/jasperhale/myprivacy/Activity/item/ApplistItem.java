package com.jasperhale.myprivacy.Activity.item;


import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;



import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.View.AppSettingActivity;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;



import static android.content.ContentValues.TAG;


/**
 * Created by ZHANG on 2017/10/29.
 */

public class ApplistItem extends BaseItem implements Comparable<ApplistItem>,Parcelable {

    @Override
    public int getViewType() {
        return R.layout.item_applist;
    }

    public ApplistItem(String AppId, String AppName, Drawable AppIcon) {
        this.AppId = AppId;
        this.AppName = AppName;
        this.AppIcon = AppIcon;
        this.AppName_compare = MyApplicantion.transformPinYin(AppName);


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
    public ApplistItem(){}

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

    public String getAppName_compare(){
        return AppName_compare;
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

    @Override
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        BitmapDrawable bd =  (BitmapDrawable) AppIcon;
        dest.writeString(AppId);
        dest.writeString(AppName);
        bd.getBitmap().writeToParcel(dest,0);
    }

    //在实现上面的接口方法后，接下来还需要执行反序列化，定义一个变量，并重新定义其中的部分方法
    public static final Parcelable.Creator< ApplistItem> CREATOR = new Parcelable.Creator<ApplistItem>(){

        @Override
        public  ApplistItem createFromParcel(Parcel source) { //在这个方法中反序列化上面的序列化内容，最后根据反序列化得到的各个属性，得到之前试图传递的对象
            //反序列化的属性的顺序必须和之前写入的顺序一致
            ApplistItem item = new  ApplistItem();
            item.AppId = source.readString();
            item.AppName = source.readString();
            item.AppIcon = new BitmapDrawable(Bitmap.CREATOR.createFromParcel(source));
            return item;
        }

        @Override
        public  ApplistItem[] newArray(int size) {
            return new  ApplistItem[size];                     //一般返回一个数量为size的传递的类的数组就可以了
        }
    };
}

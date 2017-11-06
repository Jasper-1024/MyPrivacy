package com.jasperhale.myprivacy.Activity.model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.adapter.BindingAdapterItem;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.List;

/**
 * Created by ZHANG on 2017/10/31.
 */

public class mModel implements Model {
    @Override
    public List<PackageInfo> getPackages() {
        return MyApplicantion.getContext().getPackageManager().getInstalledPackages(0);
    }

    @Override
    public BindingAdapterItem creatApplistItem(PackageInfo packageInfo) {
        ApplicationInfo appInfo = packageInfo.applicationInfo;
        return new ApplistItem(appInfo.packageName,
               appInfo.loadIcon(MyApplicantion.getContext().getPackageManager()));
    }

    /*

    public Drawable getDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height= drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable); // drawable 转换成 bitmap
        Matrix matrix = new Matrix();   // 创建操作图片用的 Matrix 对象
        float scaleWidth = ((float)w / width);   // 计算缩放比例
        float scaleHeight = ((float)h / height);
        matrix.postScale(scaleWidth, scaleHeight);         // 设置缩放比例
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);       // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
        return new BitmapDrawable(newbmp);       // 把 bitmap 转换成 drawable 并返回
    }

    //drawable 转换成 bitmap
    static Bitmap drawableToBitmap(Drawable drawable)
    {
        int width = drawable.getIntrinsicWidth();   // 取 drawable 的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;         // 取 drawable 的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);     // 建立对应 bitmap
        Canvas canvas = new Canvas(bitmap);         // 建立对应 bitmap 的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);      // 把 drawable 内容画到画布中
        return bitmap;
    }*/
}

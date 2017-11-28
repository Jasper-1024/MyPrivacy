package com.jasperhale.myprivacy.Activity.Base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.github.promeg.pinyinhelper.Pinyin;

/**
 * Created by ZHANG on 2017/10/31.
 */

public class MyApplicantion extends Application{
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }
    public static Context getContext(){
        return context;
    }
    public static String transformPinYin(String character) {
        String vp ;
        vp = character.toUpperCase();

        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < vp.length(); i++) {
            buffer.append(Pinyin.toPinyin(vp.charAt(i)));
        }
        return buffer.toString();
    }
}

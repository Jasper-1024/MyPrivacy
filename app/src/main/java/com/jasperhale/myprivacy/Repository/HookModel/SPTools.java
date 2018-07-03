package com.jasperhale.myprivacy.Repository.HookModel;

import android.content.Context;
import android.content.SharedPreferences;

import com.jasperhale.myprivacy.Base.LogUtil;
import com.jasperhale.myprivacy.Base.MyApplicantion;

public class SPTools {
    private static final SharedPreferences preferences;
    private static final SharedPreferences.Editor editor;
    private static final String TAG = "SPTools";

    static {
        preferences = MyApplicantion.getContext().getSharedPreferences("MyPrivacy", Context.MODE_WORLD_READABLE);
        editor = preferences.edit();
    }
    //设置普通键值
    public static void setSharedPreferences(String key, boolean value) {
        //LogUtil.d(TAG,key);
        editor.putBoolean(key, value).commit();
    }

    public static void setSharedPreferences(String key, String value) {
        editor.putString(key, value).commit();
    }

    public static void setSharedPreferences(String key, int value) {
        editor.putInt(key, value).commit();
    }

    //获取普通键值
    public static boolean getSharedPreferences(String key) {
        return preferences.getBoolean(key, false);
    }

    public static boolean getSharedPreferences(String key, boolean bool) {
        //LogUtil.d(TAG,key);
        return preferences.getBoolean(key, bool);
    }

    public static String getSharedPreferences(String key, String string) {
        return preferences.getString(key, string);
    }

    public static int getSharedPreferences(String key, int i) {
        return preferences.getInt(key, i);
    }


}

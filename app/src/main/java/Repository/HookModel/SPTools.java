package Repository.HookModel;

import android.content.Context;
import android.content.SharedPreferences;

import Base.LogUtil;
import Base.MyApplicantion;

class SPTools {
    private static final SharedPreferences preferences;
    private static final SharedPreferences.Editor editor;
    private static final String TAG = "SPTools";

    static {
        preferences = MyApplicantion.getContext().getSharedPreferences("MyPrivacy", Context.MODE_WORLD_READABLE);
        editor = preferences.edit();
    }
    //设置普通键值
    static void setSharedPreferences(String key, boolean value) {
        LogUtil.d(TAG,key);
        editor.putBoolean(key, value).commit();
    }

    static void setSharedPreferences(String key, String value) {
        editor.putString(key, value).commit();
    }

    static void setSharedPreferences(String key, int value) {
        editor.putInt(key, value).commit();
    }

    //获取普通键值
    static boolean getSharedPreferences(String key) {
        return preferences.getBoolean(key, false);
    }

    static boolean getSharedPreferences(String key, boolean bool) {
        return preferences.getBoolean(key, bool);
    }

    static String getSharedPreferences(String key, String string) {
        return preferences.getString(key, string);
    }

    static int getSharedPreferences(String key, int i) {
        return preferences.getInt(key, i);
    }


}

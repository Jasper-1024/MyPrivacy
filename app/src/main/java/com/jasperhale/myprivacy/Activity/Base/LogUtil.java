package com.jasperhale.myprivacy.Activity.Base;

import android.util.Log;

/**
 * Created by ZHANG on 2017/11/12.
 */

public class LogUtil {
    private static final int VERBOSE = 1;//啰嗦，等级最低的
    private static final int DEBUG = 2;//调试
    private static final int INFO = 3;//信息
    private static final int WARN = 4;//警告
    private static final int ERROR = 5;//错误
    private static final int NOTHING = 6;//什么也不打印出来
    private static final int level = DEBUG;//LEVEL:标准

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {//如果大于或者等于定义的标准就打印出来
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag, msg);
        }
    }
}

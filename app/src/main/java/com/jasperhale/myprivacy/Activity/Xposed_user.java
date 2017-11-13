package com.jasperhale.myprivacy.Activity;

/**
 * Created by ZHANG on 2017/11/13.
 */

public class Xposed_user {
    public static final String APP_NAME = "MyPrivacy";
    public static final String PACKAGE_NAME = Xposed_user .class.getPackage().getName();
    public static final String PREFS_NAME = PACKAGE_NAME + "_preferences";
    public static final String INTENT_BETTER_BATTERY_SAVER_START = "com.pyler.betterbatterysaver.START";
    public static final String INTENT_BETTER_BATTERY_SAVER_STOP = "com.pyler.betterbatterysaver.STOP";

    public static final String NOTIFICATION_LIGHT_PULSE = "notification_light_pulse";
}

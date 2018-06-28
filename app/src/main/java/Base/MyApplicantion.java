package Base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


/**
 * Created by ZHANG on 2017/10/31.
 */

public class MyApplicantion extends Application {
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
}

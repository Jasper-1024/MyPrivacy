package Repository.HookModel;

import java.lang.reflect.Field;

import Base.LogUtil;
import Repository.Room.AppHook;
import Repository.Room.ItemHook;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class mHookModel implements HookModel {
    private final String TAG = "HookModel";

    @Override
    public Observable<AppHook> getAppHook(String packageName) {
        return Observable.create((ObservableOnSubscribe<String>)
                emitter -> emitter.onNext(packageName))
                //io密集
                .observeOn(Schedulers.io())
                .map(packageNames -> {
                    AppHook appHook = new AppHook(packageNames);
                    get(appHook.packageName, appHook.appLication);
                    get(appHook.packageName, appHook.network);
                    get(appHook.packageName, appHook.location);

                    appHook.appLication.value = getValue(appHook.appLication);
                    appHook.network.value = getValue(appHook.network);
                    appHook.location.value = getValue(appHook.location);

                    LogUtil.d(TAG, "AppHook" + appHook.toString());
                    return appHook;
                });
    }

    @Override
    public void setAppHook(AppHook appHook) {
        Observable.create((ObservableOnSubscribe<AppHook>) emitter -> {
            emitter.onNext(appHook);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .subscribe(appHook1 -> {
                    set(appHook1.packageName, appHook1.appLication);
                    set(appHook1.packageName, appHook1.network);
                    set(appHook1.packageName, appHook1.location);
                });

    }

    private <T> void set(String packageName, T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    /*
                    case "class java.lang.String": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (String) field.get(t));
                        LogUtil.d(TAG, field.getType().toString());
                        break;
                    }
                    case "int": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (int) field.get(t));
                        LogUtil.d(TAG, field.getType().toString());
                        break;
                    }
                    case "boolean": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (boolean) field.get(t));
                        LogUtil.d(TAG, field.getType().toString());
                        break;
                    }
                    */
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        SPTools.setSharedPreferences(packageName + field.getName(), itemHook.value);
                        LogUtil.d(TAG, field.getType().toString());
                        LogUtil.d(TAG, packageName + field.getName() + " " + itemHook.getValue());
                        break;
                    }
                    default:
                        LogUtil.d(TAG, "nomatch" + field.getType().toString());
                        break;
                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(TAG, e.toString());
        }
    }

    private <T> void get(String packageName, T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    /*
                    case "class java.lang.String": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (String) field.get(t));
                        LogUtil.d("mModelApp", field.getType().toString());
                        break;
                    }
                    case "int": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (int) field.get(t));
                        LogUtil.d("mModelApp", field.getType().toString());
                        break;
                    }
                    case "boolean": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        itemHook.setValue(SPTools.getSharedPreferences(packageName + field.getName(), false));
                        LogUtil.d("mModelApp", field.getType().toString());
                        break;
                    }*/
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        itemHook.setValue(SPTools.getSharedPreferences(packageName + field.getName(), false));
                        LogUtil.d(TAG, field.getType().toString());
                        LogUtil.d(TAG, packageName + field.getName() + " " + itemHook.getValue());
                        break;
                    }
                    default:
                        LogUtil.d(TAG, "nomatch" + field.getType().toString());

                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(TAG, e.toString());
        }

    }

    private <T> boolean getValue(T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    /*
                    case "class java.lang.String": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (String) field.get(t));
                        LogUtil.d("mModelApp", field.getType().toString());
                        break;
                    }
                    case "int": {
                        SPTools.setSharedPreferences(packageName + field.getName(), (int) field.get(t));
                        LogUtil.d("mModelApp", field.getType().toString());
                        break;
                    }
                    case "boolean": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        itemHook.setValue(SPTools.getSharedPreferences(packageName + field.getName(), false));
                        LogUtil.d("mModelApp", field.getType().toString());
                        break;
                    }*/
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        if (itemHook.value) {
                            return true;
                        }
                        LogUtil.d(TAG, field.getType().toString());
                        break;

                    }
                    default:
                        LogUtil.d(TAG, "nomatch" + field.getType().toString());

                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(TAG, e.toString());
        }
        return false;
    }


}

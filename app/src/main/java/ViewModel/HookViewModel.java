package ViewModel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import Base.LogUtil;
import Repository.HookRepository;
import Repository.Room.AppHook;
import Repository.mHookRepository;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class HookViewModel extends ViewModel implements LifecycleObserver {
    private final String TAG = "HookViewModel";

    public String packageName = "default";
    public MutableLiveData<AppHook> appHook = new MutableLiveData<>();

    private Observer<AppHook> observer_appHook;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable mDisposable;
    private HookRepository hookRepository;

    public HookViewModel() {
        hookRepository = new mHookRepository();
        observer_appHook = Observer_appHook_creat();
        //hookRepository.getAppHook().subscribe(observer_appHook);
        appHook.postValue(new AppHook(packageName));
    }

    //activity creat
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void creat() {
        LogUtil.d(TAG, "caeat");
        hookRepository.creat(packageName);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        LogUtil.d(TAG, "start");
        hookRepository.getAppHook().subscribe(observer_appHook);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void resume() {
        LogUtil.d(TAG, "resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void pause() {
        LogUtil.d(TAG, "pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        LogUtil.d(TAG, "stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destory() {
        LogUtil.d(TAG, "destory");
        compositeDisposable.clear();
    }

    /*创建Observer*/
    private Observer<AppHook> Observer_appHook_creat() {
        return new Observer<AppHook>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                compositeDisposable.add(mDisposable);
            }

            @Override
            public void onNext(AppHook appHooks) {
                /*未解除绑定*/
                if (!mDisposable.isDisposed()) {
                    LogUtil.d(TAG, "AppHook" + appHooks.appLication.toString());
                    appHook.postValue(appHooks);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void save() {
        hookRepository.setAppHook(appHook.getValue());
    }

}

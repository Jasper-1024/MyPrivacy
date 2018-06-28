package Repository;

import Repository.HookModel.HookModel;
import Repository.HookModel.mHookModel;
import Repository.Room.AppHook;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class mHookRepository implements HookRepository {
    private final String TAG = "HookRepository";
    private HookModel hookModel;

    private BehaviorSubject<AppHook> behaviorSubject;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable mDisposable;

    public mHookRepository() {
        hookModel = new mHookModel();
        behaviorSubject = behaviorSubject_creat();
    }

    @Override
    public void setAppHook(AppHook appHook) {
        hookModel.setAppHook(appHook);
    }

    @Override
    public BehaviorSubject<AppHook> getAppHook() {
        if (behaviorSubject == null) {
            behaviorSubject = behaviorSubject_creat();
        }
        return behaviorSubject;
    }

    @Override
    public void creat(String packageName) {
        hookModel.getAppHook(packageName).subscribe(behaviorSubject);
    }

    @Override
    public void destory() {
        compositeDisposable.clear();
    }

    private BehaviorSubject<AppHook> behaviorSubject_creat() {
        return BehaviorSubject.create();
    }

}

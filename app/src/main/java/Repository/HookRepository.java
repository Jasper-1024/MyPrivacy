package Repository;

import Repository.Room.AppHook;
import io.reactivex.subjects.BehaviorSubject;

public interface HookRepository {
    BehaviorSubject<AppHook> getAppHook();

    void setAppHook(AppHook appHook);

    void creat(String packageName);

    void destory();
}

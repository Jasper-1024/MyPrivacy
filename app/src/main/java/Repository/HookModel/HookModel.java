package Repository.HookModel;

import Repository.Room.AppHook;
import io.reactivex.Observable;

public interface HookModel {
    Observable<AppHook> getAppHook(String packageName);
    void setAppHook(AppHook appHook);
}

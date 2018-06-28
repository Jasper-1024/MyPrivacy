package Ui.Item;


import android.content.Intent;
import android.util.Log;

import Base.LogUtil;
import Base.MyApplicantion;
import Repository.Model.AppInfo;
import Ui.Activity.HookActivity;
import com.jasperhale.myprivacy.R;

import static android.support.constraint.Constraints.TAG;

public class AppItem extends BaseItem {

    @Override
    public int getViewType() {
        return R.layout.item_appinfo;
    }

    public AppInfo appInfo;

    public AppItem(AppInfo appInfo) {
        this.appInfo = appInfo;

        setOnClickListener(view -> {
            LogUtil.d("AppItem",appInfo.packageName);
            Intent intent = new Intent(MyApplicantion.getContext(), HookActivity.class);
            intent.putExtra("PackageName", appInfo.packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            switch (view.getId()) {
                case R.id.ApplistIcon:
                    Log.d(TAG, "onClick: " + appInfo.packageName);
                    MyApplicantion.getContext().startActivity(intent);
                    break;
                case R.id.ApplistPackageName:
                    MyApplicantion.getContext().startActivity(intent);
                    break;
            }
        });
    }

    @Override
    public int getHash() {
        return appInfo.uid;
    }

    @Override
    public String getSearchkey() {
        return appInfo.label;
    }
}

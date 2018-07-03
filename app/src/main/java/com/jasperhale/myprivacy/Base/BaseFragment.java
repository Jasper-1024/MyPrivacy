package com.jasperhale.myprivacy.Base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

@SuppressLint("Registered")
public class BaseFragment extends Fragment {
    private String Name;

    public String getName() {
        return Name;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("Fragment", getClass().getSimpleName() + "onCreate");
    }

    @Override
    public void onStart() {
        LogUtil.d("Fragment", getClass().getSimpleName() + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("Fragment", getClass().getSimpleName() + "onResume");
    }

    @Override
    public void onPause() {
        LogUtil.d("Fragment", getClass().getSimpleName() + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtil.d("Fragment", getClass().getSimpleName() + "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtil.d("Fragment", getClass().getSimpleName() + "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtil.d("Fragment", getClass().getSimpleName() + "onDestroy");
        super.onDestroy();
    }

}

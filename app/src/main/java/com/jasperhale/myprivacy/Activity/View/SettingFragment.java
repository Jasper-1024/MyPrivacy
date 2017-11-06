package com.jasperhale.myprivacy.Activity.View;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/11/4.
 */

public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
}


package com.viator42.ugo.module.aboutus;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.viator42.ugo.R;

public class AboutUsPreferenceFragment extends PreferenceFragment {
    public AboutUsPreferenceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_about_us);
    }
}

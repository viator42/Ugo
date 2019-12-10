package com.viator42.ugo.module.profile;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.User;
import com.viator42.ugo.widget.HeadimgPreference;

public class ProfilePreferenceFragment extends PreferenceFragment {
    private AppContext appContext;
    private User user;
    private HeadimgPreference headimgPreference;
    private Preference nicknamePreference;
    private Preference telPreference;
    private Preference addressPreference;
    private Preference birthdayPreference;

    public ProfilePreferenceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_profile);

        headimgPreference = (HeadimgPreference) findPreference("headimg");
        nicknamePreference = findPreference("nickname");
        telPreference = findPreference("tel");
        addressPreference = findPreference("address");
        birthdayPreference = findPreference("birthday");

        appContext= (AppContext) getActivity().getApplicationContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        user = appContext.user;

        headimgPreference.setHeadimg();
        nicknamePreference.setSummary(user.userName);
        telPreference.setSummary(user.mobile);
        addressPreference.setSummary(user.address);
        birthdayPreference.setSummary(user.birthday);

    }
}

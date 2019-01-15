package com.viator42.ugo.module.settings;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viator42.ugo.R;
import com.viator42.ugo.module.aboutus.AboutUsActivity;
import com.viator42.ugo.module.branddetail.BrandDetailActivity;
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.user.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsPreferenceFragment extends PreferenceFragment {


    public SettingsPreferenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);

        Preference addressManage = findPreference("address_management");
        addressManage.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });

        Preference aboutUs = findPreference("about_us");
        aboutUs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                return true;
            }
        });

        Preference checkUpdate = findPreference("check_update");
        checkUpdate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });

        Preference logout = findPreference("logout");
        logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(R.string.needs_login);
//                builder.setTitle(R.string.tip);
//                builder.setPositiveButton(getResources().getString(R.string.login), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//
//                        Intent intent = new Intent(BrandDetailActivity.this, LoginActivity.class);
//                        startActivity(intent);
//
//                    }
//                });
//                builder.setNegativeButton(getResources().getString(R.string.cancel), null);
//                builder.create().show();
                return true;
            }
        });

        Preference dev = findPreference("dev");
        dev.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), DevActivity.class));
                return true;
            }
        });

    }

    private void logout() {

    }


}

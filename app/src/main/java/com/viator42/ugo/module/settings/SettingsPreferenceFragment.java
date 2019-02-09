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

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.aboutus.AboutUsActivity;
import com.viator42.ugo.module.address.AddressActivity;
import com.viator42.ugo.module.branddetail.BrandDetailActivity;
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.ref.RefAction;
import com.viator42.ugo.module.user.LoginActivity;
import com.viator42.ugo.utils.CommonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsPreferenceFragment extends PreferenceFragment {
    private AppContext appContext;
    private User user;

    public SettingsPreferenceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);

        Preference addressManage = findPreference("address_management");
        addressManage.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(user == null) {
                    CommonUtils.makeToast(getActivity(), R.string.needs_login);
                    return true;
                }
                startActivity(new Intent(getActivity(), AddressActivity.class));
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
                if (user != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.logout_confirm);
                    builder.setTitle(R.string.tip);
                    builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            logout();

                        }
                    });
                    builder.setNegativeButton(getResources().getString(R.string.cancel), null);
                    builder.create().show();

                }
                else {
                    CommonUtils.makeToast(getActivity(), R.string.needs_login);
                }
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

        appContext = (AppContext) getActivity().getApplicationContext();
        user = appContext.user;
    }

    private void logout() {
        new RefAction().removeUser(getActivity());
        appContext.user = null;
        getActivity().finish();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

}

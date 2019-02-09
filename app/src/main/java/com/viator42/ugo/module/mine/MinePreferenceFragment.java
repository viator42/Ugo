package com.viator42.ugo.module.mine;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.reply.ReplyActivity;
import com.viator42.ugo.module.text.TextDisplayActivity;
import com.viator42.ugo.utils.CommonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MinePreferenceFragment extends PreferenceFragment {
    private AppContext appContext;
    private User user;

    public MinePreferenceFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_mine);

        Preference cartPref = findPreference("cart");
        cartPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (user != null) {
                    CommonUtils.makeToast(getActivity(), R.string.incomplete);
                }
                else {
                    CommonUtils.makeToast(getActivity(), R.string.needs_login);
                }

                return true;
            }
        });

        Preference helpPref = findPreference("help");
        helpPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Bundle bundle = new Bundle();
                bundle.putString("title", getResources().getString(R.string.help));
                bundle.putString("content", getResources().getString(R.string.help_content));
                Intent intent = new Intent(getActivity(), TextDisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                return true;
            }
        });

        Preference orderPref = findPreference("order");
        orderPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (user != null) {
                    CommonUtils.makeToast(getActivity(), R.string.incomplete);
                }
                else {
                    CommonUtils.makeToast(getActivity(), R.string.needs_login);
                }

                return true;
            }
        });

        Preference replyPref = findPreference("reply");
        replyPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), ReplyActivity.class));
                return true;
            }
        });

        appContext = (AppContext) getActivity().getApplicationContext();
        user = appContext.user;
    }
}

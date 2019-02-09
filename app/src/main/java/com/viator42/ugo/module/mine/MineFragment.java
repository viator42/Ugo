package com.viator42.ugo.module.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.FuncGridItem;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.address.AddressActivity;
import com.viator42.ugo.module.brandcollect.BrandCollectActivity;
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.goodscollect.GoodsCollectActivity;
import com.viator42.ugo.module.profile.ProfileActivity;
import com.viator42.ugo.module.settings.SettingsActivity;
import com.viator42.ugo.module.user.LoginActivity;
import com.viator42.ugo.module.user.RegisterActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;
import com.viator42.ugo.widget.FuncGridItemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private Button loginBtn;
    private ViewGroup loginContainer;
    private ViewGroup profileContainer;
    private ImageView headImgView;
    private TextView usernameTextView;
    private ViewGroup goodsCollect;
    private ViewGroup brandCollect;
    private ImageView settingsBtn;

    private AppContext appContext;
    private User user;

    public MineFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = (AppContext) getContext().getApplicationContext();
    }

    @Override
    public void onStart() {
        super.onStart();

        user = appContext.user;
        if(user == null) {
            loginContainer.setVisibility(View.VISIBLE);
            profileContainer.setVisibility(View.GONE);

        }
        else {
            profileContainer.setVisibility(View.VISIBLE);
            loginContainer.setVisibility(View.GONE);

            if(!user.headImg.isEmpty()) {
                GlideApp.with(getContext())
                        .load(user.headImg)
                        .placeholder(R.drawable.ic_headimg)
                        .into(headImgView);
            }

            usernameTextView.setText(user.userName);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mine, container, false);

        loginContainer = view.findViewById(R.id.login_container);
        profileContainer = view.findViewById(R.id.profile_container);

        loginBtn = view.findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        headImgView = view.findViewById(R.id.headimg);
        usernameTextView = view.findViewById(R.id.username);

        goodsCollect = view.findViewById(R.id.goods_collect);
        goodsCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null) {
                    CommonUtils.makeToast(getActivity(), R.string.needs_login);
                    return;
                }
                startActivity(new Intent(getActivity(), GoodsCollectActivity.class));
            }
        });

        brandCollect = view.findViewById(R.id.brand_collect);
        brandCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null) {
                    CommonUtils.makeToast(getActivity(), R.string.needs_login);
                    return;
                }
                startActivity(new Intent(getActivity(), BrandCollectActivity.class));
            }
        });

        settingsBtn = view.findViewById(R.id.settings);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        profileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        return view;
    }
}

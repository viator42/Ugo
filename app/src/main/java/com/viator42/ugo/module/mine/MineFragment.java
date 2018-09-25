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
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.user.LoginActivity;
import com.viator42.ugo.utils.GlideApp;
import com.viator42.ugo.widget.FuncGridItemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private Button loginBtn;
    private Button registerBtn;
    private ViewGroup loginContainer;
    private ViewGroup profileContainer;
    private ImageView headImgView;
    private TextView usernameTextView;

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
        registerBtn = view.findViewById(R.id.register);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        headImgView = view.findViewById(R.id.headimg);
        usernameTextView = view.findViewById(R.id.username);

        return view;
    }
}

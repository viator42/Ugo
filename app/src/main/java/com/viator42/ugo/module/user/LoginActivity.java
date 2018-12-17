package com.viator42.ugo.module.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.module.ref.RefAction;
import com.viator42.ugo.module.user.LoginContract;

import com.viator42.ugo.R;
import com.viator42.ugo.module.user.param.LoginParam;
import com.viator42.ugo.module.user.result.LoginResult;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private EditText telEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private LoginPresenter loginPresenter;
    private AppContext appContext;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (AppContext) getApplicationContext();

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        telEditText = findViewById(R.id.tel);
        passwordEditText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginParam loginParam = new LoginParam();
                loginParam.mobile = telEditText.getText().toString();
                loginParam.pass = passwordEditText.getText().toString();
                loginParam.area = "370105";
                loginParam.android_version = "5.6";
                loginParam.aesKey = appContext.aesKey;

                loginPresenter.login(loginParam);
            }
        });

        loginPresenter = new LoginPresenter(LoginActivity.this);

        appContext.localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(StaticValues.BROADCAST_EXIT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appContext.localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void loginSuccess(LoginResult loginResult) {
        appContext.user = loginResult.data;
        new RefAction().setUser(this, loginResult.data);
        LoginActivity.this.finish();
    }

    @Override
    public void loginOnprogress() {

    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}

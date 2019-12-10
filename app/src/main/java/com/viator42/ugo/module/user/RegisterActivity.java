package com.viator42.ugo.module.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.module.user.param.LoginParam;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.widget.TimeoutbleProgressDialog;

public class RegisterActivity extends BaseActivity {
    private AppContext appContext;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };
    private TimeoutbleProgressDialog loginDialog;

    private EditText telEditText;
    private EditText passwordEditText;
    private EditText smsEditText;
    private Button getSmsBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appContext = (AppContext) getApplicationContext();

        appContext.localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(StaticValues.BROADCAST_EXIT));

        loginDialog = TimeoutbleProgressDialog.createProgressDialog(RegisterActivity.this, StaticValues.CONNECTION_TIMEOUT, new TimeoutbleProgressDialog.OnTimeOutListener() {
            @Override
            public void onTimeOut(TimeoutbleProgressDialog dialog) {
                loginDialog.dismiss();
                CommonUtils.makeToast(RegisterActivity.this, "加载内容失败");
            }
        });

        telEditText = findViewById(R.id.tel);
        passwordEditText = findViewById(R.id.password);
        smsEditText = findViewById(R.id.sms);
        getSmsBtn = findViewById(R.id.get_sms);
        getSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        registerBtn = findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setProgressingDialog(true);

//                LoginParam loginParam = new LoginParam();
//                loginParam.mobile = telEditText.getText().toString();
//                loginParam.pass = passwordEditText.getText().toString();
//                loginParam.area = "370105";
//                loginParam.android_version = "5.6";
//                loginParam.aesKey = appContext.aesKey;
//
//                loginPresenter.login(loginParam);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appContext.localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }
}

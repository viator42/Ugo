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

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.base.BaseActivity;

public class RegisterActivity extends BaseActivity {
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
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (AppContext) getApplicationContext();

        appContext.localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(StaticValues.BROADCAST_EXIT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appContext.localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }
}

package com.viator42.ugo.module.text;

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
import android.widget.TextView;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.utils.CommonUtils;

public class TextDisplayActivity extends AppCompatActivity {
    private TextView contentTextView;
    private Toolbar toolbar;
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
        setContentView(R.layout.activity_text_display);

        contentTextView = findViewById(R.id.content);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String content = bundle.getString("content");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (!CommonUtils.isValueEmpty(title)) {
            toolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (!CommonUtils.isValueEmpty(content)) {
            contentTextView.setText(content);
        }

        appContext = (AppContext) getApplicationContext();

        appContext.localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(StaticValues.BROADCAST_EXIT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appContext.localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }
}

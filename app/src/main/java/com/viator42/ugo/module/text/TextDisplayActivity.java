package com.viator42.ugo.module.text;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.utils.CommonUtils;

public class TextDisplayActivity extends AppCompatActivity {
    private TextView contentTextView;
    private Toolbar toolbar;

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

    }

}

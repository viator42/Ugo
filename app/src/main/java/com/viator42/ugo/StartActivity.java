package com.viator42.ugo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.viator42.ugo.module.mainpage.MainpageActivity;
import com.viator42.ugo.module.ref.RefAction;

public class
StartActivity extends AppCompatActivity {
    private AppContext appContext;
    private long currentTimeMil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        appContext = (AppContext) getApplicationContext();
        appContext.user = new RefAction().getUser(this);

        new SplashTask().start();

    }

    public class SplashTask extends Thread
    {
        public SplashTask() {
        }

        @Override
        public void run() {
            try {
                sleep(StaticValues.splashTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            wrap();
        }
    }

    private void wrap() {
        Intent intent = new Intent(StartActivity.this, MainpageActivity.class);
        startActivity(intent);

        finish();
    }

}

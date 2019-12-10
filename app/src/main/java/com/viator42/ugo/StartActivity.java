package com.viator42.ugo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.viator42.ugo.module.mainpage.MainpageActivity;
import com.viator42.ugo.module.ref.RefAction;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.widget.CircleProgressbar;

import java.util.HashMap;

public class
StartActivity extends AppCompatActivity {
    private AppContext appContext;
    private long currentTimeMil;
    private CircleProgressbar mCircleProgressbar;
    private boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        appContext = (AppContext) getApplicationContext();
        appContext.user = new RefAction().getUser(this);

//        HashMap<String, Object> channelInfo = CommonUtils.getChannelInfo(this);
//        CommonUtils.log(String.valueOf(channelInfo.get("channel_id")));
//        CommonUtils.log(String.valueOf(channelInfo.get("app_name")));

        mCircleProgressbar = (CircleProgressbar) findViewById(R.id.circle_progress);
        mCircleProgressbar.setOutLineColor(getResources().getColor(R.color.text_black));
        mCircleProgressbar.setInCircleColor(Color.parseColor("#505559"));
        mCircleProgressbar.setProgressColor(Color.parseColor("#1BB079"));
        mCircleProgressbar.setProgressLineWidth(StaticValues.splashTime);
        mCircleProgressbar.setProgressType(CircleProgressbar.ProgressType.COUNT);
        mCircleProgressbar.setTimeMillis(StaticValues.splashTime);

        mCircleProgressbar.reStart();
        mCircleProgressbar.setCountdownProgressListener(1,progressListener);

        mCircleProgressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                isClick = true;
                wrap();

            }
        });

//        new SplashTask().start();
    }

    private CircleProgressbar.OnCountdownProgressListener progressListener = new CircleProgressbar.OnCountdownProgressListener() {
        @Override
        public void onProgress(int what, int progress)
        {

            if(what==1 && progress==100 && !isClick)
            {
                wrap();
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isClick = true;
    }

    private void wrap() {
        startActivity(new Intent(StartActivity.this, MainpageActivity.class));
        overridePendingTransition(R.anim.wrap_fade_enter_in, R.anim.wrap_fade_enter_out);
        finish();
    }

    /*
    public class SplashTask extends Thread
    {
        public SplashTask() {
        }

        @Override
        public void run() {
            try {
                sleep(StaticValues.splashTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            wrap();
        }
    }
    */

}

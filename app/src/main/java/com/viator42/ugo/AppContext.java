package com.viator42.ugo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.user.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppContext extends Application {
    public Retrofit retrofit;
    public String aesKey;
    public User user;
    public RequestOptions glideRequestOptions;
    public LocalBroadcastManager localBroadcastManager;
    public EventBus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        glideRequestOptions = new RequestOptions()
        .centerCrop();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        eventBus = EventBus.builder()
                .logNoSubscriberMessages(true)
                .sendNoSubscriberEvent(true)
                .build();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

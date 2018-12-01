package com.viator42.ugo;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.user.LoginActivity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppContext extends Application {
    public Retrofit retrofit;
    public String aesKey;
    public User user;
    public RequestOptions glideRequestOptions;

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
    }

}

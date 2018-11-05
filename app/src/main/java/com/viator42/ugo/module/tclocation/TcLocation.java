package com.viator42.ugo.module.tclocation;

import android.content.Context;
import android.os.Environment;

import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.mainpage.result.HomeReResult;
import com.viator42.ugo.utils.CommonUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

//list?key=

public class TcLocation {

    public void listAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.map.qq.com/ws/district/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Map<String, String> params = new HashMap<String, String>();
        params.put("key", "B7BBZ-KMTC4-7OYUQ-X52DJ-TKO2H-ZTBMW");

        LocationAction locationAction = retrofit.create(LocationAction.class);
        locationAction.list(params)
            .subscribeOn(Schedulers.newThread()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
            .subscribe(new SingleObserver<LocationResult>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(LocationResult result) {
                    CommonUtils.log(Environment.getExternalStorageDirectory().getAbsolutePath());
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "output.txt");
                    try {
                        if(!file.exists()) {
                            file.createNewFile();
                        }
                        file.setWritable(true);

                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                        for (ArrayList<LocationItem> subList: result.result) {
                            for (LocationItem locationItem : subList) {
                                String s = locationItem.id + "," + locationItem.fullname + "\n";
                                CommonUtils.log(s);
                                bufferedWriter.write(s);
                                bufferedWriter.flush();
                            }
                        }
                        bufferedWriter.close();

                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }
            });

    }
}

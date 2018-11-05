package com.viator42.ugo.module.address;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.address.param.DeleteAddressParam;
import com.viator42.ugo.module.address.param.InsertAddressParam;
import com.viator42.ugo.module.address.result.DeleteAddressResult;
import com.viator42.ugo.module.address.result.InsertAddressResult;
import com.viator42.ugo.module.address.result.LoadResult;
import com.viator42.ugo.utils.CommonUtils;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertAddressPresenter implements InsertAddressContract.Presenter{
    InsertAddressContract.View view;

    public InsertAddressPresenter(InsertAddressContract.View view) {
        this.view = view;
    }


    @Override
    public void add(InsertAddressParam insertAddressParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(insertAddressParam);

        AddressActions addressActions = retrofit.create(AddressActions.class);

        addressActions.save(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new SingleObserver<InsertAddressResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(InsertAddressResult insertAddressResult) {
                        if(insertAddressResult.success) {
                            view.insertSuccess(insertAddressResult);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void update(InsertAddressParam insertAddressParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(insertAddressParam);

        AddressActions addressActions = retrofit.create(AddressActions.class);


    }

    @Override
    public void delete(DeleteAddressParam deleteAddressParam) {
    }
}

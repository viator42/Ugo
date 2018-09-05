package com.viator42.ugo.module.brands;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.brands.param.BrandsParam;
import com.viator42.ugo.module.brands.result.BrandsResult;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandsPresenter implements BrandsContract.Presenter {
    private BrandsContract.View view;

    public BrandsPresenter(BrandsContract.View view) {
        this.view = view;
    }

    @Override
    public void loadAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        BrandsParam brandsParam = new BrandsParam();
        brandsParam.android_version = "5.6";
        brandsParam.model = false;
        brandsParam.useCache = false;

        Gson gson = new Gson();
        String paramStr = gson.toJson(brandsParam);

        BrandsActions brandsActions = retrofit.create(BrandsActions.class);

        brandsActions.all(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new SingleObserver<BrandsResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull BrandsResult brandsResult) {
                        view.listAll(brandsResult.data.appBrandAll);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
    }
}

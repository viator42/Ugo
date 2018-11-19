package com.viator42.ugo.module.mainpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.RequestBuilder;
import com.google.gson.Gson;
import com.viator42.ugo.AppContext;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.mainpage.param.HomeParam;
import com.viator42.ugo.module.mainpage.param.HomeReParam;
import com.viator42.ugo.module.mainpage.result.HomeReResult;
import com.viator42.ugo.module.mainpage.result.HomeResult;
import com.viator42.ugo.utils.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainpagePresenter implements MainpageContract.Presenter {
    private final MainpageContract.View view;

    public MainpagePresenter(
        MainpageContract.View view) {
            this.view = view;
    }

    @Override
    public void loadHome(HomeParam homeParam, boolean useCache) {
        CacheControl.Builder cacheBuilder  = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
        cacheBuilder.maxStale(365,TimeUnit.DAYS);//这个是控制缓存的过时时间
        final CacheControl cacheControl = cacheBuilder.build();

        File cacheFile = new File(view.getContext().getCacheDir(), "ugou");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder requestBuilder = chain.request().newBuilder();
                        requestBuilder.cacheControl(cacheControl);

                        Request request = requestBuilder.build();

                        okhttp3.Response response = chain.proceed(request);

                        return response;
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(homeParam);

        CommonUtils.log(paramStr);

        MainpageActions mainpageActions = retrofit.create(MainpageActions.class);

        mainpageActions.home(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new Observer<Response<HomeResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<HomeResult> homeResultResponse) {
                        HomeResult homeResult = homeResultResponse.body();
                        homeResult.aesKey = homeResultResponse.headers().get("Set-Cookie");
                        view.listHome(homeResult);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void loadHomeRe(HomeReParam homeReParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(homeReParam);

        MainpageActions mainpageActions = retrofit.create(MainpageActions.class);

        mainpageActions.homeRe(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new SingleObserver<HomeReResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        view.setProgressingDialog(true);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull HomeReResult homeReResult) {
                        view.setProgressingDialog(false);
                        view.listHomeRe(homeReResult.data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        view.setProgressingDialog(false);
                    }
                });

    }



}

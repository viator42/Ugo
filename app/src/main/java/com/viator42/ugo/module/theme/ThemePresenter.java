package com.viator42.ugo.module.theme;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.mainpage.MainpageActions;
import com.viator42.ugo.module.mainpage.param.HomeReParam;
import com.viator42.ugo.module.mainpage.result.HomeReResult;
import com.viator42.ugo.module.theme.param.ThemeParam;
import com.viator42.ugo.module.theme.result.ThemeResult;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThemePresenter implements ThemeContract.Presenter {
    private ThemeContract.View view;

    public ThemePresenter(ThemeContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ThemeParam themeParam = new ThemeParam();
        themeParam.android_version = "5.6";
        themeParam.area = "370105";
        themeParam.model = false;

        Gson gson = new Gson();
        String paramStr = gson.toJson(themeParam);

        ThemeActions themeActions = retrofit.create(ThemeActions.class);

        themeActions.getAll(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new SingleObserver<ThemeResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull ThemeResult themeResult) {
                        view.list(themeResult.data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
    }

}

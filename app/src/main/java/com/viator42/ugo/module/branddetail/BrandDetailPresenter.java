package com.viator42.ugo.module.branddetail;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.branddetail.param.BrandGoodsParam;
import com.viator42.ugo.module.branddetail.result.BrandGoodsResult;
import com.viator42.ugo.module.brands.BrandsActions;
import com.viator42.ugo.module.brands.param.BrandsParam;
import com.viator42.ugo.module.brands.result.BrandsResult;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandDetailPresenter implements BrandDetailContract.Presenter{
    private BrandDetailContract.View view;

    public BrandDetailPresenter(BrandDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadAll(BrandGoodsParam brandGoodsParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(brandGoodsParam);

        BrandGoodsAction brandGoodsAction = retrofit.create(BrandGoodsAction.class);

        brandGoodsAction.all(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new SingleObserver<BrandGoodsResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull BrandGoodsResult brandGoodsResult) {
                        view.listAll(brandGoodsResult.data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
    }
}

package com.viator42.ugo.module.branddetail;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.branddetail.param.BrandGoodsParam;
import com.viator42.ugo.module.branddetail.param.SaveAppBarndCollectParam;
import com.viator42.ugo.module.branddetail.result.BrandGoodsResult;
import com.viator42.ugo.module.branddetail.result.SaveAppBarndCollectResult;
import com.viator42.ugo.module.brands.BrandsActions;
import com.viator42.ugo.module.brands.param.BrandsParam;
import com.viator42.ugo.module.brands.result.BrandsResult;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandDetailPresenter implements BrandDetailContract.Presenter{
    private BrandDetailContract.View view;

    public BrandDetailPresenter(BrandDetailContract.View view) {
        this.view = view;
    }

    /**
     * 添加到收藏
     * @param saveAppBarndCollectParam
     */
    @Override
    public void saveAppBrandCollectAction(SaveAppBarndCollectParam saveAppBarndCollectParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(saveAppBarndCollectParam);

        BrandGoodsAction brandGoodsAction = retrofit.create(BrandGoodsAction.class);

        brandGoodsAction.saveAppBrandCollect(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new Observer<Response<SaveAppBarndCollectResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<SaveAppBarndCollectResult> saveAppBarndCollectResultResponse) {
                        SaveAppBarndCollectResult saveAppBarndCollectResult = saveAppBarndCollectResultResponse.body();
                        if(saveAppBarndCollectResult.success) {
                            view.saveAppBarndCollectSuccess();
                        }
                        else {
                            view.saveAppBrandCollectFailed(saveAppBarndCollectResult.msg);
                        }
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

//    7067-8094/com.brand.ushopping V/params: http://www.ugouchina.com/ugouApp/SaveAppBrandCollectAction.action ---- {param={"android_version":"5.6","appbrandId":{"flag":0,"id":2,"model":false,"userId":0},"appuserId":{"model":false,"userId":7},"model":false,"newUserId":"85d0596958a447fca30d19754ac2b803","sessionid":"85732A4123FA7F5288D42DE3CF1F5E66","success":false,"userId":7}}
//    7067-8094/com.brand.ushopping V/resultString: http://www.ugouchina.com/ugouApp/SaveAppBrandCollectAction.action ---- {"data":{},"msg":"收藏成功！","sessionid":"","shopcartDiscount":{},"shopcartZhekou":{},"success":true}

}

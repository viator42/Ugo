package com.viator42.ugo.module.goods;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.module.goods.param.GoodsDetailParam;
import com.viator42.ugo.module.goods.param.SaveAppGoodsCollectParam;
import com.viator42.ugo.module.goods.result.GoodsDetailResult;
import com.viator42.ugo.module.goods.result.SaveAppGoodsCollectResult;
import com.viator42.ugo.module.user.UserAction;
import com.viator42.ugo.module.user.result.LoginResult;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsPresenter implements GoodsContract.Presenter {
    private GoodsContract.View view;

    public GoodsPresenter(GoodsContract.View view) {
        this.view = view;
    }

    @Override
    public void loadDetails(GoodsDetailParam goodsDetailParam) {
//        08-07 16:09:32.200 9655-10059/com.brand.ushopping V/params: http://139.129.116.242/ugouApp/GetAppGoodsIdAction.action ---- {param={"android_version":"5.0","area":"371603","goodsId":3757,"model":false,"success":false,"useCache":false,"userId":0}}
//        08-07 16:09:32.482 9655-10059/com.brand.ushopping V/resultString: http://139.129.116.242/ugouApp/GetAppGoodsIdAction.action ---- {"data":{"attribute":{"160/M":["绿色"],"165/L":["绿色"],"170/XL":["绿色"]},"goods":{"appbrandId":{"brandName":"纳纹","detail":"   纳纹提倡时尚、自信、都市化的着装方式，追求独特自我、高尚品位的国际化服装文化。“复古而纯真，内敛而甜美，迷恋英伦、沉浸新浪漫风格。”纳纹致力服务于20-35岁之间的女性群体，容扩各种职业追求浪漫时尚的女性。为她们打造一种迷人的隐晦性感，甜美而浪漫的女性形象，让她们永远沐浴在年轻浪漫的感觉中！","flag":0,"id":5,"logopic":"http://139.129.116.242:80/ugouApp/upload/brand/1f5a94fbfa9697b.jpg","showpic":"http://139.129.116.242:80/ugouApp/upload/brand/1453446171985.jpg"},"flag":0,"goodsIntro":"1722715114","goodsName":"纳纹2017半身裙","goodsNewDetail":"http://139.129.116.242:80/ugouApp/upload/goods/1498197819551.jpg;http://139.129.116.242:80/ugouApp/upload/goods/1498197819557.jpg;","goodsPrice":299.00,"id":3757,"images":"http://139.129.116.242:80/ugouApp/upload/goods/1498197815526.jpg;http://139.129.116.242:80/ugouApp/upload/goods/1498197815513.jpg;","logopicUrl":"http://139.129.116.242:80/ugouApp/upload/goods/1498197811538.jpg","originalPrice":299.00,"promotionPrice":299.00,"saleCount":1}},"sessionid":"","shopcartDiscount":{},"shopcartZhekou":{},"success":true}

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(goodsDetailParam);

        GoodsAction goodsAction = retrofit.create(GoodsAction.class);

        goodsAction.goodsDetail(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new Observer<Response<GoodsDetailResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<GoodsDetailResult> goodsDetailResultResponse) {
                        view.showDetails(goodsDetailResultResponse.body());
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
    public void addToCart() {

    }

    /**
     * 添加收藏
     * @param saveAppGoodsCollectParam
     */
    @Override
    public void saveAppGoodsCollect(final SaveAppGoodsCollectParam saveAppGoodsCollectParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(saveAppGoodsCollectParam);

        GoodsAction goodsAction = retrofit.create(GoodsAction.class);
        goodsAction.saveAppGoodsCollect(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new Observer<Response<SaveAppGoodsCollectResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<SaveAppGoodsCollectResult> saveAppGoodsCollectResultResponse) {
                        SaveAppGoodsCollectResult saveAppGoodsCollectResult = saveAppGoodsCollectResultResponse.body();
                        if (saveAppGoodsCollectResult != null) {
                            if (saveAppGoodsCollectResult.success) {

                            }
                            else {

                            }
                        }
                        else {

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
}

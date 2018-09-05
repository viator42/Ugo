package com.viator42.ugo.module.goods;

import com.viator42.ugo.module.branddetail.result.BrandGoodsResult;
import com.viator42.ugo.module.goods.result.GoodsDetailResult;
import com.viator42.ugo.module.user.result.LoginResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GoodsAction {
    @POST("GetAppGoodsIdAction.action")
    @FormUrlEncoded
    Observable<Response<GoodsDetailResult>> goodsDetail(@Field("param") String postParams);
}

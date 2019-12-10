package com.viator42.ugo.module.branddetail;

import com.viator42.ugo.module.branddetail.result.BrandGoodsResult;
import com.viator42.ugo.module.branddetail.result.SaveAppBarndCollectResult;
import com.viator42.ugo.module.brands.result.BrandsResult;
import com.viator42.ugo.module.goods.result.SaveAppGoodsCollectResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BrandGoodsAction {
    @POST("GetAppStoresIdAll.action")
    @FormUrlEncoded
    Single<BrandGoodsResult> all(@Field("param") String postParams);

    @POST("SaveAppBrandCollectAction.action")
    @FormUrlEncoded
    Observable<Response<SaveAppBarndCollectResult>> saveAppBrandCollect(@Field("param") String postParams);
}

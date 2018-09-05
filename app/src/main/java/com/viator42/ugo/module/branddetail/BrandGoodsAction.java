package com.viator42.ugo.module.branddetail;

import com.viator42.ugo.module.branddetail.result.BrandGoodsResult;
import com.viator42.ugo.module.brands.result.BrandsResult;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BrandGoodsAction {
    @POST("GetAppStoresIdAll.action")
    @FormUrlEncoded
    Single<BrandGoodsResult> all(@Field("param") String postParams);
}

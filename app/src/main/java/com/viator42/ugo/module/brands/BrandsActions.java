package com.viator42.ugo.module.brands;

import com.viator42.ugo.module.brands.result.BrandsResult;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BrandsActions {
    @POST("GetRecommendAppBrandAction.action")
    @FormUrlEncoded
    Single<BrandsResult> all(@Field("param") String postParams);
}

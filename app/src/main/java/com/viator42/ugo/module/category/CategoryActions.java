package com.viator42.ugo.module.category;

import com.viator42.ugo.module.category.result.CategoryGoodsResult;
import com.viator42.ugo.module.mainpage.result.HomeResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CategoryActions {
    @POST("GetAppGoodsTypeId.action")
    @FormUrlEncoded
    Observable<Response<CategoryGoodsResult>> load(@Field("param") String postParams);



}

package com.viator42.ugo.module.brandcollect;

import com.viator42.ugo.module.brandcollect.result.LoadResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BrandCollectAction {
    @POST("GetListAppBrandCollectUserIdAction.action")
    @FormUrlEncoded
    Observable<Response<LoadResult>> load(@Field("param") String postParams);
}

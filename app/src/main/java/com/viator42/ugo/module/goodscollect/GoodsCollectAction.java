package com.viator42.ugo.module.goodscollect;

import com.viator42.ugo.module.goodscollect.result.LoadResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GoodsCollectAction {
    @POST("GetListAppGoodsCollectUserIdAction.action")
    @FormUrlEncoded
    Observable<Response<LoadResult>> load(@Field("param") String postParams);
}

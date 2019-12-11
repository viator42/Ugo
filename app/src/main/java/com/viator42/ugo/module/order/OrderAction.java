package com.viator42.ugo.module.order;

import com.viator42.ugo.module.order.result.LoadResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderAction {
    @POST("GetOrderAllAction.action")
    @FormUrlEncoded
    Observable<Response<LoadResult>> load(@Field("param") String postParams);
}

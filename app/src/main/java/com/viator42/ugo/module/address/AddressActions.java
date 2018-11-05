package com.viator42.ugo.module.address;

import com.viator42.ugo.module.address.param.DeleteAddressParam;
import com.viator42.ugo.module.address.param.InsertAddressParam;
import com.viator42.ugo.module.address.result.DeleteAddressResult;
import com.viator42.ugo.module.address.result.InsertAddressResult;
import com.viator42.ugo.module.address.result.LoadResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddressActions {
    @POST("GetAppAddressAllAction.action")
    @FormUrlEncoded
    Single<LoadResult> load(@Field("param") String postParams);

    @POST("SaveAppAddressAction.action")
    @FormUrlEncoded
    Single<InsertAddressResult> save(@Field("param") String postParams);

    @POST("UpdateAddressAction.action")
    @FormUrlEncoded
    Single<InsertAddressResult> update(@Field("param") String postParams);

    @POST("DeleteAddressAction.action")
    @FormUrlEncoded
    Single<DeleteAddressResult> delete(@Field("param") String postParams);
}

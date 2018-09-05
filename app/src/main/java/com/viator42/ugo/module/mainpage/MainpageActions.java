package com.viator42.ugo.module.mainpage;

import com.viator42.ugo.module.mainpage.result.HomeReResult;
import com.viator42.ugo.module.mainpage.result.HomeResult;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MainpageActions {



    @POST("HomeAction.action")
    @FormUrlEncoded
    Observable<Response<HomeResult>> home(@Field("param") String postParams);

    @POST("HomeReAction.action")
    @FormUrlEncoded
    Single<HomeReResult> homeRe(@Field("param") String postParams);


}

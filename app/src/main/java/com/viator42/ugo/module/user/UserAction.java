package com.viator42.ugo.module.user;

import com.viator42.ugo.module.mainpage.result.HomeResult;
import com.viator42.ugo.module.theme.result.ThemeResult;
import com.viator42.ugo.module.user.result.LoginResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAction {
    @POST("GetLoginAction.action")
    @FormUrlEncoded
    Observable<Response<LoginResult>> login(@Field("param") String postParams);
}

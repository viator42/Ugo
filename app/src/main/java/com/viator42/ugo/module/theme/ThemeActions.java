package com.viator42.ugo.module.theme;

import com.viator42.ugo.module.theme.result.ThemeResult;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ThemeActions {
    @POST("GetAppThemeAllAction.action")
    @FormUrlEncoded
    Single<ThemeResult> getAll(@Field("param") String postParams);
}

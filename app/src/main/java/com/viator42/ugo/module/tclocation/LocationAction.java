package com.viator42.ugo.module.tclocation;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface LocationAction {
    @GET("list")
    Single<LocationResult> list(@QueryMap Map<String, String> params);
}

package com.viator42.ugo.module.brandcollect;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.brandcollect.BrandCollectAction;
import com.viator42.ugo.module.brandcollect.BrandCollectContract;
import com.viator42.ugo.module.brandcollect.param.LoadParam;
import com.viator42.ugo.module.brandcollect.result.LoadResult;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandCollectPresenter implements BrandCollectContract.Presenter {
    private BrandCollectContract.View view;

    public BrandCollectPresenter(BrandCollectContract.View view) {
        this.view = view;
    }

    @Override
    public void load(LoadParam loadParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(loadParam);

        BrandCollectAction brandCollectAction = retrofit.create(BrandCollectAction.class);
        brandCollectAction.load(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new Observer<Response<LoadResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<LoadResult> loadResultResponse) {
                        LoadResult loadResult = loadResultResponse.body();
                        if(loadResult != null) {
                            if(loadResult.success) {
                                view.loadSuccess();
                                view.list(loadResult.data);
                            }
                            else {

                            }
                        }
                        else {

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

package com.viator42.ugo.module.address;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.address.param.LoadParam;
import com.viator42.ugo.module.address.result.LoadResult;
import com.viator42.ugo.utils.CommonUtils;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressPresenter implements AddressContract.Presenter{
    private AddressContract.View view;

    public AddressPresenter(AddressContract.View view) {
        this.view = view;
    }


    /**
     * 地址列表
     */
    @Override
    public void load(LoadParam loadParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(loadParam);

        CommonUtils.log(paramStr);

        AddressActions addressActions = retrofit.create(AddressActions.class);

        addressActions.load(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new SingleObserver<LoadResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LoadResult loadResult) {
                        if(loadResult.success) {
                            if(loadResult.data != null) {
                                view.list(loadResult.data);

                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

    }
}

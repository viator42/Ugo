package com.viator42.ugo.module.category;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.category.param.CategoryGoodsParam;
import com.viator42.ugo.module.category.result.CategoryGoodsResult;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryPresenter implements CategoryContract.Presenter{
    CategoryContract.View view;

    public CategoryPresenter(CategoryContract.View view) {
        this.view = view;
    }

    @Override
    public void load(final CategoryGoodsParam cagegoryGoodsParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(cagegoryGoodsParam);

        CategoryActions categoryActions = retrofit.create(CategoryActions.class);
        categoryActions.load(paramStr)
                .subscribeOn(Schedulers.newThread()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
                .subscribe(new Observer<Response<CategoryGoodsResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<CategoryGoodsResult> categoryGoodsResultResponse) {
                        CategoryGoodsResult categoryGoodsResult = categoryGoodsResultResponse.body();
                        if(categoryGoodsResult != null) {
                            if(categoryGoodsResult.success) {
                                view.list(categoryGoodsResult.data);
                            }
                            else {
                                view.loadFailed();
                            }

                        }
                        else {
                                view.loadFailed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.loadFailed();
                    }

                    @Override
                    public void onComplete() {
                        view.loadSuccess();
                    }
                });
    }
}

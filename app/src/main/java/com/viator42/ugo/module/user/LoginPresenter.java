package com.viator42.ugo.module.user;

import com.google.gson.Gson;
import com.viator42.ugo.EnvValues;
import com.viator42.ugo.module.user.param.LoginParam;
import com.viator42.ugo.module.user.result.LoginResult;
import com.viator42.ugo.utils.CommonUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(LoginParam loginParam) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EnvValues.SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Gson gson = new Gson();
        String paramStr = gson.toJson(loginParam);

        UserAction userAction = retrofit.create(UserAction.class);

        userAction.login(paramStr)
            .subscribeOn(Schedulers.newThread()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
            .subscribe(new Observer<Response<LoginResult>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<LoginResult> loginResultResponse) {
                    LoginResult loginResult = loginResultResponse.body();
                    if(loginResult.data.userId != 0) {
                        view.loginSuccess(loginResultResponse.body());
                    }
                    else {
                        view.loginFailed(loginResult.data.msg);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

//        ThemeActions themeActions = retrofit.create(ThemeActions.class);
//
//        themeActions.getAll(paramStr)
//                .subscribeOn(Schedulers.newThread()) // "work" on io thread
//                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread);
//                .subscribe(new SingleObserver<ThemeResult>() {
//                    @Override
//                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@io.reactivex.annotations.NonNull ThemeResult themeResult) {
////                        view.list(themeResult.data);
//                    }
//
//                    @Override
//                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//
//                    }
//                });


    }
}

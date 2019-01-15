package com.viator42.ugo.module.user;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.module.user.param.LoginParam;
import com.viator42.ugo.module.user.result.LoginResult;

public interface LoginContract {
    interface View extends BaseView<LoginContract.Presenter> {
        void loginSuccess(LoginResult loginResult);
        void loginFailed(String msg);
        void setProgressingDialog(boolean flag);
    }


    interface Presenter {
        void login(LoginParam loginParam);
    }
}

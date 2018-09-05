package com.viator42.ugo.module.user;

import com.viator42.ugo.module.user.param.LoginParam;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class LoginPresenterTest {

    @Mock
    private LoginContract.View view;

    private LoginPresenter loginPresenter;

    @Before
    public void setUp() throws Exception {
        loginPresenter = new LoginPresenter(view);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() {
        LoginParam loginParam = new LoginParam();
        loginParam.mobile = "15165417820";
        loginParam.pass = "123";

        verify(loginPresenter).login(loginParam);
    }
}
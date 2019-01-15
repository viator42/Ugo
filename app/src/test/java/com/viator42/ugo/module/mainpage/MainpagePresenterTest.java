package com.viator42.ugo.module.mainpage;

import com.viator42.ugo.RxSchedulersOverrideRule;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.module.mainpage.param.HomeParam;
import com.viator42.ugo.module.mainpage.param.HomeReParam;
import com.viator42.ugo.utils.CommonUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class MainpagePresenterTest {

    @ClassRule
    public static RxSchedulersOverrideRule sSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Mock
    private MainpageContract.View view;

    private MainpagePresenter mainpagePresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        CommonUtils.log("Mainpage Module test setUp");

        mainpagePresenter = new MainpagePresenter(view);
    }

    @After
    public void tearDown() throws Exception {
        CommonUtils.log("Mainpage Module test fin");
    }

    @Test
    public void loadHome() {
        HomeParam homeParam = new HomeParam();
        homeParam.android_version = "5.6";
        homeParam.area = "370105";
        mainpagePresenter.loadHome(homeParam, false);
        verify(view).loadHomeSuccess();

    }

    @Test
    public void loadHomeRe() {
        HomeReParam homeReParam = new HomeReParam();
        homeReParam.android_version = "5.6";
        homeReParam.area = "370105";
        homeReParam.min = 0 * StaticValues.PAGE_COUNT;
        homeReParam.max = homeReParam.min + StaticValues.PAGE_COUNT;

        mainpagePresenter.loadHomeRe(homeReParam);
        verify(view).loadHomeReSuccess();
    }
}
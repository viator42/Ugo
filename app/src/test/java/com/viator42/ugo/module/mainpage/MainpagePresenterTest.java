package com.viator42.ugo.module.mainpage;

import com.viator42.ugo.RxSchedulersOverrideRule;

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

        mainpagePresenter = new MainpagePresenter(view);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadHome() {
    }

    @Test
    public void loadHomeRe() {
        mainpagePresenter.loadHomeRe();
        verify(view).setProgressingDialog(false);
    }
}
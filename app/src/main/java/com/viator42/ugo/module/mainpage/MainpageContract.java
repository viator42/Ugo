package com.viator42.ugo.module.mainpage;


import android.content.Context;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.module.mainpage.param.HomeParam;
import com.viator42.ugo.module.mainpage.param.HomeReParam;
import com.viator42.ugo.module.mainpage.result.HomeReItem;
import com.viator42.ugo.module.mainpage.result.HomeReResult;
import com.viator42.ugo.module.mainpage.result.HomeResult;

import java.util.ArrayList;

public interface MainpageContract {
    interface View extends BaseView<Presenter> {
        Context getContext();
        void listHome(HomeResult homeResult);
        void listHomeRe(ArrayList<HomeReItem> homeReItems);
        void setProgressingDialog(boolean isShow);
    }

    interface Presenter {
        void loadHome(HomeParam homeParam, boolean useCache);
        void loadHomeRe(HomeReParam homeReParam);

    }
}

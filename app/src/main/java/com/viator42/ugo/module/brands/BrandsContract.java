package com.viator42.ugo.module.brands;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.model.AppBrandAll;

import java.util.ArrayList;

public interface BrandsContract {
    interface View extends BaseView<BrandsContract.Presenter> {
        void listAll(ArrayList<AppBrandAll> appBrandAlls);
    }

    interface Presenter {
        void loadAll();
    }

}

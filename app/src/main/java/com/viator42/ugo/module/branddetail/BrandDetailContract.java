package com.viator42.ugo.module.branddetail;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.model.AppBrandAll;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.module.branddetail.param.BrandGoodsParam;
import com.viator42.ugo.module.brands.BrandsContract;

import java.util.ArrayList;

public interface BrandDetailContract {
    interface View extends BaseView<BrandsContract.Presenter> {
        void listAll(ArrayList<AppgoodsId> appgoodsIds);
    }

    interface Presenter {
        void loadAll(BrandGoodsParam brandGoodsParam);
    }
}

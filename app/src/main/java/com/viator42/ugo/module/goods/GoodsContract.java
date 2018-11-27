package com.viator42.ugo.module.goods;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.module.branddetail.param.BrandGoodsParam;
import com.viator42.ugo.module.goods.param.GoodsDetailParam;
import com.viator42.ugo.module.goods.param.SaveAppGoodsCollectParam;
import com.viator42.ugo.module.goods.result.GoodsDetailResult;

import java.util.ArrayList;

public class GoodsContract {
    interface View extends BaseView<GoodsContract.Presenter> {
        void showDetails(GoodsDetailResult goodsDetailResult);
        void addToCartDone();
        void saveAppGoodsCollectSuccess();
        void saveAppGoodsCollectFailed(String msg);
    }

    interface Presenter {
        void loadDetails(GoodsDetailParam goodsDetailParam);
        void addToCart();
        void saveAppGoodsCollect(SaveAppGoodsCollectParam saveAppGoodsCollectParam);
    }
}

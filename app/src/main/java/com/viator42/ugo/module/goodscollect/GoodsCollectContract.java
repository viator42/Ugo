package com.viator42.ugo.module.goodscollect;

import com.viator42.ugo.model.AppGoodsCollectItem;
import com.viator42.ugo.module.goodscollect.param.LoadParam;
import com.viator42.ugo.module.goodscollect.result.LoadResult;

import java.util.ArrayList;

public interface GoodsCollectContract {
    public interface Presenter {
        public void load(LoadParam loadParam);
    };

    public interface View {
        public void list(ArrayList<AppGoodsCollectItem> appGoodsCollectItems);
        public void loadSuccess();
        public void loadFailed(String msg);
    };
}

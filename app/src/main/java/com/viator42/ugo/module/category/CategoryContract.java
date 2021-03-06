package com.viator42.ugo.module.category;

import com.viator42.ugo.model.Goods;
import com.viator42.ugo.module.category.param.CategoryGoodsParam;

import java.util.ArrayList;

public interface CategoryContract {
    public interface View {
        public void list(ArrayList<Goods> goods);
        public void loadDone();
        public void loadFailed(String msg);
        public void loadStart();
    }

    public interface Presenter {
        public void load(CategoryGoodsParam cagegoryGoodsParam);
    }

}

package com.viator42.ugo.module.category;

import com.viator42.ugo.module.category.param.CategoryGoodsParam;

public interface CategoryContract {
    public interface View {
        public void list();
    }

    public interface Presenter {
        public void load(CategoryGoodsParam cagegoryGoodsParam);
    }

}

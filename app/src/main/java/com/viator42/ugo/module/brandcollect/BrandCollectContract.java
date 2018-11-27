package com.viator42.ugo.module.brandcollect;

import com.viator42.ugo.model.AppBrandCollectItem;
import com.viator42.ugo.module.brandcollect.param.LoadParam;

import java.util.ArrayList;

public interface BrandCollectContract {
    public interface Presenter {
        public void load(LoadParam loadParam);
    };

    public interface View {
        public void list(ArrayList<AppBrandCollectItem> appBrandCollectItems);
        public void loadSuccess();
        public void loadFailed(String msg);
    };
}

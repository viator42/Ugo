package com.viator42.ugo.module.order;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.module.order.param.LoadParam;
import com.viator42.ugo.module.order.result.LoadResult;

public interface OrderListContract {
    interface View extends BaseView<Presenter> {
        public void list(LoadResult loadResult);
    }

    interface Presenter {
        public void load(LoadParam loadParam);
    }
}

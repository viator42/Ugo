package com.viator42.ugo.module.goods.result;

import com.viator42.ugo.base.BaseResult;
import com.viator42.ugo.model.Goods;

import java.util.HashMap;

public class GoodsDetailResult extends BaseResult {
    public Data data;

    public class Data {
        public HashMap<String, String[]> attribute;
        public Goods goods;
    }

}

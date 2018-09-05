package com.viator42.ugo.module.brands.result;

import com.viator42.ugo.base.BaseResult;
import com.viator42.ugo.model.AppBrandAll;

import java.util.ArrayList;

public class BrandsResult extends BaseResult{
    public Data data;

    public class Data {
        public ArrayList<AppBrandAll> appBrandAll;
    }
}

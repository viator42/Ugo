package com.viator42.ugo.module.mainpage.result;

import java.util.ArrayList;

import com.viator42.ugo.base.BaseResult;
import com.viator42.ugo.model.Activity;
import com.viator42.ugo.model.Banner;
import com.viator42.ugo.model.Category;
import com.viator42.ugo.model.Recommend;

public class HomeResult extends BaseResult {
    public Data data;
    public String aesKey;

    public class Data {
        public ArrayList<Activity> activity;
        public ArrayList<Banner> banner;
        public ArrayList<Category> category;
        public ArrayList<Recommend> recommend;
    }

}

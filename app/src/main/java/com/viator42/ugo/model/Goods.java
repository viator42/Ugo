package com.viator42.ugo.model;

public class Goods  {
    public String goodsName;
    public double goodsPrice;
    public long id;
    public double originalPrice;
    public double promotionPrice;
    public double vipPrice;
    public String logopicUrl;
    public String attribute;
    public int count;
    public AppbrandId appbrandId;
    public AppexpressId appexpressId;
    public String goodsDetail;
    public String goodsNewDetail;
    public String goodsIntro;
    public String images;
    public String barCode;
    public int flag;
    public int saleCount;
    public AppaddressId appaddressId;
    public AppStoresId appStoresId;
    public long shopcartDiscountId;
    public long shopcartZhekouId;
    public double discountMoney;
    public AppexpressId getAppexpressId() {
        return appexpressId;
    }
    public long cartId;
    public User user;
    public int customerFlag;
    public int orderFlag;
    public int boughtType;
    public int vipFlag;
    public String attributeUid;
    public String auctionrecordId;

    public class AppaddressId {
        public long id;
    }

    public class AppStoresId {
        public long id;
    }

}

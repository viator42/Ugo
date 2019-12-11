package com.viator42.ugo.model;

public class OrderGoodsItem {
    private AppaddressId appaddressId;
    private AppgoodsId appgoodsId;
    private AppuserId appuserId;
    private AppushopId appushopId;
    private String attribute = "";
    private int flag;
    private long id;
    private double money;
    private String orderNo;
    private int quantity;

    private int customerFlag = 0;
    private String customerContent = "";
    private long customerStartTime = 0;
    private long customerCenterTime = 0;
    private long customerEndTime = 0;
    private long reTime = 0;
    private String customerProblem = "";
    private String customerExplain = "";

    public class AppaddressId {
        public long id;
    }

    public class AppStoresId {
        public long id;
    }

    public class AppushopId {
        public long id;
        public String attribute;
    }

}

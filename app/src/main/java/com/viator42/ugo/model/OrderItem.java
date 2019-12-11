package com.viator42.ugo.model;

import java.util.ArrayList;

public class OrderItem {
    private long id;
    private ArrayList<OrderGoodsItem> orderGoodsItems;
    private int flag;    //0 未付款, 1 已付款, 2 已发货
    private String orderNo;
    private double money;
    private int quantity;
    private long reTime;
}

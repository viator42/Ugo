package com.viator42.ugo.module.address.param;

import com.viator42.ugo.base.BaseParam;

public class InsertAddressParam extends BaseParam {
    public long id;
    public String area;
    public String consignee;
    public String deaddress;
    public int flag;
    public long addressId;
    public double latitude;
    public double longitude;
    public String mobile;
    public String telephone;
    public String zipcode;
    public AppUser_id appuserId;

    public class AppUser_id {
        public long userId;
    }
}

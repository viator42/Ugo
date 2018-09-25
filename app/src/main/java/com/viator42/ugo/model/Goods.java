package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Goods implements Parcelable {
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

    protected Goods(Parcel in) {
        goodsName = in.readString();
        goodsPrice = in.readDouble();
        id = in.readLong();
        originalPrice = in.readDouble();
        promotionPrice = in.readDouble();
        vipPrice = in.readDouble();
        logopicUrl = in.readString();
        attribute = in.readString();
        count = in.readInt();
        appbrandId = in.readParcelable(AppbrandId.class.getClassLoader());
        appexpressId = in.readParcelable(AppexpressId.class.getClassLoader());
        goodsDetail = in.readString();
        goodsNewDetail = in.readString();
        goodsIntro = in.readString();
        images = in.readString();
        barCode = in.readString();
        flag = in.readInt();
        saleCount = in.readInt();
        shopcartDiscountId = in.readLong();
        shopcartZhekouId = in.readLong();
        discountMoney = in.readDouble();
        cartId = in.readLong();
        user = in.readParcelable(User.class.getClassLoader());
        customerFlag = in.readInt();
        orderFlag = in.readInt();
        boughtType = in.readInt();
        vipFlag = in.readInt();
        attributeUid = in.readString();
        auctionrecordId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goodsName);
        dest.writeDouble(goodsPrice);
        dest.writeLong(id);
        dest.writeDouble(originalPrice);
        dest.writeDouble(promotionPrice);
        dest.writeDouble(vipPrice);
        dest.writeString(logopicUrl);
        dest.writeString(attribute);
        dest.writeInt(count);
        dest.writeParcelable(appbrandId, flags);
        dest.writeParcelable(appexpressId, flags);
        dest.writeString(goodsDetail);
        dest.writeString(goodsNewDetail);
        dest.writeString(goodsIntro);
        dest.writeString(images);
        dest.writeString(barCode);
        dest.writeInt(flag);
        dest.writeInt(saleCount);
        dest.writeLong(shopcartDiscountId);
        dest.writeLong(shopcartZhekouId);
        dest.writeDouble(discountMoney);
        dest.writeLong(cartId);
        dest.writeParcelable(user, flags);
        dest.writeInt(customerFlag);
        dest.writeInt(orderFlag);
        dest.writeInt(boughtType);
        dest.writeInt(vipFlag);
        dest.writeString(attributeUid);
        dest.writeString(auctionrecordId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel in) {
            return new Goods(in);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };

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

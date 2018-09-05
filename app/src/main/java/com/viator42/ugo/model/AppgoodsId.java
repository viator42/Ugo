package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppgoodsId implements Parcelable {
    public String barCode;
    public String goodsName;
    public long id;
    public String logopicUrl;
    public double goodsPrice;
    public double promotionPrice;
    public double vipPrice;
    public double originalPrice;
    public AppbrandId appbrandId;
    public long reTime;
    public AppexpressId appexpressId;
    public int saleCount;
    public String logopicSl;
    public String goodsNewDetail;
    public String images;

    public AppgoodsId() {}

    protected AppgoodsId(Parcel in) {
        barCode = in.readString();
        goodsName = in.readString();
        id = in.readLong();
        logopicUrl = in.readString();
        goodsPrice = in.readDouble();
        promotionPrice = in.readDouble();
        vipPrice = in.readDouble();
        originalPrice = in.readDouble();
        appbrandId = in.readParcelable(AppbrandId.class.getClassLoader());
        reTime = in.readLong();
        appexpressId = in.readParcelable(AppexpressId.class.getClassLoader());
        saleCount = in.readInt();
        logopicSl = in.readString();
        goodsNewDetail = in.readString();
        images = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(barCode);
        dest.writeString(goodsName);
        dest.writeLong(id);
        dest.writeString(logopicUrl);
        dest.writeDouble(goodsPrice);
        dest.writeDouble(promotionPrice);
        dest.writeDouble(vipPrice);
        dest.writeDouble(originalPrice);
        dest.writeParcelable(appbrandId, flags);
        dest.writeLong(reTime);
        dest.writeParcelable(appexpressId, flags);
        dest.writeInt(saleCount);
        dest.writeString(logopicSl);
        dest.writeString(goodsNewDetail);
        dest.writeString(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppgoodsId> CREATOR = new Creator<AppgoodsId>() {
        @Override
        public AppgoodsId createFromParcel(Parcel in) {
            return new AppgoodsId(in);
        }

        @Override
        public AppgoodsId[] newArray(int size) {
            return new AppgoodsId[size];
        }
    };
}

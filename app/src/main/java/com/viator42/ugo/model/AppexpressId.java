package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppexpressId implements Parcelable {
    private long id;
    private String company;
    private double price;
    private String code;
    private String expressNo;

    public AppexpressId() {}

    protected AppexpressId(Parcel in) {
        id = in.readLong();
        company = in.readString();
        price = in.readDouble();
        code = in.readString();
        expressNo = in.readString();
    }

    public static final Creator<AppexpressId> CREATOR = new Creator<AppexpressId>() {
        @Override
        public AppexpressId createFromParcel(Parcel in) {
            return new AppexpressId(in);
        }

        @Override
        public AppexpressId[] newArray(int size) {
            return new AppexpressId[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(company);
        dest.writeDouble(price);
        dest.writeString(code);
        dest.writeString(expressNo);
    }
}

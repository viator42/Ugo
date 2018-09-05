package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppBrandAll implements Parcelable{
    public String brandName;
    public String detail;
    public int flag;
    public long id;
    public String intro;
    public String logopic;
    public String showpic;

    protected AppBrandAll() {}

    protected AppBrandAll(Parcel in) {
        brandName = in.readString();
        detail = in.readString();
        flag = in.readInt();
        id = in.readLong();
        intro = in.readString();
        logopic = in.readString();
        showpic = in.readString();
    }

    public static final Creator<AppBrandAll> CREATOR = new Creator<AppBrandAll>() {
        @Override
        public AppBrandAll createFromParcel(Parcel in) {
            return new AppBrandAll(in);
        }

        @Override
        public AppBrandAll[] newArray(int size) {
            return new AppBrandAll[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandName);
        dest.writeString(detail);
        dest.writeInt(flag);
        dest.writeLong(id);
        dest.writeString(intro);
        dest.writeString(logopic);
        dest.writeString(showpic);
    }
}

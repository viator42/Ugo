package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppbrandId implements Parcelable {
    public long id;
    public String logopic;
    public String brandName;
    public String detail;
    public String showpic;
    public int flag;
    public String intro;

    public AppbrandId(){}

    protected AppbrandId(Parcel in) {
        id = in.readLong();
        logopic = in.readString();
        brandName = in.readString();
        detail = in.readString();
        showpic = in.readString();
        flag = in.readInt();
        intro = in.readString();
    }

    public static final Creator<AppbrandId> CREATOR = new Creator<AppbrandId>() {
        @Override
        public AppbrandId createFromParcel(Parcel in) {
            return new AppbrandId(in);
        }

        @Override
        public AppbrandId[] newArray(int size) {
            return new AppbrandId[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(logopic);
        dest.writeString(brandName);
        dest.writeString(detail);
        dest.writeString(showpic);
        dest.writeInt(flag);
        dest.writeString(intro);
    }
}

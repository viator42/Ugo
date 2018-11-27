package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Brand implements Parcelable
{
    public long id;
    public String brandName;
    public String logopic;
    public String intro;
    public String detail;
    public String showpic;
    public int flag;

    protected Brand(Parcel in) {
        id = in.readLong();
        brandName = in.readString();
        logopic = in.readString();
        intro = in.readString();
        detail = in.readString();
        showpic = in.readString();
        flag = in.readInt();

    }

    public Brand() {
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(brandName);
        dest.writeString(logopic);
        dest.writeString(intro);
        dest.writeString(detail);
        dest.writeString(showpic);
        dest.writeInt(flag);

    }
}

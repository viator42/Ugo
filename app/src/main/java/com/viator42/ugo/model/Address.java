package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
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

    public Address() {
    }

    protected Address(Parcel in) {
        id = in.readLong();
        area = in.readString();
        consignee = in.readString();
        deaddress = in.readString();
        flag = in.readInt();
        addressId = in.readLong();
        latitude = in.readDouble();
        longitude = in.readDouble();
        mobile = in.readString();
        telephone = in.readString();
        zipcode = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(area);
        dest.writeString(consignee);
        dest.writeString(deaddress);
        dest.writeInt(flag);
        dest.writeLong(addressId);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(mobile);
        dest.writeString(telephone);
        dest.writeString(zipcode);
    }
}

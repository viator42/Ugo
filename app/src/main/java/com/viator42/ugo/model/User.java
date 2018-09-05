package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public long userId;
    public String sessionid;
    public String userName;
    public String mobile;
    public String pass;
    public String msg;
    public String headImg;
    public String defaultAddress;
    public long defaultAddressId;
    public String address;
    public String birthday;
    public Boolean success;
    public String token;
    public String place;
    public String area;
    public boolean bindingFlag;
    public String newUserId;

    public User() {}

    protected User(Parcel in) {
        userId = in.readLong();
        sessionid = in.readString();
        userName = in.readString();
        mobile = in.readString();
        pass = in.readString();
        msg = in.readString();
        headImg = in.readString();
        defaultAddress = in.readString();
        defaultAddressId = in.readLong();
        address = in.readString();
        birthday = in.readString();
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        token = in.readString();
        place = in.readString();
        area = in.readString();
        bindingFlag = in.readByte() != 0;
        newUserId = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(sessionid);
        dest.writeString(userName);
        dest.writeString(mobile);
        dest.writeString(pass);
        dest.writeString(msg);
        dest.writeString(headImg);
        dest.writeString(defaultAddress);
        dest.writeLong(defaultAddressId);
        dest.writeString(address);
        dest.writeString(birthday);
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        dest.writeString(token);
        dest.writeString(place);
        dest.writeString(area);
        dest.writeByte((byte) (bindingFlag ? 1 : 0));
        dest.writeString(newUserId);
    }
}

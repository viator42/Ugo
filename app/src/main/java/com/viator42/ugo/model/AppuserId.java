package com.viator42.ugo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/12/5.
 */
public class AppuserId implements Parcelable {
    private String userName;
    public long userId;
    public String newUserId;
    public String sessionid;

    protected AppuserId(Parcel in) {
        userId = in.readLong();
        userName = in.readString();
        newUserId = in.readString();
    }

    public AppuserId() {
    }

    public static final Creator<AppuserId> CREATOR = new Creator<AppuserId>() {
        @Override
        public AppuserId createFromParcel(Parcel in) {
            return new AppuserId(in);
        }

        @Override
        public AppuserId[] newArray(int size) {
            return new AppuserId[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(userName);
        dest.writeString(newUserId);
    }
}

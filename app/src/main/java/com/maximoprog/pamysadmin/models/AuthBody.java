package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthBody implements Parcelable {
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("password")
    private String password;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    public void readFromParcel(Parcel source) {
        this.username = source.readString();
        this.password = source.readString();
    }

    protected AuthBody(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<AuthBody> CREATOR = new Parcelable.Creator<AuthBody>() {
        @Override
        public AuthBody createFromParcel(Parcel source) {
            return new AuthBody(source);
        }

        @Override
        public AuthBody[] newArray(int size) {
            return new AuthBody[size];
        }
    };
}

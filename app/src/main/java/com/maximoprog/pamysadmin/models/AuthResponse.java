package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class AuthResponse implements Parcelable {

    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("jwt")
    private String jwt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.jwt);
    }

    public void readFromParcel(Parcel source) {
        this.user = source.readParcelable(User.class.getClassLoader());
        this.jwt = source.readString();
    }

    public AuthResponse() {
    }

    protected AuthResponse(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.jwt = in.readString();
    }

    public static final Parcelable.Creator<AuthResponse> CREATOR = new Parcelable.Creator<AuthResponse>() {
        @Override
        public AuthResponse createFromParcel(Parcel source) {
            return new AuthResponse(source);
        }

        @Override
        public AuthResponse[] newArray(int size) {
            return new AuthResponse[size];
        }
    };
}

package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vendor implements Parcelable {
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("company")
    private String company;
    @Expose
    @SerializedName("idVendor")
    private int idVendor;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.company);
        dest.writeInt(this.idVendor);
    }

    public void readFromParcel(Parcel source) {
        this.description = source.readString();
        this.company = source.readString();
        this.idVendor = source.readInt();
    }

    public Vendor() {
    }

    protected Vendor(Parcel in) {
        this.description = in.readString();
        this.company = in.readString();
        this.idVendor = in.readInt();
    }

    public static final Parcelable.Creator<Vendor> CREATOR = new Parcelable.Creator<Vendor>() {
        @Override
        public Vendor createFromParcel(Parcel source) {
            return new Vendor(source);
        }

        @Override
        public Vendor[] newArray(int size) {
            return new Vendor[size];
        }
    };
}

package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    @Expose
    @SerializedName("active")
    private boolean active;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("idCategory")
    private int idCategory;

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeString(this.description);
        dest.writeString(this.name);
        dest.writeInt(this.idCategory);
    }

    public void readFromParcel(Parcel source) {
        this.active = source.readByte() != 0;
        this.description = source.readString();
        this.name = source.readString();
        this.idCategory = source.readInt();
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.active = in.readByte() != 0;
        this.description = in.readString();
        this.name = in.readString();
        this.idCategory = in.readInt();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}

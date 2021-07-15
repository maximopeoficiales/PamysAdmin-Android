package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsImages implements Parcelable {
    @Expose
    @SerializedName("url")
    private String url;
    @Expose
    @SerializedName("idProduct")
    private int idProduct;
    @Expose
    @SerializedName("idProductImages")
    private int idProductImages;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProductImages() {
        return idProductImages;
    }

    public void setIdProductImages(int idProductImages) {
        this.idProductImages = idProductImages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.idProduct);
        dest.writeInt(this.idProductImages);
    }

    public void readFromParcel(Parcel source) {
        this.url = source.readString();
        this.idProduct = source.readInt();
        this.idProductImages = source.readInt();
    }

    public ProductsImages() {
    }

    protected ProductsImages(Parcel in) {
        this.url = in.readString();
        this.idProduct = in.readInt();
        this.idProductImages = in.readInt();
    }

    public static final Parcelable.Creator<ProductsImages> CREATOR = new Parcelable.Creator<ProductsImages>() {
        @Override
        public ProductsImages createFromParcel(Parcel source) {
            return new ProductsImages(source);
        }

        @Override
        public ProductsImages[] newArray(int size) {
            return new ProductsImages[size];
        }
    };
}

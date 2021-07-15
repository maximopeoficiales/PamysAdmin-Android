package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {


    @Expose
    @SerializedName("productsImages")
    private List<ProductsImages> productsImages;
    @Expose
    @SerializedName("vendor")
    private Vendor vendor;
    @Expose
    @SerializedName("category")
    private Category category;
    @Expose
    @SerializedName("stock")
    private int stock;
    @Expose
    @SerializedName("dateCreated")
    private String dateCreated;
    @Expose
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("salePrice")
    private double salePrice;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("slug")
    private String slug;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("idVendor")
    private int idVendor;
    @Expose
    @SerializedName("idCategory")
    private int idCategory;
    @Expose
    @SerializedName("idProduct")
    private int idProduct;

    public List<ProductsImages> getProductsImages() {
        return productsImages;
    }

    public void setProductsImages(List<ProductsImages> productsImages) {
        this.productsImages = productsImages;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.productsImages);
        dest.writeParcelable(this.vendor, flags);
        dest.writeParcelable(this.category, flags);
        dest.writeInt(this.stock);
        dest.writeString(this.dateCreated);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.description);
        dest.writeDouble(this.salePrice);
        dest.writeDouble(this.price);
        dest.writeString(this.slug);
        dest.writeString(this.name);
        dest.writeInt(this.idVendor);
        dest.writeInt(this.idCategory);
        dest.writeInt(this.idProduct);
    }

    public void readFromParcel(Parcel source) {
        this.productsImages = new ArrayList<ProductsImages>();
        source.readList(this.productsImages, ProductsImages.class.getClassLoader());
        this.vendor = source.readParcelable(Vendor.class.getClassLoader());
        this.category = source.readParcelable(Category.class.getClassLoader());
        this.stock = source.readInt();
        this.dateCreated = source.readString();
        this.thumbnailUrl = source.readString();
        this.description = source.readString();
        this.salePrice = source.readDouble();
        this.price = source.readDouble();
        this.slug = source.readString();
        this.name = source.readString();
        this.idVendor = source.readInt();
        this.idCategory = source.readInt();
        this.idProduct = source.readInt();
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.productsImages = new ArrayList<ProductsImages>();
        in.readList(this.productsImages, ProductsImages.class.getClassLoader());
        this.vendor = in.readParcelable(Vendor.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.stock = in.readInt();
        this.dateCreated = in.readString();
        this.thumbnailUrl = in.readString();
        this.description = in.readString();
        this.salePrice = in.readDouble();
        this.price = in.readDouble();
        this.slug = in.readString();
        this.name = in.readString();
        this.idVendor = in.readInt();
        this.idCategory = in.readInt();
        this.idProduct = in.readInt();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}

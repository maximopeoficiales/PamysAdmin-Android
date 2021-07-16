package com.maximoprog.pamysadmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @Expose
    @SerializedName("role")
    private Role role;
    @Expose
    @SerializedName("idRol")
    private int idRol;
    @Expose
    @SerializedName("active")
    private boolean active;
    @Expose
    @SerializedName("zip_code")
    private int zip_code;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("profilePictureUrl")
    private String profilePictureUrl;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("lastName")
    private String lastName;
    @Expose
    @SerializedName("firstName")
    private String firstName;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("idClient")
    private int idClient;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.role, flags);
        dest.writeInt(this.idRol);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeInt(this.zip_code);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.profilePictureUrl);
        dest.writeString(this.address);
        dest.writeString(this.lastName);
        dest.writeString(this.firstName);
        dest.writeString(this.password);
        dest.writeString(this.username);
        dest.writeInt(this.idClient);
    }

    public void readFromParcel(Parcel source) {
        this.role = source.readParcelable(Role.class.getClassLoader());
        this.idRol = source.readInt();
        this.active = source.readByte() != 0;
        this.zip_code = source.readInt();
        this.email = source.readString();
        this.phone = source.readString();
        this.profilePictureUrl = source.readString();
        this.address = source.readString();
        this.lastName = source.readString();
        this.firstName = source.readString();
        this.password = source.readString();
        this.username = source.readString();
        this.idClient = source.readInt();
    }

    public User() {
    }

    protected User(Parcel in) {
        this.role = in.readParcelable(Role.class.getClassLoader());
        this.idRol = in.readInt();
        this.active = in.readByte() != 0;
        this.zip_code = in.readInt();
        this.email = in.readString();
        this.phone = in.readString();
        this.profilePictureUrl = in.readString();
        this.address = in.readString();
        this.lastName = in.readString();
        this.firstName = in.readString();
        this.password = in.readString();
        this.username = in.readString();
        this.idClient = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

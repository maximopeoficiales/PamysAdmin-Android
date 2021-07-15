package com.maximoprog.pamysadmin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class AuthResponse {

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
}

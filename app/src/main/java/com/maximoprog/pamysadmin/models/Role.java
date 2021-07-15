package com.maximoprog.pamysadmin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("idRole")
    private int idRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}

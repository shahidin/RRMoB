package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseMaster {
    @SerializedName("ID")
    private String id;
    @SerializedName("NAME")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

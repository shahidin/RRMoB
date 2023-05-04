package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

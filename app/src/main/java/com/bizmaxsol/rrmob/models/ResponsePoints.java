package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponsePoints {
    @SerializedName("POINTS")
    private String points;

    @SerializedName("DDATE")
    private String date;

    public String getDate() {
        return date;
    }

    public String getPoints() {
        return points;
    }
}

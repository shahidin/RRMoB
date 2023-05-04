package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseDate {
    @SerializedName("SYS_DATE")
    private String date;

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ResponseDate{" +
                "date='" + date + '\'' +
                '}';
    }
}

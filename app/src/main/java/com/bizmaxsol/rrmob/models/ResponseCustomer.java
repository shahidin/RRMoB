package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseCustomer {
    @SerializedName("CUST_ID")
    private int custId;
    @SerializedName("CUSTOMER_NAME")
    private String cusName;
    @SerializedName("MOBILE_NO")
    private String cusMobile;

    public ResponseCustomer(int custId, String cusName, String cusMobile) {
        this.custId = custId;
        this.cusName = cusName;
        this.cusMobile = cusMobile;
    }

    public int getCustId() {
        return custId;
    }

    public String getCusName() {
        return cusName;
    }

    public String getCusMobile() {
        return cusMobile;
    }
}

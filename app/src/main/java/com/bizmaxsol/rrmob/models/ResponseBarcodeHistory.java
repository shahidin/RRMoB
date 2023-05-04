package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseBarcodeHistory {
    @SerializedName("DETAIL")
    private String bdetail;
    @SerializedName("SLNO")
    private String bslno;
    @SerializedName("DOC_DATE")
    private String bdocdate;
    @SerializedName("DOC_NUMBER")
    private String bdocnumber;
    @SerializedName("REMARKS")
    private String bremarks;
    @SerializedName("QTY")
    private String bqty;

    public String getBdetail() {
        return bdetail;
    }

    public String getBslno() {
        return bslno;
    }

    public String getBdocdate() {
        return bdocdate;
    }

    public String getBdocnumber() {
        return bdocnumber;
    }

    public String getBremarks() {
        return bremarks;
    }

    public String getBqty() {
        return bqty;
    }
}

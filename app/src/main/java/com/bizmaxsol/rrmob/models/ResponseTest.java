package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseTest {
    @SerializedName("I_D")
    private int pId;
    @SerializedName("DOC_NO")
    private String pdocNo;
    @SerializedName("BILL_NO")
    private String pbillNo;
    @SerializedName("BILL_DATE")
    private String pbillDate;

    public ResponseTest(int pId, String pdocNo, String pbillNo, String pbillDate) {
        this.pId = pId;
        this.pdocNo = pdocNo;
        this.pbillNo = pbillNo;
        this.pbillDate = pbillDate;
    }

    public int getpId() {
        return pId;
    }

    public String getPdocNo() {
        return pdocNo;
    }

    public String getPbillNo() {
        return pbillNo;
    }

    public String getPbillDate() {
        return pbillDate;
    }
}

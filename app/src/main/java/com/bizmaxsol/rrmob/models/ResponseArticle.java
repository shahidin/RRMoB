package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseArticle {
    @SerializedName("BILL_DATE")
    private String aRbillDate;
    @SerializedName("INVOICE_NO")
    private String aRinvoiceNo;
    @SerializedName("BARCODE")
    private String aRbarcode;
    @SerializedName("ITEM")
    private String aRitem;
    @SerializedName("BRAND")
    private String aRbrand;
    @SerializedName("SZ")
    private String aRsz;
    @SerializedName("COLOR")
    private String aRcolor;
    @SerializedName("MRP")
    private String aRmrp;
    @SerializedName("QTY")
    private String aRqty;
    @SerializedName("DISC_PU")
    private String aRdiscPu;
    @SerializedName("GROSS_AMOUNT")
    private String aRgrossAmount;
    @SerializedName("STOCK")
    private String aRstock;

    public String getaRbillDate() {
        return aRbillDate;
    }

    public String getaRinvoiceNo() {
        return aRinvoiceNo;
    }

    public String getaRbarcode() {
        return aRbarcode;
    }

    public String getaRitem() {
        return aRitem;
    }

    public String getaRbrand() {
        return aRbrand;
    }

    public String getaRsz() {
        return aRsz;
    }

    public String getaRcolor() {
        return aRcolor;
    }

    public String getaRmrp() {
        return aRmrp;
    }

    public String getaRqty() {
        return aRqty;
    }

    public String getaRdiscPu() {
        return aRdiscPu;
    }

    public String getaRgrossAmount() {
        return aRgrossAmount;
    }

    public String getaRstock() {
        return aRstock;
    }
}

package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponsePurchase {
    @SerializedName("I_D")
    private String pId;
    @SerializedName("DOC_NO")
    private String pdocNo;
    @SerializedName("BILL_NO")
    private String pbillNo;
    @SerializedName("BILL_DATE")
    private String pbillDate;
    @SerializedName("SUPPLIER")
    private String psupplier;
    @SerializedName("TOT_QTY")
    private double ptotQty;
    @SerializedName("TOT_AMOUNT")
    private double ptotAmount;
    @SerializedName("ITEM")
    private String pItem;
    @SerializedName("STYLE")
    private String pstyle;
    @SerializedName("BRAND")
    private String pbrand;
    @SerializedName("QTY")
    private double pqty;
    @SerializedName("PRICE")
    private double pprice;
    @SerializedName("AMOUNT")
    private double pamount;
    @SerializedName("TAX_AMOUNT")
    private double ptax;

    public String getpId() {
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

    public String getPsupplier() {
        return psupplier;
    }

    public double getPtotQty() {
        return ptotQty;
    }

    public double getPtotAmount() {
        return ptotAmount;
    }

    public String getpItem() {
        return pItem;
    }

    public String getPstyle() {
        return pstyle;
    }

    public String getPbrand() {
        return pbrand;
    }

    public double getPqty() {
        return pqty;
    }

    public double getPprice() {
        return pprice;
    }

    public double getPamount() {
        return pamount;
    }

    public double getPtax() { return ptax; }
}

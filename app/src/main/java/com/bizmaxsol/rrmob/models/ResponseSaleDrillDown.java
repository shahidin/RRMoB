package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseSaleDrillDown {
//    "LAMC_SDEPT":"SAGAR",
//    "LAMC_SDIVISION":"SAREE",
//    "LAMC_SSECTION":"MATCHING",
//    "TOT_QTY":788,
//    "TOT_AMOUNT":3898470
    @SerializedName("DEPARTMENT")
    private String lamcSdept;
    @SerializedName("DIVISION")
    private String lamcSdivision;
    @SerializedName("SECTION")
    private String lamcSsection;
    @SerializedName("TOT_QTY")
    private double totQty;
    @SerializedName("TOT_AMOUNT")
    private double totAmount;
    @SerializedName("ITEM_NAME")
    private String sName;
    @SerializedName("ARTICAL")
    private String article;
    @SerializedName("BRAND")
    private String brand;
    @SerializedName("SUPPLIER")
    private String supplier;


    public String getLamcSdept() {
        return lamcSdept;
    }

    public String getLamcSdivision() {
        return lamcSdivision;
    }

    public String getLamcSsection() {
        return lamcSsection;
    }

    public double getTotQty() {
        return totQty;
    }

    public double getTotAmount() {
        return totAmount;
    }

    public String getsName() {
        return sName;
    }

    public String getArticle() {
        return article;
    }

    public String getBrand() {
        return brand;
    }

    public String getSupplier() {
        return supplier;
    }
}

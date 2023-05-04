package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseOC {

    @SerializedName("DEPARTMENT")
    private String department;
    @SerializedName("DIVISION")
    private String division;
    @SerializedName("SECTION")
    private String section;
    @SerializedName("ITEM")
    private String item;
    @SerializedName("BRAND")
    private String brand;
    @SerializedName("ART_NO")
    private String art_no;
    @SerializedName("SZ")
    private String sz;
    @SerializedName("COLOR")
    private String color;
    @SerializedName("OPN_QTY")
    private String opn_qty;
    @SerializedName("OPN_VALUE")
    private String opn_value;
    @SerializedName("PURC_QTY")
    private String purc_qty;
    @SerializedName("PURC_VALUE")
    private String purc_value;
    @SerializedName("PURCRET_QTY")
    private String purcret_qty;
    @SerializedName("PURCRET_VALUE")
    private String purcret_value;
    @SerializedName("SALES_QTY")
    private String sales_qty;
    @SerializedName("SALES_VALUE")
    private String sales_value;
    @SerializedName("CHALLAN_IN_QTY")
    private String challan_in_qty;
    @SerializedName("CHALLAN_IN_VALUE")
    private String challan_in_value;
    @SerializedName("CHALLAN_OUT_QTY")
    private String challan_out_qty;
    @SerializedName("CHALLAN_OUT_VALUE")
    private String challan_out_value;
    @SerializedName("CLOSING_QTY")
    private String closing_qty;
    @SerializedName("CLOSING_VALUE")
    private String closing_value;
    @SerializedName("IN_QTY")
    private String in_qty;
    @SerializedName("IN_VALUE")
    private String in_value;
    @SerializedName("OUT_QTY")
    private String out_qty;
    @SerializedName("OUT_VALUE")
    private String out_value;

    public String getDepartment() {
        return department;
    }

    public String getDivision() {
        return division;
    }

    public String getSection() {
        return section;
    }

    public String getItem() {
        return item;
    }

    public String getBrand() {
        return brand;
    }

    public String getArt_no() {
        return art_no;
    }

    public String getSz() {
        return sz;
    }

    public String getColor() {
        return color;
    }

    public String getOpn_qty() {
        return opn_qty;
    }

    public String getOpn_value() {
        return opn_value;
    }

    public String getPurc_qty() {
        return purc_qty;
    }

    public String getPurc_value() {
        return purc_value;
    }

    public String getPurcret_qty() {
        return purcret_qty;
    }

    public String getPurcret_value() {
        return purcret_value;
    }

    public String getSales_qty() {
        return sales_qty;
    }

    public String getSales_value() {
        return sales_value;
    }

    public String getChallan_in_qty() {
        return challan_in_qty;
    }

    public String getChallan_in_value() {
        return challan_in_value;
    }

    public String getChallan_out_qty() {
        return challan_out_qty;
    }

    public String getChallan_out_value() {
        return challan_out_value;
    }

    public String getClosing_qty() {
        return closing_qty;
    }

    public String getClosing_value() {
        return closing_value;
    }

    public String getIn_qty() {
        return in_qty;
    }

    public String getIn_value() {
        return in_value;
    }

    public String getOut_qty() {
        return out_qty;
    }

    public String getOut_value() {
        return out_value;
    }
}

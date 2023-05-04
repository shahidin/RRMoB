package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseBarcodeDetail {
    //    { name: 'SUPPLIER' },
    //    { name: 'BRAND' },
    //    { name: 'DEPARTMENT' },
    //    { name: 'DIVISION' },
    //    { name: 'SECTION' },
    //    { name: 'ITEM' },
    //    { name: 'STYLE' },
    //    { name: 'SZ' },
    //    { name: 'COLOR' },
    //    { name: 'PP' },
    //    { name: 'MRP' }
    @SerializedName("SUPPLIER")
    private String bsupplier;
    @SerializedName("BRAND")
    private String bbrand;
    @SerializedName("DEPARTMENT")
    private String bdepartment;
    @SerializedName("DIVISION")
    private String bdivision;
    @SerializedName("SECTION")
    private String bsection;
    @SerializedName("ITEM")
    private String bitem;
    @SerializedName("STYLE")
    private String bstyle;
    @SerializedName("SZ")
    private String bsize;
    @SerializedName("COLOR")
    private String bcolor;
    @SerializedName("PP")
    private String bpp;
    @SerializedName("MRP")
    private String bmrp;

    public String getBsupplier() {
        return bsupplier;
    }

    public String getBbrand() {
        return bbrand;
    }

    public String getBdepartment() {
        return bdepartment;
    }

    public String getBdivision() {
        return bdivision;
    }

    public String getBsection() {
        return bsection;
    }

    public String getBitem() {
        return bitem;
    }

    public String getBstyle() {
        return bstyle;
    }

    public String getBsize() {
        return bsize;
    }

    public String getBcolor() {
        return bcolor;
    }

    public String getBpp() {
        return bpp;
    }

    public String getBmrp() {
        return bmrp;
    }
}

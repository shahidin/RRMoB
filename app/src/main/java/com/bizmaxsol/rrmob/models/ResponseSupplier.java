package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseSupplier {
    @SerializedName("SUP_ID")
    private String supplierId;
    @SerializedName("SUPPLIER_NAME")
    private String supplierName;

    public String
    getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }
}

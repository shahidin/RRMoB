package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class TransactionRights {

    @SerializedName("USERTRANRIGHTAPP_NUSERID")
    private int usertranright_nuserid;
    @SerializedName("USERTRANRIGHTAPP_NTRANID")
    private int usertranright_ntranid;
    @SerializedName("USERTRANRIGHTAPP_NALLOWED")
    private int usertranright_nallowed;
    @SerializedName("USERTRANAPP_NID")
    private int usertran_nid;
    @SerializedName("USERTRANAPP_NMODID")
    private String usertran_nmodid;
    @SerializedName("USERTRANAPP_SCAPTION")
    private String usertran_scaption;
    @SerializedName("USERTRANAPP_NTYPE")
    private int usertran_ntype;
    @SerializedName("USERTRANAPP_SIMAGEURI")
    private String usertran_simageuri;
    @SerializedName("USERTRANAPP_SIMAGERESOURCEID")
    private String usertran_simageresourceid;

    public int getUsertranright_nuserid() {
        return usertranright_nuserid;
    }

    public int getUsertranright_ntranid() {
        return usertranright_ntranid;
    }

    public int getUsertranright_nallowed() {
        return usertranright_nallowed;
    }

    public int getUsertran_nid() {
        return usertran_nid;
    }

    public String getUsertran_nmodid() {
        return usertran_nmodid;
    }

    public String getUsertran_scaption() {
        return usertran_scaption;
    }

    public int getUsertran_ntype() {
        return usertran_ntype;
    }

    public String getUsertran_simageuri() {
        return usertran_simageuri;
    }

    public String getUsertran_simageresourceid() {
        return usertran_simageresourceid;
    }
}

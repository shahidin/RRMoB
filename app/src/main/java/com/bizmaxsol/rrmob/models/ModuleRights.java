package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ModuleRights {

    @SerializedName("USERMODULEAPP_SID")
    private String usermodule_sid;
    @SerializedName("USERMODULEAPP_SCAPTION")
    private String usermodule_scaption;
    @SerializedName("usermodule_sIMAGEURI")
    private String usermodule_simageuri;
    @SerializedName("usermodule_sIMAGERESOURCEID")
    private int usermodule_simageresourceid;

    public String getUsermodule_sid() {
        return usermodule_sid;
    }

    public String getUsermodule_scaption() {
        return usermodule_scaption;
    }

    public String getUsermodule_simageuri() {
        return usermodule_simageuri;
    }

    public int getUsermodule_simageresourceid() {
        return usermodule_simageresourceid;
    }

}

package com.bizmaxsol.rrmob.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Client {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "CLIENT_SID")
    @SerializedName("CLIENT_SID")
    private String client_sid;

    @ColumnInfo(name = "CLIENT_SNAME")
    @SerializedName("CLIENT_SNAME")
    private String client_sname;

    @ColumnInfo(name = "CLIENT_DVALIDATE")
    @SerializedName("CLIENT_DVALIDATE")
    private String client_dvalidate;

    @ColumnInfo(name = "BEARING_SDESC")
    @SerializedName("BEARING_SDESC")
    private String bearing_sdesc;

    @ColumnInfo(name = "BEARING_SURL")
    @SerializedName("BEARING_SURL")
    private String bearing_surl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient_sid() {
        return client_sid;
    }

    public void setClient_sid(String client_sid) {
        this.client_sid = client_sid;
    }

    public String getClient_sname() {
        return client_sname;
    }

    public void setClient_sname(String client_sname) {
        this.client_sname = client_sname;
    }

    public String getClient_dvalidate() {
        return client_dvalidate;
    }

    public void setClient_dvalidate(String client_dvalidate) {
        this.client_dvalidate = client_dvalidate;
    }

    public String getBearing_sdesc() {
        return bearing_sdesc;
    }

    public void setBearing_sdesc(String bearing_sdesc) {
        this.bearing_sdesc = bearing_sdesc;
    }

    public String getBearing_surl() {
        return bearing_surl;
    }

    public void setBearing_surl(String bearing_surl) {
        this.bearing_surl = bearing_surl;
    }
}

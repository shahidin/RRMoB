package com.bizmaxsol.rrmob.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ResponseUser {

    @PrimaryKey
    private int id;
    @SerializedName("USERSAPP_NID")
    @ColumnInfo(name = "USERSAPP_NID")
    private String usersapp_nid;
    @SerializedName("USERSAPP_SNAME")
    @ColumnInfo(name = "USERSAPP_SNAME")
    private String usersapp_sname;
    @SerializedName("USERSAPP_NTYPE")
    @ColumnInfo(name = "USERSAPP_NTYPE")
    private String usersapp_ntype;
    @SerializedName("USERSAPP_SPASSWORD")
    private String usersapp_spassword;
    @SerializedName("USERSAPP_SRECQ")
    private String usersapp_srecq;
    @SerializedName("USERSAPP_SRECA")
    private String usersapp_sreca;
    @SerializedName("USERSAPP_NBACKUP_PIN")
    private String usersapp_nbackup_pin;
    @SerializedName("USERSAPP_NACTIVEFLG")
    @ColumnInfo(name = "USERSAPP_NACTIVEFLG")
    private String usersapp_nactiveflg;
    @SerializedName("USERSAPP_SLOGIN")
    @ColumnInfo(name = "USERSAPP_SLOGIN")
    private String usersapp_slogin;
    @SerializedName("USERSAPP_NPRIMARYPASS")
    @ColumnInfo(name = "USERSAPP_NPRIMARYPASS")
    private String usersapp_nprimarypass;
    @SerializedName("USERSAPP_NPINSET")
    @ColumnInfo(name = "USERSAPP_NPINSET")
    private String usersapp_npinset;


    public ResponseUser(String usersapp_nid, String usersapp_spassword, String usersapp_srecq, String usersapp_sreca, String usersapp_nbackup_pin, String usersapp_slogin) {
        this.usersapp_nid = usersapp_nid;
        this.usersapp_spassword = usersapp_spassword;
        this.usersapp_srecq = usersapp_srecq;
        this.usersapp_sreca = usersapp_sreca;
        this.usersapp_nbackup_pin = usersapp_nbackup_pin;
        this.usersapp_slogin = usersapp_slogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsersapp_nid() {
        return usersapp_nid;
    }

    public void setUsersapp_nid(String usersapp_nid) {
        this.usersapp_nid = usersapp_nid;
    }

    public String getUsersapp_sname() {
        return usersapp_sname;
    }

    public void setUsersapp_sname(String usersapp_sname) {
        this.usersapp_sname = usersapp_sname;
    }

    public String getUsersapp_ntype() {
        return usersapp_ntype;
    }

    public void setUsersapp_ntype(String usersapp_ntype) {
        this.usersapp_ntype = usersapp_ntype;
    }

    public String getUsersapp_spassword() {
        return usersapp_spassword;
    }

    public void setUsersapp_spassword(String usersapp_spassword) {
        this.usersapp_spassword = usersapp_spassword;
    }

    public String getUsersapp_srecq() {
        return usersapp_srecq;
    }

    public void setUsersapp_srecq(String usersapp_srecq) {
        this.usersapp_srecq = usersapp_srecq;
    }

    public String getUsersapp_sreca() {
        return usersapp_sreca;
    }

    public void setUsersapp_sreca(String usersapp_sreca) {
        this.usersapp_sreca = usersapp_sreca;
    }

    public String getUsersapp_nbackup_pin() {
        return usersapp_nbackup_pin;
    }

    public void setUsersapp_nbackup_pin(String usersapp_nbackup_pin) {
        this.usersapp_nbackup_pin = usersapp_nbackup_pin;
    }

    public String getUsersapp_nactiveflg() {
        return usersapp_nactiveflg;
    }

    public void setUsersapp_nactiveflg(String usersapp_nactiveflg) {
        this.usersapp_nactiveflg = usersapp_nactiveflg;
    }

    public String getUsersapp_slogin() {
        return usersapp_slogin;
    }

    public void setUsersapp_slogin(String usersapp_slogin) {
        this.usersapp_slogin = usersapp_slogin;
    }

    public String getUsersapp_nprimarypass() {
        return usersapp_nprimarypass;
    }

    public void setUsersapp_nprimarypass(String usersapp_nprimarypass) {
        this.usersapp_nprimarypass = usersapp_nprimarypass;
    }

    public String getUsersapp_npinset() {
        return usersapp_npinset;
    }

    public void setUsersapp_npinset(String usersapp_npinset) {
        this.usersapp_npinset = usersapp_npinset;
    }
}

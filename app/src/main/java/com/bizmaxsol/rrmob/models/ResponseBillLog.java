package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseBillLog {

//    BILLDATE":"01/06/2020",
//    "BILLNO":"SS20//29",
//    "DESCRIPTION":"103597 SHIRTING  RAWAL PINDI",
//    "SOLDQTY":32.5,
//    "RATE":6240,
//    "ITEMDISC":0,
//    "NETITEMAMOUNT":6240,
//    "USERNAME":"SS",
//    "CHANGEDATETIME":"11-JUN-20 07:06 PM",
//    "CHANGEREMARKS":"WROND BILL

    @SerializedName("BILLDATE")
    private String billdate;
    @SerializedName("BILLNO")
    private String billdno;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("SOLDQTY")
    private double soldqty;
    @SerializedName("RATE")
    private double rate;
    @SerializedName("ITEMDISC")
    private double itemdisc;
    @SerializedName("NETITEMAMOUNT")
    private double netitemamount;
    @SerializedName("USERNAME")
    private String username;
    @SerializedName("CHANGEDATETIME")
    private String changedatetime;
    @SerializedName("CHANGEREMARKS")
    private String changeremarks;

    Boolean isSelected = false;

    public String getBilldate() {
        return billdate;
    }

    public String getBilldno() {
        return billdno;
    }

    public String getDescription() {
        return description;
    }

    public double getSoldqty() {
        return soldqty;
    }

    public double getRate() {
        return rate;
    }

    public double getItemdisc() {
        return itemdisc;
    }

    public double getNetitemamount() {
        return netitemamount;
    }

    public String getUsername() {
        return username;
    }

    public String getChangedatetime() {
        return changedatetime;
    }

    public String getChangeremarks() {
        return changeremarks;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "ResponseBillLog{" +
                "billdate=" + billdate +
                ", billdno=" + billdno +
                ", description=" + description +
                ", soldqty=" + soldqty +
                ", rate=" + rate +
                ", itemdisc=" + itemdisc +
                ", netitemamount=" + netitemamount +
                ", username=" + username +
                ", changedatetime=" + changedatetime +
                ", changeremarks=" + changeremarks +
                '}';
    }
}

package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseSaleSession {


    @SerializedName("SESSN_NID")
    private String id;
    @SerializedName("POS_NO")
    private String posNo;

    @SerializedName("CASHIER")
    private String cashier;
    @SerializedName("BILL_COUNT")
    private String billCount;

    @SerializedName("ITEM_TOTAL")
    private double itemTotal;
    @SerializedName("ITEM_DISC")
    private double itemDisc;

    @SerializedName("BILL_AMT")
    private double billAmt;
    @SerializedName("BILL_DISC")
    private  double billDisc;

    @SerializedName("CASH_PAID")
    private double cashPaid;
    @SerializedName("CREDIT_CARD_PAID")
    private double creditCardPaid;

    @SerializedName("TOT_QTY")
    private double totQty;
    @SerializedName("MONEY_REFUND")
    private double moneyRefund;

    @SerializedName("CREDIT_NOTE_ADJUSTED")
    private double creditNoteAdjusted;
    @SerializedName("CREDIT_NOTE_ISSUED")
    private double creditNoteIssued;


    @SerializedName("CREDIT_SALE")
    private double creditSale;

    public String getId() {
        return id;
    }

    public String getPosNo() {
        return posNo;
    }

    public String getCashier() {
        return cashier;
    }

    public String getBillCount() {
        return billCount;
    }

    public double getTotQty() {
        return totQty;
    }

    public double getBillDisc() {
        return billDisc;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public double getItemDisc() {
        return itemDisc;
    }

    public double getBillAmt() {
        return billAmt;
    }

    public double getCashPaid() {
        return cashPaid;
    }

    public double getCreditNoteAdjusted() {
        return creditNoteAdjusted;
    }

    public double getCreditCardPaid() {
        return creditCardPaid;
    }

    public double getMoneyRefund() {
        return moneyRefund;
    }

    public double getCreditNoteIssued() {
        return creditNoteIssued;
    }

    public double getCreditSale() {
        return creditSale;
    }

    @Override
    public String toString() {
        return "ResponseSaleSession{" +
                "id=" + id +
                ", posNo='" + posNo + '\'' +
                ", cashier='" + cashier + '\'' +
                ", billCount='" + billCount + '\'' +
                ", totQty=" + totQty +
                ", billDisc=" + billDisc +
                ", itemTotal=" + itemTotal +
                ", itemDisc=" + itemDisc +
                ", billAmt=" + billAmt +
                ", cashPaid=" + cashPaid +
                ", creditNoteAdjusted=" + creditNoteAdjusted +
                ", creditCardPaid=" + creditCardPaid +
                ", moneyRefund=" + moneyRefund +
                ", creditNoteIssued=" + creditNoteIssued +
                ", creditSale=" + creditSale +
                '}';
    }
}

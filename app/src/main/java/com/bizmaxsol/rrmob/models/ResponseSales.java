package com.bizmaxsol.rrmob.models;

import com.google.gson.annotations.SerializedName;

public class ResponseSales {
//     "TRAN_TYPE": "Sale",
//             "INVOICE_NO": "21/22-/459",
//             "TOT_QTY": 1,
//             "ITEM_TOTAL": 765,
//             "ITEM_DISC": 0,
//             "BILL_DISC": 0,
//             "BILL_AMT": 765,
//             "CASH_PAID": 765,
//             "CREDIT_NOTE_ADJUSTED": 0,
//             "CREDIT_CARD_PAID": 0,
//             "MONEY_REFUND": 0,
//             "CREDIT_NOTE_ISSUED": 0,
//             "CREDIT_SALE": 0

    @SerializedName("TRAN_TYPE")
    private String tranType;
    @SerializedName("INVOICE_NO")
    private String invoiceNo;
    @SerializedName("TOT_QTY")
    private double totQty;
    @SerializedName("ITEM_TOTAL")
    private double itemTotal;
    @SerializedName("ITEM_DISC")
    private double itemDisc;
    @SerializedName("BILL_DISC")
    private double billDisc;
    @SerializedName("BILL_AMT")
    private double billAmt;
    @SerializedName("CASH_PAID")
    private double cashPaid;
    @SerializedName("CREDIT_NOTE_ADJUSTED")
    private double creditNoteAdjusted;
    @SerializedName("CREDIT_CARD_PAID")
    private double bankPaid;
    @SerializedName("MONEY_REFUND")
    private double moneyRefund;
    @SerializedName("CREDIT_NOTE_ISSUED")
    private double creditNoteIssued;
    @SerializedName("CREDIT_SALE")
    private double creditSale;

    public String getTranType() {
        return tranType;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public double getTotQty() {
        return totQty;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public double getItemDisc() {
        return itemDisc;
    }

    public double getBillDisc() {
        return billDisc;
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

    public double getBankPaid() {
        return bankPaid;
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
        return "ResposeSales{" +
                "tranType='" + tranType + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", totQty=" + totQty +
                ", itemTotal=" + itemTotal +
                ", itemDisc=" + itemDisc +
                ", billDisc=" + billDisc +
                ", billAmt=" + billAmt +
                ", cashPaid=" + cashPaid +
                ", creditNoteAdjusted=" + creditNoteAdjusted +
                ", bankPaid=" + bankPaid +
                ", moneyRefund=" + moneyRefund +
                ", creditNoteIssued=" + creditNoteIssued +
                ", creditSale=" + creditSale +
                '}';
    }
}

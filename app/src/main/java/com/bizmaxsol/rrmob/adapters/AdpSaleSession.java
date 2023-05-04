package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseSaleSession;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdpSaleSession extends BaseAdapter {
    private final Context context;
    private final List<ResponseSaleSession> lineList;

    public AdpSaleSession(Context context, List<ResponseSaleSession> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseSaleSession responseSaleSession) {
        lineList.add(responseSaleSession);
        notifyDataSetChanged();

    }

    @Override
    public Object getItem(int position) {
        return lineList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.row_sales_session, parent, false);
        }



        // get current sale to be displayed
        ResponseSaleSession currentSale = (ResponseSaleSession) getItem(position);


        TextView textRowPosNo = convertView.findViewById(R.id.textRowPosNo);
        TextView textRowCashier = convertView.findViewById(R.id.textRowCashier);
        TextView textRowBillCount = convertView.findViewById(R.id.textRowBillCount);
        TextView textRowItemDiscount = convertView.findViewById(R.id.textRowItemDiscount);
        TextView textRowItemTotal = convertView.findViewById(R.id.textRowItemTotal);
        TextView textRowBillDisc = convertView.findViewById(R.id.textRowBillDisc);
        TextView textRowBillAmt = convertView.findViewById(R.id.textRowBillAmt);
        TextView textRowTotalQty = convertView.findViewById(R.id.textRowTotalQty);
        TextView textRowMoneyRefund = convertView.findViewById(R.id.textRowMoneyRefund);
        TextView textRowCashPaid = convertView.findViewById(R.id.textRowCashPaid);
        TextView textRowBankPaid = convertView.findViewById(R.id.textRowBankPaid);
        TextView textRowCreditNoteIssued = convertView.findViewById(R.id.textRowCreditNoteIssued);
        TextView textRowCreditSale = convertView.findViewById(R.id.textRowCreditSale);
        TextView textRowCreditNoteAdjusted = convertView.findViewById(R.id.textRowCreditNoteAdjusted);

        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);

        textRowPosNo.setText("POS NO: " + currentSale.getPosNo());
        textRowCashier.setText(currentSale.getCashier());
        textRowBillCount.setText(currentSale.getBillCount());
        textRowItemDiscount.setText(form.format(currentSale.getItemDisc()));
        textRowItemTotal.setText(form.format(currentSale.getItemTotal()));
        textRowBillDisc.setText(form.format(currentSale.getBillDisc()));
        textRowBillAmt.setText(form.format(currentSale.getBillAmt()));
        textRowTotalQty.setText(form.format(currentSale.getTotQty()));
        textRowMoneyRefund.setText(form.format(currentSale.getMoneyRefund()));
        textRowCashPaid.setText(form.format(currentSale.getCashPaid()));
        textRowBankPaid.setText(form.format(currentSale.getCreditCardPaid()));
        textRowCreditNoteIssued.setText(form.format(currentSale.getCreditNoteIssued()));
        textRowCreditSale.setText(form.format(currentSale.getCreditSale()));
        textRowCreditNoteAdjusted.setText(form.format(currentSale.getCreditNoteAdjusted()));

        return convertView;
    }
}

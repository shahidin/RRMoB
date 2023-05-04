package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseSales;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdpSalesDetail extends BaseAdapter {
    private final Context context;
    private final List<ResponseSales> lineList;

    public AdpSalesDetail(Context context, List<ResponseSales> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseSales responseSales) {
        lineList.add(responseSales);
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
                    inflate(R.layout.row_sales_detail, parent, false);
        }



        // get current sale to be displayed
        ResponseSales currentSale = (ResponseSales) getItem(position);


        TextView textRowSTranType = convertView.findViewById(R.id.textRowSTranType);
        TextView textRowSInvoiceNo = convertView.findViewById(R.id.textRowSInvoiceNo);
        TextView textRowSTotQty = convertView.findViewById(R.id.textRowSTotQty);
        TextView textRowSItemTotal = convertView.findViewById(R.id.textRowSItemTotal);
        TextView textRowSItemDisc = convertView.findViewById(R.id.textRowSItemDisc);
        TextView textRowSBillDisc = convertView.findViewById(R.id.textRowSBillDisc);
        TextView textRowSBillAmt = convertView.findViewById(R.id.textRowSBillAmt);
        TextView textRowSCash = convertView.findViewById(R.id.textRowSCash);
        TextView textRowSCNA = convertView.findViewById(R.id.textRowSCNA);
        TextView textRowSBank = convertView.findViewById(R.id.textRowSBank);
        TextView textRowSMoneyRefund = convertView.findViewById(R.id.textRowSMoneyRefund);
        TextView textRowSCNI = convertView.findViewById(R.id.textRowSCNI);
        TextView textRowSCRSale = convertView.findViewById(R.id.textRowSCRSale);

        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);


        textRowSTranType.setText(currentSale.getTranType());
        textRowSInvoiceNo.setText(currentSale.getInvoiceNo());
        textRowSTotQty.setText(form.format(currentSale.getTotQty()));
        textRowSItemTotal.setText(form.format(currentSale.getItemTotal()));
        textRowSItemDisc.setText(form.format(currentSale.getItemDisc()));
        textRowSBillDisc.setText(form.format(currentSale.getBillDisc()));
        textRowSBillAmt.setText(form.format(currentSale.getBillAmt()));
        textRowSCash.setText(form.format(currentSale.getCashPaid()));
        textRowSCNA.setText(form.format(currentSale.getCreditNoteAdjusted()));
        textRowSBank.setText(form.format(currentSale.getBankPaid()));
        textRowSMoneyRefund.setText(form.format(currentSale.getMoneyRefund()));
        textRowSCNI.setText(form.format(currentSale.getCreditNoteIssued()));
        textRowSCRSale.setText(form.format(currentSale.getCreditSale()));

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        return convertView;
    }
}

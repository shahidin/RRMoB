package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponsePurchase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdpPurchase extends BaseAdapter {
    private final Context context;
    private final List<ResponsePurchase> lineList;

    public AdpPurchase(Context context, List<ResponsePurchase> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponsePurchase responsePurchase) {
        lineList.add(responsePurchase);
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
                    inflate(R.layout.row_purchase, parent, false);
        }



        // get current sale to be displayed
        ResponsePurchase currentSale = (ResponsePurchase) getItem(position);


        TextView textViewpdate = convertView.findViewById(R.id.textViewpdate);
        TextView textViewpbill = convertView.findViewById(R.id.textViewpbill);
        TextView textViewpdoc = convertView.findViewById(R.id.textViewpdoc);
        TextView textViewpsupplier = convertView.findViewById(R.id.textViewpsupplier);
        TextView textViewpqty = convertView.findViewById(R.id.textViewpqty);
        TextView textViewpamt = convertView.findViewById(R.id.textViewpamt);


        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);
        textViewpdate.setText(currentSale.getPbillDate());
        textViewpbill.setText(currentSale.getPbillNo());
        textViewpdoc.setText(currentSale.getPdocNo());
        textViewpsupplier.setText(currentSale.getPsupplier());
        textViewpqty.setText(form.format(currentSale.getPtotQty()));
        textViewpamt.setText(form.format(currentSale.getPtotAmount()));


        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        return convertView;
    }
}

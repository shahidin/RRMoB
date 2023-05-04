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

public class AdpPurchaseChild extends BaseAdapter {
    private final Context context;
    private final List<ResponsePurchase> lineList;

    public AdpPurchaseChild(Context context, List<ResponsePurchase> lineList) {
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
                    inflate(R.layout.row_purchase_child, parent, false);
        }



        // get current sale to be displayed
        ResponsePurchase currentSale = (ResponsePurchase) getItem(position);


        TextView textViewpcItem = convertView.findViewById(R.id.textViewpcItem);
        TextView textViewpcBrand = convertView.findViewById(R.id.textViewpcBrand);
        TextView textViewpcStyle = convertView.findViewById(R.id.textViewpcStyle);
        TextView textViewpcQty = convertView.findViewById(R.id.textViewpcQty);
        TextView textViewpcPrice = convertView.findViewById(R.id.textViewpcPrice);
        TextView textViewpcAmt = convertView.findViewById(R.id.textViewpcAmt);


        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);
        textViewpcItem.setText(currentSale.getpItem());
        textViewpcBrand.setText(currentSale.getPbrand());
        textViewpcStyle.setText(currentSale.getPstyle());
        textViewpcQty.setText(form.format(currentSale.getPqty()));
        textViewpcPrice.setText(form.format(currentSale.getPprice()));
        textViewpcAmt.setText(form.format(currentSale.getPamount()));


        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        return convertView;
    }
}

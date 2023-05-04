package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseSaleDrillDown;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdpSalesDrillDown2 extends BaseAdapter {
    private final Context context;
    private final List<ResponseSaleDrillDown> lineList;

    public AdpSalesDrillDown2(Context context, List<ResponseSaleDrillDown> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseSaleDrillDown responseSales) {
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
                    inflate(R.layout.row_sales_drill_down_2, parent, false);
        }




        // get current sale to be displayed
        ResponseSaleDrillDown currentSale = (ResponseSaleDrillDown) getItem(position);

        TextView rowSDD2TotQty = convertView.findViewById(R.id.rowSDD2TotQty);
        TextView rowSDD2TotAmt = convertView.findViewById(R.id.rowSDD2TotAmt);
        TextView rowSDD2ItemName = convertView.findViewById(R.id.rowSDD2ItemName);




        DecimalFormat form = new DecimalFormat("0.00");

        rowSDD2TotQty.setText(form.format(currentSale.getTotQty()));
        rowSDD2TotAmt.setText(form.format(currentSale.getTotAmount()));
        rowSDD2ItemName.setText(currentSale.getsName());


            if (position % 2 == 0) {
                convertView.setBackgroundColor(Color.WHITE);
            } else {
                convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
            }
//        }

        return convertView;
    }
}

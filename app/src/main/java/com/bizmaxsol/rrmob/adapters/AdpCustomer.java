package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseCustomer;

import java.util.ArrayList;
import java.util.List;

public class AdpCustomer extends BaseAdapter {
    private final Context context;
    private final List<ResponseCustomer> lineList;

    public AdpCustomer(Context context, List<ResponseCustomer> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseCustomer responseCustomer) {
        lineList.add(responseCustomer);
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
                    inflate(R.layout.row_customer_list, parent, false);
        }




        // get current sale to be displayed
        ResponseCustomer currentSale = (ResponseCustomer) getItem(position);

        TextView textViewRowSNoCus = convertView.findViewById(R.id.textViewRowSNoCus);
        TextView textViewNameCus = convertView.findViewById(R.id.textViewNameCus);
        TextView textViewMobCus = convertView.findViewById(R.id.textViewMobCus);


        textViewRowSNoCus.setText((position +1) + ".");
        textViewNameCus.setText(currentSale.getCusName());
        textViewMobCus.setText(currentSale.getCusMobile());



//        if (position % 2 == 0) {
//            convertView.setBackgroundColor(Color.WHITE);
//        } else {
//                convertView.setBackgroundColor(Color.parseColor("#e0e0e0"));
//        }

        return convertView;
    }
}

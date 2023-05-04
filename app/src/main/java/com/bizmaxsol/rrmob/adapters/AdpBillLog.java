package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseBillLog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdpBillLog extends BaseAdapter {
    private final Context context;
    private final List<ResponseBillLog> lineList;

    public AdpBillLog(Context context, List<ResponseBillLog> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseBillLog responseBillLog) {
        lineList.add(responseBillLog);
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
                    inflate(R.layout.row_bill_log, parent, false);
        }



        // get current sale to be displayed
        ResponseBillLog currentSale = (ResponseBillLog) getItem(position);



        TextView row_date = convertView.findViewById(R.id.row_date);
        TextView row_bill_no = convertView.findViewById(R.id.row_bill_no);
        TextView row_dis = convertView.findViewById(R.id.row_dis);
        TextView row_rate = convertView.findViewById(R.id.row_rate);
        TextView row_qty = convertView.findViewById(R.id.row_qty);
        TextView row_username = convertView.findViewById(R.id.row_username);
        TextView row_net_item_amount = convertView.findViewById(R.id.row_net_item_amount);
        TextView row_item_dis = convertView.findViewById(R.id.row_item_dis);
        TextView row_change_remark = convertView.findViewById(R.id.row_change_remark);
        TextView row_change_date_time = convertView.findViewById(R.id.row_change_date_time);

        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);
                row_date.setText(currentSale.getBilldate());
        row_bill_no.setText(currentSale.getBilldno());
        row_dis.setText(currentSale.getDescription());
        row_rate.setText(form.format(currentSale.getRate()));
        row_qty.setText(form.format(currentSale.getSoldqty()));
        row_username.setText(currentSale.getUsername());
        row_net_item_amount.setText(form.format(currentSale.getNetitemamount()));
        row_item_dis.setText(form.format(currentSale.getItemdisc()));
        row_change_remark.setText(currentSale.getChangeremarks());
        row_change_date_time.setText(currentSale.getChangedatetime());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        return convertView;
    }
}

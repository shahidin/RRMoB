package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseBarcodeHistory;

import java.util.ArrayList;
import java.util.List;

public class AdpBarcodeHistory extends BaseAdapter {
    private final Context context;
    private final List<ResponseBarcodeHistory> lineList;

    public AdpBarcodeHistory(Context context, List<ResponseBarcodeHistory> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseBarcodeHistory responseBarcodeHistory) {
        lineList.add(responseBarcodeHistory);
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
                    inflate(R.layout.row_barcode_history, parent, false);
        }

        // get current sale to be displayed
        ResponseBarcodeHistory currentSale = (ResponseBarcodeHistory) getItem(position);


        TextView textViewBHdate = convertView.findViewById(R.id.textViewBHdate);
        TextView textViewBHdocnumer = convertView.findViewById(R.id.textViewBHdocnumer);
        TextView textViewBHdetail = convertView.findViewById(R.id.textViewBHdetail);
        TextView textViewBHqty = convertView.findViewById(R.id.textViewBHqty);
        TextView textViewBHremark = convertView.findViewById(R.id.textViewBHremark);

        convertView.setBackgroundColor(Color.WHITE);

        textViewBHdate.setText(currentSale.getBdocdate());
        textViewBHdocnumer.setText(currentSale.getBdocnumber());
        textViewBHdetail.setText(currentSale.getBdetail());
        textViewBHqty.setText(currentSale.getBqty());
        textViewBHremark.setText(currentSale.getBremarks());

        return convertView;
    }
}

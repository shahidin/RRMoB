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

public class AdpSalesDrillDown3 extends BaseAdapter {
    private final Context context;
    private final List<ResponseSaleDrillDown> lineList;
    int mBrand;
    int mSupplier;

    public AdpSalesDrillDown3(Context context, List<ResponseSaleDrillDown> lineList ,int brand,int supplier) {
        this.context = context;
        this.lineList=lineList;
        this.mBrand=brand;
        this.mSupplier=supplier;
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
                    inflate(R.layout.row_sales_drill_down_3, parent, false);
        }




        // get current sale to be displayed
        ResponseSaleDrillDown currentSale = (ResponseSaleDrillDown) getItem(position);

        TextView rowSDD3TotQty = convertView.findViewById(R.id.rowSDD3TotQty);
        TextView rowSDD3TotAmt = convertView.findViewById(R.id.rowSDD3TotAmt);
        TextView rowSDD3Article = convertView.findViewById(R.id.rowSDD3Article);
        TextView rowSDD3Brand = convertView.findViewById(R.id.rowSDD3Brand);
        TextView rowSDD3Supplier = convertView.findViewById(R.id.rowSDD3Supplier);

        DecimalFormat form = new DecimalFormat("0.00");

        rowSDD3TotQty.setText(form.format(currentSale.getTotQty()));
        rowSDD3TotAmt.setText(form.format(currentSale.getTotAmount()));
        rowSDD3Article.setText(currentSale.getArticle());
        rowSDD3Brand.setText(currentSale.getBrand());
        rowSDD3Supplier.setText(currentSale.getSupplier());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        if(mBrand==1 && mSupplier==0){
            rowSDD3Supplier.setVisibility(View.GONE);
            rowSDD3Brand.setVisibility(View.VISIBLE);
        }
        else if(mBrand==0 && mSupplier==1){
            rowSDD3Supplier.setVisibility(View.VISIBLE);
            rowSDD3Brand.setVisibility(View.GONE);
        }
        else if(mBrand==0 && mSupplier==0){
            rowSDD3Supplier.setVisibility(View.GONE);
            rowSDD3Brand.setVisibility(View.GONE);
        }
        else{
            rowSDD3Supplier.setVisibility(View.VISIBLE);
            rowSDD3Brand.setVisibility(View.VISIBLE);
        }









        return convertView;
    }
}

package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseArticle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdpArticleSale extends BaseAdapter {
    private final Context context;
    private final List<ResponseArticle> lineList;

    public AdpArticleSale(Context context, List<ResponseArticle> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseArticle responseArticle) {
        lineList.add(responseArticle);
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
                    inflate(R.layout.row_article_sales, parent, false);
        }



        // get current sale to be displayed
        ResponseArticle currentSale = (ResponseArticle) getItem(position);


        TextView textViewAstockDate = convertView.findViewById(R.id.textViewAstockDate);
        TextView textViewAstockInvoice = convertView.findViewById(R.id.textViewAstockInvoice);
        TextView textViewAstockBarcode = convertView.findViewById(R.id.textViewAstockBarcode);
        TextView textViewAstockItem = convertView.findViewById(R.id.textViewAstockItem);
        TextView textViewAstockBrand = convertView.findViewById(R.id.textViewAstockBrand);
        TextView textViewAstockSize = convertView.findViewById(R.id.textViewAstockSize);
        TextView textViewAstockColor = convertView.findViewById(R.id.textViewAstockColor);
        TextView textViewAstockQty = convertView.findViewById(R.id.textViewAstockQty);
        TextView textViewAstockDisc = convertView.findViewById(R.id.textViewAstockDisc);
        TextView textViewAstockGross = convertView.findViewById(R.id.textViewAstockGross);
        TextView textViewAstockMrp = convertView.findViewById(R.id.textViewAstockMrp);


        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);

        textViewAstockDate.setText(currentSale.getaRbillDate());
        textViewAstockInvoice.setText(currentSale.getaRinvoiceNo());
        textViewAstockBarcode.setText(currentSale.getaRbarcode());
        textViewAstockItem.setText(currentSale.getaRitem());
        textViewAstockBrand.setText(currentSale.getaRbrand());
        textViewAstockSize.setText(currentSale.getaRsz());
        textViewAstockColor.setText(currentSale.getaRcolor());
        textViewAstockQty.setText(currentSale.getaRqty());
        textViewAstockDisc.setText(currentSale.getaRdiscPu());
        textViewAstockGross.setText(currentSale.getaRgrossAmount());
        textViewAstockMrp.setText(currentSale.getaRmrp());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        return convertView;
    }
}

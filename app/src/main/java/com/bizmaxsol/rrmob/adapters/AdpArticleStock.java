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

public class AdpArticleStock extends BaseAdapter {
    private final Context context;
    private final List<ResponseArticle> lineList;

    public AdpArticleStock(Context context, List<ResponseArticle> lineList) {
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
                    inflate(R.layout.row_article_stock, parent, false);
        }



        // get current sale to be displayed
        ResponseArticle currentSale = (ResponseArticle) getItem(position);


        TextView textViewAsaleItem = convertView.findViewById(R.id.textViewAsaleItem);
        TextView textViewAsaleStock = convertView.findViewById(R.id.textViewAsaleStock);
        TextView textViewAsaleColor = convertView.findViewById(R.id.textViewAsaleColor);
        TextView textViewAsaleSize = convertView.findViewById(R.id.textViewAsaleSize);
        TextView textViewAsaleBrand = convertView.findViewById(R.id.textViewAsaleBrand);


        DecimalFormat form = new DecimalFormat("0.00");
        convertView.setBackgroundColor(Color.WHITE);

        textViewAsaleItem.setText(currentSale.getaRitem());
        textViewAsaleStock.setText(currentSale.getaRstock());
        textViewAsaleColor.setText(currentSale.getaRcolor());
        textViewAsaleSize.setText(currentSale.getaRsz());
        textViewAsaleBrand.setText(currentSale.getaRbrand());


        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#BBF7FF"));
        }

        return convertView;
    }
}

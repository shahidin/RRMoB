package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponsePoints;

import java.util.ArrayList;

public class AdpPointsDetails extends BaseAdapter {
    private final Context context;
    private final ArrayList<ResponsePoints> lineList;

    public AdpPointsDetails(Context context, ArrayList<ResponsePoints> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponsePoints responsePoints) {
        lineList.add(responsePoints);
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
                    inflate(R.layout.row_points_details, parent, false);
        }



        // get current sale to be displayed
        ResponsePoints currentSale = (ResponsePoints) getItem(position);



        TextView textViewPointsDate = convertView.findViewById(R.id.textViewPointsDate);
        TextView textViewPointsMode = convertView.findViewById(R.id.textViewPointsMode);
        TextView textViewPointsNo = convertView.findViewById(R.id.textViewPointsNo);



        textViewPointsDate.setText(currentSale.getDate());
        textViewPointsNo.setText(currentSale.getPoints());
        double points=Double.parseDouble(currentSale.getPoints());
        if(points<0){
            textViewPointsMode.setText("Redeemed");
            textViewPointsMode.setBackgroundColor(Color.rgb(255, 71, 93));
        }else{
            textViewPointsMode.setText("Earned");
            textViewPointsMode.setBackgroundColor(Color.rgb(145,255,128));
        }




        return convertView;
    }
}

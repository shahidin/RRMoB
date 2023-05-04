package com.bizmaxsol.rrmob.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ResponseArticle;
import com.bizmaxsol.rrmob.models.ResponseOC;

import java.text.DecimalFormat;
import java.util.List;

public class AdpOC extends BaseAdapter {
    private final Context context;
    private final List<ResponseOC> lineList;

    public AdpOC(Context context, List<ResponseOC> lineList) {
        this.context = context;
        this.lineList=lineList;
    }

    @Override
    public int getCount() {
        return lineList.size();
    }
    public void setLineList(ResponseOC responseOC) {
        lineList.add(responseOC);
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
                    inflate(R.layout.row_oc, parent, false);
        }



        // get current sale to be displayed
        ResponseOC currentSale = (ResponseOC) getItem(position);


        TextView textViewDepartment = convertView.findViewById(R.id.textViewDepartment);
        TextView textViewDIVISION = convertView.findViewById(R.id.textViewDIVISION);
        TextView textViewSECTION = convertView.findViewById(R.id.textViewSECTION);
        TextView textViewITEM = convertView.findViewById(R.id.textViewITEM);
        TextView textViewBRAND = convertView.findViewById(R.id.textViewBRAND);
        TextView textViewART_NO = convertView.findViewById(R.id.textViewART_NO);
        TextView textViewSZ = convertView.findViewById(R.id.textViewSZ);
        TextView textViewCOLOR = convertView.findViewById(R.id.textViewCOLOR);
        TextView textViewIN_QTY = convertView.findViewById(R.id.textViewIN_QTY);
        TextView textViewIN_VALUE = convertView.findViewById(R.id.textViewIN_VALUE);
        TextView textViewOUT_QTY = convertView.findViewById(R.id.textViewOUT_QTY);
        TextView textViewOUT_VALUE = convertView.findViewById(R.id.textViewOUT_VALUE);
        TextView textViewPURC_QTY = convertView.findViewById(R.id.textViewPURC_QTY);
        TextView textViewPURC_VALUE = convertView.findViewById(R.id.textViewPURC_VALUE);
        TextView textViewPURCRET_QTY = convertView.findViewById(R.id.textViewPURCRET_QTY);
        TextView textViewPURCRET_VALUE = convertView.findViewById(R.id.textViewPURCRET_VALUE);
        TextView textViewSALES_QTY = convertView.findViewById(R.id.textViewSALES_QTY);
        TextView textViewSALES_VALUE = convertView.findViewById(R.id.textViewSALES_VALUE);
        TextView textViewCHALLAN_IN_QTY = convertView.findViewById(R.id.textViewCHALLAN_IN_QTY);
        TextView textViewCHALLAN_IN_VALUE = convertView.findViewById(R.id.textViewCHALLAN_IN_VALUE);
        TextView textViewCHALLAN_OUT_QTY = convertView.findViewById(R.id.textViewCHALLAN_OUT_QTY);
        TextView textViewCHALLAN_OUT_VALUE = convertView.findViewById(R.id.textViewCHALLAN_OUT_VALUE);
        TextView textViewOPN_QTY = convertView.findViewById(R.id.textViewOPN_QTY);
        TextView textViewOPN_VALUE = convertView.findViewById(R.id.textViewOPN_VALUE);
        TextView textViewCLOSING_QTY = convertView.findViewById(R.id.textViewCLOSING_QTY);
        TextView textViewCLOSING_VALUE = convertView.findViewById(R.id.textViewCLOSING_VALUE);


        LinearLayout llDivSec = convertView.findViewById(R.id.llDivSec);
        LinearLayout llItemBrand = convertView.findViewById(R.id.llItemBrand);
        LinearLayout llArtSzCol = convertView.findViewById(R.id.llArtSzCol);
        LinearLayout llIN = convertView.findViewById(R.id.llIN);
        LinearLayout llOut = convertView.findViewById(R.id.llOut);
        LinearLayout llPur = convertView.findViewById(R.id.llPur);
        LinearLayout llPurrec = convertView.findViewById(R.id.llPurrec);
        LinearLayout llSale = convertView.findViewById(R.id.llSale);
        LinearLayout llChalnIn = convertView.findViewById(R.id.llChalnIn);
        LinearLayout llChalnOut = convertView.findViewById(R.id.llChalnOut);



        textViewDepartment.setText("DEPARTMENT: "+currentSale.getDepartment());
        textViewOPN_QTY.setText("OPN QTY: "+currentSale.getOpn_qty());
        textViewOPN_VALUE.setText("OPN VALUE: "+currentSale.getOpn_value());
        textViewCLOSING_QTY.setText("CLOSING QTY: "+currentSale.getClosing_qty());
        textViewCLOSING_VALUE.setText("CLOSING VALUE: "+currentSale.getClosing_value());


        if(currentSale.getDivision()==null){
            textViewDIVISION.setText("");
        }else{
            textViewDIVISION.setText("DIVISION: "+currentSale.getDivision());
        }
        if(currentSale.getSection()==null){
            textViewSECTION.setText("");
        }else{
            textViewSECTION.setText("SECTION: "+currentSale.getSection());
        }
        if(currentSale.getItem()==null){
            textViewITEM.setText("");
        }else{
            textViewITEM.setText("ITEM: "+currentSale.getItem());
        }
        if(currentSale.getBrand()==null){
            textViewBRAND.setText("");
        }else{
            textViewBRAND.setText("BRAND: "+currentSale.getBrand());
        }
        if(currentSale.getArt_no()==null){
            textViewART_NO.setText("");
        }else{
            textViewART_NO.setText("ARTICLE: "+currentSale.getArt_no());
        }
        if(currentSale.getSz()==null){
            textViewSZ.setText("");
        }else{
            textViewSZ.setText("SIZE: "+currentSale.getSz());
        }
        if(currentSale.getColor()==null){
            textViewCOLOR.setText("");
        }else{
            textViewCOLOR.setText("COLOUR: "+currentSale.getColor());
        }
        if(currentSale.getIn_qty()==null){
            llIN.setVisibility(View.GONE);
        }else{
            textViewIN_QTY.setText("IN QTY: "+currentSale.getIn_qty());
        }
        if(currentSale.getIn_value()==null){
            textViewIN_VALUE.setText("");
        }else{
            textViewIN_VALUE.setText("IN VALUE: "+currentSale.getIn_value());
        }
        if(currentSale.getOut_qty()==null){
            llOut.setVisibility(View.GONE);
        }else{
            textViewOUT_QTY.setText("OUT QTY: "+currentSale.getOut_qty());
        }
        if(currentSale.getOut_value()==null){
            textViewOUT_VALUE.setText("");
        }else{
            textViewOUT_VALUE.setText("OUT VALUE: "+currentSale.getOut_value());
        }
        if(currentSale.getPurc_qty()==null){
            llPur.setVisibility(View.GONE);
        }else{
            textViewPURC_QTY.setText("PURC QTY: "+currentSale.getPurc_qty());
        }
        if(currentSale.getPurc_value()==null){
            textViewPURC_VALUE.setText("");
        }else{
            textViewPURC_VALUE.setText("PURC VALUE: "+currentSale.getPurc_value());
        }
        if(currentSale.getPurcret_qty()==null){
            llPurrec.setVisibility(View.GONE);
        }else{
            textViewPURCRET_QTY.setText("PUR_RET QTY: "+currentSale.getPurcret_qty());
        }
        if(currentSale.getPurcret_value()==null){
            textViewPURCRET_VALUE.setText("");
        }else{
            textViewPURCRET_VALUE.setText("PUR_RET VALUE: "+currentSale.getPurcret_value());
        }
        if(currentSale.getSales_qty()==null){
            llSale.setVisibility(View.GONE);
        }else{
            textViewSALES_QTY.setText("SALE QTY: "+currentSale.getSales_qty());
        }
        if(currentSale.getSales_value()==null){
            textViewSALES_VALUE.setText("");
        }else{
            textViewSALES_VALUE.setText("SALE VALUE: "+currentSale.getSales_value());
        }
        if(currentSale.getChallan_in_qty()==null){
            llChalnIn.setVisibility(View.GONE);
        }else{
            textViewCHALLAN_IN_QTY.setText("CHALN_IN QTY: "+currentSale.getChallan_in_qty());
        }
        if(currentSale.getChallan_in_value()==null){
            textViewCHALLAN_IN_VALUE.setText("");
        }else{
            textViewCHALLAN_IN_VALUE.setText("CHALN_IN VALUE: "+currentSale.getChallan_in_value());
        }
        if(currentSale.getChallan_out_qty()==null){
            llChalnOut.setVisibility(View.GONE);
        }else{
            textViewCHALLAN_OUT_QTY.setText("CHALN_OUT QTY: "+currentSale.getChallan_out_qty());
        }
        if(currentSale.getChallan_out_value()==null){
            textViewCHALLAN_OUT_VALUE.setText("");
        }else{
            textViewCHALLAN_OUT_VALUE.setText("CHALN_OUT VALUE: "+currentSale.getChallan_out_value());
        }


        if(currentSale.getDivision()==null && currentSale.getSection()==null){
            llDivSec.setVisibility(View.GONE);
        }
        if(currentSale.getItem()==null && currentSale.getBrand()==null){
            llItemBrand.setVisibility(View.GONE);
        }
        if(currentSale.getArt_no()==null && currentSale.getSz()==null && currentSale.getColor()==null){
            llArtSzCol.setVisibility(View.GONE);
        }


        return convertView;
    }
}

package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.adapters.AdpBillLog;
import com.bizmaxsol.rrmob.adapters.AdpSaleSession;
import com.bizmaxsol.rrmob.adapters.AdpSalesDetail;
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.databinding.FragmentDailySalesBinding;
import com.bizmaxsol.rrmob.models.ResponseSales;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DailySalesFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentDailySalesBinding fragmentDailySalesBinding;
    DatePickerDialog.OnDateSetListener salefromdate;
    Calendar myCalendar = Calendar.getInstance();
    List<ResponseSales> saleList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDailySalesBinding=FragmentDailySalesBinding.inflate(inflater,container,false);
        return fragmentDailySalesBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        salefromdate = (view1, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelfromDate();
        };

        fragmentDailySalesBinding.dateDailySales.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentDailySalesBinding.dateDailySales.setText(mainViewModel.getResponseDate().getValue().getDate());
        loadlist(mainViewModel.getResponseDate().getValue().getDate());

        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentDailySalesBinding.dateDailySales.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    loadlist(fragmentDailySalesBinding.dateDailySales.getText().toString());
                }
        });



        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String fdate){
            mainViewModel.getDailySales(fdate).observe(getViewLifecycleOwner(), sales -> {
                if (sales != null) {
                    saleList=sales;
                    AdpSalesDetail adpSalesDetail = new AdpSalesDetail(requireContext(), sales);
                    adpSalesDetail.notifyDataSetChanged();
                    fragmentDailySalesBinding.listviewSaleDetail.setAdapter(adpSalesDetail);
                    setTotal();
                }else{
                    error();
                }
            });
    }
    public void setTotal(){

        double dTtotQty=0;
        double dTitemTotal=0;
        double dTitemDisc=0;
        double dTbillDisc=0;
        double dTbillAmt=0;
        double dTcash=0;
        double dTCNAdj=0;
        double dTbank=0;
        double dTrefund=0;
        double dTCNIssued=0;
        double dTCreditSales=0;

        for(ResponseSales responseSales:saleList){
            dTtotQty=dTtotQty+responseSales.getTotQty();
            dTitemTotal=dTitemTotal+responseSales.getItemTotal();
            dTitemDisc=dTitemDisc+responseSales.getItemDisc();
            dTbillDisc=dTbillDisc+responseSales.getBillDisc();
            dTbillAmt=dTbillAmt+responseSales.getBillAmt();
            dTcash=dTcash+responseSales.getCashPaid();
            dTCNAdj=dTCNAdj+responseSales.getCreditNoteAdjusted();
            dTbank=dTbank+responseSales.getBankPaid();
            dTrefund=dTrefund+responseSales.getMoneyRefund();
            dTCNIssued=dTCNIssued+responseSales.getCreditNoteIssued();
            dTCreditSales=dTCreditSales+responseSales.getCreditSale();
        }
        DecimalFormat form = new DecimalFormat("0.00");
        fragmentDailySalesBinding.TtotQty.setText(form.format(dTtotQty));
        fragmentDailySalesBinding.TitemTotal.setText(form.format(dTitemTotal));
        fragmentDailySalesBinding.TitemDisc.setText(form.format(dTitemDisc));
        fragmentDailySalesBinding.TbillDisc.setText(form.format(dTbillDisc));
        fragmentDailySalesBinding.TbillAmt.setText(form.format(dTbillAmt));
        fragmentDailySalesBinding.Tcash.setText(form.format(dTcash));
        fragmentDailySalesBinding.TCNAdj.setText(form.format(dTCNAdj));
        fragmentDailySalesBinding.Tbank.setText(form.format(dTbank));
        fragmentDailySalesBinding.Trefund.setText(form.format(dTrefund));
        fragmentDailySalesBinding.TCNIssued.setText(form.format(dTCNIssued));
        fragmentDailySalesBinding.TCreditSales.setText(form.format(dTCreditSales));
    }
    private void updateLabelfromDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentDailySalesBinding.dateDailySales.setText(sdf.format(myCalendar.getTime()));
    }
    private void error() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(requireContext());
        dialog.setTitle("No Data Found");
        dialog.setIcon(R.drawable.ic_warning);
        dialog.setPositiveButton("Ok",
                (dialog12, which) -> {
                } );
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}

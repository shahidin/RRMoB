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
import com.bizmaxsol.rrmob.databinding.FragmentSaleSessionBinding;
import com.bizmaxsol.rrmob.models.ResponseSales;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SaleSessnFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentSaleSessionBinding fragmentSaleSessionBinding;
    DatePickerDialog.OnDateSetListener salefromdate;
    Calendar myCalendar = Calendar.getInstance();
    int stat;
    int mPosition;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSaleSessionBinding=FragmentSaleSessionBinding.inflate(inflater,container,false);
        return fragmentSaleSessionBinding.getRoot();
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
        fragmentSaleSessionBinding.dateSaleSession.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        fragmentSaleSessionBinding.dateSaleSession.setText(mainViewModel.getResponseDate().getValue().getDate());
        loadlist(mainViewModel.getResponseDate().getValue().getDate());

        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentSaleSessionBinding.dateSaleSession.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else {
                    loadlist(fragmentSaleSessionBinding.dateSaleSession.getText().toString());
                }
        });
        fragmentSaleSessionBinding.listview.setOnItemClickListener((parent, view1, position, id) -> {
            mPosition=position;
            ((RightsFrag)getParentFragment()).ListClickBehave();
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cview.setOnClickListener(v->{
            String salesessnId=mainViewModel.getSalesessn().getValue().get(mPosition).getId();
            mainViewModel.getSaleSessnDetail(salesessnId).observe(getViewLifecycleOwner(), sales->{
                if(sales!=null){
                    fragmentSaleSessionBinding.saleSessonView.setVisibility(View.GONE);
                    fragmentSaleSessionBinding.salesessnDetailView.setVisibility(View.VISIBLE);
                    ((RightsFrag)getParentFragment()).ViewButtonBehave();
                    AdpSalesDetail adpSaleSession = new AdpSalesDetail(requireContext(), sales);
                    adpSaleSession.notifyDataSetChanged();
                    fragmentSaleSessionBinding.listviewSaleDetail.setAdapter(adpSaleSession);
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

                        for(ResponseSales responseSales:sales){
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
                        fragmentSaleSessionBinding.TtotQty.setText(form.format(dTtotQty));
                        fragmentSaleSessionBinding.TitemTotal.setText(form.format(dTitemTotal));
                        fragmentSaleSessionBinding.TitemDisc.setText(form.format(dTitemDisc));
                        fragmentSaleSessionBinding.TbillDisc.setText(form.format(dTbillDisc));
                        fragmentSaleSessionBinding.TbillAmt.setText(form.format(dTbillAmt));
                        fragmentSaleSessionBinding.Tcash.setText(form.format(dTcash));
                        fragmentSaleSessionBinding.TCNAdj.setText(form.format(dTCNAdj));
                        fragmentSaleSessionBinding.Tbank.setText(form.format(dTbank));
                        fragmentSaleSessionBinding.Trefund.setText(form.format(dTrefund));
                        fragmentSaleSessionBinding.TCNIssued.setText(form.format(dTCNIssued));
                        fragmentSaleSessionBinding.TCreditSales.setText(form.format(dTCreditSales));
                    }
            });
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cback.setOnClickListener(v->{
            fragmentSaleSessionBinding.saleSessonView.setVisibility(View.VISIBLE);
            fragmentSaleSessionBinding.salesessnDetailView.setVisibility(View.GONE);
            ((RightsFrag)getParentFragment()).BackButtonBehave();
        });
        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String date){
            mainViewModel.getSaleSessn(date).observe(getViewLifecycleOwner(), billLogs -> {
                if (billLogs != null) {
                    AdpSaleSession adpSaleSession = new AdpSaleSession(requireContext(), billLogs);
                    adpSaleSession.notifyDataSetChanged();
                    fragmentSaleSessionBinding.listview.setAdapter(adpSaleSession);
                    fragmentSaleSessionBinding.listview.setSelector(R.color.teal_700);
                }else{
                    error();
                }
            });
    }
    private void updateLabelfromDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentSaleSessionBinding.dateSaleSession.setText(sdf.format(myCalendar.getTime()));
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

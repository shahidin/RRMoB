package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.adapters.AdpBillLog;
import com.bizmaxsol.rrmob.adapters.AdpSalesDetail;
import com.bizmaxsol.rrmob.adapters.AdpSalesDrillDown1;
import com.bizmaxsol.rrmob.adapters.AdpSalesDrillDown2;
import com.bizmaxsol.rrmob.adapters.AdpSalesDrillDown3;
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.databinding.FragmentSalesDrillDownBinding;
import com.bizmaxsol.rrmob.models.ResponseSaleDrillDown;
import com.bizmaxsol.rrmob.models.ResponseSales;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SalesDrillDownFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentSalesDrillDownBinding fragmentSalesDrillDownBinding;
    DatePickerDialog.OnDateSetListener salefromdate;
    DatePickerDialog.OnDateSetListener saletodate;
    Calendar myCalendar = Calendar.getInstance();
    int mPosition;
    int checkDD=1;
    int checkBrand;
    int checkSupplier;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSalesDrillDownBinding=FragmentSalesDrillDownBinding.inflate(inflater,container,false);
        return fragmentSalesDrillDownBinding.getRoot();
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
        saletodate = (view1, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabeltoDate();
        };
        fragmentSalesDrillDownBinding.fromDate2.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentSalesDrillDownBinding.toDate2.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), saletodate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        String ndate=mainViewModel.getResponseDate().getValue().getDate().replace("/","-");
        fragmentSalesDrillDownBinding.fromDate2.setText(ndate);
        fragmentSalesDrillDownBinding.toDate2.setText(ndate);
        loadlist(ndate,ndate);

        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentSalesDrillDownBinding.fromDate2.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else if(fragmentSalesDrillDownBinding.toDate2.getText().toString().trim().equals("TO DATE")){
                    new DatePickerDialog(requireContext(), saletodate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    loadlist(fragmentSalesDrillDownBinding.fromDate2.getText().toString(),
                            fragmentSalesDrillDownBinding.toDate2.getText().toString());
                }
        });
        fragmentSalesDrillDownBinding.listviewSalesDrillDown.setOnItemClickListener((parent, view1, position, id) -> {
            if(checkDD==1){
                mPosition=position;
                ((RightsFrag)getParentFragment()).ListClickBehave();
            }else if(checkDD==2){
                mPosition=position;
                ((RightsFrag)getParentFragment()).ListClickBehave();
            }
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cview.setOnClickListener(v->{
            if(checkDD==1){
                String sectionId=mainViewModel.getSaleDD1().getValue().get(mPosition).getLamcSsection();
                mainViewModel.getSaleDD2list(fragmentSalesDrillDownBinding.fromDate2.getText().toString(),
                        fragmentSalesDrillDownBinding.toDate2.getText().toString(),
                        sectionId,
                        fragmentSalesDrillDownBinding.editTextTopN.getText().toString()).observe(getViewLifecycleOwner(), sales->{
                    if(sales!=null){
                        fragmentSalesDrillDownBinding.editTextTopN.setEnabled(true);
                        fragmentSalesDrillDownBinding.switchBrand.setEnabled(true);
                        fragmentSalesDrillDownBinding.switchSupplier.setEnabled(true);
                        fragmentSalesDrillDownBinding.sDDtop2.setText("ITEM NAME");
                        fragmentSalesDrillDownBinding.sDDtop3.setVisibility(View.GONE);
                        fragmentSalesDrillDownBinding.sDDtop4.setVisibility(View.GONE);
                        AdpSalesDrillDown2 adpSalesDrillDown2=new AdpSalesDrillDown2(requireContext(),sales);
                        fragmentSalesDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown2);
                        ((RightsFrag)getParentFragment()).ViewButtonBehave();
                        checkDD=2;
                    }
                });
            }else if(checkDD==2){
                boolean bBrand=fragmentSalesDrillDownBinding.switchBrand.isChecked();
                boolean bSupplier=fragmentSalesDrillDownBinding.switchSupplier.isChecked();
                if(bBrand){
                    checkBrand=1;
                    fragmentSalesDrillDownBinding.sDDtop3.setVisibility(View.VISIBLE);
                    fragmentSalesDrillDownBinding.sDDtop3.setGravity(Gravity.CENTER);
                    fragmentSalesDrillDownBinding.sDDtop3.setText("BRAND");
                }
                else{
                    checkBrand=0;
                    fragmentSalesDrillDownBinding.sDDtop3.setVisibility(View.GONE);
                }
                if(bSupplier){
                    checkSupplier=1;
                    fragmentSalesDrillDownBinding.sDDtop4.setVisibility(View.VISIBLE);
                    fragmentSalesDrillDownBinding.sDDtop4.setGravity(Gravity.CENTER);
                    fragmentSalesDrillDownBinding.sDDtop4.setText("SUPPLIER");
                }
                else{
                    checkSupplier=0;
                    fragmentSalesDrillDownBinding.sDDtop4.setVisibility(View.GONE);
                }
                fragmentSalesDrillDownBinding.sDDtop2.setText("ARTICLE");

                String itemName=mainViewModel.getSaleDD2().getValue().get(mPosition).getsName();
                mainViewModel.getSaleDD3list(fragmentSalesDrillDownBinding.fromDate2.getText().toString(),
                        fragmentSalesDrillDownBinding.toDate2.getText().toString(),
                        itemName,checkBrand,checkSupplier,
                        fragmentSalesDrillDownBinding.editTextTopN.getText().toString()).observe(getViewLifecycleOwner(), sales->{
                    if(sales!=null){
                        AdpSalesDrillDown3 adpSalesDrillDown3=new AdpSalesDrillDown3(requireContext(),sales,checkBrand,checkSupplier);
                        fragmentSalesDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown3);
                        checkDD=3;
                    }
                });
            }
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cback.setOnClickListener(v->{
            if(checkDD==2){
                ((RightsFrag)getParentFragment()).BackButtonBehave();
                fragmentSalesDrillDownBinding.sDDtop2.setGravity(Gravity.CENTER);
                fragmentSalesDrillDownBinding.sDDtop2.setText("DEPARTMENT");
                fragmentSalesDrillDownBinding.sDDtop3.setGravity(Gravity.CENTER);
                fragmentSalesDrillDownBinding.sDDtop3.setText("DIVISION");
                fragmentSalesDrillDownBinding.sDDtop4.setText("SECTION");
                fragmentSalesDrillDownBinding.sDDtop3.setVisibility(View.VISIBLE);
                fragmentSalesDrillDownBinding.sDDtop4.setVisibility(View.VISIBLE);
                fragmentSalesDrillDownBinding.editTextTopN.setText("");
                fragmentSalesDrillDownBinding.editTextTopN.setEnabled(false);
                fragmentSalesDrillDownBinding.switchBrand.setEnabled(false);
                fragmentSalesDrillDownBinding.switchSupplier.setEnabled(false);
                AdpSalesDrillDown1 adpSalesDrillDown1=new AdpSalesDrillDown1(requireContext(),mainViewModel.getSaleDD1().getValue());
                fragmentSalesDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown1);
                checkDD=1;
            }else if(checkDD==3){
                fragmentSalesDrillDownBinding.sDDtop2.setText("ITEM NAME");
                fragmentSalesDrillDownBinding.sDDtop3.setVisibility(View.GONE);
                fragmentSalesDrillDownBinding.sDDtop4.setVisibility(View.GONE);
                AdpSalesDrillDown2 adpSalesDrillDown2=new AdpSalesDrillDown2(requireContext(),mainViewModel.getSaleDD2().getValue());
                fragmentSalesDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown2);
                checkDD=2;
            }else{
                ((RightsFrag)getParentFragment()).BackButtonBehave();
            }
        });
        fragmentSalesDrillDownBinding.editTextTopN.setEnabled(false);
        fragmentSalesDrillDownBinding.switchBrand.setEnabled(false);
        fragmentSalesDrillDownBinding.switchSupplier.setEnabled(false);
        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String fdate,String tdate){
            mainViewModel.getSaleDD1list(fdate, tdate).observe(getViewLifecycleOwner(), saleDrillDownList -> {
                if (saleDrillDownList != null) {
                    AdpSalesDrillDown1 adpSalesDrillDown1 = new AdpSalesDrillDown1(requireContext(), saleDrillDownList);
                    adpSalesDrillDown1.notifyDataSetChanged();
                    fragmentSalesDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown1);
                }else{
                    error();
                }
            });
    }
    private void updateLabelfromDate() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentSalesDrillDownBinding.fromDate2.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabeltoDate() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentSalesDrillDownBinding.toDate2.setText(sdf.format(myCalendar.getTime()));
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

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
import com.bizmaxsol.rrmob.adapters.AdpSalesDrillDown1;
import com.bizmaxsol.rrmob.adapters.AdpSalesDrillDown2;
import com.bizmaxsol.rrmob.adapters.AdpSalesDrillDown3;
import com.bizmaxsol.rrmob.databinding.FragmentSalesDrillDownBinding;
import com.bizmaxsol.rrmob.databinding.FragmentStockDrillDownBinding;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StockDrillDownFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentStockDrillDownBinding fragmentStockDrillDownBinding;
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
        fragmentStockDrillDownBinding=FragmentStockDrillDownBinding.inflate(inflater,container,false);
        return fragmentStockDrillDownBinding.getRoot();
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
        fragmentStockDrillDownBinding.fromDate2.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentStockDrillDownBinding.toDate2.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), saletodate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        String ndate=mainViewModel.getResponseDate().getValue().getDate().replace("/","-");
        fragmentStockDrillDownBinding.fromDate2.setText(ndate);
        fragmentStockDrillDownBinding.toDate2.setText(ndate);
        loadlist(ndate,ndate);

        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentStockDrillDownBinding.fromDate2.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else if(fragmentStockDrillDownBinding.toDate2.getText().toString().trim().equals("TO DATE")){
                    new DatePickerDialog(requireContext(), saletodate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    loadlist(fragmentStockDrillDownBinding.fromDate2.getText().toString(),
                            fragmentStockDrillDownBinding.toDate2.getText().toString());
                }
        });
        fragmentStockDrillDownBinding.listviewSalesDrillDown.setOnItemClickListener((parent, view1, position, id) -> {
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
                String sectionId=mainViewModel.getStockDD1().getValue().get(mPosition).getLamcSsection();
                mainViewModel.getStockDD2list(sectionId).observe(getViewLifecycleOwner(), sales->{
                    if(sales!=null){
                        fragmentStockDrillDownBinding.switchBrand.setEnabled(true);
                        fragmentStockDrillDownBinding.switchSupplier.setEnabled(true);
                        fragmentStockDrillDownBinding.sDDtop2.setText("ITEM NAME");
                        fragmentStockDrillDownBinding.sDDtop3.setVisibility(View.GONE);
                        fragmentStockDrillDownBinding.sDDtop4.setVisibility(View.GONE);
                        AdpSalesDrillDown2 adpSalesDrillDown2=new AdpSalesDrillDown2(requireContext(),sales);
                        fragmentStockDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown2);
                        ((RightsFrag)getParentFragment()).ViewButtonBehave();
                        checkDD=2;
                    }
                });
            }else if(checkDD==2){
                boolean bBrand=fragmentStockDrillDownBinding.switchBrand.isChecked();
                boolean bSupplier=fragmentStockDrillDownBinding.switchSupplier.isChecked();
                if(bBrand){
                    checkBrand=1;
                    fragmentStockDrillDownBinding.sDDtop3.setVisibility(View.VISIBLE);
                    fragmentStockDrillDownBinding.sDDtop3.setGravity(Gravity.CENTER);
                    fragmentStockDrillDownBinding.sDDtop3.setText("BRAND");
                }
                else{
                    checkBrand=0;
                    fragmentStockDrillDownBinding.sDDtop3.setVisibility(View.GONE);
                }
                if(bSupplier){
                    checkSupplier=1;
                    fragmentStockDrillDownBinding.sDDtop4.setVisibility(View.VISIBLE);
                    fragmentStockDrillDownBinding.sDDtop4.setGravity(Gravity.CENTER);
                    fragmentStockDrillDownBinding.sDDtop4.setText("SUPPLIER");
                }
                else{
                    checkSupplier=0;
                    fragmentStockDrillDownBinding.sDDtop4.setVisibility(View.GONE);
                }
                fragmentStockDrillDownBinding.sDDtop2.setText("ARTICLE");

                String itemName=mainViewModel.getStockDD2().getValue().get(mPosition).getsName();
                mainViewModel.getStockDD3list(itemName,checkBrand,checkSupplier).observe(getViewLifecycleOwner(), sales->{
                    if(sales!=null){
                        AdpSalesDrillDown3 adpSalesDrillDown3=new AdpSalesDrillDown3(requireContext(),sales,checkBrand,checkSupplier);
                        fragmentStockDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown3);
                        checkDD=3;
                    }
                });
            }
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cback.setOnClickListener(v->{
            if(checkDD==2){
                ((RightsFrag)getParentFragment()).BackButtonBehave();
                fragmentStockDrillDownBinding.sDDtop2.setGravity(Gravity.CENTER);
                fragmentStockDrillDownBinding.sDDtop2.setText("DEPARTMENT");
                fragmentStockDrillDownBinding.sDDtop3.setGravity(Gravity.CENTER);
                fragmentStockDrillDownBinding.sDDtop3.setText("DIVISION");
                fragmentStockDrillDownBinding.sDDtop4.setText("SECTION");
                fragmentStockDrillDownBinding.sDDtop3.setVisibility(View.VISIBLE);
                fragmentStockDrillDownBinding.sDDtop4.setVisibility(View.VISIBLE);
                fragmentStockDrillDownBinding.switchBrand.setEnabled(false);
                fragmentStockDrillDownBinding.switchSupplier.setEnabled(false);
                AdpSalesDrillDown1 adpSalesDrillDown1=new AdpSalesDrillDown1(requireContext(),mainViewModel.getStockDD1().getValue());
                fragmentStockDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown1);
                checkDD=1;
            }else if(checkDD==3){
                fragmentStockDrillDownBinding.sDDtop2.setText("ITEM NAME");
                fragmentStockDrillDownBinding.sDDtop3.setVisibility(View.GONE);
                fragmentStockDrillDownBinding.sDDtop4.setVisibility(View.GONE);
                AdpSalesDrillDown2 adpSalesDrillDown2=new AdpSalesDrillDown2(requireContext(),mainViewModel.getStockDD2().getValue());
                fragmentStockDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown2);
                checkDD=2;
            }else{
                ((RightsFrag)getParentFragment()).BackButtonBehave();
            }
        });
        fragmentStockDrillDownBinding.switchBrand.setEnabled(false);
        fragmentStockDrillDownBinding.switchSupplier.setEnabled(false);
        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String fdate,String tdate){
            mainViewModel.getStockDD1list(fdate, tdate).observe(getViewLifecycleOwner(), saleDrillDownList -> {
                if (saleDrillDownList != null) {
                    AdpSalesDrillDown1 adpSalesDrillDown1 = new AdpSalesDrillDown1(requireContext(), saleDrillDownList);
                    adpSalesDrillDown1.notifyDataSetChanged();
                    fragmentStockDrillDownBinding.listviewSalesDrillDown.setAdapter(adpSalesDrillDown1);
                }else{
                    error();
                }
            });
    }
    private void updateLabelfromDate() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentStockDrillDownBinding.fromDate2.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabeltoDate() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentStockDrillDownBinding.toDate2.setText(sdf.format(myCalendar.getTime()));
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

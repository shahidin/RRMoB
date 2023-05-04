package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.adapters.AdpBarcodeHistory;
import com.bizmaxsol.rrmob.adapters.AdpBillLog;
import com.bizmaxsol.rrmob.databinding.FragmentBarcodeLogBinding;
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.models.ResponseBarcodeHistory;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BarcodeLogFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentBarcodeLogBinding fragmentBarcodeLogBinding;
    List<ResponseBarcodeHistory> list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBarcodeLogBinding=FragmentBarcodeLogBinding.inflate(inflater,container,false);
        return fragmentBarcodeLogBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);


        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
            if ((TextUtils.isEmpty(fragmentBarcodeLogBinding.editTextBarcode.getText().toString()))){
                fragmentBarcodeLogBinding.editTextBarcode.requestFocus();
            }else{
                loadlist(fragmentBarcodeLogBinding.editTextBarcode.getText().toString());
            }
        });

        fragmentBarcodeLogBinding.btnScan.setOnClickListener(v->{
            fragmentBarcodeLogBinding.editTextBarcode.setText("");
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
            integrator.setPrompt("Scan a barcode");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);
            integrator.setOrientationLocked(false);
            integrator.initiateScan();
        });


        //-------------------------------------------------------------------------------------------
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(requireActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                fragmentBarcodeLogBinding.editTextBarcode.setText(result.getContents());
                loadlist(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void loadlist(String barcode){
            mainViewModel.getBarcodeDetail(barcode).observe(getViewLifecycleOwner(), barcodeDetail -> {
                if (barcodeDetail != null) {
                    fragmentBarcodeLogBinding.textViewBstyle.setText(barcodeDetail.getBstyle());
                    fragmentBarcodeLogBinding.textViewBbrand.setText(barcodeDetail.getBbrand());
                    fragmentBarcodeLogBinding.textViewBdepartment.setText(barcodeDetail.getBdepartment());
                    fragmentBarcodeLogBinding.textViewBdivision.setText(barcodeDetail.getBdivision());
                    fragmentBarcodeLogBinding.textViewBsection.setText(barcodeDetail.getBsection());
                    fragmentBarcodeLogBinding.textViewBitem.setText(barcodeDetail.getBitem());
                    fragmentBarcodeLogBinding.textViewBsize.setText(barcodeDetail.getBsize());
                    fragmentBarcodeLogBinding.textViewBcolor.setText(barcodeDetail.getBcolor());
                    fragmentBarcodeLogBinding.textViewBpp.setText(barcodeDetail.getBpp());
                    fragmentBarcodeLogBinding.textViewBmrp.setText(barcodeDetail.getBmrp());
                    fragmentBarcodeLogBinding.textViewBsupplier.setText(barcodeDetail.getBsupplier());
                }else{
                    error();
                }
            });
        mainViewModel.getBarcodeHistory(barcode).observe(getViewLifecycleOwner(), barcodeHistories -> {
            if (barcodeHistories != null) {
                list=barcodeHistories;
                AdpBarcodeHistory adpBarcodeHistory = new AdpBarcodeHistory(requireContext(), barcodeHistories);
                adpBarcodeHistory.notifyDataSetChanged();
                fragmentBarcodeLogBinding.listviewBarcodehistory.setAdapter(adpBarcodeHistory);
                calculate();
            }else{
                error();
            }
        });
    }
    private void calculate(){
        double stk=0;
        for(ResponseBarcodeHistory rh:list){
            switch (rh.getBdetail()) {
                case "PURCHASE":
                    stk = stk + Double.parseDouble(rh.getBqty());
                    break;
                case "PURCHASE RETURN":
                    stk = stk - Double.parseDouble(rh.getBqty());
                    break;
                case "SALES":
                    stk = stk - Double.parseDouble(rh.getBqty());
                    break;
                case "CHALLAN IN":
                    stk = stk + Double.parseDouble(rh.getBqty());
                    break;
                case "CHALLAN OUT":
                    stk = stk - Double.parseDouble(rh.getBqty());
                    break;
            }
        }
        fragmentBarcodeLogBinding.textViewBHstock.setText(String.valueOf(stk));
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

package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.adapters.AdpBillLog;
import com.bizmaxsol.rrmob.adapters.AdpOC;
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.databinding.FragmentOcBinding;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OCFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentOcBinding fragmentOcBinding;
    DatePickerDialog.OnDateSetListener salefromdate;
    DatePickerDialog.OnDateSetListener saletodate;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentOcBinding=FragmentOcBinding.inflate(inflater,container,false);
        return fragmentOcBinding.getRoot();
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
        fragmentOcBinding.fromDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentOcBinding.toDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), saletodate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        String ndate=mainViewModel.getResponseDate().getValue().getDate().replace("/","-");
        fragmentOcBinding.fromDate.setText(ndate);
        fragmentOcBinding.toDate.setText(ndate);

        fragmentOcBinding.btnFilterOC.setOnClickListener(v->{
            if(fragmentOcBinding.linearLayoutF1.getVisibility()==View.VISIBLE){
                fragmentOcBinding.btnFilterOC.setText("SHOW FILTER");
                fragmentOcBinding.linearLayoutF1.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF2.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF3.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF4.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF5.setVisibility(View.GONE);

            }else{
                fragmentOcBinding.btnFilterOC.setText("HIDE FILTER");
                fragmentOcBinding.linearLayoutF1.setVisibility(View.VISIBLE);
                fragmentOcBinding.linearLayoutF2.setVisibility(View.VISIBLE);
                fragmentOcBinding.linearLayoutF3.setVisibility(View.VISIBLE);
                fragmentOcBinding.linearLayoutF4.setVisibility(View.VISIBLE);
                fragmentOcBinding.linearLayoutF5.setVisibility(View.VISIBLE);
            }
        });

        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentOcBinding.fromDate.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else if(fragmentOcBinding.toDate.getText().toString().trim().equals("TO DATE")){
                    new DatePickerDialog(requireContext(), saletodate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    loadlist();
                }
        });


        readfilter();
        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(){
        String fdate=fragmentOcBinding.fromDate.getText().toString();
        String tdate=fragmentOcBinding.toDate.getText().toString();
        int format;
        String dipfil;
        String secfil;
        String itemfil;
        String brandfil;
        String artfil;
        String szfil="-";
        String colfil="-";
        int dipvis;
        int secvis;
        int itemvis;
        int brandvis;
        int artvis;
        int szvis;
        int colvis;
        if(fragmentOcBinding.switchDivision.isChecked()){
            dipvis=1;
        }else{
            dipvis=0;
        }
        if(fragmentOcBinding.switchSection.isChecked()){
            secvis=1;
        }else{
            secvis=0;
        }
        if(fragmentOcBinding.switchItem.isChecked()){
            itemvis=1;
        }else{
            itemvis=0;
        }
        if(fragmentOcBinding.switchBrand.isChecked()){
            brandvis=1;
        }else{
            brandvis=0;
        }
        if(fragmentOcBinding.switchArticle.isChecked()){
            artvis=1;
        }else{
            artvis=0;
        }
        if(fragmentOcBinding.switchSize.isChecked()){
            szvis=1;
        }else{
            szvis=0;
        }
        if(fragmentOcBinding.switchColour.isChecked()){
            colvis=1;
        }else{
            colvis=0;
        }
        if(fragmentOcBinding.switchOC.isChecked()){
            format=1;
        }else{
            format=0;
        }
        if ((TextUtils.isEmpty(fragmentOcBinding.divisionOCView.getText().toString()))){
            dipfil="-";
        }else{
            dipfil=fragmentOcBinding.divisionOCView.getText().toString();
        }
        if ((TextUtils.isEmpty(fragmentOcBinding.sectionOCView.getText().toString()))){
            secfil="-";
        }else{
            secfil=fragmentOcBinding.sectionOCView.getText().toString();
        }
        if ((TextUtils.isEmpty(fragmentOcBinding.itemOCView.getText().toString()))){
            itemfil="-";
        }else{
            itemfil=fragmentOcBinding.itemOCView.getText().toString();
        }
        if ((TextUtils.isEmpty(fragmentOcBinding.brandOCView.getText().toString()))){
            brandfil="-";
        }else{
            brandfil=fragmentOcBinding.brandOCView.getText().toString();
        }
        if ((TextUtils.isEmpty(fragmentOcBinding.articleOcView.getText().toString()))){
            artfil="-";
        }else{
            artfil=fragmentOcBinding.articleOcView.getText().toString();
        }
        mainViewModel.getOClist(fdate, tdate, format, dipfil, secfil, itemfil, brandfil, artfil, szfil, colfil, dipvis, secvis, itemvis, brandvis, artvis, szvis, colvis).observe(getViewLifecycleOwner(), ocs -> {
            if (ocs != null) {
                AdpOC adpOC=new AdpOC(requireContext(),ocs);
                adpOC.notifyDataSetChanged();
                fragmentOcBinding.listview.setAdapter(adpOC);
                fragmentOcBinding.linearLayoutF1.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF2.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF3.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF4.setVisibility(View.GONE);
                fragmentOcBinding.linearLayoutF5.setVisibility(View.GONE);
                fragmentOcBinding.btnFilterOC.setText("SHOW FILTER");
            }else{
                error();
            }
        });
    }
    private void readfilter(){
        mainViewModel.getItemNamelist().observe(getViewLifecycleOwner(), masters -> {
            if (masters != null) {
                ArrayList<String> masters_arr = new ArrayList<>();
                for (int idx = 0; idx < masters.size(); idx++) {
                    masters_arr.add(masters.get(idx).getName());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        masters_arr);
                fragmentOcBinding.itemOCView.setAdapter(arrayAdapter);
            }
        });
        mainViewModel.getBrandNamelist().observe(getViewLifecycleOwner(), masters -> {
            if (masters != null) {
                ArrayList<String> masters_arr = new ArrayList<>();
                for (int idx = 0; idx < masters.size(); idx++) {
                    masters_arr.add(masters.get(idx).getName());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        masters_arr);
                fragmentOcBinding.brandOCView.setAdapter(arrayAdapter);
            }
        });
        mainViewModel.getSectionNamelist().observe(getViewLifecycleOwner(), masters -> {
            if (masters != null) {
                ArrayList<String> masters_arr = new ArrayList<>();
                for (int idx = 0; idx < masters.size(); idx++) {
                    masters_arr.add(masters.get(idx).getName());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        masters_arr);
                fragmentOcBinding.sectionOCView.setAdapter(arrayAdapter);
            }
        });
        mainViewModel.getDivisionNamelist().observe(getViewLifecycleOwner(), masters -> {
            if (masters != null) {
                ArrayList<String> masters_arr = new ArrayList<>();
                for (int idx = 0; idx < masters.size(); idx++) {
                    masters_arr.add(masters.get(idx).getName());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        masters_arr);
                fragmentOcBinding.divisionOCView.setAdapter(arrayAdapter);
            }
        });
    }
    private void updateLabelfromDate() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentOcBinding.fromDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabeltoDate() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        fragmentOcBinding.toDate.setText(sdf.format(myCalendar.getTime()));
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

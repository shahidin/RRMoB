package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputFilter;
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
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class BillLogFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentBillLogBinding fragmentBillLogBinding;
    DatePickerDialog.OnDateSetListener salefromdate;
    DatePickerDialog.OnDateSetListener saletodate;
    Calendar myCalendar = Calendar.getInstance();
    int stat;
    public BillLogFrag(int stat) {
        this.stat=stat;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBillLogBinding=FragmentBillLogBinding.inflate(inflater,container,false);
        return fragmentBillLogBinding.getRoot();
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
        fragmentBillLogBinding.fromDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentBillLogBinding.toDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), saletodate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentBillLogBinding.fromDate.setText(mainViewModel.getResponseDate().getValue().getDate());
        fragmentBillLogBinding.toDate.setText(mainViewModel.getResponseDate().getValue().getDate());
        loadlist(mainViewModel.getResponseDate().getValue().getDate(),mainViewModel.getResponseDate().getValue().getDate());

        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentBillLogBinding.fromDate.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else if(fragmentBillLogBinding.toDate.getText().toString().trim().equals("TO DATE")){
                    new DatePickerDialog(requireContext(), saletodate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    loadlist(fragmentBillLogBinding.fromDate.getText().toString(),
                            fragmentBillLogBinding.toDate.getText().toString());
                }
        });



        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String fdate,String tdate){
        if(stat==1) {
            mainViewModel.getEditedBillLog(fdate, tdate).observe(getViewLifecycleOwner(), billLogs -> {
                if (billLogs != null) {
                    AdpBillLog adpBillLog = new AdpBillLog(requireContext(), billLogs);
                    adpBillLog.notifyDataSetChanged();
                    fragmentBillLogBinding.listview.setAdapter(adpBillLog);
                }else{
                    error();
                }
            });
        }else {
            mainViewModel.getDeletedBillLog(fdate, tdate).observe(getViewLifecycleOwner(), billLogs -> {
                if (billLogs != null) {
                    AdpBillLog adpBillLog = new AdpBillLog(requireContext(), billLogs);
                    adpBillLog.notifyDataSetChanged();
                    fragmentBillLogBinding.listview.setAdapter(adpBillLog);
                }else{
                    error();
                }
            });
        }
    }
    private void updateLabelfromDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentBillLogBinding.fromDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabeltoDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        fragmentBillLogBinding.toDate.setText(sdf.format(myCalendar.getTime()));
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

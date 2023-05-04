package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.adapters.AdpBillLog;
import com.bizmaxsol.rrmob.adapters.AdpPurchase;
import com.bizmaxsol.rrmob.adapters.AdpPurchaseChild;
import com.bizmaxsol.rrmob.adapters.AdpSalesDetail;
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.databinding.FragmentPurchaseBinding;
import com.bizmaxsol.rrmob.models.ResponsePurchase;
import com.bizmaxsol.rrmob.models.ResponseSales;
import com.bizmaxsol.rrmob.models.ResponseSupplier;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PurchaseFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentPurchaseBinding fragmentPurchaseBinding;
    DatePickerDialog.OnDateSetListener salefromdate;
    DatePickerDialog.OnDateSetListener saletodate;
    Calendar myCalendar = Calendar.getInstance();
    int stat;
    int mPosition;
    List<ResponseSupplier> supplierlist;
    String supplierId;
    public PurchaseFrag(int stat) {
        this.stat=stat;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPurchaseBinding=FragmentPurchaseBinding.inflate(inflater,container,false);
        return fragmentPurchaseBinding.getRoot();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
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
        fragmentPurchaseBinding.fromDate3.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), salefromdate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentPurchaseBinding.toDate3.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(requireContext(), saletodate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        fragmentPurchaseBinding.fromDate3.setText(mainViewModel.getResponseDate().getValue().getDate());
        fragmentPurchaseBinding.toDate3.setText(mainViewModel.getResponseDate().getValue().getDate());
        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
                if(fragmentPurchaseBinding.fromDate3.getText().toString().trim().equals("FROM DATE")){
                    new DatePickerDialog(requireContext(), salefromdate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else if(fragmentPurchaseBinding.toDate3.getText().toString().trim().equals("TO DATE")){
                    new DatePickerDialog(requireContext(), saletodate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    String supplierName=fragmentPurchaseBinding.supplierView.getText().toString().trim();
                    List<ResponseSupplier> rs = supplierlist.stream().filter(ResponseSupplier->ResponseSupplier
                            .getSupplierName()
                            .equalsIgnoreCase(supplierName))
                            .collect(Collectors.toList());
                    if(rs.size()==1){
                        supplierId=rs.get(0).getSupplierId();
                        loadlist(fragmentPurchaseBinding.fromDate3.getText().toString(),
                                fragmentPurchaseBinding.toDate3.getText().toString(),supplierId);
                    }else{
                        loadlist(fragmentPurchaseBinding.fromDate3.getText().toString(),
                                fragmentPurchaseBinding.toDate3.getText().toString(),"");
                    }
                }
        });
        fragmentPurchaseBinding.listPurchases.setOnItemClickListener((parent, view1, position, id) -> {
            mPosition=position;
            ((RightsFrag)getParentFragment()).ListClickBehave();
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cview.setOnClickListener(v->{
            String pId=mainViewModel.getPurchase().getValue().get(mPosition).getpId();
            mainViewModel.getPurchaseDetaillist(pId,stat).observe(getViewLifecycleOwner(), purchaseList->{
                if(purchaseList!=null){
                    fragmentPurchaseBinding.supplier.setVisibility(View.GONE);
                    fragmentPurchaseBinding.linearLayout6.setVisibility(View.GONE);
                    fragmentPurchaseBinding.horizontalList.setVisibility(View.GONE);
                    fragmentPurchaseBinding.purchaseFooter.setVisibility(View.VISIBLE);
                    fragmentPurchaseBinding.horizontalPurdetail.setVisibility(View.VISIBLE);

                    fragmentPurchaseBinding.textViewPDate.setText("DATE: "+mainViewModel.getPurchase().getValue().get(mPosition).getPbillDate());
                    fragmentPurchaseBinding.textViewPBillno.setText("BILL NO: "+mainViewModel.getPurchase().getValue().get(mPosition).getPbillNo());
                    fragmentPurchaseBinding.textViewPDoc.setText("DOC NO: "+mainViewModel.getPurchase().getValue().get(mPosition).getPdocNo());
                    fragmentPurchaseBinding.textViewPSupplier.setText("SUPPLIER: "+mainViewModel.getPurchase().getValue().get(mPosition).getPsupplier());
                    DecimalFormat form = new DecimalFormat("0.00");
                    fragmentPurchaseBinding.textViewPQty.setText("TOTAL QTY: "+form.format(mainViewModel.getPurchase().getValue().get(mPosition).getPtotQty()));
                    double nTotamt=mainViewModel.getPurchase().getValue().get(mPosition).getPtotAmount();
                    double nTax=mainViewModel.getPurchase().getValue().get(mPosition).getPtax();
                    fragmentPurchaseBinding.textViewPAmt.setText("TOTAL AMT: "+form.format(mainViewModel.getPurchase().getValue().get(mPosition).getPtotAmount()));
                    fragmentPurchaseBinding.textViewPTax.setText("TAXES: "+form.format(mainViewModel.getPurchase().getValue().get(mPosition).getPtax()));

                    double tamt=0;
                    for(ResponsePurchase rp:purchaseList){
                        tamt=tamt+rp.getPamount();
                    }
                    double charge=nTotamt-(tamt+nTax);
                    fragmentPurchaseBinding.textViewPCharge.setText("CHARGES: "+form.format(charge));
                    AdpPurchaseChild adpPurchaseChild=new AdpPurchaseChild(requireContext(),purchaseList);
                    adpPurchaseChild.notifyDataSetChanged();
                    fragmentPurchaseBinding.listview.setAdapter(adpPurchaseChild);
                    ((RightsFrag)getParentFragment()).ViewButtonBehave();
                }
            });
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cback.setOnClickListener(v->{
            fragmentPurchaseBinding.supplier.setVisibility(View.VISIBLE);
            fragmentPurchaseBinding.linearLayout6.setVisibility(View.VISIBLE);
            fragmentPurchaseBinding.horizontalList.setVisibility(View.VISIBLE);
            fragmentPurchaseBinding.purchaseFooter.setVisibility(View.GONE);
            fragmentPurchaseBinding.horizontalPurdetail.setVisibility(View.GONE);
            ((RightsFrag)getParentFragment()).BackButtonBehave();
        });

        loadfield();
        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String fdate,String tdate,String supplierId){
            mainViewModel.getPurchaselist(fdate, tdate,supplierId,stat).observe(getViewLifecycleOwner(), purchases -> {
                if (purchases != null) {
                    AdpPurchase adpPurchase = new AdpPurchase(requireContext(), purchases);
                    adpPurchase.notifyDataSetChanged();
                    fragmentPurchaseBinding.listPurchases.setAdapter(adpPurchase);
                }else{
                    error();
                }
            });
        }
    private void loadfield(){
        mainViewModel.getSupplier().observe(getViewLifecycleOwner(), suppliers -> {
            if (suppliers != null) {
                supplierlist=suppliers;
                ArrayList<String> suppliers_arr = new ArrayList<>();
                for (int idx = 0; idx < suppliers.size(); idx++) {
                    suppliers_arr.add(suppliers.get(idx).getSupplierName());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                        suppliers_arr);
                fragmentPurchaseBinding.supplierView.setAdapter(arrayAdapter);
            }else{
                error();
            }
        });
    }
    private void updateLabelfromDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        fragmentPurchaseBinding.fromDate3.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabeltoDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        fragmentPurchaseBinding.toDate3.setText(sdf.format(myCalendar.getTime()));
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
    private void error1(String field) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(requireContext());
        dialog.setTitle("Please check "+field);
        dialog.setIcon(R.drawable.ic_warning);
        dialog.setPositiveButton("Ok",
                (dialog12, which) -> {
                } );
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}

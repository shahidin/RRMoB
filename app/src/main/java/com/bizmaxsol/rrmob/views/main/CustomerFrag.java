package com.bizmaxsol.rrmob.views.main;

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
import com.bizmaxsol.rrmob.adapters.AdpCustomer;
import com.bizmaxsol.rrmob.databinding.FragmentCustomerBinding;
import com.bizmaxsol.rrmob.models.ResponseCustomer;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class CustomerFrag extends Fragment {

    MainViewModel mainViewModel;
    FragmentCustomerBinding fragmentCustomerBinding;
    int mPosition;
    ResponseCustomer responseCustomer;
    List<ResponseCustomer> customer_list;
    String cusmerId;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCustomerBinding=FragmentCustomerBinding.inflate(inflater,container,false);
        return fragmentCustomerBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        fragmentCustomerBinding.customerDescView.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        fragmentCustomerBinding.customerMobView.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        fragmentCustomerBinding.scustomerDescView.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        fragmentCustomerBinding.scustomerMobView.setFilters(new InputFilter[]{new InputFilter.AllCaps()});


        if(((RightsFrag)getParentFragment()).fragmentRightsBinding.cadd.getTag().toString().equals("1")){
            ((RightsFrag)getParentFragment()).fragmentRightsBinding.cadd.setVisibility(View.VISIBLE);
        }

        if(((RightsFrag)getParentFragment()).fragmentRightsBinding.cview.getTag().toString().equals("1")){
            ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setVisibility(View.VISIBLE);
            loadlist();
        }
        //--------------------------------------view edit delete---------------------------------------
        fragmentCustomerBinding.listview.setOnItemClickListener((parent, view1, position, id) -> {
            mPosition=position;
            ((RightsFrag)getParentFragment()).ListClickBehave();
        });
        //---------------------------------------------------------------------------------------------


        //------------------------------------set button---------------------------------------------
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cadd.setOnClickListener(v->{
            responseCustomer=null;
            fragmentCustomerBinding.centry.setVisibility(View.VISIBLE);
            fragmentCustomerBinding.clist.setVisibility(View.GONE);
            fragmentCustomerBinding.tvnodata.setVisibility(View.GONE);
            ((RightsFrag)getParentFragment()).AddButtonBehave();
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cback.setOnClickListener(v->{
            ((RightsFrag)getParentFragment()).BackButtonBehave();
                loadlist();
            if(fragmentCustomerBinding.centry.getVisibility()==View.VISIBLE){
                fragmentCustomerBinding.centry.setVisibility(View.GONE);
                fragmentCustomerBinding.clist.setVisibility(View.VISIBLE);
                fragmentCustomerBinding.customerMobView.setEnabled(true);
                fragmentCustomerBinding.customerDescView.setEnabled(true);
                fragmentCustomerBinding.customerMobView.setText("");
                fragmentCustomerBinding.customerDescView.setText("");
            }
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
            if(((RightsFrag)getParentFragment()).fragmentRightsBinding.cview.getTag().toString().equals("1")){
                loadlist();
            }
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cview.setOnClickListener(v->{
            ((RightsFrag)getParentFragment()).ViewButtonBehave();
            responseCustomer=mainViewModel.getCustomers().getValue().get(mPosition);
            fragmentCustomerBinding.centry.setVisibility(View.VISIBLE);
            fragmentCustomerBinding.clist.setVisibility(View.GONE);
//            fragmentUomBinding.uomDescView.setText(uom.getUom_sdesc());
//            String uomcf=uom.getUom_confac();
//            if(uomcf==null){
//                fragmentUomBinding.uomCFacView.setText("");
//            }else{
//                fragmentUomBinding.uomCFacView.setText(uom.getUom_confac());
//            }
//            String buId=uom.getUom_sbaseuomid();
//            if(buId==null){
//                fragmentUomBinding.uomBaseUnitView.setText("");
//            }else{
//                fragmentUomBinding.uomBaseUnitView.setText(uom.getBaseuom());
//            }
//            fragmentUomBinding.uomDescView.setEnabled(false);
//            fragmentUomBinding.uomCFacView.setEnabled(false);
//            fragmentUomBinding.uomBaseUnitView.setEnabled(false);
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cedit.setOnClickListener(v->{
            ((RightsFrag)getParentFragment()).EditButtonBehave();

//            fragmentUomBinding.uomDescView.setEnabled(true);
//            fragmentUomBinding.uomCFacView.setEnabled(true);
//            fragmentUomBinding.uomBaseUnitView.setEnabled(true);
//
//            if(fragmentUomBinding.clist.getVisibility()==View.VISIBLE){
//                uom=mainViewModel.getUomlist().getValue().get(mPosition);
//                fragmentUomBinding.centry.setVisibility(View.VISIBLE);
//                fragmentUomBinding.clist.setVisibility(View.GONE);
//
//                fragmentUomBinding.uomDescView.setText(uom.getUom_sdesc());
//                String uomcf=uom.getUom_confac();
//                if(uomcf==null){
//                    fragmentUomBinding.uomCFacView.setText("");
//                }else{
//                    fragmentUomBinding.uomCFacView.setText(uom.getUom_confac());
//                }
//                String buId=uom.getUom_sbaseuomid();
//                if(buId==null){
//                    fragmentUomBinding.uomBaseUnitView.setText("");
//                }else{
//                    fragmentUomBinding.uomBaseUnitView.setText(uom.getBaseuom());
//                }
//            }
        });
        ((RightsFrag)getParentFragment()).fragmentRightsBinding.csave.setOnClickListener(v->{

//            if ((TextUtils.isEmpty(fragmentUomBinding.uomDescView.getText().toString()))){
//                fragmentUomBinding.uomDescView.requestFocus();
//            }else{
//                ((RightsFrag)getParentFragment()).fragmentRightsBinding.csave.setVisibility(View.GONE);
//                String mid;
//                if(uom==null){
//                    mid="0";
//                }else{
//                    mid= uom.getUom_nid();
//                }
//                uom=new UOM(mid,fragmentUomBinding.uomDescView.getText().toString(),
//                        checkUomId(fragmentUomBinding.uomBaseUnitView.getText().toString()),
//                                fragmentUomBinding.uomCFacView.getText().toString(),
//                        mainViewModel.getLogSessn().getLogsessn_nsessionno());
//                mainViewModel.updateUom(uom, uom.getUom_nid()).observe(getViewLifecycleOwner(),response->{
//                    if(response==null){
//                        notify("FAIL", uom.getUom_sdesc());
//                        ((RightsFrag)getParentFragment()).fragmentRightsBinding.csave.setVisibility(View.VISIBLE);
//                        loadlist();
////                        fragmentUomBinding.centry.setVisibility(View.GONE);
////                        fragmentUomBinding.clist.setVisibility(View.VISIBLE);
////                        fragmentUomBinding.uomDescView.setText("");
////                        fragmentUomBinding.uomCFacView.setText("");
////                        fragmentUomBinding.uomBaseUnitView.setText("");
//
//                    }else {
//                        notify("SUCCESS",uom.getUom_sdesc());
//                        loadlist();
////                        fragmentUomBinding.centry.setVisibility(View.GONE);
////                        fragmentUomBinding.clist.setVisibility(View.VISIBLE);
//                        fragmentUomBinding.uomDescView.setEnabled(false);
//                        fragmentUomBinding.uomCFacView.setEnabled(false);
//                        fragmentUomBinding.uomBaseUnitView.setEnabled(false);
//                    }
////                    ((RightsFrag)getParentFragment()).BackButtonBehave();
//                });
//            }
//        });
//        ((RightsFrag)getParentFragment()).fragmentRightsBinding.cdelete.setOnClickListener(v->{
//            String nuid=mainViewModel.getUomlist().getValue().get(mPosition).getUom_nid();
//            mainViewModel.deleteUom(nuid);
//            ((RightsFrag)getParentFragment()).DeleteButtonBehave();
//            fragmentUomBinding.uomDescView.setText("");
//            fragmentUomBinding.uomCFacView.setText("");
//            fragmentUomBinding.uomBaseUnitView.setText("");
//            fragmentUomBinding.uomDescView.setEnabled(true);
//            fragmentUomBinding.uomCFacView.setEnabled(true);
//            fragmentUomBinding.uomBaseUnitView.setEnabled(true);
//            fragmentUomBinding.centry.setVisibility(View.GONE);
//            fragmentUomBinding.clist.setVisibility(View.VISIBLE);
//            loadlist();
        });
        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(){
        String nName=fragmentCustomerBinding.scustomerDescView.getText().toString().trim();
        String nNumber=fragmentCustomerBinding.scustomerMobView.getText().toString().trim();
        if((TextUtils.isEmpty(fragmentCustomerBinding.scustomerMobView.getText().toString()))) {
            nNumber="-";
        }
        mainViewModel.getCustomerlist(nNumber,nName).observe(getViewLifecycleOwner(),customers -> {
            if(customers!=null){
                AdpCustomer adpCustomer = new AdpCustomer(requireContext(), customers);
                adpCustomer.notifyDataSetChanged();
                fragmentCustomerBinding.listview.setAdapter(adpCustomer);
                fragmentCustomerBinding.listview.setSelector(R.color.purple_500);
            }
        });
    }

    private void notify(String xtext,String ytext){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(xtext);
        builder.setMessage("Saving UOM-"+ytext+" data is "+xtext);
        builder.setCancelable(true);
        final AlertDialog closedialog= builder.create();
        closedialog.show();
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                closedialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 3000); // the timer will count 5 seconds....
    }
    private String splitNameId(String s){
        String[] separated = s.split("-");
        return separated[0];
    }

//    private String checkUomId(String uomName){
//        String nuomId="";
//        List<UOM> rs = uoms_list.stream().filter(UOM->UOM
//                .getUom_sdesc()
//                .equalsIgnoreCase(uomName))
//                .collect(Collectors.toList());
//        if(rs.size()==1){
//            nuomId=rs.get(0).getUom_nid();
//        }
//        return nuomId;
//    }
}

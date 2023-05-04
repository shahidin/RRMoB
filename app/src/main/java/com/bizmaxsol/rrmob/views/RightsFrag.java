package com.bizmaxsol.rrmob.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.databinding.FragmentRightsBinding;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.main.ArticleSaleStockFrag;
import com.bizmaxsol.rrmob.views.main.BarcodeLogFrag;
import com.bizmaxsol.rrmob.views.main.BillLogFrag;
import com.bizmaxsol.rrmob.views.main.DailySalesFrag;
import com.bizmaxsol.rrmob.views.main.OCFrag;
import com.bizmaxsol.rrmob.views.main.PurchaseFrag;
import com.bizmaxsol.rrmob.views.main.SaleSessnFrag;
import com.bizmaxsol.rrmob.views.main.SalesDrillDownFrag;
import com.bizmaxsol.rrmob.views.main.StockDrillDownFrag;


public class RightsFrag extends Fragment {
    //----------------------------Type----------------------------------------
    private int type;
    private String caption;



    //----------------------------------------------------------------------------

    MainViewModel mainViewModel;
    int id;
    FragmentTransaction transaction;

    public FragmentRightsBinding fragmentRightsBinding;

    public RightsFrag(int id) {
        this.id=id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRightsBinding=FragmentRightsBinding.inflate(inflater,container,false);
        return fragmentRightsBinding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mainViewModel=new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        ((MainActivity)getActivity()).binding.ivback.setOnClickListener(v-> {
            requireActivity().getSupportFragmentManager().popBackStack();
            ((MainActivity)getActivity()).binding.searchMain.setVisibility(View.VISIBLE);
        });
        ((MainActivity)getActivity()).binding.searchMain.setVisibility(View.GONE);
        setRights();
        fragmentRightsBinding.ccaption.setText(caption);
        fragmentRightsBinding.ctype.setText(setTypeCaption());

    }
    //--------------------------Set Right-----------------------------------------------
    private void setRights(){
        int size=mainViewModel.getTransactionLiveData().getValue().size();
        for(int idx=0;idx<size;idx++) {
            if (id == mainViewModel.getTransactionLiveData().getValue().get(idx).getUsertran_nid()) {
                type = mainViewModel.getTransactionLiveData().getValue().get(idx).getUsertran_ntype();
                caption = mainViewModel.getTransactionLiveData().getValue().get(idx).getUsertran_scaption();
            }
        }
        switch (type) {
            case 1:
                setControlTransaction();
                break;
            case 2:
                setControlReport();
                break;
            case 3:
                setControlUtility();
                break;
            default:
                setControlMaster();
        }
        loadchildfrag();
    }

    private void setControlMaster() {
    }

    private void setControlUtility() {
    }

    private void setControlTransaction() {
    }
    //-----------------------------------------------------------------------------------

    //---------------------------Button Control-----------------------------------------

    private void setControlReport(){
            fragmentRightsBinding.crefresh.setVisibility(View.VISIBLE);
    }

    //-----------------------------------------------------------------------------------

    private String setTypeCaption(){
       String typeCaption;
       if(type==0){
           typeCaption="Master";
       }else if(type==1){
           typeCaption="Transaction";
       }else if(type==2){
           typeCaption="Report";
       }else {
           typeCaption="Utility";
       }
       return typeCaption;
    }
    private void loadchildfrag(){
        switch (id) {
            case 1:
                //Edited Bill
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new BillLogFrag(1)).commit();
                break;
            case 2:
                //Deleted Bill
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new BillLogFrag(2)).commit();

                break;
            case 3:
                //Barcode Log
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new BarcodeLogFrag()).commit();
                break;
            case 31:
                //Purchase
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new PurchaseFrag(1)).commit();
                break;
            case 32:
                //Purchase Return
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new PurchaseFrag(2)).commit();
                break;
            case 61:
                //Customer
//
                break;
            case 21:
                //Sales Sessn
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new SaleSessnFrag()).commit();
                break;
            case 22:
                //Daily Sales
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new DailySalesFrag()).commit();
                break;
            case 23:
                //Sales Drill Down
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new SalesDrillDownFrag()).commit();
                break;
            case 24:
                //Article Sales Stock
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new ArticleSaleStockFrag()).commit();
                break;
            case 41:
                //Stock Drill Down
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new StockDrillDownFrag()).commit();
                break;
            case 42:
                //Stock OC
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.entryfragspace,new OCFrag()).commit();
                break;
            default:

        }
    }
    public void BackButtonBehave(){
        if(type==0 ||type==3){
            fragmentRightsBinding.cadd.setVisibility(View.VISIBLE);
            fragmentRightsBinding.crefresh.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cback.setVisibility(View.GONE);
            fragmentRightsBinding.csave.setVisibility(View.GONE);
            fragmentRightsBinding.cedit.setVisibility(View.GONE);
            fragmentRightsBinding.cdelete.setVisibility(View.GONE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
        }else if(type==2){
            fragmentRightsBinding.cback.setVisibility(View.GONE);
            fragmentRightsBinding.crefresh.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
        }
    }
    public void AddButtonBehave(){
        if(type==0 || type==3){
            fragmentRightsBinding.cadd.setVisibility(View.GONE);
            fragmentRightsBinding.crefresh.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.csave.setVisibility(View.VISIBLE);
        }
    }
    public void ViewButtonBehave(){
        if(type==0){
            fragmentRightsBinding.cadd.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
        }else if(type==3){
            fragmentRightsBinding.cadd.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
            fragmentRightsBinding.cedit.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cdelete.setVisibility(View.GONE);
        }else if(type==2){
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
            fragmentRightsBinding.crefresh.setVisibility(View.GONE);
        }
    }
    public void EditButtonBehave(){
        if(type==0){
            fragmentRightsBinding.cadd.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.csave.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
            fragmentRightsBinding.cedit.setVisibility(View.GONE);
            fragmentRightsBinding.cdelete.setVisibility(View.GONE);
        }else if(type==3){
            fragmentRightsBinding.csave.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
            fragmentRightsBinding.cedit.setVisibility(View.GONE);
        }
    }
    public void DeleteButtonBehave(){
        if(type==0 || type==3){
            fragmentRightsBinding.cdelete.setVisibility(View.GONE);
            fragmentRightsBinding.cadd.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.GONE);
            fragmentRightsBinding.cedit.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.GONE);
            fragmentRightsBinding.crefresh.setVisibility(View.VISIBLE);
        }
    }
    public void SaveButtonBehave(){
        if(type==0){

        }
    }
    public void RefreshButtonBehave(){
        if(type==0){

        }
    }
    public void PrintButtonBehave(){
        if(type==0){

        }
    }
    public void ListClickBehave(){
        if(type==0){
            fragmentRightsBinding.cadd.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.VISIBLE);
            fragmentRightsBinding.crefresh.setVisibility(View.GONE);
            if(fragmentRightsBinding.cdelete.getTag().toString().equals("1")){
                fragmentRightsBinding.cdelete.setVisibility(View.VISIBLE);
            }
            if(fragmentRightsBinding.cedit.getTag().toString().equals("1")){
                fragmentRightsBinding.cedit.setVisibility(View.VISIBLE);
            }
        }else if(type==3){
            fragmentRightsBinding.cadd.setVisibility(View.GONE);
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.VISIBLE);
            fragmentRightsBinding.crefresh.setVisibility(View.GONE);
            fragmentRightsBinding.cdelete.setVisibility(View.VISIBLE);
        }else if(type==2){
            fragmentRightsBinding.cback.setVisibility(View.VISIBLE);
            fragmentRightsBinding.cview.setVisibility(View.VISIBLE);
            fragmentRightsBinding.crefresh.setVisibility(View.GONE);
        }
    }
}

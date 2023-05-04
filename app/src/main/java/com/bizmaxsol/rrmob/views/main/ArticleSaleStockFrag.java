package com.bizmaxsol.rrmob.views.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.adapters.AdpArticleSale;
import com.bizmaxsol.rrmob.adapters.AdpArticleStock;
import com.bizmaxsol.rrmob.adapters.AdpBillLog;
import com.bizmaxsol.rrmob.databinding.FragmentArticleSaleStockBinding;
import com.bizmaxsol.rrmob.databinding.FragmentBillLogBinding;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.RightsFrag;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ArticleSaleStockFrag extends Fragment {


    MainViewModel mainViewModel;
    FragmentArticleSaleStockBinding fragmentArticleSaleStockBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentArticleSaleStockBinding=FragmentArticleSaleStockBinding.inflate(inflater,container,false);
        return fragmentArticleSaleStockBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        fragmentArticleSaleStockBinding.editTextArticleCode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



        //------------------------------------set button---------------------------------------------

        ((RightsFrag)getParentFragment()).fragmentRightsBinding.crefresh.setOnClickListener(v->{
            if ((TextUtils.isEmpty(fragmentArticleSaleStockBinding.editTextArticleCode.getText().toString()))){
                fragmentArticleSaleStockBinding.editTextArticleCode.requestFocus();
            }else{
                loadlist(fragmentArticleSaleStockBinding.editTextArticleCode.getText().toString());
            }
        });



        //-------------------------------------------------------------------------------------------
    }
    private void loadlist(String article){
            mainViewModel.getArticleSales(article).observe(getViewLifecycleOwner(), articles -> {
                if (articles != null) {
                    AdpArticleSale adpArticleSale = new AdpArticleSale(requireContext(), articles);
                    adpArticleSale.notifyDataSetChanged();
                    fragmentArticleSaleStockBinding.listviewArticleSale.setAdapter(adpArticleSale);
                }
            });
            mainViewModel.getArticleStocks(article).observe(getViewLifecycleOwner(), articles -> {
                if (articles != null) {
                    AdpArticleStock adpArticleStock = new AdpArticleStock(requireContext(), articles);
                    adpArticleStock.notifyDataSetChanged();
                    fragmentArticleSaleStockBinding.listviewArticleStock.setAdapter(adpArticleStock);
                }
            });
    }
}

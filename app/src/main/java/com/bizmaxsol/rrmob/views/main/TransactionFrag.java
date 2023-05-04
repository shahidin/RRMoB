package com.bizmaxsol.rrmob.views.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.adapters.TransactionAdapter;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;
import com.bizmaxsol.rrmob.views.MainActivity;

public class TransactionFrag extends Fragment implements TransactionAdapter.OnitemTransClickListener {

    RecyclerView recyclerView;
    MainViewModel mainViewModel;
    TextView tvclientid;
    TextView tvdate;
    TextView tvclientloc;
    TextView tvdatevalid;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_transaction,container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mainViewModel=new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        tvclientid=view.findViewById(R.id.tvclientid);
        tvdate=view.findViewById(R.id.tvdate);
        tvclientloc=view.findViewById(R.id.tvclientloc);
        tvdatevalid=view.findViewById(R.id.tvdatevalid);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new TransactionAdapter(mainViewModel.getTransactionLiveData(),this));
        ((MainActivity)getActivity()).binding.ivback.setOnClickListener(v-> {
            requireActivity().getSupportFragmentManager().popBackStack();
            ((MainActivity)getActivity()).binding.ivback.setVisibility(View.GONE);
        });
        tvclientid.setText("CLIENT NAME: "+mainViewModel.getClient().getClient_sname());
        tvdate.setText("LOGIN DATE: "+mainViewModel.getResponseDate().getValue().getDate());
        tvclientloc.setText("LOCATION: "+mainViewModel.getClient().getBearing_sdesc());
        tvdatevalid.setText("VALID: "+mainViewModel.getClient().getClient_dvalidate());
    }

    @Override
    public void onItemClick(int position) {

        int id = mainViewModel.getTransactionLiveData().getValue().get(position).getUsertran_nid();
        Log.d("clickTransaction", String.valueOf(id));
        ((MainActivity)getActivity()).loadRights(id);
    }
}

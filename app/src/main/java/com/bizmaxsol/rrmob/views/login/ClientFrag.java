package com.bizmaxsol.rrmob.views.login;



import static com.bizmaxsol.rrmob.constants.AppMainClientConstants.setBaseUrl;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.Client;
import com.bizmaxsol.rrmob.viewmodels.LoginViewModel;
import com.bizmaxsol.rrmob.views.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;

public class ClientFrag extends Fragment {

    LoginViewModel loginViewModel;
    TextInputEditText clientId_view;
    Spinner clientspinner;
    Button btnclient;
    ArrayList<String> network_arr=new ArrayList<>();
    List<Client> client_arr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        loginViewModel= new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        clientId_view=view.findViewById(R.id.clientId_view);
        clientspinner=view.findViewById(R.id.clientspinner);
        btnclient=view.findViewById(R.id.btnclient);
        clientId_view.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        btnclient.setTag(0);
        btnclient.setOnClickListener(v->{
            if(btnclient.getTag().toString().equals("0")){
                if(clientId_view.getText().toString().equals("")){
                    clientId_view.requestFocus();
                }else{
                    loginViewModel.getClientList(clientId_view.getText().toString()).observe(getViewLifecycleOwner(),clients -> {
                        client_arr=clients;
                        clientspinner.setVisibility(View.VISIBLE);
                        for(int idx=0; idx<clients.size();idx++){
                            network_arr.add((idx+1)+"- "+clients.get(idx).getBearing_sdesc());
                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_dropdown_item,
                                network_arr);
                        clientspinner.setAdapter(arrayAdapter);
                        clientId_view.setEnabled(false);
                        btnclient.setTag(1);
                    });
                }
            }else{
                int cid=Integer.parseInt(splitNameId(clientspinner.getSelectedItem().toString()));
                Client client=client_arr.get(cid-1);
                loginViewModel.insertClient(client);
                setBaseUrl(client.getBearing_surl());
                ((LoginActivity)getActivity()).mountLoginFrag();
            }
        });
    }
    private String splitNameId(String s){
        String[] separated = s.split("-");
        return separated[0];
    }
}

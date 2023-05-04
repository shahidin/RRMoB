package com.bizmaxsol.rrmob.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.viewmodels.LoginViewModel;
import com.bizmaxsol.rrmob.views.LoginActivity;
import com.bizmaxsol.rrmob.views.MainActivity;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.bizmaxsol.rrmob.R;


import java.util.Timer;
import java.util.TimerTask;

public class LoginFrag extends Fragment {
    TextInputEditText loginId_view;
    PinView pinView;
    TextInputLayout password;
    TextInputEditText password_view;
    TextView tvpasspin;
    TextView tvforgotpass;
    LoginViewModel loginViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here

        loginViewModel= new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        loginId_view=view.findViewById(R.id.loginId_view);
        pinView=view.findViewById(R.id.pinView);
        password=view.findViewById(R.id.password);
        password_view=view.findViewById(R.id.password_view);
        tvpasspin=view.findViewById(R.id.tvpasspin);
        tvforgotpass=view.findViewById(R.id.tvforgotpass);
        TextView tvresetconfig=view.findViewById(R.id.tvresetconfig);
        Button btnLogin=view.findViewById(R.id.btnLogin);

        loginId_view.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        tvpasspin.setOnClickListener(v->checkpinpass());
        btnLogin.setOnClickListener(v-> {
            doLogin();
        });
        tvresetconfig.setOnClickListener(v->resetConfigDialog());
    }
    private void resetConfig(){
        loginViewModel.deleteClient();
        notify("Restart Application to reset network configuration!");
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.exit(0);
                timer.cancel(); //this will cancel the timer of the system
            }
        }, 5000);
    }
    public void checkpinpass(){
        if (loginViewModel.getIpinpass()==0){
            pinView.setVisibility(View.GONE);
            password.setVisibility(View.VISIBLE);
            tvpasspin.setText("Login with PIN");
            loginViewModel.setIpinpass(1);
        }else{
            pinView.setVisibility(View.VISIBLE);
            password.setVisibility(View.GONE);
            tvpasspin.setText("Login with Password");
            loginViewModel.setIpinpass(0);
        }
    }
        private void doLogin() {
        if (loginViewModel.getIpinpass()==0){
            loginViewModel.setuLogin(loginId_view.getText().toString());
            loginViewModel.setuPass(pinView.getText().toString());
        }else{
            loginViewModel.setuLogin(loginId_view.getText().toString());
            loginViewModel.setuPass(password_view.getText().toString());
        }
        loginViewModel.checkLoginFromNetwork().observe(requireActivity(),user -> {
            if(user!=null){
                loginViewModel.insertUser(user);
                if(user.getUsersapp_nactiveflg().equals("1")){
                    if(user.getUsersapp_npinset().equals("1")){
                        Intent intent = new Intent(requireActivity(), MainActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    }else{
                        loginViewModel.setSpID(user.getUsersapp_nid());
                        ((LoginActivity)requireActivity()).mountProfileFrag();
                    }
                }else{
                    notify("User Not Active");
                }
            }else{
                notify("Login Id or PIN/Password Worng!");
            }
        });
    }

    private void notify(String xtext){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Message");
        builder.setIcon(R.drawable.ic_warning);
        builder.setMessage(xtext);
        builder.setCancelable(true);
        final AlertDialog closedialog= builder.create();
        closedialog.show();
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                closedialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 4500); // the timer will count 5 seconds....
    }
    private void resetConfigDialog() {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(requireContext());
        alertDialogBuilder
                .setIcon(R.drawable.ic_warning)
                .setTitle("Alert")
                .setMessage("Do you want to reset network configuration!")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) ->resetConfig())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

//Intent intent = new Intent(requireActivity(), MainActivity.class);
//                            intent.putExtra("compId",companies.get(0).getCompany_nid());
//                            startActivity(intent);
//                            requireActivity().finish();

package com.bizmaxsol.rrmob.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.QuestionDefault;
import com.bizmaxsol.rrmob.viewmodels.LoginViewModel;
import com.bizmaxsol.rrmob.views.MainActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class SetPinFrag extends Fragment {

    LoginViewModel loginViewModel;
    TextInputEditText spLoginName_view;
    TextView tvloginidcheck;
    TextInputEditText spPassword_view;
    TextInputEditText spRePassword_view;
    PinView spPIN;
    PinView spRePIN;
    AutoCompleteTextView spQuestion_view;
    TextInputEditText spAnswer_view;
    TextView tvpasscheck;
    TextView tvpincheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_pin, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        loginViewModel= new ViewModelProvider(requireActivity()).get(LoginViewModel.class);


        spLoginName_view=view.findViewById(R.id.spLoginName_view);
        tvloginidcheck=view.findViewById(R.id.tvloginidcheck);
        spPassword_view=view.findViewById(R.id.spPassword_view);
        spRePassword_view=view.findViewById(R.id.spRePassword_view);
        spPIN=view.findViewById(R.id.spPIN);
        spRePIN=view.findViewById(R.id.spRePIN);
        spQuestion_view=view.findViewById(R.id.spQuestion_view);
        spAnswer_view=view.findViewById(R.id.spAnswer_view);
        tvpasscheck=view.findViewById(R.id.tvpasscheck);
        tvpincheck=view.findViewById(R.id.tvpincheck);
        Button btn_spConfirm=view.findViewById(R.id.btn_spConfirm);

        ArrayList<String> q=new ArrayList<>();
        ArrayAdapter<String> quesAdapter= new ArrayAdapter<>(requireContext(), R.layout.string_list, q);

        spLoginName_view.setText(loginViewModel.getuLogin());

        loginViewModel.getQuestion().observe(requireActivity(),questionDefaults -> {

            for(QuestionDefault questionDefault:questionDefaults){
                q.add(questionDefault.getUserq_squestions());
            }
            spQuestion_view.setAdapter(quesAdapter);
        });

        spRePassword_view.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if((spPassword_view.getText().toString()).equals(spRePassword_view.getText().toString())){
                    tvpasscheck.setText("");
                }else{
                    tvpasscheck.setText("Password does not match");
                }
            }
        });

        spRePIN.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if((spPIN.getText().toString()).equals(spRePIN.getText().toString())){
                    tvpincheck.setText("");
                }else{
                    tvpincheck.setText("PIN does not match");
                }
            }
        });

        btn_spConfirm.setOnClickListener(v->doSetPin());

    }
    private void doSetPin(){
        if ((TextUtils.isEmpty(Objects.requireNonNull(spLoginName_view.getText()).toString()))){
            spLoginName_view.requestFocus();
        }else if ((TextUtils.isEmpty(spQuestion_view.getText().toString()))) {
            spQuestion_view.requestFocus();
        }else if ((TextUtils.isEmpty(Objects.requireNonNull(spAnswer_view.getText()).toString()))) {
            spAnswer_view.requestFocus();
        }else if (!(tvpasscheck.getText().toString()).equals("")) {
            notify("Password does not match!");
        }else if (!(tvpincheck.getText().toString()).equals("")) {
            notify("PIN does not match!");
        }else{
            loginViewModel.setSpLogin(spLoginName_view.getText().toString());
            loginViewModel.setSpPassword(spPassword_view.getText().toString());
            loginViewModel.setSpPin(spPIN.getText().toString());
            loginViewModel.setSpQuestion(spQuestion_view.getText().toString());
            loginViewModel.setSpAnswer(spAnswer_view.getText().toString());
            loginViewModel.setpinLiveData().observe(requireActivity(),status -> {
                switch (status.getStatus()) {
                    case 2:
                        notify("Please try again later");
                        break;
                    case 1:
                        sucess();
                        break;
                    case 0:
                        notify("Login ID already exist!");
                        tvloginidcheck.setText("Login ID already exist!");
                        break;
                    default:
                        notify("Please check internet connection!");
                }
            });
        }
    }
    private void sucess(){
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        startActivity(intent);
        requireActivity().finish();
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
        }, 5000); // the timer will count 5 seconds....

    }
}

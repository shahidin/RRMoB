package com.bizmaxsol.rrmob.views;



import static com.bizmaxsol.rrmob.constants.AppMainClientConstants.setBaseUrl;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.databinding.ActivityLoginBinding;
import com.bizmaxsol.rrmob.viewmodels.LoginViewModel;
import com.bizmaxsol.rrmob.views.login.ClientFrag;
import com.bizmaxsol.rrmob.views.login.LoginFrag;
import com.bizmaxsol.rrmob.views.login.SetPinFrag;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG=LoginActivity.class.getSimpleName();

    LoginViewModel loginViewModel;


    ActivityLoginBinding binding;

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        //ViewModel Provide
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);

        if(loginViewModel.getClient()==null){
            mountClientFrag();
        }else{
            setBaseUrl(loginViewModel.getClient().getBearing_surl());
            mountLoginFrag();
        }

    }
    public void mountClientFrag(){
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.loginFregPlaceholder, new ClientFrag(),TAG);
        ft.commit();
    }
    public void mountLoginFrag(){
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.loginFregPlaceholder, new LoginFrag(),TAG);
        ft.commit();
    }
    public void mountProfileFrag(){
        loginViewModel.getQuestion().observe(this,questionDefaults -> {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.loginFregPlaceholder, new SetPinFrag(),TAG);
            ft.addToBackStack(null);
            ft.commit();
        });
    }
}
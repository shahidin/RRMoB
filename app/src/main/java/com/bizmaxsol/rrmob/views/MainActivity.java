package com.bizmaxsol.rrmob.views;

import static com.bizmaxsol.rrmob.constants.AppConstants.END_SCALE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.views.main.BillLogFrag;
import com.bizmaxsol.rrmob.views.main.ModuleFrag;
import com.bizmaxsol.rrmob.views.main.TransactionFrag;
import com.google.android.material.navigation.NavigationView;
import com.bizmaxsol.rrmob.databinding.ActivityMainBinding;
import com.bizmaxsol.rrmob.models.TransactionRights;
import com.bizmaxsol.rrmob.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    MainViewModel mainViewModel;

    public ActivityMainBinding binding;

    FragmentTransaction ft;

    List<TransactionRights> tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        //ViewModel Provide
        mainViewModel= new ViewModelProvider(this).get(MainViewModel.class);
        navigationdrawer();
        mainViewModel.getUserFromDataBase();
        mainViewModel.getClientFromDataBase();
        mainViewModel.getDateFromNetwork();
        //ModuleFrag
        mainViewModel.getModuleRightsLiveData().observe(this,moduleRights -> {
            if(moduleRights!=null) {
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.mainfragview, new ModuleFrag());
                ft.commit();
            }
        });
        binding.searchMain.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        //header and drawer header--------------------------------------
        binding.mainusername.setText(mainViewModel.getUser().getUsersapp_sname());
        binding.maincompanyname.setText("RR Mobile Suit");
        View view = binding.navigationView.getHeaderView(0);
        TextView navusername=view.findViewById(R.id.navusername);
        TextView navcompanyname=view.findViewById(R.id.navcompanyname);
        navusername.setText(mainViewModel.getUser().getUsersapp_sname());
        navcompanyname.setText("RR Mobile Suit");

        mainViewModel.getSearch().observe(this,transactionRights -> {
            if(transactionRights!=null){
                tr=transactionRights;
                ArrayList<String> tr_arr = new ArrayList<>();
                for (int idx = 0; idx < transactionRights.size(); idx++) {
                    tr_arr.add(transactionRights.get(idx).getUsertran_nid() + "- " + transactionRights.get(idx).getUsertran_scaption());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                        tr_arr);
                binding.searchMain.setAdapter(arrayAdapter);
            }
        });
        binding.searchMain.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(tr!=null) {
                    for (TransactionRights t : tr) {
                        if ((t.getUsertran_nid() + "- " + t.getUsertran_scaption()).equals(binding.searchMain.getText().toString().trim())) {
                            if (t.getUsertranright_nallowed() != 0) {
                                mainViewModel.getTransactionRightsLiveData(t.getUsertran_nmodid()).observe(MainActivity.this, transactionRights -> {
                                    binding.ivback.setVisibility(View.VISIBLE);
                                    ft = getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.mainfragview, new RightsFrag(t.getUsertran_nid()));
                                    ft.setReorderingAllowed(true);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    binding.searchMain.setText("");
                                });
                            } else {
                                notify1();
                            }
                        }
                    }
                }
            }
        });
        //-----------------------------------------------------------------
    }
    public void loadTransactionfrag(String id){

        mainViewModel.getTransactionRightsLiveData(id).observe(this,transactionRights -> {
            if(transactionRights!=null) {
                binding.ivback.setVisibility(View.VISIBLE);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainfragview, new TransactionFrag());
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
    public  void loadRights(int id){
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainfragview, new RightsFrag(id));
        ft.setReorderingAllowed(true);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerVisible(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            menuExit();
        }
    }

    private void navigationdrawer() {
        binding.navigationView.bringToFront();
        binding.navigationView.setNavigationItemSelectedListener(this);
        binding.navigationView.setCheckedItem(R.id.menu_home);
        binding.menu.setOnClickListener(v->{
            if(binding.drawerLayout.isDrawerVisible(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
//        drawerLayout.setScrimColor(getResources().getColor(R.color.purple_500));
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                binding.containView.setScaleX(offsetScale);
                binding.containView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = binding.containView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                binding.containView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit:
                menuExit();
                return true;
            case R.id.menu_logout:
                menuLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void menuLogout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void menuExit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder.setIcon(R.drawable.ic_warning);
        alertDialogBuilder
                .setMessage("Do you want to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        (dialog, id) -> {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void notify1(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage("Permission Deny");
        builder.setCancelable(true);
        final androidx.appcompat.app.AlertDialog closedialog= builder.create();
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
}
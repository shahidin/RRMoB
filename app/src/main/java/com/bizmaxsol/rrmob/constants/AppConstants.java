package com.bizmaxsol.rrmob.constants;


import com.bizmaxsol.rrmob.retrofit.APIClient;
import com.bizmaxsol.rrmob.retrofit.LoginService;
import com.bizmaxsol.rrmob.retrofit.MainService;
import com.bizmaxsol.rrmob.retrofit.MasterService;
import com.bizmaxsol.rrmob.retrofit.ReportServices;

public class AppConstants {

    public static final ReportServices reportServices= APIClient.getRetrofitInstance().create(ReportServices.class);

    public static final LoginService loginService= APIClient.getRetrofitInstance().create(LoginService.class);

    public static final MainService mainservice= APIClient.getRetrofitInstance().create(MainService.class);

    public static final MasterService masterService= APIClient.getRetrofitInstance().create(MasterService.class);

    public static final float END_SCALE=0.7f;

}

package com.bizmaxsol.rrmob.constants;


import com.bizmaxsol.rrmob.retrofit.APIClient;
import com.bizmaxsol.rrmob.retrofit.ClientService;

public class AppClientConstants {

    public static final String OUR_BASE_URL="http://gate.omnisenze.com/rrgate/api/clientparaphase/";

    public static final ClientService CLIENT_SERVICE = APIClient.getOurRetrofitInstance().create(ClientService.class);


}

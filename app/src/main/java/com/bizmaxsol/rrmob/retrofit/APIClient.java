package com.bizmaxsol.rrmob.retrofit;



import static com.bizmaxsol.rrmob.constants.AppClientConstants.OUR_BASE_URL;
import static com.bizmaxsol.rrmob.constants.AppMainClientConstants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static Retrofit getOurRetrofitInstance(){
        return new Retrofit.Builder()
            .baseUrl(OUR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}

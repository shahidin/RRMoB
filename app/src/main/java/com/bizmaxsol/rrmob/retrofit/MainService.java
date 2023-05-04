package com.bizmaxsol.rrmob.retrofit;


import com.bizmaxsol.rrmob.models.ModuleRights;
import com.bizmaxsol.rrmob.models.ResponseDate;
import com.bizmaxsol.rrmob.models.TransactionRights;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainService {
    @GET("usertranright/modules/{uId}")
    Call<List<ModuleRights>> getModuleRights(@Path("uId") String uId);

    @GET("usertranright/detailmod/{uId}/{modId}")
    Call<List<TransactionRights>> getTransactionRights(@Path("uId") String uId, @Path("modId") String modId);


    @GET("usertranright/detail/{uId}")
    Call<List<TransactionRights>> getSearchModuleRights(@Path("uId") String uId);

    @GET("util/date")
    Call<ResponseDate> getDate();
}

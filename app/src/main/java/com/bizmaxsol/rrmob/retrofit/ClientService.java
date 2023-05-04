package com.bizmaxsol.rrmob.retrofit;


import com.bizmaxsol.rrmob.models.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientService {
    @GET("getclientbearing.php/")
    Call<List<Client>> getClient(@Query("CLIENT_PARAPHRASE") String cName);
}

package com.bizmaxsol.rrmob.retrofit;


import com.bizmaxsol.rrmob.models.QuestionDefault;
import com.bizmaxsol.rrmob.models.ResponseUser;
import com.bizmaxsol.rrmob.models.Status;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoginService {


    @GET("login/{loginId}/{pass}")
    Call<ResponseUser> getLoginPass(@Path("loginId") String loginId, @Path("pass") String pass);

    @GET("loginpin/{loginId}/{pass}")
    Call<ResponseUser> getLoginPin(@Path("loginId") String loginId,@Path("pass") String pass);

    @GET("userq")
    Call<List<QuestionDefault>> getQuestion();

    @PUT("usersdetails/{id}")
    Call<Status> editUser(@Path("id") String id, @Body ResponseUser responseUser);

}

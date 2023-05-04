package com.bizmaxsol.rrmob.retrofit;

import com.bizmaxsol.rrmob.models.ResponseBillLog;
import com.bizmaxsol.rrmob.models.ResponseCustomer;
import com.bizmaxsol.rrmob.models.ResponsePoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MasterService {

    //Customer

    @POST("customer/")
    Call<ResponseCustomer> postCustomer (@Body ResponseCustomer responseCustomer);

    @PUT("customer/{id}")
    Call<ResponseCustomer> putCustomer (@Path("id") int id, @Body ResponseCustomer responseCustomer);

    @GET("customer/search/{number}/{name}")
    Call<List<ResponseCustomer>> getCustomerSearch (@Path("number") String number,@Path("name") String name);

    @GET("customer/{id}")
    Call<ResponseCustomer> getCustomer (@Path("id") int id);

    @GET("customer/")
    Call <List<ResponseCustomer>> getCustomerAllList ();

    @GET("customer/getPoints/{id}")
    Call<ResponsePoints> getCustomerPoints (@Path("id") int id);

    @GET("customer/getpointdetail/{id}")
    Call<List<ResponsePoints>> getPointsDetail(@Path("id") int id);


}

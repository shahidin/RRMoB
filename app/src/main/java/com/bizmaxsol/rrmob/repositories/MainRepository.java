package com.bizmaxsol.rrmob.repositories;


import static com.bizmaxsol.rrmob.constants.AppConstants.mainservice;
import static com.bizmaxsol.rrmob.constants.AppConstants.masterService;
import static com.bizmaxsol.rrmob.constants.AppConstants.reportServices;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.bizmaxsol.rrmob.db.AppDatabase;
import com.bizmaxsol.rrmob.db.ClientDao;
import com.bizmaxsol.rrmob.db.ResponseUserDao;
import com.bizmaxsol.rrmob.models.Client;
import com.bizmaxsol.rrmob.models.ModuleRights;
import com.bizmaxsol.rrmob.models.ResponseArticle;
import com.bizmaxsol.rrmob.models.ResponseBarcodeDetail;
import com.bizmaxsol.rrmob.models.ResponseBarcodeHistory;
import com.bizmaxsol.rrmob.models.ResponseBillLog;
import com.bizmaxsol.rrmob.models.ResponseCustomer;
import com.bizmaxsol.rrmob.models.ResponseDate;
import com.bizmaxsol.rrmob.models.ResponseMaster;
import com.bizmaxsol.rrmob.models.ResponseOC;
import com.bizmaxsol.rrmob.models.ResponsePurchase;
import com.bizmaxsol.rrmob.models.ResponseSaleDrillDown;
import com.bizmaxsol.rrmob.models.ResponseSaleSession;
import com.bizmaxsol.rrmob.models.ResponseSales;
import com.bizmaxsol.rrmob.models.ResponseSupplier;
import com.bizmaxsol.rrmob.models.ResponseUser;
import com.bizmaxsol.rrmob.models.TransactionRights;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.internal.EverythingIsNonNull;

public class MainRepository {

    ResponseUserDao responseUserDao;
    ClientDao clientDao;

    public MainRepository(Application application){
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "rrmob_database").allowMainThreadQueries().build();
        responseUserDao =db.userDao();
        clientDao=db.clientDao();

    }

    //--------------------------Get from Network------------------------------------
    @EverythingIsNonNull
    public LiveData<List<TransactionRights>> getSearchFromNetwork(String uId){
        MutableLiveData<List<TransactionRights>> data=new MutableLiveData<>();
        Call<List<TransactionRights>> call= mainservice.getSearchModuleRights(uId);
        call.enqueue(new Callback<List<TransactionRights>>() {
            @Override
            public void onResponse(Call<List<TransactionRights>> call, Response<List<TransactionRights>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<TransactionRights>> call, Throwable t) {

            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ModuleRights>> getModuleRights(String uId){
        MutableLiveData<List<ModuleRights>> data=new MutableLiveData<>();
        Call<List<ModuleRights>> call=mainservice.getModuleRights(uId);
        call.enqueue(new Callback<List<ModuleRights>>() {
            @Override
            public void onResponse(Call<List<ModuleRights>> call, Response<List<ModuleRights>> response) {
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<ModuleRights>> call, Throwable t) {

            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<TransactionRights>> getTransactionRights(String uId, String modId){
        MutableLiveData<List<TransactionRights>> data=new MutableLiveData<>();
        Call<List<TransactionRights>> call=mainservice.getTransactionRights(uId,modId);
        call.enqueue(new Callback<List<TransactionRights>>() {
            @Override
            public void onResponse(Call<List<TransactionRights>> call, Response<List<TransactionRights>> response) {
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<TransactionRights>> call, Throwable t) {

            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseBillLog>> getEditedBillLogFromNetwork(String fdate, String tdate){
        MutableLiveData<List<ResponseBillLog>> data=new MutableLiveData<>();
        Call<List<ResponseBillLog>> call= reportServices.getEditedBillLog(fdate,tdate);
        call.enqueue(new Callback<List<ResponseBillLog>>() {
            @Override
            public void onResponse(Call<List<ResponseBillLog>> call, Response<List<ResponseBillLog>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseBillLog>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseBillLog>> getDeletedBillLogFromNetwork(String fdate, String tdate){
        MutableLiveData<List<ResponseBillLog>> data=new MutableLiveData<>();
        Call<List<ResponseBillLog>> call= reportServices.getDeletedBillLog(fdate,tdate);
        call.enqueue(new Callback<List<ResponseBillLog>>() {
            @Override
            public void onResponse(Call<List<ResponseBillLog>> call, Response<List<ResponseBillLog>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseBillLog>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseArticle>> getArticleSalesFromNetwork(String article){
        MutableLiveData<List<ResponseArticle>> data=new MutableLiveData<>();
        Call<List<ResponseArticle>> call= reportServices.getArticleSales(article);
        call.enqueue(new Callback<List<ResponseArticle>>() {
            @Override
            public void onResponse(Call<List<ResponseArticle>> call, Response<List<ResponseArticle>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseArticle>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseArticle>> getArticleStockFromNetwork(String article){
        MutableLiveData<List<ResponseArticle>> data=new MutableLiveData<>();
        Call<List<ResponseArticle>> call= reportServices.getArticleStockSize(article);
        call.enqueue(new Callback<List<ResponseArticle>>() {
            @Override
            public void onResponse(Call<List<ResponseArticle>> call, Response<List<ResponseArticle>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseArticle>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleSession>> getSaleSessnFromNetwork(String date){
        MutableLiveData<List<ResponseSaleSession>> data=new MutableLiveData<>();
        Call<List<ResponseSaleSession>> call= reportServices.getSaleSession(date);
        call.enqueue(new Callback<List<ResponseSaleSession>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleSession>> call, Response<List<ResponseSaleSession>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleSession>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSales>> getSaleSessnDetailFromNetwork(String sessnId){
        MutableLiveData<List<ResponseSales>> data=new MutableLiveData<>();
        Call<List<ResponseSales>> call= reportServices.getSessnSaleList(sessnId);
        call.enqueue(new Callback<List<ResponseSales>>() {
            @Override
            public void onResponse(Call<List<ResponseSales>> call, Response<List<ResponseSales>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSales>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<ResponseDate> getDateFromNetwork(){
        MutableLiveData<ResponseDate> data=new MutableLiveData<>();
        Call<ResponseDate> call= mainservice.getDate();
        call.enqueue(new Callback<ResponseDate>() {
            @Override
            public void onResponse(Call<ResponseDate> call, Response<ResponseDate> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseDate> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseBarcodeHistory>> getBarcodeHistoryFromNetwork(String barcode){
        MutableLiveData<List<ResponseBarcodeHistory>> data=new MutableLiveData<>();
        Call<List<ResponseBarcodeHistory>> call= reportServices.getBarcodeHistory(barcode);
        call.enqueue(new Callback<List<ResponseBarcodeHistory>>() {
            @Override
            public void onResponse(Call<List<ResponseBarcodeHistory>> call, Response<List<ResponseBarcodeHistory>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseBarcodeHistory>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<ResponseBarcodeDetail> getBarcodeDetailFromNetwork(String barcode){
        MutableLiveData<ResponseBarcodeDetail> data=new MutableLiveData<>();
        Call<ResponseBarcodeDetail> call= reportServices.getBarcodeDetail(barcode);
        call.enqueue(new Callback<ResponseBarcodeDetail>() {
            @Override
            public void onResponse(Call<ResponseBarcodeDetail> call, Response<ResponseBarcodeDetail> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseBarcodeDetail> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSales>> getDailySalesFromNetwork(String fdate){
        MutableLiveData<List<ResponseSales>> data=new MutableLiveData<>();
        Call<List<ResponseSales>> call= reportServices.getSaleList(fdate);
        call.enqueue(new Callback<List<ResponseSales>>() {
            @Override
            public void onResponse(Call<List<ResponseSales>> call, Response<List<ResponseSales>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSales>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSupplier>> getSupplierFromNetwork(){
        MutableLiveData<List<ResponseSupplier>> data=new MutableLiveData<>();
        Call<List<ResponseSupplier>> call= reportServices.getSupplier();
        call.enqueue(new Callback<List<ResponseSupplier>>() {
            @Override
            public void onResponse(Call<List<ResponseSupplier>> call, Response<List<ResponseSupplier>> response) {

                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSupplier>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponsePurchase>> getPurchaseFromNetwork(String fdate, String tdate, String SupplierId,int stat){
        MutableLiveData<List<ResponsePurchase>> data=new MutableLiveData<>();
        Call<List<ResponsePurchase>> call;
        if(stat==1){
            call= reportServices.getPurchase(fdate,tdate,SupplierId);
        }else{
            call= reportServices.getPurchaseReturn(fdate,tdate,SupplierId);
        }
        call.enqueue(new Callback<List<ResponsePurchase>>() {
            @Override
            public void onResponse(Call<List<ResponsePurchase>> call, Response<List<ResponsePurchase>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponsePurchase>> call, Throwable t) {
            }
        });
        return data;
    }

    @EverythingIsNonNull
    public LiveData<List<ResponsePurchase>> getPurchaseDetailFromNetwork(String pId,int stat){
        MutableLiveData<List<ResponsePurchase>> data=new MutableLiveData<>();
        Call<List<ResponsePurchase>> call;
        if(stat==1){
            call= reportServices.getPurchaseDetail(pId);
        }else{
            call= reportServices.getPurchaseReturnDetail(pId);
        }
        call.enqueue(new Callback<List<ResponsePurchase>>() {
            @Override
            public void onResponse(Call<List<ResponsePurchase>> call, Response<List<ResponsePurchase>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponsePurchase>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD1FromNetwork(String fdate, String tdate){
        MutableLiveData<List<ResponseSaleDrillDown>> data=new MutableLiveData<>();
        Call<List<ResponseSaleDrillDown>> call=reportServices.getSaleDrillDown1(fdate,tdate);
        call.enqueue(new Callback<List<ResponseSaleDrillDown>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleDrillDown>> call, Response<List<ResponseSaleDrillDown>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleDrillDown>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD2FromNetwork(String fdate, String tdate,String section,String nTop){
        MutableLiveData<List<ResponseSaleDrillDown>> data=new MutableLiveData<>();
        Call<List<ResponseSaleDrillDown>> call=reportServices.getSaleDrillDown2(fdate,tdate,section,nTop);
        call.enqueue(new Callback<List<ResponseSaleDrillDown>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleDrillDown>> call, Response<List<ResponseSaleDrillDown>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleDrillDown>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD3FromNetwork(String fdate, String tdate,String itemName,int brand,int supplier,String topn){
        MutableLiveData<List<ResponseSaleDrillDown>> data=new MutableLiveData<>();
        Call<List<ResponseSaleDrillDown>> call=reportServices.getSaleDrillDown3(fdate,tdate,itemName,brand,supplier,topn);
        call.enqueue(new Callback<List<ResponseSaleDrillDown>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleDrillDown>> call, Response<List<ResponseSaleDrillDown>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleDrillDown>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleDrillDown>> getStockDD1FromNetwork(String fdate, String tdate){
        MutableLiveData<List<ResponseSaleDrillDown>> data=new MutableLiveData<>();
        Call<List<ResponseSaleDrillDown>> call=reportServices.getStockDrillDown1(fdate,tdate);
        call.enqueue(new Callback<List<ResponseSaleDrillDown>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleDrillDown>> call, Response<List<ResponseSaleDrillDown>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleDrillDown>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleDrillDown>> getStockDD2FromNetwork(String section){
        MutableLiveData<List<ResponseSaleDrillDown>> data=new MutableLiveData<>();
        Call<List<ResponseSaleDrillDown>> call=reportServices.getStockDrillDown2(section);
        call.enqueue(new Callback<List<ResponseSaleDrillDown>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleDrillDown>> call, Response<List<ResponseSaleDrillDown>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleDrillDown>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseSaleDrillDown>> getStockDD3FromNetwork(String itemName,int brand,int supplier){
        MutableLiveData<List<ResponseSaleDrillDown>> data=new MutableLiveData<>();
        Call<List<ResponseSaleDrillDown>> call=reportServices.getStockDrillDown3(itemName,brand,supplier);
        call.enqueue(new Callback<List<ResponseSaleDrillDown>>() {
            @Override
            public void onResponse(Call<List<ResponseSaleDrillDown>> call, Response<List<ResponseSaleDrillDown>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseSaleDrillDown>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseCustomer>> getCustomerFromNetwork(String number,String name){
        MutableLiveData<List<ResponseCustomer>> data=new MutableLiveData<>();
        Call<List<ResponseCustomer>> call= masterService.getCustomerSearch(number, name);
        call.enqueue(new Callback<List<ResponseCustomer>>() {
            @Override
            public void onResponse(Call<List<ResponseCustomer>> call, Response<List<ResponseCustomer>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseCustomer>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseOC>> getOCFromNetwork(String fdate, String tdate, int format,
                                                       String dipfil, String secfil, String itemfil,
                                                       String brandfil, String artfil, String szfil,
                                                       String colfil, int dipvis, int secvis,
                                                       int itemvis, int brandvis, int artvis,
                                                       int szvis, int colvis){
        MutableLiveData<List<ResponseOC>> data=new MutableLiveData<>();
        Call<List<ResponseOC>> call= reportServices.getOC(fdate, tdate, format, dipfil, secfil,
                itemfil, brandfil, artfil, szfil, colfil, dipvis, secvis, itemvis, brandvis, artvis, szvis, colvis);
        call.enqueue(new Callback<List<ResponseOC>>() {
            @Override
            public void onResponse(Call<List<ResponseOC>> call, Response<List<ResponseOC>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseOC>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseMaster>> getItemNameFromNetwork(){
        MutableLiveData<List<ResponseMaster>> data=new MutableLiveData<>();
        Call<List<ResponseMaster>> call=reportServices.getItemName();
        call.enqueue(new Callback<List<ResponseMaster>>() {
            @Override
            public void onResponse(Call<List<ResponseMaster>> call, Response<List<ResponseMaster>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseMaster>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseMaster>> getBrandNameFromNetwork(){
        MutableLiveData<List<ResponseMaster>> data=new MutableLiveData<>();
        Call<List<ResponseMaster>> call=reportServices.getBrandName();
        call.enqueue(new Callback<List<ResponseMaster>>() {
            @Override
            public void onResponse(Call<List<ResponseMaster>> call, Response<List<ResponseMaster>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseMaster>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseMaster>> getDivisionNameFromNetwork(){
        MutableLiveData<List<ResponseMaster>> data=new MutableLiveData<>();
        Call<List<ResponseMaster>> call=reportServices.getDivisionName();
        call.enqueue(new Callback<List<ResponseMaster>>() {
            @Override
            public void onResponse(Call<List<ResponseMaster>> call, Response<List<ResponseMaster>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseMaster>> call, Throwable t) {
            }
        });
        return data;
    }
    @EverythingIsNonNull
    public LiveData<List<ResponseMaster>> getSectionNameFromNetwork(){
        MutableLiveData<List<ResponseMaster>> data=new MutableLiveData<>();
        Call<List<ResponseMaster>> call=reportServices.getSectionName();
        call.enqueue(new Callback<List<ResponseMaster>>() {
            @Override
            public void onResponse(Call<List<ResponseMaster>> call, Response<List<ResponseMaster>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseMaster>> call, Throwable t) {
            }
        });
        return data;
    }
    //--------------------------Delete From Network--------------------------
//    @EverythingIsNonNull
//    public void deleteUserFromNetwork(String mid){
//        Call<UserCreateType> call= userService.deleteUser(mid);
//        call.enqueue(new Callback<UserCreateType>() {
//            @Override
//            public void onResponse(Call<UserCreateType> call, Response<UserCreateType> response) {
//            }
//            @Override
//            public void onFailure(Call<UserCreateType> call, Throwable t) {
//            }
//        });
//    }

    //----------------------Add Edit From Network--------------------------------------------
//    @EverythingIsNonNull
//    public LiveData<UserCreateType> AddEditUserToNetwork(UserCreateType userCreateType,String uId){
//        MutableLiveData<UserCreateType> data=new MutableLiveData<>();
//        Call<UserCreateType> call;
//        if(uId.equals("0")){
//            call=userService.postUser(userCreateType);
//        }else{
//            call=userService.putUser(userCreateType,uId);
//        }
//        call.enqueue(new Callback<UserCreateType>() {
//            @Override
//            public void onResponse(Call<UserCreateType> call, Response<UserCreateType> response) {
//                if(response.isSuccessful()){
//                    data.setValue(response.body());
//                }else{
//                    data.setValue(null);
//                }
//            }
//            @Override
//            public void onFailure(Call<UserCreateType> call, Throwable t) {
//
//            }
//        });
//        return data;
//    }

    //------------------from database-----------------------------------
    public ResponseUser getUserFromDatabase(){return  responseUserDao.getUser();}
    public Client getClientFromDatabase() { return clientDao.getClient(); }
}

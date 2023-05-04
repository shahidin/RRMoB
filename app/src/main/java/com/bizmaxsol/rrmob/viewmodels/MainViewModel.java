package com.bizmaxsol.rrmob.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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
import com.bizmaxsol.rrmob.repositories.MainRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    MainRepository mainRepository;
    private Client client;
    private LiveData<ResponseDate> responseDate;
    private LiveData<List<ResponseSaleSession>> salesessn;
    private LiveData<List<ResponsePurchase>> purchase;
    private LiveData<List<ResponseSaleDrillDown>> saleDD1;
    private LiveData<List<ResponseSaleDrillDown>> saleDD2;
    private LiveData<List<ResponseSaleDrillDown>> stockDD1;
    private LiveData<List<ResponseSaleDrillDown>> stockDD2;
    private LiveData<List<ResponseCustomer>> customers;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mainRepository=new MainRepository(application);
    }


    ResponseUser responseUser;

    LiveData<List<ModuleRights>> moduleLiveData;

    LiveData<List<TransactionRights>> transactionLiveData;

    public LiveData<List<ModuleRights>> getModuleLiveData() {
        return moduleLiveData;
    }
    public LiveData<List<TransactionRights>> getTransactionLiveData() {
        return transactionLiveData;
    }

    public LiveData<ResponseDate> getResponseDate() {
        return responseDate;
    }

    public ResponseUser getUser() {
        return responseUser;
    }

    public Client getClient() {
        return client;
    }

    public LiveData<List<ResponseSaleSession>> getSalesessn() {
        return salesessn;
    }

    public LiveData<List<ResponsePurchase>> getPurchase() {
        return purchase;
    }

    public LiveData<List<ResponseSaleDrillDown>> getSaleDD1() {
        return saleDD1;
    }
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD2() {
        return saleDD2;
    }
    public LiveData<List<ResponseSaleDrillDown>> getStockDD1() {
        return stockDD1;
    }
    public LiveData<List<ResponseSaleDrillDown>> getStockDD2() {
        return stockDD2;
    }
    public LiveData<List<ResponseCustomer>> getCustomers() {
        return customers;
    }
    //---------------------------get from network-------------------------------
    public LiveData<List<ModuleRights>> getModuleRightsLiveData(){
        moduleLiveData=mainRepository.getModuleRights(responseUser.getUsersapp_nid());
        return moduleLiveData;
    }
    public LiveData<List<TransactionRights>> getTransactionRightsLiveData(String  modId){
        transactionLiveData=mainRepository.getTransactionRights(responseUser.getUsersapp_nid(),modId);
        return transactionLiveData;
    }
        public LiveData<List<ResponseBillLog>> getEditedBillLog(String fdate,String tdate){
        return mainRepository.getEditedBillLogFromNetwork(fdate,tdate);
    }
    public LiveData<List<ResponseBillLog>> getDeletedBillLog(String fdate,String tdate){
        return mainRepository.getDeletedBillLogFromNetwork(fdate,tdate);
    }
    public LiveData<List<TransactionRights>> getSearch(){
        return mainRepository.getSearchFromNetwork(responseUser.getUsersapp_nid());
    }
    public LiveData<List<ResponseArticle>> getArticleSales(String article){
        return mainRepository.getArticleSalesFromNetwork(article);
    }
    public LiveData<List<ResponseArticle>> getArticleStocks(String article){
        return mainRepository.getArticleStockFromNetwork(article);
    }
    public LiveData<List<ResponseBarcodeHistory>> getBarcodeHistory(String barcode){
        return mainRepository.getBarcodeHistoryFromNetwork(barcode);
    }
    public LiveData<ResponseBarcodeDetail> getBarcodeDetail(String barcode){
        return mainRepository.getBarcodeDetailFromNetwork(barcode);
    }
    public LiveData<List<ResponseSaleSession>> getSaleSessn(String date){
        salesessn=mainRepository.getSaleSessnFromNetwork(date);
        return salesessn;
    }
    public LiveData<List<ResponseSales>> getSaleSessnDetail(String sessnId){
        return mainRepository.getSaleSessnDetailFromNetwork(sessnId);
    }
    public LiveData<ResponseDate> getDateFromNetwork() {
        responseDate=mainRepository.getDateFromNetwork();
        return responseDate;
    }
    public LiveData<List<ResponseSales>> getDailySales(String fdate){
        return mainRepository.getDailySalesFromNetwork(fdate);
    }
    public LiveData<List<ResponseSupplier>> getSupplier(){
        return mainRepository.getSupplierFromNetwork();
    }
    public LiveData<List<ResponsePurchase>> getPurchaselist(String fdate,String tdate, String supplierId,int stat){
        purchase=mainRepository.getPurchaseFromNetwork(fdate,tdate,supplierId,stat);
        return purchase;
    }
    public LiveData<List<ResponsePurchase>> getPurchaseDetaillist(String pId,int stat){
        return mainRepository.getPurchaseDetailFromNetwork(pId,stat);
    }
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD1list(String fdate,String tdate) {
        saleDD1=mainRepository.getSaleDD1FromNetwork(fdate,tdate);
        return saleDD1;
    }
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD2list(String fdate,String tdate,String section,String nTop) {
        saleDD2=mainRepository.getSaleDD2FromNetwork(fdate,tdate,section,nTop);
        return saleDD2;
    }
    public LiveData<List<ResponseSaleDrillDown>> getSaleDD3list(String fdate,String tdate,String itemName,int brand,int supplier,String nTop) {
        return mainRepository.getSaleDD3FromNetwork(fdate,tdate,itemName,brand,supplier,nTop);
    }
    public LiveData<List<ResponseSaleDrillDown>> getStockDD1list(String fdate,String tdate) {
        stockDD1=mainRepository.getStockDD1FromNetwork(fdate,tdate);
        return stockDD1;
    }
    public LiveData<List<ResponseSaleDrillDown>> getStockDD2list(String section) {
        stockDD2=mainRepository.getStockDD2FromNetwork(section);
        return stockDD2;
    }
    public LiveData<List<ResponseSaleDrillDown>> getStockDD3list(String itemName,int brand,int supplier) {
        return mainRepository.getStockDD3FromNetwork(itemName,brand,supplier);
    }
    public LiveData<List<ResponseCustomer>> getCustomerlist(String number,String name) {
        customers=mainRepository.getCustomerFromNetwork(number,name);
        return customers;
    }
    public LiveData<List<ResponseOC>> getOClist(String fdate, String tdate, int format,
                                                String dipfil, String secfil, String itemfil,
                                                String brandfil, String artfil, String szfil,
                                                String colfil, int dipvis, int secvis,
                                                int itemvis, int brandvis, int artvis,
                                                int szvis, int colvis) {
        return mainRepository.getOCFromNetwork(fdate, tdate, format, dipfil, secfil,
                itemfil, brandfil, artfil, szfil, colfil, dipvis, secvis, itemvis, brandvis, artvis, szvis, colvis);
    }
    public LiveData<List<ResponseMaster>> getItemNamelist() {
        return mainRepository.getItemNameFromNetwork();
    }
    public LiveData<List<ResponseMaster>> getBrandNamelist() {
        return mainRepository.getBrandNameFromNetwork();
    }
    public LiveData<List<ResponseMaster>> getDivisionNamelist() {
        return mainRepository.getDivisionNameFromNetwork();
    }
    public LiveData<List<ResponseMaster>> getSectionNamelist() {
        return mainRepository.getSectionNameFromNetwork();
    }
    //----------------------------update on network------------------------------------
//    public LiveData<UserCreateType> updateUser(UserCreateType userCreateType,String uid){
//        return mainRepository.AddEditUserToNetwork(userCreateType,uid);
//    }

    //---------------------------delete from network----------------------------
//    public void deleteUser(String mid){
//        mainRepository.deleteUserFromNetwork(mid);
//    }

            //-------------------------------from database-----------------------------------
    public void getUserFromDataBase(){ responseUser=mainRepository.getUserFromDatabase();}
    public void getClientFromDataBase() { client=mainRepository.getClientFromDatabase();}

}

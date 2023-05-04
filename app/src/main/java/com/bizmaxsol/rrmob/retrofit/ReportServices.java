package com.bizmaxsol.rrmob.retrofit;

import com.bizmaxsol.rrmob.models.ResponseArticle;
import com.bizmaxsol.rrmob.models.ResponseBarcodeDetail;
import com.bizmaxsol.rrmob.models.ResponseBarcodeHistory;
import com.bizmaxsol.rrmob.models.ResponseBillLog;
import com.bizmaxsol.rrmob.models.ResponseMaster;
import com.bizmaxsol.rrmob.models.ResponseOC;
import com.bizmaxsol.rrmob.models.ResponsePurchase;
import com.bizmaxsol.rrmob.models.ResponseSaleDrillDown;
import com.bizmaxsol.rrmob.models.ResponseSaleSession;
import com.bizmaxsol.rrmob.models.ResponseSales;
import com.bizmaxsol.rrmob.models.ResponseSupplier;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReportServices {

    //Bill log

    @GET("posex/edited/{fdate}/{tdate}")
    Call<List<ResponseBillLog>> getEditedBillLog(@Path("fdate")String fdate,
                                                 @Path("tdate")String tdate);

    @GET("posex/deleted/{fdate}/{tdate}")
    Call<List<ResponseBillLog>> getDeletedBillLog(@Path("fdate")String fdate,
                                                  @Path("tdate")String tdate);

    @GET("barcode/history/{number}")
    Call<List<ResponseBarcodeHistory>> getBarcodeHistory (@Path("number") String number);

    @GET("barcode/detail/{number}")
    Call<ResponseBarcodeDetail> getBarcodeDetail (@Path("number") String number);

    @GET("article/sales/{number}")
    Call<List<ResponseArticle>> getArticleSales (@Path("number") String number);

    @GET("article/sizestock/{number}")
    Call<List<ResponseArticle>> getArticleStockSize (@Path("number") String number);

    @GET("masters/suppliers/")
    Call<List<ResponseSupplier>> getSupplier();

    @GET("purchase/{fdate}/{tdate}/{supplierId}")
    Call<List<ResponsePurchase>> getPurchase(@Path("fdate")String fdate,
                                             @Path("tdate")String tdate,
                                             @Path("supplierId")String supplierId);

    @GET("purchasedetail/{id}")
    Call<List<ResponsePurchase>> getPurchaseDetail(@Path("id")String id);

    @GET("purchasereturn/{fdate}/{tdate}/{supplierId}")
    Call<List<ResponsePurchase>> getPurchaseReturn(@Path("fdate")String fdate,
                                                   @Path("tdate")String tdate,
                                                   @Path("supplierId")String supplierId);

    @GET("purchasereturndetail/{id}")
    Call<List<ResponsePurchase>> getPurchaseReturnDetail(@Path("id")String id);

    @GET("SalesSessn/detail/{id}")
    Call<List<ResponseSales>> getSessnSaleList(@Path("id")String id);

    @GET("sales/dailysales/{sdate}")
    Call<List<ResponseSales>> getSaleList(@Path("sdate")String id);

    @GET("sales/ddsales1/{fdate}/{tdate}")
    Call<List<ResponseSaleDrillDown>> getSaleDrillDown1(@Path("fdate")String fdate,
                                                        @Path("tdate")String tdate);

    @GET("sales/ddsales2/{fdate}/{tdate}/{section}/{topn}")
    Call<List<ResponseSaleDrillDown>> getSaleDrillDown2(@Path("fdate")String fdate,
                                                        @Path("tdate")String tdate,
                                                        @Path("section")String section,
                                                        @Path("topn")String topn);

    @GET("sales/ddsales3/{fdate}/{tdate}/{item}/{brand}/{supplier}/{topn}")
    Call<List<ResponseSaleDrillDown>> getSaleDrillDown3(@Path("fdate")String fdate,
                                                        @Path("tdate")String tdate,
                                                        @Path("item")String itemName,
                                                        @Path("brand")int brand,
                                                        @Path("supplier")int supplier,
                                                        @Path("topn")String topn);
    @GET("stock/ddstock1/{fdate}/{tdate}")
    Call<List<ResponseSaleDrillDown>> getStockDrillDown1(@Path("fdate")String fdate,
                                                        @Path("tdate")String tdate);

    @GET("stock/ddstock2/{section}")
    Call<List<ResponseSaleDrillDown>> getStockDrillDown2(@Path("section")String section);

    @GET("stock/ddstock3/{item}/{brand}/{supplier}")
    Call<List<ResponseSaleDrillDown>> getStockDrillDown3(@Path("item")String itemName,
                                                        @Path("brand")int brand,
                                                        @Path("supplier")int supplier);
    @GET("SalesSessn/Sum/{date}")
    Call<List<ResponseSaleSession>> getSaleSession(@Path("date")String date);

    @GET("stock/stockoc/{fdate}/{tdate}/{format}/{dipfil}/{secfil}/{itemfil}/{brandfil}/{artfil}/{szfil}/{colfil}/{dipvis}/{secvis}/{itemvis}/{brandvis}/{artvis}/{szvis}/{colvis}")
    Call<List<ResponseOC>> getOC(@Path("fdate")String fdate,
                                 @Path("tdate")String tdate,
                                 @Path("format")int format,
                                 @Path("dipfil")String dipfil,
                                 @Path("secfil")String secfil,
                                 @Path("itemfil")String itemfil,
                                 @Path("brandfil")String brandfil,
                                 @Path("artfil")String artfil,
                                 @Path("szfil")String szfil,
                                 @Path("colfil")String colfil,
                                 @Path("dipvis")int dipvis,
                                 @Path("secvis")int secvis,
                                 @Path("itemvis")int itemvis,
                                 @Path("brandvis")int brandvis,
                                 @Path("artvis")int artvis,
                                 @Path("szvis")int szvis,
                                 @Path("colvis")int colvis);

    @GET("masters/item")
    Call<List<ResponseMaster>> getItemName();

    @GET("masters/brand")
    Call<List<ResponseMaster>> getBrandName();

    @GET("masters/division")
    Call<List<ResponseMaster>> getDivisionName();

    @GET("masters/section")
    Call<List<ResponseMaster>> getSectionName();

}

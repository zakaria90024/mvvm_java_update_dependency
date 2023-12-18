package com.example.mvvm_java_update_dependency.network;

import com.example.mvvm_java_update_dependency.model.Message;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Maybe;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MainNetworkService {

    @GET("StockGroup")
    Call<Maybe<Message>> getMessage();

    @FormUrlEncoded
    @POST("OTPVerification/Post")
    Call<String> getOtp(@Field("Mobile") String mobileNo,
                        @Field("Token") String tokenId);

    @FormUrlEncoded
    @POST("smsapiverify")
    Call<String> sendMobileNumber(@Field("mobile") String mobile);


    @GET("GetCustomer/{id}")
    Call<List<Message>> getDoctor(@Path("id") String id);

    @POST("SalesOrderInsert/Save")
    Call<String> submitOrderList(@Body JsonObject json);

    @GET
    Call<List<Message>> getSelectionGroups(@Url String url);



}

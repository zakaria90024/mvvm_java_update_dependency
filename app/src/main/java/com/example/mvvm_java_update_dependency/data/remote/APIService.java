package com.example.mvvm_java_update_dependency.data.remote;

import com.example.mvvm_java_update_dependency.model.Customer;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("GetCustomer/{id}")
    Call<List<Customer>> getCustomer(@Path("id") String id);

    @GET
    Call<List<String>> getCustomerStrin(@Path("id") String id);

    @GET
    Call<String> getString();

}
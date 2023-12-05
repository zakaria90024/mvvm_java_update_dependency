package com.example.mvvm_java_update_dependency.callback;

import com.example.mvvm_java_update_dependency.model.Customer;


public interface CustomerCallback {
    //get data model wise not list
    void onCustomerCache(Customer customerList);
    void onCustomerErrorCache(String error);
}

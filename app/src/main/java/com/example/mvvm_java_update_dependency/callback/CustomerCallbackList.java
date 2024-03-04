package com.example.mvvm_java_update_dependency.callback;

import com.example.mvvm_java_update_dependency.model.Customer;

import java.util.List;

public interface CustomerCallbackList {
    void onCustomer(List<Customer> customerList);//callback
    void onCustomerError(String error);
}

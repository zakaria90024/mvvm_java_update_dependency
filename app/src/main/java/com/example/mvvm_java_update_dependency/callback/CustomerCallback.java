package com.example.mvvm_java_update_dependency.callback;

import com.example.mvvm_java_update_dependency.model.Customer;

import java.util.List;

public interface CustomerCallback {
    void onCustomer(Customer customerList);
    void onCustomerError(String error);
}

package com.example.mvvm_java_update_dependency.repository;

import com.example.mvvm_java_update_dependency.callback.CustomerListModel;

import io.reactivex.Maybe;

public interface CustomerRepositoryOne {
    void getAllCustomer(Maybe<CustomerListModel> customerListModelMaybe);
}

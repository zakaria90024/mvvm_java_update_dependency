package com.example.mvvm_java_update_dependency.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.repository.CustomerRepository;

import java.util.List;

import io.reactivex.Flowable;

public class CustomerViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;
    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return customerRepository.getIsLoading();
    }


    //get all customer
    public Flowable<List<Customer>> getAllCustomer(){
        return customerRepository.getAllCustomer();
    }

    //Insert customer
    public void insert(Customer customer){
        customerRepository.insertCustomer(customer);
    }

    //Update customer
    public void update(Customer customer){
        customerRepository.updateCustomer(customer);
    }

    //Delete customer
    public void delete(Customer customer){
        customerRepository.deleteCustomer(customer);
    }
//
//    //Delete All Movie
//    public void deleteAllMoviesByGenre( int genre_id){
//        customerRepository.deleteAllMoviesByGenre(genre_id);
//    }

}

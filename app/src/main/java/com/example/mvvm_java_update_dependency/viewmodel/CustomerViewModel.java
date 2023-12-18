package com.example.mvvm_java_update_dependency.viewmodel;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_java_update_dependency.callback.CustomerCallbackList;
import com.example.mvvm_java_update_dependency.callback.CustomerImpl;
import com.example.mvvm_java_update_dependency.callback.CustomerListModel;
import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.repository.CustomerRepository;
import com.example.mvvm_java_update_dependency.view.CustomerActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CustomerViewModel extends AndroidViewModel {


    public MutableLiveData<List<Customer>> customerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> showErrorLiveData = new MutableLiveData<>();
    private CustomerRepository customerRepository;
    private CustomerListModel customerListModel;

    @Inject
    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
        customerListModel = new CustomerImpl();
    }

    public void getCustomerAll() {
        getIsLoading().postValue(true);

        customerListModel.getCustomerList(new CustomerCallbackList() {
            @Override
            public void onCustomer(List<Customer> customerList) {
                getIsLoading().postValue(false);
                customerMutableLiveData.postValue(customerList);

            }

            @Override
            public void onCustomerError(String error) {
                getIsLoading().postValue(false);
                showErrorLiveData.postValue(error);

                //customerMutableLiveData.postValue(null);
                //customerMutableLiveData = getAllCustomer();
                //getAllCustomer();

            }
        });
    }


    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading() {
        return customerRepository.getIsLoading();
    }


    //get all customer
    public Flowable<List<Customer>> getAllCustomerLocal() {
        return customerRepository.getAllCustomer();
    }



    //not allow room main thread
    public List<Customer> getAllCustomerLocalList() {
        return customerRepository.getAllCustomerList();
    }


    //Insert customer
    public void insert(Customer customer) {
        customerRepository.insertCustomer(customer);
    }

    //Update customer
    public void update(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    //Delete customer
    public void delete(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }

    public void deleteAllCustomer() {
        customerRepository.deleteAllCustomer();
    }

//
//    //Delete All Movie
//    public void deleteAllMoviesByGenre( int genre_id){
//        customerRepository.deleteAllMoviesByGenre(genre_id);
//    }

}

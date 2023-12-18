package com.example.mvvm_java_update_dependency.repository;

import com.example.mvvm_java_update_dependency.database.CustomerDatabase;
import com.example.mvvm_java_update_dependency.model.Message;
import com.example.mvvm_java_update_dependency.network.MainNetworkSource;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;

public class MainRepositoryOneImpl implements MainRepository {


    MainNetworkSource mainNetworkSource;
    CustomerDatabase customerDatabase;

    @Inject
    public MainRepositoryOneImpl(MainNetworkSource mainNetworkSource, CustomerDatabase customerDatabase){
        this.mainNetworkSource = mainNetworkSource;
        this.customerDatabase = customerDatabase;
    }

    static String DEFAULT_MESSAGE = "Hello World";

    @Override
    public Maybe<Message> getMessage() {
        return null;
    }

//    @Override
//    public Maybe<Message> getMessage() {
//        return mainNetworkSource.getMessage().subscribeOn(Scheduler.Worker);
//    }
}

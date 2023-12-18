package com.example.mvvm_java_update_dependency.network;

import com.example.mvvm_java_update_dependency.model.Message;

import java.io.IOException;

import javax.inject.Inject;
import io.reactivex.Maybe;


public class MainNetworkImpl implements MainNetworkSource {

    private MainNetworkService networkService;
    private MainNetworkSource mainNetworkSource;

    @Inject
    public MainNetworkImpl(MainNetworkService mainNetworkService, MainNetworkSource mainNetworkSource){
        this.networkService = mainNetworkService;
        this.mainNetworkSource = mainNetworkSource;
    }

    @Override
    public Maybe<Message> getMessage() throws Exception {

        if(networkService.getMessage().execute().isSuccessful()){
            return networkService.getMessage().execute().body();
        }else{
            throw new Exception("Fail to load");
        }

    }

    @Override
    public String failToGetMessage() throws IOException {
        if(!networkService.getMessage().execute().isSuccessful()){
            return "Fail to Load";
        }else {
            return "found exception";
        }
    }


}

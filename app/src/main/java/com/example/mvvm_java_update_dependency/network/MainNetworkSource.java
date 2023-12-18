package com.example.mvvm_java_update_dependency.network;

import com.example.mvvm_java_update_dependency.model.Message;

import java.io.IOException;

import io.reactivex.Maybe;

public interface MainNetworkSource {
    public Maybe<Message> getMessage() throws Exception;
    public String failToGetMessage() throws IOException;
}

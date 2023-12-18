package com.example.mvvm_java_update_dependency.repository;

import com.example.mvvm_java_update_dependency.model.Message;
import io.reactivex.Maybe;

public interface MainRepository {
    //void getMessage(Maybe<Message> customerListModelMaybe);
    Maybe<Message> getMessage();
}

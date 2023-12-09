package com.example.mvvm_java_update_dependency.di.module;

import android.util.Log;

import javax.inject.Inject;

public class UserRepository {

    @Inject
    public UserRepository() {

    }

    public void saveUser(String email, String pass){
        Log.d("TAG", "User Saved");
    }
}

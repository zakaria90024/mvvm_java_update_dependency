package com.example.mvvm_java_update_dependency.database.preference;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class AppPreferenceImpl implements AppPreference{



    SharedPreferences sharedPreferences ;
    ApplicationContext  context;

//    SharedPreferences sharedPreferences =context.getSharedPreferences("MySharedPref",MODE_PRIVATE);
//
//    // Creating an Editor object to edit(write to the file)
//    SharedPreferences.Editor myEdit = sharedPreferences.edit();


    @Inject
    public AppPreferenceImpl(SharedPreferences sharedPreferences,  ApplicationContext  context){
        this.sharedPreferences = sharedPreferences;
        this.context = context;

    }


    private void saveString(String key, String value ){

    }



}

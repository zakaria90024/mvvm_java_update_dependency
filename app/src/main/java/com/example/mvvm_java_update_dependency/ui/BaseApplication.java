package com.example.mvvm_java_update_dependency.ui;

import android.app.Application;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}

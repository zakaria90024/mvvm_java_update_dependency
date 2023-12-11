package com.example.mvvm_java_update_dependency.ui;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.orhanobut.logger.Logger;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel implements IViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

    @Override
    public void onAny(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Logger.d("test"+event);

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();

    }
}

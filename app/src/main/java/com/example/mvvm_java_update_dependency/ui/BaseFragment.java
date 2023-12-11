package com.example.mvvm_java_update_dependency.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<ViewModel extends BaseViewModel> extends Fragment {

    private BaseFragmentCommunicator communicator;

    //protected  ViewModel viewModel;
    //protected BINDING binding;
    @NonNull
    protected abstract ViewModel createViewModel();

    @NonNull
    protected abstract int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragmentCommunicator) {
            communicator = (BaseFragmentCommunicator) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getLifecycle().addObserver(createViewModel());
        return inflater.inflate(getLayoutId(), container, false);
    }



    public void startActivity(Class cls, Bundle bundle) {
        communicator.startActivity(cls, bundle);

    }


    public void setupActionBar(Toolbar toolbar, Boolean enableBackButtn) {
        communicator.setupActionBar(toolbar, enableBackButtn);

    }

    private Class<ViewModel> getViewModelClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<ViewModel> viewModelClass = (Class<ViewModel>) type.getActualTypeArguments()[0]; // index of 0 means first argument of Base class param
        return viewModelClass;
    }


}
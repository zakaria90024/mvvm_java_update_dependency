package com.example.mvvm_java_update_dependency.view;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.example.mvvm_java_update_dependency.R;
import com.example.mvvm_java_update_dependency.ui.BaseActivity;
import com.example.mvvm_java_update_dependency.ui.BaseViewModel;
import com.example.mvvm_java_update_dependency.viewmodel.CustomerViewModelImpl;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class CustomerActivityImp extends BaseActivity {

    @NonNull
    @Override
    protected BaseViewModel createViewModel() {
        viewModel = new CustomerViewModelImpl();
        return null;
    }

    @NonNull
    @Override
    protected ViewBinding createViewBinding(LayoutInflater layoutInflater) {
        binding = createViewBinding(layoutInflater);
        return binding;
    }

}
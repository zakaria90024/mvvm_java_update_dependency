package com.example.mvvm_java_update_dependency.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_java_update_dependency.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CustomerFragmentEntryPoint extends Fragment {

    public CustomerFragmentEntryPoint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer, container, false);
    }
}
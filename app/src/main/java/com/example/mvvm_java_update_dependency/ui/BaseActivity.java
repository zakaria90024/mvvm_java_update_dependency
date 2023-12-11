package com.example.mvvm_java_update_dependency.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.viewbinding.ViewBinding;

import javax.inject.Inject;

//if extends this must need Binding, VievModel refference
public  abstract  class BaseActivity<BINDING extends ViewBinding, ViewModel extends BaseViewModel> extends AppCompatActivity implements BaseFragmentCommunicator {


    protected  ViewModel viewModel;
    protected BINDING binding;

    @NonNull
    protected abstract ViewModel createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);



    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = createViewBinding(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        //viewModel = createViewModel();
        getLifecycle().addObserver(createViewModel());
    }

    @Override
    public void startActivity(Class clas, Bundle bundle) {

    }

    @Override
    public void setupActionBar(Toolbar toolbar, Boolean enableBackButtn) {

    }



}

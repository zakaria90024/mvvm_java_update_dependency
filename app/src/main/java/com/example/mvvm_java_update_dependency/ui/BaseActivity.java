package com.example.mvvm_java_update_dependency.ui;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import javax.inject.Inject;

public  abstract  class BaseActivity<ViewModel extends BaseViewModel> extends AppCompatActivity implements BaseFragmentCommunicator {




    protected  ViewModel viewModel;

    @NonNull
    protected abstract ViewModel createViewModel();

//    @NonNull
//    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);
//



    int  getLayOutId;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayOutId);
        getLifecycle().addObserver(viewModel);
    }

    @Override
    public void startActivity(Class clas, Bundle bundle) {

    }

    @Override
    public void setupActionBar(Toolbar toolbar, Boolean enableBackButtn) {

    }



}

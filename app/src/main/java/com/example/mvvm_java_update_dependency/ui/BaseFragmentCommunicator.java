package com.example.mvvm_java_update_dependency.ui;

import android.os.Bundle;
import android.widget.Toolbar;

public interface BaseFragmentCommunicator {
    void startActivity(Class clas, Bundle bundle);
    void setupActionBar(Toolbar toolbar, Boolean enableBackButtn);
}

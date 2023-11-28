package com.example.mvvm_java_update_dependency.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvvm_java_update_dependency.R;
import com.example.mvvm_java_update_dependency.callback.CustomerCallbackList;
import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.viewmodel.CustomerViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CustomerActivity extends AppCompatActivity implements CustomerCallbackList, CustomerAdapter.OnCustomerClickListener {

    private CustomerViewModel customarViewModel;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private CustomerAdapter customerAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(false);
        customarViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        //it's means call from api
        customarViewModel.getCustomerAll();


        //it's work when get response from api
        customarViewModel.customerMutableLiveData.observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                Toast.makeText(CustomerActivity.this, "Total - " + customers.size(), Toast.LENGTH_SHORT).show();
                setDataToRecyclerView(customers);
            }
        });

        //Check Loading Progresbar
        customarViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d(TAG, "onChanged: " + aBoolean);
                if (aBoolean != null) {
                    if (aBoolean) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        //Toast.makeText(CustomerActivity.this, "Visible", Toast.LENGTH_SHORT).show();
                    } else {
                        mProgressBar.setVisibility(View.GONE);
                        //Toast.makeText(CustomerActivity.this, "Invisible", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        //if error from api
        customarViewModel.showErrorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                //when get error here, restore data from local database
                //get from local db data
                Toast.makeText(CustomerActivity.this, "" + s, Toast.LENGTH_SHORT).show();

                Disposable disposable = customarViewModel.getAllCustomer().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Customer>>() {
                            @Override
                            public void accept(List<Customer> customers) throws Exception {
                                Log.d(TAG, "accept: Called");
                                setDataToRecyclerView(customers);
                                Toast.makeText(CustomerActivity.this, "Restored From Local DB", Toast.LENGTH_SHORT).show();
                            }
                        });
                compositeDisposable.add(disposable);

            }
        });


//        get bundle data from other activity
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            mGenre = extras.getString("genre");
//            genre_id = extras.getInt("uid");
//            Log.d(TAG, "onStart: "+genre_id+""+mGenre);
//            mTitleView.setText(mGenre+" Movies");
//        }

    }

    public void onInsertCustomarDB(Customer customers) {
        customarViewModel.insert(customers);
    }

    private void setDataToRecyclerView(List<Customer> genres) {
        customerAdapter = new CustomerAdapter(genres);
        customerAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(customerAdapter);
    }

    @Override
    public void onCustomer(List<Customer> customerList) {
        List<Customer> doctorModelList = customerList;
        for (Customer i : doctorModelList) {
            new Customer(0, i.getStrCustomerName(), i.getStrPhone(), i.getStraddress());
            onInsertCustomarDB(i);
        }
    }

    @Override
    public void onCustomerError(String error) {

    }

    @Override
    public void onCustomerClick(Customer customer) {
//
//     Intent intent = new Intent(CustomerActivity.this,MoviesActivity.class);
//     intent.putExtra("genre",customer.getGenre());
//     intent.putExtra("uid",customer.getUid());
//     startActivity(intent);
       Toast.makeText(this, "Clicked -" + customer.getStrCustomerName(), Toast.LENGTH_SHORT).show();

    }
}
package com.example.mvvm_java_update_dependency.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvvm_java_update_dependency.R;
import com.example.mvvm_java_update_dependency.callback.CustomerCallback;
import com.example.mvvm_java_update_dependency.callback.CustomerCallbackList;
import com.example.mvvm_java_update_dependency.data.remote.APIService;
import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.network.ApiClient;
import com.example.mvvm_java_update_dependency.viewmodel.CustomerViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity implements CustomerCallbackList, CustomerAdapter.OnCustomerClickListener {

    private CustomerViewModel customarViewModel;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private CustomerAdapter customerAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private CustomerCallback customerCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(false);
        //getDataFromApiCall();


        customarViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        //it's means all call from api
        customarViewModel.getCustomerAll();
        customarViewModel.customerMutableLiveData.observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {

                List<Customer> doctorModelList = customers;
                Toast.makeText(CustomerActivity.this, "cus" + customers.size(), Toast.LENGTH_SHORT).show();
                setDataToRecyclerView(customers);

                for (Customer i : doctorModelList) {
                    new Customer(1, i.getStrCustomerName(), i.getStrPhone(), i.getStraddress());

                    onInsertCustomarDB(i);
                }
            }
        });

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            mGenre = extras.getString("genre");
//            genre_id = extras.getInt("uid");
//            Log.d(TAG, "onStart: "+genre_id+""+mGenre);
//            mTitleView.setText(mGenre+" Movies");
//
//        }
        //Disposable for avoid memory leak
//        Disposable disposable = customarViewModel.getAllCustomer().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Customer>>() {
//                    @Override
//                    public void accept(List<Customer> genres) throws Exception {
//                        Log.d(TAG, "accept: Called");
//
//                        setDataToRecyclerView(genres);
//                    }
//                });
//
//
//        //Add Disposable
//        compositeDisposable.add(disposable);
//
//
//
//        //Check Loading State
//        customarViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                Log.d(TAG, "onChanged: " + aBoolean);
//                if (aBoolean != null) {
//                    if (aBoolean) {
//                        mProgressBar.setVisibility(View.VISIBLE);
//                        Toast.makeText(CustomerActivity.this, "Visible", Toast.LENGTH_SHORT).show();
//                    } else {
//                        mProgressBar.setVisibility(View.GONE);
//                        Toast.makeText(CustomerActivity.this, "INVisible", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });

    }
//
//    private void getDataFromApiCall() {
//
//        APIService service = ApiClient.getRetrofit().create(APIService.class);
//
//        Call<List<Customer>> call = service.getCustomer("259");
//
//            call.enqueue(new Callback<List<Customer>>() {
//                @Override
//                public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
//
//                    //customerCallback.onCustomer(response.body());
//
//                    if (response.isSuccessful()) {
//                        if (response.body() != null) {
//
//                            List<Customer> doctorModelList = response.body();
//
//                            Toast.makeText(CustomerActivity.this, "onRespon "+doctorModelList.size(), Toast.LENGTH_SHORT).show();
//                            if (doctorModelList.size() > 0) {
//                                Customer Customer;
//                                for(Customer i: doctorModelList){
//                                    new Customer(1, i.getStrCustomerName(), i.getStrPhone(),i.getStraddress());
//
//                                    //onCustomer(i);
//                                }
//
//                            }
//
//
//                        }
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Customer>> call, Throwable t) {
//                    customerCallback.onCustomerError(t.getMessage());
//                    Toast.makeText(CustomerActivity.this, "fail ", Toast.LENGTH_SHORT).show();
//                    //loadingdialog.dismiss();
//                    //swipeRefreshLayout.setRefreshing(true);
//                    //doctor_sync.setClickable(true);
//                    //doctor_sync.setVisibility(View.VISIBLE);
//                }
//            });
//    }

    private void setDataToRecyclerView(List<Customer> genres) {
        customerAdapter = new CustomerAdapter(genres);
        customerAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(customerAdapter);
    }

//    @Override
//    public void onCustomer(List<Customer> customerList) {
//        customarViewModel.insert((Customer) customerList);
//    }
//
//    @Override
//    public void onCustomerError(String error) {
//
//    }

//    @Override
//    public void onCustomer(Customer customerList) {
//        customarViewModel.insert(customerList);
//
//    }

    public void onInsertCustomarDB(Customer customers){
        customarViewModel.insert(customers);
    }

    @Override
    public void onCustomer(List<Customer> customerList) {
        customarViewModel.insert((Customer) customerList);
    }

    @Override
    public void onCustomerError(String error) {

    }

    @Override
    public void onCustomerClick(Customer customer) {
//
//        Intent intent = new Intent(CustomerActivity.this,MoviesActivity.class);
//        intent.putExtra("genre",customer.getGenre());
//        intent.putExtra("uid",customer.getUid());
//        startActivity(intent);

        Toast.makeText(this, "Clicked -" + customer.getStrCustomerName(), Toast.LENGTH_SHORT).show();

    }
}
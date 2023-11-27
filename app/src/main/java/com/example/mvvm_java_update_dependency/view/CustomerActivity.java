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
import com.example.mvvm_java_update_dependency.callback.CustomerCallback;
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

public class CustomerActivity extends AppCompatActivity implements CustomerCallback {

    private CustomerViewModel customarViewModel;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        getDataFromApiCall();
        customarViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            mGenre = extras.getString("genre");
//            genre_id = extras.getInt("uid");
//            Log.d(TAG, "onStart: "+genre_id+""+mGenre);
//            mTitleView.setText(mGenre+" Movies");
//
//        }
        //Disposable for avoid memory leak
        Disposable disposable = customarViewModel.getAllCustomer().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Customer>>() {
                    @Override
                    public void accept(List<Customer> genres) throws Exception {
                        Log.d(TAG, "accept: Called");
                        setDataToRecyclerView(genres);
                    }
                });


        //Add Disposable
        compositeDisposable.add(disposable);

        //Check Loading State
        customarViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d(TAG, "onChanged: " + aBoolean);
                if (aBoolean != null) {
                    if (aBoolean) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        mRecyclerView.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    private void getDataFromApiCall() {

        APIService service = ApiClient.getRetrofit().create(APIService.class);

        Call<List<Customer>> call = service.getCustomer("259");

            call.enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {

                            List<Customer> doctorModelList = response.body();

                            Toast.makeText(CustomerActivity.this, "onRespon "+doctorModelList.size(), Toast.LENGTH_SHORT).show();
                            if (doctorModelList.size() > 0) {
                                Customer Customer;
                                for(Customer i: doctorModelList){
                                    new Customer(1, i.getStrCustomerName(), i.getStrPhone(),i.getStraddress());

                                    //onCustomer(Customer);
                                }


//                                for (int i = 0; i < doctorModelList.size(); i++) {
//                                    if (i == 0) {
//                                        dbHelper.addDoctor(new DoctorModel(String.valueOf(userId), "New Doctor"));
//                                    }
//                                    DoctorModel doctorModel = new DoctorModel();
//
//                                    doctorModel = doctorModelList.get(i);
//                                    doctorModel.setMpo(userId);
//
//                                    dbHelper.addDoctor(doctorModel);
//                                }
                            }


                        }

                    }
                }

                @Override
                public void onFailure(Call<List<Customer>> call, Throwable t) {
                    Toast.makeText(CustomerActivity.this, "fail ", Toast.LENGTH_SHORT).show();
                    //loadingdialog.dismiss();
                    //swipeRefreshLayout.setRefreshing(true);
                    //doctor_sync.setClickable(true);
                    //doctor_sync.setVisibility(View.VISIBLE);
                }
            });
    }

    private void setDataToRecyclerView(List<Customer> genres) {
        //genreAdapter = new GenreAdapter(genres);
        //genreAdapter.setItemClickListener(this);
        //mRecyclerView.setAdapter(genreAdapter);
    }

    @Override
    public void onCustomer(Customer customerList) {
        customarViewModel.insert(customerList);

    }

    @Override
    public void onCustomerError(String error) {

    }
}
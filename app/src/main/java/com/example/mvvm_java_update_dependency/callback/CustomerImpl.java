package com.example.mvvm_java_update_dependency.callback;

import com.example.mvvm_java_update_dependency.data.remote.APIService;
import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerImpl implements CustomerListModel {
    APIService service = ApiClient.getRetrofit().create(APIService.class);

    @Override
    public void getCustomerList(CustomerCallbackList customerCallback) {


        Call<List<Customer>> call = service.getCustomer("259");
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        customerCallback.onCustomer(response.body());

                    } else {
                        customerCallback.onCustomerError("Content Not Found! ");
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                customerCallback.onCustomerError("Something Went Wrong! " + t.getMessage());
            }
        });


    }
}

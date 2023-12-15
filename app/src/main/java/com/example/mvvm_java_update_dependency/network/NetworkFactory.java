package com.example.mvvm_java_update_dependency.network;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.concurrent.Executor;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkFactory {
//
//    private static final String BASE_URL = "";
//    private static final long TIME_OUT = 60L;
//
//    public static <Service> Service createService(Context appContext, Class<Service> serviceClass) {
//        return getRetrofit(
//                appContext,
//                BASE_URL,
//                getOkHttpClient(
//                        getAuthInterceptor(
//                                appContext
//                        ),
//                        getLogInterceptors()
//                )
//        ).create(serviceClass);
//    }
//
//    public static Retrofit getRetrofit(Context appContext) {
//        return getRetrofit(
//                appContext,
//                getOkHttpClient(
//                        getAuthInterceptor(
//                                appContext
//                        ),
//                        getLogInterceptors()
//                )
//        );
//    }
//
//
//
//
//    public static Retrofit getRetrofit(Context context, String baseUrl, OkHttpClient okHttpClient) {
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(
//                        RxErrorHandlingCallAdapterFactory.create(
//                                context
//                        )
//                )
//                .client(okHttpClient)
//                .callbackExecutor(new Executor() {
//                    @Override
//                    public void execute(Runnable command) {
//                        Logger.d("returning");
//                    }
//                })
//                .build();
//    }

//    public static Interceptor getLogInterceptors() {
//        //return new HttpLoggingInterceptor().setLevel(com.shafayat.helloworld.BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//    }

}

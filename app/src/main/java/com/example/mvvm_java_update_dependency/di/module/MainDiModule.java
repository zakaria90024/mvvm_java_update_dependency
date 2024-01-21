package com.example.mvvm_java_update_dependency.di.module;

import android.content.Context;

import androidx.room.Room;

import com.example.mvvm_java_update_dependency.database.CustomerDatabase;
import com.example.mvvm_java_update_dependency.network.NetworkFactory;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class MainDiModule {

//its for api interface
//    @Provides
//    @Singleton
//    public MessageNetworkService provideMessageNetworkService(@ApplicationContext Context context) {
//        return NetworkFactory.createService(context, MessageNetworkService.class);
//    }


    //its for local db
    @Provides
    @Singleton
    public CustomerDatabase provideLocalDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                        context,
                        CustomerDatabase.class, "tbl_movie"
                )
                .build();
    }
}



package com.example.mvvm_java_update_dependency.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvm_java_update_dependency.database.dao.CustomerDao;
import com.example.mvvm_java_update_dependency.database.dao.MessageDao;
import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.model.Message;

@Database(entities = {Customer.class, Message.class}, version = 3,exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {

    private static CustomerDatabase instance;
    public abstract CustomerDao customerDao();
    public abstract MessageDao messageDao();

    public static synchronized CustomerDatabase getInstance(Context context){
        if(instance==null){
            //If instance is null that's mean database is not created and create new database
            instance = Room.databaseBuilder(context.getApplicationContext(),CustomerDatabase.class,"movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static Callback roomCallBack = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}

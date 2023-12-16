package com.example.mvvm_java_update_dependency.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm_java_update_dependency.model.Customer;
import com.example.mvvm_java_update_dependency.model.Message;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface MessageDao {

    //Insert new Genre
    @Insert
    void insert(List<Message> message);

    //Update existing Genre
    @Update
    void update(List<Message> message);

    //Delete Specific Genre and also delete movies under this genre
    @Delete
    void delete(List<Message> message);

    //Delete all Genre from table
    @Query("DELETE FROM table_message")
    void deleteAllMessage();

    //Get all Genre from table
    @Query("SELECT * FROM table_message")
    Flowable<List<Message>> getAllMessage();


    @Query("SELECT * FROM table_message")
    List<Message> getAllMessage1();
}

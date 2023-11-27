package com.example.mvvm_java_update_dependency.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm_java_update_dependency.model.Customer;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface CustomerDao {

    //Insert new Genre
    @Insert
    void insert(Customer customer);

    //Update existing Genre
    @Update
    void update(Customer customer);

    //Delete Specific Genre and also delete movies under this genre
    @Delete
    void delete(Customer customer);

    //Delete all Genre from table
    @Query("DELETE FROM customer_table")
    void deleteAllCustomer();

    //Get all Genre from table
    @Query("SELECT * FROM customer_table")
    Flowable<List<Customer>> getAllCustomer();
}

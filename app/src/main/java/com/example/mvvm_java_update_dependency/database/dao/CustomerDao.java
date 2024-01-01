package com.example.mvvm_java_update_dependency.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm_java_update_dependency.model.Customer;

import java.util.ArrayList;
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
    @Query("DELETE FROM table_customer")
    void deleteAllCustomer();

    //Get all Genre from table
    @Query("SELECT * FROM table_customer")
    Flowable<List<Customer>> getAllCustomer();


    @Query("SELECT * FROM table_customer")
    List<Customer> getAllCustomerList();


    //Get Customer With Id
    @Query("SELECT * FROM table_customer WHERE id LIKE :id")
    Flowable<List<Customer>> getCustomerWithId(int id);


    //Delete customer by id
    @Query("DELETE FROM table_customer WHERE id==:id")
    void deleteAllById(int id);
}

package com.example.my_foodapps.model;

import androidx.annotation.NonNull;
import androidx.room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addcustomer(Customer customer);

    @Delete
    void delete(Customer customer);
    @Update
    void Update(Customer customer);
    @Query("SELECT * FROM customers")
    LiveData<List<Customer>> getAllcustomer();

    @Query("select * from customers where cust_id ==:cust_id")
    LiveData<List<Customer>> getcustomer(int cust_id);


}

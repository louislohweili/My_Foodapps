package com.example.my_foodapps.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void addorder(Order order);

    @Update
    void updateorder(Order order);


    @Query("SELECT * FROM  `order` ")
    LiveData<List<Order>> getAllOrder();

    @Delete
    void del(Order order);
}


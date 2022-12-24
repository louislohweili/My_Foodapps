package com.example.my_foodapps.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insert (Cart cart);

    @Delete
    void Delete (Cart cart);

    @Update
    void update (Cart cart);

@Query("SELECT * FROM  CARTS ")
LiveData<List<Cart>> getAllcart();


    @Query("select * from CARTS where cart_id ==:cartID")
    LiveData<List<Cart>> getcart (int cartID);
}

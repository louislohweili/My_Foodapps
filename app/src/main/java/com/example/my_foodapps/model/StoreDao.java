package com.example.my_foodapps.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface StoreDao {
    @Insert
    void addstore(Store store);

    @Update (entity = Store.class)
    void updatestore (Store store);

    @Query("SELECT * FROM STORE")
    LiveData<List<Store>> getAllstore();

    @Query("SELECT * FROM STORE WHERE storeemail like :Email AND password like :Password")
    Store login (String Email, String Password ) ;
    @Delete
    void delete(Store store);


    @Query("select * from STORE where store_id ==:store_id")
    LiveData<List<Store>> getstore(int store_id);

    @Query("select * from STORE where storeemail ==:Email AND  password == :Password ")
    LiveData<List<Store>> getlogin(String Email , String Password);

}

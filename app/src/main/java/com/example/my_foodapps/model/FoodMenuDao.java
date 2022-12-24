package com.example.my_foodapps.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodMenuDao {
@Insert
    void insert (FoodMenuDetails foodMenuDetails);
@Update
    void update(FoodMenuDetails foodMenuDetails);
@Delete
    void delete(FoodMenuDetails foodMenuDetails);

@Query("SELECT * FROM FoodMenuDetails")
LiveData<List<FoodMenuDetails>> getAllFoodMenuDetails();


    @Query("select * from foodMenuDetails where storeid ==:storeid")
    LiveData<List<FoodMenuDetails>> getfooditem(int storeid);
@Query("DELETE FROM foodMenuDetails where storeid ==:storeid ")
void deletebystore(int storeid);
}

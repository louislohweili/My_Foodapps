package com.example.my_foodapps.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Customer;

import com.example.my_foodapps.model.FoodMenuDetails;
import com.example.my_foodapps.model.Store;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FoodMenurepo {
    private AppDatabase customerAppDatabase;
    private final Executor executor = Executors.newSingleThreadExecutor();


    public FoodMenurepo(Context context) {
        customerAppDatabase = AppDatabase.getInstance(context);
    }
    public void insertFoodmenu(FoodMenuDetails foodMenuDetails) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                customerAppDatabase.foodMenuDao().insert(foodMenuDetails);
            }
        });
    }


    public void  updatefoodmenu (FoodMenuDetails foodMenuDetails){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                customerAppDatabase.foodMenuDao().update(foodMenuDetails);

            }
        });




    }
    public void delete (FoodMenuDetails foodMenuDetails) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                customerAppDatabase.foodMenuDao().delete(foodMenuDetails);
            }
        });
    }
    public LiveData<List<FoodMenuDetails>> getAllfoodmenu()  {

        return customerAppDatabase.foodMenuDao().getAllFoodMenuDetails();


    }

    public LiveData<List<FoodMenuDetails>> getfooditem(int storeid) {
        return customerAppDatabase.foodMenuDao().getfooditem(storeid);
    }
public void deletebystore(int storeid){
        customerAppDatabase.foodMenuDao().deletebystore(storeid);
}



}

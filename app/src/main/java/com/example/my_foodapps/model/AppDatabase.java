package com.example.my_foodapps.model;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cart.class,Customer.class,Store.class,FoodMenuDetails.class,Order.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    private static final Object LOCK = new Object();
    public static final String dbname = "food_apps";
    public static AppDatabase customerAppDatabase;
    // Singleton Pattern
   // private static AppDatabase instance;
    public abstract CartDao cartDao();
    public abstract CustomerDao customerDao();
    public abstract StoreDao  storeDao();
    public abstract FoodMenuDao foodMenuDao();
    public abstract OrderDao orderDao();


    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,dbname)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}

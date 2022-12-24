package com.example.my_foodapps.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Customer;

import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.model.StoreDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Storerepo {
    private StoreDao storeDao;
    private AppDatabase customerAppDatabase;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private LiveData<List<Store>> stores;
    public Storerepo(Context context) {
        AppDatabase  storedatabase = AppDatabase.getInstance(context);
        storeDao = storedatabase.storeDao();

        stores = storeDao.getAllstore();
    }

    public void  add (Store store){
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                storeDao.addstore(store);
            }
        });


    }

    public void updatestore(Store store){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                    storeDao.updatestore(store);

                // Do after background execution is done - post execution
            }
        });
    }





    public void delete (Store store) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                storeDao.delete(store);
            }
        });
    }

    public LiveData<List<Store>> getAllStore()
    {
        return stores;
    }

    public LiveData<List<Store>>getLogin(String Email, String password){
        return customerAppDatabase.storeDao().getlogin(Email,password);
    }
    public LiveData<List<Store>> getstore(int store_id) {
        return customerAppDatabase.storeDao().getstore(store_id);
    }

    public void update(Store store)
    {
        new UpdateStoreAsyncTask(storeDao).execute(store);
    }
    private static class UpdateStoreAsyncTask extends AsyncTask<Store, Void, Void>
    {
        private StoreDao storeDao;

        private UpdateStoreAsyncTask(StoreDao storeDao)
        {
            this.storeDao = storeDao;
        }
        @Override
        protected Void doInBackground(Store... store) {
            storeDao.updatestore(store[0]);
            return null;
        }
    }

}

package com.example.my_foodapps.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Cart;
import com.example.my_foodapps.model.CartDao;
import com.example.my_foodapps.model.Store;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Cartrepo {
    Cart cart;
    private CartDao cartDao;
    Executor executor = Executors.newSingleThreadExecutor();
    private LiveData<List<Cart>> carts;

    public Cartrepo(Application application){
        AppDatabase cartDatabase = AppDatabase.getInstance(application);
        cartDao = cartDatabase.cartDao();
        carts = cartDao.getAllcart();

    }

    public void  addCart (Cart cart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.insert(cart);

            }
        });
    }
    public void DelCart (Cart cart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.Delete(cart);
            }
        });
    }
    public void updateCart(Cart cart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.update(cart);
            }
        });
    }



    public LiveData<List<Cart>> getAllcart()
    {
        return carts;
    }

    public LiveData<List<Cart>>getCart(int cartID){
        return getCart(cartID);
    }
}



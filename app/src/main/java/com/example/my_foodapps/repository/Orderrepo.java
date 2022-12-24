package com.example.my_foodapps.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Customer;
import com.example.my_foodapps.model.Order;
import com.example.my_foodapps.model.OrderDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Orderrepo {
    private OrderDao orderDao;
    Executor executor = Executors.newSingleThreadExecutor();
    public LiveData<List<Order>> orders;
    public Orderrepo(Application application){
        AppDatabase orderDatabese =AppDatabase.getInstance(application);
        orderDao = orderDatabese.orderDao();
        orders = orderDao.getAllOrder();
    }

    public void addorder(Order order) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderDao.addorder(order);
            }
        });

    }
    public void delorder(Order order) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderDao.del(order);
            }
        });

    }
    public void updateorder(Order order) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderDao.updateorder(order);
            }
        });

    }
    public LiveData<List<Order>> getAllOrder()  {

        return orders;


    }
}

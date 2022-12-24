package com.example.my_foodapps.repository;
import android.content.Context;
import android.os.AsyncTask;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;

import com.example.my_foodapps.model.AppDatabase;

import com.example.my_foodapps.model.Customer;
import com.example.my_foodapps.model.CustomerDao;
import com.example.my_foodapps.model.FoodMenuDetails;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Customerrepo {
   // private AppDatabase customerAppDatabase;

    private CustomerDao customerDao;
    Executor executor = Executors.newSingleThreadExecutor();
    private LiveData<List<Customer>> customers;
    public Customerrepo(Application application) {

        AppDatabase customereDatabase = AppDatabase.getInstance(application);

        customerDao = customereDatabase.customerDao();

        customers = customerDao.getAllcustomer();

    }

    public void insert (Customer customer) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.addcustomer(customer);
            }
        });
    }

    public void  update (Customer customer){
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.Update(customer);
            }
        });


    }
    public void delete (Customer customer) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.delete(customer);
            }
        });
    }

    public LiveData<List<Customer>> getAllCustomer()  {

        return customers;


    }


    public LiveData<List<Customer>> getCustomer(int cust_id) {
        return customerDao.getcustomer(cust_id);
    }


    /**
    public void insert(Customer customer)
    {
        new InsertCustomerAsyncTask(customerDao).execute(customer);
    }
    public LiveData<List<Customer>> getAllCustomer()
    {
        return customers;
    }


    public void update(Customer customer)
    {
        new UpdateCustomerAsyncTask(customerDao).execute(customer);
    }


    private static class InsertCustomerAsyncTask extends AsyncTask<Customer, Void, Void>
    {
        private CustomerDao customerDao;

        private InsertCustomerAsyncTask(CustomerDao customerDao )
        {
            this.customerDao = customerDao;
        }
        @Override
        protected Void doInBackground(Customer...customer) {
            customerDao.addcustomer(customer[0]);
            return null;
        }
    }
    public static class UpdateCustomerAsyncTask extends AsyncTask<Customer,Void,Void>
    {

        CustomerDao customerDao;

        public UpdateCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customer) {

            customerDao.Update(customer[0]);
            return null;
        }
    }
    public void delete(Customer customer)
    {
        new DeleteNoteAsyncTask(customerDao).execute(customer);
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Customer, Void, Void>
    {
        private CustomerDao customerDao;

        private DeleteNoteAsyncTask(CustomerDao customerDao)
        {
            this.customerDao = customerDao;
        }
        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.delete(customers[0]);
            return null;
        }
    }
**/
}

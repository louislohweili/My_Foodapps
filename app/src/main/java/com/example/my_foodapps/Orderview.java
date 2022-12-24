package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.my_foodapps.Adapter.CutomerDataAdapter;
import com.example.my_foodapps.Adapter.OrderAdapter;
import com.example.my_foodapps.model.Customer;
import com.example.my_foodapps.model.Order;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.viewmodel.Customerviewmodel;

import java.util.List;

public class Orderview extends AppCompatActivity {
    private OrderAdapter orderAdapter;
    private Customerviewmodel viewmodel;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderview);
        RecyclerView recyclerView = findViewById(R.id.rv3);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OrderAdapter adapter = new OrderAdapter();
        recyclerView.setAdapter(adapter);
      viewmodel   = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(Customerviewmodel.class);
        viewmodel.getallOrder().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                adapter.setOrders(orders);

            }
        });
        adapter.setOnitemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Order order) {
                Intent intent = new Intent(Orderview.this,UpdateOrderprog.class);
                intent.putExtra("Order_id",order.getOrder_id());
                intent.putExtra("Fullname",order.getOrderName());
                intent.putExtra("Address",order.getOrderAddress());
                intent.putExtra("Phoneno",order.getOrderphone());
                intent.putExtra("Totalacc",order.getOrdertotal());
                intent.putExtra("payment",order.getPayment());
                intent.putExtra("status",order.getStatus());


                startActivityForResult(intent,4);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 4 && resultCode == RESULT_OK) {
            String orderName = data.getStringExtra("updateFullname");
            String orderAddress = data.getStringExtra("updateAddress");
            String orderphone = data.getStringExtra("updatephoneno");
            String ordertotal = data.getStringExtra("updateTotal");
            String payment = data.getStringExtra("updatepayment");
            String status = data.getStringExtra("updatestatus");
            int Order_id = data.getIntExtra("Order_id", 0);
            ;

            Order order = new Order(orderName, orderAddress, orderphone, ordertotal, payment,status);
            order.setOrder_id(Order_id);
            viewmodel.updateorder(order);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:

                startActivity(new Intent(Orderview.this, Orderview.class));
                finish();

                return true;
        }
        switch (item.getItemId()) {
            case R.id.logout:

                startActivity(new Intent(Orderview.this, MainActivity.class));
                finish();

                return true;
        }
        return false;
    }
}
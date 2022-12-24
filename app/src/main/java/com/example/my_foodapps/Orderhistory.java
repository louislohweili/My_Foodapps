package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.my_foodapps.Adapter.OrderAdapter;
import com.example.my_foodapps.model.Order;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Orderhistory extends AppCompatActivity {
    private OrderAdapter orderAdapter;
    private Customerviewmodel viewmodel;
    private RecyclerView recyclerView;
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);

   RecyclerView recyclerView = findViewById(R.id.rv5);



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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewmodel.delOrder(adapter.getOrder(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplication(), "Store data have delete", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:

                startActivity(new Intent(Orderhistory.this, Orderhistory.class));
                finish();

                return true;
        }
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Orderhistory.this, MainActivity.class));
                finish();

                return true;
        }
        return false;
    }

    }
package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.my_foodapps.Adapter.FoodMenuDetailAdapter;
import com.example.my_foodapps.model.Cart;
import com.example.my_foodapps.model.FoodMenuDetails;

import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerView rv;
    private FloatingActionButton fab;

    private Customerviewmodel viewModdel;
    private ArrayList<FoodMenuDetails> foodmenuList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        rv = findViewById(R.id.rv2);
        fab = findViewById(R.id.fab2);

        rv.setLayoutManager(new LinearLayoutManager(this));

        final FoodMenuDetailAdapter adapter = new FoodMenuDetailAdapter();
        rv.setAdapter(adapter);

       String userid =FirebaseAuth.getInstance().getCurrentUser().getUid();
        viewModdel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(Customerviewmodel.class);

        viewModdel.getAllfooditemLive().observe(CustomerMain.this, new Observer<List<FoodMenuDetails>>() {
            @Override
            public void onChanged(List<FoodMenuDetails> foodMenuDetails) {

                adapter.setfoodmenudetailList(foodMenuDetails);

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerMain.this, Cart_view.class);


                startActivityForResult(intent,4);

            }
        });
        adapter.setListener(new FoodMenuDetailAdapter.onImageClickListener() {
            @Override
            public void onImageClick(FoodMenuDetails foodMenuDetails) {
                Intent intent = new Intent(CustomerMain.this,Add_Cart.class);
                intent.putExtra("item_id",foodMenuDetails.getItem_id());
                intent.putExtra("itemname",foodMenuDetails.getItemname());
                intent.putExtra("imageUrl",foodMenuDetails.getImageUrl());
                intent.putExtra("price",foodMenuDetails.getPrice());

                startActivityForResult(intent,3);
            }
        });

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

                startActivity(new Intent(CustomerMain.this, Orderhistory.class));
                finish();

                return true;
        }
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(CustomerMain.this, MainActivity.class));
                finish();

                return true;
        }
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        int storeid = selectedstoreid.getStore_id();

        if (requestCode == 3 && resultCode == RESULT_OK && data != null)
        {

            String itemname = data.getStringExtra("FoodName");
            String Qty = data.getStringExtra("Qty");
            String price = data.getStringExtra("Price");
            String remark = data.getStringExtra("Remark");
            String Serviceing = data.getStringExtra("Serviceing");
            int itemid = data.getIntExtra("item_id",0);
           Cart cart = new Cart(itemname,Qty,price,remark,Serviceing,itemid);



            viewModdel.AddCart(cart);
        }


    }




}


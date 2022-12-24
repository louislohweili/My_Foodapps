package com.example.my_foodapps;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.my_foodapps.Adapter.FoodMenuDetailAdapter;
import com.example.my_foodapps.model.FoodMenuDetails;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Food_menu_view extends AppCompatActivity {
    private RecyclerView rv;
    private FloatingActionButton fab;
    private Customerviewmodel viewModdel;
    private ArrayList<FoodMenuDetails> foodmenuList;
    private Store selectedstoreid;
    int store_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu_view);

        getid();

        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        rv.setLayoutManager(new LinearLayoutManager(this));

        final FoodMenuDetailAdapter adapter = new FoodMenuDetailAdapter();
        rv.setAdapter(adapter);

        viewModdel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(Customerviewmodel.class);

        viewModdel.getAllfooditemLive().observe(Food_menu_view.this, new Observer<List<FoodMenuDetails>>() {
            @Override
            public void onChanged(List<FoodMenuDetails> foodMenuDetails) {

                adapter.setfoodmenudetailList(foodMenuDetails);

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Food_menu_view.this,Add_Food_Menu.class);

                startActivityForResult(intent,3);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewModdel.deletefooditem(adapter.getPosition(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(rv);

        adapter.setListener(new FoodMenuDetailAdapter.onImageClickListener() {
            @Override
            public void onImageClick(FoodMenuDetails foodMenuDetails) {
                Intent intent = new Intent(Food_menu_view.this,Update_food_item.class);
                intent.putExtra("item_id",foodMenuDetails.getItem_id());
                intent.putExtra("itemname",foodMenuDetails.getItemname());
                intent.putExtra("imageUrl",foodMenuDetails.getImageUrl());
                intent.putExtra("price",foodMenuDetails.getPrice());
                intent.putExtra("rating",foodMenuDetails.getRating());
                startActivityForResult(intent,4);
            }
        });
    }

    private void getid() {
        Intent i = getIntent();
        store_id = i.getIntExtra("store_id",-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        int storeid = selectedstoreid.getStore_id();

        if (requestCode == 3 && resultCode == RESULT_OK && data != null)
        {
            FoodMenuDetails foodMenuDetails = new FoodMenuDetails();

            //foodMenuDetails.setStoreid(storeid);
            foodMenuDetails.setItemname(data.getStringExtra("itemname"));
            foodMenuDetails.setImageUrl(data.getByteArrayExtra("imageUrl"));
            foodMenuDetails.setPrice(data.getStringExtra("price"));
           foodMenuDetails.setRating(data.getStringExtra("rating"));

            //   foodMenuDetails.setStoreid(data.getIntExtra("storeid",0));
            foodMenuDetails.setStoreid(store_id);
            viewModdel.addfooditem(foodMenuDetails);
        }

        if (requestCode == 4 && resultCode == RESULT_OK && data != null)
        {
           // FoodMenuDetails foodMenuDetails = new FoodMenuDetails();
          //foodMenuDetails.setStoreid(store_id);

            String itemname = data.getStringExtra("UpdateFoodName");
            String price = data.getStringExtra("UpdatePrice");
            String rating = data.getStringExtra("UpdateRate");
            byte[] imageUrl = data.getByteArrayExtra("image");
           int item_id = data.getIntExtra("item_id",0);
            int storeid = store_id;

            FoodMenuDetails foodMenuDetails = new FoodMenuDetails(itemname,price,rating,imageUrl,storeid);
            foodMenuDetails.setItem_id(item_id);
            viewModdel.updatefooditem(foodMenuDetails);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:

                startActivity(new Intent(Food_menu_view.this, Orderview.class));
                finish();

                return true;
        }
        switch (item.getItemId()) {
            case R.id.logout:

                startActivity(new Intent(Food_menu_view.this, MainActivity.class));
                finish();

                return true;
        }
        return false;
    }


}
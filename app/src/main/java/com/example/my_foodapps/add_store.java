package com.example.my_foodapps;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class add_store extends AppCompatActivity {
    EditText StoreName,StoreOwnerName,address,email,password,cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Store Owner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_store);

         StoreName = findViewById(R.id.storename);
         StoreOwnerName = findViewById(R.id.storeowner);
         address = findViewById(R.id.addressN);
         email  = findViewById(R.id.email);
         password = findViewById(R.id.password);
         cpassword = findViewById(R.id.cpassword);
        Button add =  findViewById(R.id.add);
        ImageButton  back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing added", Toast.LENGTH_SHORT).show();
                finish ();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStore();

            }
        });


    }

    private void addStore() {
        String Storename =StoreName.getText().toString();
        String Fullname = StoreOwnerName.getText().toString();
        String addre =  address.getText().toString();
        String Email = email.getText().toString();
        String Pass = password.getText().toString();
        Intent i = new Intent();
        i.putExtra("storename",Storename);
        i.putExtra("storeownername" ,Fullname);
        i.putExtra("storeaddress",addre);
        i.putExtra("storeemail",Email);
        i.putExtra("pass",Pass);
        setResult(RESULT_OK,i);
        finish ();


    }



}
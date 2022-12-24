package com.example.my_foodapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Cart extends AppCompatActivity {
    private ImageView imageViewAddImage;
    Spinner foodserv;

    TextView foodname,price, qty,Serv;
    EditText remark;
    private Button buttonSave,add,less;
    private byte [] image;
    private Bitmap selectedImage;
    private Bitmap scaledImage;
    private ImageButton Back;
    private int mCounter = 0;
    int item_id;
    String[] service;

    double ans = 0;  // global variable
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);

        imageViewAddImage = findViewById(R.id.imageViewAddImage);
        foodname = findViewById(R.id.editTextfood);
        price = findViewById(R.id.editTextprice);
        qty = findViewById(R.id.qty);
        remark  = findViewById(R.id.editTextDescription);

        item_id = getIntent().getIntExtra("item_id",0);
        String itemname = getIntent().getStringExtra( "itemname");
        byte[] Image =  getIntent().getByteArrayExtra("imageUrl");
        String Price =getIntent().getStringExtra("price");
        String Rate = getIntent().getStringExtra("rating");
        foodname.setText(itemname);

        price.setText(Price);
        image = getIntent().getByteArrayExtra("imageUrl");
        buttonSave = findViewById(R.id.buttonSave);
        add = findViewById(R.id.button4);
        less = findViewById(R.id.button3);
        Back = findViewById(R.id.backimage);

        foodserv = findViewById(R.id.foodservic);
        Serv = findViewById(R.id.serv);


        service = getResources().getStringArray(R.array.service_arrays);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, service);
        foodserv.setAdapter(dataAdapter2);
        foodserv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String OpStates2 = service[position];

                Serv.setText("" + OpStates2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Serv.setText("Please select ops");
            }

        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter = mCounter + 1;
                qty.setText(Integer.toString(mCounter));
            }
        });
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCounter <= 0 ){
                    mCounter = 0;
                }else {
                    mCounter = mCounter - 1;

                    qty.setText(Integer.toString(mCounter));


                }


            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing added", Toast.LENGTH_SHORT).show();
                finish ();

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddata();

            }
        });
    }



    private void adddata() {

        Intent intent = new Intent();
        String FoodName = foodname.getText().toString();
        String Price = price.getText().toString();
       String Remark = remark.getText().toString();
        String Qty = qty.getText().toString();
        String Serviceing = Serv.getText().toString();


     //  TotoPrice = String.valueOf(ans);
       // totalprice.setText(ans);

        intent.putExtra("item_id",item_id);
        intent.putExtra("FoodName",FoodName);
        intent.putExtra("Qty",Qty);
        intent.putExtra("Price",Price);
        intent.putExtra("Remark",Remark);
        intent.putExtra("Serviceing",Serviceing);

        setResult(RESULT_OK, intent);
        finish();

    }



}
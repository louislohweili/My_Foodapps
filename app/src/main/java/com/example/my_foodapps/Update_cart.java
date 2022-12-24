package com.example.my_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Update_cart extends AppCompatActivity {
    private ImageView imageViewAddImage;
    TextView foodname,price, qty,Serv;

    Spinner foodserv;
    EditText remark;
    private Button Update,add,less;
    private byte [] image;
    private Bitmap selectedImage;
    private Bitmap scaledImage;
    private ImageButton Back;
    private int mCounter = 0;
    int cartsid;
    int item_id;
    double ans = 0;  // global variable

    String[] service;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cart);


        imageViewAddImage = findViewById(R.id.imageViewAddImage);
        foodname = findViewById(R.id.updatefood);
        price = findViewById(R.id.updateprice);
        qty = findViewById(R.id.updateqty);
        remark  = findViewById(R.id.updateTextDescription);

        cartsid = getIntent().getIntExtra("Cart_id",0);
        item_id = getIntent().getIntExtra("item_id",0);
        String itemname = getIntent().getStringExtra( "CartFoodName");

        String Remark = getIntent().getStringExtra("Remark");


        String Price =getIntent().getStringExtra("price");
        String Qty = getIntent().getStringExtra("Cartqty");

        foodname.setText(itemname);
        qty.setText(Qty);
        price.setText(Price);
        remark.setText(Remark);



        Update = findViewById(R.id.buttonupdate);
        add = findViewById(R.id.button1);
        less = findViewById(R.id.button2);
        Back = findViewById(R.id.backimage);

        foodserv = findViewById(R.id.upfoodservic);
        Serv = findViewById(R.id.upserv);


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
                Toast.makeText(getApplicationContext(),"Update added", Toast.LENGTH_SHORT).show();
                finish ();

            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updatedata();


            }
        });

    }

    private void updatedata() {
        if(cartsid == 0 && item_id == 0){
            Toast.makeText(Update_cart.this, "Data not Updated", Toast.LENGTH_LONG).show();


        } else {


            String oldsCartFoodname= foodname.getText().toString();
            String oldCartQty= qty.getText().toString();
            String oldCartprice = price.getText().toString();
            String oldRemark =remark.getText().toString();
            String Serviceing = Serv.getText().toString();

            double a = Double.parseDouble(price.getText().toString());




            Intent intent = new Intent();
            intent.putExtra("UpdatesCartFoodname",oldsCartFoodname);
            intent.putExtra("UpdateCartQty",oldCartQty);
            intent.putExtra("updateCartprice",oldCartprice);
            intent.putExtra("Remark",oldRemark);

            intent.putExtra("Serviceing",Serviceing);
            intent.putExtra("cartsid",cartsid);
            intent.putExtra("item_id",item_id);
            setResult(RESULT_OK,intent);
            finish();
            Toast.makeText(Update_cart.this, "Updated", Toast.LENGTH_LONG).show();
        }

    }


}
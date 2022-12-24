package com.example.my_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.my_foodapps.model.Store;

public class Update_store extends AppCompatActivity {
    private Store store;
    EditText StoreName,StoreOwnerName,address,email,password,cpassword;
    int store_id;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update store");
        setContentView(R.layout.activity_update_store);

        //store = new Store();

        StoreName = findViewById(R.id.ustorename);
        StoreOwnerName = findViewById(R.id.ustoreowner);
        address = findViewById(R.id.uaddressN);
        email  = findViewById(R.id.uemail);
        password = findViewById(R.id.upassword);
        cpassword = findViewById(R.id.ucpassword);
        Button update =  findViewById(R.id.update);
        ImageButton back = findViewById(R.id.back);
        getData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Nothing update", Toast.LENGTH_SHORT).show();
                finish ();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStore();

            }
        });

    }

    private void updateStore() {

        if(store_id == 0){
            Toast.makeText(Update_store.this, "Data not Updated", Toast.LENGTH_LONG).show();


        } else {


            String oldstorename = StoreName.getText().toString();
            String oldstoreownername = StoreOwnerName.getText().toString();
            String oldstoreaddress = address.getText().toString();
            String oldstoreemail =email.getText().toString();
            String oldpass = password.getText().toString();



            Intent intent = new Intent();
            intent.putExtra("updatestorename",oldstorename);
            intent.putExtra("updatestoreownername",oldstoreownername);
            intent.putExtra("updttaestoreaddress",oldstoreaddress);
            intent.putExtra("updatestoreemail",oldstoreemail);
            intent.putExtra("updtaepass",oldpass);

            intent.putExtra("store_id",store_id);
            setResult(RESULT_OK,intent);
            finish();
            Toast.makeText(Update_store.this, "Updated", Toast.LENGTH_LONG).show();
        }


    }
    private void getData(){
        Intent i = getIntent();

        store_id = i.getIntExtra("store_id" ,0);
        String Storename =   i.getStringExtra("storename");
        String Storeownername =   i.getStringExtra("storeownername");
        String Storeaddress =   i.getStringExtra("storeaddress");
        String Storeemail =   i.getStringExtra("storeemail");
        String Pass =   i.getStringExtra("pass");


        StoreName.setText(Storename);
        StoreOwnerName.setText(Storeownername);
        address.setText(Storeaddress);
        email.setText(Storeemail);
        password.setText(Pass);




    }
}
package com.example.my_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateOrderprog extends AppCompatActivity {

    EditText Name , Address , Phone ;
    TextView  Totalacc ,payment, work;
    Button Process,back;
    Spinner spinner;
    int orderid;

    String [] sta;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_orderprog);

        Name = findViewById(R.id.editTextTextPersonName1);
        Address =findViewById(R.id.editTextTextAddress1);
        Phone = findViewById(R.id.editTextPhone1);
        Totalacc= findViewById(R.id.rateview3);
        payment = findViewById(R.id.textViewpay1);
        work = findViewById(R.id.statu);
        orderid = getIntent().getIntExtra("Order_id",0);
        String FullName  =getIntent().getStringExtra("FullName");
        String Add = getIntent().getStringExtra("Address");
        String phone = getIntent().getStringExtra("Phoneno");
        String Total = getIntent().getStringExtra("Totalacc");
        String typepay =getIntent().getStringExtra("payment");
        String statu = getIntent().getStringExtra("status");
        Name.setText(FullName);
        Address.setText(Add);
        Phone.setText(phone);
        Totalacc.setText(Total);
        payment.setText(typepay);
        work.setText(statu);
        back = findViewById(R.id.backmain);
        Process = findViewById(R.id.process);
        spinner = findViewById(R.id.spinstate);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String OpStates2 = sta [position];

                work.setText("" + OpStates2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                work.setText("Please select ops");
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing Update", Toast.LENGTH_SHORT).show();
                finish ();
            }
        });

        Process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(orderid == 0){
                    Toast.makeText(UpdateOrderprog.this, "Data not Updated", Toast.LENGTH_LONG).show();


                } else {


                    String oldfullname = Name.getText().toString();
                    String oldAddress = Address.getText().toString();
                    String oldphone = Phone.getText().toString();
                    String oldTotalacc =Totalacc.getText().toString();
                    String oldpayment = payment.getText().toString();
                    String oldwork = work.getText().toString();



                    Intent intent = new Intent();
                    intent.putExtra("updateFullname",oldfullname);
                    intent.putExtra("updateAddress",oldAddress);
                    intent.putExtra("updatephoneno",oldphone);
                    intent.putExtra("updateTotal",oldTotalacc);
                    intent.putExtra("updatepayment",oldpayment);
                    intent.putExtra("updatestatus",oldwork);

                    intent.putExtra("Order_id",orderid);
                    setResult(RESULT_OK,intent);
                    finish();
                    Toast.makeText(UpdateOrderprog.this, "Updated", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}
package com.example.my_foodapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.example.my_foodapps.model.Cart;
import com.example.my_foodapps.model.Order;
import com.example.my_foodapps.viewmodel.Customerviewmodel;

import java.util.ArrayList;

public class Order_Checkout extends AppCompatActivity {
    private Customerviewmodel viewmodel;
    EditText name, address, phone;
    TextView viewacc, pay;
    Spinner payment;
    Button check;

    ImageButton google;
    String State;
    String[] payby;
    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;
    final int UPI_PAYMENT = 0;
    String names = "louis";
    String upiId = "@louis12";
    String transactionNote = "pay test";
    String status;
    Uri uri;

    private static ArrayList<Cart> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_checkout);
        name = findViewById(R.id.editTextTextPersonName);
        address = findViewById(R.id.editTextTextAddress);
        phone = findViewById(R.id.editTextPhone);
        payment = findViewById(R.id.payment);
        viewacc = findViewById(R.id.rateview2);
        check = findViewById(R.id.check);
        pay = findViewById(R.id.textViewpay);

        viewmodel = new ViewModelProvider(this).get(Customerviewmodel.class);
        String total = getIntent().getStringExtra("Total");
        //Log.e("total"+total);
        viewacc.setText("Total Amount :$ " + total);
        State = "Progran-in";

        google = findViewById(R.id.googlePayButton);


        payby = getResources().getStringArray(R.array.paymentType_arrays);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, payby);
        payment.setAdapter(dataAdapter2);
        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String OpStates2 = payby[position];

                pay.setText("" + OpStates2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pay.setText("Please select ops");
            }

        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payWithGPay();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Fullname = name.getText().toString();
                String Address = address.getText().toString();
                String phoneno = phone.getText().toString();
                String totalacc = viewacc.getText().toString();

                String TypePayment = pay.getText().toString();

                State = "Progran-in";

                Order order = new Order(Fullname, Address, phoneno, totalacc, TypePayment, State);
                viewmodel.AddOrder(order);
                Intent i = new Intent(Order_Checkout.this, CustomerMain.class);
                Toast.makeText(Order_Checkout.this, "the order have make", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }

    private void payWithGPay() {
        if (isAppInstalled(this, GOOGLE_PAY_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
        } else {
            Toast.makeText(Order_Checkout.this, "Please Install GPay", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private static Uri getUpiPaymentUri(String name, String upiId, String transactionNote, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", transactionNote)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "Sgd")
                .build();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }

        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(Order_Checkout.this, "Transaction Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Order_Checkout.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
        }

    }
}
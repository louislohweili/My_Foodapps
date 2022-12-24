package com.example.my_foodapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Customer;

import com.example.my_foodapps.model.CustomerDao;
import com.example.my_foodapps.viewmodel.Customerviewmodel;

import java.util.regex.Pattern;

public class Update_customer extends AppCompatActivity {

    private Customerviewmodel customerviewmodel;
    private Customer customer;
    private CustomerDao customerDao;
    private AppDatabase customerAppDatabase;
   //EditText fullname,address,email,phone,pass,cpass;
    int Custid;
    public static final String ID = "cust_id";
    public static final String Name = "fullname";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";
    public static final String PHONE = "phoneno";
    public static final String PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Customer");
        setContentView(R.layout.activity_update_customer);
        getData();

        customerviewmodel = new ViewModelProvider(this).get(Customerviewmodel.class);



        EditText fullname = (EditText) findViewById(R.id.updatefullname);
        EditText address  = (EditText)findViewById(R.id.updateaddressN);

        EditText email  = (EditText)findViewById(R.id.updateemail);
        EditText  phone  = (EditText)findViewById(R.id.updatephoneno);
        EditText password  = (EditText)findViewById(R.id.updatepass);
        EditText cpass  = (EditText)findViewById(R.id.updatecpass);


        ImageButton backbutton = findViewById(R.id.uback);
        Button add =  findViewById(R.id.update);
        customer = new Customer();

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing added", Toast.LENGTH_SHORT).show();
                finish ();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcustomer();

/**
                String strfName = fullname.getText().toString().trim();
                String straddress = address.getText().toString().trim();
                String Strphone = phone.getText().toString().trim();

                //PhoneNumberUtils.formatNumber(Strphone.toString());
                String StrEmail = email.getText().toString();


                // defining our own password pattern
                final Pattern PASSWORD_PATTERN =
                        Pattern.compile("^" +
                                "(?=.*[@#$%^&+=])" +     // at least 1 special character
                                "(?=\\S+$)" +            // no white spaces
                                ".{4,}" +                // at least 4 characters
                                "$");


                String StrPassword = password.getText().toString().trim();


                String StrConfirmPassword = cpass.getText().toString();
                if (TextUtils.isEmpty(strfName)) {
                    fullname.setError("Name field cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(straddress)) {
                    address.setError("Name field cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(Strphone) ) {
                    phone.setError("Name field cannot be empty");
                    return;
                }


                if (TextUtils.isEmpty(StrPassword) ) {
                    password.setError("Password cannot be empty  ");
                } else if (!PASSWORD_PATTERN.matcher(StrPassword).matches()) {
                    password.setError("Password is too weak");
                    return;
                }

                if (!StrPassword.equals(StrConfirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                                    "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(StrEmail)) {
                    email.setError("Event Name  field cannot be empty");
                    return;

                } else if (!Patterns.EMAIL_ADDRESS.matcher(StrEmail).matches()) {
                    email.setError("Please enter a valid email address");
                    return;
                }
 **/
            }



        });


    }

    public void addcustomer() {
        EditText ofullname = (EditText) findViewById(R.id.updatefullname);
        EditText oaddress  = (EditText)findViewById(R.id.updateaddressN);

        EditText oemail  = (EditText)findViewById(R.id.updateemail);
        EditText ophone  = (EditText)findViewById(R.id.updatephoneno);
        EditText opassword  = (EditText)findViewById(R.id.updatepass);
        EditText cpass  = (EditText)findViewById(R.id.updatecpass);



        if (Custid != -1 ){

            Toast.makeText(Update_customer.this, "There ia a problem!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            String oFullname =ofullname.getText().toString();
            String oAddress  = oaddress.getText().toString();
            String oEmail =  oemail.getText().toString();
            String ophoneno = ophone.getText().toString();
            String opass = opassword.getText().toString();
            intent.putExtra("ofullname", oFullname);
            intent.putExtra("oaddress",oAddress);
            intent.putExtra("oemail", oEmail);
            intent.putExtra("ophoneno", ophoneno);
            intent.putExtra("opass", opass);


            intent.putExtra("custid",Custid);
        //    Log.v("tag", customer.getFullname());

            setResult(RESULT_OK, intent);
            finish();
        }




    }




    private void getData(){
        Intent i = getIntent();
        EditText fullname = (EditText) findViewById(R.id.updatefullname);
        EditText address  = (EditText)findViewById(R.id.updateaddressN);

        EditText email  = (EditText)findViewById(R.id.updateemail);
        EditText  phone  = (EditText)findViewById(R.id.updatephoneno);
        EditText password  = (EditText)findViewById(R.id.updatepass);


       Custid = i.getIntExtra("Cust_id ",-1);
        String Fullname =  i.getStringExtra("Fullname");
        String Address =   i.getStringExtra("Address");
        String Email =   i.getStringExtra("Email");
        String phoneno =   i.getStringExtra("Phoneno");
        String Password =   i.getStringExtra("Password");

        fullname.setText(Fullname);
        address.setText(Address);
        email.setText(Email);
        phone.setText(phoneno);
        password.setText(Password);




    }

}
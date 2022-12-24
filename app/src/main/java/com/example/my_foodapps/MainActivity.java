package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Customer;

import com.example.my_foodapps.model.CustomerDao;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.model.StoreDao;
import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Customer customer;
    //private LoginActivityClickHandlers clickHandlers;

    private Customerviewmodel customerviewmodel;

    private AppDatabase customerAppDatabase;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customer = new Customer();


        customerviewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(Customerviewmodel.class);

        EditText mEmail = findViewById(R.id.email);
        EditText mPassword = findViewById(R.id.password);

        //clickHandlers = new LoginActivityClickHandlers(this);
        //binding.setClickHandler(clickHandlers);



        //image
        ImageView image = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.foodicon2).into(image);

        Button BTregister = findViewById(R.id.register);
        Button btlogin = findViewById(R.id.log_in);

        Button btforgot = findViewById(R.id.forgot);
        btforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Forgotpass.class);
                startActivity(i);
            }
        });


        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = null;
                String Email = mEmail.getText().toString();
                String Password = mPassword.getText().toString();

                signup(Email,Password);


                if (TextUtils.isEmpty(Email)) {
                    mEmail.setError("UserName field cannot be empty");
                    mEmail.requestFocus();
                } else if (TextUtils.isEmpty(Password)) {
                    mPassword.setError("Password cannot be empty");
                    mPassword.requestFocus();
                }
                }









        });
        BTregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy  = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(toy);
            }
        });
    }


    private void signup(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if(user.isEmailVerified()){
                                // Sign in success, update UI with the signed-in user's information
                                Intent toy = new Intent(MainActivity.this, Otp.class);
                                startActivity(toy);

                            }else {
                                Toast.makeText(MainActivity.this,"Please verify your email address ", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "invaild user", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_login:
                startActivity(new Intent(MainActivity.this, login.class));

                return true;
        }
        return false;
    }

/**
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= auth.getCurrentUser();
        if(user !=null ){
            Intent toy = new Intent(MainActivity.this, CustomerMain.class);
            startActivity(toy);
            finish();
        }

    }
**/

}



package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.CustomerDao;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.model.StoreDao;
import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class login extends AppCompatActivity {
    EditText Emall, Password;
    Button Login ;
    //  private ActivityRegisterBinding activityRegisterBinding;
    private Customerviewmodel viewModel;
   // FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(Customerviewmodel.class);

        Emall = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Login = findViewById(R.id.log_in);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Emall.getText().toString();
                String password = Password.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    Emall.setError("UserName field cannot be empty");
                    Emall.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    Password.setError("Password cannot be empty");
                    Password.requestFocus();
                } else if (Emall.getText().toString().equals("admin") &&
                        Password.getText().toString().equals("admin")) {

                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    final ProgressDialog progressDialog = new ProgressDialog(login.this,
                            R.style.AppTheme_AppBarOverlay);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Welcome to adminstrator...");
                    progressDialog.show();
                    Intent toy = new Intent(login.this, AdminMain.class);
                    startActivity(toy);

                } else {
                   AppDatabase customerAppDatabase = AppDatabase.getInstance(getApplicationContext());
                 //   CustomerDao customerDao = customerAppDatabase.customerDao();
                    StoreDao storeDao = customerAppDatabase.storeDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            Store store = storeDao.login(Emall.getText().toString(), Password.getText().toString());
                          //  LiveData<List<Store>> store = viewModel.getLogin(Emall.getText().toString(), Password.getText().toString());

                            if (store == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "invail user", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            } else {
                                Intent toy = new Intent(login.this, Food_menu_view.class);
                                toy.putExtra("store_id", store.getStore_id());
                                //  toy.putExtra("Email", mEmail.getText().toString());

                                startActivityForResult(toy,1);

                            }


                        }


                    }).start();

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
                startActivity(new Intent(login.this, MainActivity.class));

                return true;
        }
        return false;
    }

}
package com.example.my_foodapps;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.databinding.Bindable;
//import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Customer;

import com.example.my_foodapps.model.CustomerDao;
import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.FirestoreGrpc;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    //googole api
    GoogleApiClient googleApiClient;
    String SITE_KEY = "6Leq48ciAAAAAPiD5aVhZHznZovbgOhfzoHGjrw_";
  //  private ActivityRegisterBinding activityRegisterBinding;
    private Customerviewmodel customerviewmodel;
    private Customer customer;
    private CustomerDao customerDao;
    private AppDatabase customerAppDatabase;
    private FirebaseFirestore db =  FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Customers");
    EditText fName,address,Email,phone,pass,cpass;
    CheckBox checkBox;

    private  FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        customer = new Customer();

      //  customerviewmodel = ViewModelProvider.of(RegisterActivity.this).get(Customerviewmodel.class);

        customerviewmodel = new ViewModelProvider(this).get(Customerviewmodel.class);
       // customerviewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(Customerviewmodel.class);
        Button register = findViewById(R.id.add);

         fName = findViewById(R.id.fullname);
         address  = findViewById(R.id.addressN);

         Email  = findViewById(R.id.email);
         phone  = findViewById(R.id.phoneno);
         pass  = findViewById(R.id.password);
         cpass  = findViewById(R.id.cpassword);
         checkBox = findViewById(R.id.checkbox);

        ImageButton backbutton = findViewById(R.id.back);
        Button add =  findViewById(R.id.add);


        CheckBox checkBox = findViewById(R.id.checkbox);
        googleApiClient = new GoogleApiClient.Builder(RegisterActivity.this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(RegisterActivity.this)
                .build();
        googleApiClient.connect();

        //Authentication
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null){

                }else {

                }
            }
        };

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check condition when  checkbox checked
                if (checkBox.isChecked()) {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SITE_KEY)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if ((status != null) && status.isSuccess()) {
                                        //display success
                                        Toast.makeText(getApplicationContext()
                                                , "Succeefully Vairfied..",
                                                Toast.LENGTH_SHORT).show();

                                        checkBox.setTextColor(Color.GREEN);
                                    }
                                }

                            });
                } else {
                    checkBox.setTextColor(Color.BLACK);
                }


            }
        });





        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(toy);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addcustomer();


                String Fullname = fName.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String phoneno = phone.getText().toString().trim();
                String Pass = pass.getText().toString().trim();
                //PhoneNumberUtils.formatNumber(Strphone.toString());
                String email = Email.getText().toString();
                signup(email,Pass,phoneno,Fullname,Address);

                // defining our own password pattern
                final Pattern PASSWORD_PATTERN =
                        Pattern.compile("^" +
                                "(?=.*[@#$%^&+=])" +     // at least 1 special character
                                "(?=\\S+$)" +            // no white spaces
                                ".{4,}" +                // at least 4 characters
                                "$");





                String StrConfirmPassword = cpass.getText().toString();
                if (TextUtils.isEmpty(Fullname)) {
                    fName.setError("Name field cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(Address)) {
                    address.setError("Name field cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(phoneno) ) {
                    phone.setError("Name field cannot be empty");
                    return;
                }


                if (TextUtils.isEmpty(Pass) || pass.getText().length() < 10) {
                    pass.setError("Password cannot be less than 10 characters! ");
                } else if (!PASSWORD_PATTERN.matcher(Pass).matches()) {
                    pass.setError("Password is too weak");
                    return;
                }

                if (!Pass.equals(StrConfirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                                    "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Event Name  field cannot be empty");
                    return;

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Email.setError("Please enter a valid email address");
                    return;
                }
                if (checkBox.isChecked() == false) {
                   checkBox.setError("pleace kindly checkbok");
                   return;

                }
            }



        });


    }
/**
    private void addcustomer() {

        //on below line we are creating a new user using parse user.
        ParseUser user = new ParseUser();



        String Fullname =fName.getText().toString();
        String Address  = address.getText().toString();
        String email =  Email.getText().toString();
        String phoneno = phone.getText().toString();
        String Pass = pass.getText().toString();

        user.setUsername(email);
        user.setEmail(email);

        Customer customer = new Customer(Fullname,Address,email,phoneno);
        customerviewmodel.insertcustomer(customer);

       // Toast.makeText(RegisterActivity.this, "User Registered successfully \n Please verify your email", Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(RegisterActivity.this, MainActivity.class);

       // startActivity(i);




    }

 **/
    public void signup(String email,String Pass,final String phoneno,final String Fullname,final String Addess ){
        auth.createUserWithEmailAndPassword(email,Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();

                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent  intent =new Intent(RegisterActivity.this, MainActivity.class);
                                                //Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                                                startActivity(intent);
                                                finish();



                                                Toast.makeText(RegisterActivity.this, "YOur account have been  created",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });





                            currentUser = auth.getCurrentUser();
                            assert  currentUser !=null;
                            final String currentUserId = currentUser.getUid();
                            Map<String,String> customerObj = new HashMap<>();

                            customerObj.put("Cut_id",currentUserId);
                            customerObj.put("Fullname",Fullname);
                            customerObj.put("phoneno",phoneno);

                            customerObj.put("Address",Addess);

                            collectionReference.add(customerObj)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            documentReference.get()
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                            if(Objects.requireNonNull(task.getResult().exists())){
                                                                String Name = task.getResult().getString("Fullname");

                                                                String phone = task.getResult().getString("phoneno");
                                                                String address = task.getResult().getString("Address");

                                                                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                                                startActivity(i);
                                                                finish();
                                                                i.putExtra("Fullname",Name);
                                                                i.putExtra("phoneno",phone);
                                                                i.putExtra("Address",address);
                                                            }else {

                                                            }

                                                        }
                                                    });
                                        }
                                    });



                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this,
                                    "YOur account have been  problem",Toast.LENGTH_SHORT);

                        }
                    }
                });
    }





    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }
    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    protected void onStart() {
        super.onStart();

       currentUser = auth.getCurrentUser();
        auth.addAuthStateListener(authStateListener);
    }
}
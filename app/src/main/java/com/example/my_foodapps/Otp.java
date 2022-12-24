package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity {
    EditText phoneno, otpno;
    Button Send, get;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String codeSent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        phoneno = findViewById(R.id.phone);
        otpno= findViewById(R.id.otptext);
        Send = findViewById(R.id.send);
        get = findViewById(R.id.vbutton);







        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String phone = phoneno.getText().toString();
                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phone)
                        .setTimeout(60l, TimeUnit.SECONDS)
                        .setActivity(Otp.this)
                        .setCallbacks(mCallbacks)
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);

            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signWithPhonecode();

            }
        });


    }



    public void signWithPhonecode(){
        String otp = otpno.getText().toString();
        PhoneAuthCredential credential  = PhoneAuthProvider.getCredential(codeSent,otp);
        signinWithPhoneAuthCredential(credential);
    }

    public void signinWithPhoneAuthCredential(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent i = new Intent(Otp.this,CustomerMain.class);
                            startActivity(i);
                            finish();

                        }else {
                            Toast.makeText(Otp.this, "Please kindly check OTP", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;

        }
    };
}
package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Button customerlist = findViewById(R.id.button);
        customerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(AdminMain.this, storeview.class);
                startActivity(toy);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Main:
                startActivity(new Intent(AdminMain.this, AdminMain.class));

                return true;

         //   case R.id.menu_Manage:
             //   startActivity(new Intent(AdminMain.this, CustomerView.class));

           //     return true;

            case R.id.menu_ListData:
                startActivity(new Intent(AdminMain.this, storeview.class));

                return true;
            case R.id.menu_Logout:
                startActivity(new Intent(AdminMain.this, MainActivity.class));

                final ProgressDialog progressDialog = new ProgressDialog(AdminMain.this,
                        R.style.AppTheme_PopupOverlay);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Logout ...");
                progressDialog.show();

                return true;


        }
        return false;
    }
}
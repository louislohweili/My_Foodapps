package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.CustomerDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.my_foodapps.Adapter.CutomerDataAdapter;

import com.example.my_foodapps.model.Customer;

import com.example.my_foodapps.viewmodel.Customerviewmodel;

import java.util.List;

public class CustomerView extends AppCompatActivity {
    private AppDatabase customerAppDatabase;
  //  private ArrayList<Customer> customers = new ArrayList<>();
    private CutomerDataAdapter cutomerDataAdapter;
    private Customerviewmodel customerviewmodel;




    //FloatingActionButton  add;
    Customer customer;


    private RecyclerView recyclerView;

    public int selectedCustomerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_customer_view);
      RecyclerView recyclerView = findViewById(R.id.rv2);
   //   add = findViewById(R.id.floatingActionButton);

      recyclerView.setLayoutManager(new LinearLayoutManager(this));

      CutomerDataAdapter adapter = new CutomerDataAdapter();
      recyclerView.setAdapter(adapter);


      customerviewmodel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
              .create(Customerviewmodel.class);
      customerviewmodel.getAllCustomerLive().observe(this, new Observer<List<Customer>>() {
        @Override
        public void onChanged(List<Customer> customers) {
          adapter.setCustomers(customers);

          }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
          @Override
          public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
          }

          @Override
          public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            customerviewmodel.deletecustomer(adapter.getcustomer(viewHolder.getAdapterPosition()));
            Toast.makeText(getApplication(), "Store data have delete", Toast.LENGTH_SHORT).show();

          }
        }).attachToRecyclerView(recyclerView);
/**
      // EDIT THE customer
      adapter.setOnitemClickListener(new CutomerDataAdapter.OnItemClickListener() {
        @Override
        public void OnItemClick(Customer customer) {
          Intent intent = new Intent(CustomerView.this,Update_customer.class);
          intent.putExtra("Cust_Id",customer.getCust_id());
          intent.putExtra("Fullname",customer.getFullname());
          intent.putExtra("Address",customer.getAddress());
          intent.putExtra("Email",customer.getEmail());
          intent.putExtra("Phoneno",customer.getPhoneno());
          intent.putExtra("Password",customer.getPassword());

          startActivityForResult(intent,2);
        }
      });

      add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(CustomerView.this, Add_Register.class);
          startActivityForResult(intent,1);
        }
      });

    }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
      String fullname = data.getStringExtra("fullname");
      String address = data.getStringExtra("address");
      String email = data.getStringExtra("email");
      String phoneno = data.getStringExtra("phoneno");

      String pass = data.getStringExtra("password");

      Customer customer = new Customer(fullname, address, email,phoneno, pass);
      customerviewmodel.insertcustomer(customer);



    }else  if (requestCode == 2 && resultCode == RESULT_OK && data != null )  {
      AppDatabase customerAppDatabase = AppDatabase.getInstance(getApplicationContext());
      CustomerDao customerDao = customerAppDatabase.customerDao();

        String fullname = data.getStringExtra("ofullname");
        String address = data.getStringExtra("oaddress");
        String email = data.getStringExtra("oemail");
        String phoneno = data.getStringExtra("ophoneno");
        String password = data.getStringExtra("opass");
        int cust_id = data.getIntExtra("custid",-1);

        Customer customer = new Customer(fullname, address, email, phoneno, password);
        customer.setCust_id(cust_id);
       customerviewmodel.updatecustomer(customer);






    }
 **/


  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.admin_menu, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
    //  case R.id.menu_Main:
       // startActivity(new Intent(CustomerView.this, AdminMain.class));

       // return true;

      case R.id.menu_Manage:
        startActivity(new Intent(CustomerView.this, CustomerView.class));

        return true;

      case R.id.menu_ListData:
        startActivity(new Intent(CustomerView.this, storeview.class));

        return true;
      case R.id.menu_Logout:
        startActivity(new Intent(CustomerView.this, MainActivity.class));

        final ProgressDialog progressDialog = new ProgressDialog(CustomerView.this,
                R.style.AppTheme_PopupOverlay);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logout ...");
        progressDialog.show();

        return true;


    }
    return false;
  }

  }








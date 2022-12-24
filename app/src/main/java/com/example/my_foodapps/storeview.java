package com.example.my_foodapps;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchUIUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.my_foodapps.Adapter.StoreDataAdapter;
import com.example.my_foodapps.model.Store;
import com.example.my_foodapps.viewmodel.Customerviewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class storeview extends AppCompatActivity {
    private Customerviewmodel customerviewmodel;
    ActivityResultLauncher<Intent> activityResultLaunchereditstore;
    FloatingActionButton  add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeview);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.floatingActionButton);
        //registerActivityeditstore();
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        StoreDataAdapter adapter = new StoreDataAdapter();
        recyclerView.setAdapter(adapter);




        customerviewmodel  = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(Customerviewmodel.class);
        customerviewmodel.getAllStoreLive().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> stores) {
                adapter.setStores(stores);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                customerviewmodel.deletestore(adapter.getStore(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplication(), "Store data have delete", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);




        adapter.setOnitemClickListener(new StoreDataAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Store store) {
                Intent intent = new Intent(storeview.this,Update_store.class);
                intent.putExtra("store_id",store.getStore_id());
                intent.putExtra("storename",store.getStorename());
                intent.putExtra("storeownername",store.getStoreownername());
                intent.putExtra("storeaddress",store.getStoreaddress());
                intent.putExtra("storeemail",store.getStoreemail());
                intent.putExtra("pass",store.getPass());
               // activityResultLaunchereditstore.launch(intent);
                startActivityForResult(intent,4);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(storeview.this, add_store.class);
                startActivityForResult(intent,3);
            }
        });
    }

    /**
public  void registerActivityeditstore(){
    activityResultLaunchereditstore
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int resultCode = result.getResultCode();
            Intent data = result.getData();
            if ( resultCode == RESULT_OK && data != null){
                String storename = data.getStringExtra("updatestorename");
                String storeownername = data.getStringExtra("updatestoreownername");
                String storeaddress = data.getStringExtra("updttaestoreaddress");
                String storeemail = data.getStringExtra("updatestoreemail");
                String pass = data.getStringExtra("updtaepass");
                int store_id = data.getIntExtra("store_id",-1);

                Store store = new Store(storename,storeownername,storeaddress,storeemail,pass);
                store.setStore_id(store_id);
                customerviewmodel.update(store);
            }
        }
    });
}

     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3 && resultCode == RESULT_OK && data != null)
        {
            String storename = data.getStringExtra("storename");
            String storeownername = data.getStringExtra("storeownername");
            String storeaddress = data.getStringExtra("storeaddress");
            String storeemail = data.getStringExtra("storeemail");
            String pass = data.getStringExtra("pass");

            Store store = new Store(storename,storeownername,storeaddress,storeemail,pass);
            customerviewmodel.insertstore(store);


        }
        else  if (requestCode == 4  && resultCode == RESULT_OK ){
            String storename = data.getStringExtra("updatestorename");
            String storeownername = data.getStringExtra("updatestoreownername");
            String storeaddress = data.getStringExtra("updttaestoreaddress");
            String storeemail = data.getStringExtra("updatestoreemail");
            String pass = data.getStringExtra("updtaepass");
            int store_id = data.getIntExtra("store_id",0);;

          Store store = new Store(storename,storeownername,storeaddress,storeemail,pass);
         store.setStore_id(store_id);
          customerviewmodel.update(store);
        }





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
                startActivity(new Intent(storeview.this, AdminMain.class));

                return true;

           // case R.id.menu_Manage:
                //startActivity(new Intent(storeview.this, CustomerView.class));

               // return true;

            case R.id.menu_ListData:
                startActivity(new Intent(storeview.this, storeview.class));

                return true;
            case R.id.menu_Logout:
                startActivity(new Intent(storeview.this, MainActivity.class));

                final ProgressDialog progressDialog = new ProgressDialog(storeview.this,
                        R.style.AppTheme_PopupOverlay);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Logout ...");
                progressDialog.show();

                return true;


        }
        return false;
    }
}
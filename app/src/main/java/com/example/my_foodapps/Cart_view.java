package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_foodapps.Adapter.CartAdapter;
import com.example.my_foodapps.Adapter.CutomerDataAdapter;
import com.example.my_foodapps.model.AppDatabase;
import com.example.my_foodapps.model.Cart;
import com.example.my_foodapps.viewmodel.Customerviewmodel;

import java.util.List;

public class Cart_view extends AppCompatActivity {
    private AppDatabase customerAppDatabase;
    //  private ArrayList<Customer> customers = new ArrayList<>();
    private CutomerDataAdapter cutomerDataAdapter;
    private Customerviewmodel viewModwl;
    private RecyclerView recyclerView;
    private Button check;
    int sum= 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

      TextView rateview=findViewById(R.id.rateview2);
      check =  findViewById(R.id.checkout);


    recyclerView = findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CartAdapter adapter = new CartAdapter();
        recyclerView.setAdapter(adapter);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  total = rateview.toString();
                Intent i  = new Intent(Cart_view.this , Order_Checkout.class);

                String pay = String.valueOf(+sum);
                i.putExtra("Total",pay);
                startActivity(i);
            }
        });

        viewModwl = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(Customerviewmodel.class);
        viewModwl.getallcart().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {

                adapter.setcart(carts);




                int i;


                for(i=0;i< carts.size();i++)
                    sum= (int) (sum+(Float.parseFloat(carts.get(i).getCarttPrice()) *Float.parseFloat(carts.get(i).getCartqty())));

                rateview.setText("Total Amount :$ "+sum);


            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewModwl.DelCart(adapter.getcart(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplication(), "Store data have delete", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setListener(new  CartAdapter.onImageClickListener() {
            @Override
            public void onImageClick( Cart cart) {
                Intent intent = new Intent(Cart_view.this,Update_cart.class);
                intent.putExtra("Cart_id",cart.getCart_id());
                intent.putExtra("CartFoodName",cart.getCartFoodName());
                intent.putExtra("Cartqty",cart.getCartqty());
                intent.putExtra("price",cart.getCarttPrice());
                intent.putExtra("Remark",cart.getRemark());
                intent.putExtra("Serviceing",cart.getTypeservice());
                intent.putExtra("item_id",cart.getItemid());
                startActivityForResult(intent,5);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 5  && resultCode == RESULT_OK ){
            String cartFoodName = data.getStringExtra("UpdatesCartFoodname");
            String cartqty = data.getStringExtra("UpdateCartQty");
            String carttPrice = data.getStringExtra("updateCartprice");
            String remark = data.getStringExtra("Remark");
            String Serviceing = data.getStringExtra("Serviceing");

            int cartsid = data.getIntExtra("cartsid",0);;
            int item_id = data.getIntExtra("item_id",0);;
            Cart cart = new Cart(cartFoodName,cartqty,carttPrice,remark,Serviceing,item_id);
            cart.setCart_id(cartsid);
            viewModwl.updateCart(cart);
        }





    }
    }



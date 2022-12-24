package com.example.my_foodapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_foodapps.R;
import com.example.my_foodapps.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartDetailView> {
    TextView rateview;
    private List<Cart> carts = new ArrayList<>();
    private CartAdapter.onImageClickListener listener;

    public CartAdapter() {
        this .rateview= rateview;
        this.carts =carts;

    }

    public void setListener(CartAdapter.onImageClickListener listener) {
        this.listener = listener;
    }



    public void setcart(List<Cart> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }
    public interface onImageClickListener
    {
        void onImageClick(Cart cart);
    }

    @NonNull
    @Override
    public cartDetailView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);

        return new CartAdapter.cartDetailView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartDetailView holder, int position) {
        Cart cart = carts.get(position);


        holder.textfoodname.setText(cart.getCartFoodName());
        holder.textqty.setText ("Qty :"+ cart.getCartqty());
        holder.textremark.setText("Remark"+cart.getRemark());
        holder.textprice.setText("Price" + "$ " +cart.carttPrice);
        holder.textserving.setText("service :"+ cart.typeservice);
    }

    public Cart getcart (int position)
    {
        return carts.get(position);
    }



    @Override
    public int getItemCount() {
        return carts.size();
    }

    class cartDetailView extends RecyclerView.ViewHolder{

        private TextView textfoodname,textremark,textprice ,textqty,textserving;
        public cartDetailView(@NonNull View itemView) {
            super(itemView);

            textfoodname = itemView.findViewById(R.id.foodname);
            textqty= itemView.findViewById(R.id.qty);
            textremark = itemView.findViewById(R.id.Remark);
            textprice =itemView.findViewById(R.id.price);
            textserving = itemView.findViewById(R.id.service);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onImageClick(carts.get(position));
                    }
                }
            });

        }
    }
}
